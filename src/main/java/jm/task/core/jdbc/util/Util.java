package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
   //настройка подключения с БД
    private static final String JDBC_driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/firstdata?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSW = "Toptop101";

//    public static Connection getConnection(){
//        Connection connection = null;
//        try{
//            Class.forName(JDBC_driver);
//        } catch (ClassNotFoundException ign){
//            System.out.println("Не получилось загрузить класс");
//            ign.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(Util.URL,Util.USER_NAME,Util.PASSW);
//            System.out.println("Соединение установленно");
//        } catch (SQLException gc) {
//            System.out.println("Соединение не установлено");
//            gc.printStackTrace();
//        }
//        return connection;
//    }
public static Connection getMySQLConnection() {

    Connection connection = null;

    try {
        Class.forName(JDBC_driver);
        connection = DriverManager.getConnection(URL, USER_NAME, PASSW);

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return connection;
}

}
