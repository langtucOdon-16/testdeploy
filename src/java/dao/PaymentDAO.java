/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PaymentDTO;

/**
 *
 * @author KATANA15
 */
public class PaymentDAO extends DBContext {

    public List<PaymentDTO> getAllPaymentMethod() throws SQLException {
        List<PaymentDTO> paymentList = new ArrayList<>();
        String sql = "SELECT [paymentId]\n"
                + "      ,[method]\n"
                + "  FROM [dbo].[PaymentMethod]";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PaymentDTO payment = new PaymentDTO();
                payment.setPaymentId(rs.getInt("paymentId"));
                payment.setNamePayment(rs.getString("method"));
                paymentList.add(payment);
            }
            return paymentList;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
