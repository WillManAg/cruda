/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author william.marin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL
            = "jdbc:mariadb://localhost:3306/practica";

    private static final String USUARIO = "root";
    private static final String CLAVE = "uwu";

    public static Connection conectar() throws SQLException {

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "No se encuentra el driver de MariaDB", e
            );
        }

        return DriverManager.getConnection(
                URL, USUARIO, CLAVE
        );

    }

}
