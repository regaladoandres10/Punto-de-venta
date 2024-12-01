
package Controladores;
import Conexion.Conexion;
import Models.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Andres
 */
public class CRUDClientes 
{
    Conexion conexion;
    PreparedStatement ps;
    PreparedStatement tr = null;
    PreparedStatement com = null;
    PreparedStatement rll = null;
    ResultSet rs;
    
    public CRUDClientes()
    {
        this.conexion = new Conexion();
    }
    
    //Mostrr clientes
    
    public void mostrarClientes(JTable tableCustomers)
    {
        Conexion objConexion = new Conexion();
        Clientes objClientes = new Clientes();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
        
        modelo.addColumn("idCliente");
        modelo.addColumn("nombreCompañia");
        modelo.addColumn("nombreContacto");
        modelo.addColumn("tituloContacto");
        modelo.addColumn("direccion");
        modelo.addColumn("ciudad");
        modelo.addColumn("codigoPostal");
        modelo.addColumn("pais");
        modelo.addColumn("telefono");
        
        tableCustomers.setModel(modelo);
        sql = "SELECT idCliente, nombreCompañia, nombreContacto, tituloContacto, direccion, ciudad, codigoPostal, pais, telefono FROM clientes";
        
        try
        {
            Statement st = objConexion.conectar().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                objClientes.setIdCliente(rs.getInt("idCliente"));
                objClientes.setNombreCompañia(rs.getString("nombreCompañia"));
                objClientes.setNombreContacto(rs.getString("nombreContacto"));
                objClientes.setTituloContacto(rs.getString("tituloContacto"));
                objClientes.setDireccion(rs.getString("direccion"));
                objClientes.setCiudad(rs.getString("ciudad"));
                objClientes.setCodigoPostal(rs.getString("codigoPostal"));
                objClientes.setPais(rs.getString("Pais"));
                objClientes.setTelefono(rs.getString("telefono"));
                
                modelo.addRow(new Object[]{objClientes.getIdCliente(),
                                            objClientes.getNombreCompañia(),
                                            objClientes.getNombreContacto(),
                                            objClientes.getTituloContacto(),
                                            objClientes.getDireccion(),
                                            objClientes.getCiudad(),
                                            objClientes.getCodigoPostal(),
                                            objClientes.getPais(),
                                            objClientes.getTelefono()});
                
                
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar los clientes" + e.toString());
            
        }
        finally
        {
            objConexion.closeConexion();
        }
    }   
    
