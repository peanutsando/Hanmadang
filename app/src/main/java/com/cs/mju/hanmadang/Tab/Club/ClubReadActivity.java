package com.cs.mju.hanmadang.Tab.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.R;

/**
 * Created by park on 2016-01-18.
 */
public class ClubReadActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView content;
    private Button okButton;
    private TextView title;
    private TextView writer;
    private String password;
    private Button delButton;
    private EditText inputKey;
    private Button keyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_read);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 초기화, 이벤트 등록
        initViews();
        addListenersToView();

        // 저장된 게시글 데이터를 가져옴
        loadData();
    }

    private void initViews() {
        content = (TextView)findViewById(R.id.content);
        content.setMaxLines(12);
        content.setVerticalScrollBarEnabled(true);
        content.setHorizontalScrollBarEnabled(true);
        content.setMovementMethod(new ScrollingMovementMethod());

        okButton = (Button)findViewById(R.id.okButton);
        delButton = (Button)findViewById(R.id.delButton);
        delButton.setEnabled(false);
        delButton.setFocusable(false);
        title = (TextView)findViewById(R.id.inputTitle);
        writer = (TextView)findViewById(R.id.inputWriter);

        inputKey = (EditText)findViewById(R.id.inputKey);
        keyButton = (Button)findViewById(R.id.keyButton);
    }

    private void addListenersToView() {
        okButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
        keyButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.okButton) {
            finish();
        }else if(v.getId() == R.id.keyButton) {
            matchingKey();
        }
    }

    private void matchingKey() {
        switch(writer.getText().toString()) {
            case "AO":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "AO에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "인클루드":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "인클루드에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "한울":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "한울에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "SAT":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "SAT에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "ICUNIX":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "ICUNIX에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "NEXT":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "NEXT에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "MAP":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "MAP에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "그리고":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "그리고에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "COSCI":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "COSCI에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "OS":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                } else {
                    Toast.makeText(getApplicationContext(), "OS에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    private void loadData() {
        // 파싱할 URL에서 JSON을 객체로
        JSONParser jsonParser = new JSONParser();
        jsonParser.parseJSONFromURL(Constants.CLUB_URL);

        Intent intent = getIntent();
        int pos = intent.getExtras().getInt("pos");

        title.setText(jsonParser.object.get(pos).getB_title());
        content.setText(jsonParser.object.get(pos).getB_content());
        writer.setText(jsonParser.object.get(pos).getB_writer());
        password = jsonParser.object.get(pos).getB_password(); // 입력된 key와 비교
    }
}
