/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author leoch
 */
public class ControladorVenta {
    
    
    public void buscarProducto(JTextField nombreProducto, JTable tablaProductos){
    
    Conexion.Conexion objetoConexion = new Conexion.Conexion();
    Models.Productos objetoProducto = new Models.Productos();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Codigo");
    modelo.addColumn("Nombre");
    modelo.addColumn("Precio");
    modelo.addColumn("Stock");
    
    tablaProductos.setModel(modelo);
    
        try {
            String consulta = "select * from producto where producto.codigo like ?";
            PreparedStatement ps = objetoConexion.conectar().prepareStatement(consulta);
            ps.setString(1, nombreProducto.getText()+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                objetoProducto.setCodigo(rs.getString("codigo"));
                objetoProducto.setNombre(rs.getString("nombre"));
                objetoProducto.setPrecioUnitario(rs.getDouble("precioUnitario"));
                objetoProducto.setUnidadesEnAlmacen(rs.getInt("unidadesEnAlmacen"));
                
                modelo.addRow(new Object[]{objetoProducto.getCodigo(),objetoProducto.getNombre(),objetoProducto.getPrecioUnitario(),objetoProducto.getUnidadesEnAlmacen()});
            }
            
            tablaProductos.setModel(modelo);
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,"Error al mostrar los productos "+ e.toString());
        }finally {
            objetoConexion.closeConexion();
        }
        
