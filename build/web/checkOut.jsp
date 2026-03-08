<%-- 
    Document   : checkOut
    Created on : Feb 22, 2026, 9:12:23 PM
    Author     : KATANA15
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thanh toán</title>
        <link rel="stylesheet" href="./css/checkOut.css"/>
    </head>

    <body>

        <form action="order" method="post">

            <div class="container">

                <!-- DANH SÁCH SẢN PHẨM -->
                <div class="left-section">

                    <h2>Thông tin đơn hàng</h2>

                    <c:set var="total" value="0"/>

                    <c:forEach items="${checkoutItems}" var="item">

                        <div class="product-item">

                            <div class="product-left">
                                <img src="images/${item.img}" class="product-img">

                                <div>
                                    <div class="product-name">${item.productName}</div>
                                    <div class="product-qty">x${item.quantity}</div>
                                </div>
                            </div>

                            <div class="price">
                                <fmt:formatNumber value="${item.price * item.quantity}" type="number" groupingUsed="true"/> VND
                            </div>

                        </div>

                        <c:set var="total" value="${total + (item.price * item.quantity)}"/>

                    </c:forEach>

                </div>


                <!-- THANH TOÁN -->
                <div class="right-section">

                    <h2>Thanh toán</h2>

                    <div class="form-group">
                        <label>Phương thức thanh toán</label>
                        <select name="paymentId" required id="paymentMethod" onchange="toggleQR()">
                            <option value="">-- Chọn phương thức --</option>
                            <c:forEach items="${paymentList}" var="payment">
                                <option value="${payment.paymentId}">
                                    ${payment.namePayment}
                                </option>
                            </c:forEach>
                        </select>
                        <div id="qrSection" class="qr-float">
                            <img id="qrImage" src="" width="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ nhận hàng</label>
                        <select name="addressId" required>
                            <option value="">-- Chọn địa chỉ --</option>
                            <c:forEach items="${addressList}" var="addr">
                                <option value="${addr.addressId}">
                                    ${addr.addressName},${addr.phone}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <hr>
                    <div class="summary-row">
                        <span>Tổng tiền:</span>
                        <span class="total">
                            <fmt:formatNumber value="${total}" type="number" groupingUsed="true"/> VND
                        </span>
                    </div>
                    <button type="submit" class="btn-submit">
                        Xác nhận đặt hàng
                    </button>
                </div>

            </div>

            <script>
                function toggleQR() {
                    var method = document.getElementById("paymentMethod").value;
                    var qr = document.getElementById("qrSection");
                    var qrImg = document.getElementById("qrImage");
                    if (method === "4") {
                        qr.style.display = "block";
                        qrImg.src = "images/qr.jpg";
                    } else if (method === "3") {
                        qr.style.display = "block";
                        qrImg.src = "images/momo.jpg";
                    } else if (method === "2") {
                        qr.style.display = "block";
                        qrImg.src = "images/vnpay.jpg";
                    } else {
                        qr.style.display = "none";
                    }
                }
            </script>
        </form>

    </body>
</html>
