<%-- 
Author      : NamTQ
Created on  : Feb 13, 2026
Description : Reusable footer component for all pages
              (Computer E-commerce Website)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- =======================================================
     FOOTER SECTION
     ======================================================= -->

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
                        <div class="footer-logo-title">Tài Lộc Store</div>
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
                    <li><a href="#">Hướng dẫn thanh toán</a></li>
                    <li><a href="#">Chính sách bảo hành</a></li>
                </ul>
            </div>

            <!-- Categories -->
            <div class="footer-column">
                <h3 class="footer-title">Về chúng tôi</h3>
                <ul class="footer-links">
                    <li><a href="#">Giới thiệu chung</a></li>
                    <li><a href="#">Quy chế hoạt động</a></li>
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

        <!-- ===================================================
             FOOTER BOTTOM BAR
             =================================================== -->
        
        <div class="footer-bottom">
            <p>© 2026 Tài Lộc Store. Tất cả quyền được bảo lưu.</p>
            <div class="footer-bottom-links">
                <a href="#">Điều khoản sử dụng</a>
                <a href="#">Chính sách bảo mật</a>
            </div>
        </div>
    </div>
</footer>