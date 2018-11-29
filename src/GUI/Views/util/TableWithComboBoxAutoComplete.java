/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.util;

import com.jidesoft.swing.AutoCompletionComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.ProductoVenta;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerComboBoxModel;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <K> the combo box type
 * @param <T> the table type
 *
 */
public abstract class TableWithComboBoxAutoComplete<K, T> {

    private final JTable table;
    private final JButton addButton;
    private final JButton removeButton;
    private final AutoCompletionComboBox jComboBox;
    private final RestManagerAbstractTableModel<T> tableModel;
    private final RestManagerComboBoxModel<K> comboModel;

    public TableWithComboBoxAutoComplete(JTable table, JButton addButton, JButton removeButton,
            AutoCompletionComboBox autoCompleteComboBox,
            RestManagerAbstractTableModel<T> tableModel, RestManagerComboBoxModel<K> comboModel) {
        this.table = table;
        this.addButton = addButton;
        this.removeButton = removeButton;
        this.jComboBox = autoCompleteComboBox;
        this.tableModel = tableModel;
        this.comboModel = comboModel;
        this.addButton.addActionListener((ActionEvent e) -> {
            addFromComboBox();
        });
        this.removeButton.addActionListener((ActionEvent e) -> {
            removeObjectAtSelectedRow();
        });
        this.table.setModel(this.tableModel);
        this.jComboBox.setModel(this.comboModel);
    }

    public T addFromComboBox() {
        K selected = (K) jComboBox.getSelectedItem();
        if (selected == null) {
            throw new NoSelectedException(table);
        }
        T transformedInstance = transformK_to_T(selected);
        tableModel.addObject(transformedInstance);
        return transformedInstance;
    }

    public void removeObjectAtSelectedRow() {
        tableModel.removeObjectAtSelectedRow();
    }

    public abstract T transformK_to_T(K selected);

    //
    //Getters
    //

    public JTable getTable() {
        return table;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public AutoCompletionComboBox getjComboBox() {
        return jComboBox;
    }

    public RestManagerAbstractTableModel<T> getTableModel() {
        return tableModel;
    }

    public RestManagerComboBoxModel<K> getComboModel() {
        return comboModel;
    }
    
    
    
}
