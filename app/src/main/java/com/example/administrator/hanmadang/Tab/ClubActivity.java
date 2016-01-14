
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
    private static final int B_ACTIVITY=0;

    ListView listView;
    WriteListAdapter adapter;
    String writer;
    String timestamp;
    String title;

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
                startActivityForResult(intent, B_ACTIVITY);
            }
        });
        // 어댑터 객체 생성
        listView = (ListView) findViewById(R.id.writeList);
        adapter = new WriteListAdapter(this);

        adapter.addItem(new WriteItem("게시판에 온 것을 환영합니다!", "2016-01-14", "관리자"));
        adapter.notifyDataSetChanged();

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

    public void createListView() {
        // 아이템 등록
        adapter.addItem(new WriteItem(title, timestamp, writer));
        adapter.notifyDataSetChanged();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case B_ACTIVITY:
                    writer = intent.getExtras().getString("writer");
                    timestamp = intent.getExtras().getString("timestamp");
                    title = intent.getExtras().getString("title");

                    createListView();

                    Toast.makeText(this, "됩니까", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show();
        }
    }
}
