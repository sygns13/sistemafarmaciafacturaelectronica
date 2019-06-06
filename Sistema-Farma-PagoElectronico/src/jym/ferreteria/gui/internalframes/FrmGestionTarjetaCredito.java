/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.gui.dialogs.CobrarVentaDialog;
import jym.ferreteria.renders.CustomTableModel;

/**
 *
 * @author Omr
 */
public class FrmGestionTarjetaCredito extends javax.swing.JInternalFrame {

    private static Controlador control = new Controlador();
    private CustomTableModel model = new CustomTableModel();

    /**
     * Creates new form FrmGestionTarjetaCredito
     */
    public FrmGestionTarjetaCredito() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        model.setColumnIdentifiers(new String[]{"Id", "Tarjeta"});
        tablaTarjeta.setModel(model);
        control.hideTableColumn(tablaTarjeta, 0);
        habilitar(false);
        cargarTarjetas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreTarjeta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTarjeta = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión tarjetas de crédito");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Document.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.setPreferredSize(new java.awt.Dimension(100, 33));
        btnNuevo.setSelected(true);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);

        btnModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(118, 33));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(112, 33));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(116, 33));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        btnSalir.setMnemonic('S');
        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(92, 33));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tarjeta:");

        txtNombreTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreTarjetaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNombreTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaTarjeta.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaTarjeta.getTableHeader().setReorderingAllowed(false);
        tablaTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTarjetaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTarjeta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        registrarTarjeta();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarTarjeta();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarTarjeta();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreTarjetaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (btnNuevo.isEnabled()) {
                btnNuevo.doClick();
            } else {
                btnModificar.doClick();
            }
        }
    }//GEN-LAST:event_txtNombreTarjetaKeyPressed

    private void tablaTarjetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTarjetaMouseClicked
        if (evt.getClickCount() > 1 && tablaTarjeta.isEnabled()) {
            modificarTarjeta();
        }
    }//GEN-LAST:event_tablaTarjetaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTarjeta;
    private javax.swing.JTextField txtNombreTarjeta;
    // End of variables declaration//GEN-END:variables

    private void habilitar(boolean b) {
        txtNombreTarjeta.setEditable(b);
        txtNombreTarjeta.setText("");
    }

    private void cargarTarjetas() {
        control.LlenarJTabla(model, "SELECT * FROM `tipotarjeta` ORDER BY `nombre`;", 2);
    }

    private void registrarTarjeta() {
        if (btnNuevo.getText().equals("Nuevo")) {
            btnNuevo.setText("Grabar");
            btnNuevo.setMnemonic('G');
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            tablaTarjeta.setEnabled(false);
            habilitar(true);
            txtNombreTarjeta.requestFocus();
        } else {
            if (!txtNombreTarjeta.getText().trim().isEmpty()) {
                String c = control.DevolverRegistroDto(String.format("SELECT COUNT(*) FROM `tipotarjeta` WHERE `nombre`='%s';", txtNombreTarjeta.getText()), 1);
                if (c.equals("0")) {
                    boolean ejecutar = control.ejecutar(String.format("INSERT INTO tipotarjeta VALUES(NULL,'%s');", txtNombreTarjeta.getText()));
                    if (ejecutar) {
                        cargarTarjetas();
                        habilitar(false);
                        btnNuevo.setText("Nuevo");
                        btnNuevo.setMnemonic('N');
                        btnModificar.setEnabled(true);
                        btnEliminar.setEnabled(true);
                        tablaTarjeta.setEnabled(true);
                        JOptionPane.showMessageDialog(this, "La tarjeta se ha guardado satisfactoriamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            CobrarVentaDialog.recargarTipoTarjeta();
                        } catch (Exception e) {
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar la tarjeta", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe una tarjeta con el nombre indicado, indique otro nombre e intente nuevamente", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Indique el nombre de la tarjeta", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void modificarTarjeta() {
        if (tablaTarjeta.getSelectedRow() >= 0) {
            if (btnModificar.getText().equals("Modificar")) {
                btnModificar.setText("Grabar");
                btnModificar.setMnemonic('G');
                btnNuevo.setEnabled(false);
                btnEliminar.setEnabled(false);
                tablaTarjeta.setEnabled(false);
                habilitar(true);
                txtNombreTarjeta.setText(model.getValueAt(tablaTarjeta.getSelectedRow(), 1).toString());
                txtNombreTarjeta.requestFocus();
            } else {

                if (!txtNombreTarjeta.getText().trim().isEmpty()) {
                    String c = control.DevolverRegistroDto(String.format("SELECT COUNT(*) FROM `tipotarjeta` WHERE `nombre`='%s' AND `idTipoTarjeta`<>%s;", txtNombreTarjeta.getText(), model.getValueAt(tablaTarjeta.getSelectedRow(), 0).toString()), 1);
                    if (c.equals("0")) {
                        boolean ejecutar = control.ejecutar(String.format("UPDATE tipotarjeta t SET t.`nombre`='%s' WHERE t.`idTipoTarjeta`=%s;",
                                txtNombreTarjeta.getText(), model.getValueAt(tablaTarjeta.getSelectedRow(), 0).toString()));
                        if (ejecutar) {
                            cargarTarjetas();
                            habilitar(false);
                            btnModificar.setText("Modificar");
                            btnModificar.setMnemonic('M');
                            btnNuevo.setEnabled(true);
                            btnEliminar.setEnabled(true);
                            tablaTarjeta.setEnabled(true);
                            JOptionPane.showMessageDialog(this, "La cuenta tarjeta se ha guardado satisfactoriamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                CobrarVentaDialog.recargarTipoTarjeta();
                            } catch (Exception e) {
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar la tarjeta", "Mensaje", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ya existe una tarjeta con el nombre indicado, indique otro nombre e intente nuevamente", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Indique el número de la tarjeta", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione la tarjeta que desea modificar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarTarjeta() {
        if (tablaTarjeta.getSelectedRow() >= 0) {
            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "¿Confirma que desea eliminar la tarjeta seleccionada?", "Mensaje", JOptionPane.YES_NO_OPTION);
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                boolean ejecutar = control.ejecutar(String.format("DELETE FROM tipotarjeta WHERE `idTipoTarjeta`=%s;", model.getValueAt(tablaTarjeta.getSelectedRow(), 0).toString()));
                if (ejecutar) {
                    cargarTarjetas();
                    habilitar(false);
                    try {
                        CobrarVentaDialog.recargarTipoTarjeta();
                    } catch (Exception e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la tarjeta selccionada ya que actualmente tiene datos en uso", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione la tarjeta que desea eliminar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelar() {
        cargarTarjetas();
        habilitar(false);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnNuevo.setMnemonic('N');
        btnModificar.setMnemonic('M');
        btnNuevo.setText("Nuevo");
        btnModificar.setText("Modificar");
        tablaTarjeta.setEnabled(true);
    }
}