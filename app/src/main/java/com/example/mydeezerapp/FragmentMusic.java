package com.example.mydeezerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class FragmentMusic extends Fragment implements View.OnClickListener {

    TextView textViewArtist, textViewAlbum, textViewTitle;
    ImageView imageViewMusic;
    Button buttonPlay, buttonLink;
    Music currentMusic;
    MediaPlayer player= new MediaPlayer();
    Switch switchFav;

    public void setCurrentMusic(Music currentMusic) {
        this.currentMusic = currentMusic;
        this.refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_music, null);

        textViewArtist= (TextView) v.findViewById(R.id.textViewArtist);
        textViewAlbum= (TextView) v.findViewById(R.id.textViewAlbum);
        textViewTitle= (TextView) v.findViewById(R.id.textViewTitle);
        imageViewMusic= (ImageView) v.findViewById(R.id.imageViewMusic);
        buttonPlay= (Button) v.findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
        buttonLink= (Button) v.findViewById(R.id.ButtonLink);
        buttonLink.setOnClickListener(this);
        switchFav = (Switch) v.findViewById(R.id.switchFavoris);
        switchFav.setOnClickListener(this);

        return v;
    }

    public void refresh() {
        if(currentMusic!=null) {
            textViewArtist.setText(currentMusic.getArtist());
            textViewAlbum.setText(currentMusic.getAlbum());
            textViewTitle.setText(currentMusic.getTitle());
            Services.loadImage(getContext(), currentMusic.getImage(), imageViewMusic);
            switchFav.setChecked(FavoriteRepository.getInstance(getContext()).isFavorite(currentMusic));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.equals(buttonLink)){
            Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(currentMusic.getLink()));
            startActivity(intent);
        }else if(view.equals(buttonPlay)){
            if(!player.isPlaying()){
                buttonPlay.setText("STOP");
                try {
                    player.reset();
                    player.setDataSource(getContext(), Uri.parse(currentMusic.getPreview()));
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                buttonPlay.setText("PLAY");
                player.stop();
            }
        }else{
            if(FavoriteRepository.getInstance(getContext()).isFavorite(currentMusic)){
                FavoriteRepository.getInstance(getContext()).remove(currentMusic);
                switchFav.setChecked(false);
            }else{
                FavoriteRepository.getInstance(getContext()).add(currentMusic);
                switchFav.setChecked(true);
            }
        }
    }

}