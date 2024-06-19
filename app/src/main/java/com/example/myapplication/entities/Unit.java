package com.example.myapplication.entities;

import android.annotation.SuppressLint;
import android.graphics.Picture;
import android.media.Image;

import androidx.annotation.NonNull;

public class Unit {
    private String name;
    private int hp, damage, defence, price;
    private int iconId, menuIconId;
    public Unit(String name, int hp, int damage, int defence, int price, int iconId, int menuIconId) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.defence = defence;
        this.price = price;
        this.iconId = iconId;
        this.menuIconId = menuIconId;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Имя: %10s, \nЗдоровье: %d, \nУрон: %d, \nЗащита: %d, \nСтоимость: %d",
                name, hp, damage, defence, price);
    }
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }

    public int getPrice() {
        return price;
    }

    public int getIconId() {
        return iconId;
    }

    public int getMenuIconId() {
        return menuIconId;
    }
}
