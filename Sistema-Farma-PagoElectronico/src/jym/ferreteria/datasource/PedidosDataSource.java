package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PedidosDataSource implements JRDataSource {

    private List<Pedidos> listaPedidos = new ArrayList<Pedidos>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {
        
        return ++index<listaPedidos.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("numeroPedido".equals(jrf.getName())) {
            valor = listaPedidos.get(index).getNumeroPedido();
        } else if ("fechaRecibido".equals(jrf.getName())) {
            valor = listaPedidos.get(index).getFechaRecibido();
        } else if ("fechaPedido".equals(jrf.getName())) {
            valor = listaPedidos.get(index).getFechaPedido();
        } else if ("proveedor".equals(jrf.getName())) {
            valor = listaPedidos.get(index).getProveedor();
        } else if ("total".equals(jrf.getName())) {
            valor = listaPedidos.get(index).getTotal();
        }
        return valor;

    }

    public void addPedido(Pedidos pedidos) {
        listaPedidos.add(pedidos);

    }

}
