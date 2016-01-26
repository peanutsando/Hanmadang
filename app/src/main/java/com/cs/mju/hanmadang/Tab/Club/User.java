package com.cs.mju.hanmadang.Tab.Club;

/**
 * Created by park on 2016-01-26.
 */

public class User
{
    private String b_password;

    private String b_title;

    private String b_writer;

    private String b_content;

    private String b_no;

    private String b_timestamp;

    public String getB_password ()
    {
        return b_password;
    }

    public void setB_password (String b_password)
    {
        this.b_password = b_password;
    }

    public String getB_title ()
    {
        return b_title;
    }

    public void setB_title (String b_title)
    {
        this.b_title = b_title;
    }

    public String getB_writer ()
    {
        return b_writer;
    }

    public void setB_writer (String b_writer)
    {
        this.b_writer = b_writer;
    }

    public String getB_content ()
    {
        return b_content;
    }

    public void setB_content (String b_content)
    {
        this.b_content = b_content;
    }

    public String getB_no ()
    {
        return b_no;
    }

    public void setB_no (String b_no)
    {
        this.b_no = b_no;
    }

    public String getB_timestamp ()
    {
        return b_timestamp;
    }

    public void setB_timestamp (String b_timestamp)
    {
        this.b_timestamp = b_timestamp;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [b_password = "+b_password+", b_title = "+b_title+", b_writer = "+b_writer+", b_content = "+b_content+", b_no = "+b_no+", b_timestamp = "+b_timestamp+"]";
    }
}