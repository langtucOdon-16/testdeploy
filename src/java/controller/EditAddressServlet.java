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
 * @description : Xử lí chức năng chỉnh sửa địa chỉ của người dùng trong hệ thống
 * Chức năng chính
 * doPost(): + Kiểm tra người dùng đã đăng nhập hay chưa
 *           + Nhận và kiểm tra dữ liệu từ form (tên địa chỉ, số điện thoại)
 *           + Validate số điện thoại theo đúng định dạng
 *           + Gọi AddressDAO để cập nhật dữ liệu vào cơ sở dữ liệu
 *           + Lưu thông báo thành công hoặc lỗi vào session
 *           + Chuyển hướng về trang profile với tab địa chỉ được kích hoạt
 */
@WebServlet(name="EditAddressServlet", urlPatterns={"/edit-address"})
public class EditAddressServlet extends HttpServlet {
   
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
            out.println("<title>Servlet EditAddressServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAddressServlet at " + request.getContextPath () + "</h1>");
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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }

        AccountDTO acc = (AccountDTO) session.getAttribute("account");

        String idRaw = request.getParameter("addressId");
        String addressName = request.getParameter("addressName");
        String phone = request.getParameter("phone");

        int addressId;

        try {
            addressId = Integer.parseInt(idRaw);
        } catch (Exception e) {
            session.setAttribute("addressError", "Dữ liệu không hợp lệ.");
            response.sendRedirect("profile?tab=address");
            return;
        }

        if (addressName == null || addressName.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()) {

            session.setAttribute("addressError","Vui lòng nhập đầy đủ thông tin.");
            response.sendRedirect("profile?tab=address");
            return;
        }

        if (!phone.matches("^0\\d{9}$")) {
            session.setAttribute("addressError","Số điện thoại phải gồm 10 số và bắt đầu bằng 0.");
            response.sendRedirect("profile?tab=address");
            return;
        }

        try {
            AddressDAO dao = new AddressDAO();

            boolean updated = dao.updateAddress(addressId,acc.getAccountId(),addressName.trim(),phone.trim());

            if (updated) {
                session.setAttribute("addressSuccess","Cập nhật địa chỉ thành công.");
            } else {
                session.setAttribute("addressError","Không tìm thấy địa chỉ hợp lệ.");
            }

        } catch (Exception e) {
            session.setAttribute("addressError","Có lỗi xảy ra trong hệ thống.");
        }

        response.sendRedirect("profile?tab=address");
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
