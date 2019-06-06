package jym.ferreteria.gui.internalframes;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

/**
 * ** @author Ing. Miguel Angel Silva Zapata. *********
 */
public class FrmGestionClientes extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private JOptionPane mensj = new JOptionPane();

    private String tipoCliente = "";
    private String descripcionCliente = "";
    private String numCliente = "";
    private String idCliente = "";
    private String cliente = "";
    private CustomTableModel model = new CustomTableModel();

    public FrmGestionClientes() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tClientes.setModel(model);
        model.setColumnIdentifiers(new String[]{"ID", "Cliente", "Tipo", "Dirección", "Dni/Ruc", "Teléfono","Correo 1","Correo 2"});
        control.hideTableColumn(tClientes, 0);
        tClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
        tClientes.getColumnModel().getColumn(3).setPreferredWidth(200);
        control.hideTableColumn(tClientes, 0);
        textoBloc(false);
        MostrarCliente();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtDniRuc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtmail1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtmail2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tClientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Document.png"))); // NOI18N
        bCrear.setMnemonic('c');
        bCrear.setText("Crear");
        bCrear.setPreferredSize(new java.awt.Dimension(96, 33));
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear);

        bModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-document-edit-icon.png"))); // NOI18N
        bModificar.setMnemonic('e');
        bModificar.setText("Editar");
        bModificar.setPreferredSize(new java.awt.Dimension(98, 33));
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel1.add(bModificar);

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        bEliminar.setMnemonic('l');
        bEliminar.setText("Eliminar");
        bEliminar.setPreferredSize(new java.awt.Dimension(112, 33));
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bEliminar);

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('a');
        bCancelar.setText("Cancelar");
        bCancelar.setPreferredSize(new java.awt.Dimension(114, 33));
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar);

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setPreferredSize(new java.awt.Dimension(102, 33));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 10), java.awt.Color.white), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 153))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Teléfono:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Cliente:");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Dirección:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Natural", "Juridico", "Carnet de Extranjería", "Pasaporte", "Cédula Diplomática" }));
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        cbTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbTipoKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Tipo: ");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("N° Documento:");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtDniRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniRucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniRucKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Correo 1:");

        txtmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmail1ActionPerformed(evt);
            }
        });
        txtmail1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmail1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmail1KeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Correo 2:");

        txtmail2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmail2ActionPerformed(evt);
            }
        });
        txtmail2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmail2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmail2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtmail1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtmail2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel4)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel7)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txtmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(txtmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(0, 0, 153))); // NOI18N

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Buscar:");

        tClientes.setForeground(new java.awt.Color(0, 51, 102));
        tClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(196, 196, 196))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(6, 6, 6))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
}//GEN-LAST:event_bSalirActionPerformed

private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        
    
    CrearClinte();
}//GEN-LAST:event_bCrearActionPerformed

private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        EditarCliente();
}//GEN-LAST:event_bModificarActionPerformed

private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        EliminarCliente();
}//GEN-LAST:event_bEliminarActionPerformed

private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        textoBloc(false);
        limpiar();
        control.LimTabla(model);
        MostrarCliente();
        txtDniRuc.setEnabled(false);
        bCrear.setEnabled(true);
        bEliminar.setEnabled(true);
        bModificar.setEnabled(true);
        tClientes.setEnabled(true);
        bCrear.setMnemonic('c');
        bModificar.setMnemonic('e');
}//GEN-LAST:event_bCancelarActionPerformed

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
    }//GEN-LAST:event_cbTipoItemStateChanged
    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        MostrarCliente();
    }//GEN-LAST:event_txtBuscarClienteKeyReleased
    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
//        control.Mayusculas(txtCliente);
    }//GEN-LAST:event_txtClienteKeyReleased
    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
