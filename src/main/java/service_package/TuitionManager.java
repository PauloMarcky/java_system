// TuitionManager class - controls system flow and user input
package service_package;

import java.util.Scanner;

import interface_package.Payable;
import model_package.Person;
import model_package.Student;
import model_package.YearLevel;

public class TuitionManager {

    Scanner scanner = new Scanner(System.in); // Input reader
    
    //same as
    /*
    Student student1 = new Student("001", "Marcky", "Balaba", "BSIT", YearLevel.FIRST_YEAR, 25000);
    Student student2 = new Student("002", "Jescy", "Garma", "BSN", YearLevel.SECOND_YEAR, 33000);
    Student student3 = new Student("003", "Apriljoy", "Legaspi", "BSN", YearLevel.THIRD_YEAR, 34000);
    Student student4 = new Student("004", "Lawrence", "Manuel", "BSA", YearLevel.FIRST_YEAR, 25000);
    Student student5 = new Student("005", "Albertjan", "Santos", "BSCE", YearLevel.FOURTH_YEAR, 30000);
    Student student6 = new Student("006", "Sample", "Student", "BSIT");

    Student [] studentsArray = {
        student1, student2, student3, student4, student5, student6
    };
    */

    // List of students
    //Anonymous Objects
    Student[] studentsArray = {
        new Student("001", "Marcky", "Balaba", "BSIT", YearLevel.FIRST_YEAR, 25000),
        new Student("002", "Jescy", "Garma", "BSN", YearLevel.SECOND_YEAR, 33000),
        new Student("003", "Apriljoy", "Legaspi", "BSN", YearLevel.THIRD_YEAR, 34000),
        new Student("004", "Lawrence", "Manuel", "BSA", YearLevel.FIRST_YEAR, 25000),
        new Student("005", "Albertjan", "Santos", "BSCE", YearLevel.FOURTH_YEAR, 30000),
        new Student("006", "Sample", "Student", "BSIT")
    };

    // Tuition matrix data
    String[] courseNames = {
        "BSIT", "BSN", "BSCE", "BSA"
    };
    
    double[][] tuitionMatrix = {
        {25000, 26000, 27000, 28000},
        {32000, 33000, 34000, 35000},
        {27000, 28000, 29000, 30000},
        {25000, 25000, 25000, 25000}
    };

    // Main menu loop
    public void startingPoint() {

        int choice;
        
        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1) Show Profile Details");
            System.out.println("2) Make Payment");
            System.out.println("3) Payment History");
            System.out.println("4) Show Tuition Matrix");
            System.out.println("5) Exit");
            System.out.print("Enter choice: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> showProfile();
                case 2 -> makePayment();
                case 3 -> paymentHistory();
                case 4 -> showTuitionMatrix();
                case 5 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    // Finds and returns a student based on entered ID
    private Student findStudent() {

        // Ask user for student ID
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
    
        // Variable to store found student
        Student found = null;

        // Loop through students array to find match
        for (Student s : studentsArray) {
            if (s.getStudentId().equals(id)) {
                found = s; // Student found
                break;     // Stop searching
            }
        }
    
        // Display message if student not found
        if (found == null) {
            System.out.println("Student not found.");
        }
    
        // Return student object (or null)
        return found;
    }

    // Displays student profile information
    private void showProfile() {
        Person p = findStudent(); // Get student 
        ///same as Person p = new Student(...);
        // it will be like this Person p = new Student("001", "Marcky", "Balaba", "BSIT", YearLevel.FIRST_YEAR, 25000)
        if (p != null){
            p.displayInfo();// Show info if exists
        }
    }
    
    // Handles payment process
    private void makePayment() {

        Payable p = findStudent(); // Get student
        //same as Payable p = new Student(...);
        if (p == null) return;     // Exit if not found
    
        // Show payment options
        System.out.println("\n1) Custom Amount");
        System.out.println("2) Default Payment (1/5 tuition)");
        System.out.print("Choose: ");
    
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear input buffer

        // Custom payment
        if (choice == 1) {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            p.processPayment(amount, "Custom");
    
        // Default payment
        } else if (choice == 2) {
        p.processDefaultPayment();

        // Invalid choice
        } else {
            System.out.println("Invalid option.");
        }

    }
    
    // Displays payment history of a student
    private void paymentHistory() {
        Student s = findStudent(); // Get student
        if (s != null) s.showPaymentHistory(); // Show history
    }
    
    // Displays tuition fee table
    private void showTuitionMatrix() {
        System.out.println("\n===== TUITION MATRIX =====");
        System.out.print("Course\\Year\t");

        // Print year headers
        for (int y = 1; y <= 4; y++) {
            System.out.print("Year " + y + "\t");
        }
        System.out.println();
    
        // Print tuition fees per course and year
        for (int i = 0; i < courseNames.length; i++) {
            System.out.print(courseNames[i] + "\t\t");
            for (int j = 0; j < 4; j++) {
            System.out.print(tuitionMatrix[i][j] + "\t");
        }
        System.out.println();
        }
    }
}
