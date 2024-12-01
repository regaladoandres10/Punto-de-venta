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
public class RegistroReporteMensual {
    private int IdOrden;
    private Date fecha;
    private String nombreCliente;
    private String empleado;
    private double total;
    private int detalles;
    
    public RegistroReporteMensual(int idOrden, Date fecha, String nombreCliente, String empleado, double total, int detalles){
        this.IdOrden = idOrden;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.empleado = empleado;
        this.total = total;
        this.detalles = detalles;
    }

    public int getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(int IdOrden) {
        this.IdOrden = IdOrden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getDetalles() {
        return detalles;
    }

    public void setDetalles(int detalles) {
        this.detalles = detalles;
    }
    
    
    
    
    
    
    
}
