/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.datasource.DetalleTopProductos;
import jym.ferreteria.datasource.TopProductos;
import jym.ferreteria.gui.dialogs.SeleccionarProductoRegularizacionDialog;
import jym.ferreteria.gui.dialogs.SeleccionarProveedorDialog;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
public class FrmTopProductosVendidos extends javax.swing.JInternalFrame {


   private Controlador control = new Controlador();
    private SeleccionarProveedorDialog seleccionarProveedor = null;

    private SeleccionarProductoRegularizacionDialog seleccionarProductoRegularizacion;
    private String idProducto;
    
    
    public FrmTopProductosVendidos() {
        initComponents();
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        
        control.LlenarComboTodos(cboPresent, "select * from presentacion order by present;", 2);
    }
    
    public  void ExportarExcel(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet=book.createSheet("Reporte");
        
        
        CellStyle style3 = book.createCellStyle();
        
        style3.setBorderBottom(BorderStyle.MEDIUM);
        style3.setBorderLeft(BorderStyle.MEDIUM);
        style3.setBorderRight(BorderStyle.MEDIUM);
        style3.setBorderTop(BorderStyle.MEDIUM);
        
      Row row0=sheet.createRow(1);
      Cell celda0=row0.createCell(2);
      celda0.setCellValue("Reporte Top de Productos Más Vendidos");
      //celda0.setCellStyle(style3);
      
      String pre=cboPresent.getSelectedItem().toString();
      //Date fec1=txtFechaDesde.getDate();
      //Date fec2=txtFechaDesde.getDate();
      String fechas="Venta de Todos los Tiempos";
      String presentacion=" ";
      String sql=" ";
      
      if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {
      
      if(cboPresent.getSelectedIndex()>0)
        {
        presentacion=" where pre.present='"+cboPresent.getSelectedItem().toString()+"' "; 
        }
        
        sql = "select p.idproduto, concat(pre.present,' ',p.nomproducto) as producto, m.nommarc, sum(dv.cantReal*dv.cantidad) as cantidad, \n" +
" count(v.idventa) as ventas \n" +
" from  produto p \n" +
" inner join marca m on m.idmarca=p.idmarca \n" +
" inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
" inner join detalleventa dv on dv.idproducto=p.idproduto \n" +
" inner join venta v on v.idventa=dv.idventa and v.estado<>'Anulado' \n" +
" "+presentacion+" group by p.idproduto order by 4 desc ,5 desc;";
       
      }
      else{
          
          fechas = "Ventas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();
          
          if(cboPresent.getSelectedIndex()>0)
        {
        presentacion=" and pre.present='"+cboPresent.getSelectedItem().toString()+"' ";
        }
                
                sql = "select p.idproduto, concat(pre.present,' ',p.nomproducto) as producto, m.nommarc, sum(dv.cantReal*dv.cantidad) as cantidad, \n" +
" count(v.idventa) as ventas \n" +
" from  produto p \n" +
" inner join marca m on m.idmarca=p.idmarca \n" +
" inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
" inner join detalleventa dv on dv.idproducto=p.idproduto \n" +
" inner join venta v on v.idventa=dv.idventa and v.estado<>'Anulado' \n" 
                        + " where (v.`fecha` between '" + control.Formato_Amd(txtFechaDesde.getDate()) + "'and '" + control.Formato_Amd(txtFechaHasta.getDate()) + "')"
                        + " "+presentacion+" group by p.idproduto order by 4 desc ,5 desc;  ";
      
      }
      
        Row row1 = sheet.createRow(3);
       
       Cell celdatos1col1 = row1.createCell(0);
       celdatos1col1.setCellValue("Fechas:");
       
       Cell celdatos1col2 = row1.createCell(1);
       celdatos1col2.setCellValue(fechas);
       
       Cell celdatos1col5 = row1.createCell(3);
       celdatos1col5.setCellValue("Presentación:");
       
       Cell celdatos1col6 = row1.createCell(4);
       celdatos1col6.setCellValue(pre);
       
       System.out.print(sql);
       ResultSet result = control.DevolverRegistro(sql);
       
       int cont=1;
       String idProd="";
       String producto="";
       String laboratorio="";
       int numVendidas=0;
       int numVtas=0;
       

       
       Row rowt = sheet.createRow(4);
       
       Cell celdatos2col0= rowt.createCell(0);
       Cell celdatos2col1= rowt.createCell(1);
       Cell celdatos2col2= rowt.createCell(2);
       Cell celdatos2col3= rowt.createCell(3);
       Cell celdatos2col4= rowt.createCell(4);
       
       celdatos2col0.setCellStyle(style3);
       celdatos2col1.setCellStyle(style3);
       celdatos2col2.setCellStyle(style3);
       celdatos2col3.setCellStyle(style3);
       celdatos2col4.setCellStyle(style3);
       
       celdatos2col0.setCellValue("N°");
       celdatos2col1.setCellValue("Producto");
       celdatos2col2.setCellValue("Laboratorio");
       celdatos2col3.setCellValue("N° Unidades Vendidas");
       celdatos2col4.setCellValue("N° Ventas del Producto");
       
        try {
            while (result.next()) {
                idProd=result.getString(1);
                producto=result.getString(2);
                laboratorio=result.getString(3);
                numVendidas=result.getInt(4);
                numVtas=result.getInt(5);
                
               Row rown = sheet.createRow(cont+4);
               
               Cell celdatosncol0= rown.createCell(0);
               Cell celdatosncol1= rown.createCell(1);
               Cell celdatosncol2= rown.createCell(2);
               Cell celdatosncol3= rown.createCell(3);
               Cell celdatosncol4= rown.createCell(4);
               
               celdatosncol0.setCellStyle(style3);
               celdatosncol1.setCellStyle(style3);
               celdatosncol2.setCellStyle(style3);
               celdatosncol3.setCellStyle(style3);
               celdatosncol4.setCellStyle(style3);
       
               celdatosncol0.setCellValue(cont);
               celdatosncol1.setCellValue(producto);
               celdatosncol2.setCellValue(laboratorio);
               celdatosncol3.setCellValue(numVendidas);
               celdatosncol4.setCellValue(numVtas);
               
               
               
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
        
       try {
           
           
           FileOutputStream fileout=new FileOutputStream(System.getProperty("user.home")+"/Desktop/TopProductosVendidos.xlsx");
           book.write(fileout);
           fileout.close();
           JOptionPane.showMessageDialog(null, "Reporte creado y descargado en el escritorio con éxito, nombre: TopProductosVendidos.xlsx");
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FrmTopProductosVendidos.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(FrmTopProductosVendidos.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }

    
        private void cargarDatos() {
        Map map = new HashMap();

        TopProductos dataSource = new TopProductos();

        String sql = "";
        String fechas = "";
        String presentacion="";
        String pre=cboPresent.getSelectedItem().toString();
        
        
        //producto vacio

            if (txtFechaDesde.getDate() == null || txtFechaHasta.getDate() == null) {//sin fechas

                if(cboPresent.getSelectedIndex()>0)
        {
        presentacion=" where pre.present='"+cboPresent.getSelectedItem().toString()+"' ";
        }
                
                sql = "select p.idproduto, concat(pre.present,' ',p.nomproducto) as producto, m.nommarc, sum(dv.cantReal*dv.cantidad) as cantidad, \n" +
" count(v.idventa) as ventas \n" +
" from  produto p \n" +
" inner join marca m on m.idmarca=p.idmarca \n" +
" inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
" inner join detalleventa dv on dv.idproducto=p.idproduto \n" +
" inner join venta v on v.idventa=dv.idventa and v.estado<>'Anulado' \n" +
" "+presentacion+" group by p.idproduto order by 4 desc ,5 desc;";
            } else {//con fechas
                
                if(cboPresent.getSelectedIndex()>0)
        {
        presentacion=" and pre.present='"+cboPresent.getSelectedItem().toString()+"' ";
        }
                
                sql = "select p.idproduto, concat(pre.present,' ',p.nomproducto) as producto, m.nommarc, sum(dv.cantReal*dv.cantidad) as cantidad, \n" +
" count(v.idventa) as ventas \n" +
" from  produto p \n" +
" inner join marca m on m.idmarca=p.idmarca \n" +
" inner join presentacion pre on pre.idpresentacion=p.idpresentacion \n" +
" inner join detalleventa dv on dv.idproducto=p.idproduto \n" +
" inner join venta v on v.idventa=dv.idventa and v.estado<>'Anulado' \n" 
                        + " where (v.`fecha` between '" + control.Formato_Amd(txtFechaDesde.getDate()) + "'and '" + control.Formato_Amd(txtFechaHasta.getDate()) + "')"
                        + " "+presentacion+" group by p.idproduto order by 4 desc ,5 desc;  ";
                fechas = "Ventas Desde: " + ((JTextField) txtFechaDesde.getDateEditor()).getText() + " Hasta: " + ((JTextField) txtFechaHasta.getDateEditor()).getText();

            }
       
        map.put("fechas", fechas);
        map.put("present", pre);
        ResultSet result = control.DevolverRegistro(sql);

        try {
            while (result.next()) {

                dataSource.addDetalleTopProductos(new DetalleTopProductos(result.getString(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5)));
            }
            control.showReportDialog("Reporte Top deProductos Vendidos", "reporteTopProductos", map, dataSource);
        } catch (SQLException ex) {
            Logger.getLogger(FrmReporteEntradaStockDetalle.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboPresent = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Reporte de Productos Más Vendidos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Desde: ");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-edit-delete-icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("hasta:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Presentación:");

        cboPresent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPresentItemStateChanged(evt);
            }
        });
        cboPresent.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboPresentPopupMenuWillBecomeVisible(evt);
            }
        });
        cboPresent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboPresentKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addComponent(cboPresent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPresent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Excel2.png"))); // NOI18N
        jButton5.setText("Exportar a Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        jButton1.setMnemonic('I');
        jButton1.setText("Imprimir");
        jButton1.setPreferredSize(new java.awt.Dimension(114, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        jButton4.setMnemonic('C');
        jButton4.setText("Cancelar");
        jButton4.setPreferredSize(new java.awt.Dimension(114, 33));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton3.setMnemonic('S');
        jButton3.setText("Salir");
        jButton3.setPreferredSize(new java.awt.Dimension(90, 33));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        txtFechaDesde.setDate(null);
        txtFechaHasta.setDate(null);


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cboPresentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPresentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPresentItemStateChanged

    private void cboPresentPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboPresentPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPresentPopupMenuWillBecomeVisible

    private void cboPresentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPresentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPresentKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    ExportarExcel();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cboPresent;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    // End of variables declaration//GEN-END:variables
}
