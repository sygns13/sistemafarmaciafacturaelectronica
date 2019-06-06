/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.ws;

import com.org.factories.Util;
import com.org.model.beans.DocumentoCabResBean;
import com.org.model.beans.DocumentodetResBean;
import com.org.model.beans.Leyenda;
import com.org.util.GeneralFunctions;
import com.org.util.HeaderHandlerResolver;
import com.org.util.LecturaXML;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.security.KeyStore;
import java.security.PrivateKey;

import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;

import jym.ferreteria.clases.Controlador;
import pe.gob.sunat.service.StatusResponse;

public class ResElectronica {
    
    private static Log log = LogFactory.getLog(ResElectronica.class);
    private static final Controlador control = new Controlador();
    
    public static void actualhash(String id, String hash){
    String sql="update resumenboletas set hashcode='"+hash+"' where id='"+id+"';";
    control.ejecutar(sql);
    }
    
    public static void actualEnviado(List<DocumentodetResBean> detdocelec, String cdr, String nota, String estatus, String enviar, String id, String estado){
    
    String sql0="update resumenboletas set cdr='"+cdr+"', cdr_nota='"+nota+"', docstatus='"+estatus+"', enviar='"+enviar+"' where id='"+id+"';";
    control.ejecutar(sql0);   
    
    
    for (DocumentodetResBean listaDet : detdocelec) {
    String sql="update cabecera set cdr_observacion='1' where docu_codigo='"+listaDet.getDocu_codigo()+"';";
    control.ejecutar(sql);
    
    }
    
    }
    
    
    public static void actualEnviado2(String cdr, String nota, String estatus, String enviar, String id, String estado){
   
    String sql0="update resumenboletas set cdr='"+cdr+"', cdr_nota='"+nota+"', docstatus='"+estatus+"', enviar='"+enviar+"' where id='"+id+"';";
    control.ejecutar(sql0);   
    
    
    String sql1="select * from resumendetalle where idresumen='"+id+"';";
    
    String docu_codigo="";
    
      try {
            control.Base.st = control.Base.conec.createStatement();
            control.Base.rt = control.Base.st.executeQuery(sql1);
            while (control.Base.rt.next()) {
                                 
                docu_codigo=control.Base.rt.getString(3);
 
  
             String sql="update cabecera set cdr_observacion='1' where docu_codigo='"+docu_codigo+"';";
            control.ejecutar(sql);     
            }
           }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
      
   
    if(estado.equals("1")){
        JOptionPane.showMessageDialog(null, "La venta fue transmitida a SUNAT exitosamente");
    }else if(estado.equals("3")){
        JOptionPane.showMessageDialog(null, "La anulación de la venta fue transmitida a SUNAT exitosamente");
    }
    
    }
    
    public static String enviarSunat(String DigestValue, String ruc, String id, String fechaEmi, String estado){
    String res="";
    String resultado[] = new String [2];
    String unidadEnvio; // = Util.getPathZipFilesEnvio();
    String pathXMLFile;
    unidadEnvio = "d:\\\\Comprobantes-Resumen-SUNAT\\";
    String firma=DigestValue;
    String auxfec=fechaEmi.replaceAll("-", "");
    //pathXMLFile = unidadEnvio + items.getRuc()+ "-RC-" + auxfec + "-"+id+".xml";
    
     System.out.println("Enviar Zip es "+unidadEnvio+ ruc + "-RC-" + auxfec + "-"+id+".zip - ACA ESTA EL ERROR");
    resultado = enviarZipASunat(unidadEnvio, ruc + "-RC-" + auxfec + "-"+id+".zip", ruc);
                    
                   
                    System.out.println("El resultado 03 es "+resultado[0]);
                    System.out.println("El resultado 03 es "+resultado[1]);
                    
                 if(resultado[1].equals("nulo")){
                       // actualEnviado(docu_codigo,resultado[0], resultado[1],"L","E",id);
                       JOptionPane.showMessageDialog(null, "Hubo problemas de conexión a Internet o a los servidores de la SUNAT, intente enviar el comprobante luego");
                    }else if(resultado[1].length()>0){
                        
                        String sql0="update resumenboletas set cdr_nota='"+resultado[1]+"'  where id='"+id+"';";
                        control.ejecutar(sql0);
                        pedirStatus(unidadEnvio, ruc + "-RC-" + auxfec + "-"+id+".zip", ruc,resultado[1]);
                        actualEnviado2(resultado[0], resultado[1],"L","E",id,estado);
                        JOptionPane.showMessageDialog(null, "Se envió la Operación a SUNAT Correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Hubo problemas de conexión a Internet o a los servidores de la SUNAT, intente enviar el comprobante luego");
                    }
                    
                    
    
    return res;
    }

