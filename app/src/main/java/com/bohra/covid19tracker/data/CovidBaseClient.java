package com.bohra.covid19tracker.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidBaseClient {

    private final static String Base_Url ="https://api.covid19india.org/";
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
