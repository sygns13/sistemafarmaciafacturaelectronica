/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.datasource;

/**
 *
 * @author crist
 */
public class DetalleSalidas {
    
    private String idretiro;
    private String posicion;
    private String fecha;
    private String hora;
    private String producto;
    private String nombre;
    private String Unidad;
    private int cantreal;
    private String motivo;
    private String nomusr;

    public DetalleSalidas() {
    }
    
    public DetalleSalidas(String idretiro,String posicion, String fecha, String hora, String producto, String nombre, String Unidad,  int cantreal,  String motivo,   String nomusr) {
        this.idretiro = idretiro;
        this.posicion = posicion;
        this.fecha = fecha;
        this.hora = hora;
        this.producto = producto;
        this.nombre = nombre;
        this.Unidad = Unidad;
        this.cantreal = cantreal;
        this.motivo = motivo;
        this.nomusr= nomusr;
    }
    
    public String getIdretiro() {
        return idretiro;
    }

    public void setIdretiro(String idretiro) {
        this.idretiro = idretiro;
    }
    
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String numeroEntrada) {
        this.posicion = posicion;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String Unidad) {
        this.Unidad = Unidad;
    }
    
    public int getCantreal() {
        return cantreal;
    }

    public void setCantreal(int cantreal) {
        this.cantreal = cantreal;
    }
    
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public String getNomusr() {
        return nomusr;
    }

    public void setNomusr(String nomusr) {
        this.nomusr = nomusr;
    }
}
