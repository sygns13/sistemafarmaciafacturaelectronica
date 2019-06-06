/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.datasource;

import java.util.List;

/**
 *
 * @author Omr
 */
public class RegularizacionStock {

    private String numeroMovimiento;
    private String fecha;
    private String movimiento;
    private String almacenEntrada;
    private String almacenSalida;
    private String motivo;
    private List detalle;

    public RegularizacionStock() {
    }

    public RegularizacionStock(String numeroMovimiento, String fecha, String movimiento, String almacenEntrada, String almacenSalida, String motivo, List detalle) {
        this.numeroMovimiento = numeroMovimiento;
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.almacenEntrada = almacenEntrada;
        this.almacenSalida = almacenSalida;
        this.motivo = motivo;
        this.detalle = detalle;
    }

    public String getNumeroMovimiento() {
        return numeroMovimiento;
    }

    public void setNumeroMovimiento(String numeroMovimiento) {
        this.numeroMovimiento = numeroMovimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getAlmacenEntrada() {
        return almacenEntrada;
    }

    public void setAlmacenEntrada(String almacenEntrada) {
        this.almacenEntrada = almacenEntrada;
    }

    public String getAlmacenSalida() {
        return almacenSalida;
    }

    public void setAlmacenSalida(String almacenSalida) {
        this.almacenSalida = almacenSalida;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List getDetalle() {
        return detalle;
    }

    public void setDetalle(List detalle) {
        this.detalle = detalle;
    }

}
