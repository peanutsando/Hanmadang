package com.example.administrator.hanmadang.Function;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-01-06.
 */
public class BackKeyHandler {
    private long keyPressTime = 0;
    private Toast toast;
    private Activity activity;

    public BackKeyHandler(Activity context){
        this.activity = context;
    }

    public void onBackPressed(){
        Log.e(getClass().getSimpleName(), "뒤로가기눌렀을때 if문");
        if(System.currentTimeMillis() > keyPressTime + 2000){
            keyPressTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= keyPressTime + 2000){
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide(){
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", toast.LENGTH_SHORT);

        toast.show();
    }
}
