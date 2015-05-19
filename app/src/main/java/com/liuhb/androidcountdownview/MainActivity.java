package com.liuhb.androidcountdownview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.liuhb.androidcountdownview.view.CountDownView;


public class MainActivity extends Activity {

    private CountDownView mTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTime = (CountDownView) findViewById(R.id.time);
        mTime.setOnFinishedListener(new CountDownView.OnFinishedListener() {
            @Override
            public void onFinished() {
                Toast.makeText(MainActivity.this, "计时结束", Toast.LENGTH_SHORT).show();
            }
        });
        mTime.setMills(0 * 24 * 60 * 60 * 1000 + 0 * 60 * 60 * 1000 + 1 * 60 * 1000 + 3 * 1000 + 200);

    }

}
