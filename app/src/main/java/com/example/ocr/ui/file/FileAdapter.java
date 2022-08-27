package com.example.ocr.ui.file;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocr.R;
import com.example.ocr.databinding.ViewFileBinding;
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
        ViewFileBinding binding = ViewFileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FileHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.FileHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(files.get(position).getName());
        // 通过WPS打开Excel
        holder.itemView.setOnClickListener(v -> ExcelUtils.open(files.get(position)));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class FileHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView path;

        public FileHolder(ViewFileBinding binding) {
            super(binding.getRoot());
            name = binding.fileName;
            path = binding.filePath;
        }
    }

    public void add(File file) {
        files.add(file);
        notifyItemInserted(files.size() - 1);
    }
}
