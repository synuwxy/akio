package com.synuwxy.akio.sample.buy.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wxy
 */
@Data
public class StoreModel implements Serializable {

    private String id;

    private String name;

    private int stock;

    public StoreModel(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}
