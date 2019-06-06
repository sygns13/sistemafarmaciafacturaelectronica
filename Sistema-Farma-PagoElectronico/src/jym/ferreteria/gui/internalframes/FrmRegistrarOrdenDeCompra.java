package jym.ferreteria.gui.internalframes;

/**
 * ** @author Silva *********
 */
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.renders.CustomTableModel;

public class FrmRegistrarOrdenDeCompra extends javax.swing.JInternalFrame {

    private final Controlador control = new Controlador();
    private final CustomTableModel modeloProveedores = new CustomTableModel();
    private final CustomTableModel modeloProductos = new CustomTableModel();
    private final CustomTableModel modelopt = new CustomTableModel();

    private final CustomTableModel modelotemp = new CustomTableModel();
    private String idProveedor = "";
    
    private String idProd="";
    private String nomProd="";

    private String fe = "";
    private String idOrdenCompra = null;
    private String numero = "";
    private double impt = 0;
   private boolean facturado = false;

    private final DecimalFormat f2d = new DecimalFormat("0.00");
    private String ProductosEliminados = "0";
    private FrmListadoOrdenesdeCompra listadoOrdenesdeCompra;

    public FrmRegistrarOrdenDeCompra() {
        initComponents();

        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tablaProveedores.setModel(modeloProveedores);
        modeloProveedores.setColumnIdentifiers(new String[]{"Código", "RUC", "Proveedor", "Dirección"});
        jLabel5.setText(" ");
        control.hideTableColumn(tablaProveedores, 0);
        control.tableMaxWidthColumn(tablaProveedores, 100, 1);
        tablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(250);
        tablaProveedores.getColumnModel().getColumn(3).setPreferredWidth(250);
        MostrarProveedor();
        tablaProductos.setModel(modeloProductos);
        modeloProductos.setColumnIdentifiers(new String[]{"Código","Código", "Producto","Aplicación","Marca", "Unidad", "Costo compra"});
        control.hideTableColumn(tablaProductos, 0);
        control.tableWidthColumn(tablaProductos, 200, 2,3);
        control.tableMaxWidthColumn(tablaProductos, 80, 5);
        MostrarProducto();
        tablaProductosSeleccionados.setModel(modelopt);
        modelopt.setColumnIdentifiers(new String[]{"ID", "Código", "Producto", "Costo", "Cantidad", "Total"});
        modelotemp.setColumnIdentifiers(new String[]{"ID", "Código", "Producto", "Costo", "Cantidad", "Total"});
        tablaProductosSeleccionados.getColumnModel().getColumn(2).setPreferredWidth(250);
        control.hideTableColumn(tablaProductosSeleccionados, 0, 1);
        bComprar.setMnemonic('p');
        bCancelar.setMnemonic('c');
        bSalir.setMnemonic('s');
        numeracion();
        tablaProductosSeleccionados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                btnEditarProducto.setEnabled(true);
                btnEliminarProducto.setEnabled(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        bComprar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscarProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        txtBucarProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProductosSeleccionados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarProdOrden = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbPfinal = new javax.swing.JLabel();
        lblNum = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jlbProducto = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txCosto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registrar pedido a proveedor");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setName("jPanel5"); // NOI18N

        bComprar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bComprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bComprar.setMnemonic('G');
        bComprar.setText("Grabar");
        bComprar.setName("bComprar"); // NOI18N
        bComprar.setPreferredSize(new java.awt.Dimension(106, 35));
        bComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComprarActionPerformed(evt);
            }
        });
        jPanel5.add(bComprar);

        bCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('c');
        bCancelar.setText("Cancelar");
        bCancelar.setName("bCancelar"); // NOI18N
        bCancelar.setPreferredSize(new java.awt.Dimension(114, 35));
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel5.add(bCancelar);

        bSalir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setName("bSalir"); // NOI18N
        bSalir.setPreferredSize(new java.awt.Dimension(90, 35));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel5.add(bSalir);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 650, 50));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Buscar");
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, -1, 20));

        txtBuscarProveedor.setName("txtBuscarProveedor"); // NOI18N
        txtBuscarProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 25, 240, -1));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaProveedores.setForeground(new java.awt.Color(0, 51, 102));
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProveedores.setName("tablaProveedores"); // NOI18N
        tablaProveedores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaProveedores.getTableHeader().setReorderingAllowed(false);
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedoresMouseClicked(evt);
            }
        });
        tablaProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProveedoresKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProveedores);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 650, 180));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Proveedor");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 26, 330, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 240));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaProductos.setForeground(new java.awt.Color(0, 51, 102));
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductos.setName("tablaProductos"); // NOI18N
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        tablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 650, 180));

        txtBucarProducto.setName("txtBucarProducto"); // NOI18N
        txtBucarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBucarProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBucarProductoKeyReleased(evt);
            }
        });
        jPanel1.add(txtBucarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 25, 590, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Buscar");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, 50, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 670, 240));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(0, 51, 102));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tablaProductosSeleccionados.setForeground(new java.awt.Color(0, 51, 102));
        tablaProductosSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductosSeleccionados.setName("tablaProductosSeleccionados"); // NOI18N
        tablaProductosSeleccionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosSeleccionadosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaProductosSeleccionados);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 630, 250));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Buscar");
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 27, -1, -1));

        txtBuscarProdOrden.setName("txtBuscarProdOrden"); // NOI18N
        txtBuscarProdOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProdOrdenKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProdOrdenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarProdOrdenKeyTyped(evt);
            }
        });
        jPanel3.add(txtBuscarProdOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 25, 410, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Importe Total: ");
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, 20));

        lbPfinal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbPfinal.setForeground(new java.awt.Color(0, 51, 102));
        lbPfinal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbPfinal.setName("lbPfinal"); // NOI18N
        jPanel3.add(lbPfinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 110, 20));

        lblNum.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblNum.setForeground(new java.awt.Color(0, 51, 102));
        lblNum.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblNum.setName("lblNum"); // NOI18N
        jPanel3.add(lblNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 110, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("N°");
        jLabel9.setName("jLabel9"); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 25, -1));

        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/pencil-icon.png"))); // NOI18N
        btnEditarProducto.setEnabled(false);
        btnEditarProducto.setName("btnEditarProducto"); // NOI18N
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 40, -1));

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash-icon.png"))); // NOI18N
        btnEliminarProducto.setEnabled(false);
        btnEliminarProducto.setName("btnEliminarProducto"); // NOI18N
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 40, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 650, 360));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel6.setName("jPanel6"); // NOI18N

        jlbProducto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbProducto.setForeground(new java.awt.Color(0, 51, 102));
        jlbProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlbProducto.setName("jlbProducto"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Costo");
        jLabel6.setName("jLabel6"); // NOI18N

        txtCantidad.setText("1.00");
        txtCantidad.setName("txtCantidad"); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad");
        jLabel8.setName("jLabel8"); // NOI18N

        txCosto.setText("0.00");
        txCosto.setName("txCosto"); // NOI18N
        txCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCostoActionPerformed(evt);
            }
        });
        txCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCostoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCostoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Producto");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addComponent(txCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(txCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 650, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed

        //ActualizaEsta();
        dispose();
    }//GEN-LAST:event_bSalirActionPerformed
    private void txtBuscarProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorKeyReleased
        BuscarProveedor();
        control.Mayusculas(txtBuscarProveedor);
    }//GEN-LAST:event_txtBuscarProveedorKeyReleased
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    }//GEN-LAST:event_formComponentShown
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        MostrarProveedor();
    }//GEN-LAST:event_formInternalFrameActivated
    private void txtBucarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBucarProductoKeyReleased
        BuscarProducto();
        control.Mayusculas(txtBucarProducto);
    }//GEN-LAST:event_txtBucarProductoKeyReleased
