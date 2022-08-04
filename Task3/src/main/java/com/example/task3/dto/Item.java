package com.example.task3.dto;

import java.util.Objects;

public class Item {

    private int code;
    private String name;
    private int price;

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

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
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