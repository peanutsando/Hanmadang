package com.example.administrator.hanmadang.Function;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.hanmadang.Constants;

/**
 * Created on Youthink on 2016-01-14.
 */

public class BackKeyHandler {
	private long keyPressTime = 0;
	private Activity activity;

	public BackKeyHandler(Activity context) {
		this.activity = context;
	}

	public void onBackPressed() {
		if (System.currentTimeMillis() > keyPressTime + 2000) {
			keyPressTime = System.currentTimeMillis();
			showGuide();
		} else if (System.currentTimeMillis() <= keyPressTime + 2000) {
			activity.finish();
		}
	}

	public void showGuide() {
		Toast.makeText(activity, Constants.BACK_BTN_CLICK, Toast.LENGTH_SHORT).show();
	}
}
