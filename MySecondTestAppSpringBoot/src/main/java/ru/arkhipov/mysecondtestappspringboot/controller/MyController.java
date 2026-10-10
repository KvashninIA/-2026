package ru.arkhipov.mysecondtestappspringboot.controller;

import ru.arkhipov.mysecondtestappspringboot.exception.UnsupportedCodeException;
import ru.arkhipov.mysecondtestappspringboot.exception.ValidationFailedException;
import ru.arkhipov.mysecondtestappspringboot.model.Request;
import ru.arkhipov.mysecondtestappspringboot.model.Response;
import ru.arkhipov.mysecondtestappspringboot.service.ValidationService;
//Аннотации для включения валидации объекта Request
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//— HTTP-статусы и обёртка для HTTP-ответа.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//— Аннотации для REST-контроллера.
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//— Для форматирования даты.
import java.text.SimpleDateFormat;
import java.util.Date;

//— Аннотация, означающая, что все методы этого класса возвращают JSON (не view-шаблоны).
@RestController
public class MyController {

    //— Поле для сервиса валидации. final — внедряется один раз при создании контроллера.
    private final ValidationService validationService;
    //— Форматтер даты.
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //— Конструктор для внедрения сервиса валидации.
    @Autowired
    //— Конструктор для внедрения сервиса валидации.
    public MyController(ValidationService validationService) {
        this.validationService = validationService;
    }
    //— Метод для обработки POST-запроса.
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        //— Создание объекта
        Response response = Response.builder()
                //— Уникальный идентификатор запроса
                .uid(request.getUid())
                //— Дата и время в формате "yyyy-MM-dd HH:mm:
                .operationUid(request.getOperationUid())
                //— Текущая дата и время в формате "yyyy-MM-dd
                .systemTime(simpleDateFormat.format(new Date()))
                //— Код ответа
                .code("success")
                //— Код ошибки
                .errorCode("")
                //— Сообщение об ошибке
                .errorMessage("")
                //— Объект с данными
                .build();
        //— Обработка ошибок
        try {
            //— Проверка
            if ("123".equals(request.getUid())) {
                //— Выбрасывание исключения
                throw new UnsupportedCodeException("Неподдерживаемый код uid: 123");
            }
            //— Выбрасывание исключения
        } catch (UnsupportedCodeException e) {
            //— Установка кода ответа
            response.setCode("failed");
            //— Установка кода ошибки
            response.setErrorCode("UnsupportedCodeException");
            //— Установка сообщения об ошибке
            response.setErrorMessage(e.getMessage());
            //— Возвращение ответа
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //— Возвращение ответа
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}