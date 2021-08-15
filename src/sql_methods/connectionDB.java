/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_methods;
import java.sql.*;
/**
 *
 * @author rdaniels
 */
public class connectionDB {
    public static String url = "jdbc:mysql://localhost/administration";
    public static String usuario = "root";
    public static String contraseña = "drareneon";
    public static String clase = "com.mysql.jdbc.Driver";
    
    public static Connection conectar(){
        Connection conexion = null;
        
        try {
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url,usuario,contraseña);
            System.out.println("CONNECTION HAS BEEN ESTABLISHED SUCCESSFULLY");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        return conexion;
    }
}