    public static String generarXMLZipiadoBoleta(DocumentoCabResBean items, List<DocumentodetResBean> detdocelec, String id, String docu_codigo, String estado) {
        log.info("generarXMLZipiadoBoleta - Inicializamos el ambiente");
        org.apache.xml.security.Init.init();
        String resultado[] = new String [2];
        String res="";

        String unidadEnvio; // = Util.getPathZipFilesEnvio();
        String pathXMLFile;
        try {
            //String nrodoc = iddocument;//"943317";// request.getParameter("nrodoc");
            log.info("generarXMLZipiadoBoleta - Extraemos datos para preparar XML ");
             unidadEnvio = "d:\\Comprobantes-Resumen-SUNAT\\";
            log.info("generarXMLZipiadoBoleta - Ruta de directorios " + unidadEnvio);
            log.info("generarXMLZipiadoBoleta - Iniciamos cabecera ");
            //crear el Xml firmado
            if (items != null) {
                String auxfec=items.getFechaEnvio().replaceAll("-", "");
                pathXMLFile = unidadEnvio + items.getRuc()+ "-RC-" + auxfec + "-"+id+".xml";
                //======================crear XML =======================
                res = creaXml(items, detdocelec, unidadEnvio,id,estado);
                String firma=LecturaXML.obtenerDigestValue(pathXMLFile);
                actualhash(id,firma);
                /*=======================ENVIO A SUNAT=============*/
                if (items.getEnviar().equals("S")) {
                    log.info("generarXMLZipiadoBoleta - Preparando para enviar a SUNAT");
                    resultado = enviarZipASunat(unidadEnvio, items.getRuc()+ "-RC-" + auxfec + "-"+id+".zip", items.getRuc());
                    System.out.println("El resultado 03 es "+resultado[0]);
                    System.out.println("El resultado 03 es "+resultado[1]);
                    
                    if(resultado[1].equals("nulo")){
                       // actualEnviado(docu_codigo,resultado[0], resultado[1],"L","E",id);
                       JOptionPane.showMessageDialog(null, "Hubo problemas de conexión a Internet o a los servidores de la SUNAT, intente enviar el comprobante luego");
                    }else if(resultado[1].length()>0){
                        String sql0="update resumenboletas set cdr_nota='"+resultado[1]+"'  where id='"+id+"';";
                        control.ejecutar(sql0);
                        pedirStatus(unidadEnvio, items.getRuc() + "-RC-" + auxfec + "-"+id+".zip", items.getRuc(),resultado[1]);
                        actualEnviado(detdocelec,resultado[0], resultado[1],"L","E",id,estado);
                        JOptionPane.showMessageDialog(null, "Se envió la Operación a SUNAT Correctamente");
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Hubo problemas de conexión a Internet o a los servidores de la SUNAT, intente enviar el comprobante luego");
                    }
                    
                    
                    
                    
                } else {
                    /*este caso de boleta no se envia al sunat*/
                    log.info("generarXMLZipiadoBoleta - No se envia a SUNAT");
                    res = "0|El Comprobante numero " + items.getRuc()+ ", ha sido aceptado.";
                }

                //resultado = "termino de generar el archivo xml de la Boleta Electronica";
                
                
                //System.out.println(firma);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            res = "0100|Error al generar el archivo de formato xml de la Boleta.";
            log.error("generarXMLZipiadoBoleta - error  " + ex.toString());
        }
//
//        try {
//            LecturaXML.guardarProcesoEstado(nrodoc, "O", resultado.split("\\|", 0), conn);
//        } catch (SQLException ex) {
//            Logger.getLogger(BolElectronica.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return res;
    }

    public static String[] enviarZipASunat(String path, String zipFileName, String vruc) {
        String resultado[] = new String [2];
        resultado[0]="";
        String sws = "3";
        log.info("enviarASunat - Prepara ambiente: " + sws+"/"+zipFileName);
        try {
            
            //zipFileName="20480072872-RC-20171218-900.zip";
            javax.activation.FileDataSource fileDataSource = new javax.activation.FileDataSource(path + zipFileName);
            javax.activation.DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            String respuestaSunat = null;
            //================Enviando a sunat
            switch (sws) {
                case "1":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe ws1 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver1 = new HeaderHandlerResolver();
                    handlerResolver1.setVruc(vruc);
                    ws1.setHandlerResolver(handlerResolver1);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService port1 = ws1.getBillServicePort();
                    respuestaSunat = port1.sendSummary(zipFileName, dataHandler);
                    log.info("enviarASunat - Ambiente Beta: " + sws);
                    break;
                case "2":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa ws2 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa();
                    HeaderHandlerResolver handlerResolver2 = new HeaderHandlerResolver();
                    handlerResolver2.setVruc(vruc);
                    ws2.setHandlerResolver(handlerResolver2);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService port2 = ws2.getBillServicePort();
                    respuestaSunat = port2.sendSummary(zipFileName, dataHandler);
                    log.info("enviarASunat - Ambiente QA " + sws);
                    break;
                case "3":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe ws3 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver3 = new HeaderHandlerResolver();
                    handlerResolver3.setVruc(vruc);
                    ws3.setHandlerResolver(handlerResolver3);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService port3 = ws3.getBillServicePort();
                    respuestaSunat = port3.sendSummary(zipFileName, dataHandler);
                    log.info("enviarASunat - Ambiente Produccion " + sws);
                    break;
            }

//            javax.activation.FileDataSource fileDataSource = new javax.activation.FileDataSource(path + zipFileName);
//            javax.activation.DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            //================Grabando la respuesta de sunat en archivo ZIP solo si es nulo
           /* String pathRecepcion = "d:\\envioPruebaResumen\\";
            FileOutputStream fos = new FileOutputStream(pathRecepcion + "R-" + zipFileName);
            fos.write(respuestaSunat);
            fos.close();
            //================Descompremiendo el zip de Sunat
            log.info("enviarASunat - Descomprimiendo CDR " + pathRecepcion + "R-" + zipFileName);
            ZipFile archive = new ZipFile(pathRecepcion + "R-" + zipFileName);
            Enumeration e = archive.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                File file = new File(pathRecepcion, entry.getName());
                if (!file.isDirectory()) {
                    if (entry.isDirectory() && !file.exists()) {
                        file.mkdirs();
                    } else {
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        InputStream in = archive.getInputStream(entry);
                        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));

                        byte[] buffer = new byte[8192];
                        int read;
                        while (-1 != (read = in.read(buffer))) {
                            out.write(buffer, 0, read);
                        }
                        in.close();
                        out.close();
                    }
                }
            }
            archive.close();
            //================leeyendo la resuesta de Sunat
            zipFileName = zipFileName.substring(0, zipFileName.indexOf(".zip"));
            log.info("enviarASunat - Lectura del contenido del CDR ");
            resultado = LecturaXML.getRespuestaSunat(pathRecepcion + "R-" + zipFileName + ".xml");*/
            System.out.println("==>El envio del Zip a sunat fue exitoso");
            log.info("enviarASunat - Envio a Sunat Exitoso ");
           resultado[0]=zipFileName;
           
           if(respuestaSunat==null){
               resultado[1]="nulo";
           }else{
               resultado[1]=respuestaSunat;
           }
           
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            System.out.println(ex.toString());
            //log.error("enviarASunat - Error " + ex.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("enviarASunat - Error " + e.toString());
        }
        
        System.out.println("Resultado:: "+resultado[0]+" / "+resultado[1]);
        return resultado;
    }
    
    
    public static String[] pedirStatus(String path, String zipFileName, String vruc, String tiket) {
        String resultado[] = new String [2];
        resultado[0]="";
        String sws = "3";
        log.info("enviarASunat - Prepara ambiente: " + sws);
        try {

            javax.activation.FileDataSource fileDataSource = new javax.activation.FileDataSource(path + zipFileName);
            javax.activation.DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            StatusResponse respuestaSunat = null;
            //================Enviando a sunat
            switch (sws) {
                case "1":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe ws1 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver1 = new HeaderHandlerResolver();
                    handlerResolver1.setVruc(vruc);
                    ws1.setHandlerResolver(handlerResolver1);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService port1 = ws1.getBillServicePort();
                    respuestaSunat = port1.getStatus(tiket);
                    log.info("enviarASunat - Ambiente Beta: " + sws);
                    break;
                case "2":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa ws2 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa();
                    HeaderHandlerResolver handlerResolver2 = new HeaderHandlerResolver();
                    handlerResolver2.setVruc(vruc);
                    ws2.setHandlerResolver(handlerResolver2);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService port2 = ws2.getBillServicePort();
                    respuestaSunat = port2.getStatus(tiket);
                    log.info("enviarASunat - Ambiente QA " + sws);
                    break;
                case "3":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe ws3 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver3 = new HeaderHandlerResolver();
                    handlerResolver3.setVruc(vruc);
                    ws3.setHandlerResolver(handlerResolver3);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService port3 = ws3.getBillServicePort();
                    respuestaSunat = port3.getStatus(tiket);
                    log.info("enviarASunat - Ambiente Produccion " + sws);
                    log.info(handlerResolver3);
                    break;
            }

//            javax.activation.FileDataSource fileDataSource = new javax.activation.FileDataSource(path + zipFileName);
//            javax.activation.DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            //================Grabando la respuesta de sunat en archivo ZIP solo si es nulo
            String pathRecepcion = "d:\\\\Comprobantes-Resumen-SUNAT\\";
            FileOutputStream fos = new FileOutputStream(pathRecepcion + "R-" + zipFileName);
            fos.write(respuestaSunat.getContent());
            fos.close();
            //================Descompremiendo el zip de Sunat
            log.info("enviarASunat - Descomprimiendo CDR " + pathRecepcion + "R-" + zipFileName);
            ZipFile archive = new ZipFile(pathRecepcion + "R-" + zipFileName);
            Enumeration e = archive.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                File file = new File(pathRecepcion, entry.getName());
                if (!file.isDirectory()) {
                    if (entry.isDirectory() && !file.exists()) {
                        file.mkdirs();
                    } else {
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        InputStream in = archive.getInputStream(entry);
                        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));

                        byte[] buffer = new byte[8192];
                        int read;
                        while (-1 != (read = in.read(buffer))) {
                            out.write(buffer, 0, read);
                        }
                        in.close();
                        out.close();
                    }
                }
            }
            archive.close();
            //================leeyendo la resuesta de Sunat
            zipFileName = zipFileName.substring(0, zipFileName.indexOf(".zip"));
            log.info("enviarASunat - Lectura del contenido del CDR ");
            resultado = LecturaXML.getRespuestaSunat(pathRecepcion + "R-" + zipFileName + ".xml");
            System.out.println("==>El envio del Zip a sunat fue exitoso");
            log.info("enviarASunat - Envio a Sunat Exitoso ");
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            System.out.println(ex.toString());
            //log.error("enviarASunat - Error " + ex.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("enviarASunat - Error " + e.toString());
        }
        
        System.out.println("Resoltado 02: "+resultado[0]);
        return resultado;
    }

    private static String creaXml(DocumentoCabResBean items, List<DocumentodetResBean> detdocelec, String unidadEnvio, String id, String estado) {
        String resultado = "";
        try {
            ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");
            //Parametros del keystore
            
            String auxfec=items.getFechaEnvio().replaceAll("-", "");
           
             String keystoreType = "JKS";
            //String keystoreFile = "d:\\envio2\\MiAlmacena.jks";
            String keystoreFile= System.getProperty("user.dir") + "/firma/MiAlmacena.jks";
            String keystorePass = "miAlmacen";
            String privateKeyAlias = "miAlmacen";
            String privateKeyPass = "miAlmacen";
            String certificateAlias = "miAlmacen";            
            log.info("generarXMLResZipiadoBoleta - Lectura de certificado ");
            CDATASection cdata;
            log.info("generarXMLResZipiadoBoleta - Iniciamos la generacion del XML");
            String pathXMLFile = unidadEnvio + items.getRuc()+ "-RC-" + auxfec + "-"+id+".xml";
            File signatureFile = new File(pathXMLFile);
            ///////////////////Creación del certificado//////////////////////////////
            KeyStore ks = KeyStore.getInstance(keystoreType);
            FileInputStream fis = new FileInputStream(keystoreFile);
            ks.load(fis, keystorePass.toCharArray());
            //obtener la clave privada para firmar
            PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias, privateKeyPass.toCharArray());
            if (privateKey == null) {
                throw new RuntimeException("Private key is null");
            }
            X509Certificate cert = (X509Certificate) ks.getCertificate(certificateAlias);
            //////////////////////////////////////////////////
            javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            //Firma XML genera espacio para los nombres o tag
            dbf.setNamespaceAware(true);
            javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.newDocument();
            ////////////////////////////////////////////////// 
            log.info("generarXMLZipiadoBoleta - cabecera XML ");
            Element envelope = doc.createElementNS("", "SummaryDocuments");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns", "urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            

            
            envelope.setAttribute("xsi:schemaLocation", "urn:sunat:names:specification:ubl:peru:schema:xsd:InvoiceSummary-1\n" +
"D:\\UBL_SUNAT\\SUNAT_xml_20110112\\20110112\\xsd\\maindoc\\UBLPE-InvoiceSummary-1.0.xsd");
            
            envelope.appendChild(doc.createTextNode("\n"));
            //doc.appendChild(doc.createComment(" Preamble "));
            doc.appendChild(envelope);
            //doc.appendChild(doc.createComment(" Postamble "));

            Element UBLExtensions = doc.createElementNS("", "ext:UBLExtensions");
            envelope.appendChild(UBLExtensions);
            Element UBLExtension2 = doc.createElementNS("", "ext:UBLExtension");
            UBLExtension2.appendChild(doc.createTextNode("\n"));
            Element ExtensionContent2 = doc.createElementNS("", "ext:ExtensionContent");
            ExtensionContent2.appendChild(doc.createTextNode("\n"));
            //2do grupo
            Element UBLExtension = doc.createElementNS("", "ext:UBLExtension");
            envelope.appendChild(UBLExtension);
            Element ExtensionContent = doc.createElementNS("", "ext:ExtensionContent");
            envelope.appendChild(ExtensionContent);

            Element AdditionalInformation = doc.createElementNS("", "sac:AdditionalInformation");
            envelope.appendChild(AdditionalInformation);
            AdditionalInformation.appendChild(doc.createTextNode("\n"));


            //El baseURI es la URI que se utiliza para anteponer a URIs relativos
            String BaseURI = signatureFile.toURI().toURL().toString();
            //Crea un XML Signature objeto desde el documento, BaseURI and signature algorithm (in this case RSA)
            //XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA); Cadena URI que se ajusta a la sintaxis URI y representa el archivo XML de entrada
            XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);

            ExtensionContent.appendChild(sig.getElement());
            UBLExtension.appendChild(ExtensionContent);
            UBLExtensions.appendChild(UBLExtension);
            UBLExtensions.appendChild(UBLExtension2);
            UBLExtension2.appendChild(ExtensionContent2);
            ExtensionContent2.appendChild(AdditionalInformation);

