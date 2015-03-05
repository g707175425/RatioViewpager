package com.gys.viewpager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class MyRatioPagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager mPager;
    boolean mNeedsRedraw = false;
    private EdgeEffectCompat leftEdge;
    private EdgeEffectCompat rightEdge;

    public MyRatioPagerContainer(Context context) {
        super(context);
        init();
    }

    public MyRatioPagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRatioPagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setClipChildren(false);
        try {
            Method setLayerTypeMethod = this.getClass().getMethod("setLayerType", new Class[] {int.class, Paint.class});
            setLayerTypeMethod.invoke(this, new Object[] {LAYER_TYPE_SOFTWARE, null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onFinishInflate() {
        try {
            mPager = (ViewPager) getChildAt(0);
            mPager.setOnPageChangeListener(this);
                Field leftEdgeField = mPager.getClass().getSuperclass().getDeclaredField("mLeftEdge");
                Field rightEdgeField = mPager.getClass().getSuperclass().getDeclaredField("mRightEdge");
                if (leftEdgeField != null && rightEdgeField != null) {
                  leftEdgeField.setAccessible(true);
                  rightEdgeField.setAccessible(true);
                  leftEdge = (EdgeEffectCompat) leftEdgeField.get(mPager);
                  rightEdge = (EdgeEffectCompat) rightEdgeField.get(mPager);
                }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mNeedsRedraw) invalidate();
        if (leftEdge != null && rightEdge != null) {
            leftEdge.finish();
            rightEdge.finish();
            leftEdge.setSize(0, 0);
            rightEdge.setSize(0, 0);
          }
    }

    @Override
    public void onPageSelected(int position) { }

    @Override
    public void onPageScrollStateChanged(int state) {
        mNeedsRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
    }
}