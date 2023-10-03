package com.example.anime;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ArrayAdapterTopAnimes extends ArrayAdapter<Anime> {
    Activity activity;
    int idlayout;
    List <Anime> topAnime;

    private static final int MAX_SYNOPSIS_LENGTH = 150;
    static class Ref{
        TextView title;
        ImageView img_anime;
        TextView synopsis;
        TextView mal_id;
    }
    public ArrayAdapterTopAnimes(@NonNull Context context, int resource, @NonNull List<Anime> topAnime) {
        super(context, resource, topAnime);
        activity= (Activity) context;
        idlayout=resource;
        this.topAnime=topAnime;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArrayAdapterTopAnimes.Ref ref;
        View layout;
        if (convertView == null) {
            layout = activity.getLayoutInflater().inflate(idlayout, null);
            TextView title = layout.findViewById(R.id.tv_title);
            ImageView img_anime=layout.findViewById(R.id.img_anime);
            TextView synopsis=layout.findViewById(R.id.tv_synopsis);
            TextView mal_id=layout.findViewById(R.id.tv_mal_id);

            ref = new Ref();
            ref.title = title;
            ref.img_anime=img_anime;
            ref.synopsis=synopsis;
            ref.mal_id=mal_id;

            layout.setTag(ref);
        } else {
            layout = convertView;
            ref = (ArrayAdapterTopAnimes.Ref) layout.getTag();
        }
        Anime anime = topAnime.get(position);
        ref.title.setText(anime.getTitle());
        String synopsis = anime.getSynopsis();

        if (synopsis.length() > MAX_SYNOPSIS_LENGTH) {
            synopsis = synopsis.substring(0, MAX_SYNOPSIS_LENGTH) + "...";
        }
        ref.synopsis.setText(synopsis);
        Picasso.get().load(anime.getImage_url()).into(ref.img_anime);
        ref.mal_id.setText(anime.getMal_id().toString());


        return layout;
    }
}
