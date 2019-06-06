package jym.ferreteria.gui.internalframes;

/**
 * ** @author Omr
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import jym.ferreteria.clases.Controlador;
import static jym.ferreteria.clases.Controlador.Base;
import jym.ferreteria.gui.FrmMain;
import jym.ferreteria.gui.dialogs.FacturarEntradaDeStockDialog;
import jym.ferreteria.gui.dialogs.SeleccionarOrdenDeCompraDialog;
import jym.ferreteria.gui.dialogs.SeleccionarProductoDialog;
import jym.ferreteria.gui.dialogs.SeleccionarProveedorDialog;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LabelRenderer;
import java.util.ArrayList;
import java.util.Calendar;


public class FrmRegistrarEntradasDeStok extends javax.swing.JInternalFrame {

    private CustomTableModel modeloProductoDetalle = new CustomTableModel();
    private CustomTableModel productosModel = new CustomTableModel();
    private Controlador control = new Controlador();
    
    
   private int Select=0;
    private String codProd;
    private String proveedor;
    private DecimalFormat format = new DecimalFormat("0.000");
    private String producto;
    private String Unidad;
    private String nomProd;
    private String rubro;
    private String composicion;
    private String presentacion;
    private String laboratorio;
    private int CantReal;
    private SeleccionarProveedorDialog seleccionarProveedor = null;
    private SeleccionarProductoDialog seleccionarProducto = null;
    private boolean editando = false;
    private String idEntrada = null;
    private String productosEliminados = "0";
    private Map map = new HashMap();
    private SeleccionarOrdenDeCompraDialog seleccionarOrdenDeCompra;
    private FrmListadoOrdenesdeCompra frmListadoOrdenesdeCompra;
    private String ordenDeCompra;
    private final FrmListadoEntradaDeStock entradaDeStockFrame;
    private boolean actualizado = false;
    private boolean facturado = false;
    private final Border defaultBorder;
    private Border focusBorder = new LineBorder(new Color(255, 164, 0), 2);
    private Font font = new Font("Arial", Font.BOLD, 12);
    private String datosDistribucionProductos[];
    
    public FrmRegistrarEntradasDeStok(FrmListadoEntradaDeStock entradaDeStockFrame) {
        
       
        initComponents();
        Select=cboMoneda.getSelectedIndex();
        LlenaUnidades();
        
         
        this.defaultBorder = jScrollPaneProductos.getBorder();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        this.entradaDeStockFrame = entradaDeStockFrame;
        this.setTitle("Ingreso de productos adquiridos");
        
       

        productosModel.setColumnIdentifiers(new String[]{"Id","Codigo","Codigo x Unid.", "Producto","Rubro", "Composición", "Presentación", "Laboratorio","Unidad","P. Unid Real","cant", "P. Compra"});
        tablaProductos.setModel(productosModel);

        control.hideTableColumn(tablaProductos, 0,1,9,10);
        control.tableWidthColumn(tablaProductos, 200, 3);
         control.tableWidthColumn(tablaProductos, 100, 1,2,8);
        control.tableWidthColumn(tablaProductos, 100, 11);
        tablaProductos.setDefaultRenderer(Object.class, new LabelRenderer().setFont(font));
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        LabelRenderer labelRenderer = new LabelRenderer(SwingConstants.RIGHT);
        //labelRenderer.setFont(new Font("Arial", Font.BOLD, 10));
        tablaProductos.getColumnModel().getColumn(11).setCellRenderer(labelRenderer);


        //configuracion para tabla de detalle
        control.fillComboBox("SELECT `idAlmacen`, `nombre` FROM `almacen`;", cboAlmacen, map);
        tablaProductoSinIngresar.setModel(modeloProductoDetalle);
        modeloProductoDetalle.setColumnIdentifiers(new String[]{"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"});
        control.hideTableColumn(tablaProductoSinIngresar, 0,1, 14,15);
        control.tableWidthColumn(tablaProductoSinIngresar, 200, 3);
        control.tableWidthColumn(tablaProductoSinIngresar, 100, 8);
        control.tableMaxWidthColumn(tablaProductoSinIngresar, 100, 9, 10, 11,12,13);
        tablaProductoSinIngresar.setDefaultRenderer(Object.class, new LabelRenderer(SwingConstants.LEFT).setFont(font));
        labelRenderer = new LabelRenderer(SwingConstants.RIGHT);
        tablaProductoSinIngresar.getColumnModel().getColumn(11).setCellRenderer(labelRenderer);
        tablaProductoSinIngresar.getColumnModel().getColumn(12).setCellRenderer(labelRenderer);
        tablaProductoSinIngresar.getColumnModel().getColumn(13).setCellRenderer(labelRenderer);
        tablaProductoSinIngresar.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnEditarProducto.setEnabled(true);
                btnEliminarProducto.setEnabled(true);
            }
        });

        btnAgregarProducto.setMnemonic(KeyEvent.VK_A);
        btnProveedor.setMnemonic(KeyEvent.VK_P);
        generarNumero();
        jDateChooser1.setDate(new Date());
        
        Calendar hoy = Calendar.getInstance();
        hoy.add(Calendar.YEAR, 1);
        jDateChooser2.setDate(hoy.getTime());
        
        //Inicio Pedido
        jLabel12.setVisible(false);
        txtNumeroPedido.setVisible(false);
        btnPedido.setVisible(false);
        jLabel11.setVisible(false);
        txtFechaPedido.setVisible(false);
        //Fin Pedido
        
        cargarProductos0();
        
        
        cargarTasa();
        
         jLabel4.setVisible(false);
         txtBuscarProductoDetalle.setVisible(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jmiDistribuirProductos = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductoSinIngresar = new javax.swing.JTable();
        txtBuscarProducto = new javax.swing.JTextField();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cboUnidad = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        cboMoneda = new javax.swing.JComboBox();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtBuscarProductoDetalle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTotalFactura = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        btnProveedor = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        txtNumeroPedido = new javax.swing.JTextField();
        btnPedido = new javax.swing.JButton();
        txtFechaPedido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblFacturar = new javax.swing.JLabel();
        btnNuevo2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTasaDolar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboAlmacen = new javax.swing.JComboBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        jmiDistribuirProductos.setText("Distribuir productos");
        jmiDistribuirProductos.setName("jmiDistribuirProductos"); // NOI18N
        jmiDistribuirProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDistribuirProductosActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jmiDistribuirProductos);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Entrada de Stock");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaProductoSinIngresar.setForeground(new java.awt.Color(0, 51, 102));
        tablaProductoSinIngresar.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductoSinIngresar.setComponentPopupMenu(jPopupMenu1);
        tablaProductoSinIngresar.setName("tablaProductoSinIngresar"); // NOI18N
        tablaProductoSinIngresar.getTableHeader().setReorderingAllowed(false);
        tablaProductoSinIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoSinIngresarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProductoSinIngresarMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductoSinIngresar);

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
        txtBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProductoActionPerformed(evt);
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

        jScrollPaneProductos.setName("jScrollPaneProductos"); // NOI18N

        tablaProductos.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Buscar:");
        jLabel3.setName("jLabel3"); // NOI18N

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

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Unidades");
        jLabel14.setName("jLabel14"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Productos a la Compra"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad:");
        jLabel8.setName("jLabel8"); // NOI18N

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Costo:");
        jLabel7.setName("jLabel7"); // NOI18N

        txtCosto.setEditable(false);
        txtCosto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCosto.setName("txtCosto"); // NOI18N
        txtCosto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCostoCaretUpdate(evt);
            }
        });
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        cboMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "S/", "$" }));
        cboMoneda.setName("cboMoneda"); // NOI18N
        cboMoneda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboMonedaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cboMonedaMouseExited(evt);
            }
        });
        cboMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonedaActionPerformed(evt);
            }
        });
        cboMoneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboMonedaKeyPressed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Total: ");
        jLabel9.setName("jLabel9"); // NOI18N

        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/add-icon.png"))); // NOI18N
        btnAgregarProducto.setName("btnAgregarProducto"); // NOI18N
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pencil-icon.png"))); // NOI18N
        btnEditarProducto.setEnabled(false);
        btnEditarProducto.setName("btnEditarProducto"); // NOI18N
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash-icon.png"))); // NOI18N
        btnEliminarProducto.setEnabled(false);
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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Ingreso Directo por Código");
        jLabel4.setName("jLabel4"); // NOI18N

        txtBuscarProductoDetalle.setName("txtBuscarProductoDetalle"); // NOI18N
        txtBuscarProductoDetalle.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtBuscarProductoDetalleAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtBuscarProductoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProductoDetalleActionPerformed(evt);
            }
        });
        txtBuscarProductoDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProductoDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoDetalleKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Total:");
        jLabel2.setName("jLabel2"); // NOI18N

        txtTotalFactura.setEditable(false);
        txtTotalFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTotalFactura.setText("0.00");
        txtTotalFactura.setName("txtTotalFactura"); // NOI18N

        lblProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(24, 29, 99));
        lblProducto.setName("lblProducto"); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Lote");
        jLabel15.setName("jLabel15"); // NOI18N

        txtLote.setEditable(false);
        txtLote.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLote.setName("txtLote"); // NOI18N
        txtLote.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLoteCaretUpdate(evt);
            }
        });
        txtLote.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoteFocusGained(evt);
            }
        });
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLoteKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Fecha Vencimiento");
        jLabel16.setName("jLabel16"); // NOI18N

        jDateChooser2.setName("jDateChooser2"); // NOI18N
        jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(txtBuscarProductoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1666, 1666, 1666))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1306, 1306, 1306))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1080, 1080, 1080)
                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnCancelarProducto)
                                .addComponent(btnEliminarProducto)
                                .addComponent(btnEditarProducto))
                            .addComponent(btnAgregarProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtBuscarProductoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(666, 666, 666))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneProductos)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("N° Entrada: ");
        jLabel1.setName("jLabel1"); // NOI18N

        txtNumero.setEditable(false);
        txtNumero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNumero.setName("txtNumero"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Fecha: ");
        jLabel5.setName("jLabel5"); // NOI18N

        jDateChooser1.setName("jDateChooser1"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Proveedor");
        jLabel6.setName("jLabel6"); // NOI18N

        txtProveedor.setEditable(false);
        txtProveedor.setName("txtProveedor"); // NOI18N
        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });

        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
        btnProveedor.setName("btnProveedor"); // NOI18N
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 52, 193));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton1.setMnemonic('S');
        jButton1.setText("Salir");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 52, 193));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 52, 193));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/registrar.png"))); // NOI18N
        jButton3.setMnemonic('G');
        jButton3.setText("Guardar");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnFacturar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnFacturar.setForeground(new java.awt.Color(0, 52, 193));
        btnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/List.png"))); // NOI18N
        btnFacturar.setMnemonic('F');
        btnFacturar.setText("Facturar");
        btnFacturar.setEnabled(false);
        btnFacturar.setName("btnFacturar"); // NOI18N
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 52, 193));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/note-accept-icon.png"))); // NOI18N
        btnActualizar.setMnemonic('A');
        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.setName("btnActualizar"); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        txtNumeroPedido.setEditable(false);
        txtNumeroPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNumeroPedido.setName("txtNumeroPedido"); // NOI18N

        btnPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        btnPedido.setName("btnPedido"); // NOI18N
        btnPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidoActionPerformed(evt);
            }
        });

        txtFechaPedido.setEditable(false);
        txtFechaPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFechaPedido.setName("txtFechaPedido"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("F. Pedido:");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("N° Pedido:");
        jLabel12.setName("jLabel12"); // NOI18N

        lblFacturar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblFacturar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFacturar.setText("Pendiente Facturar");
        lblFacturar.setName("lblFacturar"); // NOI18N
        lblFacturar.setOpaque(true);

        btnNuevo2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        btnNuevo2.setMnemonic('N');
        btnNuevo2.setText("Nuevo");
        btnNuevo2.setName("btnNuevo2"); // NOI18N
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Tasa de Cambio");
        jLabel13.setName("jLabel13"); // NOI18N

        txtTasaDolar.setEditable(false);
        txtTasaDolar.setName("txtTasaDolar"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Almacén:");
        jLabel10.setName("jLabel10"); // NOI18N

        cboAlmacen.setEnabled(false);
        cboAlmacen.setName("cboAlmacen"); // NOI18N
        cboAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboAlmacenKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTasaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnProveedor)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProveedor, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel11))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addComponent(btnPedido)))
                    .addComponent(txtFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel13)
                        .addComponent(txtTasaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnCancelar)
                    .addComponent(jButton3)
                    .addComponent(btnFacturar)
                    .addComponent(btnActualizar)
                    .addComponent(lblFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed

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
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
        calcularTotal();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cboAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboAlmacenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCantidad.selectAll();
            txtCantidad.grabFocus();
        }
    }//GEN-LAST:event_cboAlmacenKeyPressed

    private void btnPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidoActionPerformed
        seleccionarPedido();
    }//GEN-LAST:event_btnPedidoActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        if (guardar()) {
            facturar();
        }

    }//GEN-LAST:event_btnFacturarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (guardar()) {
            actualizar();
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jmiDistribuirProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDistribuirProductosActionPerformed
        if (!actualizado) {

            txtBuscarProductoDetalle.setEditable(false);
            tablaProductoSinIngresar.setEnabled(false);
            int row = tablaProductoSinIngresar.getSelectedRow();

            lblProducto.setText(modeloProductoDetalle.getValueAt(row, 2).toString());
            cboAlmacen.setSelectedItem(modeloProductoDetalle.getValueAt(row, 3).toString());
            txtCantidad.setText(modeloProductoDetalle.getValueAt(row, 4).toString());
            txtCosto.setText(modeloProductoDetalle.getValueAt(row, 5).toString());
            txtCantidad.setEditable(true);
            txtCosto.setEditable(true);
            cboAlmacen.setEnabled(true);
            btnAgregarProducto.setEnabled(false);
            btnEditarProducto.setEnabled(false);
            btnEliminarProducto.setEnabled(false);

            datosDistribucionProductos = new String[7];
            datosDistribucionProductos[0] = modeloProductoDetalle.getValueAt(row, 0).toString();//id Registro
            datosDistribucionProductos[1] = modeloProductoDetalle.getValueAt(row, 1).toString();//Codigo Producto
            datosDistribucionProductos[2] = modeloProductoDetalle.getValueAt(row, 2).toString();//Descripcion
            datosDistribucionProductos[3] = modeloProductoDetalle.getValueAt(row, 3).toString();//Nombre Almacen
            datosDistribucionProductos[4] = modeloProductoDetalle.getValueAt(row, 4).toString();//Cantidad
            datosDistribucionProductos[5] = modeloProductoDetalle.getValueAt(row, 5).toString();//Costo
            datosDistribucionProductos[6] = modeloProductoDetalle.getValueAt(row, 7).toString();//id Almacen
            cboAlmacen.grabFocus();

        } else {
            JOptionPane.showMessageDialog(this, "La entrada de stock está actualizada, no se pueden modificar las lineas existentes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jmiDistribuirProductosActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
    
        control.recarga(this);
        //this.dispose();
        //control.Hora();

//this.removeAll();
      //this.remove(0);
        /*this.validate();
        this.repaint();
        this.updateUI();
    cancelar();
    calcularTotal();  */     
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void cboUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboUnidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadKeyPressed

    private void cboUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUnidadActionPerformed
        cargarProductos();
        //JOptionPane.showMessageDialog(null, dato);// TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadActionPerformed

    private void cboUnidadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseReleased
        //cargarProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseReleased

    private void cboUnidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMousePressed
        //cargarProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMousePressed

    private void cboUnidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseExited
        //cargarProductos();    // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseExited

    private void cboUnidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseEntered
        //cargarProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseEntered

    private void cboUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboUnidadMouseClicked
        // cargarProductos();   // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadMouseClicked

    private void cboUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadItemStateChanged
        //cargarProductos(); // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadItemStateChanged

    private void cboMonedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMonedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //bPagar.doClick();
        }
    }//GEN-LAST:event_cboMonedaKeyPressed

    private void cboMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonedaActionPerformed
        if(txtCosto.getText().trim().length()>0){
            int aux=cboMoneda.getSelectedIndex();
            if(Select==aux){
                Select=aux;
            }
            else{
                Select=aux;
                if(aux==0){
                    double costo=Double.parseDouble(txtCosto.getText());
                    double tasa=Double.parseDouble(txtTasaDolar.getText());
                    costo=costo*tasa;
                    txtCosto.setText(control.decimalFormat(costo));
                }
                else{
                    double costo=Double.parseDouble(txtCosto.getText());
                    double tasa=Double.parseDouble(txtTasaDolar.getText());
                    costo=costo/tasa;
                    txtCosto.setText(control.decimalFormat(costo));
                }
            }
        }
    }//GEN-LAST:event_cboMonedaActionPerformed

    private void cboMonedaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMonedaMouseExited

    }//GEN-LAST:event_cboMonedaMouseExited

    private void cboMonedaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMonedaMouseEntered

    }//GEN-LAST:event_cboMonedaMouseEntered

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed
        if (control.isEnterKey(evt) && tablaProductos.getSelectedRow() >= 0) {
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

    private void tablaProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMousePressed

    }//GEN-LAST:event_tablaProductosMousePressed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (evt.getClickCount() > 1) {
            seleccionarProducto();
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        cargarProductos();
    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void txtBuscarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (productosModel.getRowCount() > 0) {
                try {
                    jScrollPaneProductos.setBorder(focusBorder);

                    tablaProductos.getSelectionModel().setSelectionInterval(0, 0);
                    tablaProductos.requestFocus();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_txtBuscarProductoKeyPressed

    private void txtBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoActionPerformed

    private void txtBuscarProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProductoFocusGained
        jScrollPaneProductos.setBorder(defaultBorder);
    }//GEN-LAST:event_txtBuscarProductoFocusGained

    private void txtBuscarProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarProductoAncestorAdded

    }//GEN-LAST:event_txtBuscarProductoAncestorAdded

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        cancelar();
        producto = null;
        codProd= null;
        lblProducto.setText(" ");
        txtCantidad.setText("1");
        txtCosto.setText("0.00");
        txtCantidad.setEditable(false);
        txtCosto.setEditable(false);
        btnAgregarProducto.grabFocus();
        tablaProductoSinIngresar.setEnabled(true);
        txtBuscarProductoDetalle.setEnabled(true);
        cboAlmacen.setEnabled(false);
        cboUnidad.setEnabled(true);
        try {
            cboAlmacen.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void txtBuscarProductoDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoDetalleKeyReleased
        /*TableRowSorter rowSorter = new TableRowSorter(modeloProductoDetalle);
        RowFilter filter = RowFilter.regexFilter("(?i)" + txtBuscarProductoDetalle.getText(), 1);
        rowSorter.setRowFilter(filter);
        tablaProductoSinIngresar.setRowSorter(rowSorter);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);*/
    }//GEN-LAST:event_txtBuscarProductoDetalleKeyReleased

    private void txtBuscarProductoDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //JOptionPane.showMessageDialog(null, txtBuscarProductoDetalle.getText());
            PasarDirecto();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoDetalleKeyPressed

    private void txtBuscarProductoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoDetalleActionPerformed

    private void txtBuscarProductoDetalleAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarProductoDetalleAncestorAdded
        txtBuscarProductoDetalle.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoDetalleAncestorAdded

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        control.decimal(evt, txtCosto.getText());
    }//GEN-LAST:event_txtCostoKeyTyped

    private void txtCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int CantR=CantReal;
            double cant=Double.parseDouble(txtCantidad.getText());
            double prueba=0;
            prueba=CantR*cant;

            if(prueba%1==0 && prueba>0){
                if (datosDistribucionProductos == null) {
                    agregarProducto();
                } else {
                    //distribuirProductos();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "La Cantidad Asignada es Incorrecta");
                txtCantidad.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCostoKeyPressed

    private void txtCostoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCostoCaretUpdate
        calcularCostoPorProducto();
    }//GEN-LAST:event_txtCostoCaretUpdate

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        //control.decimal(evt, txtCantidad.getText());
        control.decimal(evt, txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (txtCantidad.getText().trim().length() > 0)) {
            txtCosto.requestFocus();
            txtCosto.selectAll();

        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        if (txtCosto.getText().trim().length() == 0) {
            txtCosto.grabFocus();
        }
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate
        calcularCostoPorProducto();
    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        eliminarLinea();
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        cargarEditar();
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        if(tablaProductos.getSelectedRow() >=0){
        seleccionarProducto();
        }
    else{
    JOptionPane.showMessageDialog(null,"Debe seleccionar un producto para agregar");
    tablaProductos.requestFocus();
    }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void tablaProductoSinIngresarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoSinIngresarMousePressed
        Point point = evt.getPoint();
        int currentRow = tablaProductoSinIngresar.rowAtPoint(point);
        tablaProductoSinIngresar.setRowSelectionInterval(currentRow, currentRow);
    }//GEN-LAST:event_tablaProductoSinIngresarMousePressed

    private void tablaProductoSinIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoSinIngresarMouseClicked
        if (evt.getClickCount() > 1) {
            cargarEditar();
            datosDistribucionProductos = null;
        }
    }//GEN-LAST:event_tablaProductoSinIngresarMouseClicked

    private void txtLoteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLoteCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoteCaretUpdate

    private void txtLoteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoteFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoteFocusGained

    private void txtLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyPressed
     if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (txtLote.getText().trim().length() > 0)) {
            txtCantidad.grabFocus();
            txtCantidad.selectAll();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoteKeyPressed

    private void txtLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoteKeyTyped

    private void jDateChooser2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser2KeyPressed
    if ((evt.getKeyChar() == KeyEvent.VK_ENTER) ) {
            txtCantidad.requestFocus();
           txtCantidad.selectAll();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2KeyPressed

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnPedido;
    private javax.swing.JButton btnProveedor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cboAlmacen;
    private javax.swing.JComboBox cboMoneda;
    private javax.swing.JComboBox cboUnidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private javax.swing.JMenuItem jmiDistribuirProductos;
    private javax.swing.JLabel lblFacturar;
    private javax.swing.JLabel lblProducto;
    public static javax.swing.JTable tablaProductoSinIngresar;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtBuscarProductoDetalle;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtFechaPedido;
    private javax.swing.JTextField txtLote;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroPedido;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtTasaDolar;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalFactura;
    // End of variables declaration//GEN-END:variables

    private void cargarProductos0() {
        String text = txtBuscarProducto.getText();
        String dato="Unidad";
        
        String auxCod="";
        
        if(dato.equals("Unidad")){
            auxCod="";
        }
        else{
            auxCod="General";
        }
        //JOptionPane.showMessageDialog(null, dato);
        //int dato=1;
        control.Sql = "select p.idproduto, p.codProd,"
                + "IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'"+auxCod+"')\n"
                + ",p.nomproducto,t.nomtip, p.composicion, pre.present,m.nommarc,u.nomuniddes,REPLACE(format(p.preciocompra,2), ',', ''),u.cantidad, \n " +
"                        REPLACE(format(IFNULL((select d.costocompra from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.preciocompra*u.cantidad),2), ',', '') \n " +
"                         from produto p \n " +
"                        inner join tipoproducto t on t.idtipoproducto=p.idtipoproducto \n " +
"                        inner join marca m on p.idmarca=m.idmarca \n " +
"inner join presentacion pre on pre.idpresentacion=p.idpresentacion, \n" +
"                        unidades u \n" +
                        "where u.nomuniddes='"+dato+"'\n" +
                        "and (p.codProd like '%"+text+"%' or p.codUnid like '%"+text+"%' or p.nomproducto like '%"+text+"%' or m.nommarc like '%"+text+"%') order by p.idproduto;";
        control.LlenarJTablaExistUnids(productosModel, control.Sql, 12);

//        selectedRow = -1;
    }
    
    private void cargarProductos() {
        String text = txtBuscarProducto.getText();
        String dato=cboUnidad.getSelectedItem().toString();
        
        String auxCod="";
        
        if(dato.equals("Unidad")){
            auxCod="";
        }
        else{
            auxCod="General";
        }
        //JOptionPane.showMessageDialog(null, dato);
        //int dato=1;
        control.Sql = "select p.idproduto, p.codProd,"
                + "IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'"+auxCod+"')\n"
                + ",p.nomproducto,t.nomtip, p.composicion, pre.present,m.nommarc,u.nomuniddes,REPLACE(format(p.preciocompra,2), ',', ''),u.cantidad, \n " +
"                        REPLACE(format(IFNULL((select d.costocompra from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.preciocompra*u.cantidad),2), ',', '') \n " +
"                         from produto p \n " +
"                        inner join tipoproducto t on t.idtipoproducto=p.idtipoproducto \n " +
"                        inner join marca m on p.idmarca=m.idmarca \n " +
"inner join presentacion pre on pre.idpresentacion=p.idpresentacion, \n" +
"                        unidades u \n" +
                        "where u.nomuniddes='"+dato+"'\n" +
                        "and (p.codProd like '%"+text+"%' or p.codUnid like '%"+text+"%' or p.nomproducto like '%"+text+"%' or m.nommarc like '%"+text+"%') order by p.nomproducto;";
        control.LlenarJTablaExistUnids(productosModel, control.Sql, 12);

//        selectedRow = -1;
    }
    
    private void PasarDirecto(){
    String text=txtBuscarProductoDetalle.getText().trim();
    
    control.Sql="select '',p.idproduto, p.codprod, concat(p.nomproducto,' ',m.nommarc ),u.nomuniddes,'1' as cantidad\n" +
                ", IFNULL((select d.costocompra from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.preciocompra*u.cantidad),\n" +
                " IFNULL((select d.costocompra from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.preciocompra*u.cantidad),\n" +
                " '1'\n" +
                ", u.cantidad\n" +
                "from produto p\n" +
                "inner join marca m on p.idmarca=m.idmarca,\n" +
                "unidades u\n" +
                "where p.codProd='"+text+"' group by p.idproduto\n" +
                "union\n" +
                "select '',p.idproduto, p.codprod,concat(p.nomproducto,' ',m.nommarc ), u.nomuniddes,'1' as cantidad\n" +
                ", IFNULL((select d.costocompra from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.preciocompra*u.cantidad)\n" +
                ",IFNULL((select d.costocompra from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.preciocompra*u.cantidad),\n" +
                " '1'\n" +
                ", u.cantidad\n" +
                "from produto p\n" +
                "inner join marca m on p.idmarca=m.idmarca,\n" +
                "unidades u\n" +
                "inner join detunidprod de on de.idUnidades=u.idUnidades\n" +
                "where de.idProducto=p.idproduto and de.codUnid='"+text+"';";
    
    //control.LlenarJTabla(modeloProductoDetalle,control.Sql, 10);
    
    String[] cargarRegistro = control.cargarRegistro(control.Sql, 10);
    if(cargarRegistro==null){
    
        JOptionPane.showMessageDialog(null, "Código de Producto No Registrado.");
    }    
    else{
    
    int index = buscar(cargarRegistro[1],cargarRegistro[4],txtLote.getText());
                           if (index < 0) {
                            //modeloProductoDetalle.addRow(new String[]{"",producto, codProd, lblProducto.getText(), cboUnidad.getSelectedItem().toString(), String.valueOf(cantidad),control.decimalFormat(costoProducto), control.decimalFormat(costoProducto * cantidad), map.get(cboAlmacen.getSelectedIndex()).toString(),String.valueOf(CantReal)});
                                modeloProductoDetalle.addRow(cargarRegistro);
                           } else {
                              int cantidadAnterior = Integer.parseInt(modeloProductoDetalle.getValueAt(index, 5).toString());
                             double costo=Double.parseDouble(modeloProductoDetalle.getValueAt(index, 6).toString());
                             //JOptionPane.showMessageDialog(null, costo);
                            modeloProductoDetalle.setValueAt((int) (cantidadAnterior + Integer.parseInt(cargarRegistro[5])), index, 5);
                             modeloProductoDetalle.setValueAt(control.decimalFormat(costo * (cantidadAnterior + Integer.parseInt(cargarRegistro[5]))), index, 7);
                          }
                           btnAgregarProducto.setEnabled(true);
                            btnEditarProducto.setEnabled(true);
                            btnEliminarProducto.setEnabled(true);
                            txtBuscarProductoDetalle.setEditable(true);
                            tablaProductoSinIngresar.setEnabled(true);
                            
                        editando = false;
                        lblProducto.setText(" ");
                        txtCosto.setText("0.00");
                        txtCantidad.setText("1");

                        txtTotal.setText("0.00");
                        txtCosto.setEditable(false);
                        txtCantidad.setEditable(false);
                        txtBuscarProductoDetalle.setText(null);
                        
                        cboUnidad.setEnabled(true);
                        try {
                            cboAlmacen.setSelectedIndex(0);
                        } catch (Exception e) {
                        }
                        
                        calcularTotal();
                        txtBuscarProductoDetalle.requestFocus();
    }
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
    public  void cargarTasa(){
        String tasa=control.CrearRegistroDev("select valor from config where idconfig='tasaDeCambio';");
        double ta=Double.parseDouble(tasa);
        tasa=control.decimalFormat(ta);
        txtTasaDolar.setText(tasa);
    }

    private void cancelar() {
        productosEliminados = "0";
        txtProveedor.setText("");
        txtCosto.setText("0.00");
        txtCantidad.setText("1");
        proveedor = null;
        producto = null;
        Unidad = null;
        nomProd = null;
        rubro = null;
        composicion = null;
        presentacion = null;
        laboratorio = null;
        control.LimTabla(modeloProductoDetalle);
        txtBuscarProductoDetalle.setText("");
        lblProducto.setText(" ");
        generarNumero();
        jDateChooser1.setDate(new Date());
        Calendar hoy = Calendar.getInstance();
        hoy.add(Calendar.YEAR, 1);
        jDateChooser2.setDate(hoy.getTime());
        btnAgregarProducto.setEnabled(true);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        txtCosto.setEditable(false);
        txtCantidad.setEditable(false);
        cboUnidad.setEnabled(true);
        try {
            cboAlmacen.setSelectedIndex(0);
        } catch (Exception e) {
        }
        if (idEntrada != null) {
            cargarCabecera();
        }
    }

    private void calcularCostoPorProducto() {
        double costoProducto = 0;
        double cantidad = 1;
        try {
            costoProducto = Double.parseDouble(txtCosto.getText());
        } catch (Exception e) {
        }
        try {
            cantidad = Double.parseDouble(txtCantidad.getText());
        } catch (Exception e) {
        }
        txtTotal.setText(String.valueOf(control.decimalFormat(costoProducto * cantidad)));
    }

    private void agregarProducto() {
        if (txtCosto.getText().trim().length() > 0) {
            if (txtCantidad.getText().trim().length() > 0) {
                if (Double.parseDouble(txtCosto.getText()) > 0) {
                    if (Double.parseDouble(txtCantidad.getText()) > 0) {
                        if (txtLote.getText().trim().length() > 0) {
                        
 double costoProducto = 0;
                        double cantidad = 1;
                        costoProducto = Double.parseDouble(txtCosto.getText());
                        
                        if(cboMoneda.getSelectedIndex()==0){
                            costoProducto = Double.parseDouble(txtCosto.getText());
                        }
                        else{
                            costoProducto = Double.parseDouble(txtCosto.getText());
                            double tasa=Double.parseDouble(txtTasaDolar.getText());
                            costoProducto=costoProducto*tasa;
                        }
                        
                        String fecha = control.Formato_Amd1(jDateChooser2.getDate() == null ? new Date() : jDateChooser2.getDate(),"/");
                        
                        cantidad = Double.parseDouble(txtCantidad.getText());
                        if (!editando) {
                         int index = buscar(producto,Unidad,txtLote.getText());
                           if (index < 0) {
                                                                                                                            //"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
                            modeloProductoDetalle.addRow(new String[]{"",producto, codProd, nomProd,rubro,composicion,presentacion,laboratorio, cboUnidad.getSelectedItem().toString(),txtLote.getText(),fecha, String.valueOf(control.decimalFormat(cantidad)),String.valueOf(control.decimalFormat(costoProducto)), String.valueOf(control.decimalFormat(costoProducto * cantidad)), map.get(cboAlmacen.getSelectedIndex()).toString(),String.valueOf(CantReal)});

                           } else {
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
                              double cantidadAnterior = Double.parseDouble(modeloProductoDetalle.getValueAt(index, 11).toString());
                             modeloProductoDetalle.setValueAt(String.valueOf(control.decimalFormat(costoProducto)), index, 12);
                            modeloProductoDetalle.setValueAt(String.valueOf(control.decimalFormat(cantidadAnterior + cantidad)), index, 11);
                               modeloProductoDetalle.setValueAt(String.valueOf(control.decimalFormat(costoProducto * (cantidadAnterior + cantidad))), index, 13);
                          }
                        } else {
                            int index = tablaProductoSinIngresar.getSelectedRow();
                            modeloProductoDetalle.setValueAt(cboUnidad.getSelectedItem().toString(), index, 8);
                            modeloProductoDetalle.setValueAt(String.valueOf(control.decimalFormat(costoProducto)), index, 12);
                            modeloProductoDetalle.setValueAt(String.valueOf(control.decimalFormat(cantidad)), index, 11);
                            modeloProductoDetalle.setValueAt(String.valueOf(control.decimalFormat(costoProducto * (cantidad))), index, 13);
                            modeloProductoDetalle.setValueAt(txtLote.getText(), index, 9);
                            modeloProductoDetalle.setValueAt(fecha, index, 10);
                            modeloProductoDetalle.setValueAt(map.get(cboAlmacen.getSelectedIndex()).toString(), index, 14);
                            btnAgregarProducto.setEnabled(true);
                            btnEditarProducto.setEnabled(true);
                            btnEliminarProducto.setEnabled(true);
                            txtBuscarProductoDetalle.setEditable(true);
                            tablaProductoSinIngresar.setEnabled(true);
                        }
                        editando = false;
                        lblProducto.setText(" ");
                        txtCosto.setText("0.00");
                        txtCantidad.setText("1");

                        txtTotal.setText("0.00");
                        txtLote.setText("");
                        
                        txtCosto.setEditable(false);
                        txtCantidad.setEditable(false);
                        txtLote.setEditable(false);
                        btnAgregarProducto.grabFocus();
                        cboUnidad.setEnabled(true);
                        try {
                            cboAlmacen.setSelectedIndex(0);
                        } catch (Exception e) {
                        }
                        txtBuscarProductoDetalle.requestFocus();
                        calcularTotal();
                        
                       
                    } else {
                        JOptionPane.showMessageDialog(this, "El nombre del lote del producto no puede ser vacío");
                    }

                       
                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad debe de ser mayor o igual a 1");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El costo del producto debe de ser mayor a cero");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Ingrese la cantidad de productos");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese el costos del producto");
        }
    }

    public int buscar(String idProducto, String unidad, String lote) {
        for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
            if (modeloProductoDetalle.getValueAt(i, 1).toString().equals(idProducto) && modeloProductoDetalle.getValueAt(i, 8).toString().equals(unidad) && modeloProductoDetalle.getValueAt(i, 9).toString().equals(lote)) {
                
                return i;
            }

        }
        return -1;
    }
    public int buscarA(String idProducto, String unidad) {
        for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
            if (modeloProductoDetalle.getValueAt(i, 1).toString().equals(idProducto) && modeloProductoDetalle.getValueAt(i, 8).toString().equals(unidad)) {
                
                return i;
            }

        }
        return -1;
    }

    private void generarNumero() {
        control.Sql = "SELECT MAX(`numero`) FROM `entradastock`;";
        int numero = 0;
        try {
            numero = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        } catch (Exception e) {
        }
        numero++;
        txtNumero.setText(control.format0(5, numero));
    }

    private void cargarEditar() {
        
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
        if (!actualizado) {
            editando = true;
            txtBuscarProductoDetalle.setEditable(false);
            tablaProductoSinIngresar.setEnabled(false);
            int fila = tablaProductoSinIngresar.getSelectedRow();
            producto = modeloProductoDetalle.getValueAt(fila, 1).toString();
            
            Unidad = modeloProductoDetalle.getValueAt(fila, 8).toString();
            nomProd = modeloProductoDetalle.getValueAt(fila, 3).toString();
            rubro = modeloProductoDetalle.getValueAt(fila, 4).toString();
            composicion = modeloProductoDetalle.getValueAt(fila, 5).toString();
            presentacion = modeloProductoDetalle.getValueAt(fila, 6).toString();
            laboratorio = modeloProductoDetalle.getValueAt(fila, 7).toString();
            
            txtLote.setText(modeloProductoDetalle.getValueAt(fila, 9).toString());
            String fec0=control.VolverFecha2(modeloProductoDetalle.getValueAt(fila, 10).toString());
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
                String strFecha = fec0;
                Date fecha = null;
                try {

                fecha = formatoDelTexto.parse(strFecha);
                this.jDateChooser2.setDate(fecha);

                } catch (ParseException ex) {

                ex.printStackTrace();

                }
                    
            
            CantReal=Integer.parseInt(modeloProductoDetalle.getValueAt(fila, 15).toString());
            codProd = modeloProductoDetalle.getValueAt(fila, 2).toString();
            lblProducto.setText(modeloProductoDetalle.getValueAt(fila, 6).toString()+" "+modeloProductoDetalle.getValueAt(fila, 3).toString());
            cboUnidad.setSelectedItem(modeloProductoDetalle.getValueAt(fila, 8).toString());
            txtCantidad.setText(modeloProductoDetalle.getValueAt(fila, 11).toString());
            txtCosto.setText(modeloProductoDetalle.getValueAt(fila, 12).toString());
            
            txtLote.setEditable(true);
            txtCantidad.setEditable(true);
            txtCosto.setEditable(true);
            cboAlmacen.setEnabled(true);
            btnAgregarProducto.setEnabled(false);
            btnEditarProducto.setEnabled(false);
            btnEliminarProducto.setEnabled(false);
            cboUnidad.setEnabled(false);
            txtLote.grabFocus();
        } else {
            JOptionPane.showMessageDialog(this, "La entrada de stock está actualizada, no se pueden modificar las lineas existentes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private boolean guardar() {

        boolean guardado = false;
        if (proveedor != null) {
            if (modeloProductoDetalle.getRowCount() > 0) {
                String numero = txtNumero.getText();

                String fecha = control.Formato_Amd(jDateChooser1.getDate() == null ? new Date() : jDateChooser1.getDate());
                if (idEntrada == null) {
                    generarNumero();

                    control.CrearRegistro(String.format("INSERT INTO entradastock VALUES(NULL,'%s','%s',%s,%s,0,0,NULL);", numero, fecha, proveedor, ordenDeCompra == null ? "NULL" : ordenDeCompra));
                    String id = control.DevolverRegistroDto("SELECT `idEntradaStock` FROM `entradastock` WHERE `numero`='" + numero + "';", 1);
                    for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
                                                                                                                                        //"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
                                                                                                                                        //"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"

                        control.CrearRegistro(String.format("INSERT INTO detalleentradastock VALUES(NULL,%s,%s,%s,%s,%s,%s,'%s');", id, modeloProductoDetalle.getValueAt(i, 1).toString(), modeloProductoDetalle.getValueAt(i, 11).toString(), modeloProductoDetalle.getValueAt(i, 12).toString(), modeloProductoDetalle.getValueAt(i, 14).toString(), modeloProductoDetalle.getValueAt(i, 15).toString(), modeloProductoDetalle.getValueAt(i, 8).toString()));
                        System.out.println(String.format("INSERT INTO detalleentradastock VALUES(NULL,%s,%s,%s,%s,%s,%s,'%s');", id, modeloProductoDetalle.getValueAt(i, 1).toString(), modeloProductoDetalle.getValueAt(i, 11).toString(), modeloProductoDetalle.getValueAt(i, 12).toString(), modeloProductoDetalle.getValueAt(i, 14).toString(), modeloProductoDetalle.getValueAt(i, 15).toString(), modeloProductoDetalle.getValueAt(i, 8).toString()));
                        
                        String idDet = control.DevolverRegistroDto("SELECT d.idDetalleEntradaStock FROM detalleentradastock d where d.idEntradaStock='"+id+"' order by d.idDetalleEntradaStock desc limit 1;", 1);
                        
                        String fecVen=control.CoverFecha(modeloProductoDetalle.getValueAt(i, 10).toString());
                        double cantL=Double.parseDouble(modeloProductoDetalle.getValueAt(i, 11).toString())*Double.parseDouble(modeloProductoDetalle.getValueAt(i, 15).toString());
                        int cantForm=(int)cantL;
                        String cantLote=String.valueOf(cantForm);
                        //insert into lote values(null,txtlote,curdate(),txtfec ven,idprod,idDetEntrada,canR,'3','');
                        control.CrearRegistro(String.format("insert into lote values(null,'%s',curdate(),'%s','%s','%s','%s','3','');",modeloProductoDetalle.getValueAt(i, 9).toString(),fecVen,modeloProductoDetalle.getValueAt(i, 1).toString(),idDet,cantLote));
                          System.out.println(String.format("insert into lote values(null,'%s',curdate(),'%s','%s','%s','%s','3','');",modeloProductoDetalle.getValueAt(i, 9).toString(),fecVen,modeloProductoDetalle.getValueAt(i, 1).toString(),idDet,cantLote));
                    }
                    idEntrada = id;

                } else {
                    control.EditarRegistro(String.format("UPDATE entradastock e SET e.`fecha`='%s', e.`idProveedor`=%s, e.`idOrdenDeCompra`=%s WHERE e.`idEntradaStock`=%s;", fecha, proveedor, ordenDeCompra == null ? "NULL" : ordenDeCompra, idEntrada));
                    
                    
                    for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
                        String fecVen=control.CoverFecha(modeloProductoDetalle.getValueAt(i, 10).toString());
                        double cantL=Double.parseDouble(modeloProductoDetalle.getValueAt(i, 11).toString())*Double.parseDouble(modeloProductoDetalle.getValueAt(i, 15).toString());
                        int cantForm=(int)cantL;
                        String cantLote=String.valueOf(cantForm);
                        
                        String tmp = modeloProductoDetalle.getValueAt(i, 0).toString();
                        if (tmp.equals("")) {
                            control.CrearRegistro(String.format("INSERT INTO detalleentradastock VALUES(NULL,%s,%s,%s,%s,%s,%s,'%s');", idEntrada, modeloProductoDetalle.getValueAt(i, 1).toString(), modeloProductoDetalle.getValueAt(i, 11).toString(), modeloProductoDetalle.getValueAt(i, 12).toString(), modeloProductoDetalle.getValueAt(i, 14).toString(), modeloProductoDetalle.getValueAt(i, 15).toString(), modeloProductoDetalle.getValueAt(i, 8).toString()));
                            String idDet = control.DevolverRegistroDto("SELECT d.idDetalleEntradaStock FROM detalleentradastock d where d.idEntradaStock='"+idEntrada+"' order by d.idDetalleEntradaStock desc limit 1;", 1);
                            control.CrearRegistro(String.format("insert into lote values(null,'%s',curdate(),'%s','%s','%s','%s','3','');",modeloProductoDetalle.getValueAt(i, 9).toString(),fecVen,modeloProductoDetalle.getValueAt(i, 1).toString(),idDet,cantLote));
                        }
                        else{
                          control.CrearRegistro(String.format("UPDATE detalleentradastock d SET d.`cantidad`=%s, d.`costo`=%s, d.`idAlmacen`=%s WHERE d.`idDetalleEntradaStock`=%s;", modeloProductoDetalle.getValueAt(i, 11).toString(), modeloProductoDetalle.getValueAt(i, 12).toString(), modeloProductoDetalle.getValueAt(i, 14).toString(), tmp));
                          control.CrearRegistro(String.format("update lote set nombre='%s', fecIn=curdate(), fecVen='%s', idproducto='%s', cant='%s' where idDetalleEntradaStock='%s';", modeloProductoDetalle.getValueAt(i, 9).toString(),fecVen,modeloProductoDetalle.getValueAt(i, 1).toString(),cantLote,tmp));
                        }
                      
                    }
                    control.EliminarRegistro("DELETE FROM detalleentradastock WHERE `idDetalleEntradaStock` IN (" + productosEliminados + ");");
                    control.EliminarRegistro("DELETE FROM lote WHERE `idDetalleEntradaStock` IN (" + productosEliminados + ");");
                }

                btnFacturar.setEnabled(true);
                btnActualizar.setEnabled(true);
                cargarDetalle();
                guardado = true;
                if (entradaDeStockFrame != null) {
                    entradaDeStockFrame.recargar();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto a la entrada de stock", "Aviso", JOptionPane.WARNING_MESSAGE);
                guardado = false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor", "Aviso", JOptionPane.WARNING_MESSAGE);
            guardado = false;
        }
        return guardado;
    }

    private void cargarDetalle() {
        control.LlenarJTabla(modeloProductoDetalle, "select d.iddetalleentradastock, p.idproduto,p.codProd,p.nomproducto,t.nomtip,p.composicion,pre.present,m.nommarc,d.Unidad, l.nombre,date_format(l.`fecVen`,'%d/%m/%Y') as fecha, \n " +
"d.cantidad,round(d.costo,2), round((d.cantidad* d.costo),2) AS total, a.idalmacen, d.cantReal \n " +
"from detalleentradastock d \n " +
"inner join lote l on l.iddetalleentradastock=d.iddetalleentradastock \n " +
", almacen a, produto p \n " +
"inner join marca m on p.idmarca=m.idmarca \n " +
"inner join tipoproducto t on t.idtipoproducto=p.idtipoproducto \n " +
"inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n " +
"\n" +
"                    where d.idproducto=p.idproduto \n " +
"                    and a.idalmacen=d.idalmacen \n" +
                    "AND d.idEntradaStock='" + idEntrada + "';", 16);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        btnFacturar.setEnabled(true);
        btnActualizar.setEnabled(true);
        calcularTotal();
    }

    public void cargarCabecera() {
        String[] cargarRegistro = control.cargarRegistro("SELECT e.`idEntradaStock`, e.`numero`, date_format(e.`fecha`,'%d-%m-%Y'), e.`idProveedor`, "
                + "e.`idOrdenDeCompra`, p.`Nombre`,e.`facturado`, e.`actualizado`  "
                + "FROM entradastock e, proveedor p "
                + "WHERE p.`idProveedor`=e.`idProveedor` AND e.`idEntradaStock`='" + idEntrada + "' ORDER BY e.`fecha`;", 8);
        ordenDeCompra = cargarRegistro[4];
        actualizado = cargarRegistro[7].equals("1");
        facturado = cargarRegistro[6].equals("1");
        btnPedido.setEnabled(!actualizado);
        btnProveedor.setEnabled(!actualizado);
        jDateChooser1.setEnabled(!actualizado);
        String[] datosOrdenDeCompra = control.cargarRegistro("SELECT `numero`,date_format(`fechaPedido`,'%d/%m/%Y') FROM `ordendecompra` WHERE `idOrdenDeCompra`='" + ordenDeCompra + "';", 2);
        if (datosOrdenDeCompra != null && datosOrdenDeCompra.length == 2) {
            txtNumeroPedido.setText(datosOrdenDeCompra[0]);
            txtFechaPedido.setText(datosOrdenDeCompra[1]);
        }
        txtNumero.setText(cargarRegistro[1]);
        //((JTextComponent) jDateChooser1.getDateEditor()).setText(cargarRegistro[2].replace("-", "/"));
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
String strFecha = cargarRegistro[2];
Date fecha = null;
try {

fecha = formatoDelTexto.parse(strFecha);
this.jDateChooser1.setDate(fecha);

} catch (ParseException ex) {

ex.printStackTrace();

}
        proveedor = cargarRegistro[3];
        txtProveedor.setText(cargarRegistro[5]);
        if (cargarRegistro[6].equals("1")) {
            lblFacturar.setText("Faturado");
            lblFacturar.setBackground(Color.red);
        } else {
            lblFacturar.setText("Pendiente Facturar");
            lblFacturar.setBackground(new Color(153, 255, 153));
        }
        if (cargarRegistro[7].equals("1")) {
            btnActualizar.setText("Deshacer Act.");
            btnActualizar.setMnemonic('D');
        } else {
            btnActualizar.setText("Actualizar");
            btnActualizar.setMnemonic('A');
        }
        cargarDetalle();
    }

    public void setEntradStock(String idEntradaStock) {
        this.idEntrada = idEntradaStock;
    }

    private void calcularTotal() {
        
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"

        double t = 0;
        for (int i = 0; i < modeloProductoDetalle.getRowCount(); i++) {
            t += Double.parseDouble(modeloProductoDetalle.getValueAt(i, 13).toString());
        }
        txtTotalFactura.setText(String.valueOf(t).replace(",", "."));
    }

    private void seleccionarPedido() {
        if (proveedor != null) {

            String almacen = map.get(cboAlmacen.getSelectedIndex()).toString();
            if (seleccionarOrdenDeCompra == null) {
                seleccionarOrdenDeCompra = new SeleccionarOrdenDeCompraDialog(null, true);
            }
            seleccionarOrdenDeCompra.setProveedor(proveedor);
            seleccionarOrdenDeCompra.setLocationRelativeTo(this);
            seleccionarOrdenDeCompra.setVisible(true);
            String[] datos = seleccionarOrdenDeCompra.getDatos();
            if (datos != null) {

                ordenDeCompra = datos[0];
                txtNumeroPedido.setText(datos[1]);
                txtFechaPedido.setText(datos[2]);
                control.Sql = "SELECT v.`ID`,v.`Codigo`, concat(v.`Producto`,' ',v.`Descripcion`), d.`cant`, d.`Costo`, (d.`cant`* d.`Costo`) as total FROM detalleproducto d, vta_catalogo v "
                        + "WHERE d.`idProduto`=v.`ID` AND d.`idOrdenDeCompra`='" + datos[0] + "' "
                        + "ORDER BY v.`Producto`;";
                System.out.println(control.Sql);
                ResultSet resultSet = control.DevolverRegistro(control.Sql);
                try {
                    while (resultSet.next()) {
                        modeloProductoDetalle.addRow(new String[]{"",resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), cboAlmacen.getSelectedItem().toString(), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), almacen});
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmRegistrarEntradasDeStok.class.getName()).log(Level.SEVERE, null, ex);
                }
                calcularTotal();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un proveedor para cargar sus pedidos");
        }

    }

    private void facturar() {
        if (lblFacturar.getBackground().equals(Color.red)) {
            JOptionPane.showMessageDialog(this, "La entrada de stock ya está facturado");
        } else {
            FacturarEntradaDeStockDialog facturarEntradaDeStock = new FacturarEntradaDeStockDialog(null, true);
            facturarEntradaDeStock.setLocationRelativeTo(null);
            facturarEntradaDeStock.setEntrada(idEntrada);
            facturarEntradaDeStock.setVisible(true);
            String resultado = facturarEntradaDeStock.getResultado();
            if (resultado.equals("success")) {
                lblFacturar.setText("Faturado");
                lblFacturar.setBackground(Color.red);
                facturado = true;
                if (entradaDeStockFrame != null) {
                    entradaDeStockFrame.recargar();
                }
            }
        }
    }

    private void actualizar() {
        String actualizadoVal = control.DevolverRegistroDto("SELECT `actualizado` FROM `entradastock` WHERE `idEntradaStock`='" + idEntrada + "';", 1);
        if (actualizadoVal.equals("0")) {
            int result = JOptionPane.showConfirmDialog(this, "Después de actualizar, no se permitirá la modificación de la entrada actual.\nLa actualización de esta entrada actualizará su almacén\n¿Confirma que desea continuar? ", "Atención", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                ResultSet detalleStockResult = control.DevolverRegistro("SELECT `idAlmacen`, `idProducto`, `cantidad`,`costo`, `cantReal`,`Unidad`, idDetalleEntradaStock FROM `detalleentradastock` WHERE `idEntradaStock`='" + idEntrada + "';");
                try {
                    while (detalleStockResult.next()) {
                        control.CrearRegistro(String.format("INSERT IGNORE INTO stock VALUES(%d,%d,%d);", detalleStockResult.getInt(1), detalleStockResult.getInt(2), 0));
                        control.EditarRegistro(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s WHERE s.`idAlmacen`=%d AND s.`idProducto`=%d;", (detalleStockResult.getDouble(3)*detalleStockResult.getInt(5)), detalleStockResult.getInt(1), detalleStockResult.getInt(2)));
                        System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s WHERE s.`idAlmacen`=%d AND s.`idProducto`=%d;", (detalleStockResult.getDouble(3)*detalleStockResult.getInt(5)), detalleStockResult.getInt(1), detalleStockResult.getInt(2)));
                        
                        if(detalleStockResult.getString(6).equals("Unidad")){
                        
                        control.EditarRegistro(String.format("UPDATE produto p SET p.`precioCompra`=%s WHERE p.`idProduto`=%d;", detalleStockResult.getString(4), detalleStockResult.getInt(2)));
                        System.out.println(String.format("UPDATE produto p SET p.`precioCompra`=%s WHERE p.`idProduto`=%d;", detalleStockResult.getString(4), detalleStockResult.getInt(2)));
                        }
                        control.EditarRegistro(String.format("update detunidprod d set d.costoCompra=%s where d.idProducto=%d and ((select u.nomuniddes from unidades u where u.idUnidades=d.idUnidades)='%s');", detalleStockResult.getString(4), detalleStockResult.getInt(2), detalleStockResult.getString(6)));
                        System.out.println(String.format("update detunidprod d set d.costoCompra=%s where d.idProducto=%d and ((select u.nomuniddes from unidades u where u.idUnidades=d.idUnidades)='%s');", detalleStockResult.getString(4), detalleStockResult.getInt(2), detalleStockResult.getString(6)));
                    
                    control.CrearRegistro(String.format("update lote set activo='1' where idDetalleEntradaStock='%s';", detalleStockResult.getString(7)));
                    }
                    control.EditarRegistro(String.format("UPDATE entradastock e SET e.`actualizado`=TRUE WHERE e.`idEntradaStock`=%s;", idEntrada));
                    control.EditarRegistro(String.format("UPDATE ordendecompra o SET o.`estado`='Entregado', o.`fechaRecibido`=CURDATE() WHERE o.`idOrdenDeCompra`='%s';", ordenDeCompra == null ? "" : ordenDeCompra));
                    btnActualizar.setText("Deshacer Act.");
                    btnActualizar.setMnemonic('D');
                    btnPedido.setEnabled(false);
                    btnProveedor.setEnabled(false);
                    jDateChooser1.setEnabled(false);
                    actualizado = true;
                    try {
                        FrmProductosBajosDeStock.cargarDatos();
                        frmListadoOrdenesdeCompra.cargarOrdenes();
                    } catch (Exception e) {
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmRegistrarEntradasDeStok.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (entradaDeStockFrame != null) {
                    entradaDeStockFrame.recargar();
                }
            }
        } else if (actualizadoVal.equals("1")) {//deshaciendo la actualización
            if (facturado) {
                int result = JOptionPane.showConfirmDialog(this, "La entrada de stock ha sido facturado.¿Desea continuar y deshacer la actualización de la entrada?", "Atención", JOptionPane.YES_NO_OPTION);
                if (result != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Deshacer la actualización de stock, restaurará el stock incrementado por la entrada de stock a su nivel anterior", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            int result = JOptionPane.showConfirmDialog(this, "¿Está seguro de deshacer la actualización de entrada de stock?", "Atención", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                ResultSet detalleStockResult = control.DevolverRegistro("SELECT `idAlmacen`, `idProducto`, `cantidad`, `cantReal`, `Unidad`,idDetalleEntradaStock FROM `detalleentradastock` WHERE `idEntradaStock`='" + idEntrada + "';");
                try {
                    while (detalleStockResult.next()) {
                        control.EditarRegistro(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s WHERE s.`idAlmacen`=%d AND s.`idProducto`=%d;", detalleStockResult.getDouble(3)*detalleStockResult.getInt(4), detalleStockResult.getInt(1), detalleStockResult.getInt(2)));
                        System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s WHERE s.`idAlmacen`=%d AND s.`idProducto`=%d;", detalleStockResult.getDouble(3)*detalleStockResult.getInt(4), detalleStockResult.getInt(1), detalleStockResult.getInt(2)));
                   
                    control.CrearRegistro(String.format("update lote set activo='3' where idDetalleEntradaStock='%s';", detalleStockResult.getString(6)));
                    }
                    control.EditarRegistro(String.format("UPDATE entradastock e SET e.`actualizado`=FALSE WHERE e.`idEntradaStock`=%s;", idEntrada));
                    control.EditarRegistro(String.format("UPDATE ordendecompra o SET o.`estado`='Por entregar', o.`fechaRecibido`=NULL WHERE o.`idOrdenDeCompra`='%s';", ordenDeCompra == null ? "" : ordenDeCompra));
                    btnActualizar.setText("Actualizar");
                    btnActualizar.setMnemonic('A');
                    btnPedido.setEnabled(true);
                    btnProveedor.setEnabled(true);
                    jDateChooser1.setEnabled(true);
                    actualizado = false;
                } catch (SQLException ex) {
                    Logger.getLogger(FrmRegistrarEntradasDeStok.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    FrmProductosBajosDeStock.cargarDatos();
                } catch (Exception e) {
                }
                if (entradaDeStockFrame != null) {
                    entradaDeStockFrame.recargar();
                }
            }
        }

    }

    private void seleccionarProducto() {
        if (!actualizado) {
            int row = tablaProductos.getSelectedRow();
            //"Id","Codigo","Codigo x Unid.", "Producto","Rubro", "Composición", "Presentación", "Laboratorio","Unidad","P. Unid Real","cant", "P. Compra"
            
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Costo", "Total", "idAlmacen","CantReal"
            String datos[] = new String[12];
            datos[0] = productosModel.getValueAt(row, 0).toString();//id
            datos[1] = productosModel.getValueAt(row, 1).toString();//CodProd
            datos[2] = productosModel.getValueAt(row, 2).toString();//CodUnid
            datos[3] = productosModel.getValueAt(row, 3).toString();//Producto
            datos[4] = productosModel.getValueAt(row, 4).toString();//Rubro
            datos[5] = productosModel.getValueAt(row, 5).toString();//Composicion
            datos[6] = productosModel.getValueAt(row, 6).toString();//Presentacion
            datos[7] = productosModel.getValueAt(row, 7).toString();//Laboratorio
            datos[8] = productosModel.getValueAt(row, 8).toString();//Unidad
            datos[9] = productosModel.getValueAt(row, 9).toString();//Precio Real Unidad
            datos[10] = productosModel.getValueAt(row, 10).toString();//Cant x Unidad
            datos[11] = productosModel.getValueAt(row, 11).toString();//Precio Compra

            if (datos != null) {
                
                nomProd=datos[3];
                rubro=datos[4];
                composicion=datos[5];
                presentacion=datos[6];
                laboratorio=datos[7];
                
                
                producto = datos[0];
                codProd= datos[1];
                lblProducto.setText(datos[6]+" "+datos[3]);
                Unidad=datos[8];
                CantReal=Integer.parseInt(datos[10]);
                
                try {
                    cboAlmacen.setSelectedIndex(0);
                } catch (Exception e) {
                }
                txtLote.setEditable(true);
                txtCosto.setEditable(true);
                txtCantidad.setEditable(true);
                cboAlmacen.setEnabled(false);
                cboUnidad.setEnabled(false);
                int buscar = buscarA(producto,Unidad);
                if (buscar >= 0) {
                    if (cboMoneda.getSelectedIndex() == 0) {
                        txtCosto.setText(modeloProductoDetalle.getValueAt(buscar, 12).toString());
                       // JOptionPane.showMessageDialog(null, "soles 1");
                               
                    }
                    else{
                    double cambio=Double.parseDouble(modeloProductoDetalle.getValueAt(buscar, 12).toString());
                    double tasa=Double.parseDouble(txtTasaDolar.getText());
                    cambio=cambio/tasa;
                   
                    txtCosto.setText(control.decimalFormat(cambio));
                   // JOptionPane.showMessageDialog(null, "dolares 1");
                }
                    
                } else {
                    if (cboMoneda.getSelectedIndex() == 0) {
                        txtCosto.setText(datos[11]);
                        //JOptionPane.showMessageDialog(null, "soles 2");
                    }
                    else{
                    double cambio=Double.parseDouble(datos[11]);
                    double tasa=Double.parseDouble(txtTasaDolar.getText());
                    cambio=cambio/tasa;
                    
                    txtCosto.setText(control.decimalFormat(cambio));
                  //  JOptionPane.showMessageDialog(null, "dolares 2");
                }
                    
                }
                txtCantidad.setText("1");
                txtLote.setText(ObtenLote(producto));
                txtLote.grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(this, "La entrada de stock está actualizada, no se pueden añadir lineas nuevas", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    
    private String ObtenLote(String idProd){
        String lote="Lote";
        lote=lote+" "+control.DevolverRegistroDto("SELECT count(l.idlote)+1 FROM lote l where idproducto='"+idProd+"';",1);
        return lote;
    }

    private void eliminarLinea() {
        if (!actualizado) {
            if (!facturado) {
                if (JOptionPane.showInternalConfirmDialog(this, "¿Confirma que deseal eliminar el producto seleccionado?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    int row = tablaProductoSinIngresar.getSelectedRow();
                    String id = modeloProductoDetalle.getValueAt(row, 0).toString();
                    if (!id.equals("")) {
                        productosEliminados += "," + id;
                    }
                    modeloProductoDetalle.removeRow(row);
                    calcularTotal();
                    btnEliminarProducto.setEnabled(false);
                    btnEditarProducto.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar productos de una entrada facturada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se puede eliminar productos de una entrada actualizada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void distribuirProductos() {
        if (txtCosto.getText().trim().length() > 0) {
            if (txtCantidad.getText().trim().length() > 0) {
                if (Double.parseDouble(txtCosto.getText()) > 0) {
                    if (Double.parseDouble(txtCantidad.getText()) > 0) {

                        double costoProducto = 0;
                        double cantidad = 1;
                        costoProducto = Double.parseDouble(txtCosto.getText());
                        cantidad = Double.parseDouble(txtCantidad.getText());

                        int index = tablaProductoSinIngresar.getSelectedRow();
                        modeloProductoDetalle.setValueAt(cboAlmacen.getSelectedItem().toString(), index, 4);
                        modeloProductoDetalle.setValueAt(format.format(costoProducto).replace(",", "."), index, 6);
                        modeloProductoDetalle.setValueAt(format.format(cantidad).replace(",", "."), index, 5);
                        modeloProductoDetalle.setValueAt(format.format(costoProducto * (cantidad)).replace(",", "."), index, 7);
                        modeloProductoDetalle.setValueAt(map.get(cboAlmacen.getSelectedIndex()).toString(), index, 8);

                        double cantidadOriginal = Double.parseDouble(datosDistribucionProductos[4]);
                        if (cantidadOriginal - cantidad > 0) {
                            double cantidadRestante = cantidadOriginal - cantidad;
                            double total = cantidadRestante * Double.parseDouble(datosDistribucionProductos[5]);
                            modeloProductoDetalle.addRow(new String[]{"", datosDistribucionProductos[1], datosDistribucionProductos[2], datosDistribucionProductos[3], format.format(cantidadRestante).replace(",", "."), datosDistribucionProductos[5], format.format(total).replace(",", "."), datosDistribucionProductos[6]});
                        }

                        btnAgregarProducto.setEnabled(true);
                        btnEditarProducto.setEnabled(true);
                        btnEliminarProducto.setEnabled(true);
                        txtBuscarProductoDetalle.setEditable(true);
                        tablaProductoSinIngresar.setEnabled(true);

                        lblProducto.setText(" ");
                        txtCosto.setText("0.00");
                        txtCantidad.setText("1");

                        txtTotal.setText("0.00");
                        txtCosto.setEditable(false);
                        txtCantidad.setEditable(false);
                        btnAgregarProducto.grabFocus();
                        try {
                            cboAlmacen.setSelectedIndex(0);
                        } catch (Exception e) {
                        }
                        txtBuscarProducto.requestFocus();
                        calcularTotal();
                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad debe de ser mayor o igual a 1");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El costo del producto debe de ser mayor a cero");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Ingrese la cantidad de productos");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese el costos del producto");
        }
    }

}
