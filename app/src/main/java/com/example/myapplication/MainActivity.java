package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.myapplication.fragments.Battles;
import com.example.myapplication.fragments.CalculatorFragment;
import com.example.myapplication.fragments.NewsFragment;
import com.example.myapplication.fragments.StatFragment;
import com.example.myapplication.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Battles battles = new Battles();
        BottomNavigationView navView = findViewById(R.id.main_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.fragments, battles).commit();
        navView.setOnNavigationItemSelectedListener(navListener);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        if(item.getItemId() == R.id.statistic) {
            selectedFragment = new StatFragment();
        }
        else if (item.getItemId() == R.id.main) {
            selectedFragment = new Battles();
        }
        else if(item.getItemId() == R.id.news) {
            selectedFragment = new NewsFragment();
        }
        else if(item.getItemId() == R.id.user) {
            selectedFragment = new UserFragment();
        }
        else if(item.getItemId() == R.id.calc) {
           selectedFragment = new CalculatorFragment();
        }
        if(selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments, selectedFragment).commit();
        }
        return true;
    };

}