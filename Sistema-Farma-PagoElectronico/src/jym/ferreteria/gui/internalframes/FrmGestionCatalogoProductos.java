package jym.ferreteria.gui.internalframes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;
import jym.ferreteria.clases.Controlador;
import jym.ferreteria.gui.dialogs.AsignarUnidades;
import jym.ferreteria.renders.CustomTableModel;
import jym.ferreteria.renders.LateralTableHeader;


public class FrmGestionCatalogoProductos extends javax.swing.JInternalFrame {

    private CustomTableModel modelo = new CustomTableModel();
    private Controlador control = new Controlador();
    private String idcont = "", iduni, idtipprd, idmar, idmod, producto, idProd, idPres;
    public static Map mapLinea = new HashMap();
    private boolean modoedicion = false;
    private final DecimalFormat format = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance());
    private AsignarUnidades asignarU = null;
    

    public FrmGestionCatalogoProductos() {
        initComponents();
        this.setFrameIcon(new ImageIcon(this.getClass().getResource(Controlador.ICON_PATH)));
        tCatalogoproductos.setModel(modelo);
        modelo.setColumnIdentifiers(new String[]{"N°","Código","Producto" ,"Código Unidad", "Rubro", "Composición", "Presentación", "idProducto", "Laboratorio", "IDUnidades","Stock","Precio venta","Precio compra","Ganancia x Unid"});
        control.hideTableColumn(tCatalogoproductos, 9,1,7);
        tCatalogoproductos.getColumnModel().getColumn(2).setPreferredWidth(250);
         tCatalogoproductos.getColumnModel().getColumn(6).setPreferredWidth(200);
        tCatalogoproductos.getColumnModel().getColumn(0).setCellRenderer(new LateralTableHeader());
        tCatalogoproductos.getColumnModel().getColumn(0).setMaxWidth(30);
       control.tableMaxWidthColumn(tCatalogoproductos, 120, 4, 8,10);
        LlenaMarca_Tipo_Unidades();
        cboUnidades.setSelectedIndex(0);
//        tCatalogoproductos.setDefaultRenderer(Object.class, new LabelRenderer());
//        txtprodcontenido.setEditable(false);

        MostrarCatalogo();
        Cancelar();
                //jScrollPane1.set
        //info.setGg(5);
    }

    public void cbo(boolean b) {
        cbLineas.setEnabled(b);
        cboMarcas.setEnabled(b);
        cboUnidades.setEnabled(b);
    }

    public void VerdatosCatalogo() {
        control.fila = tCatalogoproductos.getSelectedRow();
        if (control.fila >= 0) {
//"N°","Código","Producto" ,"Código Unidad", "Rubro", "Composición", "Presentación", "idProducto", "Laboratorio", "IDUnidades","Stock","Precio venta","Precio compra","Ganancia x Unid";
            idProd = tCatalogoproductos.getValueAt(control.fila, 0).toString();// Capturamos el identificador del producto a modificar
            control.MostrarEnCombo(tCatalogoproductos.getValueAt(control.fila, 4).toString(), cbLineas);
            control.MostrarEnCombo(tCatalogoproductos.getValueAt(control.fila, 8).toString(), cboMarcas);
            control.MostrarEnCombo(tCatalogoproductos.getValueAt(control.fila, 6).toString(), cboPresent);
            
            control.MostrarEnCombo(control.ObtenerDato("Vta_Catalogo", "ID", idProd, 5), cboUnidades);

            txtDetalles.setText(tCatalogoproductos.getValueAt(control.fila, 2).toString());
            
            txtDescripcion.setText(tCatalogoproductos.getValueAt(control.fila, 3).toString());
            txtComposicion.setText(tCatalogoproductos.getValueAt(control.fila, 5).toString());
           
            txPrecioMayor.setText(tCatalogoproductos.getValueAt(control.fila, 12).toString());
            txPrecxMenor.setText(tCatalogoproductos.getValueAt(control.fila, 11).toString());
            
            txStockMinimo.setText(control.DevolverRegistroDto("SELECT * FROM produto s where idProduto='"+idProd+"';", 7));
            
            
            
            txtPrioridad.setText(control.DevolverRegistroDto("SELECT * FROM produto s where idProduto='"+idProd+"';", 18));
            txtUbicacion.setText(control.DevolverRegistroDto("SELECT * FROM produto s where idProduto='"+idProd+"';", 19));
            
            txStockIni.setText(tCatalogoproductos.getValueAt(control.fila, 10).toString());
            
            String LoteIni=control.DevolverRegistroDto("select ifnull(l.nombre,'General') from lote l where l.idproducto='"+idProd+"'  limit 1;", 1);
            if(LoteIni.trim().length()==0){
            LoteIni="General";
            }
            
            String FecVen=control.DevolverRegistroDto("select date_format(l.`fecVen`,'%d-%m-%Y') as fecha from lote l where l.idproducto='"+idProd+"'  limit 1;", 1);
            
            if(FecVen.trim().length()==0){
                this.jDateChooser1.setDate(new Date());
            }
            else{
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
String strFecha = FecVen;
Date fecha = null;
try {

fecha = formatoDelTexto.parse(strFecha);
this.jDateChooser1.setDate(fecha);

} catch (ParseException ex) {

ex.printStackTrace();

}
           // ((JTextComponent) (this.jDateChooser1.getDateEditor())).setText(FecVen);
            }
            
            txtlote.setText(LoteIni);
            
            
            
            
            
            bCrear.setEnabled(false);
            bUnidades.setEnabled(false);
            bModificar.setEnabled(true);
            bUnidades.setEnabled(true);
            
            jLabel2.setVisible(true);
            cboStockIni.setVisible(true);
            //JOptionPane.showMessageDialog(null, idProd);
        }
    }

    public void EditarCatalogo() {
        if (cbLineas.getSelectedIndex() != -1) {
            if (cboMarcas.getSelectedIndex() != -1) {
                
                if(cboPresent.getSelectedIndex()!= -1){
                if (txStockMinimo.getText().equals("")) {
                    txStockMinimo.setText("0");
                }

                if (txPrecxMenor.getText().equals("")) {
                    txPrecxMenor.setText("0.00");
                }

                if (txStockMinimo.getText().trim().length() > 0) {
                    if (txPrecxMenor.getText().trim().length() > 0) {
                        if (cboUnidades.getSelectedIndex() != -1) {
                            
                            if (txtDetalles.getText().trim().length()>0){
                             
                                if (txtDescripcion.getText().trim().length()>0){
                              
                                     String fec = "NULL";
                            
                            if (txtDescripcion.getText().trim().length()>0){
                                
                                if (txtlote.getText().trim().length()==0){
                                
                                    txtlote.setText("General");
                                }
                                
                                String fecha = this.control.Formato_Amd(this.jDateChooser1.getDate() == null ? new Date() : this.jDateChooser1.getDate());

                                idPres = control.ObtenerDato("presentacion", "present", cboPresent.getSelectedItem().toString(), 1);
                                
                                int condStock=cboStockIni.getSelectedIndex();
                               

                            idmod = txtDetalles.getText()+", del laboratorio "+cboMarcas.getSelectedItem().toString()+", en la Presentación "+ cboPresent.getSelectedItem().toString();
                            idmar = control.ObtenerDato("marca", "nommarc", cboMarcas.getSelectedItem().toString(), 1);
                            idtipprd = control.ObtenerDato("tipoproducto", "nomtip", cbLineas.getSelectedItem().toString(), 1);
                            iduni = control.ObtenerDato("unidades", "nomuniddes", cboUnidades.getSelectedItem().toString(), 1);
                            /*System.out.println("call DefiniendoProducto('" + idProd + "','" + txtDetalles.getText() + "','" + 0 + "','0','" + idtipprd + "','" + idmar + "','" + iduni + "','" + txStockMinimo.getText()
                                    + "','" + txPrecxMenor.getText() + "','0','" + txStockIni.getText() + "','"+ txtDetalles.getText() + "','" + txPrecioMayor.getText() + "'," + "'" + txtDescripcion.getText() + "','" + txtDescripcion.getText() + "'," + fec  
                                    + ",'" + idPres + "',"
                                    + "'" + txtComposicion.getText().trim() + "',"
                                    + "'" + txtPrioridad.getText().trim() + "',"
                                    + "'" + txtUbicacion.getText().trim() + "',"
                                    + "'" + txtlote.getText().trim() + "',"
                                    + "'" + fecha + "',"
                                    + "'1','"+condStock+"');");*/
                            control.Sql = "call DefiniendoProducto('" + idProd + "','" + txtDetalles.getText() + "','" + 0 + "','0','" + idtipprd + "','" + idmar + "','" + iduni + "','" + txStockMinimo.getText()
                                    + "','" + txPrecxMenor.getText() + "','0','" + txStockIni.getText() + "','"+ txtDetalles.getText() + "','" + txPrecioMayor.getText() + "'," + "'" + txtDescripcion.getText() + "','" + txtDescripcion.getText() + "'," + fec  
                                    + ",'" + idPres + "',"
                                    + "'" + txtComposicion.getText().trim() + "',"
                                    + "'" + txtPrioridad.getText().trim() + "',"
                                    + "'" + txtUbicacion.getText().trim() + "',"
                                    + "'" + txtlote.getText().trim() + "',"
                                    + "'" + fecha + "',"
                                    + "'1','"+condStock+"');";
                            
                            
                           //control.EditarRegistro(String.format("UPDATE produto p SET p.`precxmeno`=%s*if(p.`CantCont`=0,1,p.`CantCont`), p.`precxmayo`=%s*if(p.`CantCont`=0,1,p.`CantCont`) WHERE p.`Idproductocont`=%s;", txPrecxMenor.getText(), 0, idProd));
                            //System.out.println(String.format("UPDATE produto p SET p.`precxmeno`=%s*if(p.`CantCont`=0,1,p.`CantCont`), p.`precxmayo`=%s*if(p.`CantCont`=0,1,p.`CantCont`) WHERE p.`Idproductocont`=%s;", txPrecxMenor.getText(), 0, idProd));
                            modoedicion = false;
                            String resultado = control.EditarRegistroDev(control.Sql);
                            if (resultado.equals("1")) {
                                MostrarCatalogo();
                                Cancelar();
                                JOptionPane.showMessageDialog(this, "El producto " + idmod + " se ha actualizado correctamente");
                                idcont = "";
                                bModificar.setEnabled(false);
                                bCrear.setEnabled(true);
                                bUnidades.setEnabled(true);
//
                            } else {
                                JOptionPane.showMessageDialog(rootPane, resultado);
                            }
                            }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "Indique el Código por Unidad del Producto");
                            }
                                
                             
                           
                           }
                           else{
                                
                                    JOptionPane.showMessageDialog(rootPane, "Indique el código del Producto");
                                }
                                  
                           }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "Indique el nombre del Producto");
                            } 
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Indique la unidad");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Indique el precio por menor");
                    }

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Indique el Stock Mínimo");
                }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Indique la presentación del producto");
                    
                   // 
                }
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Indique el laboratorio del producto");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Indique el rubro de producto");
        }
    }

    public void EliminarCatalogo() {
        control.fila = tCatalogoproductos.getSelectedRow();
        if (control.fila >= 0) {
            idProd = tCatalogoproductos.getValueAt(control.fila, 0).toString();
            idcont = tCatalogoproductos.getValueAt(control.fila, 1).toString();
            producto = tCatalogoproductos.getValueAt(control.fila, 2).toString();
            if (JOptionPane.showConfirmDialog(rootPane, "Deseas eliminar el producto \n"
                    + producto+ " de Código:"+idcont , "Confirmar", 0) == 0) {
                control.Sql = "call DefiniendoProducto('"+idProd+"','0','0','0','0','0','0','0','0','0','0','0','0','" + idcont + "','0',null,'0','0','0','0','0',null,'2','0')";

                boolean ejecutar = control.ejecutar(control.Sql);
                if (ejecutar) {
                    Cancelar();
                    MostrarCatalogo();
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede eliminar el producto seleccionado ya que éste es subproducto de otro,\no es item de un pedido, de una entrada de stok o de una factura de venta");
                }

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione el elemento a eliminar");
            tCatalogoproductos.requestFocus();
        }
    }
    
    
    public void AsignarUnidades() {
        control.fila = tCatalogoproductos.getSelectedRow();
        if (control.fila >= 0) {
            idProd = tCatalogoproductos.getValueAt(control.fila, 0).toString();
            idcont = tCatalogoproductos.getValueAt(control.fila, 1).toString();
            producto = tCatalogoproductos.getValueAt(control.fila, 2).toString();
            //JOptionPane.showMessageDialog(rootPane, "idProd: "+idProd+" Codigo: "+idcont+" producto: "+producto);
             if (asignarU == null) {
            asignarU = new AsignarUnidades(null, true);
        }
        asignarU.setLocationRelativeTo(this);
        
        asignarU.setDatos(Integer.parseInt(idProd));
        asignarU.setVisible(true);
        
        if (asignarU.getResult() == asignarU.OK) {
            //asignarU=null;
        MostrarCatalogo();
        Cancelar();
        }
          
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione el elemento para Gestionar Unidades");
            tCatalogoproductos.requestFocus();
        }
    }

    public void VerProductoContenido() {
        control.fila = tCatalogoproductos.getSelectedRow();
        if (control.fila >= 0) {
            idtipprd = control.ObtenerDato("produto", "idProduto", tCatalogoproductos.getValueAt(control.fila, 1).toString(), 3);
            idmod = control.ObtenerDato("produto", "idProduto", tCatalogoproductos.getValueAt(control.fila, 1).toString(), 4);
            if (idmod.equals("0")) {
                producto = "El producto: " + tCatalogoproductos.getValueAt(control.fila, 2).toString()
                        + "\n No contiene ningun Producto";
                JOptionPane.showMessageDialog(rootPane, producto);
            } else {
                producto = "El Producto: " + tCatalogoproductos.getValueAt(control.fila, 2).toString()
                        + " " + tCatalogoproductos.getValueAt(control.fila, 3).toString()
                        + "\n Contiene al Producto : " + control.ObtenerDato("Vta_Catalogo", "ID", idtipprd, 2)
                        + " " + control.ObtenerDato("Vta_Catalogo", "ID", idtipprd, 3)
                        + "\n En una cantidad de " + idmod;
                JOptionPane.showMessageDialog(rootPane, producto);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debes seleccionar un producto");
        }
    }

    /**
     * ***********************Fin de Métodos*****************************
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbLineas = new javax.swing.JComboBox();
        cboMarcas = new javax.swing.JComboBox();
        txPrecxMenor = new javax.swing.JTextField();
        cboUnidades = new javax.swing.JComboBox();
        txtDetalles = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txStockMinimo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txPrecioMayor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txStockIni = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboPresent = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        txtComposicion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPrioridad = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtlote = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cboStockIni = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        bCrear = new javax.swing.JButton();
        bUnidades = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCatalogoproductos = new JTable(){
            @Override
            public void changeSelection(int rowIndex, int columnIndex,
                boolean toggle, boolean extend) {
                if (columnIndex == 0){

                    // Podemos llamar a changeSelecion() incrementando la columna en 1
                    // o bien podríamos hacer directamente un return.

                    super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
                }else
                super.changeSelection(rowIndex, columnIndex, toggle, extend);
            }

        };
        jLabel5 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Catálogo de productos");
        setPreferredSize(new java.awt.Dimension(1500, 900));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(51, 0, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Rubro:");

        cbLineas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLineasItemStateChanged(evt);
            }
        });
        cbLineas.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbLineasPopupMenuWillBecomeVisible(evt);
            }
        });
        cbLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLineasActionPerformed(evt);
            }
        });

        cboMarcas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMarcasItemStateChanged(evt);
            }
        });
        cboMarcas.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboMarcasPopupMenuWillBecomeVisible(evt);
            }
        });
        cboMarcas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboMarcasKeyPressed(evt);
            }
        });

        txPrecxMenor.setText("0");
        txPrecxMenor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPrecxMenorKeyTyped(evt);
            }
        });

        cboUnidades.setForeground(new java.awt.Color(0, 51, 102));
        cboUnidades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadesItemStateChanged(evt);
            }
        });
        cboUnidades.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboUnidadesPopupMenuWillBecomeVisible(evt);
            }
        });
        cboUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUnidadesActionPerformed(evt);
            }
        });

        txtDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDetallesKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Laboratorio:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Nombre Producto:");

        txStockMinimo.setText("0");
        txStockMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txStockMinimoActionPerformed(evt);
            }
        });
        txStockMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txStockMinimoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txStockMinimoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Precio de Venta Unit.");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Stock Mínimo:");

        txPrecioMayor.setText("0");
        txPrecioMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPrecioMayorActionPerformed(evt);
            }
        });
        txPrecioMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txPrecioMayorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPrecioMayorKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Código Prod Unidad");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Costo de Compra Unit.");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Stock Inicial:");

        txStockIni.setText("0");
        txStockIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txStockIniActionPerformed(evt);
            }
        });
        txStockIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txStockIniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txStockIniKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Presentación:");

        cboPresent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPresentItemStateChanged(evt);
            }
        });
        cboPresent.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboPresentPopupMenuWillBecomeVisible(evt);
            }
        });
        cboPresent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboPresentKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Composición:");

        txtComposicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComposicionKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Prioridad:");

        txtPrioridad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrioridadKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Ubicación");

        txtUbicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacionKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Lote Inicial:");

        txtlote.setText("General");
        txtlote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtloteKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Fecha Venc. Lote:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Actualizar el Stock y Lote Inicial:");

        cboStockIni.setForeground(new java.awt.Color(0, 51, 102));
        cboStockIni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Desactivado", "Activado" }));
        cboStockIni.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboStockIniItemStateChanged(evt);
            }
        });
        cboStockIni.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboStockIniPopupMenuWillBecomeVisible(evt);
            }
        });
        cboStockIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStockIniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbLineas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMarcas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDetalles)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtComposicion)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUbicacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrioridad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txStockMinimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                            .addComponent(txPrecxMenor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txPrecioMayor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboStockIni, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txStockIni)
                            .addComponent(txtlote)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(cbLineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txtDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(cboPresent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cboUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txPrecioMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txPrecxMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txtPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboStockIni, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(txStockIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel18)
                    .addComponent(txtlote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bCrear.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Accept.png"))); // NOI18N
        bCrear.setMnemonic('c');
        bCrear.setText("Crear");
        bCrear.setPreferredSize(new java.awt.Dimension(96, 33));
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel2.add(bCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, -1));

        bUnidades.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bUnidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/group_box.png"))); // NOI18N
        bUnidades.setMnemonic('l');
        bUnidades.setText("Asignar Unidades");
        bUnidades.setPreferredSize(new java.awt.Dimension(112, 33));
        bUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUnidadesActionPerformed(evt);
            }
        });
        jPanel2.add(bUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 160, -1));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Button-Refresh-icon.png"))); // NOI18N
        jButton1.setMnemonic('r');
        jButton1.setText("Refrescar");
        jButton1.setPreferredSize(new java.awt.Dimension(116, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 170, -1));

        bCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('a');
        bCancelar.setText("Cancelar");
        bCancelar.setPreferredSize(new java.awt.Dimension(114, 33));
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(bCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 160, -1));

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setPreferredSize(new java.awt.Dimension(92, 33));
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel2.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 160, -1));

        bModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('e');
        bModificar.setText("Editar");
        bModificar.setEnabled(false);
        bModificar.setPreferredSize(new java.awt.Dimension(98, 33));
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel2.add(bModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 160, -1));

        bEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jym/ferreteria/imagenes/trash.png"))); // NOI18N
        bEliminar.setMnemonic('l');
        bEliminar.setText("Eliminar");
        bEliminar.setPreferredSize(new java.awt.Dimension(112, 33));
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N

        tCatalogoproductos.setForeground(new java.awt.Color(0, 51, 102));
        tCatalogoproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tCatalogoproductos.getTableHeader().setReorderingAllowed(false);
        tCatalogoproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tCatalogoproductosMouseClicked(evt);
            }
        });
        tCatalogoproductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCatalogoproductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tCatalogoproductos);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Buscar: ");

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txBuscar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed

    dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
    CrearCatalogo();
}//GEN-LAST:event_bCrearActionPerformed
private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
    EditarCatalogo();
}//GEN-LAST:event_bModificarActionPerformed
private void bUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUnidadesActionPerformed
    AsignarUnidades();
}//GEN-LAST:event_bUnidadesActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
    Cancelar();
    bCrear.setEnabled(true);
    bUnidades.setEnabled(true);
    bModificar.setEnabled(false);
}//GEN-LAST:event_bCancelarActionPerformed
private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
    BuscarCatalogo();
}//GEN-LAST:event_txBuscarKeyReleased
private void tCatalogoproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCatalogoproductosMouseClicked
    if (evt.getClickCount() == 2) {
        VerdatosCatalogo();
    }
}//GEN-LAST:event_tCatalogoproductosMouseClicked
private void tCatalogoproductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCatalogoproductosKeyReleased

}//GEN-LAST:event_tCatalogoproductosKeyReleased
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Refrescar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txPrecioMayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPrecioMayorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txPrecioMayorKeyTyped

    private void txPrecioMayorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPrecioMayorKeyReleased
    precioProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_txPrecioMayorKeyReleased

    private void txPrecioMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPrecioMayorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPrecioMayorActionPerformed

    private void txStockMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txStockMinimoKeyTyped
        control.decimal(evt, txStockMinimo.getText());
        control.Longitudtx(txStockMinimo, evt, 9);
    }//GEN-LAST:event_txStockMinimoKeyTyped

    private void txStockMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txStockMinimoKeyReleased

    }//GEN-LAST:event_txStockMinimoKeyReleased

    private void txStockMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txStockMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txStockMinimoActionPerformed

    private void txtDetallesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetallesKeyReleased
        //        control.Mayusculas(txtDetalles);
    }//GEN-LAST:event_txtDetallesKeyReleased

    private void cboUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUnidadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUnidadesActionPerformed

    private void cboUnidadesPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboUnidadesPopupMenuWillBecomeVisible
        control.LlenarCombo(cboUnidades, "SELECT * FROM `unidades` ORDER BY `nomuniddes`;", 2);
    }//GEN-LAST:event_cboUnidadesPopupMenuWillBecomeVisible

    private void cboUnidadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadesItemStateChanged
        //  txtPrecioCompra.grabFocus();
    }//GEN-LAST:event_cboUnidadesItemStateChanged

    private void txPrecxMenorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPrecxMenorKeyTyped
        control.decimal(evt, txPrecxMenor.getText());
        //        CalcularPrecioMayor(evt);
    }//GEN-LAST:event_txPrecxMenorKeyTyped

    private void cboMarcasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMarcasKeyPressed
        if(evt.getKeyChar()==10){
            txtDetalles.setText(cbLineas.getSelectedItem().toString()+" "+cboMarcas.getSelectedItem().toString());
            txtDetalles.requestFocus();
        }
    }//GEN-LAST:event_cboMarcasKeyPressed

    private void cboMarcasPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboMarcasPopupMenuWillBecomeVisible
        control.LlenarCombo(cboMarcas, "select * from marca order by nommarc", 2);
    }//GEN-LAST:event_cboMarcasPopupMenuWillBecomeVisible

    private void cboMarcasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMarcasItemStateChanged
        //                txtDetalles.grabFocus();
    }//GEN-LAST:event_cboMarcasItemStateChanged

    private void cbLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLineasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLineasActionPerformed

    private void cbLineasPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbLineasPopupMenuWillBecomeVisible
        control.LlenarCombo(cbLineas, "select * from tipoproducto order by nomtip", 2);
    }//GEN-LAST:event_cbLineasPopupMenuWillBecomeVisible

    private void cbLineasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLineasItemStateChanged
        // cboMarcas.grabFocus();
    }//GEN-LAST:event_cbLineasItemStateChanged

    private void txStockIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txStockIniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txStockIniActionPerformed

    private void txStockIniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txStockIniKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txStockIniKeyReleased

    private void txStockIniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txStockIniKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txStockIniKeyTyped

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
    EliminarCatalogo();        // TODO add your handling code here:
    }//GEN-LAST:event_bEliminarActionPerformed

    private void cboPresentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPresentItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPresentItemStateChanged

    private void cboPresentPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboPresentPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPresentPopupMenuWillBecomeVisible

    private void cboPresentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPresentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPresentKeyPressed

    private void txtComposicionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComposicionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComposicionKeyReleased

    private void txtPrioridadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrioridadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrioridadKeyReleased

    private void txtUbicacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionKeyReleased

    private void txtloteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtloteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtloteKeyReleased

    private void cboStockIniItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboStockIniItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboStockIniItemStateChanged

    private void cboStockIniPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboStockIniPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cboStockIniPopupMenuWillBecomeVisible

    private void cboStockIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStockIniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboStockIniActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton bUnidades;
    public static javax.swing.JComboBox cbLineas;
    public static javax.swing.JComboBox cboMarcas;
    public static javax.swing.JComboBox cboPresent;
    public static javax.swing.JComboBox cboStockIni;
    public static javax.swing.JComboBox cboUnidades;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tCatalogoproductos;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txPrecioMayor;
    private javax.swing.JTextField txPrecxMenor;
    private javax.swing.JTextField txStockIni;
    private javax.swing.JTextField txStockMinimo;
    private javax.swing.JTextField txtComposicion;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDetalles;
    private javax.swing.JTextField txtPrioridad;
    private javax.swing.JTextField txtUbicacion;
    private javax.swing.JTextField txtlote;
    // End of variables declaration//GEN-END:variables
//    private void CalcularPrecioMayor(KeyEvent e) {
//        double prmy = 0.0;
//        if ((e.getKeyChar() == 10) && (txPrecxMenor.getText().trim().length() > 0)) {
//            if (txcant.getText().trim().length() == 0) {
//                JOptionPane.showMessageDialog(rootPane, "De este producto no se debe calcular porque es una unidad");
//            } else {
//                prmy = Double.parseDouble(txPrecxMenor.getText()) * Integer.parseInt(txcant.getText());
//                prmy = control.redondearDecimal(prmy, 2);
//                txtPrecxMay.setText(Double.toString(prmy));
//                txtPrecxMay.grabFocus();
//            }
//        }
//    }
//
//    private void CalcularPrecioMayor1(KeyEvent e) {
//        double prmy = 0.0;
//        control.fila = tCatalogoproductos.getSelectedRow();
//        if (control.fila >= 0) {
//            if ((e.getKeyChar() == 10) && (txcant.getText().trim().length() > 0)) {
//                if (txtDetalles.getText().trim().length() == 0) {
//                    JOptionPane.showMessageDialog(rootPane, "De este producto no se debe calcular porque es una unidad");
//                } else {
//
//                    control.Sql = "SELECT `precxmeno`, `precxmayo` FROM `produto` WHERE `idProduto`='" + idcont + "';";
//                    System.out.println(control.Sql);
//                    String menor = control.DevolverRegistroDto(control.Sql, 1);
//                    String mayor = control.DevolverRegistroDto(control.Sql, 2);
//                    double a = 0, b = 0;
//                    try {
//                        a = Double.parseDouble(menor);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FrmGestionCatalogoProductos.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                    try {
//                        b = Double.parseDouble(mayor);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FrmGestionCatalogoProductos.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                    txPrecxMenor.setText("" + a * Integer.parseInt(txcant.getText()));
//                    prmy = (b * Integer.parseInt(txcant.getText()));
//                    prmy = control.redondearDecimal(prmy, 2);
//                    txtPrecxMay.setText(Double.toString(prmy));
//                    txtPrecxMay.grabFocus();
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Seleccione un producto");
//        }
//    }

    public void Refrescar() {
        idmar = "";
        iduni = "";
        idtipprd = "";
        if (cboMarcas.getSelectedIndex() > -1) {
            idmar = cboMarcas.getSelectedItem().toString();
        }
        if (cboUnidades.getSelectedIndex() > -1) {
            iduni = cboUnidades.getSelectedItem().toString();
        }
        if (cbLineas.getSelectedIndex() > -1) {
            idtipprd = cbLineas.getSelectedItem().toString();
        }
        LlenaMarca_Tipo_Unidades();
        if (idmar.length() > 0) {
            control.MostrarEnCombo(idmar, cboMarcas);
        }
        if (iduni.length() > 0) {
            control.MostrarEnCombo(iduni, cboUnidades);
        }
        if (idtipprd.length() > 0) {
            control.MostrarEnCombo(idtipprd, cbLineas);
        }
    }
    
    

    public void BuscarCatalogo() {
        control.Sql = "select p.idProduto,p.codprod, p.nomproducto, p.codUnid, c.nomtip, p.composicion, pre.present,p.codprod, m.nommarc, u.iddetuniprod, \n " +
"s.cantidadDisponible, format(p.precUnidad,2), format(p.precioCompra,2) ,format((p.precUnidad-p.precioCompra),2) \n " +
"from produto p \n " +
"inner join tipoproducto c on c.idTipoProducto=p.idTipoProducto \n " +
"inner join presentacion pre on pre.idPresentacion=p.idPresentacion \n " +
"inner join marca m on m.idmarca=p.idmarca \n " +
"inner join detunidprod u on u.idproducto=p.idproduto and idUnidades='1' \n " +
"inner join stock s on s.idProducto=p.idproduto and s.idAlmacen='1' where p.codprod like '%" + txBuscar.getText() + "%' or p.nomproducto like '%" + txBuscar.getText() + "%' or p.codUnid like '%" + txBuscar.getText() + "%' "
                + "or c.nomtip like '%" + txBuscar.getText() + "%' or m.nommarc LIKE '%" + txBuscar.getText() + "%';";
        control.LlenarJTabla(modelo, control.Sql, 14);
        //System.out.println(control.Sql);
    }

    public void MostrarCatalogo() {
        BuscarCatalogo();
    }

    private void Cancelar() {
        txPrecxMenor.setText("0.00");
        txStockMinimo.setText("0");
        txStockIni.setText("0");
        //txPrecioFactura.setText("0.00");
        txPrecioMayor.setText("0.00");
        txtDetalles.setText("");
        
        txtDescripcion.setText("");
        cbLineas.setSelectedIndex(-1);
        cboMarcas.setSelectedIndex(-1);
        cboPresent.setSelectedIndex(-1);

        cboUnidades.setSelectedIndex(0);
        tCatalogoproductos.clearSelection();
        MostrarCatalogo();
        cbLineas.requestFocus();
        idcont = "";
        this.jDateChooser1.setDate(new Date());
        
        txtComposicion.setText("");
        txtPrioridad.setText("");
        txtUbicacion.setText("");
        txtlote.setText("General");
        
        jLabel2.setVisible(false);
        cboStockIni.setSelectedIndex(0);
        cboStockIni.setVisible(false);
        
    }

    private void CrearCatalogo() {
        if (cbLineas.getSelectedIndex() != -1) {
            if (cboMarcas.getSelectedIndex() != -1) {
                
                if(cboPresent.getSelectedIndex()!= -1){
                if (txStockMinimo.getText().equals("")) {
                    txStockMinimo.setText("0");
                }

                if (txPrecxMenor.getText().equals("")) {
                    txPrecxMenor.setText("0.00");
                }

                if (txStockMinimo.getText().trim().length() > 0) {
                    if (txPrecxMenor.getText().trim().length() > 0) {
                        if (cboUnidades.getSelectedIndex() != -1) {
                            
                            if (txtDetalles.getText().trim().length()>0){
                             
                                if (txtDescripcion.getText().trim().length()>0){
                                
                                    if (idcont.length() == 0) {
                                idcont = "0"; // no existe ningun catalogo con id cero
                            }
                            
                            
                                
                                
                                
                                if (txtlote.getText().trim().length()==0){
                                
                                    txtlote.setText("General");
                                }
                                
                                String fecha = this.control.Formato_Amd(this.jDateChooser1.getDate() == null ? new Date() : this.jDateChooser1.getDate());
                  
                            idPres = control.ObtenerDato("presentacion", "present", cboPresent.getSelectedItem().toString(), 1);
                            
                            String fec = "NULL";
                            idmod =txtDetalles.getText()+", del laboratorio "+cboMarcas.getSelectedItem().toString()+", en la Presentación "+ cboPresent.getSelectedItem().toString();
                            idmar = control.ObtenerDato("marca", "nommarc", cboMarcas.getSelectedItem().toString(), 1);
                            idtipprd = control.ObtenerDato("tipoproducto", "nomtip", cbLineas.getSelectedItem().toString(), 1);
                            iduni = control.ObtenerDato("unidades", "nomuniddes", cboUnidades.getSelectedItem().toString(), 1);
                            idProd = Integer.toString(control.DevIdentificador("produto", "idProduto"));
                            control.Sql = "call DefiniendoProducto("
                                    + "'" + idProd + "',"
                                    + "'" + txtDetalles.getText() + "',"
                                    + "'" + idcont + "',"
                                    + "'" + 0 + "',"
                                    + "'" + idtipprd + "',"
                                    + "'" + idmar + "',"
                                    + "'" + iduni + "',"
                                    + "'" + txStockMinimo.getText()
                                    + "','" + (txPrecxMenor.getText().equals("") ? "0" : txPrecxMenor.getText()) + "',"
                                    + "'',"
                                    + "'" + txStockIni.getText() + "',"
                                    + "'" + txtDetalles.getText() + "',"
                                    + "" + txPrecioMayor.getText() + ","
                                    + "'" + txtDescripcion.getText() + "',"
                                    + "'" + txtDescripcion.getText() + "',"
                                    + "" + fec + ","
                                    + "'" + idPres + "',"
                                    + "'" + txtComposicion.getText().trim() + "',"
                                    + "'" + txtPrioridad.getText().trim() + "',"
                                    + "'" + txtUbicacion.getText().trim() + "',"
                                    + "'" + txtlote.getText().trim() + "',"
                                    + "'" + fecha + "',"
                                    + "'0','0')";
                            String resultado = control.CrearRegistroDev(control.Sql);
                            if (resultado.equals("1")) {
                                MostrarCatalogo();
                                Cancelar();
                                idcont = "";
                                JOptionPane.showMessageDialog(this, "El producto " + idmod + " se ha guardado correctamente");
                            } else {
                                JOptionPane.showMessageDialog(rootPane, resultado, "Aviso", JOptionPane.WARNING_MESSAGE);
                            }
                                }
                                else{
                                
                                    JOptionPane.showMessageDialog(rootPane, "Indique el código del Producto");
                                }
                                  
                           }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "Indique el nombre del Producto");
                            } 
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Indique la unidad");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Indique el precio por menor");
                    }

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Indique el Stock Mínimo");
                }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Indique la presentación del producto");
                    
                   // 
                }
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Indique el laboratorio del producto");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Indique el rubro de producto");
        }
    }

    public void LlenaMarca_Tipo_Unidades() {
        control.LlenarCombo(cbLineas, "select * from tipoproducto order by nomtip", 2);
        control.LlenarCombo(cboMarcas, "select * from marca order by nommarc", 2);
        control.LlenarCombo(cboUnidades, "SELECT * FROM unidades u order by idUnidades limit 1", 2);
        control.LlenarCombo(cboPresent, "select * from presentacion order by present;", 2);
        
     
    }
    
    private void precioProducto(){ 
        
        if(txPrecioMayor.getText().length()>0){
        double costo=Double.parseDouble(txPrecioMayor.getText());
        double precio;
        
        precio=costo+(costo*0.3);
        
        
        
        txPrecxMenor.setText(""+control.decimalFormat(precio));
    }
    }

}
