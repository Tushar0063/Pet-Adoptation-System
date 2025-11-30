package com.adoption.platform.service;

import com.adoption.platform.model.Pet;
import com.adoption.platform.dao.PetDAO;
import com.adoption.platform.exception.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import com.adoption.platform.service.EmailNotification;

// Fulfills the EntityManagement contract
public class PetService implements EntityManagement<Pet> {
    
    private final PetDAO petDAO = new PetDAO(); 

    @Override // Pet listing logic (Shelter functionality)
    public void create(Pet pet) {
        pet.setAdoptionStatus("Pending"); // Business Rule
        try {
            petDAO.save(pet); 
        } catch (SQLException e) {
            System.err.println("Database error during pet creation: " + e.getMessage());
        }
    }
    
    @Override // Pet retrieval logic (Adopter browsing)
    public Pet getById(int id) throws EntityNotFoundException {
        try {
            Pet pet = petDAO.findById(id);
            if (pet == null) {
                // Throwing the custom checked exception
                throw new EntityNotFoundException("Pet ID " + id + " not found.");
            }
            return pet;
        } catch (SQLException e) {
            throw new RuntimeException("Database error retrieving Pet ID: " + id, e);
        }
    }
    
    // --- Admin Functionality: Approval Logic ---
    public void approveListing(int petId, String shelterEmail) throws EntityNotFoundException {
        Pet pet = getById(petId); // Uses the exception logic above
        
        if ("Pending".equalsIgnoreCase(pet.getAdoptionStatus())) {
            try {
                petDAO.updateStatus(petId, "Available");
                
                // MULTITHREADING INTEGRATION: Send notification asynchronously
                String msg = "Listing for " + pet.getName() + " approved.";
                new Thread(new EmailNotification(shelterEmail, msg)).start(); 
                
            } catch (SQLException e) {
                throw new RuntimeException("Database error during status update.", e);
            }
        }
    }
    
    // NOTE: You must implement the remaining interface methods (update, delete, getAll).
    @Override public void update(Pet entity) { /* ... */ }
    @Override public void delete(int id) throws EntityNotFoundException { /* ... */ }
    @Override public List<Pet> getAll() { /* ... */ return null; }
}