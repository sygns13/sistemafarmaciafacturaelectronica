/**
 * To change this template, choose Tools | Templates and open the template in
 * the editor. ********
 */
/* * FrmGestionMarcas.java ** Created on 13-ene-2014, 13:14:26
 */
package jym.ferreteria.gui.internalframes;

/**
 * ** @author Ing. Miguel Angel Silva Zapata. *********
 */
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class FrmGestionMarcas extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private Controlador control = new Controlador();
    private String idMrc;

    public FrmGestionMarcas() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tMarcas.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"ID", "Laboratorio"});
        control.hideTableColumn(tMarcas, 0);
        tMarcas.getColumnModel().getColumn(1).setPreferredWidth(350);
        MostrarMarca();
        activar(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txMarca = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMarcas = new javax.swing.JTable();
        txBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Laboratorios");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bCrear.setMnemonic('c');
        bCrear.setText("Crear");
        bCrear.setPreferredSize(new java.awt.Dimension(96, 33));
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear);

        bModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('e');
        bModificar.setText("Editar");
        bModificar.setPreferredSize(new java.awt.Dimension(96, 33));
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel1.add(bModificar);

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        bEliminar.setMnemonic('l');
        bEliminar.setText("Eliminar");
        bEliminar.setPreferredSize(new java.awt.Dimension(110, 33));
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bEliminar);

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('a');
        bCancelar.setText("Cancelar");
        bCancelar.setPreferredSize(new java.awt.Dimension(114, 33));
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar);

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setPreferredSize(new java.awt.Dimension(90, 33));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Nombre Laboratorio: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(txMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

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
        tMarcas.getTableHeader().setReorderingAllowed(false);
        tMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMarcasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tMarcas);

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Buscar: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txBuscar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
    dispose();
}//GEN-LAST:event_bSalirActionPerformed

private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
    if (bCrear.getText().equals("Crear")) {
        bCrear.setText("Grabar");
        bModificar.setEnabled(false);
        bEliminar.setEnabled(false);
        this.activar(true);
        txBuscar.setText("");
        txMarca.requestFocus();
    } else {
        this.CrearMarca();
    }

}//GEN-LAST:event_bCrearActionPerformed

private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
    if (txMarca.getText().length() > 0) {
        //&& cboBanco.getSelectedItem().toString()!=null
        ModificarMarca();
    } else {
        JOptionPane.showMessageDialog(this, "Debe cargar los datos Para Modificar");
    }
}//GEN-LAST:event_bModificarActionPerformed

private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
    EliminarMarca();
}//GEN-LAST:event_bEliminarActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
    Cancelar();
    bCrear.setEnabled(true);
    bModificar.setEnabled(true);
    bEliminar.setEnabled(true);
}//GEN-LAST:event_bCancelarActionPerformed
private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
    BuscarMarca();
}//GEN-LAST:event_txBuscarKeyReleased

    private void tMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMarcasMouseClicked
        if (evt.getClickCount() > 1) {
            VerMarca();
        }
    }//GEN-LAST:event_tMarcasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tMarcas;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txMarca;
    // End of variables declaration//GEN-END:variables

    private void CrearMarca() {
        if (txMarca.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Indique el nombre del Laboratorio");
            txMarca.requestFocus();
        } else {
            idMrc = Integer.toString(control.DevIdentificador("marca", "idMarca"));
            control.Sql = "call LasMarcas('" + idMrc + "','" + txMarca.getText() + "','0')";
            String resu = control.CrearRegistroDev(control.Sql);
            if (resu.equals("1")) {
                JOptionPane.showMessageDialog(rootPane, "El Laboratorio se ha registrado correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                Cancelar();
                bEliminar.setEnabled(true);
                bModificar.setEnabled(true);
                MostrarMarca();
                recargarMarcaCatalogo();

                //FrmGestionCatalogoProductos.cbLineas.
                Cancelar();
            } else {
                JOptionPane.showMessageDialog(rootPane, resu,"Mensaje",JOptionPane.WARNING_MESSAGE);
                txMarca.grabFocus();
            }

        }
    }

    private void activar(boolean b) {
        txMarca.setEnabled(b);
    }

    private void ModificarMarca() {
        if (txMarca.getText().length() > 0) {
            control.Sql = "call LasMarcas('" + idMrc + "','" + txMarca.getText() + "','1')";
            String resu = control.EditarRegistroDev(control.Sql);
            if (resu.equals("2")) {
                JOptionPane.showMessageDialog(rootPane, "El Laboratorio se ha actualizado correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                Cancelar();
                bCrear.setEnabled(true);
                bEliminar.setEnabled(true);
                MostrarMarca();
                recargarMarcaCatalogo();
            } else {
                JOptionPane.showMessageDialog(rootPane, resu,"Mensaje",JOptionPane.WARNING_MESSAGE);
                txMarca.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Indique el nombre del Laboratorio","Mensaje",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void EliminarMarca() {
        control.fila = tMarcas.getSelectedRow();
        if (control.fila >= 0) {
            idMrc = tMarcas.getValueAt(control.fila, 0).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "Seguro deseas eliminar", "Confirmar", 0) == 0) {
                control.Sql = "call LasMarcas('" + idMrc + "','" + txMarca.getText() + "','2')";
                boolean ejecutar = control.ejecutar(control.Sql);
                if (ejecutar) {
                    Cancelar();
                    MostrarMarca();
                    recargarMarcaCatalogo();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "El Laboratorio no se puede eliminar por que actualmente está en uso","Mensaje",JOptionPane.WARNING_MESSAGE);
                    Cancelar();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione la fila a eliminar","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void VerMarca() {
        control.fila = tMarcas.getSelectedRow();
        if (control.fila >= 0) {
            idMrc = tMarcas.getValueAt(control.fila, 0).toString();
            txMarca.setText(tMarcas.getValueAt(control.fila, 1).toString());
            activar(true);
            txMarca.grabFocus();
            bCrear.setEnabled(false);
            bEliminar.setEnabled(false);
        }
    }

    private void Cancelar() {
        txMarca.setText(null);
        bCrear.setText("Crear");
        bModificar.setText("Editar");
        tMarcas.clearSelection();
        txBuscar.setText(null);
        MostrarMarca();
        activar(false);
    }

    private void MostrarMarca() {
        BuscarMarca();
    }

    private void BuscarMarca() {
        control.Sql = "select * from marca where nommarc<>'' AND nommarc like'" + txBuscar.getText() + "%' order by nommarc";
        control.LlenarJTabla(modelo, control.Sql, 2);
    }

    private void recargarMarcaCatalogo() {
        
        
        
        try {
            if (FrmGestionCatalogoProductos.cboMarcas != null) {
                Object obj = FrmGestionCatalogoProductos.cboMarcas.getSelectedItem();
                control.LlenarCombo(FrmGestionCatalogoProductos.cboMarcas, "Select * from marca order by nommarc", 2);
                if (obj != null) {
                    FrmGestionCatalogoProductos.cboMarcas.setSelectedItem(obj);
                }

            }
        } catch (Exception e) {
        }
    }
}
