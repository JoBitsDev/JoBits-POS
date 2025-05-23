/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableListIntelliHint;
import com.jobits.pos.utils.utils;
import static com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorPresenter.*;
import com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorViewModel;
import static com.jobits.pos.ui.venta.orden.presenter.ProductoVentaSelectorViewModel.*;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.CardLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Jorge
 */
public class ProductoVentaSelectorView extends AbstractViewPanel {

    BindableListIntelliHint<ProductoVenta> autoCompleteModel;

    public ProductoVentaSelectorView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public String getViewName() {
        return "MenuListView";
    }

    @Override
    public void uiInit() {
        initComponents();
        jListSecciones.addListSelectionListener((ListSelectionEvent e) -> {
            getPresenter().getOperation(ACTION_OCULTAR_SECCION).doAction();
        });
        jScrollPaneSecciones.getVerticalScrollBar().setUnitIncrement(80);
        jListSecciones.setCellRenderer(new ListCellRenderer<Seccion>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Seccion> list, Seccion value, int index, boolean isSelected, boolean cellHasFocus) {
                return new CellRenderLabel(value.getNombreSeccion(), null, isSelected, null);
            }
        });
        jListProductos.setCellRenderer(new ListCellRenderer<ProductoVenta>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends ProductoVenta> list, ProductoVenta value, int index, boolean isSelected, boolean cellHasFocus) {
                return new CellRenderLabel(value.getNombre(), utils.setDosLugaresDecimales(value.getPrecioVenta()), isSelected, value.getDescripcion());
            }
        });
        jButtonAtras.setVisible(false);
    }

    @Override
    public void wireUp() {
        Bindings.bind(jListSecciones, new SelectionInList<Seccion>(
                getPresenter().getModel(ProductoVentaSelectorViewModel.PROP_LISTA_ELEMENTOS),
                getPresenter().getModel(ProductoVentaSelectorViewModel.PROP_ELEMENTO_SELECCIONADO)));

        Bindings.bind(jListProductos, new SelectionInList<Seccion>(
                getPresenter().getModel(ProductoVentaSelectorViewModel.PROP_LISTAPRODUCTOS),
                getPresenter().getModel(ProductoVentaSelectorViewModel.PROP_PRODUCTOVENTASELECCIONADO)));

        Bindings.bind(jTextFieldSearch, getPresenter().getModel(PROP_PV_FILTRADO));
        Bindings.bind(jTextFieldSearch, "enabled", getPresenter().getModel(PROP_CAMPO_BUSQUEDA_ENABLED));

        getPresenter().addBeanPropertyChangeListener(PROP_PV_FILTRADO, (PropertyChangeEvent evt) -> {
            //jListProductos.revalidate();
            jListProductos.repaint();
           // jListSecciones.revalidate();
            jListSecciones.repaint();
            if (evt.getNewValue() != null && !((String) evt.getNewValue()).equals("")) {
                changeToProductos();
            } else {
                changeToSecciones();
            }
        });
        getPresenter().addBeanPropertyChangeListener(PROP_CAMPO_BUSQUEDA_ENABLED, (PropertyChangeEvent evt) -> {
            if ((boolean) evt.getNewValue()) {
                jTextFieldSearch.requestFocusInWindow();
            }
        });
        getPresenter().addBeanPropertyChangeListener(PROP_LISTAPRODUCTOS, (PropertyChangeEvent evt) -> {
            //jListProductos.revalidate();
            jListProductos.repaint();
        });
        getPresenter().addPropertyChangeListener(PROP_MOSTRAR_SECCIONES, (PropertyChangeEvent evt) -> {
            if ((boolean) evt.getNewValue()) {
                changeToSecciones();
            } else {
                changeToProductos();
            }
        });

        jButtonAtras.addActionListener(getPresenter().getOperation(ACTION_MOSTRAR_SECCION));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jButtonAtras = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jPanelBusqueda = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabelBuscarIcon = new javax.swing.JLabel();
        jPanelMain = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jScrollPaneSecciones = MaterialComponentsFactory.Containers.getScrollPane();
        jListSecciones = new javax.swing.JList<>();
        jScrollPaneProductos = MaterialComponentsFactory.Containers.getScrollPane();
        jListProductos = new javax.swing.JList<>();

        setMinimumSize(new java.awt.Dimension(50, 50));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(140, 60));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jButtonAtras.setIcon(MaterialIcons.ARROW_BACK);
        jButtonAtras.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtrasActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAtras, java.awt.BorderLayout.WEST);

        jPanelBusqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelBusqueda.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanelBusqueda.setLayout(new java.awt.BorderLayout());

        jTextFieldSearch.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextFieldSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSearch.setMinimumSize(new java.awt.Dimension(200, 50));
        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(200, 50));
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusLost(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldSearch);

        jLabelBuscarIcon.setIcon(MaterialIcons.SEARCH);
        jLabelBuscarIcon.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jLabelBuscarIcon);

        jPanelBusqueda.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanelBusqueda, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanelMain.setLayout(new java.awt.CardLayout());

        jScrollPaneSecciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15), "Secciones"));
        jScrollPaneSecciones.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneSecciones.setPreferredSize(new java.awt.Dimension(200, 120));

        jListSecciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListSecciones.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jListSecciones.setVisibleRowCount(-1);
        jScrollPaneSecciones.setViewportView(jListSecciones);

        jPanelMain.add(jScrollPaneSecciones, "Secciones");

        jScrollPaneProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15), "Productos"));
        jScrollPaneProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPaneProductos.setFocusable(false);

        jListProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListProductos.setFixedCellHeight(120);
        jListProductos.setFixedCellWidth(140);
        jListProductos.setFocusable(false);
        jListProductos.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jListProductos.setVisibleRowCount(-1);
        jListProductos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListProductosValueChanged(evt);
            }
        });
        jScrollPaneProductos.setViewportView(jListProductos);

        jPanelMain.add(jScrollPaneProductos, "Productos");

        add(jPanelMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyTyped
    }//GEN-LAST:event_jTextFieldSearchKeyTyped
    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        jTextFieldSearch.setText("");
    }//GEN-LAST:event_jTextFieldSearchFocusGained
    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
    }//GEN-LAST:event_jTextFieldSearchFocusLost

    private void jListProductosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListProductosValueChanged
        jListProductos.clearSelection();
        //jListProductos.revalidate();
        jListProductos.repaint();
    }//GEN-LAST:event_jListProductosValueChanged

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtrasActionPerformed
//        changeToSecciones();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAtrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JLabel jLabelBuscarIcon;
    private javax.swing.JList<ProductoVenta> jListProductos;
    private javax.swing.JList<Seccion> jListSecciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private javax.swing.JScrollPane jScrollPaneSecciones;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    private void changeToProductos() {
        CardLayout cards = (CardLayout) jPanelMain.getLayout();
        cards.show(jPanelMain, "Productos");
        jButtonAtras.setVisible(true);
    }

    private void changeToSecciones() {
        CardLayout cards = (CardLayout) jPanelMain.getLayout();
        cards.show(jPanelMain, "Secciones");
        jButtonAtras.setVisible(false);
    }
}
