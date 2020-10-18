package com.meng.EclExecuter.main;

import com.meng.EclExecuter.ExecuterMain;
import com.meng.EclExecuter.gameEntity.Enemy;
import com.meng.EclExecuter.utils.EclParamHolder;
import java.util.LinkedList;

public class EclThread {

    public final EclThreadVarStack stack = new EclThreadVarStack();
    private EclThread parent;
    public EclFunctionStackFrame current;
    public final EclFunctionStack functionStack = new EclFunctionStack();
    private int waitFrame = 0;
    private int id = -1;
    private Enemy enemy = null;
    public EclFunctionHandler handler;
    public Runnable watcher = null;

    public EclThread(EclThread parent) {
        this.parent = parent;
        handler = new EclFunctionHandler(this);
    }

    public EclThread setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setWaitFrame(int waitFrame) {
        this.waitFrame = waitFrame;
    }

    public int getWaitFrame() {
        return waitFrame;
    }

    public int decWaitFrame() {
        return --waitFrame;
    }

    public EclThread invokeEclFunction(EclParamHolder... args) {
        functionStack.push(current = new EclFunctionStackFrame(this, args));
        System.out.println(args[0].getString() + " start");
        ExecuterMain.currentPartFrameCount = 0;
        return this;
    }

    public EclThread setEnemy(Enemy e) {
        enemy = e;
        return this;
    }

    public void functionReturn() {
        System.out.println(current + " return");
        functionStack.pop();
        current = functionStack.peek();
        if (current == null) {
            EclThreadManager.getInstance().killThread(this);
            System.out.println("Thread end");
            return;
        }
    }

    public void nextFrame() {
        current.nextFrame();
        if(watcher != null){
            watcher.run();
        }
    }

    private class EclFunctionStack {
        private LinkedList<EclFunctionStackFrame> list = new LinkedList<>();

        public EclFunctionStackFrame pop() {
            if (list.size() == 0) {
                return null;
            }
            EclFunctionStackFrame removeLast = list.removeLast();
            handler.notifyCurrent(peek());
            return removeLast;
        }

        public EclFunctionStackFrame push(EclFunctionStackFrame eclFunction) {
            list.addLast(eclFunction);
            handler.notifyCurrent(eclFunction);
            return eclFunction;
        }

        public EclFunctionStackFrame peek() {
            if (list.size() == 0) {
                return null;
            }
            handler.notifyCurrent(list.getLast());
            return list.getLast();
        }
        
        public void ins_525(){
            
        }
    }

    public class EclThreadVarStack {

        private int max = 0;
        private final int maxDepth = 512;
        private int intDepth = 0;
        private int[] stack = new int[maxDepth];
        
        public void push(int n) {
            if (intDepth == maxDepth - 1) {
                throw new RuntimeException("EclStackOverflow");
            }
            stack[intDepth++] = n;
        }

        public void push(float n) {
            if(intDepth > max){
                max = intDepth;
                System.out.println("max:"+max);
            }
            push(Float.floatToRawIntBits(n));
        }

        public int popInt() {
            if (intDepth == 0) {
                throw new RuntimeException("EclStackUnderflow");
            }
            return stack[--intDepth];
        }

        public float popFloat() {
            return Float.intBitsToFloat(popInt());
        }

        public int peekInt() {
            if (intDepth == 0) {
                throw new RuntimeException("EclStackUnderflow");
            }
            return stack[intDepth - 1];
        }

        public float peekFloat() {
            return Float.intBitsToFloat(peekInt());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i < intDepth;++i){
                sb.append(stack[i]).append(" ");
            }
            return sb.toString();
        }
    }
}
