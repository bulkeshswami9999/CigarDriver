package com.hav.cigar.driver.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    //private static Retrofit retrofit2 = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // abandone link : "https://dev-api-morning.akronym.link"

        // Link : https://piersoncigars.com/api/driver_api.php?apicall=get_driver
        // Test Link : "https://piersoncigars.com/"
        retrofit = new Retrofit.Builder()
                .baseUrl("https://piersoncigars.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

//    public static Retrofit getClient2() {
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//
//        retrofit2 = new Retrofit.Builder()
//                .baseUrl("https://piersoncigars.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        return retrofit2;
//    }
}
