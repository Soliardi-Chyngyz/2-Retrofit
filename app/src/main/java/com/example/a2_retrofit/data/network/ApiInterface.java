package com.example.a2_retrofit.data.network;

import com.example.a2_retrofit.data.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("films")
    Call<List<Film>> getFilm();

    @GET("films/{filmId}")
    Call<Film> getFilmById(
            @Path("filmId") int filmId
    );
}
