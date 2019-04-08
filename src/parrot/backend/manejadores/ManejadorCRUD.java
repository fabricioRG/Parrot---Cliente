package parrot.backend.manejadores;

import java.util.LinkedList;
import java.util.List;
import parrot.backend.usuario.ManejadorUsuario;
import parrot.backend.usuario.Usuario;
import parrot.backend.usuario.UsuarioBuilder;

/**
 *
 * @author fabricio
 */
public class ManejadorCRUD {

    private static ManejadorCRUD INSTANCE = null;
    private List<Usuario> listaUser = null;
    public static String ROOT = "root";

    public ManejadorCRUD() {
        this.listaUser = new LinkedList<>();
    }

    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ManejadorCRUD();
        }
    }

    public static ManejadorCRUD getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    public void crearUsuario(String nombre, String id, String pass) throws Exception {
        String name = nombre.trim();
        String ident = id.trim();
        String pas = pass.trim();

        if (name.isEmpty() || ident.isEmpty() || pas.isEmpty()) {
            throw new Exception("Campo vacio. Intentelo de nuevo");
        } else {
            ManejadorUsuario mu = new ManejadorUsuario();
            if (mu.getListaUsuario() != null) {
                listaUser.addAll(mu.getListaUsuario());
                for (Usuario usuario : listaUser) {
                    if (usuario.getId().equals(ident)) {
                        throw new Exception("Identificador no disponible. Ingrese uno diferente.");
                    }
                }
                Usuario us = new UsuarioBuilder().nombre(name).id(ident).pass(pas).build();
                mu.addUsuario(us);
            } else {
                Usuario us = new UsuarioBuilder().nombre(name).id(ident).pass(pas).build();
                mu.addUsuario(us);
            }
        }
    }

    public List getList() {
        ManejadorUsuario mu = new ManejadorUsuario();
        if (mu.getListaUsuario() != null) {
            return mu.getListaUsuario();
        } else {
            return null;
        }
    }

    public void modificarUsuario(Usuario usuario, String id, String nombre) throws Exception {
        ManejadorUsuario mu = new ManejadorUsuario();
        if(id.isEmpty() || nombre.isEmpty()){
            throw new Exception("Campo(s) vacio(s).");
        }
        if(mu.getUsuarioById(id)!= null){
            throw new Exception("No es posible modificar el usuario. ID ya existente");
        } else {
            mu.updateUsuario(usuario, new UsuarioBuilder().id(id).nombre(nombre).build());
        }
    }

    public void eliminarUsuario(Usuario usuario) throws Exception {
        if(usuario.getId().equals(ROOT)){
            throw new Exception("No es posible eliminar el usuario root.");
        }
        ManejadorUsuario mu = new ManejadorUsuario();
        mu.removeUsuario(usuario.getId());
    }

}
