/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Data Transfer Object representing a product specification.
 *
 * This class encapsulates a single specification attribute of a product,
 * such as "CPU", "RAM", or "Screen Size", along with its corresponding value.
 *
 * It is typically used to transfer structured specification data
 * from the data layer to the presentation layer for displaying
 * product technical details.
 *
 * This class contains only data fields and accessor methods.
 * It does not include any business logic.
 * 
 * @author NamTQ
 * Date: 13/02/2026
 */
public class ProductSpecDTO {
    
    // Unique identifier of the specification.
    private int specId;
    
    // Name of the specification (e.g., "CPU", "RAM").
    private String specName;
    
    // Value of the specification (e.g., "Intel i7", "16GB").
    private String specValue;

    public ProductSpecDTO() {
    }

    public ProductSpecDTO(int specId, String specName, String specValue) {
        this.specId = specId;
        this.specName = specName;
        this.specValue = specValue;
    }

    public int getSpecId() {
        return specId;
    }

    public String getSpecName() {
        return specName;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
    
}
