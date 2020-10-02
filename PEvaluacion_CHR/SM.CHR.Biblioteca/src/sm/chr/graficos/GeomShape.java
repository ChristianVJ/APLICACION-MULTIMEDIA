/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.graficos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;

/**
 * Super clase GeomShape que representa las formas disponibles
 * Contiene los atributos necesarios y métodos abstractos que implementarán
 * las figuras. 
 * @author Chris
 */

public abstract class GeomShape {
    
    // FORMA Y ATRIBUTOS DE FORMA
    public Shape shape;
    public Point pto_1,pto_2;
    public Color color;
    public Stroke trazo;
    public Composite comp;
    public RenderingHints render;
    
    // VARIABLES DE ACCESO A ATRIBUTOS
    public boolean alisado;
    public boolean transparente;
    public boolean borde_editar = false;
    
    // VARIABLES PARA EL BOUND
    Rectangle formaEdicion = null;
    float patron_limites[] = {2.0f};
    Stroke limite_borde_editar = new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 2.0f,patron_limites, 1.0f);
    
    // GET'S y SET'S
    
    /**
     * Método para obtener la forma.
     * @return shape , forma. 
     */
    public Shape getShape() {
        return shape;
    }
    
    /**
     * Método para modificar la forma.
     * @param shape , de la forma. 
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }
    
    /**
     * Método para obtener el color.
     * @return color , de la forma. 
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Método para modificar el color.
     * @param color , el color de la forma. 
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Método para obtener el trazo.
     * @return trazo , de la forma. 
     */
    public Stroke getTrazo() {
        return trazo;
    }
    
    /**
     * Método para modificar el trazo.
     * @param trazo, grosor del trazo. 
     */
    public void setTrazo(Stroke trazo) {
        this.trazo = trazo;
    }
    
    /**
     * Método para obtener si transparencia o no.
     * @return transparente.
     */
    public boolean isTransparente() {
        return transparente;
    }
    
    /**
     * Método para modificar la transparencia
     * @param transparente, transparencia de la forma. 
     */
    public void setTransparente(boolean transparente) {
        this.transparente = transparente;
    }
    
    /**
     * Método para obtener si es alisado o no
     * @return alisado. 
     */
    public boolean isAlisado() {
        return alisado;
    }
    
    /**
     * Método para modificar el antialising.
     * @param alisado, antialiasing de la forma.  
     */
    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
    }
    
    /**
     * Método para obtener el composite.
     * @return composite, de la forma.  
     */
    public Composite getComp() {
        return comp;
    }
    
    /**
     * Método para modificar el composite.
     * @param comp, composite de la forma. 
     */
    public void setComp(Composite comp) {
        this.comp = comp;
    }
    
    /**
     * Método para obtener el render.
     * @return render, de la forma. 
     */
    public RenderingHints getRender() {
        return render;
    }
    
    /**
     * Método para modificar el render.
     * @param render, render de la forma.  
     */
    public void setRender(RenderingHints render) {
        this.render = render;
    }
    
    /**
     * Método para obtener el punto 1. 
     * @return pto_1, de la forma.
     */
    public Point2D getPto_1() {
        return pto_1;
    }
    
    /**
     * Método para modificar el punto 1. 
     * @param pto_1, de la forma. 
     */
    public void setPto_1(Point pto_1) {
        this.pto_1 = pto_1;
    }
    
    /**
     * Método para obtener el punto 2.
     * @return pto_2 , de la forma. 
     */
    public Point2D getPto_2() {
        return pto_2;
    }
    
    /**
     * Método para modificar el punto 2. 
     * @param pto_2, de la forma. 
     */
    public void setPto_2(Point pto_2) {
        this.pto_2 = pto_2;
    }
    
    /**
     * Método para establecer si se muestra o no el contorno al editar.
     * @param mostrar, booleano. 
     */
    public void drawBordeEditar(boolean mostrar) {
        borde_editar = mostrar;
    }
    
    // MÉTODOS ABSTRACTOS COMUNES
    
    /**
     * Método para dibujar la forma deseada junto a los atributos establecidos, genera
     * el contorno si estamos en modo edición y pinta la figura con relleno o sin. 
     * @param g graphics2d a dibujar.
     */
    public abstract void drawShape(Graphics2D g);
    
    /**
     * Método que actualiza la figura en función del movimiento del usuario con su ratón.
     * @param pto1 , punto de inicio.
     * @param pto2 , punto de control (curvas).
     * @param pto3 , punto final.
     */
    public abstract void updateDeShape(Point2D pto1,Point2D pto2, Point2D pto3);
    
    /**
     *Método que verifica si la pulsación se encuentra dentro del contorno de la misma.
     * @param pto , punto de click. 
     * return true, si está contenida.
     * return false, en caso contrario.
     */
    public abstract boolean getContenidoEnShape(Point2D pto);
    
    /**
     *Método que posiciona la figura en función del movimiento del ratón en modo edición.
     * @param pto1 , punto de click.
     * @param pto2 , punto auxiliar.
     * @param pto3 , punto auxiliar.
     */
    public abstract void setLocationShape(Point2D pto1, Point2D pto2, Point2D pto3);
    
}
