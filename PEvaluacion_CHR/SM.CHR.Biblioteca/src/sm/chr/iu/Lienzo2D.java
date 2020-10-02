/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.iu;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sm.chr.graficos.*;

/**
 * Clase Lienzo2D donde dibujar formas, plasmar imágenes cargadas del ordenador personal
 * o capturas de pantalla. 
 * @author Chris
 */

public class Lienzo2D extends javax.swing.JPanel {
    
    //////////////////////////////////////////////
    //LISTA DE FORMAS Y FORMAS
    protected final List<GeomShape> geomshapes;
    protected GeomShape fg;
    protected GeomShape select;
    protected FilledGeomShape fillselect;
    private Formas forma;
    private Line line;
    private Rectan rectangle;
    private Pointe point;
    private Ellipse ellipse;
    private FormaPersonal roto;
    private Curve curv;
    private GPath gp;
    //////////////////////////////////
    // PUNTOS PARA EVENTOS Y SELECCIÓN
    private Point p_inicial;
    private Point p_final;
    private Point pAux;
    private Point p1, p2, p3;
    int position_selected;
    /////////////////////
    // ATRIBUTOS DE FORMA
    private Color color;
    private Stroke trazo;
    private RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
    private Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
    ////////////////////
    //ACCESO A ATRIBUTOS
    public boolean alisado = false;
    public boolean editar = false;
    public boolean relleno = false;
    public boolean transparente = false;
    public boolean transparente_noedit = false;
    public boolean alisado_noedit = false;
    ////////////
    //SOLO CURVA
    private int contador_curva = 0;
    public boolean escurva = false;
    
    // GET'S Y SET'S
    
    /**
     * Método para obtener el conteo de pulsaciones para curva.
     * @return contador_curva. 
     */
    public int getCont() {
        return contador_curva;
    }
    
    /**
     * Método para modificar el conteo de pulsaciones para curva.
     * @param cont. 
     */
    public void setCont(int cont) {
        this.contador_curva = cont;
    }
    
    /**
     * Método para obtener la forma.
     * @return forma. 
     */
    public Formas getForma() {
        return forma;
    }

    /**
     * Método para modificar la forma.
     * @param forma. 
     */
    public void setForma(Formas forma) {
        this.forma = forma;
    }

    /**
     * Método para el punto inicial.
     * @return p_inicial. 
     */
    public Point getPunto() {
        return p_inicial;
    }
    
    /**
     * Método para modificar el punto inicial.
     * @param punto. 
     */
    public void setPunto(Point punto) {
        this.p_inicial = punto;
    }
    
    /**
     * Método para obtener el punto final.
     * @return p_final. 
     */
    public Point getPunto1() {
        return p_final;
    }

    /**
     * Método para modificar el punto final.
     * @param punto. 
     */
    public void setPunto1(Point punto) {
        this.p_final = punto;
    }
    
    /**
     * Método para obtener el punto auxiliar.
     * @return pAux. 
     */
    public Point getpAux() {
        return pAux;
    }

    /**
     * Método para modificar el punto auxiliar.
     * @param pAux. 
     */
    public void setpAux(Point pAux) {
        this.pAux = pAux;
    }

    /**
     * Método para obtener el color.
     * @return color. 
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Método para modificar el color.
     * @param color. 
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Método para obtener el trazo.
     * @return trazo. 
     */
    public Stroke getTrazo() {
        return trazo;
    }

    /**
     * Método para modificar el trazo.
     * @param trazo. 
     */
    public void setTrazo(Stroke trazo) {
        this.trazo = trazo;
    }
    
    /**
     * Método para obtener el render.
     * @return render. 
     */
    public RenderingHints getAlisado() {
        return render;
    }

    /**
     * Método para modificar el render.
     * @param render. 
     */
    public void setAlisado(RenderingHints render) {
        this.render = render;
    }

    /**
     * Método para obtener el composite.
     * @return comp. 
     */
    public Composite getComp() {
        return comp;
    }

    /**
     * Método para modificar el composite.
     * @param comp. 
     */
    public void setComp(Composite comp) {
        this.comp = comp;
    }

    /**
     * Método para obtener si está alisado o no.
     * @return alisado. 
     */
    public boolean isAlisado() {
        return alisado;
    }
    
