package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

public class UserDao {

    public User getUserByUsername(String username) {

        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "SELECT * FROM users WHERE username =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            User possibleUser = new User();
            ResultSet rs = ps.executeQuery();
            // revisit this process of getting user
            // pass parameter find userby UN, return it
            while (rs.next()) {
                int retrievedID = rs.getInt("id");
                String retrievedUsername = rs.getString("username");
                String retrievedPassword = rs.getString("password");

                possibleUser.setId(retrievedID);
                possibleUser.setUsername(retrievedUsername);
                possibleUser.setPassword(retrievedPassword);

            }
            return possibleUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        // unreachable code
    }
    public User getUserbyId(int id)
    {
        try(Connection connection = ConnectionUtil.createConnection())
        {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            User possibleUser = new User();
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int retrievedID = rs.getInt("id");
                String retrievedUsername = rs.getString("username");
                String retrievedPassword = rs.getString("password");

                possibleUser.setId(retrievedID);
                possibleUser.setUsername(retrievedUsername);
                possibleUser.setPassword(retrievedPassword);

            }
            return possibleUser;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public User createUser(UsernamePasswordAuthentication registerRequest) {
        // TODO: implement
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "INSERT INTO users(username, password) VALUES( ? , ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // id is auto increment so 2nd arg is to return the id generated
            ps.setString(1, registerRequest.getUsername());
            ps.setString(2, registerRequest.getPassword());
            // alter request so executeupdate no exec query
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            User newUser = new User();



            while (rs.next()) {
                newUser.setId(rs.getInt(1));

            }

            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            return newUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


      public static void main(String[] args) {
      UserDao dao = new UserDao();
      UsernamePasswordAuthentication newCreds = new UsernamePasswordAuthentication();
      newCreds.setPassword("new user4");
      newCreds.setUsername("new pass4");
      User returnedUser = dao.createUser(newCreds);
      System.out.println(dao.getUserByUsername(returnedUser.getUsername()));
      System.out.println(dao.getUserbyId(returnedUser.getId()));

      System.out.println(returnedUser);

      }


}
