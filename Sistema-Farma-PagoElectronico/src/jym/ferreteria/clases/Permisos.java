/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.clases;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Omr
 */
public class Permisos {
    private Map<Integer,Boolean> mapa=new HashMap();
    
    private Permisos() {
    }
    
    public static Permisos getInstance() {
        return PermisosHolder.INSTANCE;
    }
    
    private static class PermisosHolder {

        private static final Permisos INSTANCE = new Permisos();
    }

    public Map getMapa() {
        return mapa;
    }

    public void setMapa(Map mapa) {
        this.mapa = mapa;
    }
    
}
