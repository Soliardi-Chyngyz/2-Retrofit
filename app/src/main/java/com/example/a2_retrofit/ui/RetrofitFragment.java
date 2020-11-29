package com.example.a2_retrofit.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2_retrofit.R;
import com.example.a2_retrofit.data.models.Film;
import com.example.a2_retrofit.data.network.GhibliService;
import com.example.a2_retrofit.ui.adapters.RetroAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitFragment extends Fragment implements RetroAdapter.FilmCallBack{

    private RecyclerView recyclerView;
    private RetroAdapter retroAdapter;
    List<Film> filmList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("anime", "anime");
        return inflater.inflate(R.layout.fragment_retrofit, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retroAdapter = new RetroAdapter(this);

        //enqueue отдельный от главного поток
            }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(retroAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        filmList = new ArrayList<>();

        //enqueue отдельный от главного поток
        GhibliService.getInstance().getFilm().enqueue(
                new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            retroAdapter.setList(response.body());
                            recyclerView.setAdapter(retroAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        Log.e("ERR", t.getMessage());
                    }
                }
        );
    }

    @Override
    public void onItemClick(Film film, int position) {
//        Bundle bundle = new Bundle();
//        bundle.putInt("key", position);
//        getParentFragmentManager().setFragmentResult("requestKey", bundle);

        transMoving();
    }

    private void transMoving() {
        DetailFragment detailFragment = new DetailFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .remove(this)
                .replace(R.id.fragment_second, detailFragment, null)
                .addToBackStack(null)
                .commit();
    }
}