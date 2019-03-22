package com.fangwolf.library_base.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;


/**
 * @Auther 獠牙血狼
 * @Date 2019/3/11
 * @Desc 基础dialog
 */
public class BaseDialog extends Dialog {
    private int layoutId;

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int layoutId) {
        super(context);
        this.layoutId = layoutId;
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId > 0) {
            setContentView(layoutId);
        }
        //居中
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        //背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}
