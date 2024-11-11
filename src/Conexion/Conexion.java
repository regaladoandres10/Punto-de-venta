
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Conexion 
{
    Connection conectar = null;
    
    //Varibales de control para conectarnos a MySQL
    
    String user = "root";
    String password = "root";
    String bd = "puntoVenta";
    String url = "jdbc:mysql://localhost:3306/";
    String driver = "com.mysql.cj.jdbc.Driver";
    
    String cadenaConexion = url+bd;
    
    public Connection conectar()
    {
        try
        {
            Class.forName(driver);
            conectar = DriverManager.getConnection(cadenaConexion, user, password);
            //Verificar si la conexion se realiza correctamente
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No conexion exitosa: " + e.toString());
        }
        
        return conectar;
    }
    
    public void closeConexion()
    {
        try
        {
            if (conectar != null && !conectar.isClosed())
            {
                conectar.close();
                JOptionPane.showMessageDialog(null, "Conexion cerrada exitosamente");
            }
        }
        catch(SQLException e)
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
