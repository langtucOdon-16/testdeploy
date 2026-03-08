/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CommentDAO;
import dao.ProductDAO;
import dao.ProductImageDAO;
import dao.ProductSpecDAO;
import model.ProductFullInformationDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: 
 *  - ProductDetailsServlet acts as the Controller for the Product Details page.
 *  - It retrieves full product information, images, specifications,
 *    and related products from the database.
 *  - Then it forwards the data to product-details.jsp for rendering.
 */
@WebServlet(name="ProductDetailsServlet", urlPatterns={"/ProductDetailsServlet"})
public class ProductDetailsServlet extends HttpServlet {
   
    /**
     * Processes requests for both HTTP GET and POST methods.Responsibilities:
  - Retrieve productId from request parameter.- Load all necessary product information.
     *
     * - Forward request to product-details.jsp.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get productId from URL parameter
        String productIdString = request.getParameter("productId");
        
        // Load product data
        loadProductInformation(request, productIdString);
        
        // Load comment data
        loadComment(request, productIdString);
        
        // Forward to View layer
        request.getRequestDispatcher("product-details.jsp").forward(request, response);
    } 
    
    /**
     * Load full product information and related data.
     *
     * @param request HttpServletRequest object
     * @param productIdString productId retrieved from request parameter
     *
     * Business Logic:
     *  - Convert productId to integer.
     *  - Retrieve:
     *      + Full product information
     *      + Product images
     *      + Product specifications
     *      + Top related products
     *  - Store all results in request scope.
     */
    private void loadProductInformation (HttpServletRequest request, String productIdString){  
        try {
            int productId = Integer.parseInt(productIdString);
            
            ProductDAO productDAO = new ProductDAO();
            ProductImageDAO productImageDAO = new ProductImageDAO();
            ProductSpecDAO productSpecDAO = new ProductSpecDAO();
            ProductFullInformationDTO productFullInformationDTO = productDAO.getProductFullInformation(productId);
            
            request.setAttribute("productFullInformation", productFullInformationDTO);
            request.setAttribute("productImages", productImageDAO.getProductImages(productId));
            request.setAttribute("productSpecs", productSpecDAO.getProductSpecs(productId));
            request.setAttribute("top10RelatedProducts", productDAO.getTop5RelatedProducts(
                    productId,
                    productFullInformationDTO.getCategory().getCategoryId(), 
                    productFullInformationDTO.getBrand().getBrandId()));
        } catch (NumberFormatException e) {
            System.out.println("loadProductFullInformation: " + e.getMessage());
        }
    }
    
    /**
    * Loads all comments related to a specific product and
    * stores them in the request scope.
    *
    * The comment list will later be used by the JSP page for rendering.
    *
    * @param request          the HttpServletRequest object used to store attributes
    * @param productIdString  the product ID received as a String (typically from request parameter)
    */
    private void loadComment(HttpServletRequest request, String productIdString) {
        try {
            int productId = Integer.parseInt(productIdString);
            CommentDAO commentDAO = new CommentDAO();

            request.setAttribute("commentList",commentDAO.getCommentsByProductId(productId));
        } catch (NumberFormatException e) {
            System.out.println("loadProductFullInformation: " + e.getMessage());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
