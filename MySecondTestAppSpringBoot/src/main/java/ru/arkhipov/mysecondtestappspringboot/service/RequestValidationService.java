package ru.arkhipov.mysecondtestappspringboot.service;

import ru.arkhipov.mysecondtestappspringboot.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

//— @Service — аннотация, Spring автоматически создаст бин этого класса.
@Service
//— Класс реализует интерфейс ValidationService. Spring создаст его экземпляр и внедрит в контроллер.
public class RequestValidationService implements ValidationService {

    @Override
    //— Реализация метода валидации. @Override — переопределяем метод интерфейса.
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        //— Проверяем, есть ли ошибки валидации от Spring (аннотации @NotBlank, @Size и т.д.).
        if (bindingResult.hasErrors()) {
            //— Создаём объект для сборки строки со всеми ошибками.
            StringBuilder errors = new StringBuilder();
            //— Проходим по всем ошибкам полей:
            //
            //getFieldErrors() — список всех ошибок валидации
            //error.getField() — имя поля (например, uid)
            //error.getDefaultMessage() — сообщение об ошибке (например, must not be blank)
            //Собираем всё в строку: uid: must not be blank; operationUid: must not be blank;
            bindingResult.getFieldErrors().forEach(error ->
                    errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            //— Выбрасываем исключение с сообщением об ошибках.
            throw new ValidationFailedException(errors.toString());

        }
    }
}
