/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views.util;

import com.jidesoft.hints.ListDataIntelliHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.JTextComponent;

/**
 * FirstDream
 * @author Jorge
 * 
 */
 public class RestaurantManagerListIntelliHint<K> extends ListDataIntelliHints<K> {

        public RestaurantManagerListIntelliHint(JTextComponent comp, List<K> completionList) {
            super(comp, completionList);
        }

        @Override
        public boolean updateHints(Object context) {
            ArrayList<K> ret = new ArrayList<>();
            for (K x : getCompletionList()) {
                if (x.toString().toLowerCase().contains(context.toString().toLowerCase())) {
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
