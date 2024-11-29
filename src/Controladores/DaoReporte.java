/**
 *
 * @author Francisco Javier Gordillo Aguilar - S2210145
 */
package Controladores;

import Models.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DaoReporte {
    
    private static final String user = "Admin";
    private static final String pw = "1234";
    private static final String bd = "jdbc:mysql://localhost:3306/puntoVenta";
    
    
    /**
     * Permite recuperar de la base de datos un ArrayList de objetos Venta con
     * cada una de las ventas en el rango de fechas indicado
     * 
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public static ArrayList<Venta> getVentas(Date fechaInicial, Date fechaFinal){
        ArrayList<Venta> ventas = new ArrayList<>();
        
        try {
            //Crear conexion
                //Registrar el driver para la conexion
                Class.forName("com.mysql.cj.jdbc.Driver");

                //Crear la conexion     
                Connection conex = DriverManager.getConnection(bd, user, pw);

            //Hacer la operacion
                String opInicial = "SELECT * FROM venta WHERE fecha BETWEEN ? AND ?;";
                
                PreparedStatement opCompleta = conex.prepareStatement(opInicial);
                opCompleta.setDate(1, new java.sql.Date(fechaInicial.getTime()));
                opCompleta.setDate(2, new java.sql.Date(fechaFinal.getTime()));
                
                ResultSet resAct = opCompleta.executeQuery();
                
                while(resAct.next()){
                    int idempleado = resAct.getInt("idempleado");
                    int idCliente = resAct.getInt("idCliente");
                    Date fecha = resAct.getTimestamp("fecha"); 
                    double total = resAct.getDouble("total");
                    int idventa = resAct.getInt("idorden");
                    ventas.add(new Venta(idempleado,idCliente,fecha,total,idventa));
                }

            //Cerrar la conexion 
                conex.close();

            } catch (ClassNotFoundException | SQLException s) {
                System.out.println("Algo salio mal durante la operacion sql getVentas");
                System.out.println(s.getMessage());
                return null;
            }
       return ventas;
    }
        
    
}
