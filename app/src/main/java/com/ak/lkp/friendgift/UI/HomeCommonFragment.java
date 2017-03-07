package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.lkp.friendgift.Adapter.UserItemRecyclerViewAdapter;
import com.ak.lkp.friendgift.Entity.CommonBean;
import com.ak.lkp.friendgift.R;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeCommonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeCommonFragment extends Fragment {

    private static final String urlLeft = "http://api.liwushuo.com/v2/channels/";
    private static final String urlRight = "/items_v2?limit=20&ad=2&gender=1&offset=0&generation=0";
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private View view;
    private LRecyclerView mRecyclerView;
        private UserItemRecyclerViewAdapter mDataAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;


    public HomeCommonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomeCommonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeCommonFragment newInstance(String param1) {
        HomeCommonFragment fragment = new HomeCommonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_common, container, false);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView = ((LRecyclerView) view.findViewById(R.id.content_LRecyclerView));

        OkGo.get(urlLeft + mParam1 + urlRight).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                List<CommonBean> list = new ArrayList<>();
                try {
                    JSONObject jsonObject1= new JSONObject(s);
                    JSONObject jsonObject2 = new JSONObject(jsonObject1.getString("data"));
                    JSONArray jsonArray1 = new JSONArray(jsonObject2.getString("items"));
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        CommonBean bean = new CommonBean();

                        JSONObject jsonObject3 = jsonArray1.getJSONObject(i);

                        if(null!= jsonObject3.getString("title")){
                        bean.setDescription(jsonObject3.getString("title"));
                        }
                        if(null!=jsonObject3.getString("likes_count")){
                        bean.setLikes_count(jsonObject3.getString("likes_count"));
                            }
                            if (null != jsonObject3.getJSONObject("column")){
                        JSONObject jsonObject4 = jsonObject3.getJSONObject("column");
                                if(null!= jsonObject4.getString("cover_image_url")){
                        bean.setCover_image_url(jsonObject4.getString("cover_image_url"));}}
                                if (null != jsonObject3.getJSONObject("author")){
                        JSONObject jsonObject5 = jsonObject3.getJSONObject("author");
                                        if(null!= jsonObject5.getString("nickname")){
                        bean.setNickname(jsonObject5.getString("nickname"));}
                                            if(null!= jsonObject5.getString("introduction")){
                        bean.setIntroduction(jsonObject5.getString("introduction"));}
                                                if(null!= jsonObject5.getString("avatar_url")){
                        bean.setAvatar_url(jsonObject5.getString("avatar_url"));}}
                        list.add(bean);
                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mDataAdapter = new UserItemRecyclerViewAdapter(getActivity(), list);
                    mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
                    mRecyclerView.setAdapter(mLRecyclerViewAdapter);
                    mRecyclerView.setPullRefreshEnabled(false);
                    mRecyclerView.setLoadMoreEnabled(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



