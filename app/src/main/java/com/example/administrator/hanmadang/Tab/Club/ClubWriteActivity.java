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
        final CharSequence[] writerList = {"AO", "인클루드", "한울"};
        AlertDialog.Builder alt_builder = new AlertDialog.Builder(this);

        alt_builder.setIcon(R.drawable.icon);
        alt_builder.setTitle("동아리를 선택해주세요");
        alt_builder.setSingleChoiceItems(writerList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "동아리명 = " + writerList[which], Toast.LENGTH_LONG).show();
                dialog.cancel();

                selectWriter.setText(writerList[which]);

                writer = writerList[which].toString();
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

        // 제목 입력 EditText
        inputTitle = (EditText)findViewById(R.id.inputTitle);

        // 작성자 선택 textView
        selectWriter = (TextView)findViewById(R.id.inputWriter);
    }

    private void addListenersToView() {
        writeButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        selectWriter.setOnClickListener(this);
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
            Toast.makeText(getApplicationContext(), "텍스트뷰 터치", Toast.LENGTH_LONG).show();
        }
    }
}
