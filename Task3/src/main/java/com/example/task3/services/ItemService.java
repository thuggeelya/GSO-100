package com.example.task3.services;

import com.example.task3.dto.Item;
import com.example.task3.repositories.ItemRepository;
import com.example.task3.repositories.ProjectRepository;

import java.util.List;

public class ItemService {

    private static final ItemService instance = new ItemService();
    private final ProjectRepository<Item> repository = ItemRepository.getInstance();

    private ItemService() {
    }

    public static ItemService getInstance() {
        return instance;
    }

    public List<Item> getItems() {
        return repository.retrieveAll();
    }

    public boolean addItem(Item item) {
        return repository.add(item);
    }

    public boolean removeItem(int code) {
        return repository.removeById(code);
    }

    public boolean editItem(int oldCode, Item newItem) {
        return repository.editById(oldCode, newItem);
    }

    public Item findByCode(int code) {
        List<Item> items = getItems();

        for (Item i : items) {
            int iCode = i.getCode();

            if (code == iCode) {
                return new Item(iCode, i.getName(), i.getPrice());
            }
        }

        return null;
    }
}