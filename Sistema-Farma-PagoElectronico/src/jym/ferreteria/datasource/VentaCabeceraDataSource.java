package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class VentaCabeceraDataSource implements JRDataSource {

    private List<VentaCabecera> detalleVentas = new ArrayList<VentaCabecera>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < detalleVentas.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getFecha();
        } else if ("numeroComprobante".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getNumeroComprobante();
        } else if ("cliente".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getCliente();
        } else if ("subTotal".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getSubTotal();
        } else if ("igv".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getIgv();
        } else if ("total".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getTotal();
        } else if ("hora".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getHora();
        }else if ("nomusr".equals(jrf.getName())) {
            valor = detalleVentas.get(index).getNomusr();
        }
        return valor;

    }

    public void addDetalleVenta(VentaCabecera detalleVenta) {
        detalleVentas.add(detalleVenta);

    }

}
