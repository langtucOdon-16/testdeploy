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
import model.AccountDTO;

/**
 *
 * @author : GiangLT
 * @date : 9/2/2026
 * @description : Xử lí chức năng đổi mật khẩu trong hồ sơ cá nhân khi người dùng
 * đã đăng nhập thành công
 * Chức năng chính:
 * doPost(): + Lấy thông tin tài khoản hiện tại từ session
 *           + Nhận dữ liệu từ form (oldPassword, newPassword, confirmPassowrd)
 *           + Kiểm tra hợp lệ (mật khẩu hiện tại, xác nhận khớp mật khẩu mới)
 *           + Nếu không hợp lệ sẽ thông báo lỗi
 *           + Nếu hợp lệ:
 *              + Cập nhật lại mật khẩu thông qua updatePassword() với đối tượng 
 *              dao của AccountDAO
 *              + Chuyến hướng về profile.jsp
 */
@WebServlet(name="ChangePasswordServlet", urlPatterns={"/change-password"})
public class ChangePasswordServlet extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        AccountDTO acc = (AccountDTO) session.getAttribute("account");
        
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        request.setAttribute("activeTab", "password");
        
        if (!acc.getPassword().equals(oldPassword)) {
            request.setAttribute("passwordError", "Mật khẩu hiện tại không đúng!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("passwordError", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }
        
        AccountDAO dao = new AccountDAO();
        
        boolean success = dao.updatePassword(acc.getAccountId(), newPassword);
        
        if (success) {
            acc.setPassword(newPassword);
            session.setAttribute("account", acc);
            request.setAttribute("passwordSuccess", "Đổi mật khẩu thành công!");
        } else {
            request.setAttribute("passwordError", "Có lỗi xảy ra!");
        }

        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
