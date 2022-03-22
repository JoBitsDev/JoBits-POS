/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen;

import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.almacen.presenter.OperacionesListPresenter;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 *
 * @author Home
 */
public class PendingOperationsListView extends AbstractListViewPanel<Operacion> {

    public static final String VIEW_NAME = "Operaciones Pendientes";
    public javax.swing.JPanel jPanelOptionsButtons = MaterialComponentsFactory.Containers.getPrimaryPanel();
    protected javax.swing.JButton jButtonCerrar = MaterialComponentsFactory.Buttons.getOutlinedButton();
    protected javax.swing.JButton jButtonAbrirParaConfirmar = MaterialComponentsFactory.Buttons.getOutlinedButton();

    public PendingOperationsListView(AbstractListViewPresenter presenter) {
        super(presenter);

        jButtonEdit.setVisible(false);
        jButtonAdd.setVisible(false);
        jButtonDelete.setVisible(false);
        setPreferredSize(new Dimension(1200, 650));

        jPanelOptionsButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        jButtonCerrar.setPreferredSize(new java.awt.Dimension(125, 50));
        jButtonCerrar.setAction(getPresenter().getOperation(OperacionesListPresenter.ACTION_CERRAR_POPUP));
        jPanelOptionsButtons.add(jButtonCerrar);
        jButtonAbrirParaConfirmar.setPreferredSize(new java.awt.Dimension(125, 50));
        jButtonAbrirParaConfirmar.setAction(getPresenter().getOperation(OperacionesListPresenter.ACTION_ABRIR_Y_CONFIRMAR));
        jPanelOptionsButtons.add(jButtonAbrirParaConfirmar);

        add(jPanelOptionsButtons, BorderLayout.SOUTH);

        jTableList.getColumnModel().getColumn(0).setMaxWidth(250);
        jTableList.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTableList.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableList.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTableList.getColumnModel().getColumn(3).setPreferredWidth(100);


    }

    @Override
    public BindableTableModel<Operacion> generateTableModel() {
        return new BindableTableModel<Operacion>(jTableList) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getNoRecibo();
                    case 1:
                        return R.DATE_FORMAT.format(getRow(rowIndex).getFecha()) + " "
                                + R.TIME_FORMAT.format(getRow(rowIndex).getHora());
                    case 2:
                        return getRow(rowIndex).getRecibidoPor();
                    case 3:
                        Transaccion t = getRow(rowIndex).getTransaccionList().get(0);
                        if (t.getTransaccionEntrada() != null) {
                            return "ENTRADA (Total: " + t.getTransaccionEntrada().getValorTotal() + R.COIN_SUFFIX + ")";
                        }
                        if (t.getTransaccionMerma() != null) {
                            String value;
                            value = t.getTransaccionMerma().isEsMerma() ? "REBAJA (MERMA)" : "REBAJA";
                            value += ": (" + t.getTransaccionMerma().getRazon().toUpperCase() + ")";
                            return value;
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
                        return "TRASPASO DE IPV";
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "No. Factura";
                    case 1:
                        return "Fecha";
                    case 2:
                        return "Descripcion";
                    case 3:
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
