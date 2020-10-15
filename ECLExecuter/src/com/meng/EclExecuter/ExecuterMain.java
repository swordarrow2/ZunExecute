package com.meng.EclExecuter;

import com.meng.EclExecuter.io.EclFile;
import com.meng.EclExecuter.main.EclThreadManager;
import com.meng.EclExecuter.utils.EclParamHolder;

public class ExecuterMain {

    public static void main(String[] args) {
        new EclFile("st06.ecl");
        EclThreadManager etm = EclThreadManager.getInstance();
        // for (Map.Entry<String,EclFunction> entry:etm.eclFunctions.entrySet()) {
        //   System.out.println(entry.getValue() + ":" + entry.getKey());  
        //  }
        etm.creatNewThread(null).invokeEclFunction(new EclParamHolder("main"));
    }
}
