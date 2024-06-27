package org.micro.apps.data.service;

import java.util.List;

/**
 * @author 207345
 */
public interface iCrudService<T> {

    T create(T object);
    T update(T object);
    T get(long id);
    List<T> getAll();

    void delete(long id);
}
