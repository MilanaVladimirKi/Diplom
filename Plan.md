# План.
Данный тест-план служит основой для автоматизации тестирования веб-сервиса, 
взаимодействующего с СУБД и API банков. 
Он охватывает все аспекты, включая автоматизацию позитивных и негативных сценариев, 
выбор инструментов, интервальную оценку с учётом рисков в часах.

Цель проекта — автоматизация позитивных и негативных сценариев покупки тура
с использованием дебетовых и кредитных карт.

## Техническая часть.

### Перечень используемых инструментов с обоснованием выбора.
1. Git - инструмент для управления кодом.
2. Java - строго типизированный объектно-ориентированный язык программирования общего назначения, 
для создания автотестов; достаточно функционален.
3. IDEA — интегрированная среда разработки программного обеспечения 
для многих языков программирования, в частности Java.
4. JUnit 5 - наиболее широко используемая среда тестирования для приложений Java.
5. Gradle — система автоматической сборки.
6. java-faker предназначен для генерации поддельных данных для полей объекта
на основе аннотаций и конфигураций.
7. Lombok позволяет автоматически преобразовать объемный Java-код в лаконичную структуру. 
Также плагин избавляет разработчика от ручного набора однотипных фрагментов кода и 
предотвращает появление ошибок; библиотека для сокращения кода.
8. Selenide - инструмент, который управляет браузером.
9. Браузер Chrome - браузер является средним звеном между пользователем и интернетом.
10. Allure - это инструмент для создания красивых отчетов о тестировании, 
который можно использовать с фреймворками тестирования, такими как JUnit 5.
11. Joxi - этот скриншотер позволяет делать снимки монитора в нескольких форматах — 
весь экран, регулируемая область, отдельные окна.
12. Docker позволяет легко развертывать окружение с необходимыми сервисами и базами данных.
13. PostgreSQL/MySQL: для работы с базами данных.
14. Node.js — это кроссплатформенная среда выполнения JavaScript с открытым исходным кодом. 

## Перечень автоматизируемых сценариев.

##### Валидные данные:
1. Номер карты: количество цифр 16 (2200 2222 3333 4444);
2. Фамилия и Имя владельца карты: только латинские буквы (Ivanov Ivan);
3. Срок действия карты: месяц/год (07/2026);
4. CVV код: количество цифр 3 (123);

### Позитивные сценарии; оплата тура дебитовой картой.



### Позитивные сценарии; оплата тура кредитной картой.





## Перечень необходимых разрешений, данных и доступов.
1. Разрешение на отправку формы для тестирования интерфейся.
2. Доступ к база данных.
3. Доступ к API. 
API — это набор правил, по которым приложения или части программы общаются друг с другом. 
Например, получение данных с дебитовой карты.

## Перечень и описание возможных рисков при автоматизации.
1. Не доступность сайта.
2. Частое обновление интерфейса.
3. Отсутствие тестируемых меток (нет уникальных элементов).
4. Недостаток документации (может затруднить понимание функциональности приложения).
5. Проблемы с окружением (ошибки при настройке Docker могут повлиять на тестирование).
6. Нестабильность эмуляторов (могут не всегда корректно обрабатывать запросы).
7. Изменения в API (могут привести к необходимости доработки тестов).

## Перечень необходимых специалистов для автоматизации.
1. Инженер по контролю качества информационных продуктов.

## Интервальная оценка с учётом рисков в часах.
1. Техническая часть: 16 часов.
2. Планирование автоматизации: 8 часов.
3. Написание автотестов: 40 часов.
4. Прогон тестов и отладка: 16 часов.
5. Подготовка отчетных документов: 8 часов. 

Всего: 88 часов.

## Отчёт по автоматизации
Отчёт будет оформлен в виде файла Report.md и загружен в корень проекта.