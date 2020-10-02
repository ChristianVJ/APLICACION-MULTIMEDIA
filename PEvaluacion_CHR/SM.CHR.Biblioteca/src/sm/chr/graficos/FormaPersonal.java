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
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Clase FormaPersonal que representa una forma única sobre el lienzo
 * @author Chris
 */

public class FormaPersonal extends GeomShape{
    
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
    public FormaPersonal (Point2D pto1, Point2D pto2, Color cl, Stroke tr, boolean alis, boolean tras, Composite compo, RenderingHints rend){
   
        Ellipse2D.Double ojo = new Ellipse2D.Double();
        Ellipse2D.Double ojo1 = new Ellipse2D.Double();
        Ellipse2D.Double pupila = new Ellipse2D.Double();
        Ellipse2D.Double pupila1 = new Ellipse2D.Double();
        Ellipse2D.Double cabeza = new Ellipse2D.Double();
        
        Rectangle diente = new Rectangle();
        Rectangle diente1 = new Rectangle();
        Rectangle diente2 = new Rectangle();
        
        Area c1,c2,c3,p1,p2,d1,d2,ca;
        
        ojo.setFrame(pto1.getX()+35, pto1.getY()+25, 90.0, 90.0);
        ojo1.setFrame(pto1.getX()-55, pto1.getY(), 120.0, 120.0);
        
        pupila.setFrame(pto1.getX()+90, pto1.getY()+55, 10.0, 10.0);
        pupila1.setFrame(pto1.getX()-40, pto1.getY()+65, 35.0, 35.0);
        
        diente.setFrame(pto1.getX()-20, pto1.getY()+115, 40, 60);
        diente1.setFrame(pto1.getX()+22, pto1.getY()+115, 40, 90);
        diente2.setFrame(pto1.getX()+64, pto1.getY()+115, 30, 70);
        
        cabeza.setFrame(pto1.getX()-85, pto1.getY()-26, 250.0, 250.0);
         
        c1 = new Area(ojo);
        c2 = new Area(ojo1);
        c3 = new Area(diente);
        p2 = new Area (pupila1);
        p1 = new Area (pupila);
        d1 = new Area (diente1);
        d2 = new Area (diente2);
        ca = new Area (cabeza);
        
        c1.add(c2);
        c3.add(c1);
        c3.exclusiveOr(p2);
        c3.exclusiveOr(p1);
        c3.add(d1);
        c3.add(d2);
        c3.exclusiveOr(ca);
        
        shape = c3;

        this.color = cl;
        this.trazo = tr;
        this.alisado = alis;
        this.transparente = tras;
        this.comp = compo;
        this.render = rend;

    }
    
    // MÉTODO IMPLEMENTADO DRAWSHAPE
    @Override
    public void drawShape(Graphics2D g) {
         if (borde_editar) {
            System.err.println("ENTRA EN BOUNDINGBOX");
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

        Dimension2D dim = new Dimension();
        double dx, dy, dxdim, dydim;
        Point2D newp2;

        dim.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
        dxdim = (shape.getBounds().getMaxX() - shape.getBounds().getMinX()) / 2;
        dydim = (shape.getBounds().getMaxY() - shape.getBounds().getMinY()) / 2;
        dx = pto1.getX() - dxdim;
        dy = pto1.getY() - dydim;
     
        newp2 = new Point2D.Double(dx, dy);
        ((RectangularShape)shape).setFrame(newp2, dim);

    }
    
    // MÉTODO IMPLEMENTADO GETCONTENIDOENSHAPE
     @Override
    public boolean getContenidoEnShape(Point2D pto){
        return ((Area)shape).contains(pto);
    }   
    
    // MÉTODO IMPLEMENTADO SETLOCATIONSHAPE
    @Override
    public void setLocationShape(Point2D pto1, Point2D pto2, Point2D pto3){
        
        double dx = pto1.getX() - (shape.getBounds().getCenterX());
        double dy = pto1.getY() - (shape.getBounds().getCenterY());

        ((Area)shape).transform(AffineTransform.getTranslateInstance(dx,dy));  
    }
        
}



