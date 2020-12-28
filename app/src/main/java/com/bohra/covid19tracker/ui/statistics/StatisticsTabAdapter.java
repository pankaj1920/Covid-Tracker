package com.bohra.covid19tracker.ui.statistics;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class StatisticsTabAdapter extends FragmentStatePagerAdapter {

    final int TabCount;

    public StatisticsTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        TabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case (0):
                return new CountryStatisFragment();

            case (1):
                return new StateStatisFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}
