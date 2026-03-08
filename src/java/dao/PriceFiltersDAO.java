/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import model.PriceFiltersDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NamTQ
 * Date: 20/02/2026
 * Description: 
 *  - PriceFiltersDAO is responsible for communicating with the database
 *    to retrieve and manage Price Filter data.
 *  - It extends DBContext to reuse the database connection. 
 */
public class PriceFiltersDAO extends DBContext{
    
    /**
     * Retrieve all price filter records from the database.
     * 
     * @return List of all price filters
     */
    public ArrayList<PriceFiltersDTO> getAllPriceFilters() {
        ArrayList<PriceFiltersDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM PriceFilters ORDER BY minPrice ASC;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new PriceFiltersDTO(
                        rs.getInt("priceFiltersId"), 
                        rs.getNString("label"), 
                        rs.getDouble("minPrice"), 
                        rs.getDouble("maxPrice")
                ));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getAllPriceFilters: " + e.getMessage());
        }
        return data;
    }
    
    /**
     * Retrieve all price filter records from the database.
     * 
     * @return List of all price filters
     */
    public PriceFiltersDTO getPriceFiltersByPriceFiltersId(int priceFiltersId) {
        try {
            String strSQL = "SELECT * FROM PriceFilters WHERE priceFiltersId = ?;";
            PreparedStatement stm = connection.prepareStatement(strSQL); 
            stm.setInt(1, priceFiltersId);
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                return new PriceFiltersDTO(priceFiltersId, rs.getNString("label"), rs.getDouble("minPrice"), rs.getDouble("maxPrice"));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("getPriceFiltersByPriceFiltersId: " + e.getMessage());
        }
        return null;
    }
}
