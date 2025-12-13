



package interface_package;

public interface Payable {
    boolean processPayment(double amount, String method);
    boolean processDefaultPayment();
}
