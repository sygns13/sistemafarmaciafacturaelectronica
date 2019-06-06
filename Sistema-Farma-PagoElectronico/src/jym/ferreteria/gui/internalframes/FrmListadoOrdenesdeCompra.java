/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.gui.FrmMain;
import jym.ferreteria.renders.CustomTableModel;

public class FrmListadoOrdenesdeCompra extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();

    private CustomTableModel modelo = new CustomTableModel();
    private String numOrden, IDorden, estado;

    public FrmListadoOrdenesdeCompra() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tOrden.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"ID", "Numero", "Fecha", "Proveedor", "Estado"});
        control.hideTableColumn(tOrden, 0);
        control.tableWidthColumn(tOrden, 400, 3);
        tOrden.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnImprimir.setEnabled(true);
                btnGenerarEntrada.setEnabled(true);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });
        cargarOrdenes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGenerarEntrada = new javax.swing.JButton();
        txtBuscarOrden = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tOrden = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Listado de pedidos a proveedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        btnImprimir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Print.png"))); // NOI18N
        btnImprimir.setMnemonic('I');
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Document.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGenerarEntrada.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGenerarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Full basket.png"))); // NOI18N
        btnGenerarEntrada.setMnemonic('G');
        btnGenerarEntrada.setText("Generar Entrada");
        btnGenerarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnImprimir)
                    .addComponent(btnSalir)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnGenerarEntrada))
                .addContainerGap())
        );

        txtBuscarOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarOrdenKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Buscar");

        tOrden.setAutoCreateRowSorter(true);
        tOrden.setModel(new javax.swing.table.DefaultTableModel(
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
        tOrden.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tOrden.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tOrden);

        jCheckBox1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBox1.setText("Por entregar");
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarOrden)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        control.fila = tOrden.getSelectedRow();
        if (control.fila >= 0) {
            String idOrdenCompra = tOrden.getValueAt(control.fila, 0).toString();
            Map map = new HashMap();
            map.put("idOrdenDeCompra", idOrdenCompra);
            control.showReportDialog("Órden de compra", "OrdendeCompra", map);
        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtBuscarOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarOrdenKeyReleased
        cargarOrdenes();
    }//GEN-LAST:event_txtBuscarOrdenKeyReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarOrden();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       FrmRegistrarOrdenDeCompra or = new FrmRegistrarOrdenDeCompra();
            or.setListadoOrdenesdeCompra(this);
            or.setOrdendeCompra(null);
            FrmMain.escritorio.add(or, JLayeredPane.DEFAULT_LAYER);
            or.setLocation((FrmMain.escritorio.getWidth() - or.getWidth()) / 2, 0);
            or.setVisible(true);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
         control.fila = tOrden.getSelectedRow();
            if (control.fila >= 0) {
                IDorden = tOrden.getValueAt(control.fila, 0).toString();
                FrmRegistrarOrdenDeCompra or = new FrmRegistrarOrdenDeCompra();
                or.setOrdendeCompra(IDorden);
                FrmMain.escritorio.add(or, JLayeredPane.DEFAULT_LAYER);
                //or.setLocation((FrmMain.escritorio.getWidth() - or.getWidth()) / 2, 0);
                or.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una Orden para poder Modificar");
            }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGenerarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarEntradaActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Confirma que desea generar la entrada de stock para el pedido seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                generarEntrada();
                cargarOrdenes();
            }
    }//GEN-LAST:event_btnGenerarEntradaActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        cargarOrdenes();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarEntrada;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tOrden;
    private javax.swing.JTextField txtBuscarOrden;
    // End of variables declaration//GEN-END:variables

    private void generarEntrada() {
        int row = tOrden.getSelectedRow();
        boolean ejecutar;
        String idEntradaGenerado = control.DevolverRegistroDto(String.format("SELECT `idEntradaStock` FROM `entradastock` WHERE `idOrdenDeCompra`=%s;", modelo.getValueAt(row, 0).toString()), 1);

        if (!idEntradaGenerado.equals("")) {
            ejecutar = true;
        } else {
            String numero = generarNumeroEntrada();
            ejecutar = control.ejecutar(String.format("INSERT INTO entradastock  SELECT NULL,'%s', CURDATE(), `idProveedor`, `idOrdenDeCompra`,0,0,NULL FROM `ordendecompra` WHERE `idOrdenDeCompra`=%s;", numero, modelo.getValueAt(row, 0).toString()));
            if (ejecutar) {
                String almacen = control.DevolverRegistroDto("SELECT `idAlmacen` FROM `almacen` LIMIT 1;", 1);
                idEntradaGenerado = control.DevolverRegistroDto("SELECT `idEntradaStock` FROM `entradastock` WHERE `numero`='" + numero + "';", 1);
                control.ejecutar(String.format("INSERT INTO detalleentradastock  SELECT NULL,%s,d.`idProduto`, d.`cant`, d.`Costo`,%s FROM detalleproducto d WHERE d.`idOrdenDeCompra`=%s;", idEntradaGenerado, almacen, modelo.getValueAt(row, 0).toString()));

            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al generar la entrada de stock");
            }
        }
        if (ejecutar) {
            if (JOptionPane.showConfirmDialog(this, "Se ha generado la entrada de stock.\n¿Desea visualizarlo?") == JOptionPane.YES_OPTION) {
                FrmRegistrarEntradasDeStok entradasDeStok = new FrmRegistrarEntradasDeStok(null);
                entradasDeStok.setEntradStock(idEntradaGenerado);
                entradasDeStok.cargarCabecera();
                FrmMain.escritorio.add(entradasDeStok, JLayeredPane.DEFAULT_LAYER);
                entradasDeStok.setLocation((FrmMain.escritorio.getWidth() - entradasDeStok.getWidth()) / 2, 0);
                entradasDeStok.setVisible(true);
                cargarOrdenes();
            }
        }

    }

    private String generarNumeroEntrada() {
        control.Sql = "SELECT MAX(`numero`) FROM `entradastock`;";
        int numero = 0;
        try {
            numero = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        } catch (NumberFormatException e) {
        }
        numero++;
        return control.format0(5, numero);
    }

    public void cargarOrdenes() {
        control.Sql = "SELECT o.idordendecompra,o.numero, date_format(o.fechaPedido,'%d/%m/%Y'), p.Nombre, o.estado "
                + "FROM ordendecompra o, proveedor P WHERE p.idProveedor=o.idProveedor AND o.estado='" + (jCheckBox1.isSelected() ? "Por entregar" : "Entregado") + "' AND "
                + "(o.numero like '%"
                + txtBuscarOrden.getText() + "%' or date_format(o.fechaPedido,'%d/%m/%Y') "
                + "like '%" + txtBuscarOrden.getText() + "%' or p.Nombre like '%" + txtBuscarOrden.getText() + "%') order by o.numero desc";
                control.LlenarJTabla(modelo, control.Sql, 5);
       
        
        
        
        btnImprimir.setEnabled(false);
        btnGenerarEntrada.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public void EliminarOrden() {
        control.fila = tOrden.getSelectedRow();
        if (control.fila >= 0) {
            IDorden = tOrden.getValueAt(control.fila, 0).toString();

            if (JOptionPane.showConfirmDialog(rootPane, "¿Confirma que desea eliminar el pedido seleccionado?", "Confirmar", 0) == 0) {

                control.Sql = "call EliminarOrden('" + IDorden + "')";
                String EliminarRegistro = control.EditarRegistroDev(control.Sql);
                if (EliminarRegistro.equals("1")) {
                    cargarOrdenes();
                } else {
                    JOptionPane.showMessageDialog(rootPane, EliminarRegistro);
                }
            }
        }
    }
}
