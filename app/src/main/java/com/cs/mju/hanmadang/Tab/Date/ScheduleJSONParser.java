package com.cs.mju.hanmadang.Tab.Date;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joguk_1 on 2016-01-26.
 */
public class ScheduleJSONParser {
    public List<ScheduleData> object;

    public ScheduleJSONParser() {
    }

    public void parseJSONFromURL(String url) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                JsonReader reader = new JsonReader(new InputStreamReader(new URL(url).openStream()));
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<ScheduleData>>() {
                }.getType();
                object = gson.fromJson(reader, listType);

                    for (int i = 0; i < object.size(); i++) {
                        Log.e("Sevlet Android text", object.get(i).getTitle() + " " + object.get(i).getPlace() + " " + object.get(i).getContent() + " " + object.get(i).getTimestamp());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
