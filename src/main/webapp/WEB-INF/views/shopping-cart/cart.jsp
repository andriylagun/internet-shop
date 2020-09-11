<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Shopping Cart | MyShop</title>
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="row justify-content-center align-items-center">
        <div style="text-align: center">
            <h3>ShoppingCart:</h3>
            <p></p>
            <table class="table table-bordered table-hover" >
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Remove</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${shoppingCart.products}">
                    <tr>
                        <td>
                            <c:out value="${product.name}"/>
                        </td>
                        <td>
                            <c:out value="${product.price}"/>
                        </td>
                        <td>
                            <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/shopping-cart/products/remove?id=${product.id}">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-dark btn-sm" href="${pageContext.request.contextPath}/order/add?id=${shoppingCart.userId}">Create order</a>
        </div>
    </div>
</div>
</body>
</html>
