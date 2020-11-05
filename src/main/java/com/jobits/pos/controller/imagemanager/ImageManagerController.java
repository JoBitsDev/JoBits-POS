/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.imagemanager;

import com.jobits.pos.logs.RestManagerHandler;
import com.jobits.pos.recursos.R;
import static com.jobits.pos.ui.utils.ImageManager.resizeImage;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Home
 */
public class ImageManagerController implements ImageManagerService{

    String imageName;

    public ImageManagerController(String imageName) {
        this.imageName = imageName;
    }

    /**
     *
     * @return codigo del producto y con el cual se guardara la imagen del mismo
     */
    @Override
    public String getImageName() {
        return imageName;
    }

    /**
     * Abre una imagen seleccionada en el selector de archivo
     * @param imagContainerDimensions dimension a la que se reajustara la imagen seleccionada
     * @return devuelve una imagen seleccionada en el equipo
     */
    @Override
    public BufferedImage openNewImage(Dimension imagContainerDimensions) {
        BufferedImage image = null;
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "png"));
        int resultado = selector.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                image = ImageIO.read(selector.getSelectedFile());
                int xImageViewer = (int) imagContainerDimensions.getWidth();
                int yImageViewer = (int) imagContainerDimensions.getHeight();
                if (image.getWidth() > xImageViewer && image.getHeight() > yImageViewer) {
                    image = resizeImage(image, xImageViewer, yImageViewer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    /**
     * Recortar una imagen 
     * @param img imagen a recortar
     * @param x
     * @param y
     * @param ancho
     * @param alto
     * @return devuelve la imagen original recortada
     */
    @Override
    public BufferedImage recortar_imagen(Image img, float x, float y, float ancho, float alto) {
        BufferedImage Imagmemoria = null;
        if (x == 0 && y == 0 && ancho == 0 && alto == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el arrea a recortar primero");
        } else {
            Imagmemoria = ((BufferedImage) img).getSubimage((int) x, (int) y, (int) ancho, (int) alto);
        }
        return Imagmemoria;
    }

    /**
     * Guarda una imagen en la carpeta Media
     * @param Imagmemoria imagen a guardar
     */
    @Override
    public void guardar_imagen(BufferedImage Imagmemoria) {
        try {
            File mediaFolder = new File(R.MEDIA_FILE_PATH);
            if (!mediaFolder.exists()) {
                mediaFolder.mkdirs();
            }
            String path = R.MEDIA_FILE_PATH + imageName + ".jpg";
            File mediaFile = new File(path);
            if (!mediaFile.exists()) {
                mediaFile.createNewFile();
            }
            if (mediaFile.exists()) {
                mediaFile.delete();
            }
            if (Imagmemoria != null) {
                ImageIO.write(Imagmemoria, "jpg", mediaFile);
                JOptionPane.showMessageDialog(null, "Se ha guardado Correctamente la imagen recortada");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una imagen primero");
            }

        } catch (IOException ex) {
            Logger.getLogger(RestManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private BufferedImage resizeImage(BufferedImage originalImage, int boundWidth, int boundHeight) {
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
        float percentReduction = 1.00f;
        if (imageWidth > imageHeight) {
            while (imageWidth > boundWidth) {
                imageWidth *= percentReduction;
                imageHeight *= percentReduction;
                percentReduction -= 0.01f;
            }
        } else if (imageWidth < imageHeight) {
            while (imageHeight > boundHeight) {
                imageWidth *= percentReduction;
                imageHeight *= percentReduction;
                percentReduction -= 0.01f;
            }
        }
        BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public static ImageIcon loadImageIcon(String imagePath, Dimension d) {
        ImageIcon icono = new ImageIcon(R.DEFAULT_IMAGE_PRODUCT);
        if (imagePath != null) {
            try {
                File f = new File(imagePath);
                if (f.exists()) {
                    BufferedImage bi = ImageIO.read(f);
                    Image img = bi.getScaledInstance((int) d.getWidth(), (int) d.getHeight(), Image.SCALE_SMOOTH);
                    icono = new ImageIcon(img);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return icono;
    }

//    public static BufferedImage toBufferedImage(Image img) {
//        if (img instanceof BufferedImage) {
//            return (BufferedImage) img;
//        }
//        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//        Graphics2D bGr = bimage.createGraphics();
//        bGr.drawImage(img, 0, 0, null);
//        bGr.dispose();
//        return bimage;
//    }
//
//    public static Image toImage(BufferedImage bimag) {
//        return bimag.getScaledInstance((int) bimag.getWidth(), (int) bimag.getHeight(), Image.SCALE_SMOOTH);
//    }
    }
