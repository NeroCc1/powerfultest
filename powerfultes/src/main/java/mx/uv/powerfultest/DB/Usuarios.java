package mx.uv.powerfultest.DB;


public class Usuarios {
    private String usuario;
    private String contraseña;

    public Usuarios(String usuario, String contraseña) {

        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public String getusuario() {
        return usuario;
    }
    public String getcontraseña() {
        return contraseña;
    }
    public void setusuario(String usuario) {
        this.usuario = usuario;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}    
