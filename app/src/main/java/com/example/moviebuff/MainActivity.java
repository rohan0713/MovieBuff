package com.example.moviebuff;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                getTopRatedMoviesList();
            break;
            case R.id.upcoming:
                getUpcomingMoviesList();
            break;
            default: getPopularMoviesList();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private LinearLayoutManager layoutManager;
    private int function_to_be_called = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(Color.BLACK);
        recyclerView = findViewById(R.id.rvUsers);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        if(getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = new GridLayoutManager(MainActivity.this, 3);
        }else { layoutManager = new GridLayoutManager(MainActivity.this, 2);
        }
    }

    private void getPopularMoviesList(){

        function_to_be_called = 1;
        Call<MovieDBApiResponse> moviesInfoCall = RetrofitClient.GetServices().getPopularMovies();
        moviesInfoCall.enqueue(new Callback<MovieDBApiResponse>() {

            @Override
            public void onResponse(Call<MovieDBApiResponse> call, Response<MovieDBApiResponse> response) {

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                recyclerView.setLayoutManager(layoutManager);

                List<ResultsItem> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesListAdapter(getApplicationContext(), movies));
            }

            @Override
            public void onFailure(Call<MovieDBApiResponse> call, Throwable t) {
                Log.d("Error" , t.getMessage());
                Toast.makeText(MainActivity.this, "Try to connect to Internet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUpcomingMoviesList(){

        function_to_be_called = 2;
        Call<MovieDBApiResponse> moviesInfoCall = RetrofitClient.GetServices().getUpcomingMovies();
        moviesInfoCall.enqueue(new Callback<MovieDBApiResponse>() {

            @Override
            public void onResponse(Call<MovieDBApiResponse> call, Response<MovieDBApiResponse> response) {

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                recyclerView.setLayoutManager(layoutManager);

                List<ResultsItem> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesListAdapter(getApplicationContext(), movies));
            }

            @Override
            public void onFailure(Call<MovieDBApiResponse> call, Throwable t) {
                Log.d("Error" , t.getMessage());
                Toast.makeText(MainActivity.this, "Try to connect to Internet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTopRatedMoviesList(){

        function_to_be_called = 3;
        Call<MovieDBApiResponse> moviesInfoCall = RetrofitClient.GetServices().getTopRatedMovies();
        moviesInfoCall.enqueue(new Callback<MovieDBApiResponse>() {

            @Override
            public void onResponse(Call<MovieDBApiResponse> call, Response<MovieDBApiResponse> response) {

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                recyclerView.setLayoutManager(layoutManager);

                List<ResultsItem> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesListAdapter(getApplicationContext(), movies));
            }

            @Override
            public void onFailure(Call<MovieDBApiResponse> call, Throwable t) {
                Log.d("Error" , t.getMessage());
                Toast.makeText(MainActivity.this, "Try to connect to Internet!", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onResume() {
        mShimmerViewContainer.startShimmerAnimation();
        if(function_to_be_called == 1){
            getPopularMoviesList();
        }else if (function_to_be_called == 3){
            getTopRatedMoviesList();
        }else{
            getUpcomingMoviesList();
        }
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("function" , function_to_be_called);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        function_to_be_called = savedInstanceState.getInt("function");
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}