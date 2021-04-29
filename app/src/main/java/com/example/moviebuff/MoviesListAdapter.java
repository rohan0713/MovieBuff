package com.example.moviebuff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.moviesViewHolder> {

    Context context;
    List<ResultsItem> movies;
    TextView title;
    ImageView poster;


    public MoviesListAdapter(Context context , List<ResultsItem> results) {
        this.context = context;
        this.movies = results;
    }

    class moviesViewHolder extends RecyclerView.ViewHolder{

        moviesViewHolder(View itemview){
            super(itemview);
        }

        public void Bind(ResultsItem resultsItem){

            title = itemView.findViewById(R.id.textView);
            title.setText(resultsItem.title);
            poster = itemView.findViewById(R.id.imageView);
            String path = "https://image.tmdb.org/t/p/w500" + resultsItem.poster_path;
            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(path)
                    .placeholder(R.drawable.loading)
                    .fit()
                    .into(poster);

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context , DetailsScreen.class);
                    i.putExtra("posterPath" , resultsItem.poster_path);
                    i.putExtra("title" , resultsItem.title);
                    i.putExtra("language" , resultsItem.original_language);
                    i.putExtra("overview" , resultsItem.overview);
                    i.putExtra("releaseDate" , resultsItem.release_date);
                    i.putExtra("voteAverage" , resultsItem.vote_average);
                    i.putExtra("voteCount" , resultsItem.vote_count);
                    i.putExtra("adult" , resultsItem.adult);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }

    @NonNull
    @Override
    public moviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new moviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull moviesViewHolder holder, int position) {

        holder.Bind(movies.get(position));
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

}
