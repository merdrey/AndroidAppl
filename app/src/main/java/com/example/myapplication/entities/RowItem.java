package com.example.myapplication.entities;

public class RowItem {
    private String nick, date, rate, unit;
    public RowItem(String nick, String date, String rate, String unit) {
        this.nick = nick;
        this.date = date;
        this.rate = rate;
        this.unit = unit;
    }

    public String getNick() {
        return nick;
    }

    public String getDate() {
        return date;
    }

    public String getRate() {
        return rate;
    }

    public String getUnit() {
        return unit;
    }
}
