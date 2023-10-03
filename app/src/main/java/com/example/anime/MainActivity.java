package com.example.anime;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.anime.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anime.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    SearchView searchView;
    private static final String BASE_URL = "https://api.jikan.moe/v4/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performAnimeSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performAnimeSearch(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void performAnimeSearch(String query) {
        try {
            String searchedAnime = query;

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JikanApi_SearchAnime jikanApi_SearchAnime = retrofit.create(JikanApi_SearchAnime.class);

            Call<SearchAnimeResponse> call = jikanApi_SearchAnime.searchAnime(searchedAnime);

            call.enqueue(new Callback<SearchAnimeResponse>() {
                @Override
                public void onResponse(Call<SearchAnimeResponse> call, Response<SearchAnimeResponse> response) {

                    if (response.isSuccessful()) {
                        SearchAnimeResponse searchAnimeResponse = response.body();
                        if (searchAnimeResponse != null) {
                            List<Anime> animeList = searchAnimeResponse.getData();
                            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
                            NavHostFragment navHostFragment = (NavHostFragment) fragment;
                            Fragment homeFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
                            ((HomeFragment) homeFragment).updateAdapterDataWithQuery(searchedAnime, animeList);

                        } else {
                            Log.d("MainActivity", "Respuesta de la API es nula.");
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchAnimeResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });






        } catch (NumberFormatException e) {
            // Si no se puede convertir a número, muestra un mensaje de error
            Toast.makeText(MainActivity.this, "Ingrese un ID válido", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}