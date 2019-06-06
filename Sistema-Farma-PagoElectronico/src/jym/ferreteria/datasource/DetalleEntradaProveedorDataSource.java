/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DetalleEntradaProveedorDataSource implements JRDataSource {
     private List<DetalleEntradaProveedor> listaDetalleEntradaProveedor = new ArrayList<DetalleEntradaProveedor>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < listaDetalleEntradaProveedor.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("numeroEntrada".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getNumeroEntrada();
        } else if ("fechaEntrada".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getFechaEntrada();
        } else if ("nombre".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getNombre();
        } else if ("descripcion".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getDescripcion();
        } else if ("almacen".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getAlmacen();
        } else if ("cantidad".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getCantidad();
        } else if ("costo".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getCosto();
        } else if ("total".equals(jrf.getName())) {
            valor = listaDetalleEntradaProveedor.get(index).getTotal();
        }
        return valor;

    }

    public void addDetalleEntradaProveedor(DetalleEntradaProveedor detalleEntradaProveedor) {
        listaDetalleEntradaProveedor.add(detalleEntradaProveedor);

    }

}
