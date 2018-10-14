/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import restManager.controller.AbstractController;
import restManager.exceptions.NoSelectedException;

/**
 *
 * @author Jorge
 * @param <T>
 */
public abstract class AbstractListView<T> extends AbstractView {

    private MyJTableModel model;

    public AbstractListView(AbstractController controller, Frame owner, boolean modal) {
        super(DialogType.LIST, controller, owner, modal);
        initComponents();

    }

    public AbstractListView(AbstractController controller, Dialog owner, boolean modal) {
        super(DialogType.LIST, controller, owner, modal);
        initComponents();
    }

    /**
     * Creates new form CocinaLista
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanelLista = new org.jdesktop.swingx.JXPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableList = new javax.swing.JTable();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        setTitle(bundle.getString("label_insumo")); // NOI18N
        setMinimumSize(getMinimumSize());
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jXPanelLista.setLayout(new java.awt.BorderLayout(0, 10));

        jTableList.setAutoCreateRowSorter(true);
        jTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "UM", "En Almacen", "Elaborado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
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
        jTableList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableList.setShowGrid(false);
        jTableList.getTableHeader().setReorderingAllowed(false);
        jTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableList);
        jTableList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jXPanelLista.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jXPanel1.setLayout(new java.awt.BorderLayout());

        jXLabel1.setText(bundle.getString("label_buscar")); // NOI18N
        jXPanel1.add(jXLabel1, java.awt.BorderLayout.WEST);

        jTextFieldBusqueda.setPreferredSize(new java.awt.Dimension(300, 26));
        jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyReleased(evt);
            }
        });
        jXPanel1.add(jTextFieldBusqueda, java.awt.BorderLayout.EAST);

        jXPanelLista.add(jXPanel1, java.awt.BorderLayout.PAGE_START);

        jButtonAdd.setText(bundle.getString("label_agregar")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonAdd);

        jButtonEdit.setText(bundle.getString("label_editar")); // NOI18N
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonEdit);

        jButtonDelete.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonDelete);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXPanelLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXPanelControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXPanelLista, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jXPanelControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListMouseClicked

    }//GEN-LAST:event_jTableListMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        super.getController().createInstance();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        getController().setSelected(model.getObjectAtSelectedRow());
        super.getController().deleteInstance(getController().getSelected());
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        getController().setSelected(model.getObjectAtSelectedRow());
        super.getController().editInstance(getController().getSelected());
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jTextFieldBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyTyped

    }//GEN-LAST:event_jTextFieldBusquedaKeyTyped

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        model.filterByString(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableList;
    private javax.swing.JTextField jTextFieldBusqueda;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    private org.jdesktop.swingx.JXPanel jXPanelLista;
    // End of variables declaration//GEN-END:variables

    public void updateView(List<T> items) {
        model = generateTableModel(items);
        jTableList.setModel(model);
        jTableList.setRowSorter(model.getSorter());

    }

    public abstract MyJTableModel<T> generateTableModel(List<T> items);

    public abstract class MyJTableModel<T> extends AbstractTableModel {

        private final List<T> items;
        private MyTableRowFilter filter;
        private TableRowSorter<MyJTableModel<T>> sorter;

        public MyJTableModel(List<T> items) {
            this.items = items;
            initFilterAndSorter();
        }

        private void initFilterAndSorter() {
            sorter = new TableRowSorter<>(this);
            filter = new MyTableRowFilter();
            sorter.setRowFilter(filter);
        }

        /**
         * Shows the rows that contains the string passed by parameter this
         * method is case sensitive
         *
         * @param s the string to search in the table
         */
        public void filterByString(String s) {
            filter.setSearchParam(s);
            sort();

        }

        /**
         * Sorts and filters the rows in the view based on the sort keys of the
         * columns currently being sorted and the filter, if any, associated
         * with this sorter. An empty sortKeys list indicates that the view
         * should unsorted, the same as the model.
         */
        public void sort() {
            sorter.sort();
        }

        //
        //Metodos sobreescritos
        //
        @Override
        public int getRowCount() {
            return items.size();
        }

        @Override
        public abstract int getColumnCount();

        @Override
        public abstract Object getValueAt(int rowIndex, int columnIndex);

        @Override
        public abstract String getColumnName(int column);

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return super.getColumnClass(columnIndex);
        }

        public T getObjectAt(int rowIndex) {
            return items.get(rowIndex);
        }

        public TableRowSorter<MyJTableModel<T>> getSorter() {
            return sorter;
        }

        public T getObjectAtSelectedRow() {
            if (jTableList.getSelectedRow() == -1) {
                throw new NoSelectedException();
            }
            return items.get(jTableList.convertRowIndexToModel(jTableList.getSelectedRow()));
        }

        public class MyTableRowFilter extends RowFilter<MyJTableModel, Integer> {

            private String searchParam;

            public MyTableRowFilter() {
                searchParam = "";
            }

            public void setSearchParam(String searchParam) {
                this.searchParam = searchParam;
            }

            @Override
            public boolean include(RowFilter.Entry<? extends MyJTableModel, ? extends Integer> entry) {
                if (searchParam.isEmpty()) {
                    return true;
                }
                for (int i = entry.getValueCount() - 1; i >= 0; i--) {
                    if (entry.getStringValue(i).toLowerCase().contains(searchParam.toLowerCase())) {
                        return true;

                    }
                }
                return false;
            }

        }

    }

}
