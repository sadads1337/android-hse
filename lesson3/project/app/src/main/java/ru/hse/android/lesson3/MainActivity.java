package ru.hse.android.lesson3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import ru.hse.android.lesson3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AppListAdapter.OnElementClickedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSupportFragmentManager().popBackStack();
    }

    private void addFragment(@NonNull PackageInfo packageInfo) {
        int target_view = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                ? R.id.app_details_fragment_container
                : R.id.menu_fragment;
        AppDetailsFragment appDetailsFragment = new AppDetailsFragment(packageInfo);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(target_view, appDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(@NonNull PackageInfo packageInfo) {
        addFragment(packageInfo);
    }
}