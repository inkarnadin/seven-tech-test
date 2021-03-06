# Seven tech test challenge

## Задание

Должно быть 3 API (RESTful): 
- перевод денег с одного счёта на другой, 
- положить деньги на счёт, 
- снять деньги со счёта. 

Отрицательный баланс счета недопустим. 
В качестве хранилища можно использовать любую in-memory БД. 
Доступ к БД осуществить через JPA. 

Исходный код должен собираться с помощью maven или gradle в исполняемый jar.

## Решение

### Сборка

Для сборки проекта необходимо выполнить команду `mvn package`.

### Запуск

Для запуска полученного jar-файла необходимо выполнить команду `..\java -jar ..\seven-tech-test-1.jar` с указанием вместо `..` корректных путей.

### Описание

Описание конечных точек взаимодействия с приложением в формате OpenAPI можно найти тут:  
`http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config`

Для проверки работоспособности решения можно использовать запросы следующего вида:
* Положить деньги на счет:  
`
curl --request POST 'http://localhost:8080/api/v1/account/replenish'
--header 'Content-Type: application/json'
--data-raw '{
    "accountNumber": "S-564",
    "amount": 10
}'
`

* Снять деньги со счёта:  
`
curl --request POST 'http://localhost:8080/api/v1/account/withdraw'
--header 'Content-Type: application/json'
--data-raw '{
    "accountNumber": "S-564",
    "amount": 10
}'
`

* Перевести деньги с одного счета на другой:  
`
curl --request POST 'http://localhost:8080/api/v1/account/transfer'
--header 'Content-Type: application/json'
--data-raw '{
    "senderAccountNumber": "S-564",
    "recipientAccountNumber": "S-567",
    "amount": 10
}'
`

Доступные для проверки данные счётов и их баланс:

| Счет       | Баланс             |
| ---------- |:------------------:|
| S-564      | 100.5              |
| S-567      | 120.55             |
| S-009      | 0.07               |
| S-700      | 2015.23            |

В тех случаях, если счет не найден, на счете недостаточно средств, либо если для перевода денег используется один и тот же аккаунт, будет выдано сообщение об ошибке.

Решение снабжено базовыми unit-тестами (на уровне проверки слоя бизнес-логики и слоя обработки запросов) и имеет минимальный `javadoc`.