/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Front_end.Formularios;

import Controladores.DaoReporte;
import Models.PanelConFondo;
import Models.RegistroReporteTrimestral;
import Models.Reporte;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GORDILLO
 */
public class ReporteTrimestralView extends javax.swing.JFrame {

    
    /**
     * Creates new form Reporte
     */
    public ReporteTrimestralView() {
        
       
       
        //Este es el frame del fondo
        JFrame frame = new JFrame(); 
        frame.setSize(992, 447);
        
     
        //Y este es el panel a donde se le agregan todos los componentes 
        PanelConFondo fondo = new PanelConFondo("src\\imagenes\\ReporteTrimestralView.png");
       
        
        //Declaramos los componentes y los agregamos
        
        
        //TextField para el año
        JTextField anio = new JTextField();
        anio.setBounds(212, 57, 125, 22);
        
        
        ImageIcon icono2 = new ImageIcon("src/imagenes/cerrar.png");
        JButton cerrar = new JButton(icono2);
        
        //Boton para volver
        cerrar.addMouseListener(new MouseListener() { 
            @Override public void mouseClicked(MouseEvent e) {
                GenerarReporteView act = new GenerarReporteView();
                act.setVisible(true);
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
        
        cerrar.setBounds(820,20,100,25);
        
        
        
        //Boton para generar el reporte
        ImageIcon icono = new ImageIcon("src/imagenes/generar.png");
        JButton generar = new JButton(icono);
      
        generar.addMouseListener(new MouseListener() {
            
            @Override public void mouseClicked(MouseEvent e) {
                try{                    
                    
                String anioSeleccionado = anio.getText();
                
                if(Integer.parseInt(anioSeleccionado) < 0){
                    JOptionPane.showMessageDialog(null, "Ingresa un año valido");
                }else{
                    
                    fondo.removeAll();
                    
                    Reporte act = new Reporte(DaoReporte.getRegistrosTrimestral(anioSeleccionado),0);
        
                    ScrollPane scroll = new ScrollPane();
                    
                    scroll.setBounds(71, 133, 856, 255);
                    
                    JTable tablaVentas = tablaRegistros(act.getRegistrosTrimestral());
                    
                    
                    
                    scroll.add(tablaVentas);
                                     
                    fondo.add(scroll);
                    fondo.add(anio);
                    fondo.add(generar);
                    fondo.add(cerrar);
                    
                    fondo.revalidate();
                    
                    fondo.repaint();
                    frame.setVisible(true);
             
                    
                    
                }
               
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ingresa una fecha valida");
                }
                
            } 

            @Override
            public void mouseEntered(MouseEvent e) {
                 generar.setIcon(new ImageIcon("src/imagenes/generar2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 generar.setIcon(new ImageIcon("src/imagenes/generar.png"));
            }
            
            //No fueron implementados
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        generar.setBounds(350, 57, 100, 22);
        
        
        
        //Agregamos todos los componentes al panel
        
        fondo.add(anio);
        fondo.add(generar);
        fondo.add(cerrar);
        
        frame.add(fondo);
        
        frame.setVisible(true);
    }
    
    
    public void mostrarReporte(int mes, int anio){
        
    }
    
    
    /**
     * Genera la tabla con los datos sobre las ventas
     * @param registros
     * @return el JTable de las ventas con el proposito de agregarlo al scroll en
     * el constructor
     */
    public JTable tablaRegistros(ArrayList <RegistroReporteTrimestral> registros){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Producto");
        modelo.addColumn("Trimestre1");
        modelo.addColumn("Trimestre2");
        modelo.addColumn("Trimestre3");
        modelo.addColumn("Trimestre4");
        
        JTable tabla = new JTable(modelo);
        

        tabla.getColumnModel().getColumn(0).setPreferredWidth(137);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(85);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(88);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTextArea textArea = new JTextArea(value != null ? value.toString() : "");
                textArea.setBackground(new Color(188,254,254));
                Border border = BorderFactory.createLineBorder(new Color(0,28,68), 3);
                textArea.setBorder(border);
                textArea.setFont(new Font("Arial", Font.BOLD, 22));
               
                textArea.setLineWrap(true);
                textArea.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getWidth(), textArea.getPreferredSize().height));

                table.setRowHeight(row, Math.max(textArea.getPreferredSize().height, 50));
                return textArea;
            }
        });
        

        for (RegistroReporteTrimestral reg : registros) {
            Object toAdd[] = new Object[]{reg.getNombreProd(),reg.getTrimestre1(),reg.getTrimestre2(),reg.getTrimestre3(),reg.getTrimestre4()};
            modelo.addRow(toAdd);
            tabla.revalidate();
            tabla.repaint();
        }
        return tabla;
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
