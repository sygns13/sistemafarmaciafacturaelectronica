package jym.ferreteria.gui.internalframes;

/**
 * ** @author Omr
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
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import javax.swing.text.JTextComponent;
import javax.swing.SpinnerDateModel;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import static jym.ferreteria.clases.Controlador.Base;
import jym.ferreteria.gui.dialogs.CobrarVentaDialog;
import jym.ferreteria.gui.dialogs.SeleccionarClienteDialog;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LabelRenderer;
import jym.ferreteria.gui.FrmMain;
import jym.ferreteria.gui.dialogs.IniciarSesionAdmin;

public class FrmRegistrarVenta extends javax.swing.JInternalFrame {
    
    
    private IniciarSesionAdmin iniciarSesionA = null;
    
    private   CustomTableModel modeloProductoDetalle = new CustomTableModel();

    private CustomTableModel productosModel = new CustomTableModel();
    private CustomTableModel productosAlmacenModel = new CustomTableModel();
    private final Controlador control = new Controlador();

    private final DecimalFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());
     private final DecimalFormat format2 = new DecimalFormat("0", DecimalFormatSymbols.getInstance());
    private SeleccionarClienteDialog seleccionarCliente = null;
    private boolean tipoComprobanteListo;
    private String cliente;
    private String nomCliente,tipoCliente;
    private String ruc;
    private String producto;
    private String UnidadA;
    private String nomProd;
    private String rubro;
    private String composicion;
    private String presentacion;
    private String laboratorio;
    private String codprod;
    private String idAlmacen, Almacen;
    private String Unidad;
     private String codProd;
    private String datos[] = null;
    private boolean guardado = false;
    private boolean editando = false;
    private boolean auxLote=false;
    private String filtro="precxmenor";
    private List<double[]> listaVenta = new ArrayList();
    private int indexPrecios = 0;
    private int idVenta = 0,cantidadtemporal=0;
    private int CantReal=0;
    private final double precios[] = new double[2];
    private Map map = new HashMap();

    private FrmListadoVentas listadoVentas;
    private boolean facturado = false;
    private Border defaultBorder;
    private Border focusBorder = new LineBorder(new Color(255, 164, 0), 2);
    private List listaUnidades = new ArrayList();
    private String unidadDeMedidaProducto;
    
    private String tipoUsu="";
    private String idusu="";
    
   // public boolean auxG=false;

    public FrmRegistrarVenta(FrmListadoVentas listadoVentas, boolean auxG) throws ParseException {
        initComponents();
        userspeshal();
        
        
        //btnCobrar.setVisible(false);
        this.defaultBorder = this.jScrollPaneProductos.getBorder();
        Font font = new Font("Arial", Font.BOLD, 12);
  
        this.listadoVentas = this.listadoVentas;
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        this.tablaProductosDetalle.setComponentPopupMenu(this.jPopupMenu1);
        //Configuracion para la tabla de productos "Id","Codigo","Codigo x Unid.", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Menor", "Mayor", "P. Venta", "esBajoDeStock"
        //                                         "Id","Codigo","Codigo x Unid.", "Producto","Marca", "Unidad", "Menor", "Mayor", "P. Venta", "esBajoDeStock"
        this.productosModel.setColumnIdentifiers(new String[]{"Id","Codigo Gral","Codigo", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Prec. Unidad", "Cantidad", "Precio de Venta", "esBajoDeStock","Ubicación"});
        //productosModel.setColumnIdentifiers(new String[]{"Id", "Codigo","Categoría","Producto", "Marca","Unidad", "Código Und.", "Precio", "P. Compra", "esBajoDeStock"});
        this.productosAlmacenModel.setColumnIdentifiers(new String[]{"Id", "Almacén", "Cantidad","Cantidad Unitaria"});
        this.tablaProductos.setModel(this.productosModel);
        this.tablaAlmacen.setModel(this.productosAlmacenModel);
        this.control.hideTableColumn(this.tablaProductos, 0, 1 , 9, 10,12);
        //control.hideTableColumn(tablaAlmacen, 3);
        this.control.tableWidthColumn(this.tablaProductos, 60, 1);
        this.control.tableWidthColumn(this.tablaProductos, 50, 2);
        this.control.tableWidthColumn(this.tablaProductos, 80, 13);
        this.control.tableWidthColumn(this.tablaProductos, 250, 3);
        this.control.tableWidthColumn(this.tablaProductos, 150, 4,5);
        this.control.tableWidthColumn(this.tablaProductos, 150, 6,7,8);
        
        this.control.hideTableColumn(this.tablaAlmacen, 0);
        this.control.tableWidthColumn(this.tablaAlmacen, 250, 1);
        this.control.tableWidthColumn(this.tablaProductos, 110, 10);
        this.tablaProductos.setDefaultRenderer(Object.class, new ProductosLabelRenderer().setFont(font));
        this.tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        ProductosLabelRenderer labelRenderer = new ProductosLabelRenderer(SwingConstants.RIGHT);
        labelRenderer.setFont(new Font("Arial", Font.BOLD, 12));
        this.tablaProductos.getColumnModel().getColumn(9).setCellRenderer(labelRenderer);
        this.tablaProductos.getColumnModel().getColumn(10).setCellRenderer(labelRenderer);
        this.tablaProductos.getColumnModel().getColumn(11).setCellRenderer(labelRenderer);
        LabelRenderer labelRendererAlmacen = new LabelRenderer(SwingConstants.RIGHT);
        labelRenderer.setFont(new Font("Arial", Font.BOLD, 12));
        this.tablaAlmacen.getColumnModel().getColumn(2).setCellRenderer(labelRendererAlmacen);
        this.tablaAlmacen.getColumnModel().getColumn(2).setCellRenderer(labelRendererAlmacen);
        this.tablaAlmacen.getColumnModel().getColumn(3).setCellRenderer(labelRendererAlmacen);
 //       ckGeneral.setVisible(false);
          //  ckGeneral.setSelected(true);
        this.ckSinDescon.setVisible(false);
        this.btnGuardar.setVisible(false);
        //this.jLabel10.setVisible(false);
       //this.txtDescuento.setVisible(false);
       
       this.jButton2.setVisible(false);
        
        
        //jButton2.setVisible(false);
       this.tablaProductos.setColumnSelectionAllowed(false);
       
        actualizarHora();
       ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
     timer.scheduleAtFixedRate(tarea, 1, 1, TimeUnit.MINUTES);
        
        this.tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String dato=cboUnidad.getSelectedItem().toString();
                if (productosModel.getRowCount() > 0 && tablaProductos.getSelectedRow() >= 0) {
                    control.LlenarJTabla(productosAlmacenModel, String.format("SELECT a.`idAlmacen`, a.`nombre`, concat((REPLACE(format(s.`cantidadDisponible`/%s,2), ',', '')),space(1),'"+dato+"'),s.`cantidadDisponible`"
                            + " FROM stock s, almacen a WHERE s.`idAlmacen`=a.`idAlmacen` AND s.`idProducto`=%s;",productosModel.getValueAt(tablaProductos.getSelectedRow(), 10).toString(), productosModel.getValueAt(tablaProductos.getSelectedRow(), 0).toString()), 4);

                    try {
                        tablaAlmacen.getSelectionModel().addSelectionInterval(0, 0);
                    } catch (Exception ex) {
                    }
                } else {
                    control.LimTabla(productosAlmacenModel);
                }

            }
            
            
        });
        //cargarProductos();
        //if(ckPrecio.isSelected()){
          //  cargarProductosParaFactura();
        //}else{
            this.cargarProductos0();
            
            
            
        //}
        // Configuración para la tabla de detalle de venta

        this.lblNumeroVenta.setText(" " + this.control.nextId("venta"));
        this.tablaProductosDetalle.setModel(this.modeloProductoDetalle);
        //"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
        //"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"
        this.modeloProductoDetalle.setColumnIdentifiers(new String[]{"Id","idProd", "Código Prod", "Descripción producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento %", "Total", "idAlmacen","CantReal","Almacen","idLote"});
        //modeloProductoDetalle.setColumnIdentifiers(new String[]{"Id", "Código Prod", "Descripción producto", "Almacén", "Cantidad", "Precio", "Desc.", "Total", "Afecto", "idAlmacen", "Costo compra", "prec men", "prec may"});
        this.control.hideTableColumn(this.tablaProductosDetalle, 0,1, 15,16,17,18);
        this.control.tableWidthColumn(this.tablaProductosDetalle, 400, 3);
        this.control.tableWidthColumn(this.tablaProductosDetalle, 150, 4,5,6,7);
            this.control.hideTableColumn(this.tablaProductosDetalle,4,5,6,7);
        this.control.tableWidthColumn(this.tablaProductosDetalle, 120, 8,9,10);
        this.control.tableMaxWidthColumn(this.tablaProductosDetalle, 100, 11, 12,13,14);
        this.tablaProductosDetalle.setDefaultRenderer(Object.class, new LabelRenderer(SwingConstants.LEFT).setFont(font));

        LabelRenderer labelRenderer2 = new LabelRenderer(SwingConstants.RIGHT);
        labelRenderer2.setFont(font);
        //tablaProductosDetalle.getColumnModel().getColumn(3).setCellRenderer(labelRenderer2);
        this.tablaProductosDetalle.getColumnModel().getColumn(11).setCellRenderer(labelRenderer2);
        this.tablaProductosDetalle.getColumnModel().getColumn(12).setCellRenderer(labelRenderer2);
        this.tablaProductosDetalle.getColumnModel().getColumn(13).setCellRenderer(labelRenderer2);
        this.tablaProductosDetalle.getColumnModel().getColumn(14).setCellRenderer(labelRenderer2);
        this.tablaProductosDetalle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                btnEditarProducto.setEnabled(true);
                btnEliminarProducto.setEnabled(true);
            }
        });

        this.btnCliente.setMnemonic(KeyEvent.VK_C);

        this.jDateChooser1.setDate(new Date());

        this.registerKeyboardAction(agregarClienteAction, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.setClientePorDefecto();
        this.leearUnidades();
        this.control.LimTabla(this.modeloProductoDetalle);
        this.tipoComprobanteListo=true;
        this.LlenaUnidades();
        
        if(auxG==true){
        this.guardar();
        
        }
        
        
        this.txtFecVen.setEditable(false);
        this.txtCantLote.setEditable(false);
        this.cbsLote.setEnabled(false);
        
        this.jLabel19.setVisible(false);
        this.cbsLoteAux.setVisible(false);
        
        this.jLabel18.setVisible(false);
        this.txtBuscarDetalle.setVisible(false);
        

       
       this.tipoUsu=Accesos.getInstance().getTipoUsuario();
       this.btnActivarDto.setVisible(false);
       

 
    }
    
    public void userspeshal(){
    idusu=Accesos.getInstance().getIdUsuario();

    }
    
    
     final Runnable tarea = new Runnable() {
  public void run() {
    Date datenow = Calendar.getInstance().getTime();
      SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
      jHoraVta.setModel(smb);
        
      //System.out.println(smb);
      jHoraVta.setFont(new java.awt.Font("Tahoma", 1, 11));
      JSpinner.DateEditor d = new JSpinner.DateEditor(jHoraVta, "HH:mm");
      jHoraVta.setEditor(d);
     // System.out.println(d);
  }
};
     
     public void actualizarHora(){
      /*   Date date= new Date();
     SpinnerDateModel sm =  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
jHoraVta = new javax.swing.JSpinner(sm);

System.out.println(sm);
JSpinner.DateEditor de= new JSpinner.DateEditor(jHoraVta,"HH:mm");
jHoraVta.setEditor(de);
System.out.println(de);*/
      
      Date datenow = Calendar.getInstance().getTime();
      SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
      jHoraVta.setModel(smb);
        
     // System.out.println(smb);
      jHoraVta.setFont(new java.awt.Font("Tahoma", 1, 11));
     JSpinner.DateEditor d = new JSpinner.DateEditor(jHoraVta, "HH:mm");
     jHoraVta.setEditor(d);
     // System.out.println(d);
     
  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jScrollPaneAlmacen = new javax.swing.JScrollPane();
        tablaAlmacen = new javax.swing.JTable();
        cboUnidad = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtnumdoc = new javax.swing.JTextField();
        lbltipodoc = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblNumeroVenta = new javax.swing.JLabel();
        lblNumeroComprobante = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Date date= new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jHoraVta = new javax.swing.JSpinner(sm);
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbsLote = new javax.swing.JComboBox();
        txtFecVen = new javax.swing.JTextField();
        txtCantLote = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblAlmacen = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtBuscarDetalle = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnEditarProducto1 = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnActivarDto = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPaneDetalles = new javax.swing.JScrollPane();
        tablaProductosDetalle = new javax.swing.JTable();
        ckSinDescon = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSubTotalInafecto = new javax.swing.JTextField();
        txtTotalVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnBorrarT = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cbsLoteAux = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Venta de Productos");
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(1340, 710));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        lblBuscar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblBuscar.setText("Buscar");
        lblBuscar.setName("lblBuscar"); // NOI18N

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

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Cliente: ");
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("N° de Documento Cliente:");
        jLabel1.setName("jLabel1"); // NOI18N

        txtnumdoc.setName("txtnumdoc"); // NOI18N
        txtnumdoc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtnumdocAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtnumdoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumdocKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumdocKeyTyped(evt);
            }
        });

        lbltipodoc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbltipodoc.setForeground(new java.awt.Color(204, 0, 51));
        lbltipodoc.setText(" ");
        lbltipodoc.setName("lbltipodoc"); // NOI18N
        lbltipodoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbltipodocKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnumdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbltipodoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPaneAlmacen)
                    .addComponent(jScrollPaneProductos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(622, 622, 622)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(622, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel1)
                        .addComponent(txtnumdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbltipodoc))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addComponent(jLabel21)
                    .addContainerGap(124, Short.MAX_VALUE)))
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Fecha: ");
        jLabel5.setName("jLabel5"); // NOI18N

        jDateChooser1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jDateChooser1.setName("jDateChooser1"); // NOI18N
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Cliente: ");
        jLabel6.setName("jLabel6"); // NOI18N

        txtCliente.setEditable(false);
        txtCliente.setName("txtCliente"); // NOI18N

        btnCliente.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        btnCliente.setText("F3");
        btnCliente.setName("btnCliente"); // NOI18N
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("N° de Venta:");
        jLabel3.setName("jLabel3"); // NOI18N

        lblNumeroVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNumeroVenta.setText("  ");
        lblNumeroVenta.setName("lblNumeroVenta"); // NOI18N

        lblNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNumeroComprobante.setText("Sin comprobante:");
        lblNumeroComprobante.setName("lblNumeroComprobante"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Hora:");
        jLabel11.setName("jLabel11"); // NOI18N

        jHoraVta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jHoraVta.setName("jHoraVta"); // NOI18N
        JSpinner.DateEditor de= new JSpinner.DateEditor(jHoraVta,"HH:mm");
        jHoraVta.setEditor(de);
        jHoraVta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jHoraVtaKeyTyped(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Producto"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Lote");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Fecha Vencimiento");
        jLabel16.setName("jLabel16"); // NOI18N

        cbsLote.setName("cbsLote"); // NOI18N
        cbsLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsLoteActionPerformed(evt);
            }
        });

        txtFecVen.setName("txtFecVen"); // NOI18N

        txtCantLote.setName("txtCantLote"); // NOI18N
        txtCantLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantLoteActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Cant. Unidades");
        jLabel17.setName("jLabel17"); // NOI18N

        lblAlmacen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAlmacen.setText("Almacén: ");
        lblAlmacen.setName("lblAlmacen"); // NOI18N

        lblProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(18, 22, 82));
        lblProducto.setText(" ");
        lblProducto.setName("lblProducto"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad:");
        jLabel8.setName("jLabel8"); // NOI18N

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCantidad.setText("1");
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
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
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

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Precio:");
        jLabel7.setName("jLabel7"); // NOI18N

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

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Desc %: ");
        jLabel10.setName("jLabel10"); // NOI18N

        txtDescuento.setEditable(false);
        txtDescuento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtDescuento.setText("0");
        txtDescuento.setName("txtDescuento"); // NOI18N
        txtDescuento.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDescuentoCaretUpdate(evt);
            }
        });
        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Total: ");
        jLabel9.setName("jLabel9"); // NOI18N

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });

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

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Agregar Producto:");
        jLabel18.setName("jLabel18"); // NOI18N

        btnEditarProducto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/font_red_delete.png"))); // NOI18N
        btnEditarProducto1.setName("btnEditarProducto1"); // NOI18N
        btnEditarProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProducto1ActionPerformed(evt);
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

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Max Por Lote:");
        jLabel20.setName("jLabel20"); // NOI18N

        btnActivarDto.setText("Activar Dto.");
        btnActivarDto.setName("btnActivarDto"); // NOI18N
        btnActivarDto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarDtoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbsLote, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecVen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(btnActivarDto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(682, 682, 682)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addComponent(txtCantLote, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(116, 116, 116)
                        .addComponent(btnEditarProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActivarDto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txtBuscarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbsLote, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFecVen, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.CENTER)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCantidad)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAlmacen)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtCantLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProducto))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnCancelarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

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

        ckSinDescon.setText("Sin Descontar");
        ckSinDescon.setName("ckSinDescon"); // NOI18N
        ckSinDescon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckSinDesconActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-document-save-icon.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Guardar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setText("Sub total:");
        jLabel13.setName("jLabel13"); // NOI18N

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

        txtTotalVenta.setEditable(false);
        txtTotalVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTotalVenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalVenta.setText("S/ 0.00");
        txtTotalVenta.setName("txtTotalVenta"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel12.setText("Total venta: ");
        jLabel12.setName("jLabel12"); // NOI18N

        btnCobrar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/coin-add-icon.png"))); // NOI18N
        btnCobrar.setMnemonic('C');
        btnCobrar.setText("Cobrar");
        btnCobrar.setEnabled(false);
        btnCobrar.setName("btnCobrar"); // NOI18N
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

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

        jButton2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Add.png"))); // NOI18N
        jButton2.setText("Cargar COT.");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnBorrarT.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnBorrarT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/font_red_delete.png"))); // NOI18N
        btnBorrarT.setMnemonic('N');
        btnBorrarT.setText("deshacer Vta");
        btnBorrarT.setName("btnBorrarT"); // NOI18N
        btnBorrarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarTActionPerformed(evt);
            }
        });

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

        cbsLoteAux.setName("cbsLoteAux"); // NOI18N

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Aux Lote:");
        jLabel19.setName("jLabel19"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPaneDetalles)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(61, 61, 61)
                        .addComponent(cbsLoteAux, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(384, 384, 384)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrarT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(49, 49, 49))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ckSinDescon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSubTotalInafecto, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(txtTotalVenta))
                                .addContainerGap())))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSubTotalInafecto)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(cbsLoteAux))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckSinDescon)
                        .addGap(41, 41, 41)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrarT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNumeroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jHoraVta, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCliente))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jHoraVta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliente)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNumeroVenta)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProductosDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosDetalleMouseClicked
        this.btnEditarProducto.setEnabled(true);
        this.btnEliminarProducto.setEnabled(true);
        if (evt.getClickCount() > 1 && !this.facturado) {
            this.cargarEditar();
        }
    }//GEN-LAST:event_tablaProductosDetalleMouseClicked

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        this.seleccionarCliente();
    }//GEN-LAST:event_btnClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*if(!guardado){
            if(modeloProductoDetalle.getRowCount()==0){
                control.ejecutar(String.format("DELETE FROM venta WHERE `idVenta`=%s;", idVenta));
                btnNuevo.doClick();
                this.dispose();
            }else{
                eliminartodo();
                control.ejecutar(String.format("DELETE FROM venta WHERE `idVenta`=%s;", idVenta));
                btnNuevo.doClick();
                
            }
        } 
        */
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

            //eliminartodo();
        this.producto = null;
        this.editando=false;
        this.lblProducto.setText(" ");
        this.lblAlmacen.setText(" ");
        this.txtCantidad.setText("1");
        this.txtDescuento.setText("0.00");
        this.txtPrecio.setText("0.00");
        this.txtCantidad.setEditable(false);
        this.txtPrecio.setEditable(false);
        this.txtDescuento.setEditable(false);
        this.tablaProductosDetalle.setEnabled(true);
        this.txtBuscarDetalle.setEnabled(true);
        this.jScrollPaneProductos.setBorder(defaultBorder);
        this.jScrollPaneAlmacen.setBorder(defaultBorder);
        this.txtBuscarProducto.setText("");
        this.txtBuscarDetalle.setText("");
        //this.txtBuscarDetalle.requestFocus();
        this.cboUnidad.setSelectedIndex(0);
        
        
        this.lbltipodoc.setText("");
        this.txtnumdoc.setText("");
        
        this.txtnumdoc.requestFocus();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (this.cliente != null) {
            this.guardar();
            JOptionPane.showMessageDialog(this, "La venta se ha guardado correctamente");
            if (this.listadoVentas != null) {
                listadoVentas.recargar();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione el cliente para la venta");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (this.listadoVentas != null) {
            this.listadoVentas.recargar();
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        if (this.cliente != null) {
            this.guardar();
        if (this.guardado) {

            this.cobrar();
        }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione el cliente para la venta");
        }
        
        
        
        
        
        
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void txtBuscarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.productosModel.getRowCount() > 0) {
                try {
                    this.jScrollPaneProductos.setBorder(this.focusBorder);
                    this.jScrollPaneAlmacen.setBorder(this.defaultBorder);
                    this.tablaProductos.getSelectionModel().setSelectionInterval(0, 0);
                    this.tablaProductos.requestFocus();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_txtBuscarProductoKeyPressed

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased

            this.cargarProductos();

    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (evt.getClickCount() >= 2) {
            this.seleccionarProducto();
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void tablaProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMousePressed
//        cargarPopup(evt)
    }//GEN-LAST:event_tablaProductosMousePressed

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && this.tablaProductos.getSelectedRow() >= 0) {
            if (this.tablaAlmacen.getRowCount() == 0) {
                this.seleccionarProducto();
            } else {

                try {
                    this.tablaAlmacen.getSelectionModel().addSelectionInterval(0, 0);
                    this.jScrollPaneProductos.setBorder(this.defaultBorder);
                    this.jScrollPaneAlmacen.setBorder(this.focusBorder);
                    
                } catch (Exception ex) {

                }
                this.tablaAlmacen.requestFocus();
                evt.consume();
            }

        }
    }//GEN-LAST:event_tablaProductosKeyPressed

    private void tablaAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlmacenMouseClicked
        if (this.tablaProductos.getSelectedRow() >= 0) {
            if (this.tablaAlmacen.getSelectedRow() >= 0 && evt.getClickCount() > 1) {
                this.seleccionarProducto();
            }
        }
    }//GEN-LAST:event_tablaAlmacenMouseClicked

    private void tablaAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAlmacenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.seleccionarProducto();

        }
    }//GEN-LAST:event_tablaAlmacenKeyPressed

    private void txtBuscarProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarProductoAncestorAdded
        //txtBuscarProducto.requestFocus();
    }//GEN-LAST:event_txtBuscarProductoAncestorAdded

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
         this.NuevaVta();
         this.guardar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscarProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProductoFocusGained
        this.jScrollPaneProductos.setBorder(this.defaultBorder);
        this.jScrollPaneAlmacen.setBorder(this.defaultBorder);
    }//GEN-LAST:event_txtBuscarProductoFocusGained

        private void txtSubTotalInafectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalInafectoActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_txtSubTotalInafectoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CargarCotizacion cc = new CargarCotizacion();
        cc.setFrmVenta(this);
        FrmMain.escritorio.add(cc);
        cc.toFront();
        cc.setVisible(true);
        cc.setLocation(300, 120);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
     
    }//GEN-LAST:event_formInternalFrameClosed

    private void ckSinDesconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckSinDesconActionPerformed

            this.SinDEscontar(this.ckSinDescon.isSelected());

        
    }//GEN-LAST:event_ckSinDesconActionPerformed

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
        this.cargarProductos();
        //JOptionPane.showMessageDialog(null, dato);// TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadActionPerformed

    private void cboUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboUnidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadKeyPressed

    private void btnBorrarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTActionPerformed
    if (JOptionPane.showInternalConfirmDialog(this, "¿Confirma que deseal eliminar toda la Lista de productos vendidos e Iniciar una Nueva Venta?", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
        this.eliminartodo();
        this.guardar();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarTActionPerformed

    private void jHoraVtaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHoraVtaKeyTyped
        control.AnularTecl(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jHoraVtaKeyTyped

    private void jDateChooser1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyTyped
      control.AnularTecl(evt);  // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1KeyTyped

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        this.producto = null;
        this.editando=false;
        this.lblProducto.setText(" ");
        this.lblAlmacen.setText(" ");
        this.txtCantidad.setText("1");
        this.txtDescuento.setText("0.00");
        this.txtPrecio.setText("0.00");
        this.txtCantidad.setEditable(false);
        this.txtPrecio.setEditable(false);
        this.txtDescuento.setEditable(false);
        this.tablaProductosDetalle.setEnabled(true);
        this.txtBuscarDetalle.setEnabled(true);
        this.jScrollPaneProductos.setBorder(defaultBorder);
        this.jScrollPaneAlmacen.setBorder(defaultBorder);
        this.txtBuscarProducto.setText("");
        this.txtBuscarDetalle.setText("");
        //this.txtBuscarDetalle.requestFocus();
        this.cboUnidad.setSelectedIndex(0);
        this.cbsLote.removeAllItems();
        this.cbsLoteAux.removeAllItems();
        this.txtCantLote.setText("");
        this.txtFecVen.setText("");
        this.cbsLote.setEnabled(false);
        this.btnActivarDto.setVisible(false);
        
        this.lbltipodoc.setText("");
        this.txtnumdoc.setText("");
        this.txtnumdoc.requestFocus();
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (!this.facturado) {
            this.eliminarLinea();
            this.guardar();
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, no se pueden eliminar los productos", "Mensaje", 2);
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        if (!facturado) {
            //eliminarLinea();
            this.guardar();

            this.cargarEditar();
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, ya no se pueden modificar los productos");
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnEditarProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProducto1ActionPerformed
        if (JOptionPane.showInternalConfirmDialog(this, "¿Confirma que deseal eliminar toda la Lista de productos vendidos e Iniciar una Nueva Venta?", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            this.eliminartodo();
            this.guardar();
        }
    }//GEN-LAST:event_btnEditarProducto1ActionPerformed

    private void txtBuscarDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDetalleKeyReleased
        // buscarProducto();
    }//GEN-LAST:event_txtBuscarDetalleKeyReleased

    private void txtBuscarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //JOptionPane.showMessageDialog(null, txtBuscarProductoDetalle.getText());
            this.PasarDirecto();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDetalleKeyPressed

    private void txtBuscarDetalleAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarDetalleAncestorAdded
//        this.txtBuscarDetalle.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDetalleAncestorAdded

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyTyped

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        this.calcularCostoPorProducto1();    // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (this.txtCantidad.getText().trim().length() > 0)) {
            if (this.txtDescuento.getText().trim().length()==0){
                this.txtDescuento.setText("0");
            }
            Double dco=Double.parseDouble(this.txtDescuento.getText());

            if(dco<0 || dco>100){
                JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                    + " de 0 a 100");

            }
            else{
                if (this.idVenta == 0) {
                    this.guardar();

                }
                this.agregarProducto();
                System.out.println("2");
                this.guardar();
                System.out.println("3");
            }
        }
    }//GEN-LAST:event_txtTotalKeyPressed

    private void txtTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTotalCaretUpdate
        //calcularCostoPorProducto1();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalCaretUpdate

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        control.decimal(evt, this.txtDescuento.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
        this.calcularCostoPorProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoKeyReleased

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
        if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (this.txtCantidad.getText().trim().length() > 0)) {
           /* if (this.txtDescuento.getText().trim().length()==0){
                this.txtDescuento.setText("0");
            }
            Double dco=Double.parseDouble(this.txtDescuento.getText());

            if(dco<0 || dco>100){
                JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                    + " de 0 a 100");

            }
            else{
                if (this.idVenta == 0) {
                    this.guardar();

                }
                this.agregarProducto();
                System.out.println("2");
                this.guardar();
                System.out.println("3");
            }*/

            if (this.txtDescuento.getText().trim().length()==0){
                this.txtDescuento.setText("0");
            }
            double dco=Double.parseDouble(this.txtDescuento.getText());

            if(dco<0 || dco>100){
                JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                    + " de 0 a 100");

            }
            else{
                int CantR=this.CantReal;
                double cant=Double.parseDouble(this.txtCantidad.getText());
                double prueba=0;
                prueba=CantR*cant;
                if(prueba%1==0 && prueba>0){
                    int cantVenta=(int)prueba;
                    double cantLoteFis=Double.parseDouble(txtCantLote.getText());
                    int cantLote=(int)cantLoteFis;
                    if(cantVenta>cantLote){
                        JOptionPane.showMessageDialog(this, "La Cantidad Total de Unidades ingresada supera a la cantidad del Lote, por favor seleccione a lo sumo la misma cantidad del lote, \n"
                                + " y si desea mas productos similares, cargue luego el mismo producto de otros lotes");
                        this.txtCantidad.requestFocus();
                    }
                    else{
                    if (this.idVenta == 0) {
                        this.guardar();

                    }
                    this.agregarProducto();
                    //System.out.println("2");
                    this.guardar();
                    //System.out.println("3");
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, "La Cantidad Asignada es Incorrecta");
                    this.txtCantidad.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txtDescuentoKeyPressed

    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void txtDescuentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescuentoCaretUpdate

    }//GEN-LAST:event_txtDescuentoCaretUpdate

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        this.control.decimal(evt, this.txtPrecio.getText());
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.indexPrecios == 0) {
                this.indexPrecios = 1;
                this.txtPrecio.setText("" + this.control.decimalFormat(this.precios[this.indexPrecios]));
            } else {
                this.indexPrecios = 0;
                this.txtPrecio.setText("" + this.control.decimalFormat(this.precios[this.indexPrecios]));
            }
        }
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (this.txtCantidad.getText().trim().length() > 0)) {
            if (this.txtDescuento.getText().trim().length()==0){
                this.txtDescuento.setText("0");
            }
            if (this.txtPrecio.getText().trim().length()==0){
                this.txtPrecio.setText("0.00");
            }
            double dco=Double.parseDouble(this.txtDescuento.getText());

            if(dco<0 || dco>100){
                JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                    + " de 0 a 100");

            }
            else{
                if (this.idVenta == 0) {
                    this.guardar();

                }
                this.agregarProducto();
                //System.out.println("2");
                this.guardar();
                //System.out.println("3");
            }
        }
    }//GEN-LAST:event_txtPrecioKeyPressed

    private void txtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusGained
        double costoProducto = 0;
        double descuento = 0;
        double cantidad = 1;

        try {
            cantidad = Double.parseDouble(this.txtCantidad.getText().replace(",", ""));
        } catch (Exception e) {
        }
        try {
            costoProducto = Double.parseDouble(this.txtPrecio.getText().replace(",", ""));
        } catch (Exception e) {
        }
        try {
            descuento = Double.parseDouble(this.txtDescuento.getText().replace(",", ""));
        } catch (Exception e) {
        }
        costoProducto = costoProducto - descuento;
        //txtTotal.setText(control.decimalFormat(costoProducto * cantidad));
    }//GEN-LAST:event_txtPrecioFocusGained

    private void txtPrecioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPrecioCaretUpdate
        //calcularCostoPorProducto();
        //photosmart D110
    }//GEN-LAST:event_txtPrecioCaretUpdate

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        control.decimal(evt, this.txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        this.calcularCostoPorProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (this.txtCantidad.getText().trim().length() > 0)) {
            if (this.txtDescuento.getText().trim().length()==0){
                this.txtDescuento.setText("0");
            }
            double dco=Double.parseDouble(this.txtDescuento.getText());

            if(dco<0 || dco>100){
                JOptionPane.showMessageDialog(null, "Error El Descuento debe ser ingresado en un rango numérico"
                    + " de 0 a 100");

            }
            else{
                int CantR=this.CantReal;
                double cant=Double.parseDouble(this.txtCantidad.getText());
                double prueba=0;
                prueba=CantR*cant;
                if(prueba%1==0 && prueba>0){
                    int cantVenta=(int)prueba;
                    double cantLoteFis=Double.parseDouble(txtCantLote.getText());
                    int cantLote=(int)cantLoteFis;
                    if(cantVenta>cantLote){
                        JOptionPane.showMessageDialog(this, "La Cantidad Total de Unidades ingresada supera a la cantidad del Lote, por favor seleccione a lo sumo la misma cantidad del lote, \n"
                                + " y si desea mas productos similares, cargue luego el mismo producto de otros lotes");
                        this.txtCantidad.requestFocus();
                    }
                    else{
                    if (this.idVenta == 0) {
                        this.guardar();

                    }
                    this.agregarProducto();
                    //System.out.println("2");
                    this.guardar();
                    //System.out.println("3");
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, "La Cantidad Asignada es Incorrecta");
                    this.txtCantidad.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        double costoProducto = 0;
        double descuento = 0;
        double cantidad = 1;

        try {
            cantidad = Double.parseDouble(this.txtCantidad.getText().replace(",", ""));
        } catch (Exception e) {
        }
        try {
            descuento = Double.parseDouble(this.txtDescuento.getText().replace(",", ""));
        } catch (Exception e) {
        }
        if (cantidad < 1) {

            this.txtPrecio.setText(this.control.decimalFormat(this.precios[0]));
            costoProducto = this.precios[1];
            costoProducto = costoProducto - descuento;
            this.indexPrecios = 0;

        } else {
            if (!this.listaUnidades.contains(this.unidadDeMedidaProducto)) {
                this.txtPrecio.setText(this.control.decimalFormat(this.precios[0]));
                costoProducto = this.precios[0];
                costoProducto = costoProducto - descuento;
                this.indexPrecios = 0;
            } else {
                this.txtPrecio.setText(this.control.decimalFormat(this.precios[1]));
                costoProducto = this.precios[1];
                costoProducto = costoProducto - descuento;
                this.indexPrecios = 1;
            }
        }
        //txtTotal.setText(control.decimalFormat(costoProducto * cantidad));
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        if (this.txtPrecio.getText().trim().length() == 0) {
            this.txtPrecio.grabFocus();
        }
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate

    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantLoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantLoteActionPerformed

    private void cbsLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsLoteActionPerformed
        cararDatosLote();        // TODO add your handling code here:
    }//GEN-LAST:event_cbsLoteActionPerformed

    private void btnActivarDtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarDtoActionPerformed
this.iniciarsesionDescuento();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActivarDtoActionPerformed

    private void txtnumdocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumdocKeyPressed
    int num=this.txtnumdoc.getText().trim().length();
        if (evt.getKeyChar() == 10 && num >= 0) {
            if(num<8){
                JOptionPane.showMessageDialog(null, "Complete los dígitos necesarios para buscar un Cliente");
            }else{
                try {
                    this.buscarCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(FrmRegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumdocKeyPressed

    private void txtnumdocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumdocKeyTyped
    control.Longitudtx(this.txtnumdoc, evt, 15);        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumdocKeyTyped

    private void txtnumdocAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtnumdocAncestorAdded
    this.txtnumdoc.requestFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txtnumdocAncestorAdded

    private void lbltipodocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbltipodocKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbltipodocKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivarDto;
    private javax.swing.JButton btnBorrarT;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnCliente;
    public javax.swing.JButton btnCobrar;
    public javax.swing.JButton btnEditarProducto;
    public javax.swing.JButton btnEditarProducto1;
    public javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboUnidad;
    private javax.swing.JComboBox<String> cbsLote;
    private javax.swing.JComboBox<String> cbsLoteAux;
    private javax.swing.JCheckBox ckSinDescon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JSpinner jHoraVta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPaneAlmacen;
    private javax.swing.JScrollPane jScrollPaneDetalles;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private javax.swing.JLabel lblAlmacen;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblNumeroComprobante;
    private javax.swing.JLabel lblNumeroVenta;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lbltipodoc;
    private javax.swing.JTable tablaAlmacen;
    private javax.swing.JTable tablaProductos;
    public javax.swing.JTable tablaProductosDetalle;
    public javax.swing.JTextField txtBuscarDetalle;
    public javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCantLote;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtFecVen;
    private javax.swing.JTextField txtPrecio;
    public javax.swing.JTextField txtSubTotalInafecto;
    private javax.swing.JTextField txtTotal;
    public javax.swing.JTextField txtTotalVenta;
    private javax.swing.JTextField txtnumdoc;
    // End of variables declaration//GEN-END:variables

    
     public void iniciarsesionDescuento(){
        if (this.iniciarSesionA == null) {
            this.iniciarSesionA = new IniciarSesionAdmin(null, true);
        }
        this.iniciarSesionA.setLocationRelativeTo(this);
        this.iniciarSesionA.setVisible(true);
        this.iniciarSesionA.limpiar();
        
        boolean result = this.iniciarSesionA.getResult();
        if (result != false) {
              this.txtDescuento.setEditable(true);
        }
    }
    private void cancelar() {
        //control.ejecutar(String.format("DELETE FROM venta WHERE `idVenta`=%s;", idVenta));
        this.lblNumeroVenta.setText(" " + control.nextId("venta"));
        this.txtCliente.setText("");
        this.lblProducto.setText(" ");
        this.txtPrecio.setText("0.00");
        this.txtDescuento.setText("0");
        this.txtCantidad.setText("1");
        this.lblNumeroComprobante.setText(" ");
        this.cliente = null;
        this.producto = null;
        //ckGeneral.setSelected(true);
        this.ckSinDescon.setSelected(false);
        this.control.LimTabla(modeloProductoDetalle);
        this.txtBuscarDetalle.setText("");
        this.jDateChooser1.setDate(new Date());
        this.btnEditarProducto.setEnabled(false);
        this.btnEliminarProducto.setEnabled(false);
        this.txtPrecio.setEditable(false);
        this.txtDescuento.setEditable(false);
        this.txtCantidad.setEditable(false);
        this.jScrollPaneProductos.setBorder(defaultBorder);
        this.jScrollPaneAlmacen.setBorder(defaultBorder);
        this.cboUnidad.setSelectedIndex(0);
        //this.txtBuscarDetalle.requestFocus();
        this.btnActivarDto.setVisible(false);
        if (!this.guardado) {
            this.idVenta = 0;
        }
        if (this.idVenta != 0) {
            this.cargarVenta();
        } else {
            this.setClientePorDefecto();

        }
        this.lbltipodoc.setText("");
        this.txtnumdoc.setText("");
        this.txtnumdoc.requestFocus();
    }
    
    private void calcularCostoPorProducto1() {
        double precioTotal = 0;
        double descuento = 0;
        double cantidad = 1;

        try {
            cantidad = Double.parseDouble(this.txtCantidad.getText().replace(",", ""));
            
        } catch (NumberFormatException e) {

        }
        try {
            precioTotal = Double.parseDouble(this.txtTotal.getText().replace(",", ""));
        } catch (NumberFormatException e) {
        }
        try {
            descuento = Double.parseDouble(this.txtDescuento.getText().replace(",", ""));
            //descuento=(descuento*precioProducto)/100;
            descuento=descuento/100;
        } catch (NumberFormatException e) {

        }

        //precioProducto = precioProducto - descuento;
        precioTotal=precioTotal/cantidad;
        

        this.txtPrecio.setText(this.control.decimalFormat(precioTotal/(1-descuento)));
        
    }

    private void calcularCostoPorProducto() {
        double precioProducto = 0;
        double descuento = 0;
        double cantidad = 1;

        try {
            cantidad = Double.parseDouble(this.txtCantidad.getText().replace(",", ""));
            
        } catch (NumberFormatException e) {

        }
        try {
            precioProducto = Double.parseDouble(this.txtPrecio.getText().replace(",", ""));
        } catch (NumberFormatException e) {
        }
        try {
            descuento = Double.parseDouble(this.txtDescuento.getText().replace(",", ""));
            descuento=(descuento*precioProducto)/100;
        } catch (NumberFormatException e) {

        }

        precioProducto = precioProducto - descuento;

       this.txtTotal.setText(this.control.decimalFormat(precioProducto * cantidad));
    }

    public void agregarProductoCotizacion(String IDcliente, String IDcot) {
        this.cliente = IDcliente;
        this.guardado=false;
        this.guardar();
        for (int i = 0; i < this.tablaProductosDetalle.getRowCount(); i++) {
            double descuento = 0;
            double costoProducto = 0;
            double cantidad = 1;
            int cantR=1;
            this.editando = false;
            costoProducto = Double.parseDouble(this.tablaProductosDetalle.getValueAt(i, 6).toString().replace(",", ""));
            descuento = 0;
            
            cantidad = Double.parseDouble(this.tablaProductosDetalle.getValueAt(i, 5).toString().replace(",", ""));
            cantR = Integer.parseInt(this.tablaProductosDetalle.getValueAt(i, 9).toString());
            
            double stockDisponible = getStockDisponible(this.tablaProductosDetalle.getValueAt(i, 1).toString(), this.tablaProductosDetalle.getValueAt(i, 8).toString());
            boolean esGrabado = false;
            System.out.println(stockDisponible+"--"+cantidad+"--"+cantR);
            if (stockDisponible - (cantidad*cantR) >= 0) {
                
                agregarDetalleLista(this.tablaProductosDetalle.getValueAt(i, 1).toString(), this.tablaProductosDetalle.getValueAt(i, 8).toString(), cantidad);
                if (!this.editando) {
                    System.out.println("diferente de editando");
                    int index = -1;
                    if (index < 0) {
                        System.out.println("insertando normal");
                        //registrando el detalle del producto insertado
                        int id = this.control.executeAndGetId(String.format("INSERT INTO detalleventa VALUES(NULL,%d,%s,%s,%s,%s,%s,%b,%s,%s,%s);",
                                this.idVenta, 
                                this.tablaProductosDetalle.getValueAt(i, 1).toString(),
                                costoProducto,
                                this.tablaProductosDetalle.getValueAt(i, 7).toString(),
                                cantidad,
                                this.tablaProductosDetalle.getValueAt(i, 8).toString(),
                                esGrabado,
                                descuento,cantR,
                                this.tablaProductosDetalle.getValueAt(i, 9).toString()));
                        //actualizando almacén
                        this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                cantidad*cantR,
                                this.tablaProductosDetalle.getValueAt(i, 8).toString(), this.tablaProductosDetalle.getValueAt(i, 1).toString()));
                        System.out.println((String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                cantidad*cantR,
                                this.tablaProductosDetalle.getValueAt(i, 8).toString(), this.tablaProductosDetalle.getValueAt(i, 1).toString())));
                        this.tablaProductosDetalle.setValueAt(id, i, 0);
                    } else {
                        System.out.println("editando");
                        double cantidadAnterior = Double.parseDouble(this.modeloProductoDetalle.getValueAt(index, 5).toString());
                        //actualizando datos del detalle de venta
                        this.control.ejecutar(String.format("UPDATE detalleventa d SET d.`precioVenta`=%s, d.`cantidad`=%s,d.`esGrabado`=%b WHERE d.`idDetalleVenta`=%s;",
                                costoProducto, (cantidadAnterior + cantidad), esGrabado, this.modeloProductoDetalle.getValueAt(index, 0).toString()));
                        //actualizando almacén
                        this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                cantidad*cantR,
                                this.modeloProductoDetalle.getValueAt(index, 8).toString(), this.modeloProductoDetalle.getValueAt(index, 1).toString()));
                        
                        System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                cantidad*cantR,
                                modeloProductoDetalle.getValueAt(index, 8).toString(), modeloProductoDetalle.getValueAt(index, 1).toString()));
                    }
                }
                this.editando = false;
                try {
                    FrmProductosBajosDeStock.cargarDatos();
                } catch (Exception e) {
                }
                if (!this.facturado && this.modeloProductoDetalle.getRowCount() > 0) {
                    this.btnCobrar.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cantidad requerida por debajo del stock.\nStock disponible " + this.control.decimalFormat(stockDisponible), "Mensaje", JOptionPane.WARNING_MESSAGE);
                this.txtCantidad.selectAll();
                this.txtCantidad.grabFocus();
            }
        }
        this.calcularTotal();
        this.guardar();
    }

    private void agregarProducto() {
        
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"
        
        if (this.txtPrecio.getText().trim().length() > 0) {
            if (this.txtCantidad.getText().trim().length() > 0) {
                if (Double.parseDouble(this.txtPrecio.getText()) > 0) {
                    if (Double.parseDouble(this.txtCantidad.getText()) > 0) {
                        
                        double descuento = 0, dctoMostrar=0;
                        double costoProducto = 0;
                        double cantidad=1;
                        costoProducto = Double.parseDouble(this.txtPrecio.getText().replace(",", ""));
                        descuento = Double.parseDouble(this.txtDescuento.getText().replace(",", ""));
                        dctoMostrar = Double.parseDouble(this.txtDescuento.getText().replace(",", ""));
                        double cant=Double.parseDouble(this.txtCantidad.getText());
                        cantidad = cant;
                        
                        descuento=((descuento*costoProducto)/100)*cantidad;
                        
                        double stockDisponible = getStockDisponible(this.producto, this.idAlmacen);
                        
                      
                        boolean esGrabado = false;
                        
                        String idLote=this.cbsLoteAux.getSelectedItem().toString();
                        if (stockDisponible - (cantidad*this.CantReal) >= 0) {
                         this.agregarDetalleLista(this.producto, this.idAlmacen, cantidad*this.CantReal); 
                            
                            if (!this.editando) {
                                System.out.println("diferente de editando");
                                int index = this.buscar1(this.producto, this.idAlmacen,this.Unidad,this.cbsLoteAux.getSelectedItem().toString());
                                //JOptionPane.showMessageDialog(null, index);

                                if (index < 0) {
                                    System.out.println("insertando normal");
                                    //registrando el detalle del producto insertado
                                    int id = this.control.executeAndGetId(String.format("INSERT INTO detalleventa VALUES(NULL,%d,%s,%s,%s,%s,%s,%b,%s,%s,'%s', %s);",
                                            this.idVenta, this.producto,
                                            costoProducto,
                                            this.datos[11],
                                            cantidad,
                                            this.idAlmacen, esGrabado, dctoMostrar,this.CantReal,this.Unidad,idLote));
                                    System.out.println(String.format("INSERT INTO detalleventa VALUES(NULL,%d,%s,%s,%s,%s,%s,%b,%s,%s,'%s', %s);",
                                            this.idVenta, this.producto,
                                            costoProducto,
                                            this.datos[11],
                                            cantidad,
                                            this.idAlmacen, esGrabado, dctoMostrar,this.CantReal,this.Unidad,idLote) + "----->2");

                                    //actualizando almacén
                                    
                                         this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                            + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                            (cantidad*this.CantReal),
                                            this.idAlmacen, this.producto));
                                         
                                         //actualizando Lote
                                    
                                         this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`-%s "
                                            + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                                            (cantidad*this.CantReal),
                                            this.cbsLoteAux.getSelectedItem().toString(), this.producto));
                                    
                                    //modeloProductoDetalle.addRow(new String[]{"" + id, producto, lblProducto.getText(), lblAlmacen.getText(), ""+cantidad, control.decimalFormat(costoProducto-descuento), control.decimalFormat(descuento), (!esGrabado ? control.decimalFormat((costoProducto - descuento) * cantidad) : "0.00"), (esGrabado ? control.decimalFormat((costoProducto - descuento) * cantidad) : "0.00"), idAlmacen, datos[10], datos[8], datos[9]}
                                    
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"

this.modeloProductoDetalle.addRow(new String[]{""+ id,this.producto, this.codProd, this.lblProducto.getText(), this.rubro,this.composicion,this.presentacion,this.laboratorio, this.cboUnidad.getSelectedItem().toString(), this.cbsLote.getSelectedItem().toString(), txtFecVen.getText(), String.valueOf(cantidad),this.control.decimalFormat(costoProducto),this.control.decimalFormat(dctoMostrar), this.control.decimalFormat((costoProducto * cantidad)-descuento), this.idAlmacen,String.valueOf(this.CantReal),this.Almacen,this.cbsLoteAux.getSelectedItem().toString()});

                                } else {
                                    System.out.println("editando");
                                    double cantidadAnterior = Double.parseDouble(this.modeloProductoDetalle.getValueAt(index, 11).toString());
                                    //actualizando datos del detalle de venta
                                    this.control.ejecutar(String.format("UPDATE detalleventa d SET d.`precioVenta`=%s, d.`cantidad`=%s, d.descuento=%s, d.idlote=%s WHERE d.`idDetalleVenta`=%s;",
                                            costoProducto, (cantidadAnterior + cantidad),dctoMostrar,idLote, this.modeloProductoDetalle.getValueAt(index, 0).toString()));
                                    //actualizando almacén
                                    
                                        this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                            + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                            (cantidad*this.CantReal),
                                            this.modeloProductoDetalle.getValueAt(index, 15).toString(), this.modeloProductoDetalle.getValueAt(index, 1).toString()));
                                        
                                   //actualizando Lote
                                    
                                         this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`-%s "
                                            + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                                            (cantidad*this.CantReal),
                                            this.cbsLoteAux.getSelectedItem().toString(), this.modeloProductoDetalle.getValueAt(index, 1).toString()));
                                    
                            this.modeloProductoDetalle.setValueAt(this.control.decimalFormat(costoProducto), index, 12);
                            this.modeloProductoDetalle.setValueAt(String.valueOf(cantidadAnterior + cantidad), index, 11);
                            
                            descuento=((Double.parseDouble(this.txtDescuento.getText().replace(",", ""))*costoProducto)/100)*(cantidadAnterior+cantidad);
                            
                            this.modeloProductoDetalle.setValueAt(this.control.decimalFormat((costoProducto * (cantidadAnterior + cantidad))-descuento), index, 14);
                            this.modeloProductoDetalle.setValueAt(this.cbsLote.getSelectedItem().toString(), index, 9);
                            this.modeloProductoDetalle.setValueAt(this.txtFecVen.getText(), index, 10);
                            this.modeloProductoDetalle.setValueAt(control.decimalFormat(dctoMostrar), index, 13);
                                   
                                }
                            } else {
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"                                
                                actualizarPrevio();
                                System.out.println("editando normal");
                                int index = this.tablaProductosDetalle.getSelectedRow();
                                this.control.ejecutar(String.format("UPDATE detalleventa d SET d.`precioVenta`=%s, d.`cantidad`=%s, d.descuento=%s, d.idlote=%s  WHERE d.`idDetalleVenta`=%s;",
                                        costoProducto, (cantidad), dctoMostrar, idLote,
                                        this.modeloProductoDetalle.getValueAt(index, 0).toString()));

                                System.out.println(String.format("UPDATE detalleventa d SET d.`precioVenta`=%s, d.`cantidad`=%s, d.descuento=%s, idlote=%s WHERE d.`idDetalleVenta`=%s;",
                                        costoProducto, (cantidad), dctoMostrar, idLote,
                                        this.modeloProductoDetalle.getValueAt(index, 0).toString()));

                                //actualizando almacén
                                
                                    this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                        + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                        (cantidad*Double.parseDouble(this.modeloProductoDetalle.getValueAt(index, 16).toString())),
                                        this.modeloProductoDetalle.getValueAt(index, 15).toString(), this.modeloProductoDetalle.getValueAt(index, 1).toString()));
                                
                                System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                        + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                                        (cantidad*Double.parseDouble(this.modeloProductoDetalle.getValueAt(index, 16).toString())),
                                        this.modeloProductoDetalle.getValueAt(index, 15).toString(), this.modeloProductoDetalle.getValueAt(index, 1).toString()));
                                
                                
                                this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`-%s "
                                            + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                                            (cantidad*this.CantReal),
                                            this.cbsLoteAux.getSelectedItem().toString(), this.modeloProductoDetalle.getValueAt(index, 1).toString()));

                                //modeloProductoDetalle.setValueAt(lblAlmacen.getText(), index, 3);
                                //modeloProductoDetalle.setValueAt(control.decimalFormat(costoProducto), index, 5);
                                this.modeloProductoDetalle.setValueAt(control.decimalFormat(dctoMostrar), index, 13);
                                
                                this.modeloProductoDetalle.setValueAt(control.decimalFormat((cantidad*costoProducto)-descuento), index, 14);
                              
                                this.modeloProductoDetalle.setValueAt(cantidad, index, 11);
                                this.modeloProductoDetalle.setValueAt(idAlmacen, index, 15);
                                this.modeloProductoDetalle.setValueAt(this.cbsLote.getSelectedItem().toString(), index, 9);
                            this.modeloProductoDetalle.setValueAt(this.txtFecVen.getText(), index, 10);
                            this.modeloProductoDetalle.setValueAt(this.cbsLoteAux.getSelectedItem().toString(), index, 18);
                            
                                this.btnEditarProducto.setEnabled(true);
                                this.btnEliminarProducto.setEnabled(true);
                                this.txtBuscarDetalle.setEditable(true);
                                this.tablaProductosDetalle.setEnabled(true);
                                this.cboUnidad.setEnabled(true);
                            }
                            this.editando = false;
                            this.lblProducto.setText(" ");
                            this.txtPrecio.setText("0.00");
                            this.txtCantidad.setText("1");
                            this.txtDescuento.setText("0");

                            this.txtTotal.setText("0.00");
                            this.txtPrecio.setEditable(false);
                            this.txtDescuento.setEditable(false);
                            this.txtCantidad.setEditable(false);
                            this.txtTotal.setEditable(false);
                            this.txtBuscarDetalle.setText("");
                            //this.txtBuscarDetalle.requestFocus();
                            this.cboUnidad.setEnabled(true);
                            this.btnActivarDto.setVisible(false);
                            
                            txtCantLote.setText("");txtCantLote.setEditable(false);
                            txtFecVen.setText("");txtFecVen.setEditable(false);
                            cbsLote.removeAllItems();
                            cbsLoteAux.removeAllItems();
                            cbsLote.setEnabled(false);
                            calcularTotal();
                            try {
                                FrmProductosBajosDeStock.cargarDatos();
                            } catch (Exception e) {
                            }
                            if (!this.facturado && this.modeloProductoDetalle.getRowCount() > 0) {
                                this.btnCobrar.setEnabled(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Cantidad requerida por debajo del stock.\nStock disponible " + this.control.decimalFormat(stockDisponible)+" Unidades", "Mensaje", JOptionPane.WARNING_MESSAGE);
                            this.txtCantidad.selectAll();
                            this.txtCantidad.grabFocus();
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

    private void PasarDirecto(){
    String text=this.txtBuscarDetalle.getText().trim();
    
    if (!this.facturado) {

            if (this.cliente != null) {
                
                  this.control.Sql="select '',p.idproduto, p.codprod, concat(p.nomproducto,' ',m.nommarc ),u.nomuniddes,'1' as cantidad\n" +
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
    
    String[] cargarRegistro = this.control.cargarRegistro(this.control.Sql, 15);
    if(cargarRegistro==null){
    
        JOptionPane.showMessageDialog(null, "Código de Producto No Registrado.");
    }    
    else{
        
        int row = this.tablaProductos.getSelectedRow();
                this.datos = new String[12];
                this.datos[0] =cargarRegistro[1];//id
                this.datos[1] = cargarRegistro[2];//Codigo producto
                this.datos[2] = cargarRegistro[11];//Codigo Unidad
                this.datos[3] = cargarRegistro[3];//Producto y Marca
                this.datos[4] = cargarRegistro[3];//marca
                this.datos[5] = cargarRegistro[4];//nombre de Unidades
                this.datos[6] = cargarRegistro[12];//id almacen
                this.datos[7] = cargarRegistro[13];//almacen
                this.datos[8] = cargarRegistro[6];//precio de compra unitario
                this.datos[9] = cargarRegistro[9];//cantidad Real
                this.datos[10] = cargarRegistro[7];//precio de Compra segun Unidad
                this.datos[11] = cargarRegistro[14];//stock disponible
                this.unidadDeMedidaProducto = this.datos[5];
                this.editando=false;
                if (this.datos != null) {
                    this.producto = datos[0];
                    this.codProd= datos[1];
                    this.Unidad=datos[5];
                    this.lblProducto.setText(datos[3]);
                    this.idAlmacen = datos[6];
                    this.Almacen =datos[7];
                    this.CantReal=Integer.parseInt(datos[9]);
                    this.precios[0] = Double.parseDouble(datos[8]);//precio por Unidad
                    this.precios[1] = Double.parseDouble(datos[10]);//precio por Volumen Seleccionado
                    this.indexPrecios = 0;
                    if (this.datos[6].length()>0) {
                        
                        String precio = "0.00";
                        /*if (!listaUnidades.contains(datos[2])) {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        } else {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        }*/
                        precio = this.control.decimalFormat(this.precios[1]);
                        
                        //txtPrecio.setEditable(false);
                        //txtDescuento.setEditable(true);
                        //txtCantidad.setEditable(true);
                        this.txtCantidad.setText("1");
                        //txtTotal.setEditable(true);
                        this.lblAlmacen.setText(this.datos[7]);
                        int buscar = buscar(this.producto, this.idAlmacen,this.Unidad);
                        if (buscar >= 0) {
                            this.txtPrecio.setText(precio);
                            this.txtTotal.setText(precio);
                            //txtPrecio.setText(modeloProductoDetalle.getValueAt(buscar, 8).toString());
                        } else {
                            this.txtPrecio.setText(precio);
                           this.txtTotal.setText(precio);
                        }
                        
if (this.idVenta == 0) {
this.guardar();

}
this.agregarProducto();
System.out.println("2");
this.guardar();
System.out.println("3");

                    } else {
                        JOptionPane.showMessageDialog(this, "El producto " + this.datos[2] + " no tiene existencias en ninguno de los almacenes", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        this.btnCancelarProducto.doClick();
                    }
                }
          
    }  
  
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione del cliente para la venta", "Mensaje", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, ya no se pueden agregar nuevos productos", "Mensaje", 2);
        }
    this.txtBuscarDetalle.setText("");
    this.txtBuscarDetalle.requestFocus();
    }
    public int buscar(String producto, String almacen, String unidad) {
        for (int i = 0; i < this.modeloProductoDetalle.getRowCount(); i++) {
            if (this.modeloProductoDetalle.getValueAt(i, 1).toString().equals(producto)&& this.modeloProductoDetalle.getValueAt(i, 4).toString().equals(unidad) ) {

                return i;
            }
        }
        return -1;
    }
    
    public int buscar1(String producto, String almacen, String unidad, String idLote) {
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"
        for (int i = 0; i < this.modeloProductoDetalle.getRowCount(); i++) {
            if (this.modeloProductoDetalle.getValueAt(i, 1).toString().equals(producto)&& this.modeloProductoDetalle.getValueAt(i, 8).toString().equals(unidad) && this.modeloProductoDetalle.getValueAt(i, 18).toString().equals(idLote)) {

                return i;
            }
        }
        return -1;
    }

    private void cargarEditar() {
        //actualizarPrevio();

//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"
        this.editando = true;
       
        this.txtBuscarDetalle.setEditable(false);
        this.tablaProductosDetalle.setEnabled(false);
        int fila = this.tablaProductosDetalle.getSelectedRow();
        this.producto = this.modeloProductoDetalle.getValueAt(fila, 1).toString();
        this.lblAlmacen.setText(this.modeloProductoDetalle.getValueAt(fila, 17).toString());
        this.lblProducto.setText(this.modeloProductoDetalle.getValueAt(fila, 3).toString());
        //unidadDeMedidaProducto = control.DevolverRegistroDto(String.format("SELECT `Unidad` FROM `vta_catalogo` WHERE `ID`=%s;", producto), 1);
        this.txtCantidad.setText(this.modeloProductoDetalle.getValueAt(fila, 11).toString());
        double cant=Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 16).toString());
      
        
        this.cantidadtemporal=(int)(cant);
        double costo=Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 12).toString());
        this.txtPrecio.setText(String.valueOf(costo));
        this.txtTotal.setText(String.valueOf(costo*Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 11).toString())));
        txtDescuento.setText(modeloProductoDetalle.getValueAt(fila, 13).toString());
        this.idAlmacen = this.modeloProductoDetalle.getValueAt(fila, 15).toString();
        this.CantReal= Integer.parseInt(this.modeloProductoDetalle.getValueAt(fila, 16).toString());
        this.precios[0] = Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 12).toString())/Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 16).toString());
        this.precios[1] = Double.parseDouble(this.modeloProductoDetalle.getValueAt(fila, 12).toString());
        this.indexPrecios = 1;
        this.txtCantidad.setEditable(true);
        //txtPrecio.setEditable(true);
        
        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        this.txtDescuento.setEditable(true);
        }
        else{
            this.btnActivarDto.setVisible(true);
        }
        this.txtCantidad.selectAll();
        this.txtCantidad.grabFocus();
        this.btnEditarProducto.setEnabled(false);
        this.btnEliminarProducto.setEnabled(false);
        this.cboUnidad.setEnabled(false);
        this.calcularCostoPorProducto();
        
        this.cargarLote(this.modeloProductoDetalle.getValueAt(fila, 1).toString());
        
        this.cbsLoteAux.setSelectedItem(this.modeloProductoDetalle.getValueAt(fila, 18).toString());
        int posi=cbsLoteAux.getSelectedIndex();
        this.cbsLote.setSelectedIndex(posi);
        
        
    }

    public boolean guardar() {
        boolean b = false;

        String fecha = this.control.Formato_Amd(this.jDateChooser1.getDate() == null ? new Date() : this.jDateChooser1.getDate());


        SpinnerDateModel modelSpinner = (SpinnerDateModel)this.jHoraVta.getModel();
        java.util.Date date=modelSpinner.getDate();
        String hora=date.getHours()+":"+date.getMinutes()+":"+"00";
        System.out.println(hora);
        
        if (!this.guardado) {
            System.out.println("proceso insertar");
            this.control.Sql = String.format("INSERT INTO venta VALUES(NULL,'%s',%s,NULL,%s,%s,%s,'Pendiente',%s,0,0.00,'%s');", fecha, this.cliente, 0.00, 0.00, 0.00, Accesos.getInstance().getIdUsuario(),hora);
            System.out.println(this.control.Sql + "----->1");
            int id = this.control.executeAndGetId(this.control.Sql);
            if (id > 0) {
                this.idVenta = id;
                this.guardado = true;
            }
            if (!this.facturado && this.modeloProductoDetalle.getRowCount() > 0) {
                this.btnCobrar.setEnabled(true);
            }
        } else {

            System.out.println("proceso actualizar");
            this.control.ejecutar(String.format("UPDATE venta v SET "
                    + "v.`fecha`='%s', "
                    + "v.`idCliente`=%s, "
                    + "v.`subTotalInafecto`=%s, "
                    + "v.`subTotalAfecto`=%s, "
                    + "v.`igv`=%s, "
                    + "v.`hora`='%s' "
                    + "WHERE v.`idVenta`=%d;",
                    fecha, this.cliente, this.txtSubTotalInafecto.getText().replace("S/ ", "").replace(",", ""),
                    0.00, 0.00,hora, this.idVenta));
            if (!this.facturado && this.modeloProductoDetalle.getRowCount() > 0) {
                this.btnCobrar.setEnabled(true);
            }
        }
        this.lblNumeroVenta.setText(String.valueOf(this.idVenta));
        calcularTotal();
        b = true;
        actualizaAlmacen();
        return b;
        
        
    }
    
    public void actualizaAlmacen(){
    String dato=this.cboUnidad.getSelectedItem().toString();
                if (this.productosModel.getRowCount() > 0 && this.tablaProductos.getSelectedRow() >= 0) {
                    this.control.LlenarJTabla(this.productosAlmacenModel, String.format("SELECT a.`idAlmacen`, a.`nombre`, concat((REPLACE(format(s.`cantidadDisponible`/%s,2), ',', '')),space(1),'"+dato+"'),s.`cantidadDisponible`"
                            + " FROM stock s, almacen a WHERE s.`idAlmacen`=a.`idAlmacen` AND s.`idProducto`=%s;",this.productosModel.getValueAt(this.tablaProductos.getSelectedRow(), 10).toString(), this.productosModel.getValueAt(this.tablaProductos.getSelectedRow(), 0).toString()), 4);

                    try {
                        this.tablaAlmacen.getSelectionModel().addSelectionInterval(0, 0);
                    } catch (Exception ex) {
                    }
                } else {
                    this.control.LimTabla(this.productosAlmacenModel);
                }
    }

    private void calcularTotal() {
        
        
        double subTotalInafecto = 0;
//                double subTotalAfecto = 0;
        for (int i = 0; i < this.modeloProductoDetalle.getRowCount(); i++) {
            subTotalInafecto += Double.parseDouble(this.modeloProductoDetalle.getValueAt(i, 14).toString().replace(",", ""));
//                        subTotalAfecto += Double.parseDouble(modeloProductoDetalle.getValueAt(i, 8).toString().replace(",", ""));
        }
        //JOptionPane.showMessageDialog(rootPane, subTotalInafecto);
        this.txtSubTotalInafecto.setText("S/ " + this.control.decimalFormat(subTotalInafecto));
//                txtSubtotalAfecto.setText("S/ " + control.decimalFormat(subTotalAfecto));
        this.txtTotalVenta.setText("S/ " + this.control.decimalFormat(subTotalInafecto));
    }

    private void seleccionarProducto() {

        if (!this.facturado) {

//"Id","Codigo","Codigo x Unid.", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Prec. Unidad", "Cantidad", "Precio de Venta", "esBajoDeStock"
//"Id","Codigo","Codigo x Unid.", "Producto","Marca", "Unidad", "Menor", "Mayor", "P. Venta", "esBajoDeStock";
            if (this.cliente != null) {
                int row = this.tablaProductos.getSelectedRow();
                this.datos = new String[15];
                this.datos[0] = this.productosModel.getValueAt(row, 0).toString();//id
                this.datos[1] = this.productosModel.getValueAt(row, 1).toString();//Codigo producto
                this.datos[2] = this.productosModel.getValueAt(row, 2).toString();//Codigo Unidad
                this.datos[3] = this.productosModel.getValueAt(row, 3).toString();//Producto
                this.datos[4] = this.productosModel.getValueAt(row, 4).toString();//Rubro
                this.datos[5] = this.productosModel.getValueAt(row, 5).toString();//Composicion
                this.datos[6] = this.productosModel.getValueAt(row, 6).toString();//Presentacion
                this.datos[7] = this.productosModel.getValueAt(row, 7).toString();//Laboratorio
                this.datos[8] = this.productosModel.getValueAt(row, 8).toString();//nombre de Unidades
                this.datos[9] = this.productosAlmacenModel.getRowCount() > 0 ? this.productosAlmacenModel.getValueAt(this.tablaAlmacen.getSelectedRow(), 0).toString() : null;//id almacen
                this.datos[10] = this.productosAlmacenModel.getRowCount() > 0 ? this.productosAlmacenModel.getValueAt(this.tablaAlmacen.getSelectedRow(), 1).toString() : null;//almacen
                this.datos[11] = this.productosModel.getValueAt(row, 9).toString();//precio de compra unitario
                this.datos[12] = this.productosModel.getValueAt(row, 10).toString();//cantidad
                this.datos[13] = this.productosModel.getValueAt(row, 11).toString();//precio de Compra segun Unidad
                this.datos[14] = this.productosAlmacenModel.getRowCount() > 0 ? this.productosAlmacenModel.getValueAt(this.tablaAlmacen.getSelectedRow(), 3).toString() : "0";//stock disponible
                this.unidadDeMedidaProducto = this.datos[8];
                //JOptionPane.showMessageDialog(null, datos[0]+" "+datos[3]);
                this.editando=false;
                if (this.datos != null) {
                    this.producto = this.datos[0];
                    
                    this.nomProd=this.datos[3];
                    this.rubro=this.datos[4];
                    this.composicion=this.datos[5];
                    this.presentacion=this.datos[6];
                    this.laboratorio=this.datos[7];
    
    
                    this.codProd= this.datos[1];
                    this.Unidad=this.datos[8];
                    this.UnidadA=this.datos[8];
                    this.lblProducto.setText(this.datos[6]+" "+this.datos[3]);
                    this.idAlmacen = this.datos[9];
                    this.Almacen =this.datos[10];
                    this.CantReal=Integer.parseInt(this.datos[12]);
                    this.precios[0] = Double.parseDouble(this.datos[11]);//precio por Unidad
                    this.precios[1] = Double.parseDouble(this.datos[13]);//precio por Volumen Seleccionado
                    this.indexPrecios = 0;
                    if (this.datos[9] != null) {
                        
                        String precio = "0.00";
                        /*if (!listaUnidades.contains(datos[2])) {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        } else {
                            precio = control.decimalFormat(precios[1]);
                            indexPrecios = 0;
                        }*/
                        precio = this.control.decimalFormat(this.precios[1]);
                        
                        this.txtPrecio.setEditable(false);
                        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
                        this.txtDescuento.setEditable(true);
                        }
                        else{
                            this.btnActivarDto.setVisible(true);
                        }
                        this.txtCantidad.setEditable(true);
                        this.txtCantidad.setText("1");
                        //txtTotal.setEditable(true);
                        this.lblAlmacen.setText(this.datos[10]);
                        int buscar = buscar(this.producto, this.idAlmacen,this.Unidad);
                        if (buscar >= 0) {
                            this.txtPrecio.setText(precio);
                            this.txtTotal.setText(precio);
                            //txtPrecio.setText(modeloProductoDetalle.getValueAt(buscar, 8).toString());
                        } else {
                            this.txtPrecio.setText(precio);
                           this.txtTotal.setText(precio);
                        }
                        
                        this.cargarLote(this.producto);
                        this.txtCantidad.grabFocus();
                        this.txtCantidad.selectAll();

                    } else {
                        JOptionPane.showMessageDialog(this, "El producto " + this.datos[2] + " no tiene existencias en ninguno de los almacenes", "Mensaje", JOptionPane.WARNING_MESSAGE);
                        this.btnCancelarProducto.doClick();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione del cliente para la venta", "Mensaje", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, ya no se pueden agregar nuevos productos", "Mensaje", 2);
        }
    }
    
    private void cargarLote(String idProd){
        auxLote=false;
        this.cbsLote.removeAllItems();
        this.cbsLoteAux.removeAllItems();
        try {
            Base.rt = DevolverRegistro("SELECT idlote,nombre FROM lote l where activo='1' and idproducto='"+idProd+"' and cant>0 order by fecVen,idlote;");
            while (Base.rt.next()) {
                this.cbsLote.addItem(Base.rt.getString(2));
                this.cbsLoteAux.addItem(Base.rt.getString(1));
            }
            //cbo.setSelectedIndex(0);
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
        
        this.cbsLoteAux.setEnabled(true);
        this.cbsLote.setEnabled(true);
        auxLote=true;
        cararDatosLote();
    
    }
    
    private void cararDatosLote(){
        int index=this.cbsLote.getSelectedIndex();
        
        String Ven="0";
        
        if(index>=0 && auxLote==true){
            this.cbsLoteAux.setSelectedIndex(index);
            String idLote=this.cbsLoteAux.getSelectedItem().toString();
            try {
            Base.rt = DevolverRegistro("select idlote, nombre, fecIn, date_format(`fecVen`,'%d/%m/%Y') as fecha, idproducto, idDetalleEntradaStock, cant, activo, obs,if(curdate()>fecVen,1,0) from lote where idlote='"+idLote+"';");
            while (Base.rt.next()) {
                this.txtFecVen.setText(Base.rt.getString(4));
                this.txtCantLote.setText(Base.rt.getString(7));
                Ven=Base.rt.getString(10);
            }
            //cbo.setSelectedIndex(0);
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
            
            if(Ven.equals("1")){
                 this.txtFecVen.setForeground(Color.red);
                 this.txtFecVen.setFont(new Font("Tahoma", Font.BOLD, 13));
            }else{
                this.txtFecVen.setForeground(Color.black);
                 this.txtFecVen.setFont(new Font("Tahoma", Font.PLAIN, 13));
            }
            
        }
    }

    private void eliminarLinea() {
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"

        if (JOptionPane.showInternalConfirmDialog(this, "¿Confirma que deseal eliminar el producto seleccionado?", "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            int row = this.tablaProductosDetalle.getSelectedRow();
            String id = this.modeloProductoDetalle.getValueAt(row, 0).toString();
            int buscarLista =this. buscarLista(this.modeloProductoDetalle.getValueAt(row, 1).toString(), this.modeloProductoDetalle.getValueAt(row, 15).toString());
            if (buscarLista >= 0) {
                double[] get = this.listaVenta.get(buscarLista);
                get[2] = get[2] - (Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString()));
                this.listaVenta.set(buscarLista, get);
            }
            
            this.control.ejecutar(String.format("DELETE FROM detalleguiaremision WHERE `idDetalleVenta`=%s;", id));
             this.control.ejecutar(String.format("DELETE FROM detalleordenpedido WHERE `idDetalleVenta`=%s;", id));
            this.control.ejecutar(String.format("DELETE FROM detalleventa WHERE `idDetalleVenta`=%s;", id));
            System.out.println(String.format("DELETE FROM detalleventa WHERE `idDetalleVenta`=%s;", id) + "------eliminar1");
            //actualizando almacén
            if(this.ckSinDescon.isSelected()==false){
                 this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                         String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 15).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));
            
                System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=+%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 15).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()) + "------eliminar 2");
                
                System.out.println(String.format("UPDATE lote s SET s.`cant`=s.`cant`+%s "
                    + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                         String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 18).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString())+ "------eliminar 3");
                
                this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`+%s "
                    + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                         String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 18).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));
                
                
                
            }
               

            this.modeloProductoDetalle.removeRow(row);
            this.calcularTotal();
            this.btnEliminarProducto.setEnabled(false);
            this.btnEditarProducto.setEnabled(false);
            //cancelar();
            this.txtBuscarDetalle.setText("");
            this.txtBuscarProducto.setText("");
            this.txtBuscarDetalle.requestFocus();
        }

    }

     private void eliminarLineatodo( int row) {
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"

            String id = this.modeloProductoDetalle.getValueAt(row, 0).toString();
            int buscarLista = buscarLista(this.modeloProductoDetalle.getValueAt(row, 1).toString(), 
                    this.modeloProductoDetalle.getValueAt(row, 15).toString());
            if (buscarLista >= 0) {
                double[] get = this.listaVenta.get(buscarLista);
                get[2] = get[2] - (Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString()));
                this.listaVenta.set(buscarLista, get);
            }
            
            this.control.ejecutar(String.format("DELETE FROM detalleguiaremision WHERE `idDetalleVenta`=%s;", id));
             this.control.ejecutar(String.format("DELETE FROM detalleordenpedido WHERE `idDetalleVenta`=%s;", id));
             
             
            this.control.ejecutar(String.format("DELETE FROM detalleventa WHERE `idDetalleVenta`=%s;", id));
            System.out.println(String.format("DELETE FROM detalleventa WHERE `idDetalleVenta`=%s;", id) + "------eliminar1");
            //actualizando almacén
            if(this.ckSinDescon.isSelected()==false){
                this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 15).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));    
                
            System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=+%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 15).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()) + "------eliminar 2");
            
            this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`+%s "
                    + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                         String.valueOf(Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString())),
                    this.modeloProductoDetalle.getValueAt(row, 18).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));
            
            }   
            //modeloProductoDetalle.removeRow(row);
            this.calcularTotal();
            this.btnEliminarProducto.setEnabled(false);
            this.btnEditarProducto.setEnabled(false);
            this.txtBuscarProducto.requestFocus();
    }
     
          private void VenderSinDescontarStock( int row, boolean opci) {
            //actualizando almacén
              if(opci){
                  System.out.println("1");
                  System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=+%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    this.modeloProductoDetalle.getValueAt(row, 4).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 9).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));
                    this.control.ejecutar(String.format("insert into entregarluego values(null,'%s','%s','0');", this.idVenta,this.control.Formato_Amd(this.jDateChooser1.getDate())));
                  
                  this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    this.modeloProductoDetalle.getValueAt(row, 4).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 9).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));
                  
              }else{
                  System.out.println("2");
                  this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    this.modeloProductoDetalle.getValueAt(row, 4).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 9).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));                  
                  this.control.ejecutar(String.format("delete from entregarluego where idventa='%s';", this.idVenta));
                  
                  System.out.println(String.format("UPDATE stock s SET s.`cantidadDisponible`=-%s "
                    + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                    this.modeloProductoDetalle.getValueAt(row, 4).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 9).toString(),
                    this.modeloProductoDetalle.getValueAt(row, 1).toString()));
                  
              }
    }

    private int buscarLista(String producto, String almacen) {
        for (int i = 0; i < this.listaVenta.size(); i++) {
            double[] ds = this.listaVenta.get(i);
            if ((int) ds[0] == Integer.parseInt(producto) && (int) ds[1] == Integer.parseInt(almacen)) {
                return i;
            }

        }
        return -1;
    }

    private double getStockDisponible(String producto, String almacen) {
        
        System.out.println(String.format("SELECT `cantidadDisponible` FROM `stock` WHERE `idAlmacen`=%s AND `idProducto`=%s;", almacen, producto));
        double stock = Double.parseDouble(this.control.DevolverRegistroDto(String.format("SELECT `cantidadDisponible` FROM `stock` WHERE `idAlmacen`=%s AND `idProducto`=%s;", almacen, producto), 1));
        
                    
        return stock;//- cantidad;
    }

    private void agregarDetalleLista(String producto, String idAlmacen, double cantidad) {
        
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"
        int buscarLista = buscarLista(producto, idAlmacen);
        if (buscarLista >= 0) {

            double[] get = this.listaVenta.get(buscarLista);
            get[2] += cantidad;

            if (this.editando) {
                double tmpCant = Double.parseDouble(this.modeloProductoDetalle.getValueAt(this.tablaProductosDetalle.getSelectedRow(), 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(this.tablaProductosDetalle.getSelectedRow(), 16).toString());
                get[2] -= tmpCant;
            }
            this.listaVenta.set(buscarLista, get);
        } else {
            this.listaVenta.add(new double[]{Double.parseDouble(producto), Double.parseDouble(idAlmacen), cantidad});
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
        if (this.seleccionarCliente == null) {
            this.seleccionarCliente = new SeleccionarClienteDialog(null, true);
        }
        this.seleccionarCliente.setLocationRelativeTo(this);
        this.seleccionarCliente.setVisible(true);
        this.seleccionarCliente.cargarClientes();
        String[] datosCliente = this.seleccionarCliente.getDatos();
        if (datosCliente != null) {
            this.cliente = datosCliente[0];
            this.ruc=datosCliente[1];
            this.txtCliente.setText(datosCliente[2]);
            
            this.txtnumdoc.setText(this.ruc);
            
            String tipo=datosCliente[4];
            
            this.tipoCliente=tipo;
            
            if(tipo.equals("Natural"))//Natural DNI
        {
            this.lbltipodoc.setText("DNI - Cliente Natural");
        }else if(tipo.equals("Juridico"))//RUC
        {
            this.lbltipodoc.setText("RUC - Cliente Jurídico");
        }else if(tipo.equals("Carnet de Extranjería"))//Carnet de Extranjería
        {
            this.lbltipodoc.setText("Carnet de Extranjería");
        }else if(tipo.equals("Pasaporte"))//Pasaporte
        {
            this.lbltipodoc.setText("Pasaporte");
        }else if(tipo.equals("Cédula Diplomática"))//Cédula Diplomática de Identidad
        {
            this.lbltipodoc.setText("Cédula Diplomática");
        }
            
            if (this.guardado) {

            System.out.println("proceso actualizar cliente");
            this.control.ejecutar(String.format("UPDATE venta v SET v.`idCliente`=%s WHERE v.`idVenta`=%d;",this.cliente, this.idVenta));
        }
            
            
        }
    }

    void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
        this.lblNumeroVenta.setText(String.valueOf(idVenta));
        this.guardado = true;
        this.cargarVenta();
        this.calcularTotal();
    }

    private void cargarVenta() {
        this.lblNumeroVenta.setText("" + this.idVenta);
        this.btnCobrar.setEnabled(false);
        String[] cargarRegistro = this.control.cargarRegistro("SELECT IFNULL(CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`),'Sin comprobante') as comprobante, "
                + "date_format(v.`fecha`,'%d-%m-%Y') as fecha, c.`idCliente`, "
                + "c.`nomcte`, v.`estado` "
                + "FROM cliente c, (venta v LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) "
                + "LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` "
                + "WHERE v.`idCliente`=c.`idCliente` "
                + "AND v.`idVenta`=" + this.idVenta, 5);
        this.lblNumeroComprobante.setText(" " + cargarRegistro[0]);
        
        //((JTextComponent) (this.jDateChooser1.getDateEditor())).setText(cargarRegistro[1]);
        
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
String strFecha = cargarRegistro[1];
Date fecha = null;
try {

fecha = formatoDelTexto.parse(strFecha);
this.jDateChooser1.setDate(fecha);

} catch (ParseException ex) {

ex.printStackTrace();

}


        this.txtCliente.setText(cargarRegistro[3]);
        this.cliente = cargarRegistro[2];
        if (cargarRegistro[4].equals("Pagado")) {
            this.btnCobrar.setEnabled(false);
            this.btnCliente.setEnabled(false);
            this.jDateChooser1.setEnabled(false);
            this.btnGuardar.setEnabled(false);
            this.facturado = true;

        } else {
            this.btnCobrar.setEnabled(true);
        }

        this.control.LlenarJTabla(this.modeloProductoDetalle, String.format("select v.idventa, p.idproduto, p.codUnid,concat(pre.present,' ',p.nomproducto), t.nomtip, p.composicion, pre.present, m.nommarc, d.Unidad, l.nombre, l.fecven, \n" +
" REPLACE(format((d.cantidad*d.cantreal),2), ',', ''), REPLACE(format(d.precioVenta,2), ',', ''), REPLACE(format(d.descuento,2), ',', ''), REPLACE(format(((d.cantidad*d.cantreal)*(d.precioventa-(d.precioventa*(d.descuento/100)))),2), ',', ''),d.idalmacen, d.cantreal,a.nombre, d.idlote \n" +
" from venta v \n" +
" inner join detalleventa d on d.idventa=v.idventa \n" +
" inner join produto p on p.idproduto=d.idproducto \n" +
" inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
" inner join tipoproducto t on t.idtipoproducto=p.idtipoproducto \n" +
" inner join marca m on m.idmarca=p.idmarca, \n" +
" lote l, almacen a \n" +
" where l.idlote=d.idlote and a.idalmacen=d.idalmacen and v.idventa='%s'  order by d.iddetalleventa;", this.idVenta), 19);

    }

    private void cobrar() {
        if (this.modeloProductoDetalle.getRowCount() > 0) {
            CobrarVentaDialog cobrarVentaDialog = new CobrarVentaDialog(null, true);
            cobrarVentaDialog.setLocationRelativeTo(this);
            //JOptionPane.showMessageDialog(rootPane, idVenta);
            this.nomCliente=txtCliente.getText();
           
            cobrarVentaDialog.setDatos(this.idVenta, this.modeloProductoDetalle.getRowCount(),this.nomCliente,Integer.parseInt(this.cliente),this.ruc,this.tipoCliente);
            cobrarVentaDialog.setVisible(true);
            if (cobrarVentaDialog.getResult() == CobrarVentaDialog.OK) {
                this.btnCobrar.setEnabled(false);
                this.btnCliente.setEnabled(false);
                this.jDateChooser1.setEnabled(false);
                this.btnGuardar.setEnabled(false);
                this.idVenta = 0;
        this.facturado = false;
        this.guardado = false;
        this.cancelar();
        this.calcularTotal();
        this.btnCliente.setEnabled(true);
        this.lblNumeroComprobante.setText("Sin comprobante");
        this.jDateChooser1.setEnabled(true);
        this.txtBuscarProducto.setText("");
        this.cargarProductos();
        this.txtBuscarProducto.requestFocus();
        this.btnGuardar.setEnabled(true);
                
                this.facturado = true;
                this.guardado = true;
                //btnNuevo.doClick();
                 this.idVenta = 0;
                 //BtnNuevo
        this.facturado = false;
        this.guardado = false;
        this.cancelar();
        this.calcularTotal();
        this.btnCliente.setEnabled(true);
        this.lblNumeroComprobante.setText("Sin comprobante");
        this.jDateChooser1.setEnabled(true);
        this.txtBuscarProducto.setText("");
        this.cargarProductos();
        this.txtBuscarProducto.requestFocus();
        this.btnGuardar.setEnabled(true);
        this.guardar();
        //Fin Btn Nuevo
                if (this.listadoVentas != null) {
                    this.listadoVentas.recargar();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Para poder realizar el cobro de la venta, es necesario incluir al menos un producto");
        }
    }

    private void setClientePorDefecto() {
        String[] cargarRegistro = this.control.cargarRegistro("SELECT `idCliente`, `nomcte`, `dniRuc`, `direc`  FROM `cliente` WHERE `idCliente`=1;", 4);
        if (cargarRegistro != null) {
            this.cliente = cargarRegistro[0];
            this.txtCliente.setText(cargarRegistro[1]);
        }
    }
    
    public void buscarCliente() throws SQLException{
    String num=this.txtnumdoc.getText().trim();
    this.control.Sql="SELECT * FROM cliente c where dniRuc='"+num+"';";
    
    Base.rt=control.DevolverRegistro(this.control.Sql);
    
    while (Base.rt.next()) {
                 this.cliente=Base.rt.getString(1);
                 this.txtCliente.setText(Base.rt.getString(2));
                 String tipo=Base.rt.getString(3);
                 this.ruc=Base.rt.getString(5);
                 
                 this.tipoCliente=tipo;
                 
        if(tipo.equals("Natural"))//Natural DNI
        {
            this.lbltipodoc.setText("DNI - Cliente Natural");
        }else if(tipo.equals("Juridico"))//RUC
        {
            this.lbltipodoc.setText("RUC - Cliente Jurídico");
        }else if(tipo.equals("Carnet de Extranjería"))//Carnet de Extranjería
        {
            this.lbltipodoc.setText("Carnet de Extranjería");
        }else if(tipo.equals("Pasaporte"))//Pasaporte
        {
            this.lbltipodoc.setText("Pasaporte");
        }else if(tipo.equals("Cédula Diplomática"))//Cédula Diplomática de Identidad
        {
            this.lbltipodoc.setText("Cédula Diplomática");
        }
        
 
            
            }
    }
    


    public static void buscarProducto() {

//        TableRowSorter rowSorter = new TableRowSorter(modeloProductoDetalle);
//        RowFilter filter = RowFilter.regexFilter("(?i)" + txtBuscarDetalle.getText(), 1);
       // rowSorter.setRowFilter(filter);
        //tablaProductosDetalle.setRowSorter(rowSorter);
     //   this.btnEditarProducto.setEnabled(false);
       // this.btnEliminarProducto.setEnabled(false);

    }

    private void cargarProductos0() {
        String text = this.txtBuscarProducto.getText();
        String dato="Unidad";
        this.control.Sql = "select p.idproduto, p.codProd, \n " +
"        IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'') \n " +
"        ,p.nomproducto, t.nomtip,p.composicion, pre.present, \n " +
"        m.nommarc,u.nomuniddes,p.precUnidad,u.cantidad, \n " +
"        IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad) \n " +
"        ,(SUM(s.`cantidadDisponible`)= 0) AS bajoDeStock, p.ubicacion \n " +
"        from produto p\n" +
"        inner join tipoproducto t on t.idtipoproducto=p.idtipoproducto \n " +
"        inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n " +
"        inner join marca m on p.idmarca=m.idmarca \n " +
"        inner join stock s on s.idproducto=p.idproduto, \n" +
        "unidades u \n" +
        "where u.nomuniddes='"+dato+"'\n" +
        "and (p.codProd like '%"+text+"%' or p.codUnid like '%"+text+"%' or p.nomproducto like '%"+text+"%' or m.nommarc like '%"+text+"%')\n" +
        "GROUP BY s.`idProducto`\n" +
        "order by p.nomproducto;";
        //control.LlenarJTabla(productosModel, control.Sql, 10);
        this.control.LlenarJTablaMayorStockMin(this.productosModel, this.control.Sql, 14);
        this.control.LimTabla(this.productosAlmacenModel);
        System.out.println(this.control.Sql);
    }
    
    private void cargarProductos() {
        String text = this.txtBuscarProducto.getText();
        String dato=this.cboUnidad.getSelectedItem().toString();
        this.control.Sql = "select p.idproduto, p.codProd, \n " +
"        IFNULL((select d.codUnid from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),'') \n " +
"        ,p.nomproducto, t.nomtip,p.composicion, pre.present, \n " +
"        m.nommarc,u.nomuniddes,p.precUnidad,u.cantidad, \n " +
"        IFNULL((select d.precio from detunidprod d where d.idProducto=p.idproduto and d.idUnidades=u.idUnidades),p.precUnidad*u.cantidad) \n " +
"        ,(SUM(s.`cantidadDisponible`)= 0) AS bajoDeStock, p.ubicacion \n " +
"        from produto p \n " +
"        inner join tipoproducto t on t.idtipoproducto=p.idtipoproducto \n " +
"        inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n " +
"        inner join marca m on p.idmarca=m.idmarca \n " +
"        inner join stock s on s.idproducto=p.idproduto, \n" +
        "unidades u\n" +
        "where u.nomuniddes='"+dato+"'\n" +
        "and (p.codProd like '%"+text+"%' or p.codUnid like '%"+text+"%' or p.nomproducto like '%"+text+"%' or m.nommarc like '%"+text+"%')\n" +
        "GROUP BY s.`idProducto`\n" +
        "order by p.nomproducto;";
        //control.LlenarJTabla(productosModel, control.Sql, 10);
        this.control.LlenarJTablaMayorStockMin(this.productosModel, this.control.Sql, 14);
        this.control.LimTabla(this.productosAlmacenModel);
        System.out.println(this.control.Sql);
    }
    
//      private void cargarProductosParaFactura() {
//        String text = txtBuscarProducto.getText();
//        control.Sql = "SELECT v.`ID`, v.`Descripcion`, v.`Unidad`, v.`Precxmayo`, 0.00, 0.00, (SUM(s.`cantidadDisponible`)<= v.`StockMinimo`) AS bajoDeStock  "
//                + "FROM vta_catalogo v, stock s "
//                + "WHERE s.`idProducto`=v.`ID` AND "
//                + "(v.`Descripcion` LIKE '%" + text + "%' OR v.`Unidad` LIKE '%" + text + "%' OR v.`tipo`  LIKE '%" + text + "%' OR v.`Unidad` LIKE '%" + text + "%') "
//                + "GROUP BY s.`idProducto` ORDER BY v.`Descripcion`;";
//        control.LlenarJTabla(productosModel, control.Sql, 7);
//        control.LimTabla(productosAlmacenModel);
////System.out.println(control.Sql);
////        selectedRow = -1;
//    }

    private void cargarPopup(MouseEvent evt) {
        try {
            Point point = evt.getPoint();
            int currentRow = this.tablaProductos.rowAtPoint(point);
            this.tablaProductos.setRowSelectionInterval(currentRow, currentRow);
            this.jPopupMenu1.removeAll();

            String idProducto = this.productosModel.getValueAt(currentRow, 0).toString();
            String productosContenidos = this.buscarProductoContenidoMenuItem(idProducto, "1");
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
                                    //if(ckPrecio.isSelected()){
                                    //    cargarProductosParaFactura();
                                    //}else{
                                        cargarProductos();
                                    //}
                                    tablaProductos.setRowSelectionInterval(filaProductos, filaProductos);
                                } else {
                                    JOptionPane.showMessageDialog(FrmRegistrarVenta.this, "Este producto no se puede abrir ya que no tiene existencias en stock", "Mensaje", 2);
                                }
                            }

                        }
                    });
                    this.jPopupMenu1.add(item);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void leearUnidades() {
        this.listaUnidades = this.control.getListData("SELECT u.`nomuniddes` FROM unidades u;");

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
        this.cboUnidad.removeAllItems();
        try {
            Base.rt = DevolverRegistro("SELECT * FROM unidades u order by cantidad;");
            while (Base.rt.next()) {
                Lista.add(Base.rt.getString(2));
            }
            //cbo.setSelectedIndex(0);
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
        this.cboUnidad.setEnabled(true);
        for(int i=0;i<Lista.size();i++){
            this.cboUnidad.addItem(Lista.get(i));
        }

    }

    private String buscarProductoContenidoMenuItem(String id, String cant) {

        String str = "";
        String[] des = this.control.cargarRegistro(String.format("SELECT p.`Idproductocont`,p.`CantCont`*" + cant + ", lower(v.`Unidad`),concat(v.`Producto`,' ',v.`Descripcion`) FROM vta_catalogo v, produto p "
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
        String[] des = this.control.cargarRegistro(String.format("SELECT p.`Idproductocont`,p.`CantCont`*" + cant + ", CONCAT('Contiene <b>',p.`CantCont`*" + cant + ",'</b> ',lower(v.`Unidad`),'s de <i>',v.`Producto`,'</i>') "
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
        
//"Id","idProd", "Código Prod", "Producto","Rubro", "Composición", "Presentación", "Laboratorio", "Unidad", "Lote","Fec. Venc.", "Cantidad", "Precio","Descuento", "Total", "idAlmacen","CantReal","Almacen","idLote"
//"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen"

        int row = this.tablaProductosDetalle.getSelectedRow();
        String id = this.modeloProductoDetalle.getValueAt(row, 0).toString();
        
        int buscarLista = this.buscarLista(this.modeloProductoDetalle.getValueAt(row, 1).toString(), this.modeloProductoDetalle.getValueAt(row, 15).toString());
        if (buscarLista >= 0) {
            double[] get = this.listaVenta.get(buscarLista);
            get[2] = get[2] -( Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 11).toString())*Double.parseDouble(this.modeloProductoDetalle.getValueAt(row, 16).toString()));
            this.listaVenta.set(buscarLista, get);
        }
        this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s "
                + "WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;",
                //modeloProductoDetalle.getValueAt(row, 4).toString(),
                this.cantidadtemporal,
                this.modeloProductoDetalle.getValueAt(row, 15).toString(),
                this.modeloProductoDetalle.getValueAt(row, 1).toString()));
        
        this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`+%s "
                + "WHERE s.`idlote`=%s AND s.`idproducto`=%s;",
                //modeloProductoDetalle.getValueAt(row, 4).toString(),
                this.cantidadtemporal,
                this.modeloProductoDetalle.getValueAt(row, 18).toString(),
                this.modeloProductoDetalle.getValueAt(row, 1).toString()));
    }

    public void eliminartodo() {
        this.control.fila=0;
        int tod=this.modeloProductoDetalle.getRowCount();
        while(this.control.fila<tod){
            eliminarLineatodo(this.control.fila);
            this.control.fila++;        
        }
        this.control.LimTabla(modeloProductoDetalle);
        //System.out.println("IDve"+idVenta);
         this.control.ejecutar(String.format("DELETE FROM ordenpedido WHERE `idVenta`=%s;", idVenta)); 
        this.control.ejecutar(String.format("DELETE FROM guiaderemision WHERE `idVenta`=%s;", idVenta)); 
       this.control.ejecutar(String.format("DELETE FROM venta WHERE `idVenta`=%s;", idVenta)); 
       this.idVenta = 0;
       this.CantReal=0;
       this.Unidad="";
        this.cboUnidad.setSelectedIndex(0);
        this.facturado = false;
        this.guardado = false;
        this.cancelar();
        this.calcularTotal();
        this.btnCliente.setEnabled(true);
        this.lblNumeroComprobante.setText("Sin comprobante");
        this.jDateChooser1.setEnabled(true);
        this.txtBuscarProducto.setText("");
        this.cargarProductos();
        this.txtBuscarDetalle.setText("");
        this.txtBuscarDetalle.requestFocus();
        this.btnGuardar.setEnabled(true);
       
    }
    
    
    public void NuevaVta() {
        this.control.fila=0;
        int tod=this.modeloProductoDetalle.getRowCount();
        while(this.control.fila<tod){
            //eliminarLineatodo(control.fila);
            this.control.fila++;        
        }
        this.control.LimTabla(this.modeloProductoDetalle);
        //System.out.println("IDve"+idVenta);
         //control.ejecutar(String.format("DELETE FROM ordenpedido WHERE `idVenta`=%s;", idVenta)); 
       // control.ejecutar(String.format("DELETE FROM guiaderemision WHERE `idVenta`=%s;", idVenta)); 
       //control.ejecutar(String.format("DELETE FROM venta WHERE `idVenta`=%s;", idVenta)); 
       this.idVenta = 0;
       this.CantReal=0;
       this.Unidad="";
        this.cboUnidad.setSelectedIndex(0);
        this.facturado = false;
        this.guardado = false;
        this.cancelar();
        this.calcularTotal();
        this.btnCliente.setEnabled(true);
        this.lblNumeroComprobante.setText("Sin comprobante");
        this.jDateChooser1.setEnabled(true);
        this.txtBuscarProducto.setText("");
        this.cargarProductos();
        this.txtBuscarDetalle.setText("");
        //this.txtBuscarDetalle.requestFocus();
        this.btnGuardar.setEnabled(true);
        
        
        this.lbltipodoc.setText("");
        this.txtnumdoc.setText("");
        this.txtnumdoc.requestFocus();
       
    }
    
    private void SinDEscontar(boolean as) {
      //  if(JOptionPane.showConfirmDialog(null,"No se Descontaran los Productos del Almacen","", JOptionPane.YES_NO_OPTION)==0){
            this.control.fila=0;
            int tod=this.modeloProductoDetalle.getRowCount();
            while(this.control.fila<tod){
                this.VenderSinDescontarStock(this.control.fila,as);
                this.control.fila++;        
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

    public CustomTableModel getModeloDetalle() {
        return this.modeloProductoDetalle;
    }
}
