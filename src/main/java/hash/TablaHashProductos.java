package hash;

import java.util.HashMap;
import modelos.producto;

public class TablaHashProductos {

    private HashMap<Integer, producto> tablaHash;

    public TablaHashProductos() {

        tablaHash = new HashMap<>();
    }

    // INSERTAR PRODUCTO
    public void insertar(producto p) {

        tablaHash.put(p.getIdProducto(), p);
    }

    // BUSCAR PRODUCTO
    public producto buscar(int id) {

        return tablaHash.get(id);
    }

    // MOSTRAR TODO
    public void mostrar() {

        for (producto p : tablaHash.values()) {

            System.out.println(p);
        }
    }
}