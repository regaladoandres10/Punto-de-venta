/**
 *
 * @author Francisco Javier Gordillo Aguilar - S2210145
 */
package Models;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
        
public class Reporte {
    private Date inicio;
    private Date fin;
    private Date fecha;
    private ArrayList <Venta> ventas;
    double importeTotal;
    double IVA;
    
    public Reporte(ArrayList<Venta> ventas){
        this.ventas = ventas;
        this.inicio = ventas.get(0).getFechaVenta();
        this.fin = ventas.get(ventas.size()-1).getFechaVenta();
        this.fecha = new Date();
        calcImporteEIVA();
    }
    
    public void calcImporteEIVA(){
        this.importeTotal = 0;
        this.IVA = 0;
        for(Venta act : this.ventas){
            this.importeTotal+=act.getImporte();
            this.IVA+=act.getIVA();
        }
        this.importeTotal = Math.round(this.importeTotal * 10000.0) / 10000.0;
        this.IVA = Math.round(this.IVA * 10000.0) / 10000.0;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    @Override
    public String toString() {
        return "Reporte{" + "inicio=" + inicio + ", fin=" + fin + ", fecha=" + fecha + ", ventas=" + ventas + ", importeTotal=" + importeTotal + ", IVA=" + IVA + '}';
    }
    
    
    
}
