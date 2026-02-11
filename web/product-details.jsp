<%-- 
    Document   : product-detail for computer web
    Created on : Feb 8, 2026, 8:57:27 AM
    Author     : NamTQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm - Tài Lộc Store</title>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/product-details.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <!-- Logo -->
                <div class="logo">
                    <a href="index.html" class="logo-link">
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

    <!-- Breadcrumb -->
    <div class="breadcrumb-wrapper">
        <div class="container">
            <nav class="breadcrumb">
                <a href="index.html">Trang chủ</a>
                <i data-lucide="chevron-right"></i>
                <a href="#">Laptop</a>
                <i data-lucide="chevron-right"></i>
                <a href="#">Laptop HP</a>
                <i data-lucide="chevron-right"></i>
                <span>HP 14/15 - 14s/15s</span>
            </nav>
        </div>
    </div>

    <!-- Product Detail -->
    <main class="product-detail-main">
        <div class="container">
            <div class="product-detail-grid">
                <!-- Left Column - Images -->
                <div class="product-gallery">
                    <div class="main-image-wrapper">
                        <img 
                            src="https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=800" 
                            alt="Product Main Image" 
                            class="main-image"
                            id="mainImage"
                        >
                        <button class="gallery-nav gallery-prev" id="galleryPrev">
                            <i data-lucide="chevron-left"></i>
                        </button>
                        <button class="gallery-nav gallery-next" id="galleryNext">
                            <i data-lucide="chevron-right"></i>
                        </button>
                    </div>
                    
                    <!-- Thumbnail Gallery -->
                    <div class="thumbnail-gallery" id="thumbnailGallery">
                        <button class="thumbnail active" data-index="0">
                            <img src="https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?w=100" alt="Thumbnail 1">
                        </button>
                        <button class="thumbnail" data-index="1">
                            <img src="https://images.unsplash.com/photo-1719523676561-10af04209e8c?w=100" alt="Thumbnail 2">
                        </button>
                        <button class="thumbnail" data-index="2">
                            <img src="https://images.unsplash.com/photo-1656788519906-b9becc2a6833?w=100" alt="Thumbnail 3">
                        </button>
                        <button class="thumbnail" data-index="3">
                            <img src="https://images.unsplash.com/photo-1714846201700-35b42d937158?w=100" alt="Thumbnail 4">
                        </button>
                    </div>
                </div>

                <!-- Right Column - Product Info -->
                <div class="product-info-panel">
                    <!-- Product Title -->
                    <h1 class="product-detail-title">
                        Laptop HP 14-em0086AU R5 7520U/16GB/512GB/14'FHD/AMD Radeon Graphics/Win11
                    </h1>

                    <!-- Color Options -->
                    <div class="product-options">
                        <div class="option-label">Màu sắc</div>
                        <div class="color-options">
                            <button class="color-option active">
                                <div class="color-preview">
                                    <img src="https://images.unsplash.com/photo-1719523676561-10af04209e8c?w=50" alt="Bạc">
                                </div>
                                <span>Bạc</span>
                            </button>
                        </div>
                    </div>

                    <!-- Pricing -->
                    <div class="product-pricing-detail">
                        <div class="price-main">
                            <span class="current-price">14.090.000₫</span>
                            <span class="original-price">15.890.000₫</span>
                            <span class="discount-percent">11%</span>
                        </div>
                    </div>

                    <!-- Action Buttons -->
                    <div class="product-actions">
                        <button class="btn-buy-now">
                            MUA NGAY
                            <small>Giao hàng tận nơi hoặc nhận tại cửa hàng</small>
                        </button>
                        <button class="btn-add-cart">
                            THÊM VÀO GIỎ
                            <small>Đưa vào giỏ hàng</small>
                        </button>
                    </div>

                    <!-- Policies -->
                    <div class="product-policies">
                        <div class="policy-item">
                            <i data-lucide="shield-check"></i>
                            <span>Bảo hành chính hãng 12 tháng</span>
                        </div>
                        <div class="policy-item">
                            <i data-lucide="refresh-cw"></i>
                            <span>Đổi trả trong 15 ngày</span>
                        </div>
                        <div class="policy-item">
                            <i data-lucide="truck"></i>
                            <span>Giao hàng miễn phí toàn quốc</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Product Specifications -->
            <div class="product-specs-section">
                <div class="specs-header">
                    <h2>Thông số nổi bật</h2>
                </div>
                <div class="specs-grid">
                    <div class="spec-card">
                        <div class="spec-icon">
                            <i data-lucide="cpu"></i>
                        </div>
                        <div class="spec-info">
                            <div class="spec-label">CPU</div>
                            <div class="spec-value">AMD Ryzen 5 7520U</div>
                        </div>
                    </div>
                    <div class="spec-card">
                        <div class="spec-icon">
                            <i data-lucide="memory-stick"></i>
                        </div>
                        <div class="spec-info">
                            <div class="spec-label">RAM</div>
                            <div class="spec-value">16 GB DDR4</div>
                        </div>
                    </div>
                    <div class="spec-card">
                        <div class="spec-icon">
                            <i data-lucide="hard-drive"></i>
                        </div>
                        <div class="spec-info">
                            <div class="spec-label">Ổ cứng</div>
                            <div class="spec-value">512GB SSD NVMe</div>
                        </div>
                    </div>
                    <div class="spec-card">
                        <div class="spec-icon">
                            <i data-lucide="monitor"></i>
                        </div>
                        <div class="spec-info">
                            <div class="spec-label">Màn hình</div>
                            <div class="spec-value">14" FHD IPS</div>
                        </div>
                    </div>
                    <div class="spec-card">
                        <div class="spec-icon">
                            <svg width="24" height="24" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <rect x="2" y="3" width="20" height="14" rx="2" stroke-width="2"/>
                                <path d="M8 21h8" stroke-width="2" stroke-linecap="round"/>
                            </svg>
                        </div>
                        <div class="spec-info">
                            <div class="spec-label">Card đồ họa</div>
                            <div class="spec-value">AMD Radeon Graphics</div>
                        </div>
                    </div>
                    <div class="spec-card">
                        <div class="spec-icon">
                            <i data-lucide="weight"></i>
                        </div>
                        <div class="spec-info">
                            <div class="spec-label">Trọng lượng</div>
                            <div class="spec-value">1.39 kg</div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Related Products -->
            <div class="related-products-section">
                <h2 class="section-title">Sản phẩm tương tự</h2>
                <div class="product-grid" id="relatedProducts"></div>
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
    <script src="./js/product-details.js"></script>
    <script>
        lucide.createIcons();
    </script>
</body>
</html>
