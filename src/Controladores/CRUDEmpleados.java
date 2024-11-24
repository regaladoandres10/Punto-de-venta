
package Controladores;

import Conexion.Conexion;
import Models.Productos;
import Models.Empleados;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.time.ZoneId;


/**
 *
 * @author Andres
 */
public class CRUDEmpleados 
{
    Conexion conexion;
    PreparedStatement ps;
    PreparedStatement tr = null;
    PreparedStatement com = null;
    PreparedStatement rll = null;
    ResultSet rs;
    
    public CRUDEmpleados()
    {
        this.conexion = new Conexion();
    }
    
    //Mostrar Empleados
    
    public void mostrarEmpleados(JTable tableEmpleados)
    {
        Conexion objConexion = new Conexion();
        //objConexion.conectar();
        
        Empleados objEmpleados = new Empleados();
        
        //Creacion del objeto para utilizar la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
        
        modelo.addColumn("IdEmpleado");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Titulo");
        modelo.addColumn("FechaNacimiento");
        modelo.addColumn("FechaContratacion");
        modelo.addColumn("Direccion");
        modelo.addColumn("Ciudad");
        modelo.addColumn("CodigoPostal");
        modelo.addColumn("Telefono");
        modelo.addColumn("Extension");
        
        tableEmpleados.setModel(modelo);
        
        sql = "SELECT * FROM empleado;";
        
        try
        {
            // Creando la conexion con la base de datos
            Statement st = objConexion.conectar().createStatement();
            
            //Ejecutamos la consulta
            ResultSet rs = st.executeQuery(sql);
            
            //Recorrer todos los registros
            
            while(rs.next())
            {
                //Asigno los valores en el jtable traidos de la BD
                objEmpleados.setIdEmpleado(rs.getInt("idempleado"));
                objEmpleados.setNombre(rs.getString("nombre"));
                objEmpleados.setApellido(rs.getString("apellido"));
                objEmpleados.setTitulo(rs.getString("titulo"));
                Date sqlDate = rs.getDate("fechaNacimiento");
                LocalDate localDate = sqlDate.toLocalDate();
                objEmpleados.setFechaNacimiento(localDate);
                Date sqlDateContra = rs.getDate("Contratacion");
                LocalDate localDate2 = sqlDateContra.toLocalDate();
                objEmpleados.setFechaContratacion(localDate2);
                objEmpleados.setDireccion(rs.getString("direccion"));
                objEmpleados.setCiudad(rs.getString("ciudad"));
                objEmpleados.setCodigoPostal(rs.getString("codigoPostal"));
                objEmpleados.setTelefono(rs.getString("telefono"));
                objEmpleados.setExtension(rs.getString("extension"));
                
                
                //Obtemos los valores 
                modelo.addRow(new Object[]{objEmpleados.getIdEmpleado(),
                                            objEmpleados.getNombre(),
                                            objEmpleados.getApellido(),
                                            objEmpleados.getTitulo(),
                                            objEmpleados.getFechaNacimiento(),
                                            objEmpleados.getFechaContratacion(),
                                            objEmpleados.getDireccion(),
                                            objEmpleados.getCiudad(),
                                            objEmpleados.getCodigoPostal(),
                                            objEmpleados.getTelefono(),
                                            objEmpleados.getExtension()});
            }
            
            tableEmpleados.setModel(modelo);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar los empleados" + e.toString());
        }
        finally
        {
            objConexion.closeConexion();
        }
        
    }
    public void AgregarEmpleados(JTextField nombre, 
                             JTextField apellido, 
                             JTextField titulo, 
                             JDateChooser fechaNacimiento,
                             JDateChooser fechaContratacion,
                             JTextField direccion,
                             JTextField ciudad,
                             JTextField cp,
                             JTextField telefono,
                             JTextField extension) throws SQLException 
    {

        Empleados objEmpleados = new Empleados();

        Connection con = conexion.conectar();
        String transaccion = "START TRANSACTION;";
        String consulta = "INSERT INTO empleado(nombre, apellido, titulo, fechaNacimiento, Contratacion, direccion, ciudad, codigoPostal, telefono, extension) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String comm = "COMMIT;";
        String roll = "ROLLBACK;";
        
        ps = null;
        
        
        
        try 
        {  
            // Asigna valores a los atributos del producto
            
            objEmpleados.setNombre(nombre.getText());
            objEmpleados.setApellido(apellido.getText());
            objEmpleados.setTitulo(titulo.getText());
            
            
            LocalDate fechaNacimientoLocalDate = fechaNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            objEmpleados.setFechaNacimiento(fechaNacimientoLocalDate);
            
            LocalDate fechaContratacionLocalDate = fechaContratacion.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            objEmpleados.setFechaContratacion(fechaContratacionLocalDate);
            
            objEmpleados.setDireccion(direccion.getText());
            objEmpleados.setCiudad(ciudad.getText());
            objEmpleados.setCodigoPostal(cp.getText());
            objEmpleados.setTelefono(telefono.getText());
            objEmpleados.setExtension(extension.getText());      

            // Prepara la declaración SQL
            ps = con.prepareStatement(consulta);

            ps.setString(1, objEmpleados.getNombre());
            ps.setString(2, objEmpleados.getApellido());
            ps.setString(3, objEmpleados.getTitulo());
            ps.setDate(4, Date.valueOf(objEmpleados.getFechaNacimiento()));
            ps.setDate(5, Date.valueOf(objEmpleados.getFechaContratacion()));
            ps.setString(6, objEmpleados.getDireccion());
            ps.setString(7, objEmpleados.getCiudad());
            ps.setString(8, objEmpleados.getCodigoPostal());
            ps.setString(9, objEmpleados.getTelefono());
            ps.setString(10, objEmpleados.getExtension());
            
            tr = con.prepareStatement(transaccion);
            com = con.prepareStatement(comm);
            rll = con.prepareStatement(roll);
            
            // Ejecuta la inserción
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Se guardó correctamente el empleado.");

        }
        catch(Exception e) 
        {
            rll.execute();
            JOptionPane.showMessageDialog(null, "Error al guardar el empleado: " + e.toString());
        } finally 
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        
        
    }
    
