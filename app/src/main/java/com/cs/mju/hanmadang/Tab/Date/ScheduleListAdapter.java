package com.cs.mju.hanmadang.Tab.Date;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

// 어댑터 클래스 정의
public class ScheduleListAdapter extends BaseAdapter {

   private Context mContext;

   private List<ScheduleItem> mItems = new ArrayList<ScheduleItem>();

   public ScheduleListAdapter(Context context) {
       mContext = context;
   }

   public void addItem(ScheduleItem it) {
       mItems.add(it);
   }

   public void setListItems(List<ScheduleItem> lit) {
       mItems = lit;
   }

   public int getCount() {
       return mItems.size();
   }

   public Object getItem(int position) {
       return mItems.get(position);
   }

   public boolean areAllItemsSelectable() {
       return false;
   }

   public boolean isSelectable(int position) {
       try {
           return mItems.get(position).isSelectable();
       } catch (IndexOutOfBoundsException ex) {
           return false;
       }
   }

   public long getItemId(int position) {
       return position;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
       ScheduleItemView itemView;
       final Context context = parent.getContext();
       final int pos = position;
       if (convertView == null) {
           itemView = new ScheduleItemView(mContext, mItems.get(position));
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.schedule_list, parent, false);
       } else {
           itemView = (ScheduleItemView) convertView;

           itemView.setText(0, mItems.get(position).getData(0));
           itemView.setText(1, mItems.get(position).getData(1));
           // 리스트뷰의 날짜부분 끝에 0. 빼기
           String temp = mItems.get(position).getData(3).substring(0,16);
           Log.e("temp ", temp);
           itemView.setText(2, temp);

           Button deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
           deleteButton.setOnClickListener(new Button.OnClickListener() {
               public void onClick(View v) {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           try {
                               URL url = new URL(Constants.SCHEDULE_DEL_URL);
                               URLConnection conn = url.openConnection();

                               conn.setDoOutput(true);

                               /* 데이터 전송, &*&은 데이터를 구분할 토큰 */
                               OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());

                               out.write(mItems.get(pos).getData(0));
                               out.write("&*&");
                               out.write(mItems.get(pos).getData(1));
                               out.write("&*&");
                               out.write(mItems.get(pos).getData(2));
                               out.write("&*&");
                               out.write(mItems.get(pos).getData(3));

                               out.close();

                               BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                           } catch (Exception e) {
                               Log.d("Exception", e.toString());
                           }
                       }
                   }).start();
               }
           });
       }
       return itemView;
   }
}