//bloque1
            Element UBLVersionID = doc.createElementNS("", "cbc:UBLVersionID");
            envelope.appendChild(UBLVersionID);
            UBLVersionID.appendChild(doc.createTextNode("2.0"));

            Element CustomizationID = doc.createElementNS("", "cbc:CustomizationID");
            envelope.appendChild(CustomizationID);
            CustomizationID.appendChild(doc.createTextNode("1.1"));

            Element ID5 = doc.createElementNS("", "cbc:ID");
            envelope.appendChild(ID5);
            ID5.appendChild(doc.createTextNode("RC-"+auxfec+"-"+id));
            
            Element ReferenceDate =doc.createElementNS("","cbc:ReferenceDate");
            envelope.appendChild(ReferenceDate);
            ReferenceDate.appendChild(doc.createTextNode(items.getFechaEmi().trim()));

            Element IssueDate = doc.createElementNS("", "cbc:IssueDate");
            envelope.appendChild(IssueDate);
            IssueDate.appendChild(doc.createTextNode(items.getFechaEnvio().trim()));
            




//bloque2 cac:Signature--------------------------------------------------------
            Element Signature = doc.createElementNS("", "cac:Signature");
            envelope.appendChild(Signature);
            Signature.appendChild(doc.createTextNode("\n"));

            Element ID6 = doc.createElementNS("", "cbc:ID");
            Signature.appendChild(ID6);
            ID6.appendChild(doc.createTextNode(items.getRuc().trim()));

            Element SignatoryParty = doc.createElementNS("", "cac:SignatoryParty");
            Signature.appendChild(SignatoryParty);
            SignatoryParty.appendChild(doc.createTextNode("\n"));

            Element PartyIdentification = doc.createElementNS("", "cac:PartyIdentification");
            SignatoryParty.appendChild(PartyIdentification);
            PartyIdentification.appendChild(doc.createTextNode("\n"));

            Element ID7 = doc.createElementNS("", "cbc:ID");
            PartyIdentification.appendChild(ID7);
            ID7.appendChild(doc.createTextNode(items.getRuc().trim()));

            Element PartyName = doc.createElementNS("", "cac:PartyName");
            SignatoryParty.appendChild(PartyName);
            PartyName.appendChild(doc.createTextNode("\n"));

            Element Name = doc.createElementNS("", "cbc:Name");
            PartyName.appendChild(Name);
            cdata = doc.createCDATASection(items.getRazonsocial().trim());
            Name.appendChild(cdata);

            Element DigitalSignatureAttachment = doc.createElementNS("", "cac:DigitalSignatureAttachment");
            Signature.appendChild(DigitalSignatureAttachment);
            DigitalSignatureAttachment.appendChild(doc.createTextNode("\n"));

            Element ExternalReference = doc.createElementNS("", "cac:ExternalReference");
            DigitalSignatureAttachment.appendChild(ExternalReference);
            ExternalReference.appendChild(doc.createTextNode("\n"));

            Element URI = doc.createElementNS("", "cbc:URI");
            ExternalReference.appendChild(URI);
            URI.appendChild(doc.createTextNode(items.getRuc().trim()));
