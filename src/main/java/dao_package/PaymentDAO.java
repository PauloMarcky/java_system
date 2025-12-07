/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao_package;
import model_package.Payment;
import util_package.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author paulo
 */
public class PaymentDAO {
    /**
    * Payment persistence
    */
     public boolean savePayment(Payment p) {
        String sql = "INSERT INTO payments (student_id, amount, payment_date, method) VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getStudentId());
            ps.setDouble(2, p.getAmount());
            Timestamp ts = Timestamp.valueOf(p.getPaymentDate());
            ps.setTimestamp(3, ts);
            ps.setString(4, p.getMethod());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        p.setPaymentId(keys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Payment> getPaymentsByStudent(String studentId) {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT payment_id, student_id, amount, payment_date, method FROM payments WHERE student_id = ? ORDER BY payment_date";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("payment_id");
                    String sid = rs.getString("student_id");
                    double amount = rs.getDouble("amount");
                    Timestamp ts = rs.getTimestamp("payment_date");
                    LocalDateTime dt = ts.toLocalDateTime();
                    String method = rs.getString("method");
                    Payment p = new Payment(id, sid, amount, dt, method);
                    list.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
}
