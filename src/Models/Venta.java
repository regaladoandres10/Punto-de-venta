/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author GORDILLO
 */
public class Venta {
    int idempleado;
    int idventa;
    int idCliente;
    private Date fechaVenta;
    private double importe;
    private double IVA;
    
    public Venta(int idempleado,int idCliente, Date fecha,double total,int idventa){
        this.idempleado = idempleado;
        this.idCliente = idCliente;
        this.idventa = idventa;
        this.fechaVenta = fecha;
        this.importe = total;
        this.IVA = total*0.16;
    }
    
    public double getImporte(){
        return importe;
    }
    
    public double getIVA(){
        return IVA;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
