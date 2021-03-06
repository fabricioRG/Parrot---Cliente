package parrot.backend.manejadores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.Socket;
import parrot.backend.analizadores.Lexer1;
import parrot.backend.analizadores.parser;
import parrot.frontend.entrada.LecturaXML;

/**
 *
 * @author fabricio
 */
public class ManejadorLectorXML {

    private LecturaXML lector = null;
    private static ManejadorLectorXML INSTANCE = null;

    private DataOutputStream mensaje;
    private DataInputStream entrada;

    private ManejadorLectorXML() {
    }

    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ManejadorLectorXML();
        }
    }

    public static ManejadorLectorXML getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    public void setLectorXML(LecturaXML lector) {
        this.lector = lector;
    }

    public void procesarTexto(String entrada, String id) {
        StringReader sr = new StringReader(entrada);
        Lexer1 lexer = new Lexer1(sr);
        parser pars = new parser(lexer, ManejadorParser.getInstance());
        try {
            if (entrada.isEmpty()) {
                throw new Exception("Entrada vacia, intente de nuevo");
            }
            pars.parse();
            ManejadorLectorXML.getInstance().initClient(entrada, id);
        } catch (Exception ex) {
            showMessege(ex.getMessage());
        }
    }

    public void showMessege(String error) {
        this.lector.getErroresPane().setVisible(true);
        this.lector.getjErroresPane().setText(error);
    }

//Cliente
    public void initClient(String messege, String id) /*ejecuta este metodo para correr el cliente */ {

        final String HOST = "localhost";
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;
        
        String salida = "(" + id + ")\n" + "{" + messege + "}";

        try {
            Socket sc = new Socket(HOST, PUERTO);
            
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF(salida);
            
            String mensaje = in.readUTF();
            
            lector.getjErroresPane().setText(mensaje);
            
            sc.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

}
