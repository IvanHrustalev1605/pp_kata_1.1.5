package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte)34);
        userService.saveUser("Sergey", "Sergeev", (byte)23);
        userService.saveUser("Oleg", "Alexandrov", (byte)12);
        userService.saveUser("Ilya", "Smirnov", (byte)54);
        userService.getAllUsers().forEach(x -> System.out.println(x));
        userService.cleanUsersTable();
       userService.dropUsersTable();


    }
}
