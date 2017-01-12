package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.lkp.friendgift.R;
import com.ak.lkp.friendgift.Utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    private View view;

    private TextView category_tv1;
    private LinearLayout category_ll;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1) {
        CategoryFragment fragment = new CategoryFragment();
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        category_tv1 = ((TextView) view.findViewById(R.id.category_tv1));
        category_ll = ((LinearLayout) view.findViewById(R.id.category_ll));
        category_tv1.setText(mParam1);
        return view;
    }
    @Subscribe
    public void onMessage(MessageEvent messageEvent){
    category_tv1.setText(messageEvent.message);

}


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
