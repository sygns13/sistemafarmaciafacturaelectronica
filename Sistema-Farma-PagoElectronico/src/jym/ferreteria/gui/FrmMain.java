/**
 * ** @author Miguel Silva Zapata
 */
package jym.ferreteria.gui;

import java.awt.*;
import jym.ferreteria.clases.InfoGeneral;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.datasource.FrmEstadisticoVentas;
import jym.ferreteria.gui.dialogs.*;
import jym.ferreteria.gui.internalframes.*;
//import org.jvnet.substance.SubstanceLookAndFeel;
//import org.jvnet.substance.skin.SkinInfo;

import jym.ferreteria.gui.dialogs.IniciarSesionAdmin;

public class FrmMain extends javax.swing.JFrame {

    private Rectangle R = null;
    private Controlador control = new Controlador();
    private static Image imagen;
    private String us = "";
    private String idusu="";
    private String StoIni="";private String sql="";
    InfoGeneral info = new InfoGeneral();
    private IniciarSesionAdmin iniciarSesionA = null;
    
    private String tipoUsu="";

    public FrmMain() {
        initComponents();
        
        CondicionesIniciales();
        asignarPermisos();
       userspeshal();
        
        

    }
    
    
    public void userspeshal(){
    idusu=Accesos.getInstance().getIdUsuario();
    //System.out.println(idusu);
    
   // JOptionPane.showMessageDialog(null, idusu);
            
    if(idusu.equals("0")){
    jmiIniciarComprobantes.setVisible(false);
    jMenu6.setVisible(false);
    jMenu6.setVisible(false);
    
    jMenuItem7.setVisible(false);
    jMenuItem7.setVisible(false);
    
    jMenuItem9.setVisible(false);
    jmiIngresoCajaVarios.setVisible(false);
    
    jmiRegistrarIngresosVarios.setVisible(false);
    
    jmiCajadelDia.setVisible(false);
    jMenu10.setVisible(false);
    
    jMenuItem1.setVisible(false);
    jMenuItem8.setVisible(false);
    
    jmiReporteCobroCuentasCobrar.setVisible(false);
    jmiReporteCobroCuentasCobrar.setVisible(false);
    jMenu11.setVisible(false);
    }
    }
    public void asignarPermisos(){
        tipoUsu=Accesos.getInstance().getTipoUsuario();
        String us=capturarUsuario();
        //JOptionPane.showMessageDialog(null, tipoUsu);
        
        if(tipoUsu.equals("Administrador")){
            jmiGestionUsuarios.setVisible(false);
        } else if(tipoUsu.equals("Vendedor")){
            
            //Menu Configuraciones
            jmiIniciarComprobantes.setVisible(false);
            jmiGestionUsuarios.setVisible(false);
            jmiConfiguracion.setVisible(false);
            jmiMisCuentasBancarias.setVisible(false);
            
            //Menu Caja
            jMenu10.setVisible(false);
            
            //Menu Ventas
            jmiIgv.setVisible(false);
            
            
            //Menu Compras
            jMenu3.setVisible(false);
            
            
            //Menu Almacen
            jMenu4.setVisible(false);
            jMenuItem8.setVisible(false);
            jMenuItem7.setVisible(false);
            
            
            //Menu ventas
            jmiVentasRealizadas.setVisible(false);
            
            
            //Modulo Reportes
            jMenu12.setVisible(false);
            jMenu14.setVisible(false);
            jMenu11.setVisible(false);
  
        }
        else if(tipoUsu.equals("Almacenero")){
            
            //Menu Configuraciones
            jmiIniciarComprobantes.setVisible(false);
            jmiGestionUsuarios.setVisible(false);
            jmiConfiguracion.setVisible(false);
            jmiMisCuentasBancarias.setVisible(false);
            
            //Menu Caja
            jMenu10.setVisible(false);
            
            //Menu Ventas
            jMenu2.setVisible(false);
            jmiVentasRealizadas.setVisible(false);
            
            //Menu Compras
            //jMenu3.setVisible(false);
            
            
            //Menu Almacen
            //jMenu4.setVisible(false);
            //jMenuItem8.setVisible(false);
            jMenuItem7.setVisible(false);
            
            
            
            //Modulo Reportes
             jMenu12.setVisible(false);
            jMenu15.setVisible(false);
            jMenu11.setVisible(false);
            
           
  
        }
        
        if(tipoUsu.equals("Cajero")){
            
            //Menu Configuraciones
            jmiIniciarComprobantes.setVisible(false);
            jmiGestionUsuarios.setVisible(false);
            jmiConfiguracion.setVisible(false);
            jmiMisCuentasBancarias.setVisible(false);
            
            
            //Menu Caja
            
            
            //Menu Ventas
            jmiCotizacion.setVisible(false);
            jMenuItem3.setVisible(false);
            jmiRegistrarVenta.setVisible(false);
            jmiIgv.setVisible(false);
            jmiVentasRealizadas.setVisible(false);
            
            //Menu Compras
            jmiRegistrarEntrada.setVisible(false);
            
            
            //Menu Almacen
            
            jMenu4.setVisible(false);
            
            jMenuItem8.setVisible(false);
            jMenuItem7.setVisible(false);
            
            
            //Modulo Reportes
             jMenu12.setVisible(false);

  
        }
        
    }
    
