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
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * Clase Curve que representa una curva sobre el lienzo
 * @author Chris
 */

public class Curve extends GeomShape {
    
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
   public Curve(Point2D pto1, Point2D pto2, Color cl, Stroke tr, boolean alis, boolean tras, Composite compo, RenderingHints rend){
        
        this.color = cl;
        this.trazo = tr;
        this.alisado = alis;
        this.transparente = tras;
        this.comp = compo;
        this.render = rend;
        this.shape = new QuadCurve2D.Double();
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
      ((QuadCurve2D)shape).setCurve(pto1, pto3, pto2);
    }
    
    // MÉTODO IMPLEMENTADO GETCONTENIDOENSHAPE
    @Override
    public boolean getContenidoEnShape(Point2D pto){
        return ((QuadCurve2D)shape).contains(pto);
    }   
    
    // MÉTODO IMPLEMENTADO SETLOCATIONSHAPE
    @Override
    public void setLocationShape(Point2D p1, Point2D p2, Point2D p3){   

        double centro_linea_x = (((QuadCurve2D)shape).getX1() + ((QuadCurve2D)shape).getX2())/2;
        double centro_linea_y = (((QuadCurve2D)shape).getY1() + ((QuadCurve2D)shape).getY2())/2;

        double x1 = p1.getX() - (centro_linea_x - ((QuadCurve2D)shape).getX1());
        double y1 = (((QuadCurve2D)shape).getY1() - centro_linea_y) + p1.getY(); 
        Point2D punto1 = new Point2D.Double(x1,y1);
        
        double x2 = (((QuadCurve2D)shape).getX2() - centro_linea_x) + p1.getX();     
        double y2 = p1.getY() - (centro_linea_y - ((QuadCurve2D)shape).getY2());
        Point2D punto2 = new Point2D.Double(x2,y2);
        
        double calc_controlx = p1.getX() - centro_linea_x;
        double calc_controly = p1.getY() - centro_linea_y;
        Point2D punto3 = new Point2D.Double(((QuadCurve2D)shape).getCtrlX() + calc_controlx, ((QuadCurve2D)shape).getCtrlY() + calc_controly);
        
        ((QuadCurve2D)shape).setCurve(punto1, punto3 ,punto2);
        
    }
}
