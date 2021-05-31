/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author mano
 */
public class Associative {
    private int id_associative;
    private int id_movie;
    private int id_gen;

    public int getId_associative() {
        return id_associative;
    }

    public int getId_movie() {
        return id_movie;
    }

    public int getId_gen() {
        return id_gen;
    }

    public void setId_associative(int id_associative) {
        this.id_associative = id_associative;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public void setId_gen(int id_gen) {
        this.id_gen = id_gen;
    }
}
