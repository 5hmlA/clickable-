package com.example.clickabletest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {
	public MyTextView(Context paramContext) {
		super(paramContext);
	}

	public MyTextView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public MyTextView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean retu = true;
		switch (event.getAction()) {
		//如果down事件return true那么后续事件都会被消费无论move up是否返回false事件都不会被传递出去了
		case MotionEvent.ACTION_DOWN:
			retu =  super.onTouchEvent(event);
			Log.i("TextView", "down -----view......onTouchEvent-------"+retu);
			break;
		case MotionEvent.ACTION_MOVE:
			retu =  super.onTouchEvent(event);
			Log.i("TextView", "move -----view......onTouchEvent---------"+retu);
			break;
			//只要down事件被消费了return true 那么后续事件都会被消费，不会传递出去了，尽管up的时候return false事件也不会被传出去，内部直接消费
		case MotionEvent.ACTION_UP:
			retu =  super.onTouchEvent(event);
			Log.i("TextView", "up -----view......onTouchEvent-------"+retu);
			break;
		}
		return retu;
	}
}
