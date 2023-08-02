package com.example.anime;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JikanApi_TopAnime {
    @GET("top/anime/")
    Call<TopAnimeResponse> getTopAnimeByPopularity();
}