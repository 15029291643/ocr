package com.example.ocr.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ocr.ui.file.FileFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeAdapter extends FragmentStateAdapter {
    private final List<Fragment> mFragments = new ArrayList<>(Arrays.asList(
            new FileFragment(),
            new PersonFragment(),
            new PersonFragment()
    ));

    public HomeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}