package com.cs.mju.hanmadang.Tab.Date;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.StringTokenizer;

import com.cs.mju.hanmadang.Constants;

// 일자에 표시하는 텍스트뷰 정의
public class MonthItemView extends TextView {
	ScheduleJSONParser jsonParser = new ScheduleJSONParser();
	private MonthItem item;

	public MonthItemView(Context context) {
		super(context);
		init();
	}

	public MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setBackgroundColor(Color.WHITE);
	}

	public MonthItem getItem() {
		return item;
	}

	public void setItem(MonthItem item, int curYear, int curMonth) {
		this.item = item;
		int day = item.getDay();
		String[] str = new String[100];
		String scheduleTitle = searchData(day, curYear, curMonth);

		if(scheduleTitle != null){
			StringTokenizer tokens = new StringTokenizer(scheduleTitle, "&*&");
			for(int i=0; tokens.hasMoreTokens(); i++){
				str[i] = tokens.nextToken("&*&");
			}
		}

		if (day != 0) {
			if(str[0]==null){
				setText(String.valueOf(day));
			}else{
				String tempStr = ""+day;
				int i = 0;
				int count = 0; // 일정이 2개 이상일 때 이후의 일정을 카운트
				boolean flag = !str[i].isEmpty();
				while(flag){
					if(str[i].length()>3){
						str[i] = str[i].substring(0,3) + "..";
					}
					if(i < 3){
						tempStr = tempStr + "\n" + str[i];
					}else{
						count++;
					}
					i++;
					if(str[i] != null) {
						flag = !str[i].isEmpty();
					}else{
						break;
					}
				}
				if(count!=0){
					tempStr = tempStr + "\n" + "+";
					tempStr = tempStr + count;
				}
				setText(tempStr);
				setTextSize(12);
			}
		} else {
			setText("");
		}
	}

	// 해당 날짜에 일정이 있는지 확인하는 함수
	private String searchData(int day, int curYear, int curMonth) {
		if(day==0){
			return null;
		}else{
			jsonParser.parseJSONFromURL(Constants.SCHEDULE_URL);

			String title;
			String date;
			// 날짜에 보여줄 일정제목을 저장하고 리턴
			String text=null;

			// 10월 전이면 월 앞에 0을 붙이는 작업 , 10일 전일 경우 0을 붙이는 작업
			if(curMonth<10){
				if(day<10)
					date = curYear + "-" + "0"+ curMonth + "-" + "0" +day;
				else
					date = curYear + "-" + "0"+ curMonth + "-" + day;
			}else{
				if(day<10)
					date = curYear + "-" + curMonth + "-" + "0"+day;
				else
					date = curYear + "-" + curMonth + "-" + day;
			}

			for(int i=0; i<jsonParser.object.size(); i++) {
				String[] str = jsonParser.object.get(i).getTimestamp().split(" ");
				if(str[0].equals(date)){
					title = jsonParser.object.get(i).getTitle();
					if(text==null){
						text = title+"&*&";
					}else{
						text = text + title + "&*&";
						text.concat("&*&");
					}
				}
			}
			return text;
		}
	}
}