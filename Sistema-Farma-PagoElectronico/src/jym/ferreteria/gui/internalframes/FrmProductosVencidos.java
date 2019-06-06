/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LabelRenderer;
import jym.ferreteria.renders.LabelRenderer;
import jym.ferreteria.renders.LateralTableHeader;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FrmProductosVencidos extends javax.swing.JInternalFrame {

   private static Controlador controlador = new Controlador();
    private static CustomTableModel model = new CustomTableModel();
    
    public FrmProductosVencidos() {
        initComponents();
        
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        model.setColumnIdentifiers(new String[]{"N°","Codigo", "Nombre","Rubro","Composición","Presentación","Laboratorio", "Almacén", "Lote", "Fecha de vencimiento"});
        jTable1.setModel(model);
         jTable1.setDefaultRenderer(Object.class, new LabelRenderer());
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new LateralTableHeader());
        controlador.tableWidthColumn(jTable1, 50, 0);
        controlador.tableWidthColumn(jTable1, 300, 2);
         controlador.tableWidthColumn(jTable1, 180, 3,4,5,6);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        controlador.tableMaxWidthColumn(jTable1, 100, 7, 8);
        controlador.tableMaxWidthColumn(jTable1, 150, 9);
       LabelRenderer labelRenderer = new LabelRenderer(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(9).setCellRenderer(labelRenderer);
        
        configFecha(false);
        cargarDatos();
        
    }
    
    public void configFecha(boolean b){
    jLabel4.setVisible(b);
    txtFechaDesde.setVisible(b);
    txtFechaHasta.setVisible(b);
    jButton6.setVisible(b);
    txtFechaDesde.setDate(null);
    txtFechaHasta.setDate(null);
            
    }
    

    public  static void cargarDatos() {
        String bus="";
     
        controlador.LlenarJTabla(model, "select l.idlote,p.codunid, p.nomproducto,  c.nomtip,p.composicion, pre.present, m.nommarc, a.nombre,l.nombre, date_format(l.fecven,'%d/%m/%Y') as fecha  \n" +
                    " from lote l \n" +
                    " inner join produto p on p.idproduto=l.idproducto \n" +
                    " inner join tipoproducto c on c.idtipoproducto=p.idtipoproducto \n" +
                    " inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
                    " inner join marca m on m.idmarca=p.idmarca \n" +
                    " inner join stock s on s.idproducto=p.idproduto \n" +
                    " inner join almacen a on a.idalmacen=s.idalmacen \n" +
                    " where l.fecven<curdate() and l.cant>0 \n" +
                    " group by l.idlote \n" +
                    " order by l.fecven;", 10);

    }
    
    public void verPorFechas(){
        String bus="";
        bus=txtBus.getText();
        
        String fec1=controlador.Formato_Amd(txtFechaDesde.getDate());
        String fec2=controlador.Formato_Amd(txtFechaHasta.getDate());
        
        String andfecha=" (l.fecven between '"+fec1+"' and '"+fec2+"') ";
        
        
         controlador.LlenarJTabla(model, "select l.idlote, p.codunid,p.nomproducto, c.nomtip,p.composicion, pre.present, m.nommarc, a.nombre,l.nombre, date_format(l.fecven,'%d/%m/%Y') as fecha  \n" +
                    " from lote l \n" +
                    " inner join produto p on p.idproduto=l.idproducto \n" +
                    " inner join tipoproducto c on c.idtipoproducto=p.idtipoproducto \n" +
                    " inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
                    " inner join marca m on m.idmarca=p.idmarca \n" +
                    " inner join stock s on s.idproducto=p.idproduto \n" +
                    " inner join almacen a on a.idalmacen=s.idalmacen \n" +
                    " where "+andfecha+" and l.cant>0 and (p.codUnid like '%"+bus+"%' or p.nomproducto like '%"+bus+"%' or m.nommarc like '%"+bus+"%') \n" +
                    " group by l.idlote \n" +
                    " order by l.fecven;", 10);
        
    
    }
    public  void cargarDatos1() {
        String bus="";
        bus=txtBus.getText();
        int busqueda=cbscriterio.getSelectedIndex();
        
        String andfecha="";
        if(busqueda==0){
            configFecha(false);
        andfecha=" l.fecven<curdate() ";
        }else if(busqueda==1){
            configFecha(false);
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 7 DAY) ";    
        }else if(busqueda==2){
            configFecha(false);
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 14 DAY) "; 
        }else if(busqueda==3){
            configFecha(false);
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 21 DAY) "; 
        }else if(busqueda==4){
            configFecha(false);
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 1 MONTH) "; 
        }else if(busqueda==5){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 2 MONTH) "; 
        }else if(busqueda==6){
            configFecha(false);
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 3 MONTH) "; 
        }else if(busqueda==7){
            configFecha(true);
            andfecha=" 1=1 ";
        }
        controlador.LlenarJTabla(model, "select l.idlote, p.codunid,p.nomproducto, c.nomtip,p.composicion, pre.present, m.nommarc, a.nombre,l.nombre, date_format(l.fecven,'%d/%m/%Y') as fecha  \n" +
                    " from lote l \n" +
                    " inner join produto p on p.idproduto=l.idproducto \n" +
                    " inner join tipoproducto c on c.idtipoproducto=p.idtipoproducto \n" +
                    " inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
                    " inner join marca m on m.idmarca=p.idmarca \n" +
                    " inner join stock s on s.idproducto=p.idproduto \n" +
                    " inner join almacen a on a.idalmacen=s.idalmacen \n" +
                    " where "+andfecha+" and l.cant>0 and (p.codUnid like '%"+bus+"%' or p.nomproducto like '%"+bus+"%' or m.nommarc like '%"+bus+"%') \n" +
                    " group by l.idlote \n" +
                    " order by l.fecven;", 10);

    }
    
     public  void ExportarExcel(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet=book.createSheet("Reporte Productos Vencidos");
        
        
        CellStyle style3 = book.createCellStyle();
        
        style3.setBorderBottom(BorderStyle.MEDIUM);
        style3.setBorderLeft(BorderStyle.MEDIUM);
        style3.setBorderRight(BorderStyle.MEDIUM);
        style3.setBorderTop(BorderStyle.MEDIUM);
        
      Row row0=sheet.createRow(1);
      Cell celda0=row0.createCell(2);
      celda0.setCellValue("Reporte de Productos Vencidos");
      //celda0.setCellStyle(style3);
      

    String bus="";
        bus=txtBus.getText();
        int busqueda=cbscriterio.getSelectedIndex();
        
        String andfecha="";
        String mensaje="";
        if(busqueda==0){
        andfecha=" l.fecven<curdate() ";
        mensaje="Productos Vencidos";
        }else if(busqueda==1){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 7 DAY) ";
        mensaje="Productos Vencidos y productos que vencen hasta en 1 semana";
        }else if(busqueda==2){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 14 DAY) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 2 semanas";
        }else if(busqueda==3){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 21 DAY) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 3 semanas";
        }else if(busqueda==4){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 1 MONTH) "; 
         mensaje="Productos Vencidos y productos que vencen hasta en 1 mes";
        }else if(busqueda==5){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 2 MONTH) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 2 meses";
        }else if(busqueda==6){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 3 MONTH) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 3 meses";
        }else if(busqueda==7){
        String fec1=controlador.Formato_Amd(txtFechaDesde.getDate());
        String fec2=controlador.Formato_Amd(txtFechaHasta.getDate());
        
        andfecha=" (l.fecven between '"+fec1+"' and '"+fec2+"') ";
        
        String fec1e=controlador.Formato_Amd2(txtFechaDesde.getDate());
        String fec2e=controlador.Formato_Amd2(txtFechaHasta.getDate());
        
        mensaje="Productos Vencidos entre el "+fec1e+" y el "+fec2e;
        }
        
        
        String sql="select l.idlote, p.codunid,p.nomproducto, c.nomtip,p.composicion, pre.present, m.nommarc, a.nombre,l.nombre, date_format(l.fecven,'%d/%m/%Y') as fecha  \n" +
                    " from lote l \n" +
                    " inner join produto p on p.idproduto=l.idproducto \n" +
                    " inner join tipoproducto c on c.idtipoproducto=p.idtipoproducto \n" +
                    " inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
                    " inner join marca m on m.idmarca=p.idmarca \n" +
                    " inner join stock s on s.idproducto=p.idproduto \n" +
                    " inner join almacen a on a.idalmacen=s.idalmacen \n" +
                    " where "+andfecha+" and l.cant>0 and (p.codUnid like '%"+bus+"%' or p.nomproducto like '%"+bus+"%' or m.nommarc like '%"+bus+"%') \n" +
                    " group by l.idlote \n" +
                    " order by l.fecven;";
        
        
       Row row2=sheet.createRow(3);
      Cell celda2=row2.createCell(1);
      celda2.setCellValue(mensaje);
        
       System.out.print(sql);
       ResultSet result = controlador.DevolverRegistro(sql);
       
       int cont=1;
       String idlote="";
       String codunid="";
       String nomproducto="";
       String nomtip="";
       String composicion="";
       String present="";
       String nommarc="";
       String nombreAlma="";
       String nombre="";
       String fecha="";;

       

       
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


       
       celdatos2col0.setCellStyle(style3);
       celdatos2col1.setCellStyle(style3);
       celdatos2col2.setCellStyle(style3);
       celdatos2col3.setCellStyle(style3);
       celdatos2col4.setCellStyle(style3);
       celdatos2col5.setCellStyle(style3);
       celdatos2col6.setCellStyle(style3);
       celdatos2col7.setCellStyle(style3);
       celdatos2col8.setCellStyle(style3);

       
       celdatos2col0.setCellValue("N°");
       celdatos2col1.setCellValue("Código");
       celdatos2col2.setCellValue("Producto");
       celdatos2col3.setCellValue("Rubro");
       celdatos2col4.setCellValue("Composición");
       celdatos2col5.setCellValue("Presentación");
       celdatos2col6.setCellValue("Laboratorio");
       celdatos2col7.setCellValue("Lote");
       celdatos2col8.setCellValue("Fecha de Vencimiento");

       
        try {
            while (result.next()) {
               
                idlote=result.getString(1);
                codunid=result.getString(2);
                nomproducto=result.getString(3);
                nomtip=result.getString(4);
                composicion=result.getString(5);
                present=result.getString(6);
                nommarc=result.getString(7);
                nombreAlma=result.getString(8);
                nombre=result.getString(9);
                fecha=result.getString(10);
     
                
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

               
               
               celdatosncol0.setCellStyle(style3);
               celdatosncol1.setCellStyle(style3);
               celdatosncol2.setCellStyle(style3);
               celdatosncol3.setCellStyle(style3);
               celdatosncol4.setCellStyle(style3);
               celdatosncol5.setCellStyle(style3);
               celdatosncol6.setCellStyle(style3);
               celdatosncol7.setCellStyle(style3);
               celdatosncol8.setCellStyle(style3);

       
               celdatosncol0.setCellValue(cont);
               celdatosncol1.setCellValue(codunid);
               celdatosncol2.setCellValue(nomproducto);
               celdatosncol3.setCellValue(nomtip);
               celdatosncol4.setCellValue(composicion);
               celdatosncol5.setCellValue(present);
               celdatosncol6.setCellValue(nommarc);
               celdatosncol7.setCellValue(nombre);
               celdatosncol8.setCellValue(fecha);


               
               
               
               
               
               cont++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteEntradaStockDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
        sheet.setColumnWidth(0, 2500);
        sheet.setColumnWidth(1, 12500);
        sheet.setColumnWidth(2, 6250);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);

        
       try {
           
           
           FileOutputStream fileout=new FileOutputStream(System.getProperty("user.home")+"/Desktop/ReporteProductosVencidos.xlsx");
           book.write(fileout);
           fileout.close();
           
           JOptionPane.showMessageDialog(null, "Reporte creado y descargado en el escritorio con éxito, nombre: ReporteProductosVencidos.xlsx");
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

        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtBus = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbscriterio = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lotes con Productos Vencidos");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Excel2.png"))); // NOI18N
        jButton5.setText("Exportar a Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtBus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Buscar: ");

        cbscriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Productos Vencidos", "Vencen en 01 semana", "Vencen en 02 semanas", "Vencen en 03 semanas", "Vencen en 01 mes", "Vencen en 02 meses", "Vencen en 03 meses", "Rango de Fecha" }));
        cbscriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbscriterioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha: ");

        txtFechaDesde.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtFechaDesdeInputMethodTextChanged(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search1.png"))); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbscriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(182, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbscriterio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtFechaDesde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFechaHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Map map = new HashMap();
        map.put("bus", txtBus.getText());
        
        int busqueda=cbscriterio.getSelectedIndex();
        String andfecha="";
        String mensaje="";
        if(busqueda==0){
        andfecha=" l.fecven<curdate() ";
        mensaje="Productos Vencidos";
        }else if(busqueda==1){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 7 DAY) ";
        mensaje="Productos Vencidos y productos que vencen hasta en 1 semana";
        }else if(busqueda==2){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 14 DAY) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 2 semanas";
        }else if(busqueda==3){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 21 DAY) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 3 semanas";
        }else if(busqueda==4){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 1 MONTH) "; 
         mensaje="Productos Vencidos y productos que vencen hasta en 1 mes";
        }else if(busqueda==5){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 2 MONTH) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 2 meses";
        }else if(busqueda==6){
        andfecha=" l.fecven<ADDDATE(curdate(), INTERVAL 3 MONTH) "; 
        mensaje="Productos Vencidos y productos que vencen hasta en 3 meses";
        }
        
        map.put("andfecha", andfecha);
         map.put("mensaje", mensaje);
        
        
        controlador.showReportDialog("Productos vencidos", "productosVencidos", map);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusKeyReleased
        cargarDatos1();     // TODO add your handling code here:
    }//GEN-LAST:event_txtBusKeyReleased

    private void txtBusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusKeyTyped

    private void cbscriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbscriterioActionPerformed
         cargarDatos1();       // TODO add your handling code here:
    }//GEN-LAST:event_cbscriterioActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ExportarExcel();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtFechaDesdeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtFechaDesdeInputMethodTextChanged
              // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesdeInputMethodTextChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    verPorFechas();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbscriterio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBus;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    // End of variables declaration//GEN-END:variables
}
