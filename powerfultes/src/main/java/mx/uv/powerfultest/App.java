package mx.uv.powerfultest;

import static spark.Spark.*;
import mx.uv.powerfultest.DB.DAO;
import mx.uv.powerfultest.DB.Usuarios;
import com.google.gson.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Gson gson = new Gson();
    public static void main( String[] args )
    {                
        port(getHerokuAssignedPort());
        staticFiles.location("/");

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));  
        
        get("/hello", (req, res) -> "Hello Heroku World");

        
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "index.html"));
        });
        
        post("/usuario", (req, res) -> {
            // Insertamos un nuevo usuarios
            String json = req.body();
            Usuarios u = gson.fromJson(json, Usuarios.class);            

            DAO dao = new DAO();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", dao.insertarUsuario(u));
            return respuesta;
        });                

        post("/validarUsuario", (req, res) -> {
            // Insertamos un nuevo usuario
            String json = req.body();
            Usuarios usuario = gson.fromJson(json, Usuarios.class);
            
            DAO usuarioDAO = new DAO();
            int resultado = usuarioDAO.validarUsuario(usuario);
            System.out.println(resultado);
            return resultado;
    
        });              
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
