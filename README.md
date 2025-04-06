# Практика для закрепления использования наследования в JPA Entities

Разработайте простую иерархию классов моделей: суперкласс `Person` и его наследников: `AdultPerson` и `Child`.

Общий набор атрибутов можете определить на свой вкус. В качестве базового набора предлагаю использовать следующие:

- Имя;
- Дата рождения;
- Пол;
- Адрес проживания.

Для взрослых необходимо добавить специфические атрибуты, которые не характерны для детей:

- Место работы;
- Месячный доход.

Также вы можете добавить любые другие атрибуты, которые посчитаете нужными.

Для детей необходимо также добавить специфические атрибуты:

- Мать (связь с `AdultPerson`);
- Отец (связь с `AdultPerson`).

И, при желании, собственные атрибуты, характерные именно для детей.

Также необходимо реализовать следующую функциональность:

- Создание персоны. Как взрослого, так и ребенка;
- Обновление информации о персоне. Определите список обновляемых атрибутов и дайте возможность их обновлять; 
- Получение всех людей, о которых известно системе;
- Получение только взрослых;
- Получение только детей;
- Получение семьи по id указанной персоны: для детей - получение родителей, всех братьев и сестер (включая сводных). 
  Для взрослых - получение всех детей и родителей противоположного (относительно запрошенной персоны) пола;
- Совершеннолетие. Периодическая задача, срабатывающая раз в сутки, которая будет проверять детей и, при достижении 
  возраста в 18 лет, переводить информацию о них в формат взрослого. Специфические атрибуты `AdultPerson` допустимо 
  оставить пустыми или задать им значение по умолчанию.

Приложение может быть реализовано как сервлетное или консольное, это не имеет решающего значения. Для работы с БД 
необходимо использовать инструмент миграций на ваш выбор, HikariCP в качестве пула соединений, и JPA.

Вне зависимости от типа приложения, необходимо подготовить три варианта реализации со следующей спецификой:

1. Информация обо всех людях должна храниться в одной таблице;
2. Общие атрибуты и информация о наследниках должна храниться в разных таблицах;
3. Информация о каждой сущности должна храниться в отдельной таблице.
