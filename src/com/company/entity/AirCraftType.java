package com.company.entity;

/**
 * Created by Marius on 3/11/2017.
 */
public enum AirCraftType {

    BALOON("Baloon"), HELICOPTER("Helicopter"), JETPLANE("JetPlane");

    private String type;

    AirCraftType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
