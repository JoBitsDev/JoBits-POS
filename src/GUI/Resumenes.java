package GUI;

import java.awt.print.PrinterException;


import java.text.MessageFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
import restManager.resources.R;
import restManager.util.comun;

public class Resumenes extends javax.swing.JPanel{

    private Venta dia;
   // private VentaDAO vDAO;
    private Cocina cocina = null;
    private final Date end;
    private final MessageFormat headerVentas,headerGastos;
    private final List<Cocina> cocinas = staticContent.cocinaJPA.findCocinaEntities();
    private List<Float> listaVentas = new LinkedList<>();
    private List<Float> listaGastos = new LinkedList<>();

    public Resumenes(Venta dia, Date end,String headerVentas,String headerGastos) {
        this.dia = dia;
        this.dia = dia;
        this.end = end;
        
        this.headerVentas = new MessageFormat(headerVentas);
        this.headerGastos = new MessageFormat(headerGastos);

        initComponents();
        updateDialog(dia,null);  
        
       
        

    
    }

    public Resumenes(Venta dia, Cocina cocina,Date end,
            String headerVentas,String headerGastos) {//***************obsoleto???********************
        this.dia = dia ;
        this.end = end;
        this.cocina = cocina;
        this.headerVentas = new MessageFormat(headerVentas);
        this.headerGastos = new MessageFormat(headerGastos);
        
        initComponents();
        updateDialog(dia,cocina); 
      
    }

