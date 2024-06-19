package com.example.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entities.RowItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class StatFragment extends Fragment {

    ArrayList<RowItem> rowItems;

    public StatFragment() {
       super(R.layout.fragment_stat);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        TableLayout table = view.findViewById(R.id.stat_table);
        fillRowItems();
        fillTable(getContext(), table);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AtomicBoolean switchState = new AtomicBoolean(false);
        TableLayout table = view.findViewById(R.id.stat_table);
        Button btn_1 = view.findViewById(R.id.sort_btn);
        Button btn_2 = view.findViewById(R.id.sort_btn2);
        Button btn_3 = view.findViewById(R.id.sort_btn3);
        Switch sort_sw = view.findViewById(R.id.sort_switch);
        sort_sw.setOnCheckedChangeListener((buttonView, isChecked) -> switchState.set(isChecked));
        btn_1.setOnClickListener(v -> {
            if(switchState.get()) {
                rowItems.sort(Comparator.comparing(RowItem::getNick).reversed());
            }
            else {
                rowItems.sort(Comparator.comparing(RowItem::getNick));
            }
            table.removeViews(1, 4);
            fillTable(getContext(), table);
        });
        btn_2.setOnClickListener(v -> {
            final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
            if(switchState.get()) {
                rowItems.sort((o1, o2) -> {
                    try {
                        return format.parse(o1.getDate()).compareTo(format.parse(o2.getDate()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            else {
                rowItems.sort(Collections.reverseOrder((o1, o2) -> {
                    try {
                        return format.parse(o1.getDate()).compareTo(format.parse(o2.getDate()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }));
            }
            table.removeViews(1, 4);
            fillTable(getContext(), table);
        });
        btn_3.setOnClickListener(v -> {
            if(switchState.get()) {
                rowItems.sort(Comparator.comparing(RowItem::getRate).reversed());
            }
            else {
                rowItems.sort(Comparator.comparing(RowItem::getRate));
            }
            table.removeViews(1, 4);
            fillTable(getContext(), table);
        });
        super.onViewCreated(view, savedInstanceState);
    }
    private void fillRowItems() {
        rowItems = new ArrayList<>();
        String[] players = getResources().getStringArray(R.array.tab_players);
        String[] dates = getResources().getStringArray(R.array.tab_dates);
        String[] rates = getResources().getStringArray(R.array.tab_rates);
        String[] units = getResources().getStringArray(R.array.tab_units);
        for(int i = 0; i < players.length; i++) {
            rowItems.add(new RowItem(players[i], dates[i], rates[i], units[i]));
        }
    }
    private void fillTable(Context context, TableLayout table){
        table.setStretchAllColumns(true);
        for(int i = 0; i < rowItems.size(); i++) {
            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            TextView nick = new TextView(context);
            TextView date = new TextView(context);
            TextView rate = new TextView(context);
            TextView unit = new TextView(context);
            nick.setText(rowItems.get(i).getNick());
            nick.setTextSize(10);
            nick.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            date.setText(rowItems.get(i).getDate());
            date.setTextSize(10);
            date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rate.setText(rowItems.get(i).getRate());
            rate.setTextSize(10);
            rate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            unit.setText(rowItems.get(i).getUnit());
            unit.setTextSize(10);
            unit.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            row.addView(nick);
            row.addView(date);
            row.addView(rate);
            row.addView(unit);
            table.addView(row);
        }
    }
}