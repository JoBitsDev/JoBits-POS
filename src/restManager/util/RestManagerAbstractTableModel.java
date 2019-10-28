/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
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
    protected final JTable table;
    private RestManagerTableRowFilter filter;
    private TableRowSorter<RestManagerAbstractTableModel<T>> sorter;

    public RestManagerAbstractTableModel(List<T> items, JTable table) {
        this.items = items;
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.table = table;
        initFilterAndSorter();
    }

    private void initFilterAndSorter() {
        sorter = new TableRowSorter<>(this);
        filter = new RestManagerTableRowFilter();
        sorter.setRowFilter(filter);
        table.setRowSorter(sorter);
    }

    /**
     * Shows the rows that contains the string passed by parameter this method
     * is case sensitive
     *
     * @param s the string to search in the table
     */
    public void filterByString(String s) {
        filter.setSearchParam(s);
        sort();

    }

    /**
     * Sorts and filters the rows in the view based on the sort keys of the
     * columns currently being sorted and the filter, if any, associated with
     * this sorter. An empty sortKeys list indicates that the view should
     * unsorted, the same as the model.
     */
    public void sort() {
        sorter.sort();
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
        if (items.indexOf(object) != -1) {
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

    public RestManagerTableRowFilter getFilter() {
        return filter;
    }
    
    public void setItems(List<T> items) {
        this.items = items;
        fireTableDataChanged();
    }

    public TableRowSorter<RestManagerAbstractTableModel<T>> getSorter() {
        return sorter;
    }

    public class RestManagerTableRowFilter extends RowFilter<RestManagerAbstractTableModel, Integer> {

        private String searchParam;

        public RestManagerTableRowFilter() {
            searchParam = "";
        }

        public void setSearchParam(String searchParam) {
            this.searchParam = searchParam;
        }

        @Override
        public boolean include(RowFilter.Entry<? extends RestManagerAbstractTableModel, ? extends Integer> entry) {
            if (searchParam.isEmpty()) {
                return true;
            }
            for (int i = entry.getValueCount() - 1; i >= 0; i--) {
                if (entry.getStringValue(i).toLowerCase().contains(searchParam.toLowerCase())) {
                    return true;

                }
            }
            return false;
        }

    }

}
