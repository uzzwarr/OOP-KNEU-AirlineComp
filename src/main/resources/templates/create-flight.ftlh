<!DOCTYPE html>
<html lang="uk">
<head>
    <title>Дані рейсу</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>✈ Дані рейсу</h1>

    <form action="/flights" method="POST">
        <input type="hidden" name="id" value="${(flight.id)!}">

        <label>Номер рейсу:</label>
        <input type="text" name="flightNumber" value="${(flight.flightNumber)!}" required><br><br>

        <label>Звідки:</label>
        <input type="text" name="departureCity" value="${(flight.departureCity)!}" required><br><br>

        <label>Куди:</label>
        <input type="text" name="arrivalCity" value="${(flight.arrivalCity)!}" required><br><br>

        <button type="submit">Зберегти рейс</button>
    </form>
</body>
</html>l>