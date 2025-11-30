package com.adoption.platform.model;

public class Adopter extends User {
    public Adopter(int id, String name, String email) {
        super(id, name, email, "Adopter");
    }

    @Override
    public void displayDashboard() {
        System.out.println("-> Welcome to the Adopter Dashboard (Pet Browsing & Application Tracking).");
    }
    
    // Adopter-specific methods (will be implemented in the Service Layer)
    public void submitApplication(int petId) { /* ... */ }
    public void trackStatus(int appId) { /* ... */ }
}