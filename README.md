# 事件分发 -- 探究
2015/7/30 21:07:39 

测试当控件的clickable设置为true后ontouchvent对事件处理的变化

		当控件设置为可点击，那么viewgroup和view的onTouchEvent对所有事件都会消费掉
		当view 或者 viewgroup 设置clickable为true之后 他们在onTouchEvent中会消费down，move，up事件

----------------------------
2015/7/31 21:14:40 
##布局分为 ------（ 层次布局 ， 包裹布局）



###层次布局的特点是  

- 1, 上层布局先处理事件 不处理 则事件被往下传递到下层布局

- 2, 当下层布局 不消费事件时  事件丢失 不会往上传(不同于包裹布局)
		
		（事件能传到下层布局说明一定是上层布局的控件都没消费事件）

- 3, 当下层布局消费了down事件，那么后续事件直接由下层布局处理，------activity不会将后续事件分发(dispatchTouchEvent)给上层布局，而是直接分发给下层布局

- 只发生在RelativeLayout，FrameLayout所包裹的布局中，，层次布局的最外必然是包裹布局（必然是最外层包裹布局的内层布局）




		层次布局中可能有包裹布局，，包裹布局中也可能有层次布局

------------------------------------

	(后续事件统一指move up)	事件消费在onTouchEvent中体现
	dispatchTouchEvent：事件分发 		onInterceptTouchEvent：事件拦截		onTouchEvent：事件消费(事件处理)
	需要留意的是 处理（onTouchEvent）之前会查看是否存在事件处理监听OnTouchListener（onTouch）

##判断控件（view或者viewgroup）是否消费所有事件，只要看 控件对down事件是否有消费(true表示消费，事件终止---(down的时候)false表示事件丢失，事件外传)

		当down事件被消费了 那么后续事件都会被消费，无论代码中对move up事件的处理是返回false 还是true, 事件都不会被外传了

		包裹布局中 -- 被传递到内层布局的down事件 必然是 外层布局(必然是viewgroup)不拦截的事件(必然经过了外层布局的onInterceptTouchEvent(返回false))(此时后续事件要被传递到内层布局，就需要下层布局消费down事件)
		层次布局中 -- 被传递到下层布局的down事件 必然是 上层布局没消费的事件，而且 activity 必然不会将后续事件分发到上层布局

- 不消费down事件的情况只有一种(down事件被传过来了，没消费)，那么后续事件都不会被传递到该控件

- 后续事件 能被传递到控件的 - onTouchEvent

		1, view --- down事件传递到down同时view消费了down,返回true
		2, viewgroup -- 两种情况
					1，down事件传递到down同时view消费了down,返回true
					2，down事件根本没被传递到onTouchEvent（down事件被内层控件消费），同时move事件被viewgroup拦截了，那么就可以处理move事件了
					此情况 是否处理后续事件的重要前提是down事件根本没被传递到控件viewgroup，同时后续事件只有通过拦截才可被传递到onTouchEvent处理
					--(此种情况，在viewgroup对down返回false时也一样，因为事件根本没被传递过来，没有 没消费down事件之说)


ViewGroup对事件的分发拦截处理  代码流程----    disallowIntercept(指是否禁用掉事件拦截的功能,默认false)--- （onTouchEvent）之前会查看是否存在事件处理监听OnTouchListener（onTouch）
		
	public boolean disPatchTouchevent(MoventionEvent ev){
		if(down事件被消费(onTouchEvent消费了down事件)|| disallowIntercept||onInterceptTouchEvent(MoventionEvent event)){
		//事件拦截自己消费
			if(OnTouchListener存在){
				if(Listener.onTouch){
					return true;事件被当前控件消费
				}else{
					return onTouchEvent(MoventionEvent event)
				}
			}else{
				return onTouchEvent(MoventionEvent event)
				如果onTouchEvent(MoventionEvent event)返回的是false -->那么disPatchTouchevent返回false事件被向上传递 告诉外界我不处理事件，后续事件就不会被传递到此
				如果onTouchEvent(MoventionEvent event)返回的是true -->那么disPatchTouchevent返回true事件被当前控件消费
			}
		}else{ 
		//事件不拦截 --看是否存在内部控件 --onInterceptTouchEvent(MoventionEvent event)==false的时候
		   if(是否存在内层控件(是否有包裹内部控件)){
			    事件向内传递到内部控件（view或者viewGroup）(位于内层) (view的处理流程disPatchTouchevent---OnTouchListener存在？-----onTouchEvent)
			}else{
			//不存在内部控件的时候
			    if(ViewGroup的OnTouchListener存在){  Listener.onTouch执行
					if(ViewGroup的Listener.onTouch返回值){
					//当ViewGroup的Listener.onTouch返回true的时候 表示事件被当前控件消费 事件结束传递 
					(后续事件不经过onInterceptTouchEvent的判断直接到这Listener.onTouch)
					}else{
						//当ViewGroup的Listener.onTouch返回false的时候 事件被传递到onTouchEvent
						return onTouchEvent(MoventionEvent event)
					}
				}else{
				//ViewGroup的OnTouchListener不存在的时候
					return ViewGroup的onTouchEvent(MoventionEvent event)
					如果onTouchEvent(MoventionEvent event)返回的是false那么disPatchTouchevent返回false事件被向上传递
					如果onTouchEvent(MoventionEvent event)返回的是true那么disPatchTouchevent返回true事件被当前控件消费
				}
			}
	   }
	}
	

View  事件分发处理 代码流程    （onTouchEvent）之前会查看是否存在事件处理监听OnTouchListener（onTouch）

	public boolean disPatchTouchevent(MoventionEvent ev){
			if(OnTouchListener存在){
				if(Listener.onTouch){
					return true;事件被当前控件消费
				}else{
					return onTouchEvent(MoventionEvent event)
					如果onTouchEvent(MoventionEvent event)返回的是false那么disPatchTouchevent返回false事件被向上传递
					如果onTouchEvent(MoventionEvent event)返回的是true那么disPatchTouchevent返回true事件被当前控件消费
				}
			}else{
				//view的ViewonTouchEvent中 当view是clickable为true的那么 down move up事件都会被消费返回true
				return onTouchEvent(MoventionEvent event)
				如果onTouchEvent(MoventionEvent event)返回的是false那么disPatchTouchevent返回false事件被向上传递
				如果onTouchEvent(MoventionEvent event)返回的是true那么disPatchTouchevent返回true事件被当前控件消费
			}
		}
	}


相关文章：

[http://blog.csdn.net/guolin_blog/article/details/9097463](http://blog.csdn.net/guolin_blog/article/details/9097463 "Android事件分发机制完全解析，带你从源码的角度彻底理解(上) ")

[http://blog.csdn.net/guolin_blog/article/details/9153747](http://blog.csdn.net/guolin_blog/article/details/9153747 "Android事件分发机制完全解析，带你从源码的角度彻底理解(下) ")

[http://hunankeda110.iteye.com/blog/1944311](http://hunankeda110.iteye.com/blog/1944311 "Android触摸事件分发机制")

[http://blog.csdn.net/aigestudio/article/details/44260301](http://blog.csdn.net/aigestudio/article/details/44260301 "droid事件分发完全解析之为什么是她")

[http://blog.csdn.net/aigestudio/article/details/44746625](http://blog.csdn.net/aigestudio/article/details/44746625 "Android事件分发完全解析之事件从何而来")