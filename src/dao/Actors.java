package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Actors {
    private int id_actor;
    private int id_movie;
    private String name;

    public int getId_actor() {
        return id_actor;
    }

    public int getId_movie() {
        return id_movie;
    }

    public String getName() {
        return name;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public void setName(String name) {
        this.name = name;
    }
}
