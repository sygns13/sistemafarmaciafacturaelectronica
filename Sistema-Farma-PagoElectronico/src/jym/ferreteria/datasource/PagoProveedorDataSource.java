package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PagoProveedorDataSource implements JRDataSource {

    private List<PagoProveedor> pagoProveedores = new ArrayList<PagoProveedor>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < pagoProveedores.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("comprobante".equals(jrf.getName())) {
            valor = pagoProveedores.get(index).getComprobante();
        }else if ("numeroComprobante".equals(jrf.getName())) {
            valor = pagoProveedores.get(index).getNumeroComprobante();
        } else if ("proveedor".equals(jrf.getName())) {
            valor = pagoProveedores.get(index).getProveedor();
        } else if ("fechaPago".equals(jrf.getName())) {
            valor = pagoProveedores.get(index).getFechaPago();
        } else if ("modoPago".equals(jrf.getName())) {
            valor = pagoProveedores.get(index).getModoPago();
        } else if ("monto".equals(jrf.getName())) {
            valor = pagoProveedores.get(index).getMonto();
        }
        return valor;

    }

    public void addPagoProveedor(PagoProveedor pagoProveedor) {
        this.pagoProveedores.add(pagoProveedor);

    }

}
