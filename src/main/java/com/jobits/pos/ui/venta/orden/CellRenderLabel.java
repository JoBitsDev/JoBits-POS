/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden;

import com.jobits.pos.controller.imagemanager.ImageManagerService;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.utils.IconFinder;
import java.awt.Dimension;

/**
 *
 * @author Jorge
 */
public class CellRenderLabel extends javax.swing.JPanel {

    ImageManagerService imageService = PosDesktopUiModule.getInstance().getImplementation(ImageManagerService.class);

    /**
     * Creates new form CellRenderLabel
     *
     * @param nombre
     * @param precio
     * @param selected
     * @param rutaImagenProducto
     */
    public CellRenderLabel(String nombre, String precio, boolean selected, String rutaImagenProducto) {
        initComponents();
        jTextArea1.setText(nombre);
        jTextArea1.setBackground(DefaultValues.TRANSPARENT);
        jLabelPrecio.setText(precio);
        setOpaque(selected);
        if (precio == null) {
            jLabelIcon.setIcon(new IconFinder().setIcon(selected, nombre));
        } else {
            jLabelIcon.setIcon(imageService.loadImageIcon(rutaImagenProducto, new Dimension(50, 50)));//Hacer la dimension generica
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelPrecio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(DefaultValues.PRIMARY_COLOR);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setMaximumSize(new java.awt.Dimension(170, 120));
        setMinimumSize(new java.awt.Dimension(170, 90));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(170, 90));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(new javax.swing.border.LineBorder(DefaultValues.SECONDARY_COLOR, 1, true));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabelIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIcon.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.add(jLabelIcon, java.awt.BorderLayout.PAGE_START);

        jLabelPrecio.setFont(new java.awt.Font(".SF NS Text", 1, 14)); // NOI18N
        jLabelPrecio.setForeground(DefaultValues.PRIMARY_COLOR_DARK);
        jLabelPrecio.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelPrecio.setText("Precio");
        jLabelPrecio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jPanel1.add(jLabelPrecio, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(18);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(3);
        jTextArea1.setToolTipText("");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setMaximumSize(new java.awt.Dimension(138, 2147483647));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
