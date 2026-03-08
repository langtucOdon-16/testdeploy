/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Data Transfer Object representing basic information of a product.
 *
 * This class encapsulates essential product data required for displaying
 * product listings, search results, or summary views in the application.
 *
 * It contains pricing details, discount information, inventory quantity,
 * and references to related entities such as CategoryDTO and BrandDTO.
 *
 * This class does not contain business logic. It is used strictly
 * for data transfer between application layers.
 * 
 * @author NamTQ
 * Date: 11/02/2026
 */
public class ProductBasicInformationDTO {
    
    // Unique identifier of the product.
    private int productId;
    
    // Name of the product.
    private String name;
    
    // URL path of the product image.
    private String urlImage;
    
    // Available quantity in stock.
    private int quantity;
    
    // Original price before discount.
    private double originalPrice;
    
    // Final price after applying discount.
    private double afterDiscountPrice;
    
    // Discount rate applied to the product (e.g., 0.15 for 15%).
    private double discountRate;
    
    // Category information associated with the product.
    private CategoryDTO category;
    
    // Brand information associated with the product.
    private BrandDTO brand;

    public ProductBasicInformationDTO() {
    }

    public ProductBasicInformationDTO(int productId, String name, String urlImage, int quantity, double originalPrice, double afterDiscountPrice, double discountRate, CategoryDTO category, BrandDTO brand) {
        this.productId = productId;
        this.name = name;
        this.urlImage = urlImage;
        this.quantity = quantity;
        this.originalPrice = originalPrice;
        this.afterDiscountPrice = afterDiscountPrice;
        this.discountRate = discountRate;
        this.category = category;
        this.brand = brand;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getAfterDiscountPrice() {
        return afterDiscountPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setAfterDiscountPrice(double afterDiscountPrice) {
        this.afterDiscountPrice = afterDiscountPrice;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

}
