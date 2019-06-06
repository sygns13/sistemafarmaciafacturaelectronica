/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jym.ferreteria.clases.Configuracion;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.clases.InfoGeneral;
import jym.ferreteria.clases.Numero_a_Letra;
import jym.ferreteria.gui.FrmMain;
import jym.ferreteria.gui.dialogs.CobrarVentaDialog;
import jym.ferreteria.gui.dialogs.GuiaDeRemision;
import jym.ferreteria.gui.dialogs.ordendePedido;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.gui.dialogs.GenComprobante;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.org.ws.BolElectronica;
import genfactura.GenNotaCredito;

import java.util.concurrent.*;
import static jym.ferreteria.clases.Controlador.Base;


import jym.ferreteria.clases.Accesos;


import jym.ferreteria.gui.dialogs.IniciarSesionAdmin;


import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.org.ws.NotaCreditoElectronica;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FrmListadoVentas extends javax.swing.JInternalFrame  {
    
    private static genfactura.GenComprobante genComprobanteElectronico = new genfactura.GenComprobante();
    
    //private static genfactura.GenComprobante genComprobanteElectronico = new genfactura.GenComprobante();
    private Controlador control = new Controlador();
    private CustomTableModel model = new CustomTableModel();
    
    private GenNotaCredito gennota = new GenNotaCredito();
    
    private IniciarSesionAdmin iniciarSesionA = null;
    
    ResultSet rset;
    
    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    
    private String tipoUsu="";
    public boolean isAnulado=false;
    
    private String idusu="";
    


    /**
     * Creates new form ListadoVentas
     */
    public FrmListadoVentas() {
        initComponents();
        userspeshal();
        btnCobrar.setEnabled(false);
        btnOrdenPedido.setEnabled(false);
        jButton2.setEnabled(false);
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        model.setColumnIdentifiers(new String[]{"N° de Venta", "Fecha","Hora", "Comprobante", "Cliente", "SubTotal", "Guía de Remisión", "Total", "Estado","IDcliente","ruc","numRows","User Vendedor","E. a SUNAT", "Estado de Envío a SUNAT","tipo Cliente"});
        tablaVentas.setModel(model);
        control.hideTableColumn(tablaVentas,5,6,9,10,11,15);
        control.tableWidthColumn(tablaVentas, 230, 4);
        control.tableWidthColumn(tablaVentas, 200,3);
        
        control.tableWidthColumn(tablaVentas, 80,13);
        control.tableWidthColumn(tablaVentas, 350,14);
        
        btnOrdenPedido.setVisible(false);  
        btnGenComp.setEnabled(false);
        btnGuiaRemision.setVisible(false);
        

        tablaVentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnImprimir.setEnabled(true);
                btnVerVenta.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAnular.setEnabled(true);
                jButton2.setEnabled(true);
                btnGenComp.setEnabled(true);
              
            }
        });
        
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        cargarVentas();
        //timer.scheduleAtFixedRate(cargarDatos, 1, 3, TimeUnit.SECONDS);
        //timer.sc
        //ActualizarNuevo();
        asignarPermisos();
        



        
        
         
    }
    public void userspeshal(){
    idusu=Accesos.getInstance().getIdUsuario();
    cbuTipo.removeAllItems();
    
    cbuTipo.addItem("Todas");
    cbuTipo.addItem("Facturas");
    cbuTipo.addItem("Boletas");
    cbuTipo.addItem("No Cobradas");
    
    

    }
    
    public DefaultTableCellRenderer  formato_tablas = new DefaultTableCellRenderer(){
    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    		super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
    		if (col==13){
    			//double valor = Double.parseDouble((String) value);
                        
                        String valor=(String)value;
                        
                        if(valor.equals("N")){
                            setBackground(Color.decode("#FFBB33"));
                        } else if(valor.equals("L")){
                            setBackground(Color.decode("#0CEC26"));
                        } else if(valor.equals("E")){
                            setBackground(Color.decode("#F7FA28"));
                        } else if(valor.equals("A")){
                            setBackground(Color.decode("#EC0C13"));
                        } else if(valor.equals("AN")){
                            setBackground(Color.decode("#F78181"));
                        }
                        
    			/*if(valor<=3){
    				setBackground(Color.black);
    				setForeground(Color.white);
    			}else{
    				if (valor<=5){
    					setBackground(Color.red);
    					setForeground(Color.black);
    				}else{
    					if (valor<=10){
    						setBackground(Color.yellow);
    					}
    					else{
    						if (valor<=15){
    							setBackground(Color.white);
        					}
    						else{
    							if (valor<=15){
    								setBackground(Color.green);
            					}
    						}
    					}
    				}
    			}*/
    		}
                else{
                    setBackground(Color.white);
    		    setForeground(Color.black);
                }
    		return this;
    	}
    };
    

    
      public void asignarPermisos(){
        tipoUsu=Accesos.getInstance().getTipoUsuario();
    
    }
    final Runnable cargarDatos = new Runnable() {
            public void run(){
                ActualizarNuevo();
            }
};
    
    public void iniciarsesionAnular(){
        if (this.iniciarSesionA == null) {
            this.iniciarSesionA = new IniciarSesionAdmin(null, true);
        }
        this.iniciarSesionA.setLocationRelativeTo(this);
        this.iniciarSesionA.setVisible(true);
        this.iniciarSesionA.limpiar();
        
        boolean result = this.iniciarSesionA.getResult();
        if (result != false) {
              anular();
        }
    }
    
    public void iniciarsesionEliminar(){
        if (this.iniciarSesionA == null) {
            this.iniciarSesionA = new IniciarSesionAdmin(null, true);
        }
        this.iniciarSesionA.setLocationRelativeTo(this);
        this.iniciarSesionA.setVisible(true);
        this.iniciarSesionA.limpiar();
        
        boolean result = this.iniciarSesionA.getResult();
        if (result != false) {
              eliminar();
        }
    }
    
    public void iniciarsesionModificar(){
        if (this.iniciarSesionA == null) {
            this.iniciarSesionA = new IniciarSesionAdmin(null, true);
        }
        this.iniciarSesionA.setLocationRelativeTo(this);
        this.iniciarSesionA.setVisible(true);
        this.iniciarSesionA.limpiar();
        
        boolean result = this.iniciarSesionA.getResult();
        if (result != false) {
              try {
                modificar();
            } catch (ParseException ex) {
                Logger.getLogger(FrmListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public  void ExportarExcel(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet=book.createSheet("Listado de Ventas");
        
        
        CellStyle style3 = book.createCellStyle();
        
        style3.setBorderBottom(BorderStyle.MEDIUM);
        style3.setBorderLeft(BorderStyle.MEDIUM);
        style3.setBorderRight(BorderStyle.MEDIUM);
        style3.setBorderTop(BorderStyle.MEDIUM);
        
      Row row0=sheet.createRow(1);
      Cell celda0=row0.createCell(2);
      celda0.setCellValue("Reporte de Listado de Ventas");
      //celda0.setCellStyle(style3);
      

   String text = txtBuscar.getText();
        String fecha1="";
        String fecha2="";
        
        String fec1="";
        String fec2="";
        
        if(txtFechaDesde.getDate() != null){
            fecha1=control.Formato_Amd(txtFechaDesde.getDate());
            fec1=control.Formato_Amd2(txtFechaDesde.getDate());
        }
        
        if(txtFechaHasta.getDate() != null){
            fecha2=control.Formato_Amd(txtFechaHasta.getDate());
            fec2=control.Formato_Amd2(txtFechaHasta.getDate());
        }
        
        
        int tipo=cbuTipo.getSelectedIndex();
        
        String andfecha="";
        String mensaje="";
        String andtipo="";
        if(fecha1.length()>0 && fecha2.length()>0){
            andfecha=" and (v.fecha between '"+fecha1+"' and '"+fecha2+"') ";
            mensaje=" Desde: "+fec1+" Hasta: "+fec2;
        }
        
        String msj="";
        if(tipo==0){
            andtipo=" ";
            msj="Listado General de Ventas ";
        }else if(tipo==1){
            andtipo=" and t.idTipoComprobante=1 ";
            msj="Listado de Ventas con Factura ";
        }else if(tipo==2){
            andtipo=" and t.idTipoComprobante=2 ";
            msj="Listado de Ventas con Boleta ";
        }else if(tipo==3){
            andtipo=" and t.idTipoComprobante=8 ";
            msj="Listado de Ventas Libres ";
        }else if(tipo==4){
            andtipo=" AND v.estado='Pendiente' ";
            msj="Listado de Ventas no Cobradas ";
        }
        mensaje=msj+mensaje;
        
        String andtipoUser="";
        
        if(idusu.equals("0")){
            andtipoUser=" and t.idTipoComprobante<>8 ";
            
        }

        control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, v.`hora` as hora, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- if(v.enobra<0,0,enobra),2) AS SubTotal,if((select count(g.idguiaderemision) from guiaderemision g where g.idventa=v.`idVenta`)>0,'Emitida','No Emitida') as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc, \n" +
 " (select count(dv.idDetalleVenta)from detalleventa dv where dv.idVenta=v.idVenta group by dv.idVenta), u.nomusr, cab.docu_proce_status, cab.cdr_nota, c.tipo    FROM cliente c, (venta v  inner join detalleventa dt on dt.idventa=v.idventa left join cabecera cab on v.idventa=cab.idventa left join usuario u on u.idusuario=v.idusuario LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente`  "+andfecha+" "+andtipo+" "+andtipoUser+" AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%')  group by v.idventa ORDER BY v.`fecha` desc,v.`idVenta` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
        System.out.println(control.Sql);
        //control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- v.`enObra`,2) AS SubTotal,v.`enObra` as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc  FROM cliente c, (venta v LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente` AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%') ORDER BY v.`fecha` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
        //control.LlenarJTabla(model, control.Sql, 16);

        
       Row row2=sheet.createRow(3);
      Cell celda2=row2.createCell(1);
      celda2.setCellValue(mensaje);
        

       ResultSet result = control.DevolverRegistro(control.Sql);
       
       
       
     /*  "N° de Venta", "Fecha","Hora", "Comprobante", "Cliente", "SubTotal", "Guía de Remisión", "Total", "Estado","IDcliente","ruc",
       "numRows","User Vendedor","E. a SUNAT", "Estado de Envío a SUNAT","tipo Cliente"
       */
     
       int cont=1;
       String numvta="";
       String fecha="";
       String hora="";
       String comprobante="";
       String cliente="";
       String total="";
       String estado="";
       String usuario="";
       String enviado="";
       String estadoenv="";;

       

       
       Row rowt = sheet.createRow(4);
       
       Cell celdatos2col0= rowt.createCell(0);
       Cell celdatos2col1= rowt.createCell(1);
       Cell celdatos2col2= rowt.createCell(2);
       Cell celdatos2col3= rowt.createCell(3);
       Cell celdatos2col4= rowt.createCell(4);
       Cell celdatos2col5= rowt.createCell(5);
       Cell celdatos2col6= rowt.createCell(6);
       Cell celdatos2col7= rowt.createCell(7);
       Cell celdatos2col8= rowt.createCell(8);
       Cell celdatos2col9= rowt.createCell(9);
       Cell celdatos2col10= rowt.createCell(10);


       
       celdatos2col0.setCellStyle(style3);
       celdatos2col1.setCellStyle(style3);
       celdatos2col2.setCellStyle(style3);
       celdatos2col3.setCellStyle(style3);
       celdatos2col4.setCellStyle(style3);
       celdatos2col5.setCellStyle(style3);
       celdatos2col6.setCellStyle(style3);
       celdatos2col7.setCellStyle(style3);
       celdatos2col8.setCellStyle(style3);
       celdatos2col9.setCellStyle(style3);
       celdatos2col10.setCellStyle(style3);

       
       celdatos2col0.setCellValue("N°");
       celdatos2col1.setCellValue("Num. Venta");
       celdatos2col2.setCellValue("Fecha");
       celdatos2col3.setCellValue("Hora");
       celdatos2col4.setCellValue("Comprobante");
       celdatos2col5.setCellValue("Cliente");
       celdatos2col6.setCellValue("Total");
       celdatos2col7.setCellValue("Estado");
       celdatos2col8.setCellValue("Usuario Vendedor");
       celdatos2col9.setCellValue("Estado de envío a SUNAT");
       celdatos2col10.setCellValue("Mensaje de Estado de Envío a SUNAT");

       
        try {
            while (result.next()) {
               
                numvta=result.getString(1);
                fecha=result.getString(2);
                hora=result.getString(3);
                comprobante=result.getString(4);
                cliente=result.getString(5);
                total=result.getString(8);
                estado=result.getString(9);
                usuario=result.getString(13);
                enviado=result.getString(14);
                estadoenv=result.getString(15);
     
/*  "N° de Venta", "Fecha","Hora", "Comprobante", "Cliente", "SubTotal", "Guía de Remisión", "Total", "Estado","IDcliente","ruc",
       "numRows","User Vendedor","E. a SUNAT", "Estado de Envío a SUNAT","tipo Cliente"
       */
                     
               Row rown = sheet.createRow(cont+4);
               
               Cell celdatosncol0= rown.createCell(0);
               Cell celdatosncol1= rown.createCell(1);
               Cell celdatosncol2= rown.createCell(2);
               Cell celdatosncol3= rown.createCell(3);
               Cell celdatosncol4= rown.createCell(4);
               Cell celdatosncol5= rown.createCell(5);
               Cell celdatosncol6= rown.createCell(6);
               Cell celdatosncol7= rown.createCell(7);
               Cell celdatosncol8= rown.createCell(8);
               Cell celdatosncol9= rown.createCell(9);
               Cell celdatosncol10= rown.createCell(10);

               
               
               celdatosncol0.setCellStyle(style3);
               celdatosncol1.setCellStyle(style3);
               celdatosncol2.setCellStyle(style3);
               celdatosncol3.setCellStyle(style3);
               celdatosncol4.setCellStyle(style3);
               celdatosncol5.setCellStyle(style3);
               celdatosncol6.setCellStyle(style3);
               celdatosncol7.setCellStyle(style3);
               celdatosncol8.setCellStyle(style3);
               celdatosncol9.setCellStyle(style3);
               celdatosncol10.setCellStyle(style3);

       
               celdatosncol0.setCellValue(cont);
               celdatosncol1.setCellValue(numvta);
               celdatosncol2.setCellValue(fecha);
               celdatosncol3.setCellValue(hora);
               celdatosncol4.setCellValue(comprobante);
               celdatosncol5.setCellValue(cliente);
               celdatosncol6.setCellValue(total);
               celdatosncol7.setCellValue(estado);
               celdatosncol8.setCellValue(usuario);
               celdatosncol9.setCellValue(enviado);
               celdatosncol10.setCellValue(estadoenv);


               
               
               
               
               
               cont++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteEntradaStockDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
        sheet.setColumnWidth(0, 2500);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 12500);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);
        sheet.setColumnWidth(8, 12500);
        sheet.setColumnWidth(9, 6000);
        sheet.setColumnWidth(10, 15500);

        
       try {
           
           
           FileOutputStream fileout=new FileOutputStream(System.getProperty("user.home")+"/Desktop/ReportelistadoVentas.xlsx");
           book.write(fileout);
           fileout.close();
           
           JOptionPane.showMessageDialog(null, "Reporte creado y descargado en el escritorio con éxito, nombre: ReportelistadoVentas.xlsx");
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FrmTopProductosVendidos.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(FrmTopProductosVendidos.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVerVenta = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCobrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnGenComp = new javax.swing.JButton();
        btnOrdenPedido = new javax.swing.JButton();
        btnGuiaRemision = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnSunat = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbuTipo = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Ventas realizadas");
        setPreferredSize(new java.awt.Dimension(1500, 626));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Buscar: ");
        jLabel1.setName("jLabel1"); // NOI18N

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVentas.setName("tablaVentas"); // NOI18N
        tablaVentas.getTableHeader().setReorderingAllowed(false);
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        jButton1.setMnemonic('N');
        jButton1.setText("Nuevo");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAnular.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        btnAnular.setMnemonic('A');
        btnAnular.setText("Anular");
        btnAnular.setName("btnAnular"); // NOI18N
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
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

        btnVerVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnVerVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-application-pdf-icon.png"))); // NOI18N
        btnVerVenta.setMnemonic('V');
        btnVerVenta.setText("Ver Venta");
        btnVerVenta.setName("btnVerVenta"); // NOI18N
        btnVerVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentaActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimir.setMnemonic('I');
        btnImprimir.setText("Imprimir");
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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

        btnCobrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pagar.png"))); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.setName("btnCobrar"); // NOI18N
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        jButton2.setMnemonic('N');
        jButton2.setText("Editar");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnGenComp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGenComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Price list.png"))); // NOI18N
        btnGenComp.setMnemonic('N');
        btnGenComp.setText("Crear Comprobante");
        btnGenComp.setName("btnGenComp"); // NOI18N
        btnGenComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenCompActionPerformed(evt);
            }
        });

        btnOrdenPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnOrdenPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-document-edit-icon.png"))); // NOI18N
        btnOrdenPedido.setText("Orden. Ped.");
        btnOrdenPedido.setName("btnOrdenPedido"); // NOI18N
        btnOrdenPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenPedidoActionPerformed(evt);
            }
        });

        btnGuiaRemision.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuiaRemision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/note-accept-icon.png"))); // NOI18N
        btnGuiaRemision.setText("Guía Remi.");
        btnGuiaRemision.setName("btnGuiaRemision"); // NOI18N
        btnGuiaRemision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiaRemisionActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Button-Refresh-icon.png"))); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSunat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSunat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/sunat1.png"))); // NOI18N
        btnSunat.setText("Enviar a SUNAT");
        btnSunat.setName("btnSunat"); // NOI18N
        btnSunat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSunatActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Excel2.png"))); // NOI18N
        jButton5.setText("Exportar a Excel");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSunat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuiaRemision, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOrdenPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrdenPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuiaRemision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton5)
                            .addComponent(btnSunat))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Desde:");
        jLabel2.setName("jLabel2"); // NOI18N

        txtFechaDesde.setName("txtFechaDesde"); // NOI18N
        txtFechaDesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaDesdeFocusLost(evt);
            }
        });
        txtFechaDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaDesdePropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Hasta:");
        jLabel3.setName("jLabel3"); // NOI18N

        txtFechaHasta.setName("txtFechaHasta"); // NOI18N
        txtFechaHasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaHastaFocusLost(evt);
            }
        });
        txtFechaHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaHastaPropertyChange(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de Venta:");
        jLabel4.setName("jLabel4"); // NOI18N

        cbuTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Facturas", "Boletas", "Ventas Libres", "No Cobradas" }));
        cbuTipo.setName("cbuTipo"); // NOI18N
        cbuTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbuTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cbuTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1))
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbuTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        cargarVentas();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        if (evt.getClickCount() == 1) {
            
                btnCobrar.setEnabled(true);
                btnOrdenPedido.setEnabled(true);
            
        } else {
            //modificar();
        }
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        eliminar();
        //System.out.println("Admin");
        }
