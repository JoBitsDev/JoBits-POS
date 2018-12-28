/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views.util;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class RestManagerInputVerifier extends InputVerifier{
        private String regex = "[a-zA-Z][[a-zA-Z0-9\\p{IsLatin}]* X*[\\s]]*";

        public RestManagerInputVerifier() {
            super();
        }

        public RestManagerInputVerifier(String regex) {
            this.regex = regex;
        }

        @Override
        public boolean verify(JComponent input) {
            javax.swing.JTextField in = (javax.swing.JTextField) input;
            boolean invalid;
            String validName = in.getText();

            invalid = !validName.matches(regex) || validName.length() > 60;

            if (invalid) {
                in.setBorder(new LineBorder(Color.red));
            } else {
                in.setBorder(null);
            }

            return !invalid;
        }

    
}
