package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class EntradaCabeceraDataSource implements JRDataSource {

    private List<EntradaCabecera> listaEntrada = new ArrayList<EntradaCabecera>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {
        
        return ++index<listaEntrada.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("fechaEntrada".equals(jrf.getName())) {
            valor = listaEntrada.get(index).getFechaEntrada();
        } else if ("numeroEntrada".equals(jrf.getName())) {
            valor = listaEntrada.get(index).getNumeroEntrada();
        } else if ("numeroFactura".equals(jrf.getName())) {
            valor = listaEntrada.get(index).getNumeroFactura();
        } else if ("proveedor".equals(jrf.getName())) {
            valor = listaEntrada.get(index).getProveedor();
        } else if ("total".equals(jrf.getName())) {
            valor = listaEntrada.get(index).getTotal();
        }
        return valor;

    }

    public void addEntrada(EntradaCabecera entradaCabecera) {
        listaEntrada.add(entradaCabecera);

    }

}
