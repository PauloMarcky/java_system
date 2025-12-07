/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao_package;
import model_package.Student;
import model_package.YearLevel;
import util_package.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author paulo
 */
public class StudentDAO {
    
    /**
    * Data access object for student persistence
    */
    
    // get all students -> used to build array of objects
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT student_id, last_name, first_name, course, year_level, tuition, paid_amount FROM students";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("student_id");
                String last = rs.getString("last_name");
                String first = rs.getString("first_name");
                String course = rs.getString("course");
                int yl = rs.getInt("year_level");
                double tuition = rs.getDouble("tuition");
                double paid = rs.getDouble("paid_amount");
                Student st = new Student(id, last, first, course, yl, tuition);
                st.setPaidAmount(paid);
                list.add(st);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Student findStudentById(String studentId) {
        String sql = "SELECT student_id, last_name, first_name, course, year_level, tuition, paid_amount FROM students WHERE student_id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("student_id");
                    String last = rs.getString("last_name");
                    String first = rs.getString("first_name");
                    String course = rs.getString("course");
                    int yl = rs.getInt("year_level");
                    double tuition = rs.getDouble("tuition");
                    double paid = rs.getDouble("paid_amount");
                    Student st = new Student(id, last, first, course, yl, tuition);
                    st.setPaidAmount(paid);
                    return st;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // update paid_amount when payment processed
    public boolean updatePaidAmount(String studentId, double newPaidAmount) {
        String sql = "UPDATE students SET paid_amount = ? WHERE student_id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, newPaidAmount);
            ps.setString(2, studentId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
