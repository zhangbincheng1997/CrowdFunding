package com.redhat.crowdfunding.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author littleredhat
 */
public class Utils {

    /**
     * 将 客户端钱包内容 存储为 服务端钱包文件
     *
     * @param content
     * @return
     */
    public static File StoreFile(String content) {
        // 临时文件
        File tmp = null;
        try {
            tmp = File.createTempFile(Consts.PREFIX, Consts.SUFFIX);
            // 自动删除
            tmp.deleteOnExit();
            // 写入内容
            BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmp;
    }
}