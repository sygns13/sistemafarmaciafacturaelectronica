package jym.ferreteria.datasource;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class RegularizacionStockDataSource implements JRDataSource {

    private List<RegularizacionStock> regularizacionStocks = new ArrayList<RegularizacionStock>();
    private int index = -1;

    @Override
    public boolean next() throws JRException {

        return ++index < regularizacionStocks.size();
    }

    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("numeroMovimiento".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getNumeroMovimiento();
        } else if ("fecha".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getFecha();
        } else if ("movimiento".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getMovimiento();
        } else if ("almacenEntrada".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getAlmacenEntrada();
        } else if ("almacenSalida".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getAlmacenSalida();
        } else if ("motivo".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getMotivo();
        }else if ("detalle".equals(jrf.getName())) {
            valor = regularizacionStocks.get(index).getDetalle();
        }
        return valor;

    }

    public void addRegularizacionStock(RegularizacionStock regularizacionStock) {
        regularizacionStocks.add(regularizacionStock);

    }

}
