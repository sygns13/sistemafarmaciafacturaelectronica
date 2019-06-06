/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CargarCotizacion.java
 *
 * Created on 31-ene-2014, 10:50:29
 */
package jym.ferreteria.gui.internalframes;
//graficos de utilidad neta
//ver productos en ventas, compras
//cancelar venta de producto

import jym.ferreteria.clases.*;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.gui.internalframes.FrmRegistrarVenta;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Silva
 */
public class CargarCotizacion extends javax.swing.JInternalFrame {

    CustomTableModel modelo = new CustomTableModel();
    Controlador control = new Controlador();
    InfoGeneral info = new InfoGeneral();
    FrmRegistrarVenta formVentas = null;
    String[] alamcenes=new String[10];
    String Cliente;
    double con = 0;
    String[] datos = new String[11];
    int contador=0;
    /**
     * Creates new form CargarCotizacion
     */
    public CargarCotizacion() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource("/jym/ferreteria/imagenes/icoChiqui.png")));
        tCotizacion.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"Orden", "N° Cotización", "Fecha Cotizacion", "Fecha Duracion Cotizacion", "Cliente"});
       control.hideTableColumn(tCotizacion, 0);
        tCotizacion.getColumnModel().getColumn(0).setPreferredWidth(50);
        tCotizacion.getColumnModel().getColumn(1).setPreferredWidth(70);
        tCotizacion.getColumnModel().getColumn(2).setPreferredWidth(70);
        tCotizacion.getColumnModel().getColumn(3).setPreferredWidth(60);
        tCotizacion.getColumnModel().getColumn(4).setPreferredWidth(200);
        MostrarProductos();
    }

    public void MostrarProductos() {
        BuscarProductos();
    }

    public void BuscarProductos() {
        control.Sql = "SELECT idcliente,idcotizacion, fec_ctz, adddate(fec_ctz,diasdur) as fec_fin ,nomclie  FROM impri_cotizacion i  where  nomclie like'"
                + txtBuscar.getText() + "%' group by idcotizacion order by idcotizacion desc";
        control.LlenarJTabla(modelo, control.Sql, 5);

        //    JOptionPane.showMessageDialog(null,control.Sql);
    }

    public void LimTabla() {
        while (formVentas.getModeloDetalle().getRowCount() > 0) {
            formVentas.getModeloDetalle().removeRow(0);
        }
    }
