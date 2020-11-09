/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.imagemanager;

import com.jobits.pos.logs.RestManagerHandler;
import com.jobits.pos.recursos.R;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Home
 */
public class PanelDibujo extends JPanel implements MouseMotionListener, MouseListener {

    Image img;
    BufferedImage Imagmemoria;

    Graphics2D g2D;

    float x = 0;
    float y = 0;
    float ancho = 0;
    float alto = 0;

    public PanelDibujo(BufferedImage f) {
        this.img = f;
        this.setSize(f.getWidth(), f.getHeight());
        this.setVisible(true);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public BufferedImage getImagmemoria() {
        return Imagmemoria;
    }

    public Image getImg() {
        return img;
    }

    public float getXValue() {
        return x;
    }

    public float getYValue() {
        return y;
    }

    public float getAncho() {
        return ancho;
    }

    public float getAlto() {
        return alto;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (img != null) {
            Imagmemoria = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            g2D = Imagmemoria.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);
            g2D.setStroke(new BasicStroke(2f));
            g2D.setColor(Color.WHITE);
            Rectangle2D r2 = new Rectangle2D.Float(x, y, ancho, alto);
            g2D.draw(r2);
            g2.drawImage(Imagmemoria, 0, 0, this);
        }
    }
//
//    public void guardar_imagen(String nombrearchivo) {
//        try {
//            File mediaFolder = new File(R.MEDIA_FILE_PATH);
//            if (!mediaFolder.exists()) {
//                mediaFolder.mkdirs();
//            }
//            String path = R.MEDIA_FILE_PATH + newFileName + ".jpg";
//            File mediaFile = new File(path);
//            if (!mediaFile.exists()) {
//                mediaFile.createNewFile();
//            }
//            if (mediaFile.exists()) {
//                mediaFile.delete();
//            }
//            try {
//                if (Imagmemoria != null) {
//                    ImageIO.write(Imagmemoria, "jpg", mediaFile);
//                    JOptionPane.showMessageDialog(null, "Se ha guardado Correctamente la imagen recortada");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Seleccione una imagen primero");
//                }
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(null, "Error, Trate nuevamente");
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(RestManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public BufferedImage recortar_imagen() {
//        if (x == 0 && y == 0 && ancho == 0 && alto == 0) {
//            JOptionPane.showMessageDialog(null, "Seleccione el arrea a recortar primero");
//        } else {
//            Imagmemoria = ((BufferedImage) img).getSubimage((int) x, (int) y, (int) ancho, (int) alto);
//        }
//        return Imagmemoria;
//    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        ancho = e.getX() - x;
        alto = ancho;
//            alto = e.getY() - y;
        if (ancho < 0) {
            ancho = 0;
        }
        if (alto < 0) {
            alto = 0;
        }
        if (x > this.getWidth()) {
            x = this.getWidth() - ancho;
        }
        if (y > this.getHeight()) {
            y = this.getHeight() - alto;
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

}
