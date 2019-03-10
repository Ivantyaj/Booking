package com.booking.service.iface;

import java.util.List;

public interface BaseService<T, M> {
    void create(T object);

    void update(M id);

    void delete(M id);

    void get(M id);

    List<T> getAll();
}
