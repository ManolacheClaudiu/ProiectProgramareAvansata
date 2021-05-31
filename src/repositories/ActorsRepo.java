/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Main.Database;
import dao.Actors;
import dao.Movies;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author mano
 */
public class ActorsRepo {
    public void create(Actors actor)throws SQLException{
        Connection connection = Database.getConnection();
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO actors(id_movie,name) values(?,?) ")){
            preparedStatement.setInt(1,actor.getId_movie());
            preparedStatement.setString(2,actor.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void update(Actors actor)throws SQLException, ParseException {
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE actors SET id_movie=?, name=? where id_actor=? ")){
            preparedStatement.setInt(1, actor.getId_movie());
            preparedStatement.setString(2, actor.getName());
            preparedStatement.setInt(3, actor.getId_actor());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void delete(Actors actor)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM actors WHERE id_actor=?")){
            preparedStatement.setInt(1, actor.getId_actor());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public Actors findById(Actors actor) throws SQLException {

        Connection conn = Database.getConnection();
        String sql="SELECT id_movie,name FROM actors WHERE id_actor='"+actor.getId_actor()+"'";
        Statement statement=conn.prepareStatement(sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()) {

            actor.setId_movie(resultSet.getInt(1));
            actor.setName(resultSet.getString(2));
            return actor;
        }
        else{
            actor.setId_actor(-1);
            return actor;
        }

    }

}
