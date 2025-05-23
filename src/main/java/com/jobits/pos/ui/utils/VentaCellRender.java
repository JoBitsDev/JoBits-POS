/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import com.jobits.pos.utils.utils;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.jobits.pos.core.domain.VentaDAO1;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class VentaCellRender extends javax.swing.JPanel implements TableCellRenderer {

    DayReviewWrapper<ResumenVentaEstadisticas> v;
    final String RETORNO_CARRO = "\n";

    /**
     * Creates new form VentaCellRender
     *
     */
    public VentaCellRender() {
        setBackground(DefaultValues.TRANSPARENT);
    }

    private VentaCellRender(DayReviewWrapper<ResumenVentaEstadisticas> v) {
        if (v != null) {
            this.v = v;
            initComponents();

            var horaPico = VentaDAO1.getModalPickHourEstadisticas(v.getLista_contenida());

            var costoVenta = 0f;
            var ventaTotal = 0f;

            for (ResumenVentaEstadisticas r : v.getLista_contenida()) {
                costoVenta += r.getTotalCostoVenta();
                ventaTotal += r.getTotalVendido();
            }

            jLabelGastos.setText(costoVenta != 0
                    ? com.jobits.pos.utils.utils.setDosLugaresDecimales(costoVenta)
                    : "-");
            jLabelVentas.setText(ventaTotal != 0
                    ? com.jobits.pos.utils.utils.setDosLugaresDecimales(ventaTotal)
                    : "-");
            jLabelHoraPico.setText(LocalTime.of(horaPico, 0).toString());

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

        jPanel1 = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jLabelGastos = new javax.swing.JLabel();
        jLabelVentas = new javax.swing.JLabel();
        jLabelHoraPico = new javax.swing.JLabel();

        setToolTipText(CreateToolTip(v));
        setMinimumSize(new java.awt.Dimension(165, 100));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(165, 100));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 10, 15), " "+v.getFecha().getDayOfMonth()));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabelGastos.setBackground(new java.awt.Color(255, 255, 255));
        jLabelGastos.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabelGastos.setForeground(new java.awt.Color(102, 0, 0));
        jLabelGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pulgares-abajo.png"))); // NOI18N
        jLabelGastos.setToolTipText("Gastos");
        jPanel1.add(jLabelGastos, java.awt.BorderLayout.CENTER);

        jLabelVentas.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabelVentas.setForeground(new java.awt.Color(0, 102, 51));
        jLabelVentas.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pulgar-arriba.png"))); // NOI18N
        jLabelVentas.setToolTipText("Ventas");
        jLabelVentas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabelVentas, java.awt.BorderLayout.PAGE_START);

        jLabelHoraPico.setFont(new java.awt.Font("Lucida Grande", 2, 10)); // NOI18N
        jLabelHoraPico.setForeground(new java.awt.Color(0, 0, 255));
        jLabelHoraPico.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelHoraPico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/hora_pico.png"))); // NOI18N
        jLabelHoraPico.setToolTipText("Hora Pico");
        jPanel1.add(jLabelHoraPico, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
//        System.out.println("selected");        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelGastos;
    private javax.swing.JLabel jLabelHoraPico;
    private javax.swing.JLabel jLabelVentas;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            VentaCellRender ret = new VentaCellRender((DayReviewWrapper<ResumenVentaEstadisticas>) value);
            if (isSelected) {
                ret.getjPanel1().setBackground(table.getSelectionBackground());
            } else {
                ret.getjPanel1().setBackground(table.getBackground());
            }

            return ret;
        }
        return new VentaCellRender();
    }

    public DayReviewWrapper<ResumenVentaEstadisticas> getV() {
        return v;
    }

    private String CreateToolTip(DayReviewWrapper<ResumenVentaEstadisticas> v) {

        var ventaGastos = 0f;
        var ordenes = 0;
        var costos = 0f;
        for (ResumenVentaEstadisticas r : v.getLista_contenida()) {
            ventaGastos += r.getTotalGastos();
            ordenes += r.getTotalOrdenes();
            costos += r.getTotalCostoVenta();
        }
        String toolTip = "Total ordenes atendidas: " + ordenes;
        toolTip += RETORNO_CARRO;
        if (ventaGastos != 0) {
            toolTip += "Total otros gastos: " + utils.setDosLugaresDecimales(ventaGastos);
        }
        toolTip += RETORNO_CARRO;
        if (costos != 0) {
            toolTip += "Total gastos insumos " + utils.setDosLugaresDecimales(costos);
        }
        toolTip += RETORNO_CARRO;
        return toolTip;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

}
