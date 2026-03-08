<%-- 
    Document   : verifyOtp
    Created on : Feb 8, 2026, 9:51:39 AM
    Author     : GiangLT
    Description: Trang xác thực mã OTP được gửi đến email của người dùng. Hệ 
                 thống kiểm tra mã OTP hợp lệ trước khi cho phép tiếp tục quá 
                 trình đăng ký hoặc đặt lại mật khẩu.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xác minh OTP | Tài Lộc Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css">
        <script src="${pageContext.request.contextPath}/js/verifyOtp.js"></script>
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
                    <h2>Xác minh OTP</h2>
                    <p>Nhập mã OTP đã gửi về email</p>
                </div>

                <div class="card-body">

                    <form action="${pageContext.request.contextPath}/verify" method="post">
                        <input type="hidden" name="action" value="verifyOtp">

                        <div class="form-group">
                            <label>Mã OTP</label>
                            <input type="text" name="otp" required>
                        </div>

                        <button class="btn" type="submit">Xác nhận</button>
                    </form>

                    <div class="footer-text" style="margin-top:10px;">
                        Mã OTP hết hạn sau:
                        <span id="countdown"
                              data-expire="${sessionScope.otpExpireTime}"
                              style="font-weight:bold; color:#f4602a;">
                            --:--
                        </span>

                    </div>

                    <div class="footer-text" style="margin-top:10px;">
                        <form action="${pageContext.request.contextPath}/verify"
                              method="post"
                              id="resendForm">
                            <input type="hidden" name="action" value="sendOtp">
                            <span id="resendText" class="link-btn disabled">
                                Gửi lại OTP
                            </span>
                        </form>
                    </div>

                    <c:if test="${not empty error}">
                        <div class="error-message">
                            ${error}
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </body>
</html>
