// Payable interface - defines payment-related actions
package interface_package;

public interface Payable {
    // Process a custom payment
    boolean processPayment(double amount, String method);

    // Process a default payment
    boolean processDefaultPayment();
}


```

```java
// Payment class - stores individual payment details
package model_package;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {

    private double amount;          // Payment amount
    private String method;           // Payment method
    private LocalDateTime timestamp; // Date and time of payment

    // Constructor sets payment data and current time
    public Payment(double amount, String method) {
        this.amount = amount;
        this.method = method;
        this.timestamp = LocalDateTime.now();
    }

    // Getters for encapsulated fields
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Display formatted payment info
    public void printPaymentDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.of
```

