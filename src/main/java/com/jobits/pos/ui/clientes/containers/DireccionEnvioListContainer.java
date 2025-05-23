/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.containers;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.domain.DireccionEnvioDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.clientes.DireccionEnvioListView;
import com.jobits.pos.ui.clientes.presenter.DireccionEnvioListViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.utils.ComponentMover;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Home
 */
public class DireccionEnvioListContainer extends javax.swing.JDialog {

    ClienteUseCase service = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);
    DireccionEnvioListView listView;
    ClienteDomain cliente;
    DireccionEnvioDomain ret;

    /**
     * Creates new form ClienteSelectorView
     *
     * @param cliente
     */
    public DireccionEnvioListContainer(ClienteDomain cliente) {
        super();
        this.cliente = cliente;
        setModal(true);
        init();
    }

    public void init() {
        setUndecorated(true);
        setBackground(DefaultValues.TRANSPARENT);
        initComponents();
        intitUI();
        wireUp();
        setLocationRelativeTo(null);
        ComponentMover cr = new ComponentMover(this, jPanelList);
    }

    public DireccionEnvioDomain showView() {
        this.setVisible(true);
        return ret;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelList = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanelListMain = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelOpciones = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonCancelar = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonSeleccionar = MaterialComponentsFactory.Buttons.getAcceptButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelList.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelList.setPreferredSize(new java.awt.Dimension(650, 600));
        jPanelList.setLayout(new java.awt.BorderLayout());

        jPanelListMain.setLayout(new java.awt.BorderLayout());
        jPanelList.add(jPanelListMain, java.awt.BorderLayout.CENTER);

        jPanelOpciones.setMinimumSize(new java.awt.Dimension(239, 70));
        jPanelOpciones.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanelOpciones.setLayout(new java.awt.GridBagLayout());

        jButtonCancelar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanelOpciones.add(jButtonCancelar, new java.awt.GridBagConstraints());

        jButtonSeleccionar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonSeleccionar.setMnemonic('c');
        jButtonSeleccionar.setText(bundle.getString("label_crear_producto")); // NOI18N
        jButtonSeleccionar.setPreferredSize(new java.awt.Dimension(140, 40));
        jPanelOpciones.add(jButtonSeleccionar, new java.awt.GridBagConstraints());

        jPanelList.add(jPanelOpciones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelList, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JPanel jPanelList;
    private javax.swing.JPanel jPanelListMain;
    private javax.swing.JPanel jPanelOpciones;
    // End of variables declaration//GEN-END:variables

    private void selectValue() {
        ret = listView.getPresenter().getBean().getElemento_seleccionado();
        if (ret == null) {
            throw new IllegalArgumentException("Seleccione un cliente");
        }
        dispose();
    }

    private void intitUI() {
        listView = new DireccionEnvioListView(new DireccionEnvioListViewPresenter(cliente));
        listView.getjPanelOpciones().setVisible(false);
        listView.getjListDirecciones().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectValue();
                }
            }
        });
        jPanelListMain.add(listView, BorderLayout.CENTER);
    }

    private void wireUp() {
        jButtonCancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });
        jButtonSeleccionar.addActionListener((ActionEvent e) -> {
            selectValue();
        });

    }

}
