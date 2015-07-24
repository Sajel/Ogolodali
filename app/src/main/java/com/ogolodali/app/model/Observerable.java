package com.ogolodali.app.model;


import com.ogolodali.app.view.Observer;

/**
 * Created by Sajel on 01.07.2015.
 */
public interface Observerable {
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();
}