/**
 *
 * @author Denise Aguado Perez - S22120211
 */
package Controladores;

import Models.ReporteVentasEmpleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DaoReporteVentasPorEmpleado {

    private static final String user = "Admin";
    private static final String pw = "1234";
    private static final String bd = "jdbc:mysql://localhost:3306/puntoVenta";

   
    public static ArrayList<ReporteVentasEmpleado> getReporteVentasPorEmpleado(int mes, int anio) {
        ArrayList<ReporteVentasEmpleado> reporte = new ArrayList<>();

        try {
            // Registrar el driver para la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear la conexión
            Connection conex = DriverManager.getConnection(bd, user, pw);

            // Consulta a la vista de MySQL
            String consulta = " ";
            String FechaInicio=anio+"-0"+mes+"-01";
            if (mes>=10){
            FechaInicio=anio+"-"+mes+"-01";
            }
            String FechaFin=anio+"-"+mes+"-01";
            
            
                switch (mes){
                case 1 -> FechaFin=anio+"-0"+mes+"-31 23:59:59";
                case 2 -> FechaFin=anio+"-0"+mes+"-29 23:59:59";
                case 3 -> FechaFin=anio+"-0"+mes+"-31 23:59:59";
                case 4 -> FechaFin=anio+"-0"+mes+"-30 23:59:59";
                case 5 -> FechaFin=anio+"-0"+mes+"-31 23:59:59";
                case 6 -> FechaFin=anio+"-0"+mes+"-30 23:59:59";
                case 7 -> FechaFin=anio+"-0"+mes+"-31 23:59:59";
                case 8 -> FechaFin=anio+"-0"+mes+"-31 23:59:59";
                case 9 -> FechaFin=anio+"-0"+mes+"-30 23:59:59";
                case 10 -> FechaFin=anio+"-"+mes+"-31 23:59:59"; 
                case 11 -> FechaFin=anio+"-"+mes+"-30 23:59:59";
                case 12 -> FechaFin=anio+"-"+mes+"-31 23:59:59";
                
            
            }
            
            
            
               consulta = "Select empleado, sum(total) as total , count(idorden) as cantidadVenta from VentasEmpleado  where (fecha  between  ?  and ?) group by empleado ;";
           
            
            
            
            
            
            // Preparar y ejecutar la consulta
            PreparedStatement opCompleta = conex.prepareStatement(consulta);
             
            opCompleta.setString(1,FechaInicio);
            opCompleta.setString(2,FechaFin);
            
          
            
          
            ResultSet resultado = opCompleta.executeQuery();

            // Procesar los resultados
            while (resultado.next()) {
                String empleado = resultado.getString("empleado");
                double total = resultado.getDouble("total");
                int cantidadVentas = resultado.getInt("cantidadVenta");

                // Agregar los datos a la lista como objetos ReporteVentasEmpleado
                reporte.add(new ReporteVentasEmpleado(empleado, total, cantidadVentas));
            }

            // Cerrar la conexión
            conex.close();

        } catch (ClassNotFoundException | SQLException s) {
            System.out.println("Algo salió mal durante la operación SQL getReporteVentasPorEmpleado");
            System.out.println(s.getMessage());
            return null;
        }

        return reporte;
    }
}