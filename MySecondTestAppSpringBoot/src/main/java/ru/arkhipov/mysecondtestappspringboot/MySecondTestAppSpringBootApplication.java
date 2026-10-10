//— Пакет приложения.
package ru.arkhipov.mysecondtestappspringboot;

//— Импортируем Spring Boot для запуска и аннотацию для автоматического обнаружения компонентов.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//— Основной класс приложения.
@SpringBootApplication
public class MySecondTestAppSpringBootApplication {

    //— Точка входа в приложение.
	public static void main(String[] args) {

        //— Запуск приложения.
        SpringApplication.run(MySecondTestAppSpringBootApplication.class, args);
	}

}
