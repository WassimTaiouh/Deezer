package com.example.mydeezerapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements IMusicSelected {

    FragmentMusic fragmentMusic;
    FragmentSearch fragmentSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentSearch= new FragmentSearch();
        fragmentSearch.setListenerMusicSelected(this);
        fragmentMusic= new FragmentMusic();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, fragmentSearch)
                .add(R.id.frameLayout, fragmentMusic)
                .hide(fragmentMusic)
                .commit();

    }

    @Override
    public void onSelected(Music music) {
        fragmentMusic.setCurrentMusic(music);
        getSupportFragmentManager().beginTransaction()
                .hide(fragmentSearch)
                .show(fragmentMusic)
                .commit();
    }
    @Override
    public void onBackPressed() {
        if(fragmentMusic.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(fragmentMusic)
                    .show(fragmentSearch)
                    .commit();
        }else{
            super.onBackPressed();
        }
    }


}
