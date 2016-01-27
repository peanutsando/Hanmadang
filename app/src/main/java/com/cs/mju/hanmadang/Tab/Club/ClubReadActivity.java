package com.cs.mju.hanmadang.Tab.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private Button delButton;

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
        title = (TextView)findViewById(R.id.inputTitle);
        writer = (TextView)findViewById(R.id.inputWriter);
    }

    private void addListenersToView() {
        okButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.okButton) {
            finish();
        }else if(v.getId() == R.id.delButton) {

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
    }
}
