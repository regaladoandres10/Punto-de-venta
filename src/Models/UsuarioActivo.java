
package Models;

/**
 *
 * @author Andres
 */
public class UsuarioActivo 
{
    private static int id;
    private static String user;
    private static int idEmpleado;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UsuarioActivo.id = id;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        UsuarioActivo.user = user;
    }

    public static int getIdEmpleado() {
        return idEmpleado;
    }

    public static void setIdEmpleado(int idEmpleado) {
        UsuarioActivo.idEmpleado = idEmpleado;
    }
    
    
}
