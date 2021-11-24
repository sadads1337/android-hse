package ru.hse.android.lesson0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {

    private final Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int sleepTime = intent.getIntExtra("SLEEP_TIME", 0);

        timer.schedule(new SleepTask(), sleepTime);
    }

    private class SleepTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(SecondActivity.this::finish);
        }
    }
}