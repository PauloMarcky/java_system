



package model_package;

import interface_package.Payable;

public class Student extends Person implements Payable {

    private String studentId;
    private String course;
    private YearLevel yearLevel;

    private double tuition;
    private double paidAmount = 0;

    private Payment[] paymentHistory = new Payment[5];
    private int paymentCount = 0;

    public Student(String studentId, String first, String last,
                   String course, YearLevel yl, double tuition) {

        super(first, last);
        this.studentId = studentId;
        this.course = course;
        this.yearLevel = yl;
        this.tuition = tuition;
    }

    // Encapsulated getters
    public String getStudentId() { return studentId; }
    public double getTuition() { return tuition; }
    public double getPaidAmount() { return paidAmount; }
    public double getBalance() { return tuition - paidAmount; }

    @Override
    public void displayInfo() {
        System.out.println("\n===== STUDENT INFORMATION =====");
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Student ID: " + studentId);
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + yearLevel);
        System.out.printf("Tuition: %.2f\n", tuition);
        System.out.printf("Balance: %.2f\n", getBalance());
    }

    @Override
    public boolean processPayment(double amount, String method) {

        if (paymentCount >= 5) {
            System.out.println("!!!!- MAXIMUM OF 5 PAYMENTS REACHED -!!!!");
            return false;
        }

        double min = tuition / 5;

        if (amount < min) {
            System.out.println("!!!!- MINIMUM PAYMENT IS 1/5 OF TUITION (" + min + ") -!!!!");
            return false;
        }

        if (amount > getBalance()) {
            System.out.println("!!!!- PAYMENT EXCEEDS BALANCE -!!!!");
            return false;
        }

        paidAmount += amount;

        paymentHistory[paymentCount] = new Payment(amount, method);
        paymentCount++;

        System.out.println("\n==== PAYMENT SUCCESSFUL ====");
        System.out.println("New Balance: " + getBalance());
        return true;
    }

    @Override
    public boolean processDefaultPayment() {
        double defaultAmount = tuition / 5;
        return processPayment(defaultAmount, "Default");
    }

    public void showPaymentHistory() {
        System.out.println("\n==== PAYMENT HISTORY ====");
        if (paymentCount == 0) {
            System.out.println("No payments made yet.");
            return;
        }

        for (int i = 0; i < paymentCount; i++) {
            paymentHistory[i].printPaymentDetails();
        }
    }
}
