/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model_package;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author paulo
 */
public class Payment {
    
    private int paymentId; // 0 if not from DB insert
    private String studentId;
    private double amount;
    private LocalDateTime paymentDate;
    private String method;

    public Payment() {
        this.paymentId = 0;
        this.studentId = "";
        this.amount = 0.0;
        this.paymentDate = LocalDateTime.now();
        this.method = "UNKNOWN";
    }

    public Payment(String studentId, double amount, String method) {
        this.studentId = studentId;
        this.amount = amount;
        this.method = method;
        this.paymentDate = LocalDateTime.now();
    }

    public Payment(int paymentId, String studentId, double amount, LocalDateTime date, String method) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = date;
        this.method = method;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public String getStudentId() { return studentId; }
    public double getAmount() { return amount; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public String getMethod() { return method; }

    public String toDisplayString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Paid: %.2f | %s | Method: %s", amount, paymentDate.format(f), method);
    }
}
