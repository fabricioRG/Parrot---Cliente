package parrot;

import parrot.backend.manejadores.ManejadorLectorXML;
import parrot.frontend.entrada.LecturaXML;
import parrot.frontend.entrada.Login;

/**
 *
 * @author fabricio
 */
public class Parrot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
    
}
