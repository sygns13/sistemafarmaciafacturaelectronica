

package jym.ferreteria.datasource;


public class VentaDetalleProducto {
    private String fecha;
    private String numeroComprobante;
    private String descripcion;
    private double cantidad;
    private double precio;
    private double total;
    private String hora;
    private double descuento;

    public VentaDetalleProducto() {
        
    }

    public VentaDetalleProducto(String fecha, String numeroComprobante, String descripcion, double cantidad, double precio, double total, String hora, double descuento) {
        this.fecha = fecha;
        this.numeroComprobante = numeroComprobante;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.hora = hora;
        this.descuento = descuento;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
}
