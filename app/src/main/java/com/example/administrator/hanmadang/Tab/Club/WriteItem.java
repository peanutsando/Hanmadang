package com.example.administrator.hanmadang.Tab.Club;

import android.graphics.drawable.Drawable;

/**
 * Created by Y5725 on 2016-01-11.
 */
public class WriteItem {
    //private Drawable mIcon;

    private String writer;
    private String title;
    private String timestamp;

    private String[] mData;

    private boolean mSelectable = true;

    public WriteItem(String[] obj) {
        mData = obj;
    }

    public WriteItem(String title, String timestamp, String writer) {
        mData = new String[3];
        mData[0] = title;
        mData[1] = timestamp;
        mData[2] = writer;
    }

    public boolean isSelectable() { return mSelectable; }

    // setter
    public void setSelectable(boolean selectable) { mSelectable = selectable; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setTitle(String title) { this.title = title; }
    public void setWriter(String writer) { this.writer = writer; }
    public void setData(String[] obj) { this.mData = obj; }

    //public void setIcon(Drawable icon) { mIcon = icon; }

    // getter
    /**
    public String getTimestamp() { return timestamp; }
    public String getTitle() { return title; }
    public String getWriter() { return writer; }
    */
    public String[] getData() { return mData ; }

    public String getData(int index) {
        if(mData == null || index >= mData.length) {
            return null;
        }

        return mData[index];
    }

  //  public Drawable getIcon() { return mIcon; }
}