//bloque3 cac:AccountingSupplierParty-----------------------------------------

            Element AccountingSupplierParty = doc.createElementNS("", "cac:AccountingSupplierParty");
            envelope.appendChild(AccountingSupplierParty);
            AccountingSupplierParty.appendChild(doc.createTextNode("\n"));

            Element CustomerAssignedAccountID = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);
            CustomerAssignedAccountID.appendChild(doc.createTextNode(items.getRuc().trim()));

            Element AdditionalAccountID = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingSupplierParty.appendChild(AdditionalAccountID);
            AdditionalAccountID.appendChild(doc.createTextNode("6"));
//***********************************************************
            Element Party = doc.createElementNS("", "cac:Party");
            AccountingSupplierParty.appendChild(Party);
            Party.appendChild(doc.createTextNode("\n"));

            Element PartyName1 = doc.createElementNS("", "cac:PartyLegalEntity");
            Party.appendChild(PartyName1);//se anade al grupo party
            PartyName1.appendChild(doc.createTextNode("\n"));

            Element Name2 = doc.createElementNS("", "cbc:RegistrationName");
            PartyName1.appendChild(Name2);//se anade al grupo partyname1
            cdata = doc.createCDATASection(items.getRazonsocial().trim());
            Name2.appendChild(cdata);

          /*  Element PostalAddress = doc.createElementNS("", "cac:PostalAddress");
            Party.appendChild(PostalAddress);//se anade al grupo party
            PostalAddress.appendChild(doc.createTextNode("\n"));

            Element ID8 = doc.createElementNS("", "cbc:ID");
            PostalAddress.appendChild(ID8);//se anade al grupo PostalAddress
            ID8.appendChild(doc.createTextNode(items.getEmpr_ubigeo().trim()));

            Element StreetName = doc.createElementNS("", "cbc:StreetName");
            PostalAddress.appendChild(StreetName);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(items.getEmpr_direccion().trim());
            StreetName.appendChild(cdata);

            Element CityName = doc.createElementNS("", "cbc:CityName");
            PostalAddress.appendChild(CityName);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(items.getEmpr_provincia().trim());
            CityName.appendChild(cdata);

            Element CountrySubentity = doc.createElementNS("", "cbc:CountrySubentity");
            PostalAddress.appendChild(CountrySubentity);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(items.getEmpr_departamento().trim());
            CountrySubentity.appendChild(cdata);

            Element District = doc.createElementNS("", "cbc:District");
            PostalAddress.appendChild(District);//se anade al grupo PostalAddress
            cdata = doc.createCDATASection(items.getEmpr_distrito().trim());
            District.appendChild(cdata);

            Element Country = doc.createElementNS("", "cac:Country");
            PostalAddress.appendChild(Country);//se anade al grupo PostalAddress
            Country.appendChild(doc.createTextNode("\n"));

            Element IdentificationCode = doc.createElementNS("", "cbc:IdentificationCode");
            Country.appendChild(IdentificationCode);//se anade al grupo Country
            cdata = doc.createCDATASection(items.getEmpr_pais().trim());
            IdentificationCode.appendChild(cdata);

            Element PartyLegalEntity = doc.createElementNS("", "cac:PartyLegalEntity");
            Party.appendChild(PartyLegalEntity);//se anade al grupo party
            PartyLegalEntity.appendChild(doc.createTextNode("\n"));

            Element RegistrationName = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity.appendChild(RegistrationName);//se anade al grupo Country
            cdata = doc.createCDATASection(items.getEmpr_razonsocial().trim());
            RegistrationName.appendChild(cdata);*/
