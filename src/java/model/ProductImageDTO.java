/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Data Transfer Object representing a product image.
 *
 * This class encapsulates image information associated with a product,
 * including the image identifier and its URL path.
 *
 * It is typically used when transferring image data between layers
 * of the application, such as from the database layer to the presentation layer.
 *
 * This class contains only data fields and accessor methods.
 * It does not contain any business logic.
 * 
 * @author NamTQ
 * Date: 13/02/2026
 */

public class ProductImageDTO {
    
    // Unique identifier of the image.
    private int imageId;
    
    // URL path of the image.
    private String urlImage;

    public ProductImageDTO() {
    }

    public ProductImageDTO(int imageId, String urlImage) {
        this.imageId = imageId;
        this.urlImage = urlImage;
    }

    public int getImageId() {
        return imageId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    
}
