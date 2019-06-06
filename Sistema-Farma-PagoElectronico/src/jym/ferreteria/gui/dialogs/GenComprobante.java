/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.dialogs;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Configuracion;
import jym.ferreteria.clases.Controlador;
import static jym.ferreteria.clases.Controlador.Base;
import jym.ferreteria.clases.Numero_a_Letra;
//import jym.ferreteria.gui.internalframes.FrmRegistrarVenta.txtCliente;
import org.jvnet.substance.button.StandardButtonShaper;

/**
 *
 * @author Omr
 */
public class GenComprobante extends javax.swing.JDialog {

    private static Controlador control = new Controlador();
    private static genfactura.GenComprobante genComprobanteElectronico = new genfactura.GenComprobante();
    private static Map mapTipoComprobante = new HashMap();
    private static Map mapTipoTarjeta = new HashMap();
    private static Map mapBanco = new HashMap();
    private static Map mapCuentaBancaria = new HashMap();
    private static boolean cboBancoIsFill = false;
    public static final int OK = 0;
    private int result = -1;
    private SeleccionarClienteDialog seleccionarCliente = null;
    private boolean tipoComprobanteListo;
    private int maxFilas;
    private int numRows = 0;
    private int idVenta;
    private String cliente, tipoCliente="";
    private int idcliente;
    private String comprobante;
    private String ruc;
    
    private String total;
    

    private double subTotalInafecto = 0;
    private double enObra = 0;
    private double Subtotal = 0;
    private double Final = 0;
    private double paraIgv = 0;
//        private double subTotalAfecto = 0;
    private double igvAfecto = 0;
    
    private String[] idVtas;

