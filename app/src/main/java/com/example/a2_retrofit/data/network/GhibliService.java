package com.example.a2_retrofit.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GhibliService {

    private GhibliService() {
    }

    private static ApiInterface apiInterface;

    // делаем проверку, если обращение происходит впервые к ghibliApi, то срабатывает
    // buildRetrofit - инстанцируется
    public static ApiInterface getInstance() {
        if (apiInterface == null) {
            apiInterface = buildRetrofit();
        }
        return apiInterface;
    }

    private static ApiInterface buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()) //он для того чтобы мог работать с моделькой
                .build()
                .create(ApiInterface.class);
    }
}