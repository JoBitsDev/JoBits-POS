/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;
import restManager.persistencia.Venta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class RestManagerVentaTreeNode implements TreeNode {

    private RestManagerVentaTreeNode parent;
    private List<RestManagerVentaTreeNode> childrens;
    private int nivel;
    private Venta data;

    public RestManagerVentaTreeNode(Venta data) {
        this.data = data;
        nivel = 0;
        parent = null;
        childrens = new ArrayList<>();
    }

    public RestManagerVentaTreeNode(RestManagerVentaTreeNode parent, Venta data) {
        this.parent = parent;
        this.data = data;
        nivel = parent.getNivel() + 1;
        childrens = new ArrayList<>();
    }
    
    

    
    
    @Override
    public RestManagerVentaTreeNode getChildAt(int childIndex) {
        return childrens.get(childIndex);

    }

    @Override
    public int getChildCount() {
        return childrens.size();
    }

    @Override
    public RestManagerVentaTreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return childrens.indexOf(((RestManagerVentaTreeNode) node).getData());
    }

    @Override
    public boolean getAllowsChildren() {
        return nivel < 4;
    }

    @Override
    public boolean isLeaf() {
        return childrens.isEmpty();
    }

    @Override
    public Enumeration children() {
        return null;
    }

    public Venta getData() {
        return data;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getNivel() {
        return nivel;
    }
    
    

    public void insert(Venta data) {
        int compareResult = compareForInsert(getData(), data);
        switch(compareResult){
            case 1:
            getData().getOrdenList().addAll(data.getOrdenList());
            boolean deeper = false; 
                for (RestManagerVentaTreeNode x : childrens) {
                    if(x.compareForInsert(x.getData(), data) == 1){
                        deeper = true;
                        x.insert(data);
                    }
                }
                if(!deeper){
                    childrens.add(new RestManagerVentaTreeNode(this, data));
                }
        }
       
      
        
    }

    public int compare(Venta inTree, Venta v) {
        switch (nivel) {
            case 0:
                return 1;
            case 1:
                return Integer.compare(inTree.getFecha().getYear(), v.getFecha().getYear());
            case 2:
                return Integer.compare(inTree.getFecha().getMonth(), v.getFecha().getMonth());
            case 3:
                return Integer.compare(v.getFecha().getDate(), 15);
            case 4:
                return Integer.compare(inTree.getFecha().getDate(), v.getFecha().getDate());
            default:
                return -1;
        }

    }
    
       public int compareForInsert(Venta inTree, Venta v) {
       int ret = 1;
           switch (nivel) {
            case 0:
                return 1;
            case 1:
                 ret =  Integer.compare(inTree.getFecha().getYear(), v.getFecha().getYear());break;
            case 2:
                ret = Integer.compare(inTree.getFecha().getMonth(), v.getFecha().getMonth());break;
            case 3:
               ret =   Integer.compare(inTree.getFecha().getDate(), 15);break;
            case 4:
                ret = Integer.compare(inTree.getFecha().getDate(), v.getFecha().getDate());break;
        }
           return ret != 0 ? 0 : 1;

    }

    public void nivelar() {
        if (isLeaf()) {
            return;
        }
        Collections.sort(childrens, (o1, o2) -> {
        return compare(o1.getData(), o2.getData());
        });
        childrens.forEach((x) -> {
            x.nivelar();
        });
    }

}
