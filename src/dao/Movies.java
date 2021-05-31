package dao;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Movies {
    int id_movie;
    private String title;
    private String release_date;
    private int duration;
    private int score;


    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public int getId_movie() {
        return id_movie;
    }
}

