package com.cs.mju.hanmadang.Tab.Club;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.Function.PushJsonParser;
import com.cs.mju.hanmadang.Function.keyHandler;
import com.cs.mju.hanmadang.R;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    // 내용 입력 EditTEXT
    EditText inputContent;

    // 작성자 선택 textView
    TextView selectWriter;

    // Key 필드
    EditText keyFiled;
    Button keyButton;

    // DB에 저장할 내용들 및 ClubActivity 로 전달하는 title, writer, strCurDate (글목록 표시 위해)
    String title;   // 제목
    String writer = "";  // 작성자
    String strCurDate;  // 날짜
    String password;
    String content;

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
        final String[] writerList = {"AO", "인클루드", "한울", "SAT", "ICUNIX", "NEXT", "MAP", "그리고", "COSCI", "OS"};
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
        curDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.0");
        strCurDate = curDateFormat.format(date);

        // 글쓰기, 취소 버튼
        writeButton = (Button) findViewById(R.id.writeButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        writeButton.setEnabled(false);
        writeButton.setFocusable(false);

        // 제목 입력 EditText
        inputTitle = (EditText) findViewById(R.id.inputTitle);

        // 내용 입력 EditText
        inputContent = (EditText) findViewById(R.id.inputContent);

        // 작성자 선택 textView
        selectWriter = (TextView) findViewById(R.id.inputWriter);

        // key EditText
        keyFiled = (EditText) findViewById(R.id.inputKey);
        keyButton = (Button) findViewById(R.id.keyButton);
    }

    private void addListenersToView() {
        writeButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        selectWriter.setOnClickListener(this);
        keyButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.cancelButton) {
            intent = getIntent();
            this.setResult(RESULT_CANCELED, intent);
            this.finish();
        } else if (v.getId() == R.id.writeButton) {
            /* ClubActivity 로 전송할 내용들 (바로 적용시키기 위하여) */
            saveData(); // 알림푸쉬
            sendDataToClubActivity();
            /* 서버에 데이터 전송하고 종료 */
        } else if (v.getId() == R.id.inputWriter) {
            //* 작성자 (동아리) 선택 메서드 *//*
            DialogRadio();
        } else if (v.getId() == R.id.keyButton) {
            matchingKey();
        }
    }

    private void matchingKey() {
        switch (writer.toString()) {
            case "AO":
                if (keyFiled.getText().toString().equals(Constants.CLUB_AO)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_AO;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "AO에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "인클루드":
                if (keyFiled.getText().toString().equals(Constants.CLUB_INCLUDE)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_INCLUDE;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "인클루드에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "한울":
                if (keyFiled.getText().toString().equals(Constants.CLUB_HANWOOL)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_HANWOOL;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "한울에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "SAT":
                if (keyFiled.getText().toString().equals(Constants.CLUB_SAT)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_SAT;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "SAT에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "ICUNIX":
                if (keyFiled.getText().toString().equals(Constants.CLUB_ICUNIX)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_ICUNIX;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "ICUNIX에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "NEXT":
                if (keyFiled.getText().toString().equals(Constants.CLUB_NEXT)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_NEXT;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "NEXT에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "MAP":
                if (keyFiled.getText().toString().equals(Constants.CLUB_MAP)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_MAP;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "MAP에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "그리고":
                if (keyFiled.getText().toString().equals(Constants.CLUB_AND)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_AND;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "AND에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "COSCI":
                if (keyFiled.getText().toString().equals(Constants.CLUB_COSCI)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_COSCI;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "COSCI에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "OS":
                if (keyFiled.getText().toString().equals(Constants.CLUB_OS)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();
                    password = Constants.CLUB_OS;

                    writeButton.setEnabled(true);
                    writeButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "OS에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "":
                Toast.makeText(getApplicationContext(), "작성자를 선택해주세요.", Toast.LENGTH_LONG).show();
        }
    }

    private void sendDataToClubActivity() {
        intent = getIntent();
        extra = new Bundle();

        if (inputTitle.getText().toString().length() == 0)
            title = null;
        else
            title = inputTitle.getText().toString();

        extra.putString("writer", writer);
        extra.putString("timestamp", strCurDate);
        extra.putString("title", title);
        intent.putExtras(extra);
        this.setResult(RESULT_OK, intent);

        if (inputContent.getText().toString().length() == 0)
            Toast.makeText(getApplicationContext(), "내용 없음", Toast.LENGTH_LONG).show();
        else
            content = inputContent.getText().toString();
    }

    private void saveData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Constants.CLUB_SAVE_URL);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    /* 데이터 전송, &*&은 데이터를 구분할 토큰 */
                    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                    out.write(title);
                    out.write("&*&");
                    out.write(content);
                    out.write("&*&");
                    out.write(writer);
                    out.write("&*&");
                    out.write(password);
                    out.write("&*&");
                    out.write(strCurDate);

                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                }
            }
        }).start();

        sendPushMessage();
        this.finish();
    }

    private void sendPushMessage() {
        List<String> keyList = new ArrayList<String>();
        Sender sender = new Sender(Constants.GOOGLE_API_KEY);
        Message message = new Message.Builder().addData("message", inputTitle.getText().toString()).build();
        PushJsonParser pushJsonParser = new PushJsonParser();
        pushJsonParser.getTokenKeyFromURL(Constants.REG_URL);
        MulticastResult result;
        Log.e("TO", pushJsonParser.object.toString());
        int size = pushJsonParser.object.size();
        for (int i = 0; i < size; i++) {
            keyList.add(i, pushJsonParser.object.get(i).getReg_key());
            Log.e("ee", keyList.get(i));
        }
        try {
                result = sender.send(message, keyList, 1);
                if (result != null) {
                    List<Result> results = result.getResults();
                    for (Result result0 : results) {
                        Log.e("###", result0.getMessageId());
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}