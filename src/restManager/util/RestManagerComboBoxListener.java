/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.util;

import GUI.Components.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public class RestManagerComboBoxListener<T> extends KeyAdapter {

    private final JComboBox<T> comboBox;

    private final RestManagerComboBoxModel<T> model;

    public RestManagerComboBoxListener(JComboBox comboBox, RestManagerComboBoxModel model) {
        this.comboBox = comboBox;
        this.model = model;
    }

    public RestManagerComboBoxListener(JComboBox comboBox) {
        this.comboBox = comboBox;
        this.model = (RestManagerComboBoxModel) comboBox.getModel();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        String text = ((JTextField) e.getSource()).getText();

        comboBox.setModel(new RestManagerComboBoxModel<>(getFilteredList(text)));
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
        comboBox.showPopup();
    }

    private List<T> getFilteredList(String text) {
        List<T> ret = new ArrayList<>();
        for (int i = 0; i < model.getObjects().size(); i++) {
            if (model.getObjects().get(i).toString().toLowerCase().contains(text.toLowerCase())) {
                ret.add(model.getObjects().get(i));
            }
        }
        return ret;
    }

}
