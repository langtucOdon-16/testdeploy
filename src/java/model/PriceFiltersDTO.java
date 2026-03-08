/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Data Transfer Object representing a price filter range.
 *
 * This class encapsulates filtering criteria based on a price range,
 * including minimum and maximum price boundaries along with a display label.
 *
 * Typically used in product filtering features where users select
 * predefined price ranges (e.g., "Under $500", "$500 - $1000").
 *
 * This class contains only data fields and accessor methods.
 * It does not contain any business logic.
 * 
 * @author NamTQ
 * Date: 19/02/2026
 */
public class PriceFiltersDTO {
    
    // Unique identifier of the price filter.
    private int priceFiltersId;
    
    // Display label of the price filter (e.g., "Under $500").
    private String label;
    
    // Minimum price boundary of the filter.
    private double minPrice;
    
    // Maximum price boundary of the filter.
    private double maxPrice;

    public PriceFiltersDTO() {
    }

    public PriceFiltersDTO(int priceFiltersId, String label, double minPrice, double maxPrice) {
        this.priceFiltersId = priceFiltersId;
        this.label = label;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getPriceFiltersId() {
        return priceFiltersId;
    }

    public String getLabel() {
        return label;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setPriceFiltersId(int priceFiltersId) {
        this.priceFiltersId = priceFiltersId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

}
