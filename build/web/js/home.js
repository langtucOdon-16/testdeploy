/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* 
 * Author      : NamTQ
 * Created on  : Feb 8, 2026
 * Description : JavaScript file for the Home page of the Computer Website.
 *               Handles the main carousel and product sliders.
*/

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

    // Update slide and dot active state
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

    // Navigate to specific slide
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

/**
 * Initializes a horizontal scroll slider for product sections.
 * 
 * @param {string} containerId - The ID of the scrollable container.
 * @param {string} prevBtnId   - The ID of the "Previous" button.
 * @param {string} nextBtnId   - The ID of the "Next" button.
 */
function setupProductSlider(containerId, prevBtnId, nextBtnId) {
    const container = document.getElementById(containerId);
    const prevBtn = document.getElementById(prevBtnId);
    const nextBtn = document.getElementById(nextBtnId);

    if (!container || !prevBtn || !nextBtn) return;

    const scrollAmount = 280; 

    nextBtn.addEventListener('click', () => {
        container.scrollLeft += scrollAmount;
    });

    prevBtn.addEventListener('click', () => {
        container.scrollLeft -= scrollAmount;
    });
}

// INITIALIZATION ON PAGE LOAD
document.addEventListener('DOMContentLoaded', function() {
    initCarousel();
    
    setupProductSlider('hotDealsGrid', 'hotDealsPrev', 'hotDealsNext');
    setupProductSlider('hotSellingGrid', 'hotSellingPrev', 'hotSellingNext');
});

