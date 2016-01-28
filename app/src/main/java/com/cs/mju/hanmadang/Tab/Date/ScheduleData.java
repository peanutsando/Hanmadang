package com.cs.mju.hanmadang.Tab.Date;

/**
 * Created by Joguk_1 on 2016-01-26.
 */
public class ScheduleData{
    private int s_no;
    private String s_title;
    private String s_place;
    private String s_content;
    private String s_timestamp;

    public int getNo (){ return s_no; }
    public void setNo (int s_no){ this.s_no = s_no; }

    public String getContent (){ return s_content; }
    public void setContent (String s_content){ this.s_content = s_content; }

    public String getTimestamp (){ return s_timestamp; }
    public void setTimestamp (String s_timestamp){ this.s_timestamp = s_timestamp;  }

    public String getTitle (){ return s_title; }
    public void setTitle (String s_title)
    {
        this.s_title = s_title;
    }

    public String getPlace ()
    {
        return s_place;
    }
    public void setPlace (String s_place)
    {
        this.s_place = s_place;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [s_no = "+s_no+", s_title = " + s_title + ", s_place = " + s_place + "s_content = "+ s_content +", s_timestamp = "+ s_timestamp +"]";
    }

}

