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
 * @description : Xử lí chức năng quên mật khẩu trong hệ thống
 * Chức năng chính:
 * doGet(): + Hiển thị trang forgotPassword.jsp để người dùng nhập email
 * doPost(): + Nhận email từ người dùng
 *           + Kiểm tra xem email có tồn tại trong hệ thống hay không
 *           + Nếu không tồn tại thì thông báo lỗi
 *           + Nếu tồn tại:
 *              + Tạo OTP 
 *              + Lưu các thông tin cần thiết vào session
 *              + Gửi OTP về email người dùng với thời hạn 2 phút
 *              + Chuyển hướng sang /verify để xác thực OTP
 */
@WebServlet(name="ForgotPasswordServlet", urlPatterns={"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ForgotPasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordServlet at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        AccountDAO dao = new AccountDAO();

        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập email!");
            request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
            return;
        }

        if (dao.getAccountByEmail(email) == null) {
            request.setAttribute("error","Email không tồn tại trong hệ thống!");
            request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
            return;
        }

        String otp = OTPUtil.generateOTP();
        long expireTime = System.currentTimeMillis() + 2 * 60 * 1000;

        session.setAttribute("forgotEmail", email);
        session.setAttribute("otp", otp);
        session.setAttribute("otpExpireTime", expireTime);
        session.setAttribute("otpPurpose", "forgot");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h2>Yêu cầu đặt lại mật khẩu</h2>");
        sb.append("<p>Mã OTP của bạn là:</p>");
        sb.append("<h1 style='color:#f4602a;'>").append(otp).append("</h1>");
        sb.append("<p>Mã có hiệu lực trong <b>2 phút</b>.</p>");
        sb.append("<p>Vui lòng không chia sẻ mã này cho bất kỳ ai.</p>");
        sb.append("<hr>");
        sb.append("<p>Tài Lộc Shop</p>");
        sb.append("</body></html>");
        
        String content = sb.toString();

        EmailUtil.sendEmail(getServletContext(),email,"OTP đặt lại mật khẩu",content);

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
