package ru.job4j.cars;

import java.util.ArrayList;

/**
 * Интерфейс определяющий основные методы для хранилища
 * @param <K> класс объекта хранения
 */
public interface Store<K> {

    int add(K model);

    boolean update(K model);

    boolean delete(int id);

    ArrayList<K> findAll();

    K findById(int id);

    int findIdByModel(K model);
}
