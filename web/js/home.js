/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* 
    Created on : Feb 8, 2026, 8:59:17 AM
    Author     : NamTQ
    Description: JS for Home for computer web 
*/

// Product Data
const hotDeals = [
    {
        id: 1,
        name: 'Laptop Gaming ASUS ROG Strix G15',
        price: 25990000,
        originalPrice: 32990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.8,
        reviews: 245,
        badge: 'HOT',
        specs: ['Intel Core i7-12700H', 'RTX 3060 6GB', '16GB RAM, 512GB SSD'],
    },
    {
        id: 2,
        name: 'Laptop Dell Inspiron 15 3520',
        price: 13990000,
        originalPrice: 16990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsYXB0b3AlMjB1bHRyYWJvb2t8ZW58MXx8fHwxNzcwMDE2NTE0fDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.5,
        reviews: 189,
        badge: 'GIẢM SỐC',
        specs: ['Intel Core i5-1235U', 'Intel UHD Graphics', '8GB RAM, 512GB SSD'],
    },
    {
        id: 3,
        name: 'PC Gaming Core i5 12400F - RTX 3060',
        price: 18990000,
        originalPrice: 22990000,
        image: 'https://images.unsplash.com/photo-1656788519906-b9becc2a6833?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkZXNrdG9wJTIwUEMlMjBnYW1pbmclMjBzZXR1cHxlbnwxfHx8fDE3NzAwMTY1MTN8MA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.9,
        reviews: 312,
        badge: 'BÁN CHẠY',
        specs: ['Intel Core i5-12400F', 'RTX 3060 12GB', '16GB RAM, 500GB SSD'],
    },
    {
        id: 4,
        name: 'Màn Hình Gaming LG UltraGear 27" 144Hz',
        price: 6490000,
        originalPrice: 7990000,
        image: 'https://images.unsplash.com/photo-1714846201700-35b42d937158?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21wdXRlciUyMG1vbml0b3IlMjBkaXNwbGF5fGVufDF8fHx8MTc3MDAxNjUxNHww&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.7,
        reviews: 156,
        specs: ['27" IPS Full HD', '144Hz, 1ms', 'G-Sync Compatible'],
    },
    {
        id: 5,
        name: 'Bàn Phím Cơ Logitech G Pro X',
        price: 2890000,
        originalPrice: 3490000,
        image: 'https://images.unsplash.com/photo-1639120347540-5001f3603b43?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtZWNoYW5pY2FsJTIwa2V5Ym9hcmQlMjBjbG9zZXVwfGVufDF8fHx8MTc3MDAxNjUxM3ww&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.6,
        reviews: 98,
        specs: ['Switch GX Blue', 'RGB Lightsync', 'Keycap PBT']
    },
    {
        id: 6,
        name: 'MacBook Air M2 2023 13.6"',
        price: 28990000,
        originalPrice: 32990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsYXB0b3AlMjB1bHRyYWJvb2t8ZW58MXx8fHwxNzcwMDE2NTE0fDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.9,
        reviews: 421,
        badge: 'YÊU THÍCH',
        specs: ['Apple M2 8 Core', '8GB RAM, 256GB SSD', 'Retina Display'],
    }
];

