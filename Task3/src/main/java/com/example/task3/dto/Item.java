package com.example.task3.dto;

import java.util.Objects;

public class Item {

    private final int code;
    private final String name;
    private final int price;

    public Item(int code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return code == item.code && price == item.price && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode=" + code +
                ", itemName='" + name + '\'' +
                ", itemPrice=" + price +
                '}';
    }
}