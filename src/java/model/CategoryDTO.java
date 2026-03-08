/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * 
 * Data Transfer Object representing a Category.
 *
 * This class is used to transfer category data between different layers of the application (e.g., DAO, Service, Controller).
 *
 * This class contains only data fields, constructors, and accessor methods.
 * It does not contain any business logic.
 * 
 * @author NamTQ
 * Date: 08/02/2026
 */
public class CategoryDTO {
    
    // Unique identifier of the category.
    private int categoryId;
    
    // Name of the category.
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
