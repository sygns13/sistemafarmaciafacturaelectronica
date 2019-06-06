/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.gui.dialogs.SeleccionarProductoRegularizacionDialog;
import jym.ferreteria.renders.CheckBoxCellRenderer;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LabelRenderer;

/**
 *
 * @author Omr
 */
public class FrmGestionRegularizacionesDeStock extends javax.swing.JInternalFrame {

    private CustomTableModel modeloRegularizacion = new CustomTableModel();
    private CustomTableModel modeloProductosDetalle = new CustomTableModel();

    private CustomTableModel productosModelEntrada = new CustomTableModel();
    private CustomTableModel productosModelSalidaTraspaso = new CustomTableModel();
    private String producto;
    private DecimalFormat format = new DecimalFormat("0.00");
    private Controlador control = new Controlador();
    private Map mapMovimiento = new HashMap();
    private Map mapAlmacenSalida = new HashMap();
    private Map mapAlmacenEntrada = new HashMap();
    private boolean tipoMovimientoReady = false;

    private String idRegularizacionStock;
    private String tipoRegularizacion;
    private String almacenSalida;
    private SeleccionarProductoRegularizacionDialog or = new SeleccionarProductoRegularizacionDialog(null, true);
    private boolean modificando = false;
    private boolean actualizado = false;
    private String idAlmacenSalida;
    private String datosProducto[] = null;

    /**
     * Creates new form RegularizacionDeStock
     */
    public FrmGestionRegularizacionesDeStock() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        productosModelEntrada.setColumnIdentifiers(new String[]{"Id", "Descripción producto", "Unidad"});
        productosModelSalidaTraspaso.setColumnIdentifiers(new String[]{"Id", "Descripción producto", "Unidad", "Stock"});
        tablaProductos.setModel(productosModelEntrada);

        control.hideTableColumn(tablaProductos, 0);
        control.tableWidthColumn(tablaProductos, 400, 1);
        panelDetalle.setVisible(false);

        jDateChooserFecha.setDate(new Date());
        control.fillComboBox("SELECT * FROM `tiporegularizacionstock`;", cboMovimiento, mapMovimiento);
        tipoMovimientoReady = true;
        try {
            cboMovimiento.setSelectedIndex(0);
        } catch (Exception e) {
        }
        control.fillComboBox("SELECT * FROM `almacen`;", cboAlmacenSalida, mapAlmacenSalida);
        control.fillComboBox("SELECT * FROM `almacen`;", cboAlmacenEntrada, mapAlmacenEntrada);
        tablaProductosDetalle.setModel(modeloProductosDetalle);
        tablaProductosDetalle.setDefaultRenderer(Object.class, new LabelRenderer());
        modeloProductosDetalle.setColumnIdentifiers(new String[]{"idDetalle", "idProducto", "Descripción Producto", "Cantidad"});
        tablaProductosDetalle.getColumnModel().getColumn(3).setCellRenderer(new LabelRenderer(SwingConstants.RIGHT));

        control.hideTableColumn(tablaProductosDetalle, 0, 1);
        control.tableWidthColumn(tablaProductosDetalle, 500, 2);
        control.tableMaxWidthColumn(tablaProductosDetalle, 80, 3);
        tablaRegularizaciones.setModel(modeloRegularizacion);
        modeloRegularizacion.setColumnIdentifiers(new String[]{"Codigo", "Número", "Fecha", "Tipo", "Almacen Entrada", "Almacen Salida", "Motivo", "A"});
        cargarRegularizaciones();
        control.tableMaxWidthColumn(tablaRegularizaciones, 55, 1);
        control.tableMaxWidthColumn(tablaRegularizaciones, 80, 2);
        tablaRegularizaciones.getColumnModel().getColumn(5).setPreferredWidth(200);
        tablaRegularizaciones.getColumnModel().getColumn(4).setPreferredWidth(200);
        tablaRegularizaciones.getColumnModel().getColumn(6).setPreferredWidth(200);

