package parrot.backend.parrafo;

/**
 *
 * @author fabricio
 */
public class Parrafo {

    String texto;
    String alineacion;
    String color;
    
    Parrafo (ParrafoBuilder builder){
        this.texto = builder.getTexto();
        this.alineacion = builder.getAlineacion();
        this.color = builder.getColor();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(String alineacion) {
        this.alineacion = alineacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
