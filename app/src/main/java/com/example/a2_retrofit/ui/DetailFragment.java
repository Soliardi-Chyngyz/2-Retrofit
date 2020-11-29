package com.example.a2_retrofit.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2_retrofit.R;
import com.example.a2_retrofit.data.models.Film;
import com.example.a2_retrofit.data.network.GhibliService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends Fragment {

    private TextView title, desc, director, producer, releaseDate, rtScore, people, species, location,
            vehicles, url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRetrofit();


        title = view.findViewById(R.id.titl);
        title.setOnClickListener(view1 -> Toast.makeText(DetailFragment.this.getContext(), "Work", Toast.LENGTH_SHORT).show());
        desc = view.findViewById(R.id.desc);
        director = view.findViewById(R.id.director);
        producer = view.findViewById(R.id.producer);
        releaseDate = view.findViewById(R.id.release_date);
        rtScore = view.findViewById(R.id.rt_score);
        people = view.findViewById(R.id.people);
        species = view.findViewById(R.id.species);
        location = view.findViewById(R.id.locations);
        vehicles = view.findViewById(R.id.vehicles);
        url = view.findViewById(R.id.url);

    }

    private void setRetrofit() {

        Film film = new Film();
        String link = film.getId();
        int i = Integer.parseInt(link.trim());
        Log.e("TAG", "setRetrofit: " + link );
        GhibliService.getInstance().getFilmById(i).enqueue(
                new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            setData(response.body());
                            Log.i("LINK", "link " + link);
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                        Log.e("ERR", "onFailure: " + t.getMessage() );
                    }
                }
        );
    }

    private void setData(Film body) {
        Film film = new Film();
        title.setText(film.getTitle());
        title.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Work", Toast.LENGTH_SHORT).show();
        });

        desc.setText(film.getDescription());
        director.setText(film.getDirector());
        producer.setText(film.getProducer());
        releaseDate.setText(film.getReleaseDate());
        rtScore.setText(film.getRtScore());
        people.setText((CharSequence) film.getPeople());

    }
}