package com.cs.mju.hanmadang;

/**
 * Created on Youthink on 2016-01-04
 */
public class Constants {
    /* APP NAME */
    public static final String HANMADANG = "한마당";

    /* URL */
    public static final String NOTICE_URL = "http://jw4.mju.ac.kr/user/boardList.action?boardId=28510945&page=1";
    public static final String CLUB_URL = "http://117.17.158.234:8080/Hanmadang/ClubController";
    public static final String CLUB_SAVE_URL = "http://117.17.158.234:8080/Hanmadang/ClubSaveController";
    public static final String CLUB_DEL_URL = "http://117.17.158.234:8080/Hanmadang/ClubDeleteController";
    public static final String CLUB_MOD_URL = "http://117.17.158.234:8080/Hanmadang/ClubModifyController";
    public static final String REG_SAVE_URL = "http://117.17.158.234:8080/Hanmadang/RegisterKeySaveController";
    public static final String REG_URL = "http://117.17.158.234:8080/Hanmadang/RegisterKeyController";
    public static final String SCHEDULE_SAVE_URL = "http://117.17.158.234:8080/Hanmadang/ScheduleSaveController";
    public static final String SCHEDULE_URL = "http://117.17.158.234:8080/Hanmadang/ScheduleController";

    /* XML PARSING TAG AND PARAMS */
    public static final String TITLE_ELEMENT = "td[class=title]";
    public static final String NUMBER_ELEMENT = "td[class=no]";
    public static final String TIMESTAMP_ELEMENT = "td:eq(3)";
    public static final String CHECK_URL_ELEMENT = "td > a[href]";
    public static final String HREF_ELEMENT = "abs:href";
    public static final String NOTICE_DIV_VIEW_P = "div#divView > p";
    public static final String NOTICE_DIV_VIEW_DIV = "div#divView > div";
    public static final String NOTICE_DIV_VIEW_B = "div#divView > b";
    public static final String NOTICE_DIV_VIEW_BR = "div#divView > br";
    public static final String NOTICE_IMG_SELECT= "img[src]";
    public static final String NOTICE_IMG_SELECT_PARAMS= "abs:src";
    public static final String ATTACH_FILE_ELEMENT = "td[colspan]";

    public static final String DISCONNECTED_INTERNET = "인터넷 연결 안됨";

    /* NOTICE_CONTENT_PARAMS */
    public static final String NOTICE_TITLE = "제목 : ";
    public static final String NOTICE_TIMESTAMP = "작성일 : ";
    public static final String NOT_ATTACH_FILE = "첨부파일이 없습니다.";
    public static final String NON_ATTACH_FILE = "첨부파일이";

    public static final int ACTIVITY_REQUEST_CODE = 1;

    public static final String JPEG = ".jpg";

    /* push message */
    public static final String GOOGLE_API_KEY = "AIzaSyCVKcCj50siRLfgtFd6Q6aiVOP3YuaI1YA";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static String registerTokenKey = "";

    /* BackKeyHandler */
    public static final String BACK_BTN_CLICK = "\'뒤로\'버튼을 한번 더 누르시면 종료 됩니다.";

    /* Key SETTING */
    public static final String CLUB_AO = "#ao0000";
    public static final String CLUB_INCLUDE = "#in0000";
    public static final String CLUB_HANWOOL = "#ha0000";
    public static final String CLUB_SAT = "#sa0000";
    public static final String CLUB_ICUNIX = "#ic0000";
    public static final String CLUB_NEXT = "#ne0000";
    public static final String CLUB_MAP = "#ma0000";
    public static final String CLUB_AND = "#an0000";
    public static final String CLUB_COSCI = "#co0000";
    public static final String CLUB_OS = "#os0000";

    public static int num = 0;
    public static int check_tab = 0;
    public static int write = 0;

    public static final long CHECK_TIMER = 1800000;
}
