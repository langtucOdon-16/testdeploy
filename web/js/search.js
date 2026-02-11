/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Search Page JavaScript

// Sample search results data
const searchResults = [
    {
        id: 201,
        name: 'PC Gaming Core i5 12400F - RTX 3060',
        price: 18990000,
        originalPrice: 22990000,
        image: 'https://images.unsplash.com/photo-1656788519906-b9becc2a6833?w=400',
        rating: 4.9,
        reviews: 312,
        badge: 'BÁN CHẠY',
        specs: ['Intel Core i5-12400F', 'RTX 3060 12GB', '16GB RAM, 500GB SSD'],
        category: 'pc'
    },
    {
        id: 202,
        name: 'Gamepad Xbox Wireless Controller',
        price: 1490000,
        originalPrice: 1790000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?w=400',
        rating: 4.7,
        reviews: 234,
        specs: ['Bluetooth & USB-C', 'Pin 40 giờ', 'Tương thích PC/Xbox'],
        category: 'accessory'
    },
    {
        id: 203,
        name: 'Vòng đeo tay thông minh Xiaomi Band 8',
        price: 890000,
        originalPrice: 1090000,
        image: 'https://images.unsplash.com/photo-1714846201700-35b42d937158?w=400',
        rating: 4.5,
        reviews: 567,
        badge: 'HOT',
        specs: ['Màn hình AMOLED 1.62"', 'Pin 16 ngày', 'Chống nước 5ATM'],
        category: 'wearable'
    },
    {
        id: 204,
        name: 'Đồng hồ thông minh Apple Watch Series 9',
        price: 10990000,
        originalPrice: 12990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?w=400',
        rating: 4.9,
        reviews: 892,
        badge: 'MỚI',
        specs: ['Apple S9 SiP', 'GPS + Cellular', 'Always-On Display'],
        category: 'watch'
    },
    {
        id: 205,
        name: 'Máy lọc không khí Xiaomi 4 Pro',
        price: 5490000,
        originalPrice: 6990000,
        image: 'https://images.unsplash.com/photo-1656788519906-b9becc2a6833?w=400',
        rating: 4.6,
        reviews: 178,
        specs: ['CADR 500m³/h', 'Diện tích 60m²', 'Màn hình cảm ứng OLED'],
        category: 'appliance'
    },
    {
        id: 206,
        name: 'Tai nghe Apple AirPods Pro 2',
        price: 6490000,
        originalPrice: 7490000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?w=400',
        rating: 4.8,
        reviews: 1234,
        badge: 'YÊU THÍCH',
        specs: ['ANC chủ động', 'Chip H2', 'Pin 30 giờ với case'],
        category: 'accessory'
    },
    {
        id: 207,
        name: 'Bàn phím cơ gaming Razer Huntsman',
        price: 3290000,
        originalPrice: 3990000,
        image: 'https://images.unsplash.com/photo-1639120347540-5001f3603b43?w=400',
        rating: 4.7,
        reviews: 445,
        specs: ['Optical Switch', 'RGB Chroma', 'Wrist rest'],
        category: 'accessory'
    },
    {
        id: 208,
        name: 'Chuột gaming Logitech G Pro Wireless',
        price: 2990000,
        originalPrice: 3490000,
        image: 'https://images.unsplash.com/photo-1613141412501-9012977f1969?w=400',
        rating: 4.9,
        reviews: 678,
        badge: 'BÁN CHẠY',
        specs: ['HERO 25K Sensor', 'Wireless', 'Pin 60 giờ'],
        category: 'accessory'
    },
    {
        id: 209,
        name: 'Laptop MacBook Air M2 2023',
        price: 28990000,
        originalPrice: 32990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?w=400',
        rating: 4.9,
        reviews: 421,
        badge: 'YÊU THÍCH',
        specs: ['Apple M2 8 Core', '8GB RAM, 256GB SSD', 'Retina Display'],
        category: 'laptop'
    },
    {
        id: 210,
        name: 'Nồi cơm điện cao tần Cuckoo 1.8L',
        price: 4590000,
        originalPrice: 5290000,
        image: 'https://images.unsplash.com/photo-1656788519906-b9becc2a6833?w=400',
        rating: 4.6,
        reviews: 234,
        specs: ['IH cao tần', 'Dung tích 1.8L', '12 chế độ nấu'],
        category: 'appliance'
    }
];

let currentResults = [...searchResults];
let activeCategory = 'all';
let activeSortOption = 'featured';

// Initialize Search Page
function initSearchPage() {
    renderSearchResults();
    initClearSearch();
    initQuickFilters();
    initSortOptions();
    initFilterCollapse();
    initBrandFilters();
    initPriceFilters();
}

