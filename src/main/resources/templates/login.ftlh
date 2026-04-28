<!DOCTYPE html>
<html lang="uk">
<head>
    <title>Вхід в систему</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1> Вхід для Адміністратора</h1>

    <form action="/login" method="POST">
        <label>Логін:</label>
        <input type="text" name="username" required><br><br>

        <label>Пароль:</label>
        <input type="password" name="password" required><br><br>

        <button type="submit">Увійти</button>
    </form>
</body>
</html>