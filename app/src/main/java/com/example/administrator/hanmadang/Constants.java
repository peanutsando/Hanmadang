package com.example.administrator.hanmadang;

/**
 * Created by Administrator on 2015-12-25.
 */
public class Constants {
    /* NOTICE ACTIVITY */
    /* NOTICE URL */
    public static final String NOTICE_URL = "http://jw4.mju.ac.kr/user/boardList.action?boardId=28510945&page=1";

    /* XML PARSING TAG AND PARAMS */
    public static final String TITLE_ELEMENT = "td[class=title]";
    public static final String NUMBER_ELEMENT = "td[class=no]";
    public static final String TIMESTAMP_ELEMENT = "td:eq(3)";
    public static final String NOTICE_URL_ELEMENT = "td > a[href]";
    public static final String HREF_ELEMENT = "abs:href";
    public static final String NOTICE_DIV_VIEW_P = "div#divView > p";
    public static final String NOTICE_DIV_VIEW_DIV = "div#divView > div";
    public static final String NOTICE_DIV_VIEW_B = "div#divView > b";
    public static final String NOTICE_DIV_VIEW_BR = "div#divView > br";
    public static final String NOTICE_IMG_SELECT= "img[src]";
    public static final String NOTICE_IMG_SELECT_PARAMS= "abs:src";

    public static final String DISCONNECTED_INTERNET = "인터넷 연결 안됨";

    /* NOTICE_CONTENT_PARAMS */
    public static final String NOTICE_TITLE = "제목 : ";
    public static final String NOTICE_TIMESTAMP = "작성일 : ";
    public static final String NOTICE_ATTACH_FILE = "첨부파일 : ";

    public static final int ACTIVITY_REQUEST_CODE = 1;

    public static final String JPEG = ".jpg";
    /* 치ㅕㅠahdi 시발 */

}
