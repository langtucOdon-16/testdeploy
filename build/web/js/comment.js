/*
 * File        : comment.js
 * Created on  : Feb 12, 2026
 * Author      : GiangLT
 * Description : Xử lý chức năng hiển thị/ẩn form trả lời bình luận,
 *               toggle khu vực bình luận và highlight @username.
 */

function toggleReply(commentId, btn) {

    const username = btn.getAttribute("data-username");

    document.querySelectorAll(".reply-form").forEach(form => {
        form.style.display = "none";
    });

    const form = document.getElementById("reply-" + commentId);

    if (!form) return;

    form.style.display = "block";

    const textarea = form.querySelector("textarea");

    textarea.value = "@" + username + " ";
    textarea.focus();
}

function toggleCommentSection() {
    const content = document.getElementById("commentContent");
    const btn = document.querySelector(".toggle-comment-btn");

    if (content.style.display === "none") {
        content.style.display = "block";
        btn.innerText = "Ẩn bình luận";
    } else {
        content.style.display = "none";
        btn.innerText = "Hiện bình luận";
    }
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".comment-text").forEach(el => {
        el.innerHTML = el.innerHTML.replace(
            /@(\w+)/g,
            '<span class="mention">@$1</span>'
        );
    });
});