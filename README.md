# Tuition Payment System (Java OOP Project)

A console-based Java application for managing student tuition payments, demonstrating the core pillars of Object-Oriented Programming (OOP):

Encapsulation | Inheritance | Polymorphism | Abstraction

The program also incorporates:

Interfaces (Payable)

Enums (YearLevel)

Scanner input for user interaction

Packages (main_package, model_package, service_package, interface_package)

📌 Features

✔ View student profile details
✔ Make a payment (Custom or Default 1/5 tuition)
✔ View payment history
✔ Show tuition fee matrix by course and year level
✔ Supports up to 5 payments per student
✔ Uses modern OOP concepts and clean package structure

📌 OOP Concepts Used
1. Abstraction

Person is an abstract class representing a generic person:

    public abstract class Person {
        public abstract void displayInfo();
    }

It forces subclasses like Student to implement displayInfo() for their own specific details.

2. Inheritance

Student extends Person:

    public class Student extends Person implements Payable { ... }

This allows Student to inherit basic attributes like firstName and lastName, while adding tuition-specific features.

3. Polymorphism

Runtime Polymorphism (Overriding):

    @Override
    public boolean processPayment(double amount, String method) { ... }
    
    @Override
    public void displayInfo() { ... }

Compile-time Polymorphism (Overloading):

    public boolean processPayment(double amount, String method);
    public boolean processDefaultPayment();

4. Encapsulation

All sensitive fields are private or protected with getters to control access:

    private double tuition;
    private double paidAmount;
    private Payment[] paymentHistory;

📌 Interface Used

Payable ensures all student accounts implement payment methods:
    
    boolean processPayment(double amount, String method);
    boolean processDefaultPayment();

📌 Enum Used

YearLevel represents student year level, improving code readability:
    
    public enum YearLevel {
        FIRST_YEAR, SECOND_YEAR, THIRD_YEAR, FOURTH_YEAR
    }

🖥 How the Program Works

User starts the program from Main.java:

    TuitionManager app = new TuitionManager();
    app.startingPoint();


User chooses from the main menu:

    Show Profile Details
    
    Make Payment
    
    Payment History
    
    Show Tuition Matrix
    
    Exit

To make a payment, the system allows:

    Custom amount (user enters any valid value)
    
    Default payment (1/5 of total tuition)
    
    Payment rules enforce:
    
    Maximum of 5 payments per student
    
    Minimum payment = 1/5 tuition
    
    Payment cannot exceed remaining balance
    
    Payment history shows the amount, method, and timestamp.
    
    Tuition matrix displays course fees per year level.

📌 Example Output

    ===== MAIN MENU =====
    1) Show Profile Details
    2) Make Payment
    3) Payment History
    4) Show Tuition Matrix
    5) Exit
    Enter choice: 1
    
    Enter Student ID: 001
    
    ===== STUDENT INFORMATION =====
    Name: Marcky Balaba
    Student ID: 001
    Course: BSIT
    Year Level: FIRST_YEAR
    Tuition: 25000.00
    Balance: 25000.00
    
    ===== MAIN MENU =====
    1) Show Profile Details
    2) Make Payment
    3) Payment History
    4) Show Tuition Matrix
    5) Exit
    Enter choice: 2
    
    Enter Student ID: 001
    1) Custom Amount
    2) Default Payment (1/5 tuition)
    Choose: 2
    
    ==== PAYMENT SUCCESSFUL ====
    New Balance: 20000.0
    
    ===== MAIN MENU =====
    ...
