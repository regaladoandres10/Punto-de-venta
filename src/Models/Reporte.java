/**
 *
 * @author Francisco Javier Gordillo Aguilar - S2210145
 */
package Models;
import java.util.ArrayList;
        
public class Reporte {
    
    private Clientes cliente;
    private ArrayList <RegistroReporteMensual> registrosMensual;
    private ArrayList <RegistroReporteTrimestral> registrosTrimestral;
    
    /**
     * Reporte Mensual
     * @param registros
     */
    public Reporte(ArrayList<RegistroReporteMensual> registros){
        this.registrosMensual = registros;
    }
    
    /**
     * Reporte ventas trimestral
     * @param registros
     * @param trash 
     */
    public Reporte(ArrayList<RegistroReporteTrimestral> registros, int trash){
        this.registrosTrimestral = registros;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public ArrayList<RegistroReporteMensual> getRegistrosMensual() {
        return registrosMensual;
    }

    public void setRegistrosMensual(ArrayList<RegistroReporteMensual> registrosMensual) {
        this.registrosMensual = registrosMensual;
    }

    public ArrayList<RegistroReporteTrimestral> getRegistrosTrimestral() {
        return registrosTrimestral;
    }

    public void setRegistrosTrimestral(ArrayList<RegistroReporteTrimestral> registrosTrimestral) {
        this.registrosTrimestral = registrosTrimestral;
    }
    
    

    
    
    
}
