/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dto.BrandDTO;
import dto.CategoryDTO;
import dto.CategoryWithCountDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
* @author NamTQ
 * Date: 08/02/2026
 * Description: The Servlet coordinates the activities of the home page.
 */
@WebServlet(name="HomeServlet", urlPatterns={"/HomeServlet"})
public class HomeServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        loadCategoriesAndBrands(request);
        loadFeaturedCategories(request);
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
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

    private void loadCategoriesAndBrands (HttpServletRequest request){
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        
        List<CategoryDTO> categoryList = categoryDAO.getAllCategory();
        
        Map<Integer, List<BrandDTO>> brandMap = new HashMap<>();
        for (CategoryDTO categoryDTO : categoryList){
            int key = categoryDTO.getCategoryId();
            brandMap.put(key, brandDAO.getBrandsByCategoryId(key));
        }
        
        request.setAttribute("categories", categoryList);
        request.setAttribute("brands", brandMap);
    }
    
    private void loadFeaturedCategories (HttpServletRequest request){
        CategoryDAO categoryDAO = new CategoryDAO();
        request.setAttribute("featuredCategories", categoryDAO.getFeaturedCategories());
    }
}
