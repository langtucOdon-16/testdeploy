/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * BrandDTO
 *
 * Data Transfer Object (DTO) representing a product brand in the system.
 *
 * This class is used to transfer brand data between different layers of the application (DAO → Service → Controller → View).
 *
 * It typically maps to the Brand table in the database.
 *
 * Author: NamTQ  
 * Created on: 08/02/2026
 */
public class BrandDTO {
    
    // Unique identifier of the brand.
    private int brandId;
    
    // Name of the brand.
    private String brandName;

    public BrandDTO() {
    }

    public BrandDTO(int brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
}
