package com.company.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marius on 3/8/2017.
 */
public abstract class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    protected Map<String, Coordinates> moves = new HashMap<>();

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
        initializeMoves();
    }

    private long nextId(){
        return ++idCounter;
    }

    protected void changeCoordinates(int d_long, int d_lat, int d_h){
        coordinates.addToCoordinates(d_long, d_lat, d_h);
    }

    protected boolean isLanding(){
        if (coordinates.getHeight() == 0){
            return true;
        }
        return false;
    }

    protected abstract void initializeMoves();

    @Override
    public String toString() {
        return "#" + name + "(" + id + ")";
    }
}
