package parrot.backend.usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import parrot.backend.analizadores.Lexer2;
import parrot.backend.analizadores.parser2;

/**
 *
 * @author fabricio
 */
public class ManejadorUsuario {

    public static String USUARIOS_XML = "usuarios.xml";
    public static String USUARIOS_AB = "<usuarios>\n";
    public static String USUARIOS_CE = "</usuarios>";
    public static String USUARIO_AB = "<usuario>\n";
    public static String USUARIO_CE = "</usuario>\n";
    public static String NOMBRE_AB = "<nombre>[";
    public static String NOMBRE_CE = "]</nombre>\n";
    public static String ID_AB = "<id>[";
    public static String ID_CE = "]</id>\n";
    public static String PASS_AB = "<pass>[";
    public static String PASS_CE = "]</pass>\n";
    private static ManejadorUsuario INSTANCE = null;
    private Usuario us = null;
    private List<Usuario> listaUsuario = null;

    public ManejadorUsuario() {
        listaUsuario = new LinkedList<>();
        getUsuarios();
    }

    public void addUsuario(Usuario user){
        listaUsuario.add(user);
        exportarXML();
    }
    
    public void updateUsuario(Usuario user, Usuario nuevo){
        for (int i = 0; i < listaUsuario.size(); i++) {
            if(listaUsuario.get(i).getId().equals(user.getId())){
                listaUsuario.remove(i);
                listaUsuario.add(nuevo);
                break;
            }
        }
        exportarXML();
    }
    
    public void removeUsuario(String id){
        for (int i = 0; i < listaUsuario.size(); i++) {
            if(listaUsuario.get(i).getId().equals(id)){
                listaUsuario.remove(i);
                break;
            }
        }
        exportarXML();
    }
    
    private void exportarXML(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USUARIOS_XML));
            bw.write(getTextoXML());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getTextoXML(){
        String salida = USUARIOS_AB;
        for (Usuario usuario : listaUsuario) {
            salida = salida + USUARIO_AB + NOMBRE_AB + usuario.getNombre() + NOMBRE_CE +
                    ID_AB + usuario.getId() + ID_CE + PASS_AB + usuario.getPass() + PASS_CE + USUARIO_CE;
        }
        salida = salida + USUARIOS_CE;
        return salida;
    }
    
    private void getUsuarios() {
        BufferedReader reader;
        FileReader file;
        String entrada = "";
        try {
            file = new FileReader(USUARIOS_XML);
            reader = new BufferedReader(file);
            while (reader.ready()) {
                entrada = entrada + reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringReader sr = new StringReader(entrada);
        Lexer2 lexer = new Lexer2(sr);
        parser2 pars = new parser2(lexer, this);
        try {
            if (entrada.isEmpty()) {
                throw new Exception("Entrada vacia, intente de nuevo");
            }
            pars.parse();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Usuario getUsuarioById(String id) {
        for (Usuario usuario : listaUsuario) {
            if (id.equals(usuario.getId())) {
                return usuario;
            }
        }
        return null;
    }

    /* Metodo que recibe parametros de tipo string y opciones tipo int, los cuales
    al reconocerse su tipo realizan una accion especifica los cuales son:
    OPTION                      ACCION
         1       -         Asignar nombre
         2       -         Asignar Id
         3       -         Asignar Pass
         4       -         Anadir objeto
     */
    public void setUsuario(String parametro, int option) {
        String paramet = null;
        if (parametro != null && !parametro.isEmpty()) {
            paramet = parametro.substring(1, parametro.indexOf("]")).trim();
        }
        switch (option) {
            case 1:
                us = new UsuarioBuilder().nombre(paramet).build();
                break;
            case 2:
                us.setId(paramet);
                break;
            case 3:
                us.setPass(paramet);
                break;
            case 4:
                listaUsuario.add(us);
                break;
        }
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

}
