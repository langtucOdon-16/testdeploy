/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * 
 * @author NamTQ
 * Date: 10/02/2026
 * Description: Model represents Category with the quantity of products in each category.
 */
public class CategoryWithCountDTO extends CategoryDTO{
    
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
