/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Main.Database;
import dao.Actors;
import dao.Associative;

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
public class AssociativeRepo {
    public void create(Associative associative)throws SQLException{
        Connection connection = Database.getConnection();
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO associative(id_movie,id_gen) values(?,?) ")){


            preparedStatement.setInt(1,associative.getId_movie());
            preparedStatement.setInt(2,associative.getId_gen());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }

    public void update(Associative associative)throws SQLException, ParseException {
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE associative SET id_movie=?, id_gen=? where id_associative=? ")){
            preparedStatement.setInt(1, associative.getId_movie());
            preparedStatement.setInt(2, associative.getId_gen());
            preparedStatement.setInt(3, associative.getId_associative());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public void delete(Associative associative)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//
        connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM associative WHERE id_associative=?")){
            preparedStatement.setInt(1, associative.getId_associative());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        }
    }
    public Associative findById(Associative associative) throws SQLException {

        Connection conn = Database.getConnection();
        String sql="SELECT id_movie,id_gen FROM associative WHERE id_associative='"+associative.getId_associative()+"'";
        Statement statement=conn.prepareStatement(sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()) {

            associative.setId_movie(resultSet.getInt(1));
            associative.setId_gen(resultSet.getInt(2));
            return associative;
        }
        else{
            associative.setId_associative(-1);
            return associative;
        }

    }



}
