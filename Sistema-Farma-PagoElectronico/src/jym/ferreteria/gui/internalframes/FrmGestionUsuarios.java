package jym.ferreteria.gui.internalframes;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class FrmGestionUsuarios extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private CustomTableModel modelo = new CustomTableModel();
    private String dato = "", pass = "", Codigo = "";

    /**
     * Creates new form Usuarios
     */
    public FrmGestionUsuarios() {
        initComponents();
        bloquear(false);
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tVendedores.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"Código", "Dni", "Nombre", "Dirección", "Telefono", "Login", "Tipo Usuario","Contraseña"});
        tVendedores.getColumnModel().getColumn(0).setMinWidth(0);
        tVendedores.getColumnModel().getColumn(0).setMaxWidth(0);
        tVendedores.getColumnModel().getColumn(0).setPreferredWidth(0);
        bCrear.setMnemonic('c');
        bModificar.setMnemonic('d');
        bEliminar.setMnemonic('e');
        bCancelar.setMnemonic('a');
        bSalir.setMnemonic('s');
        control.LlenarCombo(cbTipoUsu, "SELECT * FROM tipousuario t;", 2);
        cargarUsuarios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        txtbucarVendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tVendedores = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txTelefono = new javax.swing.JTextField();
        txDni = new javax.swing.JTextField();
        txDireccion = new javax.swing.JTextField();
        pwClave = new javax.swing.JPasswordField();
        txUsuario = new javax.swing.JTextField();
        cbTipoUsu = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Usuarios");
        setAutoscrolls(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bCrear.setMnemonic('C');
        bCrear.setText("Crear");
        bCrear.setName("bCrear"); // NOI18N
        bCrear.setPreferredSize(new java.awt.Dimension(96, 33));
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear);

        bModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('E');
        bModificar.setText("Editar");
        bModificar.setName("bModificar"); // NOI18N
        bModificar.setPreferredSize(new java.awt.Dimension(96, 33));
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel1.add(bModificar);

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/eliminar.png"))); // NOI18N
        bEliminar.setMnemonic('L');
        bEliminar.setText("Eliminar");
        bEliminar.setName("bEliminar"); // NOI18N
        bEliminar.setPreferredSize(new java.awt.Dimension(110, 33));
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bEliminar);

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('A');
        bCancelar.setText("Cancelar");
        bCancelar.setName("bCancelar"); // NOI18N
        bCancelar.setPreferredSize(new java.awt.Dimension(114, 33));
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar);

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('S');
        bSalir.setText("Salir");
        bSalir.setName("bSalir"); // NOI18N
        bSalir.setPreferredSize(new java.awt.Dimension(90, 33));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir);

        txtbucarVendedor.setName("txtbucarVendedor"); // NOI18N
        txtbucarVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucarVendedorKeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tVendedores.setForeground(new java.awt.Color(0, 51, 102));
        tVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tVendedores.setName("tVendedores"); // NOI18N
        tVendedores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tVendedores);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Buscar:");
        jLabel9.setName("jLabel9"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Dirección:");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Apellidos y nombres:");
        jLabel7.setName("jLabel7"); // NOI18N

        tNombre.setName("tNombre"); // NOI18N
        tNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNombreKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Teléfono:");
        jLabel8.setName("jLabel8"); // NOI18N

        txTelefono.setName("txTelefono"); // NOI18N
        txTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txTelefonoKeyPressed(evt);
            }
        });

        txDni.setName("txDni"); // NOI18N
        txDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDniActionPerformed(evt);
            }
        });
        txDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDniKeyTyped(evt);
            }
        });

        txDireccion.setName("txDireccion"); // NOI18N
        txDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDireccionKeyPressed(evt);
            }
        });

        pwClave.setName("pwClave"); // NOI18N
        pwClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pwClaveKeyPressed(evt);
            }
        });

        txUsuario.setName("txUsuario"); // NOI18N
        txUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txUsuarioKeyPressed(evt);
            }
        });

        cbTipoUsu.setName("cbTipoUsu"); // NOI18N
        cbTipoUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbTipoUsuKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Tipo de Usuario:");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("DNI:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Usuario:");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Clave:");
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoUsu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pwClave))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDni)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(pwClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbucarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 147, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbucarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
}//GEN-LAST:event_bSalirActionPerformed

