package com.adoption.platform.model;

public abstract class User {
    
    // Protected fields allow subclasses (Admin, Shelter, Adopter) direct access
    protected int id;
    protected String name;
    protected String email; // <--- The field accessed by getEmail()
    protected String role;

    // Constructor to initialize common properties
    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
    
    // Abstract method: MUST be overridden by all concrete subclasses (Polymorphism)
    public abstract void displayDashboard();
    
    // ---------------------- GETTERS ----------------------
    
    // Getter required to fix the error in MainApplication.java (shelter.getEmail())
    public String getEmail() {
        return email;
    }
    
    // Getter for the ID, often used by DAO layer
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    // ---------------------- SETTERS (Optional, but often useful) ----------------------
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // Note: It's generally discouraged to allow changing the ID or Role after creation
    // so setters for id and role are usually omitted or restricted.
}