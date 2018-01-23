package com.redhat.crowdfunding.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author littleredhat
 */
public class Consts {

    private static Properties p;

    // 初始化配置
    static {
        p = new Properties();
        InputStream in = Consts.class.getResourceAsStream("/config.properties");
        InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
        try {
            p.load(r);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GAS价格
    public static BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    // GAS上限
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000L);
    // 临时文件前缀
    public static String PREFIX = "key";
    // 临时文件后缀
    public static String SUFFIX = ".tmp";

    // 连接池最大连接数
    public static int MAX_TOTAL = Integer.parseInt(p.getProperty("maxTotal"));
    // 连接池最大空闲连接数
    public static int MAX_IDLE = Integer.parseInt(p.getProperty("maxIdle"));
    // 连接池最小空闲连接数
    public static int MIN_IDLE = Integer.parseInt(p.getProperty("minIdle"));
    // 获取连接时检查连接的可用性
    public static boolean SET_TEST_ON_BORROW = Boolean.parseBoolean(p.getProperty("setTestOnBorrow"));
    // 归还连接时检查连接的可用性
    public static boolean SET_TEST_ON_RETURN = Boolean.parseBoolean(p.getProperty("setTestOnReturn"));

    // 钱包密码
    public static String PASSWORD = p.getProperty("password");
    // 钱包路径
    public static String PATH = p.getProperty("path");
    // 合约二进制
    public static String BINARY = p.getProperty("binary");
    // 合约代码
    public static String ADDRESS = p.getProperty("address");
}