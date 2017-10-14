package com.telecorp.dashqueue.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by softbaked on 8/25/16 AD.
 */
public class CustomSwipeableViewPager extends ViewPager {

    private boolean isSwipeable = true;

    public CustomSwipeableViewPager(Context context) {
        super(context);
    }

    public CustomSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isSwipeable) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isSwipeable) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public void setSwipeable(boolean isSwipeable) {
        this.isSwipeable = isSwipeable;
    }
}
