package com.ak.lkp.friendgift.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ak.lkp.friendgift.R;
import com.ak.lkp.friendgift.Utils.KeyboradUtils;
import com.ak.lkp.friendgift.Widget.XNumberKeyboardView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements XNumberKeyboardView.IOnKeyboardListener{
    private View view;
    private XNumberKeyboardView view_keyboard;
    private EditText editText;
    private KeyboradUtils keyboardUtil;
//    private boolean isCheck;
    private RelativeLayout category_rl;

    public static CategoryFragment newInstance(String param1) {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        editText = (EditText) view.findViewById(R.id.edit_text);
        category_rl = (RelativeLayout) view.findViewById(R.id.category_rl);
        view_keyboard = ((XNumberKeyboardView) view.findViewById(R.id.view_keyboard));
//        keyboardUtil = new KeyboradUtils(getActivity(), editText);
//        keyboardUtil.hideSoftInputMethod();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    if(keyboardUtil == null){
                        keyboardUtil = new KeyboradUtils(getActivity(), editText);
                    }
                    keyboardUtil.hideSoftInputMethod();
                    view_keyboard.shuffleKeyboard();
                    keyboardUtil.showKeyboard();
                }else {
                    keyboardUtil.hideKeyboard();
                }
            }
        });

        category_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardUtil.hideKeyboard();
//                isCheck = !isCheck;
            }
        });
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!isCheck){
//                    if(keyboardUtil == null){
//                        keyboardUtil = new KeyboradUtils(getActivity(), editText);
//                    }
//                    keyboardUtil.hideSoftInputMethod();
//                    keyboardUtil.showKeyboard();
//                }else {
//                keyboardUtil.hideKeyboard();
//                }
//                isCheck = !isCheck;
//            }
//        });


        view_keyboard.setIOnKeyboardListener(this);
        return view;
    }

    @Override
    public void onInsertKeyEvent(String text) {
        editText.append(text);
    }

    @Override
    public void onDeleteKeyEvent() {
        int start = editText.length() - 1;
        if (start >= 0) {
            editText.getText().delete(start, start + 1);
        }
    }

    @Override
    public void onEmptyEvent() {
        keyboardUtil.hideKeyboard();
//        isCheck = !isCheck;
    }

}
