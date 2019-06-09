package com.fangwolf.library_base.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


/**
 * @Auther 獠牙血狼
 * @Date 2019/3/11
 * @Desc 基础dialog
 */
public abstract class BaseDialog<BindingType extends ViewDataBinding> extends Dialog {
    public BindingType BD;
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
        layoutId = setLayoutID();
        if (layoutId != 0) {
            BD = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutId, null, false);
            setContentView(BD.getRoot());
        }
        //居中
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        //背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
    }

    public abstract int setLayoutID();

    public abstract void initView();

}
