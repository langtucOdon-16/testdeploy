/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.CartItemDTO;

/**
 *
 * @author KATANA15
 */
public class CartDAO extends DBContext {


    public boolean addToCart(int accountId, int productId, int quantity) throws SQLException {
        int cartId;

        // Tìm cartId theo accountId, nếu chưa có thì tạo mới
        String sql = "SELECT cartId FROM Cart WHERE accountId = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cartId = rs.getInt("cartId");
        } else {
            String addCart = "INSERT INTO Cart(accountId) VALUES(?)";
            try (PreparedStatement psCreate = connection.prepareStatement(addCart, Statement.RETURN_GENERATED_KEYS)) {
                psCreate.setInt(1, accountId);
                psCreate.executeUpdate();
                ResultSet rsKeys = psCreate.getGeneratedKeys();
                if (rsKeys.next()) {
                    cartId = rsKeys.getInt(1);
                } else {
                    throw new SQLException("Không lấy được cartId sau khi insert Cart");
                }
            }
        }

        // Kiểm tra sản phẩm đã có trong CartDetails chưa
        String check = "SELECT 1 FROM CartDetails WHERE cartId = ? AND productId = ?";
        PreparedStatement psCheck = null;
        try {
            psCheck = connection.prepareStatement(check);
            psCheck.setInt(1, cartId);
            psCheck.setInt(2, productId);
            rs = psCheck.executeQuery();
            if (rs.next()) {
                return false;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                psCheck.close();
            }
        }
        // Thêm mới sản phẩm vào CartDetails
        String insert = "INSERT INTO CartDetails(cartId, productId, quantity) VALUES(?, ?, ?)";
        PreparedStatement psInsert = null;
        try {
            psInsert = connection.prepareStatement(insert);
            psInsert.setInt(1, cartId);
            psInsert.setInt(2, productId);
            psInsert.setInt(3, quantity);
            psInsert.executeUpdate();
        } finally {
            if (psInsert != null) {
                psInsert.close();
            }
        }
        return true;
    }

    public ArrayList<CartItemDTO> getByAccountId(int accountId) throws SQLException {
        ArrayList<CartItemDTO> listItem = new ArrayList<>();
        String sql = "Select P.productId,P.name as ProductName,P.price,CD.quantity From Cart as C join CartDetails as CD on C.cartId=CD.cartId join Product as P on P.productId=CD.productId where C.accountId=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CartItemDTO item = new CartItemDTO();
            item.setPrice(rs.getDouble("price"));
            item.setProductId(rs.getInt("productId"));
            item.setProductName(rs.getString("ProductName"));
            item.setQuantity(rs.getInt("quantity"));
            listItem.add(item);
        }
        ps.close();
        rs.close();
        return listItem;
    }

    public void deleteProduct(int id) throws SQLException {
        String delete = """
                        DELETE FROM [dbo].[CartDetails]
                        WHERE productId=?""";
        PreparedStatement ps = connection.prepareStatement(delete);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}
