package com.meng.EclExecuter.main;

import com.badlogic.gdx.math.Vector2;
import com.meng.EclExecuter.gameEntity.Danmaku;
import com.meng.EclExecuter.gameEntity.Enemy;
import com.meng.EclExecuter.utils.EclParamHolder;
import com.meng.EclExecuter.utils.MathHelper;
import java.util.Random;
import com.meng.EclExecuter.ExecuterMain;

public class EclFunctionHandler {
    private EclThread thread;
    private EclThread.EclThreadVarStack stack;
    private EclThreadManager.EclGlobalVarStorage globalVars;
    private EclFunctionStackFrame frame;
    private VarWrapper varWrapper;
    
    private class VarWrapper {
        public void putGlobal(int key, int value) {
            if(key >= 0){
                frame.functionPrivateVars.set(key,value);
                return;
            }
            globalVars.put(key, value);
        }

        public void putGlobal(int key, float value) {
            if(key >= 0){
                frame.functionPrivateVars.set(key,value);
                return;
            }
            globalVars.put(key, value);
        } 

        public int getInt(int key) {
            System.out.println("key:"+key);
            if(key >= 0){
                return frame.functionPrivateVars.getInt(key);
            }
            if(key == -1 || key == -2){
                return thread.stack.popInt();
            }
            return globalVars.getInt(key);
        }
        
        public float getFloat(int key){
            float v = Float.intBitsToFloat(key);
            if(v >= 0){
                return frame.functionPrivateVars.getFloat((int)v);
            }
            if(v == -1.0f || v == -2.0f){
                return thread.stack.popFloat();
            }
            return globalVars.getFloat(key);
        }
    }
    
    public EclFunctionHandler(EclThread et){
        if(et == null){
            throw new RuntimeException("function handler must banding to a function");
        }
        thread = et;
        stack = et.stack;
        globalVars = EclThreadManager.getInstance().globalVars;
        varWrapper = new VarWrapper();
    }
    
    public void notifyCurrent(EclFunctionStackFrame f){
        frame = f;
    }
    
    public void _0() {

    }

    public void _1() {
        frame.functionEnd = true;
    }

    public void _10() {
       // frame.functionEnd = true;
    }

    public void _11(EclParamHolder... args) {
        frame.needBreak = true;
        thread.invokeEclFunction(args);
        return;
    }

    public void _12(int i0, int i1) { // goto
        frame.pointer += i0 - 24;
    }

    public void _13(int i0, int i1) { // unless goto
        if (stack.popInt() == 0) {
            frame.pointer += i0 - 24;
        }
    }

    public void _14(int i0, int i1) { // if goto
        if (stack.popInt() == 1) {
            frame.pointer += i0 - 24;
        }
    }

    public void _15(EclParamHolder... args) {
        EclThreadManager.creatNewThread(thread).invokeEclFunction(args);
    }

    public void _16(EclParamHolder... args) {
        EclThreadManager.creatNewThread(thread).setEnemy(EclThreadManager.getInstance().enemyStack.peekEnemy()).invokeEclFunction(args);
    }

    public void _17(int i0, EclParamHolder... args) {
        EclThreadManager.creatNewThread(thread).setEnemy(EclThreadManager.getInstance().enemyStack.peekEnemy()).setId(i0).invokeEclFunction(args);
    }

    public void _22(int i0, String s1) {

    }

    public void _23(int i0) {
        frame.needBreak = true;
     //   thread.setWaitFrame(i0);        
    }

    public void _30(String s0, int i1) {

    }

    public void _40(int i0) {
        frame.functionPrivateVars.init(i0);
    }

    public void _42(int i0) {
        stack.push(i0);
    }

    public void _43(int i0) {
        frame.functionPrivateVars.set(i0, stack.popInt());
    }

    public void _44(float f0) {
        stack.push(f0);
    }

    public void _45(float f0) {
        frame.functionPrivateVars.set((int) f0, stack.popFloat());
    }

    public void _50() {
        stack.push(stack.popInt() + stack.popInt());
    }

    public void _51() {
        stack.push(stack.popFloat() + stack.popFloat());
    }

    public void _52() {
        int b = stack.popInt();
        int a = stack.popInt();
        stack.push(a - b);
    }

    public void _53() {
        float b = stack.popFloat();
        float a = stack.popFloat();
        stack.push(a - b);
    }

