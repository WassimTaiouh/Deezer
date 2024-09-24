package com.example.mydeezerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

public class FragmentSearch extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, IApiListener {


    ArrayList<Music> musics;
    MusicAdapter adapter;
    ListView listViewMusics;
    EditText editTextSearch;
    ImageButton buttonSearch;
    IMusicSelected listenerMusicSelected;

    public void setListenerMusicSelected(IMusicSelected listenerMusicSelected){
        this.listenerMusicSelected = listenerMusicSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_search, null);

        listViewMusics= (ListView) v.findViewById(R.id.listViewMusics);
        musics= new ArrayList<Music>();
        adapter= new MusicAdapter(getContext(), musics);
        listViewMusics.setAdapter(adapter);
        listViewMusics.setOnItemClickListener(this);
        editTextSearch= (EditText) v.findViewById(R.id.editTextSearch);
        buttonSearch= (ImageButton) v.findViewById(R.id.imageButtonSearch);
        buttonSearch.setOnClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Music music= musics.get(i);
        listenerMusicSelected.onSelected(music);
    }

    @Override
    public void onClick(View view) {
        Services.searchMusics(getContext(), editTextSearch.getText().toString(),this);

    }

    @Override
    public void onReceiveMusics(ArrayList<Music> musics) {
        this.musics= musics;
        adapter= new MusicAdapter(getContext(), musics);
        listViewMusics.setAdapter(adapter);
    }
}