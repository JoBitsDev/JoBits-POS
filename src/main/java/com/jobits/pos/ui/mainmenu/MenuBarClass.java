/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.mainmenu;

import com.jobits.pos.ui.mainmenu.presenter.MenuBarClassPresenter;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.DefaultValues;
import static com.jobits.pos.ui.mainmenu.presenter.MenuBarClassPresenter.*;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

/**
 *
 * @author Home
 */
public class MenuBarClass extends AbstractViewPanel {

    public static final String VIEW_NAME = "Menu Bar Class";

    private static MenuBarClass menuBar;

    public MenuBarClass(AbstractViewPresenter presenter) {
        super(presenter);
    }

    public static MenuBarClass getInstance() {
        if (menuBar == null) {
            menuBar = new MenuBarClass(new MenuBarClassPresenter());
        }
        return menuBar;
    }

    public JMenuBar getMainManuBar() {
        return jMenuBarMainManuBar;
    }

    // Variables declaration - do not modify                
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemOcultarBarraEstado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemOcultarBarraLateral;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemSiemprePrimerPlano;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarMainManuBar;
    private javax.swing.JMenu jMenuEdicion;
    private javax.swing.JMenu jMenuHerramientas;
    private javax.swing.JMenuItem jMenuItemAcercaJobitPOS;
    private javax.swing.JMenuItem jMenuItemActivarLicencia;
    private javax.swing.JMenuItem jMenuItemCambiarUsuario;
    private javax.swing.JMenuItem jMenuItemCerrarSesion;
    private javax.swing.JMenuItem jMenuItemCopiasSeguridad;
    private javax.swing.JMenuItem jMenuItemDuplicar;
    private javax.swing.JMenuItem jMenuItemEliminar;
    private javax.swing.JMenuItem jMenuItemManualUsuario;
    private javax.swing.JMenuItem jMenuItemNuevo;
    private javax.swing.JMenuItem jMenuItemPreferencias;
    private javax.swing.JMenuItem jMenuItemReportarBug;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSeleccionarTodo;
    private javax.swing.JMenuItem jMenuItemUbicaciones;
    private javax.swing.JMenu jMenuJoBitsPOS;
    private javax.swing.JMenu jMenuVista;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration    

    @Override
    public void wireUp() {
        //JoBits POS
        jMenuItemAcercaJobitPOS.addActionListener(getPresenter().getOperation(ACTION_SHOW_ACERCA_DE));
        jMenuItemPreferencias.addActionListener(getPresenter().getOperation(ACTION_SHOW_PREFERENCIAS));
        jMenuItemReportarBug.addActionListener(getPresenter().getOperation(ACTION_SHOW_REPORTAR_BUG));
        jMenuItemCambiarUsuario.addActionListener(getPresenter().getOperation(ACTION_SHOW_LOGIN));
        jMenuItemCerrarSesion.addActionListener(getPresenter().getOperation(ACTION_CERRAR_SESION));
        jMenuItemSalir.addActionListener(getPresenter().getOperation(ACTION_SALIR));
        //Edicion
//        jMenuItemSalir.addActionListener(getPresenter().getOperation(ACTION_SALIR));
//        jMenuItemSalir.addActionListener(getPresenter().getOperation(ACTION_SALIR));
//        jMenuItemSalir.addActionListener(getPresenter().getOperation(ACTION_SALIR));
//        jMenuItemSalir.addActionListener(getPresenter().getOperation(ACTION_SALIR));
        //Vista
        jCheckBoxMenuItemOcultarBarraEstado.addActionListener(getPresenter().getOperation(ACTION_OCULTAR_STATUS_BAR));
        jCheckBoxMenuItemOcultarBarraLateral.addActionListener(getPresenter().getOperation(ACTION_OCULTAR_MENU_LATERAL));
        jCheckBoxMenuItemSiemprePrimerPlano.addActionListener(getPresenter().getOperation(ACTION_SIEMPRE_PRIMER_PLANO));
        //Herramientas
        jMenuItemCopiasSeguridad.addActionListener(getPresenter().getOperation(ACTION_SHOW_COPIAS_SEGURIDAD));
        jMenuItemUbicaciones.addActionListener(getPresenter().getOperation(ACTION_SHOW_UBICACIONES));
        jMenuItemActivarLicencia.addActionListener(getPresenter().getOperation(ACTION_SHOW_ACTIVAR_LICENCIA));
        //Ayuda
        jMenuItemManualUsuario.addActionListener(getPresenter().getOperation(ACTION_SHOW_MANUAL_USUARIO));
    }

