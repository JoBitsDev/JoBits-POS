/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractView;
import GUI.Views.util.AbstractCrossReferenePanel;
import java.awt.Dialog;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import restManager.controller.AbstractDialogController;
import restManager.controller.Controller;
import restManager.controller.almacen.AlmacenManageController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.InsumoAlmacenPK;
import restManager.persistencia.models.InsumoAlmacenDAO;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class AlmacenEditView extends AbstractView {

    /**
     * Creates new form AlmacenMain
     *
     * @param parent
     * @param modal
     */
    Almacen a;

    AbstractCrossReferenePanel<InsumoAlmacen, Insumo> model;

    public AlmacenEditView(AbstractDialogController controller, Dialog owner, boolean modal, Almacen a) {
        super(DialogType.FULL_SCREEN, controller, owner, modal);
        this.a = a;
        initComponents();

    }

    @Override
    public void updateView() {
        model = new AbstractCrossReferenePanel<InsumoAlmacen, Insumo>("Insumos", getController().getInsumoList()) {
            @Override
            public RestManagerAbstractTableModel<InsumoAlmacen> getTableModel() {
                return new RestManagerAbstractTableModel<InsumoAlmacen>(getController().getInsumoAlmacenList(a), getjTableCrossReference()) {
                    @Override
                    public int getColumnCount() {
                        return 5;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                            case 0:
                                return items.get(rowIndex).getInsumo().getCodInsumo();
                            case 2:
                                return items.get(rowIndex).getInsumo().getNombre();
                            case 1:
                                return items.get(rowIndex).getInsumo().getUm();
                            case 3:
                                return items.get(rowIndex).getCantidad();
                            case 4:
                                return items.get(rowIndex).getValorMonetario() / items.get(rowIndex).getCantidad();
                            case 5:
                                return items.get(rowIndex).getValorMonetario();
                            default:
                                return null;

                        }
                    }

                    @Override
                    public String getColumnName(int column) {
                        switch (column) {
                            case 0:
                                return "Codigo";
                            case 2:
                                return "Nombre";
                            case 1:
                                return "UM";
                            case 3:
                                return "En Almacen";
                            case 4:
                                return "Costo Unitario (Prom)";
                            case 5:
                                return "Valor Total";
                            default:
                                return null;
                        }
                    }
                };
            }

            @Override
            public InsumoAlmacen transformK_T(Insumo selected) {
                InsumoAlmacenPK newInsumoPK = new InsumoAlmacenPK(selected.getCodInsumo(), a.getCodAlmacen());
                InsumoAlmacen newInsumo = new InsumoAlmacen(newInsumoPK);
                newInsumo.setAlmacen(a);
                newInsumo.setCantidad((float) 0);
                newInsumo.setInsumo(selected);
                newInsumo.setValorMonetario((float) 0);
                InsumoAlmacenDAO.getInstance().create(newInsumo);
                return newInsumo;
            }
        };

        setTitle(a.getNombre());
        jXLabelValorTotal.setText(comun.setDosLugaresDecimales(a.getValorMonetario()));

    }

    @Override
    public AlmacenManageController getController() {
        return (AlmacenManageController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fetchComponentData() {
        jXPanelTabla.add(model);
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabelNombreAlmacen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jXPanelTabla = new org.jdesktop.swingx.JXPanel();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jPanel1 = new javax.swing.JPanel();
        jXLabelTotalAlmacen = new org.jdesktop.swingx.JXLabel();
        jXLabelValorTotal = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonDarReporte = new javax.swing.JButton();
        jButtonModificarStock = new javax.swing.JButton();
        jButtonVerFichasEntrada = new javax.swing.JButton();
        jButtonNuevaFicha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(getFont());
        setMinimumSize(getMinimumSize());
        getContentPane().setLayout(new java.awt.BorderLayout(0, 5));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabelNombreAlmacen.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabelNombreAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreAlmacen.setText("<Nombre Almacen>");
        jPanel3.add(jLabelNombreAlmacen, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/botonlogout.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jXPanelTabla.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jXPanelTabla, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelControles.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jXPanelControles.setLayout(new javax.swing.BoxLayout(jXPanelControles, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jXLabelTotalAlmacen.setText(bundle.getString("label_valor_almacen")); // NOI18N
        jXLabelTotalAlmacen.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelTotalAlmacen, java.awt.BorderLayout.WEST);

        jXLabelValorTotal.setText("0.00 CUC");
        jXLabelValorTotal.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelValorTotal, java.awt.BorderLayout.EAST);

        jXPanelControles.add(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        jButtonDarReporte.setText(bundle.getString("label_reporte")); // NOI18N
        jButtonDarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDarReporteActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonDarReporte);

        jButtonModificarStock.setText(bundle.getString("label_mod_stock")); // NOI18N
        jButtonModificarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarStockActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonModificarStock);

        jButtonVerFichasEntrada.setText(bundle.getString("label_ver_transacciones")); // NOI18N
        jButtonVerFichasEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFichasEntradaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonVerFichasEntrada);

        jButtonNuevaFicha.setText(bundle.getString("label_nueva_transaccion")); // NOI18N
        jPanel2.add(jButtonNuevaFicha);

        jXPanelControles.add(jPanel2);

        getContentPane().add(jXPanelControles, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDarReporteActionPerformed
        getController().imprimirReporteParaCompras(a);
    }//GEN-LAST:event_jButtonDarReporteActionPerformed

    private void jButtonModificarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarStockActionPerformed
        getController().modificarStock(model.getTableModel().getObjectAtSelectedRow().getInsumo());
    }//GEN-LAST:event_jButtonModificarStockActionPerformed

    private void jButtonVerFichasEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFichasEntradaActionPerformed
        getController().verTransacciones(a);
    }//GEN-LAST:event_jButtonVerFichasEntradaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDarReporte;
    private javax.swing.JButton jButtonModificarStock;
    private javax.swing.JButton jButtonNuevaFicha;
    private javax.swing.JButton jButtonVerFichasEntrada;
    private javax.swing.JLabel jLabelNombreAlmacen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.jdesktop.swingx.JXLabel jXLabelTotalAlmacen;
    private org.jdesktop.swingx.JXLabel jXLabelValorTotal;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    private org.jdesktop.swingx.JXPanel jXPanelTabla;
    // End of variables declaration//GEN-END:variables

}
