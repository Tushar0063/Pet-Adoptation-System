package com.adoption.platform.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Happy saini
 */



import com.adoption.platform.service.PetService;
import com.adoption.platform.model.Pet; // Import the Pet model
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShelterDashboard extends JFrame {
    
    // Define SHELTER_ID as static for access in the super() call and listPetAction
    private static final int SHELTER_ID = 101; 

    // Dependency: The GUI needs a reference to the Service layer
    private final PetService petService;

    // Swing Components for Pet Listing
    private JTextField nameField, speciesField, breedField, descField;
    private JButton listPetButton;
    private JTextArea outputArea;

    // --- CONSTRUCTOR ---
    public ShelterDashboard(PetService petService) {
        // Calls JFrame constructor using the static SHELTER_ID
        super("Shelter Dashboard - Happy Paws (ID: " + SHELTER_ID + ")"); 
        this.petService = petService;
        
        // Initialize the GUI components
        setupLayout();
        
        // Configure the Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        nameField = new JTextField(15);
        speciesField = new JTextField(15);
        breedField = new JTextField(15);
        descField = new JTextField(15);
        
        inputPanel.add(new JLabel("Pet Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Species (Dog/Cat):"));
        inputPanel.add(speciesField);
        inputPanel.add(new JLabel("Breed:"));
        inputPanel.add(breedField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descField);
        
        listPetButton = new JButton("Submit Pet Listing");
        inputPanel.add(listPetButton);
        inputPanel.add(new JLabel(" ")); // Placeholder

        // --- Output Panel ---
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add panels to the frame
        add(new JLabel("New Listing Form", SwingConstants.CENTER), BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Add the ActionListener (Connects GUI action to Service method)
        listPetButton.addActionListener(this::listPetAction);
    }
    
    // --- ACTION LISTENER: Connects GUI to Business Logic ---
    private void listPetAction(ActionEvent e) {
        // 1. Retrieve data from GUI fields
        String name = nameField.getText();
        String species = speciesField.getText();
        String breed = breedField.getText();
        String description = descField.getText();

        if (name.isEmpty() || species.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Species are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 2. Create the Pet model object (Uses the SHELTER_ID static variable)
            Pet newPet = new Pet(ShelterDashboard.SHELTER_ID, name, species, breed, description);
            
            // 3. Call the Service Layer (Triggers JDBC INSERT and business rules)
            petService.create(newPet);
            
            // 4. Provide visual feedback
            outputArea.append("✅ SUCCESS: Pet '" + name + "' submitted.\n");
            outputArea.append("   - Assigned ID: " + newPet.getId() + "\n");
            outputArea.append("   - Status: " + newPet.getAdoptionStatus() + " (Awaiting Admin Approval)\n\n");
            
            // Clear fields
            nameField.setText("");
            speciesField.setText("");
            breedField.setText("");
            descField.setText("");

        } catch (Exception ex) {
            // Catches any unexpected RuntimeException thrown from the Service/DAO layer
            outputArea.append("❌ FATAL ERROR: Could not create listing.\n");
            outputArea.append("   - " + ex.getMessage() + "\n\n");
        }
    }
}