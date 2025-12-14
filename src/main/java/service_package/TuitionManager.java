package service_package;

import model_package.Student;
import model_package.YearLevel;

import java.util.Scanner;

public class TuitionManager {

    private Scanner scanner = new Scanner(System.in);

    private Student[] studentsArray = {
            new Student("001", "Marcky", "Balaba", "BSIT", YearLevel.FIRST_YEAR, 25000),
            new Student("002", "Jescy", "Garma", "BSN", YearLevel.SECOND_YEAR, 32000),
            new Student("003", "Apriljoy", "Legaspi", "BSN", YearLevel.THIRD_YEAR, 32000),
            new Student("004", "Lawrence", "Manuel", "BSA", YearLevel.FIRST_YEAR, 25000),
            new Student("005", "Albertjan", "Santos", "BSCE", YearLevel.FOURTH_YEAR, 27000),
            new Student("006", "Sample", "Student", "BSIT", YearLevel.SECOND_YEAR, 25000)
    };

    private String[] courseNames = {"BSIT", "BSN", "BSCE", "BSA"};
    private double[][] tuitionMatrix = {
            {25000, 26000, 27000, 28000},
            {32000, 33000, 34000, 35000},
            {27000, 28000, 29000, 30000},
            {25000, 25000, 25000, 25000}
    };

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

    private Student findStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student found = null;

        for (Student s : studentsArray) {
            if (s.getStudentId().equals(id)) {
                found = s;
                break; 
            }
        }

        if (found == null) {
            System.out.println("Student not found.");
        }

        return found;
    }

    private void showProfile() {
        Student s = findStudent();
        if (s != null) s.displayInfo();
    }

    private void makePayment() {
        Student s = findStudent();
        if (s == null) return;

        System.out.println("\n1) Custom Amount");
        System.out.println("2) Default Payment (1/5 tuition)");
        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            s.processPayment(amount, "Custom");

        } else if (choice == 2) {
            s.processDefaultPayment();

        } else {
            System.out.println("Invalid option.");
        }
    }

    private void paymentHistory() {
        Student s = findStudent();
        if (s != null) s.showPaymentHistory();
    }

    private void showTuitionMatrix() {
        System.out.println("\n===== TUITION MATRIX =====");
        System.out.print("Course\\Year\t");

        for (int y = 1; y <= 4; y++) {
            System.out.print("Year " + y + "\t");
        }
        System.out.println();

        for (int i = 0; i < courseNames.length; i++) {
            System.out.print(courseNames[i] + "\t\t");
            for (int j = 0; j < 4; j++) {
                System.out.print(tuitionMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
