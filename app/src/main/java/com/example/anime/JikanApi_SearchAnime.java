package com.example.anime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JikanApi_SearchAnime {
    @GET("anime")
    Call<SearchAnimeResponse> searchAnime(@Query("q") String searchedAnime);
}
