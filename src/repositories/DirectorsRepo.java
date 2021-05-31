/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Main.Database;
import dao.Directors;

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
public class DirectorsRepo {

    public void create(Directors directors)throws SQLException{
        Connection connection = Database.getConnection();
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO directors(id_movie,name) values(?,?) ")){


            preparedStatement.setInt(1,directors.getId_movie());
            preparedStatement.setString(2,directors.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void update(Directors directors)throws SQLException, ParseException {
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE directors SET id_movie=?, name=? where id_director=? ")){
            preparedStatement.setInt(1, directors.getId_movie());
            preparedStatement.setString(2, directors.getName());
            preparedStatement.setInt(3, directors.getId_director());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void delete(Directors directors)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM directors WHERE id_director=?")){
            preparedStatement.setInt(1, directors.getId_director());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public Directors findById(Directors directors) throws SQLException {

        Connection conn = Database.getConnection();
        String sql="SELECT id_movie,name FROM directors WHERE id_director='"+directors.getId_director()+"'";
        Statement statement=conn.prepareStatement(sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()) {

            directors.setId_movie(resultSet.getInt(1));
            directors.setName(resultSet.getString(2));
            return directors;
        }
        else{
            directors.setId_director(-1);
            return directors;
        }

    }


}
