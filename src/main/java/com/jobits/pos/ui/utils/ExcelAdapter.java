/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;

/**
 *
 * @author Home
 *
 */
public class ExcelAdapter implements ActionListener {

    private Clipboard clipboard;
    private StringSelection stsel;
    private JTable jTable1;

    /**
     *
     * @param tablaObjetivo
     */
    public ExcelAdapter(JTable tablaObjetivo) {
        jTable1 = tablaObjetivo;
        final KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK, false);
        jTable1.registerKeyboardAction(this, "Copy", copy, JComponent.WHEN_FOCUSED);
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    /**
     *
     * @return
     */
    public JTable getJTable() {
        return jTable1;
    }

    public void setJTable(JTable jTable1) {
        this.jTable1 = jTable1;
    }

    /**
     * Este metodo se ejecuta cuando se presiona ctrl + c y la tabla de la cual
     * se deseea copiar los elementos posee el focus.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Copy")) {
            StringBuilder sbf = new StringBuilder();
            //Se cuantifican la cantidad de filas y columnas de la tabla
            int numcols = jTable1.getColumnCount();
            int numrows = jTable1.getRowCount();
            //se obtienen los nombres de las columnas
            for (int i = 0; i < numcols; i++) {
                String columnName = jTable1.getColumnName(i);
                sbf.append(columnName);
                sbf.append('\t');
            }
            sbf.append('\n');
            //Se copian los datos de cada celda
            for (int i = 0; i < numrows; i++) {
                for (int j = 0; j < numcols; j++) {
                    String value = jTable1.getValueAt(i, j).toString();
                    int valueLastIndex = value.length() - 1;
                    if (value.charAt(0) == ' ') {
                        value = value.substring(1, valueLastIndex);
                    }
                    if (value.charAt(valueLastIndex) == ' ') {
                        value = value.substring(0, valueLastIndex - 1);
                    }
                    sbf.append(value);
                    sbf.append('\t');
                }
                sbf.append('\n');
            }
            //Se suben los datos obtenidos al clipboard del sistema
            stsel = new StringSelection(sbf.toString());
            clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stsel, stsel);
        }
    }

}
