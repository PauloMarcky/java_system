/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model_package;

/**
 *
 * @author paulo
 */
public abstract class Person {
    private String studentId;
    private String lastName;
    private String firstName;

    public Person() {
        this.studentId = "000";
        this.lastName = "Unknown";
        this.firstName = "Unknown";
    }

    public Person(String studentId, String lastName, String firstName) {
        this.studentId = studentId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    // getters/setters -> encapsulation
    public String getStudentId(){
        return studentId; 
    }
    
    public void setStudentId(String studentId){
        this.studentId = studentId; 
    }

    public String getLastName(){
        return lastName; 
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName; 
    }

    public String getFirstName(){
        return firstName; 
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName; 
    }

    // abstract method -> must be implemented by subclasses
    public abstract void displayInfo();
    
}
