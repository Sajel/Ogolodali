package com.ogolodali.app.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import com.ogolodali.app.R;
import com.ogolodali.app.adapter.IngredientsExpandableListAdapter;
import com.ogolodali.app.model.*;

import java.util.Set;

public class IngredientsFragment extends Fragment implements Observer, ExpandableListView.OnChildClickListener {

    private static final String LOG_TAG = "Search Activity";

    private Search search;

    private ExpandableListView ingredientsExpandableListView;

    private IngredientsExpandableListAdapter ingredientsExpandableListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search = Search.getInstance();
        search.registerObserver(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredientsExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.ingredientsExpandableListView);
        ingredientsExpandableListAdapter = new IngredientsExpandableListAdapter(getActivity(), search);
        ingredientsExpandableListView.setAdapter(ingredientsExpandableListAdapter);
        ingredientsExpandableListView.setOnChildClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        return rootView;
    }

    @Override
    public void update(Search search) {
        ingredientsExpandableListAdapter.changeData(search);
        ingredientsExpandableListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Chooseable ingredient = (Chooseable) ingredientsExpandableListAdapter.getChild(groupPosition, childPosition);
        if (search.isChosen(ingredient)) {
            search.cancelChoise(ingredient);
        }   else {
            search.choose(ingredient);
        }
        return true;
    }
}
