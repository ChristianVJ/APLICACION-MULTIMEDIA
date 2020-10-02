/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.graficos;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Clase Ellipse que representa una elipse sobre el lienzo
 * @author Chris
 */

public class Ellipse extends FilledGeomShape{
    
    // CONSTRUCTOR
    /**
     * Constructor de la clase Line.
     * @param cl , color.
     * @param tr , trazo.
     * @param alis , alisado.
     * @param tras , transparencia.
     * @param compo , composite.
     * @param rend , render. 
     */
    public Ellipse (Color cl, Stroke tr, boolean alis, boolean tras, Composite compo, RenderingHints rend, boolean rell){
        
        this.color = cl;
        this.trazo = tr;
        this.alisado = alis;
        this.transparente = tras;
        this.comp = compo;
        this.render = rend;
        this.relleno = rell;
        this.shape = new Ellipse2D.Double();
    }
    
    // MÉTODO IMPLEMENTADO DRAWSHAPE
    @Override
    public void drawShape(Graphics2D g) {
        if (borde_editar){
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
        if (relleno) g.fill(shape);
        g.draw(shape);

    }
    
    // MÉTODO IMPLEMENTADO UPDATEDESHAPE
    @Override
    public void updateDeShape(Point2D pto1,Point2D pto2, Point2D pto3){
        ((Ellipse2D)shape).setFrameFromDiagonal(pto1, pto2);
    }
    
    // MÉTODO IMPLEMENTADO GETCONTENIDOENSHAPE
     @Override
    public boolean getContenidoEnShape(Point2D pto){
        return ((Ellipse2D)shape).contains(pto);
    }   
    
    // MÉTODO IMPLEMENTADO SETLOCATIONSHAPE
    @Override
    public void setLocationShape(Point2D pto1, Point2D pto2, Point2D pto3){
   
        Dimension2D bound = new Dimension();
        double px, py; 
        double centro_x, centro_y;
        bound.setSize(((Ellipse2D)shape).getWidth(), ((Ellipse2D)shape).getHeight());

        centro_x = (((Ellipse2D)shape).getMaxX() - ((Ellipse2D)shape).getMinX()) / 2;
        centro_y = (((Ellipse2D)shape).getMaxY() - ((Ellipse2D)shape).getMinY()) / 2;
        px = pto1.getX() - centro_x;
        py = pto1.getY() - centro_y;
     
        Point2D newp2 = new Point2D.Double(px, py);
        ((Ellipse2D)shape).setFrame(newp2, bound);

    }

}
