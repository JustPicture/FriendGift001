package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ak.lkp.friendgift.R;
import com.ak.lkp.friendgift.Utils.MyAdapter;
import com.ak.lkp.friendgift.Widget.CustomPopWindow;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftListFragment extends Fragment  implements View.OnClickListener{

    private TextView mButton1,mButton2,mButton3,mButton4;
    private CustomPopWindow mCustomPopWindow;
    private CustomPopWindow mListPopWindow;
    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static GiftListFragment newInstance(String param1) {
        GiftListFragment fragment = new GiftListFragment();
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
        view = inflater.inflate(R.layout.fragment_gift_list, container, false);
        mButton1 = (TextView) view.findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (TextView) view.findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (TextView) view.findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (TextView) view.findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                showPopBottom();
                break;
            case R.id.button2:
                showPopTop();
                break;
            case R.id.button3:
                showPopMenu();
                break;
            case R.id.button4:
                showPopListView();
                break;
        }
    }

    private void showPopBottom(){
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(R.layout.pop_layout1)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create()
                .showAsDropDown(mButton1,0,10);
    }

    private void showPopTop(){
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(R.layout.pop_layout2)
                .create();
        popWindow .showAsDropDown(mButton2,0,  - (mButton2.getHeight() + popWindow.getHeight()));
        //popWindow.showAtLocation(mButton1, Gravity.NO_GRAVITY,0,0);
    }

    private void showPopMenu(){
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .create()
                .showAsDropDown(mButton3,0,20);


    }

    private void showPopListView(){
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_list,null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        mListPopWindow= new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                .create()
                .showAsDropDown(mButton4,0,20);
    }

    private void handleListView(View contentView){
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter();
        adapter.setData(mockData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private List<String> mockData(){
        List<String> data = new ArrayList<>();
        for (int i=0;i<100;i++){
            data.add("Item:"+i);
        }

        return data;
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        showContent = "点击 Item菜单1";
                        break;
                    case R.id.menu2:
                        showContent = "点击 Item菜单2";
                        break;
                    case R.id.menu3:
                        showContent = "点击 Item菜单3";
                        break;
                    case R.id.menu4:
                        showContent = "点击 Item菜单4";
                        break;
                    case R.id.menu5:
                        showContent = "点击 Item菜单5" ;
                        break;
                }
                Toast.makeText(getActivity(),showContent,Toast.LENGTH_SHORT).show();
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);
        contentView.findViewById(R.id.menu5).setOnClickListener(listener);
    }

}