else{
    //JOptionPane.showMessageDialog(null, "No tiene Permisos para realizar esta acción");
    this.iniciarsesionEliminar();
   // System.out.println("No-Admin");
}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        anular();
        //System.out.println("Admin");
        }
else{
    //JOptionPane.showMessageDialog(null, "No tiene Permisos para realizar esta acción");
    this.iniciarsesionAnular();
   // System.out.println("No-Admin");
}
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnVerVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentaActionPerformed
        visualizarVenta();
    }//GEN-LAST:event_btnVerVentaActionPerformed

    @SuppressWarnings("empty-statement")
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
                nuevo();
            } catch (ParseException ex) {
                Logger.getLogger(FrmListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        cobrar();
            
        
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
        try {
                modificar();
            } catch (ParseException ex) {
                Logger.getLogger(FrmListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        //System.out.println("Admin");
        }
else{
    //JOptionPane.showMessageDialog(null, "No tiene Permisos para realizar esta acción");
    this.iniciarsesionModificar();
   // System.out.println("No-Admin");
}
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        
    }//GEN-LAST:event_formComponentShown

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        cargarVentas();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btnGenCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenCompActionPerformed
    genComprobante();        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenCompActionPerformed

    private void btnOrdenPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenPedidoActionPerformed
        ordendePedido ordenPedido = new ordendePedido(null, true);
        ordenPedido.setLocationRelativeTo(this);
        ordenPedido.setDatos(tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 0).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 4).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 9).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 3).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 10).toString());
        ordenPedido.setVisible(true);
    }//GEN-LAST:event_btnOrdenPedidoActionPerformed

    private void btnGuiaRemisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiaRemisionActionPerformed
    String fac=""+tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 3).toString();
            
            if(fac.length()>7){
            fac=fac.substring(0, 7);
            }
            //JOptionPane.showMessageDialog(null, fac);
            if(fac.equals("Factura")){
            
            GuiaDeRemision guia = new GuiaDeRemision(null, true);
        guia.setLocationRelativeTo(this);
        guia.setDatos(tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 0).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 4).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 9).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 3).toString(),tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 10).toString());
        guia.setVisible(true);

            
            }
            else{
            JOptionPane.showMessageDialog(null, "El comprobante de pago de la venta seleccionada no es una factura, por lo que no se puede generar guía de remisión");
            
            
            }            // TODO add your handling code here:
    }//GEN-LAST:event_btnGuiaRemisionActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
