package jym.ferreteria.gui.dialogs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class ordendePedido extends javax.swing.JDialog {

    private Controlador control = new Controlador();
    private CustomTableModel model = new CustomTableModel();
    private CustomTableModel modelo2 = new CustomTableModel();
    String IDVenta = "";
    String Cliente = "";
    String IDcliente = "";
    String Factura = "";
    String DniRuc = "";
    String IDproducto = "";
    String producto = "";
    String unidad = "";
    String cantidadInicial = "";
    String cantidadEntregada = "";
    String cantidadEntregadaRestore = "";
    String IDcomprobante = null;
    int filaRestore = 0;

    public ordendePedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        List listData = control.getListData("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante=3 "
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;");

        if (listData.size() > 0) {
            String tipo = listData.get(0).toString();
            String[] generarComprobante = control.generarComprobante(tipo);
            IDcomprobante = generarComprobante[0];
            lblOrdenPedido.setText(generarComprobante[1]);
        } else {
            JOptionPane.showMessageDialog(parent, "Debe Inicializar la Numeración de la Orden de Pedido");
        }

        setIconImage(new ImageIcon(getClass().getResource(Controlador.ICON_PATH)).getImage());
        jdtFecha.setDate(new Date());
        txtLLegada.requestFocus();

        model.setColumnIdentifiers(new String[]{"ID", "Descripción", "Unidad", "Cantidad Inicial", "Cantidad Entregada Guia", "Cantidad Entregada Orden"});
        tablaProductosInicial.setModel(model);
        control.hideTableColumn(tablaProductosInicial, 0, 4);
        control.tableWidthColumn(tablaProductosInicial, 300, 1);
        modelo2.setColumnIdentifiers(new String[]{"ID", "Descripción", "Unidad", "Cantidad"});
        tablaProductoEntregar.setModel(modelo2);
        control.hideTableColumn(tablaProductoEntregar, 0);
        control.tableWidthColumn(tablaProductoEntregar, 500, 1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jdtFecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtLLegada = new javax.swing.JTextField();
        lblOrdenPedido = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductoEntregar = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        lblProductoSeleccionado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductosInicial = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Fecha de Emisión:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Referencia");

        txtLLegada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLLegadaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jdtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtLLegada)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLLegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblOrdenPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblOrdenPedido.setForeground(new java.awt.Color(255, 0, 0));
        lblOrdenPedido.setText("N°OrdenPedido");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Cliente:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("DNI/RUC:");

        lblCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(255, 0, 0));

        lblDni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblDni.setForeground(new java.awt.Color(255, 0, 0));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaProductoEntregar.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductoEntregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoEntregarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductoEntregar);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Producto:");

        lblProductoSeleccionado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblProductoSeleccionado.setForeground(new java.awt.Color(255, 0, 0));

        tablaProductosInicial.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductosInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosInicialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductosInicial);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Cantidad:");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });

        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pencil-icon.png"))); // NOI18N
        btnEditarProducto.setEnabled(false);
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash-icon.png"))); // NOI18N
        btnEliminarProducto.setEnabled(false);
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnCancelarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(255, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel11)
                        .addGap(5, 5, 5)
                        .addComponent(lblProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(7, 7, 7)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarProducto)
                    .addComponent(btnEliminarProducto)
                    .addComponent(btnCancelarProducto))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblOrdenPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblOrdenPedido)
                        .addComponent(jLabel7))
                    .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (IDcomprobante != null) {
            if (txtLLegada.getText().trim().length() > 0) {
                if (tablaProductoEntregar.getRowCount() > 0) {
                    this.dispose();
                    insertarOrdenPedido();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Debe Agregar un Producto");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe Ingresar una Referencia");
                txtLLegada.requestFocus();
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Debe Inicializar la Numeración del Orden de Pedido");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cancelar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtLLegadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLLegadaKeyReleased
        control.Mayusculas(txtLLegada);
    }//GEN-LAST:event_txtLLegadaKeyReleased

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed

        control.LimTabla(modelo2);

        tablaProductosInicial.setValueAt(cantidadEntregadaRestore, filaRestore, 4);
        tablaProductosInicial.setValueAt(0, filaRestore, 5);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        lblProductoSeleccionado.setText("");
        txtCantidad.setText("");
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Confirma que deseal eliminar el producto seleccionado?", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            quitarProducto();
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed

        cargarProducto();
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (evt.getKeyChar() == 10) {
            if (tablaProductoEntregar.getSelectedRow() > -1) {
                editarProducto();
            } else {
                if (Double.parseDouble(txtCantidad.getText()) <= (Double.parseDouble(cantidadInicial) - Double.parseDouble(cantidadEntregada))) {
                    validarCantidad(cantidadInicial);

                } else {
                    JOptionPane.showMessageDialog(rootPane, "La cantidad debe ser menor o igual a la adquirida");
                    txtCantidad.requestFocus();
                }
            }

        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void tablaProductosInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosInicialMouseClicked
        if (evt.getClickCount() > 1) {
            lblProductoSeleccionado.setText(tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 1).toString());

            IDproducto = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 0).toString();
            producto = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 1).toString();
            unidad = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 2).toString();
            cantidadInicial = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 3).toString();
            cantidadEntregada = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 5).toString();
            cantidadEntregadaRestore = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 5).toString();
            filaRestore = tablaProductosInicial.getSelectedRow();
            int cantidadActual = Integer.parseInt(cantidadInicial) - Integer.parseInt(cantidadEntregada);
            //double cantidadActual = Double.parseDouble(cantidadInicial) - Double.parseDouble(cantidadEntregada);
            txtCantidad.setText(""+cantidadActual);

            txtCantidad.requestFocus();

        }
    }//GEN-LAST:event_tablaProductosInicialMouseClicked

    private void tablaProductoEntregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoEntregarMouseClicked
        if (evt.getClickCount() == 1) {
            btnEliminarProducto.setEnabled(true);
            btnEditarProducto.setEnabled(true);
        }

    }//GEN-LAST:event_tablaProductoEntregarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ordendePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ordendePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ordendePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ordendePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ordendePedido dialog = new ordendePedido(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdtFecha;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblOrdenPedido;
    private javax.swing.JLabel lblProductoSeleccionado;
    private javax.swing.JTable tablaProductoEntregar;
    private javax.swing.JTable tablaProductosInicial;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtLLegada;
    // End of variables declaration//GEN-END:variables

    public void setDatos(String idVenta, String cliente, String idCliente, String factura, String dniRuc) {
        IDVenta = idVenta;
        IDcliente = idCliente;
        Cliente = cliente;
        Factura = factura;
        DniRuc = dniRuc;
        lblCliente.setText(Cliente);
        lblDni.setText(DniRuc);
        llenarTablaInicial(IDVenta);

    }

    private void llenarTablaInicial(String IDVenta) {
        control.Sql = "SELECT p.`idProduto`,concat(t.`nomtip`,' ', m.`nommarc`,' ', p.`nomproducto`), u.`nomuniddes`, d.`cantidad`,\n"
                + "(select ifnull(sum(dg.cantidadProd),0) from guiaderemision g,detalleguiaremision dg where g.idguiaderemision=dg.idguiaderemision and g.idventa=v.idventa and d.iddetalleventa=dg.iddetalleventa) as Cantidad_EntregadaGuia\n"
                + ",(select ifnull(sum(dp.cantidadProd),0) from ordenpedido o,detalleordenpedido dp where o.idordenpedido=dp.idordenpedido and o.idventa=v.idventa and d.iddetalleventa=dp.iddetalleventa) as Cantidad_EntregadaOrden\n"
                + "FROM produto p, unidades u, marca m, tipoproducto t, detalleventa d,venta v\n"
                + "WHERE v.idventa=d.idventa and d.`idProducto`=p.`idProduto` AND t.`idTipoProducto`=p.`idTipoProducto` AND m.`idMarca`=p.`idMarca` and u.idunidades=p.idunidades and v.idventa='" + IDVenta + "' having (cantidad-Cantidad_EntregadaGuia)<>0 and (cantidad-Cantidad_EntregadaOrden)<>0;";
        System.out.println(control.Sql);
        control.LlenarJTabla(model, control.Sql, 6);

    }

    private void agregarDetalle() {
        modelo2.addRow(new String[]{IDproducto, producto, unidad, txtCantidad.getText()});
        lblProductoSeleccionado.setText("");
        txtCantidad.setText("");
        producto = "";
        unidad = "";
        IDproducto = "";
        cantidadInicial = "";
    }

    private void insertarOrdenPedido() {

        control.Sql = String.format("INSERT INTO ordenpedido VALUES(NULL,'%s','%s','%s','%s');", control.Formato_Amd(jdtFecha.getDate()), IDVenta, txtLLegada.getText(), IDcomprobante);
        int idOrdenPedido = control.executeAndGetId(control.Sql);
        control.Sql = "UPDATE comprobante c SET c.`estado`='Emitido' WHERE c.`idComprobante`='" + IDcomprobante + "'";
        control.ejecutar(control.Sql);
        llenarDetalleOrdenPedido(idOrdenPedido);
        ImprimirOrden(idOrdenPedido);
        JOptionPane.showMessageDialog(rootPane, "Se Registró Correctamente la Orden de Pedido");

    }

    private void llenarDetalleOrdenPedido(int idOrdenPedido) {   
        for (int i = 0; i < tablaProductoEntregar.getRowCount(); i++) {
            control.Sql = "SELECT idDetalleVenta,idalmacen,cantidad,idProducto FROM `detalleventa` where idVenta='" + IDVenta + "' and idProducto='" + tablaProductoEntregar.getValueAt(i, 0).toString() + "'";
            String IDdetalleVenta = control.DevolverRegistroDto(control.Sql, 1);
            String IDAlmacen = control.DevolverRegistroDto(control.Sql, 2);
            String IDProducto = control.DevolverRegistroDto(control.Sql, 4);
            control.Sql = String.format("INSERT INTO detalleordenpedido VALUES(NULL,'%s','%s','%s');", IDdetalleVenta, idOrdenPedido, tablaProductoEntregar.getValueAt(i, 3).toString());
            control.ejecutar(control.Sql);
            control.Sql="select count(*) from entregarluego where idventa='"+IDVenta+"';";
            int ContarEntregarluego = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            //int IdEntregarluego = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 2));
            if(ContarEntregarluego == 1){
                control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",tablaProductoEntregar.getValueAt(i, 3).toString(),IDAlmacen,IDProducto));
            }
        }
    }

    private void validarCantidad(String cantidadInicial) {

        double contadorCantidad = 0;
        double cantidadRestante = 0;
        for (int i = 0; i < tablaProductoEntregar.getRowCount(); i++) {
            if (tablaProductoEntregar.getValueAt(i, 0).toString() == null ? IDproducto == null : tablaProductoEntregar.getValueAt(i, 0).toString().equals(IDproducto)) {
                contadorCantidad = contadorCantidad + Double.parseDouble(tablaProductoEntregar.getValueAt(i, 3).toString());
            }
        }
        cantidadRestante = Double.parseDouble(cantidadInicial) - contadorCantidad;
        if (Double.parseDouble(txtCantidad.getText()) <= cantidadRestante) {
            int cantTot = Integer.parseInt(txtCantidad.getText()) + Integer.parseInt(cantidadEntregada);
            //double cantTot = Double.parseDouble(txtCantidad.getText()) + Double.parseDouble(cantidadEntregada);
            tablaProductosInicial.setValueAt(cantTot, tablaProductosInicial.getSelectedRow(), 5);
            agregarDetalle();
        } else {

            JOptionPane.showMessageDialog(rootPane, "La cantidad Ingresada debe ser menor o igual a la adquiridad");
        }

    }

    private void cancelar() {

        txtLLegada.setText("");
        lblProductoSeleccionado.setText("");
        txtCantidad.setText("");
        control.LimTabla(modelo2);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);

    }

    private void quitarProducto() {
        for (int i = 0; i < tablaProductosInicial.getRowCount(); i++) {
            if (tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 0).toString().equals(tablaProductosInicial.getValueAt(i, 0).toString())) {
                double cantidadEntregada = Double.parseDouble(tablaProductosInicial.getValueAt(i, 4).toString()) - Double.parseDouble(tablaProductoEntregar.getValueAt(i, 3).toString());
                tablaProductosInicial.setValueAt(cantidadEntregada, i, 4);
               tablaProductosInicial.setValueAt(0, i, 5);
            }
        }
        modelo2.removeRow(tablaProductoEntregar.getSelectedRow());
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
    }

    private void cargarProducto() {
        btnEditarProducto.setEnabled(true);
        String idproducto = tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 0).toString();
        lblProductoSeleccionado.setText(tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 1).toString());
        txtCantidad.setText(tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 3).toString());

        IDproducto = tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 0).toString();
        producto = tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 1).toString();
        unidad = tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 2).toString();
        for (int i = 0; i < tablaProductosInicial.getRowCount(); i++) {
            if (tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 0).toString().equals(tablaProductosInicial.getValueAt(i, 0).toString())) {
                cantidadInicial = tablaProductosInicial.getValueAt(i, 3).toString();

                double cantidadEntregada = Double.parseDouble(tablaProductosInicial.getValueAt(i, 4).toString()) - Double.parseDouble(tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 3).toString());
                tablaProductosInicial.setValueAt(cantidadEntregada, i, 4);
                modelo2.removeRow(tablaProductoEntregar.getSelectedRow());

            }
        }

    }

    private void editarProducto() {
        validarCantidad(cantidadInicial);
    }

    private void ImprimirOrden(int idordenpedido) {
        String idVenta = "";

        control.Sql = "SELECT idventa from ordenpedido where idordenpedido='" + idordenpedido + "';";
        idVenta = control.DevolverRegistroDto(control.Sql, 1);
        Map map = new HashMap();
        map.put("idVenta", idVenta);
        map.put("idOrden", idordenpedido);
        control.showReportDialog("Orden de Pedido", "ordenDePedidosinVenta", map);

    }
}
