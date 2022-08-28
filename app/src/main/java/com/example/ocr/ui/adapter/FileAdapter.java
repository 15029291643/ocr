package com.example.ocr.ui.adapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocr.databinding.AdapterFileBinding;
import com.example.ocr.logic.util.ExcelUtils;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileHolder> {
    private List<File> files;

    public FileAdapter(List<File> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public FileAdapter.FileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterFileBinding binding = AdapterFileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FileHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.FileHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(files.get(position).getName());
        holder.time.setText(files.get(position).getPath().split("files/")[1]);
        // 通过WPS打开Excel
        holder.itemView.setOnClickListener(v -> ExcelUtils.open(files.get(position)));
        // 删除
        holder.more.setOnClickListener(v -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("删除")
                    .setMessage("确定删除" + files.get(position).getName() + "吗?")
                    .setPositiveButton("确定", (dialog, which) -> remove(position))
                    .setNegativeButton("取消", (dialog, which) -> {})
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class FileHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView time;
        ImageView more;

        public FileHolder(AdapterFileBinding binding) {
            super(binding.getRoot());
            name = binding.fileName;
            time = binding.fileTime;
            more = binding.fileMore;
        }
    }

    public void add(File file) {
        files.add(file);
        notifyItemInserted(files.size() - 1);
    }

    public void remove(int position) {
        files.get(position).delete();
        files.remove(position);
        notifyItemRemoved(position);
    }
}
