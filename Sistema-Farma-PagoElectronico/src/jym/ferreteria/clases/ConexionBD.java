package jym.ferreteria.clases;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionBD {

    public PreparedStatement prest = null;
    public CallableStatement cllst = null;
    public static Connection conec = null;
    public Connection conec1 = null;
    public Statement st = null, st1 = null;
    public ResultSet rt = null, rt1 = null;
    public static String user, password, host, db;
    private static ConexionBD instance = null;
    
    public Statement st2 = null, st12 = null;
    public ResultSet rt2 = null, rt12 = null;

    static {

        String properties = "configuration.properties";
        PropertyResourceBundle file;
        try {
            file = new PropertyResourceBundle(new FileInputStream(properties));
            user = file.getString("user");
            password = file.getString("password");
            host = file.getString("host");
            db = file.getString("database");

        } catch (IOException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ConexionBD() {
        
    }

    public static ConexionBD getInstance() {
        if (instance == null) {
            instance = new ConexionBD();
            Conectar();

        }
        try {
            if (conec.isClosed()) {
                Conectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    public ConexionBD(String ubd, String pbd) {
        ConectarDinamyc(ubd, pbd, "localhost", "bdabarrotes");
    }

    public static void Conectar() {
        try {
//            user = "root";
//            host = "localhost";
//            password = "1234";
//            db = "bdabarrotes2";
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conec = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al realizar la conexión al servidor de base de datos.\nVerifique los parámetros de conexión.\n\nDetalle de error:\n\n" + ex + "", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void ConectarDinamyc(String usbd, String clvbd, String hs, String bs) {
        try {
            user = usbd;
            password = clvbd;
            host = hs;
            db = bs;
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conec = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//***************FIN METODOS**************
}
