package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FacturaProveedorDataSource implements JRDataSource {

    private List<FacturaProveedor> ventaFacturaProveedor = new ArrayList<FacturaProveedor>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < ventaFacturaProveedor.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getFecha();
        } else if ("comprobante".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getComprobante();
        }else if ("numeroComprobante".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getNumeroComprobante();
        } else if ("nombreProveedor".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getNombreProveedor();
        } else if ("importe".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getImporte();
        } else if ("importePagado".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getImportePagado();
        } else if ("importeRestante".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getImporteRestante();
        } else if ("simboloMoneda".equals(jrf.getName())) {
            valor = ventaFacturaProveedor.get(index).getSimboloMoneda();
        } 
        return valor;

    }

    public void addVentaFacturaProveedor(FacturaProveedor ventaFacturaProveedor) {
        this.ventaFacturaProveedor.add( ventaFacturaProveedor);

    }

}
