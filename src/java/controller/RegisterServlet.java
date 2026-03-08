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
import util.EmailUtil;
import util.OTPUtil;

/**
 *
 * @author : GiangLT
 * @date : 8/2/2026
 * @description : Xử lí chức năng đăng kí tài khoản mới
 * Chức năng chính
 * doPost(): + Nhận thông tin đăng kí username, password, email từ form đăng kí
 *           + Kiểm tra khớp mật khẩu, trùng username và email
 *              Nếu hợp lệ:
 *              + Tạo mã OTP với thời hạn là 2 phút
 *              + Lưu thông tin đăng kí tạm thời vào session
 *              + Gửi OTP xác nhận về email người dùng đăng kí
 *              + Chuyển hướng đến trang xác thực OTP (/verify)
 */
@WebServlet(name="RegisterServlet", urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath () + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        int role = 0;
        
        AccountDAO dao = new AccountDAO();
        
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        if (dao.isUsernameExists(username)) {
            request.setAttribute("error", "Username đã tồn tại!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        if (dao.isEmailExists(email)) {
            request.setAttribute("error", "Email đã tồn tại!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        String otp = OTPUtil.generateOTP();
        
        HttpSession session = request.getSession();
        long expireTime = System.currentTimeMillis() + 2 * 60 * 1000;
        session.setAttribute("otp", otp);
        session.setAttribute("otpExpireTime", expireTime);
        session.setAttribute("regUsername", username);
        session.setAttribute("regPassword", password);
        session.setAttribute("regEmail", email);
        session.setAttribute("regFullName", fullName);
        session.setAttribute("regRole", role);
        session.setAttribute("otpPurpose", "register");
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<h2>Yêu cầu xác nhận tài khoản đăng kí</h2>");
        sb.append("<p>Xin chào,</p>");
        sb.append("<p>Mã OTP của bạn là:</p>");
        sb.append("<h1 style='color:#f4602a;'>").append(otp).append("</h1>");
        sb.append("<p>Mã có hiệu lực trong <b>2 phút</b>.</p>");
        sb.append("<p>Vui lòng không chia sẻ mã này cho bất kỳ ai.</p>");
        sb.append("<hr>");
        sb.append("<p>Tài Lộc Shop</p>");
        sb.append("</body>");
        sb.append("</html>");
        
        String content = sb.toString();
        
        EmailUtil.sendEmail(getServletContext(), email, "OTP xác nhận tài khoản email", content);
        response.sendRedirect(request.getContextPath() + "/verify");
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