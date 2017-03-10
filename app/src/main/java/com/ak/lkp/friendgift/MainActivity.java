package com.ak.lkp.friendgift;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ak.lkp.friendgift.HelloCharts.BubbleChartActivity;
import com.ak.lkp.friendgift.HelloCharts.ColumnChartActivity;
import com.ak.lkp.friendgift.HelloCharts.ComboChartActivity;
import com.ak.lkp.friendgift.HelloCharts.LineChartActivity;
import com.ak.lkp.friendgift.HelloCharts.PieChartActivity;
import com.ak.lkp.friendgift.HelloCharts.PreviewChartsActivity;
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
    private Fragment currentFragment;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] lvs = {"Line chart", "Column chart", "Pie chart", "Bubble chart","Combo chart","Preview charts"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        ListView listView = (ListView) findViewById(R.id.drawleft_listview);

        toolbar.setBackgroundColor(Color.parseColor("#33bbee"));
        // App Logo
//        toolbar.setLogo(R.mipmap.ic_tab_profile_normal);
        // Title
//        toolbar.setTitle("My Title");
        toolbar.setTitle("");
        // Sub Title
//        toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.mipmap.ic_tab_mall_normal);
//        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        textView.setText("首页");
//        toolbar.setVisibility(View.GONE);
        if (null != getSupportActionBar()){
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        //设置菜单列表
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lvs);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(MainActivity.this,LineChartActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,ColumnChartActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,PieChartActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this,BubbleChartActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this,ComboChartActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this,PreviewChartsActivity.class));
                        break;
                    default:
                        break;
                }

            }
        });


        final BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
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

        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomNavigationBar.selectTab(3);
//            }
//        });
    }


    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        homeFragment = HomeFragment.newInstance("首页");
//        transaction.replace(R.id.layFrame,homeFragment,"homeFragment");
//        transaction.commit();

        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance("首页");
        }
        if (!homeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction().add(R.id.layFrame, homeFragment).commit();

            // 记录当前Fragment
            currentFragment = homeFragment;
        }

    }


    @Override
    public void onTabSelected(int position) {

//        if (fragments != null) {
//            if (position < fragments.size()) {
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment fragment = fragments.get(position);
//                if (fragment.isAdded()) {
//                    ft.replace(R.id.layFrame, fragment);
//
//                } else {
//                    ft.add(R.id.layFrame, fragment);
//                }
//                ft.commitAllowingStateLoss();
//            }
//        }

        switch (position) {
            case 0:
                if(homeFragment== null){
                    homeFragment = HomeFragment.newInstance("首页");
                }
//                ft.replace(R.id.layFrame, homeFragment,"homeFragment");
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);
                break;
            case 1:
                if(giftListFragment== null){
                    giftListFragment = GiftListFragment.newInstance("榜单");
                }
//                ft.replace(R.id.layFrame, giftListFragment,"giftListFragment");
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), giftListFragment);
                break;
            case 2:
                if(categoryFragment== null){
                    categoryFragment = CategoryFragment.newInstance("商店");
                }
//                ft.replace(R.id.layFrame, categoryFragment,"categoryFragment");
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), categoryFragment);
                break;
            case 3:
                if(mallFragment== null){
                    mallFragment = MallFragment.newInstance("分类");
                }
//                ft.replace(R.id.layFrame, mallFragment,"mallFragment");
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), mallFragment);
                break;
            case 4:
                if(meFrament== null){
                    meFrament = MeFragment.newInstance("我的");
                }
//                ft.replace(R.id.layFrame, meFrament,"meFrament");
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFrament);
                break;

            default:
                break;
        }
       // 事务提交
//        ft.commit();
    }


    @Override
    public void onTabUnselected(int position) {
//        if (fragments != null) {
//            if (position < fragments.size()) {
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment fragment = fragments.get(position);
//                ft.remove(fragment);
//                ft.commitAllowingStateLoss();
//            }
//        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 添加或者显示 fragment
     *
     * @param transaction
     * @param fragment
     */
    public void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.layFrame, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
////        return super.onCreateOptionsMenu(menu);
//    }
//    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            String msg = "";
//            switch (menuItem.getItemId()) {
//                case R.id.action_edit:
//                    msg += "Click edit";
//                    break;
//                case R.id.action_share:
//                    msg += "Click share";
//                    break;
//                case R.id.action_settings:
//                    msg += "Click setting";
//                    break;
//            }
//
//            if(!msg.equals("")) {
//                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
//    };

}
