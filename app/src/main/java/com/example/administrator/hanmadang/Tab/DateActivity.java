package com.example.administrator.hanmadang.Tab;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.hanmadang.R;
import com.example.administrator.hanmadang.Tab.Date.AddSchedule;
import com.example.administrator.hanmadang.Tab.Date.CalendarMonthAdapter;
import com.example.administrator.hanmadang.Tab.Date.CalendarMonthView;
import com.example.administrator.hanmadang.Tab.Date.MonthItem;
import com.example.administrator.hanmadang.Tab.Date.OnDataSelectionListener;

import java.util.Calendar;

/*
public class DateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "일정추가는 아직미구현", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
*/

public class DateActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_Add = 1001;

    //월별 캘린더 뷰 객체
    CalendarMonthView monthView;

    //월별 캘린더 어댑터
    CalendarMonthAdapter monthViewAdapter;

    // 월을 표시하는 텍스트뷰
    TextView monthText;

    // 현재 연도
    int curYear;

    // 현재 월
    int curMonth;

    // 달력에서 선택한 년, 월, 일, 시간, 분
    int selectedYear, selectedMonth, selectedDay, currentHour, currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(getApplicationContext(), AddSchedule.class);
                    intent.putExtra("selectedYear", selectedYear);
                    intent.putExtra("selectedMonth", selectedMonth);
                    intent.putExtra("selectedDay", selectedDay);
                    startActivityForResult(intent, REQUEST_CODE_Add);
                }catch(ActivityNotFoundException ex){
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 월별 캘린더 뷰 객체 참조
        monthView = (CalendarMonthView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);
        selectedYear = monthViewAdapter.getCurYear();
        selectedMonth = monthViewAdapter.getCurMonth();

        // 리스너 설정
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                selectedYear = monthViewAdapter.getCurYear();
                selectedMonth = monthViewAdapter.getCurMonth();
                selectedDay = curItem.getDay();
            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    // 월 표시 텍스트 설정
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth+1) + "월");
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
//        int year = data.getExtras().getInt("year", 0);
        if(resultCode == 1){
//            Toast.makeText(this, "응답으로 전달된 년도" + year, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "데이터베이스에 저장", Toast.LENGTH_LONG).show();
        }
    }
}
