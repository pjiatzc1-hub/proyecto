package menus;

import conexion.ConexionOracle;
import hash.TablaHashProductos;
import modelos.producto;

import java.sql.*;
import java.util.Scanner;

public class MenuPrincipal {

    public static void iniciar() {

        Scanner sc = new Scanner(System.in);
        TablaHashProductos tabla = new TablaHashProductos();

        try {

            Connection con = ConexionOracle.conectar();
            Statement stmt = con.createStatement();

            // ==========================
            // CARGAR PRODUCTOS A HASH
            // ==========================
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTO");

            while (rs.next()) {

                producto p = new producto(
                        rs.getInt("ID_PRODUCTO"),
                        rs.getString("NOMBRE"),
                        rs.getDouble("PRECIO")
                );

                tabla.insertar(p);
            }

            int opcion;

            // ==========================
            // MENU PRINCIPAL
            // ==========================
            do {

                System.out.println("\n===== SISTEMA COMERCIAL =====");
                System.out.println("1. Mostrar productos");
                System.out.println("2. Buscar producto");
                System.out.println("3. Insertar producto");
                System.out.println("4. Crear factura");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");

                opcion = sc.nextInt();

                switch (opcion) {

                    // ==========================
                    // MOSTRAR PRODUCTOS
                    // ==========================
                    case 1:
                        tabla.mostrar();
                        break;

                    // ==========================
                    // BUSCAR PRODUCTO
                    // ==========================
                    case 2:
                        System.out.print("Ingrese ID del producto: ");
                        int idBuscar = sc.nextInt();

                        producto encontrado = tabla.buscar(idBuscar);

                        if (encontrado != null) {
                            System.out.println(encontrado);
                        } else {
                            System.out.println("Producto no encontrado");
                        }
                        break;

                    // ==========================
                    // INSERTAR PRODUCTO
                    // ==========================
                    case 3:

                        System.out.print("ID: ");
                        int idNuevo = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombreNuevo = sc.nextLine();

                        System.out.print("Precio: ");
                        double precioNuevo = sc.nextDouble();

                        // SQL más seguro
                        String sqlInsert = "INSERT INTO PRODUCTO (ID_PRODUCTO, NOMBRE, PRECIO, ESTADO) VALUES (?, ?, ?, 1)";
                        PreparedStatement psInsert = con.prepareStatement(sqlInsert);

                        psInsert.setInt(1, idNuevo);
                        psInsert.setString(2, nombreNuevo);
                        psInsert.setDouble(3, precioNuevo);

                        psInsert.executeUpdate();

                        producto nuevo = new producto(idNuevo, nombreNuevo, precioNuevo);
                        tabla.insertar(nuevo);

                        System.out.println("Producto agregado correctamente");

                        break;

                    // ==========================
                    // CREAR FACTURA
                    // ==========================
                    case 4:

                        System.out.print("ID Factura: ");
                        int idFactura = sc.nextInt();

                        System.out.print("ID Cliente: ");
                        int idCliente = sc.nextInt();

                        String sqlFactura =
                                "INSERT INTO FACTURA (ID_FACTURA, FECHA, ID_CLIENTE) VALUES (?, SYSDATE, ?)";

                        PreparedStatement psFactura = con.prepareStatement(sqlFactura);
                        psFactura.setInt(1, idFactura);
                        psFactura.setInt(2, idCliente);

                        psFactura.executeUpdate();

                        System.out.println("Factura creada correctamente");

                        // ==========================
                        // DETALLE FACTURA
                        // ==========================
                        int continuar;

                        do {

                            System.out.print("ID Producto: ");
                            int idProducto = sc.nextInt();

                            System.out.print("Cantidad: ");
                            int cantidad = sc.nextInt();

                            System.out.print("Precio: ");
                            double precio = sc.nextDouble();

                            String sqlDetalle =
                                    "INSERT INTO DETALLE (ID_DETALLE, ID_FACTURA, ID_PRODUCTO, CANTIDAD, PRECIO) " +
                                            "VALUES (DET_SEQ.NEXTVAL, ?, ?, ?, ?)";

                            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
                            psDetalle.setInt(1, idFactura);
                            psDetalle.setInt(2, idProducto);
                            psDetalle.setInt(3, cantidad);
                            psDetalle.setDouble(4, precio);

                            psDetalle.executeUpdate();

                            System.out.println("Producto agregado al detalle");

                            System.out.print("Agregar otro producto? (1=SI / 0=NO): ");
                            continuar = sc.nextInt();

                        } while (continuar == 1);

                        break;

                    // ==========================
                    // SALIR
                    // ==========================
                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion invalida");
                }

            } while (opcion != 5);

            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}