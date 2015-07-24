package com.ogolodali.app.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import com.ogolodali.app.R;
import com.ogolodali.app.adapter.TagsExpandableListAdapter;
import com.ogolodali.app.model.Chooseable;
import com.ogolodali.app.model.Ingredient;
import com.ogolodali.app.model.Search;
import com.ogolodali.app.model.Tag;

import java.util.Set;

public class TagsFragment extends Fragment implements Observer, ExpandableListView.OnChildClickListener {

    private static final String LOG_TAG = "Search Activity";

    private Search search;

    private ExpandableListView tagsExpandableListView;

    private TagsExpandableListAdapter tagsExpandableListViewAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search = Search.getInstance();
        search.registerObserver(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagsExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.tagsExpandableListView);
        tagsExpandableListViewAdapter = new TagsExpandableListAdapter(getActivity(), search);
        tagsExpandableListView.setAdapter(tagsExpandableListViewAdapter);
        tagsExpandableListView.setOnChildClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tags, container, false);
        return rootView;
    }

    @Override
    public void update(Search search) {
        tagsExpandableListViewAdapter.changeData(search);
        tagsExpandableListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Chooseable tag = (Chooseable) tagsExpandableListViewAdapter.getChild(groupPosition, childPosition);
        if (search.isChosen(tag)) {
            search.cancelChoise(tag);
        }   else {
            search.choose(tag);
        }
        return true;
    }
}
