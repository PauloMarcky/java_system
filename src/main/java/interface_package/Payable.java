



package interface_package;

public interface Payable {

    public boolean processPayment(double amount, String method);
    public boolean processDefaultPayment();
}
