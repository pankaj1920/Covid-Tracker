package com.bohra.covid19tracker.ui.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bohra.covid19tracker.R;
import com.bohra.covid19tracker.data.CovidApi;
import com.bohra.covid19tracker.data.NewsBaseClient;
import com.bohra.covid19tracker.data.SearchNews.SearchNewsResponse;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcLoader;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    String id;
    SwipeRefreshLayout newsSwipeRefresh;
    RecyclerView newsRecycler;
    NewsRecyclerAdapter newsRecyclerAdapter;

    SimpleArcLoader simpleArcLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsSwipeRefresh = view.findViewById(R.id.newsSwipeRefresh);
        simpleArcLoader = view.findViewById(R.id.simpleArcLoader);
        newsRecycler = view.findViewById(R.id.newsRecycler);
        newsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchNews();

        newsSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchNews();
            }
        });
        newsSwipeRefresh.setColorSchemeResources(R.color.transpraent);
        return view;
    }

    private void searchNews() {

        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();
        newsRecycler.setVisibility(View.GONE);

        id = "e06f2654d4514c2ba625e0252f56eace";

        CovidApi covidApi = NewsBaseClient.getBaseClient().create(CovidApi.class);
        Call<SearchNewsResponse> call = covidApi.getNewSearch("covid 19", id);
        call.enqueue(new Callback<SearchNewsResponse>() {
            @Override
            public void onResponse(Call<SearchNewsResponse> call, Response<SearchNewsResponse> response) {

                if (response.isSuccessful() && HttpURLConnection.HTTP_OK == response.code()) {
                    newsRecycler.setVisibility(View.VISIBLE);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    SearchNewsResponse searchNewsResponse = response.body();
                    newsRecyclerAdapter = new NewsRecyclerAdapter(searchNewsResponse.getArticles());
                    newsRecycler.setAdapter(newsRecyclerAdapter);

                    newsSwipeRefresh.setRefreshing(false);
                } else {
                    newsSwipeRefresh.setRefreshing(false);
                    Toast.makeText(getActivity(), "UnSucessfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchNewsResponse> call, Throwable t) {

                newsSwipeRefresh.setRefreshing(false);
                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }

}