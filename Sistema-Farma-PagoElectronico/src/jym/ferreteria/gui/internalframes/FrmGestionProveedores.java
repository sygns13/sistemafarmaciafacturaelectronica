package jym.ferreteria.gui.internalframes;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.gui.FrmMain;
import jym.ferreteria.renders.CustomTableModel;

public class FrmGestionProveedores extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private Controlador control = new Controlador();
    private String idProveedor;

    public FrmGestionProveedores() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tProveedores.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"Código", "RUC", "Proveedor", "Dirección", "Teléfono", "Anexo", "Celular", "Responsable"});
        control.hideTableColumn(tProveedores, 0);
        control.tableMaxWidthColumn(tProveedores, 100, 1);
        control.tableWidthColumn(tProveedores, 300, 2, 3, 7);
        tProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        btnCrear.setMnemonic('N');
        btnCancelar.setMnemonic('a');
        btnModificar.setMnemonic('d');
        btnEliminar.setMnemonic('e');
        bSalir.setMnemonic('s');
        MostrarProveedores();
        habilitar(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAnexo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tProveedores = new javax.swing.JTable();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Proveedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));

        btnCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        btnCrear.setMnemonic('N');
        btnCrear.setText("Nuevo");
        btnCrear.setPreferredSize(new java.awt.Dimension(96, 33));
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear);

        btnModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        btnModificar.setMnemonic('E');
        btnModificar.setText("Editar");
        btnModificar.setPreferredSize(new java.awt.Dimension(96, 33));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setMnemonic('l');
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(110, 33));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        btnCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Add to basket.png"))); // NOI18N
        btnCuenta.setMnemonic('b');
        btnCuenta.setText("Cuenta bancaria");
        btnCuenta.setPreferredSize(new java.awt.Dimension(170, 33));
        btnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCuenta);

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(114, 33));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('S');
        bSalir.setText("Salir");
        bSalir.setPreferredSize(new java.awt.Dimension(90, 33));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Teléfono:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Proveedor:");

        txtProveedor.setNextFocusableComponent(txtDireccion);
        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
        });

        txtTelefono.setNextFocusableComponent(txtAnexo);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Dirección:");

        txtDireccion.setNextFocusableComponent(txtTelefono);
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("R.U.C.: ");

        txtRuc.setNextFocusableComponent(txtProveedor);
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Responsable:");

        txtResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtResponsableKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Anexo:");

        txtAnexo.setNextFocusableComponent(txtCelular);
        txtAnexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAnexoKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Celular:");

        txtCelular.setNextFocusableComponent(txtResponsable);
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCelularKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtResponsable)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDireccion)
                    .addComponent(txtProveedor)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelular))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(0, 51, 102))); // NOI18N

        tProveedores.setForeground(new java.awt.Color(0, 51, 102));
        tProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tProveedores.setRowSorter(null);
        tProveedores.getTableHeader().setReorderingAllowed(false);
        tProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProveedoresMouseClicked(evt);
            }
        });
        tProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tProveedoresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tProveedores);

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Buscar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        nuevoProveedor();
}//GEN-LAST:event_btnCrearActionPerformed
private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        editarProveedor();
}//GEN-LAST:event_btnModificarActionPerformed
private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarProveedores();
}//GEN-LAST:event_btnEliminarActionPerformed
private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
        btnEliminar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnCuenta.setEnabled(true);
        btnCrear.setEnabled(true);
        habilitar(false);
}//GEN-LAST:event_btnCancelarActionPerformed
private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        int row = tProveedores.getSelectedRow();
        if (row >= 0) {
            CuentaBancariaProveedor cuentaBancariaProveedor = new CuentaBancariaProveedor();
            cuentaBancariaProveedor.setdatosproveedor(modelo.getValueAt(row, 0).toString(), modelo.getValueAt(row, 2).toString(), modelo.getValueAt(row, 1).toString());
            FrmMain.escritorio.add(cuentaBancariaProveedor, JLayeredPane.DEFAULT_LAYER);
            cuentaBancariaProveedor.setLocation((FrmMain.escritorio.getWidth() - cuentaBancariaProveedor.getWidth()) / 2, 0);
            cuentaBancariaProveedor.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Proveedor de la tabla");
        }

}//GEN-LAST:event_btnCuentaActionPerformed
private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        control.Solonumeros(evt);
        control.Longitudtx(txtRuc, evt, 11);
}//GEN-LAST:event_txtRucKeyTyped
private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        BuscarProveedores();
}//GEN-LAST:event_txBuscarKeyReleased
private void tProveedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tProveedoresKeyReleased
        VerProveedores();
}//GEN-LAST:event_tProveedoresKeyReleased

    private void txtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyPressed
        if (evt.getKeyChar() == 10 && txtProveedor.getText().trim().length() > 0) {
            txtDireccion.grabFocus();
        }
    }//GEN-LAST:event_txtProveedorKeyPressed

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        if (evt.getKeyChar() == 10 && txtRuc.getText().trim().length() >= 0) {
            txtProveedor.grabFocus();
        }
    }//GEN-LAST:event_txtRucKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        control.passFocus(evt, txtTelefono);
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        control.passFocus(evt, txtAnexo);
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyPressed
        if (btnCrear.isEnabled()) {
            control.passFocus(evt, btnCrear);
        } else {
            control.passFocus(evt, btnModificar);
        }
    }//GEN-LAST:event_txtResponsableKeyPressed

    private void txtAnexoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnexoKeyPressed
        control.passFocus(evt, txtCelular);
    }//GEN-LAST:event_txtAnexoKeyPressed

    private void txtCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyPressed
        control.passFocus(evt, txtResponsable);
    }//GEN-LAST:event_txtCelularKeyPressed

    private void tProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProveedoresMouseClicked
        if (evt.getClickCount() > 1) {
            editarProveedor();

        }
    }//GEN-LAST:event_tProveedoresMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSalir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tProveedores;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txtAnexo;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void habilitar(boolean b) {
        txtRuc.setEnabled(b);
        txtProveedor.setEnabled(b);
        txtTelefono.setEnabled(b);
        txtAnexo.setEnabled(b);
        txtCelular.setEnabled(b);
        txtDireccion.setEnabled(b);
        txtResponsable.setEnabled(b);
        btnCrear.setText("Nuevo");
        btnCancelar.setMnemonic('N');
        btnModificar.setText("Editar");
        btnModificar.setMnemonic('E');
    }

    private void cancelar() {
        txBuscar.setText("");
        txtDireccion.setText("");
        txtProveedor.setText("");
        txtRuc.setText("");
        txtTelefono.setText("");
        txtAnexo.setText("");
        txtCelular.setText("");
        txtResponsable.setText("");
        tProveedores.clearSelection();
        txtRuc.requestFocus();
    }

    private void nuevoProveedor() {
        if (btnCrear.getText().compareTo("Nuevo") == 0) {
            habilitar(true);
            txtRuc.requestFocus();
            btnCrear.setText("Grabar");
            btnCrear.setMnemonic('G');
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnCuenta.setEnabled(false);

        } else {

            if (txtRuc.getText().trim().length() == 11) {
                if (txtProveedor.getText().trim().length() > 0) {
                    idProveedor = Integer.toString(control.DevIdentificador("proveedor", "idProveedor"));
                    control.Sql = "call LosProveedores('" + idProveedor + "','" + txtRuc.getText() + "','" + txtProveedor.getText() + "','" + txtDireccion.getText() + "','" + txtTelefono.getText() + "','" + txtAnexo.getText() + "','" + txtCelular.getText() + "','" + txtResponsable.getText() + "','0')";
                    String res = control.CrearRegistroDev(control.Sql);
                    if (res.equals("1")) {

                        btnEliminar.setEnabled(true);
                        btnModificar.setEnabled(true);
                        btnCuenta.setEnabled(true);
                        cancelar();
                        MostrarProveedores();
                        habilitar(false);
                        JOptionPane.showMessageDialog(this, "El proveedor se ha registrado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(this, res, "Mensaje", JOptionPane.WARNING_MESSAGE);
                        txtRuc.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Indique el nombre del proveedor", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    txtProveedor.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "El R.U.C debe tener once dígitos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                txtRuc.requestFocus();
            }
        }
    }

    private void modificarProveedor() {

        if (txtRuc.getText().trim().length() == 11) {
            if (txtProveedor.getText().trim().length() > 0) {
                control.Sql = "call LosProveedores('" + idProveedor + "','" + txtRuc.getText() + "','" + txtProveedor.getText() + "','" + txtDireccion.getText() + "','" + txtTelefono.getText() + "','" + txtAnexo.getText() + "','" + txtCelular.getText() + "','" + txtResponsable.getText() + "','1')";
                String result = control.EditarRegistroDev(control.Sql);
                if (result.equals("2")) {

                    cancelar();
                    btnEliminar.setEnabled(true);
                    btnCrear.setEnabled(true);
                    btnCuenta.setEnabled(true);
                    MostrarProveedores();
                    habilitar(false);
                    JOptionPane.showMessageDialog(this, "Los datos del proveedor se han actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, result, "Mensaje", JOptionPane.WARNING_MESSAGE);
                    txtProveedor.grabFocus();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Indique el nombre del proveedor", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                txtProveedor.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "El R.U.C debe tener once dígitos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            txtRuc.requestFocus();
        }

    }

    private void eliminarProveedores() {
        control.fila = tProveedores.getSelectedRow();
        if (control.fila >= 0) {
            idProveedor = tProveedores.getValueAt(control.fila, 0).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "¿Confirma que desea eliminar al proveedor seleccionado?", "Confirmar", 0) == 0) {
                control.Sql = "call LosProveedores('" + idProveedor + "','','','','','','','','2')";
                boolean ejecutar = control.ejecutar(control.Sql);
                if (ejecutar) {
                    cancelar();
                    MostrarProveedores();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "El Proveedor no se puede eliminar por que actualmente está en uso");
                    cancelar();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar el proveedor a eliminar");
        }
    }

    private void editarProveedor() {

        if (btnModificar.getText().compareTo("Editar") == 0) {
            if (tProveedores.getSelectedRowCount() == 1) {
                btnCrear.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnCuenta.setEnabled(false);
                habilitar(true);
                btnModificar.setText("Grabar");
                btnModificar.setMnemonic('G');
                VerProveedores();
                txtRuc.grabFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que selecionar un Proveedor para editarlo", "Aviso", 2);
            }
        } else {
            modificarProveedor();
        }

    }

    private void VerProveedores() {
        control.fila = tProveedores.getSelectedRow();
        if (control.fila >= 0) {
            idProveedor = tProveedores.getValueAt(control.fila, 0).toString();
            txtRuc.setText(tProveedores.getValueAt(control.fila, 1).toString());
            txtProveedor.setText(tProveedores.getValueAt(control.fila, 2).toString());
            txtDireccion.setText(tProveedores.getValueAt(control.fila, 3).toString());
            txtTelefono.setText(tProveedores.getValueAt(control.fila, 4).toString());
            txtAnexo.setText(tProveedores.getValueAt(control.fila, 5).toString());
            txtCelular.setText(tProveedores.getValueAt(control.fila, 6).toString());
            txtResponsable.setText(tProveedores.getValueAt(control.fila, 7).toString());
            txtRuc.grabFocus();
        }
    }

    private void MostrarProveedores() {
        BuscarProveedores();
    }

    private void BuscarProveedores() {
        control.Sql = "SELECT * FROM proveedor WHERE nombre LIKE '%" + txBuscar.getText() + "%' OR RUC LIKE '%" + txBuscar.getText() + "%' OR responsable LIKE '%" + txBuscar.getText() + "%'";
        control.LlenarJTabla(modelo, control.Sql, 8);
    }

}
