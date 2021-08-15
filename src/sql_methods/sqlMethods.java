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
public class sqlMethods {
    public static connectionDB conexion = new connectionDB();
    
    public static PreparedStatement preparacion;
    
    public static ResultSet resultado;
    
    public static String sql;
    
    public static int cantidad = 0;
    
    
    // metodo para guardar al usuario luego del registro
    public int guardar(String nombre, String apellido, String nombre_usuario, String contraseña){
        
        int resultado = 0;
        
        Connection conexion = null;
        
        String guardar = ("INSERT INTO usuarios (nombre, apellido, nombre_usuario, contraseña) VALUES(?,?,?,?)");
        
        try {
            conexion = (Connection) connectionDB.conectar();
            preparacion = conexion.prepareStatement(guardar);
            preparacion.setString(1, nombre);
            preparacion.setString(2, apellido);
            preparacion.setString(3, nombre_usuario);
            preparacion.setString(4, contraseña);
            
            resultado = preparacion.executeUpdate();
            preparacion.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return resultado;
    }
    
    // metodo para buscar el usuario con el log in para darle el acceso
    public static String  buscarUsuario(String contraseña){
            String buscar_usuario = null;
            
            Connection conexion = null;
            
            try {
                conexion = (Connection) connectionDB.conectar();
                String buscar_dba = "SELECT nombre_usuario FROM usuarios WHERE contraseña = '" + contraseña + "'";
                preparacion = conexion.prepareStatement(buscar_dba);
                resultado = preparacion.executeQuery();
                
                if(resultado.next()){
                    String nombre_usuario = resultado.getString("nombre_usuario");
                    buscar_usuario = nombre_usuario;
                }
                
                conexion.close();
                
            } catch (Exception e) {
                System.out.println("Opps ha ocurrodo un error inesperado" + e);
                
            }
            
            return buscar_usuario;
    
    }
    
    // metodo para buscar los usuariso luego del loin 
    
    public String buscarUsuarioRegistrado(String nombre_usuario,String contraseña){
        String buscar_usuario = null;
        
        Connection conexion = null;
        
        try {
            conexion = (Connection) connectionDB.conectar();
            
            String db_sentencia_buscar = ("SELECT nombre_usuario, contraseña FROM usuarios WHERE contraseña = '" + contraseña + "' && nombre_usuario = '" + nombre_usuario +"'");
            
            preparacion = conexion.prepareStatement(db_sentencia_buscar);
            
            resultado = preparacion.executeQuery();
            
            if (resultado.next()){
                
                buscar_usuario = "El usuario a sido encontrado";
            
            }
            else{
                
                db_sentencia_buscar = ("SELECT nombre_usuario, contraseña FROM usuarios WHERE contraseña = '" + contraseña + "' OR nombre_usuario = '" + nombre_usuario +"'");
                
                preparacion = conexion.prepareStatement(db_sentencia_buscar);
            
                resultado = preparacion.executeQuery();
                
                if(resultado.next()){
                    buscar_usuario = "Uno de los datos introducidos a sido encontrado pero otro no";
                }
                else{
                    buscar_usuario = "El usuario no a sido encontrado";
                }
                
            
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.out.println("Opps algo ha salido mal " + e);
        }
        
        return buscar_usuario;
    }
    
    public int ejecutarSentenciaSQL(String query){
        Connection conexion = null;
    
        try {
            conexion = (Connection) connectionDB.conectar();
            preparacion = conexion.prepareStatement(query);
            preparacion.execute();
            return 1;
            
        } catch (SQLException e) {
            System.out.println("Opps algo a ocurrido mal" + e);
        }
        
        return 0;
    }
    
    public ResultSet consultarDatos(String query){
        Connection conexion = null;
        try {
            conexion = (Connection) connectionDB.conectar();
            preparacion = conexion.prepareStatement(query);
            
            resultado = preparacion.executeQuery();
            
            return resultado;
        } catch (Exception e) {
            System.out.println("Opps algo ha susedido " + e);
            return null;
        }
        
    
    }
}
