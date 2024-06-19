package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomAdapter;
import com.example.myapplication.entities.BattleItem;

import java.util.ArrayList;
import java.util.Objects;

public class Battles extends Fragment {
    private ArrayList<BattleItem> battleItems;
    ListView list;
    public Battles() {
        super(R.layout.fragment_battles);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battles, container, false);
        list = view.findViewById(R.id.main_list);
        battleItems = new ArrayList<>();
        String[] battles = getResources().getStringArray(R.array.players);
        String[] dates = getResources().getStringArray(R.array.time);
        String[] winners = getResources().getStringArray(R.array.winners);
        fillBattles(battles, dates, winners);
        list.setAdapter(new CustomAdapter(requireActivity(), battleItems));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FragmentManager manager = getParentFragmentManager();
        BattleInfoFragment infoFragment = new BattleInfoFragment();
        manager.beginTransaction().add(R.id.battle_info, infoFragment).commit();
        list.setOnItemClickListener((parent, _view, position, id) -> {
            manager.beginTransaction().detach(infoFragment).commit();
            switch ((int) id) {
                case 0:{
                    infoFragment.setStat("Информация:\nСилы сторон: 10 | 10\nКарта: Лес\nКоличество ходов: 15");
                    break;
                }
                case 1:{
                    infoFragment.setStat("Информация:\nСилы сторон: 13 | 9\nКарта: Горы\nКоличество ходов: 25");
                    break;
                }
                case 2:{
                    infoFragment.setStat("Информация:\nСилы сторон: 2 | 2\nКарта: Болото\nКоличество ходов: 5");
                    break;
                }
            }
            manager.beginTransaction().attach(infoFragment).commit();
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void fillBattles(String[] battles, String[] dates, String[] winners) {
        for(int i = 0; i < battles.length; i++) {
            battleItems.add(new BattleItem(battles[i], dates[i], winners[i]));
        }
    }
}
