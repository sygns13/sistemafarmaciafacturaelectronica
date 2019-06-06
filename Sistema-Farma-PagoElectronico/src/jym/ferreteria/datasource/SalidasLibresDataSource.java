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

public class SalidasLibresDataSource implements JRDataSource{
     private List<DetalleSalidas> listaDetalleSalidas = new ArrayList<DetalleSalidas>();
    private int index = -1;
    
    @Override
    public boolean next() throws JRException {

        return ++index < listaDetalleSalidas.size();
    }
    
    @Override

    public Object getFieldValue(JRField jrf) throws JRException {

        Object valor = null;
        if ("idretiro".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getIdretiro();
        } else if ("posicion".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getPosicion();
        } else if ("fecha".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getFecha();
        } else if ("hora".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getHora();
        } else if ("producto".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getProducto();
        } else if ("nombre".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getNombre();
        } else if ("Unidad".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getUnidad();
        }
        else if ("cantreal".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getCantreal();
        }
        else if ("motivo".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getMotivo();
        }
        else if ("nomusr".equals(jrf.getName())) {
            valor = listaDetalleSalidas.get(index).getNomusr();
        }
        return valor;

    }
    
     public void addDetalleSalida(DetalleSalidas detalleSalida) {
        listaDetalleSalidas.add(detalleSalida);

    }
    
    
}
