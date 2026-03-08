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
 * @description : Xử lí xác thực OTP trong quá trình đăng kí tài khoản và quên mật khẩu
 * Chức năng chính:
 * doGet(): + Kiểm tra session có tồn tại thuộc tính "otpPurpose" hay không
 *          + Nếu không tồn tại chuyển hướng về trang đăng ký (/register)
 *          + Nếu tồn tại chuyển sang trang nhập mã OTP (verifyOtp.jsp)
 * doPost(): + Lấy action và otpPurpose từ session
 *           + Nếu không có otpPurpose chuyển về trang đăng ký
 *           + Action 'sendOtp':
 *              + Xác định email dựa vào mục đích:
 *                  + register thì lấy qua regEmail
 *                  + forgot thì lấy qua forgotEmail
 *              + Tạo OTP mới và cập nhật thời hạn 2 phút
 *              + Gửi OTP qua email người dùng
 *              + Chuyển hướng lại verifyOtp.jsp
 *           + Action 'verifyOtp':
 *              + Lấy OTP người dùng nhập
 *              + Kiểm tra hợp lệ (tồn tại, hết hạn, khớp)
 *              + Nếu không hợp lệ thì thông báo lỗi
 *              + Nếu hợp lệ:
 *                  + Purpose 'register'
 *                      + Lấy thông tin đăng ký từ session
 *                      + Tạo tài khoản trong cơ sở dữ liệu
 *                      + Xóa toàn bộ session liên quan đến đăng ký và OTP
 *                      + Chuyển về trang login.jsp
 *                  + Purpose 'forgot'
 *                      + Đánh dấu session "forgotVerified" = true
 *                      + Xóa session OTP
 *                      + Chuyển hướng sang /resetPassword
 */
@WebServlet(name = "VerifyRegisterServlet", urlPatterns = {"/verify"})
public class VerifyOtpServlet extends HttpServlet {

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
            out.println("<title>Servlet VerifyRegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyRegisterServlet at " + request.getContextPath() + "</h1>");
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
        String purpose = (String) session.getAttribute("otpPurpose");

        if (purpose == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        request.getRequestDispatcher("/verifyOtp.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        AccountDAO dao = new AccountDAO();

        String action = request.getParameter("action");
        String purpose = (String) session.getAttribute("otpPurpose");

        if (purpose == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        if ("sendOtp".equals(action)) {

            String email = null;

            if ("register".equals(purpose)) {
                email = (String) session.getAttribute("regEmail");
            } else if ("forgot".equals(purpose)) {
                email = (String) session.getAttribute("forgotEmail");
            }

            if (email == null) {
                response.sendRedirect(request.getContextPath() + "/register");
                return;
            }

            String otp = OTPUtil.generateOTP();
            long expireTime = System.currentTimeMillis() + 2 * 60 * 1000;

            session.setAttribute("otp", otp);
            session.setAttribute("otpExpireTime", expireTime);

            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>");
            sb.append("<h2>Xác nhận OTP</h2>");
            sb.append("<p>Mã OTP của bạn là:</p>");
            sb.append("<h1 style='color:#f4602a;'>").append(otp).append("</h1>");
            sb.append("<p>Mã có hiệu lực trong <b>2 phút</b>.</p>");
            sb.append("<p>Vui lòng không chia sẻ mã này cho bất kỳ ai.</p>");
            sb.append("<hr>");
            sb.append("<p>Tài Lộc Shop</p>");
            sb.append("</body></html>");

            String content = sb.toString();

            EmailUtil.sendEmail(getServletContext(),email,"OTP xác nhận tài khoản email",content);

            request.getRequestDispatcher("/verifyOtp.jsp").forward(request, response);
            return;
        }

        if ("verifyOtp".equals(action)) {

            String inputOtp = request.getParameter("otp");
            String sessionOtp = (String) session.getAttribute("otp");
            Long expireTime = (Long) session.getAttribute("otpExpireTime");

            if (expireTime == null || System.currentTimeMillis() > expireTime) {
                request.setAttribute("error", "Mã OTP đã hết hạn! Vui lòng gửi lại.");
                request.getRequestDispatcher("/verifyOtp.jsp").forward(request, response);
                return;
            }

            if (sessionOtp == null || !sessionOtp.equals(inputOtp)) {
                request.setAttribute("error", "Mã OTP không đúng!");
                request.getRequestDispatcher("/verifyOtp.jsp").forward(request, response);
                return;
            }

            if ("register".equals(purpose)) {
                String username = (String) session.getAttribute("regUsername");
                String password = (String) session.getAttribute("regPassword");
                String email = (String) session.getAttribute("regEmail");
                String fullName = (String) session.getAttribute("regFullName");
                Integer role = (Integer) session.getAttribute("regRole");
                dao.insertAccount(username, password, email, fullName, role);

                session.removeAttribute("regUsername");
                session.removeAttribute("regPassword");
                session.removeAttribute("regEmail");

                session.removeAttribute("otp");
                session.removeAttribute("otpExpireTime");
                session.removeAttribute("otpPurpose");

                request.setAttribute("message","Đăng ký thành công! Vui lòng đăng nhập.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            } else if ("forgot".equals(purpose)) {

                session.setAttribute("forgotVerified", true);

                session.removeAttribute("otp");
                session.removeAttribute("otpExpireTime");
                session.removeAttribute("otpPurpose");

                response.sendRedirect(request.getContextPath() + "/resetPassword");
                return;
            }

            session.removeAttribute("otp");
            session.removeAttribute("otpExpireTime");
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
