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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase Line que representa una linea sobre el lienzo
 * @author Chris
 */

public class Line extends GeomShape{
    
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
     public Line(Point2D pto1, Point2D pto2, Color cl, Stroke tr, boolean alis, boolean tras, Composite compo, RenderingHints rend){
        
        this.color = cl;
        this.trazo = tr;
        this.alisado = alis;
        this.transparente = tras;
        this.comp = compo;
        this.render = rend;
        shape = new Line2D.Float(pto1, pto2);
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
        ((Line2D)shape).setLine(pto1,pto2);
    }

    // MÉTODO IMPLEMENTADO GETCONTENIDOENSHAPE
    @Override
    public boolean getContenidoEnShape(Point2D pto){
        return isNear(pto);
    }   
    
    // MÉTODO IMPLEMENTADO SETLOCATIONSHAPE
    @Override
    public void setLocationShape(Point2D pto1, Point2D pto2, Point2D pto3){

        double centro_linea_x = (((Line2D)shape).getX1() + ((Line2D)shape).getX2())/2;
        double centro_linea_y = (((Line2D)shape).getY1() + ((Line2D)shape).getY2())/2;
        
        double x1 = pto1.getX() - (centro_linea_x - ((Line2D)shape).getX1());
        double y1 = (((Line2D)shape).getY1() - centro_linea_y) + pto1.getY(); 
        double x2 = (((Line2D)shape).getX2() - centro_linea_x) + pto1.getX();     
        double y2 = pto1.getY() - (centro_linea_y - ((Line2D)shape).getY2());
                
        ((Line2D)shape).setLine(x1, y1, x2,y2);

    }
    
    // MÉTODOS AUXILIARES (SOLO LÍNEA Y PUNTO)
    /**
     * Método auxiliar para figuras especiales, detectar si está cerca del boundingbox.
     * @param p , punto de click.
     * @return true, si está dentro.
     */
    public boolean isNear(Point2D p){
        return ((Line2D)shape).ptLineDist(p)<=2.0;
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
