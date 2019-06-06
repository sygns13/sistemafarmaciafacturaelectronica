

package jym.ferreteria.datasource;


public class VentaCabecera {
    private String fecha;
    private String numeroComprobante;
    private String cliente;
    private double subTotal;
    private double igv;
    private double total;
    private String hora;
    private String nomusr;

    public VentaCabecera() {
    }

    public VentaCabecera(String fecha, String numeroComprobante, String cliente, double subTotal, double igv, double total, String hora, String nomusr) {
        this.fecha = fecha;
        this.numeroComprobante = numeroComprobante;
        this.cliente = cliente;
        this.subTotal = subTotal;
        this.igv = igv;
        this.total = total;
        this.hora= hora;
        this.nomusr = nomusr;
    }

    public String getFecha() {
        return fecha;
    }
    
    public String getHora() {
        return hora;
    }
    
     public void setHora(String hora) {
        this.hora = hora;
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getNomusr() {
        return nomusr;
    }

    public void setNomusr(String nomusr) {
        this.nomusr = nomusr;
    }
    

}