// bloque4
       /*     Element AccountingCustomerParty = doc.createElementNS("", "cac:AccountingCustomerParty");
            envelope.appendChild(AccountingCustomerParty);
            AccountingCustomerParty.appendChild(doc.createTextNode("\n"));

            Element CustomerAssignedAccountID1 = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingCustomerParty.appendChild(CustomerAssignedAccountID1);//se anade al grupo AccountingCustomerParty
            CustomerAssignedAccountID1.appendChild(doc.createTextNode(items.getClie_numero().trim()));

            Element AdditionalAccountID1 = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingCustomerParty.appendChild(AdditionalAccountID1);//se anade al grupo AccountingCustomerParty
            AdditionalAccountID1.appendChild(doc.createTextNode(items.getClie_tipodoc().trim()));

            Element Party1 = doc.createElementNS("", "cac:Party");
            AccountingCustomerParty.appendChild(Party1);//se anade al grupo AccountingCustomerParty
            Party1.appendChild(doc.createTextNode("\n"));

            Element PartyLegalEntity1 = doc.createElementNS("", "cac:PartyLegalEntity");
            Party1.appendChild(PartyLegalEntity1);//se anade al grupo Party1
            PartyLegalEntity1.appendChild(doc.createTextNode("\n"));
            Element RegistrationName1 = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity1.appendChild(RegistrationName1);//se anade al grupo PartyLegalEntity1
            cdata = doc.createCDATASection(items.getClie_nombre().trim());
            RegistrationName1.appendChild(cdata);*/

//bloque 5
          /*  Element TaxTotal = doc.createElementNS("", "cac:TaxTotal");
            envelope.appendChild(TaxTotal);
            TaxTotal.appendChild(doc.createTextNode("\n"));

            Element TaxAmount = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmount.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
            TaxAmount.setIdAttributeNS(null, "currencyID", true);
            TaxTotal.appendChild(TaxAmount);//se anade al grupo TaxTotal
            TaxAmount.appendChild(doc.createTextNode(items.getDocu_igv().trim()));

            Element TaxSubtotal = doc.createElementNS("", "cac:TaxSubtotal");
            TaxTotal.appendChild(TaxSubtotal);//se anade al grupo TaxTotal
            TaxSubtotal.appendChild(doc.createTextNode("\n"));

            Element TaxAmount1 = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmount1.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
            TaxAmount1.setIdAttributeNS(null, "currencyID", true);
            TaxSubtotal.appendChild(TaxAmount1);//se anade al grupo TaxSubtotal
            TaxAmount1.appendChild(doc.createTextNode(items.getDocu_igv().trim()));

            Element TaxCategory = doc.createElementNS("", "cac:TaxCategory");
            TaxSubtotal.appendChild(TaxCategory);//se anade al grupo TaxSubtotal
            TaxCategory.appendChild(doc.createTextNode("\n"));

            Element TaxScheme = doc.createElementNS("", "cac:TaxScheme");
            TaxCategory.appendChild(TaxScheme);//se anade al grupo TaxCategory
            TaxScheme.appendChild(doc.createTextNode("\n"));

            Element ID9 = doc.createElementNS("", "cbc:ID");
            TaxScheme.appendChild(ID9);//se anade al grupo TaxScheme
            ID9.appendChild(doc.createTextNode("1000")); 

            Element Name3 = doc.createElementNS("", "cbc:Name");
            TaxScheme.appendChild(Name3);//se anade al grupo TaxScheme
            Name3.appendChild(doc.createTextNode("IGV"));

            Element TaxTypeCode = doc.createElementNS("", "cbc:TaxTypeCode");
            TaxScheme.appendChild(TaxTypeCode);//se anade al grupo TaxScheme
            TaxTypeCode.appendChild(doc.createTextNode("VAT"));*/
//bloque 6     
          /*  Element LegalMonetaryTotal = doc.createElementNS("", "cac:LegalMonetaryTotal");
            envelope.appendChild(LegalMonetaryTotal);
            LegalMonetaryTotal.appendChild(doc.createTextNode("\n"));*/

         /*   if (!items.getDocu_descuento().equals("0.00")) {
                Element AllowanceTotalAmount = doc.createElementNS("", "cbc:AllowanceTotalAmount");
                AllowanceTotalAmount.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
                AllowanceTotalAmount.setIdAttributeNS(null, "currencyID", true);
                LegalMonetaryTotal.appendChild(AllowanceTotalAmount);//se anade al grupo LegalMonetaryTotal
                AllowanceTotalAmount.appendChild(doc.createTextNode(items.getDocu_descuento().trim()));
            }*/

           /* Element PayableAmount = doc.createElementNS("", "cbc:PayableAmount");
            PayableAmount.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
            PayableAmount.setIdAttributeNS(null, "currencyID", true);
            LegalMonetaryTotal.appendChild(PayableAmount);//se anade al grupo LegalMonetaryTotal
            PayableAmount.appendChild(doc.createTextNode(items.getDocu_total().trim()));*/
