//Определяет принадлежность класса к пакету hello. Это важно для структуры проекта Spring Boot.
package hello;
//Аннотации из org.springframework.web.bind.annotation нужны для обработки HTTP‑запросов:
//Помечает метод как обработчик GET‑запросов для конкретного URL.
import org.springframework.web.bind.annotation.GetMapping;
//Извлекает параметры из URL (например, ?s=value).
import org.springframework.web.bind.annotation.RequestParam;
//Говорит Spring, что этот класс — REST‑контроллер: все его методы возвращают данные (JSON или текст), а не HTML‑страницы.
import org.springframework.web.bind.annotation.RestController;
//Импорты java.util.* подключают стандартные коллекции Java: ArrayList, HashMap, интерфейс List, Map.
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

//Класс‑контроллер.
@RestController
public class HelloController {

    //Статическое поле для хранения списка строк.
    //Оно общее для всех запросов к приложению (сохраняет состояние между вызовами). Изначально null, чтобы реализовать ленивую инициализацию (создаётся при первом использовании).
    private static List<String> arrayList = null;
    //Аналогично, статическое поле для HashMap, где ключ — Integer, значение — String.
    private static Map<Integer, String> hashMap = null;

    //Обрабатывает запрос по адресу /hello.
    //@RequestParam извлекает параметр name из URL. Если его нет, используется значение по умолчанию — "World".
    //Возвращает строку вида "Hello [name]!".
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    //Задание 1
    //Обрабатывает запрос по адресу /update-array.
    //Принимает параметр s (строку для добавления).
    //Если arrayList ещё не создан (null), инициализирует его.
    //Добавляет строку в список и возвращает подтверждение с текущим размером.
    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam("s") String s) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(s);
        return "Добавлено: " + s + ". Текущий размер списка: " + arrayList.size();
    }
    //Задание 2
    //Обрабатывает запрос по адресу /show-array.
    //Проверяет, существует ли список и не пуст ли он.
    //Если список пуст — возвращает сообщение "Список пуст".
    //Иначе возвращает строковое представление списка (например, [apple, banana]).
    @GetMapping("/show-array")
    public String showArrayList() {
        if (arrayList == null || arrayList.isEmpty()) {
            return "Список пуст";
        }
        return arrayList.toString();
    }
    //Задание 3
    //Обрабатывает запрос по адресу /update-map.
    //Принимает параметр s (строку для добавления).
    //Если hashMap ещё не создан, инициализирует его.
    //Использует текущий размер карты как ключ (0, 1, 2, …) — это гарантирует уникальность ключей.
    //Добавляет пару «ключ‑значение» и возвращает подтверждение.
    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam("s") String s) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        int key = hashMap.size();
        hashMap.put(key, s);
        return "Добавлено в HashMap: ключ = " + key + ", значение = " + s + ". Текущий размер: " + hashMap.size();
    }
    //Задание 4
    //Обрабатывает запрос по адресу /show-map.
    //Проверяет, существует ли карта и не пуста ли она.
    //Если пуста — возвращает "HashMap пуст".
    //Иначе возвращает строковое представление карты (например, {0=red, 1=blue}).
    @GetMapping("/show-map")
    public String showHashMap() {
        if (hashMap == null || hashMap.isEmpty()) {
            return "HashMap пуст";
        }
        return hashMap.toString();
    }
    //Задание 5
    //Обрабатывает запрос по адресу /show-all-lenght.
    //Безопасно вычисляет размеры обеих коллекций (учитывая, что они могут быть null).
    //Возвращает строку с количеством элементов в каждой коллекции.
    @GetMapping("/show-all-length")
    public String showAllLength() {
        int listSize = (arrayList == null) ? 0 : arrayList.size();
        int mapSize = (hashMap == null) ? 0 : hashMap.size();
        return "Количество элементов в ArrayList: " + listSize + ", в HashMap: " + mapSize;
    }
}
