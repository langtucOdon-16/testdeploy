<%-- 
    Document   : resetPassword
    Created on : Feb 7, 2026, 9:15:26 PM
    Author     : GiangLT
    Description: Trang đặt lại mật khẩu cho người dùng.
                 Cho phép nhập mật khẩu mới và xác nhận mật khẩu,
                 sau đó cập nhật vào cơ sở dữ liệu nếu hợp lệ.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đặt lại mật khẩu | Tài Lộc Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css">
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
                        <rect x="3" y="11" width="18" height="11" rx="2"/>
                        <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                        </svg>
                    </div>

                    <h2>Đặt lại mật khẩu</h2>
                    <p>Nhập mật khẩu mới của bạn</p>
                </div>

                <div class="card-body">

                    <form action="${pageContext.request.contextPath}/resetPassword" method="post">

                        <div class="form-group">
                            <label for="newPassword">Mật khẩu mới</label>
                            <input type="password" id="newPassword" name="newPassword" required>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword">Xác nhận mật khẩu</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" required>
                        </div>

                        <button class="btn" type="submit">
                            Đổi mật khẩu
                        </button>
                    </form>

                    <c:if test="${not empty error}">
                        <div class="error-message">
                            ${error}
                        </div>
                    </c:if>

                    <c:if test="${not empty message}">
                        <div class="success-message">
                            ${message}
                        </div>
                    </c:if>

                    <div class="footer-text">
                        <a href="${pageContext.request.contextPath}/login">
                            ← Quay lại đăng nhập
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
