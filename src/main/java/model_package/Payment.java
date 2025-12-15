// Payment class - stores individual payment details
package model_package;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Payment {


    private double amount; // Payment amount
    private String method; // Payment method
    private LocalDateTime timestamp; // Date and time of payment
    
    // Constructor sets payment data and current time
    public Payment(double amount, String method) {
        this.amount = amount;
        this.method = method;
        this.timestamp = LocalDateTime.now();
    }

    // Getters for encapsulated fields
    public double getAmount() {
        return amount; 
    }
    public String getMethod() {
        return method; 
    }
    public LocalDateTime getTimestamp() {
        return timestamp; 
    }
    
    // Display formatted payment info
    public void printPaymentDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Paid: " + String.format("%.2f", amount) + " (" + method + ") | Date: " + timestamp.format(fmt));
    }
}
