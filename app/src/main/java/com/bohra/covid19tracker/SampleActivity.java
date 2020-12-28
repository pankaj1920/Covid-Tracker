package com.bohra.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bohra.covid19tracker.data.CovidApi;
import com.bohra.covid19tracker.data.CovidBaseClient;
import com.bohra.covid19tracker.data.covid.CovidResponse;
import com.bohra.covid19tracker.data.covid.StateWise;
import com.bohra.covid19tracker.increase_num.SampleRecyclerViewAdapter;
import com.bohra.covid19tracker.ui.statistics.StateCovidDetailActivity;
import com.bohra.covid19tracker.ui.statistics.StateRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleActivity extends AppCompatActivity {

    RecyclerView sampleRecycler;
    SampleRecyclerViewAdapter sampleRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        sampleRecycler = findViewById(R.id.sampleRecycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        sampleRecycler.setLayoutManager(layoutManager);

        getCovidDetails();
    }

    private void getCovidDetails() {

        CovidApi covidApi = CovidBaseClient.getBaseClient().create(CovidApi.class);
        Call<CovidResponse> call = covidApi.getCovidDetails();
        call.enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getActivity(), "Response Success", Toast.LENGTH_SHORT).show();
                    CovidResponse covidResponse = response.body();
                    List<StateWise> stateWises = covidResponse.getStatewise();

                    sampleRecyclerViewAdapter = new SampleRecyclerViewAdapter(covidResponse.getStatewise(), SampleActivity.this);
                    sampleRecycler.setAdapter(sampleRecyclerViewAdapter);



                } else {
                    Toast.makeText(SampleActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {
                Toast.makeText(SampleActivity.this, "in On Failre", Toast.LENGTH_SHORT).show();
            }
        });
    }

}