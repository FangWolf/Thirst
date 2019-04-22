package com.fangwolf.module_chat.debug;

import android.os.Bundle;

import com.fangwolf.module_chat.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import androidx.appcompat.app.AppCompatActivity;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_debug);
        EMClient.getInstance().login("0002 ", "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
                Logger.e("onSuccess");
            }

            @Override
            public void onError(int i, String s) {
                Logger.e("onError" + s);
            }

            @Override
            public void onProgress(int i, String s) {
                Logger.e("onProgress" + s);
            }
        });
    }
}
