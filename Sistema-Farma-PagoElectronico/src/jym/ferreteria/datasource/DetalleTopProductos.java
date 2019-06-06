/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.datasource;

/**
 *
 * @author crist
 */
public class DetalleTopProductos {
    
    private String idproduto;
    private String producto;
    private String nommarc;
    private int cantidad;
    private int ventas;

    public DetalleTopProductos() {
    }
    
    public DetalleTopProductos(String idproduto, String producto, String nommarc ,int cantidad,int ventas) {
        this.idproduto = idproduto;
        this.producto = producto;
        this.nommarc = nommarc;
        this.cantidad = cantidad;
        this.ventas = ventas;

    }
    
    public String getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(String idproduto) {
        this.idproduto = idproduto;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public String getNommarc() {
        return nommarc;
    }

    public void setNommarc(String nommarc) {
        this.nommarc = nommarc;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }
    
}
