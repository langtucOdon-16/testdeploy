/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountDTO;
import model.CommentDTO;

/**
 *
 * @author : GiangLT
 * @date : 12/2/2026
 * @description : Xử lí chức năng thêm bình luận hoặc phản hồi cho sản phẩm. 
 * Hỗ trợ comment cha và reply comment con.
 * Chức năng chính:
 * doPost: - Kiểm tra người dùng đã đăng nhập.
 *         - Lấy nội dung comment, productId và parentId.
 *         - Nếu là reply của reply thì tự động quy về comment cha.
 *         - Lưu comment vào database.
 *         - Redirect về trang chi tiết sản phẩm.
 */
@WebServlet(name = "AddCommentServlet", urlPatterns = {"/AddCommentServlet"})
public class AddCommentServlet extends HttpServlet {

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
            out.println("<title>Servlet AddCommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCommentServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

//        if (session == null || session.getAttribute("account") == null) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }

        AccountDTO user = (AccountDTO) session.getAttribute("account");

        String content = request.getParameter("content");
        String parentIdStr = request.getParameter("parentId");
        String productIdStr = request.getParameter("productId");

        if (content == null || content.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/HomeServlet");
            return;
        }

        int productId = Integer.parseInt(productIdStr);

        Integer parentId = null;
        if (parentIdStr != null && !parentIdStr.isEmpty()) {
            parentId = Integer.parseInt(parentIdStr);
        }

        CommentDAO dao = new CommentDAO();

        if (parentId != null) {

            CommentDTO parentComment = dao.getCommentById(parentId);

            if (parentComment != null && parentComment.getParentId() != null) {
                parentId = parentComment.getParentId();
            }
        }

        dao.insertComment(
                user.getAccountId(),
                content.trim(),
                parentId,
                productId
        );

        response.sendRedirect(
                request.getContextPath()
                + "/ProductDetailsServlet?productId=" + productId
        );
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
