package com.synuwxy.akio.sample.model;

import lombok.Data;

/**
 * @author wxy
 */
@Data
public class GoodsModel {

    private String id;

    private String name;

    private float price;

    public GoodsModel(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
