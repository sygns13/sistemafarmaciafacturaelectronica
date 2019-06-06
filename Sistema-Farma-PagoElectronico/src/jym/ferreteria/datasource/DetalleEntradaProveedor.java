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
public class DetalleEntradaProveedor {
    private String numeroEntrada;
    private String fechaEntrada;
    private String nombre;
    private String descripcion;
    private String almacen;
    private double cantidad;
    private double costo;
    private double total;
    
     public DetalleEntradaProveedor() {
    }
     
      public DetalleEntradaProveedor(String numeroEntrada, String fechaEntrada, String nombre, String descripcion,String almacen, double cantidad, double costo, double total) {
        this.numeroEntrada = numeroEntrada;
        this.fechaEntrada = fechaEntrada;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.almacen = almacen;
        this.cantidad = cantidad;
        this.costo = costo;
        this.total = total;
    }

    public String getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(String numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
     
     
}
