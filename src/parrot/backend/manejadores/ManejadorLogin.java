package parrot.backend.manejadores;

import parrot.backend.usuario.ManejadorUsuario;
import parrot.backend.usuario.Usuario;
import parrot.backend.usuario.UsuarioBuilder;
import parrot.frontend.entrada.LecturaXML;
import parrot.frontend.entrada.Login;

/**
 *
 * @author fabricio
 */
public class ManejadorLogin {
    
    private static ManejadorLogin INSTANCE = null;

    public ManejadorLogin() {
    }
    
    private synchronized static void createInstance(){
        if (INSTANCE == null){
            INSTANCE = new ManejadorLogin();
        }
    }
    
    public static ManejadorLogin getInstance(){
        if(INSTANCE == null){
            createInstance();
        }
        return INSTANCE;
    }
    
    public void login(String id, char[] pass, Login login) throws Exception{
        String passwordUsuario = new String(pass);
        Usuario user = null;
        if (id.trim().isEmpty()) {
            throw new Exception("No se ha ingresado un \"id de usuario\" valido");
        } else if (passwordUsuario.trim().isEmpty()) {
            throw new Exception("No se ha ingresado una \"contraseña\" valida");
        }
        ManejadorUsuario mu = new ManejadorUsuario();
        if(mu.getUsuarioById(id) != null){
            user = mu.getUsuarioById(id);
            if(user.getPass().equals(passwordUsuario)){
                LecturaXML lector = new LecturaXML(user);
                lector.setVisible(true);
                login.setVisible(false);
            } else {
                throw new Exception("Contraseña incorrecta. Intentelo de nuevo.");
            }
        } else {
            throw new Exception("El ID ingresado no existe");
        }
    }
    
}
