package com.sanqing.service;

/**
 * Created by admin on 2017/7/6.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取Properties属性文件
 *
 * @author LYF
 */
public class Env extends Properties {

    private static Env instance;

    public static Env getInstance() {
        return instance;
    }

    public static Env getInstance(String path) {
        if (instance != null) {
            return instance;
        } else {
            makeInstance(path);
            return instance;
        }
    }

    private static synchronized void makeInstance(String path) {
        if (instance == null) {
            instance = new Env(path);
        }
    }

    private Env(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        try {
            load(is);
        } catch (IOException e) {
            System.err.println("错误信息:  读取属性文件失败！");
            System.err.println("请确认 【" + path + "】 文件是否存在。");
        }
    }

}