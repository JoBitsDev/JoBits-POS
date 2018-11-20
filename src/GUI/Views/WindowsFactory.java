/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views;

import java.awt.Window;
import java.util.List;
import java.util.Stack;
import javax.swing.JDialog;
import javax.swing.JFrame;


/**
 * FirstDream
 * @author Jorge
 * 
 */
public class WindowsFactory {

    private static Stack<Window> currentActiveWindows = new Stack<>();
    
 
    public static Window getWindow(ViewType type){
         switch(type){
            case DIALOG: 
                JDialog currentDialog = new JDialog(currentActiveWindows.peek());
                currentActiveWindows.add(currentDialog);
                return currentDialog;
            case FRAME: 
                JFrame currentFrame = new JFrame();
                currentActiveWindows.add(currentFrame);
                return currentFrame;
            default: return null;
        }
    }
   
    public enum ViewType{
        DIALOG,FRAME,LIST,DETAIL
    }
}
