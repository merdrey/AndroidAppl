package com.example.myapplication.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListNewsFragment extends Fragment {

    public ListNewsFragment() {
        super(R.layout.fragment_list_news);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_news, container, false);
        ScrollView scroll = view.findViewById(R.id.news_scroll);
        LinearLayout scrollLayout = scroll.findViewById(R.id.scroll_layout);
        addNews(scrollLayout);
        return view;
    }
    private void addNews(LinearLayout layout) {
        LinearLayout addLayout = new LinearLayout(getContext());
        addLayout.setOrientation(LinearLayout.VERTICAL);
        addLayout.setLayoutParams((new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
        TextView head = new TextView(getContext());
        head.setLayoutParams(addLayout.getLayoutParams());
        head.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        head.setTextSize(20);
        head.setAllCaps(true);
        head.layout(5, 5, 5, 5);
        TextView body = new TextView(getContext());
        body.setLayoutParams(addLayout.getLayoutParams());
        body.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        body.setTextSize(16);
        body.layout(5, 5, 5, 5);
        TextView date = new TextView(getContext());
        date.setLayoutParams(addLayout.getLayoutParams());
        date.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        date.layout(5, 5, 5, 15);
        ImageView image = new ImageView(getContext());
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        image.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
            head.setText(result.getString("header"));
            body.setText(result.getString("main"));
            image.setImageBitmap(result.getParcelable("image"));
            date.setText("16.06.2024");
        });
        if(head.getText() != null) {
            addLayout.addView(head);
        }
        addLayout.addView(image);
        if(body.getText() != null) {
            addLayout.addView(body);
        }
        if(date.getText() != null) {
            addLayout.addView(date);
        }
        if(head.getText() != null || body.getText() != null) {
            layout.addView(addLayout);
        }
    }
}