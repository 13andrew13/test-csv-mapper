package com.andrew.csvmapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String key = "user/user_id.png";
        Pattern pattern = Pattern.compile("user/(?<filename>(?<userId>\\w+)\\.(?<filetype>png|jpeg|jpg))$");
        Matcher matcher = pattern.matcher(key);
        boolean b = matcher.find();
        System.out.println(matcher.group("filetype"));
    }
}
