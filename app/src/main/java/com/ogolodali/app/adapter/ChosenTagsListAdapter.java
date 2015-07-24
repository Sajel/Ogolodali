package com.ogolodali.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogolodali.app.R;
import com.ogolodali.app.model.Tag;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Sajel on 01.07.2015.
 */
public class ChosenTagsListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private ArrayList<Tag> tags = new ArrayList<Tag>();

    public ChosenTagsListAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void changeData(Set<Tag> tags) {
        this.tags.clear();
        this.tags.addAll(tags);
    }

    @Override
    public int getCount() {
        return tags.size();
    }


    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.chosen_tags_list, parent, false);
        }
        Tag tag = (Tag) getItem(position);
        ((TextView) convertView.findViewById(R.id.chosenTagsTextView)).setText(tag.getName());;

        return convertView;
    }
}
