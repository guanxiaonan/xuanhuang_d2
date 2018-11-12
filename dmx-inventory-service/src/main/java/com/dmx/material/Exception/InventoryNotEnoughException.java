package com.dmx.material.Exception;

public class InventoryNotEnoughException extends RuntimeException {

    public InventoryNotEnoughException() {
        super("库存不足！！！");
    }
}
