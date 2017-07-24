package com.sanqing.kit;

import java.util.Date;

/**
 * Created by admin on 2017/7/6.
 */
public class CommonUtil {
    public static Date getNowDate() {
        Date date = new Date();
        return date;
    }

    public static void main(String[] args) {
        System.out.println(getNowDate());
    }
}
