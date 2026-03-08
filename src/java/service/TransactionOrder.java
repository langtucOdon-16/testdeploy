/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.DBContext;
import dao.OrderDAO;
import java.sql.SQLException;
import java.util.List;
import model.CartItemDTO;

/**
 *
 * @author KATANA15
 */
public class TransactionOrder extends DBContext {

    public boolean transactionOrder(int accountId, int paymentId, String addressName, double totalAmount, List<CartItemDTO> items) throws SQLException {
        try {
            connection.setAutoCommit(false);
            OrderDAO dao = new OrderDAO(connection);
            System.out.println("DEBUG Transaction: accountId=" + accountId
                    + ", paymentId=" + paymentId
                    + ", addressId=" + addressName
                    + ", total=" + totalAmount
                    + ", items=" + items.size());
            int orderId = dao.insertOrder(accountId, paymentId, addressName, totalAmount);
            for (CartItemDTO item : items) {
                Boolean updateSuccess = dao.updateQuantityItem(item.getProductId(), item.getQuantity());
                if (!updateSuccess) {
                    connection.rollback();
                    return false;
                }
                dao.insertOrderDetails(orderId, item);
                //tat ca ok thi commit
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
