
package Models;

/**
 *
 * @author Andres
 */
public class Productos 
{
    int idProducto;
    String nombre;
    String codigo;
    int idCategoria;
    int cantidadPorUnidad;
    double precioUnitario;
    int unidadesEnAlmacen;
    int unidadesEnOrden;
    int nivelDeReorden;
    boolean descontinuado;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidadPorUnidad() {
        return cantidadPorUnidad;
    }

    public void setCantidadPorUnidad(int cantidadPorUnidad) {
        this.cantidadPorUnidad = cantidadPorUnidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getUnidadesEnAlmacen() {
        return unidadesEnAlmacen;
    }

    public void setUnidadesEnAlmacen(int unidadesEnAlmacen) {
        this.unidadesEnAlmacen = unidadesEnAlmacen;
    }

    public int getUnidadesEnOrden() {
        return unidadesEnOrden;
    }

    public void setUnidadesEnOrden(int unidadesEnOrden) {
        this.unidadesEnOrden = unidadesEnOrden;
    }

    public int getNivelDeReorden() {
        return nivelDeReorden;
    }

    public void setNivelDeReorden(int nivelDeReorden) {
        this.nivelDeReorden = nivelDeReorden;
    }

    public boolean isDescontinuado() {
        return descontinuado;
    }

    public void setDescontinuado(boolean descontinuado) {
        this.descontinuado = descontinuado;
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
