package jym.ferreteria.gui.internalframes;

import jym.ferreteria.clases.Controlador;
import jym.ferreteria.clases.InfoGeneral;
import jym.ferreteria.renders.CustomTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class RegistroIngresoCaja extends javax.swing.JInternalFrame {

        private CustomTableModel modelo = new CustomTableModel();
        private Controlador control = new Controlador();
        private String idingresocaja = "";

        public RegistroIngresoCaja() {
                initComponents();
                jtbTablaIngresoCaja.setModel(modelo);
                modelo.setColumnIdentifiers(new String[]{"id","Tipo de Mov","Concepto", "Monto", "Comprobante", "N° de Comprobante", "Fecha","Hora"});
                mostrarIngresoCaja();
                control.hideTableColumn(jtbTablaIngresoCaja, 0);
                control.tableMaxWidthColumn(jtbTablaIngresoCaja, 80, 3, 6,7);
                jdFecha.setDate(new Date());
                activar(false);
                cbutipo1.addItem("Ingreso");
                cbutipo1.addItem("Salida");
                cbuComp.addItem("Libre");
                 cbuComp.addItem("Boleta");
                  cbuComp.addItem("Factura");
                   cbuComp.addItem("Recibo por Honorarios");
                
                this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));

                ((JTextField) jdFecha.getDateEditor()).addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (control.isEnterKey(e)) {
                                        bCrear.grabFocus();
                                }
                        }
                });
        }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtmonto = new javax.swing.JTextField();
        jdFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtConcepto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbuComp = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtComprobante = new javax.swing.JTextField();
        cbutipo1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbTablaIngresoCaja = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro de Movimientos de Caja");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del Movimiento de Caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel1.setOpaque(false);

        txtmonto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmonto.setText("0.00");
        txtmonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmontoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmontoKeyTyped(evt);
            }
        });

        jdFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Comprobante:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Concepto:");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Monto:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Fecha:");

        txtConcepto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConceptoKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("S/.");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Tipo:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("N° Comprobante:");

        txtComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtComprobanteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbuComp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(cbutipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbutipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbuComp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lista de Movimiento de Caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel2.setOpaque(false);

        jtbTablaIngresoCaja.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbTablaIngresoCaja.getTableHeader().setReorderingAllowed(false);
        jtbTablaIngresoCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbTablaIngresoCajaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbTablaIngresoCaja);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Buscar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setForeground(new java.awt.Color(0, 51, 102));
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bCrear.setMnemonic('c');
        bCrear.setText("Nuevo");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });

        bModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bModificar.setForeground(new java.awt.Color(0, 51, 102));
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('d');
        bModificar.setText("Editar");
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setForeground(new java.awt.Color(0, 51, 102));
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/eliminar.png"))); // NOI18N
        bEliminar.setMnemonic('e');
        bEliminar.setText("Eliminar");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(0, 51, 102));
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('a');
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setForeground(new java.awt.Color(0, 51, 102));
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(bCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(bModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(bEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
                if ("Nuevo".equals(bCrear.getText())) {
                        bCrear.setText("Registrar");
                        activar(true);
                        bModificar.setEnabled(false);
                        bEliminar.setEnabled(false);
                        txtmonto.grabFocus();

                } else {
                        registrarIngreso();

                }

    }//GEN-LAST:event_bCrearActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
                ModificarIngresoMarca();
                activar(false);
    }//GEN-LAST:event_bModificarActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
                EliminarIngresoCaja();
    }//GEN-LAST:event_bEliminarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed

                //txBuscar.setText("");
                Cancelar();
                activar(false);
                bCrear.grabFocus();
                //MostrarMarca();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
                dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    private void txtmontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmontoKeyPressed
                
        if (evt.getKeyCode() == evt.VK_ENTER) {
                        if (txtmonto.getText().trim().length() > 0) {
                                txtConcepto.grabFocus();
                        } else {
                                JOptionPane.showMessageDialog(rootPane, "Debe Ingresar Un Monto");
                        }
                }
    }//GEN-LAST:event_txtmontoKeyPressed

    private void txtConceptoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyPressed
                if (evt.getKeyChar() == 10) {
                        if (txtConcepto.getText().trim().length() > 0) {
                                txtComprobante.grabFocus();
                        } else {
                                JOptionPane.showMessageDialog(txtComprobante, "Debe Ingresar El Concepto");
                                txtConcepto.grabFocus();
                        }
                }

    }//GEN-LAST:event_txtConceptoKeyPressed

    private void jtbTablaIngresoCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbTablaIngresoCajaMouseClicked
                if (evt.getClickCount() > 1) {
                        cargarDatosEditar();
                        txtmonto.grabFocus();

                }
    }//GEN-LAST:event_jtbTablaIngresoCajaMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
                mostrarIngresoCaja();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtmontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmontoKeyTyped
         control.decimal(evt, txtmonto.getText());
    }//GEN-LAST:event_txtmontoKeyTyped

    private void txtComprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprobanteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprobanteKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbuComp;
    private javax.swing.JComboBox cbutipo1;
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
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JTable jtbTablaIngresoCaja;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtComprobante;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtmonto;
    // End of variables declaration//GEN-END:variables

        private void registrarIngreso() {

                if (txtmonto.getText().trim().length() > 0) {
                        if (txtConcepto.getText().trim().length() > 0) {
                                if (jdFecha.getDate() != null) {
                                    String tipo=cbutipo1.getSelectedItem().toString();
                                    String comp=cbuComp.getSelectedItem().toString();
                                        control.Sql = "CALL GestionCaja('0','" + txtmonto.getText() + "','" + txtConcepto.getText() + "','" + txtComprobante.getText() + "','" + control.Formato_Amd(jdFecha.getDate()) + "'," + InfoGeneral.getIdSede() + ",'0','"+tipo+"','"+comp+"');";
                                        JOptionPane.showMessageDialog(rootPane, control.DevolverRegistroDto(control.Sql, 1));
                                        Cancelar();
                                        mostrarIngresoCaja();
                                        activar(false);
                                        bModificar.setEnabled(true);
                                        bEliminar.setEnabled(true);

                                } else {
                                        JOptionPane.showMessageDialog(txtComprobante, "Debe ingresar la fecha de Ingreso");
                                        ((JTextField) jdFecha.getDateEditor()).grabFocus();
                                }
                        } else {
                                JOptionPane.showMessageDialog(txtComprobante, "Debe Ingresar el Concepto");
                                txtConcepto.grabFocus();
                        }
                } else {
                        JOptionPane.showMessageDialog(txtComprobante, "Debe ingresar el monto");
                        txtmonto.grabFocus();
                }

        }

        private void Cancelar() {
                txtmonto.setText("0.00");
                txtBuscar.setText("");
                txtComprobante.setText("");
                txtConcepto.setText("");
                jdFecha.setDate(new Date());
                bCancelar.setEnabled(true);
                bCrear.setEnabled(true);
                bEliminar.setEnabled(true);
                bModificar.setEnabled(true);
                bSalir.setEnabled(true);
                bCrear.setText("Nuevo");
                cbuComp.setSelectedIndex(1);
                cbutipo1.setSelectedIndex(1);
                cbuComp.setSelectedItem("Libre");
                cbutipo1.setSelectedItem("Ingreso");

        }

        private void activar(boolean b) {
                txtmonto.setEnabled(b);
                txtComprobante.setEnabled(b);
                txtConcepto.setEnabled(b);
                jdFecha.setEnabled(b);
                cbuComp.setEnabled(b);
                cbutipo1.setEnabled(b);

        }

        private void mostrarIngresoCaja() {
                buscarIngresoCaja();

        }

        private void buscarIngresoCaja() {
                control.Sql = "SELECT i.`idIngresoCaja`, i.`tipo`,i.`concepto` , i.`monto`, i.`tipoComp`, i.`comprobante`, date_format(i.`fecha`,'%d/%m/%Y'), i.`hora` FROM ingresocaja i where i.concepto like'%" + txtBuscar.getText() + "%' or i.tipo like'%" + txtBuscar.getText() + "%' or date_format(i.`fecha`,'%d/%m/%Y') like '%" + txtBuscar.getText() + "%';";
                control.LlenarJTabla(modelo, control.Sql, 8);

        }

        private void cargarDatosEditar() {
                control.fila = jtbTablaIngresoCaja.getSelectedRow();
                activar(true);
                if (control.fila >= 0) {
                        bCrear.setEnabled(false);
                        bEliminar.setEnabled(false);
                        idingresocaja = jtbTablaIngresoCaja.getValueAt(control.fila, 0).toString();
                        txtmonto.setText(jtbTablaIngresoCaja.getValueAt(control.fila, 3).toString());
                        txtConcepto.setText(jtbTablaIngresoCaja.getValueAt(control.fila, 2).toString());
                        txtComprobante.setText(jtbTablaIngresoCaja.getValueAt(control.fila, 5).toString());
                       // ((JTextField) jdFecha.getDateEditor()).setText(jtbTablaIngresoCaja.getValueAt(control.fila, 6).toString());
                        //control.PonerFechaenDateChooser(jdFecha, jtbTablaIngresoCaja.getValueAt(control.fila, 6).toString());
                        //jdFecha.setDate(jtbTablaIngresoCaja.getValueAt(control.fila, 4).toString());
                        
                        String fechacargar=jtbTablaIngresoCaja.getValueAt(control.fila, 6).toString();
                        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha = null;
                        
                        try {

fecha = formatoDelTexto.parse(fechacargar);
this.jdFecha.setDate(fecha);

} catch (ParseException ex) {

ex.printStackTrace();

}
                        
                        String tipo= jtbTablaIngresoCaja.getValueAt(control.fila, 1).toString();
                        
                        String comprobante= jtbTablaIngresoCaja.getValueAt(control.fila, 4).toString();
                        
                        if(tipo.equals("Ingreso"))
                        {
                            cbutipo1.setSelectedIndex(0);
                        }
                        else
                        {
                            cbutipo1.setSelectedIndex(1);
                        }
                        
                        cbuComp.setSelectedItem(comprobante);
                }

        }

        private void ModificarIngresoMarca() {

                if (txtmonto.getText().trim().length() > 0) {
                        if (txtConcepto.getText().trim().length() > 0) {
                                if (jdFecha.getDate() != null) {
                                    String tipo=cbutipo1.getSelectedItem().toString();
                                    String comp=cbuComp.getSelectedItem().toString();
                                        control.Sql = "CALL GestionCaja('" + idingresocaja + "','" + txtmonto.getText() + "','" + txtConcepto.getText() + "','" + txtComprobante.getText() + "','" + control.Formato_Amd(jdFecha.getDate()) + "','" + InfoGeneral.getIdSede() + "','1','"+tipo+"','"+comp+"');";
                                        //System.out.println(control.Sql);
                                        JOptionPane.showMessageDialog(rootPane, control.DevolverRegistroDto(control.Sql, 1));
                                        idingresocaja = "";
                                        Cancelar();
                                        mostrarIngresoCaja();

                                } else {
                                        JOptionPane.showMessageDialog(txtComprobante, "Debe ingresar la fecha de Ingreso");
                                }
                        } else {
                                JOptionPane.showMessageDialog(txtComprobante, "Debe Ingresar el Concepto");
                        }
                } else {
                        JOptionPane.showMessageDialog(txtComprobante, "Debe ingresar el monto");
                }

        }

        private void EliminarIngresoCaja() {
                control.fila = jtbTablaIngresoCaja.getSelectedRow();
                if (control.fila >= 0) {
                        idingresocaja = jtbTablaIngresoCaja.getValueAt(control.fila, 0).toString();
                        if (JOptionPane.showConfirmDialog(this, "¿Seguro que desea Eliminar el Ingreso de Caja Seleccionado?", "Confirmar", 0) == 0) {
                                control.Sql = "CALL GestionCaja('" + idingresocaja + "','0','0','0',null,0,'3','0','0');";
                                System.out.println(control.Sql);
                                JOptionPane.showMessageDialog(rootPane, control.DevolverRegistroDto(control.Sql, 1));
                                Cancelar();
                                mostrarIngresoCaja();
                        }

                }

        }
}
