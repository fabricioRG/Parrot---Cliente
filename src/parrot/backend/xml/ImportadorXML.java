package parrot.backend.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class ImportadorXML {

    public static final String ABSOLUTH_PATH_SERVER = "/var/www/BaseDeDatos/BaseDeDatos.xml";
    private SimpleDateFormat fechaFormat = null;
    
    public ImportadorXML() {
        fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    
    
}
