package com.meng.EclExecuter.main;

import com.meng.EclExecuter.utils.BitConverter;
import com.meng.EclExecuter.utils.EclFunction;
import com.meng.EclExecuter.utils.EclParamHolder;
import java.util.Arrays;
import com.badlogic.gdx.utils.IntMap;
import com.meng.EclExecuter.gameEntity.Danmaku;

public class EclFunctionStackFrame {

    public String name;
    private EclThread thread;
    public EclFunction function;
    public int pointer;
    public EclFunctionPrivateVarStorage functionPrivateVars = new EclFunctionPrivateVarStorage();
    private EclParamHolder[] params;
    public IntMap<Danmaku> danmakus = new IntMap<>();
    public boolean needBreak = false;
    public boolean functionEnd = false;

    @Override
    public String toString() {
        return name;
    }

    public EclFunctionStackFrame(EclThread et, EclParamHolder... args) {
        thread = et;
        function = EclThreadManager.getInstance().eclFunctions.get(name = args[0].getString());
        System.out.println(name);
        pointer = function.start;
        params =  args;
        System.out.println(String.format("start:%x,end:%x,len:%d", function.start, function.end, function.end - function.start));
        EclSub esub = new EclSub();
        BitConverter bc = BitConverter.getInstanceLittleEndian();
        esub.magic = bc.toInt(function.code, pointer);
        pointer += 4;
        esub.data_offset = bc.toInt(function.code, pointer);
        pointer += 4;
        esub.zero[0] = bc.toInt(function.code, pointer);
        pointer += 4;
        esub.zero[1] = bc.toInt(function.code, pointer);
        pointer += 4;
    }

    public void nextFrame() {
        while (pointer != function.end) {
            if (needBreak) {
                needBreak = false;
                break;
            }
            EclIns el = readIns(pointer);
            pointer += el.size;
            thread.handler.invoke(el);
            if (functionEnd) {
                thread.functionReturn();
                functionEnd = false;
            }
        }
        if (pointer == function.end) {
            functionEnd = true;
        }
        if (functionEnd) {
            thread.functionReturn();
        }
    }

    private EclIns readIns(int readPointer) {
        BitConverter bc = BitConverter.getInstanceLittleEndian();

        EclIns eclins = new EclIns();
        eclins.time = bc.toInt(function.code, readPointer);
        readPointer += 4;
        eclins.id = bc.toShort(function.code, readPointer);
        readPointer += 2;
        eclins.size = bc.toShort(function.code, readPointer);
        readPointer += 2;
        eclins.param_mask = bc.toShort(function.code, readPointer);
        readPointer += 2;
        eclins.rank_mask = function.code[readPointer];
        readPointer += 1;
        eclins.param_count = function.code[readPointer];
        readPointer += 1;
        eclins.zero = bc.toInt(function.code, readPointer);
        readPointer += 4;
        eclins.data = readArray(readPointer, eclins.size - 16);
        return eclins;
    }

    private byte[] readArray(int offset, int length) {
        byte[] bs = new byte[length];
        for (int i = 0; i < length; ++i) {
            bs[i] = function.code[offset + i];
        }
        return bs;
    }

    public class EclFunctionPrivateVarStorage {
        public EclParamHolder[] var;

        public void init(int byteSize) {
            var = new EclParamHolder[byteSize / 4];
            if (params.length > 1) {
                for (int i=1;i < params.length;++i) {
                    EclParamHolder eph = params[i];
                    var[(i - 1)] = eph;

                }
            }
            params = null;
        }

        public void set(int byteOffset, int value) {
            var[byteOffset / 4] = EclParamHolder.get(value);
        }

        public void set(int byteOffset, float value) {
            var[byteOffset / 4] = EclParamHolder.get(value);
        }

        public int getInt(int byteOffset) {
            return var[byteOffset / 4].getint();
        }

        public float getFloat(int byteOffset) {
            System.out.println(functionPrivateVars);
            return var[byteOffset / 4].getFloat();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (EclParamHolder eph :var) {
                sb.append(eph).append(" ");
            }
            return sb.toString();
        }

    }

    public class EclSub {
        public int magic; //0x484C4345
        public int data_offset;// sizeof(th10_sub_t) 
        public int[] zero = new int[2];
        public byte[] data;
    }

    public class EclIns {

        public int time;
        public short id;
        public short size;
        public short param_mask;
        /*
         * The rank bitmask. 1111LHNE Bits mean: easy, normal, hard, lunatic. The
         * rest are always set to 1.
         */
        public byte rank_mask;
        /*
         * There doesn't seem to be a way of telling how many parameters there are
         * from the additional data.
         */
        public byte param_count;
        /*
         * From TH13 on, this field stores the number of current stack references in
         * the parameter list.
         */
        public int zero;
        public byte data[];

        private int dataPosition = 0;

        @Override
        public String toString() {
            return String.format("time:%x,id:%d,size:%d,pmask:%d,rank:%s,pcount:%d,zero:%d,data:%s", time, id, size, param_mask, Integer.toBinaryString(rank_mask).substring(24), param_count, zero, Arrays.toString(data));
        }

        public int readInt() {
            return ((data[dataPosition++] & 0xff) | (data[dataPosition++] & 0xff) << 8 | (data[dataPosition++] & 0xff) << 16 | (data[dataPosition++] & 0xff) << 24);
        }

        public float readFloat() {
            return Float.intBitsToFloat((data[dataPosition++] & 0xff) | (data[dataPosition++] & 0xff) << 8 | (data[dataPosition++] & 0xff) << 16 | (data[dataPosition++] & 0xff) << 24);
        }

        public String readString() {
            int leng = readInt();//length, but not actually length
            StringBuilder sb = new StringBuilder();
            byte ch = 0;
            int tmp = dataPosition;
            while ((ch = data[tmp++]) != 0) {
                sb.append((char)ch);  
            }
            dataPosition += leng;
            return sb.toString();
        }
    }
}
