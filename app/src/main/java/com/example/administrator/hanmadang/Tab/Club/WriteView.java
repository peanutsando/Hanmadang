package com.example.administrator.hanmadang.Tab.Club;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.hanmadang.R;

/**
 * Created by park on 2016-01-11.
 */
public class WriteView extends LinearLayout{
  //  private ImageView mIcon;

    private TextView mWriter;
    private TextView mTitle;
    private TextView mTimestamp;

    public WriteView(Context context, WriteItem aItem) {
        super(context);

        // layout inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.club_item, this, true);

        /*
        mIcon = (ImageView) findViewById(R.id.iconItem);
        mIcon.setImageDrawable(aItem.getIcon());
        */
        // Set Text 01
        mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(aItem.getData(0));

        // Set Text 02
        mTimestamp = (TextView) findViewById(R.id.timestamp);
        mTimestamp.setText(aItem.getData(1));

        // Set Text 03
        mWriter = (TextView) findViewById(R.id.writer);
        mWriter.setText(aItem.getData(2));
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }
    public void setTimestamp(String timestamp) {
        mTimestamp.setText(timestamp);
    }
    public void setWriter(String writer) {
        mWriter.setText(writer);
    }

    public void setText(int index, String data) {
        if(index==0) {
            mTitle.setText(data);
        }else if(index==1) {
            mTimestamp.setText(data);
        }else if(index==2) {
            mWriter.setText(data);
        }else {
            throw new IllegalArgumentException();
        }
    }

    /*
    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }
    */
}
