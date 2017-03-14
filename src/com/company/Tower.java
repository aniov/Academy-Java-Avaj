package com.company;

import com.company.filehandler.FileManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Marius on 3/8/2017.
 */
public abstract class Tower {

    private List<Flyable> observers = new CopyOnWriteArrayList<>();

    public void register(Flyable flyable){
        observers.add(flyable);
        FileManager.addMessage("Tower says: " + flyable + " registered to weather tower.");
    }

    public void unregister(Flyable flyable){
        FileManager.addMessage("Tower says: " + flyable + " unregistered from weather tower.");
        observers.remove(flyable);
    }

    /** Notify all observers*/
    protected void conditionsChanged(){
        for (Flyable observer : observers){
            observer.updateConditions();
        }
    }

}
