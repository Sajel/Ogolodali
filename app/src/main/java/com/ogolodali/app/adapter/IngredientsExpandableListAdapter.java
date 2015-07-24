package com.ogolodali.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ogolodali.app.R;
import com.ogolodali.app.model.*;

import java.util.List;

/**
 * Created by Sajel on 02.07.2015.
 */
public class IngredientsExpandableListAdapter extends BaseExpandableListAdapter {

    private List<IngredientsCategory> categories;

    private LayoutInflater layoutInflater;

    private Search search;

    public IngredientsExpandableListAdapter(Context context, Search search) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        changeData(search);
    };

    public void changeData(Search search) {
        categories = search.getAllIngredients();
        this.search = search;
    }


    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categories.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.category_expandble_list, parent, false);
        }
        IngredientsCategory category = (IngredientsCategory) getGroup(groupPosition);
        TextView ingredientsCategoryName = (TextView) convertView.findViewById(R.id.elementsCategoryName);
        ingredientsCategoryName.setText(category.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_expandble_list, parent, false);
        }
        IngredientsCategory category = (IngredientsCategory) getGroup(groupPosition);
        TextView ingredientName = (TextView) convertView.findViewById(R.id.elementsItemName);
        Ingredient ingredient = category.get(childPosition);
        ingredientName.setText(ingredient.getName());
        if (search.isChosen(ingredient)) {
            convertView.setBackgroundColor(Color.GRAY);
        }
        else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
