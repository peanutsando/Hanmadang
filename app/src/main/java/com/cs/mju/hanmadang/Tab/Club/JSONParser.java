package com.cs.mju.hanmadang.Tab.Club;

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
 * Created by park on 2016-01-26.
 */
public class JSONParser {
    public List<User> object;

    public JSONParser() {
    }

    public void parseJSONFromURL(String url) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                JsonReader reader = new JsonReader(new InputStreamReader(new URL(url).openStream()));
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<User>>() {
                }.getType();
                object = gson.fromJson(reader, listType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
