/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.graficos;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase GPath que representa un trazo libre sobre el lienzo
 * @author Chris
 */

public class GPath extends GeomShape{
    
    // CONSTRUCTOR
    /**
     * Constructor de la clase Line.
     * @param pto1 , punto 1.
     * @param pto2 , punto 2.
     * @param cl , color.
     * @param tr , trazo.
     * @param alis , alisado.
     * @param tras , transparencia.
     * @param compo , composite.
     * @param rend , render. 
     */
    public GPath (Point2D pto1, Point2D pto2, Color cl, Stroke tr, boolean alis, boolean tras, Composite compo, RenderingHints rend){
        
        this.color = cl;
        this.trazo = tr;
        this.alisado = alis;
        this.transparente = tras;
        this.comp = compo;
        this.render = rend;
        this.shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(pto1.getX(), pto2.getY());
    }
    
    // MÉTODO IMPLEMENTADO DRAWSHAPE 
    @Override
    public void drawShape(Graphics2D g) {
         if (borde_editar) {
            g.setStroke(limite_borde_editar);
            formaEdicion = shape.getBounds();
            g.setPaint(Color.red);
            formaEdicion.grow(1, 1);
            g.draw(formaEdicion);
        }
        g.setPaint(color);
        g.setStroke(trazo);
        g.setRenderingHints(render);
        g.setComposite(comp);
        g.draw(shape);
    }
    
    // MÉTODO IMPLEMENTADO UPDATEDESHAPE
    @Override
    public void updateDeShape(Point2D pto1,Point2D pto2, Point2D pto3){
        ((GeneralPath) shape).lineTo(pto2.getX(), pto2.getY());
    }
    
    // MÉTODO IMPLEMENTADO GETCONTENIDOENSHAPE
    @Override
    public boolean getContenidoEnShape(Point2D pto){
        return ((GeneralPath)shape).contains(pto);
    }   
    
    // MÉTODO IMPLEMENTADO SETLOCATIONSHAPE
    @Override
    public void setLocationShape(Point2D pto1, Point2D pto2, Point2D pto3){
        
        double dx = pto1.getX() - (shape.getBounds().getCenterX());
        double dy = pto1.getY() - (shape.getBounds().getCenterY());

        ((GeneralPath)shape).transform(AffineTransform.getTranslateInstance(dx,dy)); 
    }
    
    /**
     * Método auxiliar para figuras especiales, detectar si está cerca del boundingbox.
     * @param p , punto de click.
     * @return true, si está dentro.
     */
    public boolean isNear(Point2D p){
        return ((Line2D)shape).ptLineDist(p)<=5.0;
    }
    
    /**
     * Método auxiliar para figuras especiales, detectar si está contenido en el boundingbox.
     * @param p , punto de click.
     * @return true, si está contenido.
     */
    public boolean contains(Point2D p) {
        return isNear(p);
    }

    
}