//detalle factura
            log.info("generarXMLZipiadoResBoleta - Iniciamos detalle XML ");
            int cont=1;
            for (DocumentodetResBean listaDet : detdocelec) {
                Element SummaryDocumentsLine = doc.createElementNS("", "sac:SummaryDocumentsLine");
                envelope.appendChild(SummaryDocumentsLine);
                SummaryDocumentsLine.appendChild(doc.createTextNode("\n"));

                Element LineID = doc.createElementNS("", "cbc:LineID");
                SummaryDocumentsLine.appendChild(LineID);//se anade al grupo InvoiceLine
                LineID.appendChild(doc.createTextNode(String.valueOf(cont)));cont++;
                
                Element DocumentTypeCode=doc.createElementNS("","cbc:DocumentTypeCode");
                SummaryDocumentsLine.appendChild(DocumentTypeCode);
                DocumentTypeCode.appendChild(doc.createTextNode(listaDet.getDocu_tipodocumento()));

                Element ID = doc.createElementNS("", "cbc:ID");
               // ID.setAttributeNS(null, "unitCode", listaDet.geDocu_numeroSerie().trim()+listaDet.getClie_numero());
                //ID.setIdAttributeNS(null, "unitCode", true);
                SummaryDocumentsLine.appendChild(ID);//se anade al grupo InvoiceLine
                ID.appendChild(doc.createTextNode(listaDet.geDocu_numeroSerie().trim()+"-"+listaDet.getDocu_numeroNumero().trim()));
                
                Element tipoDoc = doc.createElementNS("", "cbc:DocumentTypeCode");
                SummaryDocumentsLine.appendChild(ID);//se anade al grupo InvoiceLine
                tipoDoc.appendChild(doc.createTextNode("03"));  //TIPO DE DOCUMENTO DE LINEA

                
                Element DocumentSerialID=doc.createElementNS("","sac:DocumentSerialID");
                SummaryDocumentsLine.appendChild(DocumentSerialID);
                DocumentSerialID.appendChild(doc.createTextNode(listaDet.geDocu_numeroSerie().trim()));
                
                Element StartDocumentNumberID=doc.createElementNS("","sac:StartDocumentNumberID");
                SummaryDocumentsLine.appendChild(StartDocumentNumberID);
                StartDocumentNumberID.appendChild(doc.createTextNode(items.getNumIni().trim()));
                
                Element EndDocumentNumberID=doc.createElementNS("","sac:EndDocumentNumberID");
                SummaryDocumentsLine.appendChild(EndDocumentNumberID);
                EndDocumentNumberID.appendChild(doc.createTextNode(items.getNumFin().trim()));
            

//
                Element AccountingCustomerParty = doc.createElementNS("", "cac:AccountingCustomerParty");
                SummaryDocumentsLine.appendChild(AccountingCustomerParty);//se anade al grupo InvoiceLine
                AccountingCustomerParty.appendChild(doc.createTextNode("\n"));

                Element CustomerAssignedAccountIDCli = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
                AccountingCustomerParty.appendChild(CustomerAssignedAccountIDCli);//se anade al grupo TaxTotal1
                CustomerAssignedAccountIDCli.appendChild(doc.createTextNode(listaDet.getClie_numero().trim()));
                
                Element AdditionalAccountIDCli = doc.createElementNS("", "cbc:AdditionalAccountID");
                AccountingCustomerParty.appendChild(AdditionalAccountIDCli);//se anade al grupo TaxTotal1
                AdditionalAccountIDCli.appendChild(doc.createTextNode(listaDet.getClie_tipodoc()));
                
               /* Element BillingReference = doc.createElementNS("", "cac:BillingReference");
                SummaryDocumentsLine.appendChild(BillingReference);//se anade al grupo InvoiceLine
                BillingReference.appendChild(doc.createTextNode("\n"));
                
                Element InvoiceDocumentReference = doc.createElementNS("", "cac:InvoiceDocumentReference");
                BillingReference.appendChild(InvoiceDocumentReference);//se anade al grupo PricingReference
                InvoiceDocumentReference.appendChild(doc.createTextNode("\n"));
                
                Element IDmod = doc.createElementNS("", "cbc:ID");
                InvoiceDocumentReference.appendChild(IDmod);//se anade al grupo AlternativeConditionPrice
                IDmod.appendChild(doc.createTextNode("boleta que se modifica"));
                
                Element DocumentTypeCodemod = doc.createElementNS("", "cbc:DocumentTypeCode");
                InvoiceDocumentReference.appendChild(DocumentTypeCodemod);//se anade al grupo AlternativeConditionPrice
                DocumentTypeCodemod.appendChild(doc.createTextNode("03"));*/

//percepcion
/*
< sac:SUNATPerceptionSummaryDocumentReference>
 <sac:SUNATPerceptionSystemCode>01</sac:SUNATPerceptionSystemCode>
 <sac:SUNATPerceptionPercent>2.00</sac:SUNATPerceptionPercent>
 <cbc:TotalInvoiceAmount currencyID="PEN">4.00</cbc:TotalInvoiceAmount>
 <sac:SUNATTotalCashed>204.00</sac:SUNATTotalCashed>
 <cbc:TaxableAmount currencyID="PEN">200.00</cbc:TaxableAmount>
</sac:SUNATPerceptionSummaryDocumentReference>*/



            Element Status = doc.createElementNS("", "cac:Status");
            SummaryDocumentsLine.appendChild(Status);//se anade al grupo InvoiceLine
            Status.appendChild(doc.createTextNode("\n"));

            Element TotalAmount = doc.createElementNS("", "sac:TotalAmount");
            TotalAmount.setAttributeNS(null, "currencyID","PEN");
            TotalAmount.setIdAttributeNS(null, "currencyID", true);
            SummaryDocumentsLine.appendChild(TotalAmount);//se anade al grupo InvoiceLine
            TotalAmount.appendChild(doc.createTextNode(listaDet.getDocu_total()));
            
            

            
            Element ConditionCode = doc.createElementNS("", "cbc:ConditionCode");
            Status.appendChild(ConditionCode);//se anade al grupo TaxTotal1
            ConditionCode.appendChild(doc.createTextNode(estado.trim())); //Condicion de la boleta
            
            

            
        //payment1    
            
            Element BillingPayment1 = doc.createElementNS("", "sac:BillingPayment");
            SummaryDocumentsLine.appendChild(BillingPayment1);
            BillingPayment1.appendChild(doc.createTextNode("\n"));
            
            Element ConditionCodeDet1 = doc.createElementNS("", "cbc:PaidAmount");
            ConditionCodeDet1.setAttributeNS(null, "currencyID","PEN");
            ConditionCodeDet1.setIdAttributeNS(null, "currencyID", true);
            BillingPayment1.appendChild(ConditionCodeDet1);
            ConditionCodeDet1.appendChild(doc.createTextNode(listaDet.getDocu_gravada()));
            
            Element InstructionIDDet1 = doc.createElementNS("", "cbc:InstructionID");
            BillingPayment1.appendChild(InstructionIDDet1);
            InstructionIDDet1.appendChild(doc.createTextNode("01"));
            
                //payment2   
            /*
            Element BillingPayment2 = doc.createElementNS("", "sac:BillingPayment");
            SummaryDocumentsLine.appendChild(BillingPayment2);
            BillingPayment2.appendChild(doc.createTextNode("\n"));
            
            Element ConditionCodeDet2 = doc.createElementNS("", "cbc:PaidAmount");
            ConditionCodeDet2.setAttributeNS(null, "currencyID","PEN");
            ConditionCodeDet2.setIdAttributeNS(null, "currencyID", true);
            BillingPayment2.appendChild(ConditionCodeDet2);
            ConditionCodeDet2.appendChild(doc.createTextNode(""));
            
            Element InstructionIDDet2 = doc.createElementNS("", "cbc:InstructionID");
            BillingPayment2.appendChild(InstructionIDDet2);
            InstructionIDDet2.appendChild(doc.createTextNode("02"));
            
            */
            //payment3  
            
            /*
            Element BillingPayment3 = doc.createElementNS("", "sac:BillingPayment");
            SummaryDocumentsLine.appendChild(BillingPayment3);
            BillingPayment3.appendChild(doc.createTextNode("\n"));
            
            Element ConditionCodeDet3 = doc.createElementNS("", "cbc:PaidAmount");
            ConditionCodeDet3.setAttributeNS(null, "currencyID","PEN");
            ConditionCodeDet3.setIdAttributeNS(null, "currencyID", true);
            BillingPayment3.appendChild(ConditionCodeDet3);
            ConditionCodeDet3.appendChild(doc.createTextNode(""));
            
            Element InstructionIDDet3 = doc.createElementNS("", "cbc:InstructionID");
            BillingPayment3.appendChild(InstructionIDDet3);
            InstructionIDDet3.appendChild(doc.createTextNode("03"));
            
            */
            
                        //payment5 
            /*
            Element BillingPayment5 = doc.createElementNS("", "sac:BillingPayment");
            SummaryDocumentsLine.appendChild(BillingPayment5);
            BillingPayment5.appendChild(doc.createTextNode("\n"));
            
            Element ConditionCodeDet5 = doc.createElementNS("", "cbc:PaidAmount");
            ConditionCodeDet5.setAttributeNS(null, "currencyID","PEN");
            ConditionCodeDet5.setIdAttributeNS(null, "currencyID", true);
            BillingPayment5.appendChild(ConditionCodeDet5);
            ConditionCodeDet5.appendChild(doc.createTextNode(listaDet.getDocu_gratuita()));
            
            Element InstructionIDDet5 = doc.createElementNS("", "cbc:InstructionID");
            BillingPayment5.appendChild(InstructionIDDet5);
            InstructionIDDet5.appendChild(doc.createTextNode("05"));*/
            
            
            //Otros cargos
            
            /*
            Element AllowanceCharge = doc.createElementNS("", "cac:AllowanceCharge");
            SummaryDocumentsLine.appendChild(AllowanceCharge);
            AllowanceCharge.appendChild(doc.createTextNode("\n"));
            
            Element ChargeIndicator = doc.createElementNS("", "cbc:ChargeIndicator");
            AllowanceCharge.appendChild(ChargeIndicator);
            ChargeIndicator.appendChild(doc.createTextNode("true"));
            
            Element Amount = doc.createElementNS("", "cbc:Amount");
            Amount.setAttributeNS(null, "currencyID","PEN");
            Amount.setIdAttributeNS(null, "currencyID", true);
            AllowanceCharge.appendChild(Amount);
            Amount.appendChild(doc.createTextNode(""));
            */
            
            
            //Taxes ISC
            
            /*
            
            Element TaxTotal = doc.createElementNS("", "cac:TaxTotal");
            SummaryDocumentsLine.appendChild(TaxTotal);
            TaxTotal.appendChild(doc.createTextNode("\n"));

            Element TaxAmount = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmount.setAttributeNS(null, "currencyID", "PEN");
            TaxAmount.setIdAttributeNS(null, "currencyID", true);
            TaxTotal.appendChild(TaxAmount);//se anade al grupo TaxTotal
            TaxAmount.appendChild(doc.createTextNode(""));

            Element TaxSubtotal = doc.createElementNS("", "cac:TaxSubtotal");
            TaxTotal.appendChild(TaxSubtotal);//se anade al grupo TaxTotal
            TaxSubtotal.appendChild(doc.createTextNode("\n"));

            Element TaxAmount1 = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmount1.setAttributeNS(null, "currencyID", "PEN");
            TaxAmount1.setIdAttributeNS(null, "currencyID", true);
            TaxSubtotal.appendChild(TaxAmount1);//se anade al grupo TaxSubtotal
            TaxAmount1.appendChild(doc.createTextNode(""));

            Element TaxCategory = doc.createElementNS("", "cac:TaxCategory");
            TaxSubtotal.appendChild(TaxCategory);//se anade al grupo TaxSubtotal
            TaxCategory.appendChild(doc.createTextNode("\n"));

            Element TaxScheme = doc.createElementNS("", "cac:TaxScheme");
            TaxCategory.appendChild(TaxScheme);//se anade al grupo TaxCategory
            TaxScheme.appendChild(doc.createTextNode("\n"));

            Element ID9 = doc.createElementNS("", "cbc:ID");
            TaxScheme.appendChild(ID9);//se anade al grupo TaxScheme
            ID9.appendChild(doc.createTextNode("2000")); 

            Element Name3 = doc.createElementNS("", "cbc:Name");
            TaxScheme.appendChild(Name3);//se anade al grupo TaxScheme
            Name3.appendChild(doc.createTextNode("ISC"));

            Element TaxTypeCode = doc.createElementNS("", "cbc:TaxTypeCode");
            TaxScheme.appendChild(TaxTypeCode);//se anade al grupo TaxScheme
            TaxTypeCode.appendChild(doc.createTextNode("EXC"));
            
            */
            //IGV
            
            Element TaxTotaligv = doc.createElementNS("", "cac:TaxTotal");
            SummaryDocumentsLine.appendChild(TaxTotaligv);
            TaxTotaligv.appendChild(doc.createTextNode("\n"));

            Element TaxAmountigv = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmountigv.setAttributeNS(null, "currencyID", "PEN");
            TaxAmountigv.setIdAttributeNS(null, "currencyID", true);
            TaxTotaligv.appendChild(TaxAmountigv);//se anade al grupo TaxTotal
            TaxAmountigv.appendChild(doc.createTextNode(listaDet.getDocu_igv()));

            Element TaxSubtotaligv = doc.createElementNS("", "cac:TaxSubtotal");
            TaxTotaligv.appendChild(TaxSubtotaligv);//se anade al grupo TaxTotal
            TaxSubtotaligv.appendChild(doc.createTextNode("\n"));

            Element TaxAmount1igv = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmount1igv.setAttributeNS(null, "currencyID", "PEN");
            TaxAmount1igv.setIdAttributeNS(null, "currencyID", true);
            TaxSubtotaligv.appendChild(TaxAmount1igv);//se anade al grupo TaxSubtotal
            TaxAmount1igv.appendChild(doc.createTextNode(listaDet.getDocu_igv()));

            Element TaxCategoryigv = doc.createElementNS("", "cac:TaxCategory");
            TaxSubtotaligv.appendChild(TaxCategoryigv);//se anade al grupo TaxSubtotal
            TaxCategoryigv.appendChild(doc.createTextNode("\n"));

            Element TaxSchemeigv = doc.createElementNS("", "cac:TaxScheme");
            TaxCategoryigv.appendChild(TaxSchemeigv);//se anade al grupo TaxCategory
            TaxSchemeigv.appendChild(doc.createTextNode("\n"));

            Element ID9igv = doc.createElementNS("", "cbc:ID");
            TaxSchemeigv.appendChild(ID9igv);//se anade al grupo TaxScheme
            ID9igv.appendChild(doc.createTextNode("1000")); 

            Element Name3igv = doc.createElementNS("", "cbc:Name");
            TaxSchemeigv.appendChild(Name3igv);//se anade al grupo TaxScheme
            Name3igv.appendChild(doc.createTextNode("IGV"));

            Element TaxTypeCodeigv = doc.createElementNS("", "cbc:TaxTypeCode");
            TaxSchemeigv.appendChild(TaxTypeCodeigv);//se anade al grupo TaxScheme
            TaxTypeCodeigv.appendChild(doc.createTextNode("VAT"));
            
            //Otros tributos
            
            /*
            
            Element TaxTotalotros = doc.createElementNS("", "cac:TaxTotal");
            SummaryDocumentsLine.appendChild(TaxTotalotros);
            TaxTotalotros.appendChild(doc.createTextNode("\n"));

            Element TaxAmountotros = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmountotros.setAttributeNS(null, "currencyID", "PEN");
            TaxAmountotros.setIdAttributeNS(null, "currencyID", true);
            TaxTotalotros.appendChild(TaxAmountotros);//se anade al grupo TaxTotal
            TaxAmountotros.appendChild(doc.createTextNode(""));

            Element TaxSubtotalotros = doc.createElementNS("", "cac:TaxSubtotal");
            TaxTotalotros.appendChild(TaxSubtotalotros);//se anade al grupo TaxTotal
            TaxSubtotalotros.appendChild(doc.createTextNode("\n"));

            Element TaxAmount1otros = doc.createElementNS("", "cbc:TaxAmount");
            TaxAmount1otros.setAttributeNS(null, "currencyID", "PEN");
            TaxAmount1otros.setIdAttributeNS(null, "currencyID", true);
            TaxSubtotalotros.appendChild(TaxAmount1otros);//se anade al grupo TaxSubtotal
            TaxAmount1otros.appendChild(doc.createTextNode(""));



            Element TaxCategoryotros = doc.createElementNS("", "cac:TaxCategory");
            TaxSubtotalotros.appendChild(TaxCategoryotros);//se anade al grupo TaxSubtotal
            TaxCategoryotros.appendChild(doc.createTextNode("\n"));

            Element TaxSchemeotros = doc.createElementNS("", "cac:TaxScheme");
            TaxCategoryotros.appendChild(TaxSchemeotros);//se anade al grupo TaxCategory
            TaxSchemeotros.appendChild(doc.createTextNode("\n"));

            Element ID9otros = doc.createElementNS("", "cbc:ID");
            TaxSchemeotros.appendChild(ID9otros);//se anade al grupo TaxScheme
            ID9otros.appendChild(doc.createTextNode("9999")); 

            Element Name3otros = doc.createElementNS("", "cbc:Name");
            TaxSchemeotros.appendChild(Name3otros);//se anade al grupo TaxScheme
            Name3otros.appendChild(doc.createTextNode("OTROS"));

            Element TaxTypeCodeotros = doc.createElementNS("", "cbc:TaxTypeCode");
            TaxSchemeotros.appendChild(TaxTypeCodeotros);//se anade al grupo TaxScheme
            TaxTypeCodeotros.appendChild(doc.createTextNode("OTH"));
             */
            }
            log.info("generarXMLZipiadoBoleta - Prepara firma digital ");
            sig.setId("Sign"+items.getRuc());
            sig.addKeyInfo(cert);
            {
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
            }
            {
                //Firmar el documento
                log.info("generarXMLZipiadoResBoleta - firma el XML ");
                sig.sign(privateKey);
            }
            //--------------------fin de construccion del xml---------------------
            ///*combinacion de firma y construccion xml////
            FileOutputStream f = new FileOutputStream(signatureFile);
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            //tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.STANDALONE, "no");
            //Writer out = new StringWriter();
            StreamResult sr = new StreamResult(f);
            tf.transform(new DOMSource(doc), sr);
            sr.getOutputStream().close();

            log.info("generarXMLZipiadoBoleta - XML creado " + pathXMLFile);
            //====================== CREAR ZIP PARA EL ENVIO A SUNAT =======================
            resultado = GeneralFunctions.crearZipRes(items, unidadEnvio, signatureFile,id);

        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = "0100|Error al generar el archivo de formato xml de Resumen de Boletas de Venta.";
            log.error("generarXMLZipiadoFactura - error  " + ex.toString());

        }
        return resultado;
    }

}
