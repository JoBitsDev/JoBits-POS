/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import restManager.exceptions.DuplicatedException;
import restManager.exceptions.NoSelectedException;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class RestManagerAbstractTableModel<T> extends AbstractTableModel {

    protected List<T> items;
    private final JTable table;

    public RestManagerAbstractTableModel(List<T> items, JTable table) {
        this.items = items;
        if(this.items == null){
           this.items = new ArrayList<>();
        }
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return items.size();
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
        return items.get(rowIndex);
    }

    public T getObjectAtSelectedRow() {
        if (table.getSelectedRow() == -1) {
            throw new NoSelectedException(table.getParent());
        }
        return items.get(table.convertRowIndexToModel(table.getSelectedRow()));
    }

    public T removeObjectAtSelectedRow() {
        if (table.getSelectedRow() == -1) {
            throw new NoSelectedException(table.getParent());
        }
        int itemToDelete = table.convertRowIndexToModel(table.getSelectedRow());
        T deleted = items.remove(itemToDelete);
        fireTableRowsDeleted(itemToDelete, itemToDelete);
        return deleted;
    }

    public void addObject(T object) {
        if(items.indexOf(object) != -1){
            throw new DuplicatedException(table.getParent());
        }
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

}
