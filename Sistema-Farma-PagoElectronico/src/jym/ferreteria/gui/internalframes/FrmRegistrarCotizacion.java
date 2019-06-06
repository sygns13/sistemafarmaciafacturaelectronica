package jym.ferreteria.gui.internalframes;

/**
 * ** @author Sygns
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import static jym.ferreteria.clases.Controlador.Base;
import jym.ferreteria.gui.dialogs.CobrarVentaDialog;
import jym.ferreteria.gui.dialogs.SeleccionarClienteDialog;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LabelRenderer;
import java.util.ArrayList;
import static jym.ferreteria.clases.Controlador.Base;

public class FrmRegistrarCotizacion extends javax.swing.JInternalFrame {

    private final CustomTableModel modeloProductoDetalle = new CustomTableModel();
    private CustomTableModel productosModel = new CustomTableModel();
    private CustomTableModel productosAlmacenModel = new CustomTableModel();
    private final Controlador control = new Controlador();

    private final DecimalFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());

    private SeleccionarClienteDialog seleccionarCliente = null;
    private String filtro="precxmenor";
    private String cliente;
    private String producto;
    private String codprod;
    private String IDproducto;
    private String idAlmacen;
    private int CantReal=0;
    private String StockReal;
    private String Almacen;
    private String datos[] = null;
    private boolean guardado = false;
    private boolean editando = false;
    private String codProd;
    private String Unidad;
    
     private FrmListadoCotizaciones listadoCotizaciones;
    private List<double[]> listaVenta = new ArrayList();
    private int indexPrecios = 0;
    private int idVenta = 0,cantidadtemporal=0;;
    private int idCot =0;
    private final double precios[] = new double[2];

    private FrmListadoVentas listadoVentas;
    private boolean facturado = false;
    private Border defaultBorder;
    private Border focusBorder = new LineBorder(new Color(255, 164, 0), 2);
    private List listaUnidades = new ArrayList();
    private String unidadDeMedidaProducto;

     private int aux=0;
    public FrmRegistrarCotizacion(FrmListadoCotizaciones listadoCotizaciones) {
        initComponents();
        
        LlenaUnidades();
            
        jLabel10.setVisible(false);
        txtDescuento.setVisible(false);
        
        txtVigencia.setText("30");
        defaultBorder = jScrollPaneProductos.getBorder();
        Font font = new Font("Arial", Font.BOLD, 12);
        this.listadoCotizaciones = listadoCotizaciones;
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tablaProductosDetalle.setComponentPopupMenu(jPopupMenu1);

        //Configuracion para la tabla de productos
        productosModel.setColumnIdentifiers(new String[]{"Id","Codigo","Codigo x Unid.", "Producto","Marca", "Unidad", "Menor", "Mayor", "P. Venta", "esBajoDeStock"});
       productosAlmacenModel.setColumnIdentifiers(new String[]{"Id", "Almacén", "Cantidad","Cantidad Unitaria"});
        tablaProductos.setModel(productosModel);
        tablaAlmacen.setModel(productosAlmacenModel);
        
       control.hideTableColumn(tablaProductos, 0, 1 , 6, 7,9);
        //control.hideTableColumn(tablaAlmacen, 3);
        control.tableWidthColumn(tablaProductos, 120, 1);
        control.tableWidthColumn(tablaProductos, 120, 2);
        control.tableWidthColumn(tablaProductos, 350, 3);
        control.tableWidthColumn(tablaProductos, 150, 4);
        control.tableWidthColumn(tablaProductos, 150, 5);
        control.hideTableColumn(tablaAlmacen, 0);
        control.tableWidthColumn(tablaAlmacen, 250, 1);
        control.tableWidthColumn(tablaProductos, 110, 8);
        tablaProductos.setDefaultRenderer(Object.class, new ProductosLabelRenderer().setFont(font));
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        ProductosLabelRenderer labelRenderer = new ProductosLabelRenderer(SwingConstants.RIGHT);
        labelRenderer.setFont(new Font("Arial", Font.BOLD, 12));
        tablaProductos.getColumnModel().getColumn(6).setCellRenderer(labelRenderer);
        tablaProductos.getColumnModel().getColumn(7).setCellRenderer(labelRenderer);
        tablaProductos.getColumnModel().getColumn(8).setCellRenderer(labelRenderer);
        LabelRenderer labelRendererAlmacen = new LabelRenderer(SwingConstants.RIGHT);
        labelRenderer.setFont(new Font("Arial", Font.BOLD, 12));
        tablaAlmacen.getColumnModel().getColumn(2).setCellRenderer(labelRendererAlmacen);
        tablaAlmacen.getColumnModel().getColumn(2).setCellRenderer(labelRendererAlmacen);
        tablaAlmacen.getColumnModel().getColumn(3).setCellRenderer(labelRendererAlmacen);
        tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (productosModel.getRowCount() > 0 && tablaProductos.getSelectedRow() >= 0) {
                    String dato=cboUnidad.getSelectedItem().toString();
                    control.LlenarJTabla(productosAlmacenModel, String.format("SELECT a.`idAlmacen`, a.`nombre`, concat((format(s.`cantidadDisponible`/%s,2)),space(1),'"+dato+"'),s.`cantidadDisponible`"
                            + " FROM stock s, almacen a WHERE s.`idAlmacen`=a.`idAlmacen` AND s.`idProducto`=%s;",productosModel.getValueAt(tablaProductos.getSelectedRow(), 7).toString(), productosModel.getValueAt(tablaProductos.getSelectedRow(), 0).toString()), 4);
                    
                    try {
                        tablaAlmacen.getSelectionModel().addSelectionInterval(0, 0);
                    } catch (Exception ex) {
                    }
                } else {
                    control.LimTabla(productosAlmacenModel);
                }

            }
        });

            cargarProductos0();
        

        lblNumeroCotizacion.setText(" " + control.nextId("cotizacion"));
        tablaProductosDetalle.setModel(modeloProductoDetalle);
        //modeloProductoDetalle.setColumnIdentifiers(new String[]{"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"});
        modeloProductoDetalle.setColumnIdentifiers(new String[]{"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"});
        control.hideTableColumn(tablaProductosDetalle, 0,1,2, 8,9,10);
        control.tableWidthColumn(tablaProductosDetalle, 300, 3);
        control.tableWidthColumn(tablaProductosDetalle, 100, 4);
        control.tableMaxWidthColumn(tablaProductosDetalle, 80, 5, 6);
        tablaProductosDetalle.setDefaultRenderer(Object.class, new LabelRenderer(SwingConstants.LEFT).setFont(font));

        LabelRenderer labelRenderer2 = new LabelRenderer(SwingConstants.RIGHT);
        labelRenderer2.setFont(font);
        //tablaProductosDetalle.getColumnModel().getColumn(3).setCellRenderer(labelRenderer2);
        tablaProductosDetalle.getColumnModel().getColumn(4).setCellRenderer(labelRenderer2);
        tablaProductosDetalle.getColumnModel().getColumn(5).setCellRenderer(labelRenderer2);
        tablaProductosDetalle.getColumnModel().getColumn(6).setCellRenderer(labelRenderer2);
        tablaProductosDetalle.getColumnModel().getColumn(7).setCellRenderer(labelRenderer2);
        tablaProductosDetalle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                btnEditarProducto.setEnabled(true);
                btnEliminarProducto.setEnabled(true);
            }
        });

        btnCliente.setMnemonic(KeyEvent.VK_C);

        jdchFecha.setDate(new Date());

        this.registerKeyboardAction(agregarClienteAction, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        setClientePorDefecto();
        //leearUnidades();
       
        
        ckGeneral.setVisible(false);
        ckPrecio.setVisible(false);
        ckPreciomayor.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPaneDetalles = new javax.swing.JScrollPane();
        tablaProductosDetalle = new javax.swing.JTable();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        txtBuscarDetalle = new javax.swing.JTextField();
        btnCancelarProducto = new javax.swing.JButton();
        lblAlmacen = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jScrollPaneAlmacen = new javax.swing.JScrollPane();
        tablaAlmacen = new javax.swing.JTable();
        txtTotalVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSubTotalInafecto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        cboUnidad = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtVigencia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jdchFecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblNumeroCotizacion = new javax.swing.JLabel();
        ckGeneral = new javax.swing.JCheckBox();
        ckPrecio = new javax.swing.JCheckBox();
        ckPreciomayor = new javax.swing.JCheckBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        setClosable(true);
        setIconifiable(true);
        setTitle("Cotización de Productos");
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneDetalles.setName("jScrollPaneDetalles"); // NOI18N

        tablaProductosDetalle.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tablaProductosDetalle.setForeground(new java.awt.Color(0, 51, 102));
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
        tablaProductosDetalle.getTableHeader().setReorderingAllowed(false);
        tablaProductosDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosDetalleMouseClicked(evt);
            }
        });
        jScrollPaneDetalles.setViewportView(tablaProductosDetalle);

        jPanel1.add(jScrollPaneDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 710, 312));

        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pencil-icon.png"))); // NOI18N
        btnEditarProducto.setEnabled(false);
        btnEditarProducto.setName("btnEditarProducto"); // NOI18N
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1167, 53, 32, -1));

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash-icon.png"))); // NOI18N
        btnEliminarProducto.setEnabled(false);
        btnEliminarProducto.setName("btnEliminarProducto"); // NOI18N
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1205, 53, 32, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad:");
        jLabel8.setName("jLabel8"); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 61, -1));

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCantidad.setText("1.00");
        txtCantidad.setName("txtCantidad"); // NOI18N
        txtCantidad.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCantidadCaretUpdate(evt);
            }
        });
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 8, 50, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Precio:");
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 43, -1));

        txtPrecio.setEditable(false);
        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPrecio.setText("0.00");
        txtPrecio.setName("txtPrecio"); // NOI18N
        txtPrecio.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPrecioCaretUpdate(evt);
            }
        });
        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPrecioFocusGained(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 8, 71, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Total: ");
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1151, 10, -1, -1));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotal.setText("0.00");
        txtTotal.setName("txtTotal"); // NOI18N
        txtTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTotalCaretUpdate(evt);
            }
        });
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1189, 8, 86, -1));

        lblProducto.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(255, 51, 0));
        lblProducto.setText(" ");
        lblProducto.setName("lblProducto"); // NOI18N
        jPanel1.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 34, 680, -1));

        txtBuscarDetalle.setName("txtBuscarDetalle"); // NOI18N
        txtBuscarDetalle.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtBuscarDetalleAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtBuscarDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarDetalleKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscarDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(771, 58, 320, -1));

        btnCancelarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        btnCancelarProducto.setName("btnCancelarProducto"); // NOI18N
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1243, 53, 32, -1));

        lblAlmacen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAlmacen.setText("Almacén: ");
        lblAlmacen.setName("lblAlmacen"); // NOI18N
        jPanel1.add(lblAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 10, 170, -1));

        lblBuscar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblBuscar.setText("Buscar");
        lblBuscar.setName("lblBuscar"); // NOI18N
        jPanel1.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 11, 48, -1));

        txtBuscarProducto.setName("txtBuscarProducto"); // NOI18N
        txtBuscarProducto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtBuscarProductoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtBuscarProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarProductoFocusGained(evt);
            }
        });
        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 8, 290, -1));

        jScrollPaneProductos.setName("jScrollPaneProductos"); // NOI18N

        tablaProductos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
        tablaProductos.setToolTipText("");
        tablaProductos.setComponentPopupMenu(jPopupMenu1);
        tablaProductos.setName("tablaProductos"); // NOI18N
        tablaProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProductosMousePressed(evt);
            }
        });
        tablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductosKeyPressed(evt);
            }
        });
        jScrollPaneProductos.setViewportView(tablaProductos);

        jPanel1.add(jScrollPaneProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 38, 580, 330));

        jScrollPaneAlmacen.setName("jScrollPaneAlmacen"); // NOI18N

        tablaAlmacen.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlmacen.setName("tablaAlmacen"); // NOI18N
        tablaAlmacen.getTableHeader().setReorderingAllowed(false);
        tablaAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlmacenMouseClicked(evt);
            }
        });
        tablaAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaAlmacenKeyPressed(evt);
            }
        });
        jScrollPaneAlmacen.setViewportView(tablaAlmacen);

        jPanel1.add(jScrollPaneAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 374, 580, 115));

        txtTotalVenta.setEditable(false);
        txtTotalVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTotalVenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalVenta.setText("S/ 0.00");
        txtTotalVenta.setName("txtTotalVenta"); // NOI18N
        jPanel1.add(txtTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 440, 218, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel12.setText("Total venta: ");
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 440, -1, -1));

        txtSubTotalInafecto.setEditable(false);
        txtSubTotalInafecto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtSubTotalInafecto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotalInafecto.setText("S/ 0.00");
        txtSubTotalInafecto.setName("txtSubTotalInafecto"); // NOI18N
        txtSubTotalInafecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalInafectoActionPerformed(evt);
            }
        });
        jPanel1.add(txtSubTotalInafecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 410, 218, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setText("Vigencia (días):");
        jLabel13.setName("jLabel13"); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 110, -1));

        btnGuardar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-document-save-icon.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Registrar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 470, 116, -1));

        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton1.setMnemonic('S');
        jButton1.setText("Salir");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 470, 98, -1));

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setMnemonic('a');
        btnCancelar.setText("Cancelar");
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 470, 118, -1));

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 470, 105, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Desc%:");
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 50, -1));

        txtDescuento.setEditable(false);
        txtDescuento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtDescuento.setText("0.00");
        txtDescuento.setName("txtDescuento"); // NOI18N
        txtDescuento.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDescuentoCaretUpdate(evt);
            }
        });
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
        });
        jPanel1.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1077, 8, 70, -1));

        cboUnidad.setEnabled(false);
        cboUnidad.setName("cboUnidad"); // NOI18N
        cboUnidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadItemStateChanged(evt);
            }
        });
        cboUnidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboUnidadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboUnidadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cboUnidadMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboUnidadMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboUnidadMouseReleased(evt);
            }
        });
        cboUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUnidadActionPerformed(evt);
            }
        });
        cboUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboUnidadKeyPressed(evt);
            }
        });
        jPanel1.add(cboUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 8, 130, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Unidades");
        jLabel14.setName("jLabel14"); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Ingreso Directo por Código");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel15.setText("Sub total:");
        jLabel15.setName("jLabel15"); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 410, 79, -1));

        txtVigencia.setName("txtVigencia"); // NOI18N
        txtVigencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVigenciaKeyTyped(evt);
            }
        });
        jPanel1.add(txtVigencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 1320, 510));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Fecha: ");
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 49, 20));

        jdchFecha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jdchFecha.setName("jdchFecha"); // NOI18N
        getContentPane().add(jdchFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 110, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Cliente: ");
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 15, 52, -1));

        txtCliente.setEditable(false);
        txtCliente.setName("txtCliente"); // NOI18N
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 13, 596, -1));

        btnCliente.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        btnCliente.setText("F3");
        btnCliente.setName("btnCliente"); // NOI18N
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 11, 77, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("N° de Cotización:");
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 20));

        lblNumeroCotizacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNumeroCotizacion.setText("  ");
        lblNumeroCotizacion.setName("lblNumeroCotizacion"); // NOI18N
        getContentPane().add(lblNumeroCotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 11, 67, 20));

        buttonGroup1.add(ckGeneral);
        ckGeneral.setText("PP");
        ckGeneral.setName("ckGeneral"); // NOI18N
        ckGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckGeneralActionPerformed(evt);
            }
        });
        getContentPane().add(ckGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        buttonGroup1.add(ckPrecio);
        ckPrecio.setText("PM");
        ckPrecio.setName("ckPrecio"); // NOI18N
        ckPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(ckPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        buttonGroup1.add(ckPreciomayor);
        ckPreciomayor.setText("PC");
        ckPreciomayor.setName("ckPreciomayor"); // NOI18N
        ckPreciomayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckPreciomayorActionPerformed(evt);
            }
        });
        getContentPane().add(ckPreciomayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProductosDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosDetalleMouseClicked
        btnEditarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(true);
        if (evt.getClickCount() > 1 && !facturado) {
            cargarEditar();
        }
    }//GEN-LAST:event_tablaProductosDetalleMouseClicked

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        if (txtPrecio.getText().trim().length() == 0) {
            txtPrecio.grabFocus();
        }
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        control.decimal(evt, txtPrecio.getText());

    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPrecioCaretUpdate
      //  calcularCostoPorProducto();
        //photosmart D110
    }//GEN-LAST:event_txtPrecioCaretUpdate

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate
        
    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (txtCantidad.getText().trim().length() > 0)) {
            if (txtDescuento.getText().trim().length()==0){
                 txtDescuento.setText("0.00");
             }
            double dco=Double.parseDouble(txtDescuento.getText());
             
             
             if(dco<0 || dco>100){
             JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                     + " de 0 a 100");
             
             }
             else{
            int CantR=CantReal;
            double cant=Double.parseDouble(txtCantidad.getText());
            double prueba=0;
            prueba=CantR*cant;
                 
            if(prueba%1==0 && prueba>0){       
            agregarProducto();
                }
                 else{
                JOptionPane.showMessageDialog(this, "La Cantidad Asignada es Incorrecta");
                txtCantidad.requestFocus();
            }
                 
                 
                 
                    
                 
             }
            
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                        txtDescuento.requestFocus();
//                        txtDescuento.selectAll();
//                }
    }//GEN-LAST:event_txtPrecioKeyPressed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        seleccionarCliente();
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (!facturado) {
            eliminarLinea();
            // guardar();
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, no se pueden eliminar los productos", "Mensaje", 2);
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void txtBuscarDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDetalleKeyReleased
        //buscarProducto();
    }//GEN-LAST:event_txtBuscarDetalleKeyReleased

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        if (!facturado) {
            cargarEditar();
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta cotización, ya no se pueden modificar los productos");
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        producto = null;
        lblProducto.setText(" ");
        lblAlmacen.setText(" ");
        txtCantidad.setText("1.00");
        txtPrecio.setText("0.00");
        txtDescuento.setText("0");
        txtCantidad.setEditable(false);
        txtPrecio.setEditable(false);
        txtDescuento.setEditable(false);
        txtTotal.setEditable(false);
        tablaProductosDetalle.setEnabled(true);
        txtBuscarDetalle.setEnabled(true);
        txtBuscarDetalle.setText("");
        txtBuscarProducto.setText("");
        txtVigencia.setText("30");
        jScrollPaneProductos.setBorder(defaultBorder);
        jScrollPaneAlmacen.setBorder(defaultBorder);
        txtBuscarDetalle.requestFocus();
        cboUnidad.setSelectedIndex(0);
        cargarProductos0();
        //cancelar();
        //calcularTotal();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (cliente != null) {
            
            if(aux==0){
                guardar();
            JOptionPane.showMessageDialog(this, "La Cotización se ha guardado correctamente");
            if (listadoCotizaciones != null) {
                listadoCotizaciones.recargar();
            }
            cancelar();
            calcularTotal();
            }
            if(aux==1){
                editar();
            //guardar2()
                 JOptionPane.showMessageDialog(this, "La Cotización se ha modificado correctamente");
            if (listadoCotizaciones != null) {
                listadoCotizaciones.recargar();
            }
            cancelar();
             calcularTotal();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione el cliente para la venta");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        txtVigencia.setText("30");
        producto = null;
        lblProducto.setText(" ");
        lblAlmacen.setText(" ");
        txtCantidad.setText("1.00");
        txtPrecio.setText("0.00");
        txtDescuento.setText("0");
        txtCantidad.setEditable(false);
        txtPrecio.setEditable(false);
        txtDescuento.setEditable(false);
        txtTotal.setEditable(false);
        tablaProductosDetalle.setEnabled(true);
        txtBuscarDetalle.setEnabled(true);
        txtBuscarDetalle.setText("");
        txtBuscarProducto.setText("");
        jScrollPaneProductos.setBorder(defaultBorder);
        jScrollPaneAlmacen.setBorder(defaultBorder);
        txtBuscarDetalle.requestFocus();
        cboUnidad.setSelectedIndex(0);
        cargarProductos0();
        
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        control.decimal(evt, txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (indexPrecios == 0) {
                indexPrecios = 1;
                txtPrecio.setText("" + control.decimalFormat(precios[indexPrecios]));
            } else {
                indexPrecios = 0;
                txtPrecio.setText("" + control.decimalFormat(precios[indexPrecios]));
            }
        }
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (listadoCotizaciones != null) {
            listadoCotizaciones.recargar();
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void txtBuscarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (productosModel.getRowCount() > 0) {
                try {
                    jScrollPaneProductos.setBorder(focusBorder);
                    jScrollPaneAlmacen.setBorder(defaultBorder);
                    tablaProductos.getSelectionModel().setSelectionInterval(0, 0);
                    tablaProductos.requestFocus();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_txtBuscarProductoKeyPressed

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        
            cargarProductos();
        
    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (evt.getClickCount() >= 2) {
            seleccionarProducto();
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void tablaProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMousePressed
//        cargarPopup(evt)
    }//GEN-LAST:event_tablaProductosMousePressed

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tablaProductos.getSelectedRow() >= 0) {
            if (tablaAlmacen.getRowCount() == 0) {
                
                seleccionarProducto();
            } else {

                try {
                    tablaAlmacen.getSelectionModel().addSelectionInterval(0, 0);
                    jScrollPaneProductos.setBorder(defaultBorder);
                    jScrollPaneAlmacen.setBorder(focusBorder);
                } catch (Exception ex) {

                }
                tablaAlmacen.requestFocus();
                evt.consume();
            }

        }
    }//GEN-LAST:event_tablaProductosKeyPressed

    private void tablaAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlmacenMouseClicked
        if (tablaProductos.getSelectedRow() >= 0) {
            if (tablaAlmacen.getSelectedRow() >= 0 && evt.getClickCount() > 1) {
                seleccionarProducto();
            }
        }
    }//GEN-LAST:event_tablaAlmacenMouseClicked

    private void tablaAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAlmacenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            seleccionarProducto();

        }
    }//GEN-LAST:event_tablaAlmacenKeyPressed

    private void txtBuscarProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarProductoAncestorAdded
        //txtBuscarProducto.requestFocus();
    }//GEN-LAST:event_txtBuscarProductoAncestorAdded

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        idVenta = 0;
        facturado = false;
        guardado = false;
        cancelar();
        calcularTotal();
        btnCliente.setEnabled(true);
        jdchFecha.setEnabled(true);
        txtBuscarProducto.setText("");
        txtBuscarDetalle.setText("");
        cargarProductos();
        txtBuscarDetalle.requestFocus();
        btnGuardar.setEnabled(true);
        cboUnidad.setSelectedIndex(0);
        cargarProductos0();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscarProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProductoFocusGained
        jScrollPaneProductos.setBorder(defaultBorder);
        jScrollPaneAlmacen.setBorder(defaultBorder);
    }//GEN-LAST:event_txtBuscarProductoFocusGained

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
//        double costoProducto = 0;
//        double descuento = 0;
//        double cantidad = 1;
//
//        try {
//            cantidad = Double.parseDouble(txtCantidad.getText().replace(",", ""));
//        } catch (Exception e) {
//        }
//        try {
//            descuento = Double.parseDouble(txtDescuento.getText().replace(",", ""));
//        } catch (Exception e) {
//        }
//        if (cantidad < 1) {
//
//            txtPrecio.setText(control.decimalFormat(precios[0]));
//            costoProducto = precios[1];
//            costoProducto = costoProducto - descuento;
//            indexPrecios = 0;
//
//        } else {
//            if (!listaUnidades.contains(unidadDeMedidaProducto)) {
//                txtPrecio.setText(control.decimalFormat(precios[0]));
//                costoProducto = precios[0];
//                costoProducto = costoProducto - descuento;
//                indexPrecios = 0;
//            } else {
//                txtPrecio.setText(control.decimalFormat(precios[1]));
//                costoProducto = precios[1];
//                costoProducto = costoProducto - descuento;
//                indexPrecios = 1;
//            }
//        }
//        txtTotal.setText(control.decimalFormat(costoProducto * cantidad));
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusGained
//        double costoProducto = 0;
//        double descuento = 0;
//        double cantidad = 1;
//
//        try {
//            cantidad = Double.parseDouble(txtCantidad.getText().replace(",", ""));
//        } catch (Exception e) {
//        }
//        try {
//            costoProducto = Double.parseDouble(txtPrecio.getText().replace(",", ""));
//        } catch (Exception e) {
//        }
//        try {
//            descuento = Double.parseDouble(txtDescuento.getText().replace(",", ""));
//        } catch (Exception e) {
//        }
//        costoProducto = costoProducto - descuento;
//        txtTotal.setText(control.decimalFormat(costoProducto * cantidad));
    }//GEN-LAST:event_txtPrecioFocusGained

    private void txtDescuentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescuentoCaretUpdate
        
    }//GEN-LAST:event_txtDescuentoCaretUpdate

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
            if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (txtCantidad.getText().trim().length() > 0)) {
            if (txtDescuento.getText().trim().length()==0){
                 txtDescuento.setText("0.00");
             }
            double dco=Double.parseDouble(txtDescuento.getText());
             
             
             if(dco<0 || dco>100){
             JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                     + " de 0 a 100");
             
             }
             else{
                 agregarProducto();
             }
            
        }
    }//GEN-LAST:event_txtDescuentoKeyPressed

        private void txtSubTotalInafectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalInafectoActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_txtSubTotalInafectoActionPerformed

    private void ckPreciomayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckPreciomayorActionPerformed
        if(ckPreciomayor.isSelected()){
            filtro="precioCompra";
            cargarProductos();
        }
    }//GEN-LAST:event_ckPreciomayorActionPerformed

    private void ckGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckGeneralActionPerformed
        if(ckGeneral.isSelected()){
            filtro="precxmenor";
            cargarProductos();
        }
    }//GEN-LAST:event_ckGeneralActionPerformed

    private void ckPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckPrecioActionPerformed
        if(ckPrecio.isSelected()){
            filtro="precxmayo";
            cargarProductos();
        }
    }//GEN-LAST:event_ckPrecioActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
    calcularCostoPorProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
    calcularCostoPorProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoKeyReleased

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
      calcularCostoPorProducto1();   // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
    if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (txtCantidad.getText().trim().length() > 0)) {
            if (txtDescuento.getText().trim().length()==0){
                 txtDescuento.setText("0.00");
             }
            double dco=Double.parseDouble(txtDescuento.getText());
             
             
             if(dco<0 || dco>100){
             JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                     + " de 0 a 100");
             
             }
             else{
                 agregarProducto();
                    
                 
             }
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyPressed

    private void txtTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTotalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalCaretUpdate

    private void cboUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadItemStateChanged
        //cargarProductos(); // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadItemStateChanged

    private void cboUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseClicked
        // cargarProductos();   // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseClicked

    private void cboUnidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseEntered
        //cargarProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseEntered

    private void cboUnidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseExited
        //cargarProductos();    // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseExited

    private void cboUnidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMousePressed
        //cargarProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMousePressed

    private void cboUnidadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseReleased
        //cargarProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseReleased

    private void cboUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUnidadActionPerformed
        cargarProductos();
        //JOptionPane.showMessageDialog(null, dato);// TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadActionPerformed

    private void cboUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboUnidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadKeyPressed

    private void txtVigenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVigenciaKeyTyped
      control.Solonumeros(evt);   // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaKeyTyped

    private void txtBuscarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDetalleKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //JOptionPane.showMessageDialog(null, txtBuscarProductoDetalle.getText());
        PasarDirecto();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDetalleKeyPressed

    private void txtBuscarDetalleAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarDetalleAncestorAdded
    txtBuscarDetalle.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDetalleAncestorAdded

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboUnidad;
    private javax.swing.JCheckBox ckGeneral;
    private javax.swing.JCheckBox ckPrecio;
    private javax.swing.JCheckBox ckPreciomayor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPaneAlmacen;
    private javax.swing.JScrollPane jScrollPaneDetalles;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private com.toedter.calendar.JDateChooser jdchFecha;
    private javax.swing.JLabel lblAlmacen;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblNumeroCotizacion;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JTable tablaAlmacen;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaProductosDetalle;
    private javax.swing.JTextField txtBuscarDetalle;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSubTotalInafecto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalVenta;
    private javax.swing.JTextField txtVigencia;
    // End of variables declaration//GEN-END:variables
void setListadoCotizaciones(FrmListadoCotizaciones listadoCotizaciones) {
        this.listadoCotizaciones = listadoCotizaciones;
    }
    public void cancelar() {
        lblNumeroCotizacion.setText(" " + control.nextId("cotizacion"));
        txtCliente.setText("");
        lblProducto.setText(" ");
        txtPrecio.setText("0.00");
        txtCantidad.setText("1");
        txtDescuento.setText("0");
        cliente = null;
        aux=0;
        
        control.LimTabla(modeloProductoDetalle);
        txtBuscarDetalle.setText("");
        jdchFecha.setDate(new Date());
        
        producto = null;
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        txtPrecio.setEditable(false);
        txtDescuento.setEditable(false);
        txtCantidad.setEditable(false);
        txtTotal.setEditable(false);
        jScrollPaneProductos.setBorder(defaultBorder);
        jScrollPaneAlmacen.setBorder(defaultBorder);
        if (!guardado) {
            idVenta = 0;
        }
        if (idVenta != 0) {
            cargarVenta();
        } else {
            setClientePorDefecto();

        }
    }
    
    void setIdCot(int idCot) {
        this.idCot = idCot;
        lblNumeroCotizacion.setText(String.valueOf(idCot));
       
        CargarCabecera();
        calcularTotal();
    }
    private void CargarCabecera() {
        control.Sql = "select c.idcliente,cl.nomcte, date_format(c.fecCot,'%d/%m/%Y')\n" +
                        "from cotizacion c\n" +
                        "inner join cliente cl on cl.idcliente=c.idcliente\n" +
                        " where c.idcotizacion='"+idCot+"';";
        System.out.println(control.Sql);
        String[] cargarRegistro = control.cargarRegistro(control.Sql, 3);
        if (cargarRegistro != null) {
            cliente = cargarRegistro[0];
            txtCliente.setText(cargarRegistro[1]);
            ((JTextComponent) (jdchFecha.getDateEditor())).setText(cargarRegistro[2]);
            aux=1;
            CargarDetalle();
           /* jLabel5.setText(cargarRegistro[1]);
           /* idProveedor = cargarRegistro[0];
            lblNum.setText(cargarRegistro[2]);
            txtBuscarProveedor.setText(cargarRegistro[1]);
            BuscarProveedor();
            CargarDetalleOrden();*/
        }
    }
    private void CargarDetalle() {
        control.Sql = "SELECT d.`iddetallecotizacion`, d.`idProducto`,v.`Codigo`, concat(v.`Producto`,' ',v.`Marca`), d.`Unidad`, d.`cantidad`, round(d.`precio`,2),\n" +
"                round((d.cantidad*d.precio),2),a.`idAlmacen`,d.`cantReal`, a.`nombre`\n" +
"                FROM detallecotizacion d, vta_catalogo v, almacen a\n" +
"               WHERE v.`ID`=d.`idProducto` and a.idAlmacen='1'\n" +
"                AND d.`idcotizacion`="+idCot+" ORDER BY v.`Producto`;";
        control.LlenarJTabla(modeloProductoDetalle, control.Sql, 11);
        calcularTotal();

    }
    
     private void calcularCostoPorProducto1() {
        double precioTotal = 0;
        double descuento = 0;
        double cantidad = 1;

        try {
            cantidad = Double.parseDouble(txtCantidad.getText().replace(",", ""));
            
        } catch (NumberFormatException e) {

        }
        try {
            precioTotal = Double.parseDouble(txtTotal.getText().replace(",", ""));
        } catch (NumberFormatException e) {
        }
        try {
            descuento = Double.parseDouble(txtDescuento.getText().replace(",", ""));
            //descuento=(descuento*precioProducto)/100;
            descuento=descuento/100;
        } catch (NumberFormatException e) {

        }

        //precioProducto = precioProducto - descuento;
        precioTotal=precioTotal/cantidad;
        

        txtPrecio.setText(control.decimalFormat(precioTotal/(1-descuento)));
        
    }
    
    

    private void calcularCostoPorProducto() {
        double precioProducto = 0;
        double descuento = 0;
        double cantidad = 1;

        try {
            cantidad = Double.parseDouble(txtCantidad.getText().replace(",", ""));
        } catch (NumberFormatException e) {

        }
        try {
            precioProducto = Double.parseDouble(txtPrecio.getText().replace(",", ""));
        } catch (NumberFormatException e) {
        }
        try {
            descuento = Double.parseDouble(txtDescuento.getText().replace(",", ""));
            descuento=(descuento*precioProducto)/100;
        } catch (NumberFormatException e) {

        }

        precioProducto = precioProducto - descuento;

        txtTotal.setText(control.decimalFormat(precioProducto * cantidad));
    }

    private void agregarProducto() {
        if (txtPrecio.getText().trim().length() > 0) {
            if (txtCantidad.getText().trim().length() > 0) {
                if (Double.parseDouble(txtPrecio.getText()) > 0) {
                    if (Double.parseDouble(txtCantidad.getText()) > 0) {
                       
                       
                        double descuento = 0;
                        double costoProducto = 0;
                        double cantidad = 1, total=1;
                        
                        
                        costoProducto = Double.parseDouble(txtPrecio.getText().replace(",", ""));
                        descuento = Double.parseDouble(txtDescuento.getText().replace(",", ""));
                        double cant=Double.parseDouble(txtCantidad.getText());
                        cantidad = cant;
                        
                        descuento=(descuento*costoProducto)/100;
                        
                        double stockDisponible = getStockDisponible(producto, idAlmacen);
                        
                         if (stockDisponible - (cantidad*CantReal) >= 0) {
                         
                             agregarDetalleLista(producto, idAlmacen, cantidad*CantReal); 
                             
                              if (!editando) {
                                  System.out.println("diferente de editando");
                                  int index = buscar(producto, idAlmacen,Unidad);
                                  if (index < 0) {
                                    System.out.println("insertando normal");
                                    modeloProductoDetalle.addRow(new String[]{"",producto, codProd, lblProducto.getText(), cboUnidad.getSelectedItem().toString(), String.valueOf(cantidad),control.decimalFormat(costoProducto), control.decimalFormat(costoProducto * cantidad), idAlmacen,String.valueOf(CantReal)});
                                  }
                                  else {
                                      System.out.println("editando");
                                      double cantidadAnterior = Double.parseDouble(modeloProductoDetalle.getValueAt(index, 5).toString());
                                      
                                        modeloProductoDetalle.setValueAt(control.decimalFormat(costoProducto), index, 6);
                                        modeloProductoDetalle.setValueAt(String.valueOf(cantidadAnterior + cantidad), index, 5);
                                        modeloProductoDetalle.setValueAt(control.decimalFormat(costoProducto * (cantidadAnterior + cantidad)), index, 7);
                                  }
                              }
                              else {
                                  System.out.println("editando normal");
                                  int index = tablaProductosDetalle.getSelectedRow();
                                  
                                  modeloProductoDetalle.setValueAt(cantidad, index, 5);
                                  modeloProductoDetalle.setValueAt(idAlmacen, index, 8);
                                  modeloProductoDetalle.setValueAt((cantidad*costoProducto), index, 7);
                                btnEditarProducto.setEnabled(true);
                                btnEliminarProducto.setEnabled(true);
                                txtBuscarDetalle.setEditable(true);
                                tablaProductosDetalle.setEnabled(true);
                                cboUnidad.setEnabled(true);
                              }
                            editando = false;
                            lblProducto.setText(" ");
                            txtPrecio.setText("0.00");
                            txtCantidad.setText("1");
                            txtDescuento.setText("0");

                            txtTotal.setText("0.00");
                            txtPrecio.setEditable(false);
                            txtDescuento.setEditable(false);
                            txtCantidad.setEditable(false);
                            txtTotal.setEditable(false);
                            txtBuscarDetalle.setText("");
                            txtBuscarDetalle.requestFocus();
                            cboUnidad.setEnabled(true);
                            calcularTotal();
                         
                         }
                        else {
                            JOptionPane.showMessageDialog(this, "Cantidad requerida por debajo del stock.\nStock disponible " + control.decimalFormat(stockDisponible)+" Unidades", "Mensaje", JOptionPane.WARNING_MESSAGE);
                            txtCantidad.selectAll();
                            txtCantidad.grabFocus();
                        }
                                             
                    

                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad no puede ser cero");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El precio del producto debe de ser mayor a cero");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Ingrese la cantidad de productos");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese el precio del producto");
        }
    }

   public int buscar(String producto, String almacen, String unidad) {
        for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
            if (modeloProductoDetalle.getValueAt(i, 1).toString().equals(producto)&& modeloProductoDetalle.getValueAt(i, 4).toString().equals(unidad) ) {

                return i;
            }
        }
        return -1;
    }
     public int buscar1(String producto, String almacen) {
        for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
            if (modeloProductoDetalle.getValueAt(i, 1).toString().equals(producto) && modeloProductoDetalle.getValueAt(i, 8).toString().equals(almacen)) {

                return i;
            }
        }
        return -1;
    }

    private void cargarEditar() {
        
        editando = true;
        txtBuscarDetalle.setEditable(false);
        tablaProductosDetalle.setEnabled(false);
        int fila = tablaProductosDetalle.getSelectedRow();
        
        producto = modeloProductoDetalle.getValueAt(fila, 1).toString();
        lblAlmacen.setText(modeloProductoDetalle.getValueAt(fila, 10).toString());
        lblProducto.setText(modeloProductoDetalle.getValueAt(fila, 3).toString());
        txtCantidad.setText(modeloProductoDetalle.getValueAt(fila, 5).toString());    
        double cant=Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 5).toString())*Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 9).toString());
        cantidadtemporal=(int)(cant);
        
        double costo=Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 6).toString());
        txtPrecio.setText(String.valueOf(costo));
        idAlmacen = modeloProductoDetalle.getValueAt(fila, 8).toString();
        CantReal= Integer.parseInt(modeloProductoDetalle.getValueAt(fila, 9).toString());
        precios[0] = Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 6).toString())/Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 9).toString());
        precios[1] = Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 6).toString());
         txtTotal.setText(String.valueOf(costo*Double.parseDouble(modeloProductoDetalle.getValueAt(fila, 5).toString())));
        indexPrecios = 1;
        txtCantidad.setEditable(true);
        
        txtCantidad.selectAll();
        txtCantidad.grabFocus();
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        calcularCostoPorProducto();
        cboUnidad.setEnabled(false);
        
       
        //modeloProductoDetalle.removeRow(tablaProductosDetalle.getSelectedRow());
        txtCantidad.requestFocus();
    }

    private void guardar() {
        
        String dias=txtVigencia.getText();
        String fecha = control.Formato_Amd(jdchFecha.getDate() == null ? new Date() : jdchFecha.getDate());
        control.Sql = String.format("INSERT INTO cotizacion VALUES(NULL,'%s','%s','%s','%s');", fecha,dias, cliente, Accesos.getInstance().getIdUsuario());
        int idcotizacion = control.executeAndGetId(control.Sql);
        registrarDetalleCotizacion(idcotizacion);
        ImprimirCotizacion(idcotizacion);

    }
    
    
    private void editar(){
        String dias=txtVigencia.getText();
         String fecha = control.Formato_Amd(jdchFecha.getDate() == null ? new Date() : jdchFecha.getDate());
         control.Sql = String.format("update cotizacion set fecCot='%s', idCliente='%s',diasDur='%s' where idcotizacion='%s';", fecha, cliente,dias, idCot);
         control.CrearRegistro(control.Sql);
         
         String sql = String.format("delete from detallecotizacion where idcotizacion='%s';", idCot);
         control.CrearRegistro(sql);
         
         registrarDetalleCotizacion1(idCot);
         ImprimirCotizacion(idCot);
    }
    
    private void registrarDetalleCotizacion1(int idcotizacion) {

        for (int i = 0; i < tablaProductosDetalle.getRowCount(); i++) {
            control.Sql = String.format("INSERT INTO detallecotizacion VALUES(NULL,'%s','%s','%s','%s','%s','%s');", tablaProductosDetalle.getValueAt(i, 5).toString(), tablaProductosDetalle.getValueAt(i, 6).toString(), idcotizacion, tablaProductosDetalle.getValueAt(i, 1).toString(), tablaProductosDetalle.getValueAt(i, 9).toString(), tablaProductosDetalle.getValueAt(i, 4).toString());
            System.out.println(String.format("INSERT INTO detallecotizacion VALUES(NULL,'%s','%s','%s','%s','%s','%s');", tablaProductosDetalle.getValueAt(i, 5).toString(), tablaProductosDetalle.getValueAt(i, 6).toString(), idcotizacion, tablaProductosDetalle.getValueAt(i, 1).toString(), tablaProductosDetalle.getValueAt(i, 9).toString(), tablaProductosDetalle.getValueAt(i, 4).toString()));
            control.CrearRegistro(control.Sql);
        }

    }
    
    
    

    private void calcularTotal() {
        double subTotalInafecto = 0;
//                double subTotalAfecto = 0;
        for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
            subTotalInafecto += Double.parseDouble(modeloProductoDetalle.getValueAt(i, 7).toString().replace(",", ""));
//                        subTotalAfecto += Double.parseDouble(modeloProductoDetalle.getValueAt(i, 8).toString().replace(",", ""));
        }
        txtSubTotalInafecto.setText("S/ " + control.decimalFormat(subTotalInafecto));
