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

public class TopProductos implements JRDataSource{
    private List<DetalleTopProductos> listaDetalleTopProductos = new ArrayList<DetalleTopProductos>();
    
    private int index = -1;
    
    @Override
    public boolean next() throws JRException {

        return ++index < listaDetalleTopProductos.size();
    }
    
    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("idproduto".equals(jrf.getName())) {
            valor = listaDetalleTopProductos.get(index).getIdproduto();
        } else if ("producto".equals(jrf.getName())) {
            valor = listaDetalleTopProductos.get(index).getProducto();
        } else if ("nommarc".equals(jrf.getName())) {
            valor = listaDetalleTopProductos.get(index).getNommarc();
        } else if ("cantidad".equals(jrf.getName())) {
            valor = listaDetalleTopProductos.get(index).getCantidad();
        } else if ("ventas".equals(jrf.getName())) {
            valor = listaDetalleTopProductos.get(index).getVentas();
        } 
        return valor;

    }
    
    public void addDetalleTopProductos(DetalleTopProductos detalleTopProducto) {
        listaDetalleTopProductos.add(detalleTopProducto);

    }
    
    
}
