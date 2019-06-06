/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.datasource.InventarioDataSource;
import jym.ferreteria.renders.LabelRenderer;
import jym.ferreteria.renders.LateralTableHeader;
import jym.ferreteria.renders.CustomTableModel;

/**
 *
 * @author Omr
 */
public class FrmRegistrarInventarioInicial extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private Map mapAlmacen = new HashMap();
    private Map mapUnidad = new HashMap();
    private boolean comboAlmacenListo = false;
    private boolean comboUnidadListo = false;
    private CustomTableModel model = new CustomTableModel() {
        private DecimalFormat format = new DecimalFormat("0.00");

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 12;
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            if (column == 12) {
                try {
                    double stock = Double.parseDouble(aValue.toString());
                    super.setValueAt(format.format(stock).replace(",", "."), row, column);
                    System.out.println(format.format(stock).replace(",", "."));
                } catch (Exception e) {
                    super.setValueAt("0.00", row, column);
                }
            } else {
                super.setValueAt(aValue, row, column);
            }

        }

    };

    /**
     * Creates new form IngresarInventarioInicial
     */
    public FrmRegistrarInventarioInicial() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        control.fillComboBox("SELECT `idAlmacen`, `nombre` FROM `almacen`;", cboAlmacen, mapAlmacen, "Por cada almacén");
        comboAlmacenListo = true;
        control.fillComboBox("SELECT * FROM `unidades` ORDER BY `nomuniddes`;", cboUnidad, mapUnidad, "Todos");
        comboUnidadListo = true;
        model.setColumnIdentifiers(new String[]{"N°", "ID", "idAlmacen","Código", "Producto","Aplicación","Marca", "Und. Medida", "Almacén", "Prec. Público", "Prec. x Mayor","Prec. Compra", "Stock","CodProd"});
        tablaInventario.setModel(model);
        tablaInventario.setDefaultRenderer(Object.class, new LabelRenderer());
        tablaInventario.getColumnModel().getColumn(0).setCellRenderer(new LateralTableHeader());
        tablaInventario.getColumnModel().getColumn(12).setCellRenderer(new LabelRenderer(SwingConstants.RIGHT));
        tablaInventario.getColumnModel().getColumn(9).setCellRenderer(new LabelRenderer(SwingConstants.RIGHT));
        tablaInventario.getColumnModel().getColumn(10).setCellRenderer(new LabelRenderer(SwingConstants.RIGHT));
        tablaInventario.getColumnModel().getColumn(11).setCellRenderer(new LabelRenderer(SwingConstants.RIGHT));
        control.hideTableColumn(tablaInventario, 1, 2,13);
        control.tableWidthColumn(tablaInventario, 50, 0);
        control.tableWidthColumn(tablaInventario, 300, 4);
        control.tableWidthColumn(tablaInventario, 200, 5);
        tablaInventario.getColumnModel().getColumn(0).setMaxWidth(50);
        model.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    actualizar(e);
                   
                }
            }

            private void actualizar(final TableModelEvent e) {
                
            
                SwingWorker worker = new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                         
                        
                        control.ejecutar(String.format("INSERT IGNORE INTO stock VALUES(%s,%s,%s);", model.getValueAt(e.getFirstRow(), 2), model.getValueAt(e.getFirstRow(), 1), 0));
                        control.ejecutar(String.format("UPDATE stock s SET s.`cantidadDisponible`=%s WHERE s.`idAlmacen`=%s AND s.`idProducto`=%s;", model.getValueAt(e.getFirstRow(), 12), model.getValueAt(e.getFirstRow(), 2), model.getValueAt(e.getFirstRow(), 1) ));
                        
                        
                        try {
                             control.ejecutar(String.format("CALL FechaProd(%s);",model.getValueAt(e.getFirstRow(), 1)));
                            FrmProductosBajosDeStock.cargarDatos();
                            
                        } catch (Exception e) {
                        }
                        return true;
                    }
                };
                worker.execute();
            }

        });
        cargarInventario();
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
        tablaInventario = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        cboUnidad = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cboAlmacen = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Ingresar Inventario inicial");

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

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaInventario.setName("tablaInventario"); // NOI18N
        tablaInventario.setRowSorter(null);
        tablaInventario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaInventario);

        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        cboUnidad.setName("cboUnidad"); // NOI18N
        cboUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUnidadActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Unidad:");
        jLabel2.setName("jLabel2"); // NOI18N

        cboAlmacen.setName("cboAlmacen"); // NOI18N
        cboAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAlmacenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1165, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cboAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnImprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
      cargarInventario();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
 //       imprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void cboUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUnidadActionPerformed
        if (comboUnidadListo) {
            cargarInventario();
        }
    }//GEN-LAST:event_cboUnidadActionPerformed

    private void cboAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAlmacenActionPerformed
        if (comboAlmacenListo) {
            cargarInventario();
        }
    }//GEN-LAST:event_cboAlmacenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboAlmacen;
    private javax.swing.JComboBox cboUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

