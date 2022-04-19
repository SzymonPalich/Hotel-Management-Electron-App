# Server

## Wymagania
- Oracle OpenJDK-18

## Konfiguracja połączenia z bazą
W pliku <b>application.properties</b>
```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/hotel
spring.datasource.username=jan
spring.datasource.password=dzban
```

## Migracje FlyWayDB
Skrypty sql o określonej konwencji nazewnictwa. Szczegóły [tutaj](https://flywaydb.org/documentation/concepts/migrations#java-based-migrations).</br>
Przy pisaniu migracji pamiętać, że <b>tableName</b> w Javie to <b>table_name</b> w bazie.

## Testy Springboot JUnit
Przykładowy test [testujący połączenie z serwerem](src/test/java/com/spurvago/server/CheckHTTPResponseTest.java).

## Swagger
http://localhost:8081/swagger-ui/

## Struktura projektu
Wzorować się na razie na [tym](src/main/java/com/spurvago/server/test).