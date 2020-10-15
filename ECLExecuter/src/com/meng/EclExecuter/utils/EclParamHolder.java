package com.meng.EclExecuter.utils;

public class EclParamHolder {

    private boolean isInt = false;
    private int vi;
    private float vf;
    private String s;

    public static EclParamHolder get(int i){
        return new EclParamHolder(i);
    }
    
    public static EclParamHolder get(float f){
        return new EclParamHolder(f);
    }
    
    public static EclParamHolder get(String s){
        return new EclParamHolder(s);
    }
    
    public static EclParamHolder get(){
        return new EclParamHolder();
    }
    
    private EclParamHolder(int i) {
        init(i);
    }

    private EclParamHolder(float f) {
        init(f);
    }

    private EclParamHolder(String s) {
        init(s);
    }
    
    private EclParamHolder(){
        
    }

    public EclParamHolder init(float f) {
        isInt = false;
        s = null;
        vf = f;
        return this;
    }

    public EclParamHolder init(String s) {
        isInt = false;
        this.s = s;
        return this;
    }

    public EclParamHolder init(int i) {
        isInt = true;
        s = null;
        vi = i;
        return this;
    }

    public boolean isInt() {
        if (s != null) {
            throw new RuntimeException("is String");
        }
        return isInt;
    }

    public float getFloat() {
        if (!isInt) {
            return vf;
        }
        throw new RuntimeException("not float");
    }

    public int getint() {
        if (isInt) {
            return vi;
        }
        throw new RuntimeException("not int");
    }

    public String getString() {
        return s;
    }
}
