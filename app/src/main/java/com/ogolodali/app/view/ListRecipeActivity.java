package com.ogolodali.app.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ogolodali.app.AsynkTasks.ParseListAsyncTask;
import com.ogolodali.app.R;
import com.ogolodali.app.adapter.ListWithCategoryAdapter.ListRecipeAdapter;
import com.ogolodali.app.model.Recipe;

import java.util.List;
import java.util.Objects;


public class ListRecipeActivity extends Activity {

    private ListView listRecipes;

    private List<Recipe> compRecipe;
    private List<Recipe> uncompRecipe;

    private ListRecipeAdapter adComp;
    private ParseListAsyncTask parseTask;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listRecipes = (ListView) findViewById(R.id.recipes);
        listRecipes.setAdapter(new ListRecipeAdapter(this, R.layout.category_item, R.layout.simple_item));
        //отримувати url з попередньоъ актівіті
        Intent intent = getIntent();
        String url = "http://ogoloda.li/search/show?q=&home=false&i=_6_331_35_14_104_97_231";
        parseTask = (ParseListAsyncTask) getLastNonConfigurationInstance();
        if(parseTask == null) {
            parseTask = new ParseListAsyncTask(this);
            parseTask.execute(url);
        }
        Log.d("AsynkTask","start activity");
    }

    public Object onRetainNonConfigurationInctance(){
        return parseTask;
    }

    public void fail() {
        Log.d("MyLogs", "херово");
    }

    public List<Recipe> getCompRecipe() {
        return compRecipe;
    }

    public List<Recipe> getUncompRecipe() {
        return uncompRecipe;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ListView getListRecipes() {
        return listRecipes;
    }

    public void downloadMore(View view) {
        //додати нові рецепти в список
    }
}
