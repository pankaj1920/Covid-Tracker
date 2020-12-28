package com.bohra.covid19tracker.ui.statistics;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StateStatisFragment extends Fragment {

    RecyclerView stateRecycler;
    StateRecyclerAdapter stateRecyclerAdapter;
    String stateCode, state, active, confirmed, deaths, recovered;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state_statis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stateRecycler = view.findViewById(R.id.stateRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        stateRecycler.setLayoutManager(layoutManager);

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

                    stateRecyclerAdapter = new StateRecyclerAdapter(covidResponse.getStatewise(), getActivity());
                    stateRecycler.setAdapter(stateRecyclerAdapter);

                    stateRecyclerAdapter.setOnItemClickListner(new StateRecyclerAdapter.OnItemClickListner() {
                        @Override
                        public void onStatelickListner(View view, int postion) {
                            stateCode = stateWises.get(postion).getStatecode();
                            for (StateWise data : stateWises) {
                                if (data.getStatecode().equals(stateCode)) {
                                    active = data.getActive();
                                    state = data.getState();
                                    confirmed = data.getConfirmed();
                                    deaths = data.getDeaths();
                                    recovered = data.getRecovered();
                                }
//                                Toast.makeText(getActivity(), stateCode, Toast.LENGTH_SHORT).show();
                            }

                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(getActivity(),StateCovidDetailActivity.class);
                            bundle.putString("active",active);
                            bundle.putString("confirmed",confirmed);
                            bundle.putString("deaths",deaths);
                            bundle.putString("recovered",recovered);
                            bundle.putString("state",state);
                            bundle.putString("stateCode",stateCode);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                    for (StateWise data : stateWises) {

                    }


                } else {
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