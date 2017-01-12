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

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "blithelkp+HomeFragment";
    public static final String itemTitle = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=0";
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mData = new ArrayList<>();
    private View view;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);//设置Tab的标题来自PagerAdapter.getPageTitle()
//        final TabLayout.TabLayoutOnPageChangeListener listener =
//                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
//        mViewPager.addOnPageChangeListener(listener);

    }

    //初始化数据源
    private void initData() {
        OkGo.get(itemTitle)
                .tag(this)
                .connTimeOut(5000)
                .execute(new DialogCallback<LkpResponse<ItemEntity.DataBean>>(getActivity()) {
                    @Override
                    public void onSuccess(LkpResponse<ItemEntity.DataBean> itemEntityLkpResponse, Call call, Response response) {
                        mData.clear();
                        mTitle.clear();
                        for (int i = 0; i < itemEntityLkpResponse.data.getCandidates().size(); i++) {
                            String title = itemEntityLkpResponse.data.getCandidates().get(i).getName();

                            mData.add(title);
                            mTitle.add(title);
                        }

                        mViewPager.setAdapter(mAdapter);
                        mTabLayout.setupWithViewPager(mViewPager);

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
            return mTitle.size();
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
