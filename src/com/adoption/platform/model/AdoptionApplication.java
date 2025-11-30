package com.adoption.platform.model;

public class AdoptionApplication {
    // Core data fields
    private int id;                 // Unique Application ID
    private int petId;              // Foreign Key to the Pet being applied for
    private int adopterId;          // Foreign Key to the Adopter submitting the application
    private String applicationDetails; // Text detailing the adopter's home environment, experience, etc.
    private String status;           // e.g., "Submitted", "Reviewing", "Approved", "Rejected"
    private java.sql.Timestamp submissionDate; // When the application was submitted

    // ---------------------- CONSTRUCTOR ----------------------

    public AdoptionApplication(int id, int petId, int adopterId, String applicationDetails, String status, java.sql.Timestamp submissionDate) {
        this.id = id;
        this.petId = petId;
        this.adopterId = adopterId;
        this.applicationDetails = applicationDetails;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    // Constructor for a brand new submission (ID and date are not yet known)
    public AdoptionApplication(int petId, int adopterId, String applicationDetails) {
        this.petId = petId;
        this.adopterId = adopterId;
        this.applicationDetails = applicationDetails;
        this.status = "Submitted"; // Default initial status
        this.submissionDate = null; // Will be set by the DAO upon insert
    }

    // ---------------------- GETTERS AND SETTERS ----------------------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPetId() { return petId; }
    // ... (All other getters and setters must be included for completeness)

    public String getStatus() { return status; }
    // Crucial setter used by the ShelterService to update the application status
    public void setStatus(String status) { this.status = status; }
}