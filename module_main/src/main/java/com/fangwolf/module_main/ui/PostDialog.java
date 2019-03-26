package com.fangwolf.module_main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangwolf.module_main.R;
import com.fangwolf.module_main.databinding.MainDialogPostBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @Auther 獠牙血狼
 * @Date 2019/3/26
 * @Desc 发布弹窗
 */
public class PostDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private MainDialogPostBinding BD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BD = DataBindingUtil.inflate(inflater, R.layout.main_dialog_post, container, false);
        BD.llPostText.setOnClickListener(this);
        BD.llPostImg.setOnClickListener(this);
        BD.llPostVideo.setOnClickListener(this);
        BD.llPostOther.setOnClickListener(this);
        return BD.getRoot();
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_post_text) {

        } else if (i == R.id.ll_post_img) {

        } else if (i == R.id.ll_post_video) {

        } else if (i == R.id.ll_post_other) {

        }
        dismiss();
    }

}
