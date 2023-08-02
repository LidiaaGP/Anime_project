package com.example.anime.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anime.Anime;
import com.example.anime.JikanApi_AnimeById;
import com.example.anime.R;
import com.example.anime.TopAnimeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeExtended extends AppCompatActivity {
    TextView tv_title_extended;
    ImageView image_extended;
    TextView tv_synopsis_extended;
    TextView tv_year_extended;
    TextView tv_score_extended;
    TextView tv_japanese_title;
    TextView tv_source_extended;
    TextView tv_type_extended;
    TextView tv_status;
    TextView tv_episodes;
    TextView tv_studios;
    private static final String BASE_URL = "https://api.jikan.moe/v4/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_extended);
        tv_title_extended=findViewById(R.id.tv_title_extended);
        image_extended=findViewById(R.id.image_extended);
        tv_synopsis_extended=findViewById(R.id.tv_synopsis_extended);
        tv_year_extended=findViewById(R.id.tv_year_extended);
        tv_score_extended=findViewById(R.id.tv_score_extended);
        tv_japanese_title=findViewById(R.id.tv_title_japanese_extended);
        tv_source_extended=findViewById(R.id.tv_source_extended);
        tv_type_extended=findViewById(R.id.tv_type_extended);
        tv_status=findViewById(R.id.tv_status);
        tv_episodes=findViewById(R.id.tv_episodes);
        tv_studios=findViewById(R.id.tv_studios);

        Intent intent = getIntent();
        if (intent != null) {
            Anime selectedAnime = (Anime) intent.getSerializableExtra("selectedAnime");
            int mal_id = selectedAnime.getMal_id();
            if (selectedAnime != null) {
                Retrofit retrofitVideos = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JikanApi_AnimeById jikanApiAnimeById = retrofitVideos.create(JikanApi_AnimeById.class);
                Call<TopAnimeResponse> callVideos = jikanApiAnimeById.getAnimeById(mal_id);

                callVideos.enqueue(new Callback<TopAnimeResponse>() {
                    @Override
                    public void onResponse(Call<TopAnimeResponse> call, Response<TopAnimeResponse> response) {
                        if (response.isSuccessful()) {
                            TopAnimeResponse animeVideosResponse = response.body();
                            if (animeVideosResponse != null) {
                                List<Anime> animeVideos = animeVideosResponse.getData();

                            } else {
                                Toast.makeText(HomeExtended.this, "Respuesta vacía de videos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TopAnimeResponse> call, Throwable t) {
                        Toast.makeText(HomeExtended.this, "Error al obtener videos: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                String animeTitle = selectedAnime.getTitle();
                String imageUrl = selectedAnime.getImage_url();
                String synopsis_extended=selectedAnime.getSynopsis();
                Integer year_extended=selectedAnime.getYear();
                Float score_extended=selectedAnime.getScore();
                String japanese_title=selectedAnime.getTitle_japanese();
                String source_extended=selectedAnime.getSource();
                String type_extended=selectedAnime.getType();
                String status=selectedAnime.getStatus();
                Integer episodes=selectedAnime.getEpisodes();
                List<Anime.Studios> studiosList = selectedAnime.getStudiosList();

                StringBuilder stringBuilder = new StringBuilder();
                tv_title_extended.setText(animeTitle);
                Picasso.get().load(imageUrl).into(image_extended);
                tv_synopsis_extended.setText(synopsis_extended);
                tv_year_extended.setText(year_extended.toString());
                tv_score_extended.setText(score_extended.toString());
                tv_japanese_title.setText(japanese_title);
                tv_source_extended.setText(source_extended);
                tv_type_extended.setText(type_extended);
                tv_status.setText(status);
                tv_episodes.setText(episodes.toString());
                for (Anime.Studios studio : studiosList) {
                        stringBuilder.append(studio.getName()).append(", ");
                    }
                String allStudios = stringBuilder.toString().trim().replaceAll(",$", "");
                tv_studios.setText(allStudios);
                }

            } else {
                Toast.makeText(this, "No se recibieron datos del anime seleccionado.", Toast.LENGTH_SHORT).show();
            }
        }
    }