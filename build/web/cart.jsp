<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Giỏ hàng</title>
        <!-- Load home.css first for header/footer, then cart.css to override if needed -->
        <link rel="stylesheet" href="./css/home.css">
        <link rel="stylesheet" href="./css/cart.css">
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete Product from Cart")) {
                    window.location = "delete?productId=" + id;
                }
            }
        </script>
    </head>

    <body class="cart-body">
        
        
        <div class="cart-page-container">

            <div class="cart-header-section">
                <div class="cart-page-title">Giỏ hàng của bạn</div>
                <div class="cart-page-subtitle">Kiểm tra lại sản phẩm trước khi thanh toán</div>
            </div>

            <form action="checkout" method="post" id="checkout-form">
                <table class="cart-main-table">
                    <thead>
                        <tr>
                            <th class="cart-select-col">
                                <input type="checkbox" id="check-all">
                            </th>
                            <th>Sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:if test="${empty cart}">
                            <tr>
                                <td colspan="5" style="text-align:center; padding: 40px;">Giỏ hàng trống</td>
                            </tr>
                        </c:if>

                        <c:forEach items="${cart}" var="item">
                            <tr data-unit-price="${item.price}">

                                <td class="cart-select-col">
                                    <input type="checkbox"
                                           class="item-check"
                                           name="selectedItems"
                                           value="${item.productId}">
                                </td>

                                <td>
                                    <div class="cart-product-info">
                                        <div class="cart-product-thumb">${item.img}</div>
                                        <div>
                                            <div class="cart-product-name">${item.productName}</div>
                                        </div>
                                    </div>
                                </td>

                                <td>
                                    <div class="cart-qty-control">
                                        <button type="button" class="cart-qty-btn minus">-</button>

                                        <input type="number"
                                               class="cart-qty-input"
                                               name="quantity_${item.productId}"
                                               min="1"
                                               value="${item.quantity}">

                                        <button type="button" class="cart-qty-btn plus">+</button>
                                    </div>
                                </td>

                                <td class="cart-price-text line-total"></td>

                                <td>
                                    <a href="#" onclick="doDelete('${item.productId}')" class="cart-remove-link">Xóa</a>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- TỔNG TIỀN -->
                <div class="cart-summary-section">
                    <div class="cart-summary-box">
                        <div class="cart-summary-row cart-total-row">
                            <span>Tổng tiền</span>
                            <span id="total-amount">0₫</span>
                        </div>
                    </div>
                </div>

                <div class="cart-action-buttons">
                    <a href="HomeServlet" class="cart-btn cart-btn-ghost">← Tiếp tục mua sắm</a>
                    <button type="submit" class="cart-btn cart-btn-primary" id="checkout-btn" disabled>
                        Thanh toán
                    </button>
                </div>

            </form>

        </div>

        <script src="./js/cart.js"></script>

    </body>
</html>
