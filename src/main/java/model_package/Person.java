// Person abstract class - base class for people
package model_package;

public abstract class Person {

    protected String firstName; // First name
    protected String lastName; // Last name
    
    // Constructor for person
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
        // Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName; 
    }
    
    // Method to be implemented by subclasses
    public abstract void displayInfo();
    
}
