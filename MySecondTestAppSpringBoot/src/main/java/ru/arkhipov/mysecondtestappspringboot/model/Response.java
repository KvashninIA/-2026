package ru.arkhipov.mysecondtestappspringboot.model;

//— Lombok для геттеров/сеттеров и паттерна Builder.
import lombok.Builder;
import lombok.Data;

@Data
@Builder

//— Класс описывает структуру JSON-ответа сервера.
public class Response {

    //— Копируется из запроса для идентификации.
    private String uid;

    //— Уникальный идентификатор операции.
    private String operationUid;

    //— Время сервера в формате ISO 8601.
    private String systemTime;

    //— Код ответа: "Success" или "Error".
    private String code;

    //— Код ошибки: "ValidationException", "UnsupportedCodeException", "UnknownException" или пустая строка.
    private String errorCode;

    //— Описание ошибки или пустая строка при успехе.
    private String errorMessage;

}
