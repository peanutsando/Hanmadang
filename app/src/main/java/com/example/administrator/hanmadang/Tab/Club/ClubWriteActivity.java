package com.example.administrator.hanmadang.Tab.Club;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.hanmadang.R;
import com.example.administrator.hanmadang.Tab.ClubActivity;
import com.example.administrator.hanmadang.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClubWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button writeButton;
    private Button cancelButton;

    Bundle extra;
    Intent intent;

    // 날짜를 구현하기 위한 변수
    long now;
    Date date;
    SimpleDateFormat curDateFormat;

    // 제목 입력 EditText
    EditText inputTitle;

    // 작성자 선택 textView
    TextView selectWriter;

    // Key 필드
    EditText keyFiled;
    Button keyButton;

    // ClubActivity 로 전달하는 제목, 날짜, 작성자
    String title;
    String writer;
    String strCurDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        addListenersToView();
    }

    private void DialogRadio() {
        final String[] writerList = {"AO", "인클루드", "한울"};
        AlertDialog.Builder alt_builder = new AlertDialog.Builder(this);

        alt_builder.setIcon(R.drawable.icon);
        alt_builder.setTitle("동아리를 선택해주세요");
        alt_builder.setSingleChoiceItems(writerList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "동아리명 = " + writerList[which], Toast.LENGTH_LONG).show();
                dialog.cancel();

                selectWriter.setText(writerList[which]);

                writer = writerList[which];
            }
        });

        AlertDialog alert = alt_builder.create();
        alert.show();
    }

    private void initViews() {
        // 날짜 변수 초기화
        now = System.currentTimeMillis();
        date = new Date(now);
        curDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        strCurDate = curDateFormat.format(date);

        // 글쓰기, 취소 버튼
        writeButton = (Button)findViewById(R.id.writeButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        writeButton.setEnabled(false);
        writeButton.setFocusable(false);

        // 제목 입력 EditText
        inputTitle = (EditText)findViewById(R.id.inputTitle);

        // 작성자 선택 textView
        selectWriter = (TextView)findViewById(R.id.inputWriter);

        // key EditText
        keyFiled = (EditText)findViewById(R.id.inputKey);
        keyButton = (Button)findViewById(R.id.keyButton);
    }

    private void addListenersToView() {
        writeButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        selectWriter.setOnClickListener(this);
        keyButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.cancelButton) {
            intent = getIntent();
            this.setResult(RESULT_CANCELED, intent);
            this.finish();
        }else if(v.getId() == R.id.writeButton) {
            intent = getIntent();
            extra = new Bundle();

            if(inputTitle.getText().toString().length() == 0)
                title = null;
            else
                title = inputTitle.getText().toString();

            extra.putString("writer", writer);
            extra.putString("timestamp", strCurDate);
            extra.putString("title", title);
            intent.putExtras(extra);
            this.setResult(RESULT_OK, intent);

            this.finish();
        }else if(v.getId() == R.id.inputWriter) {
            DialogRadio();
        }else if(v.getId() == R.id.keyButton) {
            if(writer.equals("AO")) {
                if(keyFiled.getText().toString().equals(Constants.CLUB_AO)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                }else {
                    Toast.makeText(getApplicationContext(), "AO에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }
            }else if(writer.equals("인클루드")) {
                if(keyFiled.getText().toString().equals(Constants.CLUB_INCLUDE)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                }else {
                    Toast.makeText(getApplicationContext(), "인클루드에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }
            }else if(writer.equals("한울")) {
                if(keyFiled.getText().toString().equals(Constants.CLUB_HANWOOL)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                }else {
                    Toast.makeText(getApplicationContext(), "한울에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
