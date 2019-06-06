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

import jym.ferreteria.clases.Controlador;


/**
 *
 * @author crist
 */
public class GenComprobante {
    
    private static Controlador control = new Controlador();
    
    public void GenerarResumen(String idResumen, String razonSocial, String ruc, String fechaEmi, String estado){
        
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
        
        ResElectronica.generarXMLZipiadoBoleta(items, detdocelec, idResumen,docu_codigo,estado);
    }
    
    public void GenerarCOmprobante(String docu_codigo, String idExterno, String empr_razonsocial, String empr_ubigeo, String empr_nombrecomercial, String empr_direccion, String empr_provincia, String empr_departamento, String empr_distrito, String
empr_pais, String empr_nroruc, String empr_tipodoc, String clie_numero, String clie_tipodoc, String clie_nombre, String docu_fecha, String docu_tipodocumento, String docu_numero, String docu_moneda, String docu_gravada, String
docu_inafecta, String docu_exonerada, String docu_gratuita, String docu_descuento, String docu_subtotal, String docu_total, String docu_igv, String tasa_igv, String docu_isc, String tasa_isc, String docu_otrostributos, String
 tasa_otrostributos, String docu_otroscargos, String docu_percepcion, String hashcode, String cdr, String cdr_nota, String cdr_observacion, String docu_enviaws, String docu_proce_status, String docu_proce_fecha, String
 docu_link_pdf, String docu_link_cdr, String docu_link_xml, String clie_correo_cpe1, String clie_correo_cpe2, String idventaDto, String leyenda_codigo , String leyenda_texto){
    
         DocumentoCabBean items = new DocumentoCabBean();
        // Empresa o emisor
        items.setEmpr_tipodoc(empr_tipodoc);//RUC obligatorio emisor
        items.setEmpr_nroruc(empr_nroruc);
        items.setEmpr_razonsocial(empr_razonsocial);
        items.setEmpr_nombrecomercial(empr_nombrecomercial);
        items.setEmpr_direccion(empr_direccion);
        items.setEmpr_distrito(empr_distrito);
        items.setEmpr_provincia(empr_provincia);
        items.setEmpr_departamento(empr_departamento);
        items.setEmpr_ubigeo(empr_ubigeo);
        items.setEmpr_pais(empr_pais);
        items.setDocu_enviaws(docu_enviaws);
        
        // Cliente o receptor 
        items.setClie_tipodoc(clie_tipodoc); // Sin docu. "-" solo se usa DNI = "1"// anexo de catalogos
        items.setClie_numero(clie_numero);
        items.setClie_nombre(clie_nombre);
        
        //documento
        // Cabecera
        items.setDocu_tipodocumento(docu_tipodocumento); // anexo de catalogos 03 refiere a Boleta de Venta Electronica
        items.setDocu_numero(docu_numero); 
        items.setDocu_fecha(docu_fecha); // formato segun sunat
        items.setDocu_gravada(docu_gravada);
        items.setDocu_igv(docu_igv);
        items.setDocu_descuento(docu_descuento);
        items.setDocu_exonerada(docu_exonerada);
        items.setDocu_gratuita(docu_gratuita);
        items.setDocu_inafecta(docu_inafecta);
        items.setDocu_isc(docu_isc);
        items.setDocu_moneda(docu_moneda);
        items.setDocu_otroscargos(docu_otroscargos);
        items.setDocu_otrostributos(docu_otrostributos);
        items.setDocu_total(docu_total);
        items.setDocu_subtotal(docu_subtotal);
        
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
        detdoc.setItem_moneda(docu_moneda);
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
            
     /*   DocumentodetBean detdoc = new DocumentodetBean();
        List<DocumentodetBean> detdocelec = new ArrayList<>();
        
        detdoc.setItem_orden("1");
        detdoc.setItem_codproducto("12");
        detdoc.setItem_descripcion("Polos M");
        detdoc.setItem_unidad("NIU");
        detdoc.setItem_cantidad("10.00");
        detdoc.setItem_moneda("PEN");
        detdoc.setItem_pventa("25.00");
        detdoc.setItem_ti_subtotal("250.00");
        detdoc.setItem_pventa_no_onerosa("0.00");
        detdoc.setItem_ti_igv("5.20");
        detdoc.setItem_afectacion("10");
                
        detdocelec.add(detdoc);
        
        detdoc = new DocumentodetBean();        
        
        detdoc.setItem_orden("2");
        detdoc.setItem_codproducto("ZZer12");
        detdoc.setItem_descripcion("Jeans M rojo");
        detdoc.setItem_unidad("NIU");
        detdoc.setItem_cantidad("12.00");
        detdoc.setItem_moneda("PEN");
        detdoc.setItem_pventa("50.00");
        detdoc.setItem_ti_subtotal("600.00");
        detdoc.setItem_pventa_no_onerosa("0.00");
        detdoc.setItem_ti_igv("5.32");
        detdoc.setItem_afectacion("10"); //Gravado - Operación Onerosa
        
        detdocelec.add(detdoc);
*/
        //Leyendas
        Leyenda leyenda = new Leyenda();
        List<Leyenda> leyendas = new ArrayList<>();
        
        leyenda.setLeyendaCodigo(leyenda_codigo);
        leyenda.setLeyendaTexto(leyenda_texto);
        
        leyendas.add(leyenda);
        
        BolElectronica.generarXMLZipiadoBoleta(items, detdocelec, leyendas,docu_codigo);
        

    
    }
}
