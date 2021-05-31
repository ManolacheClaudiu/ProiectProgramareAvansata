/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Main.Database;
import dao.Actors;
import dao.Genres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

/**
 *
 * @author mano
 */
public class GenresRepo {
    public void create(Genres genre)throws SQLException{
        Connection connection = Database.getConnection();
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO genres(name) values(?) ")){

            preparedStatement.setString(1, genre.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void update(Genres genres)throws SQLException, ParseException {
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE genres SET name=? where id_gen=? ")){
            preparedStatement.setString(1, genres.getName());
            preparedStatement.setInt(2, genres.getId_gen());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void delete(Genres genres)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM genres WHERE id_gen=?")){
            preparedStatement.setInt(1, genres.getId_gen());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public Genres findById(Genres genres) throws SQLException {
        Connection conn = Database.getConnection();
        //here should be a select which get the name of the movie
        String sql="SELECT name from genres where id_gen = '"+genres.getId_gen()+"'";
        Statement statement=conn.prepareStatement(sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()) {


            genres.setName(resultSet.getString(1));
            return genres;
        }
        else{
            genres.setId_gen(-1);
            return genres;
        }
    }


}
