package com.bohra.covid19tracker.ui.statistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bohra.covid19tracker.R;
import com.bohra.covid19tracker.data.CovidApi;
import com.bohra.covid19tracker.data.CovidBaseClient;
import com.bohra.covid19tracker.data.covid.CasesTimeSeries;
import com.bohra.covid19tracker.data.covid.CovidResponse;
import com.bohra.covid19tracker.uitil.DateAndTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CountryStatisFragment extends Fragment {
    String currentDate,yesterdayDate;
    TextView totalConfirmed,totalDeceased,recovered,active,serious;
    RadioButton Total,Today,yesterday;
    RadioGroup covidByDayRadioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_statis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalConfirmed = view.findViewById(R.id.totalConfirmed);
        totalDeceased = view.findViewById(R.id.totalDeceased);
        recovered = view.findViewById(R.id.recovered);
        totalConfirmed = view.findViewById(R.id.totalConfirmed);
        active = view.findViewById(R.id.active);
        serious = view.findViewById(R.id.serious);


        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        yesterdayDate = DateAndTimeFormat.calYesterdayDate();

        getCovidDetails();

    }

    private void getCovidDetails() {

        CovidApi covidApi = CovidBaseClient.getBaseClient().create(CovidApi.class);
        Call<CovidResponse> call = covidApi.getCovidDetails();
        call.enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Response Success", Toast.LENGTH_SHORT).show();
                    CovidResponse covidResponse = response.body();
                    List<CasesTimeSeries> casesTimeSeriesData = covidResponse.getCases_time_series();

                    for (CasesTimeSeries data : casesTimeSeriesData){
                        if (data.getDateymd().equals(yesterdayDate)){

                           totalConfirmed.setText(data.getTotalconfirmed());
                           totalDeceased.setText(data.getTotaldeceased());
                           recovered.setText(data.getTotalrecovered());
                           active.setText(data.getDailyconfirmed());
                           serious.setText(data.getDailydeceased());
                        }
                    }


                }else {
                    Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "in On Failre", Toast.LENGTH_SHORT).show();
            }
        });
    }

}