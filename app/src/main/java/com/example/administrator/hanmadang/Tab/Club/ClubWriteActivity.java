package com.example.administrator.hanmadang.Tab.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //////////
        setContentView(R.layout.activity_club_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("","들어왔니?");
        initViews();
        addListenersToView();
    }


    private void initViews() {
        writeButton = (Button)findViewById(R.id.writeButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
    }

    private void addListenersToView() {
        writeButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }


    public void onClick(View v) {
        if(v.getId() == R.id.cancelButton) {
            Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
            this.finish();
        }else if(v.getId() == R.id.writeButton) {
            Toast.makeText(getApplicationContext(), "글쓰기 미구현", Toast.LENGTH_LONG).show();
            this.finish();
        }
    }
}
