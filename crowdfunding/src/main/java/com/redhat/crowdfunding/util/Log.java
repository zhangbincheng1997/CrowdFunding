package com.redhat.crowdfunding.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    /**
     * 是否打印日志，true表示打印日志，false表示不打印。
     * <p>
     * 开发阶段可以将其设为ture，运行阶段可以设为false
     */
    private static final boolean enabled = true;
    /**
     * 是否进行源代码定位，ture表示输出源代码所在类以及代码行
     */
    private static boolean showLocSrc = true;
    /**
     * 指定的日志级别
     */
    private static int level = 1;
    /**
     * 日志级别：调试
     */
    private static final int debug = 1;
    /**
     * 日志级别：普通
     */
    private static final int info = 2;
    /**
     * 日志级别：警告
     */
    private static final int warn = 3;
    /**
     * 日志级别：错误
     */
    private static final int error = 4;
    /**
     * 消息所属和消息内容的分隔符
     */
    private static final String msgSplit = ":";
    /**
     * 该类的名称，用于识别该类的堆栈
     */
    private static final String thisClassName = Log.class.getName();

    /**
     * 默认输出日志的日志工具：log4j
     */
    private static Logger logger = LoggerFactory.getLogger(Log.class);

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String args[]) {
        Log.info("调试信息");
    }

    /**
     * 根据日志级别，输出日志
     *
     * @param level   日志级别
     * @param message 日志消息
     * @param ste     堆栈信息
     */
    private static synchronized void log(int level, Object message, StackTraceElement[] ste) {
        if (ste != null) {
            // 加入源代码定位
            message = getStackMsg(ste) + msgSplit + message;
        }
        switch (level) {
            case debug:
                logger.debug(message.toString());
                break;
            case info:
                logger.info(message.toString());
                break;
            case warn:
                logger.warn(message.toString());
                break;
            case error:
                logger.error(message.toString());
                break;
            default:
                logger.debug(message.toString());
        }
    }

    /**
     * 根据堆栈信息得到源代码行信息
     *
     * @param ste 堆栈信息
     * @return 调用输出日志的代码所在的类.方法.代码行的相关信息
     * <p>
     * 如: com.MyClass 类里的 fun() 方法调用了Logs.debug("test");
     * <p>
     * 则堆栈信息为: com.MyClass.fun(MyClass.java 代码行号)
     */
    private static String getStackMsg(StackTraceElement[] ste) {
        if (ste == null)
            return null;
        boolean srcFlag = false;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement s = ste[i];
            // 如果上一行堆栈代码是本类的堆栈
            // 则该行代码则为源代码的原始堆栈
            if (srcFlag) {
                return s == null ? "" : s.toString();
            }
            // 定位本类的堆栈
            if (thisClassName.equals(s.getClassName())) {
                srcFlag = true;
            }
        }
        return null;
    }

    /**
     * 输出debug信息
     *
     * @param message
     */
    public static void debug(Object message) {
        // 如果禁止日志或者输出级别不符，则返回
        if (!enabled || debug < level)
            return;
        if (showLocSrc) {
            log(debug, message, Thread.currentThread().getStackTrace());
        } else {
            log(debug, message, null);
        }
    }

    /**
     * 输出info信息
     *
     * @param message
     */
    public static void info(Object message) {
        // 如果禁止日志或者输出级别不符，则返回
        if (!enabled || info < level)
            return;
        if (showLocSrc) {
            log(info, message, Thread.currentThread().getStackTrace());
        } else {
            log(info, message, null);
        }
    }

    /**
     * 输出warn信息
     *
     * @param message
     */
    public static void warn(Object message) {
        // 如果禁止日志或者输出级别不符，则返回
        if (!enabled || warn < level)
            return;
        if (showLocSrc) {
            log(warn, message, Thread.currentThread().getStackTrace());
        } else {
            log(warn, message, null);
        }
    }

    /**
     * 输出error信息
     *
     * @param message
     */
    public static void error(Object message) {
        // 如果禁止日志或者输出级别不符，则返回
        if (!enabled || error < level)
            return;
        if (showLocSrc) {
            log(error, message, Thread.currentThread().getStackTrace());
        } else {
            log(error, message, null);
        }
    }
}