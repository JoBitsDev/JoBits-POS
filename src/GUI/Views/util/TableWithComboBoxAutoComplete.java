/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.util;

import com.jidesoft.hints.ListDataIntelliHints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import restManager.exceptions.NoSelectedException;
import restManager.util.RestManagerAbstractTableModel;

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
    private final JTextField jTextField;
    private final RestManagerAbstractTableModel<T> tableModel;
    private final RestaurantManagerListIntelliHint<K> items;
    private final List<K> itemList;

    public TableWithComboBoxAutoComplete(JTable table, JButton addButton, JButton removeButton,
            JTextField autoCompleteTextField,
            RestManagerAbstractTableModel<T> tableModel, List<K> itemsList) {
        this.table = table;
        this.addButton = addButton;
        this.removeButton = removeButton;
        this.jTextField = autoCompleteTextField;
        this.tableModel = tableModel;
        this.itemList = itemsList;
        this.items = new RestaurantManagerListIntelliHint<>(jTextField, itemsList);
        this.addButton.addActionListener((ActionEvent e) -> {
            addFromAutoComplete();
        });
        this.removeButton.addActionListener((ActionEvent e) -> {
            removeObjectAtSelectedRow();
        });
        this.table.setModel(this.tableModel);
    }

    public T addFromAutoComplete() {
       jTextField.setText(jTextField.getText().trim());
        K selected = findSelected(jTextField.getText());
        if (selected == null) {
            throw new NoSelectedException(table);
        }
        T transformedInstance = transformK_to_T(selected);
        if (transformedInstance != null) {
            tableModel.addObject(transformedInstance);
        }
        return transformedInstance;
    }

    public void removeObjectAtSelectedRow() {
        tableModel.removeObjectAtSelectedRow();
    }

    /**
     * Transform de data obtained in the combo box model to the table model;
     *
     * @param selected the selected combo box data
     * @return the data to put in the table
     */
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

    public RestManagerAbstractTableModel<T> getTableModel() {
        return tableModel;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public RestaurantManagerListIntelliHint<K> getItems() {
        return items;
    }

    private K findSelected(String text) {
        for (K k : itemList) {
            if(k.toString().equals(text)){
                return k;
            }
        }
        return null;
    }

    public class RestaurantManagerListIntelliHint<K> extends ListDataIntelliHints<K> {

        public RestaurantManagerListIntelliHint(JTextComponent comp, List<K> completionList) {
            super(comp, completionList);
        }

        @Override
        public boolean updateHints(Object context) {
            ArrayList<K> ret = new ArrayList<>();
            for (K x : getCompletionList()) {
                if(x.toString().toLowerCase().contains(context.toString().toLowerCase())){
                    ret.add(x);
                }
            }
            setListData(ret.toArray());
            return true;
        }
           
        @Override
        public K getSelectedHint() {
            return (K) super.getSelectedHint(); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
