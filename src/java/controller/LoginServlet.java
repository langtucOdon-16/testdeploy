/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import model.AccountDTO;

/**
 *
 * @author : GiangLT
 * @date : 8/2/2026
 * @description : Xử lí chức năng đăng nhập của người dùng trong hệ thống
 * Chức năng chính:
 * doGet(): + Hiển thị trang đăng nhập
 *          + Được gọi khi người dùng truy cập đường dẫn /login hoặc nhấn đăng nhập
 * doPost(): + Nhận dữ liệu email và password từ form đăng nhập
 *           + Lấy tài khoản trong cơ sở dữ liệu thông qua AccountDAO
 *           + Kiểm tra xem tài khoản có tồn tại hay không
 *              Nếu hợp lệ:
 *                  + Tạo session lưu người dùng
 *                  + Chuyển hướng đến trang chính thông qua role của tài khoản
 *              Nếu không hợp lệ
 *                  + Gửi thông báo lỗi và forward lại login.jsp
 */
@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
   
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
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession(false);
        if (session != null) {
            AccountDTO acc = (AccountDTO) session.getAttribute("account");
            if (acc != null) {
                response.sendRedirect(request.getContextPath() + "/HomeServlet");
                return;
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        AccountDAO dao = new AccountDAO();
        
        AccountDTO acc = dao.getAccountByEmailAndPassword(email, password);
        
        if (acc == null) {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("account", acc);
        
        if (acc.getRole() == 1 || acc.getRole() == 2) {
            response.sendRedirect(request.getContextPath() + "/adminHome");
        } else {
            response.sendRedirect(request.getContextPath() + "/HomeServlet");
        }
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
