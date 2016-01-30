package com.cs.mju.hanmadang;

import android.app.TabActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TabHost;

import com.cs.mju.hanmadang.Function.push.RegistrationIntentService;
import com.cs.mju.hanmadang.Tab.ClubActivity;
import com.cs.mju.hanmadang.Tab.DateActivity;
import com.cs.mju.hanmadang.Tab.NoticeActivity;

/**
 * Created on Youthink on 2016-01-10.
 */
public class MainActivity extends TabActivity {
    private TabHost tabHost = null;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTabHost(Constants.num);
    }


    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void createTabHost(int pos) {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        tabHost.addTab(tabHost.newTabSpec("Notice").setIndicator(getString(R.string.notice)).setContent(new Intent(this, NoticeActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Club").setIndicator(getString(R.string.club)).setContent(new Intent(this, ClubActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Date").setIndicator(getString(R.string.date)).setContent(new Intent(this, DateActivity.class)));

        tabHost.setCurrentTab(pos);
    }
}