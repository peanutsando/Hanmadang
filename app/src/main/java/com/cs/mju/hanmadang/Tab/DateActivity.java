package com.cs.mju.hanmadang.Tab;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.mju.hanmadang.Function.BackKeyHandler;
import com.cs.mju.hanmadang.R;
import com.cs.mju.hanmadang.Tab.Date.AddSchedule;
import com.cs.mju.hanmadang.Tab.Date.CalendarMonthAdapter;
import com.cs.mju.hanmadang.Tab.Date.CalendarMonthView;
import com.cs.mju.hanmadang.Tab.Date.MonthItem;
import com.cs.mju.hanmadang.Tab.Date.OnDataSelectionListener;
import com.cs.mju.hanmadang.Tab.Date.SelectSchedule;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD = 1001;
    private BackKeyHandler keyHandler;

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
        keyHandler = new BackKeyHandler(this);

        initDate();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(getApplicationContext(), AddSchedule.class);
                    intent.putExtra("selectedYear", selectedYear);
                    intent.putExtra("selectedMonth", selectedMonth);
                    intent.putExtra("selectedDay", selectedDay);
                    startActivityForResult(intent, REQUEST_CODE_ADD);
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

                // 날짜 없는 탭을 누를 경우 엑티비티 실행 X
                if(curItem.getDay() != 0) {
                    selectedYear = monthViewAdapter.getCurYear();
                    selectedMonth = monthViewAdapter.getCurMonth();
                    selectedDay = curItem.getDay();

                    Intent intent = new Intent(getApplicationContext(), SelectSchedule.class);
                    intent.putExtra("selectedYear", selectedYear);
                    intent.putExtra("selectedMonth", selectedMonth);
                    intent.putExtra("selectedDay", selectedDay);
                    startActivityForResult(intent, REQUEST_CODE_ADD);
                }
            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedMonth--;
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedMonth++;
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(calendar.YEAR);
        selectedMonth = calendar.get(calendar.MONTH);
        selectedDay = calendar.get(calendar.DATE)+1;
    }

    // 월 표시 텍스트 설정
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth+1) + "월");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
//            Toast.makeText(this, "응답으로 전달된 년도" + year, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "데이터베이스에 저장.", Toast.LENGTH_LONG).show();
            initDate();
        }else if(resultCode == 2){
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
            initDate();
        }else if(resultCode == 3){
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
        }
    }

    /* 뒤로가기 기능 */
    @Override
    public void onBackPressed(){
        keyHandler.onBackPressed();
    }
}
