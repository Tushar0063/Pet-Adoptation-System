package com.adoption.platform;
// Add this line near the top of MainApplication.java
import javax.swing.SwingUtilities; 
import com.adoption.platform.gui.ShelterDashboard;
import com.adoption.platform.dao.PetDAO;
import com.adoption.platform.exception.EntityNotFoundException;
import com.adoption.platform.model.*;

import com.adoption.platform.service.PetService;
import java.util.List;
import java.util.Arrays;

public class MainApplication {
    public static void main(String[] args) {
        
        // --- 1. OOP Polymorphism Demonstration (10 Marks) ---
        System.out.println("--- 1. Testing Polymorphism ---");
        
        User admin = new Admin(1, "Alice", "alice@admin.com");
        User shelter = new Shelter(101, "Happy Paws", "paws@shelter.com");
        User adopter = new Adopter(201, "Bob Smith", "bob@adopt.com");
        
        // Parent List holds Child objects
        List<User> userLogins = Arrays.asList(admin, shelter, adopter);
        
        for (User user : userLogins) {
            // Displays the correct, overridden method output at runtime
            user.displayDashboard(); 
        }

        // --- 2. Functional Test (Service, JDBC, Threads) ---
        System.out.println("\n--- 2. Functional Test: Listing and Approval ---");
        
        PetService petService = new PetService();
        SwingUtilities.invokeLater(() -> {
    // Pass the required dependency
    new ShelterDashboard(petService).setVisible(true); 
});
        
        // A. Shelter lists a new pet (calls PetService.create -> PetDAO.save (JDBC INSERT))
        Pet newPet = new Pet(101, "Max", "Dog", "Lab", "Very friendly.");
        petService.create(newPet);
        System.out.println("Pet listed with ID: " + newPet.getId() + ". Status: " + newPet.getAdoptionStatus());
        
        // B. Admin approves the listing (calls PetService.approveListing)
        try {
            petService.approveListing(newPet.getId(), shelter.getEmail());
        } catch (EntityNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
              
        // The email thread output will appear after the approval, demonstrating Multithreading!
    }
}