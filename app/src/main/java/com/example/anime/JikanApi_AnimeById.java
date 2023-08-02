package com.example.anime;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JikanApi_AnimeById {
    @GET("anime/{mal_id}/videos/")
    Call<TopAnimeResponse> getAnimeById(@Path("mal_id") int malId);
}
