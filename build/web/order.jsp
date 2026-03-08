<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>

<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Lịch sử đơn hàng</title>
        <link rel="stylesheet" href="./css/order.css"/>
    </head>
    <body class="order-page">

        <div class="order-container">

            <div class="order-page-title">Lịch sử đơn hàng</div>
            <c:forEach items="${orders}" var="order">

                <div class="order-card">

                    <!-- HEADER -->
                    <div class="order-card-header">
                        <div>
                            <span class="order-card-id">Mã đơn: #${order.id}</span>
                            <span>${order.orderDate}</span>
                        </div>
                        <div class="order-card-status">${order.status}</div>
                    </div>

                    <!-- BODY -->
                    <div class="order-card-body">

                        <c:forEach items="${order.items}" var="item">

                            <div class="order-item-row">

                                <div class="order-item-left">
                                    <div class="order-item-thumb"></div>

                                    <div>
                                        <div class="order-item-name">
                                            ${item.productName}
                                        </div>
                                        <div class="order-item-qty">
                                            x${item.quantity}
                                        </div>
                                    </div>
                                </div>

                                <div class="order-item-price">
                                    <fmt:formatNumber
                                        value="${item.price * item.quantity}"
                                        type="number"
                                        groupingUsed="true"/>VND
                                </div>

                            </div>

                        </c:forEach>

                    </div>

                    <div class="order-footer">

                        <div class="order-total">
                            <span class="order-footer-label">Tổng tiền:</span>
                            <span>
                                <fmt:formatNumber value="${order.totalAmount}"
                                                  type="number"
                                                  groupingUsed="true"/>
                            </span>
                        </div>

                        <div class="order-address">
                            <span>Địa chỉ: ${order.addressName}</span>
                        </div>

                        <c:choose>

                            <c:when test="${order.status == 'Pending'}">
                                <div class="order-actions">
                                    <form action="cancel" method="post" style="display:inline;">
                                        <input type="hidden" name="orderId" value="${order.id}" />
                                        <button type="submit" class="btn btn-cancel">Hủy</button>
                                    </form>

                                    <form action="confirm" method="post" style="display:inline;">
                                        <input type="hidden" name="orderId" value="${order.id}" />
                                        <button type="submit" class="btn btn-confirm">Xác nhận</button>
                                    </form>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="order-actions">
                                    <button class="btn btn-disabled" disabled>
                                        ${order.status}
                                    </button>
                                </div>
                            </c:otherwise>

                        </c:choose>
                    </div>
                </div>
            </c:forEach>
            <div class="pagination-wrapper">
                        <div class="pagination">
                            <a href="order?page=${currentPage - 1}" class="pagination-btn-link">
                                <button class="pagination-btn pagination-prev" ${currentPage == 1 ? 'disabled' : ''}>
                                    <i data-lucide="chevron-left"></i>
                                    <span>Trước</span>
                                </button>
                            </a>
                            
                            
                            <div class="pagination-numbers">
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <a class="pagination-number ${i == currentPage ? 'active' : ''}" 
                                       href="order?page=${i}">${i}</a>
                                </c:forEach>
                            </div>
                            
                            <a href="order?page=${currentPage + 1}" class="pagination-btn-link">
                                <button class="pagination-btn pagination-next" ${currentPage == totalPages ? 'disabled' : ''}>
                                     <span>Tiếp</span>
                                     <i data-lucide="chevron-right"></i>
                                </button>
                            </a>
                        </div>
                    </div>
        </div>
    </body>
</html>