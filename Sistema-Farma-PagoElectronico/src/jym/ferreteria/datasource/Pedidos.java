
package jym.ferreteria.datasource;


public class Pedidos {
    private String numeroPedido;
    private String fechaPedido;
    private String fechaRecibido;
    private String proveedor;
    private double total;

    public Pedidos(String numeroPedido, String fechaPedido, String fechaRecibido, String proveedor, double total) {
        this.numeroPedido = numeroPedido;
        this.fechaPedido = fechaPedido;
        this.fechaRecibido = fechaRecibido;
        this.proveedor = proveedor;
        this.total = total;
    }

    public Pedidos() {
        
        
    }
    

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
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
