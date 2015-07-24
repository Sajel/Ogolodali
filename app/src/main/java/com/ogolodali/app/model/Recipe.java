package com.ogolodali.app.model;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Recipe {

    private String name;
    private String ref;
    private String cookTime;
    private Difficulty difficulty;
    private int portionCount;
    private boolean compatible;
    private List<String> ingridients = new ArrayList<String>();
    private List<String> canAdd = new ArrayList<String>();
    private List<String> missing;
    private List<String> instructions = new LinkedList<String>();
    //додати картинку

    public Recipe(){}

    public Recipe(Recipe recipe){
        this.name = recipe.getName();
        this.ref = recipe.getRef();
        this.cookTime = recipe.getCookTime();
        this.difficulty = recipe.getDifficulty();
        this.portionCount = recipe.getPortionCount();
        this.ingridients = recipe.getIngridients();
        this.canAdd = recipe.canAdd;
        this.compatible = recipe.compatible;
    }

    public Recipe(String name, String ref, String cookTime, Difficulty difficulty, int portionCount, ArrayList<String> ingridients, ArrayList<String> canAdd, boolean compatible) {
        this.name = name;
        this.ref = ref;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.portionCount = portionCount;
        this.ingridients = ingridients;
        this.canAdd = canAdd;
        this.compatible = compatible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getPortionCount() {
        return portionCount;
    }

    public void setPortionCount(int portionCount) {
        this.portionCount = portionCount;
    }

    public List<String> getIngridients() {
        return ingridients;
    }

    public void setIngridients(List<String> ingridients) {
        this.ingridients = ingridients;
    }

    public List<String> getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(List<String> canAdd) {
        this.canAdd = canAdd;
    }

    public List<String> getMissing() {
        return missing;
    }

    public void setMissing(List<String> missing) {
        this.missing = missing;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }

    @Override
    public String toString() {
        return name + difficulty.getName();   }
}
