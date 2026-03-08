/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author KATANA15
 */
public class ConfirmDAO extends DBContext {

    public boolean confirm(String status, int orderId) throws SQLException {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [status] = ?\n"
                + " WHERE orderId=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderId);
            int check = ps.executeUpdate();
            return check > 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
