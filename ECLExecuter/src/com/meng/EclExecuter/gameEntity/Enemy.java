package com.meng.EclExecuter.gameEntity;

import java.util.Arrays;

public class Enemy {

    public float x;
    public float y;
    public float hp;

    public Enemy() {
        hp = 1;
    }

    public Enemy(float x, float y, int life , int bonus, int item) {
        this.x = x;
        this.y = y;
        hp = 5501;
    }

    public void invoke(Object... args) {
        System.out.println(Arrays.toString(args));
    } 
}
