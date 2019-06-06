package jym.ferreteria.datasource;

public class CobroClientes {

    private String comprobante;
    private String nroComprobante;
    private String cliente;
    private String fechaCobro;
    private String modoPago;
    private String monto;

    public CobroClientes(String comprobante, String nroComprobante, String cliente, String fechaCobro, String modoPago, String monto) {
        this.comprobante = comprobante;
        this.nroComprobante = nroComprobante;
        this.cliente = cliente;
        this.fechaCobro = fechaCobro;
        this.modoPago = modoPago;
        this.monto = monto;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getModoPago() {
        return modoPago;
    }

    public void setModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

}