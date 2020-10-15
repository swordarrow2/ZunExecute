package com.meng.EclExecuter.utils;

public class EclFunction {
    public final byte[] code;
    public final int start;
    public final int end;

    public EclFunction(byte[] code, int start, int end) {
        this.code = code;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("array:0x%X,start:0x%X,end:0x%X", code.hashCode(), start, end);
    }
}
