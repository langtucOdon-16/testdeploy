/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author : GiangLT
 * @date : 8/2/2026
 * @description : Xử lí chức năng đặt lại mật khẩu sau khi người dùng đã xác
 * thực OTP thành công trong quy trình 'Quên mật khẩu' 
 * Chức năng chính: 
 * doGet(): + Kiểm tra session forgotVerified 
 *          + Nếu chưa xác thực OTP thì chuyển hướng về forgotPassword 
 *          + Nếu đã xác thực thì chuyển tiếp đến trang resetPassword.jsp
 * doPost(): + Nhận mật khẩu mới và mật khẩu xác nhận từ form 
 *           + Kiểm tra mật khẩu có khớp hay không 
 *           + Nếu hợp lệ thì tạo AccountDAO và cập nhật mật khẩu trong cơ sở dữ liệu 
 *           + Nếu thất bại hiển thị thông báo lỗi 
 *           + Nếu thành công 
 *              + Xoá các session liên quan đến quá trình quên mật khẩu 
 *              + Chuyển hướng người dùng về trang đăng nhập
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetPassword"})
public class ResetPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ResetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
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
        Boolean verify = (Boolean) session.getAttribute("forgotVerified");
        if (verify == null || !verify) {
            response.sendRedirect(request.getContextPath() + "/forgotPassword");
            return;
        }

        request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
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
        Boolean verify = (Boolean) session.getAttribute("forgotVerified");
        if (verify == null || !verify) {
            response.sendRedirect(request.getContextPath() + "/forgotPassword");
            return;
        }
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = (String) session.getAttribute("forgotEmail");

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }

        try {
            AccountDAO dao = new AccountDAO();
            boolean success = dao.updatePassword(email, newPassword);

            if (success) {
                session.removeAttribute("forgotVerified");
                session.removeAttribute("forgotOtp");
                session.removeAttribute("forgotEmail");

                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Có lỗi xảy ra khi đổi mật khẩu!");
                request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();

            request.setAttribute("error", "Hệ thống đang gặp sự cố. Vui lòng thử lại sau.");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
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
