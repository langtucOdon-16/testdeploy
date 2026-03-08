/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
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
 *  - HomeServlet acts as the Controller of the Home page.
 *  - It coordinates between DAO layer and View (home.jsp).
 *  - It retrieves featured categories and top products,
 *    then forwards the data to the JSP page for rendering.
 */
@WebServlet(name="HomeServlet", urlPatterns={"/HomeServlet"})
public class HomeServlet extends HttpServlet {
   
    /**
     * Processes requests for both HTTP GET and POST methods.Responsibilities:
  - Load necessary data for the home page.- Set attributes into request scope.
     *
     * - Forward request to home.jsp.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Load data for homepage
        loadFeaturedCategories(request);
        loadTop10Products(request);
        
        // Load data for homepage
        request.getRequestDispatcher("home.jsp").forward(request, response);
    } 

    /**
     * Load featured categories from database.
     * 
     * @param request HttpServletRequest object
     *
     * Business Logic:
     *  - Calls CategoryDAO to retrieve featured categories.
     *  - Stores result in request scope as "featuredCategories".
     */
    private void loadFeaturedCategories (HttpServletRequest request){
        CategoryDAO categoryDAO = new CategoryDAO();
        request.setAttribute("featuredCategories", categoryDAO.getFeaturedCategories());
    }
    
    /**
     * Load featured categories from database.
     * 
     * @param request HttpServletRequest object
     *
     * Business Logic:
     *  - Calls CategoryDAO to retrieve featured categories.
     *  - Stores result in request scope as "featuredCategories".
     */
    private void loadTop10Products (HttpServletRequest request){
        ProductDAO productDAO = new ProductDAO();
        request.setAttribute("top10HotDealProducts", productDAO.getTop10HotDealProducts());
        request.setAttribute("top10SellingProducts", productDAO.getTop10SellingProducts());
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
