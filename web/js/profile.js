/*
 * File        : profile.js
 * Created on  : Feb 8, 2026
 * Author      : GiangLT
 * Description : Xử lý chức năng chỉnh sửa và thêm địa chỉ trong trang profile.
 */

// Hiển thị form chỉnh sửa
function editAddress(id) {
    const box = document.getElementById("address-" + id);
    if (!box) return;

    const addressText = box.querySelector(".address-text");
    const phoneText = box.querySelector(".phone-text");
    const actions = box.querySelector(".address-actions");
    const editForm = box.querySelector(".edit-form");

    if (addressText) addressText.style.display = "none";
    if (phoneText) phoneText.style.display = "none";
    if (actions) actions.style.display = "none";
    if (editForm) editForm.style.display = "block";
}

// Huỷ chỉnh sửa
function cancelEdit(id) {
    const box = document.getElementById("address-" + id);
    if (!box) return;

    const addressText = box.querySelector(".address-text");
    const phoneText = box.querySelector(".phone-text");
    const actions = box.querySelector(".address-actions");
    const editForm = box.querySelector(".edit-form");

    if (editForm) editForm.style.display = "none";
    if (addressText) addressText.style.display = "block";
    if (phoneText) phoneText.style.display = "inline";
    if (actions) actions.style.display = "block";
}

// Hiển thị form thêm địa chỉ
function showAddForm() {
    const form = document.getElementById("addAddressForm");
    if (!form) return;
    form.style.display = "block";
}

// Ẩn form thêm địa chỉ
function hideAddForm() {
    const form = document.getElementById("addAddressForm");
    if (!form) return;
    form.style.display = "none";
}

// Toggle form thêm địa chỉ 
function toggleAddForm() {
    const form = document.getElementById("addAddressForm");
    if (!form) {
        console.log("Form not found");
        return;
    }

    if (form.style.display === "none" || form.style.display === "") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}

