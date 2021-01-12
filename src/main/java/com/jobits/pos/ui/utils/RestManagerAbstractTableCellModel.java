/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.core.repo.impl.PropertyName;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class RestManagerAbstractTableCellModel<T> extends AbstractTableModel {

    protected List<T> items;
    protected List<T> selectedItems;
    private final JTable table;
    private PropertyChangeSupport selectionChange;

    public RestManagerAbstractTableCellModel(List<T> items, JTable table) {
        this.items = items;
        this.selectedItems = new ArrayList<>();
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.table = table;
//        this.table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                executeLogic(e);
//
//            }
//        });
        selectionChange = new PropertyChangeSupport(this);
    }

    @Override
    public int getRowCount() {
        if (items.isEmpty()) {
            return 0;
        }
        int ret = items.size() / getColumnCount();
        int resto = items.size() % getColumnCount();
        return resto != 0 ? ret + 1 : ret;

    }

    @Override
    public abstract int getColumnCount();

    @Override
    public abstract T getValueAt(int rowIndex, int columnIndex);

    @Override
    public abstract String getColumnName(int column);

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    public T getObjectAtCell(int rowIndex, int columnIndex) {
        return items.get(getColumnCount() * rowIndex + columnIndex);
    }

    public T getObjectAtSelectedCell() {
        if (table.getSelectedRow() == -1 || table.getSelectedColumn() == -1) {
            throw new NoSelectedException(table.getParent());
        }
        return getObjectAtCell(table.convertRowIndexToModel(table.getSelectedRow()), table.convertColumnIndexToModel(table.getSelectedColumn()));
    }

    public void removeObjectAtSelectedCell() {
        if (table.getSelectedRow() == -1 || table.getSelectedColumn() == -1) {
            throw new NoSelectedException(table.getParent());
        }
        int itemToDelete = table.convertRowIndexToModel(table.getSelectedRow());
        T deleted = items.remove(itemToDelete * getColumnCount() + table.getSelectedColumn());
        fireTableRowsDeleted(itemToDelete, itemToDelete);
    }

    public void addObject(T object) {
        items.add(object);
        fireTableRowsInserted(items.indexOf(object), items.indexOf(object));
    }

    public void removeObject(T object) {
        int index = items.indexOf(object);
        items.remove(object);
        fireTableRowsDeleted(index, index);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        fireTableDataChanged();
    }

    private void executeLogic(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int column = table.columnAtPoint(e.getPoint());
        T selectedObject = (T) getValueAt(
                table.convertRowIndexToModel(row),
                table.convertColumnIndexToModel(column));
        if (selectedObject == null) {
            return;
        }
        int index = selectedItems.indexOf(selectedObject);
        if (index != -1) {
            selectedItems.remove(selectedObject);
            
            selectionChange.firePropertyChange(PropertyName.DELETE.toString(), selectedObject, null);
        } else {
            selectedItems.add(selectedObject);
            selectionChange.firePropertyChange(PropertyName.CREATE.toString(), null, selectedObject);

        }
    }

    public void addSelectionListener(PropertyChangeListener listener) {
        selectionChange.addPropertyChangeListener(listener);
    }

    public List<T> getSelectedItems() {
      ArrayList<T> ret = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                if(table.isCellSelected(i, j)){
                    ret.add(getValueAt(i, j));
                }
            }
        }
        return ret;     
    }

}
