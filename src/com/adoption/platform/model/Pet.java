package com.adoption.platform.model;

public class Pet {
    // Core Data Fields (corresponding to database table columns)
    private int id;                 
    private int shelterId;          
    private String name;            // <-- Field for getName()
    private String species;         // <-- Field for getSpecies()
    private String breed;           // <-- Field for getBreed()
    private String description;     // <-- Field for getDescription()
    private String adoptionStatus;  

    // ---------------------- FULL CONSTRUCTOR (For DB retrieval) ----------------------
    public Pet(int id, int shelterId, String name, String species, String breed, String description, String adoptionStatus) {
        this.id = id;
        this.shelterId = shelterId;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
        this.adoptionStatus = adoptionStatus;
    }

    // ---------------------- NEW LISTING CONSTRUCTOR (For Shelter creation) ----------------------
    // ID is initially 0 (will be set by DAO), Status is set to "Pending"
    public Pet(int shelterId, String name, String species, String breed, String description) {
        this(0, shelterId, name, species, breed, description, "Pending");
    }


    // ---------------------- GETTERS AND SETTERS (Used by PetDAO and PetService) ----------------------
    
    // --- Getters required by PetDAO.save() ---
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getDescription() {
        return description;
    }
    
    // --- Other necessary Getters/Setters ---
    
    public int getId() {
        return id;
    }
    
    // Crucial: Used by PetDAO to set the auto-generated ID after INSERT
    public void setId(int id) {
        this.id = id;
    }

    public int getShelterId() {
        return shelterId;
    }
    
    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    // Crucial: Used by PetService to change status (e.g., Approve listing)
    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}