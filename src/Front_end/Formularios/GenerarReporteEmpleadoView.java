/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Front_end.Formularios;

import Controladores.DaoReporteVentasPorEmpleado;
import Models.PanelConFondo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author dagua
 */
public class GenerarReporteEmpleadoView extends javax.swing.JPanel {

    public GenerarReporteEmpleadoView() {
        
       
        //Este es el frame del fondo
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 332);
        
        
        //Y este es el panel a donde se le agregan todos los componentes 
        PanelConFondo panel = new PanelConFondo("src\\imagenes\\GenerarReporteEmpleado.png");
       
        
        // generar Combo box meses
        
         String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", 
            "Mayo", "Junio", "Julio", "Agosto", 
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        
        // Crear el JComboBox con los meses
        JComboBox<String> comboMeses = new JComboBox<>(meses);
        comboMeses.setBounds(238, 126, 195, 41); // Posición y tamaño
        panel.add(comboMeses);
       
        // Crear un JTextField
        JTextField Anio = new JTextField();
        Anio.setBounds(236, 190, 162, 41); // Posición y tamaño
        panel.add(Anio);
        
        
        ImageIcon icono = new ImageIcon("src/imagenes/generar.png");
        JButton generar = new JButton(icono);
        
        generar.addMouseListener(new MouseListener() {
            //pasar datos a la ventana reporteVentasEmpleado
            @Override public void mouseClicked(MouseEvent e) {
                try{ 
                   int  mes =comboMeses.getSelectedIndex()+1;
                   int anio=Integer.parseInt(Anio.getText());
                   String VarMes=(String)comboMeses.getSelectedItem();
                   String VarAnio=Anio.getText();
                   ReporteEmpleadosView actual=new ReporteEmpleadosView(DaoReporteVentasPorEmpleado.getReporteVentasPorEmpleado(mes, anio),VarMes,VarAnio);
                   // abrir ventana
                   actual.setVisible(true);
                   //cierra ventana actual
                   frame.dispose();
                
                    
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
        
        generar.setBounds(210,245,100,25);
        
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
        
        cerrar.setBounds(340,245,100,25);
        
        //Agregamos todos los componentes al panel
        
       
       
        panel.add(generar);
        panel.add(cerrar);
        frame.add(panel);
        
        frame.setVisible(true);
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