const laptops = [
    {
        id: 11,
        name: 'MacBook Air M2 2023 13.6"',
        price: 28990000,
        originalPrice: 32990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsYXB0b3AlMjB1bHRyYWJvb2t8ZW58MXx8fHwxNzcwMDE2NTE0fDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.9,
        reviews: 421,
        badge: 'YÊU THÍCH',
        specs: ['Apple M2 8 Core', '8GB RAM, 256GB SSD', 'Retina Display'],
    },
    {
        id: 12,
        name: 'Laptop HP Pavilion 15',
        price: 15990000,
        originalPrice: 18990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.4,
        reviews: 167,
        specs: ['Intel Core i7-1255U', 'Intel Iris Xe', '16GB RAM, 512GB SSD'],
    },
    {
        id: 13,
        name: 'Laptop Lenovo IdeaPad Gaming 3',
        price: 19990000,
        originalPrice: 24990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.6,
        reviews: 203,
        badge: 'MỚI',
        specs: ['AMD Ryzen 5 6600H', 'RTX 3050 4GB', '8GB RAM, 512GB SSD']
    },
    {
        id: 14,
        name: 'Laptop Acer Aspire 5',
        price: 12990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsYXB0b3AlMjB1bHRyYWJvb2t8ZW58MXx8fHwxNzcwMDE2NTE0fDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.3,
        reviews: 134,
        specs: ['Intel Core i5-1235U', '8GB RAM, 512GB SSD', 'Windows 11']
    },
    {
        id: 15,
        name: 'Laptop MSI GF63 Thin',
        price: 17990000,
        originalPrice: 20990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.5,
        reviews: 178,
        specs: ['Intel Core i5-11400H', 'GTX 1650 4GB', '8GB RAM, 512GB SSD']
    },
    {
        id: 16,
        name: 'Laptop Gaming ASUS ROG Strix G15',
        price: 25990000,
        originalPrice: 32990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.8,
        reviews: 245,
        badge: 'HOT',
        specs: ['Intel Core i7-12700H', 'RTX 3060 6GB', '16GB RAM, 512GB SSD'],
    }
];

const accessories = [
    {
        id: 21,
        name: 'Chuột Gaming Logitech G502 Hero',
        price: 1290000,
        originalPrice: 1590000,
        image: 'https://images.unsplash.com/photo-1613141412501-9012977f1969?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBtb3VzZXxlbnwxfHx8fDE3NzAwMTY1MTN8MA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.8,
        reviews: 542,
        badge: 'BÁN CHẠY',
        specs: ['25,600 DPI', '11 Nút lập trình', 'RGB Lightsync']
    },
    {
        id: 22,
        name: 'Tai Nghe Gaming HyperX Cloud II',
        price: 1990000,
        originalPrice: 2490000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.7,
        reviews: 389,
        specs: ['7.1 Virtual Surround', 'Driver 53mm', 'Mic khử ồn']
    },
    {
        id: 23,
        name: 'SSD Samsung 980 Pro 1TB',
        price: 2790000,
        originalPrice: 3290000,
        image: 'https://images.unsplash.com/photo-1656788519906-b9becc2a6833?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkZXNrdG9wJTIwUEMlMjBnYW1pbmclMjBzZXR1cHxlbnwxfHx8fDE3NzAwMTY1MTN8MA&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.9,
        reviews: 267,
        badge: 'HOT',
        specs: ['NVMe Gen4', 'Đọc: 7000MB/s', 'Ghi: 5000MB/s']
    },
    {
        id: 24,
        name: 'Webcam Logitech C920 HD Pro',
        price: 1490000,
        image: 'https://images.unsplash.com/photo-1714846201700-35b42d937158?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21wdXRlciUyMG1vbml0b3IlMjBkaXNwbGF5fGVufDF8fHx8MTc3MDAxNjUxNHww&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.6,
        reviews: 198,
        specs: ['Full HD 1080p', 'Auto Focus', '2 Mic tích hợp']
    },
    {
        id: 25,
        name: 'Bàn Phím Cơ Keychron K2',
        price: 2190000,
        originalPrice: 2590000,
        image: 'https://images.unsplash.com/photo-1639120347540-5001f3603b43?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtZWNoYW5pY2FsJTIwa2V5Ym9hcmQlMjBjbG9zZXVwfGVufDF8fHx8MTc3MDAxNjUxM3ww&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.8,
        reviews: 324,
        specs: ['Gateron Switch', 'Wireless + Wired', 'Keycap PBT']
    },
    {
        id: 26,
        name: 'Bàn Phím Cơ Logitech G Pro X',
        price: 2890000,
        originalPrice: 3490000,
        image: 'https://images.unsplash.com/photo-1639120347540-5001f3603b43?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtZWNoYW5pY2FsJTIwa2V5Ym9hcmQlMjBjbG9zZXVwfGVufDF8fHx8MTc3MDAxNjUxM3ww&ixlib=rb-4.1.0&q=80&w=400',
        rating: 4.6,
        reviews: 98,
        specs: ['Switch GX Blue', 'RGB Lightsync', 'Keycap PBT']
    }
];

