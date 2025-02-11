/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe_christian;
import java.awt.image.BufferedImage;
import java.io.File;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author Chris
 */

public class VentanaInternaVLCPlayer extends javax.swing.JInternalFrame {
    
    private EmbeddedMediaPlayer vlcplayer = null;
    private File fMedia;
    
    /**
     * Creates new form VentanaInternaVLCPlayer
     */
    
    VentanaInternaVLCPlayer (File f) {
        initComponents();
        fMedia = f;
        EmbeddedMediaPlayerComponent aVisual = new EmbeddedMediaPlayerComponent();
        getContentPane().add(aVisual,java.awt.BorderLayout.CENTER);
        vlcplayer = aVisual.getMediaPlayer();
        vlcplayer.addMediaPlayerEventListener(new VideoListener());
    }
    
    public static VentanaInternaVLCPlayer getInstance(File f){
        VentanaInternaVLCPlayer v = new VentanaInternaVLCPlayer(f);
        return (v.vlcplayer!=null?v:null);
    }
    
    public void play() {
        if (vlcplayer != null) {
            if(vlcplayer.isPlayable()){
                //Si se estaba reproduciendo
                vlcplayer.play();
            } else {
                vlcplayer.playMedia(fMedia.getAbsolutePath());
            }
        }
    }
    
    public void stop() {
        if (vlcplayer != null) {
            if (vlcplayer.isPlaying()) {
                vlcplayer.pause();
            } else {
                vlcplayer.stop();
            }
        }
    }
    
    private class VideoListener extends MediaPlayerEventAdapter {
    @Override
        public void finished(MediaPlayer mediaPlayer) {
        botonStop.setSelected(true); 
        }
    }
    
    public BufferedImage capturaVideo(){
        BufferedImage img = vlcplayer.getSnapshot();
        return img;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotones = new javax.swing.ButtonGroup();
        PanelReproductor = new javax.swing.JPanel();
        botonPlay = new javax.swing.JButton();
        botonStop = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(800, 400));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        PanelReproductor.setLayout(new java.awt.GridLayout(1, 0));

        botonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_dibujos/Play.png"))); // NOI18N
        botonPlay.setMaximumSize(new java.awt.Dimension(90, 30));
        botonPlay.setMinimumSize(new java.awt.Dimension(90, 30));
        botonPlay.setPreferredSize(new java.awt.Dimension(90, 30));
        botonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPlayActionPerformed(evt);
            }
        });
        PanelReproductor.add(botonPlay);

        botonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_dibujos/Stop.png"))); // NOI18N
        botonStop.setMaximumSize(new java.awt.Dimension(90, 30));
        botonStop.setMinimumSize(new java.awt.Dimension(90, 30));
        botonStop.setPreferredSize(new java.awt.Dimension(90, 30));
        botonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonStopActionPerformed(evt);
            }
        });
        PanelReproductor.add(botonStop);

        getContentPane().add(PanelReproductor, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        vlcplayer.stop();
        vlcplayer=null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void botonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPlayActionPerformed
        // TODO add your handling code here:
        play();
    }//GEN-LAST:event_botonPlayActionPerformed

    private void botonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonStopActionPerformed
        // TODO add your handling code here:
        stop();
    }//GEN-LAST:event_botonStopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelReproductor;
    private javax.swing.JButton botonPlay;
    private javax.swing.JButton botonStop;
    private javax.swing.ButtonGroup grupoBotones;
    // End of variables declaration//GEN-END:variables
}
