
package Controladores;
import Conexion.Conexion;
import Models.Login;
import Front_end.Formularios.Main;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author Andres
 */
public class ControladorLogin 
{
    Conexion conexion;
    PreparedStatement ps;
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
    
    
}
