/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.gui.dialogs;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jym.ferreteria.clases.ConexionBD;
import jym.ferreteria.clases.Configuracion;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.clases.Numero_a_Letra;
import org.jvnet.substance.button.StandardButtonShaper;
import genfactura.GenComprobante;
import jym.ferreteria.clases.Accesos;

/**
 *
 * @author Omr
 */
public class CobrarVentaDialog extends javax.swing.JDialog {
    
    //public static ConexionBD Base = ConexionBD.getInstance();
    
    private static Controlador control = new Controlador();
    private static GenComprobante genComprobanteElectronico = new GenComprobante();
    private static Map mapTipoComprobante = new HashMap();
    private static Map mapTipoTarjeta = new HashMap();
    private static Map mapBanco = new HashMap();
    private static Map mapCuentaBancaria = new HashMap();
    private static boolean cboBancoIsFill = false;
    public static final int OK = 0;
    private int result = -1;

    private boolean tipoComprobanteListo;
    private int maxFilas;
    private int numRows = 0;
    private int idVenta;
    private String cliente;
    private int idcliente;
    private String comprobante;
    private String ruc;
    

    private double subTotalInafecto = 0;
    private double enObra = 0;
    private double Subtotal = 0;
    private double Final = 0;
    private double paraIgv = 0;
//        private double subTotalAfecto = 0;
    private double igvAfecto = 0;
    
    private String idusu="";

    /**
     * Creates new form CobrarVentaDialog
     *
     * @param parent
     * @param modal
     */
    public CobrarVentaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //jPanel1.setVisible(false);
        initComponents();
        userspeshal();
        calcularSubTotales();
        calcularTotal();
        
        //jPanel1.setVisible(false);
        
        txtEnObra.selectAll();
        txtEnObra.setEnabled(false);
//                getContentPane().setBackground(new Color(185, 204, 130));
        setIconImage(new ImageIcon(getClass().getResource(Controlador.ICON_PATH)).getImage());
        
       

        txtIgvInafecto.setEnabled(false);
        txtTotal.setEnabled(false);
        txtSubTotalInafecto.setEnabled(false);
        txtSubTotalFinal.setEnabled(false);
        
        txtMontoVenta.setEnabled(false);
        
        txtVuelto.setEnabled(false);
        
