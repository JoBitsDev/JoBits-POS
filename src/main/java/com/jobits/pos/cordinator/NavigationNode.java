/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

import com.jobits.pos.ui.View;
import java.util.List;
import lombok.Data;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@Data
public class NavigationNode {

    private NavigationNode parentNode;

    private View view;

    private NavigationNode(NavigationNode parentNode, View view) {
        this.parentNode = parentNode;
        this.view = view;
    }

    public static NavigationNode of(NavigationNode parentNode, View view) {
        return new NavigationNode(parentNode, view);
    }

}
