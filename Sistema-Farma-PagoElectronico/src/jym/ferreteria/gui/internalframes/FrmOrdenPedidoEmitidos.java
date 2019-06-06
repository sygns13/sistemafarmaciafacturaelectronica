/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.clases.*;

/**
 *
 * @author VALE
 */
public class FrmOrdenPedidoEmitidos extends javax.swing.JInternalFrame {

    private CustomTableModel modeloCabecera = new CustomTableModel();
    private CustomTableModel modeloDetalle = new CustomTableModel();
    private Controlador control = new Controlador();

    public FrmOrdenPedidoEmitidos() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        jdtFechaInicio.setDate(new Date());
        jdtFechaFin.setDate(new Date());

        modeloCabecera.setColumnIdentifiers(new String[]{"idOrdenPedido", "Fec.Emision", "N°OrdenPedido", "N°Comprob.Adjunto", "Cliente", "DNI/RUC", "Referencia", "idVenta"});
        tablaOrdenCabecera.setModel(modeloCabecera);
        control.hideTableColumn(tablaOrdenCabecera, 0, 7);
        control.tableWidthColumn(tablaOrdenCabecera, 190, 4);
        control.tableWidthColumn(tablaOrdenCabecera, 180, 2, 3);
        control.tableWidthColumn(tablaOrdenCabecera, 80, 1, 5);
        control.tableWidthColumn(tablaOrdenCabecera, 120, 6);

        modeloDetalle.setColumnIdentifiers(new String[]{"idOrdenPedido", "Unidades", "Producto", "Precio", "Cantidad", "Total"});
        tablaOrdenDetalle.setModel(modeloDetalle);
        control.hideTableColumn(tablaOrdenDetalle, 0);
        control.tableWidthColumn(tablaOrdenDetalle, 35, 1, 3, 4);
        control.tableWidthColumn(tablaOrdenDetalle, 350, 2);
        llenarOrdenCabecera();

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
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaOrdenCabecera = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOrdenDetalle = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Orden de Pedido Emitidos");
        setPreferredSize(new java.awt.Dimension(855, 471));

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

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        jButton4.setText("Imprimir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de Pedido Emitidos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tablaOrdenCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaOrdenCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaOrdenCabeceraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaOrdenCabecera);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Orden de Pedido (Productos)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tablaOrdenDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaOrdenDetalle);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar))
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jdtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdtFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        llenarOrdenCabecera();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaOrdenCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaOrdenCabeceraMouseClicked
        if (evt.getClickCount() == 1) {
            llenarOrdenDetalle(tablaOrdenCabecera.getValueAt(tablaOrdenCabecera.getSelectedRow(), 0).toString(), tablaOrdenCabecera.getValueAt(tablaOrdenCabecera.getSelectedRow(), 7).toString());
        }
    }//GEN-LAST:event_tablaOrdenCabeceraMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        llenarOrdenCabecera();
        control.LimTabla(modeloDetalle);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        control.LimTabla(modeloDetalle);
        tablaOrdenCabecera.getSelectionModel().clearSelection();
        jdtFechaFin.setDate(new Date());
        jdtFechaInicio.setDate(new Date());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ImprimirOrden(tablaOrdenCabecera.getValueAt(tablaOrdenCabecera.getSelectedRow(), 0).toString());
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
    private javax.swing.JTable tablaOrdenCabecera;
    private javax.swing.JTable tablaOrdenDetalle;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void llenarOrdenCabecera() {

        control.Sql = "SELECT g.`idordenpedido`,date_format(g.`fechaEmision`,'%d-%m-%Y') as Fecha,(select concat(t.`nombre`,'-',co.`serie`,'-',co.`numero`)as Comprobante from comprobante co, tipocomprobante t where t.`idTipoComprobante`=co.`idTipoComprobante` AND co.`idComprobante`=g.`idComprobante`)as NORDENPEDIDO, (SELECT concat(t.`nombre`,' ', c.`serie`,'-', c.`numero`) FROM comprobante c, tipocomprobante t WHERE t.`idTipoComprobante`=c.`idTipoComprobante` AND c.`idComprobante`=v.`idComprobante` AND v.`idVenta`=g.`idVenta`)as NcomprobanteAdjunto, c.`nomcte` as CLIENTE, c.`dniRuc`,g.`referencia`, g.`idVenta` FROM ordenpedido g, venta v, cliente c WHERE c.`idCliente`=v.`idCliente` AND v.`idVenta`=g.`idVenta` AND g.`fechaEmision` between '" + control.Formato_Amd(jdtFechaInicio.getDate()) + "' AND '" + control.Formato_Amd(jdtFechaFin.getDate()) + "' and (c.`nomcte` LIKE '%" + txtBuscar.getText() + "%' or c.`dniRuc` LIKE '%" + txtBuscar.getText() + "%' OR (select concat(t.`nombre`,'-',co.`serie`,'-',co.`numero`)as Comprobante from comprobante co, tipocomprobante t where t.`idTipoComprobante`=co.`idTipoComprobante` AND co.`idComprobante`=g.`idComprobante`) LIKE '%" + txtBuscar.getText() + "%' OR (SELECT concat(t.`nombre`,' ', c.`serie`,'-', c.`numero`) FROM comprobante c, tipocomprobante t WHERE t.`idTipoComprobante`=c.`idTipoComprobante` AND c.`idComprobante`=v.`idComprobante` AND v.`idVenta`=g.`idVenta`) LIKE '%" + txtBuscar.getText() + "%') ORDER BY g.`fechaEmision` desc;";
        //System.out.println(control.Sql);
        control.LlenarJTabla(modeloCabecera, control.Sql, 8);
    }

    private void llenarOrdenDetalle(String IDOrden, String IDventa) {
        control.Sql = "SELECT g.`idordenpedido`, u.`nomuniddes`,concat(m.`nommarc`,' ', t.`nomtip`,' ', p.`nomproducto`), dv.`precioVenta`, d.`cantidadProd`,concat((dv.`precioVenta`*d.`cantidadProd`),'.00')as total FROM ordenpedido g, detalleordenpedido d, detalleventa dv, produto p, unidades u, tipoproducto t, marca m WHERE m.`idMarca`=p.`idMarca` AND t.`idTipoProducto`=p.`idTipoProducto` AND u.`idUnidades`=p.`idUnidades` AND p.`idProduto`=dv.`idProducto` AND dv.`idDetalleVenta`=d.`iddetalleventa` AND g.`idordenpedido`=d.`idordenpedido` AND g.`idordenpedido`='" + IDOrden + "' AND g.`idVenta`='" + IDventa + "';";
        control.LlenarJTabla(modeloDetalle, control.Sql, 6);
    }
    
    private void ImprimirOrden(String idordenpedido) {
        String idVenta = "";
        idVenta = tablaOrdenCabecera.getValueAt(tablaOrdenCabecera.getSelectedRow(), 7).toString();
        Map map = new HashMap();
        map.put("idVenta", idVenta);
        map.put("idOrden", idordenpedido);
        control.showReportDialog("Orden de Pedido", "ordenDePedidosinVenta", map);

    }
}
