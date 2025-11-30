package com.adoption.platform.model;

public class Shelter extends User {
    public Shelter(int id, String name, String email) {
        super(id, name, email, "Shelter");
    }

    @Override
    public void displayDashboard() {
        System.out.println("-> Welcome to the Shelter Dashboard (Pet Listings & Application Management).");
    }
    
    // Shelter-specific methods (will be implemented in the Service Layer)
    public void listPet(Pet pet) { /* ... */ } 
    public void reviewApplication(int appId) { /* ... */ }
}