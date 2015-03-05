package com.gys.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MyRatioViewPager extends ViewPager{

	public MyRatioViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyRatioViewPager(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec)/1.5)//这里设置两侧预显示的大小
				,MeasureSpec.getMode(widthMeasureSpec));
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	
}
