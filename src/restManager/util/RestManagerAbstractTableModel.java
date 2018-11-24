/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.util;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
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
            throw new NoSelectedException();
        }
        return items.get(table.convertRowIndexToModel(table.getSelectedRow()));
    }

    public void removeObjectAtSelectedRow() {
        if (table.getSelectedRow() == -1) {
            throw new NoSelectedException();
        }
        int itemToDelete = table.convertRowIndexToModel(table.getSelectedRow());
        T deleted = items.remove(itemToDelete);
        fireTableRowsDeleted(itemToDelete, itemToDelete);
    }

    public void addObject(T object) {
        items.add(object);
        fireTableRowsInserted(items.size(), items.size());
    }

    public void removeObject(T object) {
        items.remove(object);
        fireTableDataChanged();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        fireTableDataChanged();
    }

}
