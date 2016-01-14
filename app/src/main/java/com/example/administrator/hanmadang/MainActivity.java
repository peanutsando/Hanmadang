package com.example.administrator.hanmadang;

import android.app.TabActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.administrator.hanmadang.Tab.ClubActivity;
import com.example.administrator.hanmadang.Tab.DateActivity;
import com.example.administrator.hanmadang.Tab.NoticeActivity;

public class MainActivity extends TabActivity{
    private TabHost tabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        tabHost.addTab(tabHost.newTabSpec("Notice").setIndicator(getString(R.string.notice)).setContent(new Intent(this, NoticeActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Club").setIndicator(getString(R.string.club)).setContent(new Intent(this, ClubActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Date").setIndicator(getString(R.string.date)).setContent(new Intent(this, DateActivity.class)));

        tabHost.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
