package com.yc.zhangshangshanxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-26
 * Time: 下午9:52
 * To change this template use File | Settings | File Templates.
 */
public class FirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }


    @Override
    protected void onResume() {
        super.onResume();
            final Intent intent = new Intent(this,TabHostActivity.class);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            };
            timer.schedule(timerTask,1000*2);


    }
}
