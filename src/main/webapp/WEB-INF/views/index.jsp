<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background: url("https://i.imgur.com/gjb60y0.png") no-repeat;
            background-size: cover;
        }
    </style>
    <title>Home Page</title>
</head>
<body>
<div class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/index">HomePage |</a>
        </li>
        <li class="nav-item active" style="position: absolute; right: 200px; top: 10px">
            <a class="nav-link" href="${pageContext.request.contextPath}/fill">FillData</a>
        </li>
        <li class="nav-item active" style="position: absolute; right: 0px; top: 10px">
            <button class="btn btn-dark" data-target=".bd-example-modal-sm" data-toggle="modal" type="button">Login
            </button>
        </li>
        <li class="nav-item active" style="position: absolute; right: 100px; top: 10px">
            <button class="btn btn-dark" data-target=".bd-example-modal" data-toggle="modal" type="button">
                Register
            </button>
        </li>
    </ul>
</div>
<div aria-hidden="true" aria-labelledby="mySmallModalLabel" class="modal fade bd-example-modal" role="dialog"
     tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/register">
                <div class="modal-header">
                    <h4 class="modal-title">Register new user</h4>
                    <button aria-label="Close" class="close" data-dismiss="modal" type="button">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="form-group">
                    <label class="col-form-label" for="name">Name:</label>
                    <input class="form-control" id="name" name="name" type="text" required>
                </div>

                <div class="form-group">
                    <label class="col-form-label" for="login">Login:</label>
                    <input class="form-control" id="login" name="login" type="text" required>
                </div>
                <div class="form-group">
                    <label class="col-form-label" for="passwordEdit">Password:</label>
                    <input class="form-control" id="passwordEdit" name="psw" type="password" required>
                </div>
                <div class="form-group">
                    <label class="col-form-label" for="passwordRepeat">Please repeat your password:</label>
                    <input class="form-control" id="passwordRepeat" name="pswRepeat" type="password" required>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Register</button>
                    <input class="btn btn-danger" type="reset" value="Reset">
                </div>
            </form>
        </div>
    </div>
</div>
</ul>
</div>
<div aria-hidden="true" aria-labelledby="mySmallModalLabel" class="modal fade bd-example-modal-sm" role="dialog"
     tabindex="-1">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="mySmallModalLabel">Sign in</h5>
                    <button aria-label="Close" class="close" data-dismiss="modal" type="button">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-form-label" for="username">Username:</label>
                        <input class="form-control" type="text" name="username" id="username" />
                    </div>

                    <div class="form-group">
                        <label class="col-form-label" for="username">Password:</label>
                        <input class="form-control" type="password" name="password" id="password" />
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" data-dismiss="modal" type="button">Close</button>
                    <button class="btn btn-primary" type="submit">Login</button>
                </div>
            </div>
        </div>
    </form>
</div>
<h1 style="font-family: 'Monotype Corsiva',fantasy">Please sign in or sign up</h1>
<h4 style="color: red">${message}</h4>
</body>
</html>
