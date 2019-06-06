/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import static jym.ferreteria.clases.Controlador.Base;
import jym.ferreteria.gui.dialogs.AsignarUnidades;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LateralTableHeader;


public class FrmGestionProductos extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private Controlador control = new Controlador();
    private String idcont = "", iduni, idtipprd, idmar, idmod, producto, idProd, idPres;
    public static Map mapLinea = new HashMap();
    private boolean modoedicion = false;
    private final DecimalFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());
    private AsignarUnidades asignarU = null;
    
     private boolean auxLote=false;
     private boolean auxLote1=false;
    public FrmGestionProductos(FrmListadoSalidasLibres listadoSalidas, boolean auxG) throws ParseException {
        initComponents();
        cancelSalida();
        cancelGestCantidades();
        
         tCatalogoproductos.setModel(modelo);
         this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        modelo.setColumnIdentifiers(new String[]{"N°","Código","Producto" ,"Código Unidad", "Rubro", "Composición", "Presentación", "idProducto", "Laboratorio", "IDUnidades","Stock","Precio venta","Precio compra","Ganancia x Unid"});
        control.hideTableColumn(tCatalogoproductos, 9,1,7,11,12,13);
        tCatalogoproductos.getColumnModel().getColumn(2).setPreferredWidth(250);
         tCatalogoproductos.getColumnModel().getColumn(6).setPreferredWidth(200);
        tCatalogoproductos.getColumnModel().getColumn(0).setCellRenderer(new LateralTableHeader());
        tCatalogoproductos.getColumnModel().getColumn(0).setMaxWidth(30);
       control.tableMaxWidthColumn(tCatalogoproductos, 120, 4, 8,10);
       
       MostrarCatalogo();
       
       txtFecVen.setEditable(false);
       txtCantLote.setEditable(false);
       
       cbsLoteAux.setVisible(false);
       cbsLoteAux1.setVisible(false);
    }

   
    
    public void cancelSalida(){
        lblProducto.setText("");
        cbsLote.removeAllItems();
        txtFecVen.setText("");
        txtCantLote.setText("");
        txtCantidad.setText("");
        jTextArea1.setText("");
        jPanelSalida.setVisible(false);
    }
    
    public void cancelGestCantidades(){
        lblProducto1.setText("");
        cbsLote1.removeAllItems();
        //txtFecVen1.setText("");
        txtCantLote1.setText("");
        txtCantidad1.setText("");
        jPanel2.setVisible(false);
    }
    
    public void BuscarCatalogo() {
        control.Sql = "select p.idProduto,p.codprod, p.nomproducto, p.codUnid, c.nomtip, p.composicion, pre.present,p.codprod, m.nommarc, u.iddetuniprod, \n " +
"s.cantidadDisponible, format(p.precUnidad,2), format(p.precioCompra,2) ,format((p.precUnidad-p.precioCompra),2) \n " +
"from produto p \n " +
"inner join tipoproducto c on c.idTipoProducto=p.idTipoProducto \n " +
"inner join presentacion pre on pre.idPresentacion=p.idPresentacion \n " +
"inner join marca m on m.idmarca=p.idmarca \n " +
"inner join detunidprod u on u.idproducto=p.idproduto and idUnidades='1' \n " +
"inner join stock s on s.idProducto=p.idproduto and s.idAlmacen='1' where p.codprod like '%" + txBuscar.getText() + "%' or p.nomproducto like '%" + txBuscar.getText() + "%' or p.codUnid like '%" + txBuscar.getText() + "%' "
                + "or c.nomtip like '%" + txBuscar.getText() + "%' or m.nommarc LIKE '%" + txBuscar.getText() + "%';";
        control.LlenarJTabla(modelo, control.Sql, 14);
        //System.out.println(control.Sql);
    }

    public void MostrarCatalogo() {
        BuscarCatalogo();
    }
    
    public void gestionarSalidas() {
        control.fila = tCatalogoproductos.getSelectedRow();
        if (control.fila >= 0) {
            idProd = tCatalogoproductos.getValueAt(control.fila, 0).toString();
            idcont = tCatalogoproductos.getValueAt(control.fila, 1).toString();
            producto = tCatalogoproductos.getValueAt(control.fila, 2).toString();
            //JOptionPane.showMessageDialog(rootPane, "idProd: "+idProd+" Codigo: "+idcont+" producto: "+producto);
             this.cargarLote(idProd);
             lblProducto.setText(producto);
             txtCantLote.setEditable(false);
             jPanelSalida.setVisible(true);
             txtCantidad.setEnabled(true);
             txtCantidad.setEditable(true);
             txtCantidad.grabFocus();
          
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione el elemento para Gestionar Unidades");
            tCatalogoproductos.requestFocus();
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
    
    
    public void gestionarLotes(){
        control.fila = tCatalogoproductos.getSelectedRow();
        if (control.fila >= 0) {
            idProd = tCatalogoproductos.getValueAt(control.fila, 0).toString();
            idcont = tCatalogoproductos.getValueAt(control.fila, 1).toString();
            producto = tCatalogoproductos.getValueAt(control.fila, 2).toString();
            //JOptionPane.showMessageDialog(rootPane, "idProd: "+idProd+" Codigo: "+idcont+" producto: "+producto);
             this.cargarLote2(idProd);
             lblProducto1.setText(producto);
             jPanel2.setVisible(true);
             jTextField1.setEnabled(true);
             txtCantLote1.setEditable(false);
             txtCantidad1.setEditable(true);
            // txtCantidad1.grabFocus();
          
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione el elemento para Gestionar Unidades");
            tCatalogoproductos.requestFocus();
        }
    }
    
    
    private void cargarLote2(String idProd){
        auxLote1=false;
        this.cbsLote1.removeAllItems();
        this.cbsLoteAux1.removeAllItems();
        
        cbsLote1.addItem("Agregar Nuevo Lote");
        cbsLoteAux1.addItem("0");
        try {
            Base.rt = DevolverRegistro("SELECT idlote,nombre FROM lote l where activo='1' and idproducto='"+idProd+"' and cant>0 order by fecVen,idlote;");
            while (Base.rt.next()) {
                this.cbsLote1.addItem(Base.rt.getString(2));
                this.cbsLoteAux1.addItem(Base.rt.getString(1));
            }
            //cbo.setSelectedIndex(0);
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
        
        this.cbsLoteAux1.setEnabled(true);
        this.cbsLote1.setEnabled(true);
        auxLote1=true;
        cararDatosLote1();
    
    }
    
     private void cararDatosLote1(){
        int index=this.cbsLote1.getSelectedIndex();
        
        String Ven="0";
        
        if(index>0 && auxLote1==true){
            this.cbsLoteAux1.setSelectedIndex(index);
            String idLote=this.cbsLoteAux1.getSelectedItem().toString();
            try {
            Base.rt = DevolverRegistro("select idlote, nombre, fecIn, date_format(`fecVen`,'%d-%m-%Y') as fecha, idproducto, idDetalleEntradaStock, cant, activo, obs,if(curdate()>fecVen,1,0) from lote where idlote='"+idLote+"';");
            while (Base.rt.next()) {
                
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
String strFecha = Base.rt.getString(4);
Date fecha = null;
try {

fecha = formatoDelTexto.parse(strFecha);
this.jDateChooser1.setDate(fecha);

} catch (ParseException ex) {

ex.printStackTrace();

}
double cantLoteActual=Double.parseDouble(Base.rt.getString(7));
int cantLoteActualMostrar=(int)cantLoteActual;
                this.txtCantLote1.setText(String.valueOf(cantLoteActualMostrar));
                txtCantidad1.setText(String.valueOf(cantLoteActualMostrar));
                
                jTextField1.setText(Base.rt.getString(2));
                Ven=Base.rt.getString(10);
            }
            //cbo.setSelectedIndex(0);
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
        }
            
            if(Ven.equals("1")){
                 this.jDateChooser1.setForeground(Color.red);
                 this.jDateChooser1.setFont(new Font("Tahoma", Font.BOLD, 13));
            }else{
                this.jDateChooser1.setForeground(Color.black);
                 this.jDateChooser1.setFont(new Font("Tahoma", Font.PLAIN, 13));
            }
            txtCantidad1.grabFocus();
        }
        
        if(index==0 && auxLote1==true){
            jTextField1.setText("");
            jDateChooser1.setDate(new Date());
            txtCantLote1.setText("");
            txtCantidad1.setText("");
            jTextField1.grabFocus();
        }
    }
     
     public void retiroProductos(){
     
         int index=cbsLote.getSelectedIndex();
         
         if(index<0){
         JOptionPane.showMessageDialog(null, "El producto seleccionado no posee unidades registradas para poder ser retiradas");
         }
         else{
         String cant=txtCantidad.getText();
         
         if(cant.length()==0 || cant.equals("0")){
             JOptionPane.showMessageDialog(null, "Ingrese una cantidad de retiro válida");
             txtCantidad.grabFocus();
         }
         else{
             int cantR=Integer.parseInt(cant);
             double cantMaxAux=Double.parseDouble(txtCantLote.getText());
             int cantMax=(int)cantMaxAux;
             if(cantR>cantMax){
                 JOptionPane.showMessageDialog(null, "La cantidad de retiro no puede ser superior a la cantidad máxima del lote");
                    txtCantidad.grabFocus();
             }
             else{
             String motivo=jTextArea1.getText();
             if(motivo.length()==0){
                 JOptionPane.showMessageDialog(null, "Ingrese el motivo de retiro de productos");
                    jTextArea1.grabFocus();
             }
             else{
             String idProducto=this.idProd;
             String idLote=cbsLoteAux.getSelectedItem().toString();
             String idUser=Accesos.getInstance().getIdUsuario();
             
             this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s "
                                            + "WHERE s.`idProducto`=%s;",
                                            (cantR),
                                            idProducto));
             
              this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`=s.`cant`-%s "
                                            + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                                            (cantR),
                                            idLote,idProducto));
              
              int id = this.control.executeAndGetId(String.format("INSERT INTO retiroProductos VALUES(NULL,curdate(),'%s','%s','%s','%s',curtime());",
                                            motivo,idLote,idUser,cantR));
              cancelGestCantidades();
              cancelSalida();
              BuscarCatalogo();
              JOptionPane.showMessageDialog(null, "Se ha registrado el retiro de productos exitosamente");
             
             }
             
             }
         
         } }
     }
     
     public void ajustarDatos(){
         int index=cbsLote1.getSelectedIndex();
         
         if(index==0){
             String nomLote=jTextField1.getText();
             String fecha = this.control.Formato_Amd(this.jDateChooser1.getDate() == null ? new Date() : this.jDateChooser1.getDate());
             
             if(txtCantidad1.getText().length()==0){
                 JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida");
                 txtCantidad1.grabFocus();
             }
             else{
                    
             double cantLote=Double.parseDouble(txtCantidad1.getText());
             int cantRLote=(int)cantLote;
             String idProducto=this.idProd;
             
             if(nomLote.length()>0){
                 if(fecha.length()>0){
                     if(cantLote>=0){
                     
                     int id = this.control.executeAndGetId(String.format("INSERT INTO lote VALUES(NULL,'%s',curdate(),'%s','%s',null,'%s','1','');",
                                            nomLote,fecha,idProducto,cantRLote));
                     
                     this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%s "
                                            + "WHERE s.`idProducto`=%s;",
                                            (cantRLote),
                                            idProducto));
                     
              BuscarCatalogo();
              cancelGestCantidades();
              cancelSalida();
              JOptionPane.showMessageDialog(null, "Se ha registrado el nuevo Lote Exitosamente");
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Ingrese una cantidad no menor a cero");
                 txtCantidad1.grabFocus();
             }
                     
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Ingrese una fecha válida");
                 jDateChooser1.grabFocus();
             }
             }
             else{
                 JOptionPane.showMessageDialog(null, "Ingrese un nombre para el nuevo Lote");
                 jTextField1.grabFocus();
             }
         }}
         else{
             String nomLote=jTextField1.getText();
             String fecha = this.control.Formato_Amd(this.jDateChooser1.getDate() == null ? new Date() : this.jDateChooser1.getDate());
             
              if(txtCantidad1.getText().length()==0){
                 JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida");
                 txtCantidad1.grabFocus();
             }
             else{
                  
             double cantLote=Double.parseDouble(txtCantidad1.getText());
             int cantRLote=(int)cantLote;
             String idProducto=this.idProd;
             String idLote=cbsLoteAux1.getSelectedItem().toString();
             
             if(nomLote.length()>0){
                 if(fecha.length()>0){
                     if(cantLote>=0){
                         
                         double cantAntes=Double.parseDouble(txtCantLote1.getText());
                         int cantAntesR=(int)cantAntes;
                     
                     this.control.ejecutar(String.format("UPDATE lote s SET s.`cant`='%s', s.nombre='%s', s.fecVen='%s' "
                                            + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                                            (cantRLote), nomLote,fecha,
                                            idLote,idProducto));
                     
                     System.out.println(String.format("UPDATE lote s SET s.`cant`='%s', s.nombre='%s', s.fecVen='%s' "
                                            + "WHERE s.`idlote`=%s AND s.`idProducto`=%s;",
                                            (cantRLote), nomLote,fecha,
                                            idLote,idProducto));
                     
                     this.control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`-%s+%s "
                                            + "WHERE s.`idProducto`=%s;",
                                            (cantAntesR),(cantRLote),
                                            idProducto));
                     
              BuscarCatalogo();
              cancelGestCantidades();
              cancelSalida();
              JOptionPane.showMessageDialog(null, "Se ha registrado el nuevo Lote Exitosamente");
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Ingrese una cantidad no menor a cero");
                 txtCantidad1.grabFocus();
             }
                     
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Ingrese una fecha válida");
                 jDateChooser1.grabFocus();
             }
             }
             else{
                 JOptionPane.showMessageDialog(null, "Ingrese un nombre para el nuevo Lote");
                 jTextField1.grabFocus();
             }
             
              
              }
             
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

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCatalogoproductos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanelSalida = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbsLote = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtFecVen = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCantLote = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cbsLoteAux = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtCantLote1 = new javax.swing.JTextField();
        txtCantidad1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblProducto1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbsLote1 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        cbsLoteAux1 = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de Lotes y Salidas de Productos");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N

        tCatalogoproductos.setForeground(new java.awt.Color(0, 51, 102));
        tCatalogoproductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tCatalogoproductos.getTableHeader().setReorderingAllowed(false);
        tCatalogoproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tCatalogoproductosMouseClicked(evt);
            }
        });
        tCatalogoproductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCatalogoproductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tCatalogoproductos);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Buscar: ");

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txBuscar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestionar"));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Login.png"))); // NOI18N
        jButton1.setText("Salida de Productos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Actions-document-edit-icon.png"))); // NOI18N
        jButton2.setText("Ajustar  Lotes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanelSalida.setBorder(javax.swing.BorderFactory.createTitledBorder("Salida de Productos"));

        jLabel1.setText("Producto:");

        lblProducto.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(18, 22, 82));
        lblProducto.setText(" ");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Lote");

        cbsLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsLoteActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Fecha Vencimiento");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Cant. Unidades");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText(" Por Lote:");

        txtCantLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantLoteActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad de Salida:");

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCantidad.setText("1");
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

        jLabel2.setText("Motivo de la Salida");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        jButton5.setText("Aceptar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/next-icon.png"))); // NOI18N
        jButton7.setText("Cerrar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSalidaLayout = new javax.swing.GroupLayout(jPanelSalida);
        jPanelSalida.setLayout(jPanelSalidaLayout);
        jPanelSalidaLayout.setHorizontalGroup(
            jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidaLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbsLote, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(txtFecVen, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanelSalidaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantLote, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalidaLayout.createSequentialGroup()
                .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelSalidaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSalidaLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanelSalidaLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(cbsLoteAux, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
        );
        jPanelSalidaLayout.setVerticalGroup(
            jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblProducto))
                .addGap(24, 24, 24)
                .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanelSalidaLayout.createSequentialGroup()
                        .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtCantLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecVen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(cbsLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanelSalidaLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbsLoteAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajustar Lotes"));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Lote");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Cant. Unidades");

        txtCantLote1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantLote1ActionPerformed(evt);
            }
        });

        txtCantidad1.setEditable(false);
        txtCantidad1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCantidad1.setText("1");
        txtCantidad1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCantidad1CaretUpdate(evt);
            }
        });
        txtCantidad1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidad1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidad1FocusLost(evt);
            }
        });
        txtCantidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad1ActionPerformed(evt);
            }
        });
        txtCantidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidad1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidad1KeyTyped(evt);
            }
        });

        jLabel3.setText("Producto:");

        lblProducto1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        lblProducto1.setForeground(new java.awt.Color(18, 22, 82));
        lblProducto1.setText(" ");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Cantidad Real Ajustar:");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Por Lote:");

        cbsLote1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsLote1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Fecha Vencimiento");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/next-icon.png"))); // NOI18N
        jButton10.setText("Cerrar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        jButton8.setText("Ajustar Datos");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre de Lote:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbsLote1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbsLoteAux1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel22)))
                                .addGap(18, 18, 18)
                                .addComponent(txtCantLote1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(209, 209, 209)
                                        .addComponent(jButton8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblProducto1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cbsLote1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel19)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel22))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel9)
                                            .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtCantLote1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbsLoteAux1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tCatalogoproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCatalogoproductosMouseClicked
        if (evt.getClickCount() == 2) {
           // VerdatosCatalogo();
        }
    }//GEN-LAST:event_tCatalogoproductosMouseClicked

    private void tCatalogoproductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCatalogoproductosKeyReleased

    }//GEN-LAST:event_tCatalogoproductosKeyReleased

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        BuscarCatalogo();
    }//GEN-LAST:event_txBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    gestionarSalidas(); 
    cancelGestCantidades();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    gestionarLotes();
    cancelSalida();
