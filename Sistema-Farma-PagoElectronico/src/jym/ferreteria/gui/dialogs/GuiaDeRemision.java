package jym.ferreteria.gui.dialogs;

import com.mysql.jdbc.SQLError;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class GuiaDeRemision extends javax.swing.JDialog {

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
    String cantReal="";
    String cantidadInicial = "";
    String cantidadEntregada = "";
    String cantidadEntregadaRestore = "";
    String IDcomprobante = null;
    int filaRestore = 0;

    public GuiaDeRemision(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        List listData = control.getListData("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante=7 "
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;");

        if (listData.size() > 0) {
            String tipo = listData.get(0).toString();
            String[] generarComprobante = control.generarComprobante(tipo);
            IDcomprobante = generarComprobante[0];
            lblFactura.setText(generarComprobante[1]);
        } else {
            JOptionPane.showMessageDialog(parent, "Debe Inicializar la Numeración de la Guía de Remisión");
        }

        setIconImage(new ImageIcon(getClass().getResource(Controlador.ICON_PATH)).getImage());
        jdtFecha.setDate(new Date());
        txtPartida.setText("Huaraz");
        txtDomicilio.setText("Jr.Tumbes s/n");
        txtRazonSocial.setText("Multiservicios Jack");
        txtRuc.setText("12345678912");
        txtLLegada.requestFocus();

        model.setColumnIdentifiers(new String[]{"ID", "Descripción", "Unidad", "Cantidad Inicial", "Cantidad Entregada","CantReal"});
        tablaProductosInicial.setModel(model);
        control.hideTableColumn(tablaProductosInicial, 0,5);
        control.tableWidthColumn(tablaProductosInicial, 300, 1);
        modelo2.setColumnIdentifiers(new String[]{"ID", "Descripción", "Unidad", "Cantidad","CantReal"});
        tablaProductoEntregar.setModel(modelo2);
        control.hideTableColumn(tablaProductoEntregar, 0,4);
        control.tableWidthColumn(tablaProductoEntregar, 500, 1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jdtFecha = new com.toedter.calendar.JDateChooser();
        txtPartida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtLLegada = new javax.swing.JTextField();
        lblFactura = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jchDevolucion = new javax.swing.JCheckBox();
        jchCompra = new javax.swing.JCheckBox();
        jchTransformacion = new javax.swing.JCheckBox();
        jchConsignacion = new javax.swing.JCheckBox();
        jchVenta = new javax.swing.JCheckBox();
        jchTrasladoEstablecimiento = new javax.swing.JCheckBox();
        jchTrasladoEmisor = new javax.swing.JCheckBox();
        jchImportacion = new javax.swing.JCheckBox();
        jchExportacion = new javax.swing.JCheckBox();
        jchOtros = new javax.swing.JCheckBox();
        jchVentaTer = new javax.swing.JCheckBox();
        jchVentaConfir = new javax.swing.JCheckBox();
        jchRecojo = new javax.swing.JCheckBox();
        jchZona = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMarcaPlaca = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtnConstancia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtnLicencia = new javax.swing.JTextField();
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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Punto de Partida:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Punto de Llegada:");

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPartida)
                            .addComponent(txtLLegada))
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
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLLegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFactura.setForeground(new java.awt.Color(255, 0, 0));
        lblFactura.setText("N°Guia");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Cliente:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("DNI/RUC:");

        lblCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(255, 0, 0));

        lblDni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblDni.setForeground(new java.awt.Color(255, 0, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Motivo de Traslado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        jchDevolucion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchDevolucion.setText("Devolución");
        jchDevolucion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchDevolucion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchDevolucionActionPerformed(evt);
            }
        });

        jchCompra.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchCompra.setText("Compra");
        jchCompra.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchCompra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchCompraActionPerformed(evt);
            }
        });

        jchTransformacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchTransformacion.setText("Transformación");
        jchTransformacion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchTransformacion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchTransformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchTransformacionActionPerformed(evt);
            }
        });

        jchConsignacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchConsignacion.setText("Consignación");
        jchConsignacion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchConsignacion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchConsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchConsignacionActionPerformed(evt);
            }
        });

        jchVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchVenta.setText("Venta");
        jchVenta.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchVenta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchVentaActionPerformed(evt);
            }
        });

        jchTrasladoEstablecimiento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchTrasladoEstablecimiento.setText("Traslado entre establecimiento de una misma empresa   ");
        jchTrasladoEstablecimiento.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchTrasladoEstablecimiento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchTrasladoEstablecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchTrasladoEstablecimientoActionPerformed(evt);
            }
        });

        jchTrasladoEmisor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchTrasladoEmisor.setText("Traslado por emisor itinerante de comprobantes de pago");
        jchTrasladoEmisor.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchTrasladoEmisor.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchTrasladoEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchTrasladoEmisorActionPerformed(evt);
            }
        });

        jchImportacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchImportacion.setText("Importación");
        jchImportacion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchImportacion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchImportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchImportacionActionPerformed(evt);
            }
        });

        jchExportacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchExportacion.setText("Exportación");
        jchExportacion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchExportacion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchExportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchExportacionActionPerformed(evt);
            }
        });

        jchOtros.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchOtros.setText("Otros");
        jchOtros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchOtros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchOtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchOtrosActionPerformed(evt);
            }
        });

        jchVentaTer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchVentaTer.setText("Venta Entrega Terceros");
        jchVentaTer.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchVentaTer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchVentaTer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchVentaTerActionPerformed(evt);
            }
        });

        jchVentaConfir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchVentaConfir.setText("Venta Sujeta a Confirmación por el comprador");
        jchVentaConfir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchVentaConfir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchVentaConfir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchVentaConfirActionPerformed(evt);
            }
        });

        jchRecojo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchRecojo.setText("Recojo Bienes");
        jchRecojo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchRecojo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchRecojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchRecojoActionPerformed(evt);
            }
        });

        jchZona.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jchZona.setText("Zona Primaria");
        jchZona.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jchZona.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jchZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchZonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jchVentaConfir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jchDevolucion)
                            .addGap(47, 47, 47)
                            .addComponent(jchCompra)
                            .addGap(27, 27, 27)
                            .addComponent(jchTransformacion))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jchTrasladoEmisor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jchTrasladoEstablecimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jchVenta)
                    .addComponent(jchImportacion)
                    .addComponent(jchExportacion)
                    .addComponent(jchRecojo))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jchConsignacion))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchVentaTer))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchZona, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jchOtros, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jchCompra, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jchImportacion)
                        .addComponent(jchDevolucion)
                        .addComponent(jchTransformacion)
                        .addComponent(jchConsignacion)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchTrasladoEstablecimiento)
                            .addComponent(jchExportacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchTrasladoEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jchVentaConfir))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jchZona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jchVenta)
                            .addComponent(jchVentaTer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchOtros)
                            .addComponent(jchRecojo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del Transportista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Razón Soc.:");

        txtDomicilio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDomicilioKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("RUC:");

        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Marca y N° Placa");

        txtMarcaPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaPlacaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaPlacaKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("N° de Constancia de Inscripción:");

        txtnConstancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnConstanciaKeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Domicilio Fiscal:");

        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("N° de Licencia de Conduc:");

        txtnLicencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnLicenciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnLicenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnLicenciaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnLicencia))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtnConstancia, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtRuc))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMarcaPlaca))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDomicilio)))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMarcaPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnConstancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtnLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jSeparator1.setForeground(new java.awt.Color(255, 0, 0));
        jSeparator1.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel11)
                        .addGap(5, 5, 5)
                        .addComponent(lblProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addGap(7, 7, 7)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFactura)
                        .addComponent(jLabel7))
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jchCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchCompraActionPerformed

    private void jchDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchDevolucionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchDevolucionActionPerformed

    private void jchTransformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchTransformacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchTransformacionActionPerformed

    private void jchConsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchConsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchConsignacionActionPerformed

    private void jchVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchVentaActionPerformed

    private void jchTrasladoEstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchTrasladoEstablecimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchTrasladoEstablecimientoActionPerformed

    private void jchTrasladoEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchTrasladoEmisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchTrasladoEmisorActionPerformed

    private void jchExportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchExportacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchExportacionActionPerformed

    private void jchImportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchImportacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchImportacionActionPerformed

    private void jchOtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchOtrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchOtrosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaProductosInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosInicialMouseClicked
        if (evt.getClickCount() > 1) {
            lblProductoSeleccionado.setText(tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 1).toString());
            IDproducto = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 0).toString();
            producto = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 1).toString();
            unidad = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 2).toString();
            cantidadInicial = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 3).toString();
            cantidadEntregada = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 4).toString();
            cantidadEntregadaRestore = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 4).toString();
            cantReal = tablaProductosInicial.getValueAt(tablaProductosInicial.getSelectedRow(), 5).toString();
            filaRestore = tablaProductosInicial.getSelectedRow();
            //double cantidadActual = Double.parseDouble(cantidadInicial) - Double.parseDouble(cantidadEntregada);
            double cntin=Double.parseDouble(cantidadInicial)-Double.parseDouble(cantidadEntregada);
            txtCantidad.setText(Double.toString(cntin));
            txtCantidad.requestFocus();

        }
    }//GEN-LAST:event_tablaProductosInicialMouseClicked

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed

        cargarProducto();

    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Confirma que deseal eliminar el producto seleccionado?", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            quitarProducto();
        }

    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed

        control.LimTabla(modelo2);
        control.LimTabla(model);
        llenarTablaInicial(IDVenta);
        //tablaProductosInicial.setValueAt(cantidadEntregadaRestore, filaRestore, 4);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        lblProductoSeleccionado.setText("");
        txtCantidad.setText("");
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (evt.getKeyChar() == 10) {
            double cantIn=Double.parseDouble(txtCantidad.getText());
            double Res=cantIn*Double.parseDouble(cantReal);
            if((Res%1)==0 && Res>0){
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
            else{
            JOptionPane.showMessageDialog(null, "Cantidad Incorrecta!"+Res%1);
            }

        }

    }//GEN-LAST:event_txtCantidadKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        insertarGuiadeRemision();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnLicenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnLicenciaKeyTyped

    }//GEN-LAST:event_txtnLicenciaKeyTyped

    private void txtMarcaPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaPlacaKeyReleased
        control.Mayusculas(txtMarcaPlaca);
    }//GEN-LAST:event_txtMarcaPlacaKeyReleased

    private void txtnLicenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnLicenciaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnLicenciaKeyPressed

    private void txtnLicenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnLicenciaKeyReleased
        control.Mayusculas(txtnLicencia);
    }//GEN-LAST:event_txtnLicenciaKeyReleased

    private void txtRazonSocialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyPressed
        if (evt.getKeyChar() == 10) {
            if (txtRazonSocial.getText().trim().length() > 0) {
                txtMarcaPlaca.requestFocus();
            }

        }
    }//GEN-LAST:event_txtRazonSocialKeyPressed

    private void txtMarcaPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaPlacaKeyPressed
        if (evt.getKeyChar() == 10) {
            if (txtMarcaPlaca.getText().trim().length() > 0) {
                txtRuc.requestFocus();
            }

        }
    }//GEN-LAST:event_txtMarcaPlacaKeyPressed

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        if (evt.getKeyChar() == 10) {
            if (txtRuc.getText().trim().length() > 0) {
                txtDomicilio.requestFocus();
            }

        }
    }//GEN-LAST:event_txtRucKeyPressed

    private void txtDomicilioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioKeyPressed
        if (evt.getKeyChar() == 10) {
            if (txtDomicilio.getText().trim().length() > 0) {
                txtnConstancia.requestFocus();
            }

        }
    }//GEN-LAST:event_txtDomicilioKeyPressed

    private void txtnConstanciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnConstanciaKeyPressed
        if (evt.getKeyChar() == 10) {
            if (txtnConstancia.getText().trim().length() > 0) {
                txtnLicencia.requestFocus();
            }

        }
    }//GEN-LAST:event_txtnConstanciaKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cancelar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablaProductoEntregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoEntregarMouseClicked
        if (evt.getClickCount() == 1) {
            btnEliminarProducto.setEnabled(true);
            btnEditarProducto.setEnabled(true);
        }


    }//GEN-LAST:event_tablaProductoEntregarMouseClicked

    private void txtLLegadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLLegadaKeyReleased

        control.Mayusculas(txtLLegada);
    }//GEN-LAST:event_txtLLegadaKeyReleased

    private void jchVentaTerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchVentaTerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchVentaTerActionPerformed

    private void jchVentaConfirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchVentaConfirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchVentaConfirActionPerformed

    private void jchRecojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchRecojoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchRecojoActionPerformed

    private void jchZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchZonaActionPerformed

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
            java.util.logging.Logger.getLogger(GuiaDeRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiaDeRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiaDeRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiaDeRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GuiaDeRemision dialog = new GuiaDeRemision(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox jchCompra;
    private javax.swing.JCheckBox jchConsignacion;
    private javax.swing.JCheckBox jchDevolucion;
    private javax.swing.JCheckBox jchExportacion;
    private javax.swing.JCheckBox jchImportacion;
    private javax.swing.JCheckBox jchOtros;
    private javax.swing.JCheckBox jchRecojo;
    private javax.swing.JCheckBox jchTransformacion;
    private javax.swing.JCheckBox jchTrasladoEmisor;
    private javax.swing.JCheckBox jchTrasladoEstablecimiento;
    private javax.swing.JCheckBox jchVenta;
    private javax.swing.JCheckBox jchVentaConfir;
    private javax.swing.JCheckBox jchVentaTer;
    private javax.swing.JCheckBox jchZona;
    private com.toedter.calendar.JDateChooser jdtFecha;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblProductoSeleccionado;
    private javax.swing.JTable tablaProductoEntregar;
    private javax.swing.JTable tablaProductosInicial;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtLLegada;
    private javax.swing.JTextField txtMarcaPlaca;
    private javax.swing.JTextField txtPartida;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtnConstancia;
    private javax.swing.JTextField txtnLicencia;
    // End of variables declaration//GEN-END:variables

    public void setDatos(String idVenta, String cliente, String idCliente, String factura, String dniRuc) {
        IDVenta = idVenta;
        IDcliente = idCliente;
        Cliente = cliente;
        Factura = factura;
        DniRuc = dniRuc;
        lblCliente.setText(Cliente);
        lblDni.setText(DniRuc);
        //lblFactura.setText(Factura);
        llenarTablaInicial(IDVenta);

    }

    private void llenarTablaInicial(String IDVenta) {
        control.Sql = "SELECT p.`idProduto`,concat(t.`nomtip`,' ', m.`nommarc`,' ', p.`nomproducto`),d.`Unidad`, d.`cantidad`,\n"
                + "(select ifnull(sum(dg.cantidadProd),0) from guiaderemision g,detalleguiaremision dg where g.idguiaderemision=dg.idguiaderemision and g.idventa=v.idventa and d.iddetalleventa=dg.iddetalleventa) as Cantidad_EntregadaGuia\n"
                + ",(select ifnull(sum(dp.cantidadProd),0) from ordenpedido o,detalleordenpedido dp where o.idordenpedido=dp.idordenpedido and o.idventa=v.idventa and d.iddetalleventa=dp.iddetalleventa) as Cantidad_EntregadaOrden, d.cantReal\n"
                + "FROM produto p, marca m, tipoproducto t, detalleventa d,venta v\n"
                + "WHERE v.idventa=d.idventa and d.`idProducto`=p.`idProduto` AND t.`idTipoProducto`=p.`idTipoProducto` AND m.`idMarca`=p.`idMarca` and v.idventa='" + IDVenta + "' having (cantidad-Cantidad_EntregadaGuia)<>0 and (cantidad-Cantidad_EntregadaOrden)<>0;";

        control.LlenarJTabla(model, control.Sql, 6);

    }

    private void agregarDetalle() {
        modelo2.addRow(new String[]{IDproducto, producto, unidad, txtCantidad.getText(),cantReal});
        lblProductoSeleccionado.setText("");
        txtCantidad.setText("");
        producto = "";
        unidad = "";
        IDproducto = "";
        cantidadInicial = "";
    }

    private void insertarGuiadeRemision() {
        if (IDcomprobante != null) {
            if (txtLLegada.getText().trim().length() > 0) {
                if (tablaProductoEntregar.getRowCount() > 0) {
                    if (txtRazonSocial.getText().trim().length() > 0) {
                        if (txtMarcaPlaca.getText().trim().length() > 0) {
                            if (txtDomicilio.getText().trim().length() > 0) {
                                this.dispose();
                                control.Sql = String.format("INSERT INTO guiaderemision VALUES(NULL,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", control.Formato_Amd(jdtFecha.getDate()), txtPartida.getText(), txtLLegada.getText(), txtRazonSocial.getText(), txtRuc.getText(), txtDomicilio.getText(), txtMarcaPlaca.getText(), txtnConstancia.getText(), txtnLicencia.getText(), IDVenta, IDcomprobante);
                                int idGuiaRemision = control.executeAndGetId(control.Sql);
                                control.Sql = "UPDATE comprobante c SET c.`estado`='Emitido' WHERE c.`idComprobante`='" + IDcomprobante + "'";
                                control.ejecutar(control.Sql);
                                llenarDetalleGuiaRemision(idGuiaRemision);
                                llenarMotivo(idGuiaRemision);
                                ImprimirGuia(idGuiaRemision);
                                JOptionPane.showMessageDialog(rootPane, "Se Registró Correctamente la Guia de Remisión");
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Debe Ingresar el RUC del transportista");
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Debe Ingresar la Marca y N° de Placa del Vehículo");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Debe ingresar la Razon social del Transportista");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Debe Agregar un Producto");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe Ingresar el Punto de Llegada");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe Inicializar la Numeración de la Guia de Remisión");
        }
    }

    private void llenarDetalleGuiaRemision(int idGuiaRemision) {

        for (int i = 0; i < tablaProductoEntregar.getRowCount(); i++) {
            control.Sql = "SELECT idDetalleVenta,idalmacen FROM `detalleventa` where idVenta='" + IDVenta + "' and idProducto='" + tablaProductoEntregar.getValueAt(i, 0).toString() + "'";
            String IDdetalleVenta = control.DevolverRegistroDto(control.Sql, 1);
            String IDAlmacen = control.DevolverRegistroDto(control.Sql, 2);
            control.Sql = String.format("INSERT INTO detalleguiaremision VALUES(NULL,'%s','%s','%s');", IDdetalleVenta, idGuiaRemision, tablaProductoEntregar.getValueAt(i, 3).toString());
            control.ejecutar(control.Sql);
            control.Sql="select count(*) from entregarluego where idventa='"+IDVenta+"';";
            int ContarEntregarluego = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if(ContarEntregarluego == 1){
                control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",tablaProductoEntregar.getValueAt(i, 3).toString(),IDAlmacen,tablaProductoEntregar.getValueAt(i, 0).toString()));
            }
        }
    }

    private void llenarMotivo(int idguiaremi) {
        if (jchCompra.isSelected() == true) {
            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'2','%s');", idguiaremi);
            control.ejecutar(control.Sql);
        }
        if (jchConsignacion.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'4','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchDevolucion.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'1','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchExportacion.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'9','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchImportacion.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'8','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchOtros.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'10','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchTransformacion.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'3','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchTrasladoEmisor.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'7','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchTrasladoEstablecimiento.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'6','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        if (jchVenta.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'5','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        
        if (jchVentaTer.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'11','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        
        if (jchVentaConfir.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'12','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        
        if (jchRecojo.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'13','%s');", idguiaremi);
            control.ejecutar(control.Sql);

        }
        
        if (jchZona.isSelected() == true) {

            control.Sql = String.format("INSERT INTO motivo_has_guiaderemision VALUES(NULL,'14','%s');", idguiaremi);
            control.ejecutar(control.Sql);

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
            double cantTot = Double.parseDouble(txtCantidad.getText()) + Double.parseDouble(cantidadEntregada);
            int cntin=Integer.parseInt(txtCantidad.getText()) + Integer.parseInt(cantidadEntregada);
            tablaProductosInicial.setValueAt(cntin, tablaProductosInicial.getSelectedRow(), 4);
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
        jchCompra.setSelected(false);
        jchConsignacion.setSelected(false);
        jchDevolucion.setSelected(false);
        jchExportacion.setSelected(false);
        jchImportacion.setSelected(false);
        jchOtros.setSelected(false);
        jchTransformacion.setSelected(false);
        jchTrasladoEmisor.setSelected(false);
        jchTrasladoEstablecimiento.setSelected(false);
        jchVenta.setSelected(false);
        txtMarcaPlaca.setText("");
        txtnConstancia.setText("");
        txtnLicencia.setText("");
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);

    }

    private void quitarProducto() {
        for (int i = 0; i < tablaProductosInicial.getRowCount(); i++) {
            if (tablaProductoEntregar.getValueAt(tablaProductoEntregar.getSelectedRow(), 0).toString().equals(tablaProductosInicial.getValueAt(i, 0).toString())) {
                //double cantidadEntregada = Double.parseDouble(tablaProductosInicial.getValueAt(i, 4).toString()) - Double.parseDouble(tablaProductoEntregar.getValueAt(i, 3).toString());
                //cantidadEntregada=(int)cantidadEntregada;
                tablaProductosInicial.setValueAt(0, i, 4);
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

    private void ImprimirGuia(int idGuiaRemision) {
        try {
            String motivo = "", idVenta = "";
            control.Sql = "SELECT concat(idmotivo,',') FROM `motivo_has_guiaderemision` where idGuiaderemision='" + idGuiaRemision + "';";
            ResultSet DevolverRegistro = control.DevolverRegistro(control.Sql);
            while (DevolverRegistro.next()) {
                motivo += DevolverRegistro.getString(1);

            }
            control.Sql = "SELECT idventa from guiaderemision where idguiaderemision='" + idGuiaRemision + "';";
            idVenta = control.DevolverRegistroDto(control.Sql, 1);
            Map map = new HashMap();
            map.put("idVenta", idVenta);
            map.put("idGuia", idGuiaRemision);
            map.put("motivos", motivo);
            control.showReportDialog("Guia de Remision", "reporteGuiaDeRemision", map);
        } catch (SQLException ex) {
            Logger.getLogger(GuiaDeRemision.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
