/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AddressDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountDTO;

/**
 *
 * @author : GiangLT
 * @date : 9/2/2026
 * @description : Xử lí chức năng thêm địa chỉ mới cho người dùng
 * trong hệ thống
 * Chức năng chính:
 * doGet(): + Chuyển hướng người dùng về trang profile với tab = address
 *        (profile?tab=address)
 * doPost(): + Thiết lập mã hóa UTF-8 cho request
 *           + Kiểm tra người dùng đã đăng nhập hay chưa thông qua session
 *              + Nếu chưa đăng nhập thì chuyển hướng đến trang login
 *           + Nếu đã đăng nhập:
 *              + Lấy thông tin AccountDTO từ session
 *              + Nhận dữ liệu từ form (addressName, phone)
 *              + Kiểm tra dữ liệu rỗng:
 *                  + Nếu thiếu thông tin thì lưu thông báo lỗi vào session
 *                  và redirect về profile?tab=address
 *              + Kiểm tra định dạng số điện thoại:
 *                  + Phải gồm 10 chữ số và bắt đầu bằng 0
 *                  + Nếu sai định dạng thì lưu thông báo lỗi và redirect
 *              + Nếu dữ liệu hợp lệ:
 *                  + Tạo AddressDAO
 *                  + Gọi phương thức insertAddress(accountId, addressName, phone)
 *                  + Nếu thêm thành công:
 *                      + Lưu thông báo thành công vào session
 *                  + Nếu thất bại:
 *                      + Lưu thông báo lỗi vào session
 *              + Redirect về profile?tab=address để cập nhật giao diện
 */

@WebServlet(name = "AddAddressServlet", urlPatterns = {"/add-address"})
public class AddAddressServlet extends HttpServlet {

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
            out.println("<title>Servlet AddAddressServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAddressServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("profile?tab=address");
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
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }

        AccountDTO acc = (AccountDTO) session.getAttribute("account");

        String addressName = request.getParameter("addressName");
        String phone = request.getParameter("phone");

        if (addressName == null || addressName.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()) {

            session.setAttribute("addressError", "Vui lòng nhập đầy đủ thông tin.");
            response.sendRedirect("profile?tab=address");
            return;
        }

        if (!phone.matches("^0\\d{9}$")) {
            session.setAttribute("addressError", "Số điện thoại phải gồm 10 số và bắt đầu bằng 0.");
            response.sendRedirect("profile?tab=address");
            return;
        }

        try {
            AddressDAO dao = new AddressDAO();
            boolean inserted = dao.insertAddress(acc.getAccountId(),
                    addressName.trim(),
                    phone.trim());

            if (inserted) {
                session.setAttribute("addressSuccess", "Thêm địa chỉ thành công!");
            } else {
                session.setAttribute("addressError", "Không thể thêm địa chỉ.");
            }

        } catch (Exception e) {
            session.setAttribute("addressError", "Có lỗi xảy ra trong hệ thống.");
        }

        response.sendRedirect("profile?tab=address");
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
