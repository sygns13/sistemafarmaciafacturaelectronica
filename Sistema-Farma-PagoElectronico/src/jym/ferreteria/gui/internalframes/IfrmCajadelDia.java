package jym.ferreteria.gui.internalframes;

import jym.ferreteria.clases.Controlador;
import jym.ferreteria.clases.InfoGeneral;
import static jym.ferreteria.gui.internalframes.FrmReporteCajaDiaria.CAJA_DIARIA;
import static jym.ferreteria.gui.internalframes.FrmReporteCajaDiaria.CAJA_DIARIA_DETALLE;
import static jym.ferreteria.gui.internalframes.FrmReporteCajaDiaria.GASTOS;
import static jym.ferreteria.gui.internalframes.FrmReporteCajaDiaria.GASTOS_PAGO_PROVEEDOR;
import static jym.ferreteria.gui.internalframes.FrmReporteCajaDiaria.INGRESOS;
import static jym.ferreteria.gui.internalframes.FrmReporteCajaDiaria.INGRESOS_VENTAS;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import jym.ferreteria.clases.Accesos;

public class IfrmCajadelDia extends javax.swing.JInternalFrame implements KeyListener {

        private Controlador controlador = new Controlador();
        private String idusu="";

        public IfrmCajadelDia() {
                initComponents();
                this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
                txtCajaInicial.addKeyListener(this);
                txtTotalEfectivo.addKeyListener(this);
                txtIngresoVentas.addKeyListener(this);
                txtOtrosIngresos.addKeyListener(this);
                txtGastoPagoDeProveedores.addKeyListener(this);
                txtOtrosGastos.addKeyListener(this);
                txtTotalIngresos.addKeyListener(this);
                txtTotalGastos.addKeyListener(this);
                lblFecha.setText("Fecha: " + controlador.Formato_Amd1(new Date(), "/"));
                
                
                
                userspeshal();
                calcularMontos();
                
        }
        
