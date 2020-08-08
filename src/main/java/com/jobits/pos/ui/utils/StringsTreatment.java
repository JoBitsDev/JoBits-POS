/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

/**
 *
 * @author Home
 */
public class StringsTreatment {

    public static String stringFiller(String string) {
        if (string == null) {
            return "";
        }
        String[] parts = string.split("[.]");

        String leftSide = parts[0],
                rightSide = parts[1],
                leftSideInverted = invertString(leftSide);

        StringBuilder aux = new StringBuilder();
        for (int i = 0; i < leftSideInverted.length(); i++) {
            if (i % 3 == 0 && i != 0) {
                aux = aux.append(" ");
                aux = aux.append(leftSideInverted.charAt(i));
            } else {
                aux = aux.append(leftSideInverted.charAt(i));
            }
        }
        leftSide = invertString(aux.toString());
        return leftSide + "." + rightSide;
    }

    public static String invertString(String stringToInvert) {
        String inverted = "";
        for (int i = stringToInvert.length() - 1; i >= 0; i--) {
            inverted += stringToInvert.charAt(i);
        }
        return inverted;
    }
}
