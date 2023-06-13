package ru.hse.android.lesson6;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean foregroundServiceRunning = false;
    private boolean backgroundServiceBinded = false;
    private MyBindService bindService = null;
    private final ServiceConnection serverConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            // Care of exceptions here
            bindService = ((MyBindService.LocalBinder) binder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(MyForegroundService.CHANNEL_ID,
                    "foreground channel name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onForegroundClick(@NonNull View view) {
        Intent intent = new Intent(this, MyForegroundService.class);
        intent.putExtra(MyForegroundService.TEXT, "My amazing foreground service");
        if (foregroundServiceRunning) {
            foregroundServiceRunning = false;
            try {
                ((Button) view).setText(getResources().getString(R.string.start_foreground));
            } catch (Exception ignore) {
            }
            stopService(intent);
        } else {
            foregroundServiceRunning = true;
            try {
                ((Button) view).setText(getResources().getString(R.string.stop_foreground));
            } catch (Exception ignore) {
            }
            startForegroundService(intent);
        }
    }

    public void onBackgroundClick(@NonNull View view) {
        Intent intent = new Intent(this, MyBackgroundService.class);
        startService(intent);
        // Todo use broadcasts to obtain results.
    }

    public void onJobClick(@NonNull View view) {
        Intent intent = new Intent(this, MyJobService.class);
        MyJobService.enqueueWork(this, intent);
        // Todo use broadcasts to obtain results.
    }

    public void onBindClick(@NonNull View view) {
        Intent intent = new Intent(this, MyBindService.class);
        if (backgroundServiceBinded) {
            backgroundServiceBinded = false;
            try {
                ((Button) view).setText(getResources().getString(R.string.bind));
            } catch (Exception ignore) {
            }
            unbindService(serverConnection);
            bindService = null;
        } else {
            backgroundServiceBinded = true;
            try {
                ((Button) view).setText(getResources().getString(R.string.unbind));
            } catch (Exception ignore) {
            }
            bindService(intent, serverConnection, BIND_AUTO_CREATE);
        }
    }

    public void onRandomClicked(@NonNull View view) {
        if (bindService != null) {
            Toast.makeText(this, String.valueOf(bindService.getRandomNumber()), Toast.LENGTH_SHORT).show();
        }
    }
}