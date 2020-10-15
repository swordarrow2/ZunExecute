package com.meng.EclExecuter.main;

import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntMap;
import com.meng.EclExecuter.utils.EclFunction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.LinkedList;

public class EclThreadManager {

    public final IntMap<Object> objectPool = new IntMap<>();
    public final Map<String,EclFunction> eclFunctions = new LinkedHashMap<>();

    private static EclThreadManager instance = new EclThreadManager();
    private ArrayList<EclThread> threads = new ArrayList<>();
    private ArrayList<Runnable> canntConcurrent = new ArrayList<>();
    public final EclGlobalVarStorage globalVars = new EclGlobalVarStorage();

    private ScheduledThreadPoolExecutor ExecutorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

    private EclThreadManager() {
        ExecutorService.scheduleAtFixedRate(new Runnable(){

                @Override
                public void run() {
                    threads.forEach(new Consumer<EclThread>(){

                            @Override
                            public void accept(EclThread p1) {
                                if (p1.getWaitFrame() > 0) {
                                    p1.decWaitFrame();
                                } else {
                                    p1.nextFrame();
                                }
                            }
                        });
                    if (canntConcurrent.size() != 0) {
                        for (Runnable r:canntConcurrent) {
                            r.run(); 
                        }
                    }
                    canntConcurrent.clear();
                    //System.out.println("next");
                }
            }, 0, 16666, TimeUnit.MICROSECONDS);
    }

    public static EclThreadManager getInstance() {
        return instance;
    }

    public void killThread(final EclThread et) {
        canntConcurrent.add(new Runnable(){

                @Override
                public void run() {
                    threads.remove(et);  
                }
            });
    }

    public void killThread(final int id) {
        canntConcurrent.add(new Runnable(){

                @Override
                public void run() {
                    for (EclThread et :threads) {
                        if (et.getId() == id) {
                            threads.remove(et);
                            break;
                        }
                    }
                }
            });
    }

    public static EclThread creatNewThread(EclThread parent) {
        EclThread eclThread = new EclThread(parent);
        instance.threads.add(eclThread);
        return eclThread;
    }

    public class EclGlobalVarStorage {

        private IntIntMap values = new IntIntMap();
        private Random random = new Random();

        public EclGlobalVarStorage() {
            values.put(-9949, 0);
            values.put(-9948, 0);
            values.put(-9947, 1);
            // values.put(-9985,0);
            values.put(-971246592, 0);
        }

        public void put(int key, int value) {
            values.put(key, value);
        }

        public void put(int key, float value) {
            values.put(key, Float.floatToRawIntBits(value));
        } 

        public int getInt(int i) {
            switch (i) {
                case -9904:
                case -9907:
                    return -1;
                case -9947:
                case -9948:
                case -9949:
                    return values.get(i, 0);
                case -9954:
                    return 10;//boss.hp;
                case -9959:
                    return 0;// GameMain.difficulty;
                case -9978:
                case -9979:
                case -9980:
                case -9985:
                    return values.get(i, 0);
                case -9986:
                    return 0;
                case -9988:
                    return 60;//FightScreen.instence.gameTimeFlag;
                case -10000:
                    return random.nextInt();
                default:
                    throw new NullPointerException("unexpect value:" + i);
            }
        }

        public float getFloat(int intBytes) {
            switch (intBytes) {
                case -971314176: // -9915.0
                    break;
                case -971313152: // -9916.0
                    break;
                case -971312128: // -9917.0
                    break;
                case -971311104: // -9918.0
                    break;
                case -971310080: // -9919.0
                    break;
                case -971309056: // -9920.0
                    break;
                case -971308032: // -9921.0
                    break;
                case -971307008: // -9922.0
                    break;
                case -971296768: // -9932.0
                    break;
                case -971295744: // -9933.0
                    break;
                case -971294720: // -9934.0
                    break;
                case -971293696: // -9935.0
                    break;
                case -971292672: // -9936.0
                    break;
                case -971291648: // -9937.0
                    break;
                case -971290624: // -9938.0
                    break;
                case -971289600: // -9939.0
                    break;
                case -971284480: // -9944.0
                    break;
                case -971273216: // -9955.0
                    break;
                case -971272192: // -9956.0
                    break;
                case -971270144: // -9958.0
                    break;
                case -971266048: // -9962.0
                    break;
                case -971265024: // -9963.0
                    break;
                case -971264000: // -9964.0
                    break;
                case -971262976: // -9965.0
                    break;
                case -971261952: // -9966.0
                    break;
                case -971260928: // -9967.0
                    break;
                case -971259904: // -9968.0
                    break;
                case -971258880: // -9969.0
                    break;
                case -971257856: // -9970.0
                    break;
                case -971256832: // -9971.0
                    break;
                case -971255808: // -9972.0
                    break;
                case -971254784: // -9973.0
                    break;
                case -971253760: // -9974.0
                    break;
                case -971252736: // -9975.0
                    break;
                case -971251712: // -9976.0
                    break;
                case -971250688: // -9977.0
                    break;
                case -971249664: // -9978.0
                    break;
                case -971248640: // -9979.0
                    break;
                case -971247616: // -9980.0
                    break;
                case -971246592: // -9981.0
                    return Float.intBitsToFloat(values.get(intBytes, 0));
                case -971240448: // -9987.0
                    return random.nextFloat() * 2f - 1f;
                case -971238400: // -9989.0
                    return 1.5f;
                case -971237376: // -9990.0
                    break;
                case -971236352: // -9991.0
                    break;
                case -971235328: // -9992.0
                    break;
                case -971234304: // -9993.0
                    break;
                case -971233280: // -9994.0
                    break;
                case -971232256: // -9995.0
                    break;
                case -971231232: // -9996.0
                    break;
                case -971230208: // -9997.0
                    break;
                case -971229184: // -9998.0
                    return random.nextFloat() * 6.28f - 3.14f;
                case -971228160: // -9999.0
                    break;
                default:
                    throw new NullPointerException("unexpect value:" + Float.intBitsToFloat(intBytes) + " bytes:" + intBytes);
            }
            throw new NullPointerException("unexpect value:" + Float.intBitsToFloat(intBytes) + " bytes:" + intBytes);
        }
    }
}
