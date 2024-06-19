package com.example.myapplication.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Unit;


import java.util.ArrayList;

public class UnitFragment extends Fragment {
    private ArrayList<Unit> units = new ArrayList<>();
    private int count = 0;
    private TextView info;
    private ImageView icon;
    Bitmap bitmap;
    public UnitFragment() {
        super(R.layout.fragment_unit);
        units.add(new Unit("Мечник", 50, 5, 8, 10, R.drawable.sword_icon, R.drawable.swordsman));
        units.add(new Unit("Копейщик", 35, 3, 4, 15, R.drawable.spear_icon, R.drawable.spearman));
        units.add(new Unit("Берсерк", 45, 9, 3, 20, R.drawable.axe_icon, R.drawable.axeman));
        units.add(new Unit("Лучник", 30, 6, 8, 15, R.drawable.longb_icon, R.drawable.longbow));
        units.add(new Unit("Охотник", 25, 3, 4, 19, R.drawable.shortb_icon, R.drawable.shortbow));
        units.add(new Unit("Арбалетчик", 40, 7, 3, 23, R.drawable.crossb_icon, R.drawable.crossbow));
        units.add(new Unit("Рыцарь", 30, 5, 3, 20, R.drawable.kn_icon, R.drawable.knight));
        units.add(new Unit("Кирасир", 50, 2, 7, 23, R.drawable.cr_icon, R.drawable.cuirassier));
        units.add(new Unit("Кочевник", 25, 3, 2, 25, R.drawable.horseb_icon, R.drawable.horsebow));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unit, container, false);
        info = view.findViewById(R.id.unit_info);
        icon = view.findViewById(R.id.unit_image);
        bitmap = BitmapFactory.decodeResource(getResources(), units.get(count).getMenuIconId());
        icon.setImageBitmap(bitmap);
        info.setText(units.get(count).toString());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button nextBtn = view.findViewById(R.id.button2);
        Button prevBtn = view.findViewById(R.id.button);
        nextBtn.setOnClickListener(v -> {
            count++;
            if(count > 8) { count = 0; }
            info.setText(units.get(count).toString());
            icon.setImageBitmap(BitmapFactory.decodeResource(getResources(), units.get(count).getMenuIconId()));
        });
        prevBtn.setOnClickListener(v -> {
            count--;
            if(count < 0) { count = 8; }
            info.setText(units.get(count).toString());
            icon.setImageBitmap(BitmapFactory.decodeResource(getResources(), units.get(count).getMenuIconId()));
        });

        super.onViewCreated(view, savedInstanceState);
    }
    public int getCount()  {
        return count;
    }
}