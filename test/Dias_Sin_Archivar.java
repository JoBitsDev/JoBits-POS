package GUI;


import GUI.Views.venta.Resumen;
import java.awt.Dialog;
import java.awt.Frame;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.*;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
import restManager.util.staticValues;

public class Dias_Sin_Archivar extends javax.swing.JDialog{
    
    private List<Venta> ventas = staticContent.ventaJPA.findVentaEntities();

    public Dias_Sin_Archivar(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initComponents();
        RellenarTabla();
        setVisible(true);
    }

    public Dias_Sin_Archivar(Dialog owner, boolean modal) {
        super(owner, modal);
        initComponents();
        RellenarTabla();
        setVisible(true);
        
    }

    
    public Dias_Sin_Archivar() {
        super();
        initComponents();
        RellenarTabla();
        setVisible(true);

    }

    private void RellenarTabla() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < ventas.size(); i++) {
            Object[] row = {false, ventas.get(i).getFecha(), ventas.get(i).getVentaTotal()};
            model.addRow(row);

        }

    }
    
//    private void CrearSemana(Anno anno){
//                   
//        try{
//                    anno.AgregarSem(new semana(getSeleccionadosDelaTabla()));
//                    deleteSeleccionadosDelaTabla();
//                    VaciarTabla();
//                    RellenarTabla();
//                    JOptionPane.showMessageDialog(null, "Se ha creado la semana exitosamente.");
//                    Guardar();
//        } catch (ParseException | NullPointerException | IOException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    }
//    
//    private void CrearAnno (ArrayList<Dia> dias){
//        int anno;
//        if(!dias.isEmpty()){
//            try{
//            anno = dias.get(0).getFecha().getYear()+1900;
//            annos.add(new Anno(anno));
//            annos.get(annos.size()-1).AgregarSem(new semana(dias));
//             deleteSeleccionadosDelaTabla();
//                    VaciarTabla();
//                    RellenarTabla();
//                    JOptionPane.showMessageDialog(null, "Se ha creado la semana exitosamente.");
//                    Guardar();
//        }   catch (ParseException | IOException ex) {
//                JOptionPane.showMessageDialog(null, ex.getMessage());
//            }}
//    }
    
    public ArrayList<Venta> getSeleccionadosDelaTabla() throws ParseException,NullPointerException {
        ArrayList<Venta> v = new ArrayList<>();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if ((Boolean) jTable1.getValueAt(i, 0)) {
                v.add(getDia(Format.parse((String) jTable1.getValueAt(i, 1)), (double) jTable1.getValueAt(i, 2)));
            }
        }
        if(v.isEmpty())
            throw new NullPointerException("No hay elementos seleccionados. seleccione al menos uno");
        return v;
    }

    public void deleteSeleccionadosDelaTabla() throws ParseException, IOException {
        EntityManager em = staticContent.EMF.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if ((Boolean) jTable1.getValueAt(i, 0)) {
              em.remove(ventas.remove(i));
            }
        }
        em.getTransaction().commit();
    }

