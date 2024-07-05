### Hexlet tests and linter status:
[![Actions Status](https://github.com/EugenePTCDA/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/EugenePTCDA/java-project-78/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/a8e7a50f533f4aa89b67/maintainability)](https://codeclimate.com/github/EugenePTCDA/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/a8e7a50f533f4aa89b67/test_coverage)](https://codeclimate.com/github/EugenePTCDA/java-project-78/test_coverage)

# Валидатор данных

## Цель

Проект **Валидатор данных** направлен на развитие навыков проектирования архитектуры в объектно-ориентированном стиле.

## Описание

**Валидатор данных** – это библиотека, которая позволяет проверять корректность любых данных. 
Такие библиотеки широко распространены в каждом языке программирования, так как практически все программы 
работают с внешними данными, которые необходимо проверять на корректность. 
В первую очередь речь идет о данных, заполняемых пользователями в формах. 
В качестве основы для проекта взята библиотека [yup](https://github.com/jquense/yup).

## Возможности:

### 1) NumberSchema

Метод `number()` определяет схему `NumberSchema`, которая используется для валидации чисел. Схема содержит следующий набор методов:

#### Методы NumberSchema:

- **required()**
    - Добавляет в схему ограничение, которое не позволяет использовать `null` в качестве значения.

- **positive()**
    - Добавляет ограничение на знак числа. Число должно быть положительным.

- **range(int min, int max)**
    - Добавляет допустимый диапазон, в который должно попадать значение числа, включая границы.

### 2) StringSchema

Метод `string()` создает схему `StringSchema`, которая используется для валидации строковых данных. После создания схемы можно конфигурировать ее, добавляя различные ограничения при помощи вызова методов на объекте `StringSchema`.

#### Методы StringSchema:

- **required()**
    - Делает данные обязательными для заполнения. Иными словами, добавляет в схему ограничение, которое не позволяет использовать `null` или пустую строку в качестве значения.

- **minLength(int length)**
    - Добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа.

- **contains(String substring)**
    - Добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку.

### 3) MapSchema

Метод `map()` определяет схему `MapSchema`, которая используется для валидации объектов типа `Map`.

#### Методы MapSchema:

- **required()**
    - Добавляет в схему ограничение, которое не позволяет использовать `null` в качестве значения. Требуется тип данных `Map`.

- **sizeof(int size)**
    - Добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте `Map` должно быть равно заданному.
- **shape()**
    - Метод используется для определения свойств объекта `Map` и создания схемы для валидации их значений. Каждому свойству объекта `Map` привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные.
## Пример использования

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

public class Example {
    public static void main(String[] args) {
        Validator v = new Validator();

        // Проверка строк
        StringSchema schema = v.string().required();

        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("")); // false

        // Проверка чисел
        NumberSchema numberSchema = v.number().required().positive();

        System.out.println(numberSchema.isValid(-10)); // false
        System.out.println(numberSchema.isValid(10)); // true

        // Проверка объектов Map с поддержкой проверки структуры
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        MapSchema mapSchema = v.map().sizeof(2).shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        System.out.println(mapSchema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", null);
        System.out.println(mapSchema.isValid(human2)); // false
    }
}