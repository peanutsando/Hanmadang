package com.cs.mju.hanmadang.Tab.Club;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs.mju.hanmadang.R;

/**
 * Created by park on 2016-01-18.
 */
public class ClubReadActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView content;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_read);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        addListenersToView();
    }

    private void initViews() {
        content = (TextView)findViewById(R.id.content);
        content.setMaxLines(12);
        content.setVerticalScrollBarEnabled(true);
        content.setHorizontalScrollBarEnabled(true);
        content.setMovementMethod(new ScrollingMovementMethod());

        button = (Button)findViewById(R.id.okButton);
    }

    private void addListenersToView() {
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.okButton) {
            finish();
        }
    }
}
