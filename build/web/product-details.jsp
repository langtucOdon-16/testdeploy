<%-- 
    Description: Product Details page of Tài Lộc Store e-commerce system.
                 This page displays:
                 - Breadcrumb navigation
                 - Product image gallery
                 - Detailed product information (price, discount, quantity)
                 - Product specifications
                 - Related products
                 
                 Data is provided by ProductDetailsServlet via request attributes.
    Author     : NamTQ
    Created on : Feb 8, 2026
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm - Tài Lộc Store</title>
    <link rel="stylesheet" href="./css/header-footer.css">
    <link rel="stylesheet" href="./css/product-card.css">
    <link rel="stylesheet" href="./css/comment.css">
    <link rel="stylesheet" href="./css/product-details.css">
    <link rel="stylesheet" href="./css/home.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    
    <jsp:include page="./common/header.jsp"/>

    <!-- ================= BREADCRUMB NAVIGATION ================= -->
    <!-- Displays navigation hierarchy: Home > Category > Brand > Product -->
    <div class="breadcrumb-wrapper">
        <div class="container">
            <nav class="breadcrumb">
                <a href="HomeServlet">Trang chủ</a>
                <i data-lucide="chevron-right"></i>
                <a href="SearchServlet?categoryIdName=${productFullInformation.category.categoryId}">${productFullInformation.category.categoryName}</a>
                <i data-lucide="chevron-right"></i>
                <a href="SearchServlet?categoryIdName=${productFullInformation.category.categoryId}&brandIdName=${productFullInformation.brand.brandId}">${productFullInformation.brand.brandName}</a>
                <i data-lucide="chevron-right"></i>
                <span>${productFullInformation.name}</span>
            </nav>
        </div>
    </div>
        
    <!-- ================= PRODUCT IMAGE GALLERY DATA ================= -->
    <!-- Dynamically generates image URLs for JavaScript gallery -->
    <script>
        const galleryImages = [
            <c:forEach items="${productImages}" var="image" varStatus="st">
                    "${pageContext.request.contextPath}/images/${image.urlImage}"<c:if test="${!st.last}">, </c:if>
            </c:forEach>
        ];
    </script>

    <!-- ================= PRODUCT DETAIL SECTION ================= -->
    <!-- Contains product gallery (left) and product information (right) -->
    <main class="product-detail-main">
        <div class="container">
            <div class="product-detail-grid">
                <!-- Left Column - Images -->
                <div class="product-gallery">
                    <div class="main-image-wrapper">
                        <img 
                            src="" 
                            alt="${productFullInformation.name}" 
                            class="main-image"
                            id="mainImage"
                        >
                        <button class="gallery-nav gallery-prev" id="galleryPrev">
                            <i data-lucide="chevron-left"></i>
                        </button>
                        <button class="gallery-nav gallery-next" id="galleryNext">
                            <i data-lucide="chevron-right"></i>
                        </button>
                    </div>
                    
                    <!-- Thumbnail Gallery -->
                    <div class="thumbnail-gallery" id="thumbnailGallery">
                        <c:forEach items="${productImages}" var="image" varStatus="st">
                            <button class="thumbnail active" data-index="0">
                                <img src="${pageContext.request.contextPath}/images/${image.urlImage}" alt="${image.urlImage}">
                            </button>
                        </c:forEach>
                    </div>
                </div>

                <!-- Right Column - Product Info -->
                <div class="product-info-panel">
                    <!-- Product Title -->
                    <h1 class="product-detail-title">
                        ${productFullInformation.name}
                    </h1>
                    <div class="product-quantity">Còn: ${productFullInformation.quantity}</div>

                    <!-- Pricing Information -->
                    <!-- Displays original price (if discounted), final price and discount rate -->
                    <div class="product-pricing-detail">
                        <div class="price-main">
                            <c:if test="${productFullInformation.discountRate > 0}">
                                <span class="original-price">
                                    <fmt:formatNumber 
                                    value="${productFullInformation.originalPrice}" 
                                    type="number" 
                                    groupingUsed="true" 
                                    maxFractionDigits="0"/>₫
                                </span>
                            </c:if>
                            <span class="current-price">
                                <fmt:formatNumber 
                                value="${productFullInformation.afterDiscountPrice}" 
                                type="number" 
                                groupingUsed="true"
                                maxFractionDigits="0"/>₫
                            </span>
                            <c:if test="${productFullInformation.discountRate > 0}">
                                <span class="discount-percent">${productFullInformation.discountRate}%</span>
                            </c:if>  
                        </div>
                    </div>

                    <!-- ================= PRODUCT ACTIONS ================= -->
                    <!-- Buy Now and Add to Cart forms -->
                    <div class="product-actions">
                        <form action="#" method="post">
                            <input type="hidden" name="accountId" value="${sessionScope.account.accountId}" >
                            <input type="hidden" name="productId" value="${productFullInformation.productId}" >
                            <button class="btn-buy-now" type="submit">
                                MUA NGAY
                            </button>
                        </form>
                        <form action="#" method="post">
                            <input type="hidden" name="accountId" value="${sessionScope.account.accountId}" >
                            <input type="hidden" name="productId" value="${productFullInformation.productId}" >
                            <button class="btn-add-cart" type="submit">
                                THÊM VÀO GIỎ
                            </button>
                        </form>
                    </div>

                    <!-- Policies -->
                    <div class="product-more-information">
                        <div class="information-item">
                            <i data-lucide="palette"></i>
                            <span>Màu: ${productFullInformation.color}</span>
                        </div>
                        <div class="information-item">
                            <i data-lucide="calendar-days"></i>
                            <span>Ngày phát hành: ${productFullInformation.releaseDate}</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- ================= PRODUCT SPECIFICATIONS SECTION ================= -->
            <!-- Dynamically renders technical specifications -->
            <div class="product-specs-section">
                <div class="specs-header">
                    <h2>Thông số nổi bật</h2>
                </div>
                <div class="specs-grid">
                    <c:forEach items="${productSpecs}" var="spec">
                        <div class="spec-card">
                            <div class="spec-info">
                                <div class="spec-label">${spec.specName}</div>
                                <div class="spec-value">${spec.specValue}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- ================= RELATED PRODUCTS SECTION ================= -->
            <!-- Displays top related products based on category and brand -->
            <div class="related-products-section">
                <h2 class="section-title">Sản phẩm tương tự</h2>
                <div class="product-grid" id="relatedProducts">
                    <c:forEach items="${top10RelatedProducts}" var="product">
                        <%@ include file="./common/product-card.jsp" %>
                    </c:forEach>                    
                </div>
            </div>
            
            <!-- ================= COMMENT SECTION ================= -->
            <div class="comment-section">
                <div class="container">

                    <!-- HEADER -->
                    <div class="comment-header-top">
                        <h2 class="section-title">
                            Bình luận (${fn:length(commentList)})
                        </h2>

                        <button type="button"
                                class="toggle-comment-btn"
                                onclick="toggleCommentSection()">
                            Ẩn bình luận
                        </button>
                    </div>

                    <!-- WRAPPER -->
                    <div id="commentContent">

                        <!-- FORM COMMENT CHA -->
                        <form action="AddCommentServlet"
                              method="post"
                              class="comment-form">

                            <input type="hidden"
                                   name="productId"
                                   value="${productFullInformation.productId}">

                            <input type="hidden"
                                   name="parentId"
                                   value="">

                            <textarea name="content"
                                      placeholder="Viết bình luận..."
                                      required></textarea>

                            <button type="submit">
                                Gửi bình luận
                            </button>
                        </form>

                        <!-- COMMENT LIST -->
                        <div class="comment-list">

                            <c:forEach var="c" items="${commentList}">
                                <c:if test="${c.parentId == null}">

                                    <div class="comment-box">

                                        <div class="comment-header">
                                            <span class="comment-author">
                                                ${c.username}
                                            </span>
                                            <span class="comment-time">
                                                ${c.time}
                                            </span>
                                        </div>

                                        <div class="comment-content">
                                            <span class="comment-text">
                                                <c:out value="${c.content}" />
                                            </span>
                                        </div>

                                        <!-- REPLY BUTTON CHA -->
                                        <button type="button"
                                                class="reply-btn"
                                                data-username="<c:out value='${c.username}'/>"
                                                onclick="toggleReply(${c.commentId}, this)">
                                            Trả lời
                                        </button>

                                        <!-- FORM REPLY CHA -->
                                        <form action="AddCommentServlet"
                                              method="post"
                                              class="reply-form"
                                              id="reply-${c.commentId}"
                                              style="display:none;">

                                            <input type="hidden"
                                                   name="productId"
                                                   value="${productFullInformation.productId}">

                                            <input type="hidden"
                                                   name="parentId"
                                                   value="${c.commentId}">

                                            <textarea name="content"
                                                      placeholder="Viết phản hồi..."
                                                      required></textarea>

                                            <button type="submit">
                                                Gửi
                                            </button>
                                        </form>

                                        <!-- REPLIES -->
                                        <c:forEach var="r" items="${commentList}">
                                            <c:if test="${r.parentId == c.commentId}">

                                                <div class="reply-box">

                                                    <div class="comment-header">
                                                        <span class="comment-author">
                                                            ${r.username}
                                                        </span>
                                                        <span class="comment-time">
                                                            ${r.time}
                                                        </span>
                                                    </div>

                                                    <div class="comment-content">
                                                        <span class="comment-text">
                                                            <c:out value="${r.content}" />
                                                        </span>
                                                    </div>

                                                    <!-- REPLY BUTTON CON -->
                                                    <button type="button"
                                                            class="reply-btn"
                                                            data-username="<c:out value='${r.username}'/>"
                                                            onclick="toggleReply(${r.commentId}, this)">
                                                        Trả lời
                                                    </button>

                                                    <!-- FORM REPLY CON -->
                                                    <form action="AddCommentServlet"
                                                          method="post"
                                                          class="reply-form"
                                                          id="reply-${r.commentId}"
                                                          style="display:none;">

                                                        <input type="hidden"
                                                               name="productId"
                                                               value="${productFullInformation.productId}">

                                                        <input type="hidden"
                                                               name="parentId"
                                                               value="${r.commentId}">
                                                        <textarea name="content"
                                                                  placeholder="Viết phản hồi..."
                                                                  required></textarea>

                                                        <button type="submit">
                                                            Gửi
                                                        </button>
                                                    </form>

                                                </div>

                                            </c:if>
                                        </c:forEach>

                                    </div>

                                </c:if>
                            </c:forEach>

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </main>

    <jsp:include page="./common/footer.jsp"/>

    <script src="./js/header.js"></script>
    <script src="./js/product-details.js"></script>
    <script src="./js/comment.js"></script>

</body>
</html>
