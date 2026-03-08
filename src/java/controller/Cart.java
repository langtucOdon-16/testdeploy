/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountDTO;
import model.CartItemDTO;

/**
 *
 * @author KATANA15
 */
@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class Cart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        CartDAO dao = new CartDAO();
        int accountId = account.getAccountId();
        try {
            ArrayList<CartItemDTO> listItem = dao.getByAccountId(accountId);
            session.setAttribute("cart", listItem);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int accountId = account.getAccountId();
        String productStr = request.getParameter("productId");
        int productId = Integer.parseInt(request.getParameter("productId"));
        CartDAO dao = new CartDAO();
        try {
            boolean check = dao.addToCart(accountId, productId, 1);

            // Lưu thông báo vào session, sau đó quay lại trang home (không chuyển sang cart.jsp)
            if (check) {
                session.setAttribute("msg", "Đã thêm sản phẩm vào giỏ hàng");
            } else {
                session.setAttribute("msg", "Sản phẩm đã có trong giỏ hàng");
            }

            response.sendRedirect("HomeServlet");
        } catch (SQLException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
