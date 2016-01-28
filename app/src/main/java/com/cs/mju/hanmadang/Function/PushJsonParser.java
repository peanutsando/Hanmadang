package com.cs.mju.hanmadang.Function;

import android.os.StrictMode;
import android.util.Log;

import com.cs.mju.hanmadang.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
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

    public void getTokenKeyFromURL(String url) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                JsonReader reader = new JsonReader(new InputStreamReader(new URL(url).openStream()));
                Gson gson = new Gson();
                Type getToken = new TypeToken<ArrayList<keyHandler>>() {
                }.getType();
                object = gson.fromJson(reader, getToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPushMessage(String title) {
        List<String> keyList = new ArrayList<String>();
        Sender sender = new Sender(Constants.GOOGLE_API_KEY);
        Message message = new Message.Builder().addData("message", title).build();
        getTokenKeyFromURL(Constants.REG_URL);
        MulticastResult result;
        int size = object.size();
        for (int i = 0; i < size; i++) {
            keyList.add(i, object.get(i).getReg_key());
        }
        try {
            result = sender.send(message, keyList, 1);
            if (result != null) {
                List<Result> results = result.getResults();
                for (Result result0 : results) {
                    Log.e("###", result0.getMessageId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