    public void _54() {
        stack.push(stack.popInt() * stack.popInt());
    }

    public void _55() {
        stack.push(stack.popFloat() * stack.popFloat());
    }

    public void _56() {
        int b = stack.popInt();
        int a = stack.popInt();
        stack.push(a / b);
    }

    public void _57() {
        float b = stack.popFloat();
        float a = stack.popFloat();
        stack.push(a / b);
    }

    public void _58() {
        int b = stack.popInt();
        int a = stack.popInt();
        stack.push(a % b);
    }

    public void _59() {
        if (stack.popInt() == stack.popInt()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _60() {
        if (stack.popFloat() == stack.popFloat()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _61() {
        if (stack.popInt() != stack.popInt()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _62() {
        if (stack.popFloat() != stack.popFloat()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _63() {
        if (stack.popInt() > stack.popInt()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _64() {
        if (stack.popFloat() > stack.popFloat()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _65() {
        if (stack.popInt() >= stack.popInt()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _66() {
        if (stack.popFloat() >= stack.popFloat()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _67() {
        if (stack.popInt() < stack.popInt()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _68() {
        if (stack.popFloat() < stack.popFloat()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _69() {
        if (stack.popInt() <= stack.popInt()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _70() {
        if (stack.popFloat() <= stack.popFloat()) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _71() {
        if (stack.popInt() == 0) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _72() {
        if (stack.popFloat() == 0) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _73() {
        if (stack.popInt() != 0 || stack.popInt() != 0) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _74() {
        if (stack.popInt() != 0 && stack.popInt() != 0) {
            stack.push(1);
        } else {
            stack.push(0);
        }
    }

    public void _75() {
        stack.push(stack.popInt() ^ stack.popInt());
    }

    public void _76() {
        stack.push(stack.popInt() | stack.popInt());
    }

    public void _77() {
        stack.push(stack.popInt() & stack.popInt());
    }

    public void _78(int i0) {
        
    }

    public void _81(float f0, float f1, float f2, float f3) {

    }

    public void _82(float f0) {

    }

    public void _83() {

    }

    public void _84() {

    }

    public void _85(float f0, float f1, float f2) {

    }

    public void _86(float f0, float f1, float f2) {

    }

    public void _87(float f0, float f1, float f2, float f3, float f4) {

    }

    public void _88(float f0, float f1, float f2, float f3, float f4) {

    }

    public void _89(float f0, float f1, float f2) {

    }

    public void _90(float f0, float f1, float f2, float f3, float f4) {

    }

    public void _91(int i0, float f1, int i2, int i3, float f4, float f5) {

    }

    public void _92(int i0, float f1, int i2, int i3, float f4, float f5) {

    }

    public void _93(float f0, float f1, float f2, float f3) {

    }

    public void _300(String s0, float x, float y, int life, int bonus, int item) {
        Enemy e = EclThreadManager.getInstance().enemyStack.peekEnemy();
        EclThreadManager.getInstance().enemyStack.push(new Enemy(e.x + x, e.y + y, life, bonus, item));
        EclThreadManager.getInstance().creatNewThread(thread).invokeEclFunction(EclParamHolder.get(s0));
    }

    public void _301(String s0, float x, float y, int life, int bonus, int item) {
        EclThreadManager.getInstance().enemyStack.push(new Enemy(x, y, life, bonus, item));
        EclThreadManager.getInstance().creatNewThread(thread).invokeEclFunction(EclParamHolder.get(s0));
    }

    public void _302(int i0) {

    }

    public void _303(int i0, int i1) {

    }

    public void _304(String s0, float f1, float f2, int i3, int i4, int i5) {

    }

    public void _305(String s0, float f1, float f2, int i3, int i4, int i5) {

    }

    public void _306(int i0, int i1) {

    }

    public void _307(int i0, int i1) {

    }

    public void _308(int i0, int i1) {

    }

    public void _309(String s0, float f1, float f2, int i3, int i4, int i5) {

    }

    public void _310(String s0, float f1, float f2, int i3, int i4, int i5) {

    }

    public void _311(String s0, float f1, float f2, int i3, int i4, int i5) {

    }

    public void _312(String s0, float f1, float f2, int i3, int i4, int i5) {

    }

    public void _313(int i0) {

    }

    public void _314(int i0, int i1) {

    }

    public void _315(int i0, int i1, float f2) {

    }

    public void _316(int i0, int i1) {

    }

    public void _317(int i0, int i1) {

    }

    public void _318() {

    }

    public void _319(int i0, float f1) {

    }

    public void _320(int i0, float f1, float f2) {

    }

    public void _321(String s0, int i1, int i2, int i3, int i4, int i5) {

    }

    public void _322(int i0, int i1) {

    }

    public void _323(int i0, int i1) {

    }

    public void _324(int i0) {

    }

    public void _325(int i0, int i1, int i2, int i3) {

    }

    public void _326(int i0, int i1, int i2, int i3, int i4, int i5) {

    }

    public void _327(int i0, int i1) {

    }

    public void _328(int i0, int i1, int i2, int i3) {

    }

    public void _329(int i0, float f1, float f2) {

    }

    public void _330(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _331(int i0, int i1) {

    }

    public void _332(int i0, int i1, int i2, int i3) {

    }

    public void _333(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _334(int i0) {

    }

    public void _335(int i0, float f1, float f2) {

    }

    public void _336(int i0, int i1) {

    }

    public void _337(int i0, int i1) {

    }

    public void _338(int i0, int i1, float f2, float f3, float f4) {

    }

    public void _339(int i0, int i1, int i2) {

    }

    public void _340(int i0) {

    }

    public void _400(float f0, float f1) {

    }

    public void _401(int i0, int i1, float f2, float f3) {

    }

    public void _402(float f0, float f1) {

    }

    public void _403(int i0, int i1, float f2, float f3) {

    }

    public void _404(float f0, float f1) {

    }

    public void _405(int i0, int i1, float f2, float f3) {

    }

    public void _406(float f0, float f1) {

    }

    public void _407(int i0, int i1, float f2, float f3) {

    }

    public void _408(float f0, float f1, float f2, float f3) {

    }

    public void _409(int i0, int i1, float f2, float f3, float f4) {

    }

    public void _410(float f0, float f1, float f2, float f3) {

    }

    public void _411(int i0, int i1, float f2, float f3, float f4) {

    }

    public void _412(int i0, int i1, float f2) {

    }

    public void _413(int i0, int i1, float f2) {

    }

    public void _414() {

    }

    public void _415() {

    }

    public void _416(float f0, float f1, float f2) {

    }

    public void _417(float f0, float f1, float f2) {

    }

    public void _418(float f0, float f1) {

    }

    public void _419(float f0, float f1) {

    }

    public void _420(float f0, float f1, float f2, float f3, float f4, float f5) {

    }

    public void _421(int i0, int i1, float f2, float f3, float f4, float f5, float f6) {

    }

    public void _422(float f0, float f1, float f2, float f3, float f4, float f5) {

    }

    public void _423(int i0, int i1, float f2, float f3, float f4, float f5, float f6) {

    }

    public void _424(int i0) {

    }

    public void _425(int i0, float f1, float f2, float f3, float f4, float f5, float f6) {

    }

    public void _426(int i0, float f1, float f2, float f3, float f4, float f5, float f6) {

    }

    public void _427() {

    }

    public void _428(float f0, float f1) {

    }

    public void _429(int i0, int i1, float f2, float f3) {

    }

    public void _430(float f0, float f1) {

    }

    public void _431(int i0, int i1, float f2, float f3) {

    }

    public void _432(int i0) {

    }

    public void _433(int i0) {

    }

    public void _434(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _435(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _436(int i0, int i1, float f2, float f3) {

    }

    public void _437(int i0, int i1, float f2, float f3) {

    }

    public void _438(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _439(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _440(float f0) {

    }

    public void _441(int i0, int i1, float f2) {

    }

    public void _442(float f0) {

    }

    public void _443(int i0, int i1, float f2) {

    }

    public void _444(float f0) {

    }

    public void _445(int i0, int i1, float f2) {

    }

    public void _446(float f0) {

    }

    public void _447(int i0, int i1, float f2) {

    }

    public void _500(float f0, float f1) {

    }

    public void _501(float f0, float f1) {

    }

    public void _502(int i0) {

    }

    public void _503(int i0) {

    }

    public void _504(float f0, float f1, float f2, float f3) {

    }

    public void _505() {

    }

    public void _506() {

    }

    public void _507(int i0, int i1) {

    }

    public void _508(float f0, float f1) {

    }

    public void _509() {

    }

    public void _510(int i0) {

    }

    public void _511(int i0) {
        
    }

    public void _512(int i0) {
   /*     if (i0 == -1) {
            FightScreen.instence.onBoss = false;
        } else {
            FightScreen.instence.onBoss = true;
        }
        _301("", 0, 0, 0, 0, 0);*/
    }

    public void _513() {

    }

    public void _514(int i0, final int hp, final int time, final String functionName) {
        Runnable r = new Runnable(){

            @Override
            public void run() {
                if(EclThreadManager.getInstance().enemyStack.peekEnemy().hp == hp || ExecuterMain.currentPartFrameCount == time){
                    thread.functionStack.pop();
                    thread.invokeEclFunction(EclParamHolder.get(functionName));
                }
            }
        };
        thread.watcher = r;
    //    EclManager.nextSub = EclManager.getSubPack("BossCard1").setManager(enemy);
    }

    public void _515(int i0) {

    }

    public void _516(int i0) {

    }

    public void _517(int i0, int i1, int i2) {

    }

    public void _518(int i0) {

    }

    public void _519() {

    }

    public void _520() {

    }

    public void _521(int i0, String s1) {

    }

    public void _522(int i0, int i1, int i2, String s3) {

    }

    public void _523() {

    }

    public void _524(int i0) {

    }

    public void _525() {
        EclThreadManager.getInstance().ins_525();
    }

    public void _526(float f0) {

    }

    public void _527(int i0, float f1, int i2) {

    }

    public void _528(int i0, int i1, int i2, String s3) {

    }

    public void _529(float f0, float f1, float f2, float f3) {

    }

    public void _530(float f0, float f1, float f2, float f3, float f4, float f5) {

    }

    public void _531(float f0, float f1, float f2) {

    }

    public void _532(int i0, int i1, int i2, int i3) {

    }

    public void _533(int i0, int i1, int i2, int i3, int i4, int i5) {

    }

    public void _534(int i0, int i1, int i2) {

    }

    public void _535(int i0, int i1, int i2, int i3, int i4) {
        switch (0/*GameMain.difficulty*/) {
            case 0:
                frame.functionPrivateVars.set(i0, i1);
                break;
            case 1:
                frame.functionPrivateVars.set(i0, i2);
                break;
            case 2:
                frame.functionPrivateVars.set(i0, i3);
                break;
            case 3:
                frame.functionPrivateVars.set(i0, i4);
                break;
        }
    }

    public void _536(float f0, float f1, float f2, float f3, float f4) {
        switch (0/*GameMain.difficulty*/) {
            case 0:
                frame.functionPrivateVars.set((int) f0, f1);
                break;
            case 1:
                frame.functionPrivateVars.set((int) f0, f2);
                break;
            case 2:
                frame.functionPrivateVars.set((int) f0, f3);
                break;
            case 3:
                frame.functionPrivateVars.set((int) f0, f4);
                break;
        }
        
    }

    public void _537(int i0, int i1, int i2, String s3) {

    }

    public void _538(int i0, int i1, int i2, String s3) {

    }

    public void _539(int i0, int i1, int i2, String s3) {

    }

    public void _540(int i0) {

    }

    public void _541(int i0) {

    }

    public void _542() {

    }

    public void _543() {

    }

    public void _544(int i0) {

    }

    public void _545() {

    }

    public void _546(int i0, int i1) {

    }

    public void _547(float f0) {

    }

    public void _548(int i0, int i1, int i2, int i3) {

    }

    public void _549(int i0) {

    }

    public void _550(int i0) {

    }

    public void _551(int i0) {

    }

    public void _552(int i0) {

    }

    public void _553(int i0) {

    }

    public void _554() {

    }

    public void _555(int i0, int i1) {

    }

    public void _556(String s0) {

    }

    public void _557(int i0, int i1, int i2, float f3, float f4) {

    }

    public void _558(int i0) {

    }

    public void _559(int i0) {

    }

    public void _560(float f0, float f1) {

    }

    public void _561() {

    }

    public void _562() {

    }

    public void _563(int i0) {

    }

    public void _564(float f0) {

    }

    public void _565(float f0) {

    }

    public void _566() {

    }

    public void _567(int i0) {

    }

    public void _568(int i0) {

    }

    public void _569(int i0) {

    }

    public void _570() {

    }

    public void _571() {

    }

    public void _572(int i0) {

    }

    public void _600(int i0) {
        frame.danmakus.put(frame.hashCode(),new Danmaku());
    }

    public void _601(int danmakuNum) {
        frame.danmakus.get(frame.hashCode()).shoot();
    }

    public void _602(int danmakuNum, int form, int color) {
        frame.danmakus.get(frame.hashCode()).setFormAndColor(form, color);
    }

    public void _603(int danmakuNum, float offsetX, float offsetY) {
        frame.danmakus.get(frame.hashCode()).setOffsetCartesian(offsetX, offsetY);
    }

    public void _604(int danmakuNum, float direct, float r) {
        frame.danmakus.get(frame.hashCode()).setDirectionAndSub(direct - 1.5707963267948966f, r);
    }

    public void _605(int danmakuNum, float speed, float slowestSpeed) {
        frame.danmakus.get(frame.hashCode()).setSpeed(speed, slowestSpeed);
    }

    public void _606(int danmakuNum, int way, int ceng) {
        frame.danmakus.get(frame.hashCode()).setWaysAndOverlap(way, ceng);
    }

    public void _607(int danmakuNum, int style) {
        frame.danmakus.get(frame.hashCode()).setStyle(style);
    }

    public void _608(int danmakuNum, int voiceOnShoot, int voiceOnChange) {

    }

    public void _609(int danmakuNum, int num, int way, int mode, int inta, int intb, float floatr, float floats) {
    //    frame.danmakus.get(frame.hashCode()).addChange(num, new ChangeTask(way == 0, mode, inta, intb, 0, 0, floatr, floats, 0, 0));
    }

    public void _610(int danmakuNum, int num, int way, int mode, int inta, int intb, int intc, int intd, float floatr,
                      float floats, float floatm, float floatn) {
     //   frame.danmakus.get(frame.hashCode()).addChange(num, new ChangeTask(way == 0, mode, inta, intb, intc, intd, floatr, floats, floatm, floatn));
    }

    public void _611(int danmakuNum, int way, int mode, int inta, int intb, float floatr, float floats) {
    //    frame.danmakus.get(frame.hashCode()).addChange(new ChangeTask(way == 0, mode, inta, intb, 0, 0, floatr, floats, 0, 0));
    }

    public void _612(int danmakuNum, int way, int mode, int inta, int intb, int intc, int intd, float floatr,
                      float floats, float floatm, float floatn) {
    }

    public void _613() {

    }

    public void _614(int danmakuA, int danmakuB) {
   //     eclBulletShooters.put(danmakuA, eclBulletShooters.get(danmakuB).clone());
    }

    public void _615(float floatR) {

    }

    public void _616(float floatR) {

    }

    public void _617(int i0, float f1, float f2, float f3, float f4, float f5, float f6) {

    }

    public void _618(int i0, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9,
                      float f10) {

    }

    public void _619(int i0, float f1, float f2, float f3, float f4) {

    }

    public void _620(int i0, int i1, int i2, int i3, int i4, int i5, int i6) {

    }

    public void _621(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {

    }

    public void _622(int i0, int i1, int i2, int i3, int i4) {

    }

    public void _623(float floatVarName, float floatX, float floatY) {

    }

    public void _624(int danmakuNum, float floatA, float b, float c, float d, float e, float f, float g, float h) {

    }

    public void _625(int danmakuNum, int intA, int b, int c, int d, int e, int f, int g, int h) {

    }

    public void _626(int danmakuNum, float floatAngel, float r) {
        Vector2 cart = MathHelper.polarToCartesian(floatAngel, r);
        Random random = new Random();
        frame.danmakus.get(frame.hashCode()).setOffsetCartesian(random.nextFloat() * r * (random.nextBoolean() ? 1 : -1),
                                                             random.nextFloat() * r * (random.nextBoolean() ? 1 : -1));
    }

    public void _627(int danmakuNum, float r) {
    }

    public void _628(int danmakuNum, float floatX, float y) {
      //  frame.danmakus.get(frame.hashCode()).setCenter(floatX + GameMain.width / 2f, GameMain.height - y);
    }

    public void _629(float floatR, int intRgb) {

    }

    // int
    public void _630(int a) {

    }

    public void _631(int a) {

    }

    public void _632(int a) {

    }

    public void _633(int a) {

    }

    public void _634(int a) {

    }

    // float
    public void _635(float a) {

    }

    public void _636(float a) {

    }

    // int
    public void _637(int a) {

    }

    public void _638(int a) {

    }

    public void _639(int a) {

    }

    // mode=16777216 danmakuNum=2
    public void _640(int danmakuNum, int intMode, String sub) {
    }

    public void _641(int danmakuNum) {

    }

    public void _700(int i0, float f1, float f2, float f3, float f4) {

    }

    public void _701(int i0, int i1, int i2, int i3, int i4, int i5) {

    }

    public void _702(int i0) {

    }

    public void _703(int i0, int i1) {

    }

    public void _704(int i0, float f1, float f2) {

    }

    public void _705(int i0, float f1, float f2) {

    }

    public void _706(int i0, float f1) {

    }

    public void _707(int i0, float f1) {

    }

    public void _708(int i0, float f1) {

    }

    public void _709(int i0, float f1) {

    }

    public void _710(int i0) {

    }

    public void _711(int i0) {

    }

    public void _712(float f0, float f1) {

    }

    public void _713(int i0) {

    }

    public void _714(int i0, int i1) {

    }

    public void _800(int i0, String s1) {

    }

    public void _801(float f0, float f1, int i2) {

    }

    public void _802(int i0) {

    }

    public void _900(int i0) {

    }

    public void _901() {

    }

    public void _902() {

    }

    public void _1000(int i0) {

    }

    public void _1001(int i0) {

    }

    public void _1002(int i0) {

    }

    public void invoke(EclFunctionStackFrame.EclIns ins) {
        System.out.println(frame.name+":"+ins+" start:"+Integer.toHexString(frame.function.start)+" end:"+Integer.toHexString(frame.function.end));
        if((ins.rank_mask & ExecuterMain.difficult) == 0){
            return;
        }
        switch (ins.id) {
            case 0:
                _0();
                break;
            case 1:
                _1();
                break;
            case 10:
                _10();
                break;
            case 11:
                if(ins.param_count == 1){
                    _11(EclParamHolder.get(ins.readString()));
                    break;  
                }
                EclParamHolder[] args11 = new EclParamHolder[ins.param_count];
                args11[0] = EclParamHolder.get(ins.readString());
                for (int i = 1; i < args11.length; ++i) {
                    int type = ins.readInt();
                    switch (type) {
                        case 26985: //0x69 0x69 i i
                            args11[i] = EclParamHolder.get(ins.readInt());
                            break; 
                        case 26214://0x66 0x66 f f
                            args11[i] = EclParamHolder.get(ins.readFloat());
                            break;
                        case 26217://0x69 0x66 i f
                            args11[i] = EclParamHolder.get((float)ins.readInt());
                            break;
                        case 26982://0x66 0x69 f i
                            args11[i] = EclParamHolder.get((int) ins.readFloat());
                            break;
                        default:
                            throw new RuntimeException("unexpect value:" + type);
                    }
                }
                _11(args11);
                break;
            case 12:
                _12((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 13:
                _13((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 14:
                _14((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 15:
                if(ins.param_count == 1){
                    _15(EclParamHolder.get(ins.readString()));
                    break;  
                }
                EclParamHolder[] args15 = new EclParamHolder[ins.param_count];
                args15[0] = EclParamHolder.get(ins.readString());
                for (int i = 1; i < args15.length; ++i) {
                    int type = ins.readInt();
                    switch (type) {
                        case 26985: //0x69 0x69 i i
                            args15[i] = EclParamHolder.get(ins.readInt());
                            break; 
                        case 26214://0x66 0x66 f f
                            args15[i] = EclParamHolder.get(ins.readFloat());
                            break;
                        case 26217://0x69 0x66 i f
                            args15[i] = EclParamHolder.get((float)ins.readInt());
                            break;
                        case 26982://0x66 0x69 f i
                            args15[i] = EclParamHolder.get((int) ins.readFloat());
                            break;
                        default:
                            throw new RuntimeException("unexpect value:" + type);
                    }
                }
                _15(args15);
                break;
            case 16:
                _16(EclParamHolder.get(ins.readString()), EclParamHolder.get(((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt()),
                    EclParamHolder.get(((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt()));
                break;
            case 17:
                _17((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 22:
                _22((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 23:
                _23((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 30:
                _30(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 40:
                _40(ins.readInt());
                break;
            case 42:
                _42((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 43:
                int k = ins.readInt();
                if(k >= 0){
                    frame.functionPrivateVars.set(k,stack.popInt());
                }else {
                    EclThreadManager.getInstance().globalVars.put(k,stack.popInt());
                }
             //   varWrapper.putGlobal(ins.readInt(),stack.popInt());
             //   _43((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 44:
                _44((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 45:
                // _45((ins.param_mask & 1) == 1 ? eclGlobalVars.getFloat(ins.readInt()) :
                // ins.readFloat());
              //  _45(ins.readFloat());
                float f = ins.readFloat();
                if(f >= 0){
                    frame.functionPrivateVars.set((int)f,stack.popInt());
                }else {
                    EclThreadManager.getInstance().globalVars.put((int)f,stack.popInt());
                }
                
                break;
            case 50:
                _50();
                break;
            case 51:
                _51();
                break;
            case 52:
                _52();
                break;
            case 53:
                _53();
                break;
            case 54:
                _54();
                break;
            case 55:
                _55();
                break;
            case 56:
                _56();
                break;
            case 57:
                _57();
                break;
            case 58:
                _58();
                break;
            case 59:
                _59();
                break;
            case 60:
                _60();
                break;
            case 61:
                _61();
                break;
            case 62:
                _62();
                break;
            case 63:
                _63();
                break;
            case 64:
                _64();
                break;
            case 65:
                _65();
                break;
            case 66:
                _66();
                break;
            case 67:
                _67();
                break;
            case 68:
                _68();
                break;
            case 69:
                _69();
                break;
            case 70:
                _70();
                break;
            case 71:
                _71();
                break;
            case 72:
                _72();
                break;
            case 73:
                _73();
                break;
            case 74:
                _74();
                break;
            case 75:
                _75();
                break;
            case 76:
                _76();
                break;
            case 77:
                _77();
                break;
            case 78:
             //      _78((ins.param_mask & 1) == 1 ? int1 : varPos);
                if((ins.param_mask & 1) == 1){
                    int varPos = ins.readInt();
                    int int1 = varWrapper.getInt(varPos);
                    stack.push(int1 != 0 ? 1 : 0);
                    varWrapper.putGlobal(varPos,--int1); 
                }else{
                    stack.push(ins.readInt() != 0 ? 1 : 0);     
                }
                break;
            case 81:
                _81((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 82:
                _82((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 83:
                _83();
                break;
            case 84:
                _84();
                break;
            case 85:
                _85((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 86:
                _86((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 87:
                _87((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 88:
                _88((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 89:
                _89((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 90:
                _90((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 91:
                _91((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 92:
                _92((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                    ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 93:
                _93((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                    ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 300:
                _300(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 301:
                _301(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 302:
                _302((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 303:
                _303((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 304:
                _304(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 305:
                _305(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 306:
                _306((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 307:
                _307((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 308:
                _308((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 309:
                _309(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 310:
                _310(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 311:
                _311(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 312:
                _312(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 313:
                _313((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 314:
                _314((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 315:
                _315((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 316:
                _316((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 317:
                _317((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 318:
                _318();
                break;
            case 319:
                _319((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 320:
                _320((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 321:
                _321(ins.readString(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 322:
                _322((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 323:
                _323((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 324:
                _324((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 325:
                _325((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 326:
                _326((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 327:
                _327((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 328:
                _328((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 329:
                _329((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 330:
                _330((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 331:
                _331((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 332:
                _332((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 333:
                _333((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 334:
                _334((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 335:
                _335((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 336:
                _336((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 337:
                _337((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 338:
                _338((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 339:
                _339((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 340:
                _340((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 400:
                _400((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 401:
                _401((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 402:
                _402((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 403:
                _403((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 404:
                _404((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 405:
                _405((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 406:
                _406((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 407:
                _407((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 408:
                _408((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 409:
                _409((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 410:
                _410((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 411:
                _411((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 412:
                _412((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 413:
                _413((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 414:
                _414();
                break;
            case 415:
                _415();
                break;
            case 416:
                _416((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 417:
                _417((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 418:
                _418((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 419:
                _419((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 420:
                _420((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 421:
                _421((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 422:
                _422((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 423:
                _423((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 424:
                _424((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 425:
                _425((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 426:
                _426((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 427:
                _427();
                break;
            case 428:
                _428((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 429:
                _429((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 430:
                _430((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 431:
                _431((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 432:
                _432((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 433:
                _433((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 434:
                _434((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 435:
                _435((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 436:
                _436((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 437:
                _437((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 438:
                _438((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 439:
                _439((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 440:
                _440((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 441:
                _441((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 442:
                _442((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 443:
                _443((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 444:
                _444((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 445:
                _445((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 446:
                _446((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 447:
                _447((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 500:
                _500((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 501:
                _501((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 502:
                _502((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 503:
                _503((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 504:
                _504((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 505:
                _505();
                break;
            case 506:
                _506();
                break;
            case 507:
                _507((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 508:
                _508((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 509:
                _509();
                break;
            case 510:
                _510((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 511:
                _511((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 512:
                _512((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 513:
                _513();
                break;
            case 514:
                _514((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 515:
                _515((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 516:
                _516((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 517:
                _517((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 518:
                _518((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 519:
                _519();
                break;
            case 520:
                _520();
                break;
            case 521:
                _521((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 522:
                _522((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 523:
                _523();
                break;
            case 524:
                _524((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 525:
                _525();
                break;
            case 526:
                _526((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 527:
                _527((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 528:
                _528((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 529:
                _529((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 530:
                _530((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 531:
                _531((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 532:
                _532((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 533:
                _533((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 534:
                _534((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 535:
                _535(ins.readInt(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 536:
                _536(ins.readFloat(), ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 537:
                _537((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 538:
                _538((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 539:
                _539((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 540:
                _540((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 541:
                _541((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 542:
                _542();
                break;
            case 543:
                _543();
                break;
            case 544:
                _544((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 545:
                _545();
                break;
            case 546:
                _546((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 547:
                _547((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 548:
                _548((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 549:
                _549((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 550:
                _550((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 551:
                _551((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 552:
                _552((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 553:
                _553((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 554:
                _554();
                break;
            case 555:
                _555((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 556:
                _556(ins.readString());
                break;
            case 557:
                _557((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 558:
                _558((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 559:
                _559((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 560:
                _560((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 561:
                _561();
                break;
            case 562:
                _562();
                break;
            case 563:
                _563((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 564:
                _564((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 565:
                _565((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 566:
                _566();
                break;
            case 567:
                _567((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 568:
                _568((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 569:
                _569((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 570:
                _570();
                break;
            case 571:
                _571();
                break;
            case 572:
                _572((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 600:
                _600((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 601:
                _601((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 602:
                _602((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 603:
                _603((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 604:
                _604((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 605:
                int p1 = (ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt();
                float p2 = ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat();
                float p3 = ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat();
                _605(p1, p2, p3);
                break;
            case 606:
                _606((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 607:
                _607((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 608:
                _608((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 609:
                _609((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 610:
                _610((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 8) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 9) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 10) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 11) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 611:
                _611((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 612:
                _612((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 8) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 9) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 10) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 613:
                _613();
                break;
            case 614:
                _614((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 615:
                _615((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 616:
                _616((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 617:
                _617((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 618:
                _618((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 8) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 9) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 10) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 619:
                _619((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 620:
                _620((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 621:
                _621((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 8) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 9) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 10) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 622:
                _622((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 623:
                _623((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 624:
                _624((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 8) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 625:
                _625((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 6) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 7) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 8) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 626:
                _626((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 627:
                _627((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 628:
                _628((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 629:
                _629((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 630:
                _630((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 631:
                _631((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 632:
                _632((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 633:
                _633((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 634:
                _634((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 635:
                _635((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 636:
                _636((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 637:
                _637((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 638:
                _638((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 639:
                _639((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 640:
                _640((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 641:
                _641((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 700:
                _700((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 701:
                _701((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 3) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 4) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 5) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 702:
                _702((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 703:
                _703((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 704:
                _704((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 705:
                _705((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 706:
                _706((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 707:
                _707((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 708:
                _708((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 709:
                _709((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 710:
                _710((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 711:
                _711((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 712:
                _712((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat());
                break;
            case 713:
                _713((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 714:
                _714((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 800:
                _800((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt(), ins.readString());
                break;
            case 801:
                _801((ins.param_mask & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 1) & 1) == 1 ? varWrapper.getFloat(ins.readInt()) : ins.readFloat(),
                     ((ins.param_mask >> 2) & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 802:
                _802((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 900:
                _900((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 901:
                _901();
                break;
            case 902:
                _902();
                break;
            case 1000:
                _1000((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 1001:
                _1001((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
            case 1002:
                _1002((ins.param_mask & 1) == 1 ? varWrapper.getInt(ins.readInt()) : ins.readInt());
                break;
        }
    }
}
