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
		boolean retu = true;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN://0
			 retu = super.onInterceptTouchEvent(event);
			Log.e("RelativeLayout",
					"down-----viewgroup.....onInterceptTouchEvent---------"+retu);
			break;
		case MotionEvent.ACTION_MOVE://2
			 retu = super.onInterceptTouchEvent(event);
			Log.e("RelativeLayout",
					"move------viewgroup.....onInterceptTouchEvent--------"+retu);
			break;
		case MotionEvent.ACTION_UP://1
			 retu = super.onInterceptTouchEvent(event);
			Log.e("RelativeLayout",
					"up------viewgroup.....onInterceptTouchEvent-------"+retu);
			break;
		}
		return retu;
	}

	//默认是 不消费任何事件  down返回false
	//当clickable为true时 会消费所有事件 down返回true
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean retu = true;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			 retu = super.onInterceptTouchEvent(event);
			Log.e("RelativeLayout",
					"down----viewgroup.....onTouchEvent--------"+retu);
			break;
		case MotionEvent.ACTION_MOVE:
			 retu = super.onInterceptTouchEvent(event);
			Log.e("RelativeLayout",
					"move----viewgroup.....onTouchEvent--------"+retu);
			break;
		case MotionEvent.ACTION_UP:
			 retu = super.onInterceptTouchEvent(event);
			Log.e("RelativeLayout",
					"up------viewgroup.....onTouchEvent--------"+retu);
			break;
		}
		return retu;
	}
}
