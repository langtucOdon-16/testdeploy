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
 * @description : Xử lí xoá địa chỉ người dùng trong cơ sở dữ liệu
 * Chức năng chính
 * doGet(): + Lấy thông tin tài khoản hiện tại từ session
 *          + Nhận id địa chỉ cần xoá từ request
 *          + Kiểm tra id có hợp lệ hay không
 *          + Nếu không hợp lệ sẽ thông báo lỗi
 *          + Nếu hợp lệ:
 *             + Gọi deleteAddress() thông qua đối tượng dao của AddressDAO
 *             + Nếu xoá thành công sẽ thông báo thành công
 *             + Nếu thất bại sẽ thông báo lỗi
 *          + Chuyển hướng về trang profile với tab địa chỉ được kích hoạt
 */
@WebServlet(name="DeleteAddressServlet", urlPatterns={"/delete-address"})
public class DeleteAddressServlet extends HttpServlet {
   
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
            out.println("<title>Servlet DeleteAddressServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteAddressServlet at " + request.getContextPath () + "</h1>");
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

        AccountDTO acc = (AccountDTO) session.getAttribute("account");

        String idRaw = request.getParameter("id");

        if (idRaw == null) {
            session.setAttribute("addressError", "Không tìm thấy địa chỉ!");
            response.sendRedirect("profile?tab=address");
            return;
        }

        try {
            int addressId = Integer.parseInt(idRaw);

            AddressDAO dao = new AddressDAO();
            boolean result = dao.deleteAddress(addressId, acc.getAccountId());

            if (result) {
                session.setAttribute("addressSuccess", "Xoá địa chỉ thành công!");
            } else {
                session.setAttribute("addressError", "Không thể xoá địa chỉ!");
            }

        } catch (NumberFormatException e) {
            session.setAttribute("addressError", "ID địa chỉ không hợp lệ!");
        }

        response.sendRedirect("profile?tab=address");
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
