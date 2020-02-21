package com.synuwxy.akio.sample.model;

import lombok.Data;

@Data
public class UserModel {

    private String id;

    private String name;

    private int stock;

    private float money;

    public UserModel(String id, String name, int stock, float money) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.money = money;
    }
}
