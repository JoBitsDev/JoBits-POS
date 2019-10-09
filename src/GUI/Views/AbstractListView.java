/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXPanel;
import restManager.controller.AbstractListController;
import restManager.exceptions.NoSelectedException;
import restManager.resources.R;

/**
 *
 * @author Jorge
 * @param <T>
 */
public abstract class AbstractListView<T> extends AbstractView {

    protected MyJTableModel<T> model;

    public AbstractListView(DialogType type, AbstractListController<T> controller, AbstractView parent) {
        super(type, controller, parent);

    }

    public AbstractListView(AbstractListController<T> controller, AbstractView parent, boolean modal) {
        super(DialogType.LIST, controller, parent, modal);
        initComponents();
        createPopUpMenu();

    }

    public AbstractListView(AbstractListController<T> controller, Frame parent, boolean modal) {
        super(DialogType.LIST, controller, parent, modal);
        initComponents();
        createPopUpMenu();

    }

    public AbstractListView(AbstractListController<T> controller, Dialog parent, boolean modal) {
        super(DialogType.LIST, controller, parent, modal);
        initComponents();
        createPopUpMenu();
    }

    private void showPopUpMenu(java.awt.event.MouseEvent evt) {
        if (evt.isPopupTrigger()) {
            if (!jPopupMenuClickDerecho.isVisible()) {
                int clickedRow = jTableList.rowAtPoint(evt.getPoint());
                jTableList.getSelectionModel().setSelectionInterval(clickedRow, clickedRow);
                jPopupMenuClickDerecho.setLocation(evt.getLocationOnScreen());
                jPopupMenuClickDerecho.setVisible(true);

            }
        }
    }

