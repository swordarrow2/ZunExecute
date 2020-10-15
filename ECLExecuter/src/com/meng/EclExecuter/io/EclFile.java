package com.meng.EclExecuter.io;

import com.badlogic.gdx.utils.IntMap;
import com.meng.EclExecuter.main.EclThreadManager;
import com.meng.EclExecuter.utils.EclFunction;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class EclFile {
    private EclHeader eclHeader = new EclHeader();
    private EclIncludeList includeAnm = new EclIncludeList();
    private EclIncludeList includeEcl = new EclIncludeList();

    public EclFile(String fileName) {
        ByteReader byteReader = new ByteReader(readFile("/storage/emulated/0/AppProjects/ZunRunner/ZunRunner/2un/ecl/" + fileName));
        eclHeader.magic = byteReader.readInt();
        eclHeader.unknown1 = byteReader.readShort();
        eclHeader.include_length = byteReader.readShort();
        eclHeader.include_offset = byteReader.readInt();
        eclHeader.zero1 = byteReader.readInt();
        eclHeader.sub_count = byteReader.readInt();
        eclHeader.zero2[0] = byteReader.readInt();
        eclHeader.zero2[1] = byteReader.readInt();
        eclHeader.zero2[2] = byteReader.readInt();
        eclHeader.zero2[3] = byteReader.readInt();
        includeAnm.magic = byteReader.readInt();
        includeAnm.count = byteReader.readInt();
        ArrayList<String> includeNames = new ArrayList<>();
        StringBuilder includeNameBuilder = new StringBuilder();
        byte includeBuilderFlag;
        while (includeNames.size() < includeAnm.count) {
            if ((includeBuilderFlag = byteReader.readByte()) != 0) {
                includeNameBuilder.append((char)includeBuilderFlag);
            } else {
                includeNames.add(includeNameBuilder.toString());
                includeNameBuilder.setLength(0);
            } 
        }

        includeAnm.include = includeNames.toArray(includeAnm.include);
        byteReader.moveToNextInt();
        includeEcl.magic = byteReader.readInt();
        includeEcl.count = byteReader.readInt();
        includeNames.clear();
        includeNameBuilder.setLength(0);

        while (includeNames.size() < includeEcl.count) {
            if ((includeBuilderFlag = byteReader.readByte()) != 0) {
                includeNameBuilder.append((char)includeBuilderFlag);
            } else {
                includeNames.add(includeNameBuilder.toString());
                includeNameBuilder.setLength(0);
            } 
        }
        includeEcl.include = includeNames.toArray(includeEcl.include);
        byteReader.moveToNextInt();

        IntMap<String> offsetToName = new IntMap<>();
        int[] functionIndex = new int[eclHeader.sub_count];
        for (int i=0;i < eclHeader.sub_count;i++) {
            functionIndex[i] = byteReader.readInt();
        }      
        byteReader.moveToNextInt();

        // EclThreadManager tm = EclThreadManager.getInstance();
        StringBuilder sb = new StringBuilder();
        byte b;
        while (offsetToName.size < eclHeader.sub_count) {
            if ((b = byteReader.readByte()) != 0) {
                sb.append((char)b);
            } else {
                offsetToName.put(functionIndex[offsetToName.size], sb.toString());
                sb.setLength(0);
            } 
        }
        byteReader.moveToNextInt();
        for (int i = 1;i < eclHeader.sub_count - 1;i++) {
            EclFunction eclf = new EclFunction(byteReader.fileByte, functionIndex[i - 1], functionIndex[i]);
            EclThreadManager.getInstance().eclFunctions.put(offsetToName.get(functionIndex[i - 1]) , eclf);
            byteReader.readInt();//magic
            byteReader.readInt();//data offset;
            byteReader.readInt();//0
            byteReader.readInt();//0
            while (byteReader.position < eclf.end) {
                byteReader.readByte();
            }
        }
        EclFunction eclf = new EclFunction(byteReader.fileByte, functionIndex[functionIndex.length - 1], byteReader.fileByte.length - 1);
        EclThreadManager.getInstance().eclFunctions.put(offsetToName.get(functionIndex[functionIndex.length - 1]) , eclf);
        for (String s : includeEcl.include) {
            new EclFile(s);
        }
    }

    public class EclHeader {
        public int magic;
        public short unknown1; // 1
        public short include_length; // include_offset + ANIM + ECLI length
        public int include_offset; // sizeof(EclHeader)=0x24
        public int zero1;
        public int sub_count;
        public int[] zero2 = new int[4];
    }

    public class EclIncludeList {
        public int magic; //anim:0x54504353  ecl:0x494C4345
        public int count;
        public String[] include = new String[0];
    }

    private byte[] readFile(String s) {
        File f = new File(s);
        byte[] fb = new byte[(int)f.length()];
        try {
            FileInputStream fi = new FileInputStream(f);
            fi.read(fb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fb;
    }
}

