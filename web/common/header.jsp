<%-- 
Author      : NamTQ
Created on  : Feb 12, 2026
Description : Reusable header component for all pages
              (Computer E-commerce Website)
              Includes:
              - Logo
              - Category & Brand Dropdown
              - Search Bar
              - Cart
              - Authentication Menu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- =======================================================
     HEADER SECTION
     ======================================================= -->

<header class="header">
    <div class="container">
        <div class="header-content">

            <!-- ===================================================
                 1. LOGO
                 =================================================== -->

            <div class="logo">
                <a href="HomeServlet" class="logo-link">
                    <div class="logo-icon">
                        <i data-lucide="laptop"></i>
                    </div>
                    <div class="logo-text">
                        <div class="logo-title">Tài Lộc Store</div>
                        <div class="logo-subtitle">Cửa hàng máy tính</div>
                    </div>
                </a>
            </div>

            <!-- ===================================================
                 2. CATEGORY & BRAND MENU
                 =================================================== -->

            <div class="category-menu-wrapper">
                <button class="category-btn" id="categoryBtn">
                    <i data-lucide="menu" id="menuIcon"></i>
                    <span class="category-text">Danh mục</span>
                </button>

                <!-- Category Dropdown -->
                <div class="category-dropdown" id="categoryDropdown">
                    <div class="category-dropdown-inner">
                        <h3 class="category-title">Danh mục sản phẩm</h3>
                        <div class="category-grid">
                            <c:forEach items="${categories}" var="category">
                                <a class="category-item" data-category-id="${category.categoryId}"
                                   href="SearchServlet?categoryIdName=${category.categoryId}">
                                    <span>${category.categoryName}</span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <!-- ===============================
                     BRAND DROPDOWN (Dynamic by category)
                     Data from: ${brands[category.categoryId]}
                     =============================== -->

                <div class="brand-dropdown" id="brandDropdown">
                    <div class="brand-dropdown-inner">
                        <c:forEach items="${categories}" var="category">
                            <div class="brand-group" data-category-id="${category.categoryId}">
                                <h3 class="brand-title">Hãng sản phẩm</h3>
                                <div class="brand-grid">
                                    <c:forEach items="${brands[category.categoryId]}" var="brand">
                                        <a class="brand-item"
                                           href="SearchServlet?categoryIdName=${category.categoryId}&brandIdName=${brand.brandId}">
                                            <span>${brand.brandName}</span>
                                        </a>
                                    </c:forEach>
                                </div>  
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="category-overlay" id="categoryOverlay"></div>
            </div>

            <!-- ===================================================
                 3. SEARCH BAR
                 =================================================== -->

            <div class="search-bar">
                <form action="SearchServlet" method="get">
                    <input 
                        type="text" 
                        placeholder="Bạn tìm gì hôm nay? (Laptop, PC, màn hình,...)"
                        class="search-input"
                        id="searchInput"
                        name="keyword"
                        value="${param.keyword}"
                        >
                    <button class="search-btn" type="submit">
                        <i data-lucide="search"></i>
                    </button>
                </form>  
            </div>

            <!-- ===================================================
                 4. CART & USER AUTHENTICATION
                 =================================================== -->

            <div class="header-actions">
                <a href="cart" class="action-btn cart-btn">
                    <i data-lucide="shopping-cart"></i>
                    <span>Giỏ hàng</span>
                    <span class="cart-badge">${cartSize}</span>
                </a>

                <!-- Nếu CHƯA đăng nhập -->
                <c:if test="${empty sessionScope.account}">
                    <a href="login.jsp" class="action-btn">
                        <i data-lucide="user"></i>
                        <span>Đăng nhập</span>
                    </a>  
                </c:if>

                <!-- Nếu ĐÃ đăng nhập -->
                <c:if test="${not empty sessionScope.account}">
                    <div class="function-menu-wrapper" >
                        <button class="action-btn function-btn" id="functionBtn">
                            <i data-lucide="user"></i>
                            <span>${sessionScope.account.fullName}</span>
                        </button>

                        <div class="function-dropdown" id="functionDropdown">
                            <div class="function-dropdown-inner">
                                <h3 class="function-title">Xin chào ${sessionScope.account.fullName}!</h3>
                                <div class="function-grid">
                                    <a href="profile" class="function-item">Thông tin cá nhân</a>
                                    <a href="order" class="function-item">Lịch sử đơn hàng</a>
                                    <c:if test="${sessionScope.account.role == 1 || sessionScope.account.role == 2}">
                                        <a href="adminHome" class="function-item">Chuyển sang Dashboard Admin</a>
                                    </c:if>
                                    <a href="logout" class="function-item">Đăng xuất</a>
                                </div>
                            </div>
                        </div>

                        <div class="function-overlay" id="functionOverlay"></div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</header>
