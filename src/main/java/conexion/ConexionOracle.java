package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:umg";

    private static final String USER = "system";

    private static final String PASSWORD = "Umg$2026";

    public static Connection conectar() {

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Conexion exitosa");

            return con;

        } catch (Exception e) {

            System.out.println("Error de conexion");

            e.printStackTrace();

            return null;
        }
    }
}