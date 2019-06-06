package jym.ferreteria.datasource;

public class FacturaProveedor {

    private String fecha;
    private String comprobante;
    private String numeroComprobante;
    private String nombreProveedor;
    private double importe;
    private double importePagado;
    private double importeRestante;
    private String simboloMoneda;

    public FacturaProveedor() {
    }

    public FacturaProveedor(String fecha, String comprobante, String numeroComprobante, String nombreProveedor, double importe, double importePagado, double importeRestante, String simboloMoneda) {
        this.fecha = fecha;
        this.comprobante = comprobante;
        this.numeroComprobante = numeroComprobante;
        this.nombreProveedor = nombreProveedor;
        this.importe = importe;
        this.importePagado = importePagado;
        this.importeRestante = importeRestante;
        this.simboloMoneda = simboloMoneda;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(double importePagado) {
        this.importePagado = importePagado;
    }

    public double getImporteRestante() {
        return importeRestante;
    }

    public void setImporteRestante(double importeRestante) {
        this.importeRestante = importeRestante;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

}