        txtMontoAbonado.setEnabled(false);
        cboTipoPago.setSelectedIndex(0);
        recargarBancos();
        recargarTipoTarjeta();
        recargarCuentaBancaria();

    }
    
     public void userspeshal(){
    idusu=Accesos.getInstance().getIdUsuario();

    }
    
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
             
             if(Integer.parseInt(idusu)>0){
            control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7 "
                    + " and ( c.`idTipoComprobante`='8') "
                + "GROUP BY t.`idTipoComprobante`, c.`serie` order by id desc;", cboTipoComprobante, mapTipoComprobante);
            
            }
             else{
             control.fillComboBox("SELECT CONCAT(c.`idTipoComprobante`,'-', c.`serie`) AS id, CONCAT(t.`nombre`, ' - ',c.`serie`) AS numero "
                + "FROM tipocomprobante t, comprobante c "
                + "WHERE c.`idTipoComprobante`=t.`idTipoComprobante` and t.idtipoComprobante<>7  and t.idtipoComprobante<>1 "
              //  + "and c.`idTipoComprobante` not in('8')"
                + "GROUP BY t.`idTipoComprobante`, c.`serie`;", cboTipoComprobante, mapTipoComprobante);
             }
        }
        tipoComprobanteListo = true;
        try {
            cboTipoComprobante.setSelectedIndex(0);
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

        jLabel4 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel5 = new javax.swing.JLabel();
        cboTipoComprobante = new javax.swing.JComboBox();
        txtNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSubTotalInafecto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIgvInafecto = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtEnObra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSubTotalFinal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jchIncluido = new javax.swing.JCheckBox();
        jchenObra = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cboTipoPago = new javax.swing.JComboBox();
        lblTipoTarjeta = new javax.swing.JLabel();
        cboTipoTarjeta = new javax.swing.JComboBox();
        lblBanco = new javax.swing.JLabel();
        cboBanco = new javax.swing.JComboBox();
        lblCuenta = new javax.swing.JLabel();
        cboCuenta = new javax.swing.JComboBox();
        lblNumeroCheque = new javax.swing.JLabel();
        lblNumeroTarjeta = new javax.swing.JLabel();
        txtNumeroCheque = new javax.swing.JTextField();
        txtNumeroTarjeta = new javax.swing.JTextField();
        txtMontoAbonado = new javax.swing.JTextField();
        txtVuelto = new javax.swing.JTextField();
        lblNumeroTarjeta1 = new javax.swing.JLabel();
        lblNumeroTarjeta2 = new javax.swing.JLabel();
        lblNumeroTarjeta3 = new javax.swing.JLabel();
        txtMontoVenta = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        jLabel4.setText("jLabel4");
        jLabel4.setName("jLabel4"); // NOI18N

        jDesktopPane1.setName("jDesktopPane1"); // NOI18N

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel5.setText("jLabel5");
        jLabel5.setName("jLabel5"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Confirmar pago");
        addWindowFocusListener(formListener);
        addWindowListener(formListener);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboTipoComprobante.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboTipoComprobante.setName("cboTipoComprobante"); // NOI18N
        cboTipoComprobante.addItemListener(formListener);
        cboTipoComprobante.addActionListener(formListener);
        cboTipoComprobante.addKeyListener(formListener);
        getContentPane().add(cboTipoComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 11, 200, -1));

        txtNumero.setEditable(false);
        txtNumero.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(0, 52, 193));
        txtNumero.setName("txtNumero"); // NOI18N
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 38, 200, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Comprobante:");
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 107, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Número: ");
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 107, -1));

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
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 420, 70));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N

        txtSubTotalInafecto.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtSubTotalInafecto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotalInafecto.setText("0");
        txtSubTotalInafecto.setDisabledTextColor(new java.awt.Color(102, 0, 204));
        txtSubTotalInafecto.setName("txtSubTotalInafecto"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText(" IGV");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText(" Total venta");
        jLabel3.setName("jLabel3"); // NOI18N

        txtIgvInafecto.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtIgvInafecto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIgvInafecto.setDisabledTextColor(new java.awt.Color(102, 0, 204));
        txtIgvInafecto.setName("txtIgvInafecto"); // NOI18N

        txtTotal.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setDisabledTextColor(new java.awt.Color(102, 0, 204));
        txtTotal.setName("txtTotal"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText(" Subtotal x Venta");
        jLabel13.setName("jLabel13"); // NOI18N

        txtEnObra.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtEnObra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEnObra.setText("0");
        txtEnObra.setName("txtEnObra"); // NOI18N
        txtEnObra.addCaretListener(formListener);
        txtEnObra.addKeyListener(formListener);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("En Obra");
        jLabel14.setName("jLabel14"); // NOI18N

        txtSubTotalFinal.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtSubTotalFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotalFinal.setText("0");
        txtSubTotalFinal.setName("txtSubTotalFinal"); // NOI18N
        txtSubTotalFinal.addCaretListener(formListener);
        txtSubTotalFinal.addKeyListener(formListener);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Sub Total Final");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Incluido en Obra");
        jLabel1.setName("jLabel1"); // NOI18N

        jchIncluido.setName("jchIncluido"); // NOI18N
        jchIncluido.addMouseListener(formListener);
        jchIncluido.addActionListener(formListener);
        jchIncluido.addKeyListener(formListener);

        jchenObra.setName("jchenObra"); // NOI18N
        jchenObra.addMouseListener(formListener);
        jchenObra.addActionListener(formListener);
        jchenObra.addKeyListener(formListener);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("En Obra");
        jLabel2.setName("jLabel2"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modo de pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setText("Tipo pago:");
        jLabel11.setName("jLabel11"); // NOI18N

        cboTipoPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cboTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarjeta", "Cheque", "Cuenta Bancaria" }));
        cboTipoPago.setName("cboTipoPago"); // NOI18N
        cboTipoPago.addActionListener(formListener);
        cboTipoPago.addKeyListener(formListener);

        lblTipoTarjeta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblTipoTarjeta.setText("Tarjeta:");
        lblTipoTarjeta.setName("lblTipoTarjeta"); // NOI18N

        cboTipoTarjeta.setName("cboTipoTarjeta"); // NOI18N
        cboTipoTarjeta.addKeyListener(formListener);

        lblBanco.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblBanco.setText("Banco:");
        lblBanco.setName("lblBanco"); // NOI18N

        cboBanco.setName("cboBanco"); // NOI18N
        cboBanco.addActionListener(formListener);
        cboBanco.addKeyListener(formListener);

        lblCuenta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblCuenta.setText("Cuenta:");
        lblCuenta.setName("lblCuenta"); // NOI18N

        cboCuenta.setName("cboCuenta"); // NOI18N
        cboCuenta.addKeyListener(formListener);

        lblNumeroCheque.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblNumeroCheque.setText("N° cheque:");
        lblNumeroCheque.setName("lblNumeroCheque"); // NOI18N

        lblNumeroTarjeta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblNumeroTarjeta.setText("N° tarjeta:");
        lblNumeroTarjeta.setName("lblNumeroTarjeta"); // NOI18N

        txtNumeroCheque.setName("txtNumeroCheque"); // NOI18N
        txtNumeroCheque.addKeyListener(formListener);

        txtNumeroTarjeta.setName("txtNumeroTarjeta"); // NOI18N
        txtNumeroTarjeta.addKeyListener(formListener);

        txtMontoAbonado.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtMontoAbonado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoAbonado.setMinimumSize(new java.awt.Dimension(6, 20));
        txtMontoAbonado.setName("txtMontoAbonado"); // NOI18N
        txtMontoAbonado.setPreferredSize(new java.awt.Dimension(6, 20));
        txtMontoAbonado.addCaretListener(formListener);
        txtMontoAbonado.addKeyListener(formListener);

        txtVuelto.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtVuelto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtVuelto.setMinimumSize(new java.awt.Dimension(6, 20));
        txtVuelto.setName("txtVuelto"); // NOI18N
        txtVuelto.setPreferredSize(new java.awt.Dimension(6, 20));

        lblNumeroTarjeta1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblNumeroTarjeta1.setText("Monto abonado:");
        lblNumeroTarjeta1.setName("lblNumeroTarjeta1"); // NOI18N

        lblNumeroTarjeta2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblNumeroTarjeta2.setText("Vuelto:");
        lblNumeroTarjeta2.setName("lblNumeroTarjeta2"); // NOI18N

        lblNumeroTarjeta3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblNumeroTarjeta3.setText("Monto de Venta:");
        lblNumeroTarjeta3.setName("lblNumeroTarjeta3"); // NOI18N

        txtMontoVenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoVenta.setName("txtMontoVenta"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNumeroTarjeta3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMontoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNumeroTarjeta1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(lblNumeroTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumeroCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTipoTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumeroTarjeta2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVuelto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMontoAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroTarjeta3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMontoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroTarjeta1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroTarjeta2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotal)
                            .addComponent(txtIgvInafecto, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jchIncluido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jchenObra))
                            .addComponent(txtSubTotalFinal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEnObra)
                            .addComponent(txtSubTotalInafecto)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(289, 289, 289)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchIncluido)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jchenObra)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotalInafecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotalFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIgvInafecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 420, 290));

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.ItemListener, java.awt.event.KeyListener, java.awt.event.MouseListener, java.awt.event.WindowFocusListener, java.awt.event.WindowListener, javax.swing.event.CaretListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == cboTipoComprobante) {
                CobrarVentaDialog.this.cboTipoComprobanteActionPerformed(evt);
            }
            else if (evt.getSource() == btnAceptar) {
                CobrarVentaDialog.this.btnAceptarActionPerformed(evt);
            }
            else if (evt.getSource() == btnCancelar) {
                CobrarVentaDialog.this.btnCancelarActionPerformed(evt);
            }
            else if (evt.getSource() == jchIncluido) {
                CobrarVentaDialog.this.jchIncluidoActionPerformed(evt);
            }
            else if (evt.getSource() == jchenObra) {
                CobrarVentaDialog.this.jchenObraActionPerformed(evt);
            }
            else if (evt.getSource() == cboTipoPago) {
                CobrarVentaDialog.this.cboTipoPagoActionPerformed(evt);
            }
            else if (evt.getSource() == cboBanco) {
                CobrarVentaDialog.this.cboBancoActionPerformed(evt);
            }
        }

        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getSource() == cboTipoComprobante) {
                CobrarVentaDialog.this.cboTipoComprobanteItemStateChanged(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == cboTipoComprobante) {
                CobrarVentaDialog.this.cboTipoComprobanteKeyPressed(evt);
            }
            else if (evt.getSource() == jchIncluido) {
                CobrarVentaDialog.this.jchIncluidoKeyPressed(evt);
            }
            else if (evt.getSource() == jchenObra) {
                CobrarVentaDialog.this.jchenObraKeyPressed(evt);
            }
            else if (evt.getSource() == cboTipoPago) {
                CobrarVentaDialog.this.cboTipoPagoKeyPressed(evt);
            }
            else if (evt.getSource() == cboTipoTarjeta) {
                CobrarVentaDialog.this.cboTipoTarjetaKeyPressed(evt);
            }
            else if (evt.getSource() == cboBanco) {
                CobrarVentaDialog.this.cboBancoKeyPressed(evt);
            }
            else if (evt.getSource() == cboCuenta) {
                CobrarVentaDialog.this.cboCuentaKeyPressed(evt);
            }
            else if (evt.getSource() == txtNumeroCheque) {
                CobrarVentaDialog.this.txtNumeroChequeKeyPressed(evt);
            }
            else if (evt.getSource() == txtNumeroTarjeta) {
                CobrarVentaDialog.this.txtNumeroTarjetaKeyPressed(evt);
            }
            else if (evt.getSource() == txtMontoAbonado) {
                CobrarVentaDialog.this.txtMontoAbonadoKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == txtEnObra) {
                CobrarVentaDialog.this.txtEnObraKeyReleased(evt);
            }
            else if (evt.getSource() == txtSubTotalFinal) {
                CobrarVentaDialog.this.txtSubTotalFinalKeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == txtMontoAbonado) {
                CobrarVentaDialog.this.txtMontoAbonadoKeyTyped(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jchIncluido) {
                CobrarVentaDialog.this.jchIncluidoMouseClicked(evt);
            }
            else if (evt.getSource() == jchenObra) {
                CobrarVentaDialog.this.jchenObraMouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == CobrarVentaDialog.this) {
                CobrarVentaDialog.this.formWindowGainedFocus(evt);
            }
        }

        public void windowLostFocus(java.awt.event.WindowEvent evt) {
        }

        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == CobrarVentaDialog.this) {
                CobrarVentaDialog.this.formWindowClosing(evt);
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

        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            if (evt.getSource() == txtEnObra) {
                CobrarVentaDialog.this.txtEnObraCaretUpdate(evt);
            }
            else if (evt.getSource() == txtSubTotalFinal) {
                CobrarVentaDialog.this.txtSubTotalFinalCaretUpdate(evt);
            }
            else if (evt.getSource() == txtMontoAbonado) {
                CobrarVentaDialog.this.txtMontoAbonadoCaretUpdate(evt);
            }
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
        txtMontoAbonado.grabFocus();
    }//GEN-LAST:event_formWindowGainedFocus

    private void cboTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoComprobanteItemStateChanged

    }//GEN-LAST:event_cboTipoComprobanteItemStateChanged

    private void cboTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoComprobanteActionPerformed
        
       // System.out.println(mapTipoComprobante.get(cboTipoComprobante.getSelectedIndex()).toString());
        if (tipoComprobanteListo) {
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
            calcularTotal();
        }
    }//GEN-LAST:event_cboTipoComprobanteActionPerformed

    private void cboTipoComprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTipoComprobanteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cboTipoPago.requestFocus();
        }
    }//GEN-LAST:event_cboTipoComprobanteKeyPressed

    private void cboTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoPagoActionPerformed
        if (cboTipoPago.getSelectedIndex() == 0) {
            cboTipoTarjeta.setVisible(false);
            cboBanco.setVisible(false);
            cboCuenta.setVisible(false);
            txtNumeroCheque.setVisible(false);
            txtNumeroTarjeta.setVisible(false);
            lblTipoTarjeta.setVisible(false);
            lblBanco.setVisible(false);
            lblCuenta.setVisible(false);
            lblNumeroCheque.setVisible(false);
            lblNumeroTarjeta.setVisible(false);
        } else if (cboTipoPago.getSelectedIndex() == 1) {
            cboTipoTarjeta.setVisible(true);
            txtNumeroTarjeta.setVisible(true);
            lblTipoTarjeta.setVisible(true);
            lblNumeroTarjeta.setVisible(true);

            cboBanco.setVisible(false);
            cboCuenta.setVisible(false);
            txtNumeroCheque.setVisible(false);
            lblBanco.setVisible(false);
            lblCuenta.setVisible(false);
            lblNumeroCheque.setVisible(false);

        } else if (cboTipoPago.getSelectedIndex() == 2) {
            cboBanco.setVisible(true);
            txtNumeroCheque.setVisible(true);
            lblBanco.setVisible(true);
            lblNumeroCheque.setVisible(true);

            cboTipoTarjeta.setVisible(false);
            cboCuenta.setVisible(false);
            txtNumeroTarjeta.setVisible(false);
            lblTipoTarjeta.setVisible(false);
            lblCuenta.setVisible(false);
            lblNumeroTarjeta.setVisible(false);
        } else {
            cboBanco.setVisible(true);
            cboCuenta.setVisible(true);
            lblBanco.setVisible(true);
            lblCuenta.setVisible(true);

            cboTipoTarjeta.setVisible(false);
            txtNumeroCheque.setVisible(false);
            txtNumeroTarjeta.setVisible(false);
            lblTipoTarjeta.setVisible(false);

            lblNumeroCheque.setVisible(false);
            lblNumeroTarjeta.setVisible(false);
        }
    }//GEN-LAST:event_cboTipoPagoActionPerformed

    private void cboTipoPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTipoPagoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cboTipoTarjeta.isVisible()) {
                cboTipoTarjeta.requestFocus();
            } else if (cboBanco.isVisible()) {
                cboBanco.requestFocus();
            } else {
                txtMontoAbonado.requestFocus();
                txtMontoAbonado.selectAll();
            }
        }
    }//GEN-LAST:event_cboTipoPagoKeyPressed

    private void cboTipoTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTipoTarjetaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNumeroTarjeta.requestFocus();
        }
    }//GEN-LAST:event_cboTipoTarjetaKeyPressed

    private void cboBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBancoActionPerformed
        if (cboCuenta.isVisible()) {
            recargarCuentaBancaria();
        }
    }//GEN-LAST:event_cboBancoActionPerformed

    private void cboBancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboBancoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cboCuenta.isVisible()) {
                cboCuenta.requestFocus();
            } else {
                txtNumeroCheque.requestFocus();
            }
        }
    }//GEN-LAST:event_cboBancoKeyPressed

    private void cboCuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboCuentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMontoAbonado.requestFocus();
            txtMontoAbonado.selectAll();
        }
    }//GEN-LAST:event_cboCuentaKeyPressed

    private void txtNumeroChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroChequeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMontoAbonado.requestFocus();
            txtMontoAbonado.selectAll();
        }
    }//GEN-LAST:event_txtNumeroChequeKeyPressed

    private void txtNumeroTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroTarjetaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMontoAbonado.requestFocus();
            txtMontoAbonado.selectAll();
        }
    }//GEN-LAST:event_txtNumeroTarjetaKeyPressed

    private void txtMontoAbonadoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMontoAbonadoCaretUpdate
        try {
            double vuelto = Double.parseDouble(txtMontoAbonado.getText().replace(",", ""))
            - Double.parseDouble(txtTotal.getText().replace(",", ""));
            txtVuelto.setText(control.decimalFormat(vuelto));
            //            if (Double.parseDouble(txtMontoAbonado.getText().replace(",", "")) < 0) {
                //                btnAceptar.setEnabled(false);
                //            } else {
                //                btnAceptar.setEnabled(true);
                //            }
        } catch (NumberFormatException e) {
            //            btnAceptar.setEnabled(false);
            txtVuelto.setText("0.00");
        }
    }//GEN-LAST:event_txtMontoAbonadoCaretUpdate

    private void txtMontoAbonadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            cboTipoComprobante.grabFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAceptar.grabFocus();
        }
    }//GEN-LAST:event_txtMontoAbonadoKeyPressed

    private void txtMontoAbonadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAbonadoKeyTyped
        control.decimal(evt, txtMontoAbonado.getText());
    }//GEN-LAST:event_txtMontoAbonadoKeyTyped

    private void txtSubTotalFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubTotalFinalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalFinalKeyReleased

    private void txtSubTotalFinalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSubTotalFinalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalFinalCaretUpdate

    private void txtEnObraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnObraKeyReleased

    }//GEN-LAST:event_txtEnObraKeyReleased

    private void txtEnObraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEnObraCaretUpdate
        calcularTotal();
    }//GEN-LAST:event_txtEnObraCaretUpdate

    private void jchenObraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jchenObraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchenObraKeyPressed

    private void jchenObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchenObraActionPerformed
        jchIncluido.setEnabled(!jchenObra.isSelected());
        jchIncluido.setSelected(false);
        txtEnObra.setEnabled(jchenObra.isSelected());
    }//GEN-LAST:event_jchenObraActionPerformed

    private void jchenObraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchenObraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jchenObraMouseClicked

    private void jchIncluidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jchIncluidoKeyPressed

    }//GEN-LAST:event_jchIncluidoKeyPressed

    private void jchIncluidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchIncluidoActionPerformed
        jchenObra.setEnabled(!jchIncluido.isSelected());
        jchenObra.setSelected(false);
        txtEnObra.setEnabled(!jchIncluido.isSelected());
    }//GEN-LAST:event_jchIncluidoActionPerformed

    private void jchIncluidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchIncluidoMouseClicked

    }//GEN-LAST:event_jchIncluidoMouseClicked

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
            java.util.logging.Logger.getLogger(CobrarVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CobrarVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CobrarVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CobrarVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CobrarVentaDialog dialog = new CobrarVentaDialog(new javax.swing.JFrame(), true);
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
    private static javax.swing.JComboBox cboBanco;
    private static javax.swing.JComboBox cboCuenta;
    private javax.swing.JComboBox cboTipoComprobante;
    private javax.swing.JComboBox cboTipoPago;
    private static javax.swing.JComboBox cboTipoTarjeta;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jchIncluido;
    private javax.swing.JCheckBox jchenObra;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblNumeroCheque;
    private javax.swing.JLabel lblNumeroTarjeta;
    private javax.swing.JLabel lblNumeroTarjeta1;
    private javax.swing.JLabel lblNumeroTarjeta2;
    private javax.swing.JLabel lblNumeroTarjeta3;
    private javax.swing.JLabel lblTipoTarjeta;
    private javax.swing.JTextField txtEnObra;
    private javax.swing.JTextField txtIgvInafecto;
    private javax.swing.JTextField txtMontoAbonado;
    private javax.swing.JTextField txtMontoVenta;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroCheque;
    private javax.swing.JTextField txtNumeroTarjeta;
    private javax.swing.JTextField txtSubTotalFinal;
    private javax.swing.JTextField txtSubTotalInafecto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables

    public void setDatos(int idVenta, int numRows,String cliente,int idcliente,String ruc, String tipoCliente) {
        this.idVenta = idVenta;
        this.cliente= cliente;
        this.idcliente = idcliente;
        this.ruc = ruc;
        openComprobante(idcliente,tipoCliente);
        calcularSubTotales();

        cboTipoComprobante.requestFocus();
        this.numRows = numRows;
        calcularTotal();
    }

    public int getResult() {
        return result;
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
            codCli="0";
            nomcte="\n";
            dniRuc="\n";
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
        
        String total=control.decimalFormat(parte1+parte2);
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
        docu_total=total;
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
    leyenda_texto=new Numero_a_Letra().Convertir(txtTotal.getText().replace("S/ ", "").replace(",", ""), false);
    
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

/*
double newGrabada=0;
double newIgv=0;
*/
            try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql3);
            while (control.Base.rt.next()) {
                
                    double punit=Double.parseDouble(control.Base.rt.getString(4));
                    double desc=Double.parseDouble(control.Base.rt.getString(5));
                    
                    double preunitfin=punit-((punit*desc)/100);
                    
                    
                                        
                    Double totalItem=Double.parseDouble(control.Base.rt.getString(6));
                    Double totalNeto=totalItem/(1+Configuracion.getInstance().getIgv()/100);
                   Double totalIgv=(totalNeto*Configuracion.getInstance().getIgv()) / 100;
                    
                    
                   double preunit=Double.parseDouble(control.decimalFormat(totalNeto))/Double.parseDouble(control.Base.rt.getString(1));
                    
                    
                    //Double totalIgv=Double.parseDouble(control.decimalFormat(preunit))*0.18*Double.parseDouble(control.Base.rt.getString(1));
                    
                    //preunit=(Double.parseDouble(control.decimalFormat(totalIgv))/Double.parseDouble(control.Base.rt.getString(1)))/0.18;
                    
                   // double totalIgv=(Double.parseDouble(control.decimalFormat(totalNeto)))*0.18;
                    
                   // double preunit=(Double.parseDouble(control.decimalFormat(totalNeto)))/Double.parseDouble(control.Base.rt.getString(1));
                    
                  //  preunitfin=((Double.parseDouble(control.decimalFormat(preunit)))+(Double.parseDouble(control.decimalFormat(totalIgv)))/Double.parseDouble(control.Base.rt.getString(1)));
                    
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
                    
                   /* double newitempvta=Double.parseDouble(item_pventa);
                    double newitemsubtotal=Double.parseDouble(item_pventa)*Integer.parseInt(item_cantidad);
                    double newitemigv=(Double.parseDouble(item_preunitfin)*Integer.parseInt(item_cantidad))-newitemsubtotal;*/
                    
                /*    newGrabada=newGrabada+Double.parseDouble(control.decimalFormat(newitemsubtotal));
                    newIgv=newIgv+Double.parseDouble(control.decimalFormat(newitemigv));
                    */
                  //control.ejecutar(String.format("insert into detalle values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", docu_codigo, item_orden, item_unidad, item_cantidad, item_codproducto, item_descripcion, item_afectacion, item_tipo_precio_venta, control.decimalFormat(newitempvta), item_pventa_nohonerosa, control.decimalFormat(newitemsubtotal), control.decimalFormat(newitemigv),item_preunitfin));
              
                    
            control.ejecutar(String.format("insert into detalle values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", docu_codigo, item_orden, item_unidad, item_cantidad, item_codproducto, item_descripcion, item_afectacion, item_tipo_precio_venta, item_pventa, item_pventa_nohonerosa, item_to_subtotal, item_to_igv,item_preunitfin));
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
            
          /*  docu_gravada=control.decimalFormat(newGrabada);
            docu_subtotal=control.decimalFormat(newGrabada);
            docu_igv=control.decimalFormat(newIgv);*/
            
            control.ejecutar(String.format("update cabecera set docu_subtotal='%s', docu_gravada='%s', docu_igv='%s' where idventa='%s';",docu_subtotal,docu_gravada,docu_igv,idventaDto));
            control.ejecutar(String.format("update venta set subTotalInafecto='%s', igv='%s' where idventa='%s';", docu_subtotal,docu_igv,idventaDto));


genComprobanteElectronico.GenerarCOmprobante(docu_codigo,idExterno, empr_razonsocial, empr_ubigeo, empr_nombrecomercial, empr_direccion, empr_provincia, empr_departamento, empr_distrito, empr_pais, empr_nroruc, empr_tipodoc, clie_numero, clie_tipodoc, clie_nombre, docu_fecha, docu_tipodocumento, docu_numero, docu_moneda, docu_gravada, docu_inafecta, docu_exonerada, docu_gratuita, docu_descuento, docu_subtotal, docu_total, docu_igv, tasa_igv, docu_isc, tasa_isc, docu_otrostributos, tasa_otrostributos, docu_otroscargos, docu_percepcion, hashcode, cdr, cdr_nota, cdr_observacion, docu_enviaws, docu_proce_status, docu_proce_fecha, docu_link_pdf, docu_link_cdr, docu_link_xml, clie_correo_cpe1, clie_correo_cpe2, idventaDto, leyenda_codigo, leyenda_texto);
        
        
    }
    private void calcularSubTotales() {

        String afecto = control.ejecutarMsg(String.format("SELECT ROUND(IFNULL(SUM((`precioVenta`-((`descuento`*`precioVenta`)/100))* `cantidad`),0.00),2) AS subTotalAfecto "
                + "FROM `detalleventa` "
                + "WHERE `idVenta`=%s "
                + "AND `esGrabado`=TRUE;", idVenta));
        String inafecto = control.ejecutarMsg(String.format("SELECT ROUND(IFNULL(SUM((`precioVenta`- ((`descuento`*`precioVenta`)/100))* `cantidad`),0.00),2) AS subTotalInafecto "
                + "FROM `detalleventa` WHERE `idVenta`=%s AND `esGrabado`=FALSE;", idVenta));
//                subTotalAfecto = Double.parseDouble(afecto);
        subTotalInafecto = Double.parseDouble(inafecto);

    }

    private void calcularTotal() {
        double porcentajeIGV = Configuracion.getInstance().getIgv() / 100;
        //enObra = Double.parseDouble(txtEnObra.getText());
        enObra=0.00;
        //Subtotal = (subTotalInafecto) - (subTotalInafecto *porcentajeIGV);
        
        Subtotal=subTotalInafecto/(1+porcentajeIGV);
        paraIgv = Subtotal;

        if (cboTipoComprobante.getItemCount() > 0) {
            if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura") || cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("boleta")) {

                txtSubTotalInafecto.setText(control.decimalFormat(subTotalInafecto/(1+porcentajeIGV)));
                txtSubTotalFinal.setText(control.decimalFormat(subTotalInafecto/(1+porcentajeIGV)));
                txtIgvInafecto.setText(control.decimalFormat((paraIgv) * porcentajeIGV));
                txtTotal.setText(control.decimalFormat((subTotalInafecto)));

            } else {
                txtSubTotalInafecto.setText(control.decimalFormat((subTotalInafecto)));
                txtSubTotalFinal.setText(control.decimalFormat((subTotalInafecto)));
                txtIgvInafecto.setText("0.00");
                txtTotal.setText(control.decimalFormat((subTotalInafecto + enObra)));
            }
        } else {
            txtSubTotalInafecto.setText(control.decimalFormat((subTotalInafecto)));
            txtIgvInafecto.setText("0.00");
            txtTotal.setText(control.decimalFormat((subTotalInafecto + enObra)));
        }
        txtMontoAbonado.setText(control.decimalFormat((subTotalInafecto + enObra)));
        txtMontoAbonado.requestFocus();
        txtMontoAbonado.setEnabled(true);
        
        txtMontoVenta.setText(txtMontoAbonado.getText());
//        txtMontoVenta.setText(control.decimalFormat((subTotalInafecto + enObra)));
        

    }

    private void guardar() {
        enObra = Double.parseDouble(txtEnObra.getText().replace(",", ""));
        Subtotal = Double.parseDouble(txtSubTotalInafecto.getText().replace(",", ""));
        if (cboTipoTarjeta.isVisible()) {
            if (cboTipoTarjeta.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(this, "Indique la tarjeta", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (txtNumeroTarjeta.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Indique el número de la tarjeta", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if (cboBanco.isVisible()) {
            if (cboBanco.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(this, "Indique el banco", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cboCuenta.isVisible()) {
                if (cboCuenta.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(this, "Indique el la cuenta bancaria", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            if (txtNumeroCheque.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Indique el número de cheque", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

        }
        //Si pasa todas las validaciones continúa con la ejecución
        if (numRows <= maxFilas) {
            double total = Double.parseDouble(txtTotal.getText().replace(",", ""));
            double montoAbonado = Double.parseDouble(txtMontoAbonado.getText().replace(",", ""));

            if (montoAbonado > total) {
                montoAbonado = total;
            }
            String[] generarComprobante = control.generarComprobante(mapTipoComprobante.get(cboTipoComprobante.getSelectedIndex()).toString());
            control.ejecutar(String.format("UPDATE comprobante c SET c.`estado`='Emitido' WHERE c.`idComprobante`=%s;", generarComprobante[0]));
            double precioEnObra=Double.parseDouble(txtEnObra.getText());
           /* if(jchIncluido.isSelected()==false && jchenObra.isSelected()==false){
                precioEnObra=-1;
            
            }*/
            control.ejecutar(String.format("UPDATE venta v SET "
                    + "v.`idComprobante`=%s, "
                    + "v.`subTotalInafecto`=%s, "
                    + "v.`subTotalAfecto`=%s, "
                    + "v.`igv`=%s, "
                    + "v.`pagado`=%s, "
                    + "v.`enObra`=%s "
                    + "WHERE v.`idVenta`=%d;",
                    generarComprobante[0], (txtSubTotalFinal.getText().replace(",", "")), 0.00, txtIgvInafecto.getText().replace(",", ""), ((total - montoAbonado) == 0), precioEnObra, idVenta));
            control.ejecutar(String.format("UPDATE venta v SET v.`estado`='" + ((total - montoAbonado) <= 0 ? "Pagado" : "Pendiente") + "' WHERE v.`idVenta`=%d;", idVenta));

            guardarCobroVenta();
            result = OK;
            double deudor = Double.parseDouble(txtVuelto.getText().replace(",", ""));
            
            int tipoCompro=Integer.parseInt(generarComprobante[2]);
            if(tipoCompro==1 || tipoCompro==2){
            guardarVentaElectronica(generarComprobante[2],generarComprobante[1].replace(" ",""));
            }
            
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
            
            
        } else {
            JOptionPane.showMessageDialog(this, "El comprobante seleccionado solamente admite un máximo de " + maxFilas + " filas.\n"
                    + "Por favor recorte el número de filas para poder continuar");
            result = -1;
            this.dispose();
        }

    }

    private void imprimirComprobante(String idVenta) {
        if (JOptionPane.showConfirmDialog(this, "¿Se ha generado el comprobante N° " + txtNumero.getText() + " desea imprimirlo?", "Comprobante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                      
            
            Map map = new HashMap();
            map.put("idVenta", idVenta);
            map.put("enObra", enObra);
                map.put("montoLetra", new Numero_a_Letra().Convertir(txtTotal.getText().replace("S/ ", "").replace(",", ""), false));

            if (Configuracion.getInstance().isVistaPreviaComprobantesVenta()) {
                if (cboTipoComprobante.getSelectedItem().toString().toLowerCase().contains("factura")) {
                    
                    control.showReportDialog("Comprobante de Venta", "ticketFacturaElectronica", map);
                   // control.showReportDialog("Comprobante de Venta", "FacturaVentaReal", map);
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
            map1.put("montoLetra", new Numero_a_Letra().Convertir(txtTotal.getText().replace("S/ ", "").replace(",", ""), false));
                    control.showReportDialog("Comprobante de Venta", "VentaLibre", map1);
                }

            } else {
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

            }
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


    public static void recargarBancos() {
        cboBancoIsFill = false;
        Object selectedItem = cboBanco.getSelectedItem();
        control.fillComboBox("SELECT `idBanco`, `nombre` FROM `banco` ORDER BY `nombre`;", cboBanco, mapBanco);
        if (selectedItem != null) {
            cboBanco.setSelectedItem(selectedItem);
        } else {
            try {
                cboBanco.setSelectedIndex(0);
            } catch (Exception e) {
            }
        }
        cboBancoIsFill = true;
    }

    public static void recargarTipoTarjeta() {
        Object selectedItem = cboTipoTarjeta.getSelectedItem();
        control.fillComboBox("SELECT * FROM `tipotarjeta` ORDER BY `nombre`;", cboTipoTarjeta, mapTipoTarjeta);
        if (selectedItem != null) {
            cboTipoTarjeta.setSelectedItem(selectedItem);
        }
    }

    public static void recargarCuentaBancaria() {
        if (cboBancoIsFill) {
            Object selectedItem = cboCuenta.getSelectedItem();
            control.fillComboBox(String.format("SELECT `idMisCuentas`, `numeroCuenta` FROM `miscuentas` WHERE `idBanco`=%s;", mapBanco.get(cboBanco.getSelectedIndex())), cboCuenta, mapCuentaBancaria);
            if (selectedItem != null) {
                cboCuenta.setSelectedItem(selectedItem);
            }
        }
    }

    private void guardarCobroVenta() {
        if (Double.parseDouble(txtMontoAbonado.getText().replace(",", "")) > 0) {
            
            double total = Double.parseDouble(txtTotal.getText().replace(",", ""));
            double montoAbonado = Double.parseDouble(txtMontoAbonado.getText().replace(",", ""));

            if (montoAbonado > total) {
                montoAbonado = total;
            }
            
            control.Sql = String.format("CALL registrarCobroVenta(%s,%s,%s,%d,%s,%s,%s,%s,'%s','%s',0);",
                    0, idVenta, "CURDATE()", cboTipoPago.getSelectedIndex(), String.valueOf(montoAbonado),
                    mapTipoTarjeta.get(cboTipoTarjeta.getSelectedIndex()), mapBanco.get(cboBanco.getSelectedIndex()),
                    mapCuentaBancaria.get(cboCuenta.getSelectedIndex()), txtNumeroTarjeta.getText(), txtNumeroCheque.getText());
            control.ejecutar(control.Sql);
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
}