//    public Dia getDia(Date fecha, double valor) {
//        Dia s;
//        for (int i = 0; i < dias.size(); i++) {
//
//            if (dias.get(i).getFecha().getDate() == fecha.getDate() && 
//                    dias.get(i).getFecha().getMonth() == fecha.getMonth() && 
//                    dias.get(i).getImporteTotal() == valor) {
//                return dias.get(i);
//            }
//        }
//
//        return null;
//    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButtonSalir = new javax.swing.JButton();
        jButtonCrearSemana = new javax.swing.JButton();
        jButtonCierreVentas = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dias sin Archivar");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(jTable1.getFont().deriveFont(jTable1.getFont().getSize()+7f));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccionado", "Dia(Fecha)", "Total Recaudado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dias sin Archivar");

        jCheckBox1.setText("Seleccionar Todos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        jButtonCrearSemana.setText("Crear Semana");
        jButtonCrearSemana.setEnabled(false);
        jButtonCrearSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearSemanaActionPerformed(evt);
            }
        });

        jButtonCierreVentas.setText("Hacer Cierre de Ventas");
        jButtonCierreVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCierreVentasActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jButtonEliminar)
                                    .addGap(89, 89, 89)
                                    .addComponent(jButtonSalir))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jButtonCrearSemana)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonCierreVentas))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrearSemana)
                    .addComponent(jButtonCierreVentas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalir)
                    .addComponent(jButtonEliminar))
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        boolean val = jCheckBox1.isSelected();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(val, i, 0);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButtonCrearSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearSemanaActionPerformed
//        if(JOptionPane.showConfirmDialog(null, "Seguro que desea crear la semana?")==0)
//        try {
//            if (!getSeleccionadosDelaTabla().isEmpty()) {
//                
//                if (annos.isEmpty()) {
//                    annos.add(new Anno(fecha.getYear() + 1900));
//                }
//               
//                
//                ArrayList<Dia> dias = getSeleccionadosDelaTabla();
//                    for (int i = 0; i < annos.size(); i++) {
//                        if (dias.get(0).getFecha().getYear() + 1900 == annos.get(i).getAnno()) {
//                            CrearSemana(annos.get(i));
//                            return;
//                        }
//                    }
//                   CrearAnno(dias);
//                   
//
//
//            } else {
//                JOptionPane.showMessageDialog(null, "No hay elementos seleccionados.");
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(Dias_Sin_Archivar.class.getName()).log(Level.SEVERE, null, ex);
//
//        } catch (IndexOutOfBoundsException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }


    }//GEN-LAST:event_jButtonCrearSemanaActionPerformed

    private void jButtonCierreVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCierreVentasActionPerformed
        ArrayList<Venta> x;
        Date fechafin = new Date(100, 1, 1);
        Date fechainicio = staticValues.today;
        try {
            x = new ArrayList<>(getSeleccionadosDelaTabla());

            ArrayList<Orden> ord = new ArrayList<>();
            for (Dia dia : x) {
                ord.addAll(dia.getOrdeness());
                
                
                if(dia.getFecha().after(fechafin)){
                    fechafin = dia.getFecha();
                }
                if(dia.getFecha().before(fechainicio))
                    fechainicio = dia.getFecha();
                
            }

            new Resumen(new Dia(fechainicio,ord,new ArrayList(),new ArrayList(), new ArrayList()),fechafin);

        } catch (ParseException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButtonCierreVentasActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            try {
                Date f = Format.parse((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                double valor = (double) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
                new Comenzar_Dia(dias.indexOf(getDia(f, valor)), getDia(f, valor));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el dia seleccionado");
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        VaciarTabla();
        RellenarTabla();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed

      try {
          if (JOptionPane.showConfirmDialog(null, "Seguro desea eliminar los dias seleccionados.") == JOptionPane.YES_OPTION)
          for (int i = 0; i < jTable1.getRowCount(); i++) {
              if ((boolean)jTable1.getValueAt(i, 0)) {
                Date B;
                String A = (String) jTable1.getValueAt(i, 1);
                double valor = (double) jTable1.getValueAt(i, 2);
                B = Format.parse(A);
                 for (int j = 0; j < dias.size(); j++) {
                        if (compareDates(dias.get(j).getFecha(), B)) {
                            dias.remove(j);
                            break;
                        }
                    }
                
               
            } 
          }
            RE.Guardar_Dias_Sin_Guardar();
            VaciarTabla();
            RellenarTabla();
            

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error al eliminar el dia.");
        }


    }//GEN-LAST:event_jButtonEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCierreVentas;
    private javax.swing.JButton jButtonCrearSemana;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    
    /*   ---------------------metodo para actualizar los cambios que se han hecho en el menu para los dias si archivar----------------------------
    ej: si se cambiaron las propiedades de cocina....los dias viejos no se van actualiizar.....este metodo los actualiza
    hay que hacerle un boton
    ----------------------------------------------------------------------------------------------------------------------------------------------
    //......................el metodo ..................................
    private void boton ()throws NullPointerException,ParseException{
        
      ArrayList<Dia> x = getSeleccionadosDelaTabla();
      
      x.stream().forEach((y) -> {
          y.getOrdeness().stream().forEach((z) -> {
              z.getPlatos()[0].stream().forEach((plato) -> {
                  platos.stream().filter((test) -> (test.getNombre().equals
                (((Platos)plato).getNombre()) && !test.getNombredeCocina().equals
                (((Platos)plato).getNombredeCocina()))).forEach((test) -> {
                      ((Platos)plato).setNombredeCocina(test.getNombredeCocina());
                  });
              });
          });
        });
    
    
    
    
        
    }
*/

    private boolean compareDates (Date a , Date b){
        return ((a.getDate() == b.getDate()) 
                && (a.getMonth() == b.getMonth())
                && (a.getYear() == b.getYear()));
    }
}
