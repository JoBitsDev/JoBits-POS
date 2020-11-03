/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import javax.swing.ImageIcon;

/**
 *
 * @author Home
 */
public class IconFinder {

    String ICONS_RESOURCE_PATH = "/restManager/resources/food & drinks/";

    public IconFinder() {
    }

    public ImageIcon setIcon(boolean colored, String keyWord) {
        String nombre = keyWord.toLowerCase();
        if (colored) {
            if (nombre.contains("coctel") || nombre.contains("cocktail")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "cocktail" + "_color.png"));
            } else if (nombre.contains("jugo")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "jugo" + "_color.png"));
            } else if (nombre.contains("cerveza")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "beer" + "_color.png"));
            } else if (nombre.contains("bebida")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "bebida" + "_color.png"));
            } else if (nombre.contains("wiskey") || nombre.contains("whiskey")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "whiskey" + "_color.png"));
            } else if (nombre.contains("vino")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "vino" + "_color.png"));
            } else if (nombre.contains("entrante")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "entrante" + "_color.png"));
            } else if (nombre.contains("postre") || nombre.contains("dulce")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "postre" + "_color.png"));
            } else if (nombre.contains("huevo")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "huevo" + "_color.png"));
            } else if (nombre.contains("pizza")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "pizza" + "_color.png"));
            } else if (nombre.contains("carne") || nombre.contains("res ")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "carne" + "_color.png"));
            } else if (nombre.contains("pollo")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "pollo" + "_color.png"));
            } else if (nombre.contains("hamburguesa") || nombre.contains("pan")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "burger" + "_color.png"));
            } else if (nombre.contains("pescado") || nombre.contains("marisco")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "pescado" + "_color.png"));
            } else if (nombre.contains("entremes") || nombre.contains("plato")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "plato" + "_color.png"));
            } else {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "varios" + "_color.png"));
            }
        } else {
            if (nombre.contains("coctel") || nombre.contains("cocktail")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "cocktail" + "_gray.png"));
            } else if (nombre.contains("jugo")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "jugo" + "_gray.png"));
            } else if (nombre.contains("cerveza")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "beer" + "_gray.png"));
            } else if (nombre.contains("bebida")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "bebida" + "_gray.png"));
            } else if (nombre.contains("wiskey") || nombre.contains("whiskey")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "whiskey" + "_gray.png"));
            } else if (nombre.contains("vino")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "vino" + "_gray.png"));
            } else if (nombre.contains("entrante")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "entrante" + "_gray.png"));
            } else if (nombre.contains("postre") || nombre.contains("dulce")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "postre" + "_gray.png"));
            } else if (nombre.contains("huevo")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "huevo" + "_gray.png"));
            } else if (nombre.contains("pizza")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "pizza" + "_gray.png"));
            } else if (nombre.contains("carne") || nombre.contains("res ")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "carne" + "_gray.png"));
            } else if (nombre.contains("pollo")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "pollo" + "_gray.png"));
            } else if (nombre.contains("hamburguesa") || nombre.contains("pan")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "burger" + "_gray.png"));
            } else if (nombre.contains("pescado") || nombre.contains("marisco")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "pescado" + "_gray.png"));
            } else if (nombre.contains("entremes") || nombre.contains("plato")) {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "plato" + "_gray.png"));
            } else {
                return new ImageIcon(getClass().getResource(ICONS_RESOURCE_PATH + "varios" + "_gray.png"));
            }
        }
    }
}
