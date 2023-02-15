Weather Senser API

I created this using Java and Spring Web with an SQLite database. I used Postman to assist in sending Post requests, DB Browser for SQLite to easily view what was in my database, Maven to handle dependencies and IntelliJ as my IDE.

The endpoints are as follows:

http://localhost:8080 This returns "WEATHER SENSOR REST API".

http://localhost:8080/sensor Returns all sensors

http://localhost:8080/sensor/{id} Returns a sensor by its id

http://localhost:8080/sensor/create I used Postman to send my post requests as it allows you to send json in the request body. The JSON should look like this
{
    "uuid": 400,
    "country": "Ireland",
    "city": "Limerick"}

http://localhost:8080/sensor/data Returns all sensor readings 

http://localhost:8080/sensor/data/{id} Returns all sensor readings for a specific sensor based on id

http://localhost:8080/sensor/data/average?id=101&metric=Temperature Using a query like this, you can use Temperature, Humidity or WindSpeed to get the average metric for a sensor by id.

http://localhost:8080/sensor/data/average/date?id=101&metric=WindSpeed&date=11/01/2023
Using a query like this you can get the average metric, by id, by date in the format dd/mm/yyyy.

http://localhost:8080/sensor/data/average/date-range?id=101&metric=WindSpeed&firstDate=11/01/2023&lastDate=12/01/2023
Using a query like this you can get the average metric, by id, within a date range. The dates must be in the format dd/mm/yyyy

http://localhost:8080/sensor/data/create I used Postman to send my post requests as it allows you to send json in the request body. To create a sensor reading, the JSON should look like this.
{
    "temp": 50,
    "humidity": 33,
    "windSpeed": 28,
    "date": "15/01/2023",
    "uuid": 313
}

If I had more time to work on this coding challenge I would have included more input validation, exception handling and far more unit tests. I did find this quite time consuming and unfortunately didn’t have the time to keep working on it before the deadline. 
The database weatherSensorDB.db is saved here but the app also contains the functionality to create the Database, by uncommenting out line 17 of the main. Thanks for taking the time to look at my coding challenge!
