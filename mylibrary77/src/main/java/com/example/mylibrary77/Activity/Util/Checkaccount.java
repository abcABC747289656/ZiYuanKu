package com.example.mylibrary77.Activity.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkaccount {

    //判断是否为邮箱号
    public static Boolean isEmail(String email) {
        Boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (email.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    //判断是否为手机号
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern
                .compile("^(13[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
