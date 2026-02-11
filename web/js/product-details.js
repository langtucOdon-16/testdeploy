/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Product Detail Page JavaScript

// Sample images for gallery
const galleryImages = [
    'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBsYXB0b3AlMjBtb2Rlcm58ZW58MXx8fHwxNzcwMDE2NTEyfDA&ixlib=rb-4.1.0&q=80&w=800',
    'https://images.unsplash.com/photo-1719523676561-10af04209e8c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsYXB0b3AlMjB1bHRyYWJvb2t8ZW58MXx8fHwxNzcwMDE2NTE0fDA&ixlib=rb-4.1.0&q=80&w=800',
    'https://images.unsplash.com/photo-1656788519906-b9becc2a6833?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkZXNrdG9wJTIwUEMlMjBnYW1pbmclMjBzZXR1cHxlbnwxfHx8fDE3NzAwMTY1MTN8MA&ixlib=rb-4.1.0&q=80&w=800',
    'https://images.unsplash.com/photo-1714846201700-35b42d937158?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21wdXRlciUyMG1vbml0b3IlMjBkaXNwbGF5fGVufDF8fHx8MTc3MDAxNjUxNHww&ixlib=rb-4.1.0&q=80&w=800'
];

let currentImageIndex = 0;

// Initialize Product Detail Page
function initProductDetail() {
    initGallery();
    renderRelatedProducts();
    initMediaTabs();
}

// Gallery Navigation
function initGallery() {
    const mainImage = document.getElementById('mainImage');
    const thumbnails = document.querySelectorAll('.thumbnail');
    const prevBtn = document.getElementById('galleryPrev');
    const nextBtn = document.getElementById('galleryNext');

    // Thumbnail click
    thumbnails.forEach((thumb, index) => {
        thumb.addEventListener('click', () => {
            currentImageIndex = index;
            updateGalleryImage();
        });
    });

    // Navigation buttons
    if (prevBtn) {
        prevBtn.addEventListener('click', () => {
            currentImageIndex = currentImageIndex > 0 ? currentImageIndex - 1 : galleryImages.length - 1;
            updateGalleryImage();
        });
    }

    if (nextBtn) {
        nextBtn.addEventListener('click', () => {
            currentImageIndex = currentImageIndex < galleryImages.length - 1 ? currentImageIndex + 1 : 0;
            updateGalleryImage();
        });
    }

    function updateGalleryImage() {
        if (mainImage) {
            mainImage.src = galleryImages[currentImageIndex];
        }

        // Update active thumbnail
        thumbnails.forEach((thumb, index) => {
            if (index === currentImageIndex) {
                thumb.classList.add('active');
            } else {
                thumb.classList.remove('active');
            }
        });
    }
}

// Media Tabs
function initMediaTabs() {
    const mediaTabs = document.querySelectorAll('.media-tab');
    
    mediaTabs.forEach(tab => {
        tab.addEventListener('click', () => {
            mediaTabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            
            // Here you can add logic to show different content based on tab
            console.log('Switched to tab:', tab.textContent.trim());
        });
    });
}

// Related Products
const relatedProducts = [
    {
        id: 101,
        name: 'Laptop HP Pavilion 15',
        price: 15990000,
        originalPrice: 18990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?w=400',
        rating: 4.4,
        reviews: 167,
        specs: ['Intel Core i7-1255U', 'Intel Iris Xe', '16GB RAM, 512GB SSD']
    },
    {
        id: 102,
        name: 'Laptop Dell Inspiron 15 3520',
        price: 13990000,
        originalPrice: 16990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?w=400',
        rating: 4.5,
        reviews: 189,
        badge: 'GIẢM SỐC',
        specs: ['Intel Core i5-1235U', 'Intel UHD Graphics', '8GB RAM, 512GB SSD']
    },
    {
        id: 103,
        name: 'Laptop Lenovo IdeaPad Gaming 3',
        price: 19990000,
        originalPrice: 24990000,
        image: 'https://images.unsplash.com/photo-1759820940702-ad8f5490a88a?w=400',
        rating: 4.6,
        reviews: 203,
        badge: 'MỚI',
        specs: ['AMD Ryzen 5 6600H', 'RTX 3050 4GB', '8GB RAM, 512GB SSD']
    },
    {
        id: 104,
        name: 'Laptop Acer Aspire 5',
        price: 12990000,
        image: 'https://images.unsplash.com/photo-1719523676561-10af04209e8c?w=400',
        rating: 4.3,
        reviews: 134,
        specs: ['Intel Core i5-1235U', '8GB RAM, 512GB SSD', 'Windows 11']
    }
];

function renderRelatedProducts() {
    const container = document.getElementById('relatedProducts');
    if (!container) return;

    // Reuse createProductCard function from main script.js
    if (typeof createProductCard === 'function') {
        container.innerHTML = relatedProducts.map(product => createProductCard(product)).join('');
        
        // Reinitialize icons
        if (typeof lucide !== 'undefined') {
            lucide.createIcons();
        }
    }
}

// Initialize on DOM load
document.addEventListener('DOMContentLoaded', function() {
    initProductDetail();
    
    // Reinitialize icons
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});

