package com.ogolodali.app.adapter.ListWithCategoryAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ogolodali.app.R;
import com.ogolodali.app.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRecipeAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    private int idResCategory;
    private int idResSimple;


    private List<ListItem> list;
    public static int COMP_ITEM = 0;
    public static int UNCOMP_ITEM = 1;

    private int positionForComp;

    public ListRecipeAdapter(Context context,
                             int idResCategory, int idResSimple){
        this.context = context;
        this.list = new ArrayList<ListItem>();
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.idResCategory = idResCategory;
        this.idResSimple = idResSimple;
        positionForComp = 1;
        list.add(new ListItem("Можна приготувати"));
        list.add(new ListItem("Якщо сходити в магазин"));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view;
        ListItem item = list.get(i);
        if(convertView == null){
            if(item.isCategoryItem())
                view = layoutInflater.inflate(idResCategory ,parent ,false);
            else
                view = layoutInflater.inflate(idResSimple ,parent ,false);
        }
        else
            view = convertView;
        if(item.isCategoryItem()) {
            ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
        }
        else {
            ((TextView) view.findViewById(R.id.name)).setText(item.getTitle());
            ((TextView) view.findViewById(R.id.difficulty)).setText(item.getTitle());
        }
        return view;
    }

    public void add(ListItem item, int category){
        if(category == COMP_ITEM)
            list.add(positionForComp, item);
        else
            list.add(item);
    }
}
