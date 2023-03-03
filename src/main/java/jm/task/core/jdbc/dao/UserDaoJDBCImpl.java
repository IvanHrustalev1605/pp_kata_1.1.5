package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.property.access.spi.GetterFieldImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT auto_increment primary key," +
                "name VARCHAR(40)," +
                "lastName VARCHAR(30)," +
                "age TINYINT)";
        try(PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        try(PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
               preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql =  "INSERT INTO user (name, lastName, age) VALUES (?, ?, ? )";
        try(PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id = ? ";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
          preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
            if (list.size() >= 0) {
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
