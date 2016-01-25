package com.cs.mju.hanmadang.Tab.Notice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.Function.photoview.PhotoViewAttacher;
import com.cs.mju.hanmadang.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created on Youthink on 2016-01-12.
 */

public class NoticeContent extends AppCompatActivity {

    private String url, title, timestamp, content = "";
    private ArrayList<String> imageURL;
    private TextView titleView, timestampView, contentView, attachView;
    private ImageView contentImage;
    private GetContentTask getContentTask;
    private GetImageTask getImageTask;

    private PhotoViewAttacher photoViewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        title = intent.getStringExtra("TITLE");
        timestamp = intent.getStringExtra("TIMESTAMP");

        titleView = (TextView) findViewById(R.id.contentTitle);
        timestampView = (TextView) findViewById(R.id.contentTimestamp);
        contentView = (TextView) findViewById(R.id.content);
        attachView = (TextView) findViewById(R.id.attachFile);
        contentImage = (ImageView) findViewById(R.id.contentImage);

        titleView.setText(Constants.NOTICE_TITLE + title);
        timestampView.setText(Constants.NOTICE_TIMESTAMP + timestamp);

        getContentTask = new GetContentTask();
        getContentTask.execute();

        getImageTask = new GetImageTask();
        getImageTask.execute();

        attachView.setText("첨부파일이 없습니다.(는 아직 미구현)");
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

    class GetContentTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(url).get();

                Elements contentElement = doc.select(Constants.NOTICE_DIV_VIEW_P);
                for (Element e : contentElement) {
                    if (!e.text().equals("")) {
                        content = content + e.text() + "<br />";
                    }else{
                        content = content + "<br />";
                    }
                }

                contentElement = doc.select(Constants.NOTICE_DIV_VIEW_DIV);
                for (Element e : contentElement) {
                    Log.e("", String.valueOf(contentElement.select("b")));

                    if (!e.text().equals("")) {
                        content = content + e.text() + "<br />";
                    }else{
                        content = content + "<br />";
                    }
                }

                contentElement = doc.select(Constants.NOTICE_DIV_VIEW_B);
                for (Element e : contentElement) {
                    if (!e.text().equals("")) {
                        content = content + e.text() + "<br />";
                    }else{
                        content = content + "<br />";
                    }
                }

                contentElement = doc.select(Constants.NOTICE_DIV_VIEW_BR);
                for (Element e : contentElement) {
                    if (!e.text().equals("")) {
                        content = content + e.text() + "<br />";
                    }else{
                        content = content + "<br />";
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            contentView.setText(Html.fromHtml(result));
        }

    }

    class GetImageTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String jpg = "";
            try {
                imageURL = new ArrayList<String>();
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select(Constants.NOTICE_IMG_SELECT);
                for (Element e : elements) {
                    Log.e(getClass().getSimpleName(), e.attr(Constants.NOTICE_IMG_SELECT_PARAMS));
                    if (e.attr(Constants.NOTICE_IMG_SELECT_PARAMS).contains(Constants.JPEG)) {
                        jpg = e.attr(Constants.NOTICE_IMG_SELECT_PARAMS);
                    }
                    Log.e(Constants.JPEG, jpg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jpg;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("IMG SOURCE = ", result);

            if (!result.equals("")) {
                Picasso.with(getApplicationContext())
                        .load(result)
                        .placeholder(R.drawable.progress_animation)
                        .into(contentImage);
                photoViewAttacher = new PhotoViewAttacher(contentImage);
                photoViewAttacher.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }
}
