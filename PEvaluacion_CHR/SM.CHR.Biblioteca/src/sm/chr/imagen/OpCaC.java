/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sm.chr.imagen;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Clase OpCaC donde aplica un filtro a la imagen a partir de una operación
 * componente a componente. 
 * @author Chris
 */

public class OpCaC extends sm.image.BufferedImageOpAdapter{
    
    //ATRIBUTOS ALPHA Y COLORES
    float alpha;
    float color_1[], color_2[], color_3[];
    
    // CONSTRUCTOR
    /**
    * Clase de la clase OpCaC.
    * @param alpha.
    * @param color1.
    */
    public OpCaC (float alpha, Color color1) {
        this.alpha = alpha;
        this.color_1 = color1.getColorComponents(null);
        this.color_2 = (Color.black).getColorComponents(null);
        this.color_3 = (Color.black).getColorComponents(null);
        for(int i=0; i< this.color_1.length; i++) this.color_1[i] *=255;
        for(int i=0; i< this.color_2.length; i++) this.color_2[i] *=255;
        for(int i=0; i< this.color_3.length; i++) this.color_3[i] *=255;
    }
    
    // MÉTODO PIXEL A PIXEL PERSONAL
    /**
    * Método que trabaja sobre una imagen componente a componente, aplicando una operación personal
    * sobre distintas bandas.
    * @param src
    * @param dest
    * @return dest
    */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest){
        
        if (dest == null) 
            dest = createCompatibleDestImage(src, null);

        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        System.err.println(src.getRaster().getNumBands());
        
         for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                for (int band = 0; band < src.getRaster().getNumBands(); band++) {
                     int sample = srcRaster.getSample(x, y, band);
                        if (src.getRaster().getNumBands() == 3){
                            switch (band) {
                                case 0:
                                    sample = (int) ( alpha * color_1[band] + (2f + alpha)* sample );
                                break;
                                case 1:
                                    sample = (int) ( alpha * color_2[band] - (1f + alpha)* sample );
                                break;
                                case 2:
                                    sample = (int) ( alpha * color_3[band] - (1f + alpha)* sample );
                                break;
                            }   
                        }else{
                            switch (band % 2) {
                                case 0:
                                    sample = (int) ( alpha * color_1[band] + (0.5f + alpha)* sample );
                                break;
                                case 1:
                                    sample = (int) ( alpha * color_3[band] + (0.5f + alpha)* sample );
                                break;
                            }      
                        }
                destRaster.setSample(x, y, band, sample);
                }
            }
         }
            return dest;
    }
}
