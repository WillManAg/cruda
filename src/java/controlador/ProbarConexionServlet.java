/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author william.marin
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConexionBD;

@WebServlet("/probarConexion")
public class ProbarConexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        try (Connection conn = ConexionBD.conectar()) {

            response.getWriter().println(
                    "Conexion con MariaDB establecida correctamente."
            );

        } catch (SQLException e) {

            getServletContext().log("Error de conexion JDBC", e);

            response.setStatus(500);
            response.getWriter().println(
                    "Error al conectar. Revisa la consola de Tomcat."
            );
        }
    }

}
