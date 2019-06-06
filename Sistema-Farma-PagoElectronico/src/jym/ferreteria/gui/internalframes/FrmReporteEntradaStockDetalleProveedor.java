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
import javax.swing.JTextField;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.datasource.DetalleEntrada;
import jym.ferreteria.datasource.DetalleEntradaProveedor;
import jym.ferreteria.datasource.DetalleEntradaProveedorDataSource;
import jym.ferreteria.datasource.EntradaCabecera;
import jym.ferreteria.datasource.EntradaCabeceraDataSource;
import jym.ferreteria.gui.dialogs.SeleccionarProductoRegularizacionDialog;
import jym.ferreteria.gui.dialogs.SeleccionarProveedorDialog;
public class FrmReporteEntradaStockDetalleProveedor extends javax.swing.JInternalFrame {
    
    
    private Controlador control = new Controlador();


    private SeleccionarProveedorDialog seleccionarProveedor = null;
    private String proveedor;
    
  
     
    
    /**
     * Creates new form FrmReporteEntradaStockDetalleProveedor
     */
    public FrmReporteEntradaStockDetalleProveedor() {
        initComponents();
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
    }
    
    
    
    private void cargarDatos() {
        Map map = new HashMap();
        map.put("proveedor", proveedor == null ? "" : "Proveedor: " + txtProveedor.getText());
        DetalleEntradaProveedorDataSource dataSource = new DetalleEntradaProveedorDataSource();
        String sql = "";
        String fechas = "";
        if (proveedor == null && (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null)) {
            sql = "SELECT e.`numero`, e.`fecha`, p.`nombre`, concat(d.Unidad,' ',pre.`present`,' ',v.`Producto`) as Descripcion, a.`nombre`, d.`cantidad`, d.`costo`, (d.`cantidad`*d.`costo`)as Total\n" +
"                        FROM entradastock e left join facturaproveedor f on e.idfacturaproveedor = f.idfacturaproveedor,proveedor p\n" +
"                        , detalleentradastock d inner join produto pro on pro.idproduto=d.idproducto inner join presentacion pre on pre.idpresentacion=pro.idpresentacion, vta_catalogo v, almacen a\n" +
"                        WHERE e.`idEntradaStock`=d.`idEntradaStock` AND a.`idAlmacen` = d.`idAlmacen`\n" +
"                        AND v.`ID`=d.`idProducto` and P.idproveedor=e.idproveedor;";

        } else if (proveedor == null && (txtFechaDesde.getDate() != null && txtFechaHasta.getDate() != null)) {

            sql = "SELECT e.`numero`, e.`fecha`, p.`nombre`, concat(d.Unidad,' ',pre.`present`,' ',v.`Producto`) as Descripcion, a.`nombre`, d.`cantidad`, d.`costo`, (d.`cantidad`*d.`costo`)as Total\n" +
"                        FROM entradastock e left join facturaproveedor f on e.idfacturaproveedor = f.idfacturaproveedor,proveedor p\n" +
"                        , detalleentradastock d inner join produto pro on pro.idproduto=d.idproducto inner join presentacion pre on pre.idpresentacion=pro.idpresentacion, vta_catalogo v, almacen a\n" +
"                        WHERE e.`idEntradaStock`=d.`idEntradaStock` AND a.`idAlmacen` = d.`idAlmacen`\n" +
"                        AND v.`ID`=d.`idProducto` and P.idproveedor=e.idproveedor and (e.`fecha` between '" + control.Formato_Amd(txtFechaDesde.getDate()) + "'and '" + control.Formato_Amd(txtFechaHasta.getDate()) + "')";
            fechas = "Entradas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
        } else if (proveedor != null) {
            if ((txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null)) {
                sql = "SELECT e.`numero`, e.`fecha`, p.`nombre`, concat(d.Unidad,' ',pre.`present`,' ',v.`Producto`) as Descripcion, a.`nombre`, d.`cantidad`, d.`costo`, (d.`cantidad`*d.`costo`)as Total\n" +
"                        FROM entradastock e left join facturaproveedor f on e.idfacturaproveedor = f.idfacturaproveedor,proveedor p\n" +
"                        , detalleentradastock d inner join produto pro on pro.idproduto=d.idproducto inner join presentacion pre on pre.idpresentacion=pro.idpresentacion, vta_catalogo v, almacen a\n" +
"                        WHERE e.`idEntradaStock`=d.`idEntradaStock` AND a.`idAlmacen` = d.`idAlmacen`\n" +
"                        AND v.`ID`=d.`idProducto` and P.idproveedor=e.idproveedor  and p.idproveedor=" + proveedor;
            } else {
                sql = "SELECT e.`numero`, e.`fecha`, p.`nombre`, concat(d.Unidad,' ',pre.`present`,' ',v.`Producto`) as Descripcion, a.`nombre`, d.`cantidad`, d.`costo`, (d.`cantidad`*d.`costo`)as Total\n" +
"                        FROM entradastock e left join facturaproveedor f on e.idfacturaproveedor = f.idfacturaproveedor,proveedor p\n" +
"                        ,detalleentradastock d inner join produto pro on pro.idproduto=d.idproducto inner join presentacion pre on pre.idpresentacion=pro.idpresentacion, vta_catalogo v, almacen a\n" +
"                        WHERE e.`idEntradaStock`=d.`idEntradaStock` AND a.`idAlmacen` = d.`idAlmacen`\n" +
"                        AND v.`ID`=d.`idProducto` and P.idproveedor=e.idproveedor  and p.`idProveedor`=" + proveedor + " and (e.`fecha` between '" + control.Formato_Amd(txtFechaDesde.getDate()) + "'and '" + control.Formato_Amd(txtFechaHasta.getDate()) + "')";
                fechas = "Entradas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();

            }
        }
        map.put("fechas", fechas);
        ResultSet result = control.DevolverRegistro(sql);
        try {
            while (result.next()) {

                dataSource.addDetalleEntradaProveedor(new DetalleEntradaProveedor(result.getString(1), result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getDouble(6), result.getDouble(7), result.getDouble(8)));

            }
            control.showReportDialog("Listado Detalle Por Proveedor", "reporteEntradaStockDetalleProveedor", map, dataSource);
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteEntradaStockCabecera.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel2 = new javax.swing.JLabel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Listado de Compras de Productos Detallados por Proveedor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Fecha: ");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Proveedor");

        txtProveedor.setEditable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        jButton1.setMnemonic('I');
        jButton1.setText("Imprimir");
        jButton1.setPreferredSize(new java.awt.Dimension(114, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        jButton4.setMnemonic('C');
        jButton4.setText("Cancelar");
        jButton4.setPreferredSize(new java.awt.Dimension(114, 33));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton3.setMnemonic('S');
        jButton3.setText("Salir");
        jButton3.setPreferredSize(new java.awt.Dimension(90, 33));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if (seleccionarProveedor == null) {
            seleccionarProveedor = new SeleccionarProveedorDialog(null, true);
        }
        seleccionarProveedor.setLocationRelativeTo(this);
        seleccionarProveedor.setVisible(true);
        String[] datos = seleccionarProveedor.getDatos();
        if (datos != null) {
            proveedor = datos[0];
            txtProveedor.setText(datos[2]);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtProveedor.setText("");
        proveedor = null;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //txtProveedor.setText("");
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
        txtProveedor.setText("");
        proveedor = null;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}