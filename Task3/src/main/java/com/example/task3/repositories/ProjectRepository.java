package com.example.task3.repositories;

import java.util.List;

public interface ProjectRepository<T> {

    List<T> retrieveAll();
    boolean add(T t);
    boolean removeById(int id);
    boolean editById(int oldId, T newT);
}