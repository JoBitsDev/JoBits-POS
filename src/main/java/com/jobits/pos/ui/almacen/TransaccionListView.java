/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen;

import com.jhw.swing.material.standars.MaterialIcons;
import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractListViewPanel;
import static com.jobits.pos.ui.almacen.presenter.TransaccionListPresenter.*;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 *
 * @author Home
 */
public class TransaccionListView extends AbstractListViewPanel<Transaccion> {

    public static final String VIEW_NAME = "Transacciones";
    public javax.swing.JPanel jPanelOptionsButtons = MaterialComponentsFactory.Containers.getPrimaryPanel();
    protected javax.swing.JButton jButtonCerrar = MaterialComponentsFactory.Buttons.getOutlinedButton();
    protected javax.swing.JButton jButtonPrint = MaterialComponentsFactory.Buttons.getOutlinedButton();

    public TransaccionListView(AbstractListViewPresenter presenter) {
        super(presenter);

        jButtonEdit.setVisible(false);
        jButtonAdd.setVisible(false);
        jButtonDelete.setVisible(false);
        setPreferredSize(new Dimension(1200, 650));

        jPanelOptionsButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        jButtonCerrar.setPreferredSize(new java.awt.Dimension(125, 50));
        jButtonCerrar.setAction(getPresenter().getOperation(ACTION_CERRAR_POPUP));
        jPanelOptionsButtons.add(jButtonCerrar);

        jButtonPrint.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonPrint.setAction(getPresenter().getOperation(ACTION_IMPRIMIR_TRANSACCIONES));
        jButtonPrint.setIcon(MaterialIcons.PRINT);
        jXPanelControles.add(jButtonPrint);

        add(jPanelOptionsButtons, BorderLayout.SOUTH);

        jTableList.getColumnModel().getColumn(0).setMaxWidth(250);
        jTableList.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTableList.getColumnModel().getColumn(1).setMaxWidth(150);
        jTableList.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableList.getColumnModel().getColumn(2).setMaxWidth(150);
        jTableList.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTableList.getColumnModel().getColumn(3).setMaxWidth(150);
        jTableList.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTableList.getColumnModel().getColumn(4).setMaxWidth(150);
        jTableList.getColumnModel().getColumn(4).setPreferredWidth(150);

    }

    @Override
    public BindableTableModel<Transaccion> generateTableModel() {
        return new BindableTableModel<Transaccion>(jTableList) {
            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ((Transaccion) getListModel().getElementAt(rowIndex)).getInsumocodInsumo().getNombre();
                    case 1:
                        return R.DATE_FORMAT.format(((Transaccion) getListModel().getElementAt(rowIndex)).getFecha());
                    case 2:
                        return R.TIME_FORMAT.format(((Transaccion) getListModel().getElementAt(rowIndex)).getHora());
                    case 3:
                        return ((Transaccion) getListModel().getElementAt(rowIndex)).getCantidad();
                    case 4:
                        return ((Transaccion) getListModel().getElementAt(rowIndex)).getDescripcion();
                    case 5:
                        Transaccion t = ((Transaccion) getListModel().getElementAt(rowIndex));
                        if (t.getTransaccionEntrada() != null) {
                            return "ENTRADA (Total: " + t.getTransaccionEntrada().getValorTotal() + R.COIN_SUFFIX + ")";
                        }
                        if (t.getTransaccionMerma() != null) {
                            return "REBAJA: (" + t.getTransaccionMerma().getRazon().toUpperCase() + ")";
                        }
                        if (t.getTransaccionSalida() != null) {
                            return "SALIDA: " + t.getTransaccionSalida().getCocinacodCocina();
                        }
                        if (t.getTransaccionTraspaso() != null) {
                            return "TRASPASO: (" + t.getTransaccionTraspaso().getAlmacenDestino() + ")";
                        }
                        if (t.getTransaccionTransformacionList() != null) {
                            if (!t.getTransaccionTransformacionList().isEmpty()) {
                                return "TRANSFORMACION: ";
                            }
                        }
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Insumo";
                    case 1:
                        return "Fecha";
                    case 2:
                        return "Hora";
                    case 3:
                        return "Cantidad";
                    case 4:
                        return java.util.ResourceBundle.getBundle("Strings").getString("label_descripcion");
                    case 5:
                        return "Tipo";
                    default:
                        return null;
                }
            }

        };
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}
