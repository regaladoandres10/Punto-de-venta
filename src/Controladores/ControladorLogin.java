
package Controladores;
import Conexion.Conexion;
import Models.Login;
import Models.UsuarioActivo;
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
            return; //Salir si los campos están vacíos
        }
        
        //Creamos la conexion a la BD
        Conexion objConexion = new Conexion();
        String sql = "SELECT * FROM login WHERE user = ? AND password = SHA(?);";
        
        try (Connection con = objConexion.conectar(); 
         PreparedStatement ps = con.prepareStatement(sql)) 
        {
        
            ps.setString(1, usuario);
            ps.setString(2, contra);

            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    // Almacena los datos del usuario en UsuarioActivo
                    UsuarioActivo.setId(rs.getInt("id"));
                    UsuarioActivo.setUser(rs.getString("user"));
                    UsuarioActivo.setIdEmpleado(rs.getInt("idempleado"));

                    JOptionPane.showMessageDialog(null, "¡Bienvenido " + usuario + "!");
                    Main pantallaPrincipal = new Main();
                    pantallaPrincipal.setVisible(true);

                    ventanaActual.dispose();
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void AgregarUsuario(JTextField id, JTextField usuario, JTextField contra) throws SQLException
    {
        Login objLogin = new Login();
        
        Connection con = conexion.conectar();
        String transaction = "START TRANSACTION";
        String consulta = "INSERT INTO login(user, password, idempleado) VALUES(?, sha(?), ?);";
        String commit = "COMMIT";
        String roll = "ROLLBACK";
        
        ps = null;
        
        try
        {
            objLogin.setUser(usuario.getText());
            objLogin.setPassword(contra.getText());
            objLogin.setIdEmpleado(Integer.parseInt(id.getText()));
            
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
    
    public void modificarUsuario(JTextField id, JTextField usuario, JTextField contra, JTextField idEmpleado) throws SQLException {
    Login objLogin = new Login();

    Connection con = conexion.conectar();
    String transaction = "START TRANSACTION";
    String consulta = "UPDATE login SET user = ?, password = SHA(?), idempleado = ? WHERE id = ?;";
    String commit = "COMMIT";
    String roll = "ROLLBACK";

    try 
    {
        //Validar campos de entrada
        if (id.getText().isEmpty() || idEmpleado.getText().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Los campos ID e IDEmpleado no pueden estar vacíos.");
            return;
        }

        //Validar si los valores son números
        try 
        {
            objLogin.setId(Integer.parseInt(id.getText()));
            objLogin.setIdEmpleado(Integer.parseInt(idEmpleado.getText()));
        } 
        catch (NumberFormatException ex) 
        {
            JOptionPane.showMessageDialog(null, "Los campos ID e IDEmpleado deben ser números válidos.");
            return;
        }

        objLogin.setUser(usuario.getText());
        objLogin.setPassword(contra.getText());

        ps = con.prepareStatement(consulta);

        ps.setString(1, objLogin.getUser());
        ps.setString(2, objLogin.getPassword());
        ps.setInt(3, objLogin.getIdEmpleado());
        ps.setInt(4, objLogin.getId());

        tr = con.prepareStatement(transaction);
        com = con.prepareStatement(commit);
        rll = con.prepareStatement(roll);

        tr.execute();
        ps.executeUpdate();
        com.execute();

        JOptionPane.showMessageDialog(null, "Usuario modificado con éxito");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error al modificar el usuario: " + e.toString());
            rll.execute();
        }
        finally 
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }

    
    public void limpiarCamposUsuarios(JTextField id, JTextField usuario, JTextField contra, JTextField idEmpleado)
    {
        id.setText("");
        usuario.setText("");
        contra.setText("");
        idEmpleado.setText("");
    }
    
    public void borrarUsuario(JTextField id) throws SQLException
    {
        Login objLogin = new Login();
        
        Connection con = conexion.conectar();
        String transaction = "START TRANSACTION";
        String consulta = "DELETE FROM login WHERE id = ?;";
        String commit = "COMMIT";
        String roll = "ROLLBACK";
        
        try
        {
            objLogin.setId(Integer.parseInt(id.getText()));
            ps = con.prepareStatement(consulta);
            
            ps.setInt(1, objLogin.getId());
            
            tr = con.prepareStatement(transaction);
            com = con.prepareStatement(commit);
            rll = con.prepareStatement(roll);
            
            //Ejecutar las insercciones
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Usuario borrado con exito");
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al borrar el usuario: " + e.toString());
            rll.execute();
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
}