//        private void cargarInventario() {
//            control.LlenarJTabla(model, "SELECT '',v.`ID`,a.`idAlmacen`, v.`Descripcion`, v.`Unidad` ,a.`nombre`, "
//                    + "IFNULL((SELECT  s2.`cantidadDisponible` FROM stock s2 WHERE s2.`idAlmacen`=a.`idAlmacen` AND s2.`idProducto`=v.`ID`),0.00) AS stock "
//                    + "FROM vta_catalogo v LEFT JOIN stock s ON v.`ID`=s.`idProducto` ,almacen a "
//                    + "WHERE v.`Descripcion` LIKE '%" + txtBuscar.getText() + "%' OR a.`nombre` LIKE '%" + txtBuscar.getText() + "%' "
//                    + "GROUP BY v.`ID`,a.`idAlmacen` ORDER BY  v.`Descripcion`, a.`idAlmacen`;", 7);
//        }
    private void cargarInventario() {
        String sql = "";
        String groupOrderBy = "";
        String extra = "";

        if (cboUnidad.getSelectedIndex() == 0) {//Todas las unidades
            extra = "";
        }else {//Por unidad en específico
            extra = " AND v.idUnidades=" + mapUnidad.get(cboUnidad.getSelectedIndex()).toString();
        }

        if (cboAlmacen.getSelectedIndex() == 0) {//Por cada almacén
            sql = "SELECT '',v.`ID`,a.`idAlmacen`,v.`Codigo` ,  v.`Producto`,v.`Descripcion`,v.`marca` , v.`Unidad` ,a.`nombre`,FORMAT(`Precxmenor`,2), FORMAT(`Precxmayor`,2),FORMAT(`precioCompra`,2), "
                    + "IFNULL((SELECT  s2.`cantidadDisponible` FROM stock s2 WHERE s2.`idAlmacen`=a.`idAlmacen` AND s2.`idProducto`=v.`ID`),0.00) AS stock , v.`Codigo`"
                    + "FROM vta_catalogo v LEFT JOIN stock s ON v.`ID`=s.`idProducto` ,almacen a "
                    + "WHERE (v.`Codigo` LIKE '%" + txtBuscar.getText() + "%' OR v.`Producto` LIKE '%" + txtBuscar.getText() + "%' OR v.`Descripcion` LIKE '%" + txtBuscar.getText() + "%' OR a.`nombre` LIKE '%" + txtBuscar.getText() + "%'"
                    + "OR v.`marca` LIKE '%" + txtBuscar.getText() + "%' or v.`Unidad` LIKE '%" + txtBuscar.getText() +"%')";
            extra += "";
            groupOrderBy = " GROUP BY v.`ID`,a.`idAlmacen` ORDER BY  v.`Producto`, a.`idAlmacen`;";
        } else {//Por almacén en específico
            sql = "SELECT '',v.`ID`,a.`idAlmacen`,v.`Codigo` ,  v.`Producto`,v.`Descripcion`,v.`marca` , v.`Unidad` ,a.`nombre`,FORMAT(`Precxmenor`,2), FORMAT(`Precxmayor`,2),FORMAT(`precioCompra`,2), "
                    + "IFNULL((SELECT  s2.`cantidadDisponible` FROM stock s2 WHERE s2.`idAlmacen`=a.`idAlmacen` AND s2.`idProducto`=v.`ID`),0.00) AS stock , v.`Codigo`"
                    + "FROM vta_catalogo v LEFT JOIN stock s ON v.`ID`=s.`idProducto` ,almacen a "
                    + "WHERE  (v.`Codigo` LIKE '%" + txtBuscar.getText() + "%' OR v.`Producto` LIKE '%" + txtBuscar.getText() + "%' OR v.`Descripcion` LIKE '%" + txtBuscar.getText() + "%' OR a.`nombre` LIKE '%" + txtBuscar.getText()+ "%'"
                    + "OR v.`marca` LIKE '%" + txtBuscar.getText() + "%' or v.`Unidad` LIKE '%" + txtBuscar.getText() +"%')";
            extra += " AND a.`idAlmacen`=" + mapAlmacen.get(cboAlmacen.getSelectedIndex()).toString();
            groupOrderBy = " GROUP BY v.`ID`,a.`idAlmacen` ORDER BY  v.`Producto`, a.`idAlmacen`;";
        }

        sql = sql + extra + groupOrderBy;
        control.LlenarJTabla(model, sql, 14);
    }

    /*private void imprimir() {
        Map map = new HashMap();
        map.put("almacen", cboAlmacen.getSelectedItem().toString());
        map.put("unidad", cboUnidad.getSelectedItem().toString());
    
        InventarioDataSource dataSource = new InventarioDataSource();
        for (int i = 0; i < model.getRowCount(); i++) {
                        dataSource.addInventario(new jym.ferreteria.datasource.Inventario(tablaInventario.getValueAt(i, 3).toString(), tablaInventario.getValueAt(i, 4).toString()+ ' '+tablaInventario.getValueAt(i, 5).toString(),tablaInventario.getValueAt(i, 6).toString(), tablaInventario.getValueAt(i, 7).toString(), tablaInventario.getValueAt(i, 8).toString(), tablaInventario.getValueAt(i, 9).toString(), tablaInventario.getValueAt(i, 10).toString(), tablaInventario.getValueAt(i, 12).toString()));
        }
        control.showReportDialog("Inventario de productos", "inventario", map, dataSource);
    }*/
}
