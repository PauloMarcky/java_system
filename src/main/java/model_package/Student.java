// Student class - represents a student and handles payments
package model_package;


import interface_package.Payable;


public class Student extends Person implements Payable {

    private String studentId; // Student ID
    private String course; // Course name
    private YearLevel yearLevel;// Year level
    private double tuition; // Total tuition
    private double paidAmount = 0; // Total paid


    private Payment[] paymentHistory = new Payment[5]; // Max 5 payments
    private int paymentCount = 0; // Number of payments

    // Constructor uses super to set name
    public Student(String studentId, String first, String last,
    String course, YearLevel yl, double tuition) {
        super(first, last);
        this.studentId = studentId;
        this.course = course;
        this.yearLevel = yl;
        this.tuition = tuition;
    }
    
    //Overload Constructor
    public Student(String studentId, String first, String last, String course) {
        super(first, last);
        this.studentId = studentId;
        this.course = course;
        this.yearLevel = YearLevel.FIRST_YEAR;
        this.tuition = 25000; 
    }

    // Encapsulated getters
    public String getStudentId() {
        return studentId; 
    }
    public double getTuition() {
        return tuition; 
    }
    public double getPaidAmount() {
        return paidAmount; 
    }
    public double getBalance() {
        return tuition - paidAmount; 
    }

    // Display student information
    @Override
    public void displayInfo() {
        System.out.println("===== STUDENT INFORMATION =====");
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Student ID: " + studentId);
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + yearLevel);
        System.out.printf("Tuition: %.2f", tuition);
        System.out.printf("Balance: %.2f", getBalance());
    }

    // Process custom payment
    @Override
    public boolean processPayment(double amount, String method) {
        // Check payment limit
        if (paymentCount >= 5) {
            System.out.println("!!!!- MAXIMUM OF 5 PAYMENTS REACHED -!!!!");
            return false;
        }
    
        // Check minimum payment
        double min = tuition / 5;
        if (amount < min) {
            System.out.println("!!!!- MINIMUM PAYMENT IS 1/5 OF TUITION (" + min + ") -!!!!");
            return false;
        }

        // Prevent overpayment
        if (amount > getBalance()) {
            System.out.println("!!!!- PAYMENT EXCEEDS BALANCE -!!!!");
            return false;
        }
        
        // Record payment
        paidAmount += amount;
        paymentHistory[paymentCount] = new Payment(amount, method);
        paymentCount++;
        
        
        System.out.println("
        ==== PAYMENT SUCCESSFUL ====");
        System.out.println("New Balance: " + getBalance());
        return true;
        }

        // Process default payment
    @Override
    public boolean processDefaultPayment() {
        double defaultAmount = tuition / 5;
        return processPayment(defaultAmount, "Default");
    }
        
        // Show all payments
    public void showPaymentHistory() {
        System.out.println("
        ==== PAYMENT HISTORY ====");
        if (paymentCount == 0) {
        System.out.println("No payments made yet.");
            
        return;
        }
        for (int i = 0; i < paymentCount; i++) {
            paymentHistory[i].printPaymentDetails();
        }
    }
}
