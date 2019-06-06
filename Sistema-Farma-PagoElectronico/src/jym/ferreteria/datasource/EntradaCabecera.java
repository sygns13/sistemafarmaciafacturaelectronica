
package jym.ferreteria.datasource;


public class EntradaCabecera {
    
    private String fechaEntrada;
    private String numeroEntrada;
    private String numeroFactura;
    private String proveedor;
    private double total;

    public EntradaCabecera() {
    }

    public EntradaCabecera(String fechaEntrada, String numeroPedido, String numeroFactura, String proveedor, double total) {
        this.fechaEntrada = fechaEntrada;
        this.numeroEntrada = numeroPedido;
        this.numeroFactura = numeroFactura;
        this.proveedor = proveedor;
        this.total = total;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(String numeroPedido) {
        this.numeroEntrada = numeroPedido;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

}
