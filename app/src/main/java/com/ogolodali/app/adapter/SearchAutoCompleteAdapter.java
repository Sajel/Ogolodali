package com.ogolodali.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.ogolodali.app.R;
import com.ogolodali.app.model.Chooseable;
import com.ogolodali.app.model.Ingredient;
import com.ogolodali.app.model.IngredientsCategory;
import com.ogolodali.app.model.Search;
import com.ogolodali.app.model.Tag;
import com.ogolodali.app.model.TagsCategory;

import java.util.ArrayList;

/**
 * Created by Sajel on 01.07.2015.
 */
public class SearchAutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 5;

    private Search search;
    private LayoutInflater layoutInflater;

    private ArrayList<Chooseable> suggestions = new ArrayList<Chooseable>();
    private ArrayList<Chooseable> filteredList = new ArrayList<Chooseable>();

    public SearchAutoCompleteAdapter(Context context, Search search) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.search = search;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.autocomplete_dropdown, parent, false);
        }
        Chooseable element = (Chooseable) getItem(position);
        ((TextView) convertView.findViewById(R.id.autoCompleteNameTextView)).setText(element.getName());
        TextView typeTextView = (TextView) convertView.findViewById(R.id.autoCompleteIdTextView);
        if (element instanceof Ingredient) {
            typeTextView.setText("Ингредиент");
        }
        if (element instanceof Tag) {
            typeTextView.setText("Категория");
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    suggestions.clear();
                    for (IngredientsCategory category : search.getAllIngredients()) {
                        for (Ingredient ingredient : category) {
                            if (ingredient.getName().toLowerCase()
                                    .startsWith(constraint.toString().toLowerCase())) {
                                if (!suggestions.contains(ingredient)&& !search.isChosen(ingredient)) {
                                    suggestions.add(ingredient);
                                }
                            }
                        }

                    }
                    for (TagsCategory category : search.getAllTags()) {
                        for (Tag tag : category) {
                            if (tag.getName().toLowerCase()
                                    .startsWith(constraint.toString().toLowerCase())) {
                                if (!suggestions.contains(tag) && !search.isChosen(tag)) {
                                    suggestions.add(tag);
                                }
                            }
                        }

                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    filteredList = (ArrayList<Chooseable>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
