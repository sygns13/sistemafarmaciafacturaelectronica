package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class InventarioDataSource implements JRDataSource {

    private List<Inventario> listaDetalleEntrada = new ArrayList<Inventario>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < listaDetalleEntrada.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("codigo".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getCodigo();
        }if ("producto".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getProducto();
        }else if ("rubro".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getRubro();
        } 
        else if ("composicion".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getComposicion();
        } else if ("presentacion".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getPresentacion();
        } else if ("laboratorio".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getLaboratorio();
        } else if ("almacen".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getAlmacen();
        } else if ("stock".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getStock();
        } else if ("preVenta".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getPreVenta();
        }else if ("preCompra".equals(jrf.getName())) {
            valor = listaDetalleEntrada.get(index).getPreCompra();
        }
        return valor;

    }

    public void addInventario(Inventario inventario) {
        listaDetalleEntrada.add(inventario);
    }
}
