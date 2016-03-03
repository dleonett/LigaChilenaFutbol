package com.danielleonett.ligachilenafutbol.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 02/03/2016.
 */
public class HttpClientGenerator {

    // No need to instantiate this class.
    private HttpClientGenerator() {
    }

    public static <S> S createClient(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

}
