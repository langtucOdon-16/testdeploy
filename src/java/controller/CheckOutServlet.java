/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AddressDAO;
import dao.CartDAO;
import dao.PaymentDAO;
import dao.ProductDAO;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountDTO;
import model.AddressDTO;
import model.CartItemDTO;
import model.PaymentDTO;

/**
 *
 * @author KATANA15
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/checkout"})
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String[] selectedId = request.getParameterValues("selectedItems");
        PaymentDAO payDao = new PaymentDAO();
        List<PaymentDTO> paymentList = new ArrayList<>();
        // Lấy danh sách các phương thức thanh toán từ database
        try {
            paymentList = payDao.getAllPaymentMethod();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<CartItemDTO> selectedItem = new ArrayList<>();
        // Lấy cart từ session
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int accountId = account.getAccountId();
        AddressDAO daoAdd = new AddressDAO();
        List<AddressDTO> addressList = new ArrayList<>();
        addressList = daoAdd.getByAccountId(accountId);
        CartDAO dao = new CartDAO();
        List<CartItemDTO> cart;
        try {
            cart = dao.getByAccountId(accountId);
            if (selectedId != null && cart != null) {

                // Chuyển mảng selectedId thành Set để tìm nhanh hơn
                Set<String> selectedSet = new HashSet<>(Arrays.asList(selectedId));

                for (CartItemDTO item : cart) {

                    String productIdStr = String.valueOf(item.getProductId());

                    // Nếu sản phẩm được tick
                    if (selectedSet.contains(productIdStr)) {

                        // Lấy quantity mới từ form
                        String qtyParam = request.getParameter("quantity_" + productIdStr);

                        int quantity = 1; // mặc định

                        if (qtyParam != null && !qtyParam.isEmpty()) {
                            quantity = Integer.parseInt(qtyParam);
                        }

                        // Tạo object mới để tránh sửa cart gốc
                        CartItemDTO newItem = new CartItemDTO();
                        newItem.setProductId(item.getProductId());
                        newItem.setProductName(item.getProductName());
                        newItem.setPrice(item.getPrice());
                        newItem.setQuantity(quantity);
                        newItem.setImg(item.getImg());

                        selectedItem.add(newItem);
                    }
                }
            }
            request.setAttribute("addressList", addressList);
            request.setAttribute("paymentList", paymentList);
            session.setAttribute("checkoutItems", selectedItem);
            request.getRequestDispatcher("checkOut.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
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
