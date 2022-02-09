package ru.hse.android.lesson4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private UUID workId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        Button button = findViewById(R.id.work_button);
        button.setOnClickListener(view -> {
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
            workId = request.getId();

            WorkManager.getInstance(this).enqueue(request);

            view.setClickable(false);
            view.setBackgroundColor(Color.RED);

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(workId).observe(this, workInfo -> {
                if (workInfo.getState() == WorkInfo.State.RUNNING) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else if (workInfo.getState() == WorkInfo.State.SUCCEEDED || workInfo.getState() == WorkInfo.State.FAILED) {
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(MainActivity.this, "Work finished: " + workInfo.getState().toString(), Toast.LENGTH_LONG).show();

                    button.setClickable(true);
                    button.setBackgroundColor(Color.GREEN);
                }
            });
        });

    }
}