//                txtSubtotalAfecto.setText("S/ " + control.decimalFormat(subTotalAfecto));
        txtTotalVenta.setText("S/ " + control.decimalFormat(subTotalInafecto));
    }

    private void seleccionarProducto() {

        if (!facturado) {

            if (cliente != null) {
                int row = tablaProductos.getSelectedRow();
                datos = new String[12];
                datos[0] = productosModel.getValueAt(row, 0).toString();//id
                datos[1] = productosModel.getValueAt(row, 1).toString();//Codigo producto
                datos[2] = productosModel.getValueAt(row, 2).toString();//Codigo Unidad
                datos[3] = productosModel.getValueAt(row, 3).toString();//Producto
                datos[4] = productosModel.getValueAt(row, 4).toString();//marca
                datos[5] = productosModel.getValueAt(row, 5).toString();//nombre de Unidades
                datos[6] = productosAlmacenModel.getRowCount() > 0 ? productosAlmacenModel.getValueAt(tablaAlmacen.getSelectedRow(), 0).toString() : null;//id almacen
                datos[7] = productosAlmacenModel.getRowCount() > 0 ? productosAlmacenModel.getValueAt(tablaAlmacen.getSelectedRow(), 1).toString() : null;//almacen
                datos[8] = productosModel.getValueAt(row, 6).toString();//precio de compra unitario
                datos[9] = productosModel.getValueAt(row, 7).toString();//cantidad
                datos[10] = productosModel.getValueAt(row, 8).toString();//precio de Compra segun Unidad
                datos[11] = productosAlmacenModel.getRowCount() > 0 ? productosAlmacenModel.getValueAt(tablaAlmacen.getSelectedRow(), 3).toString() : "0";//stock disponible
                unidadDeMedidaProducto = datos[5];
                if (datos != null) {
                  /*  producto = datos[0];
                    CantReal = Integer.parseInt(datos[9]);
                    lblProducto.setText(datos[5]+' '+datos[3]);
                    idAlmacen = datos[6];
                    StockReal = datos[11];
                    precios[0] = Double.parseDouble(datos[8]);//precio por unidad
                    precios[1] = Double.parseDouble(datos[10]);//precio por volumen
                    indexPrecios = 0;*/
                    producto = datos[0];
                    codProd= datos[1];
                    Unidad=datos[5];
                    lblProducto.setText(datos[3]+" "+datos[4]);
                    idAlmacen = datos[6];
                    Almacen =datos[7];
                    CantReal=Integer.parseInt(datos[9]);
                    precios[0] = Double.parseDouble(datos[8]);//precio por Unidad
                    precios[1] = Double.parseDouble(datos[10]);//precio por Volumen Seleccionado
                    indexPrecios = 0;
                    
                   /* if (datos[6] != null) {

                        String precio = "0.00";
                        if (!listaUnidades.contains(datos[2])) {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        } else {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        }
                        txtPrecio.setEditable(false);
                        txtDescuento.setEditable(true);
                        txtCantidad.setEditable(true);
                        txtTotal.setEditable(true);
                        txtCantidad.setText("1.00");
                        lblAlmacen.setText(datos[7]);
                        int buscar = buscar1(producto, idAlmacen);
                        if (buscar >= 0) {
                            txtPrecio.setText(modeloProductoDetalle.getValueAt(buscar, 5).toString());
                        } else {
                            txtPrecio.setText(precio);
                        }
                        txtCantidad.grabFocus();
                        txtCantidad.selectAll();

                    }*/
                    if (datos[6] != null) {
                        
                        String precio = "0.00";
                        /*if (!listaUnidades.contains(datos[2])) {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        } else {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        }*/
                        precio = control.decimalFormat(precios[1]);
                        
                        txtPrecio.setEditable(false);
                        //txtDescuento.setEditable(true);
                        txtCantidad.setEditable(true);
                        txtCantidad.setText("1");
                        //txtTotal.setEditable(true);
                        lblAlmacen.setText(datos[7]);
                        int buscar = buscar(producto, idAlmacen,Unidad);
                        if (buscar >= 0) {
                            txtPrecio.setText(precio);
                            txtTotal.setText(precio);
                            //txtPrecio.setText(modeloProductoDetalle.getValueAt(buscar, 8).toString());
                        } else {
                            txtPrecio.setText(precio);
                           txtTotal.setText(precio);
                        }
                        txtCantidad.grabFocus();
                        txtCantidad.selectAll();

                    }
                    else {
                        JOptionPane.showMessageDialog(this, "El producto " + datos[2] + " no tiene existencias en ninguno de los almacenes", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        btnCancelarProducto.doClick();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione del cliente para la venta", "Mensaje", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, ya no se pueden agregar nuevos productos", "Mensaje", 2);
        }
    }

    private void PasarDirecto(){
    String text=txtBuscarDetalle.getText().trim();
    
    if (!facturado) {

            if (cliente != null) {
                
                  control.Sql="select '',p.idproduto, p.codprod, concat(p.nomproducto,' ',m.nommarc ),u.nomuniddes,'1' as cantidad\n" +
"                , IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad),\n" +
"                 IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad),\n" +
"                 '1'\n" +
"                , u.cantidad, '1' as idAlmacen , IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'General') as 'Codigo Unidad'\n" +
",(Select s.idAlmacen from stock s where s.idProducto=p.idproduto)\n" +
",(Select a.nombre from stock s inner join almacen a on a.idAlmacen=s.idAlmacen where s.idProducto=p.idproduto)\n" +  
" ,IFNULL((Select s.cantidadDisponible from stock s where s.idProducto=p.idproduto),'0') from produto p\n" +
"                inner join marca m on p.idmarca=m.idmarca,\n" +
"                unidades u\n" +
"                where p.codProd='"+text+"' group by p.idproduto\n" +
"                union\n" +
"                select '',p.idproduto, p.codprod,concat(p.nomproducto,' ',m.nommarc ), u.nomuniddes,'1' as cantidad\n" +
"                , p.precUnidad \n" +
"                ,IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad),\n" +
"                 '1'\n" +
"                , u.cantidad, '1' as idAlmacen , de.codUnid as 'Codigo Unidad'\n" +
",(Select s.idAlmacen from stock s where s.idProducto=p.idproduto)\n" +
",(Select a.nombre from stock s inner join almacen a on a.idAlmacen=s.idAlmacen where s.idProducto=p.idproduto)\n" +                         
",IFNULL((Select s.cantidadDisponible from stock s where s.idProducto=p.idproduto),'0') from produto p\n" +
"                inner join marca m on p.idmarca=m.idmarca,\n" +
"                unidades u\n" +
"                inner join detunidprod de on de.idUnidades=u.idUnidades\n" +
"                where de.idProducto=p.idproduto and de.codUnid='"+text+"';";
    
    //control.LlenarJTabla(modeloProductoDetalle,control.Sql, 10);
    
    String[] cargarRegistro = control.cargarRegistro(control.Sql, 15);
    if(cargarRegistro==null){
    
        JOptionPane.showMessageDialog(null, "Código de Producto No Registrado.");
    }    
    else{
        
        int row = tablaProductos.getSelectedRow();
                datos = new String[12];
                datos[0] =cargarRegistro[1];//id
                datos[1] = cargarRegistro[2];//Codigo producto
                datos[2] = cargarRegistro[11];//Codigo Unidad
                datos[3] = cargarRegistro[3];//Producto y Marca
                datos[4] = cargarRegistro[3];//marca
                datos[5] = cargarRegistro[4];//nombre de Unidades
                datos[6] = cargarRegistro[12];//id almacen
                datos[7] = cargarRegistro[13];//almacen
                datos[8] = cargarRegistro[6];//precio de compra unitario
                datos[9] = cargarRegistro[9];//cantidad Real
                datos[10] = cargarRegistro[7];//precio de Compra segun Unidad
                datos[11] = cargarRegistro[14];//stock disponible
                unidadDeMedidaProducto = datos[5];
                editando=false;
                if (datos != null) {
                    producto = datos[0];
                    codProd= datos[1];
                    Unidad=datos[5];
                    lblProducto.setText(datos[3]);
                    idAlmacen = datos[6];
                    Almacen =datos[7];
                    CantReal=Integer.parseInt(datos[9]);
                    precios[0] = Double.parseDouble(datos[8]);//precio por Unidad
                    precios[1] = Double.parseDouble(datos[10]);//precio por Volumen Seleccionado
                    indexPrecios = 0;
                    if (datos[6].length()>0) {
                        
                        String precio = "0.00";
                        /*if (!listaUnidades.contains(datos[2])) {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        } else {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        }*/
                        precio = control.decimalFormat(precios[1]);
                        
                        //txtPrecio.setEditable(false);
                        //txtDescuento.setEditable(true);
                        //txtCantidad.setEditable(true);
                        txtCantidad.setText("1");
                        //txtTotal.setEditable(true);
                        lblAlmacen.setText(datos[7]);
                        int buscar = buscar(producto, idAlmacen,Unidad);
                        if (buscar >= 0) {
                            txtPrecio.setText(precio);
                            txtTotal.setText(precio);
                            //txtPrecio.setText(modeloProductoDetalle.getValueAt(buscar, 8).toString());
                        } else {
                            txtPrecio.setText(precio);
                           txtTotal.setText(precio);
                        }
                        

agregarProducto();
System.out.println("2");
System.out.println("3");

                    } else {
                        JOptionPane.showMessageDialog(this, "El producto " + datos[2] + " no tiene existencias en ninguno de los almacenes", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        btnCancelarProducto.doClick();
                    }
                }
          
    }  
  
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione del cliente para la venta", "Mensaje", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, ya no se pueden agregar nuevos productos", "Mensaje", 2);
        }
    txtBuscarDetalle.setText("");
    txtBuscarDetalle.requestFocus();
    }
    private void eliminarLinea() {

        modeloProductoDetalle.removeRow(tablaProductosDetalle.getSelectedRow());
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        calcularTotal();

    }

    private int buscarLista(String producto, String almacen) {
        for (int i = 0; i < listaVenta.size(); i++) {
            double[] ds = listaVenta.get(i);
            if ((int) ds[0] == Integer.parseInt(producto) && (int) ds[1] == Integer.parseInt(almacen)) {
                return i;
            }

        }
        return -1;
    }

    private double getStockDisponible(String producto, String almacen) {

        double stock = Double.parseDouble(control.DevolverRegistroDto(String.format("SELECT `cantidadDisponible` FROM `stock` WHERE `idAlmacen`=%s AND `idProducto`=%s;", almacen, producto), 1));

        return stock;//- cantidad;
    }

    private void agregarDetalleLista(String producto, String idAlmacen, double cantidad) {
        int buscarLista = buscarLista(producto, idAlmacen);
        if (buscarLista >= 0) {

            double[] get = listaVenta.get(buscarLista);
            get[2] += cantidad;

            if (editando) {
                double tmpCant = Double.parseDouble(modeloProductoDetalle.getValueAt(tablaProductosDetalle.getSelectedRow(), 5).toString())*Double.parseDouble(modeloProductoDetalle.getValueAt(tablaProductosDetalle.getSelectedRow(), 9).toString());
                get[2] -= tmpCant;
            }
            listaVenta.set(buscarLista, get);
        } else {
            listaVenta.add(new double[]{Double.parseDouble(producto), Double.parseDouble(idAlmacen), cantidad});
        }

    }

    private final Action agregarClienteAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seleccionarCliente();
        }
    };

    /**
     * Abre el formulario para seleccionar el cliente
     */
    private void seleccionarCliente() {
        if (seleccionarCliente == null) {
            seleccionarCliente = new SeleccionarClienteDialog(null, true);
        }
        seleccionarCliente.setLocationRelativeTo(this);
        seleccionarCliente.setVisible(true);
        String[] datosCliente = seleccionarCliente.getDatos();
        if (datosCliente != null) {
            cliente = datosCliente[0];
            txtCliente.setText(datosCliente[2]);
        }
    }

 

    private void cargarVenta() {
        
    }

    private void setClientePorDefecto() {
        String[] cargarRegistro = control.cargarRegistro("SELECT `idCliente`, `nomcte`, `dniRuc`, `direc`  FROM `cliente` WHERE `idCliente`=1;", 4);
        if (cargarRegistro != null) {
            cliente = cargarRegistro[0];
            txtCliente.setText(cargarRegistro[1]);
        }
    }

    private void buscarProducto() {
        TableRowSorter rowSorter = new TableRowSorter(modeloProductoDetalle);
        RowFilter filter = RowFilter.regexFilter("(?i)" + txtBuscarDetalle.getText(), 1);
        rowSorter.setRowFilter(filter);
        tablaProductosDetalle.setRowSorter(rowSorter);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
    }
    
    private void cargarProductos0() {
        String text = txtBuscarProducto.getText();
        String dato="Unidad";
        control.Sql = "select p.idproduto, p.codProd,\n" +
        "IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'')\n" +
        ",p.nomproducto,m.nommarc,u.nomuniddes,p.precUnidad,u.cantidad,\n" +
        "IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad)\n" +
        ",(SUM(s.`cantidadDisponible`)< p.stockminimo) AS bajoDeStock\n" +
        "from produto p\n" +
        "inner join marca m on p.idmarca=m.idmarca\n" +
        "inner join stock s on s.idproducto=p.idproduto,\n" +
        "unidades u\n" +
        "where u.nomuniddes='"+dato+"'\n" +
        "and (p.codProd like '%"+text+"%' or p.codUnid like '%"+text+"%' or p.nomproducto like '%"+text+"%' or m.nommarc like '%"+text+"%')\n" +
        "GROUP BY s.`idProducto`\n" +
        "order by p.nomproducto;";
        control.LlenarJTablaSinUnid(productosModel, control.Sql, 10);
        control.LimTabla(productosAlmacenModel);

//        selectedRow = -1;
    }
    
    private void cargarProductos() {
        String text = txtBuscarProducto.getText();
        String dato=cboUnidad.getSelectedItem().toString();
        control.Sql = "select p.idproduto, p.codProd,\n" +
        "IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'')\n" +
        ",p.nomproducto,m.nommarc,u.nomuniddes,p.precUnidad,u.cantidad,\n" +
        "IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad)\n" +
        ",(SUM(s.`cantidadDisponible`)< p.stockminimo) AS bajoDeStock\n" +
        "from produto p\n" +
        "inner join marca m on p.idmarca=m.idmarca\n" +
        "inner join stock s on s.idproducto=p.idproduto,\n" +
        "unidades u\n" +
        "where u.nomuniddes='"+dato+"'\n" +
        "and (p.codProd like '%"+text+"%' or p.codUnid like '%"+text+"%' or p.nomproducto like '%"+text+"%' or m.nommarc like '%"+text+"%')\n" +
        "GROUP BY s.`idProducto`\n" +
        "order by p.nomproducto;";
        control.LlenarJTablaSinUnid(productosModel, control.Sql, 10);
        control.LimTabla(productosAlmacenModel);

//        selectedRow = -1;
    }
    private void cargarProductosFactura() {
        String text = txtBuscarProducto.getText();
         control.Sql = "SELECT v.`ID`,v.`Codigo`, v.`Producto`,v.`Descripcion`, v.`Marca`, v.`Unidad`,"+filtro+" , 0.00, 0.00, (SUM(s.`cantidadDisponible`)<= v.`StockMinimo`) AS bajoDeStock  "
                + "FROM vta_catalogo v, stock s "
                + "WHERE s.`idProducto`=v.`ID` AND "
                + "(v.`Codigo` Like '%" + text + "%' OR v.`Producto` LIKE '%" + text + "%' OR v.`Descripcion` LIKE '%" + text + "%' OR v.`Marca`  LIKE '%" + text + "%' OR v.`Unidad` LIKE '%" + text + "%') "
                + "GROUP BY s.`idProducto` ORDER BY v.`Producto`;";
        control.LlenarJTabla(productosModel, control.Sql, 10);
        control.LimTabla(productosAlmacenModel);

//        selectedRow = -1;
    }

    private void cargarPopup(MouseEvent evt) {
        try {
            Point point = evt.getPoint();
            int currentRow = tablaProductos.rowAtPoint(point);
            tablaProductos.setRowSelectionInterval(currentRow, currentRow);
            jPopupMenu1.removeAll();

            String idProducto = productosModel.getValueAt(currentRow, 0).toString();
            String productosContenidos = buscarProductoContenidoMenuItem(idProducto, "1");
            String[] lineas = productosContenidos.split("@@");

            for (int i = 0; i < lineas.length; i++) {
                String[] dts = lineas[i].split("##");
                System.out.println(Arrays.asList(dts));
                if (dts.length >= 4) {
                    JMenuItem item = new JMenuItem();
                    item.setName("item-" + idProducto + "-" + dts[0] + "-" + dts[1]);
                    item.setText("Covertir en " + dts[1] + " " + dts[2] + "s de " + dts[3]);
                    item.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JMenuItem jmi = (JMenuItem) e.getSource();
                            String[] split = jmi.getName().split("-");
                            int filaAlmacen = tablaAlmacen.getSelectedRow();
                            if (filaAlmacen >= 0) {
                                double cant = Double.parseDouble(productosAlmacenModel.getValueAt(filaAlmacen, 2).toString());
                                if (cant > 0) {
                                    control.ejecutar(String.format("INSERT IGNORE INTO stock VALUES(%s,%s,0);", productosAlmacenModel.getValueAt(filaAlmacen, 0).toString(), split[2]));
                                    control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;", split[3], productosAlmacenModel.getValueAt(filaAlmacen, 0).toString(), split[2]));
                                    control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-1 WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;", productosAlmacenModel.getValueAt(filaAlmacen, 0).toString(), split[1]));
                                    int filaProductos = tablaProductos.getSelectedRow();
                                    //cargarProductos();
                                    
                                        cargarProductos();
                                    
                                    tablaProductos.setRowSelectionInterval(filaProductos, filaProductos);
                                } else {
                                    JOptionPane.showMessageDialog(FrmRegistrarCotizacion.this, "Este producto no se puede abrir ya que no tiene existencias en stock", "Mensaje", 2);
                                }
                            }

                        }
                    });
                    jPopupMenu1.add(item);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void leearUnidades() {
        listaUnidades = control.getListData("SELECT u.`nomuniddes` FROM unidades u WHERE u.`pormayor`=true;");

    }

    private String buscarProductoContenidoMenuItem(String id, String cant) {

        String str = "";
        String[] des = control.cargarRegistro(String.format("SELECT p.`Idproductocont`,p.`CantCont`*" + cant + ", lower(v.`Unidad`),concat(v.`Producto`,' ',v.`Descripcion`) FROM vta_catalogo v, produto p "
                + "WHERE p.`Idproductocont`=v.`ID` AND p.`idProduto`=%s;", id), 4);

        if (des != null) {
            str += des[0] + "##" + des[1] + "##" + des[2] + "##" + des[3];
            if (des[0] == null || "".equals(des[0]) || "0".equals(des[0])) {
                return "";
            } else {
                str += "@@" + buscarProductoContenidoMenuItem(des[0], des[1]);
            }

        } else {
            return "";
        }
        if (str.endsWith("@@")) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    private String buscarProductoContenido(String id, String cant) {
        String str = "";
        String[] des = control.cargarRegistro(String.format("SELECT p.`Idproductocont`,p.`CantCont`*" + cant + ", CONCAT('Contiene <b>',p.`CantCont`*" + cant + ",'</b> ',lower(v.`Unidad`),'s de <i>',concat(v.`Producto`),'</i>') "
                + "FROM vta_catalogo v, produto p "
                + "WHERE p.`Idproductocont`=v.`ID` AND p.`idProduto`=%s;", id), 3);
//        System.out.println("SELECT p.`Idproductocont`,p.`CantCont`*" + cant + ", CONCAT('Contiene <b>',p.`CantCont`*" + cant + ",'</b> ',lower(v.`Unidad`),'s de <i>',v.`Descripcion`,'</i>') "
//                + "FROM vta_catalogo v, produto p "
//                + "WHERE p.`Idproductocont`=v.`ID` AND p.`idProduto`=%s;");
        if (des != null) {
            str += des[2];
            if (des[0] == null || "".equals(des[0]) || "0".equals(des[0])) {
                return "";
            } else {
                str += "\n" + buscarProductoContenido(des[0], des[1]);
            }

        } else {
            return "";
        }
        if (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void actualizarPrevio() {
        int row = tablaProductosDetalle.getSelectedRow();
        String id = modeloProductoDetalle.getValueAt(row, 0).toString();
        int buscarLista = buscarLista(modeloProductoDetalle.getValueAt(row, 1).toString(), modeloProductoDetalle.getValueAt(row, 9).toString());
        if (buscarLista >= 0) {
            double[] get = listaVenta.get(buscarLista);
            get[2] = get[2] - Double.parseDouble(modeloProductoDetalle.getValueAt(row, 4).toString());
            listaVenta.set(buscarLista, get);
        }
        control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s "
                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                modeloProductoDetalle.getValueAt(row, 4).toString(),
                modeloProductoDetalle.getValueAt(row, 9).toString(),
                modeloProductoDetalle.getValueAt(row, 1).toString()));
    }

    private void registrarDetalleCotizacion(int idcotizacion) {

        for (int i = 0; i < tablaProductosDetalle.getRowCount(); i++) {
            control.Sql = String.format("INSERT INTO detallecotizacion VALUES(NULL,'%s','%s','%s','%s','%s','%s');", tablaProductosDetalle.getValueAt(i, 5).toString(), tablaProductosDetalle.getValueAt(i, 6).toString(), idcotizacion, tablaProductosDetalle.getValueAt(i, 1).toString(), tablaProductosDetalle.getValueAt(i, 9).toString(), tablaProductosDetalle.getValueAt(i, 4).toString());
            System.out.println(String.format("INSERT INTO detallecotizacion VALUES(NULL,'%s','%s','%s','%s','%s','%s');", tablaProductosDetalle.getValueAt(i, 5).toString(), tablaProductosDetalle.getValueAt(i, 6).toString(), idcotizacion, tablaProductosDetalle.getValueAt(i, 1).toString(), tablaProductosDetalle.getValueAt(i, 9).toString(), tablaProductosDetalle.getValueAt(i, 4).toString()));
            control.ejecutar(control.Sql);
        }

    }

    private void ImprimirCotizacion(int idcotizacion) {

        Map map = new HashMap();
        map.put("idCotizacion", idcotizacion);
        control.showReportDialog("Reporte Cotización", "reporteCotizacion", map);

    }
    public ResultSet DevolverRegistro(String sq) {
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
        return Base.rt;
    }
    public void LlenaUnidades() {

        //control.LlenarCombo1(cboUnidad, "SELECT * FROM unidades u order by cantidad;", 2);
        //cboUnidad.addItem("prueba ctm");
        ArrayList<String> Lista = new ArrayList<String>();
        cboUnidad.removeAllItems();
        try {
            Base.rt = DevolverRegistro("SELECT * FROM unidades u order by cantidad;");
            while (Base.rt.next()) {
                Lista.add(Base.rt.getString(2));
            }
            //cbo.setSelectedIndex(0);
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
        cboUnidad.setEnabled(true);
        for(int i=0;i<Lista.size();i++){
            cboUnidad.addItem(Lista.get(i));
        }

    }
    class ProductosLabelRenderer implements TableCellRenderer {

        private int horizontalAlign = SwingConstants.LEFT;
        private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        private Font font;

        public ProductosLabelRenderer(int horizontalAlign) {
            this.horizontalAlign = horizontalAlign;
        }

        public ProductosLabelRenderer() {

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            renderer.setFont(new Font("Arial", Font.BOLD, 12));
            renderer.setBackground(new JTable().getBackground());
            renderer.setForeground(Color.black);
            renderer.setText(value.toString());

            renderer.setHorizontalAlignment(horizontalAlign);
            if (font != null) {
                renderer.setFont(font);
            }

            if ("1".equals(table.getValueAt(row, 6))) {
//                renderer.setBackground(Color.red);
//                renderer.setForeground(Color.white);
            }
//            SwingWorker worker = new SwingWorker() {
//
//                @Override
//                protected Object doInBackground() throws Exception {
            String des = buscarProductoContenido(table.getValueAt(row, 0).toString(), "1");

            if (!"".equals(des)) {

                renderer.setToolTipText("<html><pre>" + des + "</pre></html>");
            } else {
                renderer.setToolTipText(null);
            }
//            renderer.addMouseListener(new MouseAdapter() {
//
//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    String des = buscarProductoContenido(table.getValueAt(row, 0).toString(), "1");
//                    if (!"".equals(des)) {
//                        renderer.setToolTipText("<html><pre>" + des + "</pre></html>");
//                    } else {
//                        renderer.setToolTipText(null);
//                    }
//                }
//
//            });
//                    return true;
//                }
//            };
//            worker.execute();

            return (Component) renderer;
        }

        public DefaultTableCellRenderer getRenderer() {
            return renderer;
        }

        public ProductosLabelRenderer setFont(Font font) {
            this.font = font;
            return this;
        }
        

    }

}
