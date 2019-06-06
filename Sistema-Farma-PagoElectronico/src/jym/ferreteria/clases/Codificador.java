/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.clases;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chichi
 */
public class Codificador {

    /**
     * ****************************DECLARACION DE
     * ATRIBUTOS**********************
     */
    public ConexionBD BD = ConexionBD.getInstance();
    String Sq = "";
    int ct;

    /**
     * *************************FIN DECLARACION DE
     * ATRIBUTOS*********************
     */
    /**
     * **************************IMPLEMENTACION DE
     * METODOS************************
     */
    public String GeneracodigoAMGCO(String fec, String Tabla, String cmp) {//Genera un codigo con año mes guion y correlativo
        String cod = "", rst = fec.substring(6, 10) + fec.substring(3, 5);
        boolean ba = true;
        int ct = 1;
        while (ba == true) {
            try {
                cod = rst + "_" + Integer.toString(ct);
                Sq = "select * from " + Tabla + " where " + cmp + "='" + cod + "'";
                BD.st = BD.conec.createStatement();
                BD.rt = BD.st.executeQuery(Sq);
                if (BD.rt.next()) {
                    ct++;
                } else {
                    ba = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(Codificador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cod;
    }

    public String CapturaIniciales(String txt) {
        String cd = "" + txt.charAt(0);
        int i;
        for (i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == ' ') {
                cd = cd + txt.charAt(i + 1);
            }
        }
        return cd.toString().toUpperCase();
    }

    public boolean VerificaNorepite(String tabla, String nom, String cod) {
        boolean b = false;
        BD.Conectar();
        try {
            String sq = "select * from " + tabla + " where " + nom + "='" + cod + "';";
            BD.st = BD.conec.createStatement();
            BD.rt = BD.st.executeQuery(sq);
            b = BD.rt.next();
        } catch (Exception ex) {
            Logger.getLogger(Codificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    public String CodigoLetrasCorre(String tabla, String nom, String vlr) {//Genera un codigo con las primeras letras de un nombre y un correlativo
        String codi = CapturaIniciales(vlr);
        boolean ba = false;
        while (ba == false) {
            if (VerificaNorepite(tabla, nom, codi) == true) {
                ct++;
                codi = CapturaIniciales(vlr) + "_" + Integer.toString(ct);
            } else {
                ba = true;
            }
        }
        return codi.toUpperCase();
    }

    public int IdentificadoValido1(String tabla, String nom, String vlr) {
        int codi = 1, c = 0;

        boolean ba = false;
        while (ba == false) {
            if (VerificaNorepite(tabla, nom, Integer.toString(codi)) == true) {
                c++;
                codi = c;
                ba = false;
            } else {
                ba = true;
            }
        }
        return codi;
    }

    /**
     * **************************FIN IMPLEMENTACION DE
     * METODOS********************
     */
}
