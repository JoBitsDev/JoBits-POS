/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ButtonList extends JButton{

    String text;
    
    public ButtonList(String text) {
        super(text);
        
    }

    public ButtonList(Action a) {
        super(a);
    }

    
    
}

class ActionListenerPers implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
    
    
    }
    
}
