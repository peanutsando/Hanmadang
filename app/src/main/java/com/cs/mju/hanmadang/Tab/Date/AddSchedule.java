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
import com.cs.mju.hanmadang.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddSchedule extends ActionBarActivity implements View.OnClickListener {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    TextView currentTime;
    DateTimePicker dateTimePicker;
    private Button addButton, cancelButton;
    // 텍스트 값
    EditText tempTitle, tempPlace, tempContent;
    String title, place, content, timestamp;
    int selectedYear, selectedMonth, selectedDay, currentHour, currentMinute, currentSecond;
    // 결과 인텐트
    final Intent resultIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chooser);
        calCurrentTime();

        // 버튼 참조
        currentTime = (TextView)findViewById(R.id.currentTime);
        dateTimePicker = (DateTimePicker)findViewById(R.id.dateTimePicker);
        tempTitle = (EditText)findViewById(R.id.editTitle);
        tempPlace = (EditText)findViewById(R.id.editPlace);
        tempContent = (EditText)findViewById(R.id.editContent);

        initViews();
        addListenersToView();

        // 이벤트 처리
        dateTimePicker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            public void onDateTimeChanged(DateTimePicker view, int year,
                                          int monthOfYear, int dayOfYear, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(getIntent().getIntExtra("selectedYear",0), getIntent().getIntExtra("selectedMonth",0), getIntent().getIntExtra("selectedDay",0), hourOfDay, minute);
                timestamp = year + "-" + (monthOfYear+1) + "-" + dayOfYear + " " + hourOfDay  + ":" + minute + ":" + currentSecond;

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
        Calendar calendar = Calendar.getInstance();
//      calendar.set(dateTimePicker.getYear(), dateTimePicker.getMonth(), dateTimePicker.getDayOfMonth(), dateTimePicker.getCurrentHour(), dateTimePicker.getCurrentMinute());
        calendar.set(getIntent().getIntExtra("selectedYear",0), getIntent().getIntExtra("selectedMonth",0), getIntent().getIntExtra("selectedDay",0), currentHour, currentMinute);
        currentTime.setText(dateFormat.format(calendar.getTime()));

    }

    private void initViews() {
        addButton = (Button)findViewById(R.id.addButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
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
            finish();
        }else if(v.getId() == R.id.addButton) {
            Intent resultIntent = new Intent();
            setResult(1, resultIntent);
            writedTextSave();
            saveData();
            finish();
        }
    }

    private void writedTextSave() {
        title = tempTitle.getText().toString();
        place = tempPlace.getText().toString();
        content = tempContent.getText().toString();
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