        public void userspeshal(){
    idusu=Accesos.getInstance().getIdUsuario();
    if(idusu.equals("0")){

    }
    }
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtCajaInicial = new javax.swing.JTextField();
        txtTotalEfectivo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtIngresoVentas = new javax.swing.JTextField();
        txtOtrosIngresos = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtGastoPagoDeProveedores = new javax.swing.JTextField();
        txtOtrosGastos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotalGastos = new javax.swing.JTextField();
        txtTotalIngresos = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnImprimirResumen = new javax.swing.JButton();
        btnImprimirDetallado = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cierre diario");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Caja Inicial");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Total en Efectivo");

        lblFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(0, 51, 102));
        lblFecha.setText("Fecha:");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pencil-icon.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtCajaInicial.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtTotalEfectivo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTotalEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalEfectivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCajaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCajaInicial)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalEfectivo))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ingresos Efectivo del Día"));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Ventas:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Otros Ingresos:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtIngresoVentas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtOtrosIngresos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtOtrosIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIngresoVentas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIngresoVentas)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOtrosIngresos)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Salidas del Día"));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Pago a Proveedores:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Otros Gastos:");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtGastoPagoDeProveedores.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtOtrosGastos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtOtrosGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtGastoPagoDeProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGastoPagoDeProveedores)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOtrosGastos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("Total Ingresos en Efectivo:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Total Gastos:");

        txtTotalGastos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtTotalIngresos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton2.setMnemonic('S');
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Refresh-icon.png"))); // NOI18N
        jButton8.setMnemonic('R');
        jButton8.setText("Recalcular");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnImprimirResumen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimirResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimirResumen.setMnemonic('R');
        btnImprimirResumen.setText("Imprimir resumen");
        btnImprimirResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirResumenActionPerformed(evt);
            }
        });

        btnImprimirDetallado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimirDetallado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimirDetallado.setMnemonic('R');
        btnImprimirDetallado.setText("Imprimir detallado");
        btnImprimirDetallado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirDetalladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimirDetallado, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalIngresos))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotalIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimirResumen, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnImprimirDetallado, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
                calcularMontos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                imprimir(INGRESOS);
    }//GEN-LAST:event_jButton4ActionPerformed

        private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
                String val = txtCajaInicial.getText().replace(",", "");
                FrmCajaInicial cajaInicial = new FrmCajaInicial(null, true);
                cajaInicial.setValue(val);
                cajaInicial.setModo(1);
                cajaInicial.setLocationRelativeTo(txtCajaInicial);
                cajaInicial.setVisible(true);
                calcularMontos();
        }//GEN-LAST:event_jButton7ActionPerformed

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                imprimir(INGRESOS_VENTAS);
        }//GEN-LAST:event_jButton3ActionPerformed

        private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
                imprimir(GASTOS_PAGO_PROVEEDOR);
        }//GEN-LAST:event_jButton5ActionPerformed

        private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
                imprimir(GASTOS);
        }//GEN-LAST:event_jButton6ActionPerformed

        private void btnImprimirResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirResumenActionPerformed
                imprimir(CAJA_DIARIA);
        }//GEN-LAST:event_btnImprimirResumenActionPerformed

        private void btnImprimirDetalladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirDetalladoActionPerformed
                imprimir(CAJA_DIARIA_DETALLE);
        }//GEN-LAST:event_btnImprimirDetalladoActionPerformed

    private void txtTotalEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalEfectivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimirDetallado;
    private javax.swing.JButton btnImprimirResumen;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField txtCajaInicial;
    private javax.swing.JTextField txtGastoPagoDeProveedores;
    private javax.swing.JTextField txtIngresoVentas;
    private javax.swing.JTextField txtOtrosGastos;
    private javax.swing.JTextField txtOtrosIngresos;
    private javax.swing.JTextField txtTotalEfectivo;
    private javax.swing.JTextField txtTotalGastos;
    private javax.swing.JTextField txtTotalIngresos;
    // End of variables declaration//GEN-END:variables

        private void calcularMontos() {
                double cajaInicial = getCajaDiaria();
                double ingresoVentas = getIngresosEnVentas();
                double otrosIngresos = getOtrosIngresos();
                double pagoProveedores = getPagoProvedores();
                double otrosGastos = getOtrosGastos();
                double totalEfectivo = cajaInicial + ingresoVentas + otrosIngresos - (pagoProveedores + otrosGastos);

                txtCajaInicial.setText(controlador.decimalFormat(cajaInicial));
                txtTotalEfectivo.setText(controlador.decimalFormat(totalEfectivo));
                txtIngresoVentas.setText(controlador.decimalFormat(ingresoVentas));
                txtOtrosIngresos.setText(controlador.decimalFormat(otrosIngresos));
                txtGastoPagoDeProveedores.setText(controlador.decimalFormat(pagoProveedores));
                txtOtrosGastos.setText(controlador.decimalFormat(otrosGastos));
                txtTotalIngresos.setText(controlador.decimalFormat(ingresoVentas + otrosIngresos));
                txtTotalGastos.setText(controlador.decimalFormat(pagoProveedores + otrosGastos));
                if (totalEfectivo < 0) {
                        txtTotalEfectivo.setBackground(Color.red);
                        txtTotalEfectivo.setForeground(Color.white);
                } else {
                        txtTotalEfectivo.setBackground(Color.white);
                        txtTotalEfectivo.setForeground(Color.black);
                }

        }

        private double getCajaDiaria() {
                String caja = controlador.DevolverRegistroDto(String.format("SELECT `monto` FROM `cajainicial` WHERE `fecha`=CURDATE() AND `idSede`=%s;", InfoGeneral.getIdSede()), 1);
                if (caja == null) {
                        return 0.00;
                }
                return controlador.toDouble(caja);
        }

        private double getIngresosEnVentas() {
                String ventas = controlador.DevolverRegistroDto(String.format("SELECT IFNULL(sum(cv.importe),0.00)\n" +
"FROM venta v, tipocomprobante t, comprobante c, cobroventa cv\n" +
"WHERE t.`idTipoComprobante`=c.`idTipoComprobante` and cv.`idVenta`=v.`idVenta`\n" +
"AND c.`idComprobante`=v.`idComprobante`  AND t.`idSede`='%s' and v.estado<>'Anulado' AND v.`fecha`=CURDATE();", InfoGeneral.getIdSede()), 1);
                //System.out.println(String.format("SELECT IFNULL(SUM(d.`precioVenta`),0.00) FROM venta v, tipocomprobante t, detalleventa d, comprobante c WHERE t.`idTipoComprobante`=c.`idTipoComprobante` AND c.`idComprobante`=v.`idComprobante` AND v.`idVenta`=d.`idVenta` AND t.`idSede`=CURDATE() AND v.`fecha`='%s';", InfoGeneral.getIdSede()));
                return Double.parseDouble(ventas);
        }

        private double getOtrosIngresos() {
                String ingresos = controlador.DevolverRegistroDto("SELECT IFNULL(sum(i.monto),0.00) FROM ingresocaja i where i.fecha=curdate() AND i.idSede='" + InfoGeneral.getIdSede() + "' and tipo='Ingreso'", 1);
                return Double.parseDouble(ingresos);
        }

        private double getPagoProvedores() {
                String pagoProveedor = controlador.DevolverRegistroDto(String.format("SELECT IFNULL(SUM(p.`montoReal`),0.00) FROM pagoproveedor p, facturaproveedor f, tipocomprobante t\n"
                + "WHERE t.`idTipoComprobante`=f.`idTipoComprobante` AND p.`fechapago`=CURDATE() AND f.`idFacturaProveedor`=p.`idFacturaProveedor` AND t.`idSede`='%s';", InfoGeneral.getIdSede()), 1);
                return Double.parseDouble(pagoProveedor);
        }

        private double getOtrosGastos() {
                String gastos = controlador.DevolverRegistroDto("SELECT IFNULL(sum(i.monto),0.00) FROM ingresocaja i where i.fecha=curdate() AND i.idSede='" + InfoGeneral.getIdSede() + "' and tipo='Salida'", 1);
                return Double.parseDouble(gastos);
        }

        @Override
        public void keyTyped(KeyEvent e) {
                e.consume();
        }

        @Override
        public void keyPressed(KeyEvent e) {
                e.consume();
        }

        @Override
        public void keyReleased(KeyEvent e) {
                e.consume();
        }

        private void imprimir(int tipo) {
                Date date = new Date();
                Map map = new HashMap();
                map.put("titulo", null);
                map.put("f_inicio", controlador.Formato_Amd(date));
                map.put("f_fin", controlador.Formato_Amd(date));
                map.put("idsede", InfoGeneral.getIdSede());
                switch (tipo) {
                        case CAJA_DIARIA:
                                controlador.showReportDialog("Reporte de caja diaria", "reporteCierreCajaFecha", map);
                                break;
                        case INGRESOS_VENTAS:
                                controlador.showReportDialog("Reporte de caja diaria - Ventas", "cajafinal_subreportIngresosVentas", map);
                                break;
                        case INGRESOS:
                                controlador.showReportDialog("Reporte de caja diaria - Ingresos", "cajafinal_subreportOtrosIngresos", map);
                                break;
                        case GASTOS_PAGO_PROVEEDOR:
                                controlador.showReportDialog("Reporte de caja diaria - Pago Proveedores", "cajafinal_subreportPagoProveedor", map);
                                break;
                        case GASTOS:
                                controlador.showReportDialog("Reporte de caja diaria - Gastos", "cajafinal_subreportOtrosGastos", map);
                                break;
                        case CAJA_DIARIA_DETALLE:
                                double ingresos,gastos;
                                ingresos=Double.parseDouble(txtTotalIngresos.getText().replace(",", ""));
                                gastos=Double.parseDouble(txtTotalGastos.getText().replace(",", ""));
                                map.put("totalIngresos", ingresos);
                                map.put("totalEgresos", gastos);
                                map.put("titulo", "Movimiento de caja ");

                                controlador.showReportDialog("Reporte de caja diaria", "cajafinal", map);
                                break;

                }

        }
}
