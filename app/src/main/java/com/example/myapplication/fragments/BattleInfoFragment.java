package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;



public class BattleInfoFragment extends Fragment {

    private TextView info;
    private String stat;
    private Button btn;

    public BattleInfoFragment() {
        super(R.layout.fragment_battle_info);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle_info, container, false);
        info = view.findViewById(R.id.info_text);
        btn = view.findViewById(R.id.close_info_button);
        info.setText(stat);
        if(stat == null) {
            btn.setVisibility(View.INVISIBLE);
        }
        else {
            btn.setOnClickListener(v -> {
                info.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.INVISIBLE);
            });
        }
        return view;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}