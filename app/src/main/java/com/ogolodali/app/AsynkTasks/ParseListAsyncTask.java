package com.ogolodali.app.AsynkTasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.ogolodali.app.adapter.ListWithCategoryAdapter.ListItem;
import com.ogolodali.app.adapter.ListWithCategoryAdapter.ListRecipeAdapter;
import com.ogolodali.app.model.Recipe;
import com.ogolodali.app.parser.ResultParser;
import com.ogolodali.app.view.ListRecipeActivity;

import java.io.IOException;
import java.util.List;

public class ParseListAsyncTask extends AsyncTask<String, Recipe, Void> {

    private boolean failed = false;
    private ResultParser parser = new ResultParser(this);

    ListRecipeActivity activity;

    public ParseListAsyncTask(ListRecipeActivity activity) {
        this.activity = activity;
    }

    void setActivity(ListRecipeActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(String... url) {
        Log.i("MyLogs", "startTask");
        try {
            parser.parseList(url[0]);
        } catch (IOException e) {
            e.printStackTrace();
            failed = true;
        }
        Log.i("MyLogs", "норм чо");
        return null;
    }

    @Override
    protected void onProgressUpdate(Recipe... recipes){
        super.onProgressUpdate(recipes);
        ListRecipeAdapter adapter = (ListRecipeAdapter)activity.getListRecipes().getAdapter();
        for(Recipe recipe : recipes) {
            if (recipe.isCompatible()) {
                activity.getCompRecipe().add(recipe);
                adapter.add(new ListItem(recipe),
                        ListRecipeAdapter.COMP_ITEM);
                Log.d("MyLogs", "едрашит compartible");
            } else {
                activity.getUncompRecipe().add(recipe);
                adapter.add(new ListItem(recipe),
                        ListRecipeAdapter.UNCOMP_ITEM);
                Log.d("MyLogs", "едрашит uncompartible");
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (failed) {
            activity.fail();
        }
        activity.getProgressBar().setVisibility(View.INVISIBLE);
        Log.d("MyLogs", "все");
    }

    public void comeElement(Recipe[] recipes){
        publishProgress(recipes);
        Log.d("MyLogs", "новый елемент списка");
    }

    public ResultParser getParser() {
        return parser;
    }
}
