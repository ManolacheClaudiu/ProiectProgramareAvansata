/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Main.Database;
import dao.Movies;

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
public class MoviesRepo {

    public void create(Movies movie)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO movies(title,release_date,duration,score) values(?,?,?,?) ")){
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getRelease_date());
            preparedStatement.setInt(3,movie.getDuration());
            preparedStatement.setInt(4,movie.getScore());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void update(Movies movie)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE movies SET title=? ,release_date=?,duration=?,score=? where id_movie=? ")){
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getRelease_date());
            preparedStatement.setInt(3,movie.getDuration());
            preparedStatement.setInt(4,movie.getScore());
            preparedStatement.setInt(5,movie.getId_movie());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void delete(Movies movie)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM movies WHERE id_movie=?")){
            preparedStatement.setInt(1,movie.getId_movie());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public Movies findById(Movies movies) throws SQLException {

        Connection conn = Database.getConnection();
        String sql="SELECT title,release_date,duration,score FROM movies WHERE id_movie='"+movies.getId_movie()+"'";
        Statement statement=conn.prepareStatement(sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()) {
            movies.setTitle(resultSet.getString(1));
            movies.setRelease_date(resultSet.getString(2));
            movies.setDuration(resultSet.getInt(3));
            movies.setScore(resultSet.getInt(4));
            return movies;
        }
        else{
          movies.setId_movie(-1);
          return movies;
        }

    }



}
