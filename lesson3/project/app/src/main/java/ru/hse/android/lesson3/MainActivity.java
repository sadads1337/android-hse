package ru.hse.android.lesson3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import ru.hse.android.lesson3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        setContentView(view);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.appList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        binding.appList.setLayoutManager(layoutManager);

        adapter = new AppListAdapter(getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA));
        binding.appList.setAdapter(adapter);
    }
}