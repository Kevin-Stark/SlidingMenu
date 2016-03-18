package com.kevin.slidingmenutest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.kevin.slidingmenu.SlidingMenu;
import com.kevin.slidingmenutest.fragment.Fragment1;
import com.kevin.slidingmenutest.fragment.Fragment2;
import com.kevin.slidingmenutest.fragment.Fragment3;
import com.kevin.slidingmenutest.fragment.MenuFragment;

public class MainActivity extends FragmentActivity {
    private SlidingMenu menu;


    public Fragment1 fragment1;
    public Fragment2 fragment2;
    public Fragment3 fragment3;

    // 菜单的fragment管理
    private FragmentTransaction mMenuTransaction;
    private FragmentManager mMenuFragmentManager;
    // 内容的fragment管理
    private FragmentTransaction mContentTransaction;
    private FragmentManager mContentFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_frame);
        mMenuFragmentManager = getFragmentManager();
        mContentFragmentManager = getFragmentManager();
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.3f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置滑动菜单的视图界面
        menu.setMenu(R.layout.left_menu);
        mMenuFragmentManager.beginTransaction().add(R.id.menu_frame, new MenuFragment()).commit();
    }

    private void removeFragments() {
        if (fragment1 != null) {
            mContentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            mContentTransaction.remove(fragment2);
            fragment2 = null;
        }
        if (fragment3 != null) {
            mContentTransaction.hide(fragment3);
            fragment3 = null;
        }
    }

    public void setFragment(int index) {
        mContentTransaction = mContentFragmentManager.beginTransaction();
        removeFragments();
        switch (index) {
            case 1:
                if (fragment1 == null) {
                    fragment1 = new Fragment1();
                    mContentTransaction.add(R.id.content_frame, fragment1);
                } else {
                    mContentTransaction.show(fragment1);
                }
                break;
            case 2:
                if (fragment2 == null) {
                    fragment2 = new Fragment2();
                    mContentTransaction.add(R.id.content_frame, fragment2);
                } else {
                    mContentTransaction.show(fragment2);
                }
                break;
            case 3:
                if (fragment3 == null) {
                    fragment3 = new Fragment3();
                    mContentTransaction.add(R.id.content_frame, fragment3);
                } else {
                    mContentTransaction.show(fragment3);
                }
                break;
            default:
                break;
        }
        mContentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        //点击返回键关闭滑动菜单
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    //打开抽屉
    public void openDraw() {
        menu.showMenu();
    }

    //关闭抽屉
    public void closeDraw() {
        menu.showContent();
    }
}
