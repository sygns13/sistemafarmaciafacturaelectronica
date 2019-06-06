/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

/**
 * ** @author Ing. Miguel Angel Silva Zapata. *********
 */
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class CuentaBancaria extends javax.swing.JInternalFrame {

    private CustomTableModel modeloProveedores = new CustomTableModel();
    private CustomTableModel modeloCuentasBancarias = new CustomTableModel();
    private Controlador control = new Controlador();
    private String Ruc = "", CodProv = "";
    private String idCuentaProv;
    private String idProveedor;

    public CuentaBancaria() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tProveedores1.setModel(modeloProveedores);
        modeloProveedores.setColumnIdentifiers(new String[]{"Id", "Ruc", "Proveedor"});
        control.hideTableColumn(tProveedores1, 0);
        control.tableMaxWidthColumn(tProveedores1, 100, 1);
        tCuentas.setModel(modeloCuentasBancarias);
        modeloCuentasBancarias.setColumnIdentifiers(new String[]{"Codigo", "Ruc", "Banco", "Cuenta Bancaria"});

        tCuentas.getColumnModel().getColumn(1).setPreferredWidth(100);
        tCuentas.getColumnModel().getColumn(2).setPreferredWidth(200);
        tCuentas.getColumnModel().getColumn(3).setPreferredWidth(100);
        control.hideTableColumn(tCuentas, 0);
        BuscarProveedores();
        control.LlenarCombo(cboBanco, "SELECT * FROM banco ", 2);
        try {
            cboBanco.setSelectedIndex(0);
        } catch (Exception e) {
           
        }

        btnCrear.setEnabled(false);
        btnEditar.setEnabled(false);
        tCuentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnEliminar.setEnabled(true);
            }
        });

    }

    /**
     * ***********************Fin Métodos***********************
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscarProveedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tProveedores1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbProveedor = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cboBanco = new javax.swing.JComboBox();
        txtNumeroCuenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCuentas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cuentas bancarias de proveedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar Proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(51, 0, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 51, 102));

        txtBuscarProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Buscar");

        tProveedores1.setForeground(new java.awt.Color(0, 51, 102));
        tProveedores1.setModel(new javax.swing.table.DefaultTableModel(
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
        tProveedores1.getTableHeader().setReorderingAllowed(false);
        tProveedores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tProveedores1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tProveedores1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarProveedor)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos a Grabar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(51, 0, 153))); // NOI18N

        lbProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbProveedor.setText(" ");
        lbProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Proveedor:");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnEditar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        btnCrear.setText("Nuevo");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        cboBanco.setForeground(new java.awt.Color(0, 51, 102));
        cboBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboBancoKeyPressed(evt);
            }
        });

        txtNumeroCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroCuentaKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Banco:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("N° cuenta:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboBanco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProveedor)
                    .addComponent(jLabel6))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Cuentas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(51, 0, 153))); // NOI18N

        tCuentas.setModel(new javax.swing.table.DefaultTableModel(
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
        tCuentas.getTableHeader().setReorderingAllowed(false);
        tCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tCuentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tCuentas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1)
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
}//GEN-LAST:event_btnSalirActionPerformed

    private void tProveedores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tProveedores1MouseClicked
        if (evt.getClickCount() == 2) {
            SelecionarProvvedor();
        }
    }//GEN-LAST:event_tProveedores1MouseClicked

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        crearCuentaBancaria();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        ModificarCuenta();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCuentasMouseClicked
        if (evt.getClickCount() > 1) {
            control.fila = tCuentas.getSelectedRow();
            if (control.fila >= 0) {
                idCuentaProv = tCuentas.getValueAt(control.fila, 0).toString();
                cboBanco.setSelectedItem(tCuentas.getValueAt(control.fila, 2).toString());
                txtNumeroCuenta.setText(tCuentas.getValueAt(control.fila, 3).toString());
                btnCrear.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnEditar.setEnabled(true);
                cboBanco.setEnabled(true);
                txtNumeroCuenta.setEditable(true);

            }
        }
    }//GEN-LAST:event_tCuentasMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarCuenta();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorKeyReleased
        BuscarProveedores();
    }//GEN-LAST:event_txtBuscarProveedorKeyReleased

    private void txtNumeroCuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroCuentaKeyPressed
        if (btnCrear.isEnabled()) {
            control.passFocus(evt, btnCrear);
        } else {
            control.passFocus(evt, btnEditar);
        }
    }//GEN-LAST:event_txtNumeroCuentaKeyPressed

    private void cboBancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboBancoKeyPressed
        if (cboBanco.getSelectedIndex() >= 0) {
            control.passFocus(evt, txtNumeroCuenta);
        }
    }//GEN-LAST:event_cboBancoKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox cboBanco;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbProveedor;
    private javax.swing.JTable tCuentas;
    private javax.swing.JTable tProveedores1;
    private javax.swing.JTextField txtBuscarProveedor;
    private javax.swing.JTextField txtNumeroCuenta;
    // End of variables declaration//GEN-END:variables

    private void crearCuentaBancaria() {
        if (btnCrear.getText().equals("Nuevo")) {
            btnCrear.setText("Guardar");
            cboBanco.setEnabled(true);
            txtNumeroCuenta.setEditable(true);
        } else {
            if (cboBanco.getItemCount() > 0) {
                if (txtNumeroCuenta.getText().trim().length() > 0) {

                    control.Sql = "select idBanco from banco where nombre='" + cboBanco.getSelectedItem().toString() + "';";
                    String codBanc = control.DevolverRegistroDto(control.Sql, 1);
                    control.Sql = "CALL CuentaProveedor('0','" + txtNumeroCuenta.getText().trim() + "','" + codBanc + "','" + idProveedor + "','0');";

                    String resultado = control.ejecutarMsg(control.Sql);
                    if (resultado.equals("1")) {
                        JOptionPane.showMessageDialog(rootPane, "La cuenta se ha registrado correctamente");
                        // Cancelar();
                        try {
                            cboBanco.setSelectedIndex(0);
                        } catch (Exception e) {
                        }

                        txtNumeroCuenta.setText("");
                        cboBanco.setEnabled(false);
                        txtNumeroCuenta.setEditable(false);
                        btnCrear.setText("Nuevo");
                        //BuscarProveedores();
                        mostrarCuentaBancaria();
                        if (FrmRegistrarPagoFacturaProveedor.cboBancoProveedor != null) {
                            FrmRegistrarPagoFacturaProveedor.recargarCuentas();
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, resultado);
                        txtNumeroCuenta.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Indique el número de cuenta");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Indique el banco");
            }
        }

    }

    private void BuscarProveedores() {
        control.Sql = "select idProveedor,Ruc, nombre from Proveedor where ruc like'"
                + txtBuscarProveedor.getText() + "%' or nombre like'"
                + txtBuscarProveedor.getText() + "%'";
        control.LlenarJTabla(modeloProveedores, control.Sql, 3);
        try {
            cboBanco.setSelectedIndex(0);
        } catch (Exception e) {
        }
        txtNumeroCuenta.setEditable(false);
        cboBanco.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCrear.setEnabled(false);
        txtNumeroCuenta.setText("");

    }

    private void SelecionarProvvedor() {
        if (tProveedores1.getSelectedRowCount() == 1) {
            Ruc = modeloProveedores.getValueAt(tProveedores1.getSelectedRow(), 1).toString();
            idProveedor = modeloProveedores.getValueAt(tProveedores1.getSelectedRow(), 0).toString();
            lbProveedor.setText("" + modeloProveedores.getValueAt(tProveedores1.getSelectedRow(), 2).toString());
            //System.out.print(modelo.getValueAt(tProveedores1.getSelectedRow(), 1).toString());
            btnCrear.setEnabled(true);
            btnCancelar.setEnabled(true);
            btnSalir.setEnabled(true);
            txtNumeroCuenta.setText("");
            mostrarCuentaBancaria();
            //tCuentas.setEnabled(false);
        }
    }

    private void mostrarCuentaBancaria() {

        control.Sql = "SELECT c.`idCuentaProveedor`, p.`RUC`, b.`nombre`, c.`numerocunt` FROM cuentaproveedor c, proveedor p, banco b WHERE p.`idProveedor`=c.`idProveedor` AND b.`idBanco`=c.`idBanco` and p.idProveedor ='" + idProveedor + "' order by b.nombre asc";

        control.LlenarJTabla(modeloCuentasBancarias, control.Sql, 4);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);

    }

    private void Cancelar() {
        btnCrear.setText("Nuevo");
        btnCrear.setEnabled(false);
        btnEditar.setEnabled(false);
        cboBanco.setEnabled(false);
        txtNumeroCuenta.setEditable(false);
        txtNumeroCuenta.setText(" ");
        try {
            cboBanco.setSelectedIndex(0);
        } catch (Exception e) {
        }

        txtBuscarProveedor.setText("");
        txtBuscarProveedor.grabFocus();
        tCuentas.setEnabled(true);
        tProveedores1.setEnabled(true);
        mostrarCuentaBancaria();

    }

    private void EditarCuentaBamcaria() {
        if (tCuentas.getSelectedRowCount() == 1) {
            btnEditar.setEnabled(true);
            cboBanco.setSelectedItem(modeloCuentasBancarias.getValueAt(tCuentas.getSelectedRow(), 3).toString());
            lbProveedor.setText(modeloCuentasBancarias.getValueAt(tCuentas.getSelectedRow(), 2).toString());
            Ruc = modeloCuentasBancarias.getValueAt(tCuentas.getSelectedRow(), 1).toString();
            txtNumeroCuenta.setText(modeloCuentasBancarias.getValueAt(tCuentas.getSelectedRow(), 4).toString());
            CodProv = modeloCuentasBancarias.getValueAt(tCuentas.getSelectedRow(), 0).toString();
            tProveedores1.setEnabled(false);
        }
    }

    private void ModificarCuenta() {

        if (cboBanco.getItemCount() > 0) {
            if (txtNumeroCuenta.getText().trim().length() > 0) {
                control.Sql = "select idBanco from banco where nombre='" + cboBanco.getSelectedItem().toString() + "';";
                String codBanc = control.DevolverRegistroDto(control.Sql, 1);

                control.Sql = "CALL CuentaProveedor('" + idCuentaProv + "','" + txtNumeroCuenta.getText().trim() + "','" + codBanc + "','" + idProveedor + "','1');";
                String resultado = control.ejecutarMsg(control.Sql);
                //System.out.print(control.Sql);
                if (resultado.equals("2")) {
                    JOptionPane.showMessageDialog(rootPane, "La cuenta se ha guardado correctamente");
                    btnCrear.setEnabled(true);
                    cboBanco.setSelectedIndex(0);
                    txtNumeroCuenta.setText("");
                    txtNumeroCuenta.setEditable(false);
                    cboBanco.setEnabled(false);
                    mostrarCuentaBancaria();
                    if (FrmRegistrarPagoFacturaProveedor.cboBancoProveedor != null) {
                        FrmRegistrarPagoFacturaProveedor.recargarCuentas();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, resultado);
                    txtNumeroCuenta.grabFocus();

                }
            } else {
                JOptionPane.showMessageDialog(this, "Indique el número de la cuenta");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Indique el banco");
        }

    }

    private void EliminarCuenta() {
        control.fila = tCuentas.getSelectedRow();
        if (control.fila >= 0) {
            idCuentaProv = tCuentas.getValueAt(control.fila, 0).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "¿Confirma que desea eliminar la cuenta seleccionada?", "Confirmar", 0) == 0) {
                control.Sql = "call CuentaProveedor('" + idCuentaProv + "','0','0','0','2')";
                if (control.ejecutar(control.Sql)) {
                    mostrarCuentaBancaria();
                    if (FrmRegistrarPagoFacturaProveedor.cboBancoProveedor != null) {
                        FrmRegistrarPagoFacturaProveedor.recargarCuentas();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede eliminar la cuenta selecionada, pues está actualmente en uso");
                }

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione la cuenta a eliminar");
        }
    }
}
