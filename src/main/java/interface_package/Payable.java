// Payable interface - defines payment-related actions
package interface_package;

public interface Payable {
    // Process a custom payment
    boolean processPayment(double amount, String method);

    // Process a default payment
    boolean processDefaultPayment();
}
