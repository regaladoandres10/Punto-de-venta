
package Controladores;

import javax.swing.JTable;
import Conexion.Conexion;
import Models.Productos;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JRadioButton;

/**
 *
 * @author Andres
 */
public class CRUDProductos 
{
    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    public CRUDProductos()
    {
        this.conexion = new Conexion();
    }
    
    //Mostrar Productos
    
    public void mostrarProductos(JTable tableProducts)
    {
        Conexion objConexion = new Conexion();
        //objConexion.conectar();
        
        Productos objProducto = new Productos();
        
        //Creacion del objeto para utilizar la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "SELECT * FROM producto;";
        
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
                objProducto.setIdProducto(rs.getInt("idproducto"));
                objProducto.setNombre(rs.getString("nombre"));
                objProducto.setCodigo(rs.getString("codigo"));
                objProducto.setIdCategoria(rs.getInt("idcategoria"));
                objProducto.setCantidadPorUnidad(rs.getInt("cantidadPorUnidad"));
                objProducto.setPrecioUnitario(rs.getDouble("precioUnitario"));
                objProducto.setUnidadesEnAlmacen(rs.getInt("unidadesEnAlamcen"));
                objProducto.setUnidadesEnOrden(rs.getInt("unidadesEnOrden"));
                objProducto.setNivelDeReorden(rs.getInt("nivelDeReorden"));
                objProducto.setDescontinuado(rs.getBoolean("descontinuado"));
                
                //Obtemos los valores 
                modelo.addRow(new Object[]{objProducto.getIdProducto(),
                                            objProducto.getNombre(),
                                            objProducto.getCodigo(),
                                            objProducto.getIdCategoria(),
                                            objProducto.getCantidadPorUnidad(),
                                            objProducto.getPrecioUnitario(),
                                            objProducto.getUnidadesEnAlmacen(),
                                            objProducto.getCantidadPorUnidad(),
                                            objProducto.getNivelDeReorden(),
                                            objProducto.isDescontinuado()});
            }
            
