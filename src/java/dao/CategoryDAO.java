/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.CategoryDTO;
import model.CategoryWithCountDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for Category entity.
 *
 * This class is responsible for retrieving Category-related data
 * from the database.
 *
 * It extends {@link DBContext} to reuse the database connection.
 * 
 * @author NamTQ
 * Date: 08/02/2026
 */
public class CategoryDAO extends DBContext{
    
    /**
     * Retrieves all categories ordered alphabetically by name.
     *
     * @return list of CategoryDTO
     */
    public ArrayList<CategoryDTO> getAllCategory() {
        ArrayList<CategoryDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM Category ORDER BY categoryName ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new CategoryDTO(
                    rs.getInt("categoryId"), 
                    rs.getNString("categoryName"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllCategory: " + e.getMessage());
        }
        return data;
    }
    
    /**
     * Retrieves featured categories with the number of products
     * in each category.
     *
     * This method queries a database view named
     * featured_categories_view, which is expected
     * to contain aggregated product count data.
     *
     * @return list of CategoryWithCountDTO
     */
    public ArrayList<CategoryWithCountDTO> getFeaturedCategories() {
        ArrayList<CategoryWithCountDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM featured_categories_view;";
            PreparedStatement stm = connection.prepareStatement(strSQL);       
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new CategoryWithCountDTO(
                    rs.getInt("categoryId"), 
                    rs.getNString("categoryName"),
                    rs.getInt("numberOfProducts"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getFeaturedCategories: " + e.getMessage());
        }
        return data;
    }
}
