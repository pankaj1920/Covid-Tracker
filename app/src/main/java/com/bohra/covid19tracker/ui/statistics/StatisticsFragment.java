package com.bohra.covid19tracker.ui.statistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bohra.covid19tracker.R;
import com.bohra.covid19tracker.data.CovidApi;
import com.bohra.covid19tracker.data.CovidBaseClient;
import com.bohra.covid19tracker.data.covid.CasesTimeSeries;
import com.bohra.covid19tracker.data.covid.CovidResponse;
import com.bohra.covid19tracker.data.covid.StateWise;
import com.bohra.covid19tracker.uitil.DateAndTimeFormat;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  StatisticsFragment extends Fragment {

    TabLayout statisticsTabLayout;
    ViewPager statisticsViewPager;
    StatisticsTabAdapter statisticsTabAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        statisticsTabLayout = view.findViewById(R.id.statisticsTabLayout);
        statisticsViewPager = view.findViewById(R.id.statisticsViewPager);

        statisticsTabLayout.addTab(statisticsTabLayout.newTab().setText("My Country"));
        statisticsTabLayout.addTab(statisticsTabLayout.newTab().setText("States"));

        statisticsTabAdapter = new StatisticsTabAdapter(getActivity().getSupportFragmentManager(),statisticsTabLayout.getTabCount());
        statisticsViewPager.setAdapter(statisticsTabAdapter);

        statisticsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(statisticsTabLayout));

        statisticsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                statisticsViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}