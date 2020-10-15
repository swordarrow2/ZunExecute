package com.meng.EclExecuter.main;

import com.meng.EclExecuter.gameEntity.Enemy;
import com.meng.EclExecuter.utils.EclParamHolder;
import java.util.LinkedList;
import com.meng.EclExecuter.gameEntity.Danmaku;

public class EclThread {

    public final EclThreadVarStack stack = new EclThreadVarStack();
    private EclThread parent;
    public EclFunctionStackFrame current;
    public final EclFunctionStack functionStack = new EclFunctionStack();
    private int waitFrame = 0;
    private int id = -1;

    public EclThread(EclThread parent) {
        this.parent = parent;
    }

    public void setId(int id) {
        this.id = id;
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

    public void invokeEclFunction(EclParamHolder... args) {
        functionStack.push(current = new EclFunctionStackFrame(this, args));
    }

    public void functionReturn() {
        functionStack.pop();
        current = functionStack.peek();
        if (current == null) {
            EclThreadManager.getInstance().killThread(this);
        }
    }

    public void nextFrame() {
        current.nextFrame();
    }

    private class EclFunctionStack {
        private LinkedList<EclFunctionStackFrame> list = new LinkedList<>();

        public EclFunctionStackFrame pop() {
            return list.removeLast();
        }

        public EclFunctionStackFrame push(EclFunctionStackFrame eclFunction) {
            list.addLast(eclFunction);
            return eclFunction;
        }

        public EclFunctionStackFrame peek() {
            return list.getLast();
        }
    }

    public class EclThreadVarStack {

        private final int maxDepth = 32;
        private int intDepth = 0;
        private int[] stack = new int[maxDepth];

        public void push(int n) {
            if (intDepth == maxDepth - 1) {
                throw new RuntimeException("stack full");
            }
            stack[intDepth++] = n;
        }

        public void push(float n) {
            if (intDepth == maxDepth - 1) {
                throw new RuntimeException("stack full");
            }
            stack[intDepth++] = Float.floatToRawIntBits(n);
        }

        public void push(Enemy e) {
            push((Object)e);
        }

        public void push(Danmaku d) {
            push((Object)d);
        }

        public void push(Object o) {
            push(o.hashCode());
            EclThreadManager.getInstance().objectPool.put(o.hashCode(), o);
        }

        public Object popObject() {
            return EclThreadManager.getInstance().objectPool.remove(popInt());
        }

        public <T> T popObject(Class<?> cls) {
            return (T)popObject();
        }

        public Enemy popEnemy() {
            return popObject(Enemy.class);
        }

        public Danmaku popDanmaku() {
            return popObject(Danmaku.class);
        }

        public int popInt() {
            if (intDepth == 0) {
                throw new RuntimeException("stack blank");
            }
            return stack[--intDepth];
        }

        public float popFloat() {
            if (intDepth == 0) {
                throw new RuntimeException("stack blank");
            }
            return Float.intBitsToFloat(stack[--intDepth]);
        }

        public int peekInt() {
            if (intDepth == 0) {
                throw new RuntimeException("stack blank");
            }
            return stack[intDepth - 1];
        }

        public float peekFloat() {
            if (intDepth == 0) {
                throw new RuntimeException("stack blank");
            }
            return Float.intBitsToFloat(stack[intDepth - 1]);
        }

        public Object peekObject() {
            return EclThreadManager.getInstance().objectPool.get(peekInt());
        }

        public <T> T peekObject(Class<?> cls) {
            return (T)peekObject();
        }

        public Enemy peekEnemy() {
            return peekObject(Enemy.class);
        }

        public Danmaku peekDanmaku() {
            return peekObject(Danmaku.class);
        }
    }
}
