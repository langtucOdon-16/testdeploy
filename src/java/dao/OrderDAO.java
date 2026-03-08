/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.CartItemDTO;
import model.OrderDTO;

/**
 *
 * @author KATANA15
 */
public class OrderDAO {

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // sửa addressId thay bằng addressName lưu name vào lịch sử
    public int insertOrder(int accountId, int paymentId, String addressName, double totalAmount) throws SQLException {
        int orderId;
        String insert = "INSERT INTO [dbo].[Order]\n"
                + "([accountId]\n"
                + ",[timeOrder]\n"
                + ",[status]\n"
                + ",[paymentId]\n"
                + ",[addressName]\n"
                + ",[totalAmount])\n"
                + "VALUES\n"
                + "(?,GETDATE(),'Pending',?,?,?)";
        PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, accountId);
        ps.setInt(2, paymentId);
        ps.setNString(3, addressName);
        ps.setDouble(4, totalAmount);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("Insert order failed, no ID obtained.");
    }

    public void insertOrderDetails(int orderId, CartItemDTO item) throws SQLException {
        String insertOD = "INSERT INTO [dbo].[OrderDetails]\n"
                + "           ([orderId]\n"
                + "           ,[productId]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insertOD);
            ps.setInt(1, orderId);
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public boolean updateQuantityItem(int productId, int stock) throws SQLException {
        String update = "UPDATE [dbo].[Product]\n"
                + "   SET [quantity] = quantity-? \n"
                + " WHERE productId=? AND quantity>=?";
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setInt(1, stock);
        ps.setInt(2, productId);
        ps.setInt(3, stock);
        int check = ps.executeUpdate();
        return check > 0;
    }

    public List<OrderDTO> items(int accountId, int offset, int limit) throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT [orderId],[timeOrder],[status],[addressName],[totalAmount] "
                + "FROM [dbo].[Order] "
                + "WHERE accountId = ? "
                + "ORDER BY timeOrder DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountId);
        ps.setInt(2, offset);
        ps.setInt(3, limit);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            OrderDTO order = new OrderDTO();
            order.setAccountId(accountId);
            order.setId(rs.getInt("orderId"));
            order.setStatus(rs.getString("status"));
            order.setAddressName(rs.getString("addressName"));
            order.setTotalAmount(rs.getDouble("totalAmount"));
            order.setOrderDate(rs.getTimestamp("timeOrder"));
            orders.add(order);
        }
        return orders;
    }

    public List<CartItemDTO> listItem(int orderId) throws SQLException {
        List<CartItemDTO> items = new ArrayList<>();
        String selectItem = "Select P.name as Pname,P.productId as PProductId,OD.Quantity as ODquantity,OD.Price as ODprice,urlImage from OrderDetails as OD join Product as P on OD.productId=P.productId where orderId=?";
        PreparedStatement ps = connection.prepareStatement(selectItem);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CartItemDTO item = new CartItemDTO();
            item.setImg(rs.getString("urlImage"));
            item.setPrice(rs.getDouble("ODprice"));
            item.setProductId(rs.getInt("PProductId"));
            item.setProductName(rs.getString("Pname"));
            item.setQuantity(rs.getInt("ODquantity"));
            items.add(item);
        }
        return items;
    }

    public int countOrdersByAccount(int accountId) throws Exception {
        String sql = "SELECT COUNT(*) FROM [dbo].[Order] WHERE accountId = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

}