        control.hideTableColumn(tablaRegularizaciones, 0);
        control.tableMaxWidthColumn(tablaRegularizaciones, 20, 7);
        tablaRegularizaciones.getColumnModel().getColumn(7).setCellRenderer(new CheckBoxCellRenderer());
        habilitar(false);
        tablaRegularizaciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnEliminar.setEnabled(true);
                btnActualizar.setEnabled(true);
                if (tablaRegularizaciones.getSelectedRow() >= 0) {
                    actualizado = control.ejecutarMsg(String.format("SELECT `actualizado` FROM `regularizacionstock` WHERE `idRegularizacionStock`=%s;", modeloRegularizacion.getValueAt(tablaRegularizaciones.getSelectedRow(), 0).toString())).equals("1");
                    tipoRegularizacion = modeloRegularizacion.getValueAt(tablaRegularizaciones.getSelectedRow(), 3).toString();
                    btnActualizar.setText(actualizado ? "Deshacer la actualización" : "Actualizar");
//                    btnEliminar.setEnabled(!actualizado);
                    btnImprimirListado.setEnabled(true);
                }
            }
        });
        ((JTextField) jDateChooserFecha.getDateEditor()).addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent evt) {
                control.passFocus(evt, cboMovimiento);

            }

        });
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRegularizaciones = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblAlmacenSalida = new javax.swing.JLabel();
        lblAlmacenEntrada = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroMovimiento = new javax.swing.JTextField();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        cboMovimiento = new javax.swing.JComboBox();
        cboAlmacenEntrada = new javax.swing.JComboBox();
        cboAlmacenSalida = new javax.swing.JComboBox();
        txtMotivo = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnImprimirListado = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelDetalle = new javax.swing.JPanel();
        lblNombreProducto = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductosDetalle = new javax.swing.JTable();
        btnEliminarProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        lblCantidadDisponible = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        txtBuscarProducto = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Regularización de Stock");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaRegularizaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaRegularizaciones.setName("tablaRegularizaciones"); // NOI18N
        tablaRegularizaciones.getTableHeader().setReorderingAllowed(false);
        tablaRegularizaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRegularizacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaRegularizacionesMouseEntered(evt);
            }
        });
        tablaRegularizaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaRegularizacionesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRegularizaciones);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("N° movimiento");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Fecha");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Movimiento");
        jLabel3.setName("jLabel3"); // NOI18N

        lblAlmacenSalida.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAlmacenSalida.setText("Alm. Salida");
        lblAlmacenSalida.setName("lblAlmacenSalida"); // NOI18N

        lblAlmacenEntrada.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAlmacenEntrada.setText("Alm. Entrada");
        lblAlmacenEntrada.setName("lblAlmacenEntrada"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Motivo");
        jLabel6.setName("jLabel6"); // NOI18N

        txtNumeroMovimiento.setEnabled(false);
        txtNumeroMovimiento.setName("txtNumeroMovimiento"); // NOI18N

        jDateChooserFecha.setName("jDateChooserFecha"); // NOI18N
        jDateChooserFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooserFechaKeyPressed(evt);
            }
        });

        cboMovimiento.setToolTipText("");
        cboMovimiento.setName("cboMovimiento"); // NOI18N
        cboMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMovimientoActionPerformed(evt);
            }
        });
        cboMovimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboMovimientoKeyPressed(evt);
            }
        });

        cboAlmacenEntrada.setToolTipText("");
        cboAlmacenEntrada.setName("cboAlmacenEntrada"); // NOI18N
        cboAlmacenEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboAlmacenEntradaKeyPressed(evt);
            }
        });

        cboAlmacenSalida.setToolTipText("");
        cboAlmacenSalida.setName("cboAlmacenSalida"); // NOI18N
        cboAlmacenSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboAlmacenSalidaKeyPressed(evt);
            }
        });

        txtMotivo.setName("txtMotivo"); // NOI18N
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMotivoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumeroMovimiento)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboMovimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboAlmacenSalida, 0, 134, Short.MAX_VALUE)
                    .addComponent(lblAlmacenSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboAlmacenEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAlmacenEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtMotivo))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(lblAlmacenSalida)
                    .addComponent(lblAlmacenEntrada)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumeroMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboAlmacenEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboAlmacenSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Add to basket.png"))); // NOI18N
        btnActualizar.setMnemonic('A');
        btnActualizar.setText("Actualizar");
        btnActualizar.setName("btnActualizar"); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Document.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnImprimirListado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimirListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimirListado.setMnemonic('I');
        btnImprimirListado.setText("Imprimir");
        btnImprimirListado.setName("btnImprimirListado"); // NOI18N
        btnImprimirListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirListadoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-document-save-icon.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        btnSalir.setMnemonic('S');
        btnSalir.setText("Salir");
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        panelDetalle.setName("panelDetalle"); // NOI18N

        lblNombreProducto.setForeground(new java.awt.Color(0, 52, 193));
        lblNombreProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblNombreProducto.setName("lblNombreProducto"); // NOI18N

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtCantidad.setText("1.00");
        txtCantidad.setName("txtCantidad"); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaProductosDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductosDetalle.setName("tablaProductosDetalle"); // NOI18N
        tablaProductosDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosDetalleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductosDetalle);

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash-icon.png"))); // NOI18N
        btnEliminarProducto.setName("btnEliminarProducto"); // NOI18N
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnCancelarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        btnCancelarProducto.setName("btnCancelarProducto"); // NOI18N
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });

        lblCantidadDisponible.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblCantidadDisponible.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblCantidadDisponible.setName("lblCantidadDisponible"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductos.setName("tablaProductos"); // NOI18N
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        tablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tablaProductos);

        txtBuscarProducto.setName("txtBuscarProducto"); // NOI18N
        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelDetalleLayout = new javax.swing.GroupLayout(panelDetalle);
        panelDetalle.setLayout(panelDetalleLayout);
        panelDetalleLayout.setHorizontalGroup(
            panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleLayout.createSequentialGroup()
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDetalleLayout.createSequentialGroup()
                        .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarProducto))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelDetalleLayout.setVerticalGroup(
            panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalleLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCantidadDisponible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBuscarProducto)
                        .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelarProducto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblBuscar.setText("Buscar: ");
        lblBuscar.setName("lblBuscar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimirListado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnImprimirListado)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitar(true);
        btnNuevo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        tablaRegularizaciones.setEnabled(false);
        generarNumero();
        ((JTextField) jDateChooserFecha.getDateEditor()).requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void agregarProducto() {
        if (!actualizado) {
            if (datosProducto != null) {
                double cantidad = 0;
                try {
                    cantidad = Double.parseDouble(txtCantidad.getText());
                } catch (Exception e) {
                }
                if (cantidad > 0) {
                    double stock = Double.MAX_VALUE;
                    try {
                        stock = Double.parseDouble(lblCantidadDisponible.getText());
                    } catch (Exception e) {
                    }
                    if (cantidad <= stock) {
                        int index = buscarProducto(datosProducto[0]);
                        System.out.println(index);
                        if (index < 0) { //Registrando nuevo item
                            int id = control.executeAndGetId(String.format("INSERT INTO detalleregularizacionstock VALUES(NULL,%s,%s,%s);", datosProducto[0], cantidad, idRegularizacionStock));
                            modeloProductosDetalle.addRow(new String[]{String.valueOf(id), datosProducto[0], datosProducto[1], format.format(cantidad).replace(",", ".")});
                            datosProducto = null;
                            txtCantidad.setText("1.00");
                            lblCantidadDisponible.setText(" ");
                            lblNombreProducto.setText(" ");
                            txtCantidad.setEditable(false);
                            txtBuscarProducto.requestFocus();
                        } else {//Modificando item
                            double cantidadExistente = Double.parseDouble(modeloProductosDetalle.getValueAt(index, 3).toString());
                            if (cantidad + cantidadExistente <= stock) {
                                modeloProductosDetalle.setValueAt(format.format(cantidad + cantidadExistente).replace(",", "."), index, 3);
                                datosProducto = null;
                                txtCantidad.setText("1.00");
                                lblCantidadDisponible.setText(" ");
                                lblNombreProducto.setText(" ");
                                txtCantidad.setEditable(false);
                                txtBuscarProducto.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(this, "Cantidad por debajo de stock, estock disponible " + lblCantidadDisponible.getText(), "Mensaje", 0);
                                txtCantidad.requestFocus();
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Cantidad por debajo de stock, estock disponible " + lblCantidadDisponible.getText(), "Mensaje", 0);
                        txtCantidad.requestFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 1.00", "Mensaje", 2);
                    txtCantidad.requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(this, "No ha seleccionado ningún producto", "Mensaje", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "La regularización ya está actualizada,no se puede agregar más productos", "Aviso", 1);
        }
    }

    private void cboMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMovimientoActionPerformed
        if (tipoMovimientoReady) {
            if (cboMovimiento.getSelectedIndex() == 0) {
                lblAlmacenSalida.setVisible(false);
                cboAlmacenSalida.setVisible(false);
                lblAlmacenEntrada.setVisible(true);
                cboAlmacenEntrada.setVisible(true);
            } else if (cboMovimiento.getSelectedIndex() == 1) {
                lblAlmacenSalida.setVisible(true);
                cboAlmacenSalida.setVisible(true);
                lblAlmacenEntrada.setVisible(false);
                cboAlmacenEntrada.setVisible(false);
            } else if (cboMovimiento.getSelectedIndex() == 2) {
                lblAlmacenSalida.setVisible(true);
                cboAlmacenSalida.setVisible(true);
                lblAlmacenEntrada.setVisible(true);
                cboAlmacenEntrada.setVisible(true);
            }
        }
    }//GEN-LAST:event_cboMovimientoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (!modificando) {
            cancelar();
        } else {
            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "La información ha sido modificada.\n¿Confirma que desea Cancelar los cambios?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                cancelar();
            }
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (panelDetalle.isVisible() == false) {
            guardar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tablaRegularizacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRegularizacionesMouseClicked

        if (evt.getClickCount() > 1 && tablaRegularizaciones.isEnabled()) {
            cargarDatosRegularizacion();
        }
    }//GEN-LAST:event_tablaRegularizacionesMouseClicked

    private void tablaRegularizacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRegularizacionesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaRegularizacionesMouseEntered

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (control.isEnterKey(evt)) {
            agregarProducto();
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed

        eliminarLinea();
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        datosProducto = null;

        lblNombreProducto.setText(" ");
        txtCantidad.setText("1.00");
        txtCantidad.setEditable(false);

        tablaProductosDetalle.setEnabled(true);
        lblCantidadDisponible.setText("");

    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarStock();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        control.decimal(evt, txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    }//GEN-LAST:event_formInternalFrameClosing

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        cargarRegularizaciones();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnImprimirListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirListadoActionPerformed
        Map map = new HashMap();
        map.put("ID", modeloRegularizacion.getValueAt(tablaRegularizaciones.getSelectedRow(), 0).toString());
        System.out.println(idRegularizacionStock);
        control.showReportDialog("Regularización de Stock", "ReporteRegularizacionStock", map);
    }//GEN-LAST:event_btnImprimirListadoActionPerformed

    private void txtBuscarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyPressed
        if (control.isEnterKey(evt)) {
            if (tablaProductos.getRowCount() > 0) {
                try {
                    tablaProductos.getSelectionModel().setSelectionInterval(0, 0);
                    tablaProductos.requestFocus();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_txtBuscarProductoKeyPressed

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        if (tablaProductos.getColumnCount() == 3) {
            cargarProductosEntrada();
        } else {
            cargarProductosTraspasoSalida();
        }

    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed
        if (control.isEnterKey(evt)) {
            int row = tablaProductos.getSelectedRow();

            seleccionarProducto();
            if (row == 0) {
                try {
                    tablaProductos.getSelectionModel().setSelectionInterval(0, -2);
                } catch (Exception e) {
                }
            } else {
                tablaProductos.getSelectionModel().setSelectionInterval(row - 1, row - 1);
            }

        }
    }//GEN-LAST:event_tablaProductosKeyPressed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (evt.getClickCount() > 1) {
            seleccionarProducto();
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void tablaProductosDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosDetalleMouseClicked
        if (evt.getClickCount() > 1) {
            eliminarLinea();
        }
    }//GEN-LAST:event_tablaProductosDetalleMouseClicked

    private void jDateChooserFechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserFechaKeyPressed

    }//GEN-LAST:event_jDateChooserFechaKeyPressed

    private void cboMovimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMovimientoKeyPressed
        if (cboAlmacenSalida.isVisible()) {
            control.passFocus(evt, cboAlmacenSalida);
        } else if (cboAlmacenEntrada.isVisible()) {
            control.passFocus(evt, cboAlmacenEntrada);
        } else {
            control.passFocus(evt, txtMotivo);
        }
    }//GEN-LAST:event_cboMovimientoKeyPressed

    private void cboAlmacenEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboAlmacenEntradaKeyPressed
        control.passFocus(evt, txtMotivo);
    }//GEN-LAST:event_cboAlmacenEntradaKeyPressed

    private void txtMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyPressed
        if (control.isEnterKey(evt)) {
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtMotivoKeyPressed

    private void cboAlmacenSalidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboAlmacenSalidaKeyPressed
        if (cboAlmacenEntrada.isVisible()) {
            control.passFocus(evt, cboAlmacenEntrada);
        } else {
            control.passFocus(evt, txtMotivo);
        }
    }//GEN-LAST:event_cboAlmacenSalidaKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (control.isEnterKey(evt)) {
            tablaRegularizaciones.requestFocus();
            try {
                tablaRegularizaciones.getSelectionModel().addSelectionInterval(0, 0);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tablaRegularizacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaRegularizacionesKeyPressed
        if (control.isEnterKey(evt)) {
            int row = tablaProductos.getSelectedRow();
            cargarDatosRegularizacion();

            if (row == 0) {
                try {
                    tablaProductos.getSelectionModel().setSelectionInterval(0, -2);
                } catch (Exception e) {
                }
            } else {
                tablaProductos.getSelectionModel().setSelectionInterval(row - 1, row - 1);
            }

        }
    }//GEN-LAST:event_tablaRegularizacionesKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimirListado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboAlmacenEntrada;
    private javax.swing.JComboBox cboAlmacenSalida;
    private javax.swing.JComboBox cboMovimiento;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAlmacenEntrada;
    private javax.swing.JLabel lblAlmacenSalida;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCantidadDisponible;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaProductosDetalle;
    private javax.swing.JTable tablaRegularizaciones;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNumeroMovimiento;
    // End of variables declaration//GEN-END:variables

    private void habilitar(boolean b) {
//        txtNumeroMovimiento.setEnabled(b);
        jDateChooserFecha.setEnabled(b);
        cboMovimiento.setEnabled(b);
        cboAlmacenEntrada.setEnabled(b);
        cboAlmacenSalida.setEnabled(b);
        txtMotivo.setEditable(b);
        txtMotivo.setText("");
        try {
            cboAlmacenEntrada.setSelectedIndex(0);
        } catch (Exception e) {
        }
        try {
            cboAlmacenSalida.setSelectedIndex(0);
        } catch (Exception e) {
        }

    }

    private void cancelar() {

        try {
            cboMovimiento.setSelectedIndex(0);
        } catch (Exception e) {
        }
        idRegularizacionStock = null;
        txtNumeroMovimiento.setText("");
        jDateChooserFecha.setDate(new Date());
        txtMotivo.setText("");
        btnNuevo.setEnabled(true);
        btnImprimirListado.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
        tablaRegularizaciones.setEnabled(true);
        habilitar(false);
        cargarRegularizaciones();
        panelDetalle.setVisible(false);
        jPanel1.setVisible(true);
        jScrollPane1.setVisible(true);
        lblBuscar.setVisible(true);
        txtBuscar.setVisible(true);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        pack();
    }

    private void cargarRegularizaciones() {
        String text = txtBuscar.getText();
        control.Sql = "SELECT r.`idRegularizacionStock`, r.`numero`, date_format(r.`fecha`,'%d/%m/%Y'), t.`nombre` as 'TIPO', "
                + "(select nombre from almacen where idalmacen=idalmacenentrada)as 'Almacen Entrada', "
                + "(select nombre from almacen where idAlmacen=idalmacenSalida) as 'Almacen Salida', r.motivo, r.actualizado "
                + "FROM regularizacionstock r, tiporegularizacionstock t "
                + "WHERE t.`idTipoRegularizacionStock`=r.`idTipoRegularizacionStock` AND "
                + "(r.`numero` LIKE '%" + text + "%' "
                + "OR date_format(r.`fecha`,'%d/%m/%Y') LIKE '%" + text + "%' "
                + "OR t.`nombre` LIKE '%" + text + "%') ORDER BY r.numero DESC;";
        control.LlenarJTabla(modeloRegularizacion, control.Sql, 8);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnImprimirListado.setEnabled(false);
    }

    private void guardar() {
        generarNumero();

        //if(cboAlmacenEntrada.getSelectedItem().toString() == null ? cboAlmacenSalida.getSelectedItem().toString() != null : !cboAlmacenEntrada.getSelectedItem().toString().equals(cboAlmacenSalida.getSelectedItem().toString())){
        control.Sql = "SELECT r.`idRegularizacionStock`, r.`numero`, r.`fecha`, t.`nombre` as 'TIPO',(select nombre from almacen where idalmacen=idalmacenentrada)as 'Almacen Entrada',(select nombre from almacen where idAlmacen=idalmacenSalida) as 'Almacen Salida', r.motivo FROM regularizacionstock r, tiporegularizacionstock t WHERE t.`idTipoRegularizacionStock`=r.`idTipoRegularizacionStock`;";
        String idTipo = mapMovimiento.get(cboMovimiento.getSelectedIndex()).toString();
        String idAlmacenE = mapAlmacenEntrada.get(cboAlmacenEntrada.getSelectedIndex()).toString();
        String idAlmacenS = mapAlmacenSalida.get(cboAlmacenSalida.getSelectedIndex()).toString();
        control.Sql = "call RegularizacionStock('0','" + txtNumeroMovimiento.getText() + "','" + control.Formato_Amd(jDateChooserFecha.getDate()) + "','" + idTipo + "','" + idAlmacenE + "','" + idAlmacenS + "','" + txtMotivo.getText() + "'," + Accesos.getInstance().getIdUsuario() + ",'0')";
        String Resultado = control.ejecutarMsg(control.Sql);

        if (Resultado.equals("1")) {
            JOptionPane.showMessageDialog(rootPane, "La regularización se ha guardado correctamente");
            generarNumero();
            cargarRegularizaciones();
            btnNuevo.setEnabled(true);
            btnGuardar.setEnabled(false);
            tablaRegularizaciones.setEnabled(true);
            habilitar(false);
            txtBuscar.requestFocus();

        } else {
            JOptionPane.showMessageDialog(rootPane, Resultado);
        }

    }

    private void generarNumero() {
        control.Sql = "SELECT MAX(`numero`) FROM `regularizacionstock`;";
        int numero = 0;
        try {
            numero = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        } catch (Exception e) {
        }
        numero++;
        txtNumeroMovimiento.setText(control.format0(5, numero));
    }

    private void llenarTablaDetalle() {

        control.Sql = "SELECT d.`idDetalleRegularizacionStock`,v.`ID`, v.`Descripcion`, d.`cantidad` FROM detalleregularizacionstock d, vta_catalogo v WHERE v.`ID`=d.`idproducto` AND d.`idRegularizacionStock`='" + idRegularizacionStock + "'";
        control.LlenarJTabla(modeloProductosDetalle, control.Sql, 4);

    }

    private void actualizarStock() {
        int showConfirmDialog = -1;
        idRegularizacionStock = modeloRegularizacion.getValueAt(tablaRegularizaciones.getSelectedRow(), 0).toString();
        actualizado = control.ejecutarMsg(String.format("SELECT `actualizado` FROM `regularizacionstock` WHERE `idRegularizacionStock`=%s;", idRegularizacionStock)).equals("1");
        if (!actualizado) {
            showConfirmDialog = JOptionPane.showConfirmDialog(this, "Después de actualizar, no se permitirá la modificación de la regularización actual.\nLa actualización de este movimiento actualizará su almacén\n¿Confirma que desea continuar? ", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } else {
            showConfirmDialog = JOptionPane.showConfirmDialog(this, "Deshacer la actualización, restaurará el stock incrementado o disminuido \npor la regularización seleccionada a su nivel anterior", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }

        if (showConfirmDialog == JOptionPane.YES_OPTION) {

            control.Sql = "SELECT d.`idproducto`, d.`cantidad`, r.`idAlmacenEntrada`, r.`idAlmacenSalida` FROM detalleregularizacionstock d, regularizacionstock r WHERE r.`idRegularizacionStock`=d.`idRegularizacionStock` and d.`idRegularizacionStock`=" + idRegularizacionStock;
            ResultSet detalle = control.DevolverRegistro(control.Sql);
            //Actualiza los almacenes, con la cantidad de los productos del detalle
            if (!actualizado) {
                try {
                    while (detalle.next()) {
                        if ("Entrada".equals(tipoRegularizacion)) {
                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(3) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(3) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                        } else if ("Salida".equals(tipoRegularizacion)) {

                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(4) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(4) + " AND s.`idProducto`=" + detalle.getString(1) + "");

                        } else {
                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(3) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(3) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(4) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(4) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmGestionRegularizacionesDeStock.class.getName()).log(Level.SEVERE, null, ex);
                }

                actualizado = true;
                JOptionPane.showMessageDialog(this, "La regularización se ha actualizado correctamente");
                btnActualizar.setText("Deshacer la actualización");

            } else {
                //Deshace la actualizaci anterior
                System.out.println("deshaciendo");
                try {
                    while (detalle.next()) {
                        if ("Entrada".equals(tipoRegularizacion)) {
                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(3) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(3) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                        } else if ("Salida".equals(tipoRegularizacion)) {

                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(4) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(4) + " AND s.`idProducto`=" + detalle.getString(1) + "");

                        } else {
                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(3) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(3) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                            control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(4) + "," + detalle.getString(1) + ",0)");
                            control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(4) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmGestionRegularizacionesDeStock.class.getName()).log(Level.SEVERE, null, ex);
                }
                actualizado = false;
                JOptionPane.showMessageDialog(this, "Se ha revocado la actualización de la regularización correctamente");
                btnActualizar.setText("Actualizar");
            }
            control.ejecutar(String.format("UPDATE regularizacionstock r SET r.`actualizado`=%s WHERE r.`idRegularizacionStock`=%s;", String.valueOf(actualizado), idRegularizacionStock));
            recargar();
            try {
                FrmProductosBajosDeStock.cargarDatos();
            } catch (Exception e) {
            }
        }

    }

    public void recargar() {
        int row = tablaRegularizaciones.getSelectedRow();
        cargarRegularizaciones();
        if (row >= 0) {
            tablaRegularizaciones.getSelectionModel().setSelectionInterval(row, row);
        }
    }

    private void eliminar() {
        int resultado = 2;
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "¿Confirma que desea eliminar la regularización seleccionada?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            String id = modeloRegularizacion.getValueAt(tablaRegularizaciones.getSelectedRow(), 0).toString();
            boolean esActualizado = control.ejecutarMsg(String.format("SELECT `actualizado` FROM `regularizacionstock` WHERE `idRegularizacionStock`=%s;", id)).equals("1");
            if (esActualizado) {
                resultado = JOptionPane.showOptionDialog(this, "La regularización de stock que está intentando eliminar ya está actualiza\nPor favor seleccione la acción de su conveniencia", "Acciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, frameIcon, new String[]{"Restaurar stock y eliminar", "Solo eliminar", "Cancelar"}, "Cancelar");
            }

            if (resultado != 2 && resultado != -1) {
                if (resultado == 0) {
                    control.Sql = "SELECT d.`idproducto`, d.`cantidad`, r.`idAlmacenEntrada`, r.`idAlmacenSalida` FROM detalleregularizacionstock d, regularizacionstock r WHERE r.`idRegularizacionStock`=d.`idRegularizacionStock` and d.`idRegularizacionStock`=" + id;
                    ResultSet detalle = control.DevolverRegistro(control.Sql);

                    //Deshace la actualizaci anterior
                    try {
                        while (detalle.next()) {
                            if ("Entrada".equals(tipoRegularizacion)) {
                                control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(3) + "," + detalle.getString(1) + ",0)");
                                control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(3) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                            } else if ("Salida".equals(tipoRegularizacion)) {

                                control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(4) + "," + detalle.getString(1) + ",0)");
                                control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(4) + " AND s.`idProducto`=" + detalle.getString(1) + "");

                            } else {
                                control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(3) + "," + detalle.getString(1) + ",0)");
                                control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(3) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                                control.ejecutar("INSERT ignore INTO stock VALUES(" + detalle.getString(4) + "," + detalle.getString(1) + ",0)");
                                control.ejecutar("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+" + detalle.getString(2) + " WHERE s.`idAlmacen`=" + detalle.getString(4) + " AND s.`idProducto`=" + detalle.getString(1) + "");
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FrmGestionRegularizacionesDeStock.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    actualizado = false;

                    control.ejecutar(String.format("DELETE FROM regularizacionstock WHERE `idRegularizacionStock`=%s;", id));
                    cargarRegularizaciones();

                } else if (resultado == 1) {
                    if (control.ejecutar(String.format("DELETE FROM regularizacionstock WHERE `idRegularizacionStock`=%s;", id))) {
                        cargarRegularizaciones();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar la regularización seleccionada");

                    }
                }
            } else if (resultado == 2) {
                if (control.ejecutar(String.format("DELETE FROM regularizacionstock WHERE `idRegularizacionStock`=%s;", id))) {
                    cargarRegularizaciones();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la regularización seleccionada");

                }
            }

        }
    }

    private void cargarProductosEntrada() {
        String text = txtBuscarProducto.getText();
        control.Sql = "select  `ID`, `Descripcion`, `Unidad`  from Vta_Catalogo where `Descripcion` like '%" + text
                + "%' or marca like'%" + text + "%' or tipo like '%" + text
                + "%' or unidad like'%" + text + "%'order by Descripcion";
        control.LlenarJTabla(productosModelEntrada, control.Sql, 3);
    }

    private void cargarProductosTraspasoSalida() {

        this.aplicarFormato();
        String text = txtBuscarProducto.getText();
        control.Sql = "select  `ID`, `Descripcion`, `Unidad`,s.`cantidadDisponible`  from Vta_Catalogo v, stock s "
                + "WHERE s.`idProducto`=v.ID AND s.`idAlmacen`='" + idAlmacenSalida + "' and ( `Descripcion` like '%" + text
                + "%' or marca like'%" + text + "%' or tipo like '%" + text
                + "%' or unidad like'%" + text + "%')order by Descripcion";

        control.LlenarJTabla(productosModelSalidaTraspaso, control.Sql, 4);

    }

    private void aplicarFormato() {
        control.hideTableColumn(tablaProductos, 0);
        control.tableWidthColumn(tablaProductos, 500, 1);
        if (tablaProductos.getColumnCount() >= 4) {
            tablaProductos.getColumnModel().getColumn(3).setCellRenderer(new LabelRenderer(SwingConstants.RIGHT));
        }
    }

    private void seleccionarProducto() {
        int row = tablaProductos.getSelectedRow();
        if (tablaProductos.getColumnCount() == 3) {
            datosProducto = new String[4];
            datosProducto[0] = productosModelEntrada.getValueAt(row, 0).toString();//id
            datosProducto[1] = productosModelEntrada.getValueAt(row, 1).toString();//nombre
            datosProducto[2] = productosModelEntrada.getValueAt(row, 2).toString();//unidad
            datosProducto[3] = "";//stock
        } else {
            datosProducto = new String[4];
            datosProducto[0] = productosModelSalidaTraspaso.getValueAt(row, 0).toString();//id
            datosProducto[1] = productosModelSalidaTraspaso.getValueAt(row, 1).toString();//nombre
            datosProducto[2] = productosModelSalidaTraspaso.getValueAt(row, 2).toString();//unidad
            datosProducto[3] = productosModelSalidaTraspaso.getValueAt(row, 3).toString();//stock
        }
        lblNombreProducto.setText("<html>&nbsp;&nbsp;" + datosProducto[1] + "</html>");
        lblCantidadDisponible.setText(datosProducto[3]);
        txtCantidad.setText("1.00");
        txtCantidad.selectAll();
        txtCantidad.requestFocus();
        txtCantidad.setEditable(true);
    }

    private int buscarProducto(String idProducto) {

        for (int i = 0; i < modeloProductosDetalle.getRowCount(); i++) {
            if (idProducto.equals(modeloProductosDetalle.getValueAt(i, 1).toString())) {
                return i;
            }

        }
        return -1;
    }

    private void eliminarLinea() {
        int selectedRow = tablaProductosDetalle.getSelectedRow();
        if (selectedRow >= 0) {
            if (!actualizado) {
                if (JOptionPane.showConfirmDialog(this, "¿Confirma que desea eliminar el producto seleccionado?", "Eliminar producto", JOptionPane.YES_NO_OPTION, 3) == JOptionPane.YES_OPTION) {
                    control.ejecutar(String.format("DELETE FROM detalleregularizacionstock WHERE `idDetalleRegularizacionStock`=%s;", modeloProductosDetalle.getValueAt(selectedRow, 0).toString()));
                    modeloProductosDetalle.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this, "La regularización ya está actualizada,no se puede eliminar los productos de la misma", "Aviso", 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione la linea de producto a eliminar", "Mensaje", 2);
        }

    }

    private void cargarDatosRegularizacion() {
        btnGuardar.setEnabled(false);
        int row = tablaRegularizaciones.getSelectedRow();
        idRegularizacionStock = modeloRegularizacion.getValueAt(row, 0).toString();
        tipoRegularizacion = modeloRegularizacion.getValueAt(row, 3).toString();
        almacenSalida = modeloRegularizacion.getValueAt(row, 5).toString();

        panelDetalle.setVisible(true);
        jPanel1.setVisible(false);
        jScrollPane1.setVisible(false);
        lblBuscar.setVisible(false);
        txtBuscar.setVisible(false);

        btnNuevo.setEnabled(false);
        btnImprimirListado.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        habilitar(false);
        llenarTablaDetalle();
        if (tipoRegularizacion.equals("Entrada")) {
            tablaProductos.setModel(productosModelEntrada);
            cargarProductosEntrada();
        } else if (tipoRegularizacion.equals("Salida") || tipoRegularizacion.equals("Traspaso")) {
            tablaProductos.setModel(productosModelSalidaTraspaso);
            idAlmacenSalida = control.DevolverRegistroDto("select * from almacen where nombre='" + almacenSalida + "';", 1);
            cargarProductosTraspasoSalida();
        }
        txtBuscarProducto.requestFocus();
        pack();
    }

}
