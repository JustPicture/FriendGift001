package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ak.lkp.friendgift.R;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MallFragment extends Fragment {
private View view;
    private Button textView;
    private BottomNavigationBar bottomNavigationBar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static MallFragment newInstance(String param1) {
        MallFragment fragment = new MallFragment();
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
        view = inflater.inflate(R.layout.fragment_mall, container, false);
        textView = ((Button) view.findViewById(R.id.textView));
        bottomNavigationBar = (BottomNavigationBar) getActivity().findViewById(R.id.bottom_navigation_bar);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int i = bottomNavigationBar.getCurrentSelectedPosition();
//                Toast.makeText(getActivity(), "别点我"+i, Toast.LENGTH_SHORT).show();
//                bottomNavigationBar.hide();
//                EventBus.getDefault().post(new MessageEvent(getFragmentManager().beginTransaction(),CategoryFragment.newInstance("商店")));
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                CategoryFragment categoryFragment = CategoryFragment.newInstance("你好001");
//                ft.replace(R.id.layFrame, categoryFragment,"categoryFragment");
//                ft.commit();
//                EventBus.getDefault().post(new MessageEvent("过年了001"));
                bottomNavigationBar.selectTab(2,true);
            }
        });
        return view;
    }

}
