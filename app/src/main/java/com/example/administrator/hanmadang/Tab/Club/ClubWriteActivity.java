package com.example.administrator.hanmadang.Tab.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.hanmadang.R;
import com.example.administrator.hanmadang.Tab.ClubActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClubWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button writeButton;
    private Button cancelButton;

    Bundle extra;
    Intent intent;

    long now;
    Date date;
    SimpleDateFormat curDateFormat;
    String strCurDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        addListenersToView();
    }

    private void initViews() {
        now = System.currentTimeMillis();
        date = new Date(now);
        curDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        strCurDate = curDateFormat.format(date);

        writeButton = (Button)findViewById(R.id.writeButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
    }

    private void addListenersToView() {
        writeButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        intent = getIntent();

        if(v.getId() == R.id.cancelButton) {
            this.setResult(RESULT_CANCELED, intent);
            this.finish();
        }else if(v.getId() == R.id.writeButton) {
            extra = new Bundle();

            extra.putString("writer", "임의");
            extra.putString("timestamp", strCurDate);
            extra.putString("title", "임의의 제목입니다");
            intent.putExtras(extra);
            this.setResult(RESULT_OK, intent);

            this.finish();
        }
    }
}
