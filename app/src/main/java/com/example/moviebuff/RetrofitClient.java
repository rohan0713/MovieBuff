package com.example.moviebuff;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitClient {

    private static final String key = "8d3b00f81c896c05ce9a57ac253edfcb&language=en-US&page=1";
    private static final String base_url = "https://api.themoviedb.org/3/movie/";

    public static Services Services = null;
    public static Services GetServices(){

        if(Services == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Services = retrofit.create(Services.class);
        }
        return Services;
    }
    public interface Services{

        @GET("popular?api_key="+key)
        Call<MovieDBApiResponse> getPopularMovies();

        @GET("top_rated?api_key="+key)
        Call<MovieDBApiResponse> getTopRatedMovies();

        @GET("upcoming?api_key="+key)
        Call<MovieDBApiResponse> getUpcomingMovies();
    }
}
