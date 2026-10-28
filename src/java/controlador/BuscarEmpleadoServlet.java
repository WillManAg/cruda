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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;

@WebServlet("/buscarEmpleado")
public class BuscarEmpleadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (!"modificar".equals(accion)
                && !"eliminar".equals(accion)) {

            mostrarError(request, response,
                    "Operacion no valida.");
            return;
        }

        try {

            int empno = Integer.parseInt(
                    request.getParameter("empno")
            );

            EmpleadoDAO dao = new EmpleadoDAO();

            Empleado empleado = dao.buscarPorEmpno(empno);

            if (empleado == null) {

                mostrarError(request, response,
                        "No existe ningun empleado con ese numero.");
                return;
            }

            request.setAttribute("empleado", empleado);

            String destino;

            if ("eliminar".equals(accion)) {
                destino = "/confirmarEliminar.jsp";
            } else {
                destino = "/formulario.jsp";
            }

            request.getRequestDispatcher(destino)
                    .forward(request, response);

        } catch (NumberFormatException e) {

            mostrarError(request, response,
                    "Introduce un numero de empleado valido.");

        } catch (SQLException e) {

            getServletContext().log(
                    "Error al buscar empleado", e
            );

            mostrarError(request, response,
                    "Error al consultar la base de datos.");
        }
    }

    private void mostrarError(HttpServletRequest request,
            HttpServletResponse response,
            String mensaje)
            throws ServletException, IOException {

        request.setAttribute("error", mensaje);

        request.getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }
}
