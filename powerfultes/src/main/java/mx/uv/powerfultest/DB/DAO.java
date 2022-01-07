package mx.uv.powerfultest.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    private Conexion conexion = new Conexion();

    public String insertarUsuario(Usuarios u) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "INSERT INTO usuario (usuario, contraseña) VALUES (?, ?)";
            prestm = conn.prepareStatement(sql);
            prestm.setString(1, u.getusuario());
            prestm.setString(2, u.getcontraseña());
            if (prestm.executeUpdate() >0) 
                msj = "Usuario agregado";
            else
                msj = "No se agregó el usuario";            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm != null){
                try {
                    prestm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;
    }

    public int validarUsuario(Usuarios usuario) throws Exception{
        Statement stm;
        ResultSet usuarios;
        String sql = "select * from usuario;";
        
        try (Connection conn = conexion.getConnection();){
            stm = conn.createStatement();
            usuarios = stm.executeQuery(sql);
            System.out.println(usuarios);
            while (usuarios.next()){
                Usuarios u = new Usuarios(usuarios.getString("usuario"), usuarios.getString("contraseña"));
                
                    if(usuarios.getString("usuario").equals(u.getusuario()) && usuarios.getString("contraseña").equals(u.getcontraseña())){                        
                        System.out.println("Bienvenido " + usuario);                                               
                        
                    }else{
                        System.out.println("Usuario no registrado");
                        return 0;
                    }                
            }
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
        return 0;
    }

   
}

