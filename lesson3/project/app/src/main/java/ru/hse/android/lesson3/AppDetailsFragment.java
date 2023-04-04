package ru.hse.android.lesson3;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.hse.android.lesson3.databinding.FragmentAppListElementDetailsBinding;

public class AppDetailsFragment extends Fragment {
    private FragmentAppListElementDetailsBinding binding;
    private PackageInfo packageInfo;

    public AppDetailsFragment() {

    }

    public AppDetailsFragment(@NonNull PackageInfo info) {
        packageInfo = info;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAppListElementDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (packageInfo != null) {
            binding.appDetails.setText(packageInfo.applicationInfo.toString());
        }
    }
}
