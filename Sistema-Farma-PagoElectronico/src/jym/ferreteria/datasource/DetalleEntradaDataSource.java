package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DetalleEntradaDataSource implements JRDataSource {

    private List<DetalleEntrada> listaDetalleEntrada = new ArrayList<DetalleEntrada>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < listaDetalleEntrada.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("numeroEntrada".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getNumeroEntrada();
        } else if ("fechaEntrada".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getFechaEntrada();
        } else if ("descripcion".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getDescripcion();
        } else if ("almacen".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getAlmacen();
        } else if ("cantidad".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getCantidad();
        } else if ("costo".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getCosto();
        } else if ("total".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getTotal();
        }
        return valor;

    }

    public void addDetalleEntrada(DetalleEntrada detalleEntrada) {
        listaDetalleEntrada.add(detalleEntrada);

    }

}
