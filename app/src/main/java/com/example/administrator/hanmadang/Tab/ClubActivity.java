
package com.example.administrator.hanmadang.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.hanmadang.Function.BackKeyHandler;
import com.example.administrator.hanmadang.Tab.Club.ClubReadActivity;
import com.example.administrator.hanmadang.Tab.Club.ClubWriteActivity;
import com.example.administrator.hanmadang.R;
import com.example.administrator.hanmadang.Tab.Club.WriteItem;
import com.example.administrator.hanmadang.Tab.Club.WriteListAdapter;

public class ClubActivity extends AppCompatActivity {
    private static final int WRITE_ACTIVITY=0;
    private static final int READ_ACTIVITY=1;
    private BackKeyHandler keyHandler;

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

        keyHandler = new BackKeyHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubWriteActivity.class);
                startActivityForResult(intent, WRITE_ACTIVITY);
            }
        });
        // 어댑터 객체 생성
        listView = (ListView) findViewById(R.id.writeList);
        adapter = new WriteListAdapter(this);

        adapter.addItem(new WriteItem("게시판에 온 것을 환영합니다!", "2016-01-14", "관리자"));
        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 WriteItem curItem = (WriteItem) adapter.getItem(position);
                 String[] curData = curItem.getData();

                 Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();
                 */
                Intent intent = new Intent(getApplicationContext(), ClubReadActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createListView() {
        // 아이템 등록
        if(title == null)
            Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_LONG).show();
        else {
            adapter.addItem(new WriteItem(title, timestamp, writer));
            ((WriteListAdapter)listView.getAdapter()).notifyDataSetInvalidated();
            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case WRITE_ACTIVITY:
                    writer = intent.getExtras().getString("writer");
                    timestamp = intent.getExtras().getString("timestamp");
                    title = intent.getExtras().getString("title");

                    // 아이템 등록
                    createListView();
                case READ_ACTIVITY:
                    break;
            }
        }else {
            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed(){
        keyHandler.onBackPressed();
    }
}
