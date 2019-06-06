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
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class CuentaBancariaProveedor extends javax.swing.JInternalFrame {

    private CustomTableModel modelo1 = new CustomTableModel();
    private Controlador control = new Controlador();
    private String Ruc = "", CodProv = "";
    private String idCuentaProv;
    private String idProveedor;

    public CuentaBancariaProveedor() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));

        tCuentas.setModel(modelo1);
        modelo1.setColumnIdentifiers(new String[]{"Codigo", "Ruc", "Banco", "Cuenta Bancaria"});
        tCuentas.getColumnModel().getColumn(1).setPreferredWidth(100);
        tCuentas.getColumnModel().getColumn(2).setPreferredWidth(200);
        tCuentas.getColumnModel().getColumn(3).setPreferredWidth(100);

        control.hideTableColumn(tCuentas, 0);
        control.LlenarCombo(cboBanco, "SELECT * FROM banco ", 2);
        btnCrear.setText("Crear");
        btnEditar.setText("Editar");
        activar(false);
    }

    public void activar(boolean b) {
        cboBanco.setEnabled(b);
        txtNumeroCuenta.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbProveedor = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        txtNumeroCuenta = new javax.swing.JTextField();
        cboBanco = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCuentas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cuentas bancarias de proveedor");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(51, 0, 153))); // NOI18N

        lbProveedor.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
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
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        bEliminar.setText("Eliminar");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4))
        );

        txtNumeroCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroCuentaKeyPressed(evt);
            }
        });

        cboBanco.setForeground(new java.awt.Color(0, 51, 102));
        cboBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboBancoKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Banco:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("N° Cuenta:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbProveedor))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
}//GEN-LAST:event_bSalirActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if (btnCrear.getText().equals("Crear")) {
            btnCrear.setText("Grabar");
            btnEditar.setEnabled(false);
            bEliminar.setEnabled(false);
            this.activar(true);
            // this.limpiar();
            cboBanco.requestFocus();
        } else {
            this.crearCuentaBancaria();
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        Cancelar();
        btnCrear.setEnabled(true);
        btnEditar.setEnabled(true);
        bEliminar.setEnabled(true);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (txtNumeroCuenta.getText().length() > 0 && cboBanco.getSelectedItem().toString() != null) {
            //&& cboBanco.getSelectedItem().toString()!=null
            ModificarCuenta();
        } else {
            JOptionPane.showMessageDialog(this, "Debe cargar los datos Para Modificar");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCuentasMouseClicked
        if (evt.getClickCount() > 1) {
            control.fila = tCuentas.getSelectedRow();
            activar(true);
            if (control.fila >= 0) {
                idCuentaProv = tCuentas.getValueAt(control.fila, 0).toString();
                cboBanco.setSelectedItem(tCuentas.getValueAt(control.fila, 2).toString());
                txtNumeroCuenta.setText(tCuentas.getValueAt(control.fila, 3).toString());
                btnCrear.setEnabled(false);
                bEliminar.setEnabled(false);

            }
        }
    }//GEN-LAST:event_tCuentasMouseClicked

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        EliminarCuenta();
    }//GEN-LAST:event_bEliminarActionPerformed

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
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    public static javax.swing.JComboBox cboBanco;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProveedor;
    private javax.swing.JTable tCuentas;
    private javax.swing.JTextField txtNumeroCuenta;
    // End of variables declaration//GEN-END:variables
    private void crearCuentaBancaria() {
        if (cboBanco.getSelectedIndex() >= 0) {
            if (txtNumeroCuenta.getText().trim().length() > 0) {
                idCuentaProv = Integer.toString(control.DevIdentificador("cuentaproveedor", "idcuentaproveedor"));

                control.Sql = "select idBanco from banco where nombre='" + cboBanco.getSelectedItem().toString() + "';";
                String codBanc = control.DevolverRegistroDto(control.Sql, 1);
                control.Sql = "CALL CuentaProveedor('" + idCuentaProv + "','" + txtNumeroCuenta.getText().trim() + "','" + codBanc + "','" + idProveedor + "','0');";

                String resultado = control.ejecutarMsg(control.Sql);
                if (resultado.equals("1")) {
                    JOptionPane.showMessageDialog(rootPane, "Se Registró Correctamente");
                    cboBanco.setSelectedIndex(-1);
                    txtNumeroCuenta.setText("");
                    this.activar(false);
                    btnCrear.setText("Crear");
                    btnEditar.setEnabled(true);
                    bEliminar.setEnabled(true);
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

    private void mostrarCuentaBancaria() {

        control.Sql = "SELECT c.`idCuentaProveedor`, p.`RUC`, b.`nombre`, c.`numerocunt` FROM cuentaproveedor c, proveedor p, banco b WHERE p.`idProveedor`=c.`idProveedor` AND b.`idBanco`=c.`idBanco` and p.idProveedor ='" + idProveedor + "' order by b.nombre asc";

        control.LlenarJTabla(modelo1, control.Sql, 4);

    }

    private void Cancelar() {
        btnCrear.setText("Crear");
        btnEditar.setText("Editar");
        txtNumeroCuenta.setText("");
        cboBanco.setSelectedIndex(-1);
        tCuentas.clearSelection();
        activar(false);
        mostrarCuentaBancaria();
    }

    private void EditarCuentaBamcaria() {
        if (tCuentas.getSelectedRowCount() == 1) {
            btnEditar.setEnabled(true);
            cboBanco.setSelectedItem(modelo1.getValueAt(tCuentas.getSelectedRow(), 3).toString());
            lbProveedor.setText(modelo1.getValueAt(tCuentas.getSelectedRow(), 2).toString());
            Ruc = modelo1.getValueAt(tCuentas.getSelectedRow(), 1).toString();
            txtNumeroCuenta.setText(modelo1.getValueAt(tCuentas.getSelectedRow(), 4).toString());
            CodProv = modelo1.getValueAt(tCuentas.getSelectedRow(), 0).toString();

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una cuenta para Modificar");
        }
    }

    private void ModificarCuenta() {
        if (cboBanco.getSelectedIndex() >= 0) {
            if (txtNumeroCuenta.getText().trim().length() > 0) {
                control.Sql = "select idBanco from banco where nombre='" + cboBanco.getSelectedItem().toString() + "';";
                String codBanc = control.DevolverRegistroDto(control.Sql, 1);
                control.Sql = "select idproveedor from Proveedor where nombre='" + lbProveedor.getText() + "' and Ruc='" + Ruc + "';";

                control.Sql = "CALL CuentaProveedor('" + idCuentaProv + "','" + txtNumeroCuenta.getText().trim() + "','" + codBanc + "','" + idProveedor + "','1');";
                String resultado = control.ejecutarMsg(control.Sql);
                //System.out.print(control.Sql);
                if (resultado.equals("2")) {
                    JOptionPane.showMessageDialog(rootPane, "Se Actualizó Correctamente");
                    btnCrear.setEnabled(true);
                    bEliminar.setEnabled(true);
                    cboBanco.setSelectedIndex(-1);
                    txtNumeroCuenta.setText("");
                    this.activar(false);
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

    private void EliminarCuenta() {
        control.fila = tCuentas.getSelectedRow();
        if (control.fila >= 0) {
            idCuentaProv = tCuentas.getValueAt(control.fila, 0).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "Seguro deseas eliminar", "Confirmar", 0) == 0) {
                control.Sql = "call CuentaProveedor('" + idCuentaProv + "','0','0','0','2')";
                boolean ejecutar = control.ejecutar(control.Sql);
                if (ejecutar) {
                    mostrarCuentaBancaria();
                    Cancelar();
                    if (FrmRegistrarPagoFacturaProveedor.cboBancoProveedor != null) {
                        FrmRegistrarPagoFacturaProveedor.recargarCuentas();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "El Cuenta no se puede eliminar por que actualmente está en uso");

                }

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione la fila a eliminar");
        }
    }

    public void setdatosproveedor(String Idproveedor, String nomProveedor, String ruc) {
        this.idProveedor = Idproveedor;
        this.Ruc = ruc;
        lbProveedor.setText(nomProveedor);
        mostrarCuentaBancaria();
    }

}
