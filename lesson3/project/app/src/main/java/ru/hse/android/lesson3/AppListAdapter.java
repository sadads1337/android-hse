package ru.hse.android.lesson3;


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
    private List<PackageInfo> dataSet;

    public AppListAdapter(List<PackageInfo> packages) {
        dataSet = packages;
    }

    @NonNull
    @Override
    public AppListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.fragment_package_info, parent, false);
        return new AppListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppListAdapter.AppListViewHolder holder, int position) {
        if (position < dataSet.size())
        {
            holder.apply(dataSet.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class AppListViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        public AppListViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
        }

        public void apply(PackageInfo packageInfo) {
            final TextView appPackageTextView = rootView.findViewById(R.id.app_package);
            setText(appPackageTextView, packageInfo.packageName);
            try {
                final ImageView iconImageView = rootView.findViewById(R.id.app_icon);
                setIcon(iconImageView, rootView.getContext().getPackageManager()
                        .getApplicationIcon(packageInfo.packageName));

                final TextView appLabelTextView = rootView.findViewById(R.id.app_label);
                setText(appLabelTextView, String.valueOf(rootView.getContext().getPackageManager()
                        .getApplicationLabel(packageInfo.applicationInfo)));

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
