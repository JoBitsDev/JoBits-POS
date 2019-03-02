package GUI.Views.login;


import GUI.Views.AbstractView;
import java.awt.Color;
import restManager.controller.AbstractDialogController;
import restManager.controller.login.LogInController;
import restManager.util.ComponentMover;

/**
 *
 * @author Jorge
 */
public class LogInDialogView extends AbstractView {

    /**
     * Creates new form LogInDialogView
     */
    private String estadoConexion;
    private Color colorLabel;

    public LogInDialogView(AbstractDialogController controller) {
        super(DialogType.DEFINED, controller);
        initComponents();
        ComponentMover cr = new ComponentMover(this, jPanelCenter);
        buttonGroup1.add(jRadioButtonLocal);
        buttonGroup1.add(jRadioButtonRemoto);

    }

    @Override
    public LogInController getController() {
        return (LogInController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jXLabelUser1 = new org.jdesktop.swingx.JXLabel();
        jideButtonConfig = new com.jidesoft.swing.JideButton();
        jPanelCenter = new javax.swing.JPanel();
        jPanelUser = new javax.swing.JPanel();
        jideLabel1 = new com.jidesoft.swing.JideLabel();
        overlayTextField1 = new com.jidesoft.swing.OverlayTextField();
        jPanelPass = new javax.swing.JPanel();
        jideLabel2 = new com.jidesoft.swing.JideLabel();
        jPasswordField = new com.jidesoft.swing.OverlayPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jXLabelConnected = new org.jdesktop.swingx.JXLabel();
        jPanelConn = new javax.swing.JPanel();
        jRadioButtonLocal = new javax.swing.JRadioButton();
        jRadioButtonRemoto = new javax.swing.JRadioButton();
        jPanelOptions = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAutenticar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setMinimumSize(new java.awt.Dimension(426, 272));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(452, 239));
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout(20, 0));

        jPanel2.setOpaque(false);

        jXLabelUser1.setBackground(new java.awt.Color(153, 153, 153));
        jXLabelUser1.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jXLabelUser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelUser1.setText("Restaurant Manager ");
        jXLabelUser1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jPanel2.add(jXLabelUser1);

        jideButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/config.png"))); // NOI18N
        jPanel2.add(jideButtonConfig);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanelCenter.setBackground(new java.awt.Color(0, 153, 153));
        jPanelCenter.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicio Sesión"));
        jPanelCenter.setMaximumSize(new java.awt.Dimension(417, 146));
        jPanelCenter.setLayout(new javax.swing.BoxLayout(jPanelCenter, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelUser.setOpaque(false);

        jideLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/usuario.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jideLabel1.setText(bundle.getString("label_usuario")); // NOI18N
        jPanelUser.add(jideLabel1);

        overlayTextField1.setMinimumSize(new java.awt.Dimension(200, 26));
        overlayTextField1.setPreferredSize(new java.awt.Dimension(257, 22));
        jPanelUser.add(overlayTextField1);

        jPanelCenter.add(jPanelUser);

        jPanelPass.setOpaque(false);

        jideLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/contraseña.png"))); // NOI18N
        jideLabel2.setText(bundle.getString("label_contrasena")); // NOI18N
        jPanelPass.add(jideLabel2);

        jPasswordField.setToolTipText("Contraseña");
        jPasswordField.setMaximumSize(new java.awt.Dimension(2147483647, 22));
        jPasswordField.setMinimumSize(new java.awt.Dimension(200, 26));
        jPasswordField.setPreferredSize(new java.awt.Dimension(257, 22));
        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });
        jPanelPass.add(jPasswordField);

        jPanelCenter.add(jPanelPass);

        jPanel1.setOpaque(false);

        jXLabelConnected.setForeground(colorLabel);
        jXLabelConnected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelConnected.setText(estadoConexion);
        jXLabelConnected.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jPanel1.add(jXLabelConnected);

        jPanelCenter.add(jPanel1);

        jPanelConn.setMaximumSize(new java.awt.Dimension(405, 23));
        jPanelConn.setOpaque(false);
        jPanelConn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 0));

        jRadioButtonLocal.setBackground(new java.awt.Color(0, 102, 102));
        jRadioButtonLocal.setText(bundle.getString("label_servidor_local")); // NOI18N
        jRadioButtonLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLocalActionPerformed(evt);
            }
        });
        jPanelConn.add(jRadioButtonLocal);

        jRadioButtonRemoto.setBackground(new java.awt.Color(0, 102, 102));
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
        jPanelOptions.setMaximumSize(new java.awt.Dimension(272, 44));
        jPanelOptions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButtonCancelar.setText(bundle.getString("label_salir")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonCancelar);

        jButtonAutenticar.setText(bundle.getString("label_autenticar")); // NOI18N
        jButtonAutenticar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutenticarActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonAutenticar);

        getContentPane().add(jPanelOptions, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLocalActionPerformed
        actualizarLabelConexion(getController().connectLocal());
    }//GEN-LAST:event_jRadioButtonLocalActionPerformed

    private void jRadioButtonRemotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRemotoActionPerformed
        actualizarLabelConexion(getController().connectRemote());
    }//GEN-LAST:event_jRadioButtonRemotoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonAutenticarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutenticarActionPerformed
        getController().autenticar(overlayTextField1.getText(),jPasswordField.getPassword());
        jPasswordField.setText("");
    }//GEN-LAST:event_jButtonAutenticarActionPerformed

    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldActionPerformed
        if(jButtonAutenticar.isEnabled()){
            getController().autenticar(overlayTextField1.getText(), jPasswordField.getPassword());
            jPasswordField.setText("");
        }
    }//GEN-LAST:event_jPasswordFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAutenticar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private com.jidesoft.swing.JideLabel jideLabel1;
    private com.jidesoft.swing.JideLabel jideLabel2;
    private com.jidesoft.swing.OverlayTextField overlayTextField1;
    // End of variables declaration//GEN-END:variables

    private void actualizarLabelConexion(boolean conn) {
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
        jButtonAutenticar.setEnabled(conn);
    }

    @Override
    public void updateView() {
        actualizarLabelConexion(getController().isConnected());
    }

}
