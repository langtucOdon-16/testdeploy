/*
 * File        : profile.js
 * Created on  : Feb 8, 2026
 * Author      : GiangLT
 * Description : Xử lý hiệu ứng giao diện (UI) và tương tác phía client
 *               cho toàn bộ hệ thống.
 */

//Hiển thị thông báo nổi (success / error) trên giao diện
function showToast(message, type = 'success') {
    const toast = document.getElementById('toast');
    if (!toast) return;

    toast.textContent = message;
    toast.className = `toast show ${type}`;

    setTimeout(() => {
        toast.className = 'toast';
    }, 3000);
}

//Tạo hiệu ứng gợn sóng khi nhấn nút
document.addEventListener('click', function (e) {
    const btn = e.target.closest('.btn');
    if (!btn) return;

    const ripple = document.createElement('span');
    ripple.className = 'ripple';

    const rect = btn.getBoundingClientRect();
    ripple.style.left = (e.clientX - rect.left) + 'px';
    ripple.style.top = (e.clientY - rect.top) + 'px';

    btn.appendChild(ripple);

    setTimeout(() => {
        ripple.remove();
    }, 600);
});

//Thêm bóng đổ cho navbar khi cuộn trang 
window.addEventListener('scroll', () => {
    const navbar = document.querySelector('.navbar');
    if (!navbar) return;

    if (window.scrollY > 10) {
        navbar.classList.add('navbar-scrolled');
    } else {
        navbar.classList.remove('navbar-scrolled');
    }
});

//Thêm hiệu ứng khi hover vào thẻ feature
document.querySelectorAll('.feature-card').forEach(card => {
    card.addEventListener('mouseenter', () => {
        card.classList.add('hovered');
    });

    card.addEventListener('mouseleave', () => {
        card.classList.remove('hovered');
    });
});

//Kiểm tra phía client input required không được để trống
document.querySelectorAll('form').forEach(form => {
    form.addEventListener('submit', function (e) {
        const inputs = form.querySelectorAll('input[required]');
        let valid = true;

        inputs.forEach(input => {
            if (!input.value.trim()) {
                input.classList.add('input-error');
                valid = false;
            } else {
                input.classList.remove('input-error');
            }
        });

        if (!valid) {
            e.preventDefault();
            showToast('Vui lòng nhập đầy đủ thông tin!', 'error');
        }
    });
});

//Thêm class khi trang tải xong để kích hoạt animation
document.addEventListener('DOMContentLoaded', () => {
    document.body.classList.add('page-loaded');
});