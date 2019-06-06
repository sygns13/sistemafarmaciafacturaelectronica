/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.datasource;

/**
 *
 * @author Omr
 */
public class UtilidadVenta {

    private String fecha;
    private String numeroComprobante;
    private String cliente;
    private double importeVenta;
    private double importeCompra;
    private double utilidadBruta;
    private double margenUtilidad;

    public UtilidadVenta() {
    }

    public UtilidadVenta(String fecha, String numeroComprobante, String cliente, double importeVenta, double importeCompra) {
        this.fecha = fecha;
        this.numeroComprobante = numeroComprobante;
        this.cliente = cliente;
        this.importeVenta = importeVenta;
        this.importeCompra = importeCompra;
        this.utilidadBruta = importeVenta - importeCompra;
        this.margenUtilidad = ((importeVenta - importeCompra)/(importeCompra==0?1:importeCompra))*100;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getImporteVenta() {
        return importeVenta;
    }

    public void setImporteVenta(double importeVenta) {
        this.importeVenta = importeVenta;
    }

    public double getImporteCompra() {
        return importeCompra;
    }

    public void setImporteCompra(double importeCompra) {
        this.importeCompra = importeCompra;
    }

    public double getUtilidadBruta() {
        return utilidadBruta;
    }

    public void setUtilidadBruta(double utilidadBruta) {
        this.utilidadBruta = utilidadBruta;
    }

    public double getMargenUtilidad() {
        return margenUtilidad;
    }

    public void setMargenUtilidad(double margenUtilidad) {
        this.margenUtilidad = margenUtilidad;
    }

   

}
