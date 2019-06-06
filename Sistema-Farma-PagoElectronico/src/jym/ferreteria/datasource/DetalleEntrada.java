package jym.ferreteria.datasource;

public class DetalleEntrada {

    private String numeroEntrada;
    private String fechaEntrada;
    private String descripcion;
    private String almacen;
    private double cantidad;
    private double costo;
    private double total;

    public DetalleEntrada() {
    }

    public DetalleEntrada(String numeroEntrada, String fechaEntrada, String descripcion,String almacen, double cantidad, double costo, double total) {
        this.numeroEntrada = numeroEntrada;
        this.fechaEntrada = fechaEntrada;
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
