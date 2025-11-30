package com.adoption.platform.model;

public class Admin extends User {
    // 1. Constructor for Admin
    public Admin(int id, String name, String email) {
        // 2. Calls the parent (User) constructor
        // super(id, name, email, role);
        super(id, name, email, "Admin");
    }

    // 3. Implements the abstract method from the parent User class
    @Override
    public void displayDashboard() {
        System.out.println("-> Welcome to the Admin Dashboard (User Management & Approvals).");
    }
}