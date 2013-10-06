package com.example.touchdrawexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View.OnTouchListener;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener{

	ImageView imageView;
	Bitmap bitmap;
	Canvas canvas;
	Paint paint;
	
	float startx = 0f;
	float starty = 0f;
	float endx = 0f;
	float endy = 0f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//全屏显示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//横屏显示
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView)findViewById(R.id.imageView);
		Display display = getWindowManager().getDefaultDisplay();
		
		bitmap = Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		//设置画笔
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		imageView.setImageBitmap(bitmap);
		imageView.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			startx = event.getX();
			starty = event.getY();
			break;
			
		case MotionEvent.ACTION_UP:
			break;
			
		case MotionEvent.ACTION_MOVE:
			endx = event.getX();
			endy = event.getY();
			
			canvas.drawLine(startx, starty, endx, endy, paint);
			//重绘图像
			imageView.invalidate();
			//重新设置起始坐标
			startx = endx;
			starty = endy;
			break;
			
		case MotionEvent.ACTION_CANCEL:
			break;
			
	    default:
			break;
		}
		
		return true;
	}
	

}
