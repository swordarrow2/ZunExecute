package com.meng.EclExecuter.gameEntity;
import com.meng.EclExecuter.main.EclThread;
import java.util.ArrayList;
import java.util.Arrays;

public class Danmaku extends BaseGameObject implements Cloneable {

    public void invoke(Object... args) {
        System.out.println(Arrays.toString(args));
    }

    @Override
    protected Danmaku clone() {
        try {
            return (Danmaku)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        
    }

}
