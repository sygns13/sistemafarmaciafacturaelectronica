/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.clases.*;

/**
 *
 * @author VALE
 */
public class FrmGuiasRemisionEmitidas extends javax.swing.JInternalFrame {

    private CustomTableModel modeloCabecera = new CustomTableModel();
    private CustomTableModel modeloDetalle = new CustomTableModel();
    private Controlador control = new Controlador();

    public FrmGuiasRemisionEmitidas() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        jdtFechaInicio.setDate(new Date());
        jdtFechaFin.setDate(new Date());

        modeloCabecera.setColumnIdentifiers(new String[]{"idGuiaRemision", "Fec.Emision", "N°GuiaRemision", "N°Comprob.Adjunto", "Cliente", "DNI/RUC", "Punto de LLegada", "Transportista", "RUC transportista", "Marca/Placa", "idVenta"});
        tablaGuiaRemisionCabecera.setModel(modeloCabecera);
        control.hideTableColumn(tablaGuiaRemisionCabecera, 0, 7, 8, 9, 10);
        control.tableWidthColumn(tablaGuiaRemisionCabecera, 150, 2, 3, 4);
        control.tableWidthColumn(tablaGuiaRemisionCabecera, 40, 5);
        control.tableWidthColumn(tablaGuiaRemisionCabecera, 50, 1);
        llenarGuiaCabecera();
        modeloDetalle.setColumnIdentifiers(new String[]{"idGuiaRemision", "Unidades", "Producto", "Precio", "Cantidad", "Total"});
        tablaGuiaRemisionDetalle.setModel(modeloDetalle);
        control.hideTableColumn(tablaGuiaRemisionDetalle, 0);
        control.tableWidthColumn(tablaGuiaRemisionDetalle, 35, 1, 3, 4);
        control.tableWidthColumn(tablaGuiaRemisionDetalle, 350, 2);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdtFechaInicio = new com.toedter.calendar.JDateChooser();
        jdtFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGuiaRemisionCabecera = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaGuiaRemisionDetalle = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Guias de Remision Emitidas");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Desde");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Hasta");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Buscar:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
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

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        jButton4.setText("Imprimir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de Pedido Emitidos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tablaGuiaRemisionCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaGuiaRemisionCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaGuiaRemisionCabeceraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaGuiaRemisionCabecera);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Orden de Pedido (Productos)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tablaGuiaRemisionDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaGuiaRemisionDetalle);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4))
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jdtFechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(jdtFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        llenarGuiaCabecera();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaGuiaRemisionCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaGuiaRemisionCabeceraMouseClicked
        if (evt.getClickCount() == 1) {
            llenarGuiaDetalle(tablaGuiaRemisionCabecera.getValueAt(tablaGuiaRemisionCabecera.getSelectedRow(), 0).toString(), tablaGuiaRemisionCabecera.getValueAt(tablaGuiaRemisionCabecera.getSelectedRow(), 10).toString());
        }
    }//GEN-LAST:event_tablaGuiaRemisionCabeceraMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        llenarGuiaCabecera();
        control.LimTabla(modeloDetalle);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        control.LimTabla(modeloDetalle);
        tablaGuiaRemisionCabecera.getSelectionModel().clearSelection();
        jdtFechaFin.setDate(new Date());
        jdtFechaInicio.setDate(new Date());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ImprimirGuia(tablaGuiaRemisionCabecera.getValueAt(tablaGuiaRemisionCabecera.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdtFechaFin;
    private com.toedter.calendar.JDateChooser jdtFechaInicio;
    private javax.swing.JTable tablaGuiaRemisionCabecera;
    private javax.swing.JTable tablaGuiaRemisionDetalle;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void llenarGuiaCabecera() {
        control.Sql = "SELECT g.`idguiaderemision`,date_format(g.`fechaEmision`,'%d-%m-%Y') as Fecha, (select concat(t.`nombre`,'-',co.`serie`,'-',co.`numero`)as Comprobante from comprobante co, tipocomprobante t where t.`idTipoComprobante`=co.`idTipoComprobante` AND co.`idComprobante`=g.`idComprobante`)as nGuiaRemision, (SELECT concat(t.`nombre`,' ', c.`serie`,'-', c.`numero`) FROM comprobante c, tipocomprobante t WHERE t.`idTipoComprobante`=c.`idTipoComprobante` AND c.`idComprobante`=v.`idComprobante` AND v.`idVenta`=g.`idVenta`)as NcomprobanteAdjunto, c.`nomcte` as CLIENTE, c.`dniRuc`,g.`llegada`, g.`nomTranport`, g.`rucTransport`, g.`marcaPlaca`,g.`idVenta` FROM guiaderemision g, venta v, cliente c WHERE c.`idCliente`=v.`idCliente` AND v.`idVenta`=g.`idVenta` AND g.`fechaEmision` between '" + control.Formato_Amd(jdtFechaInicio.getDate()) + "' AND '" + control.Formato_Amd(jdtFechaFin.getDate()) + "' and (c.`nomcte` LIKE '%" + txtBuscar.getText() + "%' or c.`dniRuc` LIKE '%" + txtBuscar.getText() + "%' OR (select concat(t.`nombre`,'-',co.`serie`,'-',co.`numero`)as Comprobante from comprobante co, tipocomprobante t where t.`idTipoComprobante`=co.`idTipoComprobante` AND co.`idComprobante`=g.`idComprobante`) LIKE '%" + txtBuscar.getText() + "%' OR (SELECT concat(t.`nombre`,' ', c.`serie`,'-', c.`numero`) FROM comprobante c, tipocomprobante t WHERE t.`idTipoComprobante`=c.`idTipoComprobante` AND c.`idComprobante`=v.`idComprobante` AND v.`idVenta`=g.`idVenta`) LIKE '%" + txtBuscar.getText() + "%') ORDER BY g.`fechaEmision` desc;";
        //System.out.println(control.Sql);
        control.LlenarJTabla(modeloCabecera, control.Sql, 11);
    }

    private void llenarGuiaDetalle(String IDGuia, String IDventa) {
        control.Sql = "SELECT g.`idguiaderemision`, u.`nomuniddes`,concat(m.`nommarc`,' ', t.`nomtip`,' ', p.`nomproducto`), dv.`precioVenta`, d.`cantidadProd`,concat((dv.`precioVenta`*d.`cantidadProd`),'.00')as total FROM guiaderemision g, detalleguiaremision d, detalleventa dv, produto p, unidades u, tipoproducto t, marca m WHERE m.`idMarca`=p.`idMarca` AND t.`idTipoProducto`=p.`idTipoProducto` AND u.`idUnidades`=p.`idUnidades` AND p.`idProduto`=dv.`idProducto` AND dv.`idDetalleVenta`=d.`iddetalleventa` AND g.`idguiaderemision`=d.`idguiaderemision` AND g.`idguiaderemision`='" + IDGuia + "' AND g.`idVenta`='" + IDventa + "';";
        control.LlenarJTabla(modeloDetalle, control.Sql, 6);
    }

    private void ImprimirGuia(String idGuiaRemision) {
        try {
            String motivo = "", idVenta = "";
            control.Sql = "SELECT concat(idmotivo,',') FROM `motivo_has_guiaderemision` where idGuiaderemision='" + idGuiaRemision + "';";
            ResultSet DevolverRegistro = control.DevolverRegistro(control.Sql);
            while (DevolverRegistro.next()) {
                
                motivo += DevolverRegistro.getString(1);

            }
            idVenta = tablaGuiaRemisionCabecera.getValueAt(tablaGuiaRemisionCabecera.getSelectedRow(), 10).toString();
            Map map = new HashMap();
            map.put("idVenta", idVenta);
            map.put("idGuia", idGuiaRemision);
            map.put("motivos", motivo);
            control.showReportDialog("Guia de Remision", "reporteGuiaDeRemision", map);
        } catch (SQLException ex) {
            Logger.getLogger(FrmGuiasRemisionEmitidas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