    public void Seleccionar(JTable tableEmployes,
                             JTextField id,
                             JTextField nombre, 
                             JTextField apellido, 
                             JTextField titulo, 
                             JDateChooser fechaNacimiento,
                             JDateChooser fechaContratacion,
                             JTextField direccion,
                             JTextField ciudad,
                             JTextField cp,
                             JTextField telefono,
                             JTextField extension)
    {
        int fila = tableEmployes.getSelectedRow();
        
        try
        {
            if(fila >= 0)
            {
                id.setText(tableEmployes.getValueAt(fila, 0).toString());
                nombre.setText(tableEmployes.getValueAt(fila, 1).toString());
                apellido.setText(tableEmployes.getValueAt(fila, 2).toString());
                titulo.setText(tableEmployes.getValueAt(fila, 3).toString());
                fechaNacimiento.setDate(Date.valueOf(tableEmployes.getValueAt(fila, 4).toString()));
                fechaContratacion.setDate(Date.valueOf(tableEmployes.getValueAt(fila, 5).toString()));
                direccion.setText(tableEmployes.getValueAt(fila, 6).toString());
                ciudad.setText(tableEmployes.getValueAt(fila, 7).toString());
                cp.setText(tableEmployes.getValueAt(fila, 8).toString());
                telefono.setText(tableEmployes.getValueAt(fila, 9).toString());
                extension.setText(tableEmployes.getValueAt(fila, 10).toString());
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al seleccionar: " + e.toString());
        }   
    }
    
    public void modificarEmpleados(JTextField id,
                             JTextField nombre, 
                             JTextField apellido, 
                             JTextField titulo, 
                             JDateChooser fechaNacimiento,
                             JDateChooser fechaContratacion,
                             JTextField direccion,
                             JTextField ciudad,
                             JTextField cp,
                             JTextField telefono,
                             JTextField extension) throws SQLException
    {
        Conexion objConexion = new Conexion();
        Empleados objEmpleados = new Empleados();
        
        Connection con = conexion.conectar();
        String transaccion = "START TRANSACTION;";
        String consulta = "UPDATE empleado SET nombre = ?, apellido = ?, titulo = ?, fechaNacimiento = ?, Contratacion = ?, direccion = ?, ciudad = ?, codigoPostal = ?, telefono = ?, extension = ? WHERE idempleado = ?;";
        String comm = "COMMIT;";
        String roll = "ROLLBACK;";
        
        try
        {
            objEmpleados.setIdEmpleado(Integer.parseInt(id.getText()));
            objEmpleados.setNombre(nombre.getText());
            objEmpleados.setApellido(apellido.getText());
            objEmpleados.setTitulo(titulo.getText());
            
            
            LocalDate fechaNacimientoLocalDate = fechaNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            objEmpleados.setFechaNacimiento(fechaNacimientoLocalDate);
            
            LocalDate fechaContratacionLocalDate = fechaContratacion.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            objEmpleados.setFechaContratacion(fechaContratacionLocalDate);
            
            objEmpleados.setDireccion(direccion.getText());
            objEmpleados.setCiudad(ciudad.getText());
            objEmpleados.setCodigoPostal(cp.getText());
            objEmpleados.setTelefono(telefono.getText());
            objEmpleados.setExtension(extension.getText());      
            
            // Prepara la declaración SQL
            ps = con.prepareStatement(consulta);

            ps.setString(1, objEmpleados.getNombre());
            ps.setString(2, objEmpleados.getApellido());
            ps.setString(3, objEmpleados.getTitulo());
            ps.setDate(4, Date.valueOf(objEmpleados.getFechaNacimiento()));
            ps.setDate(5, Date.valueOf(objEmpleados.getFechaContratacion()));
            ps.setString(6, objEmpleados.getDireccion());
            ps.setString(7, objEmpleados.getCiudad());
            ps.setString(8, objEmpleados.getCodigoPostal());
            ps.setString(9, objEmpleados.getTelefono());
            ps.setString(10, objEmpleados.getExtension());
            ps.setInt(11, objEmpleados.getIdEmpleado());
            
            tr = con.prepareStatement(transaccion);
            com = con.prepareStatement(comm);
            rll = con.prepareStatement(roll);

            // Ejecuta la inserción
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Empleado modificado con exito");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al modificar el empleado: " + e.toString());
            rll.execute();
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void limpiarCampos(JTextField id,
                             JTextField nombre, 
                             JTextField apellido, 
                             JTextField titulo, 
                             JDateChooser fechaNacimiento,
                             JDateChooser fechaContratacion,
                             JTextField direccion,
                             JTextField ciudad,
                             JTextField cp,
                             JTextField telefono,
                             JTextField extension) 
    {
        id.setText("");
        nombre.setText("");
        apellido.setText("");
        titulo.setText("");
        fechaNacimiento.setDate(null);
        fechaContratacion.setDate(null);
        direccion.setText("");
        ciudad.setText("");
        cp.setText("");
        telefono.setText("");
        extension.setText("");
    }
    
    public void borrarEmpleado(JTextField id) throws SQLException
    {
        Conexion objConexion = new Conexion();
        Empleados objEmpleados = new Empleados();
        
        Connection con = conexion.conectar();
        String transaccion = "START TRANSACTION;";
        String consulta = "DELETE FROM empleado WHERE idEmpleado = ?;";
        String comm = "COMMIT;";
        String roll = "ROLLBACK;";
        
        try
        {
            objEmpleados.setIdEmpleado(Integer.parseInt(id.getText()));
            
            // Prepara la declaración SQL
            ps = con.prepareStatement(consulta);

            ps.setInt(1, objEmpleados.getIdEmpleado());
            tr = con.prepareStatement(transaccion);
            com = con.prepareStatement(comm);
            rll = con.prepareStatement(roll);
            

            // Ejecuta la inserción
            tr.execute();
            ps.executeUpdate();
            com.execute();
            
            JOptionPane.showMessageDialog(null, "Empleado borrado exitosamente");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se elimino correctamemte el empleado: " + e.toString());
            rll.execute();
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
}