jTextField1.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbsLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsLoteActionPerformed
        cararDatosLote();        // TODO add your handling code here:
    }//GEN-LAST:event_cbsLoteActionPerformed

    private void txtCantLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantLoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantLoteActionPerformed

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate

    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
       /* if (this.txtPrecio.getText().trim().length() == 0) {
            this.txtPrecio.grabFocus();
        }*/
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
/*        double costoProducto = 0;
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
        }*/
        //txtTotal.setText(control.decimalFormat(costoProducto * cantidad));
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
       /* if ((evt.getKeyChar() == KeyEvent.VK_ENTER) && (this.txtCantidad.getText().trim().length() > 0)) {
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
        }*/
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        //this.calcularCostoPorProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        control.Solonumeros(evt);
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void cbsLote1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsLote1ActionPerformed
    cararDatosLote1();        // TODO add your handling code here:
    }//GEN-LAST:event_cbsLote1ActionPerformed

    private void txtCantLote1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantLote1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantLote1ActionPerformed

    private void txtCantidad1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidad1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1CaretUpdate

    private void txtCantidad1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidad1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1FocusGained

    private void txtCantidad1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidad1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1FocusLost

    private void txtCantidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1ActionPerformed

    private void txtCantidad1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1KeyPressed

    private void txtCantidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1KeyReleased

    private void txtCantidad1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    cancelGestCantidades();
    cancelSalida();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    cancelSalida();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    cancelGestCantidades();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    
        if (JOptionPane.showConfirmDialog(null, "Al registrar la salida se actualizará los almacenes con el producto y la cantidad seleccionada "
                    + "\n¿Confirma que desea registrar la Salida del Prodcuto?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
       retiroProductos();  
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
if (JOptionPane.showConfirmDialog(null, "Al ajustar los datos del Lote, o crear un lote nuevo, se actualizará los almacenes con el producto y la cantidad seleccionada "
                    + "\n¿Confirma que desea realizar el ajuste o creación de Lotes?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
    ajustarDatos();

}
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbsLote;
    private javax.swing.JComboBox<String> cbsLote1;
    private javax.swing.JComboBox<String> cbsLoteAux;
    private javax.swing.JComboBox<String> cbsLoteAux1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelSalida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProducto1;
    private javax.swing.JTable tCatalogoproductos;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txtCantLote;
    private javax.swing.JTextField txtCantLote1;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtFecVen;
    // End of variables declaration//GEN-END:variables
}
