package com.example.miraclehwan.myapplicationclasstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView textView = (TextView) findViewById(R.id.sub_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        long mCurrentTime = System.currentTimeMillis();
        if (MyApplication.getAppInstance().isStartPinActivity(mCurrentTime)){
            Intent intent = new Intent(SubActivity.this, PinActivity.class);
            startActivity(intent);
        }else{
            MyApplication.getAppInstance().setLatestActive(getClass().getName(), System.currentTimeMillis());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.getAppInstance().setLatestActive(getClass().getName(), System.currentTimeMillis());
    }

    @Override
    public void onBackPressed() {

        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            moveTaskToBack(true);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 꺼버린다.", Toast.LENGTH_SHORT).show();
        }
    }

}
