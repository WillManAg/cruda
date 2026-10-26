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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

            // 1. RECUPERAR DATOS DEL FORMULARIO
            Integer empno = enteroOpcional(
                    request.getParameter("empno"), "EMPNO");

            String ename = textoOpcional(
                    request.getParameter("ename"));

            String job = textoOpcional(
                    request.getParameter("job"));

            Integer mgr = enteroOpcional(
                    request.getParameter("mgr"), "MGR");

            Date hiredate = fechaOpcional(
                    request.getParameter("hiredate"));

            Integer sal = enteroOpcional(
                    request.getParameter("sal"), "SAL");

            Integer comm = enteroOpcional(
                    request.getParameter("comm"), "COMM");

            Integer deptno = enteroOpcional(
                    request.getParameter("deptno"), "DEPTNO");

            // 2. VALIDACIONES
            if (empno == null) {
                throw new IllegalArgumentException(
                        "El número de empleado es obligatorio.");
            }

            if (ename != null && ename.length() > 10) {
                throw new IllegalArgumentException(
                        "El nombre no puede superar 10 caracteres.");
            }

            if (job != null && job.length() > 9) {
                throw new IllegalArgumentException(
                        "El puesto no puede superar 9 caracteres.");
            }

            // 3. CREAR OBJETO EMPLEADO
            Empleado empleado = new Empleado(
                    empno, ename, job, mgr,
                    hiredate, sal, comm, deptno
            );

            // 4. GUARDAR EN MARIADB
            EmpleadoDAO dao = new EmpleadoDAO();

            int filas = dao.insertarEmpleado(empleado);

            // 5. COMPROBAR RESULTADO
            if (filas == 1) {

                request.getSession().setAttribute(
                        "empleadoCreado", empno
                );

                response.sendRedirect(
                        request.getContextPath() + "/formulario.jsp"
                );

            } else {

                mostrarError(request, response,
                        "No se ha insertado ningún empleado.");
            }

        } catch (IllegalArgumentException e) {

            mostrarError(request, response, e.getMessage());

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {

                mostrarError(request, response,
                        "Ya existe un empleado con ese número.");

            } else {

                getServletContext().log(
                        "Error al insertar empleado", e
                );

                mostrarError(request, response,
                        "Error al guardar. Revisa los datos.");
            }
        }
    }

    // CONVERTIR TEXTO VACÍO A NULL
    private String textoOpcional(String texto) {

        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }

        return texto.trim();
    }

    // CONVERTIR NÚMEROS OPCIONALES
    private Integer enteroOpcional(String texto, String campo) {

        String valor = textoOpcional(texto);

        if (valor == null) {
            return null;
        }

        try {
            return Integer.valueOf(valor);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    campo + " debe ser un número entero válido.");
        }
    }

    // VALIDAR LA FECHA
    private Date fechaOpcional(String texto) {

        String valor = textoOpcional(texto);

        if (valor == null) {
            return null;
        }

        try {

            LocalDate fecha = LocalDate.parse(valor);

            if (fecha.getYear() < 1000 || fecha.getYear() > 9999) {
                throw new IllegalArgumentException(
                        "La fecha debe estar entre los años 1000 y 9999.");
            }

            return Date.valueOf(fecha);

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException(
                    "La fecha introducida no es válida.");
        }
    }

    // DEVOLVER EL ERROR AL FORMULARIO
    private void mostrarError(HttpServletRequest request,
            HttpServletResponse response,
            String mensaje)
            throws ServletException, IOException {

        request.setAttribute("error", mensaje);

        request.getRequestDispatcher("/formulario.jsp")
                .forward(request, response);
    }
}