private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        guardarUsuario();
}//GEN-LAST:event_bCrearActionPerformed

private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        modificarUsuario();
}//GEN-LAST:event_bModificarActionPerformed

private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        eliminarUsuario();
}//GEN-LAST:event_bEliminarActionPerformed

private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        cancelar();
}//GEN-LAST:event_bCancelarActionPerformed

    private void txtbucarVendedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarVendedorKeyReleased
        cargarUsuarios();
    }//GEN-LAST:event_txtbucarVendedorKeyReleased

    private void txDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDniActionPerformed

    private void txDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyTyped
        control.Solonumeros(evt);
        control.Longitudtx(txDni, evt, 8);        // TODO add your handling code here:
    }//GEN-LAST:event_txDniKeyTyped

    private void tNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreKeyPressed
        control.passFocus(evt, txDni);
    }//GEN-LAST:event_tNombreKeyPressed

    private void txDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyPressed
        control.passFocus(evt, txDireccion);
    }//GEN-LAST:event_txDniKeyPressed

    private void txDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDireccionKeyPressed
        control.passFocus(evt, txTelefono);
    }//GEN-LAST:event_txDireccionKeyPressed

    private void txTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txTelefonoKeyPressed
        control.passFocus(evt, cbTipoUsu);
    }//GEN-LAST:event_txTelefonoKeyPressed

    private void cbTipoUsuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbTipoUsuKeyPressed
        control.passFocus(evt, txUsuario);
    }//GEN-LAST:event_cbTipoUsuKeyPressed

    private void txUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txUsuarioKeyPressed
        control.passFocus(evt, pwClave);
    }//GEN-LAST:event_txUsuarioKeyPressed

    private void pwClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwClaveKeyPressed
        if (bCrear.isEnabled()) {
            control.passFocus(evt, bCrear);
        } else {
            control.passFocus(evt, bModificar);
        }
    }//GEN-LAST:event_pwClaveKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbTipoUsu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pwClave;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTable tVendedores;
    private javax.swing.JTextField txDireccion;
    private javax.swing.JTextField txDni;
    private javax.swing.JTextField txTelefono;
    private javax.swing.JTextField txUsuario;
    private javax.swing.JTextField txtbucarVendedor;
    // End of variables declaration//GEN-END:variables

    private void bloquear(boolean a) {
        tNombre.setEnabled(a);
        txDireccion.setEnabled(a);
        txDni.setEnabled(a);
        txTelefono.setEditable(a);
        txUsuario.setEnabled(a);
        pwClave.setEnabled(a);
        cbTipoUsu.setEnabled(a);
    }

    private void limpiar() {
        cbTipoUsu.setSelectedIndex(-1);
        txDireccion.setText("");
        txDni.setText("");
        txTelefono.setText("");
        txUsuario.setText("");
        pwClave.setText("");
        tNombre.setText("");
    }

    private void cancelar() {
        limpiar();
        bloquear(false);
        bCrear.setText("Crear");
        bModificar.setText("Editar");
        bCrear.setEnabled(true);
        bModificar.setEnabled(true);
        bEliminar.setEnabled(true);
    }

    private void cargarUsuarios() {
        control.Sql = "SELECT idusuario,dni,nom,dire,tel, nomusr,nomtpus,psw FROM vendedores   where idusuario not in(9) and ( nom like'"
                + txtbucarVendedor.getText() + "%' or dni like'"
                + txtbucarVendedor.getText() + "%' )";
        control.LlenarJTabla(modelo, control.Sql, 8);

    }

    private void guardarUsuario() {
        if (bCrear.getText().compareTo("Crear") == 0) {
            bloquear(true);
            tNombre.grabFocus();
            bEliminar.setEnabled(false);
            bModificar.setEnabled(false);
            bCrear.setText("Grabar");
        } else {
            if (tNombre.getText().trim().length() > 0) {
                if (txDni.getText().trim().length() == 8) {
                    if (txUsuario.getText().trim().length() > 0) {
                        if (cbTipoUsu.getSelectedIndex() != -1) {
                            if (pwClave.getText().trim().length() > 0) {
                                if (control.Verificarconsulta("select * from usuario where nomusr='" + txUsuario.getText() + "';") == false) {
                                    control.Sql = "select InsertaVendedor('" + txUsuario.getText() + "','" + pwClave.getText() + "','" + cbTipoUsu.getSelectedItem().toString() + "');";
                                    dato = control.DevolverRegistroDto(control.Sql, 1);
                                    control.Sql = "insert into datosusuario(nom,dire,tel, dni, Usuario_idusuario) values"
                                            + "('" + control.PriLtrasMayuscula(tNombre.getText()) + "','" + txDireccion.getText() + "','" + txTelefono.getText() + "','" + txDni.getText() + "','" + dato + "');";
                                    control.CrearRegistro(control.Sql);
                                    bloquear(false);
                                    limpiar();
                                    bEliminar.setEnabled(true);
                                    bModificar.setEnabled(true);
                                    bCrear.setText("Crear");
                                    control.LimTabla(modelo);
                                    cargarUsuarios();
                                } else {
                                    JOptionPane.showMessageDialog(null, "El nombre de usuario indicado ya está en uso, especifique otro nombre e intente nuevamente!!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese una contraseña");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Seleccione un tipo de usuario");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el usuario");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El número de DNI debe tener 8 dígitos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Indique los nombres y apellidos del usuario");
            }
        }
    }

    private void eliminarUsuario() {

        if (tVendedores.getSelectedRowCount() == 1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario seleccionado?", "", JOptionPane.YES_NO_OPTION) == 0) {
//                control.Sql = "delete from datosusuario where usuario_idusuario='" + modelo.getValueAt(tVendedores.getSelectedRow(), 0) + "';";
//                control.EliminarRegistro(control.Sql);
                control.Sql = "delete from usuario where idusuario='" + modelo.getValueAt(tVendedores.getSelectedRow(), 0) + "';";

                if (control.ejecutar(control.Sql)) {
                    limpiar();
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "No es posible eliminar el usuario seleccionado, ya que actualmente está en uso");
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario", "Aviso", 2);
        }

    }

    private void modificarUsuario() {
        if (bModificar.getText().compareTo("Editar") == 0) {
            if (tVendedores.getSelectedRowCount() == 1) {
                bloquear(true);
                tNombre.grabFocus();
                bCrear.setEnabled(false);
                bEliminar.setEnabled(false);
                Codigo = modelo.getValueAt(tVendedores.getSelectedRow(), 0).toString();
                tNombre.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 2).toString());
                txDireccion.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 3).toString());
                txDni.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 1).toString());
                txTelefono.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 4).toString());
                txUsuario.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 5).toString());
                cbTipoUsu.setSelectedItem(modelo.getValueAt(tVendedores.getSelectedRow(), 6).toString());
                pass = control.DevolverRegistroDto("select psw from usuario where idusuario='" + Codigo + "';", 1);
                pwClave.setText(pass);
                bModificar.setText("Grabar");
            } else {
                JOptionPane.showMessageDialog(null, "Selecione un Vendedor!!");
            }
        } else {
            if (tNombre.getText().trim().length() > 0) {
                if (txDni.getText().trim().length() == 8) {
                    if (txUsuario.getText().trim().length() > 0) {
                        if (cbTipoUsu.getSelectedIndex() != -1) {
                            if (pwClave.getText().trim().length() > 0) {
                                control.Sql = "call UpdateVendedor('" + Codigo + "','" + txUsuario.getText() + "','" + pwClave.getText() + "','" + control.PriLtrasMayuscula(tNombre.getText()) + "','" + txDireccion.getText() + "','" + txTelefono.getText() + "','" + txDni.getText() + "');";
                                control.EditarRegistro(control.Sql);
                                control.Sql = "update usuario set idTipousuario='" + control.DevolverRegistroDto("SELECT idTipousuario FROM tipousuario where nomtpus='" + cbTipoUsu.getSelectedItem().toString() + "';", 1) + "' where idusuario='" + Codigo + "';";
                                control.EditarRegistro(control.Sql);
                                //System.out.print(control.Sql);
                                bloquear(false);
                                limpiar();
                                bCrear.setEnabled(true);
                                bEliminar.setEnabled(true);
                                bModificar.setText("Editar");
                                control.LimTabla(modelo);
                                cargarUsuarios();
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese una contraseña");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Seleccione un tipo de usuario");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el usuario");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente el DNI");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            }
        }
    }
}
