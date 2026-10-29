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

@WebServlet("/modificarEmpleado")
public class ModificarEmpleadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if (!"modificar".equals(request.getParameter("accion"))) {
            response.sendError(400, "Operacion no valida.");
            return;
        }

        try {

            Empleado empleado = EmpleadoFormulario.leer(request);

            EmpleadoDAO dao = new EmpleadoDAO();

            int filas = dao.actualizarEmpleado(empleado);

            if (filas == 1) {

                CookieOperaciones.incrementar(request, response);
                
                request.getSession().setAttribute(
                        "empleadoActualizado", empleado.getEmpno()
                );

                response.sendRedirect(
                        request.getContextPath()
                        + "/buscarEmpleado?accion=modificar&empno="
                        + empleado.getEmpno()
                );

            } else if (filas == 0) {

                mostrarError(request, response,
                        "No se han confirmado cambios. "
                        + "Comprueba que el empleado existe.");

            } else {

                mostrarError(request, response,
                        "Se han modificado varias filas. "
                        + "Revisar la clave primaria EMPNO.");
            }

        } catch (IllegalArgumentException e) {

            mostrarError(request, response, e.getMessage());

        } catch (SQLException e) {

            getServletContext().log(
                    "Error al modificar empleado", e
            );

            mostrarError(request, response,
                    "Error al actualizar empleado.");
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
