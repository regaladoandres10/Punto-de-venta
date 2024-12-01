/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Front_end.Formularios;

import Models.PanelConFondo;
import Models.RegistroReporteMensual;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GORDILLO
 */
public class GenerarReporteView extends javax.swing.JPanel {
    
   
    
    public GenerarReporteView() {
        
        initComponents();
       
        //Este es el frame del fondo
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(525, 485);
        
     
        //Y este es el panel a donde se le agregan todos los componentes 
        PanelConFondo fondo = new PanelConFondo("src/imagenes/GenerarTiposReporte.png");
       
        
        //Declaramos los componentes y los agregamos
        
        ImageIcon icono = new ImageIcon("src/imagenes/PersonaDibujo.png");
        JButton usuario = new JButton(icono);
        
        usuario.addMouseListener(new MouseListener() {
            
            @Override public void mouseClicked(MouseEvent e) {
                try{                    
                    GenerarReporteEmpleadoView act = new GenerarReporteEmpleadoView();
                    act.setVisible(true);
                    frame.dispose();
                
                
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ingresa una fecha valida");
                }
                
            } 

            @Override
            public void mouseEntered(MouseEvent e) {
                 usuario.setIcon(new ImageIcon("src/imagenes/PersonaDibujo2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 usuario.setIcon(new ImageIcon("src/imagenes/PersonaDibujo.png"));
            }
            
            //No fueron implementados
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        usuario.setBounds(33,245,164,158);
        
        
        ImageIcon icono2 = new ImageIcon("src/imagenes/DibujoProductos.png");
        JButton productos = new JButton(icono2);
        
        productos.addMouseListener(new MouseListener() {
            
            @Override public void mouseClicked(MouseEvent e) {
                try{                    
                    ReporteTrimestralView act = new ReporteTrimestralView();
                    act.setVisible(true);
                    frame.dispose();
                
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ingresa una fecha valida");
                }
                
            } 

            @Override
            public void mouseEntered(MouseEvent e) {
                 productos.setIcon(new ImageIcon("src/imagenes/DibujoProductos2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 productos.setIcon(new ImageIcon("src/imagenes/DibujoProductos.png"));
            }
            
            //No fueron implementados
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        
        productos.setBounds(325,243,160,160);
        
        ImageIcon icono3 = new ImageIcon("src/imagenes/CalendarioDibujo.png");
        JButton calendario = new JButton(icono3);
        
        calendario.addMouseListener(new MouseListener() {
            
            @Override public void mouseClicked(MouseEvent e) {
                try{                    
                    ReporteMensualView act = new ReporteMensualView();
                    act.setVisible(true);
                    frame.dispose();
                
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ingresa una fecha valida");
                }
                
            } 

            @Override
            public void mouseEntered(MouseEvent e) {
                 calendario.setIcon(new ImageIcon("src/imagenes/CalendarioDibujo2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 calendario.setIcon(new ImageIcon("src/imagenes/CalendarioDibujo.png"));
            }
            
            //No fueron implementados
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        
        calendario.setBounds(192,90,123,124);
        
          
        ImageIcon imCerrar = new ImageIcon("src/imagenes/cerrar.png");
        JButton cerrar = new JButton(imCerrar);
        
        cerrar.addMouseListener(new MouseListener() { 
            @Override public void mouseClicked(MouseEvent e) {
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
        
        cerrar.setBounds(380,15,100,25);
        
        //Agregamos todos los componentes al panel
        
       
        fondo.add(usuario);
        fondo.add(calendario);
        fondo.add(productos);
        fondo.add(cerrar);
        frame.add(fondo);
        
        frame.setVisible(true);
    }
    
    
    
    /**
     * Genera la tabla con los datos mensuales
     * @param registros
     * @return el JTable de los datos mensuales con el proposito de agregarlo al scroll en
     * el constructor
     */
    public JTable cargarVentas(ArrayList <RegistroReporteMensual> registros){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Venta");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente");
        modelo.addColumn("Total");
        modelo.addColumn("Detalles");
        
        JTable tabla = new JTable(modelo);
        

        tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(55);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(135);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(135);
        
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


        for (RegistroReporteMensual reg : registros) {
            Object toAdd[] = new Object[]{reg.getIdOrden(),reg.getFecha(),reg.getNombreCliente(),reg.getEmpleado(),reg.getTotal(),reg.getDetalles()};
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

        setPreferredSize(new java.awt.Dimension(700, 332));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
