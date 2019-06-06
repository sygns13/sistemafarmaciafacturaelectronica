package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CuentaPorCobrarDataSource implements JRDataSource {

    private List<CuentaPorCobrar> cuentaPorCobrars = new ArrayList<CuentaPorCobrar>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < cuentaPorCobrars.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getFecha();
        } else if ("comprobante".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getComprobante();
        } else if ("numeroComprobante".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getNumeroComprobante();
        } else if ("nombreCliente".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getNombreCliente();
        } else if ("importe".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getImporte();
        } else if ("importePagado".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getImportePagado();
        } else if ("importeRestante".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getImporteRestante();
        }else if ("hora".equals(jrf.getName())) {
            valor = cuentaPorCobrars.get(index).getHora();
        }
        return valor;

    }

    public void addCuentaPorCobrar(CuentaPorCobrar cuentaPorCobrar) {
        this.cuentaPorCobrars.add(cuentaPorCobrar);

    }

}
