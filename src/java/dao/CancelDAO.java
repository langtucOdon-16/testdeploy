/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KATANA15
 */
public class CancelDAO extends DBContext {

    public boolean cancelOrder(String status, int orderId) throws SQLException {
        try {
            connection.setAutoCommit(false); // bắt đầu transaction

            // Lấy danh sách đơn hàng trong Order để nếu bị hủy thì update;
            String select = "SELECT [productId]\n"
                    + "      ,[quantity]\n"
                    + "  FROM [ASSIGNMENT3].[dbo].[OrderDetails] where orderId=?";
            PreparedStatement ps1 = connection.prepareStatement(select);
            ps1.setInt(1, orderId);
            ResultSet rs = ps1.executeQuery();
            //update đơn hàng khi ấn nút Hủy
            String updateQuantity = "UPDATE [dbo].[Product]\n"
                    + "   SET [quantity] = quantity+?\n"
                    + " WHERE productId=?";
            PreparedStatement ps2 = connection.prepareStatement(updateQuantity);
            while (rs.next()) {
                int productId = rs.getInt("productId");
                int quantity = rs.getInt("quantity");
                ps2.setInt(1, quantity);
                ps2.setInt(2, productId);
                ps2.executeUpdate();
            }
            //Update trạng thái đơn
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [status] = ? WHERE orderId=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw (e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
