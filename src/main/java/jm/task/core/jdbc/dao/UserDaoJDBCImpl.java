package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {



    public void createUsersTable() {
        //комадна создания таблицы
        String sqlCommand = "CREATE TABLE users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age SMALLINT NOT NULL, PRIMARY KEY (id))";

        //создание таблицы
        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(sqlCommand)) { // создание объекта prepareStatement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE users";
        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(dropTable)) {
            //удаление таблицы
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement prepareStatement = Util.getMySQLConnection().prepareStatement(saveUser)) {
            //добавление юзеров
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getMySQLConnection().rollback();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id";
        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(removeUser)) {
            //удаление юзара по айди
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> arr = new ArrayList<>();
        String getuser = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(getuser);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                arr.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Util.getMySQLConnection().rollback();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return arr;
    }

    public void cleanUsersTable() {

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            //удаление таблицы
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}