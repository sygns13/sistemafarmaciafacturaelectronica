/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.internalframes;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class FrmGestionPresentacion extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private Controlador control = new Controlador();
    private String idPrec;
    
    public FrmGestionPresentacion() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tPresentaciones.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"ID", "Presentaciones"});
        control.hideTableColumn(tPresentaciones, 0);
        tPresentaciones.getColumnModel().getColumn(1).setPreferredWidth(350);
        MostrarMarca();
        activar(false);
    }
    
    private void CrearMarca() {
        if (txPresentacion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Indique el nombre de la Presentación");
            txPresentacion.requestFocus();
        } else {
            idPrec = Integer.toString(control.DevIdentificador("presentacion", "idPresentacion"));
            control.Sql = "call LasPresentaciones('" + idPrec + "','" + txPresentacion.getText() + "','0')";
            String resu = control.CrearRegistroDev(control.Sql);
            if (resu.equals("1")) {
                JOptionPane.showMessageDialog(rootPane, "La Presentación se ha registrado correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                Cancelar();
                bEliminar.setEnabled(true);
                bModificar.setEnabled(true);
                MostrarMarca();
                recargarMarcaCatalogo();

                //FrmGestionCatalogoProductos.cbLineas.
                Cancelar();
            } else {
                JOptionPane.showMessageDialog(rootPane, resu,"Mensaje",JOptionPane.WARNING_MESSAGE);
                txPresentacion.grabFocus();
            }

        }
    }
    
      private void activar(boolean b) {
        txPresentacion.setEnabled(b);
    }

    private void ModificarMarca() {
        if (txPresentacion.getText().length() > 0) {
            control.Sql = "call LasPresentaciones('" + idPrec + "','" + txPresentacion.getText() + "','1')";
            String resu = control.EditarRegistroDev(control.Sql);
            if (resu.equals("2")) {
                JOptionPane.showMessageDialog(rootPane, "La Presentación se ha actualizado correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                Cancelar();
                bCrear.setEnabled(true);
                bEliminar.setEnabled(true);
                MostrarMarca();
                recargarMarcaCatalogo();
            } else {
                JOptionPane.showMessageDialog(rootPane, resu,"Mensaje",JOptionPane.WARNING_MESSAGE);
                txPresentacion.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Indique el nombre de la Presentación","Mensaje",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void EliminarMarca() {
        control.fila = tPresentaciones.getSelectedRow();
        if (control.fila >= 0) {
            idPrec = tPresentaciones.getValueAt(control.fila, 0).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "Seguro deseas eliminar", "Confirmar", 0) == 0) {
                control.Sql = "call LasPresentaciones('" + idPrec + "','" + txPresentacion.getText() + "','2')";
                boolean ejecutar = control.ejecutar(control.Sql);
                if (ejecutar) {
                    Cancelar();
                    MostrarMarca();
                    recargarMarcaCatalogo();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "La Presentación no se puede eliminar por que actualmente está en uso","Mensaje",JOptionPane.WARNING_MESSAGE);
                    Cancelar();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione la fila a eliminar","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void VerMarca() {
        control.fila = tPresentaciones.getSelectedRow();
        if (control.fila >= 0) {
            idPrec = tPresentaciones.getValueAt(control.fila, 0).toString();
            txPresentacion.setText(tPresentaciones.getValueAt(control.fila, 1).toString());
            activar(true);
            txPresentacion.grabFocus();
            bCrear.setEnabled(false);
            bEliminar.setEnabled(false);
        }
    }

    private void Cancelar() {
        txPresentacion.setText(null);
        bCrear.setText("Crear");
        bModificar.setText("Editar");
        tPresentaciones.clearSelection();
        txBuscar.setText(null);
        MostrarMarca();
        activar(false);
    }

    private void MostrarMarca() {
        BuscarMarca();
    }

    private void BuscarMarca() {
        control.Sql = "select * from presentacion where present<>'' AND present like'" + txBuscar.getText() + "%' order by present";
        control.LlenarJTabla(modelo, control.Sql, 2);
    }

    private void recargarMarcaCatalogo() {
        
        
        
        try {
            if (FrmGestionCatalogoProductos.cboMarcas != null) {
                Object obj = FrmGestionCatalogoProductos.cboMarcas.getSelectedItem();
                control.LlenarCombo(FrmGestionCatalogoProductos.cboMarcas, "Select * from presentacion order by present", 2);
                if (obj != null) {
                    FrmGestionCatalogoProductos.cboMarcas.setSelectedItem(obj);
                }

            }
        } catch (Exception e) {
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
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txPresentacion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tPresentaciones = new javax.swing.JTable();
        txBuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de Presentaciones");

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
        jLabel1.setText("Presentación:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(txPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        tPresentaciones.setForeground(new java.awt.Color(0, 51, 102));
        tPresentaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        tPresentaciones.getTableHeader().setReorderingAllowed(false);
        tPresentaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tPresentacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tPresentaciones);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
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
                .addContainerGap(13, Short.MAX_VALUE))
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

    private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        if (bCrear.getText().equals("Crear")) {
            bCrear.setText("Grabar");
            bModificar.setEnabled(false);
            bEliminar.setEnabled(false);
            this.activar(true);
            txBuscar.setText("");
            txPresentacion.requestFocus();
        } else {
            this.CrearMarca();
        }
    }//GEN-LAST:event_bCrearActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        if (txPresentacion.getText().length() > 0) {
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

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    private void tPresentacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPresentacionesMouseClicked
        if (evt.getClickCount() > 1) {
            VerMarca();
        }
    }//GEN-LAST:event_tPresentacionesMouseClicked

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        BuscarMarca();
    }//GEN-LAST:event_txBuscarKeyReleased


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
    private javax.swing.JTable tPresentaciones;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txPresentacion;
    // End of variables declaration//GEN-END:variables
}
