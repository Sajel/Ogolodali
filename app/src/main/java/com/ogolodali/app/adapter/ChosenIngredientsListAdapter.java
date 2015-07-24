package com.ogolodali.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogolodali.app.R;
import com.ogolodali.app.model.Ingredient;

import java.util.ArrayList;
import java.util.Set;


/**
 * Created by Sajel on 01.07.2015.
 */
public class ChosenIngredientsListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public ChosenIngredientsListAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void changeData(Set<Ingredient> ingredients) {
        this.ingredients.clear();
        this.ingredients.addAll(ingredients);
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }


    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.chosen_ingredients_list, parent, false);
        }
        Ingredient ingredient = (Ingredient) getItem(position);
        ((TextView) convertView.findViewById(R.id.chosenIngredientTextView)).setText(ingredient.getName());

        return convertView;
    }
}
