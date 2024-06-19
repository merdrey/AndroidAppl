package com.example.myapplication.fragments;

import static java.lang.Math.abs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Unit;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment {
    private int userBudget = 100, botBudget = 100;
    private ArrayList<Unit> userUnits;
    private ArrayList<Unit> botUnits;
    private UnitFragment unitFrag;
    private int botHp, botDefence, botDamage, userHp, userDefence, userDamage;
    public CalculatorFragment() {
        super(R.layout.fragment_calculator);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        unitFrag = new UnitFragment();
        userUnits = new ArrayList<>();
        botUnits = new ArrayList<>();
        getParentFragmentManager().beginTransaction().add(R.id.units_frag, unitFrag).commit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button userBtn = view.findViewById(R.id.add_to_user);
        Button botBtn = view.findViewById(R.id.add_to_bot);
        Button resBtn = view.findViewById(R.id.result_button);
        TextView userBdgt = view.findViewById(R.id.budget_unit);
        TextView botBdgt = view.findViewById(R.id.budget_bot);
        TextView resTxt = view.findViewById(R.id.battle_result_text);
        LinearLayout botIcons = view.findViewById(R.id.bot_icons);
        LinearLayout userIcons = view.findViewById(R.id.user_icons);
        userBdgt.setText(String.valueOf(userBudget));
        botBdgt.setText(String.valueOf(botBudget));
        userBtn.setOnClickListener(v -> {
            userUnits.add(addUnit(unitFrag.getCount()));
            Unit unit = userUnits.get(userUnits.size() - 1);
            if(userBudget < unit.getPrice())
            {
                userUnits.remove(unit);
            }
            else {
                userHp += unit.getHp();
                userDamage += unit.getDamage();
                userDefence += unit.getDefence();
                userBudget -= unit.getPrice();
                userBdgt.setText(String.valueOf(userBudget));
                ImageView tmpImage = new ImageView(getContext());
                tmpImage.setImageBitmap(
                        BitmapFactory.decodeResource(getResources(), unit.getIconId())
                );
                userIcons.addView(tmpImage);
            }
        });
        botBtn.setOnClickListener(v -> {
            botUnits.add(addUnit(unitFrag.getCount()));
            Unit unit = botUnits.get(botUnits.size() - 1);
            if(botBudget < unit.getPrice()) {
                botUnits.remove(unit);
            }
            else {
                botHp += unit.getHp();
                botDamage += unit.getDamage();
                botDefence += unit.getDefence();
                botBudget -= unit.getPrice();
                botBdgt.setText(String.valueOf(botBudget));
                ImageView tmpImage = new ImageView(getContext());
                tmpImage.setImageBitmap(
                        BitmapFactory.decodeResource(getResources(), unit.getIconId())
                );
                botIcons.addView(tmpImage);
            }
        });
        resBtn.setOnClickListener(v -> {
            if(!botUnits.isEmpty() && !userUnits.isEmpty()) {
                resTxt.setText(calculateBattle(userHp, botHp, userDamage, botDamage, userDefence, botDefence));
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    private String calculateBattle(int uHp, int bHp, int uDam, int bDam, int uDef, int bDef) {
        if(uHp < 5) {
            if(bHp < 5) {
                return "Исход неизвестен";
            }
            else {
                return "Победа бота";
            }
        }
        else {
            if(bHp < 5) {
                return "Победа игрока";
            }
            else {
                if(uDam > bDef) {
                    bDef = 0;
                }
                bHp = bHp + bDef - uDam;
                if(bDam > uDef) {
                    uDef = 0;
                }
                uHp = uHp + uDef - bDam;
                return calculateBattle(uHp, bHp, uDam, bDam, uDef, bDef);
            }
        }
    }
    private Unit addUnit(int i) {
        switch (i) {
            case 0: {
                return new Unit("Мечник", 50, 5, 8, 10, R.drawable.sword_icon, R.drawable.swordsman);
            }
            case 1: {
                return new Unit("Копейщик", 35, 3, 4, 15, R.drawable.spear_icon, R.drawable.spearman);
            }
            case 2: {
                return new Unit("Берсерк", 45, 9, 3, 20, R.drawable.axe_icon, R.drawable.axeman);
            }
            case 3: {
                return new Unit("Лучник", 30, 6, 8, 15, R.drawable.longb_icon, R.drawable.longbow);
            }
            case 4: {
                return new Unit("Охотник", 25, 3, 4, 19, R.drawable.shortb_icon, R.drawable.shortbow);
            }
            case 5: {
                return new Unit("Арбалетчик", 40, 7, 3, 23, R.drawable.crossb_icon, R.drawable.crossbow);
            }
            case 6: {
                return new Unit("Рыцарь", 30, 5, 3, 20, R.drawable.kn_icon, R.drawable.knight);
            }
            case 7: {
                return new Unit("Кирасир", 50, 2, 7, 23, R.drawable.cr_icon, R.drawable.cuirassier);
            }
            case 8: {
                return new Unit("Кочевник", 25, 3, 2, 25, R.drawable.horseb_icon, R.drawable.axeman);
            }
            default: {
                return null;
            }
        }
    }
}