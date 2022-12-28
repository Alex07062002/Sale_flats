<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    .header{
        max-width: 100%;
        max-height: 15%;
        background-color: orange;
        text-align: right;
    }
    .body{
        max-width: 100%;
        max-height: 60%;
        height: auto;
        background-color: aliceblue;
    }
    .footer{
        max-width: 100%;
        max-height: 25%;
        background-color: aquamarine;
    }
    .overlay {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: 9999;
        background-color: black;
        color: white;
        padding: 10% 0;
        text-align: center;
    }
</style>
текст
<div id="protect-body" class="body">Скрытый текст</div>
текст
<div id="protect-overlay" class="overlay">
<p> Админская страница...</p>
    <form action="#">
        <input name="answer" type="password" placeholder="Введите пароль"/> <button type="submit">Открыть страницу</button>
    </form>
</div>
<script>
    var o = document.getElementById('protect-overlay');
    o.getElementsByTagName('form')[0].onsubmit = function() {
        if (this.answer.value === 'Polozhencev') {
            o.style.display = "none";
        } else {
            alert('Неправильный пароль!'),
                top.location.href="http://localhost:8080/";
        }
        return false;
    };
</script>
<form class="header">
    <a href="AuthAdmin.jsp">Авторизация/Регистрация</a>
</form>
<form class="body">
    <h1>О компании</h1>
    <h4>Времени не хватает... поэтому заглушка</h4>
</form>
<form class="footer">
    <h2>Контакты</h2>
    <a href="index.jsp">На главную</a>
</form>
</body>
</html>
