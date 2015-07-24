package com.ogolodali.app.model;


import com.ogolodali.app.view.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Search implements Observerable {

    private static Search instance;

    static {instance = new Search();}

    private ArrayList<Observer> observers;

    private Set<Ingredient> chosenIngredients;

    private Set<Tag> chosenTags;

    private String minDuraition;

    private String maxDuration;

    private Difficulty minDiffuclty;

    private Difficulty maxDiffuclty;

    private List<IngredientsCategory> allIngredients;

    private List<TagsCategory> allTags;

    public static Search getInstance() {
        return instance;
    }

    private Search() {
        chosenIngredients = new HashSet<Ingredient>();
        chosenTags = new HashSet<Tag>();
        observers = new ArrayList<Observer>();
    }

    public void choose(Chooseable choice) {
        if(choice instanceof Ingredient) {
            chosenIngredients.add((Ingredient) choice);
        }
        if(choice instanceof Tag) {
            chosenTags.add((Tag) choice);
        }
        notifyObservers();
    }

    public boolean isChosen(Chooseable element) {
        if(element instanceof Ingredient) {
            return chosenIngredients.contains(element);
        }
        if(element instanceof Tag) {
            return chosenTags.contains(element);
        }
        return false;
    }

    public void cancelChoise(Chooseable choice) {
        if(choice instanceof Ingredient) {
            chosenIngredients.remove((Ingredient) choice);
        }
        if(choice instanceof Tag) {
            chosenTags.remove((Tag) choice);
        }
        notifyObservers();
    }

    public Set<Ingredient> getChosenIngredients() {
        return chosenIngredients;
    }

    public Set<Tag> getChosenTags() {
        return chosenTags;
    }

    public String getMinDuraition() {
        return minDuraition;
    }

    public void setMinDuraition(String minDuraition) {
        this.minDuraition = minDuraition;
    }

    public String getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(String maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Difficulty getMinDiffuclty() {
        return minDiffuclty;
    }

    public void setMinDiffuclty(Difficulty minDiffuclty) {
        this.minDiffuclty = minDiffuclty;
    }

    public Difficulty getMaxDiffuclty() {
        return maxDiffuclty;
    }

    public void setMaxDiffuclty(Difficulty maxDiffuclty) {
        this.maxDiffuclty = maxDiffuclty;
    }

    public List<IngredientsCategory> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(List<IngredientsCategory> allIngredients) {
        this.allIngredients = allIngredients;
    }

    public List<TagsCategory> getAllTags() {
        return allTags;
    }

    public void setAllTags(List<TagsCategory> allTags) {
        this.allTags = allTags;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }


}
