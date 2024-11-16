
package com.mycompany.sistema_punto_venta;

import Conexion.Conexion;
import Front_end.Formularios.FormProductos;
import Front_end.Formularios.Main;
import Login.Login;

/**
 *
 * @author Andres
 */
public class PuntoVenta 
{
    /*
    // Probar conexión
    public static void main(String[] args) 
    {
        Conexion c = new Conexion();
        c.conectar();
    }
    */
    
    public static void main(String[] args) 
    {
        /*
        Main objMenuPrincipal = new Main();
        objMenuPrincipal.setVisible(true);
        */
        
        Login objLogin = new Login();
        objLogin.setVisible(true);
    }
    
}
