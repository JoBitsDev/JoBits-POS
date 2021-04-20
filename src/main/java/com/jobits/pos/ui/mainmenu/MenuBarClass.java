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
import com.jobits.ui.components.MaterialComponentsFactory;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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

    public JLabel getjLabelFecha() {
        return jLabelFecha;
    }

    public JLabel getjLabelFechaText() {
        return jLabelFechaText;
    }

    // Variables declaration - do not modify                
    private javax.swing.JMenuBar jMenuBarMainManuBar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemOcultarBarraEstado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemOcultarBarraLateral;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemSiemprePrimerPlano;
    private javax.swing.JButton jButtonRefrescarVista;
    private javax.swing.JMenu jMenuJoBitsPOS;
    private javax.swing.JMenu jMenuVista;
    private javax.swing.JMenu jMenuHerramientas;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenu jMenuVenta;
    private javax.swing.JMenuItem jMenuItemAcercaJobitPOS;
    private javax.swing.JMenuItem jMenuItemReabrirVenta;
    private javax.swing.JMenuItem jMenuItemTerminarVenta;
    private javax.swing.JMenuItem jMenuItemTerminarExportarVenta;
    private javax.swing.JMenuItem jMenuItemActivarLicencia;
    private javax.swing.JMenuItem jMenuItemCambiarUsuario;
    private javax.swing.JMenuItem jMenuItemCerrarSesion;
    private javax.swing.JMenuItem jMenuItemCopiasSeguridad;
    private javax.swing.JMenuItem jMenuItemManualUsuario;
    private javax.swing.JMenuItem jMenuItemPreferencias;
    private javax.swing.JMenuItem jMenuItemReportarBug;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemCambiarTurno;
    private javax.swing.JMenuItem jMenuItemNuevoTurno;
    private javax.swing.JMenuItem jMenuItemUbicaciones;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;

    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFechaText;
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
        //Venta
        jMenuItemCambiarTurno.addActionListener(getPresenter().getOperation(ACTION_CAMBIAR_TURNO));
        jMenuItemNuevoTurno.addActionListener(getPresenter().getOperation(ACTION_NUEVO_TURNO));
        jMenuItemReabrirVenta.addActionListener(getPresenter().getOperation(ACTION_REABRIR_VENTA));
        jMenuItemTerminarVenta.addActionListener(getPresenter().getOperation(ACTION_TERMINAR_VENTA));
        jMenuItemTerminarExportarVenta.addActionListener(getPresenter().getOperation(ACTION_TERMINAR_EXPORTAR_VENTA));
        //Vista
        jButtonRefrescarVista.addActionListener(getPresenter().getOperation(ACTION_REFRESCAR_VISTA));
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
        jMenuVista = new javax.swing.JMenu();
        jMenuHerramientas = new javax.swing.JMenu();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuVenta = new javax.swing.JMenu();

        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuItemAcercaJobitPOS = new javax.swing.JMenuItem();
        jMenuItemPreferencias = new javax.swing.JMenuItem();
        jMenuItemReportarBug = new javax.swing.JMenuItem();
        jMenuItemCerrarSesion = new javax.swing.JMenuItem();
        jMenuItemCambiarUsuario = new javax.swing.JMenuItem();
        jMenuItemCopiasSeguridad = new javax.swing.JMenuItem();
        jMenuItemUbicaciones = new javax.swing.JMenuItem();
        jMenuItemActivarLicencia = new javax.swing.JMenuItem();
        jMenuItemManualUsuario = new javax.swing.JMenuItem();
        jMenuItemReabrirVenta = new javax.swing.JMenuItem();
        jMenuItemTerminarVenta = new javax.swing.JMenuItem();
        jMenuItemTerminarExportarVenta = new javax.swing.JMenuItem();
        jMenuItemNuevoTurno = new javax.swing.JMenuItem();
        jMenuItemCambiarTurno = new javax.swing.JMenuItem();

        jLabelFecha = new javax.swing.JLabel();
        jLabelFechaText = new javax.swing.JLabel();

        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();

        jCheckBoxMenuItemOcultarBarraLateral = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemOcultarBarraEstado = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemSiemprePrimerPlano = new javax.swing.JCheckBoxMenuItem();

        jButtonRefrescarVista = new javax.swing.JButton();

        jMenuBarMainManuBar.setBackground(DefaultValues.WHITE);
        jMenuJoBitsPOS.setBackground(DefaultValues.WHITE);
        jMenuVista.setBackground(DefaultValues.WHITE);
        jMenuVenta.setBackground(DefaultValues.WHITE);
        jMenuHerramientas.setBackground(DefaultValues.WHITE);
        jMenuAyuda.setBackground(DefaultValues.WHITE);

        jMenuJoBitsPOS.setText("JoBits POS");
        jMenuItemAcercaJobitPOS.setText("Acerca de JoBits POS");
        jMenuItemAcercaJobitPOS.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/acerca_color.png")));
        jMenuJoBitsPOS.add(jMenuItemAcercaJobitPOS);

        jMenuItemPreferencias.setText("Preferencias");
        jMenuItemPreferencias.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/configuracion_color.png")));
        jMenuJoBitsPOS.add(jMenuItemPreferencias);

        jMenuItemReportarBug.setText("Reportar Bug");
        jMenuItemReportarBug.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/reportarBug_color.png")));
        jMenuJoBitsPOS.add(jMenuItemReportarBug);
        jMenuJoBitsPOS.add(jSeparator1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jMenuItemCerrarSesion.setText(bundle.getString("label_cerrar_sesion")); // NOI18N
        jMenuItemCerrarSesion.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/bloquear_color.png")));
        jMenuJoBitsPOS.add(jMenuItemCerrarSesion);

        jMenuItemCambiarUsuario.setText("Cambiar de Usuario");
        jMenuItemCambiarUsuario.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/usuario_color.png")));
        jMenuJoBitsPOS.add(jMenuItemCambiarUsuario);
        jMenuJoBitsPOS.add(jSeparator2);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.setIcon(new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/apagar_color.png")));
        jMenuJoBitsPOS.add(jMenuItemSalir);

        jMenuBarMainManuBar.add(jMenuJoBitsPOS);

        jMenuVista.setText("Vista");
        jCheckBoxMenuItemOcultarBarraLateral.setSelected(true);
        jCheckBoxMenuItemOcultarBarraLateral.setText("Mostrar Barra Lateral");
        jCheckBoxMenuItemOcultarBarraLateral.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/dashboard_color.png")));
        jCheckBoxMenuItemOcultarBarraLateral.setBackground(Color.white);
        jMenuVista.add(jCheckBoxMenuItemOcultarBarraLateral);

        jCheckBoxMenuItemOcultarBarraEstado.setSelected(true);
        jCheckBoxMenuItemOcultarBarraEstado.setText("Mostrar Barra de Estado");
        jCheckBoxMenuItemOcultarBarraEstado.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/status_bar_color.png")));
        jCheckBoxMenuItemOcultarBarraEstado.setBackground(Color.white);
        jMenuVista.add(jCheckBoxMenuItemOcultarBarraEstado);
        jMenuVista.add(jSeparator3);

        jCheckBoxMenuItemSiemprePrimerPlano.setSelected(false);
        jCheckBoxMenuItemSiemprePrimerPlano.setText("Siempre en Primer Plano");
        jCheckBoxMenuItemSiemprePrimerPlano.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/primer_plano_color.png")));
        jCheckBoxMenuItemSiemprePrimerPlano.setBackground(Color.white);
        jMenuVista.add(jCheckBoxMenuItemSiemprePrimerPlano);

        jMenuBarMainManuBar.add(jMenuVista);

        jMenuHerramientas.setText("Herramientas");
        jMenuItemCopiasSeguridad.setText("Copias de Seguridad");
        jMenuItemCopiasSeguridad.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/backup_color.png")));
        jMenuHerramientas.add(jMenuItemCopiasSeguridad);

        jMenuItemUbicaciones.setText("Ubicaciones");
        jMenuItemUbicaciones.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/ubicacion_color.png")));
        jMenuHerramientas.add(jMenuItemUbicaciones);

        jMenuItemActivarLicencia.setText("Activar Licencia");
        jMenuItemActivarLicencia.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/licencia_color.png")));
        jMenuHerramientas.add(jMenuItemActivarLicencia);
        jMenuBarMainManuBar.add(jMenuHerramientas);

        jMenuVenta.setText("Venta");
        jMenuItemNuevoTurno.setText("Nuevo Turno");
        jMenuItemNuevoTurno.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/nuevo_turno_color.png")));
        jMenuVenta.add(jMenuItemNuevoTurno);

        jMenuItemCambiarTurno.setText("Cambiar Turno");
        jMenuItemCambiarTurno.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/cambiar_turno_color.png")));
        jMenuVenta.add(jMenuItemCambiarTurno);

        jMenuItemReabrirVenta.setText("Reabrir");
        jMenuItemReabrirVenta.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/reabrir_ventas_color.png")));
        jMenuVenta.add(jMenuItemReabrirVenta);

        jMenuItemTerminarVenta.setText("Cerrar Dia");
        jMenuItemTerminarVenta.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/cerrar_ventas_color.png")));
        jMenuVenta.add(jMenuItemTerminarVenta);

        jMenuItemTerminarExportarVenta.setText("Cerrar Dia y Exportar");
        jMenuItemTerminarExportarVenta.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/cerrar_exportar_ventas_color.png")));
        jMenuVenta.add(jMenuItemTerminarExportarVenta);
        jMenuBarMainManuBar.add(jMenuVenta);

        jMenuAyuda.setText("Ayuda");
        jMenuItemManualUsuario.setText("Manual de Usuario");
        jMenuItemManualUsuario.setIcon(
                new ImageIcon(getClass().getResource("/restManager/resources/icons pack/MenuBar/manual_usuario_color.png")));
        jMenuAyuda.add(jMenuItemManualUsuario);
        jMenuBarMainManuBar.add(jMenuAyuda);

        jMenuBarMainManuBar.add(Box.createGlue());

        jLabelFechaText.setText("Fecha: ");
        jMenuBarMainManuBar.add(jLabelFechaText, new java.awt.GridBagConstraints());
//        jLabelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));
        jLabelFecha.setText("xx/xx/xx");
        jMenuBarMainManuBar.add(jLabelFecha, new java.awt.GridBagConstraints());

        jButtonRefrescarVista.setIcon(MaterialIcons.REFRESH);
        jButtonRefrescarVista.setToolTipText("Refrescar Vista");
        jButtonRefrescarVista.setBackground(DefaultValues.TRANSPARENT);
        jButtonRefrescarVista.setMnemonic(KeyEvent.VK_F5);
        jMenuBarMainManuBar.add(jButtonRefrescarVista);

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}
