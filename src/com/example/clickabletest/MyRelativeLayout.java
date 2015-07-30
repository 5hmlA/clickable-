package com.example.clickabletest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {
	public MyRelativeLayout(Context paramContext) {
		super(paramContext);
	}

	public MyRelativeLayout(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public MyRelativeLayout(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN://0
			Log.e("RelativeLayout",
					"viewgroup.....onInterceptTouchEvent--------------down");
			break;
		case MotionEvent.ACTION_MOVE://2
			Log.e("RelativeLayout",
					"viewgroup.....onInterceptTouchEvent--------------move");
			break;
		case MotionEvent.ACTION_UP://1
			Log.e("RelativeLayout",
					"viewgroup.....onInterceptTouchEvent--------------up");
			break;
		}
		boolean retu = super.onInterceptTouchEvent(event);
		Log.e("RelativeLayout", event.getAction()+"---viewgroup....-super==父类..onInterceptTouchEvent...." + retu);
		return retu;
	}

	//默认是 不消费任何事件  down返回false
	//当clickable为true时 会消费所有事件 down返回true
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("RelativeLayout",
					"viewgroup.....onTouchEvent--------------down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("RelativeLayout",
					"viewgroup.....onTouchEvent--------------move");
			break;
		case MotionEvent.ACTION_UP:
			Log.e("RelativeLayout",
					"viewgroup.....onTouchEvent--------------up");
			break;
		}
		boolean bool = super.onTouchEvent(event);
		Log.e("RelativeLayout", "viewgroup...-super==父类..onTouchEvent...." + bool);
		return bool;
	}
}
