package com.example.clickabletest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {
	public MyListView(Context paramContext) {
		super(paramContext);
	}

	public MyListView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public MyListView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	// listview在onInterceptTouchEvent的時候，會攔截 &y>&x 時候的move事件
	// 將攔截到的move事件交給自己的onTouchEvent處理
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("listview",
					"viewgroup.....onInterceptTouchEvent--------------down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("listview",
					"viewgroup.....onInterceptTouchEvent--------------move");
			break;
		case MotionEvent.ACTION_UP:
			Log.d("listview",
					"viewgroup.....onInterceptTouchEvent--------------up");
			break;
		}
		boolean retu = super.onInterceptTouchEvent(event);
		Log.d("listview", "viewgroup....-super==父类..onInterceptTouchEvent...." + retu);
		return retu;
	}

	// listview中onTouchEvent会对down事件消费 也就是说会消费down move up事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		// 判断事件是否会被传出去只要看down事件是否被消费，down被消费则当前以及后续事件无论如何都不会被传出去
		case MotionEvent.ACTION_DOWN:
			Log.d("listview", "viewgroup.....onTouchEvent--------------down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("listview", "viewgroup.....onTouchEvent--------------move");
			break;
		// move up事件是否会被接收
		// 1，要down事件被消费，后续事件可被接收，2，down事件被丢失(down传过来了我不消费返回false) 返回false，(告诉上层控件我不处理任何事件)那么后续事件不会被传到该控件
		// 3，down事件没被消费（具体是down事件根本就没被传递到该控件,必需要 不拦截(只能)下层控件消费down事件）
		// 但是onInterceptTouchEvent拦截了move,up事件
		// 2，down事件没到该控件但是后续事件能到这 （viewgroup中拦截了move up事件）
		// 也可能是下层传递上来的（错误，当down事件没被传到此说明down事件被下层消费，那么后续事件就不会外传了，(move
		// up)不肯能被传到该控件）
		case MotionEvent.ACTION_UP:
			Log.d("listview", "viewgroup.....onTouchEvent--------------up");
			break;
		}
		if (0 == pointToPosition((int) event.getX(), (int) event.getY())) {
			// 结束 不走super的滑动代码
			return false;
		}
		boolean bool = super.onTouchEvent(event);// 在move事件中 完成 滑動效果，并將move事件消費
		Log.d("listview", "viewgroup...-super==父类..onTouchEvent...." + bool);
		return bool;
	}
}
