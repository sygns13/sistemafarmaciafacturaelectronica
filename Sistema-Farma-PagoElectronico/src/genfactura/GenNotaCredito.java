/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genfactura;


import com.org.model.beans.DocumentoCabBean;
import com.org.model.beans.DocumentodetBean;
import com.org.model.beans.Leyenda;
import com.org.util.LecturaXML;
import com.org.ws.BolElectronica;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import com.org.model.beans.DocumentoCabResBean;
import com.org.model.beans.DocumentodetResBean;
import com.org.ws.ResElectronica;
import com.org.ws.NotaCreditoElectronica;

import jym.ferreteria.clases.Controlador;


/**
 *
 * @author crist
 */
public class GenNotaCredito {
    
    private static Controlador control = new Controlador();
    
    public void GenerarResumen(String idResumen, String razonSocial, String ruc, String fechaEmi){
        
        DocumentoCabResBean items = new DocumentoCabResBean();
        
        items.setIdResumen(idResumen);
        items.setRazonsocial(razonSocial);
        items.setRuc(ruc);
        items.setFechaEmi(fechaEmi);
        items.setEnviar("S");
        items.setDocstatus("N");
        
        String sql="select c.docu_codigo,r.id,c.docu_tipodocumento,left(c.docu_numero,4) as serie, c.clie_tipodoc, c.clie_numero, c.docu_gravada, c.docu_exonerada, \n" +
            " c.docu_inafecta, c.docu_otroscargos,c.docu_isc,c.docu_igv, c.docu_otrostributos, c.docu_total, c.docu_gratuita, rd.id, SUBSTRING(c.docu_numero,6,length(c.docu_numero)) as numero \n" +
            " , r.fechaEnvio from cabecera c \n" +
            " inner join resumendetalle rd on rd.docu_codigo=c.docu_codigo \n" +
            " inner join resumenboletas r on r.id=rd.idresumen \n" +
            " where r.id='"+idResumen+"';";
        
        String detresid="",docu_codigo="",idRes="",docu_tipodocumento="", docu_serie="",docu_numero="", clie_tipodoc="", clie_numero="", docu_gravada="", docu_exonerada="", docu_inafecta="", docu_otroscargos="", docu_isc="", docu_igv="", docu_otrostributos="", docu_total="", docu_gratuita="";
        
        DocumentodetResBean detdoc = new DocumentodetResBean();
        List<DocumentodetResBean> detdocelec = new ArrayList<>(); 
        
        int cont=1;
        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                docu_codigo=control.Base.rt.getString(1); 
                idRes=control.Base.rt.getString(2); 
                docu_tipodocumento=control.Base.rt.getString(3); 
                docu_serie=control.Base.rt.getString(4); 
                clie_tipodoc=control.Base.rt.getString(5); 
                clie_numero=control.Base.rt.getString(6); 
                docu_gravada=control.Base.rt.getString(7); 
                docu_exonerada=control.Base.rt.getString(8); 
                docu_inafecta=control.Base.rt.getString(9); 
                docu_otroscargos=control.Base.rt.getString(10); 
                docu_isc=control.Base.rt.getString(11); 
                docu_igv=control.Base.rt.getString(12); 
                docu_otrostributos=control.Base.rt.getString(13); 
                docu_total=control.Base.rt.getString(14); 
                docu_gratuita=control.Base.rt.getString(15); 
                detresid=control.Base.rt.getString(16);
                docu_numero=control.Base.rt.getString(17);
                
                items.setFechaEnvio(control.Base.rt.getString(18));
                
                if(cont==1){
                items.setNumIni(docu_numero);
                }
                
                items.setNumFin(docu_numero);
                
                
                if(cont>1){
            detdoc = new DocumentodetResBean();
                    }
                    
        detdoc.setDocu_codigo(docu_codigo);
        detdoc.setIdResumen(idRes);
        detdoc.setIddetalle(detresid);
        detdoc.setDocu_tipodocumento(docu_tipodocumento);
        detdoc.setDocu_numeroSerie(docu_serie);
        detdoc.setDocu_numeroNumero(docu_numero);
        detdoc.setClie_tipodoc(clie_tipodoc);
        detdoc.setClie_numero(clie_numero);
        detdoc.setDocu_gravada(docu_gravada);
        detdoc.setDocu_exonerada(docu_exonerada);
        detdoc.setDocu_inafecta(docu_inafecta);
        detdoc.setDocu_otroscargos(docu_otroscargos);
        detdoc.setDocu_isc(docu_isc);
        detdoc.setDocu_igv(docu_igv);
        detdoc.setDocu_otrostributos(docu_otrostributos);
        detdoc.setDocu_total(docu_total);
        detdoc.setDocu_gratuita(docu_gratuita);

        
                
        detdocelec.add(detdoc);
        
        cont++;

                
                }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        String estado="1";
        ResElectronica.generarXMLZipiadoBoleta(items, detdocelec, idResumen,docu_codigo,estado);
    }
    
    public void GenerarNotaCredito(String idNota,String fechaProce,String ruc,String numeroFactura, String docu_codigo, String numeroNota){
    
         DocumentoCabBean items = new DocumentoCabBean();
        // Empresa o emisor
        items.setEmpr_tipodoc("");//RUC obligatorio emisor
        items.setEmpr_nroruc("");
        items.setEmpr_razonsocial("");
        items.setEmpr_nombrecomercial("");
        items.setEmpr_direccion("");
        items.setEmpr_distrito("");
        items.setEmpr_provincia("");
        items.setEmpr_departamento("");
        items.setEmpr_ubigeo("");
        items.setEmpr_pais("");
        items.setDocu_enviaws("");
        
        // Cliente o receptor 
        items.setClie_tipodoc(""); // Sin docu. "-" solo se usa DNI = "1"// anexo de catalogos
        items.setClie_numero("");
        items.setClie_nombre("");
        
        //documento
        // Cabecera
        items.setDocu_tipodocumento(""); // anexo de catalogos 03 refiere a Boleta de Venta Electronica
        items.setDocu_numero(""); 
        items.setDocu_fecha(""); // formato segun sunat
        items.setDocu_gravada("");
        items.setDocu_igv("");
        items.setDocu_descuento("");
        items.setDocu_exonerada("");
        items.setDocu_gratuita("");
        items.setDocu_inafecta("");
        items.setDocu_isc("");
        items.setDocu_moneda("");
        items.setDocu_otroscargos("");
        items.setDocu_otrostributos("");
        items.setDocu_total("");
        items.setDocu_subtotal("");
        
        //Cabecera
        String sqlz="select * from cabecera where docu_codigo='"+docu_codigo+"';";
        
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sqlz);
            while (control.Base.rt.next()) {
                
        items.setEmpr_tipodoc(control.Base.rt.getString(12));//RUC obligatorio emisor
        items.setEmpr_nroruc(control.Base.rt.getString(11));
        items.setEmpr_razonsocial(control.Base.rt.getString(3));
        items.setEmpr_nombrecomercial(control.Base.rt.getString(5));
        items.setEmpr_direccion(control.Base.rt.getString(6));
        items.setEmpr_distrito(control.Base.rt.getString(9));
        items.setEmpr_provincia(control.Base.rt.getString(7));
        items.setEmpr_departamento(control.Base.rt.getString(8));
        items.setEmpr_ubigeo(control.Base.rt.getString(4));
        items.setEmpr_pais(control.Base.rt.getString(10));
        items.setDocu_enviaws("S");
        
        // Cliente o receptor 
        items.setClie_tipodoc(control.Base.rt.getString(14)); // Sin docu. "-" solo se usa DNI = "1"// anexo de catalogos
        items.setClie_numero(control.Base.rt.getString(13));
        items.setClie_nombre(control.Base.rt.getString(15));
        
        //documento
        // Cabecera
        items.setDocu_tipodocumento(control.Base.rt.getString(17)); // anexo de catalogos 03 refiere a Boleta de Venta Electronica
        items.setDocu_numero(control.Base.rt.getString(18)); 
        items.setDocu_fecha(control.Base.rt.getString(16)); // formato segun sunat
        items.setDocu_gravada(control.Base.rt.getString(20));
        items.setDocu_igv(control.Base.rt.getString(27));
        items.setDocu_descuento(control.Base.rt.getString(24));
        items.setDocu_exonerada(control.Base.rt.getString(22));
        items.setDocu_gratuita(control.Base.rt.getString(23));
        items.setDocu_inafecta(control.Base.rt.getString(21));
        items.setDocu_isc(control.Base.rt.getString(29));
        items.setDocu_moneda(control.Base.rt.getString(19));
        items.setDocu_otroscargos(control.Base.rt.getString(33));
        items.setDocu_otrostributos(control.Base.rt.getString(32));
        items.setDocu_total(control.Base.rt.getString(26));
        items.setDocu_subtotal(control.Base.rt.getString(25));
                
                }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
            
        
        //Detalle
        
        String sql="";
        sql="SELECT * FROM detalle d where docu_codigo='"+docu_codigo+"';";
        
        String item_orden, item_unidad, item_cantidad, item_codproducto, item_descripcion, item_afectacion, item_tipo_precio_venta, item_pventa, item_pventa_nohonerosa, item_to_subtotal, item_to_igv,item_preunitfin;
            

        DocumentodetBean detdoc = new DocumentodetBean();
        List<DocumentodetBean> detdocelec = new ArrayList<>();    
        int cont=1;
        try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql);
            while (control.Base.rt.next()) {
                  
                    item_orden=control.Base.rt.getString(3); 
                    item_unidad=control.Base.rt.getString(4); 
                    item_cantidad=control.decimalFormat(Integer.parseInt(control.Base.rt.getString(5))); 
                    item_codproducto=control.Base.rt.getString(6); 
                    item_descripcion=control.Base.rt.getString(7);
                    item_afectacion=control.Base.rt.getString(8);
                    item_tipo_precio_venta=control.Base.rt.getString(9);
                    item_pventa=control.Base.rt.getString(10);
                    item_pventa_nohonerosa=control.Base.rt.getString(11);
                    item_to_subtotal=control.Base.rt.getString(12);
                    item_to_igv=control.Base.rt.getString(13);
                    item_preunitfin=control.Base.rt.getString(14);
              
                    if(cont>1){
        detdoc = new DocumentodetBean();
                    }
                    
        detdoc.setItem_orden(item_orden);
        detdoc.setItem_codproducto(item_codproducto);
        detdoc.setItem_descripcion(item_descripcion);
        detdoc.setItem_unidad(item_unidad);
        detdoc.setItem_cantidad(item_cantidad);
        detdoc.setItem_moneda("PEN");
        detdoc.setItem_pventa(item_pventa);
        detdoc.setItem_ti_subtotal(item_to_subtotal);
        detdoc.setItem_pventa_no_onerosa(item_pventa_nohonerosa);
        detdoc.setItem_ti_igv(item_to_igv);
        detdoc.setItem_afectacion(item_afectacion);
        detdoc.setItem_preunitfin(item_preunitfin);
                
        detdocelec.add(detdoc);
        
        cont++;
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        //Leyendas
     /*   Leyenda leyenda = new Leyenda();
        List<Leyenda> leyendas = new ArrayList<>();
        
        leyenda.setLeyendaCodigo(leyenda_codigo);
        leyenda.setLeyendaTexto(leyenda_texto);
        
        leyendas.add(leyenda);*/
        
        NotaCreditoElectronica.generarXMLZipiadoBoleta(items, detdocelec,docu_codigo,idNota,fechaProce,numeroNota);
        

    
    }
}
