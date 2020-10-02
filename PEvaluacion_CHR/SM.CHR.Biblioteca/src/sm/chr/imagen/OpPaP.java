/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.imagen;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Clase OpPaP donde aplica un filtro a la imagen a partir de una operación
 * pixel a pixel. 
 * @author Chris
 */

public class OpPaP extends sm.image.BufferedImageOpAdapter{
    
    //CONSTRUCTOR
    /**
    * Clase de la clase OpPaP.
    */
    public OpPaP () {
    }
    
    // MÉTODO COMPONENTE A COMPONENTE PERSONAL
    /**
    * Método que trabaja sobre una imagen pixel a pixel, aplicando una operación personal
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
        
        Color colorPx;
        int M,R,G,B;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                colorPx = new Color(src.getRGB(x, y));
                M = Math.max(Math.max(colorPx.getRed(), colorPx.getGreen()), colorPx.getBlue());
                if(colorPx.getRed() < M) R = 0;
                    else R = colorPx.getRed();
                if(colorPx.getGreen() < M) G = 0;
                    else G = colorPx.getGreen();
                if(colorPx.getBlue() < M) B = 0;
                    else B = colorPx.getBlue();
                colorPx = new Color(R,G,B);
                dest.setRGB(x,y,colorPx.getRGB());
            }
        }
            return dest;
    }
}

