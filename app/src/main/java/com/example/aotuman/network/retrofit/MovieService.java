package com.example.aotuman.network.retrofit;

import com.example.aotuman.network.retrofit.bean.NowPlayingMovie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  MovieService {

    @GET("v2/movie/nowplaying?apikey=0df993c66c0c636e29ecbb5344252a4a")
    Call<NowPlayingMovie> listNowPlayingMovies();

}
