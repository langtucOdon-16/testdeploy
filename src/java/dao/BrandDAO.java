/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import dto.BrandDTO;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: Communicate with the database to use and update Brand data.
 */
public class BrandDAO extends DBContext{
    
    public ArrayList<BrandDTO> getBrandsByCategoryId(int categoryId) {
        ArrayList<BrandDTO> data = new ArrayList<>();
        try {
            String strSQL = "SELECT DISTINCT B.brandId, brandName " 
                    + "FROM Brand B INNER JOIN Product P "
                    + "ON B.brandId = P.brandId AND categoryId = ?;";
            PreparedStatement stm = connection.prepareCall(strSQL); 
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();                          
            while (rs.next()) {
                data.add(new BrandDTO(
                        rs.getInt("brandId"), 
                        rs.getNString("brandName"))
                );
            }
        } catch (SQLException e) {
            System.out.println("getBrandByCategoryId: " + e.getMessage());
        }
        return data;
    }
    
}
