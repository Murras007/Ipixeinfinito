package com.ramossoft.ipixelinfinito.RestClient;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// setting up the Retrofit Interface
public class RestClient {
// the URL that connects the application to the API
public static final String  BASE_URL="https://paystring.org/sandbox/";
    private static Retrofit retrofit=null;
    //this function its call every time setting up a Retrofit
    public static Retrofit getClient() {
        if (retrofit == null) {
            //setting up OkHttp Interceptor which represents OKttp that can monitor, rewrite and retry calls
            HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(logLevel);

            //handles when the connection is not connecting or taking long to connect
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES).build();
            //calling the Library Gson
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //converting everything it receives into Gson
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


        }
        return retrofit;
    }
}
