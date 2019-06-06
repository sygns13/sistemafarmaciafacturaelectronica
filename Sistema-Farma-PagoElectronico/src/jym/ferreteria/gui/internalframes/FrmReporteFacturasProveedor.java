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
import jym.ferreteria.datasource.FacturaProveedor;
import jym.ferreteria.datasource.FacturaProveedorDataSource;
import jym.ferreteria.gui.dialogs.SeleccionarProveedorDialog;

public class FrmReporteFacturasProveedor extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private SeleccionarProveedorDialog seleccionarProveedor = null;

    private String proveedor;

    public FrmReporteFacturasProveedor() {
        initComponents();
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Listado de facturas de proveedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Proveedor:");

        txtProveedor.setEditable(false);
        txtProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Estado: ");

        cboEstado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Pendiente Pagar", "Pagadas" }));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton5))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton6)))
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        jButton1.setMnemonic('I');
        jButton1.setText("Imprimir");
        jButton1.setPreferredSize(new java.awt.Dimension(116, 33));
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
        jButton4.setPreferredSize(new java.awt.Dimension(116, 33));
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        txtProveedor.setText("");
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
        proveedor = null;
        try {
            cboEstado.setSelectedIndex(0);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        seleccionarProveedor();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtProveedor.setText("");
        proveedor = null;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos() {
        Map map = new HashMap();

        FacturaProveedorDataSource dataSource = new FacturaProveedorDataSource();

        String sql = "SELECT date_format(f.`fecha`,'%d/%m/%Y'), t.`nombre`, CONCAT(f.`serie`,' - ', f.`numero`) numeroComprobante, p.`Nombre`, \n" +
"                REPLACE(FORMAT(f.total, 2), ',', '') AS importe, \n" +
"                IFNULL((SELECT REPLACE(FORMAT(SUM(pp.`montoReal`), 2), ',', '') FROM pagoproveedor pp WHERE pp.`idFacturaProveedor`=f.`idFacturaProveedor`),0.00) AS importePagado, \n" +
"                IF(f.`enSoles`,'S/ ','$ ') \n" +
"                FROM facturaproveedor f, proveedor p, tipocomprobante t \n" +
"                WHERE f.`idProveedor`=p.`idProveedor` AND f.`idTipoComprobante`=t.`idTipoComprobante`";
        String extra = "";
        String fechas = "";
        if (proveedor == null) {//Sin proveedor

            if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//sin fecha
                if (cboEstado.getSelectedIndex() == 0) {
                    extra = " ORDER BY f.`fecha`,p.`Nombre`;";
                } else {
                    String estado = "1";
                    if (cboEstado.getSelectedIndex() == 1) {
                        estado = "0";
                    }
                    extra = " AND f.pagado='" + estado + "' ORDER BY f.`fecha`,p.`Nombre`;";
                }
            } else {//con fecha
                if (cboEstado.getSelectedIndex() == 0) {
                    extra = " AND (f.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY f.`fecha`,p.`Nombre`;";
                } else {
                    String pagado = "1";
                    if (cboEstado.getSelectedIndex() == 1) {
                        pagado = "0";
                    }
                    extra = " AND f.pagado='" + pagado + "' AND (f.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY f.`fecha`,p.`Nombre`;";
                }
                fechas = "Entradas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
            }
        } else {//Con proveedor
            if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//sin fecha
                if (cboEstado.getSelectedIndex() == 0) {
                    extra = " AND f.idproveedor='" + proveedor + "' ORDER BY f.`fecha`,p.`Nombre`;";
                } else {
                    String pagado = "1";
                    if (cboEstado.getSelectedIndex() == 1) {
                        pagado = "0";
                    }
                    extra = "AND f.idproveedor='" + proveedor + "' AND f.pagado=" + pagado + " ORDER BY f.`fecha`,p.`Nombre`;";
                }

            } else {//con fecha
                if (cboEstado.getSelectedIndex() == 0) {
                    extra = " AND f.idproveedor='" + proveedor + "' AND (f.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY f.`fecha`,p.`Nombre`;";
                } else {
                    String pagado = "1";
                    if (cboEstado.getSelectedIndex() == 1) {
                        pagado = "0";
                    }
                    extra = " AND f.idproveedor='" + proveedor + "' AND f.pagado='" + pagado + "' AND (f.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ORDER BY f.`fecha`,p.`Nombre`;";
                }
                fechas = "Entradas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
            }
        }

        map.put("proveedor", proveedor == null ? "" : "Proveedor: " + txtProveedor.getText());
        map.put("fechas", fechas);
        map.put("estado", "Estado: " + cboEstado.getSelectedItem().toString());

        ResultSet result = control.DevolverRegistro(sql + extra);
        try {
            while (result.next()) {
                dataSource.addVentaFacturaProveedor(new FacturaProveedor(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getDouble(5), result.getDouble(6), result.getDouble(5) - result.getDouble(6), result.getString(7)));
            }
            control.showReportDialog("Listado de cuentas a pagar", "reporteFacturasProveedor", map, dataSource);
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteFacturasProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccionarProveedor() {
        if (seleccionarProveedor == null) {
            seleccionarProveedor = new SeleccionarProveedorDialog(null, true);
        }
        seleccionarProveedor.setLocationRelativeTo(null);
        seleccionarProveedor.setVisible(true);
        String[] datos = seleccionarProveedor.getDatos();
        if (datos != null) {
            proveedor = datos[0];
            txtProveedor.setText(datos[2]);
        }

    }
}
