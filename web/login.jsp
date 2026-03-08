<%-- 
    Document   : login
    Created on : Feb 7, 2026, 8:05:02 PM
    Author     : GiangLT
    Description: Trang đăng nhập cho phép người dùng nhập email và mật khẩu.
                 Hệ thống sẽ kiểm tra thông tin trong cơ sở dữ liệu và
                 xác thực tài khoản. Nếu hợp lệ, người dùng sẽ được chuyển
                 đến trang tương ứng theo vai trò (role).
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Tài Lộc Shop - Đăng nhập</title>
        <link rel="stylesheet" href="css/auth.css">
        <script src="js/main.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <div class="icon">
                        <svg xmlns="http://www.w3.org/2000/svg" 
                             viewBox="0 0 24 24" 
                             fill="none" stroke="white" stroke-width="2" 
                             stroke-linecap="round" stroke-linejoin="round">
                        <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                        <circle cx="12" cy="7" r="4"/>
                        </svg>
                    </div>

                    <h2>Đăng Nhập</h2>
                    <p>Nhập email và mật khẩu để tiếp tục</p>
                </div>

                <div class="card-body">

                    <form action="login" method="post">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" required>
                        </div>

                        <div class="form-group">
                            <label>Mật khẩu</label>
                            <input type="password" name="password" required>
                        </div>
                        
                        <c:if test="${not empty error}">
                            <div class="error-message">${error}</div>
                        </c:if>
                        
                        <button class="btn" type="submit">Đăng nhập</button>

                    </form>

                    <div class="footer-text">
                        <a href="forgotPassword.jsp">Quên mật khẩu?</a>
                    </div>

                    <div class="footer-text">
                        Bạn chưa có tài khoản?
                        <a href="register.jsp">Đăng ký</a>
                    </div>

                    <br>
                    <hr>
                    
                    <div class="footer-text">
                        <a href="HomeServlet">← Quay lại trang chủ</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
