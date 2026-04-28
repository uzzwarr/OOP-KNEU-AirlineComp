<!DOCTYPE html>
<html lang="uk">
<head>
    <title>Панель керування рейсами</title>
    <meta charset="UTF-8">
    <style>
        table { border-collapse: collapse; width: 80%; margin-top: 20px; font-family: sans-serif; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f4f4f4; }
        tr:hover { background-color: #f9f9f9; }
        .delete-link { color: #d9534f; text-decoration: none; font-weight: bold; margin-left: 10px; }
        .delete-link:hover { text-decoration: underline; }
        .edit-link { color: #0275d8; text-decoration: none; font-weight: bold; }
        .edit-link:hover { text-decoration: underline; }
        .add-button { display: inline-block; margin-bottom: 15px; padding: 10px 15px; background-color: #5cb85c; color: white; text-decoration: none; border-radius: 4px; font-weight: bold; }
        .add-button:hover { background-color: #4cae4c; }
    </style>
</head>
<body>
    <h1>✈ Керування рейсами авіакомпанії</h1>

    <a href="/flights/new" class="add-button">➕ Додати новий рейс</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Номер рейсу</th>
                <th>Звідки</th>
                <th>Куди</th>
                <th>Дії</th>
            </tr>
        </thead>
        <tbody>
            <#list flights as flight>
            <tr>
                <td>${flight.id}</td>
                <td>${flight.flightNumber}</td>
                <td>${flight.departureCity}</td>
                <td>${flight.arrivalCity}</td>
                <td>
                    <a href="/flights/edit/${flight.id}" class="edit-link"> Редагувати</a>
                    <a href="/flights/delete/${flight.id}" class="delete-link"> Видалити</a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>