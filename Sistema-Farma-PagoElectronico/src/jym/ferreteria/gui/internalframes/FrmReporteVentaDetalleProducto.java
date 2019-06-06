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
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.datasource.VentaDetalleProducto;
import jym.ferreteria.datasource.VentaDetalleProductoDataSource;
import jym.ferreteria.gui.dialogs.SeleccionarClienteDialog;
import jym.ferreteria.gui.dialogs.SeleccionarProductoRegularizacionDialog;

public class FrmReporteVentaDetalleProducto extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private String cliente;
    private SeleccionarClienteDialog seleccionarCliente;
    private String idProducto;
    private SeleccionarProductoRegularizacionDialog seleccionarProductoRegularizacion;
    private String idusu="";

    public FrmReporteVentaDetalleProducto() {
        initComponents();
        userspeshal();
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
    }
    
    public void userspeshal(){
    idusu=Accesos.getInstance().getIdUsuario();
    //System.out.println(idusu);
    
   // JOptionPane.showMessageDialog(null, idusu);
            

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboTipoComprobante = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Listado de ventas detallada");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Cliente: ");

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha: ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Comprobante: ");

        cboTipoComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Factura", "Boleta", "Ticket" }));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Producto: ");

        txtProducto.setEditable(false);
        txtProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jButton3.setPreferredSize(new java.awt.Dimension(92, 33));
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //txtProveedor.setText("");
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        seleccionarCliente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtCliente.setText("");
        cliente = null;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        seleccionarProducto();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        txtProducto.setText("");
        idProducto = null;
    }//GEN-LAST:event_jButton8ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboTipoComprobante;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCliente;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables

    
    private void cargarDatos() {
        Map map = new HashMap();
        VentaDetalleProductoDataSource dataSource = new VentaDetalleProductoDataSource();
        String extra = "";
        String fechas = "";
        String andtipo="";
        
        if(idusu.equals("0")){
            andtipo=" and t.idTipoComprobante<>8 ";
        }
        
        String sql = "SELECT date_format(v.fecha,'%d/%m/%Y') as fecha,CONCAT(t.nombre,' N° ', c.serie,' - ' ,c.`numero`) as comprobante,"
                + "concat(d.Unidad,' ',pre.present,' ',ca.Producto) as Descripcion,d.cantidad, REPLACE(format(d.precioVenta,2), ',', '') as precio, REPLACE(format(((d.cantidad*d.cantreal)*(d.precioventa-(d.precioventa*(d.descuento/100)))),2), ',', '') as total, ifnull(v.hora,'--') as hora, d.descuento "
                + "FROM detalleventa d inner join produto pro on pro.idproduto=d.idproducto inner join presentacion pre on pre.idpresentacion=pro.idpresentacion, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) "
                + "LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca "
                + "WHERE ca.id=d.idproducto AND d.idventa=v.idventa and v.estado='Pagado' "+andtipo+" ";

        if (cliente == null) {//Cliente vacío
            if (idProducto == null) {//Producto vacío
                if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//fecha vacía,
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente vacío, Producto vacío, fecha vacía, todos los comprobantes
                        extra = " ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente vacío, Producto vacío, fecha vacía, comprobante específico
                        extra = " AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                } else {//con intervalo de fechas
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente vacío, Producto vacío, con intervalo de fechas, todos los comprobantes
                        extra = " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente vacío, Producto vacío, con intervalo de fechas, comprobante específico
                        extra = " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                    fechas = "Ventas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
                }
            } else {//Con producto específico
                if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//fecha vacía
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente vacío, Con producto específico, fecha vacía, todos los comprobantes
                        extra = " AND d.`idProducto`=" + idProducto + " ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente vacío, Con producto específico, fecha vacía, comprobante específico
                        extra = " AND d.`idProducto`=" + idProducto + " AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                } else {//con intervalo de fechas
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente vacío, Con producto específico, con intervalo de fechas, todos los comprobantes
                        extra = " AND d.`idProducto`=" + idProducto + " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente vacío, Con producto específico, con intervalo de fechas, comprobante específico
                        extra = " AND d.`idProducto`=" + idProducto + " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                    fechas = "Ventas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
                }
            }

        } else {//Cliente seleccionado
            if (idProducto == null) {//Con producto vacío
                if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//fecha vacía
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente seleccionado, Con producto vacío, fecha vacía, todos los comprobantes
                        extra = " AND v.`idCliente`=" + cliente + " ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente seleccionado, Con producto vacío, fecha vacía, comprobante específico
                        extra = " AND v.`idCliente`=" + cliente + " AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                } else {//con intervalo de fechas
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente seleccionado, Con producto vacío, con intervalo de fechas, todos los comprobantes
                        extra = " AND v.`idCliente`=" + cliente + " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente seleccionado, Con producto vacío, con intervalo de fechas, comprobante específico
                        extra = " AND v.`idCliente`=" + cliente + " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                    fechas = "Ventas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
                }
            } else {//Con producto específico
                if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//fecha vacía
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente seleccionado, Con producto vacío, fecha vacía, todos los comprobantes
                        extra = " AND d.`idProducto`=" + idProducto + " AND v.`idCliente`=" + cliente + " ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente seleccionado, Con producto vacío, fecha vacía, comprobante específico
                        extra = " AND d.`idProducto`=" + idProducto + " AND v.`idCliente`=" + cliente + " AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                } else {//con intervalo de fechas
                    if (cboTipoComprobante.getSelectedIndex() == 0) {//Todos los comprobantes
                        //Cliente seleccionado, Con producto vacío, con intervalo de fechas, todos los comprobantes
                        extra = " AND d.`idProducto`=" + idProducto + " AND v.`idCliente`=" + cliente + " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY comprobante, descripcion;";
                    } else {//Con comprobante específico
                        //Cliente seleccionado, Con producto vacío, con intervalo de fechas, comprobante específico
                        extra = " AND d.`idProducto`=" + idProducto + " AND v.`idCliente`=" + cliente + " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') AND t.idtipocomprobante=" + cboTipoComprobante.getSelectedIndex() + " ORDER BY comprobante, descripcion;";
                    }
                    fechas = "Ventas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
                }
            }

        }
        
        map.put("cliente", cliente == null ? "" : "Cliente: " + txtCliente.getText());
        map.put("producto", idProducto == null ? "" : "Producto: " + txtProducto.getText());
        if(idProducto!=null){

        System.out.println(String.format("SELECT sum(cantidadDisponible) FROM stock where idproducto='%s';",idProducto));
        String  stocfin = control.DevolverRegistroDto(String.format("SELECT sum(cantidadDisponible) FROM stock where idproducto='%s';",idProducto), 1);
        String ccntven="";
            if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {
                        System.out.println(String.format("SELECT  IFNULL(sum(d.cantidad),'0') " +
                        " FROM detalleventa d, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) " +
                        " LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca " +
                        " WHERE ca.id=d.idproducto AND d.idventa=v.idventa  and d.idproducto='%s' group by d.idproducto;",idProducto));
            ccntven = control.DevolverRegistroDto(String.format("SELECT  IFNULL(sum(d.cantidad),'0') " +
                        " FROM detalleventa d, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) " +
                        " LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca " +
                        " WHERE ca.id=d.idproducto AND d.idventa=v.idventa v.estado<>'Anulado' and d.idproducto='%s' group by d.idproducto;",idProducto),1);
//            int ccntven = Integer.parseInt(String.format("SELECT  IFNULL(sum(d.cantidad),'0') " +
//                        " FROM detalleventa d, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) " +
//                        " LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca " +
//                        " WHERE ca.id=d.idproducto AND d.idventa=v.idventa and v.fecha between curdate() and curdate() and d.idproducto='%s';",idProducto),1);
            }
            else{
            
                        System.out.println(String.format("SELECT  IFNULL(sum(d.cantidad),'0') " +
                        " FROM detalleventa d, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) " +
                        " LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca " +
                        " WHERE ca.id=d.idproducto AND d.idventa=v.idventa and v.fecha between '"+control.Formato_Amd(txtFechaDesde.getDate())+"' and '"+control.Formato_Amd(txtFechaHasta.getDate())+"' and d.idproducto='%s' group by d.idproducto;",idProducto));
            ccntven = control.DevolverRegistroDto(String.format("SELECT  IFNULL(sum(d.cantidad),'0') " +
                        " FROM detalleventa d, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) " +
                        " LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca " +
                        " WHERE ca.id=d.idproducto AND d.idventa=v.idventa and v.estado<>'Anulado' and v.fecha between '"+control.Formato_Amd(txtFechaDesde.getDate())+"' and '"+control.Formato_Amd(txtFechaHasta.getDate())+"' and d.idproducto='%s' group by d.idproducto;",idProducto),1);
//            int ccntven = Integer.parseInt(String.format("SELECT  IFNULL(sum(d.cantidad),'0') " +
//                        " FROM detalleventa d, (venta v LEFT JOIN comprobante c ON v.idComprobante=c.idComprobante) " +
//                        " LEFT JOIN tipocomprobante t on c.`idTipoComprobante`=t.`idTipoComprobante`, vta_catalogo ca " +
//                        " WHERE ca.id=d.idproducto AND d.idventa=v.idventa and v.fecha between curdate() and curdate() and d.idproducto='%s';",idProducto),1);
            }

            
//            int  stocfin = Integer.parseInt(control.DevolverRegistroDto(String.format("SELECT sum(cantidadDisponible) FROM stock where idproducto='%s';",idProducto), 1));


            
            
            //double sumn= Double.parseDouble(stocfin)+Double.parseDouble(ccntven);
            if(stocfin.length()==0){
                stocfin="0";
            }
            if(ccntven.length()==0){
                ccntven="0";
            }
            int sumn= (int)Double.parseDouble(stocfin)+(int)Double.parseDouble(ccntven);
            System.out.println(stocfin+"--"+ccntven);
            map.put("stock", "Stock Inicial : "+sumn+"        Vendidos : "+ccntven+"       En Stock : "+(int)Double.parseDouble(stocfin)+" ");
        }else{
            map.put("stock", "");
        }
        
        map.put("fechas", fechas);
        map.put("tipoComprobante", "Comprobante: " + cboTipoComprobante.getSelectedItem().toString());
        ResultSet result = control.DevolverRegistro(sql + extra);
        try {
            while (result.next()) {

                dataSource.addDetalleVentaProducto(new VentaDetalleProducto(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getDouble(6), result.getString(7),result.getDouble(8)));
            }
            control.showReportDialog("Listado detallado de ventas", "reporteVentasDetalle", map, dataSource);
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteVentaDetalleProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccionarCliente() {
        if (seleccionarCliente == null) {
            seleccionarCliente = new SeleccionarClienteDialog(null, true);
        }
        seleccionarCliente.setLocationRelativeTo(null);
        seleccionarCliente.setVisible(true);
        String[] datos = seleccionarCliente.getDatos();
        if (datos != null) {
            cliente = datos[0];
            txtCliente.setText(datos[2]);
        }
    }

    private void seleccionarProducto() {
        if (seleccionarProductoRegularizacion == null) {
            seleccionarProductoRegularizacion = new SeleccionarProductoRegularizacionDialog(null, true);
        }
        seleccionarProductoRegularizacion.setProductosRegularizacion("", "", "", "Entrada");
        seleccionarProductoRegularizacion.setLocationRelativeTo(null);
        seleccionarProductoRegularizacion.setVisible(true);
        String[] datos = seleccionarProductoRegularizacion.getDatos();
        if (datos != null) {
            idProducto = datos[0];
            txtProducto.setText(datos[1]);
        }
    }
}
