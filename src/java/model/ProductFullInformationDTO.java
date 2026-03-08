/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 * Data Transfer Object representing full product information.
 *
 * This class extends {@link ProductBasicInformationDTO} by adding
 * additional detailed attributes such as color and release date.
 *
 * It is typically used when displaying detailed product pages
 * where more information is required beyond basic listing data.
 *
 * This class contains only data fields and accessor methods.
 * It does not contain any business logic.
 * 
 * @author NamTQ
 * Date: 13/02/2026
 */
public class ProductFullInformationDTO extends ProductBasicInformationDTO {
    
    // Color of the product.
    private String color;
    
    // Official release date of the product.
    private LocalDate releaseDate;

    public ProductFullInformationDTO() {
    }

    public ProductFullInformationDTO(int productId, String name, String urlImage, int quantity, double originalPrice, double afterDiscountPrice, double discountRate, CategoryDTO category, BrandDTO brand, String color, LocalDate releaseDate) {
        super(productId, name, urlImage, quantity, originalPrice, afterDiscountPrice, discountRate, category, brand);
        this.color = color;
        this.releaseDate = releaseDate;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}
