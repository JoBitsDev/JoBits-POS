package com.jobits.pos.ui.venta.resumen;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.filter.FilterMainView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.ui.utils.ExcelAdapter;
import static com.jobits.pos.ui.viewmodel.AbstractListViewModel.PROP_TITULO_VISTA;
import static com.jobits.pos.ui.venta.resumen.presenter.AbstractResumenViewModel.*;
import com.jobits.pos.ui.venta.resumen.presenter.AbstractResumenViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Jorge
 * @param <Main, Detail> tipo de dato del modelo
 */
public abstract class AbstractListResumenViewPanel<Main, Detail> extends AbstractViewPanel {

    protected BindableTableModel<Main> modelMain;
    protected BindableTableModel<Detail> modelDetail;

    public AbstractListResumenViewPanel(AbstractResumenViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuClickDerecho = new javax.swing.JPopupMenu();
        jPanelControlesSuperiores = new javax.swing.JPanel();
        jLabelNombreTabla = MaterialComponentsFactory.Displayers.getH3Label();
        jToggleButtonDetail = new javax.swing.JToggleButton();
        jPanelTabla = MaterialComponentsFactory.Containers.getTransparentPanel();
        jScrollPaneMain = MaterialComponentsFactory.Containers.getScrollPane();
        jTableMain = new javax.swing.JTable();
        jPanelDetailPanel = MaterialComponentsFactory.Containers.getTransparentPanel();
        jScrollPaneDetail = MaterialComponentsFactory.Containers.getScrollPane();
        jTableDetail = new javax.swing.JTable();
        jPanelFootter = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabelTotal = new javax.swing.JLabel();
        jButtonImprimir = MaterialComponentsFactory.Buttons.getLinedButton();

        jPopupMenuClickDerecho.setInvoker(jTableMain);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 5, 15));
        setMinimumSize(getMinimumSize());
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanelControlesSuperiores.setOpaque(false);
        jPanelControlesSuperiores.setLayout(new java.awt.BorderLayout());

        jLabelNombreTabla.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelNombreTabla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNombreTabla.setText("Nombre Tabla");
        jPanelControlesSuperiores.add(jLabelNombreTabla, java.awt.BorderLayout.CENTER);

        jToggleButtonDetail.setText("Detalles");
        jToggleButtonDetail.setMaximumSize(new java.awt.Dimension(150, 32));
        jToggleButtonDetail.setMinimumSize(new java.awt.Dimension(150, 32));
        jToggleButtonDetail.setPreferredSize(new java.awt.Dimension(150, 32));
        jPanelControlesSuperiores.add(jToggleButtonDetail, java.awt.BorderLayout.EAST);

        add(jPanelControlesSuperiores, java.awt.BorderLayout.NORTH);

        jPanelTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelTabla.setOpaque(false);
        jPanelTabla.setLayout(new java.awt.CardLayout());

        jScrollPaneMain.setBorder(null);

        jTableMain.setAutoCreateRowSorter(true);
        jTableMain.setBackground(getBackground());
        jTableMain.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTableMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableMain.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableMain.setRowHeight(25);
        jTableMain.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableMain.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableMain.getTableHeader().setReorderingAllowed(false);
        jTableMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMainMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMainMouseReleased(evt);
            }
        });
        jScrollPaneMain.setViewportView(jTableMain);
        jTableMain.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanelTabla.add(jScrollPaneMain, "main");

        jPanelDetailPanel.setLayout(new java.awt.BorderLayout());

        jScrollPaneDetail.setBorder(null);

        jTableDetail.setAutoCreateRowSorter(true);
        jTableDetail.setBackground(getBackground());
        jTableDetail.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDetail.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableDetail.setRowHeight(25);
        jTableDetail.getTableHeader().setReorderingAllowed(false);
        jTableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDetailMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableDetailMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableDetailMouseReleased(evt);
            }
        });
        jScrollPaneDetail.setViewportView(jTableDetail);
        jTableDetail.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanelDetailPanel.add(jScrollPaneDetail, java.awt.BorderLayout.CENTER);

        jPanelTabla.add(jPanelDetailPanel, "detail");

        add(jPanelTabla, java.awt.BorderLayout.CENTER);

        jPanelFootter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 10, 15));
        jPanelFootter.setPreferredSize(new java.awt.Dimension(93, 60));
        jPanelFootter.setLayout(new java.awt.BorderLayout());

        jLabelTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotal.setText("xx.xx MN");
        jLabelTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0), "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanelFootter.add(jLabelTotal, java.awt.BorderLayout.WEST);

        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setPreferredSize(new java.awt.Dimension(140, 50));
        jPanelFootter.add(jButtonImprimir, java.awt.BorderLayout.EAST);

        add(jPanelFootter, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMouseClicked

    }//GEN-LAST:event_jTableMainMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void jTableMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMousePressed
    }//GEN-LAST:event_jTableMainMousePressed

    private void jTableMainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMouseReleased
    }//GEN-LAST:event_jTableMainMouseReleased

    private void jTableDetailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetailMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDetailMousePressed

    private void jTableDetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetailMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDetailMouseReleased

    private void jTableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetailMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDetailMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jButtonImprimir;
    protected javax.swing.JLabel jLabelNombreTabla;
    protected javax.swing.JLabel jLabelTotal;
    protected javax.swing.JPanel jPanelControlesSuperiores;
    protected javax.swing.JPanel jPanelDetailPanel;
    protected javax.swing.JPanel jPanelFootter;
    protected javax.swing.JPanel jPanelTabla;
    protected javax.swing.JPopupMenu jPopupMenuClickDerecho;
    protected javax.swing.JScrollPane jScrollPaneDetail;
    protected javax.swing.JScrollPane jScrollPaneMain;
    protected javax.swing.JTable jTableDetail;
    protected javax.swing.JTable jTableMain;
    protected javax.swing.JToggleButton jToggleButtonDetail;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        if (getPresenter().getFilterPresenter() != null) {
            jPanelDetailPanel.add(new FilterMainView(getPresenter().getFilterPresenter()), BorderLayout.EAST);
        }
        jTableMain.setRowSorter(modelMain.getSorter());
        jTableDetail.setRowSorter(modelDetail.getSorter());
        Bindings.bind(jTableMain,
                new SelectionInList(getPresenter().getModel(PROP_LISTAMAIN),
                        getPresenter().getModel(PROP_MAINSELECTED)));
        Bindings.bind(jTableDetail,
                new SelectionInList(getPresenter().getModel(PROP_LISTADETAIL)));
        Bindings.bind(jLabelNombreTabla, getPresenter().getModel(PROP_TITULO_VISTA));
        Bindings.bind(jLabelTotal, getPresenter().getModel(PROP_TOTAL_RESUMEN));

        Bindings.bind(jToggleButtonDetail, getPresenter().getModel(PROP_DETAILSELECTED));
        Bindings.bind(jButtonImprimir, "visible", getPresenter().getModel(PROP_DETAILSELECTED));
        jButtonImprimir.addActionListener((e) -> {
            MessageFormat m = new MessageFormat(jLabelNombreTabla.getText() + " (" + jLabelTotal.getText() + ")");
            try {
                jTableDetail.print(JTable.PrintMode.FIT_WIDTH, m, null);
            } catch (PrinterException ex) {
                Logger.getLogger(AbstractListResumenViewPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @Override
    public void uiInit() {
        initComponents();
        modelMain = generateMainTableModel();
        modelDetail = generateDetailTableModel();
        jTableMain.setModel(modelMain);
        jTableDetail.setModel(modelDetail);
//        jTableDetail.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        jTableDetail.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ExcelAdapter a = new ExcelAdapter(jTableDetail);

        setBackground(DefaultValues.SECONDARY_COLOR_LIGHT);

        getPresenter().addBeanPropertyChangeListener(PROP_DETAILSELECTED, (PropertyChangeEvent evt) -> {
            ((CardLayout) jPanelTabla.getLayout()).show(jPanelTabla, (boolean) evt.getNewValue() ? "detail" : "main");
        });
    }

    public abstract BindableTableModel<Main> generateMainTableModel();

    public abstract BindableTableModel<Detail> generateDetailTableModel();

    //
    // Getters Setters
    //
    @Override
    public AbstractResumenViewPresenter getPresenter() {
        return (AbstractResumenViewPresenter) super.getPresenter(); //To change body of generated methods, choose Tools | Templates.
    }
}
