package com.example.administrator.hanmadang.Tab;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.hanmadang.Tab.Club.ClubWriteActivity;
import com.example.administrator.hanmadang.R;
import com.example.administrator.hanmadang.Tab.Club.WriteItem;
import com.example.administrator.hanmadang.Tab.Club.WriteListAdapter;
import com.example.administrator.hanmadang.Tab.Notice.NoticeItem;

import java.util.ArrayList;

public class ClubActivity extends AppCompatActivity {
    ListView listView;
    WriteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubWriteActivity.class);
                startActivity(intent);
            }
        });
        // 어댑터 객체 생성
        listView = (ListView) findViewById(R.id.writeList);

        // 어댑터 객체 생성
        adapter = new WriteListAdapter(this);

        // 아이템 데이터 만들기
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "900 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "1000 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "800 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "700 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "900 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "1000 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "800 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "700 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "900 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "1000 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "800 원"));
        adapter.addItem(new WriteItem("추억의 테트리스", "30,000 다운로드", "700 원"));

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WriteItem curItem = (WriteItem) adapter.getItem(position);
                String title = curItem.getTitle();

                Toast.makeText(getApplicationContext(), "Selected : " + title, Toast.LENGTH_LONG).show();
            }
        });
    }
}
