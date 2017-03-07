package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mData = new ArrayList<>();
    private ArrayList<Integer> mId = new ArrayList<>();
    private List<HomeCommonFragment> commonFragmentList = new ArrayList<>();

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
    }

    //初始化数据源
    private void initData() {
        OkGo.get(itemTitle)
                .tag(this)
                .connTimeOut(5000)
                .execute(new DialogCallback<LkpResponse<ItemEntity.DataBean>>(getActivity()) {
                    @Override
                    public void onSuccess(LkpResponse<ItemEntity.DataBean> itemEntityLkpResponse, Call call, Response response) {
                        mTitle.clear();
                        mId.clear();
                        for (int i = 0; i < itemEntityLkpResponse.data.getChannels().size(); i++) {
                            String title = itemEntityLkpResponse.data.getChannels().get(i).getName();
                            int id = itemEntityLkpResponse.data.getChannels().get(i).getId();
                            mId.add(id);
                            Log.i("=====---======", "onSuccess: id===="+id);
                            mTitle.add(title);
                        }
                        for (int i = 0; i < mTitle.size(); i++) {
                            Log.i("=====---======", "onSuccess: mTitle===="+mId.get(i));
                          commonFragmentList.add(HomeCommonFragment.newInstance(mId.get(i).toString()));
                        }

                        mViewPager.setAdapter(new MyViewpagerAdapter(getActivity().getSupportFragmentManager()));
                        mTabLayout.setupWithViewPager(mViewPager);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    public class MyViewpagerAdapter extends FragmentPagerAdapter{

        public MyViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return commonFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return commonFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }

//    private PagerAdapter mAdapter = new PagerAdapter() {
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitle.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mTitle.size();
//        }
//
//        @Override
//        public Object instantiateItem(View container, int position) {
//            TextView tv = new TextView(getActivity());
//            tv.setTextSize(30.f);
//            tv.setText(mId.get(position)+"");
//            ((ViewPager) container).addView(tv);
//            return tv;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((ViewPager) container).removeView((View) object);
//
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//    };
//
    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}
