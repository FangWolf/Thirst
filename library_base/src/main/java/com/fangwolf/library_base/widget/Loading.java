package com.fangwolf.library_base.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;

import com.fangwolf.library_base.R;


/**
 * @Auther 獠牙血狼
 * @Date 2019/3/7
 * @Desc loading
 */
public class Loading extends Dialog {

    public Loading(Context context) {
        super(context, R.style.activity_Theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        //居中
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        //背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
