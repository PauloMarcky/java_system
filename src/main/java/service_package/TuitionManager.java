/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service_package;
import dao_package.PaymentDAO;
import dao_package.StudentDAO;
import model_package.Payment;
import model_package.Student;
import model_package.YearLevel;

import java.util.*;
/**
 *
 * @author paulo
 */
public class TuitionManager {
    /**
    * Business logic, menu, and console UI.
    * Demonstrates nested loops, arrays, for-each, break, etc.
    */
    
    private final StudentDAO studentDAO = new StudentDAO();
    private final PaymentDAO paymentDAO = new PaymentDAO();

    private final Scanner scanner = new Scanner(System.in);

    // Will hold an array of Student objects (requirement: array of objects)
    private Student[] studentsArray;

    // Example 2D array: tuition matrix courses x yearlevel
    private final String[] courseNames = {"BSIT", "BSN", "BSCE", "BSA"};
    private final double[][] tuitionMatrix = {
            {25000, 26000, 27000, 28000}, // BSIT: Y1..Y4
            {32000, 33000, 34000, 35000}, // BSN
            {27000, 28000, 29000, 30000}, // BSCE
            {25000, 25000, 25000, 25000}  // BSA
    };

    public TuitionManager() {
        // load students from DB and populate array of objects
        loadStudentsFromDB();
    }

    private void loadStudentsFromDB() {
        List<Student> list = studentDAO.getAllStudents();
        studentsArray = new Student[list.size()];
        for (int i = 0; i < list.size(); i++) {
            studentsArray[i] = list.get(i);
        }
    }

    public void startingPoint() {
        int choice;
        do {
            System.out.println("\n~~~~ CHOOSE A COMMAND ~~~~");
            System.out.println("1) Show Profile Details");
            System.out.println("2) Make Payment");
            System.out.println("3) Payment History (DB)");
            System.out.println("4) Show Tuition Matrix (2D array)");
            System.out.println("5) Exit");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\n!!!!-PLEASE ENTER A VALID NUMBER-!!!!\n");
                scanner.next();
                System.out.print("Enter choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> studentInfoDisplay();
                case 2 -> payTuition();
                case 3 -> viewPaymentHistoryDB();
                case 4 -> showTuitionMatrix();
                case 5 -> System.out.println("==== THANK YOU ====");
                default -> System.out.println("\n!!!!-INVALID CHOICE: TRY AGAIN-!!!!\n");
            }
        } while (choice != 5);
    }

    // find student by scanning array -> demonstrates for-loop and break
    private Student findMatchIdLocal() {
        System.out.print("\nEnter your Student ID: ");
        String inputId = scanner.nextLine().trim();
        // for loop with break
        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i].getStudentId().equals(inputId)) {
                return studentsArray[i];
            }
        }
        System.out.println("\n!!!!-ERROR: STUDENT NOT FOUND-!!!!");
        return null;
    }

    private void studentInfoDisplay() {
        Student student = findMatchIdLocal();
        if (student == null) return;
        student.displayInfo();
    }

    private void payTuition() {
        Student student = findMatchIdLocal();
        if (student == null) return;

        int choicePayment;
        System.out.println("\n~~~~CHOOSE PAYMENT~~~~\n");
        System.out.println("1) Custom Amount");
        System.out.println("2) Default Payment (1/5 of tuition)");
        System.out.println("3) Exit");
        System.out.print("Enter payment method: ");

        while (!scanner.hasNextInt()) {
            System.out.println("\n!!!!-PLEASE ENTER A VALID NUMBER-!!!!\n");
            scanner.next();
            System.out.print("Enter choice: ");
        }
        choicePayment = scanner.nextInt();
        scanner.nextLine();

        switch (choicePayment) {
            case 1 -> {
                System.out.print("Enter pay amount: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("!!!!-PLEASE ENTER A VALID NUMBER-!!!!");
                    scanner.next();
                }
                double pay = scanner.nextDouble();
                scanner.nextLine();
                boolean ok = student.processPayment(pay);
                if (ok) {
                    // persist payment
                    Payment p = new Payment(student.getStudentId(), pay, "Manual");
                    paymentDAO.savePayment(p);
                    studentDAO.updatePaidAmount(student.getStudentId(), student.getPaidAmount());
                }
            }
            case 2 -> {
                boolean ok = student.processDefaultPayment();
                if (ok) {
                    double defaultPayment = student.getTuition() / 5.0;
                    Payment p = new Payment(student.getStudentId(), defaultPayment, "Default");
                    paymentDAO.savePayment(p);
                    studentDAO.updatePaidAmount(student.getStudentId(), student.getPaidAmount());
                }
            }
            default -> System.out.println("\n!!!!-INVALID PAYMENT CHOICE: TRY AGAIN-!!!!\n");
        }
    }

    private void viewPaymentHistoryDB() {
        Student student = findMatchIdLocal();
        if (student == null) return;
        // get payment history from DB and show
        List<Payment> payments = paymentDAO.getPaymentsByStudent(student.getStudentId());
        System.out.println("\n==== PAYMENT HISTORY (DB) FOR " + student.getFirstName() + " " + student.getLastName() + " ====");
        if (payments.isEmpty()) {
            System.out.println("!!!!- NO PAYMENT MADE YET -!!!!");
        } else {
            for (Payment p : payments) {
                System.out.println(p.toDisplayString());
            }
        }
    }

    // Demonstrate nested loops and 2D array printing
    private void showTuitionMatrix() {
        System.out.println("\n          ==== TUITION COST MATRIX (Course x YearLevel) ====\n");
        // header
        System.out.print("Course\\Year\t");
        for (int c = 1; c <= 4; c++) System.out.print("Year " + c + "        " + "\t");
        System.out.println();
        // nested for loop
        for (int i = 0; i < tuitionMatrix.length; i++) {
            System.out.print(courseNames[i] + "\t\t");
            for (int j = 0; j < tuitionMatrix[i].length; j++) {
                System.out.print(String.format("%.2f", tuitionMatrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}
