package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class VentaDetalleProductoDataSource implements JRDataSource {

    private List<VentaDetalleProducto> detalleVentaProducto = new ArrayList<VentaDetalleProducto>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < detalleVentaProducto.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getFecha();
        } else if ("numeroComprobante".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getNumeroComprobante();
        } else if ("descripcion".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getDescripcion();
        } else if ("cantidad".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getCantidad();
        } else if ("precio".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getPrecio();
        } else if ("total".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getTotal();
        }else if ("hora".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getHora();
        }else if ("descuento".equals(jrf.getName())) {
            valor = detalleVentaProducto.get(index).getDescuento();
        }
        return valor;

    }

    public void addDetalleVentaProducto(VentaDetalleProducto detalleVentaProducto) {
        this.detalleVentaProducto.add(detalleVentaProducto);

    }

}
