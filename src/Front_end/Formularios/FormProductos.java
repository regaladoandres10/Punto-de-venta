/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Front_end.Formularios;
import Controladores.CRUDProductos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Andres
 */
public class FormProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form FormProductos
     */
    CRUDProductos productos = new CRUDProductos();
    public FormProductos() {
        initComponents();
        CRUDProductos objProductos = new CRUDProductos();
        objProductos.mostrarProductos(tbProductos);
        
        tbProductos.setRowHeight(40);
        
        JTableHeader header = tbProductos.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbProductos, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setBackground(new Color(37, 39, 64)); // Color de fondo
                label.setForeground(Color.WHITE); // Color del texto
                label.setFont(new Font("Roboto", Font.BOLD, 14)); // Estilo de texto
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alineación
                label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                return label;
            }
        });
        
        txtNivelReorden.addKeyListener(new java.awt.event.KeyAdapter(){
        @Override
        public void keyPressed(java.awt.event.KeyEvent evt){
            if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                try {
                    productos.AgregarProductos(txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
                    productos.mostrarProductos(tbProductos);
                    productos.limpiarCampos(txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
        } catch (SQLException ex) {
            Logger.getLogger(FormProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        back = new javax.swing.JPanel();
        PanelProductos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtUnidadAlmacen = new javax.swing.JTextField();
        txtUnidadesOrden = new javax.swing.JTextField();
        txtNivelReorden = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnGuardarProductos = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnModificar1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnEliminar1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnLimpiar1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Productos");

        back.setBackground(new java.awt.Color(37, 39, 64));
        back.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelProductos.setBackground(new java.awt.Color(34, 32, 52));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Codigo");

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cantidad por unidad:");

        jLabel4.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio unitario:");

        jLabel5.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Unidades en almacen:");

        jLabel6.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Unidades en orden:");

        jLabel7.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nivel de reorden:");

        txtNombre.setBackground(new java.awt.Color(34, 32, 52));
        txtNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(229, 229, 229));

        txtCodigo.setBackground(new java.awt.Color(34, 32, 52));
        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(229, 229, 229));

        txtCantidad.setBackground(new java.awt.Color(34, 32, 52));
        txtCantidad.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(229, 229, 229));

        txtPrecio.setBackground(new java.awt.Color(34, 32, 52));
        txtPrecio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(229, 229, 229));

        txtUnidadAlmacen.setBackground(new java.awt.Color(34, 32, 52));
        txtUnidadAlmacen.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtUnidadAlmacen.setForeground(new java.awt.Color(229, 229, 229));
        txtUnidadAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadAlmacenActionPerformed(evt);
            }
        });

        txtUnidadesOrden.setBackground(new java.awt.Color(34, 32, 52));
        txtUnidadesOrden.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtUnidadesOrden.setForeground(new java.awt.Color(229, 229, 229));

        txtNivelReorden.setBackground(new java.awt.Color(34, 32, 52));
        txtNivelReorden.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNivelReorden.setForeground(new java.awt.Color(229, 229, 229));

        jLabel9.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("IdCategoria:");

        txtIdCategoria.setBackground(new java.awt.Color(34, 32, 52));
        txtIdCategoria.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtIdCategoria.setForeground(new java.awt.Color(229, 229, 229));

        jLabel10.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Id Producto:");

        txtIdProducto.setEditable(false);
        txtIdProducto.setBackground(new java.awt.Color(34, 32, 52));
        txtIdProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtIdProducto.setForeground(new java.awt.Color(229, 229, 229));
        txtIdProducto.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(124, 113, 221));
        jLabel8.setText("Registrar nuevo producto");

        javax.swing.GroupLayout PanelProductosLayout = new javax.swing.GroupLayout(PanelProductos);
        PanelProductos.setLayout(PanelProductosLayout);
        PanelProductosLayout.setHorizontalGroup(
            PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelProductosLayout.createSequentialGroup()
                        .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtNivelReorden))
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo)
                                    .addComponent(txtIdProducto)))
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad))
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUnidadAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdCategoria)
                                    .addComponent(txtNombre)))
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrecio))
                            .addGroup(PanelProductosLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtUnidadesOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        PanelProductosLayout.setVerticalGroup(
            PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductosLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUnidadAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtUnidadesOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNivelReorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        back.add(PanelProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 670, 300));

        tbProductos.setBackground(new java.awt.Color(80, 77, 130));
        tbProductos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tbProductos.setForeground(new java.awt.Color(255, 255, 255));
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);

        back.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 940, 240));

        jPanel1.setBackground(new java.awt.Color(34, 32, 52));

        btnGuardarProductos.setBackground(new java.awt.Color(33, 169, 66));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        jLabel14.setText("GUARDAR");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnGuardarProductosLayout = new javax.swing.GroupLayout(btnGuardarProductos);
        btnGuardarProductos.setLayout(btnGuardarProductosLayout);
        btnGuardarProductosLayout.setHorizontalGroup(
            btnGuardarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        btnGuardarProductosLayout.setVerticalGroup(
            btnGuardarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        btnModificar1.setBackground(new java.awt.Color(35, 108, 176));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        jLabel13.setText("EDITAR");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnModificar1Layout = new javax.swing.GroupLayout(btnModificar1);
        btnModificar1.setLayout(btnModificar1Layout);
        btnModificar1Layout.setHorizontalGroup(
            btnModificar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btnModificar1Layout.setVerticalGroup(
            btnModificar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        btnEliminar1.setBackground(new java.awt.Color(224, 51, 68));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        jLabel15.setText("ELIMINAR");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnEliminar1Layout = new javax.swing.GroupLayout(btnEliminar1);
        btnEliminar1.setLayout(btnEliminar1Layout);
        btnEliminar1Layout.setHorizontalGroup(
            btnEliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        btnEliminar1Layout.setVerticalGroup(
            btnEliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        btnLimpiar1.setBackground(new java.awt.Color(28, 160, 184));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/escoba.png"))); // NOI18N
        jLabel16.setText("LIMPIAR");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnLimpiar1Layout = new javax.swing.GroupLayout(btnLimpiar1);
        btnLimpiar1.setLayout(btnLimpiar1Layout);
        btnLimpiar1Layout.setHorizontalGroup(
            btnLimpiar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btnLimpiar1Layout.setVerticalGroup(
            btnLimpiar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnModificar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnGuardarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        back.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 270, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        CRUDProductos productos = new CRUDProductos();
        productos.Seleccionar(tbProductos, txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
    }//GEN-LAST:event_tbProductosMouseClicked

    private void txtUnidadAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadAlmacenActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        CRUDProductos productos = new CRUDProductos();
        try {
            productos.AgregarProductos(txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
            productos.mostrarProductos(tbProductos);
            productos.limpiarCampos(txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
        } catch (SQLException ex) {
            Logger.getLogger(FormProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        btnGuardarProductos.setBackground(new Color(45, 117, 80));
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        btnGuardarProductos.setBackground(new Color(33, 169, 66));
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        CRUDProductos productos = new CRUDProductos();
        try {
            productos.modificarProductos(txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
            productos.mostrarProductos(tbProductos);
            productos.limpiarCampos(txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
        } catch (SQLException ex) {
            Logger.getLogger(FormProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        productos.mostrarProductos(tbProductos);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        btnModificar1.setBackground(new Color(23, 72, 117));
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        btnModificar1.setBackground(new Color(35, 108, 176));
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        CRUDProductos productos = new CRUDProductos();
        try {
            
            int opcion = JOptionPane.showConfirmDialog
            (
            null, 
            "¿Estás seguro de que deseas eliminar este producto?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
            );
            
            // Verifica la respuesta del usuario
            if (opcion == JOptionPane.YES_OPTION) 
            {
                productos.borrarProductos(txtIdProducto);
                productos.mostrarProductos(tbProductos);
                productos.limpiarCampos(txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
                
            } else 
            {
                JOptionPane.showMessageDialog(null, "Operacion cancelada");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FormProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        btnEliminar1.setBackground(new Color(149, 34, 45));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        btnEliminar1.setBackground(new Color(224, 51, 68));
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        CRUDProductos productos = new CRUDProductos();
        productos.limpiarCampos(txtIdProducto, txtNombre, txtCodigo, txtIdCategoria, txtCantidad, txtPrecio, txtUnidadAlmacen, txtUnidadesOrden, txtNivelReorden);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        btnLimpiar1.setBackground(new Color(18, 106, 122));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        btnLimpiar1.setBackground(new Color(28, 160, 184));
    }//GEN-LAST:event_jLabel16MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelProductos;
    private javax.swing.JPanel back;
    private javax.swing.JPanel btnEliminar1;
    private javax.swing.JPanel btnGuardarProductos;
    private javax.swing.JPanel btnLimpiar1;
    private javax.swing.JPanel btnModificar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNivelReorden;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtUnidadAlmacen;
    private javax.swing.JTextField txtUnidadesOrden;
    // End of variables declaration//GEN-END:variables
}
