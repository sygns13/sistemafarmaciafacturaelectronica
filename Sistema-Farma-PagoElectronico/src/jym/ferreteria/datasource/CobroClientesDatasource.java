package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CobroClientesDatasource implements JRDataSource {

    private List<CobroClientes> cobroClientes = new ArrayList<CobroClientes>();
    private int index = -1;
  
    @Override
    public boolean next() throws JRException {

        return ++index < cobroClientes.size();
        
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("comprobante".equals(jrf.getName())) {
            valor = cobroClientes.get(index).getComprobante();
        }else if ("nroComprobante".equals(jrf.getName())) {
            valor = cobroClientes.get(index).getNroComprobante();
        } else if ("cliente".equals(jrf.getName())) {
            valor = cobroClientes.get(index).getCliente();
        } else if ("fechaCobro".equals(jrf.getName())) {
            valor = cobroClientes.get(index).getFechaCobro();
        } else if ("modoPago".equals(jrf.getName())) {
            valor = cobroClientes.get(index).getModoPago();
        } else if ("monto".equals(jrf.getName())) {
            valor = cobroClientes.get(index).getMonto();
        }
        return valor;

    }

    public void addCobroClientes(CobroClientes cobroClientes) {
        this.cobroClientes.add(cobroClientes);

    }

}
