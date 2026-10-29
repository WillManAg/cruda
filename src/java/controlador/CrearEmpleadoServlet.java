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

@WebServlet("/crearEmpleado")
public class CrearEmpleadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            Empleado empleado = EmpleadoFormulario.leer(request);

            EmpleadoDAO dao = new EmpleadoDAO();

            int filas = dao.insertarEmpleado(empleado);

            if (filas == 1) {

                CookieOperaciones.incrementar(request, response);
                
                request.getSession().setAttribute(
                        "empleadoCreado", empleado.getEmpno()
                );

                response.sendRedirect(
                        request.getContextPath() + "/formulario.jsp"
                );

            } else {

                mostrarError(request, response,
                        "No se ha insertado el empleado.");
            }

        } catch (IllegalArgumentException e) {

            mostrarError(request, response, e.getMessage());

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {

                mostrarError(request, response,
                        "Ya existe un empleado con ese numero.");

            } else {

                getServletContext().log(
                        "Error al crear empleado", e
                );

                mostrarError(request, response,
                        "Error al guardar el empleado.");
            }
        }
    }

    private void mostrarError(HttpServletRequest request,
            HttpServletResponse response,
            String mensaje)
            throws ServletException, IOException {

        request.setAttribute("error", mensaje);

        request.getRequestDispatcher("/formulario.jsp")
                .forward(request, response);
    }
}
