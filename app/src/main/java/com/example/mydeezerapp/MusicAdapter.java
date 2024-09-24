package com.example.mydeezerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {

    private ArrayList<Music> musics;
    private Context context;

    public MusicAdapter(Context context, ArrayList<Music> musics) {
        this.musics = musics;
        this.context = context;
    }

    @Override
    public int getCount() {
        return musics.size();
    }

    @Override
    public Object getItem(int i) {
        return musics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return musics.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_music, viewGroup, false);
        }
        TextView artist= view.findViewById(R.id.textViewItemArtist);
        artist.setText(musics.get(i).getArtist());
        TextView title= view.findViewById(R.id.textViewItemTitle);
        title.setText(musics.get(i).getTitle());
        TextView album= view.findViewById(R.id.textViewItemAlbum);
        album.setText(musics.get(i).getAlbum());

        ImageView imageView= view.findViewById(R.id.imageViewItemMusic);
        Services.loadImage(context,musics.get(i).getImage(), imageView);

        ImageView imageViewFav= view.findViewById(R.id.imageViewItemFavoris);
        if(FavoriteRepository.getInstance(context).isFavorite(musics.get(i))){
            imageViewFav.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            imageViewFav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        return view;
    }
}
