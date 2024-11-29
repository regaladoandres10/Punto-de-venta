
package Controladores;
import Conexion.Conexion;
import Models.Login;
import Front_end.Formularios.Main;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author Andres
 */
public class ControladorLogin 
{
    Conexion conexion;
    PreparedStatement ps;
    PreparedStatement tr = null;
    PreparedStatement com = null;
    PreparedStatement rll = null;
    ResultSet rs;
   
    public ControladorLogin()
    {
        this.conexion = new Conexion();
    }
    
    public void IniciarSesion(JTextField user, JPasswordField pass, JFrame ventanaActual)
    {
        String usuario = user.getText();
        String contra = new String(pass.getPassword());
        
        if (usuario.isEmpty() || contra.isEmpty() || contra.equalsIgnoreCase("") || usuario.equalsIgnoreCase("Ingrese su nombre de usuario...") || contra.equalsIgnoreCase("********")) 
        {
            JOptionPane.showMessageDialog(null, "Por favor ingrese su nombre de usuario y contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return; // Salir si los campos están vacíos
        }
        
        //Creamos la conexion a la BD
        Conexion objConexion = new Conexion();
        
        Login objLogin = new Login();
        
        //Creamos la consulta
        
        String sql = "SELECT * FROM login WHERE user = '"+usuario+"' AND password = sha('"+contra+"');";
        
        try
        {
            //Creando la conexión con la base de datos
            Statement st = objConexion.conectar().createStatement();
            
            //Ejecutamos la consulta
            ResultSet rs = st.executeQuery(sql);
            
            
            if(rs.next())
            {
                objConexion.conectar();
                JOptionPane.showMessageDialog(null, "¡Bienvenido " + usuario + "!");

                Main pantallaPrincipal = new Main();
                pantallaPrincipal.setVisible(true);

                // Cierra la ventana actual
                ventanaActual.dispose();
            }
            else
            {
                // Usuario o contraseña incorrectos
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error: " +e);
        }
    }
    
    public void AgregarUsuario(JTextField id, JTextField usuario, JTextField contra) throws SQLException
    {
        Login objLogin = new Login();
        
        Connection con = conexion.conectar();
        String transaction = "START TRANSACTION";
        String consulta = "INSERT INTO login(user, password, idEmpleado) VALUES(?, sha(?), ?);";
        String commit = "COMMIT";
        String roll = "ROLLBACK";
        
        ps = null;
        
        try
        {
            objLogin.setUser(usuario.getText());
            objLogin.setPassword(contra.getText());
            
            ps = con.prepareStatement(consulta);
            
            ps.setString(1, objLogin.getUser());
            ps.setString(2, objLogin.getPassword());
            ps.setInt(3, objLogin.getIdEmpleado());
            
            tr = con.prepareStatement(transaction);
            com = con.prepareStatement(commit);
            rll = con.prepareStatement(roll);
            
            //Ejecutar las insercciones
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Se guardo correctamente el usuario");
        }
        catch(Exception e)
        {
            rll.execute();
            JOptionPane.showMessageDialog(null, "Error al guardar el usuario: " + e.toString());
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    
}
