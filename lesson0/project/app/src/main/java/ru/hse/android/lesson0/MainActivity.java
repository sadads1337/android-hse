package ru.hse.android.lesson0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Log.i(LOG_TAG, "Clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("SLEEP_TIME", 10000);
        startActivity(intent);
    }
}