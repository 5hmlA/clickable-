# clickable-
测试当控件的clickable设置为true后ontouchvent对事件处理的变化

当view 或者 viewgroup 设置clickable为true之后 他们在onTouchEvent中会消费down，move，up事件

布局分为  层次布局  包裹布局

层次布局的特点是  上层控件先处理事件 不处理 则事件被往下传递
				当下层事件 不消费事件时  事件丢失 不会往上传(不同于包裹布局)
