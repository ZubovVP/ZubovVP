package ru.job4j.serialization.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.01.2021.
 */
public class Car {
    private final String name;
    private final int version;
    private final boolean exists;
    private final Engine engine;
    private final String[] owners;

    public Car(String name, int version, boolean exists, Engine engine, String... owners) {
        this.name = name;
        this.version = version;
        this.exists = exists;
        this.engine = engine;
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public boolean isExsist() {
        return exists;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getOwners() {
        return owners;
    }

    @Override
    public String toString() {
        return "Car{" + "name='" + name + '\'' + ", version=" + version + ", exists=" + exists + ", engine=" + engine + ", owners=" + Arrays.toString(owners) + '}';
    }

    public static class Engine {
        private final String name;
        private final int version;

        public Engine(String name, int version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public int getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "Engine{" + "name='" + name + '\'' + ", version=" + version + '}';
        }
    }

    public static void main(String[] args) {
        Car car = new Car("Volvo", 3, Boolean.TRUE, new Engine("V6", 2), "Owner_1", "Owner_2", "Owner_3");

        /* Используем Gson*/
        /* Преобразуем объект person в json-строку. Используем Gson*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /* Модифицируем json-строку */
        final String personJson =
                "{" + "\"name\":Volvo," + "\"version\":3," + "\"exists\":true," + "\"engine\":" + "{" + "\"name\":V6," + "\"version\":2" + "}," + "\"owners\":" + "[\"Owner_1\",\"Owner_2\", \"Owner_3\"]" + "}";
        final Car personMod = gson.fromJson(personJson, Car.class);
        System.out.println(personMod);


        /* Используем Json*/
        /* Для удаления зацыкливания используется аннотонация @JSONPropertyIgnore */
        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"name\":\"V6\", \"version\":\"2\"}");

        /* JSONArray из  массива(коллекции)*/
        String[] owners = new String[]{"Owner_1", "Owner_2", "Owner_3"};
        JSONArray jsonOwners = new JSONArray(owners);

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", car.getName());
        jsonObject.put("version", car.getVersion());
        jsonObject.put("exist", car.isExsist());
        jsonObject.put("engine", jsonEngine);
        /* Можно добавлять сразу массив*/
        //jsonObject.put("owners", owners);
        jsonObject.put("owners", jsonOwners);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
