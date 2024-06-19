package com.example.myapplication.entities;

public class BattleItem {
    private final String battle;
    private final String date;
    private final String winner;
    public BattleItem(String battle, String date, String winner) {
        this.battle = battle;
        this.date = date;
        this.winner = winner;
    }
    public String getBattle() {
        return battle;
    }

    public String getDate() {
        return date;
    }

    public String getWinner() {
        return winner;
    }
}
