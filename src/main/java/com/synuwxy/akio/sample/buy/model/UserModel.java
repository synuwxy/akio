package com.synuwxy.akio.sample.buy.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wxy
 */
@Data
public class UserModel implements Serializable {

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
