



package model_package;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment{

    private double amount;
    private String method;
    private LocalDateTime timestamp;

    public Payment(double amount, String method) {
        this.amount = amount;
        this.method = method;
        this.timestamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount; 
    }
    public String getMethod() {
        return method; 
    }
    public LocalDateTime getTimestamp() {
        return timestamp; 
    }

    public void printPaymentDetails() {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        System.out.println("Paid: " + String.format("%.2f", amount) + " (" + method + ") | Date: " + timestamp.format(fmt));
    }

}
