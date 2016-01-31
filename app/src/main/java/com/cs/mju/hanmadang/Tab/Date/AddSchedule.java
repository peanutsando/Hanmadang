package com.cs.mju.hanmadang.Tab.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.Function.PushJsonParser;
import com.cs.mju.hanmadang.R;
import com.cs.mju.hanmadang.Tab.DateActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class AddSchedule extends ActionBarActivity implements View.OnClickListener {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    TextView currentTime, titleBar;
    DateTimePicker dateTimePicker;
    private Button addButton, cancelButton;
    // 텍스트 값
    private EditText tempTitle, tempPlace, tempContent;
    private String title, place, content, timestamp;
    int selectedYear, selectedMonth, selectedDay, currentHour, currentMinute, currentSecond;
    // 결과 인텐트
    final Intent resultIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chooser);
        calCurrentTime();
        // 버튼 참조
        initViews();

        addListenersToView();

        // 이벤트 처리
        dateTimePicker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            public void onDateTimeChanged(DateTimePicker view, int year,
                                          int monthOfYear, int dayOfYear, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(getIntent().getIntExtra("selectedYear", 0), getIntent().getIntExtra("selectedMonth", 0), getIntent().getIntExtra("selectedDay", 0), hourOfDay, minute);
                timestamp = year + "-" + (monthOfYear + 1) + "-" + dayOfYear + " " + hourOfDay + ":" + minute + ":" + currentSecond;

                // 결과 인테트에 저장
                resultIntent.putExtra("title", title);
                resultIntent.putExtra("place", place);
                resultIntent.putExtra("content", content);
                resultIntent.putExtra("year", year);
                resultIntent.putExtra("monthOfYear", monthOfYear);
                resultIntent.putExtra("dayOfYear", dayOfYear);
                resultIntent.putExtra("hourOfDay", hourOfDay);
                resultIntent.putExtra("minute", minute);

                // 바뀐 시간 텍스트뷰에 표시
                currentTime.setText(dateFormat.format(calendar.getTime()));
            }
        });

        // 현재 시간 텍스트뷰에 표시
        initTime();
    }

    private void initViews() {
        titleBar = (TextView)findViewById(R.id.titleBar);
        currentTime = (TextView)findViewById(R.id.currentTime);
        dateTimePicker = (DateTimePicker)findViewById(R.id.dateTimePicker);
        tempTitle = (EditText)findViewById(R.id.editTitle);
        tempPlace = (EditText)findViewById(R.id.editPlace);
        tempContent = (EditText)findViewById(R.id.editContent);
        addButton = (Button)findViewById(R.id.addButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);

        // 수정페이지 일 경우 텍스트 입력
        if(getIntent().getStringExtra("title")!=null){
            tempTitle.setText(getIntent().getStringExtra("title"));
            tempPlace.setText(getIntent().getStringExtra("place"));
            tempContent.setText(getIntent().getStringExtra("content"));
        }
        // 추가 버튼 수정 버튼으로 체인지
        if (getIntent().getStringExtra("title")!=null)
            addButton.setBackgroundResource(R.drawable.schedule_mod_btn);
        else
            addButton.setBackgroundResource(R.drawable.schedule_write_btn);
        // 일정 추가 버튼 추가 버튼으로 체인지
        if (getIntent().getStringExtra("title")!=null)
            titleBar.setText("일정 수정");
    }

    // 현재시간 텍스트뷰에 표시
    private void initTime(){
        if(getIntent().getStringExtra("title")!=null){ // 업데이트 일 시 현재시간이 아닌 받아온 시간을 표시
            StringTokenizer tokens = new StringTokenizer(getIntent().getStringExtra("timestamp"));
            String year = tokens.nextToken("-");
            String month = tokens.nextToken("-");

            String date = tokens.nextToken(" ");
            String[] temp = date.split("-");

            String hour = tokens.nextToken(":");
            String[] temp2 = hour.split(" ");

            String minute = tokens.nextToken(":");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(temp[1]), Integer.parseInt(temp2[1]), Integer.parseInt(minute));
            currentTime.setText(dateFormat.format(calendar.getTime()));
        }else{ // 그냥  추가일 경우 현재 시간
            Calendar calendar = Calendar.getInstance();
            calendar.set(getIntent().getIntExtra("selectedYear", 0), getIntent().getIntExtra("selectedMonth", 0), getIntent().getIntExtra("selectedDay", 0), currentHour, currentMinute);
            currentTime.setText(dateFormat.format(calendar.getTime()));
        }
    }

    // 현재 시간 계산하기
    private void calCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        currentHour = calendar.get(calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(calendar.MINUTE);
        currentSecond = calendar.get(calendar.SECOND);

        timestamp = getIntent().getIntExtra("selectedYear", 0) + "-" + (getIntent().getIntExtra("selectedMonth",0)+1) + "-" + getIntent().getIntExtra("selectedDay",0) + " " +currentHour + ":" + currentMinute + ":" + currentSecond;
    }

    private void addListenersToView() {
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.cancelButton) {
            setResult(2, resultIntent);
        }else if(v.getId() == R.id.addButton) {
            Intent resultIntent = new Intent();
            setResult(1, resultIntent);
            writedTextSave();
            if(getIntent().getStringExtra("title")!=null){
                updateData();
            }else{
                saveData();
            }
            PushJsonParser pushJsonParser = new PushJsonParser();
            Constants.num = 2;
            pushJsonParser.sendPushMessage(timestamp + " " + Constants.NEW_DATE);
        }
        finish();
    }

    private void writedTextSave() {
        if(tempTitle.length()==0){
            title = "내용없음";
        }else{
            title = tempTitle.getText().toString();
        }
        if(tempPlace.length()==0){
            place = "내용없음";
        }else{
            place = tempPlace.getText().toString();
        }
        if(tempContent.length()==0){
            content = "내용없음";
        }else{
            content = tempContent.getText().toString();
        }
    }

    private void saveData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Constants.SCHEDULE_SAVE_URL);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    /* 데이터 전송, &*&은 데이터를 구분할 토큰 */
                    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                    out.write(title);
                    out.write("&*&");
                    out.write(place);
                    out.write("&*&");
                    out.write(content);
                    out.write("&*&");
                    out.write(timestamp);

                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                }catch(Exception e) {
                    Log.d("Exception", e.toString());
                }
            }
        }).start();

        this.finish();
    }

    private void updateData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Constants.SCHEDULE_MOD_URL);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    Log.e("이전 제목", getIntent().getStringExtra("title"));
                    Log.e("이전 장소", getIntent().getStringExtra("place"));
                    Log.e("이전 내용", getIntent().getStringExtra("content"));
                    Log.e("이전 시간", getIntent().getStringExtra("timestamp"));
                    Log.e("바뀐 제목", title);
                    Log.e("바뀐 장소", place);
                    Log.e("바뀐 내용", content);
                    Log.e("바뀐 시간", timestamp);
                    /* 데이터 전송, &*&은 데이터를 구분할 토큰 */
                    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                    out.write(getIntent().getStringExtra("title"));
                    out.write("&*&");
                    out.write(getIntent().getStringExtra("place"));
                    out.write("&*&");
                    out.write(getIntent().getStringExtra("content"));
                    out.write("&*&");
                    out.write(getIntent().getStringExtra("timestamp"));
                    out.write("@@");
                    out.write(title);
                    out.write("&*&");
                    out.write(place);
                    out.write("&*&");
                    out.write(content);
                    out.write("&*&");
                    out.write(timestamp);

                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                }catch(Exception e) {
                    Log.d("Exception", e.toString());
                }
            }
        }).start();

        this.finish();
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