package com.cs.mju.hanmadang;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cs.mju.hanmadang.Function.PushJsonParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParsingService extends Service {
    private String noticeNumber;
    private GetNoticeTask getNoticeTask;
    private SharedPreferences preferences;
    private String count;

    public ParsingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        registerAlarm(this, Constants.CHECK_TIMER);
        count = preferences.getString("NoticeNumber", noticeNumber);
        Log.e("Default Count", count);

        getNoticeTask = new GetNoticeTask();
        getNoticeTask.execute();

        return START_NOT_STICKY;
    }

    class GetNoticeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                SharedPreferences.Editor edit = preferences.edit();
                Document doc = Jsoup.connect(Constants.NOTICE_URL).get();
                Elements elements = doc.select(Constants.NUMBER_ELEMENT);
                for (Element e : elements) {
                    if (e.text() != null && e.text().length() != 0) {
                        preferences = getSharedPreferences("Hanmadang", MODE_PRIVATE);
                        noticeNumber = e.text();
                        Log.e(getClass().getSimpleName(), "noticceNumber = " + noticeNumber +" count=" + count);
                        if (!(count.equals(noticeNumber))) {
                            edit.putString("NoticeNumber", noticeNumber);
                            edit.commit();
                            PushJsonParser jsonParser = new PushJsonParser();
                            Constants.num = 0;
                            jsonParser.sendPushMessage("새로운 공지사항이 있습니다.");
                        }
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

    public static void registerAlarm(Context context, long second) {
        Log.i("Alaram Regit", "RegitAlarm");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ParsingService.class);

        PendingIntent sender = PendingIntent.getService(context, 1234, intent, 0);

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + second, sender);

    }
}
