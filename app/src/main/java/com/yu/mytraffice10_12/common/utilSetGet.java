package com.yu.mytraffice10_12.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 小新 on 2017/10/15.
 */

public class  utilSetGet {
    static urlBeanHttp http;
    static String urlhttp="http";
    static String urlport="port";
    static String username,userpass;
    public static void saveSettig(Context context,String http,String port){
        SharedPreferences preferences = context.getSharedPreferences("http",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(urlhttp,http);
        editor.putString(urlport,port);
        editor.commit();
    }
    public static urlBeanHttp loadSetting(Context context){
        http = new urlBeanHttp();
        SharedPreferences preferences = context.getSharedPreferences("http",Context.MODE_PRIVATE);
        http.setUrlHttp(preferences.getString(urlhttp,""));
        http.setUrlPort(preferences.getString(urlport,""));
        return http;
    }

}
