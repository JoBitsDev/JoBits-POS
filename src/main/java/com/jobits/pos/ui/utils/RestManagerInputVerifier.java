/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.utils;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import com.jobits.pos.recursos.RegularExpressions;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class RestManagerInputVerifier extends InputVerifier{
        private final String regex ;
               

        public RestManagerInputVerifier() {
           this.regex  = RegularExpressions.NOT_EMPTY;
        }

        public RestManagerInputVerifier(String regex) {
            this.regex = regex;
        }

        @Override
        public boolean verify(JComponent input) {
            javax.swing.JTextField in = (javax.swing.JTextField) input;
            boolean invalid;
            String validName = in.getText();

            invalid = !validName.matches(regex) || validName.length() > 100;

            if (invalid) {
                in.setBorder(new LineBorder(Color.red));
            } else {
                in.setBorder(null);
            }

            return !invalid;
        }

    
}
