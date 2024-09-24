package com.example.mydeezerapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Music implements Serializable {

    private int id;
    private String title;
    private String artist;
    private String album;
    private String image;
    private String link;
    private String preview;

    public Music() {
    }

    public Music(int id, String title, String artist, String album, String image, String link, String preview) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.image = image;
        this.link = link;
        this.preview = preview;
    }

    public static Music jsonToMusic(JSONObject object){
        Music temp= new Music();
        try {
            temp.setId(object.getInt("id"));
            temp.setTitle(object.getString("title"));
            temp.setArtist(object.getJSONObject("artist").getString("name"));
            temp.setAlbum(object.getJSONObject("album").getString("title"));
            temp.setImage(object.getJSONObject("album").getString("cover_medium"));
            temp.setPreview(object.getString("preview"));
            temp.setLink(object.getString("link"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
