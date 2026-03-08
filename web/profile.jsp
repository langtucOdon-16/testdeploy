<%-- 
    Document   : profile
    Created on : Feb 8, 2026, 9:05:55 AM
    Author     : GiangLT
    Description: Trang hiển thị và quản lý thông tin cá nhân của người dùng.
                 Cho phép xem hồ sơ, địa chỉ và cập nhật mật khẩu tài khoản.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Tài khoản của tôi</title>
        <link rel="stylesheet" href="./css/header-footer.css">
        <link rel="stylesheet" href="css/profile.css">
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <!-- Lucide Icons -->
        <script src="https://unpkg.com/lucide@latest"></script>
    </head>
    <body>
        
        <jsp:include page="./common/header.jsp"/>
        
        <div class="profile-wrapper">

            <div class="profile-menu">
                <h3>Tài khoản</h3>

                <a href="profile?tab=profile"
                   class="${empty activeTab || activeTab == 'profile' ? 'active' : ''}">
                    Hồ sơ
                </a>

                <a href="profile?tab=address"
                   class="${activeTab == 'address' ? 'active' : ''}">
                    Địa chỉ
                </a>

                <a href="profile?tab=password"
                   class="${activeTab == 'password' ? 'active' : ''}">
                    Đổi mật khẩu
                </a>

                <a href="HomeServlet">← Trang chủ</a>
            </div>

            <div class="profile-content">

                <!------- Hồ sơ ------->
                <c:if test="${empty activeTab || activeTab == 'profile'}">
                    <div class="tab-content active">
                        <h2>Hồ sơ của tôi</h2>

                        <div class="form-group">
                            <label>Tên tài khoản</label>
                            <input type="text" value="${sessionScope.account.username}" readonly>
                        </div>
                        
                        <div class="form-group">
                            <label>Tên đầy đủ</label>
                            <input type="text" value="${sessionScope.account.fullName}" readonly>
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" value="${sessionScope.account.email}" readonly>
                        </div>
                    </div>
                </c:if>

                <!------- Địa chỉ ------->
                <c:if test="${activeTab == 'address'}">
                    <div class="tab-content active">
                        <h2>Địa chỉ của tôi</h2>

                        <div class="address-list">
                            <c:if test="${empty addressList}">
                                <p>Bạn chưa có địa chỉ nào.</p>

                                <button type="button"
                                        class="btn-save"
                                        onclick="toggleAddForm()">
                                    + Thêm địa chỉ
                                </button>
                            </c:if>

                            <div id="addAddressForm"
                                 class="edit-form"
                                 style="display:none; margin-top:15px;">


                                <form action="${pageContext.request.contextPath}/add-address" method="post">

                                    <div class="form-group">
                                        <label>Tên địa chỉ</label>
                                        <input type="text" name="addressName" required>
                                    </div>

                                    <div class="form-group">
                                        <label>Số điện thoại</label>
                                        <input type="text" name="phone" required>
                                    </div>

                                    <button type="submit" class="btn-save">Lưu</button>

                                    <button type="button" class="btn-save" onclick="hideAddForm()">
                                        Huỷ
                                    </button>

                                </form>
                            </div>

                            <c:forEach var="a" items="${addressList}">
                                <div class="address-item" id="address-${a.addressId}">
                                    <p>
                                        <b>${sessionScope.account.username}</b> |
                                        <span class="phone-text">${a.phone}</span>
                                    </p>

                                    <p class="address-text">${a.addressName}</p>

                                    <form action="${pageContext.request.contextPath}/edit-address"
                                          method="post"
                                          class="edit-form"
                                          style="display:none;">

                                        <input type="hidden" name="addressId"
                                               value="${a.addressId}">

                                        <div class="form-group">
                                            <input type="text" name="addressName"
                                                   value="${a.addressName}" required>
                                        </div>

                                        <div class="form-group">
                                            <input type="text" name="phone"
                                                   value="${a.phone}" required>
                                        </div>

                                        <button type="submit" class="btn-save">Lưu</button>
                                        <button type="button"
                                                class="btn-save"
                                                onclick="cancelEdit(${a.addressId})">
                                            Huỷ
                                        </button>
                                    </form>

                                    <div class="address-actions">
                                        <a href="javascript:void(0)"
                                           onclick="editAddress(${a.addressId})">
                                            Sửa
                                        </a>
                                        |
                                        <a href="${pageContext.request.contextPath}/delete-address?id=${a.addressId}"
                                           onclick="return confirm('Bạn có chắc muốn xoá địa chỉ này?')">
                                            Xoá
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${not empty addressError}">
                                <p class="error">${addressError}</p>
                            </c:if>

                            <c:if test="${not empty addressSuccess}">
                                <p class="success">${addressSuccess}</p>
                            </c:if>


                        </div>
                    </div>
                </c:if>

                <!-- Đổi mật khẩu -->
                <c:if test="${activeTab == 'password'}">
                    <div class="tab-content active">
                        <h2>Đổi mật khẩu</h2>

                        <form action="change-password" method="post" class="password-form">
                            <div class="form-group">
                                <label>Mật khẩu hiện tại</label>
                                <input type="password" name="oldPassword" required>
                            </div>

                            <div class="form-group">
                                <label>Mật khẩu mới</label>
                                <input type="password" name="newPassword" required>
                            </div>

                            <div class="form-group">
                                <label>Xác nhận mật khẩu mới</label>
                                <input type="password" name="confirmPassword" required>
                            </div>

                            <c:if test="${not empty passwordError}">
                                <p class="error">${passwordError}</p>
                            </c:if>

                            <c:if test="${not empty passwordSuccess}">
                                <p class="success">${passwordSuccess}</p>
                            </c:if>

                            <button type="submit" class="btn-save">Đổi mật khẩu</button>
                        </form>
                    </div>
                </c:if>

            </div>
        </div>
                    
        <jsp:include page="./common/footer.jsp"/>
        
        <script src="./js/header.js"></script>
    </body>
</html>
