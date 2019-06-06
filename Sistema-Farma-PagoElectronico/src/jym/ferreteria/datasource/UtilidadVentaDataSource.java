package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class UtilidadVentaDataSource implements JRDataSource {

    private List<UtilidadVenta> utilidadVentas = new ArrayList<UtilidadVenta>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < utilidadVentas.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getFecha();
        } else if ("numeroComprobante".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getNumeroComprobante();
        } else if ("cliente".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getCliente();
        } else if ("importeVenta".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getImporteVenta();
        } else if ("importeCompra".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getImporteCompra();
        } else if ("utilidadBruta".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getUtilidadBruta();
        
        } else if ("margenUtilidad".equals(jrf.getName())) {
            valor = utilidadVentas.get(index).getMargenUtilidad();
        }
        return valor;

    }

    public void addDetalleVenta(UtilidadVenta utilidadVenta) {
        this.utilidadVentas.add(utilidadVenta);

    }

}
