package ru.hse.android.lesson3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.hse.android.lesson3.databinding.FragmentAppListBinding;

public class AppListFragment extends Fragment {
    private RecyclerView.Adapter adapter;

    private FragmentAppListBinding binding;

    public AppListFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        adapter = new AppListAdapter(context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA), context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAppListBinding.inflate(inflater, container, false);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.appList.setHasFixedSize(true);

        binding.appList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.appList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
