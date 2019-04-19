package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Класс тестирует основные действия с сущностью объявления о продаже автомобиля
 * В качестве обязательных параметров оставлены: текст объявления, пользователь,
 * город нахождения автомобиля, марка и модель авто
 */
public class DBStoreTest {
    private CarsDbStore carsdb = new CarsDbStore();

    @Test
    /**
     * Метод проверяет добавление сущности в хранилище
     */
    public void addEntityTest() {
        CarEntity car = new CarEntity("test");
        int idx = carsdb.add(car);
        car = carsdb.findById(idx);
        assertThat(car.getNote(), is("test"));
    }

    @Test
    /**
     * Метод проверяет изменение сущности в хранилище
     */
    public void updateEntityTest() {
        CarEntity car = new CarEntity("test");
        int idx = carsdb.add(car);
        car.setNote("test3");
        carsdb.update(car);
        car = carsdb.findById(idx);
        assertThat(car.getNote(), is("test3"));
    }

    @Test
    /**
     * Метод проверяет удаление сущности из хранилище
     */
    public void deleteEntityTest() {
        CarEntity car = new CarEntity("test");
        int idx = carsdb.add(car);
        carsdb.delete(idx);
        assertThat(carsdb.findById(idx), is(nullValue()));
    }

    @Test
    /**
     * Метод проверяет поиск всех сущностей
     */
    public void findAllEntityTest() {
        CarEntity car = new CarEntity("test");
        CarEntity car2 = new CarEntity("note2");
        carsdb.add(car);
        carsdb.add(car2);
        ArrayList<CarEntity> result = carsdb.findAll();
        assertThat(result.size(), is(2));
    }

    @Test
    /**
     * Метод проверяет поиск сущности по ее id
     */
    public void findByIdEntityTest() {
        CarEntity car = new CarEntity("test");
        int idx = carsdb.add(car);
        CarEntity car2 = carsdb.findById(idx);
        assertThat(car2, is(car));
    }

    @Test
    /**
     * Метод проверяет поиск id сущности по самой сущности
     */
    public void findByModekEntityTest() {
        CarEntity car = new CarEntity("test");
        int idx = carsdb.add(car);
        assertThat(carsdb.findIdByModel(car), is(idx));
    }

    @Test
    /**
     * Метод проверяет получение имени сущности
     */
    public void getEntityNameTest() {
        assertThat(carsdb.getEntityname(), is("CarEntity"));
    }

    @Test
    /**
     * Метод проверяет использование фильтра списка объявлений
     */
    public void findAllCarsWithFiterTest() {
        CarEntity car = new CarEntity("test");
        Store<MarkEntity> markdb = new DbStore<>(MarkEntity.class);
        markdb.add(new MarkEntity("djdjdjdjd"));
        car.setMark(markdb.findById(1));
        carsdb.add(car);
        carsdb.add(new CarEntity("car2"));
        carsdb.add(new CarEntity("car3"));
        List<String> filters = Arrays.asList("selectmark", "djdjdjdjd");
        List<CarEntity> result = carsdb.findAllCarsWithFilter(filters);
        assertThat(result.size(), is(1));
    }

    @After
    public void afterDo() {
        List<CarEntity> all = carsdb.findAll();
        for (CarEntity c: all) {
            carsdb.delete(c.getId());
        }
    }
}