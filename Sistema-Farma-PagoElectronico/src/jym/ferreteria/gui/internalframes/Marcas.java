/**
 * *** Marcas.java ** Created on 13-ene-2014, 13:14:26 **********
 */
package jym.ferreteria.gui.internalframes;

import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.clases.Controlador;
//import jym.ferreteria.clases.InfoGeneral;
import static jym.ferreteria.gui.internalframes.FrmGestionCatalogoProductos.cbLineas;
import static jym.ferreteria.gui.internalframes.FrmGestionCatalogoProductos.mapLinea;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Marcas extends javax.swing.JInternalFrame {

    /**
     * ************************Atributos****************************
     */
    private CustomTableModel modelo = new CustomTableModel();
    Controlador control = new Controlador();
    String idMrc;
//    InfoGeneral info = new InfoGeneral();

    /**
     * ************************Atributos****************************
     */

    /**
     * ************************Métodos******************************
     */
    public void CrearMarca() {
        if (txMarca.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Faltan datos");
            txMarca.requestFocus();
        } else {
            control.Sql = "call crear_marcas ('0','0','" + txMarca.getText() + "','"
                    + cboLinea.getSelectedItem().toString() + "','1')";
            JOptionPane.showMessageDialog(this, control.CrearRegistroDev(control.Sql));
            MostrarMarca();
            Cancelar();
        }
    }

    public void ModificarMarca() {
        if (txMarca.getText().length() > 0) {
            control.Sql = "call crear_marcas ('" + tMarcas.getValueAt(tMarcas.getSelectedRow(), 3)
                    + "','" + tMarcas.getValueAt(tMarcas.getSelectedRow(), 0) + "', '" + txMarca.getText()
                    + "', '" + cboLinea.getSelectedItem().toString() + "','2')";
            JOptionPane.showMessageDialog(this, control.CrearRegistroDev(control.Sql));
            Cancelar();
            MostrarMarca();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Faltan datos");
        }
    }

    public void EliminarMarca() {
        control.fila = tMarcas.getSelectedRow();
        if (control.fila >= 0) {
            idMrc = tMarcas.getValueAt(control.fila, 0).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "Seguro deseas eliminar",
                    "Confirmar", 0) == 0) {
                control.Sql = "call crear_marcas('0','" + idMrc + "','0','0','3')";
                control.ejecutar(control.Sql);
                if (control.ejecutar(control.Sql)) {
                    Cancelar();
                    MostrarMarca();
                } else {
                    JOptionPane.showMessageDialog(this, "La marca seleccionada no se puede eliminar, actualmente está en uso", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
                control.LlenarCombo(FrmGestionCatalogoProductos.cboMarcas, "select * from marca order by nommrc", 2);
                FrmGestionCatalogoProductos.cboMarcas.setSelectedIndex(-1);
//                control.LlenarCombo(Modelos.cboMarca, "select * from marca order by nommrc", 2);
                control.fillComboBox("select * from lineaproducto order by nomlna", cbLineas, mapLinea);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione la fila a eliminar");
        }
    }

    public void VerMarca() {
        control.fila = tMarcas.getSelectedRow();
        if (control.fila >= 0) {
            idMrc = tMarcas.getValueAt(control.fila, 0).toString();
            txMarca.setText(tMarcas.getValueAt(control.fila, 1).toString());
            control.MostrarEnCombo(tMarcas.getValueAt(control.fila, 2).toString(), cboLinea);
        }
    }

    public void Cancelar() {
        txMarca.setText(null);
        tMarcas.clearSelection();
        txBuscar.setText(null);
        txMarca.requestFocus();
        cboLinea.setSelectedIndex(-1);
    }

    public void MostrarMarca() {
        BuscarMarca();
    }

    public void BuscarMarca() {
        control.Sql = "SELECT * FROM vta_linea_marca where nommrc <> ' ' and nommrc like'%"
                + txBuscar.getText() + "%' or nomlna like '%" + txBuscar.getText() + "%' order by nommrc";
        control.LlenarJTabla(modelo, control.Sql, 4);
    }

    public Marcas() {
        initComponents();
        setTitle("Las Marcas de productos");
        this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
        tMarcas.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"Código", "Marca", "Linea de Producto", "idmarca"});
        control.hideTableColumn(tMarcas, 0, 3);
        tMarcas.getColumnModel().getColumn(0).setPreferredWidth(150);
        control.LlenarCombo(cboLinea, "select *  from lineaproducto order by nomlna", 2);
        MostrarMarca();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bSalir = new javax.swing.JButton();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboLinea = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMarcas = new javax.swing.JTable();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setForeground(new java.awt.Color(0, 51, 102));
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 100, 40));

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setForeground(new java.awt.Color(0, 51, 102));
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bCrear.setMnemonic('c');
        bCrear.setText("Crear");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 40));

        bModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bModificar.setForeground(new java.awt.Color(0, 51, 102));
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('e');
        bModificar.setText("Editar");
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel1.add(bModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 100, 40));

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setForeground(new java.awt.Color(0, 51, 102));
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/eliminar.png"))); // NOI18N
        bEliminar.setMnemonic('l');
        bEliminar.setText("Eliminar");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 110, 40));

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(0, 51, 102));
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('a');
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 120, 40));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la marca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(51, 0, 153));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Marca");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Linea de Producto");

        cboLinea.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboLineaPopupMenuWillBecomeVisible(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de marcas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        tMarcas.setForeground(new java.awt.Color(0, 51, 102));
        tMarcas.setModel(new javax.swing.table.DefaultTableModel(
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
        tMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMarcasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tMarcasMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tMarcas);

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(txBuscar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        CrearMarca();
}//GEN-LAST:event_bCrearActionPerformed
private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        ModificarMarca();
}//GEN-LAST:event_bModificarActionPerformed
private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        EliminarMarca();
}//GEN-LAST:event_bEliminarActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        Cancelar();
}//GEN-LAST:event_bCancelarActionPerformed
private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        BuscarMarca();
}//GEN-LAST:event_txBuscarKeyReleased
private void tMarcasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMarcasMouseEntered

}//GEN-LAST:event_tMarcasMouseEntered
private void tMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMarcasMouseClicked
        VerMarca();
}//GEN-LAST:event_tMarcasMouseClicked
    private void cboLineaPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboLineaPopupMenuWillBecomeVisible
        control.LlenarCombo(cboLinea, "select *  from lineaproducto order by nomlna", 2);
    }//GEN-LAST:event_cboLineaPopupMenuWillBecomeVisible

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cboLinea;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tMarcas;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txMarca;
    // End of variables declaration//GEN-END:variables
}
