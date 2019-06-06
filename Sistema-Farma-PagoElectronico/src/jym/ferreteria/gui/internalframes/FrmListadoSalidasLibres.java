/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

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

import java.util.concurrent.*;
import static jym.ferreteria.clases.Controlador.Base;


import jym.ferreteria.clases.Accesos;


import jym.ferreteria.gui.dialogs.IniciarSesionAdmin;


public class FrmListadoSalidasLibres extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private CustomTableModel model = new CustomTableModel();
    
    private IniciarSesionAdmin iniciarSesionA = null;
    
    ResultSet rset;
    
    private String tipoUsu="";
    public boolean isAnulado=false;
    
    public FrmListadoSalidasLibres() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        cancelar();
        model.setColumnIdentifiers(new String[]{"id","N° de Salida", "Fecha","Hora", "Producto", "Lote", "Unidad", "Cantidad", "Motivo","Usuario"});
        tablaVentas.setModel(model);
        control.hideTableColumn(tablaVentas,0);
        control.tableWidthColumn(tablaVentas, 250, 4);
        control.tableWidthColumn(tablaVentas, 400,8);
        
        jButton2.setVisible(false);
        
        
        tablaVentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnImprimir.setEnabled(true);
                btnEliminar.setEnabled(true);
                jButton2.setEnabled(true);

              
            }
        });
        
         cargarSalidas();

        asignarPermisos();
    }
    
    private void cargarSalidas() {
        String text = txtBuscar.getText();
        control.Sql = "select r.idretiro, (@rownum:=@rownum+1) AS `posicion`, date_format(r.fecha,'%d/%m/%Y') as fecha, r.hora, concat(pre.present,' ',p.nomproducto), l.nombre,'Unidad', r.cantreal, r.motivo, u.nomusr \n" +
" from (SELECT @rownum:=0) ro, retiroproductos r \n" +
" inner join lote l on l.idlote=r.idlote \n" +
" inner join produto p on p.idproduto=l.idproducto \n" +
" inner join presentacion pre on pre.idpresentacion= p.idpresentacion \n" +
" left join usuario u on u.idusuario=r.iduser \n" +
" where p.nomproducto like '%"+text+"%' or concat(pre.present,' ',p.nomproducto) like '%"+text+"%' or pre.present like '%"+text+"%' order by r.idretiro desc;";
        System.out.println(control.Sql);
        //control.Sql = "SELECT v.`idVenta`, date_format(v.`fecha`,'%d/%m/%Y') as fecha, CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`) as comprobante, c.`nomcte`,FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`- v.`enObra`,2) AS SubTotal,v.`enObra` as En_Obra, FORMAT(v.`subTotalInafecto`+v.`subTotalAfecto`+ v.`igv`,2) AS total, v.`estado`,c.idcliente,c.dniRuc  FROM cliente c, (venta v LEFT JOIN comprobante co ON v.idComprobante=co.idComprobante) LEFT JOIN tipocomprobante t on co.`idTipoComprobante`=t.`idTipoComprobante` WHERE v.`idCliente`=c.`idCliente` AND (date_format(v.`fecha`,'%d/%m/%Y') LIKE '%"+txtBuscar.getText()+"%' OR t.`nombre` LIKE '%"+txtBuscar.getText()+"%' OR CONCAT(co.`serie`,' - ', co.`numero`) LIKE '%"+txtBuscar.getText()+"%' OR c.`nomcte` LIKE '%"+txtBuscar.getText()+"%') ORDER BY v.`fecha` desc,CONCAT(t.`nombre`,' N° ', co.`serie`,' - ' ,co.`numero`)desc;";
        control.LlenarJTabla(model, control.Sql, 10);
        btnImprimir.setEnabled(false);
        btnEliminar.setEnabled(false);
        jButton2.setEnabled(false);
    }
    
    public void asignarPermisos(){
        tipoUsu=Accesos.getInstance().getTipoUsuario();
    
    }
    
    public void cancelar(){
    
    btnEliminar.setEnabled(false);
    jButton2.setEnabled(false);
    btnImprimir.setEnabled(false);
    
    }
    
    private void nuevo() throws ParseException {
        FrmGestionProductos gestionProductos = new FrmGestionProductos(this,true);

        FrmMain.escritorio.add(gestionProductos, JLayeredPane.DEFAULT_LAYER);
        gestionProductos.setLocation((FrmMain.escritorio.getWidth() - gestionProductos.getWidth()) / 2, 0);
        gestionProductos.setVisible(true);
        
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
     
    private void eliminar() {
    if (tablaVentas.getSelectedRow() >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Al eliminar la Salida seleccionada se actualizará los almacenes con los productos contenidos en esta Salida."
                    + "\n¿Confirma que desea eliminar la Salida seleccionada?", "Mensaje", JOptionPane.YES_NO_OPTION) == 0) {
                
                int f = tablaVentas.getSelectedRow();
                
                String idSalida = model.getValueAt(f, 0).toString();
                
                control.Sql="select r.idretiro,l.idlote,r.cantreal, p.idproduto from retiroproductos r \n" +
                            " inner join lote l on l.idlote=r.idlote \n" +
                            " inner join produto p on p.idproduto=l.idproducto \n" +
                            " where r.idretiro='"+idSalida+"';";
                
                ResultSet detalleStockResult = control.DevolverRegistro(control.Sql);
                try {
                    while (detalleStockResult.next()) {
                        control.EditarRegistro(String.format("UPDATE stock s SET s.`cantidadDisponible`=s.`cantidadDisponible`+%d WHERE s.`idAlmacen`=%d AND s.`idProducto`=%d;", detalleStockResult.getInt(3), 1, detalleStockResult.getInt(4)));
                        control.CrearRegistro(String.format("update lote set  cant=cant+%d where idlote='%s';", detalleStockResult.getInt(3), detalleStockResult.getInt(2)));
                    }
                    
                    control.EditarRegistro(String.format("DELETE FROM retiroproductos WHERE `idretiro`='%s';", idSalida));

                    
                    try {
                        FrmProductosBajosDeStock.cargarDatos();
                    } catch (Exception e) {
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FrmRegistrarEntradasDeStok.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    JOptionPane.showMessageDialog(null, "La Salida de Productos Libre ha sido eliminada del sistema");
                    cargarSalidas();
                
                }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una Venta para eliminar");
        
            }
    }
    
    
    
    
        private void imprimir() {
     
            String idSalida = model.getValueAt(tablaVentas.getSelectedRow(), 0).toString();
            String numSalida = model.getValueAt(tablaVentas.getSelectedRow(), 1).toString();


            Map map = new HashMap();
            map.put("idSalida", idSalida);
            map.put("numSalida", numSalida);
            
             control.showReportDialog("Reporte de Salida Libre", "SalidaLibre", map);
           


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
        btnEliminar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listado de Salidas Libres de Productos");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Buscar: ");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion de Salidas"));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        jButton1.setMnemonic('N');
        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Files-New-File-icon.png"))); // NOI18N
        jButton2.setMnemonic('N');
        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(564, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(btnImprimir)
                    .addComponent(btnSalir))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1298, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtBuscar)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(498, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       cargarSalidas();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        if (evt.getClickCount() == 1) {

           // btnCobrar.setEnabled(true);
           // btnOrdenPedido.setEnabled(true);

        } else {
            //modificar();
        }
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            nuevo();
        } catch (ParseException ex) {
            Logger.getLogger(FrmListadoSalidasLibres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

/*        if(tipoUsu.equals("Superadministrador")||tipoUsu.equals("Administrador")){
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
        }*/

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
