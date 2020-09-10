<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Register | MyShop</title>
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
<%@include file="header.jsp"%>
<div class="container" align="center">
    <h2>Please fill the form to register a new user</h2>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <h4 style="color: red">${message}</h4>
                    <div class="form-group">
                        <label class="col-form-label" for="name">Name:</label>
                        <input class="form-control" id="name" name="name" type="text" style="text-align: center; width: 500px" required>
                    </div>

                    <div class="form-group">
                        <label class="col-form-label" for="login">Login:</label>
                        <input class="form-control" id="login" name="login" type="text" style="text-align: center; width: 500px" required>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="passwordEdit">Password:</label>
                        <input class="form-control" id="passwordEdit" name="psw" type="password" style="text-align: center; width: 500px" required>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="passwordRepeat">Please repeat your password:</label>
                        <input class="form-control" id="passwordRepeat" name="pswRepeat" type="password" style="text-align: center; width: 500px" required>
                    </div>
                    <button class="btn btn-primary" type="submit">Save</button>
                    <button class="btn btn-secondary" type="reset">Reset</button>
    </form>
</div>
</body>
</html>