            tableProducts.setModel(modelo);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar los usuarioss" + e.toString());
        }
        finally
        {
            objConexion.closeConexion();
        }
        
        //Ingresando las columnas dentro del jtable
        
        modelo.addColumn("IdProducto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo");
        modelo.addColumn("idCategoria");
        modelo.addColumn("CantidadPorUnidad");
        modelo.addColumn("PrecioUnitario");
        modelo.addColumn("UnidadesEnAlmacen");
        modelo.addColumn("UnidadesEnOrden");
        modelo.addColumn("NivelDeReorden");
        modelo.addColumn("Descontinuado");        
        
        tableProducts.setModel(modelo);
        
    }
    
    public void AgregarProductos(JTextField nombre, 
                             JTextField Codigo, 
                             JTextField idCategoria, 
                             JTextField CantidadPorUnidad,
                             JTextField precio,
                             JTextField unidadesAlmacen,
                             JTextField unidadesOrden,
                             JTextField nivel) throws SQLException 
    {

        Productos objProductos = new Productos();

        // Asegúrate de que `conexion` esté inicializada
        Connection con = conexion.conectar();
        String consulta = "INSERT INTO producto(nombre, codigo, idcategoria, cantidadPorUnidad, precioUnitario, unidadesEnAlamcen, unidadesEnOrden, nivelDeReorden, descontinuado) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, 0);";

        PreparedStatement ps = null;

        try 
        {  
            // Asigna valores a los atributos del producto
            objProductos.setNombre(nombre.getText());
            objProductos.setCodigo(Codigo.getText());
            objProductos.setIdCategoria(Integer.parseInt(idCategoria.getText()));
            objProductos.setCantidadPorUnidad(Integer.parseInt(CantidadPorUnidad.getText()));
            objProductos.setPrecioUnitario(Double.parseDouble(precio.getText()));
            objProductos.setUnidadesEnAlmacen(Integer.parseInt(unidadesAlmacen.getText()));
            objProductos.setUnidadesEnOrden(Integer.parseInt(unidadesOrden.getText()));
            objProductos.setNivelDeReorden(Integer.parseInt(nivel.getText()));
            //objProductos.setDescontinuado(descontinuado.isSelected()); // Usa isSelected() para JRadioButton

            // Prepara la declaración SQL
            ps = con.prepareStatement(consulta);

            ps.setString(1, objProductos.getNombre());
            ps.setString(2, objProductos.getCodigo());
            ps.setInt(3, objProductos.getIdCategoria());
            ps.setInt(4, objProductos.getCantidadPorUnidad());
            ps.setDouble(5, objProductos.getPrecioUnitario());
            ps.setInt(6, objProductos.getUnidadesEnAlmacen());
            ps.setInt(7, objProductos.getUnidadesEnOrden());
            ps.setInt(8, objProductos.getNivelDeReorden());
            //ps.setBoolean(9, objProductos.isDescontinuado());

            // Ejecuta la inserción
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se guardó correctamente");

        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.toString());
        } finally 
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void modificarProductos(JTextField id,
                             JTextField nombre, 
                             JTextField Codigo, 
                             JTextField idCategoria, 
                             JTextField CantidadPorUnidad,
                             JTextField precio,
                             JTextField unidadesAlmacen,
                             JTextField unidadesOrden,
                             JTextField nivel) throws SQLException
    {
        Conexion objConexion = new Conexion();
        Productos objProducto = new Productos();
        
        Connection con = conexion.conectar();
        String consulta = "UPDATE producto SET nombre = ?, codigo = ?, idcategoria = ?, cantidadPorUnidad = ?, precioUnitario = ?, unidadesEnAlamcen = ?, unidadesEnOrden = ?, nivelDeReorden = ?, descontinuado = 0 WHERE idproducto = ?;";
        
        try
        {
            objProducto.setIdProducto(Integer.parseInt(id.getText()));
            objProducto.setNombre(nombre.getText());
            objProducto.setCodigo(Codigo.getText());
            objProducto.setIdCategoria(Integer.parseInt(idCategoria.getText()));
            objProducto.setCantidadPorUnidad(Integer.parseInt(CantidadPorUnidad.getText()));
            objProducto.setPrecioUnitario(Double.parseDouble(precio.getText()));
            objProducto.setUnidadesEnAlmacen(Integer.parseInt(unidadesAlmacen.getText()));
            objProducto.setUnidadesEnOrden(Integer.parseInt(unidadesOrden.getText()));
            objProducto.setNivelDeReorden(Integer.parseInt(nivel.getText()));
            
            // Prepara la declaración SQL
            ps = con.prepareStatement(consulta);

            ps.setString(1, objProducto.getNombre());
            ps.setString(2, objProducto.getCodigo());
            ps.setInt(3, objProducto.getIdCategoria());
            ps.setInt(4, objProducto.getCantidadPorUnidad());
            ps.setDouble(5, objProducto.getPrecioUnitario());
            ps.setInt(6, objProducto.getUnidadesEnAlmacen());
            ps.setInt(7, objProducto.getUnidadesEnOrden());
            ps.setInt(8, objProducto.getNivelDeReorden());
            ps.setInt(9, objProducto.getIdProducto());
            //ps.setBoolean(9, objProductos.isDescontinuado());

            // Ejecuta la inserción
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Producto modificado con exito");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al modificar producto: " + e.toString());
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void limpiarCampos(JTextField id,
                             JTextField nombre, 
                             JTextField Codigo, 
                             JTextField idCategoria, 
                             JTextField CantidadPorUnidad,
                             JTextField precio,
                             JTextField unidadesAlmacen,
                             JTextField unidadesOrden,
                             JTextField nivel)
    {
        id.setText("");
        nombre.setText("");
        Codigo.setText("");
        idCategoria.setText("");
        CantidadPorUnidad.setText("");
        precio.setText("");
        unidadesAlmacen.setText("");
        unidadesOrden.setText("");
        nivel.setText("");
    }
    
    public void borrarProductos(JTextField id) throws SQLException
    {
        Conexion objConexion = new Conexion();
        Productos objProducto = new Productos();
        
        Connection con = conexion.conectar();
        String consulta = "DELETE FROM producto WHERE idproducto = ?;";
        
        try
        {
            objProducto.setIdProducto(Integer.parseInt(id.getText()));
            
            // Prepara la declaración SQL
            ps = con.prepareStatement(consulta);

            ps.setInt(1, objProducto.getIdProducto());
            

            // Ejecuta la inserción
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Producto borrado con exito");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se elimino correctamemte error " + e.toString());
        }
        finally
        {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
}
