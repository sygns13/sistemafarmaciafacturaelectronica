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
import jym.ferreteria.datasource.CuentaPorCobrar;
import jym.ferreteria.datasource.CuentaPorCobrarDataSource;
import jym.ferreteria.gui.dialogs.SeleccionarClienteDialog;

public class FrmReporteCuentasPorCobrar extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private SeleccionarClienteDialog seleccionarClienteDialog = null;

    private String cliente;
    
    private String idusu="";

    public FrmReporteCuentasPorCobrar() {
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
        txtProveedor = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnCancelarCliente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox();
        btnCancelarFechas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Listado de cuentas por cobrar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Cliente:");

        txtProveedor.setEditable(false);
        txtProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnCancelarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        btnCancelarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClienteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha: ");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Estado: ");

        cboEstado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Pendiente por cobrar", "Cobradas" }));

        btnCancelarFechas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        btnCancelarFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFechasActionPerformed(evt);
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
                            .addComponent(btnBuscarCliente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelarCliente))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelarFechas)))
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
                            .addComponent(btnBuscarCliente)
                            .addComponent(btnCancelarCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnCancelarFechas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnImprimir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimir.setMnemonic('I');
        btnImprimir.setText("Imprimir");
        btnImprimir.setPreferredSize(new java.awt.Dimension(116, 33));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel2.add(btnImprimir);

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
        jPanel2.add(btnCancelar);

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
        jPanel2.add(btnSalir);

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

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        cargarDatos();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtProveedor.setText("");
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
        cliente = null;
        try {
            cboEstado.setSelectedIndex(0);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        seleccionarCliente();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClienteActionPerformed
        txtProveedor.setText("");
        cliente = null;
    }//GEN-LAST:event_btnCancelarClienteActionPerformed

    private void btnCancelarFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFechasActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_btnCancelarFechasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarCliente;
    private javax.swing.JButton btnCancelarFechas;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboEstado;
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

        CuentaPorCobrarDataSource dataSource = new CuentaPorCobrarDataSource();
        
        String andtipo="";
        
        if(idusu.equals("0")){
            andtipo=" and t.idTipoComprobante<>8 ";
        }

        String sql = "SELECT date_format(v.`fecha`,'%d/%m/%Y'), t.`nombre`, CONCAT(c.`serie`,' ' ,c.`numero`) AS numeroComprobante, cl.`nomcte`, "
                + "     (v.`subTotalInafecto`+ v.`igv`) AS importe, "
                + "     @pagado:=(SELECT IFNULL(SUM(c.`importe`),0.00) FROM cobroventa c WHERE c.`idVenta`=v.`idVenta`) As importePagado, "
                + "     round((v.`subTotalInafecto`+ v.`igv`) - @pagado,2) AS importePendiente, ifnull(v.hora,'--') as hora "
                + "     FROM venta v, comprobante c, tipocomprobante t, cliente cl "
                + "     WHERE cl.`idCliente`=v.`idCliente` and v.estado<>'Anulado' "+andtipo+"  "
                + "     AND v.`idComprobante`=c.`idComprobante` "
                + "     AND c.`idTipoComprobante`=t.`idTipoComprobante` ";
        String extra = "";
        String fechas = "";

        if (txtFechaDesde.getDate() != null && txtFechaHasta.getDate() != null) {//Con fechas
            extra = " AND (v.`fecha` BETWEEN '" + control.Formato_Amd(txtFechaDesde.getDate()) + "' AND '" + control.Formato_Amd(txtFechaHasta.getDate()) + "') ";
            fechas = "Cuentas del: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " al " + ((JTextField) txtFechaHasta.getDateEditor()).getText();

        }
        if (cboEstado.getSelectedIndex() == 1) {//Pendientes de cobrar
            extra += " AND v.`pagado`=FALSE ";
        } else if (cboEstado.getSelectedIndex() == 2) {//Cobrados
            extra += " AND v.`pagado`=TRUE ";
        }
        if (cliente != null) {
            extra += " AND v.`idCliente`='" + cliente + "' ";
        }
        map.put("cliente", cliente == null ? "" : "Cliente: " + txtProveedor.getText());
        map.put("fechas", fechas);
        map.put("estado", "Estado: " + cboEstado.getSelectedItem().toString());

        ResultSet result = control.DevolverRegistro(sql + extra + " ORDER BY v.`fecha`;");
        try {
            while (result.next()) {
                dataSource.addCuentaPorCobrar(new CuentaPorCobrar(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getDouble(5), result.getDouble(6), result.getDouble(7),result.getString(8)));
            }
            control.showReportDialog("Listado de cuentas por cobrar", "reporteCuentasPorCobrar", map, dataSource);
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteCuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccionarCliente() {
        if (seleccionarClienteDialog == null) {
            seleccionarClienteDialog = new SeleccionarClienteDialog(null, true);
        }
        seleccionarClienteDialog.setLocationRelativeTo(null);
        seleccionarClienteDialog.setVisible(true);
        String[] datos = seleccionarClienteDialog.getDatos();
        if (datos != null) {
            cliente = datos[0];
            txtProveedor.setText(datos[2]);

        }

    }
    /*


     SELECT v.`fecha`, t.`nombre`, CONCAT(c.`serie`,' ' ,c.`numero`) AS numeroComprobante, cl.`nomcte`,
     (v.`subTotalInafecto`+ v.`subTotalAfecto`) AS importe,
     @pagado:=(SELECT IFNULL(SUM(c.`importe`),0.00) FROM cobroventa c WHERE c.`idVenta`=v.`idVenta`) As importePagado,
     ((v.`subTotalInafecto`+ v.`subTotalAfecto`) - @pagado) AS importePendiente
     FROM venta v, comprobante c, tipocomprobante t, cliente cl
     WHERE cl.`idCliente`=v.`idCliente`
     AND v.`idComprobante`=c.`idComprobante`
     AND c.`idTipoComprobante`=t.`idTipoComprobante`;
     */
}
