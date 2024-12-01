/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Front_end.Formularios;

import Models.PanelConFondo;
import Models.ReporteVentasEmpleado;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dagua
 */
public class ReporteEmpleadosView extends javax.swing.JFrame {
    ArrayList<ReporteVentasEmpleado> reporteActual ;
     /**
     * Creates new form ReporteEmpleadoView
     * @param RepActual
     * @param Mes
     * @param anio
     */
    
    
    public ReporteEmpleadosView( ArrayList<ReporteVentasEmpleado> RepActual,String Mes,String anio ) {
        
        reporteActual=RepActual;
         // Crear el JFrame
        JFrame frame = new JFrame("Reporte de Ventas por Empleado");
        
        frame.setSize(922, 447);
       

        // Panel principal
        PanelConFondo panel= new PanelConFondo("src/imagenes/ReporteEmpleadoView.png");
     
      

        // Etiqueta de fecha
        JLabel fecha = new JLabel(Mes+" "+anio);
        
        fecha.setFont(new Font("Arial", Font.BOLD, 24));
        fecha.setBounds(400, 60, 209, 22);
        fecha.setForeground(Color.WHITE);
        panel.add(fecha);
        
        
        ImageIcon icono = new ImageIcon("src/imagenes/cerrar.png");
        JButton cerrar = new JButton(icono);
        // agregar listeners
        cerrar.addMouseListener(new MouseListener() { 
            @Override public void mouseClicked(MouseEvent e) {
                GenerarReporteEmpleadoView nuevo = new GenerarReporteEmpleadoView();
                nuevo.setVisible(true);
                frame.dispose();
            } 

            @Override
            public void mouseEntered(MouseEvent e) {
                 cerrar.setIcon(new ImageIcon("src/imagenes/cerrar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 cerrar.setIcon(new ImageIcon("src/imagenes/cerrar.png"));
            }
            
            //No fueron implementados
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        cerrar.setBounds(788,24,100,25);
        panel.add(cerrar);
        

        // ScrollPane para contener la tabla
        ScrollPane arrastrar = new ScrollPane();
        arrastrar.setBounds(192, 139, 540, 241);

        // Cargar la tabla con los datos
        JTable ReporteEmpleados= tablaEmpleados ( reporteActual );
        arrastrar.add(ReporteEmpleados);

        // Agregar los componentes al panel
        panel.add(arrastrar);

        // Configurar el frame
        frame.add(panel);
        frame.setVisible(true);
    }
    public JTable tablaEmpleados(ArrayList<ReporteVentasEmpleado> DatosTabla){
    DefaultTableModel Columnas=new DefaultTableModel();
    Columnas.addColumn("Nombre");
    Columnas.addColumn("Total");
    Columnas.addColumn("Cantidad de Ventas");
    // crear tabla
    JTable Tabla = new JTable (Columnas);
    Tabla.getColumnModel().getColumn(0).setPreferredWidth(98);
    Tabla.getColumnModel().getColumn(1).setPreferredWidth(67);
    Tabla.getColumnModel().getColumn(2).setPreferredWidth(33);
    
       // Dar formato a cada casilla de la tabla  
        Tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTextArea casilla = new JTextArea(value != null ? value.toString() : "");
                casilla.setBackground(new Color(188,254,254));
                Border border = BorderFactory.createLineBorder(new Color(0,28,68), 3);
                casilla.setBorder(border);
                casilla.setFont(new Font("Arial", Font.BOLD, 22));
               
                casilla.setLineWrap(true);
                casilla.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getWidth(), casilla.getPreferredSize().height));

                table.setRowHeight(row, Math.max(casilla.getPreferredSize().height, 50));
                return casilla;
            }
        });
        // agregar datos a la tabla 
        for (ReporteVentasEmpleado registro: DatosTabla){
        
        Object paquetito[]=new Object []{
        registro.getEmpleado(),registro.getTotal(),registro.getCantidadVentas()
        
        };
        Columnas.addRow(paquetito);
        //Actualiza datos en la tabla
        Tabla.revalidate();
        Tabla.repaint();
        }
    return Tabla;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
