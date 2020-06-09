package com.example.demo.base;

import java.io.*;

public class Utils {
    private static final String PREFIX = "key";
    private static final String SUFFIX = ".tmp";

    /**
     * 临时文件
     *
     * @param content
     * @return
     */
    public static File StoreTmpFile(byte[] content) {
        File tmp = null;
        try {
            tmp = File.createTempFile(PREFIX, SUFFIX);
            tmp.deleteOnExit();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmp));
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }
}
