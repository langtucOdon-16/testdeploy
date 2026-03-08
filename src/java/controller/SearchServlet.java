/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.PriceFiltersDAO;
import dao.ProductDAO;
import model.BrandDTO;
import model.CategoryDTO;
import model.PriceFiltersDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * SearchServlet
 *
 * This servlet handles product search functionality, including:
 *  - Keyword-based searching
 *  - Filtering by category, brand, and price range
 *  - Sorting results
 *  - Pagination handling
 *
 * It prepares all necessary data and forwards the request to the search page (search.jsp).
 * 
 * @author NamTQ
 * Date: 08/02/2026
 */
@WebServlet(name="SearchServlet", urlPatterns={"/SearchServlet"})
public class SearchServlet extends HttpServlet {
   
    /**
     * Processes both HTTP GET and POST requests.
     * 
     * Retrieves search parameters from the request, loads filter options,
     * performs product searching with pagination, and forwards the result
     * to the search.jsp page.
     *
     * @param request  the HttpServletRequest object containing client request data
     * @param response the HttpServletResponse object for sending response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Retrieve search parameters from request
        String keyword = request.getParameter("keyword");
        String[] categoryValues = request.getParameterValues("categoryIdName");
        String[] brandValues = request.getParameterValues("brandIdName");
        String[] priceFiltersValues = request.getParameterValues("priceIdName");
        String sortingType = request.getParameter("sortingType");
        String page = request.getParameter("currentPage");
        
        // Retrieve search parameters from request
        loadAllCategory(request);
        loadAllBrand(request);
        loadAllPriceFilters(request);
        loadAllSort(request, sortingType);
        loadSearchProduct(request, keyword, categoryValues, brandValues, priceFiltersValues, sortingType, page);
        
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
    
    /**
     * Loads all categories for checkbox filter.
     * Adds a default "All" option at the top of the list.
     *
     * @param request HttpServletRequest used to store attributes
     */
    private void loadAllCategory (HttpServletRequest request){
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<CategoryDTO> categoryList = new ArrayList<>();
        
        categoryList.add(new CategoryDTO(-1, "Tất cả"));
        for (CategoryDTO category : categoryDAO.getAllCategory()){
            categoryList.add(category);
        }

        request.setAttribute("allCategory", categoryList);
    }
    
    /**
     * Loads all brands for checkbox filter.
     * Adds a default "All" option at the top of the list.
     *
     * @param request HttpServletRequest used to store attributes
     */
    private void loadAllBrand (HttpServletRequest request){
        BrandDAO brandDAO = new BrandDAO();
        ArrayList<BrandDTO> brandList = new ArrayList<>();
        
        brandList.add(new BrandDTO(-1, "Tất cả"));
        for (BrandDTO brand : brandDAO.getAllBrand()){
            brandList.add(brand);
        }
        
        request.setAttribute("allBrand", brandList);
    }
    
    /**
     * Loads all price filters for checkbox filter.
     * Adds a default "All" option at the top of the list.
     *
     * @param request HttpServletRequest used to store attributes
     */
    private void loadAllPriceFilters (HttpServletRequest request){
        PriceFiltersDAO priceFiltersDAO = new PriceFiltersDAO();
        ArrayList<PriceFiltersDTO> priceFiltersList = new ArrayList<>();
        
        priceFiltersList.add(new PriceFiltersDTO(-1, "Tất cả", 0, 0));
        for (PriceFiltersDTO priceFilters : priceFiltersDAO.getAllPriceFilters()){
            priceFiltersList.add(priceFilters);
        }
        
        request.setAttribute("allPriceFilters", priceFiltersList);
    }
    
    /**
     * Determines the selected sorting type.
     * If no sorting option is provided, the default sorting is "decreaseDiscount".
     *
     * @param request     HttpServletRequest used to store attributes
     * @param sortingType sorting option selected by the user
     */
    private  void loadAllSort (HttpServletRequest request, String sortingType){
        if (sortingType == null || sortingType.isEmpty()){
            request.setAttribute("sortingType", "decreaseDiscount");
        }else {
            request.setAttribute("sortingType", sortingType);
        }
    }
    
    /**
     * Loads searched products with filtering, sorting, and pagination logic.
     *
     * This method:
     *  - Calculates total number of results
     *  - Determines total number of pages
     *  - Builds page navigation list
     *  - Retrieves products for the current page
     *
     * @param request             HttpServletRequest used to store attributes
     * @param keyword             search keyword
     * @param categoryValues      selected category filters
     * @param brandValues         selected brand filters
     * @param priceFiltersValues  selected price range filters
     * @param sortingType         sorting option
     * @param page                current page number
     */
    private void loadSearchProduct (HttpServletRequest request, String keyword, String[] categoryValues, 
            String[] brandValues, String[] priceFiltersValues, String sortingType, String page){
        try{
            ProductDAO productDAO = new ProductDAO();
            int totalProduct = productDAO.getTotalSearchProduct(keyword, categoryValues, brandValues, priceFiltersValues);
            int numberOfPage = totalProduct / 20 + 1;
            int currentPage = 1;
            ArrayList<Integer> pageList = new ArrayList<>();
            
            if (page != null && !page.isEmpty()){
                currentPage = Integer.parseInt(page);
            }
            
            // Generate pagination range (max 5 pages shown)
            if (currentPage - 2 < 1){
                for (int i = 1; i <= 5; i++){
                    if (i <= numberOfPage){
                        pageList.add(i);
                    }
                }
            }else if (currentPage + 2 > numberOfPage){
                for (int i = numberOfPage - 5; i <= numberOfPage; i++){
                    if (i >= 1){
                        pageList.add(i);
                    }
                }
            }else {
                for (int i = currentPage - 2; i <= currentPage + 2; i++){
                    pageList.add(i);
                }
            }
            
            request.setAttribute("numberPageList", pageList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("numberOfPage", numberOfPage);
            request.setAttribute("numberOfResult", totalProduct);
            request.setAttribute("searchProduct", productDAO.getSearchProduct(keyword, categoryValues, brandValues, priceFiltersValues, sortingType, currentPage));
        }
        catch (NullPointerException e){
            System.out.println("loadSearchProduct: " + e.getMessage());
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
