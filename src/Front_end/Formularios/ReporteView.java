/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Front_end.Formularios;

import Models.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.ScrollPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author GORDILLO
 */
public class ReporteView extends javax.swing.JPanel {
    
    Reporte actual;
    
    /**
     * Creates new form Reporte
     * @param act
     */
    public ReporteView(Reporte act) {
        this.actual = act;
        
        initComponents();
       
        //Este es el frame del fondo
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 550);
        int frameW = frame.getWidth();
        int frameH = frame.getHeight();
     
        //Y este es el panel a donde se le agregan todos los componentes 
        PanelConFondo fondo = new PanelConFondo("src\\imagenes\\ReporteView.png");
        
        //Declaramos los componentes y los agregamos
        JLabel fechaCreacion = new JLabel(new SimpleDateFormat().format(this.actual.getFecha())); 
        
        fechaCreacion.setBounds(frameW-(int)((double)frameW*0.23), frameH-(int)((double)frameH*0.923), (int)((double)frameW*0.2), (int)((double)frameH*0.065));
        fechaCreacion.setFont(new Font("Arial", Font.BOLD, 22));
        fechaCreacion.setForeground(new Color(1,1,1));
        
        
        
        JLabel monto = new JLabel(this.actual.getImporteTotal()+"");
        
        monto.setBounds(frameW-(int)((double)frameW*0.22), frameH-(int)((double)frameH*0.671), (int)((double)frameW*0.2), (int)((double)frameH*0.065));
        monto.setFont(new Font("Arial", Font.BOLD, 22));
        
        
        JLabel iva = new JLabel(this.actual.getIVA()+"");
        
        iva.setBounds(frameW-(int)((double)frameW*0.22), frameH-(int)((double)frameH*0.525), (int)((double)frameW*0.2), (int)((double)frameH*0.065));
        iva.setFont(new Font("Arial", Font.BOLD, 22));
        
        
        JLabel montoMasIva = new JLabel(""+(Math.round((this.actual.getImporteTotal()+this.actual.getIVA()*1000.0))/1000.0));
        
        montoMasIva.setBounds(frameW-(int)((double)frameW*0.22), frameH-(int)((double)frameH*0.375), (int)((double)frameW*0.2), (int)((double)frameH*0.055));
        montoMasIva.setFont(new Font("Arial", Font.BOLD, 22));
        
        
        ScrollPane scroll = new ScrollPane();
        scroll.setBounds(frameW-(int)((double)frameW*0.943), frameH-(int)((double)frameH*0.695), (int)((double)frameW*0.586), (int)((double)frameH*0.524));
        
        JTable tablaVentas = cargarVentas(act.getVentas());
        scroll.add(tablaVentas);
        
        ImageIcon icono = new ImageIcon("src/imagenes/cerrar.png");
        JButton cerrar = new JButton(icono);
        
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
        
        cerrar.setBounds(760,420,100,25);
        
        
        
        //Agregamos todos los componentes al panel
        fondo.add(fechaCreacion);
        fondo.add(monto);
        fondo.add(iva);
        fondo.add(montoMasIva);
        fondo.add(scroll);
        fondo.add(cerrar);
        frame.add(fondo);
        frame.setVisible(true);
    }
    
    
    /**
     * Genera la tabla con los datos sobre las ventas
     * @param ventas
     * @return el JTable de las ventas con el proposito de agregarlo al scroll en
     * el constructor
     */
    public JTable cargarVentas(ArrayList <Venta> ventas){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Venta");
        modelo.addColumn("Empleado");
        modelo.addColumn("Fecha");
        modelo.addColumn("Total");
        
        JTable tabla = new JTable(modelo);
        

        tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(55);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(135);
        
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


        for (Venta v : ventas) {
            Object toAdd[] = new Object[]{v.getIdventa(),v.getIdempleado(),new SimpleDateFormat().format(v.getFechaVenta()), v.getImporte()};
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
