package com.synuwxy.akio.sample.model;

import lombok.Data;

/**
 * @author wxy
 */
@Data
public class StoreModel {

    private String id;

    private String name;

    private int stock;

    public StoreModel(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}
