package com.example.clickabletest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 最终结论 view，viewgroup设置clickable为true那么 onToutchEvent对所有事件都 消费返回true
 * 
 * 判断事件是否消费，主要看 onTouchEvent 对down事件是否消费了(返回true)
 * 当down事件被消费了(返回false)，那么后续事件(move up)无论是否返回true还是false，那么后续事件都会被消费 不会被传递出去
 * 当down事件被丢失了(返回true)表示该控件不处理任何时间，那么后续事件(move up)都不会被传递到该控件
 * 
 * viewgroup的事件来源(onTouchEvent中处理的事件来源) 1，onInterceptHoverEvent拦截来的 2，下层控件不消费上传的
 * 
 * 事件处理 onTouchEvent-----要点：down返回true (告诉上层控件我处理所有事件)后续事件全部消费，，，
 * 																down返回 false ，(告诉上层控件我不处理任何事件)，后续事件根本不会被传递到该控件
 * 
 * view中的任何事件都是来自于 上层viewgroup控件onInterceptHoverEvent 并返回false （viewgroup不拦截事件才传到view中被处理）
 * 
 * move事件能被传递到 控件 说明down事件必然被消费了
 * 
 * @author Refuse
 * 
 */
public class MainActivity extends Activity {
	private List<String> data = new ArrayList();
	private MyListView mlv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mlv = (MyListView) findViewById(R.id.mlv);
		MyRelativeLayout rl = (MyRelativeLayout) findViewById(R.id.rl);
		getData();

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, data);
		MyAdapter adapter2 = new MyAdapter();
		mlv.setAdapter(adapter2);
	}

	public class MyAdapter extends BaseAdapter {
		public MyAdapter() {
		}

		public int getCount() {
			return MainActivity.this.data.size();
		}

		public Object getItem(int paramInt) {
			return null;
		}

		public long getItemId(int paramInt) {
			return 0L;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = new TextView(MainActivity.this);
			TextView tv2 = new TextView(
					MainActivity.this.getApplicationContext());
			MyTextView mtv = new MyTextView(
					MainActivity.this.getApplicationContext());
			MyTextView mtv2 = new MyTextView(MainActivity.this);
			tv.setText(data.get(position));
			mtv.setText(data.get(position));
			return mtv;
			// return tv;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void getData() {
		for (int i = 0;; i++) {
			if (i >= 10)
				return;
			this.data
					.add("今晚打老虎SD卡激發了看到該就愛看老師的價格發給大家愛國拉克是大家噶看到幾個阿的江噶的感覺啊啊可立即訂購阿哥卡里的結果了卡機的覆蓋了卡機的風格的快樂 ");
		}
	}

}
