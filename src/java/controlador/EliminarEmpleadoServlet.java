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
import modelo.EmpleadoDAO;

@WebServlet("/eliminarEmpleado")
public class EliminarEmpleadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int empno = Integer.parseInt(
                    request.getParameter("empno")
            );

            EmpleadoDAO dao = new EmpleadoDAO();

            int filas = dao.eliminarEmpleado(empno);

            if (filas == 1) {

                request.getSession().setAttribute(
                        "empleadoEliminado", empno
                );

                response.sendRedirect(
                        request.getContextPath() + "/index.jsp"
                );

            } else if (filas == 0) {

                mostrarError(request, response,
                        "El empleado ya no existe en la bbdd.");

            } else {

                getServletContext().log(
                        "Se eliminaron " + filas + " filas."
                );

                mostrarError(request, response,
                        "Se han eliminado varias filas. "
                        + "Revisa la primary key");
            }

        } catch (NumberFormatException e) {

            mostrarError(request, response,
                    "empno no valido.");

        } catch (SQLException e) {

            getServletContext().log(
                    "Error al eliminar empleado", e
            );

            mostrarError(request, response,
                    "Error al eliminar el empleado.");
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

