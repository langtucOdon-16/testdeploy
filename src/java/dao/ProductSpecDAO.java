/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.ProductSpecDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NamTQ
 * Date: 14/02/2026
 * Description: 
 *  - ProductSpecDAO is responsible for interacting with the database
 *    to retrieve Product Specification data.
 *  - It extends DBContext to reuse the database connection.
 */
public class ProductSpecDAO extends DBContext{
    
    /**
     * Retrieve all specifications of a specific product.
     *
     * @param productId - The ID of the product
     * @return List of product specifications
     *
     * Business Logic:
     *  - Each record in ProductSpec represents one specification 
     *    (e.g., CPU, RAM, Storage, Display, etc.).
     *  - Multiple specifications can belong to one product.
     */
    public List<ProductSpecDTO> getProductSpecs(int productId) {
        List<ProductSpecDTO> data = new ArrayList<>();
        try {
                String strSQL = "SELECT * "
                        + "FROM ProductSpec "
                        + "WHERE productId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL);   
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();  
            
            while (rs.next()) {
                data.add(new ProductSpecDTO(
                        rs.getInt("specId"), 
                        rs.getNString("specName"),
                        rs.getNString("specValue"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getProductSpecs: " + e.getMessage());
        }
        return data;
    }
}
