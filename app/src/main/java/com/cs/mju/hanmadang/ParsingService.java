package com.cs.mju.hanmadang;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParsingService extends Service {
    private String noticeNumber;
    private GetNoticeTask getNoticeTask;
    private SharedPreferences preferences;

    public ParsingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("hi", "hi");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        getNoticeTask = new GetNoticeTask();
        getNoticeTask.execute();

        String count = preferences.getString("NoticeNumber", noticeNumber);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("NoticeNumber", count);
        edit.commit();


        // 프리퍼런스에 가장 최근에 파싱한 글의 넘버를 저장해
        // 파싱했을때 프리퍼런스에서 가져와서 넘버랑 비교해
        // 다르면 노티바 띄워
        // 씨발 이걸 못해 ?
        return START_NOT_STICKY;
    }

    class GetNoticeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(Constants.NOTICE_URL).get();

                Elements elements = doc.select(Constants.NUMBER_ELEMENT);
                for (Element e : elements) {
                    if (e.text() != null && e.text().length() != 0) {
                        noticeNumber = e.text();
                        // 프리퍼런스에 첫번째 div 값 저장 기모띠
                        preferences = getSharedPreferences("Hanmadang", MODE_PRIVATE);
                        String realOrgasm = preferences.getString("NoticeNumber", "");
                        if (!realOrgasm.equals(noticeNumber)) {
                            // 알람으로 가버렷
                            Log.i("앗흥", "알..알람이야");
                        }
                        Log.i("앗흥", "넘버는 = " + noticeNumber);
                        preferences.edit().putString("NoticeNumber", noticeNumber);
                        preferences.edit().commit();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return noticeNumber;
        }

        @Override
        protected void onPostExecute(String key) {
            super.onPostExecute(key);
        }
    }
}
