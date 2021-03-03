/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class NavigationNode {

    private NavigationNode parentNode;

    private String viewUIDName;

    public NavigationNode(NavigationNode parentNode, String viewUIDName) {
        this.parentNode = parentNode;
        this.viewUIDName = viewUIDName;
    }

    public static NavigationNode of(NavigationNode parentNode, String viewUIDName) {
        return new NavigationNode(parentNode, viewUIDName);
    }

    public NavigationNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(NavigationNode parentNode) {
        this.parentNode = parentNode;
    }

    public String getViewUIDName() {
        return viewUIDName;
    }

    public void setViewUIDName(String viewUIDName) {
        this.viewUIDName = viewUIDName;
    }

}
