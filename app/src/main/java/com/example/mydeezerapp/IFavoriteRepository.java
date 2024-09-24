package com.example.mydeezerapp;

import java.util.ArrayList;

public interface IFavoriteRepository {

    public boolean add(Music music);

    public boolean remove(Music music);

    public boolean isFavorite(Music music);

    public ArrayList<Music> getAll();
}
