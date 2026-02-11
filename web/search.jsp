<%-- 
    Document   : search for computer web
    Created on : Feb 8, 2026, 8:58:07 AM
    Author     : NamTQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tìm kiếm - Tài Lộc Store</title>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/search.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <!-- Logo -->
                <div class="logo">
                    <a href="/" class="logo-link">
                        <div class="logo-icon">
                            <i data-lucide="laptop"></i>
                        </div>
                        <div class="logo-text">
                            <div class="logo-title">Tài Lộc Store</div>
                            <div class="logo-subtitle">Cửa hàng máy tính</div>
                        </div>
                    </a>
                </div>

                <!-- Category Menu Button -->
                <div class="category-menu-wrapper">
                    <button class="category-btn" id="categoryBtn">
                        <i data-lucide="menu" id="menuIcon"></i>
                        <i data-lucide="x" id="closeIcon" style="display: none;"></i>
                        <span class="category-text">Danh mục</span>
                    </button>

                    <!-- Category Dropdown -->
                    <div class="category-dropdown" id="categoryDropdown">
                        <div class="category-dropdown-inner">
                            <h3 class="category-title">Danh mục sản phẩm</h3>
                            <div class="category-grid">
                                <button class="category-item">
                                    <i data-lucide="laptop"></i>
                                    <span>Laptop</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="cpu"></i>
                                    <span>PC/Máy Tính Bàn</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="monitor"></i>
                                    <span>Màn Hình</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="keyboard"></i>
                                    <span>Bàn Phím</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="mouse"></i>
                                    <span>Chuột</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="headphones"></i>
                                    <span>Tai Nghe</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="speaker"></i>
                                    <span>Loa</span>
                                </button>
                                <button class="category-item">
                                    <i data-lucide="hard-drive"></i>
                                    <span>Ổ Cứng & SSD</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="category-overlay" id="categoryOverlay"></div>
                </div>

                <!-- Search Bar -->
                <div class="search-bar">
                    <input 
                        type="text" 
                        placeholder="Bạn tìm gì hôm nay? (Laptop, PC, màn hình...)"
                        class="search-input"
                        id="searchInput"
                    >
                    <button class="search-btn">
                        <i data-lucide="search"></i>
                    </button>
                </div>

                <!-- User & Cart -->
                <div class="header-actions">
                     <button class="action-btn cart-btn">
                        <i data-lucide="shopping-cart"></i>
                        <span>Giỏ hàng</span>
                        <span class="cart-badge">3</span>
                    </button>
                    <button class="action-btn">
                        <i data-lucide="user"></i>
                        <span>Tài khoản</span>
                    </button>
                </div>
            </div>
        </div>
    </header>

    <!-- Search Results -->
    <main class="search-results-main">
        <div class="container">
            <!-- Search Results Layout -->
            <div class="search-layout">
                <!-- Sidebar Filters -->
                <aside class="search-sidebar">
                    <div class="filter-section">
                        <button class="filter-toggle" id="filterToggle">
                            <i data-lucide="sliders-horizontal"></i>
                            <span>Bộ lọc tìm kiếm</span>
                            <i data-lucide="chevron-down" class="toggle-icon"></i>
                        </button>
                    </div>

                    <!-- Brand Filter -->
                    <div class="filter-group">
                        <div class="filter-header">
                            <h3>Hãng sản xuất</h3>
                            <button class="filter-collapse" data-target="brandFilter">
                                <i data-lucide="chevron-up"></i>
                            </button>
                        </div>
                        <div class="filter-content" id="brandFilter">
                            <div class="brand-grid">
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23333' font-size='10' font-weight='bold'%3ECORSAIR%3C/text%3E%3C/svg%3E" alt="Corsair">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%2376b900' font-size='10' font-weight='bold'%3EKangaroo%3C/text%3E%3C/svg%3E" alt="Kangaroo">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23ff6900' font-size='10' font-weight='bold'%3EXIAOMI%3C/text%3E%3C/svg%3E" alt="Xiaomi">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%230066cc' font-size='10' font-weight='bold'%3ECoway%3C/text%3E%3C/svg%3E" alt="Coway">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23000' font-size='10' font-weight='bold'%3EDeepCool%3C/text%3E%3C/svg%3E" alt="DeepCool">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23ff9900' font-size='10' font-weight='bold'%3EGoldSun%3C/text%3E%3C/svg%3E" alt="GoldSun">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23000' font-size='10' font-weight='bold'%3EJoyoung%3C/text%3E%3C/svg%3E" alt="Joyoung">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23e60000' font-size='10' font-weight='bold'%3ESUNHOUSE%3C/text%3E%3C/svg%3E" alt="Sunhouse">
                                </button>
                                <button class="brand-item">
                                    <img src="data:image/svg+xml,%3Csvg width='60' height='40' xmlns='http://www.w3.org/2000/svg'%3E%3Ctext x='50%25' y='50%25' dominant-baseline='middle' text-anchor='middle' fill='%23107c10' font-size='10' font-weight='bold'%3EXBOX%3C/text%3E%3C/svg%3E" alt="Xbox">
                                </button>
                            </div>
                            <button class="btn-show-more">Thu gọn</button>
                        </div>
                    </div>

                    <!-- Price Filter -->
                    <div class="filter-group">
                        <div class="filter-header">
                            <h3>Mức giá</h3>
                            <button class="filter-collapse" data-target="priceFilter">
                                <i data-lucide="chevron-up"></i>
                            </button>
                        </div>
                        <div class="filter-content" id="priceFilter">
                            <div class="price-checkboxes">
                                <label class="checkbox-item">
                                    <input type="checkbox" checked>
                                    <span class="checkbox-label">Tất cả</span>
                                </label>
                                <label class="checkbox-item">
                                    <input type="checkbox">
                                    <span class="checkbox-label">Dưới 2 triệu</span>
                                </label>
                                <label class="checkbox-item">
                                    <input type="checkbox">
                                    <span class="checkbox-label">Từ 2 - 4 triệu</span>
                                </label>
                                <label class="checkbox-item">
                                    <input type="checkbox">
                                    <span class="checkbox-label">Từ 4 - 7 triệu</span>
                                </label>
                                <label class="checkbox-item">
                                    <input type="checkbox">
                                    <span class="checkbox-label">Từ 7 - 13 triệu</span>
                                </label>
                                <label class="checkbox-item">
                                    <input type="checkbox">
                                    <span class="checkbox-label">Trên 13 triệu</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </aside>

                <!-- Results Content -->
                <div class="search-content">
                    <!-- Quick Category Filters -->
                    <div class="quick-filters">
                        <button class="quick-filter-btn active">
                            <i data-lucide="grid"></i>
                            <span>Tất cả</span>
                        </button>
                        <button class="quick-filter-btn">
                            <i data-lucide="plug"></i>
                            <span>Laptop</span>
                            <span class="filter-count">(10)</span>
                        </button>
                        <button class="quick-filter-btn">
                            <i data-lucide="smartphone"></i>
                            <span>Màn hình</span>
                            <span class="filter-count">(7)</span>
                        </button>
                        <button class="quick-filter-btn">
                            <i data-lucide="watch"></i>
                            <span>Chuột</span>
                            <span class="filter-count">(3)</span>
                        </button>
                        <button class="quick-filter-btn">
                            <i data-lucide="wind"></i>
                            <span>Loa</span>
                            <span class="filter-count">(3)</span>
                        </button>
                        <button class="quick-filter-btn">
                            <i data-lucide="cpu"></i>
                            <span>PC</span>
                            <span class="filter-count">(1)</span>
                        </button>
                        <button class="quick-filter-btn">
                            <i data-lucide="chef-hat"></i>
                            <span>Bàn phím</span>
                            <span class="filter-count">(1)</span>
                        </button>
                    </div>

                    <!-- Results Header -->
                    <div class="results-header">
                        <div class="results-info">
                            Tìm thấy <strong>25 kết quả</strong> với từ khóa <strong>"ap"</strong>
                        </div>
                        <div class="sort-options">
                            <button class="sort-btn active">Nổi bật</button>
                            <button class="sort-btn">Giá tăng dần</button>
                            <button class="sort-btn">Giá giảm dần</button>
                        </div>
                    </div>

                    <!-- Product Grid -->
                    <div class="search-results-grid" id="searchResultsGrid">
                        <!-- Products will be loaded here by JavaScript -->
                    </div>

                    <!-- Load More -->
                    <div class="load-more-wrapper">
                        <button class="btn-load-more">
                            Xem thêm sản phẩm
                            <i data-lucide="chevron-down"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <div class="footer-grid">
                <!-- Company Info -->
                <div class="footer-column">
                    <div class="footer-logo">
                        <div class="footer-logo-icon">
                            <i data-lucide="laptop"></i>
                        </div>
                        <div>
                            <div class="footer-logo-title">ComputerStore</div>
                            <div class="footer-logo-subtitle">Cửa hàng máy tính</div>
                        </div>
                    </div>
                    <p class="footer-description">
                        Hệ thống bán lẻ máy tính, laptop và phụ kiện công nghệ hàng đầu Việt Nam. 
                        Cam kết sản phẩm chính hãng, giá tốt nhất.
                    </p>
                    <div class="social-links">
                        <a href="#" class="social-link"><i data-lucide="facebook"></i></a>
                        <a href="#" class="social-link"><i data-lucide="youtube"></i></a>
                        <a href="#" class="social-link"><i data-lucide="instagram"></i></a>
                    </div>
                </div>

                <!-- Quick Links -->
                <div class="footer-column">
                    <h3 class="footer-title">Hỗ trợ khách hàng</h3>
                    <ul class="footer-links">
                        <li><a href="#">Hướng dẫn mua hàng</a></li>
                        <li><a href="#">Chính sách bảo hành</a></li>
                        <li><a href="#">Chính sách đổi trả</a></li>
                        <li><a href="#">Hướng dẫn thanh toán</a></li>
                        <li><a href="#">Chính sách vận chuyển</a></li>
                        <li><a href="#">Câu hỏi thường gặp</a></li>
                    </ul>
                </div>

                <!-- Categories -->
                <div class="footer-column">
                    <h3 class="footer-title">Về chúng tôi</h3>
                    <ul class="footer-links">
                        <li><a href="#">Giới thiệu chung</a></li>
                        <li><a href="#">Quy chế hoạt động</a></li>
                        <li><a href="#">Chính sách bảo hành</a></li>
                        <li><a href="#">Tra cứu hóa đơn điện tử</a></li>
                        <li><a href="#">Tin tức khuyến mãi</a></li>
                    </ul>
                </div>

                <!-- Contact Info -->
                <div class="footer-column">
                    <h3 class="footer-title">Thông tin liên hệ</h3>
                    <ul class="footer-contact">
                        <li>
                            <i data-lucide="map-pin"></i>
                            <span>Xã Hòa Lạc, Hà Nội</span>
                        </li>
                        <li>
                            <i data-lucide="phone"></i>
                            <span>Hotline: 1900 xxxx</span>
                        </li>
                        <li>
                            <i data-lucide="mail"></i>
                            <span>support@techstore.vn</span>
                        </li>
                    </ul>
                    <div class="footer-hours">
                        <div class="footer-hours-label">Giờ làm việc</div>
                        <div class="footer-hours-time">8:00 - 22:00 (Cả tuần)</div>
                    </div>
                </div>
            </div>

            <!-- Bottom Bar -->
            <div class="footer-bottom">
                <p>© 2026 Tài Lộc Store. Tất cả quyền được bảo lưu.</p>
                <div class="footer-bottom-links">
                    <a href="#">Điều khoản sử dụng</a>
                    <a href="#">Chính sách bảo mật</a>
                </div>
            </div>
        </div>
    </footer>

    <script src="./js/home.js"></script>
    <script src="./js/search.js"></script>
    <script>
        lucide.createIcons();
    </script>
</body>
</html>