private void tablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedoresMouseClicked

        if (evt.getClickCount() == 2) {
            SeleccionarProveedor();
        }
}//GEN-LAST:event_tablaProveedoresMouseClicked
private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked

        if (evt.getClickCount() == 2) {
            if (jLabel5.getText().trim().length() > 0) {
                
                SeleccionarProducto();

            } else {
                JOptionPane.showMessageDialog(null, "Selecione un proveedor!!");
            }
        }

}//GEN-LAST:event_tablaProductosMouseClicked
private void txtBuscarProdOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProdOrdenKeyReleased
        control.Mayusculas(txtBuscarProdOrden);
        BuscaProdutoPrestar();
}//GEN-LAST:event_txtBuscarProdOrdenKeyReleased
private void tablaProductosSeleccionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosSeleccionadosMouseClicked

       /* if (evt.getClickCount() == 2) {
            QuitarProducto();
        }*/
    
     btnEditarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(true);
        if (evt.getClickCount() > 1 && !facturado) {
            cargarEditar();
        }
}//GEN-LAST:event_tablaProductosSeleccionadosMouseClicked
private void bComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComprarActionPerformed

        RegistrarOrdenDeCompra();

        numeracion();
        lbPfinal.setText("");
        idOrdenCompra = null;
        txtBuscarProveedor.setText("");
        txtBucarProducto.setText("");
        BuscarProveedor();
        BuscarProducto();
        control.LimTabla(modelopt);
        control.LimTabla(modelotemp);

        if (listadoOrdenesdeCompra != null) {
            listadoOrdenesdeCompra.cargarOrdenes();
        }
}//GEN-LAST:event_bComprarActionPerformed
private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        SelecionaArticulo();
        limpiar();
        txtBucarProducto.selectAll();
        txtBucarProducto.requestFocus();
}//GEN-LAST:event_txtCantidadActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // ActualizaEsta();
        Cancelar();

}//GEN-LAST:event_bCancelarActionPerformed

    private void txCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCostoActionPerformed
    }//GEN-LAST:event_txCostoActionPerformed

    private void txCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCostoKeyTyped
        control.decimal(evt, txCosto.getText());

    }//GEN-LAST:event_txCostoKeyTyped

    private void txCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCostoKeyPressed
        if (evt.getKeyChar() == 10 && txCosto.getText().trim().length() > 0) {
            txtCantidad.grabFocus();
        }
    }//GEN-LAST:event_txCostoKeyPressed

    private void txtBuscarProdOrdenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProdOrdenKeyTyped
    }//GEN-LAST:event_txtBuscarProdOrdenKeyTyped

    private void txtBuscarProdOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProdOrdenKeyPressed
    }//GEN-LAST:event_txtBuscarProdOrdenKeyPressed

    private void txCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCostoKeyReleased
    }//GEN-LAST:event_txCostoKeyReleased

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        control.Solonumeros(evt);
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtBuscarProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorKeyPressed
        if (control.isEnterKey(evt)) {
            if (modeloProveedores.getRowCount() > 0) {
                try {
                    tablaProveedores.getSelectionModel().setSelectionInterval(0, 0);
                    tablaProveedores.requestFocus();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_txtBuscarProveedorKeyPressed

    private void tablaProveedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProveedoresKeyPressed
        if (control.isEnterKey(evt)) {
            int row = tablaProveedores.getSelectedRow();

            SeleccionarProveedor();
            if (row == 0) {
                try {
                    tablaProveedores.getSelectionModel().setSelectionInterval(0, -2);
                } catch (Exception e) {
                }
            } else {
                tablaProveedores.getSelectionModel().setSelectionInterval(row - 1, row - 1);
            }
            txtBucarProducto.requestFocus();
        }
    }//GEN-LAST:event_tablaProveedoresKeyPressed

    private void txtBucarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBucarProductoKeyPressed
        if (control.isEnterKey(evt)) {
            if (modeloProductos.getRowCount() > 0) {
                try {
                    tablaProductos.getSelectionModel().setSelectionInterval(0, 0);
                    tablaProductos.requestFocus();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_txtBucarProductoKeyPressed

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed

        if (control.isEnterKey(evt)) {
            int row = tablaProductos.getSelectedRow();

            if (jLabel5.getText().trim().length() > 0) {
                SeleccionarProducto();

            } else {
                JOptionPane.showMessageDialog(null, "Selecione un proveedor!!");
            }
            if (row == 0) {
                try {
                    tablaProductos.getSelectionModel().setSelectionInterval(0, -2);
                } catch (Exception e) {
                }
            } else {
                tablaProductos.getSelectionModel().setSelectionInterval(row - 1, row - 1);
            }
            txCosto.requestFocus();
            txCosto.selectAll();
        }

    }//GEN-LAST:event_tablaProductosKeyPressed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        if (!facturado) {
            cargarEditar();
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, ya no se pueden modificar los productos");
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (!facturado) {
            //eliminarLinea();
            // guardar();
            QuitarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Ya se ha generado un comprobante para esta venta, no se pueden eliminar los productos", "Mensaje", 2);
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bComprar;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlbProducto;
    private javax.swing.JLabel lbPfinal;
    private javax.swing.JLabel lblNum;
    public static javax.swing.JTable tablaProductos;
    public static javax.swing.JTable tablaProductosSeleccionados;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField txCosto;
    private javax.swing.JTextField txtBucarProducto;
    private javax.swing.JTextField txtBuscarProdOrden;
    private javax.swing.JTextField txtBuscarProveedor;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables

    void setOrdendeCompra(String idordedecompra) {

        this.idOrdenCompra = idordedecompra;
        if (idordedecompra != null) {
            CargarCabecera();
        }
    }

    private void CargarCabecera() {
        control.Sql = "SELECT p.`idProveedor`, p.`Nombre`, o.`numero` FROM ordendecompra o, proveedor p "
                + "WHERE p.`idProveedor`=o.`idProveedor` AND o.`idOrdenDeCompra`='" + idOrdenCompra + "'";
        System.out.println(control.Sql);
        String[] cargarRegistro = control.cargarRegistro(control.Sql, 3);
        if (cargarRegistro != null) {
            jLabel5.setText(cargarRegistro[1]);
            idProveedor = cargarRegistro[0];
            lblNum.setText(cargarRegistro[2]);
            txtBuscarProveedor.setText(cargarRegistro[1]);
            BuscarProveedor();
            CargarDetalleOrden();
        }
    }

    private void CargarDetalleOrden() {
        control.LlenarJTabla(modelopt, "SELECT d.`idDetalleProducto`, v.`ID`, concat(v.`Producto`,' ',v.`Descripcion`), d.`Costo`, d.`cant`, (d.`cant`* d.`Costo`) FROM detalleproducto d, vta_catalogo v "
                + "WHERE v.`ID` = d.`idProduto` AND d.`idOrdenDeCompra`='" + idOrdenCompra + "'", 6);
        double t = 0;
        for (int i = 0; i < modelopt.getRowCount(); i++) {
            t += Double.parseDouble(tablaProductosSeleccionados.getValueAt(i, 5).toString());

        }
        String f = f2d.format(t).replace(",", ".");
        lbPfinal.setText(f);

    }

    void setListadoOrdenesdeCompra(FrmListadoOrdenesdeCompra listadoOrdenesdeCompra) {
        this.listadoOrdenesdeCompra = listadoOrdenesdeCompra;
    }

    private void SeleccionarProveedor() {
        control.fila = tablaProveedores.getSelectedRow();
        if (control.fila >= 0) {
            idProveedor = tablaProveedores.getValueAt(control.fila, 0).toString();
            jLabel5.setText(tablaProveedores.getValueAt(control.fila, 2).toString());
        }
    }

    private void SeleccionarProducto() {
        control.fila = tablaProductos.getSelectedRow();
        if (control.fila >= 0) {

            jlbProducto.setText(tablaProductos.getValueAt(control.fila, 2).toString()+' '+tablaProductos.getValueAt(control.fila, 3).toString());
            txtCantidad.setText("1");
            txCosto.setText(tablaProductos.getValueAt(control.fila, 6).toString());
            txCosto.requestFocus();
            idProd=tablaProductos.getValueAt(control.fila, 0).toString();
            nomProd=tablaProductos.getValueAt(control.fila, 2).toString()+' '+tablaProductos.getValueAt(control.fila, 3).toString();
        }
    }

    private void QuitarProducto() {
        control.fila = tablaProductosSeleccionados.getSelectedRow();
        if (control.fila >= 0) {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que quiere eliminar el producto seleccionado?", "Mensaje", 0) == 0) {

                int row = tablaProductosSeleccionados.getSelectedRow();
                String antot = tablaProductosSeleccionados.getValueAt(row, 5).toString();
                if (tablaProductosSeleccionados.getValueAt(control.fila, 0).toString().length() > 0) {
                    ProductosEliminados += "," + tablaProductosSeleccionados.getValueAt(row, 0).toString();
                }

                Double to = Double.parseDouble(lbPfinal.getText());
                Double restot = Double.parseDouble(antot);
                Double preciofnl = to - restot;
                String f = f2d.format(preciofnl).replace(",", ".");
                lbPfinal.setText(f);
                modelopt.removeRow(control.fila);
                txtBucarProducto.setText("ab");
                txtBucarProducto.setText(null);
                MostrarProducto();
            }
        }
    }

    private void BuscaProdutoPrestar() {
        int ctd = 0, leng;
        leng = txtBuscarProdOrden.getText().length();
        control.LimTabla(modelopt);

        if (leng > 0) {
            while (ctd < modelotemp.getRowCount()) {
                if ((modelotemp.getValueAt(ctd, 0).toString().contains(txtBuscarProdOrden.getText()))
                        || (modelotemp.getValueAt(ctd, 1).toString().contains(txtBuscarProdOrden.getText()))
                        || (modelotemp.getValueAt(ctd, 2).toString().contains(txtBuscarProdOrden.getText()))) {
                    //JOptionPane.showMessageDialog(rootPane, modelotemp.getValueAt(ctd, 1).toString());
                    control.Data[0] = modelotemp.getValueAt(ctd, 0).toString();
                    control.Data[1] = modelotemp.getValueAt(ctd, 1).toString();
                    control.Data[2] = modelotemp.getValueAt(ctd, 2).toString();
                    control.Data[3] = modelotemp.getValueAt(ctd, 3).toString();
                    control.Data[4] = modelotemp.getValueAt(ctd, 4).toString();
                    modelopt.addRow(control.Data);

                }
                ctd++;
            }
        } else {
            while (ctd < modelotemp.getRowCount()) {
                control.Data[0] = modelotemp.getValueAt(ctd, 0).toString();
                control.Data[1] = modelotemp.getValueAt(ctd, 1).toString();
                control.Data[2] = modelotemp.getValueAt(ctd, 2).toString();
                control.Data[3] = modelotemp.getValueAt(ctd, 3).toString();
                control.Data[4] = modelotemp.getValueAt(ctd, 4).toString();
                modelopt.addRow(control.Data);
                ctd++;
            }
        }

    }

    private void SelecionaArticulo() {
       
        String id = idProd;
        String PROD = nomProd;
        String costo = txCosto.getText().trim();
        String Cant = txtCantidad.getText();
        String Cant2;
        int bandera = 0;
        for (int e = 0; e < modelopt.getRowCount(); e++) {
            String idpt = tablaProductosSeleccionados.getValueAt(e, 1).toString();
            if (id.equals(idpt)) {
                double pto1 = 0;
                Cant2 = tablaProductosSeleccionados.getValueAt(e, 4).toString();
                int cn2 = Integer.parseInt(Cant2);
                double pr = Double.parseDouble(costo);
                int cn = Integer.parseInt(Cant);
                int cnfinal = cn2 + cn;
                impt = pr * cnfinal;
                String total1 = Double.toString(impt);
                String cndtot = Integer.toString(cnfinal);
                tablaProductosSeleccionados.setValueAt(total1, e, 5);
                tablaProductosSeleccionados.setValueAt(cndtot, e, 4);
                tablaProductosSeleccionados.setValueAt(costo, e, 3);
                for (int i = 0; i < tablaProductosSeleccionados.getRowCount(); i++) {
                    String preci = tablaProductosSeleccionados.getValueAt(i, 5).toString();
                    pto1 = pto1 + Double.parseDouble(preci);
                }
                String f = f2d.format(pto1).replace(",", ".");
                lbPfinal.setText(f);
                bandera = 1;
            }
        }
        if (bandera == 0) {
            double pr = Double.parseDouble(costo);
            double cn = Double.parseDouble(Cant);
            impt = pr * cn;
            String total = Double.toString(impt);
            control.Data[0] = "";
            control.Data[1] = id;
            control.Data[2] = PROD;
            control.Data[3] = costo;
            control.Data[4] = Cant;
            control.Data[5] = total;
            modelopt.addRow(control.Data);
            modelotemp.addRow(control.Data);
            tablaProductosSeleccionados.setModel(modelopt);
            double pto2 = 0;
            for (int i = 0; i < tablaProductosSeleccionados.getRowCount(); i++) {
                String preci = tablaProductosSeleccionados.getValueAt(i, 5).toString();
                pto2 = pto2 + Double.parseDouble(preci);
            }
            String f = f2d.format(pto2).replace(",", ".");
            lbPfinal.setText(f);
        }
    }

    private void MostrarProducto() {
        BuscarProducto();
    }

    private void BuscarProveedor() {
        control.Sql = "SELECT idProveedor,RUC,nombre,direccion FROM proveedor where nombre like '%"
                + txtBuscarProveedor.getText() + "%' or RUC like '%" + txtBuscarProveedor.getText() + "%'";
        control.LlenarJTabla(modeloProveedores, control.Sql, 4);

    }

    private void MostrarProveedor() {
        BuscarProveedor();
    }

    private void BuscarProducto() {
        control.Sql = "SELECT `ID`,`Codigo` ,`Producto`,`Descripcion`,`Marca`, `Unidad`, `precioCompra` FROM `vta_catalogo` where Codigo like '%" + txtBucarProducto.getText()+"%' or ID like '%"
                + txtBucarProducto.getText() + "%' or Producto"
                + " like '%" + txtBucarProducto.getText()
                + "%' or marca like '%" + txtBucarProducto.getText() + "%'";
        control.LlenarJTabla(modeloProductos, control.Sql, 7);

    }

    private void RegistrarOrdenDeCompra() {
        if (tablaProductosSeleccionados.getRowCount() > 0) {
            if (idOrdenCompra == null) {

                fe = control.Fecha();
                control.Sql = "call RegistrarOrdendeCompra('0','" + lblNum.getText() + "','" + fe + "','" + idProveedor + "','Por entregar','0')";
                control.CrearRegistro(control.Sql);
                control.Sql = "select max(idordendecompra) from ordendecompra";
                idOrdenCompra = control.DevolverRegistroDto(control.Sql, 1);
                LlenarDetalleProducto();
                Cancelar();
                Map map = new HashMap();
                map.put("idOrdenDeCompra", idOrdenCompra);
                control.showReportDialog("Órden de compra", "OrdendeCompra", map);

            } else {

                control.Sql = "call RegistrarOrdendeCompra('" + idOrdenCompra + "','" + lblNum.getText() + "','2014-10-10','" + idProveedor + "','Por entregar','1')";

                if (control.ejecutar(control.Sql)) {
                    LlenarDetalleProducto();
                    Cancelar();
                    Map map = new HashMap();
                    map.put("idOrdenDeCompra", idOrdenCompra);
                    control.showReportDialog("Órden de compra", "OrdendeCompra", map);

                } else {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar la órden de compra");
                }

            }

        }
    }

    private void LlenarDetalleProducto() {

        for (control.fila = 0; control.fila < tablaProductosSeleccionados.getRowCount(); control.fila++) {

            //control.Sql = "select id from vta_catalogo where Descripcion='" + tProductosP.getValueAt(control.fila, 1).toString() + "';";
            String codprod = tablaProductosSeleccionados.getValueAt(control.fila, 1).toString();
            String can = tablaProductosSeleccionados.getValueAt(control.fila, 4).toString();
            String prec = tablaProductosSeleccionados.getValueAt(control.fila, 3).toString();
            if (tablaProductosSeleccionados.getValueAt(control.fila, 0).toString().length() == 0) {
                control.Sql = "call DetalleProducto('0','" + can + "','" + prec + "','" + idOrdenCompra + "','" + codprod + "');";
                control.CrearRegistro(control.Sql);
            } else {
                control.Sql = "UPDATE detalleproducto d SET d.`cant`='" + can + "', d.`Costo`='" + prec + "' WHERE d.`idDetalleProducto`='" + tablaProductosSeleccionados.getValueAt(control.fila, 0) + "'";
                control.EditarRegistro(control.Sql);
            }

        }
        control.EliminarRegistro("DELETE FROM detalleproducto WHERE `idDetalleProducto` in (" + ProductosEliminados + ")");

    }

    private void Cancelar() {
        //ActualizaEsta();
        jLabel5.setText(" ");
        txCosto.setText("");
        txtCantidad.setText("");
        tablaProveedores.clearSelection();
        tablaProductos.clearSelection();
        control.LimTabla(modelotemp);
        control.LimTabla(modelopt);
        txtBuscarProveedor.grabFocus();
        ProductosEliminados = "0";
        if (idOrdenCompra != null) {
            CargarCabecera();
        }
    }

    private void limpiar() {
        jlbProducto.setText(" ");
        txCosto.setText(" ");
        txtCantidad.setText("");
    }

    private void numeracion() {
        int nro;
        control.Sql = "select max(numero) from ordendecompra";
        numero = control.DevolverRegistroDto(control.Sql, 1);
        if (numero == null) {
            lblNum.setText("00001");
        } else {
            nro = Integer.parseInt(numero);
            nro = nro + 1;
            String numro = Integer.toString(nro);

            if (numro.trim().length() == 1) {
                String n1 = "0000" + Integer.toString(nro);
                lblNum.setText(n1);
            }
            if (numro.trim().length() == 2) {
                String n1 = "000" + Integer.toString(nro);
                lblNum.setText(n1);
            }
            if (numro.trim().length() == 3) {
                String n1 = "00" + Integer.toString(nro);
                lblNum.setText(n1);
            }
            if (numro.trim().length() == 4) {
                String n1 = "0" + Integer.toString(nro);
                lblNum.setText(n1);
            }
            if (numro.trim().length() == 5) {
                String n1 = Integer.toString(nro);
                lblNum.setText(n1);
            }
        }
    }
    
     private void cargarEditar() {

       
       jlbProducto.setText(tablaProductosSeleccionados.getValueAt(tablaProductosSeleccionados.getSelectedRow(), 2).toString());
       txtCantidad.setText(tablaProductosSeleccionados.getValueAt(tablaProductosSeleccionados.getSelectedRow(), 4).toString());
       txCosto.setText(tablaProductosSeleccionados.getValueAt(tablaProductosSeleccionados.getSelectedRow(), 3).toString());
        //txtTotal.setText(tablaProductosDetalle.getValueAt(tablaProductosDetalle.getSelectedRow(), 7).toString());
       idProd=tablaProductosSeleccionados.getValueAt(tablaProductosSeleccionados.getSelectedRow(), 1).toString();
       nomProd=tablaProductosSeleccionados.getValueAt(tablaProductosSeleccionados.getSelectedRow(), 2).toString();
        txtCantidad.grabFocus();
       modelopt.removeRow(tablaProductosSeleccionados.getSelectedRow());
     
    }

}
