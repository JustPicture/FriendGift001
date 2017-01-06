package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.lkp.friendgift.Entity.ItemEntity;
import com.ak.lkp.friendgift.Entity.LkpResponse;
import com.ak.lkp.friendgift.OkGo.DialogCallback;
import com.ak.lkp.friendgift.R;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "blithelkp+HomeFragment";
    public static final String itemTitle = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=0";
//    private String[] mTitle = new String[20];
//    private String[] mData = new String[20];
    private List<String> mTitle = new ArrayList<>();
    private List<String> mData = new ArrayList<>();
    private View view;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mTabLayout = ((TabLayout) view.findViewById(R.id.home_tl));
        mViewPager = ((ViewPager) view.findViewById(R.id.home_vp));
//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//设置Tab的标题来自PagerAdapter.getPageTitle()
//        final TabLayout.TabLayoutOnPageChangeListener listener =
//                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
//        mViewPager.addOnPageChangeListener(listener);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    //初始化数据源
    private void initData() {
//        for (int i = 0; i < 20; i++) {
//            mTitle[i] = "搞笑" + i;
//            mData[i] = "data" + i;
//        }

        OkGo.get(itemTitle)
                .tag(this)
                .connTimeOut(3000)
                .execute(new DialogCallback<LkpResponse<ItemEntity.DataBean>>(getActivity()) {
                    @Override
                    public void onSuccess(LkpResponse<ItemEntity.DataBean> itemEntityLkpResponse, Call call, Response response) {
                        List<String> ttile= new ArrayList<>();
                        List<String> ddata= new ArrayList<>();
                        for (int i = 0; i < itemEntityLkpResponse.data.getCandidates().size(); i++) {
                            String title= itemEntityLkpResponse.data.getCandidates().get(i).getName();

                            ttile.add(title);
                            ddata.add(title);
                        }
                            mTitle.addAll(ttile);
                            mData.addAll(ddata);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object instantiateItem(View container, int position) {
            TextView tv = new TextView(getActivity());
            tv.setTextSize(30.f);
            tv.setText(mData.get(position));
            ((ViewPager) container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}
