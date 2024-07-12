package ru.stepup.demohikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoJdbc {

    private Connection connection;

    @Autowired
    public UserDaoJdbc(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int save (User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users(username) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                var rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // выборка из таблицы Users в список
    public List<User> getUsers() {

        List<User> listUser = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                listUser.add(user);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return listUser;
    }

    public User getUser(long id) {
        User user = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(int id, String username) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE users SET username = ? WHERE id = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, id);
            int row = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM users");
            preparedStatement.executeQuery();
        }
        catch (SQLException e) {
        }
    }
}
