package com.adoption.platform.service;

// Implements Runnable to define an asynchronous task
public class EmailNotification implements Runnable {
    private final String recipient;
    private final String message;

    public EmailNotification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("--- Starting Email Thread ---");
        System.out.println("Processing email for: " + recipient);
        try {
            // Simulate network delay for sending email
            Thread.sleep(2000); 
            System.out.println("SUCCESS: Notification sent - Message: " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            System.err.println("Email sending interrupted.");
        }
        System.out.println("--- Email Thread Finished ---");
    }
}