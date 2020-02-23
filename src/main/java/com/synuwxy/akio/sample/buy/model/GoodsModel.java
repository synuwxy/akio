package com.synuwxy.akio.sample.buy.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wxy
 */
@Data
public class GoodsModel implements Serializable {

    private String id;

    private String name;

    private float price;

    public GoodsModel(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
