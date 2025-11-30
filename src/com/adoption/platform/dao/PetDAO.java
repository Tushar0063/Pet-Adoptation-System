package com.adoption.platform.dao;

import com.adoption.platform.model.Pet;
import com.adoption.platform.util.DBUtil;
import java.sql.*;

public class PetDAO {

    private static final String INSERT_PET_SQL = 
        "INSERT INTO Pet (shelter_id, name, species, breed, description, adoption_status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PET_BY_ID_SQL = 
        "SELECT id, shelter_id, name, species, breed, description, adoption_status FROM Pet WHERE id = ?";
    private static final String UPDATE_PET_STATUS_SQL = 
        "UPDATE Pet SET adoption_status = ? WHERE id = ?";

    // 1. SAVE Method (JDBC INSERT)
    public void save(Pet pet) throws SQLException {
        // Uses try-with-resources for automatic closing
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PET_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, pet.getShelterId());
            ps.setString(2, pet.getName());
            ps.setString(3, pet.getSpecies());
            ps.setString(4, pet.getBreed());
            ps.setString(5, pet.getDescription());
            ps.setString(6, pet.getAdoptionStatus());

            ps.executeUpdate(); // Execute DML

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pet.setId(generatedKeys.getInt(1)); 
                }
            }
        }
    }

    // 2. FIND Method (JDBC SELECT)
    public Pet findById(int id) throws SQLException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PET_BY_ID_SQL)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { // Execute Query
                if (rs.next()) {
                    // Mapping the ResultSet to the Pet object
                    return mapResultSetToPet(rs); 
                }
                return null; 
            }
        }
    }

    // 3. UPDATE Method (JDBC UPDATE)
    public void updateStatus(int id, String status) throws SQLException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PET_STATUS_SQL)) {
            
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
    
    // Helper to map DB row to Java object
    private Pet mapResultSetToPet(ResultSet rs) throws SQLException {
        return new Pet(
            rs.getInt("id"), rs.getInt("shelter_id"), rs.getString("name"),
            rs.getString("species"), rs.getString("breed"), rs.getString("description"),
            rs.getString("adoption_status")
        );
    }
}