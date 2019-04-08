package parrot.backend.menu;

import parrot.backend.etiqueta.Etiqueta;
import parrot.backend.paginaweb.PaginaWeb;

/**
 *
 * @author fabricio
 */
public class Menu {

    PaginaWeb padre;
    Etiqueta etiquetas;
    
    Menu (MenuBuilder builder){
        this.padre = builder.getPadre();
        this.etiquetas = builder.getEtiquetas();
    }

    public PaginaWeb getPadre() {
        return padre;
    }

    public void setPadre(PaginaWeb padre) {
        this.padre = padre;
    }

    public Etiqueta getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Etiqueta etiquetas) {
        this.etiquetas = etiquetas;
    }
    
}