    /**
     * Creates new form CobrarVentaDialog
     *
     * @param parent
     * @param modal
     */
    public GenComprobante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //jPanel1.setVisible(false);
        initComponents();
        calcularSubTotales();
     
        
       
       
//                getContentPane().setBackground(new Color(185, 204, 130));
        setIconImage(new ImageIcon(getClass().getResource(Controlador.ICON_PATH)).getImage());
        
       
        setClientePorDefecto();
        openComprobante(idcliente,tipoCliente);
       
        
       


    }

    public GenComprobante() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void setClientePorDefecto() {
        String[] cargarRegistro = this.control.cargarRegistro("SELECT `idCliente`, `nomcte`, `dniRuc`, `direc`, tipo  FROM `cliente` WHERE `idCliente`=1;", 5);
        if (cargarRegistro != null) {
            this.idcliente=Integer.parseInt(cargarRegistro[0]);
            this.cliente = cargarRegistro[1];
            this.txtCliente.setText(cargarRegistro[1]);
            this.tipoCliente=cargarRegistro[4];
        }
    }
    
   public void openComprobante(int cliente, String tipoCliente){
         if(cliente!=1){
             
             if(tipoCliente.equals("Juridico")){
                 control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7 "
                + "and c.`idTipoComprobante` not in('8')"
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;", cboTipoComprobante, mapTipoComprobante);
             }else{
                 control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7  and t.idtipoComprobante<>1 "
                + "and c.`idTipoComprobante` not in('8')"
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;", cboTipoComprobante, mapTipoComprobante);
             }
        
        }
        else{
            control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7 "
                    + " and ( c.`idTipoComprobante`='2') "
                + "GROUP BY t.`idTipoComprobante`, c.`serie` order by id desc;", cboTipoComprobante, mapTipoComprobante);
        }
        
        try {
            cboTipoComprobante.setSelectedIndex(0);
            tipoComprobanteListo = true;
        } catch (Exception e) {
        }
    }
    /*
    public void openComprobante(int cliente, String tipoCliente){
         if(cliente!=1){
             
             if(tipoCliente.equals("Juridico")){
                 control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7 "
              //  + "and c.`idTipoComprobante` not in('8')"
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;", cboTipoComprobante, mapTipoComprobante);
             }else{
                 control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7  and t.idtipoComprobante<>1 "
              //  + "and c.`idTipoComprobante` not in('8')"
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;", cboTipoComprobante, mapTipoComprobante);
             }
        
        }
        else{
            control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7 "
                    + " and ( c.`idTipoComprobante`='8' or c.`idTipoComprobante`='2') "
                + "GROUP BY t.`idTipoComprobante`, c.`serie` order by id desc;", cboTipoComprobante, mapTipoComprobante);
        }
        tipoComprobanteListo = true;
        try {
            cboTipoComprobante.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }*/
    
    
    public void buscarCliente() throws SQLException{
    String num=this.txtnumdoc.getText().trim();
    this.control.Sql="SELECT * FROM cliente c where dniRuc='"+num+"';";
    
    Base.rt=control.DevolverRegistro(this.control.Sql);
    
    while (Base.rt.next()) {
                 this.idcliente=Integer.parseInt(Base.rt.getString(1));
                 this.cliente=Base.rt.getString(2);
                 this.txtCliente.setText(Base.rt.getString(2));
                 String tipo=Base.rt.getString(3);
                 this.ruc=Base.rt.getString(5);
                 
                 this.tipoCliente=tipo;
                 
                 openComprobante(idcliente,tipoCliente);
                 
        if(tipo.equals("Natural"))//Natural DNI
        {
            this.lbltipodoc.setText("DNI - Cliente Natural");
        }else if(tipo.equals("Juridico"))//RUC
        {
            this.lbltipodoc.setText("RUC - Cliente Jurídico");
        }else if(tipo.equals("Carnet de Extranjería"))//Carnet de Extranjería
        {
            this.lbltipodoc.setText("Carnet de Extranjería");
        }else if(tipo.equals("Pasaporte"))//Pasaporte
        {
            this.lbltipodoc.setText("Pasaporte");
        }else if(tipo.equals("Cédula Diplomática"))//Cédula Diplomática de Identidad
        {
            this.lbltipodoc.setText("Cédula Diplomática");
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

        jLabel4 = new javax.swing.JLabel();
        cboTipoComprobante = new javax.swing.JComboBox();
        txtNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCliente = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lbltipodoc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtnumdoc = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        jLabel4.setText("jLabel4");
        jLabel4.setName("jLabel4"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generar Comprobante");
        addWindowFocusListener(formListener);
        addWindowListener(formListener);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboTipoComprobante.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboTipoComprobante.setName("cboTipoComprobante"); // NOI18N
        cboTipoComprobante.addItemListener(formListener);
        cboTipoComprobante.addActionListener(formListener);
        cboTipoComprobante.addKeyListener(formListener);
        getContentPane().add(cboTipoComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 200, -1));

        txtNumero.setEditable(false);
        txtNumero.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(0, 52, 193));
        txtNumero.setName("txtNumero"); // NOI18N
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 200, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Comprobante:");
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 107, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Número: ");
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 107, -1));

        jPanel3.setName("jPanel3"); // NOI18N

        btnAceptar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/registrar.png"))); // NOI18N
        btnAceptar.setMnemonic('C');
        btnAceptar.setText("Confirmar");
        btnAceptar.setName("btnAceptar"); // NOI18N
        btnAceptar.addActionListener(formListener);

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        btnCancelar.setMnemonic('a');
        btnCancelar.setText("Cancelar");
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(formListener);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 420, 80));

        txtCliente.setEditable(false);
        txtCliente.setName("txtCliente"); // NOI18N
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 280, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Cliente: ");
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        btnCliente.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Search-icon.png"))); // NOI18N
        btnCliente.setText("F3");
        btnCliente.setName("btnCliente"); // NOI18N
        btnCliente.addActionListener(formListener);
        getContentPane().add(btnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Fecha: ");
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jDateChooser1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jDateChooser1.setName("jDateChooser1"); // NOI18N
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 200, -1));

        lbltipodoc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbltipodoc.setForeground(new java.awt.Color(204, 0, 51));
        lbltipodoc.setText(" ");
        lbltipodoc.setName("lbltipodoc"); // NOI18N
        getContentPane().add(lbltipodoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 280, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("N° de Documento Cliente:");
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 170, -1));

        txtnumdoc.setName("txtnumdoc"); // NOI18N
        txtnumdoc.addAncestorListener(formListener);
        txtnumdoc.addKeyListener(formListener);
        getContentPane().add(txtnumdoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 210, -1));

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.ItemListener, java.awt.event.KeyListener, java.awt.event.WindowFocusListener, java.awt.event.WindowListener, javax.swing.event.AncestorListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == cboTipoComprobante) {
                GenComprobante.this.cboTipoComprobanteActionPerformed(evt);
            }
            else if (evt.getSource() == btnAceptar) {
                GenComprobante.this.btnAceptarActionPerformed(evt);
            }
            else if (evt.getSource() == btnCancelar) {
                GenComprobante.this.btnCancelarActionPerformed(evt);
            }
            else if (evt.getSource() == btnCliente) {
                GenComprobante.this.btnClienteActionPerformed(evt);
            }
        }

        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getSource() == cboTipoComprobante) {
                GenComprobante.this.cboTipoComprobanteItemStateChanged(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == cboTipoComprobante) {
                GenComprobante.this.cboTipoComprobanteKeyPressed(evt);
            }
            else if (evt.getSource() == txtnumdoc) {
                GenComprobante.this.txtnumdocKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == txtnumdoc) {
                GenComprobante.this.txtnumdocKeyTyped(evt);
            }
        }

        public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == GenComprobante.this) {
                GenComprobante.this.formWindowGainedFocus(evt);
            }
        }

        public void windowLostFocus(java.awt.event.WindowEvent evt) {
        }

        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == GenComprobante.this) {
                GenComprobante.this.formWindowClosing(evt);
            }
        }

        public void windowDeactivated(java.awt.event.WindowEvent evt) {
        }

        public void windowDeiconified(java.awt.event.WindowEvent evt) {
        }

        public void windowIconified(java.awt.event.WindowEvent evt) {
        }

        public void windowOpened(java.awt.event.WindowEvent evt) {
        }

        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            if (evt.getSource() == txtnumdoc) {
                GenComprobante.this.txtnumdocAncestorAdded(evt);
            }
        }

        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
        }

        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (cboTipoComprobante.getItemCount() > 0) {
            
            guardar();
        } else {
            JOptionPane.showMessageDialog(this, "Indique el tipo de comprobante a emitir");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        result = -1;
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        result = -1;
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
    }//GEN-LAST:event_formWindowGainedFocus

    private void cboTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoComprobanteItemStateChanged

    }//GEN-LAST:event_cboTipoComprobanteItemStateChanged

    private void cboTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoComprobanteActionPerformed
        if (tipoComprobanteListo && cboTipoComprobante.getSelectedItem()!=null) {
            if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura")) {
                // maxFilas = Configuracion.getInstance().getMaximoRenglonesFactura();
               maxFilas = Integer.MAX_VALUE;
            } else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("boleta")) {
                //  maxFilas = Configuracion.getInstance().getMaximoRenglonesBoleta();
              maxFilas = Integer.MAX_VALUE;
            } else {
                maxFilas = Integer.MAX_VALUE;
            }

            String[] generarComprobante = control.generarComprobante(mapTipoComprobante.get(cboTipoComprobante.getSelectedIndex()).toString());
            if (generarComprobante != null) {
                txtNumero.setText(generarComprobante[1]);

            } else {
                JOptionPane.showMessageDialog(this, "Este tipo de comprobante aún no se ha inicializado, para continuar inicialize el tipo de comprobante actual");
                this.dispose();
            }
            
        }
    }//GEN-LAST:event_cboTipoComprobanteActionPerformed

    private void cboTipoComprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTipoComprobanteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          
        }
    }//GEN-LAST:event_cboTipoComprobanteKeyPressed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        seleccionarCliente();
    }//GEN-LAST:event_btnClienteActionPerformed

    private void txtnumdocAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtnumdocAncestorAdded
        this.txtnumdoc.requestFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txtnumdocAncestorAdded

    private void txtnumdocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumdocKeyPressed
        int num=this.txtnumdoc.getText().trim().length();
        if (evt.getKeyChar() == 10 && num >= 0) {
            if(num<8){
                JOptionPane.showMessageDialog(null, "Complete los dígitos necesarios para buscar un Cliente");
            }else{
                try {
                    this.buscarCliente();
                } catch (SQLException ex) {
                    //Logger.getLogger(FrmRegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumdocKeyPressed

    private void txtnumdocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumdocKeyTyped
        control.Longitudtx(this.txtnumdoc, evt, 15);        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumdocKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenComprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GenComprobante dialog = new GenComprobante(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliente;
    private javax.swing.JComboBox cboTipoComprobante;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbltipodoc;
    public static javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtnumdoc;
    // End of variables declaration//GEN-END:variables

    public void setDatos(int idVenta, int numRows,String cliente,int idcliente,String ruc) {
        this.idVenta = idVenta;
        this.cliente= cliente;
        this.idcliente = idcliente;
        this.ruc = ruc;
        openComprobante(this.idcliente,tipoCliente);
        calcularSubTotales();

        cboTipoComprobante.requestFocus();
        this.numRows = numRows;
        
    }
    public void setVtas(String[] idV, String total){
        idVtas=idV;
        this.total=total;
        openComprobante(idcliente,tipoCliente);
        jDateChooser1.setDate(new Date());
    }
    
    

    public int getResult() {
        return result;
    }

    private void calcularSubTotales() {

        String afecto = control.ejecutarMsg(String.format("SELECT ifnull(replace(format(sum(cantidad*(precioventa-((precioventa*descuento)/100))),'2'),',',''),'0') AS subTotalAfecto "
                + "FROM `detalleventa` "
                + "WHERE `idVenta`=%s "
                + "AND `esGrabado`=TRUE;", idVenta));
        String inafecto = control.ejecutarMsg(String.format("SELECT ifnull(replace(format(sum(cantidad*(precioventa-((precioventa*descuento)/100))),'2'),',',''),'0') AS subTotalInafecto "
                + "FROM `detalleventa` WHERE `idVenta`=%s AND `esGrabado`=FALSE;", idVenta));
//                subTotalAfecto = Double.parseDouble(afecto);
        subTotalInafecto = Double.parseDouble(inafecto);

    }

    private void seleccionarCliente() {
        if (seleccionarCliente == null) {
            seleccionarCliente = new SeleccionarClienteDialog(null, true);
        }
        seleccionarCliente.setLocationRelativeTo(this);
        seleccionarCliente.setVisible(true);
        String[] datosCliente = seleccionarCliente.getDatos();
        if (datosCliente != null) {
            idcliente = Integer.parseInt(datosCliente[0]);
            ruc=datosCliente[1];
            txtCliente.setText(datosCliente[2]);
            cliente=datosCliente[2];
            
            String tipo=datosCliente[4];
            
           
            this.tipoCliente=tipo;
            
            this.txtnumdoc.setText(this.ruc);
            
            if(tipo.equals("Natural"))//Natural DNI
        {
            this.lbltipodoc.setText("DNI - Cliente Natural");
        }else if(tipo.equals("Juridico"))//RUC
        {
            this.lbltipodoc.setText("RUC - Cliente Jurídico");
        }else if(tipo.equals("Carnet de Extranjería"))//Carnet de Extranjería
        {
            this.lbltipodoc.setText("Carnet de Extranjería");
        }else if(tipo.equals("Pasaporte"))//Pasaporte
        {
            this.lbltipodoc.setText("Pasaporte");
        }else if(tipo.equals("Cédula Diplomática"))//Cédula Diplomática de Identidad
        {
            this.lbltipodoc.setText("Cédula Diplomática");
        }
        }
        openComprobante(idcliente,this.tipoCliente);
    }
  

   

    private void imprimirComprobante(String idVenta) {
        if (JOptionPane.showConfirmDialog(this, "¿Se ha generado el comprobante N° " + txtNumero.getText() + " desea imprimirlo?", "Comprobante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                      
            
            Map map = new HashMap();
            map.put("idVenta", idVenta);
            map.put("enObra", enObra);
            map.put("montoLetra", new Numero_a_Letra().Convertir(total.replace(",", ""), false));

            if (Configuracion.getInstance().isVistaPreviaComprobantesVenta()) {
                if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura")) {
                    //control.showReportDialog("Comprobante de Venta", "FacturaVentaReal", map);
                    control.showReportDialog("Comprobante de Venta", "ticketFacturaElectronica", map);
                } else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("boleta")) {
                    //control.showReportDialog("Comprobante de Venta", "BoletaVentaReal", map);
                    control.showReportDialog("Comprobante de Venta", "ticketBoletaElectronica", map);
                } else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("orden de pedido")) {
                    control.showReportDialog("Comprobante de Venta", "ordenDePedido", map);
                } else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("venta libre")) {
            Map map1 = new HashMap();
            int idve=Integer.parseInt(idVenta);
            map1.put("idVenta", idve);
            map1.put("enObra", enObra);
            map1.put("montoLetra", new Numero_a_Letra().Convertir(total.replace(",", ""), false));
                    control.showReportDialog("Comprobante de Venta", "VentaLibre", map1);
                }

            } else {
                {
                if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura")) {
                   // control.imprimirComprobanteVentaFBO(idVenta, "FacturaVentaReal", map);
                   control.imprimirComprobanteVentaFBO(idVenta, "ticketFacturaElectronica", map);
                } else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("boleta")) {
                    //control.imprimirComprobanteVentaFBO(idVenta, "boletaVentaReal", map);
                    control.imprimirComprobanteVentaFBO(idVenta, "ticketBoletaElectronica", map);
                } else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("orden de pedido")) {
                    control.imprimirComprobanteVentaFBO(idVenta, "ordenDePedido", map);
                }
                else if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("venta libre")) {
                    control.imprimirComprobanteVentaFBO(idVenta, "VentaLibre", map);
                }

            }}
        }
    }
    
    private void imprimirGuia(String idVenta, String Cliente, String idCliente,String comprobante, String ruc) {
        if (JOptionPane.showConfirmDialog(this, "¿Desea Generar una guía de remisión, desea imprimirlo?", "Comprobante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                      
            GuiaDeRemision guia = new GuiaDeRemision(null, true);
        guia.setLocationRelativeTo(this);
        guia.setDatos(idVenta,Cliente,idCliente,comprobante,ruc);
        guia.setVisible(true);
        
        
            
        }
    }

private void guardar() {
    
    double monto=0;
    if(txtCliente.getText().trim().length()>0){
        String fecha = control.Formato_Amd(jDateChooser1.getDate() == null ? new Date() : jDateChooser1.getDate());
     System.out.println("proceso insertar");
     control.Sql = String.format("INSERT INTO venta VALUES(NULL,'%s',%s,NULL,%s,%s,%s,'Pendiente',%s,0,0.00,curtime());", fecha, idcliente, 0.00, 0.00, 0.00, Accesos.getInstance().getIdUsuario());
     System.out.println(control.Sql + "----->1");
     int id = control.executeAndGetId(control.Sql);
    if (id > 0) {
                idVenta = id;
                
    }
    
    for(int i=0;i<idVtas.length;i++){
        control.ejecutar(String.format("update detalleventa set idVenta='%s' where idVenta='%s';",idVenta,idVtas[i]));
        monto=monto+Double.parseDouble(control.CrearRegistroDev("select importe from cobroventa where idVenta='"+idVtas[i]+"';"));
        
    }
    
    String[] generarComprobante = control.generarComprobante(mapTipoComprobante.get(cboTipoComprobante.getSelectedIndex()).toString());
    control.ejecutar(String.format("UPDATE comprobante c SET c.`estado`='Emitido' WHERE c.`idComprobante`=%s;", generarComprobante[0]));
    
    control.ejecutar(String.format("insert into cobroventa values(null,'%s',curdate(),0,'%s',null,null,null,null,null);",idVenta,monto ));
    //select sum(precioventa-descuento) from detalleventa where idVenta='34';
    double montoF=Double.parseDouble(control.CrearRegistroDev("select replace(format(sum(cantidad*(precioventa-((precioventa*descuento)/100))),'2'),',','') from detalleventa where idVenta='"+idVenta+"' group by idVenta;"));
    
    if(monto>=montoF){
        monto=montoF;
        
        double montoVta=montoF/(1+0.18);
        double igv=montoVta*0.18;
        
        String igv1=control.decimalFormat(igv);
        String mntoVta1=control.decimalFormat(montoVta);
        
        
        
     control.ejecutar(String.format("update venta set subTotalInafecto='%s',igv='%s',estado='Pagado',pagado='1', enObra='-1.00', idComprobante='%s' where idVenta='%s';",mntoVta1,igv1,generarComprobante[0],idVenta));   
    }
    
    else{
        double montoVta=montoF/(1+0.18);
        double igv=montoVta*0.18;
        
        String igv1=control.decimalFormat(igv);
        String mntoVta1=control.decimalFormat(montoVta);
        
        
        
     control.ejecutar(String.format("update venta set subTotalInafecto='%s',igv='%s', enObra='-1.00' , idComprobante='%s', hora=curtime() where idVenta='%s';",mntoVta1,igv1,generarComprobante[0],idVenta));
    }
    
    for(int i=0;i<idVtas.length;i++){
         control.ejecutar(String.format("delete from cobroventa where idVenta='%s';",idVtas[i])); 
        control.ejecutar(String.format("delete from venta where idVenta='%s';",idVtas[i])); 
    }
    
    int tipoCompro=Integer.parseInt(generarComprobante[2]);
            if(tipoCompro==1 || tipoCompro==2){
            guardarVentaElectronica(generarComprobante[2],generarComprobante[1].replace(" ",""));
            }
    
    
    result = OK;
    double deudor =monto-montoF;
     if (deudor < 0) {
                this.dispose();
                imprimirComprobante(String.valueOf(idVenta));
                imprimirComprobanteTicket(String.valueOf(idVenta),deudor);
                if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura")) {
                 comprobante=txtNumero.getText();
                //imprimirGuia(String.valueOf(idVenta),String.valueOf(cliente),String.valueOf(idcliente),String.valueOf(comprobante),String.valueOf(ruc));
                } 
                
                
            } else {
                this.dispose();
                imprimirComprobante(String.valueOf(idVenta));
                if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura")) {
                 comprobante=txtNumero.getText();
                //imprimirGuia(String.valueOf(idVenta),String.valueOf(cliente),String.valueOf(idcliente),String.valueOf(comprobante),String.valueOf(ruc));
                } 
            }
    }
    
    else{
        JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
    }
    
}
  


  


    private void imprimirComprobanteTicket(String idVenta, double deuda) {
        if (JOptionPane.showConfirmDialog(this, "Al existir una deuda por parte del cliente, se ha emitido un ticket, ¿Desea Imprimirlo?", "Comprobante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            Map map = new HashMap();
            map.put("idVenta", idVenta);
            double deuda1=deuda*(-1);
            map.put("deuda", deuda1);
            control.imprimirComprobanteVenta(idVenta, "ticket", map);
        }

    }
    
    public void guardarVentaElectronica(String tipoC, String Comp)
    {
        String codComp="";
        if(tipoC.equals("1"))
        {
            codComp="01";
        }else if(tipoC.equals("2"))
        {
            codComp="03";
        }
        
        
        
        String sql="",sql2="", ruc="10320432591";
        
        sql="SELECT * FROM venta v where idVenta='"+idVenta+"';";

        String fechaDto="", idClienteDto="", idComprobanteDto="", subTotalInafectoDto="", subTotalAfectoDto="",
                igvDto="", estadoDto="", idUsuarioDto="", pagadoDto="", enObraDto="", horaDto="";
        
        String nomcte="", tipoCli="", direc="", dniRuc="", tele="", correo1="", correo2="";
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                  
                    fechaDto=control.Base.rt.getString(2);
                    idClienteDto=control.Base.rt.getString(3);
                    idComprobanteDto=control.Base.rt.getString(4);
                    subTotalInafectoDto=control.Base.rt.getString(5);
                    subTotalAfectoDto=control.Base.rt.getString(6);
                    igvDto=control.Base.rt.getString(7);
                    estadoDto=control.Base.rt.getString(8);
                    idUsuarioDto=control.Base.rt.getString(9);
                    pagadoDto=control.Base.rt.getString(10);
                    enObraDto=control.Base.rt.getString(11);
                    horaDto=control.Base.rt.getString(12);
            }
            
            sql2="SELECT * FROM cliente c where idCliente='"+idClienteDto+"';";
            
            try {
            control.Base.st2 = control.Base.conec.createStatement();
            control.Base.rt2 = control.Base.st2.executeQuery(sql2);
            while (control.Base.rt2.next()) {
                  
                    nomcte=control.Base.rt2.getString(2); 
                    tipoCli=control.Base.rt2.getString(3); 
                    direc=control.Base.rt2.getString(4); 
                    dniRuc=control.Base.rt2.getString(5); 
                    tele=control.Base.rt2.getString(6);
                    correo1=control.Base.rt2.getString(7);
                    correo2=control.Base.rt2.getString(8);
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        
        String codCli="";
        if(idClienteDto.equals("1"))//Sin Doc
        {
            codCli="-";
            nomcte="-";
            dniRuc="-";
        }
        else if(tipoCli.equals("Natural"))//Natural DNI
        {
            codCli="1";
        }else if(tipoCli.equals("Juridico"))//RUC
        {
            codCli="6";
        }else if(tipoCli.equals("Carnet de Extranjería"))//Carnet de Extranjería
        {
            codCli="4";
        }else if(tipoCli.equals("Pasaporte"))//Pasaporte
        {
            codCli="7";
        }else if(tipoCli.equals("Cédula Diplomática"))//Cédula Diplomática de Identidad
        {
            codCli="A";
        }
        
        Double parte1=Double.parseDouble(subTotalInafectoDto);
        Double parte2=Double.parseDouble(igvDto);
        
        String totalFactura=control.decimalFormat(parte1+parte2);
        String IgvDto=control.decimalFormat(Configuracion.getInstance().getIgv());
        
        String  idExterno, empr_razonsocial, empr_ubigeo, empr_nombrecomercial, empr_direccion, empr_provincia, empr_departamento, empr_distrito,
empr_pais, empr_nroruc, empr_tipodoc, clie_numero, clie_tipodoc, clie_nombre, docu_fecha, docu_tipodocumento, docu_numero, docu_moneda, docu_gravada,
docu_inafecta, docu_exonerada, docu_gratuita, docu_descuento, docu_subtotal, docu_total, docu_igv, tasa_igv, docu_isc, tasa_isc, docu_otrostributos,
 tasa_otrostributos, docu_otroscargos, docu_percepcion, hashcode, cdr, cdr_nota, cdr_observacion, docu_enviaws, docu_proce_status, docu_proce_fecha,
 docu_link_pdf, docu_link_cdr, docu_link_xml, clie_correo_cpe1, clie_correo_cpe2, idventaDto;
        
        idExterno=ruc+codComp+Comp;
        empr_razonsocial="QUISPE GUTARRA DE VILCA CARMEN RUT";
        empr_ubigeo="20601";
        empr_nombrecomercial="BOTICA VIRGEN DEL CARMEN";
        empr_direccion="Jr. Comercio N° 185";
        empr_provincia="CARHUAZ";
        empr_departamento="ANCASH";
        empr_distrito="CARHUAZ";
        empr_pais="PE";
        empr_nroruc=ruc;
        empr_tipodoc="6";
        
        clie_numero=dniRuc;
        clie_tipodoc=codCli;
        clie_nombre=nomcte;
        
        docu_fecha=java.time.LocalDate.now().toString();
        docu_tipodocumento=codComp;
        docu_numero=Comp;
        docu_moneda="PEN";
        docu_gravada=subTotalInafectoDto;
        docu_inafecta="0.00";
        docu_exonerada="0.00";
        docu_gratuita="0.00";
        docu_descuento="0.00";
        docu_subtotal=subTotalInafectoDto;
        docu_total=totalFactura;
        docu_igv=igvDto;
        tasa_igv=IgvDto;
        docu_isc="0.00";
        tasa_isc="0.00";
        docu_otrostributos="0.00";
        tasa_otrostributos="0.00";
        docu_otroscargos="0.00";
        docu_percepcion="0.00";
        hashcode="";
        cdr="";
        cdr_nota="";
        cdr_observacion="";
        docu_enviaws="S";
        docu_proce_status="N";
        docu_proce_fecha="null";
        docu_link_pdf="";
        docu_link_cdr="";
        docu_link_xml="";
        clie_correo_cpe1=correo1;
        clie_correo_cpe2=correo2;
        idventaDto=String.valueOf(idVenta);
        
        System.out.println(control.Sql);
        control.Sql =String.format("insert into cabecera values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%s,'%s','%s','%s','%s','%s','%s');", idExterno, empr_razonsocial, empr_ubigeo, empr_nombrecomercial, empr_direccion, empr_provincia, empr_departamento, empr_distrito,
empr_pais, empr_nroruc, empr_tipodoc, clie_numero, clie_tipodoc, clie_nombre, docu_fecha, docu_tipodocumento, docu_numero, docu_moneda, docu_gravada,
docu_inafecta, docu_exonerada, docu_gratuita, docu_descuento, docu_subtotal, docu_total, docu_igv, tasa_igv, docu_isc, tasa_isc, docu_otrostributos,
 tasa_otrostributos, docu_otroscargos, docu_percepcion, hashcode, cdr, cdr_nota, cdr_observacion, docu_enviaws, docu_proce_status, docu_proce_fecha,
 docu_link_pdf, docu_link_cdr, docu_link_xml, clie_correo_cpe1, clie_correo_cpe2, idventaDto);
        
        
    
    int idCabecera = control.executeAndGetId(control.Sql);
    
    String docu_codigoZ="",leyenda_codigo="",leyenda_texto="";
    
    docu_codigoZ=String.valueOf(idCabecera);
    leyenda_codigo="1000";
    leyenda_texto=new Numero_a_Letra().Convertir(totalFactura.replace("S/ ", "").replace(",", ""), false);
    
    leyenda_texto=leyenda_texto.toUpperCase();
    
    System.out.println(String.format("insert into leyenda values(null,'%s','%s','%s');", docu_codigoZ,leyenda_codigo, leyenda_texto));
    control.ejecutar(String.format("insert into leyenda values(null,'%s','%s','%s');", docu_codigoZ,leyenda_codigo, leyenda_texto));
    

    String docu_codigo="", item_orden="", item_unidad="", item_cantidad="", item_codproducto="", item_descripcion="", item_afectacion="", item_tipo_precio_venta="",
            item_pventa="", item_pventa_nohonerosa="", item_to_subtotal="", item_to_igv="", item_preunitfin="";
    
            
String sql3="select d.cantidad as cantidad, p.codunid as codigo, concat(pre.`present`,' ',p.`nomproducto`) as Descripcion, \n" +
" replace(format(d.precioVenta,2),',','') as precioUnitario, replace(format(d.descuento,2),',','') as descuento, \n" +
" replace(format(((d.cantidad*d.cantreal)*(d.precioventa-(d.precioventa*(d.descuento/100)))),2),',','') as total \n" +
" from detalleventa d \n" +
" inner join venta v on v.idventa=d.idventa \n" +
" inner join produto p on p.idproduto=d.idproducto \n" +
" inner join presentacion pre on pre.idpresentacion=p.idpresentacion and d.`idVenta`='"+idVenta+"' order by d.iddetalleventa;";
int cont=1;            
            try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql3);
            while (control.Base.rt.next()) {
                
                    double punit=Double.parseDouble(control.Base.rt.getString(4));
                    double desc=Double.parseDouble(control.Base.rt.getString(5));
                    
                    double preunitfin=punit-((punit*desc)/100);
                    
                    double preunit=preunitfin/(1+Configuracion.getInstance().getIgv()/100);
                                        
                    Double totalItem=Double.parseDouble(control.Base.rt.getString(6));
                    Double totalNeto=totalItem/(1+Configuracion.getInstance().getIgv()/100);
                    Double totalIgv=(totalNeto*Configuracion.getInstance().getIgv()) / 100;
                    
                    //Double IgvItem=(totalItem*Configuracion.getInstance().getIgv()) / 100;
                    
                    docu_codigo=String.valueOf(idCabecera);
                    item_orden=String.valueOf(cont);
                    item_unidad="NIU";
                    item_cantidad=control.Base.rt.getString(1);
                    item_codproducto=control.Base.rt.getString(2); 
                    item_descripcion=control.Base.rt.getString(3); 
                    item_afectacion="10"; //Gravado - Operación Onerosa
                    item_tipo_precio_venta="01"; 
                    item_pventa=control.decimalFormat(preunit);
                    item_pventa_nohonerosa="0.00";
                    item_to_subtotal=control.decimalFormat(totalNeto);
                    item_to_igv=control.decimalFormat(totalIgv);
                    item_preunitfin=control.decimalFormat(preunitfin);
                    
                    cont++;
                    
            control.ejecutar(String.format("insert into detalle values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", docu_codigo, item_orden, item_unidad, item_cantidad, item_codproducto, item_descripcion, item_afectacion, item_tipo_precio_venta, item_pventa, item_pventa_nohonerosa, item_to_subtotal, item_to_igv,item_preunitfin));
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }



genComprobanteElectronico.GenerarCOmprobante(docu_codigo,idExterno, empr_razonsocial, empr_ubigeo, empr_nombrecomercial, empr_direccion, empr_provincia, empr_departamento, empr_distrito, empr_pais, empr_nroruc, empr_tipodoc, clie_numero, clie_tipodoc, clie_nombre, docu_fecha, docu_tipodocumento, docu_numero, docu_moneda, docu_gravada, docu_inafecta, docu_exonerada, docu_gratuita, docu_descuento, docu_subtotal, docu_total, docu_igv, tasa_igv, docu_isc, tasa_isc, docu_otrostributos, tasa_otrostributos, docu_otroscargos, docu_percepcion, hashcode, cdr, cdr_nota, cdr_observacion, docu_enviaws, docu_proce_status, docu_proce_fecha, docu_link_pdf, docu_link_cdr, docu_link_xml, clie_correo_cpe1, clie_correo_cpe2, idventaDto, leyenda_codigo, leyenda_texto);
        
        
    }
}
