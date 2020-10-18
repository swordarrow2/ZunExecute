package com.meng.EclExecuter;

import com.meng.EclExecuter.io.EclFile;
import com.meng.EclExecuter.main.EclThread;
import com.meng.EclExecuter.main.EclThreadManager;
import com.meng.EclExecuter.utils.EclParamHolder;
import java.util.Scanner;
import java.util.function.Consumer;

public class ExecuterMain {

    public static int difficult = 0b1;
    public static int frameCount = 0;
    public static int currentPartFrameCount =0;
    
    
    public static void main(String[] args) {
        new EclFile("st06.ecl");
        EclThreadManager etm = EclThreadManager.getInstance();
        // for (Map.Entry<String,EclFunction> entry:etm.eclFunctions.entrySet()) {
        //   System.out.println(entry.getValue() + ":" + entry.getKey());  
        //  }
        etm.creatNewThread(null).invokeEclFunction(EclParamHolder.get("main"));
    }
}
