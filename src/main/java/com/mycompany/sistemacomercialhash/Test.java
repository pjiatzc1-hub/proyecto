/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacomercialhash;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
      public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "system",
                "oracle"
            );

            System.out.println("Conexion exitosa");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
