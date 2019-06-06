package com.org.ws;


import com.org.model.beans.DocumentoCabBean;
import com.org.model.beans.DocumentodetBean;
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

//import android.util.Base64;

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

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.util.GregorianCalendar;


import jym.ferreteria.clases.Controlador;

public class NotaCreditoElectronica {

    private static Log log = LogFactory.getLog(NotaCreditoElectronica.class);
    private static final Controlador control = new Controlador();
    
     Calendar calendar = Calendar.getInstance();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    


    
   
    
    
    
    
    public static void actualhash(String idNota, String hash){
    String sql="update notascredito set hashcode='"+hash+"' where id='"+idNota+"';";
    control.ejecutar(sql);
    }
    
    public static void actualEnviado(String idNota, String cdr, String nota, String estatus, String enviar, String docu_codigo, String numAnulado){
    String sql="update notascredito set cdr='"+cdr+"', cdr_nota='"+nota+"', docu_proce_status='"+estatus+"', docu_enviaws='"+enviar+"' where id='"+idNota+"';";
    String sql2="update cabecera set docu_proce_status='L', cdr_nota='Anulación de la Factura "+numAnulado+" aceptada con éxito' where docu_codigo='"+docu_codigo+"';";
                            
    control.ejecutar(sql);
    control.ejecutar(sql2);
    
    }
    
    public static String enviarSunat(String DigestValue, String ruc, String numero, String docu_codigo,String idNota, String numeroAnulado){
    String res="";
    String resultado[] = new String [2];
    String unidadEnvio; // = Util.getPathZipFilesEnvio();
    String pathXMLFile;
    unidadEnvio = "d:\\\\Comprobantes-NotasCredito-SUNAT\\";
    String firma=DigestValue;
    
    resultado = enviarZipASunat(unidadEnvio, ruc + "-07-" + numero + ".zip", ruc);
    
    log.info("generarXMLZipiadoNota - Ruta de directorios " +  ruc + "-07-" + numero + ".zip");
                    System.out.println("El resultado 03 es "+resultado[0]);
                    System.out.println("El resultado 03 es "+resultado[1]);
                    
                      if(resultado[0].equals("0")){
                        actualEnviado(idNota,resultado[0], resultado[1],"L","E",docu_codigo,numeroAnulado);
                        JOptionPane.showMessageDialog(null, "Nota de Crédito de Anulación de Factura: "+resultado[1]);
                    }else if(resultado[0].equals("E")){
                        actualEnviado(idNota,resultado[0], resultado[1],"E","E",docu_codigo,numeroAnulado);
                    }else if(resultado[0].length()>0){
                        actualEnviado(idNota,resultado[0], resultado[1],"A","E",docu_codigo,numeroAnulado);
                    }else{
                        JOptionPane.showMessageDialog(null, "Hubo problemas de conexión a Internet o a los servidores de la SUNAT, intente enviar la anulación del comprobante luego");
                    
                    String sql="update cabecera set docu_proce_status='AN', cdr_nota='Necesita enviar la anulación de la Factura "+numeroAnulado+" a SUNAT' where docu_codigo='"+docu_codigo+"';";
                            control.ejecutar(sql);
                    }
                    
    
    return res;
    }

