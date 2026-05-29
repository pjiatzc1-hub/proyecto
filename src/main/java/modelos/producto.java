package modelos;

public class producto {

    private int idproducto;
    private String nombre;
    private double precio;

    public producto(int idproducto, String nombre, double precio) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdProducto() {
        return idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return idproducto + " - " + nombre + " - Q" + precio;
    }
}


