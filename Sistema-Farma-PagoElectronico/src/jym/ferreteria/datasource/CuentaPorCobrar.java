package jym.ferreteria.datasource;

public class CuentaPorCobrar {

    private String fecha;
    private String comprobante;
    private String numeroComprobante;
    private String nombreCliente;
    private double importe;
    private double importePagado;
    private double importeRestante;
    private String hora;

    public CuentaPorCobrar(String fecha, String comprobante, String numeroComprobante, String nombreCliente, double importe, double importePagado, double importeRestante, String hora) {
        this.fecha = fecha;
        this.comprobante = comprobante;
        this.numeroComprobante = numeroComprobante;
        this.nombreCliente = nombreCliente;
        this.importe = importe;
        this.importePagado = importePagado;
        this.importeRestante = importeRestante;
        this.hora = hora;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

}