// Format currency
function formatPrice(price) {
    return price.toLocaleString('vi-VN') + '₫';
}

// Create product card HTML
function createProductCard(product) {
    const discountPercent = product.originalPrice 
        ? Math.round(((product.originalPrice - product.price) / product.originalPrice) * 100)
        : 0;

    const starsHTML = Array(5).fill(0).map((_, i) => 
        `<svg class="star ${i < Math.floor(product.rating) ? 'filled' : ''}" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z"/>
        </svg>`
    ).join('');

    const specsHTML = product.specs 
        ? product.specs.slice(0, 2).map(spec => `<div class="product-spec">• ${spec}</div>`).join('')
        : '';

    return `
        <div class="product-card">
            <div class="product-image-wrapper">
                <img src="${product.image}" alt="${product.name}" class="product-image">
                ${product.badge ? `<div class="product-badge">${product.badge}</div>` : ''}
                ${discountPercent > 0 ? `<div class="product-discount-badge">-${discountPercent}%</div>` : ''}
            </div>
            <div class="product-info">
                <div class="product-name">${product.name}</div>
                ${specsHTML ? `<div class="product-specs">${specsHTML}</div>` : ''}
                <div class="product-rating">
                    <div class="stars">${starsHTML}</div>
                    <span class="reviews-count">(${product.reviews})</span>
                </div>
                <div class="product-pricing">
                    <div class="price-wrapper">
                        <span class="product-price">${formatPrice(product.price)}</span>
                        ${product.originalPrice ? `<span class="product-original-price">${formatPrice(product.originalPrice)}</span>` : ''}
                    </div>
                    ${product.discount ? `<div class="product-discount-text">${product.discount}</div>` : ''}
                </div>
                <button class="add-to-cart-btn">
                    <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"/>
                    </svg>
                    Thêm vào giỏ
                </button>
            </div>
        </div>
    `;
}

// Render products
function renderProducts() {
    const hotDealsGrid = document.getElementById('hotDealsGrid');
    const laptopsGrid = document.getElementById('laptopsGrid');
    const accessoriesGrid = document.getElementById('accessoriesGrid');

    if (hotDealsGrid) {
        hotDealsGrid.innerHTML = hotDeals.map(product => createProductCard(product)).join('');
    }

    if (laptopsGrid) {
        laptopsGrid.innerHTML = laptops.map(product => createProductCard(product)).join('');
    }

    if (accessoriesGrid) {
        accessoriesGrid.innerHTML = accessories.map(product => createProductCard(product)).join('');
    }

    // Reinitialize icons for dynamically created content
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
}

// Category Menu Toggle
function initCategoryMenu() {
    const categoryBtn = document.getElementById('categoryBtn');
    const categoryDropdown = document.getElementById('categoryDropdown');
    const categoryOverlay = document.getElementById('categoryOverlay');

    function toggleCategoryMenu() {
        const isActive = categoryDropdown.classList.contains('active');
        
        if (isActive) {
            categoryDropdown.classList.remove('active');
            categoryOverlay.classList.remove('active');
        } else {
            categoryDropdown.classList.add('active');
            categoryOverlay.classList.add('active');
        }
    }

    if (categoryBtn) {
        categoryBtn.addEventListener('click', toggleCategoryMenu);
    }

    if (categoryOverlay) {
        categoryOverlay.addEventListener('click', toggleCategoryMenu);
    }
}

// Brand Menu Toggle
function initBrandMenu() {
    const categoryItems = document.querySelectorAll('.category-item');
    const brandDropdown = document.getElementById('brandDropdown');
    const categoryOverlay = document.getElementById('categoryOverlay');
    let preItem = null;
    let preItemCategoryId = null;
    
    function showBrands (categoryId){
        if (preItemCategoryId !== null){
            document.querySelector(`.brand-group[data-category-id="${preItemCategoryId}"]`).style.display = 'none';
        }
        document.querySelector(`.brand-group[data-category-id="${categoryId}"]`).style.display = 'block';
        preItemCategoryId = categoryId;
    }
    
    function toggleBrandMenu(item) {
        const isActive = brandDropdown.classList.contains('active');
        
        if (isActive && item === preItem){
            brandDropdown.classList.remove('active');
            preItem = null;
            item.blur();
        } else {
            brandDropdown.classList.add('active'); 
            preItem = item;
        }
    }
    
    categoryItems.forEach(item => {
        item.addEventListener('click', () => {
            const categoryId = item.dataset.categoryId;
            showBrands(categoryId);
            toggleBrandMenu(item);
        }); 
    });
    
    if (categoryOverlay) {
        categoryOverlay.addEventListener('click', () => brandDropdown.classList.remove('active'));
        preItem = null;
    }
}

