/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.imagemanager;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Home
 */
public interface ImageManagerService {

    public String getImageName();

    public BufferedImage openNewImage(Dimension imagContainerDimensions);

    public BufferedImage recortar_imagen(Image img, float x, float y, float ancho, float alto);

    public void guardar_imagen(BufferedImage Imagmemoria);

}
