/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.imagen;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Clase SepiaOp donde aplica un filtro sepia a la imagen a partir de una operación
 * pixel a pixel.
 * @author Chris
 */

public class SepiaOp extends sm.image.BufferedImageOpAdapter {
    
    //CONSTRUCTOR
    /**
    * Clase de la clase SepiaOp.
    */
    public SepiaOp () {
    }
    
    // MÉTODO PIXEL A PIXEL SEPIA
    /**
    * Método que trabaja sobre una imagen pixel a pixel, aplicando una operación sepia
    * a cada uno.
    * @param src
    * @param dest
    * @return dest
    */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest){
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                
                Color colorPx = new Color(src.getRGB(x, y));
                int sepiaR = (int) Math.min(255, 0.393*colorPx.getRed() + 0.769*colorPx.getGreen() + 0.189*colorPx.getBlue());
                int sepiaG = (int) Math.min(255, 0.349*colorPx.getRed() + 0.686*colorPx.getGreen() + 0.168*colorPx.getBlue());
                int sepiaB = (int) Math.min(255, 0.272*colorPx.getRed() + 0.534*colorPx.getGreen() + 0.131*colorPx.getBlue());
                colorPx = new Color(sepiaR,sepiaG,sepiaB);
                dest.setRGB(x,y,colorPx.getRGB());
            }
        }
        return dest;
    }
}