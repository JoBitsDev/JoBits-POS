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

    public static String stringFiller(String string, int splitIndex, int dotPosition) {
        if (string == null) {
            return "";
        }
        if (string.length() > splitIndex + 1) {
            if (dotPosition != -1) {
                String a = String.valueOf(string.charAt(string.length() - dotPosition));
                if (a.equals(".")) {
                    splitIndex++;
                }
            }
            StringBuilder aux = new StringBuilder();
            String rightSide = string.substring(string.length() - splitIndex),
                    leftSide = string.substring(0, string.length() - splitIndex),
                    leftSideInverted = invertString(leftSide);

            for (int i = 0; i < leftSideInverted.length(); i++) {
                if (i % 3 == 0) {
                    aux = aux.append(" ");
                    aux = aux.append(leftSideInverted.charAt(i));
                } else {
                    aux = aux.append(leftSideInverted.charAt(i));
                }
            }
            leftSide = invertString(aux.toString());
            return leftSide + rightSide;
        }

        return string;
    }

    public static String invertString(String stringToInvert) {
        String inverted = "";
        for (int i = stringToInvert.length() - 1; i >= 0; i--) {
            inverted += stringToInvert.charAt(i);
        }
        return inverted;
    }
}
