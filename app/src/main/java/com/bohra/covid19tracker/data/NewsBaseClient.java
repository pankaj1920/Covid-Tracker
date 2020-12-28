package com.bohra.covid19tracker.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsBaseClient {

    private final static String Base_Url ="https://newsapi.org/v2/";
    private static Retrofit retrofitEndPoint;

    public static Retrofit getBaseClient(){
        if (retrofitEndPoint == null){
            retrofitEndPoint = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitEndPoint;
}

}

