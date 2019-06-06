package jym.ferreteria.gui.internalframes;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jym.ferreteria.clases.Configuracion;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class FrmRegistrarPagoFacturaProveedor extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private static Controlador control = new Controlador();
    private String Ruc = "", CodProv = "", idBancoEmisor = "";
    private String idCuentaProv;
    private String idFacturaProveedor;
    public static boolean cboBancoProveedorCargado = false;
    private String idPagoProveedor = null;
    private static Map mapcuenta = new HashMap();
    private DecimalFormat format = new DecimalFormat("0.00");
    private boolean pagado = false;
    private final FrmListadoFacturas listadoFacturas;
    private String idBancoProveedor;
    private double montoEditando;
    private double totalSoles = 0;
    private boolean enSoles;

    public FrmRegistrarPagoFacturaProveedor(FrmListadoFacturas listadoFacturas) {
        initComponents();
        this.listadoFacturas = listadoFacturas;
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        modelo.setColumnIdentifiers(new String[]{"IdPagoProveedor", "Fecha Pago", "Tipo de Pago", "Banco Emisor", "Banco Proveedor","N° Operación/Cheque", "N° Cuenta", "Monto Pago", "Monto Real"});
        tPagosRealizados.setModel(modelo);
        control.hideTableColumn(tPagosRealizados, 0);
        control.tableMaxWidthColumn(tPagosRealizados, 80, 1);
        control.tableMaxWidthColumn(tPagosRealizados, 100, 2);
        control.tableMaxWidthColumn(tPagosRealizados, 100, 7, 8);

        lblBancoEmisor.setVisible(false);
        lblBancoProveedor.setVisible(false);
        lblCheque.setVisible(false);
        lblCuenta.setVisible(false);
        cboBancoEmisor.setVisible(false);
        cboBancoProveedor.setVisible(false);
        txtNumcheque.setVisible(false);
        cboCuenta.setVisible(false);
        lblOpe.setVisible(false);
        txtNumOp.setVisible(false);

        control.LlenarCombo(cboBancoEmisor, "SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", 2);
        control.LlenarCombo(cboBancoProveedor, "SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", 2);
        cboBancoProveedorCargado = true;
        jDateChooser1.setDate(new Date());
        tPagosRealizados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnEliminar.setEnabled(true);
            }
        });
        pack();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbMonto = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbProveedor = new javax.swing.JLabel();
        lbNumero = new javax.swing.JLabel();
        lbRuc = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboTipopago = new javax.swing.JComboBox();
        lblBancoEmisor = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        cboBancoEmisor = new javax.swing.JComboBox();
        lblCheque = new javax.swing.JLabel();
        txtNumcheque = new javax.swing.JTextField();
        lblCuenta = new javax.swing.JLabel();
        cboCuenta = new javax.swing.JComboBox();
        lblBancoProveedor = new javax.swing.JLabel();
        cboBancoProveedor = new javax.swing.JComboBox();
        cboMoneda = new javax.swing.JComboBox();
        lblOpe = new javax.swing.JLabel();
        txtNumOp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtImportePagadoSoles = new javax.swing.JTextField();
        txtImportePendienteSoles = new javax.swing.JTextField();
        bPagar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tPagosRealizados = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pago de Factura de Proveedor");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Generales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(51, 0, 153))); // NOI18N

        lbMonto.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbMonto.setForeground(new java.awt.Color(0, 51, 102));
        lbMonto.setText(" ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Proveedor:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Serie - Número:");

        lbProveedor.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbProveedor.setForeground(new java.awt.Color(0, 51, 102));
        lbProveedor.setText(" ");

        lbNumero.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbNumero.setForeground(new java.awt.Color(0, 51, 102));
        lbNumero.setText(" ");

        lbRuc.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbRuc.setForeground(new java.awt.Color(0, 51, 102));
        lbRuc.setText(" ");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("RUC:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Monto:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 255, 0))); // NOI18N

        jDateChooser1.setDateFormatString("dd/M/yyyy");
        jDateChooser1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Tipo de pago:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Fecha:");

        cboTipopago.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cboTipopago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Cheque", "Cuenta Bancaria" }));
        cboTipopago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipopagoActionPerformed(evt);
            }
        });
        cboTipopago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTipopagoKeyPressed(evt);
            }
        });

        lblBancoEmisor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblBancoEmisor.setText("Banco emisor:");

        lblMonto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblMonto.setText("Monto:");

        txtMonto.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtMonto.setText("0.00");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        cboBancoEmisor.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cboBancoEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBancoEmisorActionPerformed(evt);
            }
        });
        cboBancoEmisor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboBancoEmisorKeyPressed(evt);
            }
        });

        lblCheque.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCheque.setText("Numero Cheque:");

        txtNumcheque.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtNumcheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumchequeKeyPressed(evt);
            }
        });

        lblCuenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCuenta.setText("Cuentas:");

        cboCuenta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cboCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboCuentaKeyPressed(evt);
            }
        });

        lblBancoProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblBancoProveedor.setText("Banco proveedor:");

        cboBancoProveedor.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cboBancoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBancoProveedorActionPerformed(evt);
            }
        });
        cboBancoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboBancoProveedorKeyPressed(evt);
            }
        });

        cboMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "S/", "$" }));
        cboMoneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboMonedaKeyPressed(evt);
            }
        });

        lblOpe.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblOpe.setText("Numero de Op:");

        txtNumOp.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtNumOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumOpKeyPressed(evt);
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
                        .addComponent(lblOpe, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumOp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBancoEmisor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboBancoEmisor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumcheque)
                            .addComponent(cboCuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMonto)
                            .addComponent(cboTipopago, 0, 299, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBancoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboBancoProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTipopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboBancoEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBancoEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBancoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBancoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumcheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOpe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Importe pagado:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Importe pendiente:");

        txtImportePagadoSoles.setEditable(false);
        txtImportePagadoSoles.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtImportePagadoSoles.setForeground(new java.awt.Color(0, 51, 102));

        txtImportePendienteSoles.setEditable(false);
        txtImportePendienteSoles.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtImportePendienteSoles.setForeground(new java.awt.Color(0, 51, 102));

        bPagar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pagar.png"))); // NOI18N
        bPagar.setMnemonic('G');
        bPagar.setText("Guardar pago");
        bPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPagarActionPerformed(evt);
            }
        });

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('C');
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('S');
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImportePagadoSoles, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(txtImportePendienteSoles)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(bCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtImportePagadoSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtImportePendienteSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                            .addComponent(lbNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(lbRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNumero)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbProveedor)
                            .addComponent(lbRuc)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMonto)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 110));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N

        tPagosRealizados.setModel(new javax.swing.table.DefaultTableModel(
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
        tPagosRealizados.getTableHeader().setReorderingAllowed(false);
        tPagosRealizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tPagosRealizadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tPagosRealizados);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTipopagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipopagoActionPerformed

        if (cboTipopago.getSelectedIndex() == 0) {
            limpiar();
            lblBancoEmisor.setVisible(false);
            lblBancoProveedor.setVisible(false);
            lblCheque.setVisible(false);
            lblCuenta.setVisible(false);
            cboBancoEmisor.setVisible(false);
            cboBancoProveedor.setVisible(false);
            txtNumcheque.setVisible(false);
            cboCuenta.setVisible(false);
            lblOpe.setVisible(false);
            txtNumOp.setVisible(false);
            pack();
        } else if (cboTipopago.getSelectedIndex() == 1) {
            limpiar();
            lblBancoEmisor.setVisible(true);
            lblBancoProveedor.setVisible(false);
            lblCheque.setVisible(true);
            lblCuenta.setVisible(false);
            cboBancoEmisor.setVisible(true);
            cboBancoProveedor.setVisible(false);
            txtNumcheque.setVisible(true);
            cboCuenta.setVisible(false);
            lblOpe.setVisible(false);
            txtNumOp.setVisible(false);
            pack();
        } else {
            limpiar();
            lblBancoEmisor.setVisible(false);
            lblBancoProveedor.setVisible(true);
            lblCheque.setVisible(false);
            lblCuenta.setVisible(true);
            cboBancoEmisor.setVisible(false);
            cboBancoProveedor.setVisible(true);
            txtNumcheque.setVisible(false);
            cboCuenta.setVisible(true);
            lblOpe.setVisible(true);
            txtNumOp.setVisible(true);
            pack();
        }
    }//GEN-LAST:event_cboTipopagoActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    private void bPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPagarActionPerformed
        if (!pagado || idPagoProveedor != null) {
            registrarpago();

        } else {
            JOptionPane.showMessageDialog(this, "La factura ya está completamente pagada, ya no es posible realizar más pagos.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bPagarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.cancelar();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        control.decimal(evt, txtMonto.getText());
    }//GEN-LAST:event_txtMontoKeyTyped

    private void tPagosRealizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPagosRealizadosMouseClicked
        if (evt.getClickCount() > 1) {
            cargarDatosModificar();
        }
    }//GEN-LAST:event_tPagosRealizadosMouseClicked

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        control.passFocus(evt, cboCuenta);
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        control.passFocus(evt, cboMoneda);
    }//GEN-LAST:event_txtMontoKeyPressed

    private void cboBancoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBancoProveedorActionPerformed
        recargarCuentas();
    }//GEN-LAST:event_cboBancoProveedorActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarPago();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cboTipopagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTipopagoKeyPressed
        if (control.isEnterKey(evt)) {
            if (cboBancoEmisor.isVisible()) {
                cboBancoEmisor.requestFocus();
            } else if (cboBancoProveedor.isVisible()) {
                cboBancoProveedor.requestFocus();
            } else if (txtMonto.isVisible()) {
                txtMonto.requestFocus();
            }
        }
    }//GEN-LAST:event_cboTipopagoKeyPressed

    private void cboBancoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboBancoProveedorKeyPressed
        if (control.isEnterKey(evt)) {
            cboCuenta.requestFocus();
        }
    }//GEN-LAST:event_cboBancoProveedorKeyPressed

    private void txtNumchequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumchequeKeyPressed
        if (control.isEnterKey(evt)) {
            txtMonto.requestFocus();
        }
    }//GEN-LAST:event_txtNumchequeKeyPressed

    private void cboCuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboCuentaKeyPressed
        if (control.isEnterKey(evt)) {
            txtMonto.requestFocus();
        }
    }//GEN-LAST:event_cboCuentaKeyPressed

    private void cboMonedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMonedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bPagar.doClick();
        }
    }//GEN-LAST:event_cboMonedaKeyPressed

    private void cboBancoEmisorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboBancoEmisorKeyPressed
        if (control.isEnterKey(evt)) {
            if (cboBancoProveedor.isVisible()) {
                cboBancoProveedor.requestFocus();
            } else if (txtNumcheque.isVisible()) {
                txtNumcheque.requestFocus();
            }
        }
    }//GEN-LAST:event_cboBancoEmisorKeyPressed

    private void cboBancoEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBancoEmisorActionPerformed

    }//GEN-LAST:event_cboBancoEmisorActionPerformed

    private void txtNumOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumOpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumOpKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bPagar;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton btnEliminar;
    public static javax.swing.JComboBox cboBancoEmisor;
    public static javax.swing.JComboBox cboBancoProveedor;
    public static javax.swing.JComboBox cboCuenta;
    private javax.swing.JComboBox cboMoneda;
    private javax.swing.JComboBox cboTipopago;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMonto;
    private javax.swing.JLabel lbNumero;
    private javax.swing.JLabel lbProveedor;
    private static javax.swing.JLabel lbRuc;
    private javax.swing.JLabel lblBancoEmisor;
    private javax.swing.JLabel lblBancoProveedor;
    private javax.swing.JLabel lblCheque;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblOpe;
    private javax.swing.JTable tPagosRealizados;
    private javax.swing.JTextField txtImportePagadoSoles;
    private javax.swing.JTextField txtImportePendienteSoles;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumOp;
    private javax.swing.JTextField txtNumcheque;
    // End of variables declaration//GEN-END:variables

    public void setDatosFacturaProveedor(String idFacturaProveedor, String nombreProveedor, String rucProveedor, String serieNumero, String importe) {
        this.idFacturaProveedor = idFacturaProveedor;
        lbProveedor.setText(nombreProveedor);
        lbRuc.setText(rucProveedor);
        lbNumero.setText(serieNumero);
        lbMonto.setText(importe);

        String ejecutarMsg[] = control.cargarRegistro(String.format("SELECT `pagado`, `total`,`enSoles` FROM `facturaproveedor` WHERE `idFacturaProveedor`=%s;", idFacturaProveedor), 3);

        pagado = ejecutarMsg[0].equals("1");
        totalSoles = Double.parseDouble(ejecutarMsg[1]);
        enSoles = ejecutarMsg[2].equals("1");

        mostrarpagos();
    }

    private void limpiar() {
        txtMonto.setText("0.00");
        txtNumcheque.setText("");
        cboCuenta.setSelectedIndex(-1);
    }

    private void cancelar() {
        txtMonto.setText("");
        txtNumcheque.setText("");
        cboCuenta.setSelectedIndex(-1);
        cboBancoEmisor.setSelectedIndex(-1);
        idPagoProveedor = null;
        tPagosRealizados.setEnabled(true);
        btnEliminar.setEnabled(tPagosRealizados.getSelectedRow() >= 0);
        montoEditando = 0;

    }

    private void registrarpago() {

        double tasaDeCambio = Configuracion.getInstance().getTasaDeCambio();

        //validamos que el formato de la fecha sea el correcto
        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "el formato de la fecha es incorrecto", "Mensaje", JOptionPane.WARNING_MESSAGE);
            ((JTextField) jDateChooser1.getDateEditor()).requestFocus();
            return;
        }
        //Validamos que esté seleccionado el banco emisor y/o banco proveedor y/o la cuenta bancaria y/o el número de cheque

        idBancoEmisor = null;
        if (cboBancoEmisor.isVisible()) {
            if (cboBancoEmisor.getSelectedIndex() >= 0) {
                idBancoEmisor = control.ObtenerDato("banco", "nombre", cboBancoEmisor.getSelectedItem().toString(), 1);
            } else {
                JOptionPane.showMessageDialog(this, "Debe indicar el Banco emisor", "Mensaje", JOptionPane.WARNING_MESSAGE);
                cboBancoEmisor.requestFocus();
                return;
            }
        }
        idBancoProveedor = null;
        if (cboBancoProveedor.isVisible()) {
            if (cboBancoProveedor.getSelectedIndex() >= 0) {
                idBancoProveedor = control.ObtenerDato("banco", "nombre", cboBancoProveedor.getSelectedItem().toString(), 1);
            } else {
                JOptionPane.showMessageDialog(this, "Debe indicar el Banco en donde el proveedor tiene una cuenta bancaria", "Mensaje", JOptionPane.WARNING_MESSAGE);
                cboBancoProveedor.requestFocus();
                return;
            }
        }
        idCuentaProv = null;
        if (cboCuenta.isVisible()) {
            if (cboCuenta.getSelectedIndex() >= 0) {
                idCuentaProv = mapcuenta.get(cboCuenta.getSelectedIndex()).toString();
            } else {
                JOptionPane.showMessageDialog(this, "Debe indicar la cuenta bancaria", "Mensaje", JOptionPane.WARNING_MESSAGE);
                cboCuenta.requestFocus();
                return;
            }
            if(idCuentaProv.equals("0")){
                JOptionPane.showMessageDialog(this, "No Posee Cuentas Bancarias del Proveedor Registradas ", "Mensaje", JOptionPane.WARNING_MESSAGE);
                cboCuenta.requestFocus();
                return;
            }
        }
        if (txtNumcheque.isVisible()) {
            if (txtNumcheque.getText().trim().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Debe indicar el número del cheque girado", "Mensaje", JOptionPane.WARNING_MESSAGE);
                txtNumcheque.requestFocus();
                return;
            }
        }
        
         if (txtNumOp.isVisible()) {
            if (txtNumOp.getText().trim().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Debe indicar el número de Operación Bancario", "Mensaje", JOptionPane.WARNING_MESSAGE);
                txtNumOp.requestFocus();
                return;
            }
        }

        double montoRestante = Double.parseDouble(txtImportePendienteSoles.getText().replace(",", ""));
        if (montoRestante + montoEditando <= 0) {
            JOptionPane.showMessageDialog(this, "La factura ya está completamente pagada, ya no es posible realizar más pagos.");
            return;
        }
        double importePago = 0;
        double importeReal = 0;
        try {
            importePago = Double.parseDouble(txtMonto.getText().trim());
        } catch (NumberFormatException e) {

        }

        if (importePago > 0) {

            if (enSoles) {
                if (cboMoneda.getSelectedIndex() == 0) {
                    if (importePago > montoRestante + montoEditando) {
                        importePago = montoRestante + montoEditando;
                    }
                    importeReal = importePago;
                } else {
                    importeReal = importePago * tasaDeCambio;
                    if ( importeReal > montoRestante + montoEditando) {
                        importeReal = montoRestante + montoEditando;
                    }
                    
                }
            } /*else {//La factura está en dólares
                if (cboMoneda.getSelectedIndex() == 0) {
                    importeReal = importePago / tasaDeCambio;
                    if (importePago / tasaDeCambio > montoRestante + montoEditando) {
                        importeReal = montoRestante + montoEditando;
                    }
                    importePago = importeReal * tasaDeCambio;

                } else {
                    if (importePago > montoRestante + montoEditando) {
                        importePago = (montoRestante + montoEditando);
                    }
                    importeReal = importePago;
                }
            }*/

            control.Sql = String.format("CALL pagoProveedor(%s,'%s',%s,%s,%s,'%s',%s,%s,%s,'%s',%s,'%s',%d)",
                    idPagoProveedor,
                    control.Formato_Amd(jDateChooser1.getDate()), importePago, importeReal, cboMoneda.getSelectedIndex() == 0,
                    cboTipopago.getSelectedItem().toString(), idBancoEmisor, idBancoProveedor, idCuentaProv,
                    txtNumcheque.getText(), idFacturaProveedor , txtNumOp.getText()+"", idPagoProveedor == null ? 0 : 1);
            boolean res = control.ejecutar(control.Sql);
            if (res) {
                cboTipopago.setSelectedIndex(0);
                txtNumcheque.setText("");
                txtMonto.setText("0.00");
                jDateChooser1.setDate(new Date());
                tPagosRealizados.setEnabled(true);
                idPagoProveedor = null;
                cboMoneda.setSelectedIndex(0);
                buscarPagos();
                montoRestante = Double.parseDouble(txtImportePendienteSoles.getText().replace(",", ""));

                control.ejecutar(String.format("UPDATE facturaproveedor f SET f.`pagado`=%s WHERE f.`idFacturaProveedor`=%s;", montoRestante <= 0, idFacturaProveedor));
                pagado = montoRestante <= 0;

                JOptionPane.showMessageDialog(rootPane, "El pago se ha guardado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                ((JTextField) jDateChooser1.getDateEditor()).requestFocus();
                if (listadoFacturas != null) {
                    listadoFacturas.recargar();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al generar el pago de la factura", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El monto debe de ser mayor a cero (0.00)", "Mensaje", JOptionPane.WARNING_MESSAGE);
            txtMonto.requestFocus();
        }

    }

    private void mostrarpagos() {
        buscarPagos();
    }

    private void buscarPagos() {
        String moneda = "$ ";
        if (enSoles) {
            moneda = "S/ ";
        }
        control.Sql = "(SELECT p.`idPagoProveedor`, date_format(p.`fechapago`,'%d/%m/%Y') AS fecha,'Efectivo','','','','', CONCAT(IF(p.`esSoles`=TRUE,'S/ ','$ '),(IF(p.montoPago<1,format(p.montoPago,length(p.montopago)),p.montoPago))) AS monto,CONCAT('" + moneda + "',(IF(p.montoreal<1,format(p.montoreal,length(p.montoreal)),p.montoreal))) AS montoReal, p.`fechapago` FROM pagoproveedor p \n" +
"                WHERE p.`tipoPago`=0 and p.`idFacturaProveedor`='"+idFacturaProveedor+"') union \n" +
"                (SELECT p.`idPagoProveedor`, date_format(p.`fechapago`,'%d/%m/%Y') AS fecha,'Cheque', b.`nombre`, '', p.`numeroCheque`,'',CONCAT(IF(p.`esSoles`=TRUE,'S/ ','$ '),(IF(p.montoPago<1,format(p.montoPago,length(p.montopago)),p.montoPago))) AS monto,CONCAT('" + moneda + "',IF(p.montoreal<1,format(p.montoreal,length(p.montoreal)),p.montoreal)) AS montoReal, p.`fechapago` FROM pagoproveedor p, banco b \n" +
"                WHERE p.`idBancoEmisor` = b.`idBanco` AND p.`tipoPago`=1 and p.`idFacturaProveedor`='"+idFacturaProveedor+"') union \n" +
"                (SELECT p.`idPagoProveedor`, date_format(p.`fechapago`,'%d/%m/%Y') AS fecha,'Cuenta Bancaria', '', \n" +
"                (SELECT b.`nombre` FROM banco b WHERE b.`idBanco` = p.`idBancoProveedor`),p.`codOpe`, c.`numerocunt`,CONCAT(IF(p.`esSoles`=TRUE,'S/ ','$ '),IF(p.`esSoles`=TRUE,'S/ ','$ '),(IF(p.montoPago<1,format(p.montoPago,length(p.montopago)),p.montoPago)))  AS monto,CONCAT('" + moneda + "',IF(p.montoreal<1,format(p.montoreal,length(p.montoreal)),p.montoreal)) AS montoReal, p.`fechapago` \n" +
"                FROM pagoproveedor p,banco b2, cuentaproveedor c \n" +
"                WHERE p.`idBancoProveedor` = b2.`idBanco` AND p.`tipoPago`=2 \n" +
"                AND c.`idCuentaProveedor` = p.`idCuentaProveedor` \n" +
"                AND p.`idFacturaProveedor`='"+idFacturaProveedor+"') ORDER BY 9;";

        control.LlenarJTabla(modelo, control.Sql, 9);
        btnEliminar.setEnabled(false);
        double importePagado = 0;
        double total = totalSoles;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            importePagado += Double.parseDouble(modelo.getValueAt(i, 8).toString().replace(",", "").replace("S/ ", "").replace("$ ", ""));

        }
        txtImportePagadoSoles.setText(String.valueOf(importePagado));
        txtImportePendienteSoles.setText(String.valueOf(total - importePagado));

        montoEditando = 0;

    }

    private void eliminarPago() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Si elimina este pago ya no podrá recuperarlo posteriormente.\n¿Confirma que desea continuar?", "Eliminar pago", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            control.ejecutar(String.format("DELETE FROM pagoproveedor WHERE `idPagoProveedor`=%s;", modelo.getValueAt(tPagosRealizados.getSelectedRow(), 0).toString()));
            buscarPagos();
            double montoRestante = Double.parseDouble(txtImportePendienteSoles.getText().replace(",", ""));

            control.ejecutar(String.format("UPDATE facturaproveedor f SET f.`pagado`=%s WHERE f.`idFacturaProveedor`=%s;", montoRestante <= 0, idFacturaProveedor));
            pagado = montoRestante <= 0;

            if (listadoFacturas != null) {
                listadoFacturas.recargar();
            }
        }
    }

    private void cargarDatosModificar() {
        int row = tPagosRealizados.getSelectedRow();
        idPagoProveedor = modelo.getValueAt(row, 0).toString();
        ((JTextField) jDateChooser1.getDateEditor()).setText(modelo.getValueAt(row, 1).toString());
        cboTipopago.setSelectedItem(modelo.getValueAt(row, 2).toString());
        if (cboBancoEmisor.isVisible()) {
            cboBancoEmisor.setSelectedItem(modelo.getValueAt(row, 3).toString());
        }
        if (cboBancoProveedor.isVisible()) {
            cboBancoProveedor.setSelectedItem(modelo.getValueAt(row, 4).toString());
        }
        if (cboCuenta.isVisible()) {
            cboCuenta.setSelectedItem(modelo.getValueAt(row, 5).toString());
        }
        if (txtNumcheque.isVisible()) {
            txtNumcheque.setText(modelo.getValueAt(row, 5).toString());
        }
        txtMonto.setText(modelo.getValueAt(row, 6).toString().replace("S/ ", "").replace("$ ", "").replace(",", ""));
        cboMoneda.setSelectedIndex(modelo.getValueAt(row, 6).toString().charAt(0) == '$' ? 1 : 0);
        montoEditando = Double.parseDouble(modelo.getValueAt(row, 7).toString().replace("S/ ", "").replace("$ ", "").replace(",", ""));
        ((JTextField) jDateChooser1.getDateEditor()).requestFocus();
        tPagosRealizados.setEnabled(false);
        btnEliminar.setEnabled(false);

    }

    public static void recargarCuentas() {
        String dato="";
        
        if(cboBancoProveedor.getSelectedIndex()>=0){
        dato=cboBancoProveedor.getSelectedItem().toString();
        }
        
        if (cboBancoProveedorCargado) {
            System.out.println("holaaaaa");
            control.fillComboBox("select (SELECT IF((SELECT count(c.idcuentaproveedor) FROM cuentaproveedor c, proveedor p, banco b\n" +
"                    WHERE c.`idProveedor` = p.`idProveedor` and c.idbanco=b.idbanco and\n" +
"                    p.ruc='" + lbRuc.getText() + "' and b.nombre='" + dato + "')>0,(SELECT c.idcuentaproveedor FROM cuentaproveedor c, proveedor p, banco b\n" +
"                    WHERE c.`idProveedor` = p.`idProveedor` and c.idbanco=b.idbanco and\n" +
"                    p.ruc='" + lbRuc.getText() + "' and b.nombre='" + dato + "'),'0')),\n" +
" (SELECT IF((SELECT count(c.idcuentaproveedor) FROM cuentaproveedor c, proveedor p, banco b\n" +
"                    WHERE c.`idProveedor` = p.`idProveedor` and c.idbanco=b.idbanco and\n" +
"                    p.ruc='" + lbRuc.getText() + "' and b.nombre='" +dato+ "')>0,(SELECT c.`numerocunt` FROM cuentaproveedor c, proveedor p, banco b\n" +
"                    WHERE c.`idProveedor` = p.`idProveedor` and c.idbanco=b.idbanco and\n" +
"                    p.ruc='" + lbRuc.getText() + "' and b.nombre='" + dato+ "'),'No hay Cuentas Registradas'));", cboCuenta, mapcuenta);
        }
    }
}
