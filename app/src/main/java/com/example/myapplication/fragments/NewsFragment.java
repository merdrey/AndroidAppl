package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

public class NewsFragment extends Fragment {
    FragmentManager manager;
    ListNewsFragment newsFragment;
    AddNewsFragment addNewsFragment;
    public NewsFragment() {
        super(R.layout.fragment_news);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        manager = getParentFragmentManager();
        newsFragment = new ListNewsFragment();
        manager.beginTransaction().add(R.id.news_fragments, newsFragment).commit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addNewsFragment = new AddNewsFragment();
        newsFragment = new ListNewsFragment();
        Button addBtn = view.findViewById(R.id.news_btn);
        addBtn.setOnClickListener(v -> {
            if(addBtn.getText().equals("Предложить новость")) {
                addBtn.setText("К новостям");
                manager.beginTransaction().replace(R.id.news_fragments, addNewsFragment).commit();
            }
            else if(addBtn.getText().equals("К новостям")) {
                addBtn.setText("Предложить новость");
                manager.beginTransaction().replace(R.id.news_fragments, newsFragment).commit();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}