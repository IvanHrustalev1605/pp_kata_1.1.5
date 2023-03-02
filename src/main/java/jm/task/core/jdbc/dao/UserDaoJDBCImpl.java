package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

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
        try(Statement statement = util.getConnection()) {
            ResultSet rs = statement.getResultSet();
           int row = statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (" +
                    "id INT auto_increment primary key," +
                    "name VARCHAR(40)," +
                    "lastName VARCHAR(30)," +
                    "age TINYINT)");
                System.out.println("Таблица создана!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = util.getConnection()) {
           int row = statement.executeUpdate("DROP TABLE IF EXISTS user");
               System.out.println("Тфблица успешно удалена!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = util.getConnection()) {
            statement.executeUpdate("INSERT INTO user (name, lastName, age)" +
                    "values('"+name+"','"+lastName+"', '"+age+"' )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пользователь с именем " +name+ " добавлено!");
    }

    public void removeUserById(long id) {
        try (Statement statement = util.getConnection()) {
          int row = statement.executeUpdate("DELETE FROM user WHERE id = '"+id+"' ");
          if (row > 0) {
              System.out.println("Пользователь с id = " + id +" успешно удален! ");
          } else {
              System.err.println("OOOPS something goes wrong((");
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = util.getConnection()){
            int k = 1;
            ResultSet rs = statement.executeQuery("SELECT * FROM user");
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
            if (list.size() > 0) {
                return list;
            }
            System.err.println("Что то пошло не так(((");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection()){
           int row = statement.executeUpdate("DELETE FROM user");
           if (row > 0) {
               System.out.println("Таблица очищена!");
           }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
