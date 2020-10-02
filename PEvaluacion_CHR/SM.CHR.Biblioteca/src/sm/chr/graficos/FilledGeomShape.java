/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.graficos;
import java.awt.geom.Point2D;

/**
 * Clase intermedia FilledGeomShape que contiene los atributos característicos
 * de formas con relleno. 
 * @author Chris
 */

public abstract class FilledGeomShape extends GeomShape {
    
    // ATRIBUTOS DE FIGURAS CON RELLENO
    public boolean relleno;
    private Point2D pt_1, pt_2;
    
    // GET'S Y SET'S
    
    /**
     * Método para saber si está relleno o no.
     * @return relleno, de la forma. 
     */
    public boolean isRelleno() {
        return relleno;
    }
    
    /**
     * Método para modificar el relleno.
     * @param relleno , de la forma. 
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
    
    /**
     * Método para obtener el punto 1.
     * @return pt_1, de la forma. 
     */
    public Point2D getPt_1() {
        return pt_1;
    }
    
    /**
     * Método para modificar el punto 1.
     * @param pt_1 , de la forma. 
     */
    public void setPt_1(Point2D pt_1) {
        this.pt_1 = pt_1;
    }
    
    /**
     * Método para obtener el punto 2.
     * @return pt_2, de la forma. 
     */
    public Point2D getPt_2() {
        return pt_2;
    }
    
    /**
     * Método para modificar el punto 2.
     * @param pt_2 , de la forma. 
     */
    public void setPt_2(Point2D pt_2) {
        this.pt_2 = pt_2;
    }

    
}
