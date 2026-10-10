//— Пакет моделей данных.
package ru.arkhipov.mysecondtestappspringboot.model;

//— Импорты.
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//— Класс модели данных.
//@Data — геттеры, сеттеры, toString(), equals(), hashCode()
//@Builder — паттерн Builder для создания объекта
//@NoArgsConstructor — конструктор без параметров (нужен Jackson для JSON)
//@AllArgsConstructor — конструктор со всеми параметрами
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//— Класс описывает структуру входящего JSON-запроса.
public class Request {

    @NotBlank
    @Size(max = 32)
    private String uid;

    //— Поля.
    @NotBlank
    @Size(max = 32)
    private String operationUid;
    //— Имя системы-отправителя. Необязательное поле, без валидации.
    private String systemName;

    @NotBlank
    //— Время создания сообщения. Обязательное поле, не может быть пустым.
    private String systemTime;
    //— Наименование ресурса. Необязательное поле.
    private String source;

    @Min(1)
    @Max(100000)
    private Integer communicationId;
    private Integer templateId;
    private Integer productCode;
    private Integer smsCode;

}
