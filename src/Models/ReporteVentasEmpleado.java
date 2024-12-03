/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author dagua
 */
public class ReporteVentasEmpleado {
    private String empleado;
    private double total;
    private int cantidadVentas;

    public ReporteVentasEmpleado(String empleado, double total, int cantidadVentas) {
        this.empleado = empleado;
        this.total = total;
        this.cantidadVentas = cantidadVentas;
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

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    @Override
    public String toString() {
        return "Empleado: " + empleado + ", Total: " + total + ", Ventas: " + cantidadVentas;
    }
    
}