// Clear Search
function initClearSearch() {
    const clearBtn = document.getElementById('clearSearch');
    const searchInput = document.getElementById('searchInput');
    
    if (clearBtn && searchInput) {
        clearBtn.addEventListener('click', () => {
            searchInput.value = '';
            searchInput.focus();
        });

        // Show/hide clear button based on input
        searchInput.addEventListener('input', () => {
            if (searchInput.value) {
                clearBtn.style.display = 'flex';
            } else {
                clearBtn.style.display = 'none';
            }
        });

        // Initial state
        if (searchInput.value) {
            clearBtn.style.display = 'flex';
        }
    }
}

// Quick Category Filters
function initQuickFilters() {
    const filterBtns = document.querySelectorAll('.quick-filter-btn');
    
    filterBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            filterBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            
            // Filter results based on category
            const category = btn.textContent.toLowerCase().trim();
            filterByCategory(category);
        });
    });
}

function filterByCategory(category) {
    if (category.includes('tất cả')) {
        currentResults = [...searchResults];
    } else if (category.includes('phụ kiện')) {
        currentResults = searchResults.filter(p => p.category === 'accessory');
    } else if (category.includes('laptop')) {
        currentResults = searchResults.filter(p => p.category === 'laptop');
    } else if (category.includes('đồng hồ')) {
        currentResults = searchResults.filter(p => p.category === 'watch' || p.category === 'wearable');
    } else if (category.includes('điện')) {
        currentResults = searchResults.filter(p => p.category === 'appliance');
    } else {
        currentResults = [...searchResults];
    }
    
    renderSearchResults();
}

// Sort Options
function initSortOptions() {
    const sortBtns = document.querySelectorAll('.sort-btn');
    
    sortBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            sortBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            
            const sortType = btn.textContent.toLowerCase().trim();
            sortResults(sortType);
        });
    });
}

function sortResults(sortType) {
    if (sortType.includes('giá tăng')) {
        currentResults.sort((a, b) => a.price - b.price);
    } else if (sortType.includes('giá giảm')) {
        currentResults.sort((a, b) => b.price - a.price);
    } else if (sortType.includes('nổi bật')) {
        currentResults.sort((a, b) => b.rating - a.rating);
    }
    
    renderSearchResults();
}

// Filter Collapse
function initFilterCollapse() {
    const collapseBtns = document.querySelectorAll('.filter-collapse');
    
    collapseBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            const targetId = btn.getAttribute('data-target');
            const target = document.getElementById(targetId);
            
            if (target) {
                target.classList.toggle('collapsed');
                btn.classList.toggle('collapsed');
            }
        });
    });
}

// Brand Filters
function initBrandFilters() {
    const brandItems = document.querySelectorAll('.brand-item');
    
    brandItems.forEach(item => {
        item.addEventListener('click', () => {
            item.classList.toggle('active');
            // Here you can add logic to filter by brand
        });
    });

    // Show more/less button
    const showMoreBtn = document.querySelector('.btn-show-more');
    if (showMoreBtn) {
        let isExpanded = true;
        showMoreBtn.addEventListener('click', () => {
            isExpanded = !isExpanded;
            showMoreBtn.textContent = isExpanded ? 'Thu gọn' : 'Xem thêm';
            // Here you can add logic to show/hide brands
        });
    }
}

// Price Filters
function initPriceFilters() {
    const priceCheckboxes = document.querySelectorAll('.checkbox-item input[type="checkbox"]');
    
    priceCheckboxes.forEach((checkbox, index) => {
        checkbox.addEventListener('change', () => {
            if (index === 0) {
                // "Tất cả" checkbox
                priceCheckboxes.forEach((cb, i) => {
                    if (i !== 0) cb.checked = false;
                });
            } else {
                // Other checkboxes
                priceCheckboxes[0].checked = false;
            }
            
            // Here you can add logic to filter by price
            filterByPrice();
        });
    });
}

function filterByPrice() {
    const checkboxes = document.querySelectorAll('.checkbox-item input[type="checkbox"]');
    const checkedBoxes = Array.from(checkboxes).filter(cb => cb.checked);
    
    if (checkedBoxes.length === 0 || checkedBoxes[0] === checkboxes[0]) {
        // Show all if none selected or "Tất cả" is selected
        currentResults = [...searchResults];
    } else {
        // Filter based on selected price ranges
        // This is a simplified example
        currentResults = [...searchResults];
    }
    
    renderSearchResults();
}

// Render Search Results
function renderSearchResults() {
    const container = document.getElementById('searchResultsGrid');
    if (!container) return;

    // Update results count
    const resultsInfo = document.querySelector('.results-info');
    if (resultsInfo) {
        resultsInfo.innerHTML = `Tìm thấy <strong>${currentResults.length} kết quả</strong> với từ khóa <strong>"ap"</strong>`;
    }

    // Reuse createProductCard function from main script.js
    if (typeof createProductCard === 'function') {
        container.innerHTML = currentResults.map(product => createProductCard(product)).join('');
        
        // Reinitialize icons
        if (typeof lucide !== 'undefined') {
            lucide.createIcons();
        }
    }
}

// Initialize on DOM load
document.addEventListener('DOMContentLoaded', function() {
    initSearchPage();
    
    // Reinitialize icons
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});

