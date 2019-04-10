package parrot.backend.manejadores;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import parrot.backend.error.ManejadorErrores;

/**
 *
 * @author fabricio
 */
public class ManejadorParser {

    public static final String ABSOLUTH_PATH_SERVER = "/var/www/html";
    private static ManejadorParser INSTANCE = null;

    private ManejadorParser() {
    }

    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ManejadorParser();
        }
    }

    public static ManejadorParser getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    
    
    public void showErrorMessege(String error) {
        ManejadorLectorXML.getInstance().showMessege(error);
        System.out.println(error);
    }
    
}