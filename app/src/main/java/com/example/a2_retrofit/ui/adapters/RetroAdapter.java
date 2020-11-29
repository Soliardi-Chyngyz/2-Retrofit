package com.example.a2_retrofit.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2_retrofit.R;
import com.example.a2_retrofit.data.models.Film;

import java.util.List;

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.RetroViewHolder> {

    private FilmCallBack filmCallBack;
    private List<Film> filmList;

    public RetroAdapter(FilmCallBack filmCallback) {
        this.filmCallBack = filmCallback;
    }

    public void setList(List<Film> filmList) {
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RetroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler, parent, false);
        return new RetroViewHolder(view, filmCallBack);
    }

    @Override
    public void onBindViewHolder(@NonNull RetroViewHolder holder, int position) {
        holder.onBind(filmList.get(position));

    }

    @Override
    public int getItemCount() {
        if (filmList != null)
            return filmList.size();
        return 0;
    }

    public class RetroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, director, date, detail;
        FilmCallBack filmCallBack;
        Film film;

        public RetroViewHolder(@NonNull View itemView, FilmCallBack filmCallBack) {
            super(itemView);
            title = itemView.findViewById(R.id.main_title);
            director = itemView.findViewById(R.id.main_director);
            date = itemView.findViewById(R.id.main_date);
            this.filmCallBack = filmCallBack;
        }

        public void onBind(Film film) {
            itemView.setOnClickListener(view -> filmCallBack.onItemClick(film, getAdapterPosition()));
            this.film = film;
            title.setText(film.getTitle());
            director.setText(film.getDirector());
            date.setText(film.getReleaseDate());
        }


        @Override
        public void onClick(View view) {
            if (filmCallBack != null)
                filmCallBack.onItemClick(film, getAdapterPosition());
        }
    }

    public interface FilmCallBack {
        void onItemClick(Film film, int position);
    }
}
