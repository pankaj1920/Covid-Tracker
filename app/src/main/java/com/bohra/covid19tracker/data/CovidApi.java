package com.bohra.covid19tracker.data;

import com.bohra.covid19tracker.data.SearchNews.SearchNewsResponse;
import com.bohra.covid19tracker.data.covid.CovidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidApi {



    @GET("everything")
    Call<SearchNewsResponse> getNewSearch(
            @Query("q") String keyWord,
            @Query("apiKey") String ApiKey);

    @GET("data.json")
    Call<CovidResponse> getCovidDetails();
}
