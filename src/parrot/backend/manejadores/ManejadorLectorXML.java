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
    final String HOST = "localhost";
    final int PUERTO = 8600;
    Socket sc;
    DataOutputStream mensaje;
    DataInputStream entrada;

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

    public void procesarTexto(String entrada) {
        this.lector.getErroresPane().setVisible(false);
        StringReader sr = new StringReader(entrada);
        Lexer1 lexer = new Lexer1(sr);
        parser pars = new parser(lexer, ManejadorParser.getInstance());
        try {
            if (entrada.isEmpty()) {
                throw new Exception("Entrada vacia, intente de nuevo");
            }
            pars.parse();
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorMessege(ex.getMessage());
        }
    }

    public void showErrorMessege(String error) {
        this.lector.getErroresPane().setVisible(true);
        this.lector.getjErroresPane().setText(this.lector.getjErroresPane().getText() + error + "\n");
    }

//Cliente
    public void initClient() /*ejecuta este metodo para correr el cliente */ {

        try {
            sc = new Socket(HOST, PUERTO);
            /*conectar a un servidor en localhost con puerto 5000*/

            //creamos el flujo de datos por el que se enviara un mensaje
            mensaje = new DataOutputStream(sc.getOutputStream());

            //enviamos el mensaje
            mensaje.writeUTF("hola que tal!!");

            //cerramos la conexión
            sc.close();
        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

}