        for (int column = 0; column < tablaProductos.getColumnCount(); column++) {
            Class <?> columnClass = tablaProductos.getColumnClass(column);
            tablaProductos.setDefaultEditor(columnClass,null);
            
        }
        
        
    }
    
    public void seleccionarProductoVenta(JTable tablaProductos, JTextField codigo, JTextField nombre ,JTextField precio, JTextField stock, JTextField precioVenta){
        
        int fila = tablaProductos.getSelectedRow();
        try {
            codigo.setText(tablaProductos.getValueAt(fila, 0).toString());
            nombre.setText(tablaProductos.getValueAt(fila, 1).toString());
            precio.setText(tablaProductos.getValueAt(fila, 2).toString());
            stock.setText(tablaProductos.getValueAt(fila, 3).toString());
            precioVenta.setText(tablaProductos.getValueAt(fila, 2).toString());
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error "+e);
        } 
    }
    
    public void pasarProductosVenta(JTable tablaResumen, JTextField codigo, JTextField nombre,JTextField precioVenta, JTextField stock,JTextField cantidad, JTextField buscador){
        
        DefaultTableModel modelo = (DefaultTableModel) tablaResumen.getModel();
        
        int disponible = Integer.parseInt(stock.getText());
        String codigo1 = codigo.getText();
        
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String existente = (String) modelo.getValueAt(i,0);
            if(existente.equals(codigo1)){
                JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                return;
            }
        }
        
        String nombre1 = nombre.getText();
        double precio1 = Double.parseDouble(precioVenta.getText());
        int cantidad1 = Integer.parseInt(cantidad.getText());
        
        if(cantidad1 > disponible){
            JOptionPane.showMessageDialog(null, "La cantidad a comprar es mayor a el stock");
            return;
        }
        
        if(cantidad.getText() == null){
            JOptionPane.showMessageDialog(null, "No seleccionaste una cantidad a vender");
        }
        
        double subtotal = precio1*cantidad1;
        
        modelo.addRow(new Object[] {codigo1,nombre1,precio1,cantidad1,subtotal}) ;
        buscador.setText("");
        buscador.requestFocus();
        cantidad.setText("");
    }
    
    public void eliminearProductosVenta(JTable tablaResumen){
    
        DefaultTableModel modelo = (DefaultTableModel) tablaResumen.getModel();
        
        int indiceSeleccionado= tablaResumen.getSelectedRow();
        
        if(indiceSeleccionado != -1){
            modelo.removeRow(indiceSeleccionado);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una opcion para eliminar");
                    
        }
    }
    
    public void total(JTable tablaResumen, JLabel IVA, JLabel total){
    
       DefaultTableModel modelo = (DefaultTableModel) tablaResumen.getModel();
       double totalS =0;
       double iva = 0.16;
       
       double totalIva=0;
       
       DecimalFormat formato = new DecimalFormat();
       
        for (int i = 0; i < modelo.getRowCount(); i++) {
            totalS = Double.parseDouble(formato.format(totalS+(double)modelo.getValueAt(i, 4))) ;
            totalIva=Double.parseDouble(formato.format(iva*totalS));
        }
        
        total.setText(String.valueOf(totalS));
        IVA.setText(String.valueOf(totalIva));
    }
    
    public void hacerVenta(JTable tablaResumen, JLabel total) throws SQLException{
    
        Conexion.Conexion objetoConexion = new Conexion.Conexion();
        String transaccion = "START TRANSACTION;";
        
        String venta = "insert into venta (idEmpleado,fecha,total) values(1,now(),?);";
        String consulta = "insert into detallesVenta(precioUnitario,cantidad,codigo,idorden)values (?,?,?,(select MAX(idorden) from venta));";
        String consultaStock = "UPDATE producto set producto.unidadesEnAlmacen = unidadesEnAlmacen - ? where producto.codigo= ?;";
        
        String comm = "COMMIT;";
        String roll = "ROLLBACK;";
        
        try {
            PreparedStatement psDetalle = objetoConexion.conectar().prepareCall(consulta);
            PreparedStatement psStock = objetoConexion.conectar().prepareStatement(consultaStock);
            CallableStatement psVenta = objetoConexion.conectar().prepareCall(venta);
            PreparedStatement tr = objetoConexion.conectar().prepareStatement(transaccion);
            PreparedStatement com = objetoConexion.conectar().prepareStatement(comm);
            
            
            int filas = tablaResumen.getRowCount();
            
            tr.execute();
            psVenta.setDouble(1,Double.parseDouble(total.getText()));
            psVenta.execute();
            com.execute();
            
            for (int i = 0; i < filas; i++) {
                
                
                
                
                String codigo1 = tablaResumen.getValueAt(i, 0).toString();
                double precio = Double.parseDouble(tablaResumen.getValueAt(i, 2).toString());
                int cantidad = Integer.parseInt(tablaResumen.getValueAt(i, 3).toString());
                
                
                psDetalle.setDouble(1,precio);
                psDetalle.setInt(2,cantidad);
                psDetalle.setString(3,codigo1);
                psDetalle.executeUpdate();
                
                
                psStock.setInt(1,cantidad);
                psStock.setString(2, codigo1);
                
                tr.execute();
                psStock.execute();
                com.execute();
                
                
            }
            
            JOptionPane.showMessageDialog(null, "La compra se ha realizado con exito");
        } catch (Exception e) {
            PreparedStatement rll = objetoConexion.conectar().prepareStatement(roll);
            JOptionPane.showMessageDialog(null, "Eror al realizar la venta "+e.toString());
            rll.execute();
        } finally {
            
            objetoConexion.closeConexion();
        }
    }
    
    public void limpliar(JTextField buscador,JTable productos, JTextField codigo, JTextField nombre, JTextField precio , JTextField stock, JTextField precioVenta, JTextField cantidad,JTable tablaResumen, JLabel IVA, JLabel total){
   
        codigo.setText("");
        nombre.setText("");
        precio.setText("");
        stock.setText("");
        precioVenta.setText("");
        cantidad.setText("");
        buscador.setText("");
        buscador.requestFocus();
        IVA.setText("");
        total.setText("");
        
        DefaultTableModel modeloProductos = (DefaultTableModel) productos.getModel();
        modeloProductos.setRowCount(0);
        
        DefaultTableModel modeloResumen = (DefaultTableModel) tablaResumen.getModel();
        modeloResumen.setRowCount(0);
        
    }
    
    public void generarTicket(JTable jTable2,JLabel lblTOTAL, JLabel lbllVA, int idEmpleado) throws IOException{
        
        String nombreArchivo = "ticket_" + System.currentTimeMillis() + ".txt"; 
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))){
            String[]empleado=obtenerEmpleado(idEmpleado);
            String nombreEmpleado=empleado[0];
            String apellidoEmpleado=empleado[1];
            String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

      
        writer.write("===================================================================\n");
        writer.write("                         *** TICKET DE COMPRA ***\n");                         
        writer.write("===================================================================\n\n");
        writer.write("Fecha: " + fechaHoy + "\n");
        writer.write("Empleado: " + nombreEmpleado + " " + apellidoEmpleado + "\n\n");
        writer.write("Productos:\n");
            DefaultTableModel model=(DefaultTableModel) jTable2.getModel();
            for(int i=0; i < model.getRowCount();i++){
                String codigo=model.getValueAt(i, 0).toString();
                String nombre=model.getValueAt(i, 1).toString();
                String precio=model.getValueAt(i, 2).toString();
                String cantidad=model.getValueAt(i, 3).toString();
                String subtotal=model.getValueAt(i, 4).toString();
                
                writer.write("-"+nombre+"   "+"-Cantidad:"+cantidad+"   "+"-Precio: $"+precio+"   "+"- subtotal: $"+subtotal+"\n");
               
            }
            
            writer.write("\nIVA (16%):$"+lbllVA.getText()+"\n");
            writer.write("Total:$"+lblTOTAL.getText()+"\n");
           
            
            writer.write("===================================================================\n");
            writer.write("                   ¡Gracias por su preferencia!      \n");
            writer.write("===================================================================\n");

          
               
            System.out.println("El ticket ha sido generado en " + nombreArchivo);
              
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            
            Desktop.getDesktop().open(archivo);
        }
    } catch (IOException e) {
        System.err.println("Error al generar el ticket: " + e.getMessage());
    }
         
    }
    
    private String[] obtenerEmpleado(int idEmpleado){
        String[] empleado = new String[2];
        Conexion.Conexion objetoConexion =new Conexion.Conexion();
        try{
            String consulta= "SELECT nombre,apellido FROM Empleado WHERE idempleado= ?";
            PreparedStatement ps = objetoConexion.conectar().prepareStatement(consulta);
            ps.setInt(1, idEmpleado);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               empleado[0] = rs.getString("nombre");
               empleado[1] = rs.getString("apellido");  
            }
        
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener empleados"+ e.toString());
        } finally {
        objetoConexion.closeConexion();
    } return empleado;
        
    }
}