    /**
     * Creates new form CocinaLista
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuClickDerecho = new javax.swing.JPopupMenu();
        jPanelTabla = new javax.swing.JPanel();
        jPanelControlesSuperiores = new javax.swing.JPanel();
        jLabelCantidad = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableList = new javax.swing.JTable();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jPanelHeader = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jPanelExtra = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jPopupMenuClickDerecho.setInvoker(jTableList);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        setTitle(bundle.getString("label_lista")); // NOI18N
        setMinimumSize(getMinimumSize());
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(102, 255, 204));
        dropShadowBorder1.setShadowOpacity(1.0F);
        dropShadowBorder1.setShowBottomShadow(false);
        dropShadowBorder1.setShowLeftShadow(true);
        jPanelTabla.setBorder(dropShadowBorder1);
        jPanelTabla.setLayout(new java.awt.BorderLayout());

        jPanelControlesSuperiores.setOpaque(false);
        jPanelControlesSuperiores.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelCantidad.setFont(new java.awt.Font("Malayalam Sangam MN", 1, 13)); // NOI18N
        jLabelCantidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCantidad.setText("40 Elementos");
        jPanelControlesSuperiores.add(jLabelCantidad);

        jPanelTabla.add(jPanelControlesSuperiores, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(new org.pushingpixels.substance.internal.utils.border.SubstanceBorder());

        jTableList.setAutoCreateRowSorter(true);
        jTableList.setBackground(getBackground());
        jTableList.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableList.setRowHeight(25);
        jTableList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTableList.setShowGrid(false);
        jTableList.getTableHeader().setReorderingAllowed(false);
        jTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableList);
        jTableList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanelTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelTabla, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(102, 255, 204));
        dropShadowBorder2.setShadowOpacity(1.0F);
        dropShadowBorder2.setShowLeftShadow(true);
        jXPanelControles.setBorder(dropShadowBorder2);

        jButtonAdd.setMnemonic('a');
        jButtonAdd.setText(bundle.getString("label_agregar")); // NOI18N
        jButtonAdd.setPreferredSize(new java.awt.Dimension(93, 40));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonAdd);

        jButtonEdit.setMnemonic('e');
        jButtonEdit.setText(bundle.getString("label_editar")); // NOI18N
        jButtonEdit.setPreferredSize(new java.awt.Dimension(80, 40));
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonEdit);

        jButtonDelete.setMnemonic('d');
        jButtonDelete.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonDelete.setPreferredSize(new java.awt.Dimension(95, 40));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonDelete);

        getContentPane().add(jXPanelControles, java.awt.BorderLayout.PAGE_END);

        jPanelHeader.setBackground(new java.awt.Color(204, 204, 204));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShadowColor(new java.awt.Color(102, 255, 204));
        dropShadowBorder3.setShadowOpacity(1.0F);
        dropShadowBorder3.setShowBottomShadow(false);
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        jPanelHeader.setBorder(dropShadowBorder3);
        jPanelHeader.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/buscar.png"))); // NOI18N
        jXLabel1.setText(bundle.getString("label_buscar")); // NOI18N
        jXLabel1.setToolTipText("Buscar");
        jPanel1.add(jXLabel1);

        jTextFieldBusqueda.setPreferredSize(new java.awt.Dimension(200, 26));
        jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaKeyReleased(evt);
            }
        });
        jPanel1.add(jTextFieldBusqueda);

        jPanelHeader.add(jPanel1, java.awt.BorderLayout.EAST);

        jPanelExtra.setOpaque(false);
        jPanelExtra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jPanelHeader.add(jPanelExtra, java.awt.BorderLayout.PAGE_END);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelHeader.add(jButton1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == 1) {
            editSelected();
        }

    }//GEN-LAST:event_jTableListMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        getController().createInstance();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (jTableList.getSelectedRows().length > 0) {
            int[] selecteds = jTableList.getSelectedRows();
            int r = JOptionPane.showConfirmDialog(this, "Desea borrar " + selecteds.length + " elementos de la lista."
                    + "\n Esta accion no se puede deshacer");
            if (r == JOptionPane.YES_OPTION) {
                ArrayList<T> sel = new ArrayList<>();
                for (int s : selecteds) {
                    sel.add(model.getObjectAt(s));
                }
                for (T x : sel) {
                    getController().destroy(x, true);
                }
                JOptionPane.showMessageDialog(this, R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"),
                        R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                        new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));
            }
        } else {
            getController().setSelected(model.getObjectAtSelectedRow());
            getController().destroy(getController().getSelected());
        }

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        editSelected();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jTextFieldBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyTyped

    }//GEN-LAST:event_jTextFieldBusquedaKeyTyped

    private void jTextFieldBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaKeyReleased
        model.filterByString(jTextFieldBusqueda.getText());
    }//GEN-LAST:event_jTextFieldBusquedaKeyReleased

    private void jTableListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListMousePressed
        showPopUpMenu(evt);
    }//GEN-LAST:event_jTableListMousePressed

    private void jTableListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListMouseReleased
        showPopUpMenu(evt);
    }//GEN-LAST:event_jTableListMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelControlesSuperiores;
    private javax.swing.JPanel jPanelExtra;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelTabla;
    protected javax.swing.JPopupMenu jPopupMenuClickDerecho;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableList;
    private javax.swing.JTextField jTextFieldBusqueda;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateView() {
        model = generateTableModel(getController().getItems());
        jLabelCantidad.setText(getController().getItems().size() + " Elementos");
        model.addTableModelListener((TableModelEvent e) -> {
            jLabelCantidad.setText(getController().getItems().size() + " Elementos");
        });
        jTableList.setModel(model);
        jTableList.setRowSorter(model.getSorter());
        if (!jTextFieldBusqueda.getText().isEmpty()) {
            model.filterByString(jTextFieldBusqueda.getText());
        }

    }

    @Override
    public AbstractListController<T> getController() {
        return (AbstractListController<T>) super.getController();
    }

    public abstract MyJTableModel<T> generateTableModel(List<T> items);

    //
    // Private Methods
    //
    private void editSelected() {
        getController().setSelected(model.getObjectAtSelectedRow());
        getController().update(getController().getSelected()); //To change body of generated methods, choose Tools | Templates.
    }

    //
    //Protected Methods
    //
    protected void createPopUpMenu() {
    }

    //
    // Getters n Setters
    //
    public MyJTableModel<T> getModel() {
        return model;
    }

    public JButton getjButtonAdd() {
        return jButtonAdd;
    }

    public void setjButtonAdd(JButton jButtonAdd) {
        this.jButtonAdd = jButtonAdd;
    }

    public JButton getjButtonDelete() {
        return jButtonDelete;
    }

    public void setjButtonDelete(JButton jButtonDelete) {
        this.jButtonDelete = jButtonDelete;
    }

    public JButton getjButtonEdit() {
        return jButtonEdit;
    }

    public void setjButtonEdit(JButton jButtonEdit) {
        this.jButtonEdit = jButtonEdit;
    }

    public JXPanel getjXPanelControles() {
        return jXPanelControles;
    }


    public JTable getjTableList() {
        return jTableList;
    }

    public JPanel getjPanelControlesSuperiores() {
        return jPanelControlesSuperiores;
    }

    public JPanel getjPanelExtra() {
        return jPanelExtra;
    }

    //
    // Inner Class
    //
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

        public void addrow(T newObject) {
            items.add(newObject);
            fireTableRowsInserted(items.indexOf(newObject), items.indexOf(newObject));
        }

        public void deleteRow(T objectDeleted) {
            int itemToDelete = jTableList.convertRowIndexToModel(jTableList.getSelectedRow());
            items.remove(itemToDelete);
            fireTableRowsDeleted(itemToDelete, itemToDelete);
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
            return items.get(jTableList.convertRowIndexToModel(rowIndex));
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

        public List<T> getSelectedsObjects() {
            ArrayList<T> ret = new ArrayList<>();
            for (int x : jTableList.getSelectedRows()) {
                ret.add(items.get(jTableList.convertRowIndexToModel(x)));
            }
            return ret;
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