// Carousel functionality
function initCarousel() {
    const slides = document.querySelectorAll('.carousel-slide');
    const prevBtn = document.getElementById('carouselPrev');
    const nextBtn = document.getElementById('carouselNext');
    const dotsContainer = document.getElementById('carouselDots');
    
    let currentSlide = 0;
    const totalSlides = slides.length;
    let autoplayInterval;

    // Create dots
    for (let i = 0; i < totalSlides; i++) {
        const dot = document.createElement('button');
        dot.classList.add('carousel-dot');
        if (i === 0) dot.classList.add('active');
        dot.addEventListener('click', () => goToSlide(i));
        dotsContainer.appendChild(dot);
    }

    const dots = document.querySelectorAll('.carousel-dot');

    function updateSlides() {
        slides.forEach((slide, index) => {
            slide.classList.remove('active');
            if (index === currentSlide) {
                slide.classList.add('active');
            }
        });

        dots.forEach((dot, index) => {
            dot.classList.remove('active');
            if (index === currentSlide) {
                dot.classList.add('active');
            }
        });
    }

    function goToSlide(n) {
        currentSlide = n;
        if (currentSlide >= totalSlides) currentSlide = 0;
        if (currentSlide < 0) currentSlide = totalSlides - 1;
        updateSlides();
        resetAutoplay();
    }

    function nextSlide() {
        goToSlide(currentSlide + 1);
    }

    function prevSlide() {
        goToSlide(currentSlide - 1);
    }

    function startAutoplay() {
        autoplayInterval = setInterval(nextSlide, 5000);
    }

    function stopAutoplay() {
        clearInterval(autoplayInterval);
    }

    function resetAutoplay() {
        stopAutoplay();
        startAutoplay();
    }

    if (prevBtn) prevBtn.addEventListener('click', prevSlide);
    if (nextBtn) nextBtn.addEventListener('click', nextSlide);

    // Pause autoplay on hover
    const carouselContainer = document.querySelector('.carousel-container');
    if (carouselContainer) {
        carouselContainer.addEventListener('mouseenter', stopAutoplay);
        carouselContainer.addEventListener('mouseleave', startAutoplay);
    }

    // Start autoplay
    startAutoplay();
}

// Hàm thiết lập Slider cho từng mục
function setupProductSlider(containerId, prevBtnId, nextBtnId) {
    const container = document.getElementById(containerId);
    const prevBtn = document.getElementById(prevBtnId);
    const nextBtn = document.getElementById(nextBtnId);

    if (!container || !prevBtn || !nextBtn) return;

    // Tính toán khoảng cách cuộn (Chiều rộng thẻ + khoảng cách gap)
    // 260px (min-width) + 16px (1rem gap) = 276px
    const scrollAmount = 280; 

    nextBtn.addEventListener('click', () => {
        container.scrollLeft += scrollAmount;
    });

    prevBtn.addEventListener('click', () => {
        container.scrollLeft -= scrollAmount;
    });
}

// Kích hoạt slider khi trang tải xong
document.addEventListener('DOMContentLoaded', function() {
    renderProducts();
    initCategoryMenu();
    initBrandMenu();
    initCarousel();
    
    // 1. Kích hoạt slider cho mục "Giảm giá sốc"
    setupProductSlider('hotDealsGrid', 'hotDealsPrev', 'hotDealsNext');
    setupProductSlider('laptopsGrid', 'laptopsPrev', 'laptopsNext');
    setupProductSlider('accessoriesGrid', 'accessoriesPrev', 'accessoriesNext');
    
    // Nhớ cập nhật icon Lucide lại vì chúng ta vừa thêm nút mới
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});

