package com.telecorp.dashqueue.custom;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.telecorp.dashqueue.R;

/**
 * Created by Saran on 13/10/2560.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    private CustomSwipeableViewPager mViewPager;
    private static final String KEY_CAN_SCROLL_VERTICALLY = "CAN_SCROLL_VERTICALLY";

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public void setupWithViewPager(CustomSwipeableViewPager viewPager, TabLayout tabLayout) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                setEnabled(state != ViewPager.SCROLL_STATE_DRAGGING);
            }
        });

    }

    @Override
    public boolean canChildScrollUp() {
        if (null != mViewPager) {
            return canViewScrollUp(mViewPager);
        } else {
            return super.canChildScrollUp();
        }

    }


    private static boolean canViewScrollUp(View view) {
        // For ICS and above we can call canScrollVertically() to determine this
        Log.i(KEY_CAN_SCROLL_VERTICALLY, "Can " + ViewCompat.canScrollVertically(view, -1));
        return ViewCompat.canScrollVertically(view, -1);
    }
}
