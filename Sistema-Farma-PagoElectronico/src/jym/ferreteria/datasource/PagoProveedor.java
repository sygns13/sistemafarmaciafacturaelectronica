package jym.ferreteria.datasource;

public class PagoProveedor {

    private String comprobante;
    private String numeroComprobante;
    private String proveedor;
    private String fechaPago;
    private String modoPago;
    private String monto;

    public PagoProveedor() {
    }

    public PagoProveedor(String comprobante, String numeroComprobante, String proveedor, String fechaPago, String modoPago, String monto) {

        this.comprobante = comprobante;
        this.numeroComprobante = numeroComprobante;
        this.proveedor = proveedor;
        this.fechaPago = fechaPago;
        this.modoPago = modoPago;
        this.monto = monto;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
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
