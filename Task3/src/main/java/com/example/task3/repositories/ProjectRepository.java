package com.example.task3.repositories;

import java.util.List;

public interface ProjectRepository<T> {

    List<T> retrieveAll();
    boolean add(T t);
    boolean remove(T t);
    boolean edit(T oldT, T newT);
}