/**
 *
 * @author Francisco Javier Gordillo Aguilar - S2210145
 */
package Controladores;

import Models.RegistroReporteMensual;
import Models.RegistroReporteTrimestral;
import Models.Venta;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
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
   
    
    public static ArrayList<RegistroReporteMensual> getRegistrosMensual(java.sql.Date fechaInicial, java.sql.Date fechaFinal){
        ArrayList<RegistroReporteMensual> registros = new ArrayList<>();
        
        try {
            //Crear conexion
                //Registrar el driver para la conexion
                Class.forName("com.mysql.cj.jdbc.Driver");

                //Crear la conexion     
                Connection conex = DriverManager.getConnection(bd, user, pw);

            //Hacer la operacion
                String opInicial = "SELECT * FROM reporteMes WHERE fecha BETWEEN ? AND ?;";
                
                PreparedStatement opCompleta = conex.prepareStatement(opInicial);
                opCompleta.setDate(1, fechaInicial);
                opCompleta.setDate(2, fechaFinal);
                
                ResultSet resAct = opCompleta.executeQuery();
                
                
                while(resAct.next()){
                    int idOrden = resAct.getInt("idOrden");
                    Date fecha = new Date(resAct.getTimestamp("fecha").getTime()); 
                    String nombreCompania = resAct.getString("nombreCompania");
                    String empleado = resAct.getString("empleado");
                    double total = resAct.getDouble("total");
                    int detalles = resAct.getInt("detalles");
                    
                    registros.add(new RegistroReporteMensual(idOrden, fecha, nombreCompania, empleado, total, detalles));
                }
                

            //Cerrar la conexion 
                conex.close();

            } catch (ClassNotFoundException | SQLException s) {
                System.out.println("Algo salio mal durante la operacion sql getRegistrosMensual");
                System.out.println(s.getMessage());
                return null;
            }
       return registros;
    }
    
    
    public static ArrayList<RegistroReporteTrimestral> getRegistrosTrimestral(String anio){
        ArrayList<RegistroReporteTrimestral> registros = new ArrayList<>();
        
        try {
            //Crear conexion
                //Registrar el driver para la conexion
                Class.forName("com.mysql.cj.jdbc.Driver");

                //Crear la conexion     
                Connection conex = DriverManager.getConnection(bd, user, pw);

            //Hacer la operacion
                String fechaT1 = anio + "-01-01";
                String fechaFinT1 = anio + "-03-31";
                String fechaT2 = anio + "-04-01";
                String fechaFinT2 = anio + "-06-30";
                String fechaT3 = anio + "-07-01";
                String fechaFinT3 = anio + "-09-30";
                String fechaT4 = anio + "-10-01";
                String fechaFinT4 = anio + "-12-31";
                
                String op1 = "SELECT producto, sum(cantidad) AS T1 FROM ventasPorProducto WHERE fecha BETWEEN ? and ? GROUP BY producto;";
                String op2 = "SELECT producto, sum(cantidad) AS T2 FROM ventasPorProducto WHERE fecha BETWEEN ? and ? GROUP BY producto;";
                String op3 = "SELECT producto, sum(cantidad) AS T3 FROM ventasPorProducto WHERE fecha BETWEEN ? and ? GROUP BY producto;";
                String op4 = "SELECT producto, sum(cantidad) AS T4 FROM ventasPorProducto WHERE fecha BETWEEN ? and ? GROUP BY producto;";
                
                
                PreparedStatement Tri1 = conex.prepareStatement(op1);
                PreparedStatement Tri2 = conex.prepareStatement(op2);
                PreparedStatement Tri3 = conex.prepareStatement(op3);
                PreparedStatement Tri4 = conex.prepareStatement(op4);
                
                Tri1.setString(1, fechaT1);
                Tri1.setString(2, fechaFinT1);
                Tri2.setString(1, fechaT2);
                Tri2.setString(2, fechaFinT2);
                Tri3.setString(1, fechaT3);
                Tri3.setString(2, fechaFinT3);
                Tri4.setString(1, fechaT4);
                Tri4.setString(2, fechaFinT4);
                
                ResultSet Trimestre1 = Tri1.executeQuery();
                ResultSet Trimestre2 = Tri2.executeQuery();
                ResultSet Trimestre3 = Tri3.executeQuery();
                ResultSet Trimestre4 = Tri4.executeQuery();
                
                
                ArrayList<Pair<String,Integer>> resultadosParaT1 = new ArrayList<>();
                ArrayList<Pair<String,Integer>> resultadosParaT2 = new ArrayList<>();
                ArrayList<Pair<String,Integer>> resultadosParaT3 = new ArrayList<>();
                ArrayList<Pair<String,Integer>> resultadosParaT4 = new ArrayList<>();
                        
                while(Trimestre1.next()){
                    String x = Trimestre1.getString("producto");
                    int y = Trimestre1.getInt("T1");
                    resultadosParaT1.add(new Pair<>(x,y));
                }
                
                while(Trimestre2.next()){
                    String x = Trimestre2.getString("producto");
                    int y = Trimestre2.getInt("T2");
                    resultadosParaT2.add(new Pair<>(x,y));
                }
                
                while(Trimestre3.next()){
                    String x = Trimestre3.getString("producto");
                    int y = Trimestre3.getInt("T3");
                    resultadosParaT3.add(new Pair<>(x,y));
                }
                
                while(Trimestre4.next()){
                    String x = Trimestre4.getString("producto");
                    int y = Trimestre4.getInt("T4");
                    resultadosParaT4.add(new Pair<>(x,y));
                }
                
                int max = Integer.max(Integer.max(resultadosParaT1.size(), resultadosParaT2.size()),Integer.max(resultadosParaT3.size(), resultadosParaT4.size()));
                for(int i=0;i<=max-1;i++){
                    
                    String prod = "";
                    int T1=0,T2=0,T3=0,T4=0;
                    
                    if(resultadosParaT1.size()-1>=i){
                        prod = resultadosParaT1.get(i).left;
                    }else if(resultadosParaT2.size()-1>=i){
                        prod = resultadosParaT2.get(i).left;
                    }else if(resultadosParaT3.size()-1>=i){
                        prod = resultadosParaT3.get(i).left;
                    }else if(resultadosParaT4.size()-1>=i){
                        prod = resultadosParaT4.get(i).left;
                    }
                    
                    if(resultadosParaT1.size()-1>=i){
                        T1 = resultadosParaT1.get(i).right;
                    }
                    if(resultadosParaT2.size()-1>=i){
                        T2 = resultadosParaT2.get(i).right;
                    }
                    if(resultadosParaT3.size()-1>=i){
                        T3 = resultadosParaT3.get(i).right;
                    }
                    if(resultadosParaT4.size()-1>=i){
                        T4 = resultadosParaT4.get(i).right;
                    }
                    
                    
                    registros.add(new RegistroReporteTrimestral(prod,T1,T2,T3,T4));
                }
                

            //Cerrar la conexion 
                conex.close();

            } catch (ClassNotFoundException | SQLException s) {
                System.out.println("Algo salio mal durante la operacion sql getRegistroTrimestral");
                System.out.println(s.getMessage());
                return null;
            }
       return registros;
    
    }
}