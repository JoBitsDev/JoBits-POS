/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public class RestManagerComboBoxModel<T> extends AbstractListModel<T> implements MutableComboBoxModel<T> {

    List<T> objects;
    Object selectedObject;

    public RestManagerComboBoxModel() {
        objects = new ArrayList<>();
    }

    public RestManagerComboBoxModel(List<T> elements) {
        this.objects = elements;
    }

    @Override
    public void addElement(T item) {
        objects.add(item);
        fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
        if (objects.size() == 1 && selectedObject == null && item != null) {
            setSelectedItem(item);
        }

    }

    @Override
    public void removeElement(Object obj) {
        int index = objects.indexOf(obj);
        if (index != -1) {
            removeElementAt(index);
        }
    }

    @Override
    public void insertElementAt(T item, int index) {
        objects.set(index, item);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        if (getElementAt(index) == selectedObject) {
            if (index == 0) {
                setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
            } else {
                setSelectedItem(getElementAt(index - 1));
            }
        }

        objects.remove(index);

        fireIntervalRemoved(this, index, index);
    }

    @Override
    public void setSelectedItem(Object anObject) {
        if ((selectedObject != null && !selectedObject.equals(anObject))
                || selectedObject == null && anObject != null) {
            selectedObject = anObject;
            fireContentsChanged(this, -1, -1);
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedObject;
    }

    @Override
    public int getSize() {
        return objects.size();
    }

    @Override
    public T getElementAt(int index) {
        if (index >= 0 && index < objects.size()) {
            return objects.get(index);
        } else {
            return null;
        }
    }

    public List<T> getObjects() {
        return objects;
    }
}