    public static String generarXMLZipiadoBoleta(DocumentoCabBean items, List<DocumentodetBean> detdocelec, String docu_codigo, String idNota, String fechaAnula, String numeroNota) {
        log.info("generarXMLZipiadoBoleta - Inicializamos el ambiente");
        org.apache.xml.security.Init.init();
        String resultado[] = new String [2];
        String res="";
        
       // String ruc="10320432591";

        String unidadEnvio; // = Util.getPathZipFilesEnvio();
        String pathXMLFile;
        try {
            //String nrodoc = iddocument;//"943317";// request.getParameter("nrodoc");
            log.info("generarXMLZipiadoNota - Extraemos datos para preparar XML ");
             unidadEnvio = "d:\\\\Comprobantes-NotasCredito-SUNAT\\";
            log.info("generarXMLZipiadoNota - Ruta de directorios " + unidadEnvio);
            log.info("generarXMLZipiadoNota - Iniciamos cabecera ");
            //crear el Xml firmado
            if (idNota != null) {
                pathXMLFile = unidadEnvio + items.getEmpr_nroruc() + "-07-" + numeroNota + ".xml";
                //======================crear XML =======================
                res = creaXml(items, detdocelec, docu_codigo, unidadEnvio,idNota,fechaAnula,numeroNota);
                String firma=LecturaXML.obtenerDigestValue(pathXMLFile);
                actualhash(idNota,firma);
                /*=======================ENVIO A SUNAT=============*/
                if (items.getDocu_enviaws().equals("S")) {
                    
                    if(items.getDocu_tipodocumento()=="03"){
                        
                        resultado[0]="0";
                        resultado[1]="La Boleta se enviará en el resumen de boletas";
                    }
                    else{
                        log.info("generarXMLZipiadoNota- Preparando para enviar a SUNAT");
                    resultado = enviarZipASunat(unidadEnvio, items.getEmpr_nroruc() + "-07-" + numeroNota + ".zip", items.getEmpr_nroruc());
                    System.out.println("El resultado 03 es "+resultado[0]);
                    System.out.println("El resultado 03 es "+resultado[1]);
                    
                    }
                    
                    
                    if(resultado[0].equals("0")){
                        actualEnviado(idNota,resultado[0], resultado[1],"L","E",docu_codigo,items.getDocu_numero());
                        JOptionPane.showMessageDialog(null, "Nota de Crédito de Anulación de Factura: "+resultado[1]);
                    }else if(resultado[0].equals("E")){
                        actualEnviado(idNota,resultado[0], resultado[1],"E","E",docu_codigo,items.getDocu_numero());
                    }else if(resultado[0].length()>0){
                        actualEnviado(idNota,resultado[0], resultado[1],"A","E",docu_codigo,items.getDocu_numero());
                    }else{
                        JOptionPane.showMessageDialog(null, "Hubo problemas de conexión a Internet o a los servidores de la SUNAT, intente enviar la anulación del comprobante luego");
                    
                    String sql="update cabecera set docu_proce_status='AN', cdr_nota='Necesita enviar la anulación de la Factura "+items.getDocu_numero()+" a SUNAT' where docu_codigo='"+docu_codigo+"';";
                            control.ejecutar(sql);
                    }
                    
                    
                    
                    
                } else {
                    /*este caso de boleta no se envia al sunat*/
                    log.info("generarXMLZipiadoBoleta - No se envia a SUNAT");
                    res = "0|El Comprobante numero " + items.getDocu_numero() + ", ha sido aceptado.";
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
        log.info("enviarASunat - Prepara ambiente: " + sws);
        try {

            javax.activation.FileDataSource fileDataSource = new javax.activation.FileDataSource(path + zipFileName);
            javax.activation.DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            byte[] respuestaSunat = null;
            //================Enviando a sunat
            switch (sws) {
                case "1":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe ws1 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver1 = new HeaderHandlerResolver();
                    handlerResolver1.setVruc(vruc);
                    ws1.setHandlerResolver(handlerResolver1);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service_bta.BillService port1 = ws1.getBillServicePort();
                    respuestaSunat = port1.sendBill(zipFileName, dataHandler);
                    log.info("enviarASunat - Ambiente Beta: " + sws);
                    break;
                case "2":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa ws2 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService_Service_sqa();
                    HeaderHandlerResolver handlerResolver2 = new HeaderHandlerResolver();
                    handlerResolver2.setVruc(vruc);
                    ws2.setHandlerResolver(handlerResolver2);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa.BillService port2 = ws2.getBillServicePort();
                    respuestaSunat = port2.sendBill(zipFileName, dataHandler);
                    log.info("enviarASunat - Ambiente QA " + sws);
                    break;
                case "3":
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe ws3 = new pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service_fe();
                    HeaderHandlerResolver handlerResolver3 = new HeaderHandlerResolver();
                    handlerResolver3.setVruc(vruc);
                    ws3.setHandlerResolver(handlerResolver3);
                    pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService port3 = ws3.getBillServicePort();
                    respuestaSunat = port3.sendBill(zipFileName, dataHandler);
                    log.info("enviarASunat - Ambiente Produccion " + sws);
                    log.info(handlerResolver3);
                    break;
            }

//            javax.activation.FileDataSource fileDataSource = new javax.activation.FileDataSource(path + zipFileName);
//            javax.activation.DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            //================Grabando la respuesta de sunat en archivo ZIP solo si es nulo
            String pathRecepcion = "d:\\\\Comprobantes-NotasCredito-SUNAT\\";
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
    /*
public static X509Certificate parseCertificate(String _headerName, HttpServletRequest _request) throws CertificateException {
    String certStr = _request.getHeader("x-clientcert");
    //before decoding we need to get rod off the prefix and suffix
    byte [] decoded = Base64.getDecoder().decode(certStr.replaceAll(X509Factory.BEGIN_CERT, "").replaceAll(X509Factory.END_CERT, ""));

    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(decoded));
    
    res = creaXml(items, detdocelec, docu_codigo, unidadEnvio,idNota,fechaAnula,numeroNota);
}*/
    private static String creaXml(DocumentoCabBean items, List<DocumentodetBean> detdocelec,String docu_codigo, String unidadEnvio, String idNota, String fechaAnula, String numeroNota) {
        String resultado = "";
        try {
           ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");

           
           String keystoreType = "JKS";
            //String keystoreFile = "d:\\envio2\\MiAlmacena.jks";
            String keystoreFile= System.getProperty("user.dir") + "/firma/MiAlmacena.jks";
            String keystorePass = "miAlmacen";
            String privateKeyAlias = "miAlmacen";
            String privateKeyPass = "miAlmacen";
            String certificateAlias = "miAlmacen";            
            log.info("generarXMLResZipiadoNota- Lectura de certificado ");
            CDATASection cdata;
            log.info("generarXMLResZipiadoNota - Iniciamos la generacion del XML");
               //pathXMLFile = unidadEnvio + items.getRuc()+ "-RC-" + auxfec + "-"+id+".xml";
            String pathXMLFile = unidadEnvio + items.getEmpr_nroruc() + "-07-" + numeroNota + ".xml";
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
            log.info("generarXMLZipiadoBoleta - cabecera XML  llega aca");
            Element envelope = doc.createElementNS("", "CreditNote");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns", "urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            envelope.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
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

    

            //El baseURI es la URI que se utiliza para anteponer a URIs relativos
            String BaseURI = signatureFile.toURI().toURL().toString();
            //Crea un XML Signature objeto desde el documento, BaseURI and signature algorithm (in this case RSA)
            //XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA); Cadena URI que se ajusta a la sintaxis URI y representa el archivo XML de entrada
            XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);

            ExtensionContent.appendChild(sig.getElement());
            
            
            //bloque1
            Element UBLVersionID = doc.createElementNS("", "cbc:UBLVersionID");
            envelope.appendChild(UBLVersionID);
            UBLVersionID.appendChild(doc.createTextNode("2.1"));

            Element CustomizationID = doc.createElementNS("", "cbc:CustomizationID");
            envelope.appendChild(CustomizationID);
            CustomizationID.appendChild(doc.createTextNode("2.0"));

            Element ID5 = doc.createElementNS("", "cbc:ID");
            envelope.appendChild(ID5);
            ID5.appendChild(doc.createTextNode(numeroNota));

            Element IssueDate = doc.createElementNS("", "cbc:IssueDate");
            envelope.appendChild(IssueDate);
            IssueDate.appendChild(doc.createTextNode(fechaAnula));
            
            Element DocumentCurrencyCode = doc.createElementNS("", "cbc:DocumentCurrencyCode");
            envelope.appendChild(DocumentCurrencyCode);
            DocumentCurrencyCode.appendChild(doc.createTextNode(items.getDocu_moneda().trim()));
            



            Element DiscrepancyResponse = doc.createElementNS("", "cac:DiscrepancyResponse");

            envelope.appendChild(DiscrepancyResponse);

            DiscrepancyResponse.appendChild(doc.createTextNode("\n"));
            
           
            Element ReferenceID = doc.createElementNS("", "cbc:ReferenceID");
            DiscrepancyResponse.appendChild(ReferenceID);
            ReferenceID.appendChild(doc.createTextNode(items.getDocu_numero()));
            
            Element ResponseCode = doc.createElementNS("", "cbc:ResponseCode");
            DiscrepancyResponse.appendChild(ResponseCode);
            ResponseCode.appendChild(doc.createTextNode("07"));
            
            Element Description = doc.createElementNS("", "cbc:Description");
            DiscrepancyResponse.appendChild(Description);
            Description.appendChild(doc.createTextNode("Anulación de la Venta - Devolución de Productos"));
                     

            
            
            UBLExtension.appendChild(ExtensionContent);
            UBLExtensions.appendChild(UBLExtension);
            
            
            
            //DOCUMENTO QUE ANULA

            Element BillingReference = doc.createElementNS("", "cac:BillingReference");
            envelope.appendChild(BillingReference);
            BillingReference.appendChild(doc.createTextNode("\n"));
         
            Element InvoiceDocumentReference = doc.createElementNS("", "cac:InvoiceDocumentReference");
            BillingReference.appendChild(InvoiceDocumentReference);
            InvoiceDocumentReference.appendChild(doc.createTextNode("\n"));
                
            Element idModif = doc.createElementNS("", "cbc:ID");
           // idModif.setAttributeNS(null, "schemeID", items.getClie_tipodoc().trim());
            //idModif.setIdAttributeNS(null, "schemeID", true);
            InvoiceDocumentReference.appendChild(idModif);//se anade al grupo PartyIdentification1
            idModif.appendChild(doc.createTextNode(items.getDocu_numero()));
            
             String atribute1="PE:SUNAT";
            Element DocumentTypeCode = doc.createElementNS("", "cbc:DocumentTypeCode");
            DocumentTypeCode.setAttributeNS(null, "listAgencyName", atribute1.trim());
            //idModif.setIdAttributeNS(null, "schemeID", true);
            InvoiceDocumentReference.appendChild(DocumentTypeCode);//se anade al grupo PartyIdentification1
            DocumentTypeCode.appendChild(doc.createTextNode(items.getDocu_tipodocumento()));

          /* PARTY 1  listAgencyName
            */




//bloque2 cac:Signature--------------------------------------------------------
            Element Signature = doc.createElementNS("", "cac:Signature");
            envelope.appendChild(Signature);
            Signature.appendChild(doc.createTextNode("\n"));

            Element ID6 = doc.createElementNS("", "cbc:ID");
            Signature.appendChild(ID6);
            ID6.appendChild(doc.createTextNode("Sign"+items.getEmpr_nroruc().trim()));

            Element SignatoryParty = doc.createElementNS("", "cac:SignatoryParty");
            Signature.appendChild(SignatoryParty);
            SignatoryParty.appendChild(doc.createTextNode("\n"));

            Element PartyIdentification = doc.createElementNS("", "cac:PartyIdentification");
            SignatoryParty.appendChild(PartyIdentification);
            PartyIdentification.appendChild(doc.createTextNode("\n"));

            Element ID7 = doc.createElementNS("", "cbc:ID");
            PartyIdentification.appendChild(ID7);
            ID7.appendChild(doc.createTextNode(items.getEmpr_nroruc().trim()));

            Element PartyName = doc.createElementNS("", "cac:PartyName");
            SignatoryParty.appendChild(PartyName);
            PartyName.appendChild(doc.createTextNode("\n"));

            Element Name = doc.createElementNS("", "cbc:Name");
            PartyName.appendChild(Name);
            cdata = doc.createCDATASection(items.getEmpr_razonsocial().trim());
            Name.appendChild(cdata);

            Element DigitalSignatureAttachment = doc.createElementNS("", "cac:DigitalSignatureAttachment");
            Signature.appendChild(DigitalSignatureAttachment);
            DigitalSignatureAttachment.appendChild(doc.createTextNode("\n"));

            Element ExternalReference = doc.createElementNS("", "cac:ExternalReference");
            DigitalSignatureAttachment.appendChild(ExternalReference);
            ExternalReference.appendChild(doc.createTextNode("\n"));

            Element URI = doc.createElementNS("", "cbc:URI");
            ExternalReference.appendChild(URI);
            URI.appendChild(doc.createTextNode(items.getEmpr_nroruc().trim()));
//bloque3 cac:AccountingSupplierParty-----------------------------------------

            Element AccountingSupplierParty = doc.createElementNS("", "cac:AccountingSupplierParty");
            envelope.appendChild(AccountingSupplierParty);
            AccountingSupplierParty.appendChild(doc.createTextNode("\n"));


//***********************************************************
            Element Party = doc.createElementNS("", "cac:Party");
            AccountingSupplierParty.appendChild(Party);
            Party.appendChild(doc.createTextNode("\n"));
            
            Element PartyRuc = doc.createElementNS("", "cac:PartyIdentification");
            Party.appendChild(PartyRuc);//se anade al grupo party
            PartyRuc.appendChild(doc.createTextNode("\n"));
            
            Element RucIden = doc.createElementNS("", "cbc:ID");
            
            RucIden.setAttributeNS(null, "schemeID", items.getEmpr_tipodoc().trim());
            RucIden.setIdAttributeNS(null, "schemeID", true);
            PartyRuc.appendChild(RucIden);//se anade al grupo partyname1
            RucIden.appendChild(doc.createTextNode(items.getEmpr_nroruc().trim()));
            

            Element PartyName1 = doc.createElementNS("", "cac:PartyName");
            Party.appendChild(PartyName1);//se anade al grupo party
            PartyName1.appendChild(doc.createTextNode("\n"));

            Element Name2 = doc.createElementNS("", "cbc:Name");
            PartyName1.appendChild(Name2);//se anade al grupo partyname1
            cdata = doc.createCDATASection(items.getEmpr_nombrecomercial().trim());
            Name2.appendChild(cdata);
            
            
            Element PartyLegalEntity = doc.createElementNS("", "cac:PartyLegalEntity");
            Party.appendChild(PartyLegalEntity);//se anade al grupo party
            PartyLegalEntity.appendChild(doc.createTextNode("\n"));

            Element RegistrationName = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity.appendChild(RegistrationName);//se anade al grupo Country
            cdata = doc.createCDATASection(items.getEmpr_razonsocial().trim());
            RegistrationName.appendChild(cdata);
            
            Element RegistrationAddress = doc.createElementNS("", "cac:RegistrationAddress");
            PartyLegalEntity.appendChild(RegistrationAddress);//se anade al grupo PartyLegalEntity
            RegistrationAddress.appendChild(doc.createTextNode("\n"));
            
            Element AddressTypeCode = doc.createElementNS("", "cbc:AddressTypeCode");
            RegistrationAddress.appendChild(AddressTypeCode);//se anade al grupo Country
            AddressTypeCode.appendChild(doc.createTextNode("0001"));
            


           
// bloque4
            Element AccountingCustomerParty = doc.createElementNS("", "cac:AccountingCustomerParty");
            envelope.appendChild(AccountingCustomerParty);
            AccountingCustomerParty.appendChild(doc.createTextNode("\n"));

       /*     Element CustomerAssignedAccountID1 = doc.createElementNS("", "cbc:CustomerAssignedAccountID");
            AccountingCustomerParty.appendChild(CustomerAssignedAccountID1);//se anade al grupo AccountingCustomerParty
            CustomerAssignedAccountID1.appendChild(doc.createTextNode(items.getClie_numero().trim()));

            Element AdditionalAccountID1 = doc.createElementNS("", "cbc:AdditionalAccountID");
            AccountingCustomerParty.appendChild(AdditionalAccountID1);//se anade al grupo AccountingCustomerParty
            AdditionalAccountID1.appendChild(doc.createTextNode(items.getClie_tipodoc().trim()));
*/
            Element Party1 = doc.createElementNS("", "cac:Party");
            AccountingCustomerParty.appendChild(Party1);//se anade al grupo AccountingCustomerParty
            Party1.appendChild(doc.createTextNode("\n"));


            
            Element PartyIdentification1 = doc.createElementNS("", "cac:PartyIdentification");
            Party1.appendChild(PartyIdentification1);//se anade al grupo Party1
            PartyIdentification1.appendChild(doc.createTextNode("\n"));
            
            Element idCli1 = doc.createElementNS("", "cbc:ID");
            idCli1.setAttributeNS(null, "schemeID", items.getClie_tipodoc().trim());
            idCli1.setIdAttributeNS(null, "schemeID", true);
            
            PartyIdentification1.appendChild(idCli1);//se anade al grupo PartyIdentification1
            idCli1.appendChild(doc.createTextNode(items.getClie_numero().trim()));
            
            
            Element PartyLegalEntity1 = doc.createElementNS("", "cac:PartyLegalEntity");
            Party1.appendChild(PartyLegalEntity1);//se anade al grupo Party1
            PartyLegalEntity1.appendChild(doc.createTextNode("\n"));
            Element RegistrationName1 = doc.createElementNS("", "cbc:RegistrationName");
            PartyLegalEntity1.appendChild(RegistrationName1);//se anade al grupo PartyLegalEntity1
            //cdata = doc.createCDATASection(items.getClie_nombre().trim());
            RegistrationName1.appendChild(doc.createTextNode(items.getClie_nombre().trim()));
            
            
          


//bloque 5
            Element TaxTotal = doc.createElementNS("", "cac:TaxTotal");
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
            
            Element TaxableAmount = doc.createElementNS("", "cbc:TaxableAmount");
            TaxableAmount.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
            TaxableAmount.setIdAttributeNS(null, "currencyID", true);
            TaxSubtotal.appendChild(TaxableAmount);//se anade al grupo TaxSubtotal
            TaxableAmount.appendChild(doc.createTextNode(items.getDocu_subtotal().trim()));

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
            TaxTypeCode.appendChild(doc.createTextNode("VAT"));
//bloque 6     
            Element LegalMonetaryTotal = doc.createElementNS("", "cac:LegalMonetaryTotal");
            envelope.appendChild(LegalMonetaryTotal);
            LegalMonetaryTotal.appendChild(doc.createTextNode("\n"));

            if (!items.getDocu_descuento().equals("0.00")) {
                Element AllowanceTotalAmount = doc.createElementNS("", "cbc:AllowanceTotalAmount");
                AllowanceTotalAmount.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
                AllowanceTotalAmount.setIdAttributeNS(null, "currencyID", true);
                LegalMonetaryTotal.appendChild(AllowanceTotalAmount);//se anade al grupo LegalMonetaryTotal
                AllowanceTotalAmount.appendChild(doc.createTextNode(items.getDocu_descuento().trim()));
            }

            Element PayableAmount = doc.createElementNS("", "cbc:PayableAmount");
            PayableAmount.setAttributeNS(null, "currencyID", items.getDocu_moneda().trim());
            PayableAmount.setIdAttributeNS(null, "currencyID", true);
            LegalMonetaryTotal.appendChild(PayableAmount);//se anade al grupo LegalMonetaryTotal
            PayableAmount.appendChild(doc.createTextNode(items.getDocu_total().trim()));
            
            

                
                
//detalle factura
            log.info("generarXMLZipiadoNota - Iniciamos detalle XML ");
            
            if(1==1){
            for (DocumentodetBean listaDet : detdocelec) {
                Element InvoiceLine = doc.createElementNS("", "cac:CreditNoteLine");
                envelope.appendChild(InvoiceLine);
                InvoiceLine.appendChild(doc.createTextNode("\n"));

                Element ID11 = doc.createElementNS("", "cbc:ID");
                InvoiceLine.appendChild(ID11);//se anade al grupo InvoiceLine
                ID11.appendChild(doc.createTextNode(listaDet.getItem_orden().trim()));
                
                double numItm = Double.parseDouble(listaDet.getItem_cantidad());
                
                int finItm=(int)numItm;

                Element InvoicedQuantity = doc.createElementNS("", "cbc:CreditedQuantity");
                InvoicedQuantity.setAttributeNS(null, "unitCode", listaDet.getItem_unidad().trim());
                InvoicedQuantity.setIdAttributeNS(null, "unitCode", true);

                InvoiceLine.appendChild(InvoicedQuantity);//se anade al grupo InvoiceLine
                InvoicedQuantity.appendChild(doc.createTextNode(listaDet.getItem_cantidad()));

                Element LineExtensionAmount1 = doc.createElementNS("", "cbc:LineExtensionAmount");
                LineExtensionAmount1.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim());
                LineExtensionAmount1.setIdAttributeNS(null, "currencyID", true);

                InvoiceLine.appendChild(LineExtensionAmount1);//se anade al grupo InvoiceLine
                LineExtensionAmount1.appendChild(doc.createTextNode(listaDet.getItem_ti_subtotal().trim()));

                Element PricingReference = doc.createElementNS("", "cac:PricingReference");
                InvoiceLine.appendChild(PricingReference);//se anade al grupo InvoiceLine
                PricingReference.appendChild(doc.createTextNode("\n"));

                Element AlternativeConditionPrice = doc.createElementNS("", "cac:AlternativeConditionPrice");
                PricingReference.appendChild(AlternativeConditionPrice);//se anade al grupo PricingReference
                AlternativeConditionPrice.appendChild(doc.createTextNode("\n"));

                Element PriceAmount = doc.createElementNS("", "cbc:PriceAmount");
                PriceAmount.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim());
                PriceAmount.setIdAttributeNS(null, "currencyID", true);
                AlternativeConditionPrice.appendChild(PriceAmount);//se anade al grupo AlternativeConditionPrice
                PriceAmount.appendChild(doc.createTextNode(listaDet.getItem_preunitfin().trim()));

                Element PriceTypeCode = doc.createElementNS("", "cbc:PriceTypeCode");
                AlternativeConditionPrice.appendChild(PriceTypeCode);//se anade al grupo AlternativeConditionPrice
                PriceTypeCode.appendChild(doc.createTextNode("01")); //=================================>Faltaba especificar ite

//                  
                if (!listaDet.getItem_pventa_no_onerosa().equals("0.00")) {
                    Element AlternativeConditionPrice02 = doc.createElementNS("", "cac:AlternativeConditionPrice");
                    PricingReference.appendChild(AlternativeConditionPrice02);//se anade al grupo PricingReference
                    AlternativeConditionPrice02.appendChild(doc.createTextNode("\n"));

                    Element PriceAmount02 = doc.createElementNS("", "cbc:PriceAmount");
                    PriceAmount02.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim());
                    PriceAmount02.setIdAttributeNS(null, "currencyID", true);
                    AlternativeConditionPrice02.appendChild(PriceAmount02);//se anade al grupo AlternativeConditionPrice
                    PriceAmount02.appendChild(doc.createTextNode(listaDet.getItem_pventa_no_onerosa().trim()));

                    Element PriceTypeCode02 = doc.createElementNS("", "cbc:PriceTypeCode");
                    AlternativeConditionPrice02.appendChild(PriceTypeCode02);//se anade al grupo AlternativeConditionPrice
                    PriceTypeCode02.appendChild(doc.createTextNode("02")); //==>Para los casos de gatuito venta no Onerosa
                }

//
                Element TaxTotal1 = doc.createElementNS("", "cac:TaxTotal");
                InvoiceLine.appendChild(TaxTotal1);//se anade al grupo InvoiceLine
                TaxTotal1.appendChild(doc.createTextNode("\n"));

                Element TaxAmount2 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount2.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim());
                TaxAmount2.setIdAttributeNS(null, "currencyID", true);
                TaxTotal1.appendChild(TaxAmount2);//se anade al grupo TaxTotal1
                TaxAmount2.appendChild(doc.createTextNode(listaDet.getItem_ti_igv().trim()));

                Element TaxSubtotal1 = doc.createElementNS("", "cac:TaxSubtotal");
                TaxTotal1.appendChild(TaxSubtotal1);//se anade al grupo TaxTotal1
                TaxSubtotal1.appendChild(doc.createTextNode("\n"));

                Element TaxableAmount2 = doc.createElementNS("", "cbc:TaxableAmount");
                TaxableAmount2.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim());
                TaxableAmount2.setIdAttributeNS(null, "currencyID", true);

                TaxSubtotal1.appendChild(TaxableAmount2);//se anade al grupo TaxSubtotal1
                TaxableAmount2.appendChild(doc.createTextNode(listaDet.getItem_ti_subtotal().trim()));

                Element TaxAmount3 = doc.createElementNS("", "cbc:TaxAmount");
                TaxAmount3.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim()); //================>errror estaba con item..getItem_moneda()
                TaxAmount3.setIdAttributeNS(null, "currencyID", true);
                TaxSubtotal1.appendChild(TaxAmount3);//se anade al grupo TaxSubtotal1
                TaxAmount3.appendChild(doc.createTextNode(listaDet.getItem_ti_igv().trim()));

                

                Element TaxCategory1 = doc.createElementNS("", "cac:TaxCategory");
                TaxSubtotal1.appendChild(TaxCategory1);//se anade al grupo TaxSubtotal1
                TaxCategory1.appendChild(doc.createTextNode("\n"));

                Element ID12a = doc.createElementNS("", "cbc:ID");
                
                ID12a.setAttributeNS(null, "schemeID","UN/ECE 5305"); //================>errror estaba con item..getItem_moneda()
                ID12a.setIdAttributeNS(null, "schemeID", true);
                
              /*  ID12.setAttributeNS(null, "schemeName", "Tax Category Identifier"); //================>errror estaba con item..getItem_moneda()
                ID12.setIdAttributeNS(null, "schemeName", true);
                
                ID12.setAttributeNS(null, "curreschemeAgencyNamencyID", "United Nations Economic Commission for Europe"); //================>errror estaba con item..getItem_moneda()
                ID12.setIdAttributeNS(null, "curreschemeAgencyNamencyID", true);*/
                
                TaxCategory1.appendChild(ID12a);//se anade al grupo TaxCategory1
                ID12a.appendChild(doc.createTextNode("S"));
                
                
                Element Percent = doc.createElementNS("", "cbc:Percent");
                TaxCategory1.appendChild(Percent);//se anade al grupo TaxSubtotal1
                Percent.appendChild(doc.createTextNode("18.00"));

                Element TaxExemptionReasonCode = doc.createElementNS("", "cbc:TaxExemptionReasonCode");
                TaxCategory1.appendChild(TaxExemptionReasonCode);//se anade al grupo TaxCategory1
                TaxExemptionReasonCode.appendChild(doc.createTextNode(listaDet.getItem_afectacion().trim()));

           /*     Element TierRange = doc.createElementNS("", "cbc:TierRange");
                TaxCategory1.appendChild(TierRange);//se anade al grupo TaxCategory1
                TierRange.appendChild(doc.createTextNode("00"));*/

                Element TaxScheme1 = doc.createElementNS("", "cac:TaxScheme");
                TaxCategory1.appendChild(TaxScheme1);//se anade al grupo TaxCategory1
                TaxScheme1.appendChild(doc.createTextNode("\n"));

                Element ID15 = doc.createElementNS("", "cbc:ID");
                TaxScheme1.appendChild(ID15);//se anade al grupo TaxCategory1
                ID15.appendChild(doc.createTextNode("1000"));

                Element Name9 = doc.createElementNS("", "cbc:Name");
                TaxScheme1.appendChild(Name9);//se anade al grupo TaxCategory1
                Name9.appendChild(doc.createTextNode("IGV"));

                Element TaxTypeCode1 = doc.createElementNS("", "cbc:TaxTypeCode");
                TaxScheme1.appendChild(TaxTypeCode1);//se anade al grupo TaxCategory1
                TaxTypeCode1.appendChild(doc.createTextNode("VAT"));

                Element Item = doc.createElementNS("", "cac:Item");
                InvoiceLine.appendChild(Item);//se anade al grupo InvoiceLine
                Item.appendChild(doc.createTextNode("\n"));

                Element Description2 = doc.createElementNS("", "cbc:Description");
                Item.appendChild(Description2);//se anade al grupo Item
                cdata = doc.createCDATASection(listaDet.getItem_descripcion().trim());
                Description2.appendChild(cdata);

                Element SellersItemIdentification = doc.createElementNS("", "cac:SellersItemIdentification");
                Item.appendChild(SellersItemIdentification);//se anade al grupo Item
                SellersItemIdentification.appendChild(doc.createTextNode("\n"));

                Element ID18 = doc.createElementNS("", "cbc:ID");
                SellersItemIdentification.appendChild(ID18);//se anade al grupo Item
                ID18.appendChild(doc.createTextNode(listaDet.getItem_codproducto().trim()));

                Element Price = doc.createElementNS("", "cac:Price");
                InvoiceLine.appendChild(Price);//se anade al grupo InvoiceLine
                Price.appendChild(doc.createTextNode("\n"));

                Element PriceAmount2 = doc.createElementNS("", "cbc:PriceAmount");
                PriceAmount2.setAttributeNS(null, "currencyID", listaDet.getItem_moneda().trim());
                PriceAmount2.setIdAttributeNS(null, "currencyID", true);
                Price.appendChild(PriceAmount2);//se anade al grupo Price
                PriceAmount2.appendChild(doc.createTextNode(listaDet.getItem_pventa().trim()));
                
                
               
                }
            }
            
                      /*   Element DocumentTypeCode = doc.createElementNS("", "cbc:DocumentTypeCode");
             DocumentTypeCode.setAttributeNS(null, "listID", "0101");
             DocumentTypeCode.setIdAttributeNS(null, "listID", true);
             envelope.appendChild(DocumentTypeCode);//se anade al grupo invoice
             DocumentTypeCode.appendChild(doc.createTextNode("0101"));*/
             
            log.info("generarXMLZipiadoNota- Prepara firma digital ");
            sig.setId("SignN"+items.getEmpr_nroruc());
            sig.addKeyInfo(cert);
            {
                Transforms transforms = new Transforms(doc);
                transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
                sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
            }
            {
                //Firmar el documento
                log.info("generarXMLZipiadoNota - firma el XML ");
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

            log.info("generarXMLZipiadoNota - XML creado " + pathXMLFile);
            //====================== CREAR ZIP PARA EL ENVIO A SUNAT =======================
            resultado = GeneralFunctions.crearZipNCredito(items, unidadEnvio, signatureFile,numeroNota);

        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = "0100|Error al generar el archivo de formato xml de la Factura.";
            log.error("generarXMLZipiadoFactura - error  " + ex.toString());

        }
        return resultado;
    }

}
