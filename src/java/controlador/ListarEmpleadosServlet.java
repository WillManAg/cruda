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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;

@WebServlet("/listarEmpleados")
public class ListarEmpleadosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            EmpleadoDAO dao = new EmpleadoDAO();

            ArrayList<Empleado> empleados
                    = dao.listarEmpleados();
            
            CookieOperaciones.incrementar(request, response);

            request.setAttribute("empleados", empleados);

            request.getRequestDispatcher("/listado.jsp")
                    .forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Error al consultar los empleados", e
            );
        }
    }
}
