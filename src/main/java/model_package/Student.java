/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model_package;
import java.util.ArrayList;
import java.util.List;

import interface_package.Payable;
/**
 *
 * @author paulo
 */
public class Student extends Person implements Payable{
    
    /**
    * Student class demonstrates:
    * - encapsulation (private fields + getters/setters)
    * - constructor overloading (default + parameterized)
    * - implements Payable (interface) - polymorphism possible
    * - overrides displayInfo() from Person (inheritance + overriding)
    */
     private String course;
    private YearLevel yearLevel;
    private double tuition;
    private double paidAmount;
    private final List<Payment> paymentHistory; // dynamic history

    // default constructor
    public Student() {
        super();
        this.course = "Unknown";
        this.yearLevel = YearLevel.FIRST_YEAR;
        this.tuition = 0.0;
        this.paidAmount = 0.0;
        this.paymentHistory = new ArrayList<>();
    }

    // parameterized constructors (constructor overloading)
    public Student(String studentId, String lastName, String firstName, String course, YearLevel yearLevel, double tuition) {
        super(studentId, lastName, firstName);
        this.course = course;
        this.yearLevel = yearLevel;
        this.tuition = tuition;
        this.paidAmount = 0.0;
        this.paymentHistory = new ArrayList<>();
    }

    public Student(String studentId, String lastName, String firstName, String course, int yearLevelInt, double tuition) {
        this(studentId, lastName, firstName, course, YearLevel.fromInt(yearLevelInt), tuition);
    }

    // getters/setters -> encapsulation
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public YearLevel getYearLevel() { return yearLevel; }
    public void setYearLevel(YearLevel yearLevel) { this.yearLevel = yearLevel; }

    public double getTuition() { return tuition; }
    public void setTuition(double tuition) { this.tuition = tuition; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }

    public List<Payment> getPaymentHistory() { return paymentHistory; }

    // compute remaining balance -> demonstrates a method that returns a value
    public double getRemainingBalance() {
        return Math.max(0.0, tuition - paidAmount);
    }

    // implement Payable: process payment, returns true if payment accepted
    @Override
    public boolean processPayment(double amount) {
        double balance = getRemainingBalance();
        if (balance == 0) {
            System.out.println("\n==== YOU ARE ALREADY PAID ====");
            return false;
        }
        if (amount <= 0) {
            System.out.println("!!!!-INVALID PAYMENT AMOUNT-!!!!");
            System.out.println("==== REMAINING BALANCE: " + String.format("%.2f", balance) + " ====");
            return false;
        }
        if (amount > balance) {
            System.out.println("!!!!-INVALID PAYMENT AMOUNT: exceeds balance-!!!!");
            System.out.println("==== REMAINING BALANCE: " + String.format("%.2f", balance) + " ====");
            return false;
        }
        // enforce minimum: at least 1/5 of tuition
        double min = tuition / 5.0;
        if (amount < min) {
            System.out.println("!!!!-LOWEST AMOUNT PAYMENT IS 1/5 OF YOUR TUITION-!!!!");
            System.out.println("Minimum: " + String.format("%.2f", min));
            return false;
        }
        paidAmount += amount;
        Payment p = new Payment(getStudentId(), amount, "Manual");
        paymentHistory.add(p);
        System.out.println("\n==== PAYMENT OF PHP " + String.format("%.2f", amount) + " WAS SUCCESSFUL ====");
        System.out.println("==== New Balance: " + String.format("%.2f", getRemainingBalance()) + " ====");
        return true;
    }

    // default payment (1/5 of tuition) — overload example: not method overloading vs interface but shows convenience method
    public boolean processDefaultPayment() {
        double defaultPayment = tuition / 5.0;
        boolean ok = processPayment(defaultPayment);
        if (ok) {
            // add payment with method Default
            Payment p = new Payment(getStudentId(), defaultPayment, "Default");
            paymentHistory.add(p);
            System.out.println("\n==== DEFAULT PAYMENT: (" + String.format("%.2f", defaultPayment) + ") WAS SUCCESSFUL ====");
            System.out.println("New Balance: " + String.format("%.2f", getRemainingBalance()));
        }
        return ok;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n==== STUDENT INFORMATION ====");
        System.out.println("Student Name: " + getLastName() + ", " + getFirstName());
        System.out.println("Student ID: " + getStudentId());
        System.out.println("Course: " + course);
        System.out.println("Year Level: " + yearLevel + " (" + yearLevel.getLevel() + ")");
        System.out.printf("Tuition: %.2f\n", tuition);
        System.out.printf("Paid: %.2f\n", paidAmount);
        System.out.printf("Balance: %.2f\n", getRemainingBalance());
    }

    // show payment history
    public void showPaymentHistory() {
        System.out.println("\n==== PAYMENT HISTORY FOR " + getFirstName() + " " + getLastName() + " ====");
        if (paymentHistory.isEmpty()) {
            System.out.println("!!!!- NO PAYMENT MADE YET -!!!!");
        } else {
            for (Payment p : paymentHistory) { // for-each loop
                System.out.println(p.toDisplayString());
            }
        }
    }
    
}
