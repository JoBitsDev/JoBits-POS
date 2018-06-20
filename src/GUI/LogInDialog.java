package GUI;

import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import restManager.persistencia.Personal;
import restManager.persistencia.jpa.staticContent;
import restManager.util.ComponentMover;
import restManager.util.LoadingWindow;
/**
 *
 * @author Jorge
 */
public class LogInDialog extends javax.swing.JDialog {

    /**
     * Creates new form LogInDialog
     */
    private String estadoConexion;
    
    
    public LogInDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 388, 237, 30, 30));
        if (comprobarConexion()) {
            estadoConexion = "Conectado";
        } else {
            estadoConexion = "No hay conexión";
        }
        initComponents();
        ComponentMover cr = new ComponentMover(this, panelNice1);

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        passwordField = new org.edisoncor.gui.passwordField.PasswordFieldRound();
        jXLabelPass = new org.jdesktop.swingx.JXLabel();
        jXLabelUser = new org.jdesktop.swingx.JXLabel();
        textFieldUser = new org.edisoncor.gui.textField.TextFieldRectBackground();
        buttonConfig = new org.edisoncor.gui.button.ButtonColoredAction();
        buttonSalir = new org.edisoncor.gui.button.ButtonColoredAction();
        buttonAuth = new org.edisoncor.gui.button.ButtonColoredAction();
        jXLabelUser1 = new org.jdesktop.swingx.JXLabel();
        jXLabelUser2 = new org.jdesktop.swingx.JXLabel();

        panelRect1.setAnchoDeBorde(0.0F);
        panelRect1.setAnchoDeSegundoBorde(0.0F);
        panelRect1.setColorDeBorde(new java.awt.Color(0, 0, 0));
        panelRect1.setColorDeSegundoBorde(new java.awt.Color(0, 0, 0));

        jXLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jXLabel1.setText("Dirección IP");

        jXLabel2.setText("jXLabel1");

        javax.swing.GroupLayout panelRect1Layout = new javax.swing.GroupLayout(panelRect1);
        panelRect1.setLayout(panelRect1Layout);
        panelRect1Layout.setHorizontalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        panelRect1Layout.setVerticalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelNice1.setAlignmentX(0.0F);
        panelNice1.setAlignmentY(0.0F);
        panelNice1.setAnchoDeBorde(0.0F);

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordFieldKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        jXLabelPass.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelPass.setText("Contraseña");
        jXLabelPass.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jXLabelUser.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelUser.setText("Usuario");
        jXLabelUser.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        textFieldUser.setDescripcion("Usuario");

        buttonConfig.setText("Configurar");
        buttonConfig.setEnabled(false);

        buttonSalir.setText("Salir");
        buttonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirActionPerformed(evt);
            }
        });

        buttonAuth.setText("Autenticar");
        buttonAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAuthActionPerformed(evt);
            }
        });

        jXLabelUser1.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelUser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelUser1.setText("Restaurant Manager V1.01\n");
        jXLabelUser1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jXLabelUser2.setForeground(new java.awt.Color(0, 204, 0));
        jXLabelUser2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelUser2.setText(estadoConexion);
        jXLabelUser2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNice1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addComponent(buttonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(buttonAuth, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelNice1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jXLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jXLabelPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(96, 96, 96)
                                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jXLabelUser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addComponent(jXLabelUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jXLabelUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jXLabelUser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAuth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panelNice1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonSalirActionPerformed

    private void buttonAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAuthActionPerformed
        autenticar();
    }//GEN-LAST:event_buttonAuthActionPerformed

    private void passwordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyTyped
        //System.out.println(evt.getKeyCode());

    }//GEN-LAST:event_passwordFieldKeyTyped

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            autenticar();
        }
    }//GEN-LAST:event_passwordFieldKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonColoredAction buttonAuth;
    private org.edisoncor.gui.button.ButtonColoredAction buttonConfig;
    private org.edisoncor.gui.button.ButtonColoredAction buttonSalir;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabelPass;
    private org.jdesktop.swingx.JXLabel jXLabelUser;
    private org.jdesktop.swingx.JXLabel jXLabelUser1;
    private org.jdesktop.swingx.JXLabel jXLabelUser2;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    private org.edisoncor.gui.passwordField.PasswordFieldRound passwordField;
    private org.edisoncor.gui.textField.TextFieldRectBackground textFieldUser;
    // End of variables declaration//GEN-END:variables

    private boolean comprobarConexion() {
        return true;//TODO: no esta comprobando la conexion
    }

    private void autenticar() {
        LoadingWindow.show(this);
        
        

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

            Personal p ;

            @Override
            protected String doInBackground() throws Exception {
                String user = textFieldUser.getText();
                char[] pass = passwordField.getPassword();
                if (!user.isEmpty() && pass.length != 0) {
                    p =  staticContent.personalJPA.findPersonal(textFieldUser.getText());
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

}