cargarVentas();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);     
        cargarVentas();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbuTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbuTipoActionPerformed
    cargarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_cbuTipoActionPerformed

    private void txtFechaDesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaDesdeFocusLost
         // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesdeFocusLost

    private void txtFechaHastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaHastaFocusLost
       // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHastaFocusLost

    private void txtFechaDesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaDesdePropertyChange
    cargarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesdePropertyChange

    private void txtFechaHastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaHastaPropertyChange
    cargarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHastaPropertyChange

    private void btnSunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSunatActionPerformed
    enviarSunat();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSunatActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ExportarExcel();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenComp;
    private javax.swing.JButton btnGuiaRemision;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnOrdenPedido;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSunat;
    private javax.swing.JButton btnVerVenta;
    private javax.swing.JComboBox<String> cbuTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtBuscar;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    // End of variables declaration//GEN-END:variables

    private void cargarVentas() {
        String text = txtBuscar.getText();
        String fecha1="";
        String fecha2="";
        
        if(txtFechaDesde.getDate() != null){
            fecha1=control.Formato_Amd(txtFechaDesde.getDate());
        }
        
        if(txtFechaHasta.getDate() != null){
            fecha2=control.Formato_Amd(txtFechaHasta.getDate());
        }
        
        
        int tipo=cbuTipo.getSelectedIndex();
        
        String andfecha="";
        String andtipo="";
        if(fecha1.length()>0 && fecha2.length()>0){
            andfecha=" and (v.fecha between '"+fecha1+"' and '"+fecha2+"') ";
        }
        
        String andtipoUser="";
        
        if(idusu.equals("0")){
            andtipoUser=" and t.idTipoComprobante<>8 ";
            
        }
        
        if(tipo==0){
            andtipo=" ";
        }else if(tipo==1){
            andtipo=" and t.idTipoComprobante=1 ";
        }else if(tipo==2){
            andtipo=" and t.idTipoComprobante=2 ";
        }else if(tipo==3){
            andtipo=" and t.idTipoComprobante=8 ";
        }else if(tipo==4){
            andtipo=" AND v.estado='Pendiente' ";
        }

        control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, v.`hora` as hora, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- if(v.enobra<0,0,enobra),2) AS SubTotal,if((select count(g.idguiaderemision) from guiaderemision g where g.idventa=v.`idVenta`)>0,'Emitida','No Emitida') as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc, \n" +
 " (select count(dv.idDetalleVenta)from detalleventa dv where dv.idVenta=v.idVenta group by dv.idVenta), u.nomusr, cab.docu_proce_status, cab.cdr_nota, c.tipo    FROM cliente c, (venta v  inner join detalleventa dt on dt.idventa=v.idventa left join cabecera cab on v.idventa=cab.idventa left join usuario u on u.idusuario=v.idusuario LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente`  "+andfecha+" "+andtipo+" "+andtipoUser+" AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%')  group by v.idventa ORDER BY v.`fecha` desc,v.`idVenta` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
        System.out.println(control.Sql);
        //control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- v.`enObra`,2) AS SubTotal,v.`enObra` as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc  FROM cliente c, (venta v LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente` AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%') ORDER BY v.`fecha` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
        control.LlenarJTabla(model, control.Sql, 16);
        btnImprimir.setEnabled(false);
        btnAnular.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnVerVenta.setEnabled(false);
        jButton2.setEnabled(false);
        
        tablaVentas.setDefaultRenderer(Object.class, formato_tablas);
    }
    
    public void ActualizarNuevo(){
        
        int numReg=tablaVentas.getRowCount();
        //System.out.println("Numero de Registros: "+numReg);
        
        int RegBD=Integer.parseInt(control.DevolverRegistroDto("select count(v.idventa) from venta v;", 1));
         //System.out.println("Numero de Registros BD: "+RegBD);   
        if(RegBD>numReg){
            cargarVentas();
            
            System.out.println("Actualizando Listado de Ventas");
        }
        if (numReg>30){
        numReg=30;
        }
        for(int i=0;i<numReg;i++){
            String idv = model.getValueAt(i, 0).toString();
            int idcli = Integer.parseInt(model.getValueAt(i, 9).toString());
            Double total =  Double.parseDouble(model.getValueAt(i, 7).toString());
            String sq="SELECT *,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) FROM venta v where idventa='"+idv+"';";
            try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
            while (Base.rt.next()) {
              if(Integer.parseInt(Base.rt.getString(3))!=idcli || Double.parseDouble(Base.rt.getString(12))!=total){
                    cargarVentas();
                    System.out.println("Actualizando Listado de Ventas por cambio de cliente  ocosto");
                }
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        }
    
    }
    public void recargar() {
        int row = tablaVentas.getSelectedRow();
        cargarVentas();
        if (row >= 0) {
            tablaVentas.getSelectionModel().setSelectionInterval(row, row);
        }
    }

    private void modificar() throws ParseException {
        
        
        String estadoEnvia= model.getValueAt(tablaVentas.getSelectedRow(), 13).toString();
            boolean b=false;
            String msj="";
            
            if(estadoEnvia.equals("L") || estadoEnvia.equals("E") || estadoEnvia.equals("A")){
                msj="No se puede eliminar esta venta, porque ya ha sido transmitida a la SUNAT";
            } else {
                b=true;
            }
            if(b){

        int idVenta = Integer.parseInt(model.getValueAt(tablaVentas.getSelectedRow(), 0).toString());
        
        System.out.println(idVenta);
        String estado=model.getValueAt(tablaVentas.getSelectedRow(), 8).toString();
        System.out.println(estado);
        isAnulado=false;
        if(estado.equals("Anulado")){
            isAnulado=true;
        }
        
        if(isAnulado==false){
        FrmRegistrarVenta registrarVenta = new FrmRegistrarVenta(this,false);
        registrarVenta.setIdVenta(idVenta);
        FrmMain.escritorio.add(registrarVenta, JLayeredPane.DEFAULT_LAYER);
        registrarVenta.setLocation((FrmMain.escritorio.getWidth() - registrarVenta.getWidth()) / 2, 0);
        registrarVenta.setVisible(true);
        }
        else{
        JOptionPane.showMessageDialog(null, "La venta no se puede editar porque se encuentra anulada");
        }
        
        } else{
            JOptionPane.showMessageDialog(null, msj);
        }
    }
    
    
    private void anularXML(){
     String idVenta= model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                String sql="select * from cabecera where idventa='"+idVenta+"';";
                
                
                
                    
                    String DigestValue="", ruc="", tipodoc="", numero="", docu_codigo="";
                    
                    
                    
                
                 try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                                 

                    DigestValue=control.Base.rt.getString(35);
                    ruc=control.Base.rt.getString(11); 
                    tipodoc=control.Base.rt.getString(17); 
                    numero=control.Base.rt.getString(18);
                    docu_codigo=control.Base.rt.getString(1);
                    
                    if(tipodoc.equals("01")){
                        
                        anularFactura(DigestValue, ruc, tipodoc, numero, docu_codigo);
                       // NotaCreditoElectronica.generarXMLZipiadoBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    }else if(tipodoc.equals("03")){
                        
                        anularBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    
                    }
                    
                    
                    
                    cargarVentas();
    }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
                 
                    cargarVentas();
    }
    
    
     private void enviarAnulado(){
     String idVenta= model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                String sql="select * from cabecera where idventa='"+idVenta+"';";
                
                
                
                    
                    String DigestValue="", ruc="", tipodoc="", numeroAnulado="", docu_codigo="",docu_enviaws="";;
                    
                    
                    
                
                 try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                                 

                   
                    ruc=control.Base.rt.getString(11); 
                    tipodoc=control.Base.rt.getString(17); 
                    numeroAnulado=control.Base.rt.getString(18);
                    docu_codigo=control.Base.rt.getString(1);
                    docu_enviaws=control.Base.rt.getString(39);
                    
                    if(tipodoc.equals("01")){
                        
    String sql2="select * from notascredito where docu_codigo='"+docu_codigo+"';";
        
        String numero="",idNota="";
      
        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql2);
            while (control.Base.rt.next()) {
                    idNota=control.Base.rt.getString(1);
                    numero=control.Base.rt.getString(4)+"-"+control.Base.rt.getString(5); 
                    DigestValue=control.Base.rt.getString(8);
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        

       if(docu_enviaws.equals("S")){
                            enviarAnular();
                        }
        
        NotaCreditoElectronica.enviarSunat(DigestValue,ruc,numero,docu_codigo,idNota,numeroAnulado);
        
        
                       // NotaCreditoElectronica.generarXMLZipiadoBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    }else if(tipodoc.equals("03")){
                        
                       // anularBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    
                    }
                    
                    
                    
                    cargarVentas();
    }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
                 
                    cargarVentas();
    }
    
    private void enviarSunat(){
        if (tablaVentas.getSelectedRow() >= 0) {
            String estadoEnvia= model.getValueAt(tablaVentas.getSelectedRow(), 13).toString();
            String estado = model.getValueAt(tablaVentas.getSelectedRow(), 8).toString();
            
            
            boolean b=false;
            boolean c=false;
            boolean d=false;
            String msj="";
            
            if(estadoEnvia.equals("L") || estadoEnvia.equals("E") || estadoEnvia.equals("A")){
                msj="No se puede volver a enviar esta venta, porque ya ha sido transmitida a la SUNAT";
            }else if(estadoEnvia.length()==0){
                msj="No se puede enviar esta venta a SUNAT, porque no cuenta con factura o boleta electrónica";
            }
            else if(estadoEnvia.equals("N")) {
                b=true;
                if (!"Anulado".equals(estado)) {
                d=true;
                }
            }else if(estadoEnvia.equals("AN")) {
                c=true;
            }
            
            if(b){
                
                if (JOptionPane.showConfirmDialog(null, "¿Confirma que desea enviar a SUNAT la venta sleccionada?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
                String idVenta= model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                String sql="select * from cabecera where idventa='"+idVenta+"';";
                
                String DigestValue="", ruc="", tipodoc="", numero="", docu_codigo="";
                
                 try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                                 

                    DigestValue=control.Base.rt.getString(35);
                    ruc=control.Base.rt.getString(11); 
                    tipodoc=control.Base.rt.getString(17); 
                    numero=control.Base.rt.getString(18);
                    docu_codigo=control.Base.rt.getString(1);
                    
                    BolElectronica.enviarSunat(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    cargarVentas();
                    if(d) {
                        anularXML();
                    }
            }
           } 
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
                
         }       
            } else if(c) {
                enviarAnulado();
            }
            else{
                JOptionPane.showMessageDialog(null, msj);
            }
            
            
        }
        else{
        JOptionPane.showMessageDialog(null, "Selecione una Venta para enviar a la SUNAT");
        }
    
    
    }
        
    private void eliminar() {

        if (tablaVentas.getSelectedRow() >= 0) {
            
            String estadoEnvia= model.getValueAt(tablaVentas.getSelectedRow(), 13).toString();
            boolean b=false;
            String msj="";
            
            if(estadoEnvia.equals("L") || estadoEnvia.equals("E") || estadoEnvia.equals("A")){
                msj="No se puede eliminar esta venta, porque ya ha sido transmitida a la SUNAT";
            } else {
                b=true;
            }
            if(b){
                
            if (JOptionPane.showConfirmDialog(null, "Al eliminar la venta seleccionada se actualizará los almacenes con los productos contenidos en esta venta."
                    + "\n¿Confirma que desea eliminar la venta seleccionada?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
                String idventa = model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                
                String estado= model.getValueAt(tablaVentas.getSelectedRow(), 8).toString();
                isAnulado=false;
                if(estado.equals("Anulado")){isAnulado=true;}
                
                ResultSet DevolverRegistro = control.DevolverRegistro("SELECT d.iddetalleventa,d.idproducto,(d.cantidad*d.cantReal),d.idalmacen, d.idlote FROM detalleventa d where idventa='" + idventa + "'");
                ResultSet DevolverRegistro1 = control.DevolverRegistro("select g.idguiaderemision from guiaderemision g where g.idventa='" + idventa + "'");
                try {
                    while (DevolverRegistro.next()) {
                        control.ejecutar(String.format("DELETE FROM detalleguiaremision WHERE `idDetalleVenta`=%s;", DevolverRegistro.getString(1)));
             control.ejecutar(String.format("DELETE FROM detalleordenpedido WHERE `idDetalleVenta`=%s;", DevolverRegistro.getString(1)));
             
                if(isAnulado=false){
                        control.ejecutar(String.format("UPDATE stock s SET s.cantidadDisponible=s.cantidadDisponible+%s WHERE s.idAlmacen=%s AND s.idProducto=%s;", DevolverRegistro.getString(3), DevolverRegistro.getString(4), DevolverRegistro.getString(2)));
                        control.ejecutar(String.format("UPDATE lote s SET s.cant=s.cant+%s WHERE s.idlote=%s AND s.idProducto=%s;", DevolverRegistro.getString(3), DevolverRegistro.getString(5), DevolverRegistro.getString(2)));
                        }
                }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    while (DevolverRegistro1.next()) {
                        control.ejecutar(String.format("DELETE FROM motivo_has_guiaderemision WHERE idguiaderemision=%s;", DevolverRegistro1.getString(1)));
            
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
                control.ejecutar(String.format("DELETE FROM ordenpedido WHERE `idVenta`=%s;", idventa)); 
                control.ejecutar(String.format("DELETE FROM guiaderemision WHERE `idVenta`=%s;", idventa)); 
                control.ejecutar("delete from venta where idventa='" + idventa + "'");
                try {
                    FrmProductosBajosDeStock.cargarDatos();
                } catch (Exception e) {
                }
                cargarVentas();
            }
        } else{
            JOptionPane.showMessageDialog(null, msj);
        }
          }  else {
            JOptionPane.showMessageDialog(null, "Selecione una Venta para eliminar");
        
      }
        

    }
    
    private void genComprobante() {

        if (tablaVentas.getSelectedRow() >= 0) {
            
            int []tSel=tablaVentas.getSelectedRows();
            int lar=tSel.length;
            String idVta []=new String [lar];
            int aux=0;
            
            for(int i=0;i<lar;i++){
                
                String comprobante = model.getValueAt(tSel[i], 3).toString();
                idVta[i]= model.getValueAt(tSel[i], 0).toString();
                
                if(comprobante.startsWith("Venta")){
                   
                }
                else{
                    aux=1;
                }
            }
            
            if(aux==1){
                JOptionPane.showMessageDialog(null, "Seleccione solo Ventas Libres");
            }
            
            else{
                if (JOptionPane.showConfirmDialog(null, ""
                    + "\n¿Confirma que desea generar un comprobante de pago a las ventas libres seleccinadas?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
                
                     /*for(int i=0;i<lar;i++){
                         JOptionPane.showMessageDialog(null, idVta[i]);
                     }*/
                    
            GenComprobante genComprobante = new GenComprobante(null,true);
            genComprobante.setLocationRelativeTo(this);
            
            genComprobante.setVtas(idVta,model.getValueAt(tablaVentas.getSelectedRow(), 7).toString());
            genComprobante.setVisible(true);
            if (genComprobante.getResult() == GenComprobante.OK) {
                cargarVentas();
            }
            else {
            JOptionPane.showMessageDialog(this, "No se Generó el Comprobante");
        }
            }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione una Venta Libre para generar Comprobante");
        }

    }
    
    private void cobrar() {
    if (tablaVentas.getSelectedRow() >= 0) { 
    String facturado= model.getValueAt(tablaVentas.getSelectedRow(), 3).toString();
    if(facturado.length()==0){
       
        double cuenta=Double.parseDouble(model.getValueAt(tablaVentas.getSelectedRow(), 7).toString());
        if(cuenta>0){
        String idVenta=model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
        String nomCliente=model.getValueAt(tablaVentas.getSelectedRow(), 4).toString();
        String cliente=model.getValueAt(tablaVentas.getSelectedRow(), 9).toString();
        String ruc=model.getValueAt(tablaVentas.getSelectedRow(), 10).toString();
        String numRows=model.getValueAt(tablaVentas.getSelectedRow(), 11).toString();
        
        CobrarVentaDialog cobrarVentaDialog = new CobrarVentaDialog(null, true);
        cobrarVentaDialog.setLocationRelativeTo(this);
        
        String tipoCliente= model.getValueAt(tablaVentas.getSelectedRow(), 15).toString();
        
        cobrarVentaDialog.setDatos(Integer.parseInt(idVenta), Integer.parseInt(numRows),nomCliente,Integer.parseInt(cliente),ruc,tipoCliente);
        cobrarVentaDialog.setVisible(true);
        
        if (cobrarVentaDialog.getResult() == CobrarVentaDialog.OK) {
            cargarVentas();
        }
        
        
        }
        else{
            JOptionPane.showMessageDialog(null, "La Venta no cuenta con ningun producto");
        }
        
    
    
    }
    else{
        JOptionPane.showMessageDialog(null, "La Venta ya se encuentra Facturada");
    }
    
    
    
    }
    else {
            JOptionPane.showMessageDialog(null, "Seleccione una Venta!!");
       
        }
    }
    
    private void anularFactura(String DigestValue, String ruc,String tipodoc,String numeroFactura,String docu_codigo)
    {
        String sql="select * from notascredito where  tipo='07' and tipoanulado='factura' order by id desc limit 1;";
        
        String serieNota="F001";
        int numeroNota=1;
        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                    serieNota=control.Base.rt.getString(4);
                    numeroNota=Integer.parseInt(control.Base.rt.getString(5))+numeroNota; 
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        control.ejecutar(String.format("insert into notascredito values (null,'%s','%s','%s','%s','01','Factura Anulada por Anulación de la Operación','','','','','S','N','07',curdate(),'factura');", docu_codigo,numeroFactura,serieNota,String.valueOf(numeroNota)));
        System.out.println(String.format("insert into notascredito values (null,'%s','%s','%s','%s','01','Factura Anulada por Anulación de la Operación','','','','','S','N','07',curdate(),'factura');", docu_codigo,numeroFactura,serieNota,String.valueOf(numeroNota)));
        
        
         String sql2="select * from notascredito where  tipo='07' and tipoanulado='factura' order by id desc limit 1;";
        
         String idNota="0";
         String fechaProce="0000-00-00";

        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                    idNota=control.Base.rt.getString(1);
                    fechaProce=control.Base.rt.getString(15);
                    
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        
        gennota.GenerarNotaCredito(idNota,fechaProce,ruc,numeroFactura,docu_codigo,serieNota+"-"+String.valueOf(numeroNota));
        
        
    }
    
    private void anularBoleta(String DigestValue, String ruc,String tipodoc,String numeroFactura,String docu_codigo)
    {
        String sql="select * from resumenboletas order by id desc limit 1;";
        
        String serieResumen="RC";
        int numeroResumen=1;
        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                   // serieNota=control.Base.rt.getString(4);
                    numeroResumen=Integer.parseInt(control.Base.rt.getString(1))+numeroResumen; 
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        String razonsocial="QUISPE GUTARRA DE VILCA CARMEN RUT";
        control.ejecutar(String.format("insert into resumenboletas values (null,'%s','10320432591',curdate(),curdate(),'S','N','','','','3','','');",razonsocial));
        //System.out.println(String.format("insert into notascredito values (null,'%s','%s','%s','%s','01','Factura Anulada por Anulación de la Operación','','','','','S','N','07',curdate(),'factura');", docu_codigo,numeroFactura,serieNota,String.valueOf(numeroNota)));
        
        
         String sql2="select * from resumenboletas order by id desc limit 1;";
        
         String idRes="0";
         String fechaProce="0000-00-00";

        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql2);
            while (control.Base.rt.next()) {
                    idRes=control.Base.rt.getString(1);
                    fechaProce=control.Base.rt.getString(4);
                    
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        
        control.ejecutar(String.format("insert into resumendetalle values(null,'%s','%s');",idRes,docu_codigo));
        
        
        //gennota.GenerarNotaCredito(idNota,fechaProce,ruc,numeroFactura,docu_codigo,serieNota+"-"+String.valueOf(numeroNota));
        String esta="3";
        genComprobanteElectronico.GenerarResumen(String.valueOf(idRes),razonsocial,ruc,fechaProce,esta);
        
        
    }
    
     private void enviarAnularBoleta(String DigestValue, String ruc,String tipodoc,String numeroFactura,String docu_codigo)
    {
       String sql="select * from resumenboletas order by id desc limit 1;";
        
        String serieResumen="RC";
        int numeroResumen=1;
        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                   // serieNota=control.Base.rt.getString(4);
                    numeroResumen=Integer.parseInt(control.Base.rt.getString(1))+numeroResumen; 
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        String razonsocial="QUISPE GUTARRA DE VILCA CARMEN RUT";
        control.ejecutar(String.format("insert into resumenboletas values (null,'%s','10320432591',curdate(),curdate(),'S','N','','','','3','','');",razonsocial));
        //System.out.println(String.format("insert into notascredito values (null,'%s','%s','%s','%s','01','Factura Anulada por Anulación de la Operación','','','','','S','N','07',curdate(),'factura');", docu_codigo,numeroFactura,serieNota,String.valueOf(numeroNota)));
        
        
         String sql2="select * from resumenboletas order by id desc limit 1;";
        
         String idRes="0";
         String fechaProce="0000-00-00";

        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql2);
            while (control.Base.rt.next()) {
                    idRes=control.Base.rt.getString(1);
                    fechaProce=control.Base.rt.getString(4);
                    
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        
        control.ejecutar(String.format("insert into resumendetalle values(null,'%s','%s');",idRes,docu_codigo));
        
        
        //gennota.GenerarNotaCredito(idNota,fechaProce,ruc,numeroFactura,docu_codigo,serieNota+"-"+String.valueOf(numeroNota));
        String esta="1";
        genComprobanteElectronico.GenerarResumen(String.valueOf(idRes),razonsocial,ruc,fechaProce,esta);
        
        
    }
    
    
    private void enviarAnular(){
    String idVenta= model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                          
                String sql="select * from cabecera where idventa='"+idVenta+"';";
                
                String DigestValue="", ruc="", tipodoc="", numero="", docu_codigo="";
                
                 try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                                 

                    DigestValue=control.Base.rt.getString(35);
                    ruc=control.Base.rt.getString(11); 
                    tipodoc=control.Base.rt.getString(17); 
                    numero=control.Base.rt.getString(18);
                    docu_codigo=control.Base.rt.getString(1);
                    
                    BolElectronica.enviarSunat(DigestValue, ruc, tipodoc, numero, docu_codigo);
             
               
            }
           } 
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
                
          
            
           
    }
    private void anular() {
       
        if (tablaVentas.getSelectedRow() >= 0) {
            String estado = model.getValueAt(tablaVentas.getSelectedRow(), 8).toString();
            if (!"Anulado".equals(estado)) {
                 if (model.getValueAt(tablaVentas.getSelectedRow(), 8).toString().equals("Pagado")) {
        
       
                if (JOptionPane.showConfirmDialog(null, "Al anular la venta se actualizará los almacenes con los productos contenidos en esta venta.¿Confirma que desea anular esta venta?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
                    String idventa = model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                    ResultSet DevolverRegistro = control.DevolverRegistro("SELECT d.iddetalleventa,d.idproducto,(d.cantidad*d.cantReal),d.idalmacen, d.idlote  FROM detalleventa d where idventa='" + idventa + "'");
                    try {
                        while (DevolverRegistro.next()) {
                            control.ejecutar(String.format("UPDATE stock s SET s.cantidadDisponible=s.cantidadDisponible+%s WHERE s.idAlmacen=%s AND s.idProducto=%s;", DevolverRegistro.getString(3), DevolverRegistro.getString(4), DevolverRegistro.getString(2)));
    
                            control.ejecutar(String.format("UPDATE lote s SET s.cant=s.cant+%s WHERE s.idlote=%s AND s.idProducto=%s;", DevolverRegistro.getString(3), DevolverRegistro.getString(5), DevolverRegistro.getString(2)));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FrmListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    control.ejecutar("update venta v set v.estado='Anulado' where idventa='" + idventa + "'");
                    
                    
                    String idVenta= model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                String sql="select * from cabecera where idventa='"+idVenta+"';";
                
                
                
                    
                    String DigestValue="", ruc="", tipodoc="", numero="", docu_codigo="",docu_enviaws="",cdr_observacion="";
                    
                    
                    
                
                 try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                                 

                    DigestValue=control.Base.rt.getString(35);
                    ruc=control.Base.rt.getString(11); 
                    tipodoc=control.Base.rt.getString(17); 
                    numero=control.Base.rt.getString(18);
                    docu_codigo=control.Base.rt.getString(1);
                    cdr_observacion=control.Base.rt.getString(38);
                    docu_enviaws=control.Base.rt.getString(39);
                    
                    if(tipodoc.equals("01")){
                        
                        if(docu_enviaws.equals("S")){
                            enviarAnular();
                        }
                        
                        anularFactura(DigestValue, ruc, tipodoc, numero, docu_codigo);
                       // NotaCreditoElectronica.generarXMLZipiadoBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    }else if(tipodoc.equals("03")){
                        
                        if(cdr_observacion.equals("")){
                            enviarAnularBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                        }
                        
                        anularBoleta(DigestValue, ruc, tipodoc, numero, docu_codigo);
                    
                    }
                    
                    
                    
                    cargarVentas();
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
                 
                    cargarVentas();
                    
                    
                    try {
                        FrmProductosBajosDeStock.cargarDatos();
                    } catch (Exception e) {
                    }
                }
                 }
        else{ 
                     JOptionPane.showMessageDialog(this, "Solo se puede Anular, las ventas que han sido pagadas y se ha generado su número de comprobante");
                 
                 }
            } else {
                JOptionPane.showMessageDialog(null, "Esta venta ya está Anulada!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Venta!!");
       
        }
    }

    private void imprimir() {
        if (model.getValueAt(tablaVentas.getSelectedRow(), 8).toString().equals("Pagado")) {
            String idVenta = model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
            String comprobante = model.getValueAt(tablaVentas.getSelectedRow(), 3).toString();

            Map map = new HashMap();
            map.put("idVenta", idVenta);
            map.put("montoLetra", "" + new Numero_a_Letra().Convertir(model.getValueAt(tablaVentas.getSelectedRow(), 7).toString().replace(",", ""), false));
            if (Configuracion.getInstance().isVistaPreviaComprobantesVenta()) {
                if (comprobante.startsWith("Factura")) {
                    control.showReportDialog("Comprobante de Venta", "ticketFacturaElectronica", map);
                } else if (comprobante.startsWith("Boleta")) {
                    //control.showReportDialog("Comprobante de Venta", "boletaVentaReal", map);
                    control.showReportDialog("Comprobante de Venta", "ticketBoletaElectronica", map);
                } else if (comprobante.startsWith("Ticket")) {
                    control.showReportDialog("Comprobante de Venta", "ticket", map);
                }else if(comprobante.startsWith("Orden")){                 
                  
                    control.showReportDialog("Orden de Pedido", "ordenDePedido", map);
                    //JOptionPane.showMessageDialog(null,"orden de pedido");
                }
                else if(comprobante.startsWith("Venta")){
            Map map1 = new HashMap();
            int idv=Integer.parseInt(idVenta);
            map1.put("idVenta", idv);
            map1.put("montoLetra", "" + new Numero_a_Letra().Convertir(model.getValueAt(tablaVentas.getSelectedRow(), 7).toString().replace(",", ""), false));
                    control.showReportDialog("Comprobante de Venta", "VentaLibre", map1);
                    //JOptionPane.showMessageDialog(null,"orden de pedido");
                }

            } else {
                if (comprobante.startsWith("Factura")) {
                    control.imprimirComprobanteVenta(idVenta, "FacturaVentaReal", map);
                } else if (comprobante.startsWith("Boleta")) {
                    control.imprimirComprobanteVenta(idVenta, "boletaVentaReal", map);
                } else if (comprobante.startsWith("Ticket")) {
                    control.imprimirComprobanteVenta(idVenta, "ticket", map);
                }else if(comprobante.startsWith("Orden")){
                    control.imprimirComprobanteVenta(idVenta, "ordenDePedido", map);
                    //JOptionPane.showMessageDialog(null,"orden de pedido");
                }

            }
        } else {
            JOptionPane.showMessageDialog(this, "Solo se puede enviar a la impresora, las ventas que han sido pagadas y se ha generado su número de comprobante");
        }

    }

    private void visualizarVenta() {
        String idVenta = model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
        String comprobante = model.getValueAt(tablaVentas.getSelectedRow(), 3).toString();
        Map map = new HashMap();
        map.put("idVenta",  Integer.parseInt(idVenta));
        map.put("montoLetra", "Son " + new Numero_a_Letra().Convertir(model.getValueAt(tablaVentas.getSelectedRow(), 7).toString().replace(",", ""), false));
        if (comprobante.startsWith("Factura")) {
            control.showReportDialog("Comprobante de Venta", "facturaVentaVista1", map);
        } else if (comprobante.startsWith("Boleta")) {
            control.showReportDialog("Comprobante de Venta", "boletaVentaVistaReal", map);
        } else if (comprobante.startsWith("Orden")) {
            control.showReportDialog("Comprobante de Venta", "ordenDePedidoVista", map);
            //JOptionPane.showMessageDialog(null, "orden depedido");
        } else if (comprobante.startsWith("Ticket")) {
            control.showReportDialog("Comprobante de Venta", "ticket", map);
        } else if (comprobante.startsWith("Venta")) {
            control.showReportDialog("Comprobante de Venta", "VentaLibre", map);
        }else {
            JOptionPane.showMessageDialog(this, "Esta venta aún no ha sido facturado");
        }
        
    }

    private void nuevo() throws ParseException {
        FrmRegistrarVenta registrarVenta = new FrmRegistrarVenta(this,true);

        FrmMain.escritorio.add(registrarVenta, JLayeredPane.DEFAULT_LAYER);
        registrarVenta.setLocation((FrmMain.escritorio.getWidth() - registrarVenta.getWidth()) / 2, 0);
        registrarVenta.setVisible(true);
        
    }

}