public void llnaralmacen(){
    try {
        contador=0;
        control.Base.Conectar();
        control.Sql="select * from almacen";
        control.Base.st=control.Base.conec.createStatement();
        control.Base.rt=control.Base.st.executeQuery(control.Sql);
        while (control.Base.rt.next()) {
            alamcenes[contador]=control.Base.rt.getString(1);
            contador++;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void Mensaje(String us) {
        try {
            LimTabla();
            control.Sql = "select '',p.idproduto, p.codProd, concat(p.nomproducto,' ',m.nommarc), d.Unidad,d.cantidad, d.precio,(d.cantidad*d.precio),1,d.CantReal, 'Almacen 1' \n" +
                            " from cotizacion c \n" +
                            " inner join detallecotizacion d on c.idcotizacion=d.idcotizacion \n" +
                            " inner join produto p on p.idproduto=d.idproducto \n" +
                            " inner join marca m on m.idmarca=p.idmarca \n" +
                            " where c.idcotizacion='"+us+"';";
            System.out.println(control.Sql);
            ResultSet res = control.DevolverRegistro(control.Sql);
            String mensaje = "";
            //int cont=0;
            while (res.next()) {
                System.out.println("paso aqui");
                double cant=res.getDouble(6);
                int cantidadR = res.getInt(10);
                double stock = 0;
                String idal="",nomal="";
                llnaralmacen();
                for (int i = 0; i < contador; i++) {
                    control.Sql = "SELECT s.`cantidadDisponible` ,a.idalmacen,nombre FROM vta_catalogo v, stock s, almacen a "
                            + "WHERE a.`idAlmacen`=s.`idAlmacen` AND v.`ID`=s.`idProducto` and a.idalmacen='"+alamcenes[i]+"' "
                            + "and v.`ID`='" + res.getString(2) + "'";
                System.out.println(control.Sql+"---->aqio");
                    
                    String c = control.DevolverRegistroDto(control.Sql, 1);
                    idal = control.DevolverRegistroDto(control.Sql, 2);
                    nomal = control.DevolverRegistroDto(control.Sql, 3);
                   // System.out.println(c);
                    
                    if (c.length() > 0) {
                        stock = Double.parseDouble(c);
                    }
                    if(stock>=(cantidadR*cant)){
                        break;
                    }
                }
                
                
                double cantidadReal = 0;
                if (stock >= (cantidadR*cant)) {
                    cantidadReal = (cantidadR*cant);
                } else {
                    cantidadReal = stock;
                    cant=0;
                    mensaje += "Stock insuficiente para: " + res.getString(4) + " Stock disponible= " + stock + ", Cantidad solicitada= " + (cantidadR*cant) + " \n";
                }
                    if (cantidadReal > 0) {
//JOptionPane.showMessageDialog(rootPane,res.getString(1)+" - "+res.getString(2)+" - "+res.getString(3)+" - "+res.getString(4)+" - "+res.getString(5)+" - "+res.getString(6)+" - "+res.getString(7)+" - "+res.getString(8)+" - "+res.getString(9)+" - "+res.getString(10)+" - "+res.getString(11)+" - "+res.getString(12));
                    datos[0] = res.getString(1);
                    datos[1] = res.getString(2);
                    datos[2] = res.getString(3);
                   // datos[3] = nomal;
                    datos[3] = res.getString(4);
                    datos[4] = res.getString(5);
                    datos[5] = "" + cant;
                    
                    datos[6] = control.decimalFormat(Double.parseDouble(res.getString(7)));
                    datos[7] = control.decimalFormat(Double.parseDouble(res.getString(8)));
                    datos[8] = res.getString(9);
                    //datos[9] = res.getString(10);
                    datos[9] = res.getString(10);
                    datos[10] = res.getString(11);
        
                   
                    //"Id","idProd", "Código Prod", "Descripción producto", "Unidad", "Cantidad", "Precio", "Total", "idAlmacen","CantReal","Almacen";
                   //  "Id", "Código Prod", "Descripción producto", "Almacén", "Cantidad", "Precio", "Desc.", "Total", "Afecto", "idAlmacen", "Costo compra", "prec men", "prec may"
                         
                    con = con + Double.parseDouble(datos[6]);
                    
                    
                    formVentas.getModeloDetalle().addRow(datos);

                }

            }
            
            
            
           
//            FrmRegistrarVenta.txtTotalVenta.setText("" + con);
//            FrmRegistrarVenta.txtSubTotalInafecto.setText("" + con);
            FrmRegistrarVenta.buscarProducto();
            if (mensaje.length() > 0) {
                JOptionPane.showMessageDialog(this, mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCotizacion = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cotizaciones");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Buscar:");
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tCotizacion.setForeground(new java.awt.Color(0, 51, 102));
        tCotizacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tCotizacion.setName("tCotizacion"); // NOI18N
        tCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tCotizacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tCotizacion);

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        jButton1.setText("Seleccionar");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.setName("jButton2"); // NOI18N
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (tCotizacion.getSelectedRowCount() > 0) {
            formVentas.eliminartodo();
            Cliente = modelo.getValueAt(tCotizacion.getSelectedRow(), 4).toString();
            formVentas.txtCliente.setText(Cliente); //Mensaje(Cliente);
            formVentas.btnCobrar.setEnabled(true); //(activarBotonCobrar);
           Mensaje(modelo.getValueAt(tCotizacion.getSelectedRow(), 1).toString());
            info.setPass(modelo.getValueAt(tCotizacion.getSelectedRow(), 0).toString());
            formVentas.agregarProductoCotizacion(modelo.getValueAt(tCotizacion.getSelectedRow(), 0).toString(),modelo.getValueAt(tCotizacion.getSelectedRow(), 1).toString());
            dispose();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tCotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCotizacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tCotizacionMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
    MostrarProductos();
    }//GEN-LAST:event_txtBuscarKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tCotizacion;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    void setFrmVenta(FrmRegistrarVenta frmVenta) {
        formVentas=frmVenta;
    }
}
