/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.BrandDTO;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Data Access Object for Brand entity.
 *
 * This class is responsible for communicating with the database
 * to retrieve Brand-related data.
 *
 * It extends {@link DBContext} to reuse the database connection.
 * 
 * @author NamTQ
 * Date: 08/02/2026
 */
public class BrandDAO extends DBContext{
    
    /**
     * Retrieves all distinct brands that belong to a specific category.
     *
     * This method performs a JOIN between Brand and Product tables
     * to filter brands by category.
     *
     * @param categoryId the category identifier
     * @return list of BrandDTO belonging to the specified category
     */
    public ArrayList<BrandDTO> getBrandsByCategoryId(int categoryId) {
        ArrayList<BrandDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT DISTINCT B.brandId, brandName " 
                    + "FROM Brand B INNER JOIN Product P "
                    + "ON B.brandId = P.brandId AND categoryId = ? "
                    + "ORDER BY brandName ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new BrandDTO(
                        rs.getInt("brandId"), 
                        rs.getNString("brandName"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getBrandByCategoryId: " + e.getMessage());
        }
        return data;
    }
    
    /**
     * Retrieves all brands from the database.
     *
     * @return list of all BrandDTO ordered alphabetically by brand name
     */
    public ArrayList<BrandDTO> getAllBrand(){
        ArrayList<BrandDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * " 
                    + "FROM Brand "
                    + "ORDER BY brandName ASC; ";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new BrandDTO(
                        rs.getInt("brandId"), 
                        rs.getNString("brandName"))
                );
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllBrand: " + e.getMessage());
        }
        return data;
    }
}
