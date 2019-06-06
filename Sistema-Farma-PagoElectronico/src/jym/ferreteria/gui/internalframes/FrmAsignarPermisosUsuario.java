/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmAsignarPermisosUsuario.java
 *
 * Created on 06-feb-2014, 17:11:22
 */
package jym.ferreteria.gui.internalframes;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CheckBoxCellEditor;
import jym.ferreteria.renders.CustomTableModel;

/**
 *
 * @author Silva
 */
public class FrmAsignarPermisosUsuario extends javax.swing.JInternalFrame {

    private Controlador control = new Controlador();
    private String idUsuario = "";
    private CustomTableModel modeloUsuarios = new CustomTableModel();
    private CustomTableModel modeloPermisosDisponibles = new CustomTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }
    };
    private CustomTableModel modeloPermisosAsignados = new CustomTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }
    };

    /**
     * Creates new form AsignarPermisosUsuario
     */
    public FrmAsignarPermisosUsuario() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tablaUsuarios.setModel(modeloUsuarios);
        modeloUsuarios.setColumnIdentifiers(new String[]{"Codigo", "DNI", "Apellidos y Nombres", "Login", "Perfil"});
        tablaPermisosDisponibles.setModel(modeloPermisosDisponibles);
        modeloPermisosDisponibles.setColumnIdentifiers(new String[]{"", "Item", "Permiso"});
        tablaPermisosUsuario.setModel(modeloPermisosAsignados);
        modeloPermisosAsignados.setColumnIdentifiers(new String[]{"", "Item", "Permiso"});

        control.hideTableColumn(tablaUsuarios, 0);
        control.hideTableColumn(tablaPermisosDisponibles, 1);
        control.tableMaxWidthColumn(tablaPermisosDisponibles, 25, 0);

        control.tableMaxWidthColumn(tablaPermisosUsuario, 25, 0);
        control.hideTableColumn(tablaPermisosUsuario, 1);
        control.tableMaxWidthColumn(tablaUsuarios, 80, 1);
        cargarUsuarios();

        tablaPermisosDisponibles.getColumnModel().getColumn(0).setCellEditor(new CheckBoxCellEditor());
        tablaPermisosUsuario.getColumnModel().getColumn(0).setCellEditor(new CheckBoxCellEditor());
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPermisosDisponibles = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPermisosUsuario = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Asignar Permisos");

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaPermisosDisponibles.setForeground(new java.awt.Color(0, 51, 102));
        tablaPermisosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tablaPermisosDisponibles.setName("tablaPermisosDisponibles"); // NOI18N
        tablaPermisosDisponibles.getTableHeader().setReorderingAllowed(false);
        tablaPermisosDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPermisosDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPermisosDisponibles);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaPermisosUsuario.setForeground(new java.awt.Color(0, 51, 102));
        tablaPermisosUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tablaPermisosUsuario.setName("tablaPermisosUsuario"); // NOI18N
        tablaPermisosUsuario.getTableHeader().setReorderingAllowed(false);
        tablaPermisosUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPermisosUsuarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPermisosUsuario);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tablaUsuarios.setForeground(new java.awt.Color(0, 51, 102));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUsuarios.setName("tablaUsuarios"); // NOI18N
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaUsuarios);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Buscar");
        jLabel2.setName("jLabel2"); // NOI18N

        txtBuscarUsuario.setName("txtBuscarUsuario"); // NOI18N
        txtBuscarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarUsuarioKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Todos los Permisos");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Permisos del Usuario");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Nombres:");
        jLabel5.setName("jLabel5"); // NOI18N

        lblNombres.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(0, 51, 102));
        lblNombres.setText(" ");
        lblNombres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblNombres.setName("lblNombres"); // NOI18N

        lblLogin.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(0, 51, 102));
        lblLogin.setText(" ");
        lblLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblLogin.setName("lblLogin"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Nombre Login:");
        jLabel8.setName("jLabel8"); // NOI18N

        jCheckBox1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBox1.setText("Seleccionar Todos");
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.setOpaque(false);
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBox2.setText("Seleccionar Todos");
        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.setOpaque(false);
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/next-icon.png"))); // NOI18N
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/previous-icon.png"))); // NOI18N
        btnQuitar.setName("btnQuitar"); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNombres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblLogin))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsuarioKeyReleased
        cargarUsuarios();
    }//GEN-LAST:event_txtBuscarUsuarioKeyReleased

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        if (evt.getClickCount() > 1) {
            if (tablaUsuarios.getSelectedRowCount() == 1) {
                idUsuario = modeloUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString();
                lblNombres.setText(modeloUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 2).toString());
                lblLogin.setText(modeloUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 3).toString());
                cargarPermisosDisponibles(idUsuario);
                cargarPermisosAsignados(idUsuario);
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void tablaPermisosDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosDisponiblesMouseClicked
        if (evt.getClickCount() > 1) {
            if (tablaPermisosDisponibles.getSelectedRowCount() == 1) {
                control.Sql = "insert into permisosusuario (idUsuario,idPermisos) values('" + idUsuario + "','" + modeloPermisosDisponibles.getValueAt(tablaPermisosDisponibles.getSelectedRow(), 1).toString() + "');";
                control.CrearRegistro(control.Sql);
                cargarPermisosDisponibles(idUsuario);
                cargarPermisosAsignados(idUsuario);
            }
        }
    }//GEN-LAST:event_tablaPermisosDisponiblesMouseClicked

    private void tablaPermisosUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosUsuarioMouseClicked
        if (evt.getClickCount() == 2) {
            if (tablaPermisosUsuario.getSelectedRowCount() == 1) {
                control.Sql = "delete from permisosusuario where idUsuario='" + idUsuario + "' and idPermisos='" + modeloPermisosAsignados.getValueAt(tablaPermisosUsuario.getSelectedRow(), 1).toString() + "';";
                control.EliminarRegistro(control.Sql);
                cargarPermisosDisponibles(idUsuario);
                cargarPermisosAsignados(idUsuario);
            }
        }
    }//GEN-LAST:event_tablaPermisosUsuarioMouseClicked

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        try {
            tablaPermisosDisponibles.getCellEditor().cancelCellEditing();
        } catch (Exception e) {
            //Logger.getLogger(FrmAsignarPermisosUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < modeloPermisosDisponibles.getRowCount(); i++) {
            modeloPermisosDisponibles.setValueAt(jCheckBox1.isSelected(), i, 0);
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        try {
            tablaPermisosDisponibles.getCellEditor().cancelCellEditing();
        } catch (Exception e) {
//            Logger.getLogger(FrmAsignarPermisosUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < modeloPermisosAsignados.getRowCount(); i++) {
            modeloPermisosAsignados.setValueAt(jCheckBox2.isSelected(), i, 0);
        }
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            tablaPermisosDisponibles.getCellEditor().stopCellEditing();
        } catch (Exception e) {
            //Logger.getLogger(FrmAsignarPermisosUsuario.class.getName()).log(Level.SEVERE, null, e);
        }

        for (int i = 0; i < modeloPermisosDisponibles.getRowCount(); i++) {
            if ("true".equals(modeloPermisosDisponibles.getValueAt(i, 0).toString())) {

                control.Sql = "insert into permisosusuario (idUsuario,idPermisos) values('" + idUsuario + "','" + modeloPermisosDisponibles.getValueAt(i, 1).toString() + "');";
                control.CrearRegistro(control.Sql);

            }
        }
        cargarPermisosDisponibles(idUsuario);
        cargarPermisosAsignados(idUsuario);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        try {
            tablaPermisosUsuario.getCellEditor().stopCellEditing();
        } catch (Exception e) {
            //Logger.getLogger(FrmAsignarPermisosUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < modeloPermisosAsignados.getRowCount(); i++) {

            if ("true".equals(modeloPermisosAsignados.getValueAt(i, 0).toString())) {
                control.Sql = "delete from permisosusuario where idUsuario='" + idUsuario + "' and idPermisos='" + modeloPermisosAsignados.getValueAt(i, 1).toString() + "';";
                control.EliminarRegistro(control.Sql);

            }
        }
        cargarPermisosDisponibles(idUsuario);
        cargarPermisosAsignados(idUsuario);
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
    }//GEN-LAST:event_btnQuitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JTable tablaPermisosDisponibles;
    private javax.swing.JTable tablaPermisosUsuario;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtBuscarUsuario;
    // End of variables declaration//GEN-END:variables

    private void cargarUsuarios() {
        BuscarCliente();
    }

    private void BuscarCliente() {
        control.Sql = "SELECT idusuario,dni,nom, nomusr,nomtpus FROM vendedores  "
                + "where dni like '%" + txtBuscarUsuario.getText() + "%' OR nomusr like '%" + txtBuscarUsuario.getText() + "%' or nom like '%" + txtBuscarUsuario.getText() + "%' ";
        control.LlenarJTabla(modeloUsuarios, control.Sql, 5);
    }

    private void cargarPermisosDisponibles(String cod) {
        control.LimTabla(modeloPermisosDisponibles);
        if (!"".equals(cod)) {
            control.Sql = "SELECT p.idpermisos,p.descripcion FROM permisos p where idPermisos not in (SELECT idPermisos FROM permisosusuario p  where p.idusuario='" + cod + "') ORDER BY p.descripcion;";
            control.DevolverRegistro(control.Sql);
            control.LimTabla(modeloPermisosDisponibles);
            try {
                while (control.Base.rt.next()) {
                    modeloPermisosDisponibles.addRow(new Object[]{Boolean.FALSE, control.Base.rt.getString(1), control.Base.rt.getString(2)});
                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmAsignarPermisosUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void cargarPermisosAsignados(String cod) {
        control.LimTabla(modeloPermisosAsignados);
        if (!"".equals(cod)) {
            control.Sql = "SELECT idpermisos,descripcion FROM vtapermisosusuarios where idusuario='" + cod + "' ORDER BY descripcion;";
            control.DevolverRegistro(control.Sql);
            control.LimTabla(modeloPermisosAsignados);
            try {
                while (control.Base.rt.next()) {
                    modeloPermisosAsignados.addRow(new Object[]{Boolean.FALSE, control.Base.rt.getString(1), control.Base.rt.getString(2)});

                }
            } catch (SQLException ex) {
                Logger.getLogger(FrmAsignarPermisosUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
