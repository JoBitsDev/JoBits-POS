package GUI;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import restManager.persistencia.*;
import restManager.persistencia.jpa.staticContent;

public class TrabajadorCrear extends javax.swing.JDialog {

    private Personal trabajador;
    private List<String> puestos;
    private boolean modif = false;

    public TrabajadorCrear(Dialog owner, boolean modal,Personal p) {
        super(owner, modal);
        initComponents();
        fillComboBoxPuestosTrabajo();
        if(p==null){
        trabajador = new Personal();
        }
        else{
         trabajador = p; 
         llenarDialog(trabajador);
         modif = true;
        }
        
        
        
        
        setVisible(true);
    }
    public TrabajadorCrear(Frame owner, boolean modal,Personal p) {
        super(owner, modal);
        initComponents();
        fillComboBoxPuestosTrabajo();
        if(p==null){
        trabajador = new Personal();
        }
        else{
         trabajador = p; 
         llenarDialog(trabajador);
        }
        
        
        
        
        setVisible(true);
    }
    
   



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCrearPuesto = new javax.swing.JButton();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelpassword = new javax.swing.JLabel();
        jTextFieldApellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTelMovil = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTelFijo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxPuestoTrabajo = new javax.swing.JComboBox();
        jButtonCrear = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordFieldPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Nuevo Trabajador");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jButtonCrearPuesto.setText("Crear Puesto");
        jButtonCrearPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearPuestoActionPerformed(evt);
            }
        });

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNombre.setText("Nombre*");

        jTextFieldNombre.setName(""); // NOI18N

        jLabelpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelpassword.setText("Contraseña*");

        jTextFieldApellidos.setColumns(30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("No. Carnet");

        jTextFieldCI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCIKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Sexo");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Telefono Móvil");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Telefono Fijo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Descripcion");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Trabajo");

        jComboBoxPuestoTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPuestoTrabajoActionPerformed(evt);
            }
        });

        jButtonCrear.setText("Crear");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Fecha Nac:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Edad:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelApellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelApellidos.setText("Apellidos*");

        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUsuario.setText("Usuario*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCrear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jButtonCrearPuesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(16, 16, 16))
                            .addComponent(jButtonCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jTextFieldDireccion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jComboBoxPuestoTrabajo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(jTextFieldCI, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(39, 39, 39)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabelpassword)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addGap(107, 107, 107))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(97, 97, 97)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldTelMovil)
                                                .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldDescripcion))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldCI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxPuestoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCrear)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCancelar)
                        .addComponent(jButtonCrearPuesto)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCrearPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearPuestoActionPerformed
        //new Crear_Puesto_Trabajo();
    }//GEN-LAST:event_jButtonCrearPuestoActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        crearTrab();
        
        
       
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
    }//GEN-LAST:event_formWindowGainedFocus

    private void jComboBoxPuestoTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPuestoTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPuestoTrabajoActionPerformed

    private void jTextFieldCIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCIKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCIKeyTyped

