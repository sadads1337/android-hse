package ru.hse.android.lesson3;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppListViewHolder> {
    private final List<PackageInfo> dataSet;

    public interface OnElementClickedListener {
        void onClick(@NonNull PackageInfo packageInfo);
    }

    private OnElementClickedListener listener;

    public AppListAdapter(List<PackageInfo> packages, @NonNull Context context) {
        dataSet = packages;
        listener = (OnElementClickedListener) context;
    }

    @NonNull
    @Override
    public AppListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.fragment_app_list_element, parent, false);
        return new AppListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppListAdapter.AppListViewHolder holder, int position) {
        if (position < dataSet.size())
        {
            holder.apply(dataSet.get(position), listener);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class AppListViewHolder extends RecyclerView.ViewHolder {
        private final View rootView;

        public AppListViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
        }

        public void apply(@NonNull PackageInfo packageInfo, @NonNull OnElementClickedListener listener) {
            TextView appPackageTextView = rootView.findViewById(R.id.app_package);
            setText(appPackageTextView, packageInfo.packageName);
            try {
                ImageView iconImageView = rootView.findViewById(R.id.app_icon);
                setIcon(iconImageView, rootView.getContext().getPackageManager()
                        .getApplicationIcon(packageInfo.packageName));

                TextView appLabelTextView = rootView.findViewById(R.id.app_label);
                setText(appLabelTextView, String.valueOf(rootView.getContext().getPackageManager()
                        .getApplicationLabel(packageInfo.applicationInfo)));

                rootView.setOnClickListener(view -> listener.onClick(packageInfo));
            } catch (PackageManager.NameNotFoundException error) {
                Log.i(AppListViewHolder.class.getSimpleName(), "Can't find icon: " + error.getMessage());
            }
        }

        private void setText(TextView targetView, String text) {
            if (targetView != null) {
                targetView.setText(text);
            }
        }

        private void setIcon(ImageView targetView, Drawable icon) {
            if (targetView != null) {
                targetView.setImageDrawable(icon);
            }
        }
    }
}