    public void CondicionesIniciales(){
    jmiReportePagoCuentasPagar.setText("Reporte de Pagos Realizados");
        jmiReporteCobroCuentasCobrar.setText("Reporte de Pagos de Clientes Cobrados");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource(Controlador.ICON_PATH)).getImage());
        imagen = new ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/logo.png")).getImage();
        
        
        //jMenuItem14.setVisible(false);

        
        jmiRegularizacionStock.setVisible(false);
            jmiReporteRegularizacionStock.setVisible(false);
            jmiClasificacionConceptoGastos.setVisible(false);
            jmiRegistrarPeriodoAnalisis.setVisible(false);
            jmiRegistrarGastos.setVisible(false);
            jmiRegistrarPedido.setVisible(false);
            jmiPedidosRealizados.setVisible(false);
            jMenu7.setVisible(false);
            jmiOrdenPedidoEmitidos.setVisible(false);
            jmiUtilidadesxVenta.setVisible(false);
            jMenuItem37.setVisible(false);
            jmiAlmacenes.setVisible(false);
            jmiTarjetasCredito.setVisible(false);
            jmiReportePedidos.setVisible(false);
            
            //Try Combos
            jMenuItem13.setVisible(false);
            
            //Cotizacion!!!
            //jmiCotizacion.setVisible(false);
            //jMenuItem3.setVisible(false);
            
            //Guias de Remision!!!
            jmiGuiaRemisionEmitidas.setVisible(false);
            
            
            //Manual de Usuario
           // jMenuItem17.setVisible(false);
            
            
            //String idUs= Accesos.getInstance().getIdUsuario();
            //JOptionPane.showMessageDialog(null, us);
            /*if(us.equals("crisfer")){
            jMenuStockIn.setVisible(true);
            }
            else{
                jMenuStockIn.setVisible(false);
            }*/
            
            //casoEspecial
            jMenuStockIn.setVisible(false);
            
            sql="select valor from config where idconfig='stoIni';";
            StoIni=control.CrearRegistroDev(sql);
            
            if(StoIni.equals("Inactivo")){
            jmiInventarioInicial.setVisible(false);
            }
            
            jMenu8.setVisible(false);
            
            jmiCotizacion.setVisible(false);
            jMenuItem3.setVisible(false);
    }
    
     public void iniciarsesionCuentasPorCobrar(){
        if (this.iniciarSesionA == null) {
            this.iniciarSesionA = new IniciarSesionAdmin(null, true);
        }
        this.iniciarSesionA.setLocationRelativeTo(this);
        this.iniciarSesionA.setVisible(true);
        this.iniciarSesionA.limpiar();
        
        boolean result = this.iniciarSesionA.getResult();
        if (result != false) {
              try {
            loadFrame(43);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        escritorio = new javax.swing.JDesktopPane()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d=(Graphics2D)g;
                g2d.setColor(new Color(0,50,255));
                //g2d.fillRect(0, 0, getWidth(), getHeight());
                int altura = (int)imagen.getHeight(this)/2;
                int anchura = (int)(imagen.getWidth(this)/2);
                //Dibujamos la imagen con las dimensiones apropiadas en el escritorio usando el metodo drawImage
                g.drawImage(imagen, (int)getWidth()/2 - anchura, (int)getHeight()/2 - altura, this);

            }};    ;
            jMenuBar1 = new javax.swing.JMenuBar();
            jMenu1 = new javax.swing.JMenu();
            jmiIniciarComprobantes = new javax.swing.JMenuItem();
            jMenu6 = new javax.swing.JMenu();
            jmiGestionUsuarios = new javax.swing.JMenuItem();
            jmiCambiarClave = new javax.swing.JMenuItem();
            jMenu7 = new javax.swing.JMenu();
            jmiBackup = new javax.swing.JMenuItem();
            jmiRestaurarBackup = new javax.swing.JMenuItem();
            jMenu8 = new javax.swing.JMenu();
            jMenuItem37 = new javax.swing.JMenuItem();
            jmiAsignarPermisos = new javax.swing.JMenuItem();
            jmiConfiguracion = new javax.swing.JMenuItem();
            jmiMisCuentasBancarias = new javax.swing.JMenuItem();
            jMenuStockIn = new javax.swing.JMenuItem();
            jMenuItem1 = new javax.swing.JMenuItem();
            jmiSalir = new javax.swing.JMenuItem();
            jMenu10 = new javax.swing.JMenu();
            jmiClasificacionConceptoGastos = new javax.swing.JMenuItem();
            jmiRegistrarPeriodoAnalisis = new javax.swing.JMenuItem();
            jmiRegistrarGastos = new javax.swing.JMenuItem();
            jSeparator12 = new javax.swing.JPopupMenu.Separator();
            jmiRegistrarIngresosVarios = new javax.swing.JMenuItem();
            jSeparator13 = new javax.swing.JPopupMenu.Separator();
            jmiCajadelDia = new javax.swing.JMenuItem();
            jMenu2 = new javax.swing.JMenu();
            jmiClientes = new javax.swing.JMenuItem();
            jSeparator6 = new javax.swing.JPopupMenu.Separator();
            jmiCotizacion = new javax.swing.JMenuItem();
            jMenuItem3 = new javax.swing.JMenuItem();
            jMenuItem13 = new javax.swing.JMenuItem();
            jSeparator15 = new javax.swing.JPopupMenu.Separator();
            jmiRegistrarVenta = new javax.swing.JMenuItem();
            jmiVentasRealizadas = new javax.swing.JMenuItem();
            jMenuItem14 = new javax.swing.JMenuItem();
            jSeparator16 = new javax.swing.JPopupMenu.Separator();
            jmiGuiaRemisionEmitidas = new javax.swing.JMenuItem();
            jmiOrdenPedidoEmitidos = new javax.swing.JMenuItem();
            jmiCuentasPorCobrar = new javax.swing.JMenuItem();
            jSeparator7 = new javax.swing.JPopupMenu.Separator();
            jmiIgv = new javax.swing.JMenuItem();
            jmiTarjetasCredito = new javax.swing.JMenuItem();
            jMenu3 = new javax.swing.JMenu();
            jmiProveedores = new javax.swing.JMenuItem();
            jmiBancos = new javax.swing.JMenuItem();
            jmiCuentasBancarias = new javax.swing.JMenuItem();
            jSeparator3 = new javax.swing.JPopupMenu.Separator();
            jmiRegistrarPedido = new javax.swing.JMenuItem();
            jmiPedidosRealizados = new javax.swing.JMenuItem();
            jSeparator1 = new javax.swing.JPopupMenu.Separator();
            jmiRegistrarEntrada = new javax.swing.JMenuItem();
            jmiEntradasRealizadas = new javax.swing.JMenuItem();
            jSeparator2 = new javax.swing.JPopupMenu.Separator();
            jmiFacturasProveedores = new javax.swing.JMenuItem();
            jMenu4 = new javax.swing.JMenu();
            jmiCatalogoProductos = new javax.swing.JMenuItem();
            jSeparator4 = new javax.swing.JPopupMenu.Separator();
            jmiTipoProductos = new javax.swing.JMenuItem();
            jmiMarcaProductos = new javax.swing.JMenuItem();
            jMenuItem5 = new javax.swing.JMenuItem();
            jmiUnidades = new javax.swing.JMenuItem();
            jSeparator5 = new javax.swing.JPopupMenu.Separator();
            jmiAlmacenes = new javax.swing.JMenuItem();
            jmiInventario = new javax.swing.JMenuItem();
            jmiRegularizacionStock = new javax.swing.JMenuItem();
            jmiInventarioInicial = new javax.swing.JMenuItem();
            jMenuItem7 = new javax.swing.JMenuItem();
            jMenuItem8 = new javax.swing.JMenuItem();
            jSeparator8 = new javax.swing.JPopupMenu.Separator();
            jMenuItem4 = new javax.swing.JMenuItem();
            jMenuItem6 = new javax.swing.JMenuItem();
            jMenu12 = new javax.swing.JMenu();
            jmiReporteClientes = new javax.swing.JMenuItem();
            jMenu9 = new javax.swing.JMenu();
            jmiListadoProveedores = new javax.swing.JMenuItem();
            jmiCuentasProveedores = new javax.swing.JMenuItem();
            jmiReporteBancos = new javax.swing.JMenuItem();
            jmiReporteMarcas = new javax.swing.JMenuItem();
            jmiReporteLineaProducto = new javax.swing.JMenuItem();
            jMenu16 = new javax.swing.JMenu();
            jmiProductosxAlmacen = new javax.swing.JMenuItem();
            jmiReporteInventario = new javax.swing.JMenuItem();
            jmiListadoPreciosProductos = new javax.swing.JMenuItem();
            jMenuItem9 = new javax.swing.JMenuItem();
            jMenu14 = new javax.swing.JMenu();
            jmiReportePedidos = new javax.swing.JMenuItem();
            jSeparator9 = new javax.swing.JPopupMenu.Separator();
            jmiReporteEntradaStock = new javax.swing.JMenuItem();
            jmiReporteStockDetallado = new javax.swing.JMenuItem();
            jMenuItem2 = new javax.swing.JMenuItem();
            jSeparator10 = new javax.swing.JPopupMenu.Separator();
            jmiReporteCuentasPagar = new javax.swing.JMenuItem();
            jmiReportePagoCuentasPagar = new javax.swing.JMenuItem();
            jMenu15 = new javax.swing.JMenu();
            jmiVentaCabecera = new javax.swing.JMenuItem();
            jMenuItem12 = new javax.swing.JMenuItem();
            jmiVentaDetalle = new javax.swing.JMenuItem();
            jMenuItem10 = new javax.swing.JMenuItem();
            jmiUtilidadesxVenta = new javax.swing.JMenuItem();
            jSeparator11 = new javax.swing.JPopupMenu.Separator();
            jmiReporteCuentasCobrar = new javax.swing.JMenuItem();
            jmiReporteCobroCuentasCobrar = new javax.swing.JMenuItem();
            jMenuItem11 = new javax.swing.JMenuItem();
            jmiReporteRegularizacionStock = new javax.swing.JMenuItem();
            jMenu11 = new javax.swing.JMenu();
            jmiRptCajaDiaria = new javax.swing.JMenuItem();
            jmiIngresoCajaVentas = new javax.swing.JMenuItem();
            jmiIngresoCajaVarios = new javax.swing.JMenuItem();
            jmiGastosPagoProveedor = new javax.swing.JMenuItem();
            jmiGastosVarios = new javax.swing.JMenuItem();
            jmiIngresosEgresosDetallado = new javax.swing.JMenuItem();
            jMenu5 = new javax.swing.JMenu();
            jMenuItem16 = new javax.swing.JMenuItem();
            jMenuItem17 = new javax.swing.JMenuItem();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(204, 255, 102));
            setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent evt) {
                    formComponentShown(evt);
                }
            });
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowActivated(java.awt.event.WindowEvent evt) {
                    formWindowActivated(evt);
                }
            });

            escritorio.setBackground(new java.awt.Color(255, 255, 255));
            escritorio.setAutoscrolls(true);
            escritorio.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    escritorioMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(escritorio);

            getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

            jMenuBar1.setBorderPainted(false);
            jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenuBar1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
            jMenuBar1.setPreferredSize(new java.awt.Dimension(804, 26));

            jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Key.png"))); // NOI18N
            jMenu1.setMnemonic('C');
            jMenu1.setText("Configuracion y Seguridad del Sistema");
            jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu1.setDisplayedMnemonicIndex(-1);
            jMenu1.setPreferredSize(new java.awt.Dimension(261, 34));

            jmiIniciarComprobantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/note_go.png"))); // NOI18N
            jmiIniciarComprobantes.setText("Iniciar Comprobantes");
            jmiIniciarComprobantes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiIniciarComprobantesActionPerformed(evt);
                }
            });
            jMenu1.add(jmiIniciarComprobantes);

            jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/User.png"))); // NOI18N
            jMenu6.setText("Usuarios");

            jmiGestionUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/user_add.png"))); // NOI18N
            jmiGestionUsuarios.setText("Gestión de usuarios");
            jmiGestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiGestionUsuariosActionPerformed(evt);
                }
            });
            jMenu6.add(jmiGestionUsuarios);

            jmiCambiarClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/password_protect_directories.png"))); // NOI18N
            jmiCambiarClave.setText("Cambiar Password");
            jmiCambiarClave.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCambiarClaveActionPerformed(evt);
                }
            });
            jMenu6.add(jmiCambiarClave);

            jMenu1.add(jMenu6);

            jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Database.png"))); // NOI18N
            jMenu7.setText("Bases de datos");

            jmiBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/backups.png"))); // NOI18N
            jmiBackup.setText("Backup de la Base de datos");
            jmiBackup.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiBackupActionPerformed(evt);
                }
            });
            jMenu7.add(jmiBackup);

            jmiRestaurarBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/download_database.png"))); // NOI18N
            jmiRestaurarBackup.setText("Restaurar la Base de datos");
            jmiRestaurarBackup.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRestaurarBackupActionPerformed(evt);
                }
            });
            jMenu7.add(jmiRestaurarBackup);

            jMenu1.add(jMenu7);

            jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/toolbar.png"))); // NOI18N
            jMenu8.setText("Permisos");

            jMenuItem37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/toolbar_add.png"))); // NOI18N
            jMenuItem37.setText("Agregar Permisos");
            jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem37ActionPerformed(evt);
                }
            });
            jMenu8.add(jMenuItem37);

            jmiAsignarPermisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/toolbar_go.png"))); // NOI18N
            jmiAsignarPermisos.setText("Asignar Permisos");
            jmiAsignarPermisos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiAsignarPermisosActionPerformed(evt);
                }
            });
            jMenu8.add(jmiAsignarPermisos);

            jMenu1.add(jMenu8);

            jmiConfiguracion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
            jmiConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Settings-icon.png"))); // NOI18N
            jmiConfiguracion.setText("Configuración");
            jmiConfiguracion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiConfiguracionActionPerformed(evt);
                }
            });
            jMenu1.add(jmiConfiguracion);

            jmiMisCuentasBancarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/tarjetas-credito.png"))); // NOI18N
            jmiMisCuentasBancarias.setText("Mis cuentas bancarias");
            jmiMisCuentasBancarias.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiMisCuentasBancariasActionPerformed(evt);
                }
            });
            jMenu1.add(jmiMisCuentasBancarias);

            jMenuStockIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/List.png"))); // NOI18N
            jMenuStockIn.setText("Controlar Stock Inicial");
            jMenuStockIn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuStockInActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuStockIn);

            jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Restart.png"))); // NOI18N
            jMenuItem1.setText("Cambiar de Usuario");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem1);

            jmiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
            jmiSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-window-close-icon.png"))); // NOI18N
            jmiSalir.setText("Salir del Sistema");
            jmiSalir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiSalirActionPerformed(evt);
                }
            });
            jMenu1.add(jmiSalir);

            jMenuBar1.add(jMenu1);

            jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cuentas.png"))); // NOI18N
            jMenu10.setMnemonic('G');
            jMenu10.setText("Movimiento de Caja");
            jMenu10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu10.setFocusPainted(true);
            jMenu10.setPreferredSize(new java.awt.Dimension(160, 24));

            jmiClasificacionConceptoGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/category-icon.png"))); // NOI18N
            jmiClasificacionConceptoGastos.setText("Clasificación y Conceptos de gastos");
            jmiClasificacionConceptoGastos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiClasificacionConceptoGastosActionPerformed(evt);
                }
            });
            jMenu10.add(jmiClasificacionConceptoGastos);

            jmiRegistrarPeriodoAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/light-bulb-icon.png"))); // NOI18N
            jmiRegistrarPeriodoAnalisis.setText("Registrar periodos de análisis");
            jmiRegistrarPeriodoAnalisis.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegistrarPeriodoAnalisisActionPerformed(evt);
                }
            });
            jMenu10.add(jmiRegistrarPeriodoAnalisis);

            jmiRegistrarGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/nuevo-sol-16.png"))); // NOI18N
            jmiRegistrarGastos.setText("Registrar gastos");
            jmiRegistrarGastos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegistrarGastosActionPerformed(evt);
                }
            });
            jMenu10.add(jmiRegistrarGastos);
            jMenu10.add(jSeparator12);

            jmiRegistrarIngresosVarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/dinero-icono-8242-16.png"))); // NOI18N
            jmiRegistrarIngresosVarios.setText(" Registrar Movimiento de Caja");
            jmiRegistrarIngresosVarios.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegistrarIngresosVariosActionPerformed(evt);
                }
            });
            jMenu10.add(jmiRegistrarIngresosVarios);
            jMenu10.add(jSeparator13);

            jmiCajadelDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/la-lucha-contra-la-caja-registradora-icono-4028-16.png"))); // NOI18N
            jmiCajadelDia.setText("Caja del día");
            jmiCajadelDia.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCajadelDiaActionPerformed(evt);
                }
            });
            jMenu10.add(jmiCajadelDia);

            jMenuBar1.add(jMenu10);

            jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/ventasps.png"))); // NOI18N
            jMenu2.setMnemonic('v');
            jMenu2.setText("Ventas");
            jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu2.setDisplayedMnemonicIndex(-1);
            jMenu2.setPreferredSize(new java.awt.Dimension(81, 24));

            jmiClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
            jmiClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/parentesco.png"))); // NOI18N
            jmiClientes.setText("Clientes");
            jmiClientes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiClientesActionPerformed(evt);
                }
            });
            jMenu2.add(jmiClientes);
            jMenu2.add(jSeparator6);

            jmiCotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/generate_ssl_certificate.png"))); // NOI18N
            jmiCotizacion.setText("Cotizacion de Productos");
            jmiCotizacion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCotizacionActionPerformed(evt);
                }
            });
            jMenu2.add(jmiCotizacion);

            jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Price list.png"))); // NOI18N
            jMenuItem3.setText("Cotizaciones Realizadas");
            jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem3ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem3);

            jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Full basket.png"))); // NOI18N
            jMenuItem13.setText("Configurar Combos de Productos");
            jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem13ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem13);
            jMenu2.add(jSeparator15);

            jmiRegistrarVenta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
            jmiRegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/money_add.png"))); // NOI18N
            jmiRegistrarVenta.setText("Realizar Venta");
            jmiRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegistrarVentaActionPerformed(evt);
                }
            });
            jMenu2.add(jmiRegistrarVenta);

            jmiVentasRealizadas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
            jmiVentasRealizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/document_editing.png"))); // NOI18N
            jmiVentasRealizadas.setText("Ventas Realizadas");
            jmiVentasRealizadas.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiVentasRealizadasActionPerformed(evt);
                }
            });
            jMenu2.add(jmiVentasRealizadas);

            jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/report_go.png"))); // NOI18N
            jMenuItem14.setText("Resúmenes de Boletas");
            jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem14ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem14);
            jMenu2.add(jSeparator16);

            jmiGuiaRemisionEmitidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/page_copy.png"))); // NOI18N
            jmiGuiaRemisionEmitidas.setText("Guias de Remision Emitidas");
            jmiGuiaRemisionEmitidas.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiGuiaRemisionEmitidasActionPerformed(evt);
                }
            });
            jMenu2.add(jmiGuiaRemisionEmitidas);

            jmiOrdenPedidoEmitidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/receipt_share.png"))); // NOI18N
            jmiOrdenPedidoEmitidos.setText("Orden de Pedido Emitidos");
            jmiOrdenPedidoEmitidos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiOrdenPedidoEmitidosActionPerformed(evt);
                }
            });
            jMenu2.add(jmiOrdenPedidoEmitidos);

            jmiCuentasPorCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/coins_add.png"))); // NOI18N
            jmiCuentasPorCobrar.setText(" Cuentas por cobrar");
            jmiCuentasPorCobrar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCuentasPorCobrarActionPerformed(evt);
                }
            });
            jMenu2.add(jmiCuentasPorCobrar);
            jMenu2.add(jSeparator7);

            jmiIgv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
            jmiIgv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/coins_in_hand.png"))); // NOI18N
            jmiIgv.setText("IGV");
            jmiIgv.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiIgvActionPerformed(evt);
                }
            });
            jMenu2.add(jmiIgv);

            jmiTarjetasCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Window-Enter-icon.png"))); // NOI18N
            jmiTarjetasCredito.setText("Tarjetas de crédito");
            jmiTarjetasCredito.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiTarjetasCreditoActionPerformed(evt);
                }
            });
            jMenu2.add(jmiTarjetasCredito);

            jMenuBar1.add(jMenu2);

            jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/vendermenu.png"))); // NOI18N
            jMenu3.setMnemonic('o');
            jMenu3.setText("Compras");
            jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu3.setFocusPainted(true);
            jMenu3.setPreferredSize(new java.awt.Dimension(95, 24));

            jmiProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
            jmiProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/premium_support.png"))); // NOI18N
            jmiProveedores.setText("Proveedores");
            jmiProveedores.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiProveedoresActionPerformed(evt);
                }
            });
            jMenu3.add(jmiProveedores);

            jmiBancos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
            jmiBancos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/bank.png"))); // NOI18N
            jmiBancos.setText("Bancos");
            jmiBancos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiBancosActionPerformed(evt);
                }
            });
            jMenu3.add(jmiBancos);

            jmiCuentasBancarias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
            jmiCuentasBancarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/client_account_template.png"))); // NOI18N
            jmiCuentasBancarias.setText("Cuentas bancarias");
            jmiCuentasBancarias.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCuentasBancariasActionPerformed(evt);
                }
            });
            jMenu3.add(jmiCuentasBancarias);
            jMenu3.add(jSeparator3);

            jmiRegistrarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cart_full.png"))); // NOI18N
            jmiRegistrarPedido.setText("Nuevo Pedido");
            jmiRegistrarPedido.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegistrarPedidoActionPerformed(evt);
                }
            });
            jMenu3.add(jmiRegistrarPedido);

            jmiPedidosRealizados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/order-history-icon.png"))); // NOI18N
            jmiPedidosRealizados.setText("Pedidos realizados");
            jmiPedidosRealizados.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiPedidosRealizadosActionPerformed(evt);
                }
            });
            jMenu3.add(jmiPedidosRealizados);
            jMenu3.add(jSeparator1);

            jmiRegistrarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/basket.png"))); // NOI18N
            jmiRegistrarEntrada.setText("Compras a Proveedores");
            jmiRegistrarEntrada.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegistrarEntradaActionPerformed(evt);
                }
            });
            jMenu3.add(jmiRegistrarEntrada);

            jmiEntradasRealizadas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
            jmiEntradasRealizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/list_box.png"))); // NOI18N
            jmiEntradasRealizadas.setText("Entradas de stock realizadas (compras)");
            jmiEntradasRealizadas.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiEntradasRealizadasActionPerformed(evt);
                }
            });
            jMenu3.add(jmiEntradasRealizadas);
            jMenu3.add(jSeparator2);

            jmiFacturasProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
            jmiFacturasProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/coins_delete.png"))); // NOI18N
            jmiFacturasProveedores.setText("Cuentas por pagar");
            jmiFacturasProveedores.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiFacturasProveedoresActionPerformed(evt);
                }
            });
            jMenu3.add(jmiFacturasProveedores);

            jMenuBar1.add(jMenu3);

            jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Box.png"))); // NOI18N
            jMenu4.setMnemonic('I');
            jMenu4.setText("Almacen");
            jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu4.setFocusPainted(true);
            jMenu4.setPreferredSize(new java.awt.Dimension(93, 24));

            jmiCatalogoProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
            jmiCatalogoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/list_box.png"))); // NOI18N
            jmiCatalogoProductos.setText("Catálogo de producto");
            jmiCatalogoProductos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCatalogoProductosActionPerformed(evt);
                }
            });
            jMenu4.add(jmiCatalogoProductos);
            jMenu4.add(jSeparator4);

            jmiTipoProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
            jmiTipoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/category-icon.png"))); // NOI18N
            jmiTipoProductos.setText("Rubros");
            jmiTipoProductos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiTipoProductosActionPerformed(evt);
                }
            });
            jMenu4.add(jmiTipoProductos);

            jmiMarcaProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
            jmiMarcaProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/odata.png"))); // NOI18N
            jmiMarcaProductos.setText("Laboratorios");
            jmiMarcaProductos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiMarcaProductosActionPerformed(evt);
                }
            });
            jMenu4.add(jmiMarcaProductos);

            jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Window-Enter-icon.png"))); // NOI18N
            jMenuItem5.setText("Presentaciones");
            jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem5ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem5);

            jmiUnidades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
            jmiUnidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/group_box.png"))); // NOI18N
            jmiUnidades.setText("Unidades");
            jmiUnidades.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiUnidadesActionPerformed(evt);
                }
            });
            jMenu4.add(jmiUnidades);
            jMenu4.add(jSeparator5);

            jmiAlmacenes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
            jmiAlmacenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Box.png"))); // NOI18N
            jmiAlmacenes.setText("Almacenes");
            jmiAlmacenes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiAlmacenesActionPerformed(evt);
                }
            });
            jMenu4.add(jmiAlmacenes);

            jmiInventario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
            jmiInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/inventory-maintenance-icon.png"))); // NOI18N
            jmiInventario.setText("Inventario");
            jmiInventario.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiInventarioActionPerformed(evt);
                }
            });
            jMenu4.add(jmiInventario);

            jmiRegularizacionStock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
            jmiRegularizacionStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Refresh-icon.png"))); // NOI18N
            jmiRegularizacionStock.setText("Regularización de Stock");
            jmiRegularizacionStock.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRegularizacionStockActionPerformed(evt);
                }
            });
            jMenu4.add(jmiRegularizacionStock);

            jmiInventarioInicial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
            jmiInventarioInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Start-Menu-Program-Defaults-icon.png"))); // NOI18N
            jmiInventarioInicial.setText("Inventario Inicial");
            jmiInventarioInicial.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiInventarioInicialActionPerformed(evt);
                }
            });
            jMenu4.add(jmiInventarioInicial);

            jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/eliminar.png"))); // NOI18N
            jMenuItem7.setText("Gestión de Lotes de Productos");
            jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem7ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem7);

            jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/note_go.png"))); // NOI18N
            jMenuItem8.setText("Salidas Libres de Productos Realizadas");
            jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem8ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem8);
            jMenu4.add(jSeparator8);

            jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/SEO-icon.png"))); // NOI18N
            jMenuItem4.setText("Productos bajos de stock");
            jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem4ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem4);

            jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/odata.png"))); // NOI18N
            jMenuItem6.setText("Lista de Productos vencidos");
            jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem6ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem6);

            jMenuBar1.add(jMenu4);

            jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Diagram.png"))); // NOI18N
            jMenu12.setMnemonic('R');
            jMenu12.setText("Reportes");
            jMenu12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu12.setFocusPainted(true);
            jMenu12.setPreferredSize(new java.awt.Dimension(93, 24));

            jmiReporteClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteClientes.setText("Reporte de Clientes");
            jmiReporteClientes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteClientesActionPerformed(evt);
                }
            });
            jMenu12.add(jmiReporteClientes);

            jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenu9.setText("Proveedores");

            jmiListadoProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiListadoProveedores.setText("Listado de proveedores");
            jmiListadoProveedores.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiListadoProveedoresActionPerformed(evt);
                }
            });
            jMenu9.add(jmiListadoProveedores);

            jmiCuentasProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiCuentasProveedores.setText("Cuenta de proveedores");
            jmiCuentasProveedores.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiCuentasProveedoresActionPerformed(evt);
                }
            });
            jMenu9.add(jmiCuentasProveedores);

            jMenu12.add(jMenu9);

            jmiReporteBancos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteBancos.setText("Reporte de Bancos");
            jmiReporteBancos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteBancosActionPerformed(evt);
                }
            });
            jMenu12.add(jmiReporteBancos);

            jmiReporteMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteMarcas.setText("Reporte de Laboratorios");
            jmiReporteMarcas.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteMarcasActionPerformed(evt);
                }
            });
            jMenu12.add(jmiReporteMarcas);

            jmiReporteLineaProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteLineaProducto.setText("Reporte de Rubros");
            jmiReporteLineaProducto.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteLineaProductoActionPerformed(evt);
                }
            });
            jMenu12.add(jmiReporteLineaProducto);

            jMenu16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenu16.setText("Productos");
            jMenu16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenu16ActionPerformed(evt);
                }
            });

            jmiProductosxAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiProductosxAlmacen.setText("Productos por Almacen");
            jmiProductosxAlmacen.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiProductosxAlmacenActionPerformed(evt);
                }
            });
            jMenu16.add(jmiProductosxAlmacen);

            jmiReporteInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteInventario.setText("Reporte de Inventario");
            jmiReporteInventario.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteInventarioActionPerformed(evt);
                }
            });
            jMenu16.add(jmiReporteInventario);

            jmiListadoPreciosProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiListadoPreciosProductos.setText("Listado de precios de productos");
            jmiListadoPreciosProductos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiListadoPreciosProductosActionPerformed(evt);
                }
            });
            jMenu16.add(jmiListadoPreciosProductos);

            jMenu12.add(jMenu16);

            jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenuItem9.setText("Reporte de Salidas Libres");
            jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem9ActionPerformed(evt);
                }
            });
            jMenu12.add(jMenuItem9);

            jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenu14.setText("Compras");

            jmiReportePedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReportePedidos.setText("Pedidos");
            jmiReportePedidos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReportePedidosActionPerformed(evt);
                }
            });
            jMenu14.add(jmiReportePedidos);
            jMenu14.add(jSeparator9);

            jmiReporteEntradaStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteEntradaStock.setText("Compras - Entradas de stock");
            jmiReporteEntradaStock.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteEntradaStockActionPerformed(evt);
                }
            });
            jMenu14.add(jmiReporteEntradaStock);

            jmiReporteStockDetallado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteStockDetallado.setText("Compras Detallados por Producto");
            jmiReporteStockDetallado.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteStockDetalladoActionPerformed(evt);
                }
            });
            jMenu14.add(jmiReporteStockDetallado);

            jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenuItem2.setText("Compras Detallados por Proveedor");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem2ActionPerformed(evt);
                }
            });
            jMenu14.add(jMenuItem2);
            jMenu14.add(jSeparator10);

            jmiReporteCuentasPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteCuentasPagar.setText("Reporte de Cuentas por pagar");
            jmiReporteCuentasPagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteCuentasPagarActionPerformed(evt);
                }
            });
            jMenu14.add(jmiReporteCuentasPagar);

            jmiReportePagoCuentasPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReportePagoCuentasPagar.setText("Pago de cuentas por pagar");
            jmiReportePagoCuentasPagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReportePagoCuentasPagarActionPerformed(evt);
                }
            });
            jMenu14.add(jmiReportePagoCuentasPagar);

            jMenu12.add(jMenu14);

            jMenu15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenu15.setText("Ventas");
            jMenu15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jMenu15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenu15ActionPerformed(evt);
                }
            });

            jmiVentaCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiVentaCabecera.setText("Venta Cabecera");
            jmiVentaCabecera.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiVentaCabeceraActionPerformed(evt);
                }
            });
            jMenu15.add(jmiVentaCabecera);

            jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenuItem12.setText("Ventas por Vendedor");
            jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem12ActionPerformed(evt);
                }
            });
            jMenu15.add(jMenuItem12);

            jmiVentaDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiVentaDetalle.setText("Venta Detalle");
            jmiVentaDetalle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiVentaDetalleActionPerformed(evt);
                }
            });
            jMenu15.add(jmiVentaDetalle);

            jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenuItem10.setText("Top de Productos Vendidos");
            jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem10ActionPerformed(evt);
                }
            });
            jMenu15.add(jMenuItem10);

            jmiUtilidadesxVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiUtilidadesxVenta.setText("Utilidades por Venta");
            jmiUtilidadesxVenta.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiUtilidadesxVentaActionPerformed(evt);
                }
            });
            jMenu15.add(jmiUtilidadesxVenta);
            jMenu15.add(jSeparator11);

            jmiReporteCuentasCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteCuentasCobrar.setText("Reporte de Cuentas por cobrar");
            jmiReporteCuentasCobrar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteCuentasCobrarActionPerformed(evt);
                }
            });
            jMenu15.add(jmiReporteCuentasCobrar);

            jmiReporteCobroCuentasCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteCobroCuentasCobrar.setText("Cobro - Cuentas por cobrar");
            jmiReporteCobroCuentasCobrar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteCobroCuentasCobrarActionPerformed(evt);
                }
            });
            jMenu15.add(jmiReporteCobroCuentasCobrar);

            jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenuItem11.setText("Análisis Estadístico");
            jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem11ActionPerformed(evt);
                }
            });
            jMenu15.add(jMenuItem11);

            jMenu12.add(jMenu15);

            jmiReporteRegularizacionStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiReporteRegularizacionStock.setText("Reporte de Regularización de stock");
            jmiReporteRegularizacionStock.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiReporteRegularizacionStockActionPerformed(evt);
                }
            });
            jMenu12.add(jmiReporteRegularizacionStock);

            jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jMenu11.setText("Caja");

            jmiRptCajaDiaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiRptCajaDiaria.setText("Caja Diaria");
            jmiRptCajaDiaria.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiRptCajaDiariaActionPerformed(evt);
                }
            });
            jMenu11.add(jmiRptCajaDiaria);

            jmiIngresoCajaVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiIngresoCajaVentas.setText("Ingreso caja - Ventas");
            jmiIngresoCajaVentas.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiIngresoCajaVentasActionPerformed(evt);
                }
            });
            jMenu11.add(jmiIngresoCajaVentas);

            jmiIngresoCajaVarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiIngresoCajaVarios.setText("Ingreso caja - Varios");
            jmiIngresoCajaVarios.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiIngresoCajaVariosActionPerformed(evt);
                }
            });
            jMenu11.add(jmiIngresoCajaVarios);

            jmiGastosPagoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiGastosPagoProveedor.setText("Gastos por pago a proveedor");
            jmiGastosPagoProveedor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiGastosPagoProveedorActionPerformed(evt);
                }
            });
            jMenu11.add(jmiGastosPagoProveedor);

            jmiGastosVarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiGastosVarios.setText("Gastos - Varios");
            jmiGastosVarios.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiGastosVariosActionPerformed(evt);
                }
            });
            jMenu11.add(jmiGastosVarios);

            jmiIngresosEgresosDetallado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-pdf-icon.png"))); // NOI18N
            jmiIngresosEgresosDetallado.setText("Ingresos - Egresos detallado");
            jmiIngresosEgresosDetallado.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiIngresosEgresosDetalladoActionPerformed(evt);
                }
            });
            jMenu11.add(jmiIngresosEgresosDetallado);

            jMenu12.add(jMenu11);

            jMenuBar1.add(jMenu12);

            jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Info.png"))); // NOI18N
            jMenu5.setMnemonic('y');
            jMenu5.setText(" Ayuda");
            jMenu5.setFocusPainted(true);
            jMenu5.setPreferredSize(new java.awt.Dimension(81, 24));

            jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
            jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Button-Help-icon.png"))); // NOI18N
            jMenuItem16.setText("Acerca de... ");
            jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem16ActionPerformed(evt);
                }
            });
            jMenu5.add(jMenuItem16);

            jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/book.png"))); // NOI18N
            jMenuItem17.setText("Manual de Usuario");
            jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem17ActionPerformed(evt);
                }
            });
            jMenu5.add(jMenuItem17);

            jMenuBar1.add(jMenu5);

            setJMenuBar(jMenuBar1);

            pack();
        }// </editor-fold>//GEN-END:initComponents
