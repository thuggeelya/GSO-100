package com.example.task3.services;

import com.example.task3.dto.Item;
import com.example.task3.repositories.ItemRepository;

import java.util.List;

public class ItemService {

    private static final ItemService instance = new ItemService(ItemRepository.getInstance());

    public static ItemService getInstance() {
        return instance;
    }

    public ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getItems() {
        return repository.retrieveAll();
    }

    public boolean addItem(Item item) {
        return repository.add(item);
    }

    public boolean removeItem(Item item) {
        return repository.remove(item);
    }

    public boolean editItem(Item oldItem, Item newItem) {
        return repository.edit(oldItem, newItem);
    }
}