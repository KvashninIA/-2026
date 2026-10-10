package ru.arkhipov.mysecondtestappspringboot.service;

import ru.arkhipov.mysecondtestappspringboot.exception.ValidationFailedException;
import org.springframework.validation.BindingResult;

//— Интерфейс определяет контракт для валидации.
public interface ValidationService {

    //— Метод принимает объект BindingResult и выбрасывает исключение Validation, если есть ошибки.
    void isValid(BindingResult bindingResult) throws ValidationFailedException;

}
