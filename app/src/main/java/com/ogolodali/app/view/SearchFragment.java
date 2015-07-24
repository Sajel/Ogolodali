package com.ogolodali.app.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ogolodali.app.R;
import com.ogolodali.app.adapter.ChosenIngredientsListAdapter;
import com.ogolodali.app.adapter.ChosenTagsListAdapter;
import com.ogolodali.app.adapter.SearchAutoCompleteAdapter;
import com.ogolodali.app.model.*;
import com.ogolodali.app.parser.SearchParser;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class SearchFragment extends Fragment implements Observer {

    private Search search;

    private TextView cookingTimeBoundsTextView;
    private TextView difficultyBoundsTextView;

    private ListView chosenIngredientsList;
    private ListView chosenTagsList;

    @Override
    public void onResume() {
        super.onResume();
        update(search);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search = Search.getInstance();
        search.registerObserver(this);

        ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.searchLayout);

        cookingTimeBoundsTextView = (TextView) getActivity().findViewById(R.id.cookingTimeBoundsTextView);
        cookingTimeBoundsTextView.setText(R.string.cookingTimeBounds);

        difficultyBoundsTextView = (TextView) getActivity().findViewById(R.id.difficultyBoundsTextView);
        difficultyBoundsTextView.setText(R.string.diffuclyBounds);



        AutoCompleteTextView searchTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.searchTextView);
        searchTextView.setHint(R.string.searchAutoCompleteHint);
        SearchAutoCompleteAdapter adapter = new SearchAutoCompleteAdapter(getActivity(), search);
        searchTextView.setAdapter(adapter);

        searchTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search.choose((Chooseable) parent.getItemAtPosition(position));
            }
        });

        //Создание и размещение SeekBar'а для настройки времени приготовления
        RangeSeekBar<Integer> cookingTimeSeekBar = new RangeSeekBar<Integer>(0, 25, getActivity());
        cookingTimeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                String min = String.valueOf(minValue * 5);
                String max = String.valueOf(maxValue * 5);
                if(maxValue.equals(bar.getAbsoluteMaxValue())) {
                    max = "9000";
                }
                search.setMinDuraition(min);
                search.setMaxDuration(max);
                cookingTimeBoundsTextView.setText("от " + min + " до " + max + " мин.");
            }
        });


        layout.addView(cookingTimeSeekBar);

        RelativeLayout.LayoutParams cookingTimeSeekBarLayoutParams = (RelativeLayout.LayoutParams) cookingTimeSeekBar.getLayoutParams();
        cookingTimeSeekBarLayoutParams.addRule(RelativeLayout.BELOW, R.id.cookingTimeBoundsTextView);
        cookingTimeSeekBarLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.cookingTimeTextView);
        cookingTimeSeekBarLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;

        //Создание и размещение SeekBar'а для настройки сложности
        final Difficulty[] difficulties = Difficulty.values();
        RangeSeekBar<Integer> difficlutySeekBar = new RangeSeekBar<Integer>(0, difficulties.length - 1, getActivity());
        difficlutySeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                String min = difficulties[minValue].getName();
                String max = difficulties[maxValue].getName();
                search.setMinDiffuclty(difficulties[minValue]);
                search.setMaxDiffuclty(difficulties[maxValue]);
                difficultyBoundsTextView.setText(min + " — " + '\n' + max);
            }
        });

        layout.addView(difficlutySeekBar);
        RelativeLayout.LayoutParams difficultySeekBarLayoutParams = (RelativeLayout.LayoutParams) difficlutySeekBar.getLayoutParams();
        difficultySeekBarLayoutParams.addRule(RelativeLayout.BELOW, R.id.difficultyBoundsTextView);
        difficultySeekBarLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.difficultyTextView);
        difficultySeekBarLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        difficultySeekBarLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        chosenIngredientsList = (ListView) getActivity().findViewById(R.id.chosenIngredientsListView);
        ChosenIngredientsListAdapter chosenIngredientsListAdapter = new ChosenIngredientsListAdapter(getActivity());
        chosenIngredientsList.setAdapter(chosenIngredientsListAdapter);
        chosenIngredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search.cancelChoise((Chooseable) parent.getItemAtPosition(position));
            }
        });

        chosenTagsList = (ListView) getActivity().findViewById(R.id.chosenTagsListView);
        ChosenTagsListAdapter chosenTagsListAdapter = new ChosenTagsListAdapter(getActivity());
        chosenTagsList.setAdapter(chosenTagsListAdapter);
        chosenTagsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search.cancelChoise((Chooseable) parent.getItemAtPosition(position));
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        return rootView;
    }

    @Override
    public void update(Search search) {
        ((ChosenIngredientsListAdapter) chosenIngredientsList.getAdapter()).changeData(search.getChosenIngredients());
        ((ChosenIngredientsListAdapter) chosenIngredientsList.getAdapter()).notifyDataSetChanged();
        ((ChosenTagsListAdapter) chosenTagsList.getAdapter()).changeData(search.getChosenTags());
        ((ChosenTagsListAdapter) chosenTagsList.getAdapter()).notifyDataSetChanged();
    }
}
