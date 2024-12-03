/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Front_end.Formularios;

import Controladores.DaoReporte;
import Models.PanelConFondo;
import Models.RegistroReporteMensual;
import Models.Reporte;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ReporteMensualView extends javax.swing.JFrame {
    
    
    
    /**
     * Creates new form ReporteMensual
     */
    public ReporteMensualView() {
        
       
        //Este es el frame del fondo
        JFrame frame = new JFrame();
        
        frame.setSize(992, 447);
        
     
        //Y este es el panel a donde se le agregan todos los componentes 
        PanelConFondo fondo = new PanelConFondo("src\\imagenes\\ReporteMensualView.png");
       
        
        //Declaramos los componentes y los agregamos
        
        //ComboBox de meses
        String[] mes = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
        JComboBox<String> meses = new JComboBox<>(mes);
        meses.setBounds(285, 60, 120, 21);
        
        //TextField para el año
        JTextField anio = new JTextField();
        anio.setBounds(419, 58, 90, 25);
        
        //Boton para volver
        ImageIcon icono2 = new ImageIcon("src/imagenes/cerrar.png");
        JButton cerrar = new JButton(icono2);
        
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
                int mesSeleccionado = meses.getSelectedIndex();
                int anioSeleccionado = Integer.parseInt(anio.getText());
                if(anioSeleccionado <= 0){
                    JOptionPane.showMessageDialog(null, "Ingresa un año valido");
                }else{
                    
                    fondo.removeAll();
                    
                    mesSeleccionado+=1;
                    long milisegundosFechaInicial = calcularMilisegundos(anioSeleccionado,mesSeleccionado,1);
                    
                    java.sql.Date fechaDeReporte = new java.sql.Date (milisegundosFechaInicial);
                   
                    int ultimoDia = 30;
        
                    if((mesSeleccionado<=7 && mesSeleccionado%2!=0) || (mesSeleccionado>=8 && mesSeleccionado%2==0)){
                        ultimoDia = 31;
                    }else if(mesSeleccionado == 2 && esBisiesto(anioSeleccionado)){
                        ultimoDia = 29;
                    }else if(mesSeleccionado==2){
                        ultimoDia = 28;
                    }
                    long milisegundosFechaFinal = calcularMilisegundos(anioSeleccionado, mesSeleccionado, ultimoDia);
            
                    java.sql.Date fechaFinReporte = new java.sql.Date(milisegundosFechaFinal);
                    
                    Reporte act = new Reporte(DaoReporte.getRegistrosMensual(fechaDeReporte, fechaFinReporte));
        
                    ScrollPane scroll = new ScrollPane();
                    
                    JTable tablaVentas = tablaRegistros(act.getRegistrosMensual());
                    scroll.add(tablaVentas);
                    
                    scroll.setBounds(37,125,918,265);
                    
                    scroll.setVisible(true);
                    
                    fondo.add(scroll);
                    fondo.add(anio);
                    fondo.add(meses);
                    fondo.add(generar);
                    fondo.add(cerrar);
                    fondo.revalidate();
                    fondo.repaint();
                    frame.setVisible(true);
                }
               
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "NO HAY REGISTROS PARA ESTA FECHA");
                    ex.printStackTrace();
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
        
        generar.setBounds(530, 58, 90, 25);
        
        
        
        //Agregamos todos los componentes al panel
        fondo.add(anio);
        fondo.add(meses);
        fondo.add(generar);
        fondo.add(cerrar);
        frame.add(fondo);
        
        frame.setVisible(true);
    }
    
    
    public boolean esBisiesto(int anio){
        boolean es = false;
        if(anio%4==0){
            es = true;
        }
        if(anio%100==0 && anio%400!=0){
            es = false;
        }
        return es;
    }
    
    
    /**
     * Genera la tabla con los datos sobre las ventas
     * @param registros
     * @return el JTable de las ventas con el proposito de agregarlo al scroll en
     * el constructor
     */
    public JTable tablaRegistros(ArrayList <RegistroReporteMensual> registros){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("IdOrden");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente");
        modelo.addColumn("Empleado");
        modelo.addColumn("Total");
        modelo.addColumn("Detalles");
        
        JTable tabla = new JTable(modelo);
        

        tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(67);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(118);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(105);
        
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
        
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        
        for (RegistroReporteMensual reg : registros) {
            Object toAdd[] = new Object[]{reg.getIdOrden(),formato.format(reg.getFecha()),reg.getNombreCliente(),reg.getEmpleado(),"$"+reg.getTotal(),reg.getDetalles()};
            modelo.addRow(toAdd);
            tabla.revalidate();
            tabla.repaint();
        }
        return tabla;
    }
    
    
    public long calcularMilisegundos(int anio, int mes, int dia){
        long resultado = 0;
        resultado+=(anio-1970)*365;
        boolean esBis = esBisiesto(anio);
        
        for(int i=1972;i<anio;i+=4){
            resultado++;
        }
        for(int i=1;i<=mes-1;i++){
           if(i==2){
               if(esBis){
                   resultado+=29;
               }else{
                   resultado+=28;
               }
           }else{
               if(i<=7){
                   if(i%2==0){
                       resultado+=30;
                   }else{
                       resultado+=31;
                   }
               }else{
                   if(i%2!=0){
                       resultado+=30;
                   }else{
                       resultado+=31;
                   }
               }
               
               
           }
        }
        
        resultado+=dia;
        
        resultado = resultado * 24 * 60 * 60 * 1000;
        if(dia==1){
            
        }
        return resultado;
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
