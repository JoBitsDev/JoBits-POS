package GUI.Views.login;

import GUI.EsquemaSalon;
import GUI.Main;
import GUI.Views.AbstractView;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;

import restManager.persistencia.Personal;
import restManager.persistencia.jpa.staticContent;
import restManager.resources.R;
import restManager.util.ComponentMover;
import restManager.util.LoadingWindow;

/**
 *
 * @author Jorge
 */
public class LogInDialog extends AbstractView {

    /**
     * Creates new form LogInDialog
     */
    private String estadoConexion;
    private Color colorLabel;

    public LogInDialog(AbstractDialogController controller) {
        super(DialogType.DEFINED, controller);
        setUndecorated(true);
        comprobarConexion();
        initComponents();
        ComponentMover cr = new ComponentMover(this, jPanelCenter);

        buttonGroup1.add(jRadioButtonLocal);
        buttonGroup1.add(jRadioButtonRemoto);
        LoadingWindow.hide();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jXLabelUser1 = new org.jdesktop.swingx.JXLabel();
        jPanelCenter = new javax.swing.JPanel();
        jPanelUser = new javax.swing.JPanel();
        overlayTextField1 = new com.jidesoft.swing.OverlayTextField();
        jPanelPass = new javax.swing.JPanel();
        jPasswordField = new com.jidesoft.swing.OverlayPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jXLabelConnected = new org.jdesktop.swingx.JXLabel();
        jPanelConn = new javax.swing.JPanel();
        jRadioButtonLocal = new javax.swing.JRadioButton();
        jRadioButtonRemoto = new javax.swing.JRadioButton();
        jPanelOptions = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jideButtonConfig = new com.jidesoft.swing.JideButton();
        jButtonAutenticar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setMinimumSize(new java.awt.Dimension(452, 239));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(452, 239));
        setResizable(false);

        jXLabelUser1.setBackground(new java.awt.Color(153, 153, 153));
        jXLabelUser1.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jXLabelUser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelUser1.setText("Restaurant Manager V1.01\n");
        jXLabelUser1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        getContentPane().add(jXLabelUser1, java.awt.BorderLayout.NORTH);

        jPanelCenter.setBackground(new java.awt.Color(0, 153, 153));
        jPanelCenter.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicio Sesión"));
        jPanelCenter.setLayout(new javax.swing.BoxLayout(jPanelCenter, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelUser.setOpaque(false);

        overlayTextField1.setMinimumSize(new java.awt.Dimension(200, 26));
        overlayTextField1.setPreferredSize(new java.awt.Dimension(257, 22));
        jPanelUser.add(overlayTextField1);

        jPanelCenter.add(jPanelUser);

        jPanelPass.setOpaque(false);

        jPasswordField.setToolTipText("Contraseña");
        jPasswordField.setMaximumSize(new java.awt.Dimension(2147483647, 22));
        jPasswordField.setMinimumSize(new java.awt.Dimension(200, 26));
        jPasswordField.setPreferredSize(new java.awt.Dimension(257, 22));
        jPanelPass.add(jPasswordField);

        jPanelCenter.add(jPanelPass);

        jPanel1.setOpaque(false);

        jXLabelConnected.setForeground(colorLabel);
        jXLabelConnected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelConnected.setText(estadoConexion);
        jXLabelConnected.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jPanel1.add(jXLabelConnected);

        jPanelCenter.add(jPanel1);

        jPanelConn.setOpaque(false);
        jPanelConn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 0));

        jRadioButtonLocal.setBackground(new java.awt.Color(30, 30, 30));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jRadioButtonLocal.setText(bundle.getString("label_servidor_local")); // NOI18N
        jRadioButtonLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLocalActionPerformed(evt);
            }
        });
        jPanelConn.add(jRadioButtonLocal);

        jRadioButtonRemoto.setBackground(new java.awt.Color(30, 30, 30));
        jRadioButtonRemoto.setSelected(true);
        jRadioButtonRemoto.setText(bundle.getString("label_servidor_remoto")); // NOI18N
        jRadioButtonRemoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRemotoActionPerformed(evt);
            }
        });
        jPanelConn.add(jRadioButtonRemoto);

        jPanelCenter.add(jPanelConn);

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelOptions.setBackground(new java.awt.Color(153, 153, 153));
        jPanelOptions.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jPanelOptions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jPanelOptions.add(jButtonCancelar);

        jideButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/imprimir.png"))); // NOI18N
        jPanelOptions.add(jideButtonConfig);

        jButtonAutenticar.setText(bundle.getString("label_autenticar")); // NOI18N
        jPanelOptions.add(jButtonAutenticar);

        getContentPane().add(jPanelOptions, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLocalActionPerformed
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        conectarServidor();
    }//GEN-LAST:event_jRadioButtonLocalActionPerformed

    private void jRadioButtonRemotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRemotoActionPerformed
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");
        conectarServidor();
    }//GEN-LAST:event_jRadioButtonRemotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAutenticar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelConn;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelPass;
    private javax.swing.JPanel jPanelUser;
    private com.jidesoft.swing.OverlayPasswordField jPasswordField;
    private javax.swing.JRadioButton jRadioButtonLocal;
    private javax.swing.JRadioButton jRadioButtonRemoto;
    private org.jdesktop.swingx.JXLabel jXLabelConnected;
    private org.jdesktop.swingx.JXLabel jXLabelUser1;
    private com.jidesoft.swing.JideButton jideButtonConfig;
    private com.jidesoft.swing.OverlayTextField overlayTextField1;
    // End of variables declaration//GEN-END:variables

    private boolean comprobarConexion() {
        boolean conn = staticContent.isCONECTADO();
        if (conn) {
            estadoConexion = "Conectado";
            colorLabel = Color.green;
        } else {
            estadoConexion = "No hay conexión";
            colorLabel = Color.red;

        }
        if (jXLabelConnected != null) {
            jXLabelConnected.setText(estadoConexion);
            jXLabelConnected.setForeground(colorLabel);
        }

        return conn;
    }

    private void autenticar() {

        if (!comprobarConexion()) {
            JOptionPane.showMessageDialog(this, R.RESOURCE_BUNDLE.getString("Error_Sin_Conexion"),
                    R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
            return;
        }

        LoadingWindow.show(this);

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

            Personal p;

            @Override
            protected String doInBackground() throws Exception {
                String user = overlayTextField1.getText();
                char[] pass = jPasswordField.getPassword();
                if (!user.isEmpty() && pass.length != 0) {
                    p = staticContent.personalJPA.findPersonal(user);
                    if (p != null) {
                        if (Arrays.equals(p.getContrasenna().toCharArray(), pass)) {
                            return "Autenticación correcta";
                        } else {
                            return "La contraseña es incorrecta";
                        }
                    } else {
                        return "El usuario no existe";
                    }
                } else {
                    return "Campos vacios";

                }

            }

            @Override
            protected void done() {
                String status;
                try {
                    status = get();
                    LoadingWindow.hide();
                    if (status.equals("Autenticación correcta")) {
                        JOptionPane.showMessageDialog(null, status);
                        new Main(p);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, status,
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(EsquemaSalon.class.getName()).log(Level.SEVERE, null, ex);
                    LoadingWindow.hide();
                    // JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();

    }

    private void conectarServidor() {

        staticContent.init(R.PERIRSTENCE_UNIT_NAME);
        comprobarConexion();
    }

    @Override
    public void updateView() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
