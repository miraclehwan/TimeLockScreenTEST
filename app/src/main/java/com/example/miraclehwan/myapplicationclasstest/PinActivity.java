package com.example.miraclehwan.myapplicationclasstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PinActivity extends AppCompatActivity {

    Boolean isLogin = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockscreen);

        TextView mLockText = (TextView) findViewById(R.id.lockscreen);

        mLockText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = true;
                if (MyApplication.getmLatestActiveActivityName() == null){
                    MyApplication.getAppInstance().setLatestActive(getClass().getName(), System.currentTimeMillis());
                    Intent intent = new Intent(PinActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isLogin){
            moveTaskToBack(true);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
