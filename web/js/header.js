/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
 * Description: Handles interactive behaviors for the website header,
 *              including:
 *              - Category dropdown toggle
 *              - Function menu toggle
 *              - Category → Brand hover menu interaction
 *              - Overlay handling
 *              - Lucide icon initialization
 *
 * Author     : NamTQ
 * Created on : Feb 8, 2026
 */

/**
 * Initializes a toggle menu with overlay support.
 *
 * @param {string} btnId        - The ID of the button that triggers the menu
 * @param {string} dropdownId   - The ID of the dropdown menu element
 * @param {string} overlayId    - The ID of the overlay element
 *
 * This function:
 * - Toggles the visibility of a dropdown menu
 * - Shows/hides overlay when menu is active
 * - Prevents errors if required elements are not found
 */
function initMenu(btnId, dropdownId, overlayId) {
    const btn = document.getElementById(btnId);
    const dropdown = document.getElementById(dropdownId);
    const overlay = document.getElementById(overlayId);
    
    if (btn === null || dropdown === null || overlay === null){
        return;
    }
    
    /**
     * Toggles the active state of the dropdown and overlay.
     */
    function toggleMenu() {
        const isActive = dropdown.classList.contains('active');
        
        if (isActive) {
            dropdown.classList.remove('active');
            overlay.classList.remove('active');
        } else {
            dropdown.classList.add('active');
            overlay.classList.add('active');
        }
    }

    if (btn) {
        btn.addEventListener('click', toggleMenu);
    }

    if (overlay) {
        overlay.addEventListener('click', toggleMenu);
    }
}

/**
 * Initializes category-brand hover interaction.
 *
 * Behavior:
 * - When hovering over a category item:
 *     + Shows corresponding brand group
 *     + Highlights selected category
 * - Only one category can be active at a time
 * - Clicking overlay closes the dropdown
 */
function initCategoryBrandMenu() {
    const categoryItems = document.querySelectorAll('.category-item');
    const brandDropdown = document.getElementById('brandDropdown');
    const categoryOverlay = document.getElementById('categoryOverlay');
    let preItem = null;
    let preItemCategoryId = null;
    
    /**
     * Displays brand group associated with a specific category.
     *
     * @param {string} categoryId - The ID of the selected category
     */
    function showBrands (categoryId){
        if (preItemCategoryId !== null){
            document.querySelector(`.brand-group[data-category-id="${preItemCategoryId}"]`).style.display = 'none';
        }
        document.querySelector(`.brand-group[data-category-id="${categoryId}"]`).style.display = 'block';
        preItemCategoryId = categoryId;
    }
    
    /**
     * Activates selected category item and deactivates previous one.
     *
     * @param {HTMLElement} item - The hovered category element
     */
    function toggleCategoryBrandMenu(item) {
        brandDropdown.classList.add('active'); 
        item.classList.add('active');
        if (preItem !== null && preItem !== item){
            preItem.classList.remove('active');
        }
        preItem = item;
    }
    
    categoryItems.forEach(item => {
        item.addEventListener('mouseenter', () => {
            const categoryId = item.dataset.categoryId;
            showBrands(categoryId);
            toggleCategoryBrandMenu(item);
        }); 
    });
    
    if (categoryOverlay) {
        categoryOverlay.addEventListener('click', () => {
            brandDropdown.classList.remove('active');
            categoryItems.forEach(item => {
                item.classList.remove('active');
            });
            preItem = null;
        });
    }
}

/**
 * Initializes header functionality after DOM is fully loaded.
 * - Category dropdown
 * - Category-brand hover menu
 * - Function dropdown
 * - Lucide icons rendering
 */
document.addEventListener('DOMContentLoaded', function() {
    initMenu('categoryBtn', 'categoryDropdown', 'categoryOverlay');
    initCategoryBrandMenu();
    initMenu('functionBtn', 'functionDropdown', 'functionOverlay');
    
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }
});