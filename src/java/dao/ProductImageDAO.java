/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.ProductImageDTO;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: 
 *  - ProductImageDAO is responsible for interacting with the database
 *    to retrieve Product Image data.
 *  - It extends DBContext to reuse the database connection.
 */
public class ProductImageDAO extends DBContext{
    
    /**
     * Retrieve all images belonging to a specific product.
     *
     * @param productId - The ID of the product
     * @return List of images associated with the product
     *
     * Business Logic:
     *  - Query the ProductImage table by productId.
     *  - Each record represents one image of the product.
     */
    public List<ProductImageDTO> getProductImages(int productId) {
        List<ProductImageDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * "
                    + "FROM ProductImage "
                    + "WHERE productId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL);   
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                data.add(new ProductImageDTO(
                        rs.getInt("imageId"),  
                        rs.getString("urlImage"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getProductImages: " + e.getMessage());
        }
        return data;
    }
}
