package jym.ferreteria.gui.internalframes;

/**
 * ** @author Ing. Miguel Angel Silva Zapata. *********
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class FrmGestionBancos extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private Controlador control = new Controlador();
    private String idBnc;
    private int num;

    public FrmGestionBancos() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tBancos.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"Código", "Banco", "Dirección"});
        control.hideTableColumn(tBancos, 0);
        tBancos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tBancos.getColumnModel().getColumn(2).setPreferredWidth(250);
        cargarBancos();
    }

    /**
     * ***********************Métodos***********************
     */
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
        txBanco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txDireccion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tBancos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de bancos");

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
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
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('e');
        bModificar.setText("Editar");
        bModificar.setPreferredSize(new java.awt.Dimension(96, 33));
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
        bEliminar.setPreferredSize(new java.awt.Dimension(110, 33));
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
        bCancelar.setToolTipText("");
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
        bSalir.setPreferredSize(new java.awt.Dimension(90, 33));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Banco:");

        txBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBancoKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Dirección:");

        txDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDireccionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txBanco)
                    .addComponent(txDireccion))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Buscar");

        tBancos.setForeground(new java.awt.Color(0, 51, 102));
        tBancos.setModel(new javax.swing.table.DefaultTableModel(
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
        tBancos.setRowSorter(null);
        tBancos.getTableHeader().setReorderingAllowed(false);
        tBancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tBancosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tBancos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        CrearBanco();
}//GEN-LAST:event_bCrearActionPerformed
private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        modificarBanco();
}//GEN-LAST:event_bModificarActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        cancelar();
}//GEN-LAST:event_bCancelarActionPerformed
private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        eliminarBanco();
}//GEN-LAST:event_bEliminarActionPerformed
private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        cargarBancos();
}//GEN-LAST:event_txBuscarKeyReleased
private void tBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tBancosMouseClicked
        if (evt.getClickCount() > 1) {
            verBanco();
        }
}//GEN-LAST:event_tBancosMouseClicked

    private void txBancoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBancoKeyReleased

        if (evt.getKeyChar() == 10 && txBanco.getText().trim().length() > 0) {
            txDireccion.grabFocus();
        }
    }//GEN-LAST:event_txBancoKeyReleased

    private void txDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDireccionKeyPressed
        if (bCrear.isEnabled()) {
            control.passFocus(evt, bCrear);
        } else {
            control.passFocus(evt, bModificar);
        }
    }//GEN-LAST:event_txDireccionKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tBancos;
    private javax.swing.JTextField txBanco;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txDireccion;
    // End of variables declaration//GEN-END:variables

    private void CrearBanco() {
        if (txBanco.getText().trim().length() > 0) {
            idBnc = Integer.toString(control.DevIdentificador("banco", "idBanco"));
            control.Sql = "call LosBancos('" + idBnc + "','" + txBanco.getText() + "','"
                    + txDireccion.getText() + "','0')";
            String resultado = control.CrearRegistroDev(control.Sql);
            if (resultado.equals("1")) {

                cancelar();
                cargarBancos();
                recargarBancosOtrasVentanas();
                JOptionPane.showMessageDialog(rootPane, "El banco se ha registrado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(rootPane, resultado, "Mensaje", JOptionPane.WARNING_MESSAGE);
                txBanco.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Indique el nombre del banco", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelar() {
        txBanco.setText(null);
        txDireccion.setText(null);
        tBancos.clearSelection();
        txBuscar.setText(null);
        txBanco.requestFocus();
    }

    private void modificarBanco() {
        if (txBanco.getText().length() > 0) {
            control.Sql = "call LosBancos('" + idBnc + "','" + txBanco.getText() + "','" + txDireccion.getText() + "','1')";
            String rs = control.EditarRegistroDev(control.Sql);
            if (rs.equals("2")) {
                idBnc = null;
                cancelar();
                cargarBancos();
                JOptionPane.showMessageDialog(rootPane, "El banco se ha actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                recargarBancosOtrasVentanas();
            } else {
                JOptionPane.showMessageDialog(rootPane, rs, "Mensaje", JOptionPane.WARNING_MESSAGE);
                txBanco.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Indique el nombre del banco", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarBanco() {
        control.fila = tBancos.getSelectedRow();
        if (control.fila >= 0) {
            idBnc = tBancos.getValueAt(control.fila, 0).toString();
            num = Integer.parseInt(idBnc);
            if (JOptionPane.showConfirmDialog(this, "¿Confirma que desea eliminar el banco seleccionado?", "Confirmar", 0) == 0) {
                control.Sql = "call LosBancos('" + idBnc + "','" + txBanco.getText() + "','" + txDireccion.getText() + "','2')";
                if (control.ejecutar(control.Sql)) {
//                    control.ModificarEstrTabla(num, "banco");
                    cancelar();
                    cargarBancos();
                    recargarBancosOtrasVentanas();
                } else {
                    JOptionPane.showMessageDialog(this, "El banco seleccionado no se puede eliminar, pues actualmente está en uso", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }

            }
        }
    }

    private void verBanco() {
        int fila = tBancos.getSelectedRow();
        if (fila >= 0) {
            idBnc = tBancos.getValueAt(fila, 0).toString();
            System.out.print(idBnc);
            txBanco.setText(tBancos.getValueAt(fila, 1).toString());
            txDireccion.setText(tBancos.getValueAt(fila, 2).toString());
        }
    }

    private void cargarBancos() {
        control.Sql = "select * from banco where nombre like'" + txBuscar.getText() + "%' ORDER BY nombre";
        control.LlenarJTabla(modelo, control.Sql, 3);
    }

    private void recargarBancosOtrasVentanas() {
        //Recargando el combo banco de la ventana de CuentaBancaria
        try {
            if (CuentaBancaria.cboBanco != null) {
                Object obj = CuentaBancaria.cboBanco.getSelectedItem();
                control.LlenarCombo(CuentaBancaria.cboBanco, "SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", 2);
                if (obj != null) {
                    CuentaBancaria.cboBanco.setSelectedItem(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FrmGestionBancos.class.getName()).log(Level.SEVERE, null, e);
        }
        //Recargando el combo banco de la ventana de CuentaBancariaProveedor
        try {
            if (CuentaBancariaProveedor.cboBanco != null) {
                Object obj = CuentaBancariaProveedor.cboBanco.getSelectedItem();
                control.LlenarCombo(CuentaBancariaProveedor.cboBanco, "SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", 2);
                if (obj != null) {
                    CuentaBancariaProveedor.cboBanco.setSelectedItem(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FrmGestionBancos.class.getName()).log(Level.SEVERE, null, e);
        }
        //Recargando el combo banco de Emisor de la ventana de FrmRegistrarPagoFacturaProveedor
        try {
            if (FrmRegistrarPagoFacturaProveedor.cboBancoEmisor != null) {
                Object obj = FrmRegistrarPagoFacturaProveedor.cboBancoEmisor.getSelectedItem();
                control.LlenarCombo(FrmRegistrarPagoFacturaProveedor.cboBancoEmisor, "SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", 2);
                if (obj != null) {
                    FrmRegistrarPagoFacturaProveedor.cboBancoEmisor.setSelectedItem(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FrmGestionBancos.class.getName()).log(Level.SEVERE, null, e);
        }
        //Recargando el combo banco proveedor de la ventana de FrmRegistrarPagoFacturaProveedor
        try {
            if (FrmRegistrarPagoFacturaProveedor.cboBancoProveedor != null) {
                Object obj = FrmRegistrarPagoFacturaProveedor.cboBancoProveedor.getSelectedItem();
                FrmRegistrarPagoFacturaProveedor.cboBancoProveedorCargado = false;
                control.LlenarCombo(FrmRegistrarPagoFacturaProveedor.cboBancoProveedor, "SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", 2);
                FrmRegistrarPagoFacturaProveedor.cboBancoProveedorCargado = true;
                if (obj != null) {
                    FrmRegistrarPagoFacturaProveedor.cboBancoProveedor.setSelectedItem(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FrmGestionBancos.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
