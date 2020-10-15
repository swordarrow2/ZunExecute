package com.meng.EclExecuter.gameEntity;

import java.util.Arrays;

public class Enemy {

    public float x;
    public float y;

    public void invoke(Object... args) {
        System.out.println(Arrays.toString(args));
    } 
}
