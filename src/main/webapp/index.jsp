<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <title>TODO List</title>

</head>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

<body class="bg-light">

<script>
    <jsp:include page='scripts/index.js'/>
</script>

<div class="container">
    <div class="row float-right" style="font-size: x-large ; margin-right: 10px">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" id="userName" style="color: white" href="<%=request.getContextPath()%>/login.jsp">
                    <i class="bi bi-person-circle"></i>
                    <c:out value="${user.name}"/>
                </a>
            </li>
            <li class="nav-item">
                <c:if test="${user != null}">
                    <a class="nav-link" style="color: white" href="<%=request.getContextPath()%>/logout.do">
                        <i class="bi bi-door-open-fill"></i>
                        Выйти
                    </a>
                </c:if>
            </li>
        </ul>
    </div>

    <div class="header">
        <h1 class="bg-secondary text-white">
            <p style="margin-left: 10px">
                TODO Список дел <i class="bi bi-clipboard-check"></i>
            </p>
        </h1>
    </div>

    <div class="newTask">
        <div class="row">
            <div class="col-md-9 order-md-1 mb-4">
                <form class="was-validated">
                    <label class="col-form-label" for="newTask" style="font-size: large">Добавить новое задание:</label>
                    <input type="text" class="form-control" id="newTask" placeholder="Новая задача" required><br><br>
                    <button id="btn" type="button" class="btn btn-secondary" onclick="addNewTask();">Добавить</button>
                </form>
            </div>

            <div class="col-md-3 order-md-2 mb-4">
                <form class="was-validated" method="post">
                    <label class="col-form-label" for="cIds" style="font-size: large">Категория:</label>
                    <div>
                        <select class="form-control" size="6rem" name="cIds" id="cIds" required multiple></select>
                    </div>
                </form>
            </div>
        </div>

        <div class="form-check float-right">
            <label class="form-check-label">
                <input type="checkbox" class="form-check-input" id="showAll" onclick="getTable();"> Показать все
            </label>
        </div>

        <table class="table table-striped" id="taskTable">
            <thead>
            <tr>
                <th>Описание</th>
                <th>Добавлен</th>
                <th>Пользователь</th>
                <th>Статус</th>
            </tr>
            </thead>
            <tbody id="table">
            </tbody>
        </table>
    </div>
</div>
</body>
</html>