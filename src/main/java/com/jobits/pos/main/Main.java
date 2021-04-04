/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {

        Application app = Application.getInstance();

        SplashScreen sp = new SplashScreen();
        app.init();
        sp.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            app.start();
        });

    }
}
