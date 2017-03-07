package com.ak.lkp.friendgift.Widget;


import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.util.AttributeSet;

/**
 * Created by likunpeng on 2017/3/7.
 */

public class PasswordKeyboardView extends KeyboardView implements OnKeyboardActionListener{
    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {

    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
