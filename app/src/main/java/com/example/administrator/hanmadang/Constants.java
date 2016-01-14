package com.example.administrator.hanmadang;

/**
 * Created on Youthink on 2016-01-04
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
	public static final String NOTICE_IMG_SELECT = "img[src]";
	public static final String NOTICE_IMG_SELECT_PARAMS = "abs:src";

	public static final String DISCONNECTED_INTERNET = "인터넷 연결 안됨";

	/* NOTICE_CONTENT_PARAMS */
	public static final String NOTICE_TITLE = "제목 : ";
	public static final String NOTICE_TIMESTAMP = "작성일 : ";
	public static final String NOTICE_ATTACH_FILE = "첨부파일 : ";

	public static final int ACTIVITY_REQUEST_CODE = 1;

	public static final String JPEG = ".jpg";

	/* BackKeyHandler */
	public static final String BACK_BTN_CLICK = "순순히 \'뒤로\' 버튼에서손을 떼신다면 유혈사태는 일어나지 않을 것입니다.";
	public static final String BACK_BTN_DOUBLE_CLICK = "장비를 정지합니다. \n정지하겠습니다.";
	public static final String BACK_BTN_TRIPLE_CLICK = "안 되잖아?";
	public static final String BACK_BTN_QUADRUPLE_CLICK = "정지가 안 돼\n정지시킬 수가 없어, 앙돼!";

	/* 커찮아졌어 */
	public static final String[] BACK_BTN_MSG_ARRAY = {BACK_BTN_CLICK, BACK_BTN_DOUBLE_CLICK, BACK_BTN_TRIPLE_CLICK, BACK_BTN_QUADRUPLE_CLICK};
//    public static final String BACK_BTN_CLICK = "\'뒤로\'버튼을 한번 더 누르시면 종료 됩니다.";

}
