package com.example.moviebuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsScreen extends AppCompatActivity {

    ImageView poster;
    TextView title, rating, votecount, overview, language, adult, release_date;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        getWindow().setNavigationBarColor(Color.BLACK);
        title = findViewById(R.id.movie_name);
        rating = findViewById(R.id.rating);
        votecount = findViewById(R.id.vote_count);
        overview = findViewById(R.id.overview);
        language = findViewById(R.id.language);
        adult = findViewById(R.id.adult);
        release_date = findViewById(R.id.release_date);
        poster = findViewById(R.id.poster_image);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setTitle(getIntent().getStringExtra("title"));
        actionBar.setDisplayHomeAsUpEnabled(true);



        String poster_url = "https://image.tmdb.org/t/p/w500" +
                getIntent().getStringExtra("posterPath");
        Picasso.get().load(poster_url)
                .placeholder(R.drawable.loading)
                .fit()
                .into(poster);

        title.setText("Movie Name: " + getIntent().getStringExtra("title"));
        rating.setText("Rating: " + getIntent().getDoubleExtra("voteAverage" , 0) + "/10");
        votecount.setText("Number of votes: " + getIntent().getIntExtra("voteCount" , 0));
        language.setText("Language: " + getIntent().getStringExtra("language"));
        release_date.setText("Movie Released on: " + getIntent().getStringExtra("releaseDate"));
        overview.setText(getIntent().getStringExtra("overview"));
        if(!getIntent().getBooleanExtra("adult", false)){
            adult.setText(R.string.NotAdult);
        }else{
            adult.setText(R.string.adult);
        }

    }
}