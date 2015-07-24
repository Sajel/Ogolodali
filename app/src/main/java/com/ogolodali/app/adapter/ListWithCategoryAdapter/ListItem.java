package com.ogolodali.app.adapter.ListWithCategoryAdapter;

import com.ogolodali.app.model.Recipe;

public class ListItem {

    private Recipe recipe;
    private String title;
    private boolean isCategoryItem;

    public ListItem(Recipe recipe) {
        this.recipe = recipe;
        this.isCategoryItem = false;
    }

    public ListItem(String title) {
        this.title = title;
        this.isCategoryItem = true;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean isCategoryItem() {
        return isCategoryItem;
    }

    public void setIsCategoryItem(boolean isCategoryItem) {
        this.isCategoryItem = isCategoryItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
