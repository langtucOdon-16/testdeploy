<%-- 
    Document   : register
    Created on : Feb 7, 2026, 8:46:30 PM
    Author     : GiangLT
    Description: Trang đăng ký tài khoản mới cho người dùng.
                 Cho phép nhập username, email, mật khẩu và tạo tài khoản
                 trong hệ thống sau khi kiểm tra dữ liệu hợp lệ.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tài Lộc Shop - Đăng ký</title>
        <link rel="stylesheet" href="css/auth.css">
        <script src="js/main.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="card">

                <div class="card-header">

                    <div class="icon">
                        <!-- User Plus Icon -->
                        <svg xmlns="http://www.w3.org/2000/svg" 
                             viewBox="0 0 24 24" 
                             fill="none" stroke="white" stroke-width="2" 
                             stroke-linecap="round" stroke-linejoin="round">
                        <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"/>
                        <circle cx="9" cy="7" r="4"/>
                        <line x1="19" y1="8" x2="19" y2="14"/>
                        <line x1="22" y1="11" x2="16" y2="11"/>
                        </svg>
                    </div>

                    <h2>Đăng Ký</h2>
                    <p>Tạo tài khoản mới để bắt đầu</p>
                </div>

                <div class="card-body">

                    <form action="register" method="post">

                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" name="username" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Full Name</label>
                            <input type="text" name="fullName" required>
                        </div>

                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="password" required>
                        </div>

                        <div class="form-group">
                            <label>Re-Password</label>
                            <input type="password" name="repassword" required>
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" required>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="error-message">${error}</div>
                        </c:if>

                        <button class="btn" type="submit">Đăng ký</button>

                    </form>

                    <div class="footer-text">
                        Đã có tài khoản?
                        <a href="login.jsp">Đăng nhập</a>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
