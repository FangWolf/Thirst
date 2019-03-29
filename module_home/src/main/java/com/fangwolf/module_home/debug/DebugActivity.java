package com.fangwolf.module_home.debug;

import android.os.Bundle;

import com.fangwolf.module_home.R;
import com.jaeger.library.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_debug);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white));
    }
}
