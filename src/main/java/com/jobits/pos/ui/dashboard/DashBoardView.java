/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.dashboard;

import com.jgoodies.forms.builder.ListViewBuilder;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;

/**
 *
 * @author Jorge
 */
public class DashBoardView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Dashboard";

    public DashBoardView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void wireUp() {

    }

    @Override
    public void uiInit() {
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