//        control.Mayusculas(txtDireccion);
    }//GEN-LAST:event_txtDireccionKeyReleased
    private void txtDniRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniRucKeyTyped
        int a=cbTipo.getSelectedIndex();
        
        if(a<2){
        control.Solonumeros(evt);
           if(a==0){
               control.Longitudtx(txtDniRuc, evt, 8);
           }else if(a==1){
               control.Longitudtx(txtDniRuc, evt, 11);
           }
        }else if(a==2 || a==3){
            control.Longitudtx(txtDniRuc, evt, 12);
        }else if(a==4){
            control.Longitudtx(txtDniRuc, evt, 15);
        }
        
        
    }//GEN-LAST:event_txtDniRucKeyTyped
    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        control.Solonumeros(evt);
        control.Longitudtx(txtTelefono, evt, 13);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        if (evt.getKeyChar() == 10 && txtDireccion.getText().trim().length() > 0) {
            if (txtDniRuc.isEnabled() == true) {
                txtDniRuc.requestFocus();
            } else {
                txtTelefono.grabFocus();
            }
        }
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtDniRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniRucKeyPressed
        if (evt.getKeyChar() == 10 && txtDniRuc.getText().trim().length() > 0) {
            txtTelefono.grabFocus();
        }
    }//GEN-LAST:event_txtDniRucKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed

        if (bCrear.isEnabled()) {
            control.passFocus(evt, bCrear);
        } else {
            control.passFocus(evt, bModificar);
        }
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void cbTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbTipoKeyPressed
        if (evt.getKeyChar() == 10 && cbTipo.getSelectedIndex() > -1) {
            txtDireccion.grabFocus();
        }
    }//GEN-LAST:event_cbTipoKeyPressed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        
        txtDniRuc.setText(null);
        
        /*if (cbTipo.getSelectedIndex() == 1) {
         txtDni.setEnabled(false);
         txtDniRuc.setEnabled(true);
         tipoCli = "Juridico";
         DescriCli = "Ruc";
         //txtDireccion.grabFocus();
         }
         if (cbTipo.getSelectedIndex() == 0) {
         txtDni.setEnabled(true);
         txtDniRuc.setEnabled(false);
         tipoCli = "Natural";
         DescriCli = "Dni";
         //txtDireccion.grabFocus();
         }*/
    }//GEN-LAST:event_cbTipoActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        control.passFocus(evt, cbTipo);        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyPressed

    private void txtmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmail1ActionPerformed

    private void txtmail1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmail1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmail1KeyPressed

    private void txtmail1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmail1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmail1KeyTyped

    private void txtmail2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmail2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmail2ActionPerformed

    private void txtmail2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmail2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmail2KeyPressed

    private void txtmail2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmail2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmail2KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    public static javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tClientes;
    private javax.swing.JTextField txtBuscarCliente;
    public static javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDniRuc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtmail1;
    private javax.swing.JTextField txtmail2;
    // End of variables declaration//GEN-END:variables

    private void textoBloc(boolean b) {
        txtCliente.setEnabled(b);
        txtDireccion.setEnabled(b);
        txtTelefono.setEnabled(b);
        txtDniRuc.setEnabled(b);
        cbTipo.setEnabled(b);
        bCrear.setText("Crear");
        bModificar.setText("Editar");
        txtmail1.setEnabled(b);
        txtmail2.setEnabled(b);
    }

    private void MostrarCliente() {
        BuscarCliente();
    }

    private void BuscarCliente() {
        control.Sql = "SELECT * FROM cliente where nomcte like'" + txtBuscarCliente.getText() + "%' or tipo like'"
                + txtBuscarCliente.getText() + "%' or DniRuc like'" + txtBuscarCliente.getText() + "%' order by nomcte asc ";
        control.LlenarJTabla(model, control.Sql, 8);
    }

    private void limpiar() {
        txtCliente.setText(null);
        txtDireccion.setText(null);
        txtDniRuc.setText(null);
        txtTelefono.setText(null);
        txtmail1.setText(null);
        txtmail2.setText(null);
        try {
            cbTipo.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }

    private void CrearClinte() {
        if (bCrear.getText().compareTo("Crear") == 0) {
            textoBloc(true);
            txtCliente.grabFocus();
            bCrear.setText("Grabar");
            bCrear.setMnemonic('g');
            bEliminar.setEnabled(false);
            bModificar.setEnabled(false);
        } else {
            int num=txtDniRuc.getText().trim().length();
            int tipo=cbTipo.getSelectedIndex();
            boolean resp=false;
            String msj="";
            if(tipo==0){
                if(num==8){
                    resp=true;
                }else{
                    msj="EL DNI no cuenta con un formato válido, consigne 08 dígitos numéricos";
                }
            }else if(tipo==1){
                if(num==11){
                    resp=true;
                }else{
                    msj="EL número RUC no cuenta con un formato válido, consigne 11 dígitos numéricos";
                }
            }else if(tipo==2){
                if(num>=8){
                    resp=true;
                }else{
                    msj="EL formato del Carne de Extranjería no es el correcto";
                }
            }else if(tipo==3){
                if(num>=8){
                    resp=true;
                }else{
                    msj="EL formato del Pasaporte no es el correcto";
                }
            }else if(tipo==4){
                if(num>=8){
                    resp=true;
                }else{
                    msj="EL formato de la cédula diplomática no es la correcta";
                }
            }
            
            
            if (resp) {
                if (txtCliente.getText().trim().length() > 0) {
                    idCliente = Integer.toString(control.DevIdentificador("cliente", "idCliente"));
                    control.Sql = "call Losclientes('" + idCliente + "','" + txtCliente.getText().trim() + "','"
                            + cbTipo.getSelectedItem().toString() + "','" + txtDireccion.getText().trim()
                            + "','" + txtDniRuc.getText() + "','" + txtTelefono.getText() + "','"+txtmail1.getText()+"','"+txtmail2.getText()+"','0')";
                    String Resultado = control.CrearRegistroDev(control.Sql);
                    if (Resultado.equals("1")) {
                        JOptionPane.showMessageDialog(rootPane, "Se ha registrado correctamente al cliente");
                        bCrear.setText("Crear");
                        bCrear.setMnemonic('c');
                        bEliminar.setEnabled(true);
                        bModificar.setEnabled(true);
                        limpiar();
                        MostrarCliente();
                        textoBloc(false);
                    } else {
                        JOptionPane.showMessageDialog(this, Resultado);
                        txtDniRuc.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Indique el nombre del cliente");
                    txtCliente.grabFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, msj);
                txtDniRuc.grabFocus();
            }

        }
    }

    private void EditarCliente() {
        if (bModificar.getText().compareTo("Editar") == 0) {
            if (tClientes.getSelectedRowCount() == 1) {
                bCrear.setEnabled(false);
                bEliminar.setEnabled(false);
                textoBloc(true);
                bModificar.setText("Grabar");
                bModificar.setMnemonic('g');
                MostarDatosClinete();
                txtCliente.grabFocus();
            } else {
                mensj.showMessageDialog(null, "Tiene que selecionar un Cliente para Editarlo", "", 2);
            }
        } else {
            ActualizarCliente();
        }
    }

    private void ActualizarCliente() {
        if (txtDniRuc.getText().trim().length() >= 8) {
            if (txtCliente.getText().trim().length() > 0) {
                control.Sql = "call Losclientes('" + idCliente + "','" + txtCliente.getText().trim() + "','"
                        + cbTipo.getSelectedItem().toString() + "','" + txtDireccion.getText().trim()
                        + "','" + txtDniRuc.getText() + "','" + txtTelefono.getText() + "','"+txtmail1.getText()+"','"+txtmail2.getText()+"','1')";
                System.out.println(control.Sql);
                String Resultado = control.EditarRegistroDev(control.Sql);
                if (Resultado.equals("2")) {
                    JOptionPane.showMessageDialog(rootPane, "Se Actualizó  Correctamente el Cliente");
                    bModificar.setText("Editar");
                    bCrear.setEnabled(true);
                    tClientes.setEnabled(true);
                    //ActualizarCliente();
                    textoBloc(false);
                    txtDniRuc.setEnabled(false);
                    txtBuscarCliente.grabFocus();
                    bCrear.setText("Crear");
                    bEliminar.setEnabled(true);
                    bModificar.setEnabled(true);
                    bModificar.setMnemonic('e');
                    limpiar();
                    MostrarCliente();
                } else {
                    JOptionPane.showMessageDialog(rootPane, Resultado);

                    txtDniRuc.grabFocus();

                }
            } else {
                JOptionPane.showMessageDialog(this, "Indique el nombre del cliente");
                txtCliente.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Indique el formato correcto del RUC / DNI");
            txtDniRuc.grabFocus();
        }
    }

    private void MostarDatosClinete() {
        idCliente = model.getValueAt(tClientes.getSelectedRow(), 0).toString();
        txtCliente.setText(model.getValueAt(tClientes.getSelectedRow(), 1).toString());
        txtDireccion.setText(model.getValueAt(tClientes.getSelectedRow(), 3).toString());
        txtTelefono.setText(model.getValueAt(tClientes.getSelectedRow(), 5).toString());
        cbTipo.setSelectedItem(model.getValueAt(tClientes.getSelectedRow(), 2).toString());
        txtDniRuc.setText(model.getValueAt(tClientes.getSelectedRow(), 4).toString());
        
        txtmail1.setText(model.getValueAt(tClientes.getSelectedRow(), 6).toString());
        txtmail2.setText(model.getValueAt(tClientes.getSelectedRow(), 7).toString());
    }

    private void EliminarCliente() {
        if (tClientes.getSelectedRowCount() == 1) {
            if (mensj.showConfirmDialog(null, "¿Confirma que desea aliminar al cliente seleccionado?", "", JOptionPane.YES_NO_OPTION) == 0) {
                idCliente = (model.getValueAt(tClientes.getSelectedRow(), 0).toString());
                control.Sql = "call Losclientes('" + idCliente + "','','','','','','2')";
                if (control.ejecutar(control.Sql)) {
                    limpiar();
                    MostrarCliente();
                } else {
                    JOptionPane.showMessageDialog(this, "El cliente no puede ser eliminado, pues éste tiene ventas relacionadas");

                }

            }
        } else {
            mensj.showMessageDialog(null, "Tiene Que Seleccionar un Cliente para Eliminar", "", 2);
        }
    }
}
