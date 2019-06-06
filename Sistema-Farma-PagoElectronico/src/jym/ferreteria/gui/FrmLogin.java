package jym.ferreteria.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import jym.ferreteria.clases.InfoGeneral;
import javax.swing.UnsupportedLookAndFeelException;
import jym.ferreteria.clases.Accesos;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.skin.DefinicionColor;
import jym.ferreteria.skin.Fonts;
import jym.ferreteria.skin.JyMSystemSoftBlueMetalSkin;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceMagmaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMangoLookAndFeel;

public class FrmLogin extends javax.swing.JFrame {

    private Controlador control = new Controlador();

    public FrmLogin() {
        initComponents();

        setIconImage(new ImageIcon(getClass().getResource(Controlador.ICON_PATH)).getImage());
        this.setLocationRelativeTo(this);
        llenarcbo();
        cbTipoUsuario.setSelectedIndex(-1);

    }

    public void llenarcbo() {
        control.LlenarCombo(cbTipoUsuario, "SELECT * FROM tipousuario t", 2);
    }

    public void Acceder() {

        if (txtPassword.getText().trim().length() > 0 && txtUsuario.getText().trim().length() > 0 && cbTipoUsuario.getSelectedIndex() > -1) {
            Accesos.getInstance().setLogin(txtUsuario.getText());
            Accesos.getInstance().setTipoUsuario(cbTipoUsuario.getSelectedItem().toString());
            control.Sql = "select idtipousuario from tipousuario where nomtpus='" + cbTipoUsuario.getSelectedItem().toString() + "';";
            String tipous = control.DevolverRegistroDto(control.Sql, 1);
            
            String sql1="select * from usuario where nomusr='" + txtUsuario.getText() + "'"
                + " and psw='" + txtPassword.getText() + "' and idTipousuario='" + tipous + "';";
            String idUsuario = control.DevolverRegistroDto(sql1, 1);
            Accesos.getInstance().setIdUsuario(idUsuario);
            
             InfoGeneral.setIdSede("1");
            control.AccesoSistema(txtUsuario.getText(), txtPassword.getText(), this, new FrmMain(), 3, tipous,idUsuario);
        } else {
            JOptionPane.showMessageDialog(null, "Datos Incompletos");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        bAceptar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        cbTipoUsuario = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bienvenidos");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setToolTipText("");
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(null);

        bAceptar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bAceptar.setMnemonic('a');
        bAceptar.setText("Aceptar");
        bAceptar.setName("bAceptar"); // NOI18N
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(bAceptar);
        bAceptar.setBounds(20, 10, 110, 40);

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setName("bSalir"); // NOI18N
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir);
        bSalir.setBounds(190, 10, 120, 40);

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 330, 60));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("USUARIO");
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 43, 90, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("PASSWORD");
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 73, 90, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("TIPO USUARIO");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, 90, -1));

        txtUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 210, -1));

        cbTipoUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoUsuario.setForeground(new java.awt.Color(0, 51, 102));
        cbTipoUsuario.setName("cbTipoUsuario"); // NOI18N
        cbTipoUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoUsuarioItemStateChanged(evt);
            }
        });
        cbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoUsuarioActionPerformed(evt);
            }
        });
        cbTipoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbTipoUsuarioKeyPressed(evt);
            }
        });
        jPanel2.add(cbTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 210, -1));

        txtPassword.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 110));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACCESO AL SISTEMA ");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 11, 330, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyChar() == 10 && txtUsuario.getText().trim().length() > 0) {
            txtPassword.grabFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed

        if (evt.getKeyChar() == 10) {
            if (txtPassword.getText().trim().length() > 0) {
                bAceptar.doClick();
            }
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        Acceder();
    }//GEN-LAST:event_bAceptarActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(rootPane, control.DevIdentificador("Banco","idBanco"));

    }//GEN-LAST:event_bSalirActionPerformed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        //bAceptar.doClick();
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void cbTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoUsuarioActionPerformed
    private void cbTipoUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoUsuarioItemStateChanged

    }//GEN-LAST:event_cbTipoUsuarioItemStateChanged

    private void cbTipoUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbTipoUsuarioKeyPressed
        if (cbTipoUsuario.getSelectedIndex() >= 0) {
            control.passFocus(evt, txtUsuario);
        }
    }//GEN-LAST:event_cbTipoUsuarioKeyPressed

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                                JDialog.setDefaultLookAndFeelDecorated(true);

//                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin");
                                SubstanceLookAndFeel.setSkin(new JyMSystemSoftBlueMetalSkin());
                                UIManager.put("TextField.disabledTextColor", new Color(0, 0, 0));
                                UIManager.put("TextField.font", new Font("Arial", Font.BOLD, 12));
                                UIManager.put("OptionPane.background", new Color(255, 255, 255));
                                UIManager.put("Panel.background", new Color(255, 255, 255));
                                UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
                                UIManager.put("Menu.font", new Font("Arial", Font.BOLD, 12));
                                UIManager.put("MenuItem.font", new Font("Arial", Font.BOLD, 12));
                                UIManager.put("Button.Font", new Font("Arial", Font.PLAIN, 12));
                                UIManager.put(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
                                System.setProperty("awt.useSystemAAFontSettings", "lcd");

                                new FrmLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
