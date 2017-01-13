package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.lkp.friendgift.R;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeCommonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeCommonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private LRecyclerView lrecyclerview;


    public HomeCommonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeCommonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeCommonFragment newInstance(String param1, String param2) {
        HomeCommonFragment fragment = new HomeCommonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        lrecyclerview = ((LRecyclerView) view.findViewById(R.id.content_LRecyclerView));
    }

}