    public void Imprimir() {

        try {
           

            MessageFormat footer = new MessageFormat("-Pag {0}-");

            jTableGastos.print(JTable.PrintMode.FIT_WIDTH, headerGastos, footer);
            jTableVenta.print(JTable.PrintMode.FIT_WIDTH, headerVentas, footer);

        } catch (PrinterException ex) {
            Logger.getLogger(Resumen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        jLabelFecha = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        jLabelGanancia = new javax.swing.JLabel();
        jLabelTRecaudado = new javax.swing.JLabel();
        jLabelDineroInvertido = new javax.swing.JLabel();
        jButtonImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jTextFieldTotalRecaudado = new javax.swing.JTextField();
        jTextFieldInversion = new javax.swing.JTextField();
        jTextFieldGanancia = new javax.swing.JTextField();
        jLabelGastosCasa = new javax.swing.JLabel();
        jTextFieldGastos = new javax.swing.JTextField();
        jCheckBoxRedondearValores = new javax.swing.JCheckBox();
        jCheckBoxConsumoCasa = new javax.swing.JCheckBox();
        jTextFieldFechaInit = new javax.swing.JTextField();
        jLabelDel = new javax.swing.JLabel();
        jLabelAl = new javax.swing.JLabel();
        jTextFieldFechaFin = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(977, 631));
        setLayout(new java.awt.BorderLayout());

        panelRect1.setPreferredSize(new java.awt.Dimension(977, 631));

        jLabelFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelFecha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFecha.setText("Fecha");

        jTableVenta.setAutoCreateRowSorter(true);
        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Plato", "Precio", "Cantidad", "Dinero Recaudado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableVenta);

        jLabelGanancia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelGanancia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGanancia.setText("Ganancia");

        jLabelTRecaudado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTRecaudado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTRecaudado.setText("Total Recaudado");

        jLabelDineroInvertido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelDineroInvertido.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDineroInvertido.setText("Dinero Invertido");

        jButtonImprimir.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jTableGastos.setAutoCreateRowSorter(true);
        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Ingrediente", "U/M", "Cantidad", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGastos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableGastos);

        jTextFieldTotalRecaudado.setEditable(false);
        jTextFieldTotalRecaudado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldTotalRecaudado.setForeground(new java.awt.Color(153, 153, 0));
        jTextFieldTotalRecaudado.setText("Recaudo");
        jTextFieldTotalRecaudado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalRecaudadoActionPerformed(evt);
            }
        });

        jTextFieldInversion.setEditable(false);
        jTextFieldInversion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldInversion.setForeground(new java.awt.Color(204, 0, 0));
        jTextFieldInversion.setText("inversion");

        jTextFieldGanancia.setEditable(false);
        jTextFieldGanancia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldGanancia.setForeground(new java.awt.Color(0, 153, 0));
        jTextFieldGanancia.setText("Ganancia");

        jLabelGastosCasa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelGastosCasa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGastosCasa.setText("Gastos de la Casa");

        jTextFieldGastos.setEditable(false);
        jTextFieldGastos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldGastos.setForeground(new java.awt.Color(204, 0, 0));
        jTextFieldGastos.setText("Gastos");

        jCheckBoxRedondearValores.setBackground(new java.awt.Color(51, 51, 51));
        jCheckBoxRedondearValores.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jCheckBoxRedondearValores.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxRedondearValores.setText("Redondear valores");
        jCheckBoxRedondearValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRedondearValoresActionPerformed(evt);
            }
        });

        jCheckBoxConsumoCasa.setBackground(new java.awt.Color(51, 51, 51));
        jCheckBoxConsumoCasa.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jCheckBoxConsumoCasa.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxConsumoCasa.setText("Mostrar consumo de la casa");
        jCheckBoxConsumoCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxConsumoCasaActionPerformed(evt);
            }
        });

        jTextFieldFechaInit.setEditable(false);
        jTextFieldFechaInit.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTextFieldFechaInit.setText(R.DATE_FORMAT.format(dia.getFecha()));
        jTextFieldFechaInit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaInitActionPerformed(evt);
            }
        });

        jLabelDel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabelDel.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDel.setText("Del");

        jLabelAl.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabelAl.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAl.setText("Al");

        jTextFieldFechaFin.setEditable(false);
        jTextFieldFechaFin.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTextFieldFechaFin.setText("");
        jTextFieldFechaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRect1Layout = new javax.swing.GroupLayout(panelRect1);
        panelRect1.setLayout(panelRect1Layout);
        panelRect1Layout.setHorizontalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addComponent(jLabelFecha)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFechaInit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addGroup(panelRect1Layout.createSequentialGroup()
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTRecaudado)
                                    .addComponent(jLabelGanancia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldTotalRecaudado)
                                    .addComponent(jTextFieldGanancia, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jCheckBoxConsumoCasa)))
                        .addGap(29, 29, 29)
                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBoxRedondearValores, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect1Layout.createSequentialGroup()
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDineroInvertido)
                                    .addComponent(jLabelGastosCasa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldGastos)
                                    .addComponent(jTextFieldInversion, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))))
                        .addGap(22, 22, 22))))
        );
        panelRect1Layout.setVerticalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFecha)
                    .addComponent(jLabelDel)
                    .addComponent(jTextFieldFechaInit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAl)
                    .addComponent(jTextFieldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxConsumoCasa)
                    .addComponent(jCheckBoxRedondearValores))
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDineroInvertido)
                    .addComponent(jTextFieldInversion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalRecaudado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTRecaudado))
                .addGap(9, 9, 9)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGastosCasa)
                    .addComponent(jTextFieldGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGanancia))
                .addGap(14, 14, 14)
                .addComponent(jButtonImprimir)
                .addGap(10, 10, 10))
        );

        add(panelRect1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        Imprimir();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jTextFieldTotalRecaudadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalRecaudadoActionPerformed

    }//GEN-LAST:event_jTextFieldTotalRecaudadoActionPerformed

    private void jTextFieldFechaInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaInitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaInitActionPerformed

    private void jCheckBoxRedondearValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxRedondearValoresActionPerformed

        if (jCheckBoxRedondearValores.isSelected()) {
            for (int i = 0; i < jTableGastos.getRowCount(); i++) {
                if (jTableGastos.getValueAt(i, 3) != null && 
                        (float) jTableGastos.getValueAt(i, 3) >= 1) {
                    listaVentas.add((float) jTableGastos.getValueAt(i, 3));
                    jTableGastos.setValueAt((float) Math.
                            round((float) jTableGastos.getValueAt(i, 3)), i, 3);
                }

            }
            for (int i = 0; i < jTableGastos.getRowCount(); i++) {
                if (jTableGastos.getValueAt(i, 4) != null && 
                        (float) jTableGastos.getValueAt(i, 4) >= 1) {
                    listaGastos.add((float) jTableGastos.getValueAt(i, 4));
                    jTableGastos.setValueAt((float) Math.
                            round((float) jTableGastos.getValueAt(i, 4)), i, 4);
                }

            }

        } else {
            for (int i = 0; i < jTableGastos.getRowCount(); i++) {
                if (jTableGastos.getValueAt(i, 3) != null && 
                        (float) jTableGastos.getValueAt(i, 3) >= 1) {
                    jTableGastos.setValueAt(listaVentas.remove(0), i, 3);
                
                }

            }
            for (int i = 0; i < jTableGastos.getRowCount(); i++) {
                if (jTableGastos.getValueAt(i, 4) != null && 
                        (float) jTableGastos.getValueAt(i, 4) >= 1) {
                    jTableGastos.setValueAt(listaGastos.remove(0), i, 4);
                    
                }

            }
        }
    }//GEN-LAST:event_jCheckBoxRedondearValoresActionPerformed

    private void jTextFieldFechaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaFinActionPerformed

    private void jCheckBoxConsumoCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxConsumoCasaActionPerformed
        if (jTableVenta.getRowCount() > 0) {
            DefaultTableModel model = (DefaultTableModel) jTableVenta.getModel();

            String totalRecaudado = jTextFieldTotalRecaudado.getText();
            totalRecaudado = totalRecaudado.substring(0, totalRecaudado.length()-3);
            if (jCheckBoxConsumoCasa.isSelected()) {
                Object[] row = {"Gastos de la casa",null,null,null,null};
                model.addRow(row);
                if(cocina == null){
                VentaDAO.getResumenVentasDeLaCasaOnTable(jTableVenta, dia);}
                else{
                VentaDAO.getResumenVentasDeLaCasaXCocinaOnTable(jTableVenta, dia, cocina);
                }
                Object [] row2 = {"TotalRecaudado", null, null, null,Float.parseFloat(totalRecaudado)};
                model.addRow(row2);
               
              
            } else {
                String parada = "";
                do {
                    parada = ((String) jTableVenta.getValueAt(jTableVenta.getRowCount() - 1, 0));
                    model.removeRow(jTableVenta.getRowCount() - 1);
                } while (!parada.equals("Gastos de la casa"));
                Object[] row = {"TotalRecaudado", null, null,null, Float.parseFloat(totalRecaudado)};
                model.addRow(row);
                
               }
        } else {
            JOptionPane.showMessageDialog(null, "Esta funcion no esta disponible con la tabla vacia");
        }
    }//GEN-LAST:event_jCheckBoxConsumoCasaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JCheckBox jCheckBoxConsumoCasa;
    private javax.swing.JCheckBox jCheckBoxRedondearValores;
    private javax.swing.JLabel jLabelAl;
    private javax.swing.JLabel jLabelDel;
    private javax.swing.JLabel jLabelDineroInvertido;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelGanancia;
    private javax.swing.JLabel jLabelGastosCasa;
    private javax.swing.JLabel jLabelTRecaudado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableGastos;
    private javax.swing.JTable jTableVenta;
    private javax.swing.JTextField jTextFieldFechaFin;
    private javax.swing.JTextField jTextFieldFechaInit;
    private javax.swing.JTextField jTextFieldGanancia;
    private javax.swing.JTextField jTextFieldGastos;
    private javax.swing.JTextField jTextFieldInversion;
    private javax.swing.JTextField jTextFieldTotalRecaudado;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    // End of variables declaration//GEN-END:variables

    private void updateDialog(Venta dia,Cocina c) {
        jTextFieldFechaInit.setText(restManager.resources.R.
                DATE_FORMAT.format(dia.getFecha()));
        jTextFieldFechaFin.setText(restManager.resources.R.
                DATE_FORMAT.format(end));
        fillTablaVentas(c);
        fillTablaGastos(c);
    
    }

    private void fillTablaVentas(Cocina c) {
         comun.limpiarTabla(jTableVenta);
        if(c == null){
       VentaDAO.getResumenVentasOnTable(jTableVenta, dia);
       jTextFieldTotalRecaudado.setText(comun.calcularSumaTabla(jTableVenta, 4) + Main.moneda);}
        else{
       VentaDAO.getResumenDetalladoVentasCocinaOnTable(jTableVenta, dia, c);
       jTextFieldTotalRecaudado.setText(comun.calcularSumaTabla(jTableVenta, 4) + Main.moneda);
            
        }
       
    }

    private void fillTablaGastos(Cocina c) {
        comun.limpiarTabla(jTableGastos);
        if(c == null){
        VentaDAO.getResumenGastosOnTable(jTableGastos, dia);
        jTextFieldInversion.setText(comun.calcularSumaTabla(jTableGastos, 4) + Main.moneda);
        }
        else{
          VentaDAO.getResumenGastosCocinaOnTable(jTableGastos, dia, c);
          jTextFieldInversion.setText(comun.calcularSumaTabla(jTableGastos, 4) + Main.moneda);
        }
        
    }

    
    
}