//    public void SetJcomboBoxPuestos() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBoxPuestoTrabajo.getModel();
//        model.removeAllElements();
//        for (int i = 0; i < puestos.size(); i++) {
//            model.addElement(puestos.get(i).getNombrePuesto());
//        }
//
//    }

    private void SetIDPuesto(Personal p) {
        for (int i = 0; i < jComboBoxPuestoTrabajo.getItemCount(); i++) {
            if (p.getPuestoTrabajoList().get(0).getNombrePuesto().
                    equals((String) jComboBoxPuestoTrabajo.getItemAt(i))) {

                jComboBoxPuestoTrabajo.setSelectedIndex(i);
                break;
            }

        }

    }

    private void crearTrab() {
        try {

            int resp = JOptionPane.showConfirmDialog(null, "Desea crear este trabajador.");

            if (resp == 0) {
                //recolectando datos
                String Nombre = jTextFieldNombre.getText();
                String Apellidos = jTextFieldApellidos.getText();
                String NoIdentidad = jTextFieldCI.getText();
                String usuario = jTextFieldUsuario.getText();
                char[] pass = jPasswordFieldPass.getPassword();
                char sexo;
                if (jComboBoxSexo.getSelectedIndex() == 0) {
                    sexo = 'M';
                } else {
                    sexo = 'F';
                }
                String Direccion = jTextFieldDireccion.getText();
                String Movil = jTextFieldTelMovil.getText();
                String Fijo = jTextFieldTelFijo.getText();
                String Descripcion = jTextFieldDescripcion.getText();

                //creando los datos personales del trabajador
                DatosPersonales datosP = new DatosPersonales(usuario, Nombre, Apellidos);
                datosP.setSexo(sexo);
                datosP.setDireccion(Direccion);
                if(!Fijo.isEmpty()){
                datosP.setTelefonoFijo(Integer.valueOf(Fijo));
                }
                if (!Movil.isEmpty()) {
                datosP.setTelefonoMovil(Integer.valueOf(Movil));
                }
                
                datosP.setDescripcion(Descripcion);

                Personal nuevoPersonal = new Personal(usuario, String.valueOf(pass), false);
                
                ArrayList<PuestoTrabajo> puesto = new ArrayList<>();
                puesto.add(staticContent.puestosJPA.findPuestoTrabajo(
                        (String) jComboBoxPuestoTrabajo.getSelectedItem()));
                nuevoPersonal.setPuestoTrabajoList(puesto);
                 
                
                if(modif){
                  staticContent.personalJPA.edit(nuevoPersonal);  
                }
                else{
                 staticContent.personalJPA.create(nuevoPersonal);
                 staticContent.datosPJPA.create(datosP);
                }
                staticContent.clearConnCache();
                JOptionPane.showMessageDialog(this, "Accion realizada exitosamente");
                dispose();
                
                
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Parámetros Incorrectos.\n" + e.getMessage());
            Logger.getLogger(TrabajadorCrear.class.getName()).log(Level.SEVERE, null, e);

        }
        

    }

    private void llenarDialog(Personal p) {
        jTextFieldNombre.setText(p.getDatosPersonales().getNombre());
        jTextFieldApellidos.setText(p.getDatosPersonales().getApellidos());
        jTextFieldCI.setText(p.getDatosPersonales().getCarnet());
        jTextFieldDireccion.setText(p.getDatosPersonales().getDireccion());
        jTextFieldTelMovil.setText("" + p.getDatosPersonales().getTelefonoMovil());
        jTextFieldTelFijo.setText("" + p.getDatosPersonales().getTelefonoFijo());
        jTextFieldDescripcion.setText(p.getDatosPersonales().getDescripcion());
        jTextFieldUsuario.setText(p.getUsuario());
        jPasswordFieldPass.setText(p.getContrasenna());

        if (p.getDatosPersonales().getSexo() == 'M') {
            jComboBoxSexo.setSelectedIndex(0);
        } else {
            jComboBoxSexo.setSelectedIndex(1);
        }

        SetIDPuesto(p);
        /*jLabel11.setText(p.getDatosPersonalescarnet().getFechaNacimineto().getDate()
                + " / " + (p.getDatosPersonalescarnet().getFechaNacimineto().getMonth() + 1)
                + " / " + (p.getDatosPersonalescarnet().getFechaNacimineto().getYear() + 1900));*/

       /* jLabel14.setText("" + p.getDatosPersonalescarnet().getEdad() + " años");*/

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonCrearPuesto;
    private javax.swing.JComboBox jComboBoxPuestoTrabajo;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelpassword;
    private javax.swing.JPasswordField jPasswordFieldPass;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCI;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelFijo;
    private javax.swing.JTextField jTextFieldTelMovil;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

    private void updateDialog(Personal trabajador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fillComboBoxPuestosTrabajo() {
      List<PuestoTrabajo> aux = staticContent.puestosJPA.findPuestoTrabajoEntities();
        for (PuestoTrabajo x: aux) {
            if(x.getNivelAcceso()<5){
            jComboBoxPuestoTrabajo.addItem(x.getNombrePuesto());}
        }
    }

}