    public void AgregarClientes(JTextField nombreCompañia,
                                JTextField nombreContacto,
                                JTextField tituloContacto,
                                JTextField direccion,
                                JTextField ciudad,
                                JTextField codigoPostal,
                                JTextField pais,
                                JTextField telefono) throws SQLException
    {
        Clientes objClientes = new Clientes();
        
        Connection con = conexion.conectar();
        String transaction = "START TRANSACTION";
        String consulta = "CALL guardarClientes(?, ?, ?, ?, ?, ?, ?, ?);";
        String commit = "COMMIT";
        String roll = "ROLLBACK";
        
        ps = null;
        
        try
        {
            objClientes.setNombreCompañia(nombreCompañia.getText());
            objClientes.setNombreContacto(nombreContacto.getText());
            objClientes.setTituloContacto(tituloContacto.getText());
            objClientes.setDireccion(direccion.getText());
            objClientes.setCiudad(ciudad.getText());
            objClientes.setCodigoPostal(codigoPostal.getText());
            objClientes.setPais(pais.getText());
            objClientes.setTelefono(telefono.getText());
            
            ps = con.prepareStatement(consulta);
            
            ps.setString(1, objClientes.getNombreCompañia());
            ps.setString(2, objClientes.getNombreContacto());
            ps.setString(3, objClientes.getTituloContacto());
            ps.setString(4, objClientes.getDireccion());
            ps.setString(5, objClientes.getCiudad());
            ps.setString(6, objClientes.getCodigoPostal());
            ps.setString(7, objClientes.getPais());
            ps.setString(8, objClientes.getTelefono());
            
            tr = con.prepareStatement(transaction);
            com = con.prepareStatement(commit);
            rll = con.prepareStatement(roll);
            
            //Ejecutar las insercciones
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Se guardo correctamente el cliente");
            
        }
        catch(Exception e)
        {
            rll.execute();
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + e.toString());
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void Seleccionar(JTable tableCustomers,
                            JTextField id,
                            JTextField nombreCompañia,
                            JTextField nombreContacto,
                            JTextField tituloContacto,
                            JTextField direccion,
                            JTextField ciudad,
                            JTextField codigoPostal,
                            JTextField pais,
                            JTextField telefono)
    {
        int fila = tableCustomers.getSelectedRow();
        
        try
        {
            if(fila >= 0)
            {
                id.setText(tableCustomers.getValueAt(fila, 0).toString());
                nombreCompañia.setText(tableCustomers.getValueAt(fila, 1).toString());
                nombreContacto.setText(tableCustomers.getValueAt(fila, 2).toString());
                tituloContacto.setText(tableCustomers.getValueAt(fila, 3).toString());
                direccion.setText(tableCustomers.getValueAt(fila, 4).toString());
                ciudad.setText(tableCustomers.getValueAt(fila, 5).toString());
                codigoPostal.setText(tableCustomers.getValueAt(fila, 6).toString());
                pais.setText(tableCustomers.getValueAt(fila, 7).toString());
                telefono.setText(tableCustomers.getValueAt(fila, 8).toString());
                
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al seleccionar: " + e.toString());
        }
        
    }
    
    public void modificarClientes(JTextField id,
                            JTextField nombreCompañia,
                            JTextField nombreContacto,
                            JTextField tituloContacto,
                            JTextField direccion,
                            JTextField ciudad,
                            JTextField codigoPostal,
                            JTextField pais,
                            JTextField telefono) throws SQLException
    {
        Conexion objConexion = new Conexion();
        Clientes objClientes = new Clientes();
        
        Connection con = conexion.conectar();
        String transaction = "START TRANSACTION";
        String consulta = "CALL actualizarClientes(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String commit = "COMMIT";
        String roll = "ROLLBACK";
        
        try
        {
            objClientes.setIdCliente(Integer.parseInt(id.getText()));
            objClientes.setNombreCompañia(nombreCompañia.getText());
            objClientes.setNombreContacto(nombreContacto.getText());
            objClientes.setTituloContacto(tituloContacto.getText());
            objClientes.setDireccion(direccion.getText());
            objClientes.setCiudad(ciudad.getText());
            objClientes.setCodigoPostal(codigoPostal.getText());
            objClientes.setPais(pais.getText());
            objClientes.setTelefono(telefono.getText());
            
            ps = con.prepareStatement(consulta);
            
            ps.setInt(1, objClientes.getIdCliente());
            ps.setString(2, objClientes.getNombreCompañia());
            ps.setString(3, objClientes.getNombreContacto());
            ps.setString(4, objClientes.getTituloContacto());
            ps.setString(5, objClientes.getDireccion());
            ps.setString(6, objClientes.getCiudad());
            ps.setString(7, objClientes.getCodigoPostal());
            ps.setString(8, objClientes.getPais());
            ps.setString(9, objClientes.getTelefono());
            
            
            tr = con.prepareStatement(transaction);
            com = con.prepareStatement(commit);
            rll = con.prepareStatement(roll);
            
            //Ejecutar las insercciones
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al modificar el cliente: " + e.toString());
            rll.execute();
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        
    }
    
    public void limpiarCampos(JTextField id,
                            JTextField nombreCompañia,
                            JTextField nombreContacto,
                            JTextField tituloContacto,
                            JTextField direccion,
                            JTextField ciudad,
                            JTextField codigoPostal,
                            JTextField pais,
                            JTextField telefono)
    {
        id.setText("");
        nombreCompañia.setText("");
        nombreContacto.setText("");
        tituloContacto.setText("");
        direccion.setText("");
        ciudad.setText("");
        codigoPostal.setText("");
        pais.setText("");
        telefono.setText("");
        
    }
    
    public void borrarCliente(JTextField id) throws SQLException
    {
        Conexion objConexion = new Conexion();
        Clientes objClientes = new Clientes();
        
        Connection con = conexion.conectar();
        String transaction = "START TRANSACTION";
        String consulta = "CALL eliminarCliente(?);";
        String commit = "COMMIT";
        String roll = "ROLLBACK";
        
        try
        {
            objClientes.setIdCliente(Integer.parseInt(id.getText()));
            
            //Preparar la declaracion SQL
            ps = con.prepareStatement(consulta);
            ps.setInt(1, objClientes.getIdCliente());
            
            tr = con.prepareStatement(transaction);
            com = con.prepareStatement(commit);
            rll = con.prepareStatement(roll);
            
            //Ejecutar las insercciones
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Cliente borrado exitosamente");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se elimino correctamemte el cliente: " + e.toString());
            rll.execute();
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
}
