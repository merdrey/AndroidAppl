package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.entities.BattleItem;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<BattleItem> objects;
    LayoutInflater inflater;
    public CustomAdapter(Context context, ArrayList<BattleItem> objects) {
        this.context = context;
        this.objects = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.list_items, parent, false);
        }
        BattleItem battle = (BattleItem) getItem(position);
        TextView itemText = view.findViewById(R.id.text_1);
        TextView subitemText = view.findViewById(R.id.text_2);
        TextView footText = view.findViewById(R.id.text_3);
        itemText.setText(battle.getBattle());
        subitemText.setText(battle.getDate());
        footText.setText(battle.getWinner());
        return view;
    }
}
