package com.reactnativenavigation.views;

import android.content.Context;
import android.graphics.Color;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.reactnativenavigation.animation.VisibilityAnimator;
import com.reactnativenavigation.params.ScreenParams;
import com.reactnativenavigation.params.StyleParams;
import com.reactnativenavigation.utils.ViewUtils;

import java.util.List;

public class BottomTabs extends AHBottomNavigation {

    private VisibilityAnimator visibilityAnimator;

    public BottomTabs(Context context) {
        super(context);
        setForceTint(true);
        setId(ViewUtils.generateViewId());
        createVisibilityAnimator();
    }

    public void addTabs(List<ScreenParams> params, OnTabSelectedListener onTabSelectedListener) {
        for (ScreenParams screenParams : params) {
            AHBottomNavigationItem item = new AHBottomNavigationItem(screenParams.title, screenParams.tabIcon,
                    Color.GRAY);
            addItem(item);
            setOnTabSelectedListener(onTabSelectedListener);
        }
    }

    public void setVisibility(boolean hidden, boolean animated) {
        visibilityAnimator.setVisible(hidden, animated);
    }

    public void setStyleFromScreen(StyleParams params) {
        setBackgroundColor(params.bottomTabsColor);

        if (params.bottomTabsButtonColor.hasColor()) {
            setInactiveColor(params.bottomTabsButtonColor.getColor());
        }

        if (params.selectedBottomTabsButtonColor.hasColor()) {
            setAccentColor(params.selectedBottomTabsButtonColor.getColor());
        }

        setForceTitlesDisplay(params.forceTitlesDisplay);

        setVisibility(params.bottomTabsHidden);
    }

    private void setBackgroundColor(StyleParams.Color bottomTabsColor) {
        if (bottomTabsColor.hasColor()) {
            setDefaultBackgroundColor(bottomTabsColor.getColor());
        } else {
            setDefaultBackgroundColor(Color.WHITE);
        }
    }

    private void setVisibility(boolean bottomTabsHidden) {
        setVisibility(bottomTabsHidden ? GONE : VISIBLE);
    }

    private void createVisibilityAnimator() {
        ViewUtils.runOnPreDraw(this, new Runnable() {
            @Override
            public void run() {
                visibilityAnimator = new VisibilityAnimator(BottomTabs.this,
                        VisibilityAnimator.HideDirection.Down,
                        getHeight());
            }
        });
    }
}
