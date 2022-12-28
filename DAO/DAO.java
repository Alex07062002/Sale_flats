package org.example.DAO;

import java.util.List;
import java.util.Queue;

public interface DAO<T> {

    List<T> getAll();

    void create(T t);

    /**
     * design pattern "Execute around" для открытия/закрытия ресурсов
     * https://medium.com/@sandeep12.rao/execute-around-design-pattern-cf1f2e38f626
     * https://riptutorial.com/java/example/9855/lambdas-and-execute-around-pattern
     *
    */
    /**
     *
     * Подробнее про wildcards https://javarush.com/groups/posts/2324-wildcards-v-generics
     * неудача!!! Ещё больше костылей
     */
    void update(int id,List<?> params);

    void delete(int id);

}
