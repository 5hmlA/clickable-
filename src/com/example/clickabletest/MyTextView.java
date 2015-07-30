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
	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		switch (paramMotionEvent.getAction()) {
		//如果down事件return true那么后续事件都会被消费无论move up是否返回false事件都不会被传递出去了
		case MotionEvent.ACTION_DOWN:
			Log.i("TextView", "view......onTouchEvent--------------down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("TextView", "view......onTouchEvent--------------move");
			break;
			//只要down事件被消费了return true 那么后续事件都会被消费，不会传递出去了，尽管up的时候return false事件也不会被传出去，内部直接消费
		case MotionEvent.ACTION_UP:
			Log.i("TextView", "view......onTouchEvent--------------up");
			break;
		}
		return super.onTouchEvent(paramMotionEvent);
	}
}
