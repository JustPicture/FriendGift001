package com.ak.lkp.friendgift;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ak.lkp.friendgift.UI.CategoryFragment;
import com.ak.lkp.friendgift.UI.GiftListFragment;
import com.ak.lkp.friendgift.UI.HomeFragment;
import com.ak.lkp.friendgift.UI.MallFragment;
import com.ak.lkp.friendgift.UI.MeFragment;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private ArrayList<Fragment> fragments;
    private static final String TAG = "blithelkp+MainActivity";
    private HomeFragment homeFragment;
    private GiftListFragment giftListFragment;
    private CategoryFragment categoryFragment;
    private MallFragment mallFragment;
    private MeFragment meFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("5")
                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_tab_home_selected, "首页").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tab_gift_normal, "榜单").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tab_mall_normal, "商店").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tab_category_normal, "分类").setActiveColorResource(R.color.red))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tab_profile_normal, "我的").setActiveColorResource(R.color.red)).setBarBackgroundColor(R.color.colorPrimary)
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }


    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame,homeFragment);
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        giftListFragment = new GiftListFragment();
        categoryFragment = new CategoryFragment();
        mallFragment = new MallFragment();
        meFrament = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(giftListFragment);
        fragments.add(categoryFragment);
        fragments.add(mallFragment);
        fragments.add(meFrament);
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
