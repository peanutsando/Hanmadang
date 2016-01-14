package com.example.administrator.hanmadang.Function;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.hanmadang.Constants;

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
        toast = Toast.makeText(activity, Constants.BACK_BTN_CLICK, toast.LENGTH_SHORT);

        toast.show();
    }
}
