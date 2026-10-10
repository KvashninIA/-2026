//— Пакет исключений.
package ru.arkhipov.mysecondtestappspringboot.exception;

//— Проверяемое исключение (checked exception). Расширяет Exception, поэтому требует обработки через try-catch.
public class ValidationFailedException extends Exception {

    //— Конструктор, передаёт сообщение в родительский класс Exception.
    public ValidationFailedException(String message) { super(message); }

}
