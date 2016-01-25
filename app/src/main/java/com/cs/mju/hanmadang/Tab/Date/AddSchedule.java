package com.cs.mju.hanmadang.Tab.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.cs.mju.hanmadang.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddSchedule extends ActionBarActivity implements View.OnClickListener {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    TextView textView1;
    DateTimePicker dateTimePicker;
    private Button addButton, cancelButton;
    // 텍스트 값
    EditText tempSchedule, tempPlace, tempExplain;
    String schedule, place, explain;
    int selectedYear, selectedMonth, selectedDay, currentHour, currentMinute;
    // 결과 인텐트
    final Intent resultIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chooser);
        calCurrentTime();

        textView1 = (TextView)findViewById(R.id.textView1);
        dateTimePicker = (DateTimePicker)findViewById(R.id.dateTimePicker);
        tempSchedule = (EditText)findViewById(R.id.editSchedule);
        tempPlace = (EditText)findViewById(R.id.editPlace);
        tempExplain = (EditText)findViewById(R.id.editExplain);

        schedule = tempSchedule.toString();
        place = tempPlace.toString();
        explain = tempExplain.toString();

        initViews();
        addListenersToView();

        // 이벤트 처리
        dateTimePicker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            public void onDateTimeChanged(DateTimePicker view, int year,
                                          int monthOfYear, int dayOfYear, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfYear, hourOfDay, minute);

                // 결과 인테트에 저장
                resultIntent.putExtra("schedule", schedule);
                resultIntent.putExtra("place", place);
                resultIntent.putExtra("explain", explain);
                resultIntent.putExtra("year", year);
                resultIntent.putExtra("monthOfYear", monthOfYear);
                resultIntent.putExtra("dayOfYear", dayOfYear);
                resultIntent.putExtra("hourOfDay", hourOfDay);
                resultIntent.putExtra("minute", minute);

                // 바뀐 시간 텍스트뷰에 표시
                textView1.setText(dateFormat.format(calendar.getTime()));
            }
        });

        // 현재 시간 텍스트뷰에 표시
        Calendar calendar = Calendar.getInstance();
//      calendar.set(dateTimePicker.getYear(), dateTimePicker.getMonth(), dateTimePicker.getDayOfMonth(), dateTimePicker.getCurrentHour(), dateTimePicker.getCurrentMinute());
        calendar.set(getIntent().getIntExtra("selectedYear",0), getIntent().getIntExtra("selectedMonth",0), getIntent().getIntExtra("selectedDay",0), currentHour, currentMinute);
        textView1.setText(dateFormat.format(calendar.getTime()));

    }

    private void initViews() {
        addButton = (Button)findViewById(R.id.addButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
    }

    // 현재 시간 계산하기
    private void calCurrentTime(){
        Calendar calendar = Calendar.getInstance();
//        selectedYear = calendar.get(calendar.YEAR);
//        selectedMonth = calendar.get(calendar.MONTH);
//        selectedDay = calendar.get(calendar.DATE);
        currentHour = calendar.get(calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(calendar.MINUTE);
    }

    private void addListenersToView() {
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.cancelButton) {
            setResult(2, resultIntent);
            finish();
        }else if(v.getId() == R.id.addButton) {
            Intent resultIntent = new Intent();
            setResult(1, resultIntent);
            finish();
        }
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
