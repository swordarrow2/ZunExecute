package com.meng.EclExecuter.gameEntity;
import com.meng.EclExecuter.main.EclThread;
import java.util.ArrayList;
import java.util.Arrays;

public class Danmaku extends BaseGameObject implements Cloneable {

    public void setStyle(int style) {
    }

    public void setWaysAndOverlap(int way, int ceng) {
    }

    public void setSpeed(float speed, float slowestSpeed) {
    }

    public void setDirectionAndSub(float direct, float r) {
    }

    public void setOffsetCartesian(float offsetX, float offsetY) {
    }

    public void setFormAndColor(int form, int color) {
    }

    public void shoot() {
    }

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
