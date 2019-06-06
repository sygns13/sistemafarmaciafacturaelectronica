/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jym.ferreteria.datasource;

/**
 *
 * @author Omr
 */
public class Inventario {
    private String codigo;
    private String producto;
    private String rubro;
    private String composicion;
    private String presentacion;
    private String laboratorio;
    private String almacen;
    private String stock;
    private String preVenta;
    private String preCompra;

    public Inventario(String codigo,String producto,String rubro, String composicion, String presentacion, String laboratorio, String almacen, String stock, String preVenta, String preCompra) {
        this.codigo = codigo;
        this.producto = producto;
        this.rubro = rubro;
        this.composicion = composicion;
        this.presentacion = presentacion;
        this.laboratorio = laboratorio;
        this.almacen = almacen;
        this.stock = stock;
        this.preVenta = preVenta;
        this.preCompra = preCompra;
    }
    
     public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    public String getPreVenta() {
        return preVenta;
    }

    public void setPreVenta(String preVenta) {
        this.preVenta = preVenta;
    }
    
    public String getPreCompra() {
        return preVenta;
    }

    public void setPreCompra(String preCompra) {
        this.preCompra = preCompra;
    }
    
    
}
