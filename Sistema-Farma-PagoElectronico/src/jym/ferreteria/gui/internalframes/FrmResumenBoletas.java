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
import com.org.ws.ResElectronica;



import java.util.concurrent.*;
import static jym.ferreteria.clases.Controlador.Base;


import jym.ferreteria.clases.Accesos;


import jym.ferreteria.gui.dialogs.IniciarSesionAdmin;


import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.org.ws.ResElectronica;
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



/**
 *
 * @author crist
 */
public class FrmResumenBoletas extends javax.swing.JInternalFrame {
    private Controlador control = new Controlador();
    private CustomTableModel model = new CustomTableModel();
    private static genfactura.GenComprobante genComprobanteElectronico = new genfactura.GenComprobante();
    
    private IniciarSesionAdmin iniciarSesionA = null;
    
    ResultSet rset;
    
    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    
    private String tipoUsu="";
    public boolean isAnulado=false;
    /**
     * Creates new form FrmResumenBoletas
     */
    public FrmResumenBoletas() {
        initComponents();
        
        btnVerVenta.setVisible(false);
         btnImprimir.setVisible(false);
        botones(false);
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
         model.setColumnIdentifiers(new String[]{"N°","RUC","Descripción","Fecha de Boletas","Sumatoria Total S/.","Número de Ventas","Estado","E","fecE","rzonsocial","enviar","hash","idRes","fRes","cdrnota"});
         tablaVentas.setModel(model);
         control.hideTableColumn(tablaVentas,1,7,8,9,10,11,12,13,14);
         control.tableWidthColumn(tablaVentas, 20, 0);
         control.tableWidthColumn(tablaVentas, 250, 2);
         control.tableWidthColumn(tablaVentas, 80, 3,4,5);
         control.tableWidthColumn(tablaVentas, 200, 6);
         
         tablaVentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
               botones(true);
              
            }
        });
         
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        cargarVentas();
        
    }
    public void botones(boolean b){
        btnSunat.setEnabled(b);
        btnVerVenta.setEnabled(b);
        btnImprimir.setEnabled(b);

    }
    
    private void cargarVentas() {
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
            andfecha=" and (c.docu_fecha between '"+fecha1+"' and '"+fecha2+"') ";
        }
        
        if(tipo==0){
            andtipo=" ";
        }else if(tipo==1){
            andtipo=" and c.cdr_observacion='1' ";
        }else if(tipo==2){
            andtipo=" and c.cdr_observacion='' ";
        }

        control.Sql = "SELECT c.empr_nroruc, 'Ventas con Comprobantes Boletas Electrónicas' as descripcion, date_format(c.`docu_fecha`,'%d/%m/%Y'), sum(c.docu_total), count(c.docu_codigo),if(c.cdr_observacion='1','Resumen Enviado','Resumen No Enviado'),c.cdr_observacion, c.docu_fecha,c.empr_razonsocial \n"
                + " , ifnull(r.enviar,'N'), ifnull(r.hashcode,''), ifnull(r.id,''), ifnull(r.fechaEnvio,''), ifnull(r.cdr_nota,'') FROM cabecera c left join resumendetalle rd on rd.docu_codigo=c.docu_codigo \n" +
                    " left join resumenboletas r on r.id=rd.idresumen where docu_tipodocumento='03'  "+andtipo+"  "+andfecha+"   \n" +
                        " group by c.cdr_observacion,c.docu_fecha, r.id  order by c.docu_fecha, c.docu_codigo desc;";
        System.out.println(control.Sql);
        //control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- v.`enObra`,2) AS SubTotal,v.`enObra` as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc  FROM cliente c, (venta v LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente` AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%') ORDER BY v.`fecha` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
        control.LlenarJTablaNum(model, control.Sql, 14);
        botones(false);
        
        tablaVentas.setDefaultRenderer(Object.class, formato_tablas);
    }
    
      public DefaultTableCellRenderer  formato_tablas = new DefaultTableCellRenderer(){
    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    		super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
    		if (col==6){
    			//double valor = Double.parseDouble((String) value);
                        
                        String valor=(String)value;
                        
                        if(valor.equals("Resumen No Enviado")){
                            setBackground(Color.decode("#FFBB33"));
                        } else if(valor.equals("Resumen Enviado")){
                            setBackground(Color.decode("#0CEC26"));
                        } else if(valor.equals("E")){
                            setBackground(Color.decode("#F7FA28"));
                        } else if(valor.equals("A")){
                            setBackground(Color.decode("#EC0C13"));
                        }
                        
    			
    		}
                else{
                    setBackground(Color.white);
    		    setForeground(Color.black);
                }
    		return this;
    	}
    };
      
      
      
      private void enviarSunat(){
        if (tablaVentas.getSelectedRow() >= 0) {
            String estadoEnvia= model.getValueAt(tablaVentas.getSelectedRow(), 7).toString();

            
            boolean b=false;
            boolean c=false;
            String msj="";
            
            if(estadoEnvia.equals("1")){
                msj="No se puede volver a enviar este resumen de boletas electrónicas, porque ya ha sido transmitida a la SUNAT";
            }else if(estadoEnvia.length()==0){
                b=true;
            }

            
            if(b){
                
                String enviar= model.getValueAt(tablaVentas.getSelectedRow(), 10).toString();
                
                
                
                if (JOptionPane.showConfirmDialog(null, "¿Confirma que desea enviar a SUNAT el resumen de boletas de ventas seleccionada?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
                String razonSocial=model.getValueAt(tablaVentas.getSelectedRow(), 9).toString();
                String ruc=model.getValueAt(tablaVentas.getSelectedRow(), 1).toString();
                String fechaEmi=model.getValueAt(tablaVentas.getSelectedRow(), 8).toString();
                    
                String idVenta= model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
                    
                    if(enviar.equals("N")){
                        
                        
                
                control.Sql =String.format("insert into resumenboletas values(null,'%s','%s','%s',curdate(),'%s','%s','','','','','','');", razonSocial,ruc,fechaEmi,'S','N');
                
                int idResumen = control.executeAndGetId(control.Sql);
                
                String sql="select c.docu_codigo,c.docu_tipodocumento,c.docu_numero, c.clie_tipodoc, c.clie_numero, c.docu_gravada, c.docu_exonerada, \n" +
                            " c.docu_inafecta, c.docu_otroscargos,c.docu_isc,c.docu_igv, c.docu_otrostributos, c.docu_total, c.docu_gratuita \n" +
                            " from cabecera c where c.docu_tipodocumento='03' and c.docu_fecha='"+fechaEmi+"' and c.cdr_observacion='';";
                String docu_codigo="";
                
                 try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                                 
                docu_codigo=control.Base.rt.getString(1);
 
  
             control.ejecutar(String.format("insert into resumendetalle values(null,'%s','%s');",String.valueOf(idResumen),docu_codigo));        
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
                 
                 String estado="1";
                 
                genComprobanteElectronico.GenerarResumen(String.valueOf(idResumen),razonSocial,ruc,fechaEmi,estado);
                cargarVentas();
                
                }
                else if(enviar.equals("S"))
                {
                    
                    String digestvalue=model.getValueAt(tablaVentas.getSelectedRow(), 11).toString();
                    String idRes=model.getValueAt(tablaVentas.getSelectedRow(), 12).toString();
                    String fecenvio=model.getValueAt(tablaVentas.getSelectedRow(), 13).toString();
                    String estado="1";
                    ResElectronica.enviarSunat(digestvalue,ruc,idRes,fecenvio,estado);
                    
                    cargarVentas();
                }
                    
                    
                    
                
                
         }       
            }
            else{
                JOptionPane.showMessageDialog(null, msj);
            }
            
            
        }
        else{
        JOptionPane.showMessageDialog(null, "Selecione una Venta para enviar a la SUNAT");
        }
    
    
    }
      
      public  void ExportarExcel(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet=book.createSheet("Reporte de Resúmen de Boletas");
        
        
        CellStyle style3 = book.createCellStyle();
        
        style3.setBorderBottom(BorderStyle.MEDIUM);
        style3.setBorderLeft(BorderStyle.MEDIUM);
        style3.setBorderRight(BorderStyle.MEDIUM);
        style3.setBorderTop(BorderStyle.MEDIUM);
        
      Row row0=sheet.createRow(1);
      Cell celda0=row0.createCell(2);
      celda0.setCellValue("Reporte de Resúmen de Boletas");
      //celda0.setCellStyle(style3);
      

    String fecha1="";
        String fecha2="";
        String mensaje="";
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
        String andtipo="";
        if(fecha1.length()>0 && fecha2.length()>0){
            andfecha=" and (c.docu_fecha between '"+fecha1+"' and '"+fecha2+"') ";
            mensaje=" Desde: "+fec1+" Hasta: "+fec2;
        }
        
        String msj="";
        
        if(tipo==0){
            andtipo=" ";
            msj="Listado General de Resúmenes de Boletas ";
        }else if(tipo==1){
            andtipo=" and c.cdr_observacion='1' ";
            msj="Listado de Resúmenes de Boletas Enviados a SUNAT ";
        }else if(tipo==2){
            andtipo=" and c.cdr_observacion='' ";
            msj="Listado de Resúmenes de Boletas Enviados Pendientes de Envío a SUNAT ";
        }

        control.Sql = "SELECT c.empr_nroruc, 'Ventas con Comprobantes Boletas Electrónicas' as descripcion, date_format(c.`docu_fecha`,'%d/%m/%Y'), sum(c.docu_total), count(c.docu_codigo),if(c.cdr_observacion='1','Resumen Enviado','Resumen No Enviado'),c.cdr_observacion, c.docu_fecha,c.empr_razonsocial \n"
                + " , ifnull(r.enviar,'N'), ifnull(r.hashcode,''), ifnull(r.id,''), ifnull(r.fechaEmi,''), ifnull(r.cdr_nota,'') FROM cabecera c left join resumendetalle rd on rd.docu_codigo=c.docu_codigo \n" +
                    " left join resumenboletas r on r.id=rd.idresumen where docu_tipodocumento='03'  "+andtipo+"  "+andfecha+"   \n" +
                        " group by c.cdr_observacion,c.docu_fecha, r.id  order by c.docu_fecha, c.docu_codigo desc;";
        System.out.println(control.Sql);
        //control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- v.`enObra`,2) AS SubTotal,v.`enObra` as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc  FROM cliente c, (venta v LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente` AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%') ORDER BY v.`fecha` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
      //  control.LlenarJTablaNum(model, control.Sql, 14);

        mensaje=msj+mensaje;
        
       Row row2=sheet.createRow(3);
      Cell celda2=row2.createCell(1);
      celda2.setCellValue(mensaje);
        

       ResultSet result = control.DevolverRegistro(control.Sql);
       
       
       
     /*  "N°","RUC","Descripción","Fecha de Boletas","Sumatoria Total S/.","Número de Ventas","Estado","E","fecE","rzonsocial",
       "enviar","hash","idRes","fRes","cdrnota"
       */
     
       int cont=1;
       String descripcion="";
       String fecha="";
       String sumatoria="";
       String numvtas="";
       String estado="";


       

       
       Row rowt = sheet.createRow(4);
       
       Cell celdatos2col0= rowt.createCell(0);
       Cell celdatos2col1= rowt.createCell(1);
       Cell celdatos2col2= rowt.createCell(2);
       Cell celdatos2col3= rowt.createCell(3);
       Cell celdatos2col4= rowt.createCell(4);
       Cell celdatos2col5= rowt.createCell(5);



       
       celdatos2col0.setCellStyle(style3);
       celdatos2col1.setCellStyle(style3);
       celdatos2col2.setCellStyle(style3);
       celdatos2col3.setCellStyle(style3);
       celdatos2col4.setCellStyle(style3);
       celdatos2col5.setCellStyle(style3);


       
       celdatos2col0.setCellValue("N°");
       celdatos2col1.setCellValue("Descripción");
       celdatos2col2.setCellValue("Fecha de Boletas");
       celdatos2col3.setCellValue("Sumatoria Total S/.");
       celdatos2col4.setCellValue("Número de Ventas");
       celdatos2col5.setCellValue("Estado");


       
        try {
            while (result.next()) {
               
                descripcion=result.getString(2);
                fecha=result.getString(3);
                sumatoria=result.getString(4);
                numvtas=result.getString(5);
                estado=result.getString(6);

     
     /*  "N°","RUC","Descripción","Fecha de Boletas","Sumatoria Total S/.","Número de Ventas","Estado","E","fecE","rzonsocial",
       "enviar","hash","idRes","fRes","cdrnota"
       */
                     
               Row rown = sheet.createRow(cont+4);
               
               Cell celdatosncol0= rown.createCell(0);
               Cell celdatosncol1= rown.createCell(1);
               Cell celdatosncol2= rown.createCell(2);
               Cell celdatosncol3= rown.createCell(3);
               Cell celdatosncol4= rown.createCell(4);
               Cell celdatosncol5= rown.createCell(5);


               
               
               celdatosncol0.setCellStyle(style3);
               celdatosncol1.setCellStyle(style3);
               celdatosncol2.setCellStyle(style3);
               celdatosncol3.setCellStyle(style3);
               celdatosncol4.setCellStyle(style3);
               celdatosncol5.setCellStyle(style3);


       
               celdatosncol0.setCellValue(cont);
               celdatosncol1.setCellValue(descripcion);
               celdatosncol2.setCellValue(fecha);
               celdatosncol3.setCellValue(sumatoria);
               celdatosncol4.setCellValue(numvtas);
               celdatosncol5.setCellValue(estado);



               
               
               
               
               
               cont++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteEntradaStockDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
        sheet.setColumnWidth(0, 2500);
        sheet.setColumnWidth(1, 12500);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 12500);

        
       try {
           
           
           FileOutputStream fileout=new FileOutputStream(System.getProperty("user.home")+"/Desktop/ReporteResumenVentas.xlsx");
           book.write(fileout);
           fileout.close();
           
           JOptionPane.showMessageDialog(null, "Reporte creado y descargado en el escritorio con éxito, nombre: ReporteResumenVentas.xlsx");
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

        jLabel2 = new javax.swing.JLabel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbuTipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btnSunat = new javax.swing.JButton();
        btnVerVenta = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Resúmen de Boletas Electrónicas");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Desde:");

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
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de Venta:");

        cbuTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Enviadas a SUNAT", "No enviadas a SUNAT" }));
        cbuTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbuTipoActionPerformed(evt);
            }
        });

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
        tablaVentas.getTableHeader().setReorderingAllowed(false);
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestión de Resumen"));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Button-Refresh-icon.png"))); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSunat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSunat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/sunat1.png"))); // NOI18N
        btnSunat.setText("Enviar a SUNAT");
        btnSunat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSunatActionPerformed(evt);
            }
        });

        btnVerVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnVerVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Mimetypes-application-pdf-icon.png"))); // NOI18N
        btnVerVenta.setMnemonic('V');
        btnVerVenta.setText("Ver Ventas");
        btnVerVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentaActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimir.setMnemonic('I');
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        btnSalir.setMnemonic('S');
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Excel2.png"))); // NOI18N
        jButton5.setText("Exportar a Excel");
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
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(btnSunat)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btnSunat)
                    .addComponent(btnVerVenta)
                    .addComponent(btnSalir)
                    .addComponent(btnImprimir)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGap(0, 364, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbuTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaDesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaDesdeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesdeFocusLost

    private void txtFechaDesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaDesdePropertyChange
      cargarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesdePropertyChange

    private void txtFechaHastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaHastaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHastaFocusLost

    private void txtFechaHastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaHastaPropertyChange
        cargarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHastaPropertyChange

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
       cargarVentas();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbuTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbuTipoActionPerformed
       cargarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_cbuTipoActionPerformed

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        if (evt.getClickCount() == 1) {

           // btnCobrar.setEnabled(true);
           // btnOrdenPedido.setEnabled(true);

        } else {
            //modificar();
        }
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarVentas();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSunatActionPerformed
       enviarSunat();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSunatActionPerformed

    private void btnVerVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentaActionPerformed
       // visualizarVenta();
    }//GEN-LAST:event_btnVerVentaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ExportarExcel();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSunat;
    private javax.swing.JButton btnVerVenta;
    private javax.swing.JComboBox<String> cbuTipo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVentas;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    // End of variables declaration//GEN-END:variables
}