private void jmiBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiBancosActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")||tipoUsu.equals("Cajero")){
    try {
            loadFrame(8);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiBancosActionPerformed
private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    R = escritorio.getBounds();
    iniciarCaja();
    SwingWorker worker = new SwingWorker() {

        @Override
        protected Object doInBackground() throws Exception {
            FrmProductosBajosDeStock frm = new FrmProductosBajosDeStock();
            escritorio.add(frm, JLayeredPane.DEFAULT_LAYER);
            frm.setLocation((R.width - frm.getWidth()), R.height - frm.getHeight());
            frm.setVisible(true);

             FrmProductosVencidos frm2 = new FrmProductosVencidos();
            escritorio.add(frm2, JLayeredPane.DEFAULT_LAYER);
            frm2.setLocation((R.width - frm2.getWidth()), R.height - frm2.getHeight());
            frm2.setVisible(true);
            
            return true;
            
           
            
            
        }
    };
    worker.execute();
}//GEN-LAST:event_formComponentShown
private void jmiMarcaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMarcaProductosActionPerformed
    if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
        try {
            loadFrame(18);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiMarcaProductosActionPerformed
private void jmiUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUnidadesActionPerformed
    if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
        try {
            loadFrame(19);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiUnidadesActionPerformed
private void jmiTipoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTipoProductosActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
    try {
            loadFrame(17);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiTipoProductosActionPerformed
private void jmiProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProveedoresActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")||tipoUsu.equals("Cajero")){
    try {
            loadFrame(22);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiProveedoresActionPerformed
private void jmiCuentasBancariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCuentasBancariasActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")||tipoUsu.equals("Cajero")){
    try {
            loadFrame(9);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiCuentasBancariasActionPerformed
private void jmiCatalogoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCatalogoProductosActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
    try {
            loadFrame(15);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
}//GEN-LAST:event_jmiCatalogoProductosActionPerformed
private void jmiIniciarComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIniciarComprobantesActionPerformed
        try {
            loadFrame(0);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jmiIniciarComprobantesActionPerformed

    private void jmiGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGestionUsuariosActionPerformed
        try {
            loadFrame(1);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiGestionUsuariosActionPerformed

    private void jmiCambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCambiarClaveActionPerformed
        try {
            loadFrame(2);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiCambiarClaveActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        try {
            loadFrame(3);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jmiAsignarPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAsignarPermisosActionPerformed
        try {
            loadFrame(4);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiAsignarPermisosActionPerformed

    private void jmiRegistrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistrarPedidoActionPerformed
        try {
            loadFrame(11);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiRegistrarPedidoActionPerformed

    private void jmiBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiBackupActionPerformed
        BackupDialog backupDialog = new BackupDialog(this, true);
        backupDialog.setLocationRelativeTo(this);
        backupDialog.setVisible(true);
    }//GEN-LAST:event_jmiBackupActionPerformed

    private void jmiRestaurarBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRestaurarBackupActionPerformed
        RestoreDialog restoreDialog = new RestoreDialog(this, true);
        restoreDialog.setLocationRelativeTo(this);
        restoreDialog.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jmiRestaurarBackupActionPerformed

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Desea Salir del Sistema!!", "Salir", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        AboutDialog about = new AboutDialog(this, true);
        about.setLocationRelativeTo(null);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jmiAlmacenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAlmacenesActionPerformed
    if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
        try {
            loadFrame(20);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiAlmacenesActionPerformed

    private void jmiIgvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIgvActionPerformed
        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        try {
            loadFrame(7);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiIgvActionPerformed

    private void jmiRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistrarVentaActionPerformed
        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Vendedor")){
        try {
            loadFrame(6);
            //FrmRegistrarVenta frm = new FrmRegistrarVenta(null);
            //setInternalFrame(frm);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiRegistrarVentaActionPerformed

    private void jmiClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClientesActionPerformed

if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Vendedor")||tipoUsu.equals("Cajero")){
        try {
            loadFrame(5);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    }//GEN-LAST:event_jmiClientesActionPerformed

    private void jmiPedidosRealizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPedidosRealizadosActionPerformed
        try {
            loadFrame(12);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiPedidosRealizadosActionPerformed

    private void jmiInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInventarioActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
    try {
            loadFrame(21);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiInventarioActionPerformed

    private void jmiEntradasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEntradasRealizadasActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")||tipoUsu.equals("Cajero")){
        try {
            loadFrame(14);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiEntradasRealizadasActionPerformed

    private void jmiRegistrarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistrarEntradaActionPerformed
    if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
        try {
            loadFrame(13);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}

    }//GEN-LAST:event_jmiRegistrarEntradaActionPerformed

    private void escritorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escritorioMouseClicked

    }//GEN-LAST:event_escritorioMouseClicked

    private void jmiVentasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentasRealizadasActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Vendedor")||tipoUsu.equals("Cajero")){
        try {
            loadFrame(23);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiVentasRealizadasActionPerformed

    private void jmiRegularizacionStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegularizacionStockActionPerformed
if(tipoUsu.equals("SuperadministradorSygns")){
    try {
            loadFrame(26);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiRegularizacionStockActionPerformed

    private void jmiInventarioInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInventarioInicialActionPerformed
if(tipoUsu.equals("SuperadministradorSygns")){
        try {
            loadFrame(27);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiInventarioInicialActionPerformed

    private void jmiConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfiguracionActionPerformed
        
        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        ConfiguracionDialog configuracion = new ConfiguracionDialog(this, true);
        configuracion.setLocationRelativeTo(this);
        configuracion.setVisible(true);
        }
    }//GEN-LAST:event_jmiConfiguracionActionPerformed

    private void jmiFacturasProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFacturasProveedoresActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")||tipoUsu.equals("Cajero")){
        try {
            loadFrame(29);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jmiFacturasProveedoresActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        FrmLogin.main(new String[]{});
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiClasificacionConceptoGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClasificacionConceptoGastosActionPerformed
        try {
            loadFrame(35);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiClasificacionConceptoGastosActionPerformed

    private void jmiRegistrarPeriodoAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistrarPeriodoAnalisisActionPerformed
        try {
            loadFrame(36);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiRegistrarPeriodoAnalisisActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            loadFrame(38);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmiRegistrarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistrarGastosActionPerformed
        try {
            loadFrame(40);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiRegistrarGastosActionPerformed

    private void jmiMisCuentasBancariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMisCuentasBancariasActionPerformed
        try {
            loadFrame(41);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiMisCuentasBancariasActionPerformed

    private void jmiTarjetasCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTarjetasCreditoActionPerformed
        try {
            loadFrame(42);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiTarjetasCreditoActionPerformed

    private void jmiCuentasPorCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCuentasPorCobrarActionPerformed


if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        try {
            loadFrame(43);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Admin");
        }
else{
    //JOptionPane.showMessageDialog(null, "No tiene Permisos para realizar esta acción");
    this.iniciarsesionCuentasPorCobrar();
   // System.out.println("No-Admin");
}

        
    }//GEN-LAST:event_jmiCuentasPorCobrarActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
       try {
            File path = new File(System.getProperty("user.dir") + "/src/manual/manual_de_Usuario_Sistema_Farmacia.pdf");
           Desktop.getDesktop().open(path);
       } catch (IOException ex) {
            ex.printStackTrace();
       }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jmiCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCotizacionActionPerformed
        try {
            loadFrame(46);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiCotizacionActionPerformed

    private void jmiRegistrarIngresosVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistrarIngresosVariosActionPerformed
        try {
            loadFrame(47);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiRegistrarIngresosVariosActionPerformed

    private void jmiCajadelDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCajadelDiaActionPerformed
        try {
            loadFrame(48);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiCajadelDiaActionPerformed

    private void jmiReporteRegularizacionStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteRegularizacionStockActionPerformed
        try {
            loadFrame(39);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteRegularizacionStockActionPerformed

    private void jmiReporteCobroCuentasCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteCobroCuentasCobrarActionPerformed
        try {
            loadFrame(44);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteCobroCuentasCobrarActionPerformed

    private void jmiReporteCuentasCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteCuentasCobrarActionPerformed
        try {
            loadFrame(45);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteCuentasCobrarActionPerformed

    private void jmiUtilidadesxVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUtilidadesxVentaActionPerformed
        try {
            loadFrame(32);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiUtilidadesxVentaActionPerformed

    private void jmiVentaDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentaDetalleActionPerformed
        try {
            loadFrame(31);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiVentaDetalleActionPerformed

    private void jmiVentaCabeceraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentaCabeceraActionPerformed
        try {
            loadFrame(30);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiVentaCabeceraActionPerformed

    private void jmiReportePagoCuentasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReportePagoCuentasPagarActionPerformed
        try {
            loadFrame(34);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReportePagoCuentasPagarActionPerformed

    private void jmiReporteCuentasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteCuentasPagarActionPerformed
        try {
            loadFrame(33);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteCuentasPagarActionPerformed

    private void jmiReporteStockDetalladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteStockDetalladoActionPerformed
        try {
            loadFrame(28);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteStockDetalladoActionPerformed

    private void jmiReporteEntradaStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteEntradaStockActionPerformed
        try {
            loadFrame(25);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteEntradaStockActionPerformed

    private void jmiReportePedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReportePedidosActionPerformed
        try {
            loadFrame(24);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReportePedidosActionPerformed

    private void jmiListadoPreciosProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiListadoPreciosProductosActionPerformed
        control.showReportDialog("Listado de precios de productos", "preciosProductos", null);
    }//GEN-LAST:event_jmiListadoPreciosProductosActionPerformed

    private void jmiReporteInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteInventarioActionPerformed
        try {
            //        Map map = new HashMap();
            //        control.showReportDialog("Inventario", "inventario", map);
            loadFrame(21);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiReporteInventarioActionPerformed

    private void jmiProductosxAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProductosxAlmacenActionPerformed
        control.showReportDialog("Reporte de productos por Almacen", "ReporteAlmacen", null);
    }//GEN-LAST:event_jmiProductosxAlmacenActionPerformed

    private void jmiReporteLineaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteLineaProductoActionPerformed
        control.showReportDialog("Lista de Rubros", "ReporteTipoProducto", null);
    }//GEN-LAST:event_jmiReporteLineaProductoActionPerformed

    private void jmiReporteMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteMarcasActionPerformed
        control.showReportDialog("Lista de Laboratorios", "ReporteLasMarcas", null);
    }//GEN-LAST:event_jmiReporteMarcasActionPerformed

    private void jmiReporteBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteBancosActionPerformed
        Map map = new HashMap();
        control.showReportDialog("Reporte de bancos", "ReporteLosBancos", map);
    }//GEN-LAST:event_jmiReporteBancosActionPerformed

    private void jmiCuentasProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCuentasProveedoresActionPerformed
        control.showReportDialog("Cuentas de Proveedores", "ReporteCuentasProveedores", null);
    }//GEN-LAST:event_jmiCuentasProveedoresActionPerformed

    private void jmiListadoProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiListadoProveedoresActionPerformed
        control.showReportDialog("Lista de Los Proveedores", "listadoProveedores", null);
    }//GEN-LAST:event_jmiListadoProveedoresActionPerformed

    private void jmiReporteClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteClientesActionPerformed
        control.showReportDialog("Lista de los Clientes", "ReporteLosClientes2", null);
    }//GEN-LAST:event_jmiReporteClientesActionPerformed

    private void jmiRptCajaDiariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRptCajaDiariaActionPerformed
        FrmReporteCajaDiaria cajaDiaria = new FrmReporteCajaDiaria();
        cajaDiaria.setTipo(FrmReporteCajaDiaria.CAJA_DIARIA);
        setInternalFrame(cajaDiaria);
    }//GEN-LAST:event_jmiRptCajaDiariaActionPerformed

    private void jmiIngresoCajaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIngresoCajaVentasActionPerformed
        FrmReporteCajaDiaria cajaDiaria = new FrmReporteCajaDiaria();
        cajaDiaria.setTipo(FrmReporteCajaDiaria.INGRESOS_VENTAS);
        setInternalFrame(cajaDiaria);
    }//GEN-LAST:event_jmiIngresoCajaVentasActionPerformed

    private void jmiIngresoCajaVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIngresoCajaVariosActionPerformed
        FrmReporteCajaDiaria cajaDiaria = new FrmReporteCajaDiaria();
        cajaDiaria.setTipo(FrmReporteCajaDiaria.INGRESOS);
        setInternalFrame(cajaDiaria);
    }//GEN-LAST:event_jmiIngresoCajaVariosActionPerformed

    private void jmiGastosPagoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGastosPagoProveedorActionPerformed
        FrmReporteCajaDiaria cajaDiaria = new FrmReporteCajaDiaria();
        cajaDiaria.setTipo(FrmReporteCajaDiaria.GASTOS_PAGO_PROVEEDOR);
        setInternalFrame(cajaDiaria);
    }//GEN-LAST:event_jmiGastosPagoProveedorActionPerformed

    private void jmiGastosVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGastosVariosActionPerformed
        FrmReporteCajaDiaria cajaDiaria = new FrmReporteCajaDiaria();
        cajaDiaria.setTipo(FrmReporteCajaDiaria.GASTOS);
        setInternalFrame(cajaDiaria);
    }//GEN-LAST:event_jmiGastosVariosActionPerformed

    private void jmiIngresosEgresosDetalladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIngresosEgresosDetalladoActionPerformed
        FrmReporteCajaDiaria cajaDiaria = new FrmReporteCajaDiaria();
        cajaDiaria.setTipo(FrmReporteCajaDiaria.CAJA_DIARIA_DETALLE);
        setInternalFrame(cajaDiaria);
    }//GEN-LAST:event_jmiIngresosEgresosDetalladoActionPerformed

    private void jmiGuiaRemisionEmitidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGuiaRemisionEmitidasActionPerformed
        try {
            loadFrame(49);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }//GEN-LAST:event_jmiGuiaRemisionEmitidasActionPerformed

    private void jmiOrdenPedidoEmitidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOrdenPedidoEmitidosActionPerformed
        try {
            loadFrame(50);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiOrdenPedidoEmitidosActionPerformed

    private void jMenu15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu15ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            loadFrame(51);        // TODO add your handling code here:
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuStockInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStockInActionPerformed
        ConfiguracionDialog1 configuracion = new ConfiguracionDialog1(this, true);
        configuracion.setLocationRelativeTo(this);
        configuracion.setVisible(true);
    }//GEN-LAST:event_jMenuStockInActionPerformed

    private void jMenu16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu16ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     try {
            loadFrame(52);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")||tipoUsu.equals("Almacenero")){
        try {
            loadFrame(53);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }}        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
     try {
            loadFrame(54);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            loadFrame(55);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            loadFrame(56);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            loadFrame(57);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
     try {
            loadFrame(58);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
     try {
            loadFrame(59);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
         try {
            loadFrame(60);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        try {
            loadFrame(61);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
       try {
            loadFrame(62);
        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuStockIn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem jmiAlmacenes;
    private javax.swing.JMenuItem jmiAsignarPermisos;
    private javax.swing.JMenuItem jmiBackup;
    private javax.swing.JMenuItem jmiBancos;
    private javax.swing.JMenuItem jmiCajadelDia;
    private javax.swing.JMenuItem jmiCambiarClave;
    private javax.swing.JMenuItem jmiCatalogoProductos;
    private javax.swing.JMenuItem jmiClasificacionConceptoGastos;
    private javax.swing.JMenuItem jmiClientes;
    private javax.swing.JMenuItem jmiConfiguracion;
    private javax.swing.JMenuItem jmiCotizacion;
    private javax.swing.JMenuItem jmiCuentasBancarias;
    private javax.swing.JMenuItem jmiCuentasPorCobrar;
    private javax.swing.JMenuItem jmiCuentasProveedores;
    private javax.swing.JMenuItem jmiEntradasRealizadas;
    private javax.swing.JMenuItem jmiFacturasProveedores;
    private javax.swing.JMenuItem jmiGastosPagoProveedor;
    private javax.swing.JMenuItem jmiGastosVarios;
    private javax.swing.JMenuItem jmiGestionUsuarios;
    private javax.swing.JMenuItem jmiGuiaRemisionEmitidas;
    private javax.swing.JMenuItem jmiIgv;
    private javax.swing.JMenuItem jmiIngresoCajaVarios;
    private javax.swing.JMenuItem jmiIngresoCajaVentas;
    private javax.swing.JMenuItem jmiIngresosEgresosDetallado;
    private javax.swing.JMenuItem jmiIniciarComprobantes;
    private javax.swing.JMenuItem jmiInventario;
    private javax.swing.JMenuItem jmiInventarioInicial;
    private javax.swing.JMenuItem jmiListadoPreciosProductos;
    private javax.swing.JMenuItem jmiListadoProveedores;
    private javax.swing.JMenuItem jmiMarcaProductos;
    private javax.swing.JMenuItem jmiMisCuentasBancarias;
    private javax.swing.JMenuItem jmiOrdenPedidoEmitidos;
    private javax.swing.JMenuItem jmiPedidosRealizados;
    private javax.swing.JMenuItem jmiProductosxAlmacen;
    private javax.swing.JMenuItem jmiProveedores;
    private javax.swing.JMenuItem jmiRegistrarEntrada;
    private javax.swing.JMenuItem jmiRegistrarGastos;
    private javax.swing.JMenuItem jmiRegistrarIngresosVarios;
    private javax.swing.JMenuItem jmiRegistrarPedido;
    private javax.swing.JMenuItem jmiRegistrarPeriodoAnalisis;
    private javax.swing.JMenuItem jmiRegistrarVenta;
    private javax.swing.JMenuItem jmiRegularizacionStock;
    private javax.swing.JMenuItem jmiReporteBancos;
    private javax.swing.JMenuItem jmiReporteClientes;
    private javax.swing.JMenuItem jmiReporteCobroCuentasCobrar;
    private javax.swing.JMenuItem jmiReporteCuentasCobrar;
    private javax.swing.JMenuItem jmiReporteCuentasPagar;
    private javax.swing.JMenuItem jmiReporteEntradaStock;
    private javax.swing.JMenuItem jmiReporteInventario;
    private javax.swing.JMenuItem jmiReporteLineaProducto;
    private javax.swing.JMenuItem jmiReporteMarcas;
    private javax.swing.JMenuItem jmiReportePagoCuentasPagar;
    private javax.swing.JMenuItem jmiReportePedidos;
    private javax.swing.JMenuItem jmiReporteRegularizacionStock;
    private javax.swing.JMenuItem jmiReporteStockDetallado;
    private javax.swing.JMenuItem jmiRestaurarBackup;
    private javax.swing.JMenuItem jmiRptCajaDiaria;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JMenuItem jmiTarjetasCredito;
    private javax.swing.JMenuItem jmiTipoProductos;
    private javax.swing.JMenuItem jmiUnidades;
    private javax.swing.JMenuItem jmiUtilidadesxVenta;
    private javax.swing.JMenuItem jmiVentaCabecera;
    private javax.swing.JMenuItem jmiVentaDetalle;
    private javax.swing.JMenuItem jmiVentasRealizadas;
    // End of variables declaration//GEN-END:variables

    private void setPosition(JInternalFrame internalFrame) {
        internalFrame.setLocation((R.width - internalFrame.getWidth()) / 2, 20);
    }

    public void setInternalFrame(JInternalFrame internalFrame) {
        escritorio.add(internalFrame, JLayeredPane.DEFAULT_LAYER);
        setPosition(internalFrame);
        internalFrame.setVisible(true);
    }

    private void iniciarCaja() {
        String res = control.getValueQuery(String.format("SELECT COUNT(`fecha`) FROM `cajainicial` WHERE `fecha`=CURDATE() AND `idSede`=%s;", InfoGeneral.getIdSede()));
        if (res.equals("0")) {
            FrmCajaInicial cajaInicial = new FrmCajaInicial(this, true);
            cajaInicial.setLocationRelativeTo(this);
            cajaInicial.setVisible(true);
        }
    }

    private JInternalFrame buscarFrame(Class obj) {
        JInternalFrame frame = null;
        for (JInternalFrame allFrame : escritorio.getAllFrames()) {
            if (allFrame.getClass() == obj) {
                frame = allFrame;
            }
        }
        return frame;
    }

    private void loadFrame(int index) throws ParseException {
        JInternalFrame frame;
        switch (index) {
            case 0:
                frame = buscarFrame(FrmIniciarComprobantes.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmIniciarComprobantes frm = new FrmIniciarComprobantes();
                    setInternalFrame(frm);
                }
                break;
            case 1:
                frame = buscarFrame(FrmGestionUsuarios.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionUsuarios frm = new FrmGestionUsuarios();
                    setInternalFrame(frm);
                }
                break;
            case 2:
                frame = buscarFrame(FrmCambiarPassword.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmCambiarPassword frm = new FrmCambiarPassword();
                    setInternalFrame(frm);
                }
                break;
            case 3:
                frame = buscarFrame(FrmGestionPermiso.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionPermiso frm = new FrmGestionPermiso();
                    setInternalFrame(frm);
                }
                break;
            case 4:
                frame = buscarFrame(FrmAsignarPermisosUsuario.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmAsignarPermisosUsuario frm = new FrmAsignarPermisosUsuario();
                    setInternalFrame(frm);
                }
                break;
            case 5:
                frame = buscarFrame(FrmGestionClientes.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionClientes frm = new FrmGestionClientes();
                    setInternalFrame(frm);
                }
                break;
            case 6:
                frame = buscarFrame(FrmRegistrarVenta.class);
                if (frame != null) {
                    FrmRegistrarVenta frm = new FrmRegistrarVenta(null,true);
                    setInternalFrame(frm);
                 
            try {
                frm.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                } else {
                    FrmRegistrarVenta frm = new FrmRegistrarVenta(null,true);
                    setInternalFrame(frm);
                }
                break;
            case 7:
                frame = buscarFrame(FrmIgv.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmIgv frm = new FrmIgv();
                    setInternalFrame(frm);
                }
                break;
            case 8:
                frame = buscarFrame(FrmGestionBancos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionBancos frm = new FrmGestionBancos();
                    setInternalFrame(frm);
                }
                break;
            case 9:
                frame = buscarFrame(CuentaBancaria.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    CuentaBancaria frm = new CuentaBancaria();
                    setInternalFrame(frm);
                }
                break;

            case 11:
                frame = buscarFrame(FrmRegistrarOrdenDeCompra.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarOrdenDeCompra frm = new FrmRegistrarOrdenDeCompra();
                    setInternalFrame(frm);
                }
                break;
            case 12:
                frame = buscarFrame(FrmListadoOrdenesdeCompra.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoOrdenesdeCompra frm = new FrmListadoOrdenesdeCompra();
                    setInternalFrame(frm);
                }
                break;
            case 13:
                frame = buscarFrame(FrmRegistrarEntradasDeStok.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarEntradasDeStok frm = new FrmRegistrarEntradasDeStok(null);
                    setInternalFrame(frm);
                }
                break;
            case 14:
                frame = buscarFrame(FrmListadoEntradaDeStock.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoEntradaDeStock frm = new FrmListadoEntradaDeStock();
                    setInternalFrame(frm);
                }
                break;
            case 15:
                frame = buscarFrame(FrmGestionCatalogoProductos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionCatalogoProductos frm = new FrmGestionCatalogoProductos();
                    setInternalFrame(frm);
                }
                break;
            case 16:
                frame = buscarFrame(FrmGestionCatalogoProductos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionCatalogoProductos frm = new FrmGestionCatalogoProductos();
                    setInternalFrame(frm);
                }
                break;
            case 17:
                frame = buscarFrame(FrmGestionTipoProducto.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionTipoProducto frm = new FrmGestionTipoProducto();
                    setInternalFrame(frm);
                }
                break;
            case 18:
                frame = buscarFrame(FrmGestionMarcas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionMarcas frm = new FrmGestionMarcas();
                    setInternalFrame(frm);
                }
                break;
            case 19:
                frame = buscarFrame(FrmGestionUnidadMedida.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionUnidadMedida frm = new FrmGestionUnidadMedida();
                    setInternalFrame(frm);
                }
                break;
            case 20:
                frame = buscarFrame(FrmGestionAlmacenes.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionAlmacenes frm = new FrmGestionAlmacenes();
                    setInternalFrame(frm);
                }
                break;
            case 21:
                frame = buscarFrame(FrmReporteInventario.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteInventario frm = new FrmReporteInventario();
                    setInternalFrame(frm);
                }
                break;
            case 22:
                frame = buscarFrame(FrmGestionProveedores.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionProveedores frm = new FrmGestionProveedores();
                    setInternalFrame(frm);
                }
                break;
            case 23:
                frame = buscarFrame(FrmListadoVentas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoVentas frm = new FrmListadoVentas();
                    setInternalFrame(frm);
                }
                break;

            case 24:
                frame = buscarFrame(FrmReportePedidos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReportePedidos frm = new FrmReportePedidos();
                    setInternalFrame(frm);
                }
                break;
            case 25:
                frame = buscarFrame(FrmReporteEntradaStockCabecera.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteEntradaStockCabecera frm = new FrmReporteEntradaStockCabecera();
                    setInternalFrame(frm);
                }
                break;
            case 26:
                frame = buscarFrame(FrmGestionRegularizacionesDeStock.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionRegularizacionesDeStock frm = new FrmGestionRegularizacionesDeStock();
                    setInternalFrame(frm);
                }
                break;
            case 27:
                frame = buscarFrame(FrmRegistrarInventarioInicial.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarInventarioInicial frm = new FrmRegistrarInventarioInicial();
                    setInternalFrame(frm);
                }
                break;
            case 28:
                frame = buscarFrame(FrmReporteEntradaStockDetalle.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteEntradaStockDetalle frm = new FrmReporteEntradaStockDetalle();
                    setInternalFrame(frm);
                }
                break;
            case 29:
                frame = buscarFrame(FrmListadoFacturas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoFacturas frm = new FrmListadoFacturas();
                    setInternalFrame(frm);
                }
                break;
            case 30:
                frame = buscarFrame(FrmReporteVentaCabecera.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteVentaCabecera frm = new FrmReporteVentaCabecera();
                    setInternalFrame(frm);
                }
                break;
            case 31:
                frame = buscarFrame(FrmReporteVentaDetalleProducto.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteVentaDetalleProducto frm = new FrmReporteVentaDetalleProducto();
                    setInternalFrame(frm);
                }
                break;
            case 32:
                frame = buscarFrame(FrmReporteUtilidadesVenta.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteUtilidadesVenta frm = new FrmReporteUtilidadesVenta();
                    setInternalFrame(frm);
                }
                break;
            case 33:
                frame = buscarFrame(FrmReporteFacturasProveedor.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteFacturasProveedor frm = new FrmReporteFacturasProveedor();
                    setInternalFrame(frm);
                }
                break;
            case 34:
                frame = buscarFrame(FrmReportePagoProveedores.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReportePagoProveedores frm = new FrmReportePagoProveedores();
                    setInternalFrame(frm);
                }
                break;
            case 35:
                frame = buscarFrame(FrmRegistrarClasificacionConceptoGastos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarClasificacionConceptoGastos frm = new FrmRegistrarClasificacionConceptoGastos();
                    setInternalFrame(frm);
                }
                break;
            case 36:
                frame = buscarFrame(FrmGestionPeriodoGastos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionPeriodoGastos frm = new FrmGestionPeriodoGastos();
                    setInternalFrame(frm);
                }
                break;

            case 37:
                frame = buscarFrame(FrmRegistrarFechasCaducidadProductos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarFechasCaducidadProductos frm = new FrmRegistrarFechasCaducidadProductos();
                    setInternalFrame(frm);
                }
                break;
            case 38:
                frame = buscarFrame(FrmProductosBajosDeStock.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmProductosBajosDeStock frm = new FrmProductosBajosDeStock();
                    setInternalFrame(frm);
                }
                break;
            case 39:
                frame = buscarFrame(FrmReporteRegularizacion.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteRegularizacion frm = new FrmReporteRegularizacion();
                    setInternalFrame(frm);
                }
                break;
            case 40:
                frame = buscarFrame(FrmRegistrarGastos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarGastos frm = new FrmRegistrarGastos();
                    setInternalFrame(frm);
                }
                break;
            case 41:
                frame = buscarFrame(FrmMisCuentas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmMisCuentas frm = new FrmMisCuentas();
                    setInternalFrame(frm);
                }
                break;
            case 42:
                frame = buscarFrame(FrmGestionTarjetaCredito.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionTarjetaCredito frm = new FrmGestionTarjetaCredito();
                    setInternalFrame(frm);
                }
                break;
            case 43:
                frame = buscarFrame(FrmListadoCuentasPorCobrar.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoCuentasPorCobrar frm = new FrmListadoCuentasPorCobrar();
                    setInternalFrame(frm);
                }
                break;
            case 44:
                frame = buscarFrame(FrmReporteCobroClientes.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteCobroClientes frm = new FrmReporteCobroClientes();
                    setInternalFrame(frm);
                }
                break;
            case 45:
                frame = buscarFrame(FrmReporteCuentasPorCobrar.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteCuentasPorCobrar frm = new FrmReporteCuentasPorCobrar();
                    setInternalFrame(frm);
                }
                break;
            case 46:
                frame = buscarFrame(FrmRegistrarCotizacion.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmRegistrarCotizacion frm = new FrmRegistrarCotizacion(null);
                    setInternalFrame(frm);
                }
                break;
            case 47:
                frame = buscarFrame(RegistroIngresoCaja.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    RegistroIngresoCaja report = new RegistroIngresoCaja();
                    setInternalFrame(report);
                }
                break;
            case 48:
                frame = buscarFrame(IfrmCajadelDia.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    IfrmCajadelDia report = new IfrmCajadelDia();
                    setInternalFrame(report);
                }
                break;
                case 49:
                frame = buscarFrame(FrmGuiasRemisionEmitidas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGuiasRemisionEmitidas guia = new FrmGuiasRemisionEmitidas();
                    setInternalFrame(guia);
                }
                break;
                    case 50:
                frame = buscarFrame(FrmOrdenPedidoEmitidos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmOrdenPedidoEmitidos ordenPedido = new FrmOrdenPedidoEmitidos();
                    setInternalFrame(ordenPedido);
                }
                break;
                        case 51:
                frame = buscarFrame(FrmListadoCotizaciones.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoCotizaciones listaCot = new FrmListadoCotizaciones();
                    setInternalFrame(listaCot);
                }
                break;
                
                case 52:
                frame = buscarFrame(FrmReporteEntradaStockDetalleProveedor.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteEntradaStockDetalleProveedor frm = new FrmReporteEntradaStockDetalleProveedor();
                    setInternalFrame(frm);
                }
                break;
                
                case 53:
                frame = buscarFrame(FrmGestionPresentacion.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionPresentacion frm = new FrmGestionPresentacion();
                    setInternalFrame(frm);
                }
                break;
                
                case 54:
                    frame = buscarFrame(FrmProductosVencidos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmProductosVencidos frm = new FrmProductosVencidos();
                    setInternalFrame(frm);
                }
                break;
                
                case 55:
                    frame = buscarFrame(FrmGestionProductos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionProductos frm = new FrmGestionProductos(null,true);
                    setInternalFrame(frm);
                }
                break;
                
                case 56:
                    frame = buscarFrame(FrmListadoSalidasLibres.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmListadoSalidasLibres frm = new FrmListadoSalidasLibres();
                    setInternalFrame(frm);
                }
                break;
                
                case 57:
                frame = buscarFrame(FrmReporteSalidaProductos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteSalidaProductos frm = new FrmReporteSalidaProductos();
                    setInternalFrame(frm);
                }
                
                break;
                case 58:
                frame = buscarFrame(FrmTopProductosVendidos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmTopProductosVendidos frm = new FrmTopProductosVendidos();
                    setInternalFrame(frm);
                }
                break;
                
                case 59:
                frame = buscarFrame(FrmEstadisticoVentas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmEstadisticoVentas frm = new FrmEstadisticoVentas();
                    setInternalFrame(frm);
                }
                break;
                case 60:
                frame = buscarFrame(FrmReporteVentaVendedores.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmReporteVentaVendedores frm = new FrmReporteVentaVendedores();
                    setInternalFrame(frm);
                }
                break;
                
                 case 61:
                frame = buscarFrame(FrmGestionCombos.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmGestionCombos frm = new FrmGestionCombos();
                    setInternalFrame(frm);
                }
                break;
                
                case 62:
                frame = buscarFrame(FrmResumenBoletas.class);
                if (frame != null) {
                    frame.toFront();
                } else {
                    FrmResumenBoletas frm = new FrmResumenBoletas();
                    setInternalFrame(frm);
                }
                break;

        }
    }

    public String capturarUsuario() {
        String usu="";
        usu=usu+Accesos.getInstance().getLogin();
        this.setTitle("Sistema de Gestión de Ventas Botica Virgen del Carmen V. 1.0 " + " Usuario "+tipoUsu +": "+ Accesos.getInstance().getLogin());
        return usu;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b); //To change body of generated methods, choose Tools | Templates.
        //verificandoPermisos();
    }
    
      

    private void verificandoPermisos() {

        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {

                jym.ferreteria.clases.Permisos.getInstance().setMapa(control.obtenerPermisos(Accesos.getInstance().getIdUsuario()));
                Map<Integer, Boolean> map = jym.ferreteria.clases.Permisos.getInstance().getMapa();
                jmiIniciarComprobantes.setEnabled(map.get(1));
                jmiGestionUsuarios.setEnabled(map.get(2));
                jmiCambiarClave.setEnabled(map.get(3));
                jmiBackup.setEnabled(map.get(4));
                jmiRestaurarBackup.setEnabled(map.get(5));
                jMenuItem37.setEnabled(map.get(6));
                jmiAsignarPermisos.setEnabled(map.get(7));
                jmiConfiguracion.setEnabled(map.get(8));
                jmiMisCuentasBancarias.setEnabled(map.get(9)); 
                jmiClasificacionConceptoGastos.setEnabled(map.get(11));
                jmiRegistrarPeriodoAnalisis.setEnabled(map.get(12));
                jmiRegistrarGastos.setEnabled(map.get(13));
                jmiRegistrarIngresosVarios.setEnabled(map.get(14));
                jmiCajadelDia.setEnabled(map.get(15));
                jmiClientes.setEnabled(map.get(16));
                jmiCotizacion.setEnabled(map.get(17));
                jmiRegistrarVenta.setEnabled(map.get(18));
                jmiVentasRealizadas.setEnabled(map.get(19));
                jmiGuiaRemisionEmitidas.setEnabled(map.get(20));
                jmiOrdenPedidoEmitidos.setEnabled(map.get(21));
                jmiCuentasPorCobrar.setEnabled(map.get(22));
                jmiIgv.setEnabled(map.get(23));
                jmiTarjetasCredito.setEnabled(map.get(24));
                jmiProveedores.setEnabled(map.get(25));
                jmiBancos.setEnabled(map.get(26));
                jmiCuentasBancarias.setEnabled(map.get(27));
                jmiRegistrarPedido.setEnabled(map.get(28));
                jmiPedidosRealizados.setEnabled(map.get(29));
                jmiRegistrarEntrada.setEnabled(map.get(30));
                jmiEntradasRealizadas.setEnabled(map.get(31));
                jmiFacturasProveedores.setEnabled(map.get(32));
                jmiCatalogoProductos.setEnabled(map.get(33));
                jmiTipoProductos.setEnabled(map.get(34));
                jmiMarcaProductos.setEnabled(map.get(35));
                jmiUnidades.setEnabled(map.get(36));
                jmiAlmacenes.setEnabled(map.get(37));
                jmiInventario.setEnabled(map.get(38));
                jmiRegularizacionStock.setEnabled(map.get(39));
                jmiInventarioInicial.setEnabled(map.get(40));
                jMenuItem4.setEnabled(map.get(41));
                jmiReporteClientes.setEnabled(map.get(42));
                jmiListadoProveedores.setEnabled(map.get(43));
                jmiCuentasProveedores.setEnabled(map.get(44));
                jmiReporteBancos.setEnabled(map.get(45));
                jmiReporteMarcas.setEnabled(map.get(46));
                jmiReporteLineaProducto.setEnabled(map.get(47));
                jmiProductosxAlmacen.setEnabled(map.get(48));
                jmiReporteInventario.setEnabled(map.get(49));
                jmiListadoPreciosProductos.setEnabled(map.get(50));
                jmiReportePedidos.setEnabled(map.get(51));
                jmiReporteEntradaStock.setEnabled(map.get(52));
                jmiReporteStockDetallado.setEnabled(map.get(53));
                jmiReporteCuentasPagar.setEnabled(map.get(54));
                jmiReportePagoCuentasPagar.setEnabled(map.get(55));
                jmiVentaCabecera.setEnabled(map.get(56));
                jmiVentaDetalle.setEnabled(map.get(57));
                jmiUtilidadesxVenta.setEnabled(map.get(58));
                jmiReporteCuentasCobrar.setEnabled(map.get(59));
                jmiReporteCobroCuentasCobrar.setEnabled(map.get(60));
                jmiReporteRegularizacionStock.setEnabled(map.get(61));
                jmiRptCajaDiaria.setEnabled(map.get(62));
                jmiIngresoCajaVentas.setEnabled(map.get(63));
                jmiIngresoCajaVarios.setEnabled(map.get(64));
                jmiGastosPagoProveedor.setEnabled(map.get(65));
                jmiGastosVarios.setEnabled(map.get(66));
                jmiIngresosEgresosDetallado.setEnabled(map.get(67));
                
                
                if (Accesos.getInstance().getTipoUsuario().equals("Administrador")) {
                    jmiAsignarPermisos.setEnabled(true);
                }
                return true;
            }
        };
        worker.execute();
    }
}
