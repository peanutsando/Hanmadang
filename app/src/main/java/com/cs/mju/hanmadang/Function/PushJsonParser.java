package com.cs.mju.hanmadang.Function;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 하옹 on 2016-01-29.
 */
public class PushJsonParser {
    public List<keyHandler> object;

    public PushJsonParser() {
    }

    public void getTokenKeyFromURL(String url){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try{
                JsonReader reader = new JsonReader(new InputStreamReader(new URL(url).openStream()));
                Gson gson = new Gson();
                Type getToken = new TypeToken<ArrayList<keyHandler>>(){
                }.getType();
                object = gson.fromJson(reader, getToken);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
