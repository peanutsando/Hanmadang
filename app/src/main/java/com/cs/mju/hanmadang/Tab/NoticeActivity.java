package com.cs.mju.hanmadang.Tab;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.Function.BackKeyHandler;
import com.cs.mju.hanmadang.R;
import com.cs.mju.hanmadang.Tab.Notice.NoticeContent;
import com.cs.mju.hanmadang.Tab.Notice.NoticeItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on Youthink on 2016-01-10.
 */

public class NoticeActivity extends AppCompatActivity {
    private ListView listView;
    private NoticeListAdapter noticeListAdapter = null;
    private ArrayList<NoticeItem> itemList = new ArrayList<>();

    private ConnectivityManager cManager;
    private NetworkInfo lte;
    private NetworkInfo wifi;

    private GetNoticeTask getNoticeTask;
    private ArrayList<String> number;
    private ArrayList<String> timestamp;
    private ArrayList<String> title;
    private ArrayList<String> urlList;
    private NoticeListAdapter<NoticeItem> adapter;
    private int count;
    private BackKeyHandler keyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        keyHandler = new BackKeyHandler(this);
        listView = (ListView) findViewById(R.id.noticeList);
        listView.setAdapter(noticeListAdapter);


        if (isInternet()) {
            Toast.makeText(getApplicationContext(), Constants.DISCONNECTED_INTERNET, Toast.LENGTH_SHORT).show();
        } else {
            getNoticeTask = new GetNoticeTask();
            getNoticeTask.execute();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), NoticeContent.class);
                intent.putExtra("URL", urlList.get(i+count));
                intent.putExtra("TITLE", title.get(i+count));
                intent.putExtra("TIMESTAMP", timestamp.get(i+count));
                startActivityForResult(intent, Constants.ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onBackPressed(){
        keyHandler.onBackPressed();
    }

    private boolean isInternet() {
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        lte = cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return !lte.isConnected() && !wifi.isConnected();
    }

    public class NoticeListAdapter<T extends NoticeItem> extends ArrayAdapter<T> {
        private Context mContext;
        private ArrayList<T> arrayList;

        public NoticeListAdapter(Context mContext, int id, ArrayList<T> items) {
            super(mContext, id, items);
            this.mContext = mContext;
            arrayList = items;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.notice_item, null);

                T notice = arrayList.get(position);

                if (notice != null) {
                    holder.mNumber = (TextView) view.findViewById(R.id.contentTitle);
                    holder.mTimstamp = (TextView) view.findViewById(R.id.attachFile);
                    holder.mTitle = (TextView) view.findViewById(R.id.noticeTitle);
                }
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            NoticeItem mNotice = itemList.get(position);

            holder.mNumber.setText(mNotice.number);
            holder.mTimstamp.setText(mNotice.timestamp);
            holder.mTitle.setText(mNotice.title);

            return view;
        }

    }

    class ViewHolder {
        public TextView mNumber;
        public TextView mTimstamp;
        public TextView mTitle;
    }

    class GetNoticeTask extends AsyncTask<Void, Void, List<NoticeItem>> {
        @Override
        protected List<NoticeItem> doInBackground(Void... voids) {
            try {
                count = 0;
                Document doc = Jsoup.connect(Constants.NOTICE_URL).get();

                number = new ArrayList<String>();
                title = new ArrayList<String>();
                timestamp = new ArrayList<String>();
                urlList = new ArrayList<String>();

                /*Elements urlm = doc.select("li > a[href]");
                for (Element e : urlm){
                    Log.e("div paging", e.attr("abs:href"));
                    if(e.tagName().equals("a")) {
                        Log.e("a Tag", e.text());
                    }
                }*/

                Elements titleElement = doc.select(Constants.TITLE_ELEMENT);
                for (Element e : titleElement) {
                    title.add(String.valueOf(e.text()));
                }

                Elements numberElement = doc.select(Constants.NUMBER_ELEMENT);
                for (Element e : numberElement) {
                    number.add(String.valueOf(e.text()));
                }

                Elements timestampElement = doc.select(Constants.TIMESTAMP_ELEMENT);

                for (Element e : timestampElement) {
                    timestamp.add(String.valueOf(e.text()));
                }

                Elements urlElement = doc.select(Constants.NOTICE_URL_ELEMENT);
                for(Element e : urlElement){
                    if(!e.text().equals("")){
                        urlList.add(String.valueOf(e.attr(Constants.HREF_ELEMENT)));
                    }
                }
                Log.e("Size", String.valueOf(timestampElement.size()));
                for (int i = 0; i < timestampElement.size(); i++) {
                    if (number.get(i).equals("")) {
                        count++;
                    }
                }
                for(int i = count ; i < numberElement.size(); i++){
                    if (!number.get(i).equals("")) {
                        itemList.add(NoticeItem.toNoticeItem(number.get(i), timestamp.get(i), title.get(i), urlList.get(i)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Collections.emptyList();
        }

        @Override
        protected void onPostExecute(List<NoticeItem> list) {
            super.onPostExecute(list);
            NoticeView();
        }

        private void NoticeView() {
            adapter = new NoticeListAdapter<NoticeItem>(getApplicationContext(), R.layout.notice_item, itemList);
            listView.setAdapter(adapter);
        }
    }
}
