package com.fangwolf.library_base.base;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/12
 * @Desc 基础DialogFragment
 */
public class BaseDialogFragment extends AppCompatDialogFragment {

    @Override
    public void onStart() {
//        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        super.onStart();
//        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        getDialog().getWindow().getDecorView().setSystemUiVisibility(uiOptions);
//        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
//        设置位置
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.gravity =Gravity.CENTER;
        getDialog().getWindow().setAttributes(lp);
    }
}
