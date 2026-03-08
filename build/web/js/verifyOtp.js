/*
 * File        : verifyOtp.js
 * Created on  : Feb 8, 2026
 * Author      : GiangLT
 * Description : Xử lý chức năng đếm ngược thời gian hết hạn OTP
 *               và điều khiển trạng thái nút "Gửi lại OTP".
 *               Hiển thị thời gian còn lại trước khi OTP hết hạn.
 *               Tự động bật chức năng gửi lại OTP khi hết thời gian.
 */
document.addEventListener("DOMContentLoaded", function () {
    
    // Lấy các phần tử cần thao tác trên giao diện
    const countdownEl = document.getElementById("countdown");
    const resendText = document.getElementById("resendText");
    const resendForm = document.getElementById("resendForm");

    const expireTime = parseInt(countdownEl.dataset.expire) || 0;

    //Vô hiệu hoá chức năng Gửi lại OTP
    function disableResend() {
        resendText.style.pointerEvents = "none";
        resendText.style.color = "#aaa";
        resendText.style.cursor = "not-allowed";
    }

    //Kích hoạt chức năng Gửi lại OTP
    function enableResend() {
        resendText.style.pointerEvents = "auto";
        resendText.style.color = "#f4602a";
        resendText.style.cursor = "pointer";
    }

    //Cập nhật thời gian đếm ngược OTP mỗi giây
    //Khi hết thời gian đếm ngược thì bật nút Gửi lại OTP
    function updateCountdown() {
        if (!expireTime || isNaN(expireTime)) {
            countdownEl.textContent = "00:00";
            enableResend();
            return;
        }
        
        const now = Date.now();
        let remain = Math.floor((expireTime - now) / 1000);

        if (remain <= 0) {
            countdownEl.textContent = "00:00";
            enableResend();
            return;
        }

        const minutes = Math.floor(remain / 60);
        const seconds = remain % 60;

        countdownEl.textContent =
            String(minutes).padStart(2, "0") + ":" +
            String(seconds).padStart(2, "0");
    }

    //Xử lí sự kiện khi người dùng nhấn Gửi lại OTP
    resendText.addEventListener("click", function () {
        if (resendText.style.pointerEvents === "none") return;
        resendForm.submit();
    });

    //Khởi tạo ban đầu
    disableResend();
    updateCountdown();
    setInterval(updateCountdown, 1000);
});
