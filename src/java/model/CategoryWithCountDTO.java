/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * 
 * Data Transfer Object representing a Category along with the number of products in that category.
 *
 * This class extends {@link CategoryDTO} by adding an additional
 * attribute that stores the total number of products belonging
 * to the category.
 *
 * Typically used when retrieving aggregated data from the database,
 * such as using COUNT(*) in SQL queries combined with GROUP BY.
 * 
 * @author NamTQ
 * Date: 10/02/2026
 */
public class CategoryWithCountDTO extends CategoryDTO{
    
    // Total number of products belonging to the category.
    private int numberOfProducts;

    public CategoryWithCountDTO() {
    }

    public CategoryWithCountDTO(int categoryId, String categoryName, int numberOfProducts) {
        super(categoryId, categoryName);
        this.numberOfProducts = numberOfProducts;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
    
}