    /**
     * Método para modificar el renderizado
     * aplicando OFF o ON en según que caso.
     * @param renderizar. 
     */
    public void setAlisado(boolean renderizar) {
        this.alisado_noedit= renderizar;
        if(!alisado_noedit){
            System.out.println("NO ALISADO");
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }else{
            System.out.println("ALISADO");
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    /**
     * Método para obtener si está en modo editar o no.
     * @return editar.
     */
    public boolean isEditar() {
        return editar;
    }
    
    /**
     * Método para modificar el modo de edición.
     * @param editar. 
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * Método para obtener la transparencia.
     * @return transparente. 
     */
    public boolean isTransparente() {
        return transparente;
    }
    
    /**
     * Método para modificar la transparencia
     * aplicando 1.0f o 0.4f según el caso. 
     * @param transparente. 
     */
    public void setTransparente(boolean transparente) {
        this.transparente_noedit = transparente;
        if(!transparente_noedit){
            System.out.println("NO TRANSPARENCIA");
            comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        }else{
            System.out.println("TRANSPARENCIA");
            comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        }
    }

    /**
     * Método para obtener si existe o no relleno.
     * @return relleno. 
     */
    public boolean isRelleno() {
        return relleno;
    }
    
    /**
     * Método para modificar el relleno.
     * @param relleno. 
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
    
    // CONSTRUCTOR
    /**
     * Constructor de la clase LIENZO2D.
     */
    public Lienzo2D() {
        this.geomshapes = new ArrayList<>();
        this.color = Color.BLACK;
        this.forma = Formas.PUNTO;
        this.trazo = new BasicStroke(1.0f);
        initComponents();
    }
    
    // MÉTODO PAINT
    /**
     * Método para pintar la forma seleccionada con sus atributos correspondientes
     * y de la forma solicitada en el guión.
     * @param g. 
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        geomshapes.forEach((GeomShape f) -> {
            f.drawShape(g2d);
        });
    }
    
    // MÉTODO CREATESHAPE
    /**
     * Método para crear las figuras selecciondas en la interfaz con los parametros correspondientes.
     * La figura creada se añade a la lista de figuras. 
     */
    public void createShape(){
        
        switch(forma){
        
            case PUNTO: 
                point = new Pointe(p_inicial,color,trazo,transparente,alisado,comp,render);
                geomshapes.add(point);
                break;
            case LINEA: 
                line = new Line(p_inicial,p_inicial,color,trazo,transparente,alisado,comp,render);
                geomshapes.add(line);
                break;
            case RECTANGULO:
                rectangle = new Rectan(color,trazo,transparente,alisado,comp,render,relleno);
                geomshapes.add(rectangle); 
                break;
            case ELIPSE:
                ellipse = new Ellipse(color,trazo,transparente,alisado,comp,render,relleno);
                geomshapes.add(ellipse);
                break;
            case ROTODOS:
                roto = new FormaPersonal(p_inicial,p_inicial,color,trazo,transparente,alisado,comp,render);
                geomshapes.add(roto);
                break;
            case CURVA:
                escurva = true;
                System.out.println(escurva);
                curv = new Curve(p_inicial,p_inicial,color,trazo,transparente,alisado,comp,render);
                geomshapes.add(curv);
                break;
            case GP:
                gp = new GPath(p_inicial,p_inicial,color,trazo,transparente,alisado,comp,render);
                geomshapes.add(gp);
                break;
        }
    }
    
    // MÉTODO UPDATESHAPE
    /**
     * Método para actualizar la figura en función del movimiento del ratón y 
     * de la figura creada anteriormente.
     */
    public void updateShape(){
        
        switch(forma){
        
            case PUNTO: 
                point.updateDeShape(p_inicial, p_final, null);
                break;
            case LINEA: 
                line.updateDeShape(p_inicial, p_final, null);
                break;
            case RECTANGULO:
                rectangle.updateDeShape(p_inicial, p_final, null);
                break;
            case ELIPSE:
                ellipse.updateDeShape(p_inicial, p_final, null);
                break;
            case ROTODOS:
                roto.updateDeShape(p_inicial, p_final, null);
                break;
            case CURVA:
                curv.updateDeShape(p1, p2, p3);
                break;
            case GP:
                gp.updateDeShape(p_inicial, p_final, null);
                break;
        }
    }
    
    // MÉTODO GETSELECTEDSHAPE
    /**
     * Método que recorre la lista de figuras y comprueba cuál ha sido seleccionada, 
     * guardando en una variable la figura concreta para operaciones posteriores. 
     * @param p. 
     * @return selected.
     */
    public GeomShape getSelectedShape(Point2D p){
        
        GeomShape selected = null;
        
        for(GeomShape s:geomshapes)
            
            if(s.getContenidoEnShape(p)){
                position_selected = geomshapes.indexOf(s);
                System.err.println(position_selected);
                fg = s;  
                
                if(fg instanceof Line){
                    line = (Line) fg;
                    selected = line;
                    
                }
                if(fg instanceof Rectan){
                    rectangle = (Rectan) fg;
                    selected = rectangle;
                    
                }
                if(fg instanceof Ellipse){
                    ellipse = (Ellipse) fg;
                    selected = ellipse;
                    
                }    
                if(fg instanceof Pointe){
                    point = (Pointe) fg;    
                    selected = point;
                    
                }
                if(fg instanceof FormaPersonal){
                    roto = (FormaPersonal) fg;    
                    selected = roto;
                    
                }
                if(fg instanceof Curve){
                    curv = (Curve) fg;    
                    selected = curv;
                    
                }
                if(fg instanceof GPath){
                    gp = (GPath) fg;    
                    selected = gp;
                    
                }
            } 
        return selected;
    }
    
    // MÉTODO SETLOCATION
    /**
     * Método para modificar la posición de la figura en función del ratón. Se aplica la función setLocationShape
     * de cada clase.
     * @param pos. 
     */
    @Override
    public void setLocation(Point pos) {
        
        if (fg instanceof Line){
            line.setLocationShape(pos, null,null);
        }
        
        if (fg instanceof Rectan){
            rectangle.setLocationShape(pos,null,null);
        }
        
        if (fg instanceof Ellipse){
            ellipse.setLocationShape(pos, null,null);
        }
        
        if (fg instanceof Pointe){
            point.setLocationShape(pos, null,null);
        }
        
        if (fg instanceof FormaPersonal){
            roto.setLocationShape(pos, null,null);
        }
        
        if (fg instanceof Curve){
            curv.setLocationShape(pos,p2,p3);
        }
        
        if (fg instanceof GPath){
            gp.setLocationShape(pos, null,null);
        }
    }
    
    // MÉTODOS DE POSICIÓN DE PRIORIDAD EN EL LIENZO
    /**
     * Método para subir una posición en el lienzo la figura. 
     */
    public void Up() {
        Collections.swap(geomshapes, position_selected, position_selected+1);
    }
    /**
     * Método para subir a la primera posición en el lienzo la figura. 
     */
    public void UpAll() {
        Collections.swap(geomshapes, position_selected, geomshapes.size()-1);
    }
    /**
     * Método para bajar una posición en el lienzo la figura. 
     */
    public void Down() {
        Collections.swap(geomshapes, position_selected, position_selected-1);
    }
    /**
     * Método para bajar a la ultima posición en el lienzo la figura. 
     */
    public void DownAll() {
        Collections.swap(geomshapes, position_selected, 0);
    }
    
    // MÉTODOS DE EDICIÓN EN MODO EDITAR
    /**
     * Método para modificar el color en modo edición.
     * @param color. 
     */
    public void setColorEdit(Color color){
        select.color = color;
    }
    /**
     * Método para modificar si está en modo relleno o no en edición.
     * @param relleno. 
     */
    public void setRellenoEdit(boolean relleno) {
       ((FilledGeomShape)select).relleno = relleno;
    }
    /**
     * Método para modificar el grosor en modo edición.
     * @param trazo. 
     */
    public void setGrosorEdit(Stroke trazo) {
        select.trazo = trazo;
    }
    /**
     * Método para modificar la transparencia en modo edición.
     * @param transparente. 
     */
    public void setTransparenciaEdit(boolean transparente) {
        select.transparente = transparente;
        if(!transparente){
            System.out.println("NO TRANSPARENCIA (EDITAR)");
            select.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        }else{
            System.out.println("NO TRANSPARENCIA (EDITAR)");
            select.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        }
    }  
    /**
     * Método para modificar el alisado en modo edición
     * @param alisado. 
     */
    public void setAlisadoEdit(boolean alisado) {
        select.alisado = alisado;
        if(!alisado){
            System.out.println("NO ALISADO");
            select.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }else{
            System.out.println("ALISADO");
            select.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        p_final= new Point(evt.getPoint());
       
        if(!editar){
             if(escurva){
                if (contador_curva == 0){
                    p2 = evt.getPoint();
                    updateShape();
                }else{
                    p3 = evt.getPoint();
                    updateShape();
                }
            }else{
              this.updateShape();
             }
        }else{
            p2 = evt.getPoint();
            this.setLocation(p_final);
        }
        this.repaint();
        
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
       p_inicial = evt.getPoint();
       p_final = p_inicial;
       p3 = p1;
       
       if(!editar){
           if(escurva){
                if(contador_curva == 0){
                    p1 = evt.getPoint();
                    createShape();
                    this.setLocation(p_inicial);
                }else{
                    p3 = evt.getPoint();
                    updateShape();
                }
           }else{
                 p1 = evt.getPoint();
                 p_inicial = evt.getPoint();
                this.createShape();   
           }
           
           geomshapes.forEach((s) -> {
               s.drawBordeEditar(false);
           });
       }else{
           select = this.getSelectedShape(evt.getPoint()); 
           select.drawBordeEditar(editar);
       }
       this.repaint();      
   
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
         if(contador_curva == 1) 
              contador_curva = 0;
         else
              contador_curva = 1;   
         
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
