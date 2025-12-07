/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main_package;
import java.sql.Connection;
import service_package.TuitionManager;
import util_package.DBConnection;
/**
 *
 * @author paulo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TuitionManager app = new TuitionManager();
        app.startingPoint();
    }
    
}
