package com.cs.mju.hanmadang.Tab.Date;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.mju.hanmadang.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Joguk_1 on 2016-01-13.
 */
public class SelectSchedule extends ActionBarActivity {
    public static final int REQUEST_CODE_SELECTADD = 1002;
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    ListView listView1;
    ScheduleListAdapter adapter;
    TextView textView1;
    int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);

        // 리스트뷰 객체 참조
        listView1 = (ListView) findViewById(R.id.listView1);
        // 텍스트뷰 객체 참조
        textView1 = (TextView)findViewById(R.id.textView1);
        setDate();

        // 어댑터 객체 생성
        adapter = new ScheduleListAdapter(this);

        // 아이템 데이터 만들기
        Resources res = getResources();
        adapter.addItem(new ScheduleItem("최종 제출", "최종 제출!!", "컴공과사"));
        adapter.addItem(new ScheduleItem("레이아웃 짜기", "레이아웃 마감", "Git"));
        adapter.addItem(new ScheduleItem("2주 남았당", "유후", "하아"));

        // 리스트뷰에 어댑터 설정
        listView1.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleItem curItem = (ScheduleItem) adapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();
            }
        });

        // 백버튼 이벤트 처리
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(3);
                finish();
            }
        });

        // 글쓰기 버튼
        Button writeButton = (Button) findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                selectedYear = calendar.get(calendar.YEAR);
//                selectedMonth = calendar.get(calendar.MONTH);
//                selectedDay = calendar.get(calendar.DATE);

                Intent intent = new Intent(getApplicationContext(), AddSchedule.class);
//                intent.putExtra("selectedYear", selectedYear);
//                intent.putExtra("selectedMonth", selectedMonth);
//                intent.putExtra("selectedDay", selectedDay);
                intent.putExtra("selectedYear", getIntent().getIntExtra("selectedYear",0));
                intent.putExtra("selectedMonth", getIntent().getIntExtra("selectedMonth",0));
                intent.putExtra("selectedDay", getIntent().getIntExtra("selectedDay",0));
                startActivityForResult(intent, REQUEST_CODE_SELECTADD);
            }
        });
    }

    private void setDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(getIntent().getIntExtra("selectedYear",0), getIntent().getIntExtra("selectedMonth",0), getIntent().getIntExtra("selectedDay",0));
        textView1.setText(dateFormat.format(calendar.getTime()));
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
//            Toast.makeText(this, "응답으로 전달된 년도" + year, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "데이터베이스에 저장.", Toast.LENGTH_LONG).show();
        }else if(resultCode == 2){
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
        }
    }
}