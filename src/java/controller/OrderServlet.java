/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AddressDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.AccountDTO;
import model.CartItemDTO;
import model.OrderDTO;
import service.TransactionOrder;

/**
 *
 * @author KATANA15
 */
@WebServlet(name = "Order", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Order</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Order at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
            return;
        }
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int accountId = account.getAccountId();
        try {
            int page = 1;
            int pageSize = 5;//set cho 1 trang chỉ hiển thị 5 đơn

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            // set trường hợp lấy về page=0
            page = Math.max(page, 1);
            //set để bỏ qua số sản phẩm đầu tiên mình muốn theo hệ số 5
            int offset = (page - 1) * pageSize;
            OrderDAO dao = new OrderDAO(new service.TransactionOrder().connection);
            // Lấy tổng số đơn
            int totalOrders = dao.countOrdersByAccount(accountId);
            List<OrderDTO> orderList = dao.items(accountId, offset, pageSize);
            for (OrderDTO order : orderList) {
                order.setItems(dao.listItem(order.getId()));
            }
            // tổng số trang cần để phân
            int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
            request.setAttribute("orders", orderList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        } catch (Exception e) {
            getServletContext().log("DEBUG: Exception in /order doGet", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().log("DEBUG: HIT /order doPost, paymentId=" + request.getParameter("paymentId"));

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
            return;
        }
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int accountId = account.getAccountId();
        List<CartItemDTO> items = (List<CartItemDTO>) session.getAttribute("checkoutItems");

        if (items == null || items.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }
        String paymentStr = request.getParameter("paymentId");
        int paymentId;
        try {
            paymentId = Integer.parseInt(paymentStr);
        } catch (Exception e) {
            request.getRequestDispatcher("checkOut.jsp").forward(request, response);
            return;
        }
        double total = 0;
        for (CartItemDTO item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        String addressStr = request.getParameter("addressId");
        int addressId = Integer.parseInt(addressStr);
        System.out.println("AddressId = " + addressId);
        AddressDAO adDao = new AddressDAO();
        String addressname = adDao.selectAddressName(addressId);
        if (addressname == null || addressname.isEmpty()) {
            response.sendRedirect("home");
            return;
        }
        TransactionOrder transaction = new TransactionOrder();
        try {
            boolean check = transaction.transactionOrder(accountId, paymentId, addressname, total, items);

            if (!check) {
                request.setAttribute("orderError", "TRANSACTION_FAILED");
                request.getRequestDispatcher("cart").forward(request, response);
                return;
            }
            session.removeAttribute("checkoutItems");
            response.sendRedirect("order");
        } catch (Exception e) {
            request.setAttribute("orderError", "EXCEPTION");
            request.getRequestDispatcher("checkOut.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
