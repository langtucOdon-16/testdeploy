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
import java.util.List;
import model.AccountDTO;
import model.AddressDTO;

/**
 *
 * @author : GiangLT
 * @date : 9/2/2026
 * @description : Xử lí chức năng hiển thị trang hồ sơ cá nhân của người dùng
 * trong hệ thống
 * Chức năng chính:
 * doGet(): + Kiểm tra người dùng đã đăng nhập hay chưa thông qua session
 *          + Nếu chưa thì chuyển hướng người dùng login
 *          + Nếu đã đăng nhập:
 *              + Lấy thông tin của tài khoản từ session
 *              + Nhận tham số tab từ URL (?tab=profile | address | password)
 *              + Nếu không có tab thì mặc định hiển thị tab profile
 *              + Nếu tab = 'address'
 *                  + Tạo AddressDAO và lấy danh sách địa chỉ theo accountId
 *                  + Gửi danh sách địa chỉ sang profile.jsp
 *                  + Gửi activeTab sang JSP để xác định tab đang được chọn
 *                  + Forward đến trang profile.jsp để hiển thị dữ liệu
 */
@WebServlet(name="ProfileServlet", urlPatterns={"/profile"})
public class ProfileServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ProfileServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath () + "</h1>");
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
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }

        if (session != null) {
            String addressError = (String) session.getAttribute("addressError");
            String addressSuccess = (String) session.getAttribute("addressSuccess");

            if (addressError != null) {
                request.setAttribute("addressError", addressError);
                session.removeAttribute("addressError");
            }

            if (addressSuccess != null) {
                request.setAttribute("addressSuccess", addressSuccess);
                session.removeAttribute("addressSuccess");
            }
        }

        AccountDTO acc = (AccountDTO) session.getAttribute("account");

        String tab = request.getParameter("tab");
        if (tab == null || tab.isEmpty()) {
            tab = "profile"; 
        }

        if ("address".equals(tab)) {
            AddressDAO addressDAO = new AddressDAO();
            List<AddressDTO> addressList = addressDAO.getByAccountId(acc.getAccountId());
            request.setAttribute("addressList", addressList);
        }

        request.setAttribute("activeTab", tab);

        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
