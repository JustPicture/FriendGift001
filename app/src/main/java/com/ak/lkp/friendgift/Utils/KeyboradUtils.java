package com.ak.lkp.friendgift.Utils;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.ak.lkp.friendgift.R;
import com.ak.lkp.friendgift.Widget.XNumberKeyboardView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.ak.lkp.friendgift.R.id.view_keyboard;

/**
 * Created by likunpeng on 2017/3/7.
 */

public class KeyboradUtils {
    private Activity activity;
    private EditText editText;
    private XNumberKeyboardView mKeyboardView;
    private Keyboard number_keyboar;

    public KeyboradUtils(Activity activity, EditText editText) {
        this.activity = activity;
        this.editText = editText;
        number_keyboar = new Keyboard(activity, R.xml.keyboard_number_password);
        mKeyboardView = (XNumberKeyboardView) activity.findViewById(view_keyboard);
    }
    /**
     * 软键盘展示状态
     */
    public boolean isShow() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    /**
     * 软键盘展示
     */
    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 软键盘隐藏
     */
    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 禁掉系统软键盘
     */
    public void hideSoftInputMethod() {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
        if (methodName == null) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName,
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (NoSuchMethodException e) {
                editText.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