    @Override
    public void uiInit() {
        jMenuBarMainManuBar = new javax.swing.JMenuBar();
        jMenuJoBitsPOS = new javax.swing.JMenu();
        jMenuItemAcercaJobitPOS = new javax.swing.JMenuItem();
        jMenuItemPreferencias = new javax.swing.JMenuItem();
        jMenuItemReportarBug = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCerrarSesion = new javax.swing.JMenuItem();
        jMenuItemCambiarUsuario = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuEdicion = new javax.swing.JMenu();
        jMenuItemNuevo = new javax.swing.JMenuItem();
        jMenuItemDuplicar = new javax.swing.JMenuItem();
        jMenuItemEliminar = new javax.swing.JMenuItem();
        jMenuItemSeleccionarTodo = new javax.swing.JMenuItem();
        jMenuVista = new javax.swing.JMenu();
        jCheckBoxMenuItemOcultarBarraLateral = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemOcultarBarraEstado = new javax.swing.JCheckBoxMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItemSiemprePrimerPlano = new javax.swing.JCheckBoxMenuItem();
        jMenuHerramientas = new javax.swing.JMenu();
        jMenuItemCopiasSeguridad = new javax.swing.JMenuItem();
        jMenuItemUbicaciones = new javax.swing.JMenuItem();
        jMenuItemActivarLicencia = new javax.swing.JMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemManualUsuario = new javax.swing.JMenuItem();

        jMenuJoBitsPOS.setBackground(DefaultValues.WHITE);
        jMenuEdicion.setBackground(DefaultValues.WHITE);
        jMenuVista.setBackground(DefaultValues.WHITE);
        jMenuHerramientas.setBackground(DefaultValues.WHITE);
        jMenuAyuda.setBackground(DefaultValues.WHITE);
        jMenuBarMainManuBar.setBackground(DefaultValues.WHITE);

        jMenuJoBitsPOS.setText("JoBits POS");

        jMenuItemAcercaJobitPOS.setText("Acerca de JoBits POS");
        jMenuItemAcercaJobitPOS.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/acerca_color.png")));

        jMenuJoBitsPOS.add(jMenuItemAcercaJobitPOS);

        jMenuItemPreferencias.setText("Preferencias");
        jMenuItemPreferencias.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/configuracion_color.png")));
        jMenuJoBitsPOS.add(jMenuItemPreferencias);

        jMenuItemReportarBug.setText("Reportar Bug");
        jMenuItemReportarBug.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/reportarBug_color.png")));
        jMenuJoBitsPOS.add(jMenuItemReportarBug);
        jMenuJoBitsPOS.add(jSeparator1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jMenuItemCerrarSesion.setText(bundle.getString("label_cerrar_sesion")); // NOI18N
        jMenuItemCerrarSesion.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/bloquear_color.png")));
        jMenuJoBitsPOS.add(jMenuItemCerrarSesion);

        jMenuItemCambiarUsuario.setText("Cambiar de Usuario");
        jMenuItemCambiarUsuario.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/usuario_color.png")));
        jMenuJoBitsPOS.add(jMenuItemCambiarUsuario);
        jMenuJoBitsPOS.add(jSeparator2);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/apagar_color.png")));
        jMenuJoBitsPOS.add(jMenuItemSalir);

        jMenuBarMainManuBar.add(jMenuJoBitsPOS);

//        jMenuEdicion.setText(bundle.getString("label_edicion")); // NOI18N
//
//        jMenuItemNuevo.setText("Nuevo");
//        jMenuEdicion.add(jMenuItemNuevo);
//
//        jMenuItemDuplicar.setText("Duplicar");
//        jMenuEdicion.add(jMenuItemDuplicar);
//
//        jMenuItemEliminar.setText("Eliminar");
//        jMenuEdicion.add(jMenuItemEliminar);
//
//        jMenuItemSeleccionarTodo.setText("Seleccionar Todo");
//        jMenuEdicion.add(jMenuItemSeleccionarTodo);
//        jMenuBarMainManuBar.add(jMenuEdicion);
////        TODO: Agregar Seccion de Edicion en el MenuBar
        jMenuVista.setText("Vista");

        jCheckBoxMenuItemOcultarBarraLateral.setSelected(true);
        jCheckBoxMenuItemOcultarBarraLateral.setText("Mostrar Barra Lateral");
        jCheckBoxMenuItemOcultarBarraLateral.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/dashboard_color.png")));
        jMenuVista.add(jCheckBoxMenuItemOcultarBarraLateral);

        jCheckBoxMenuItemOcultarBarraEstado.setSelected(true);
        jCheckBoxMenuItemOcultarBarraEstado.setText("Mostrar Barra de Estado");
        jCheckBoxMenuItemOcultarBarraEstado.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/status_bar_color.png")));
        jMenuVista.add(jCheckBoxMenuItemOcultarBarraEstado);
        jMenuVista.add(jSeparator3);

        jCheckBoxMenuItemSiemprePrimerPlano.setSelected(false);
        jCheckBoxMenuItemSiemprePrimerPlano.setText("Siempre en Primer Plano");
        jCheckBoxMenuItemSiemprePrimerPlano.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/primer_plano_color.png")));
        jMenuVista.add(jCheckBoxMenuItemSiemprePrimerPlano);

        jMenuBarMainManuBar.add(jMenuVista);

        jMenuHerramientas.setText("Herramientas");

        jMenuItemCopiasSeguridad.setText("Copias de Seguridad");
        jMenuItemCopiasSeguridad.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/backup_color.png")));
        jMenuHerramientas.add(jMenuItemCopiasSeguridad);

        jMenuItemUbicaciones.setText("Ubicaciones");
        jMenuItemUbicaciones.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/ubicacion_color.png")));
        jMenuHerramientas.add(jMenuItemUbicaciones);

        jMenuItemActivarLicencia.setText("Activar Licencia");
        jMenuItemActivarLicencia.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/licencia_color.png")));
        jMenuHerramientas.add(jMenuItemActivarLicencia);

        jMenuBarMainManuBar.add(jMenuHerramientas);

        jMenuAyuda.setText("Ayuda");

        jMenuItemManualUsuario.setText("Manual de Usuario");
        jMenuItemManualUsuario.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/manual_usuario_color.png")));
        jMenuAyuda.add(jMenuItemManualUsuario);

        jMenuBarMainManuBar.add(jMenuAyuda);

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}
