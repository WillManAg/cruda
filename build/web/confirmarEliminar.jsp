<%-- 
    Document   : confirmarEliminar
    Created on : 28 oct 2026, 12:07:47
    Author     : william.marin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Empleado"%>

<%!
    private String mostrar(Object valor) {
        if (valor == null) {
            return "—";
        }

        return valor.toString()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>

<%
    Empleado empleado =
            (Empleado) request.getAttribute("empleado");

    if (empleado == null) {
        response.sendRedirect(
                request.getContextPath() + "/index.jsp"
        );
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Confirmar eliminación</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/estilos.css">
</head>

<body>

    <main class="contenedor">

        <header class="cabecera">

            <span class="etiqueta">
                GESTIÓN DE EMPLEADOS
            </span>

            <h1>Eliminar empleado<span class="punto">.</span></h1>

            <p>Revisa si es el usuario que quieres eliminar antes de continuar</p>

        </header>

        <section class="panel">

            <div class="panel-titulo">
                <h2>Datos del empleado</h2>
                <span>EMPNO: <%= empleado.getEmpno() %></span>
            </div>

            <div class="tabla-contenedor">

                <table class="tabla-empleados">

                    <tbody>

                        <tr>
                            <th>EMPNO</th>
                            <td><%= mostrar(empleado.getEmpno()) %></td>
                        </tr>

                        <tr>
                            <th>ENAME</th>
                            <td><%= mostrar(empleado.getEname()) %></td>
                        </tr>

                        <tr>
                            <th>JOB</th>
                            <td><%= mostrar(empleado.getJob()) %></td>
                        </tr>

                        <tr>
                            <th>MGR</th>
                            <td><%= mostrar(empleado.getMgr()) %></td>
                        </tr>

                        <tr>
                            <th>HIREDATE</th>
                            <td><%= mostrar(empleado.getHiredate()) %></td>
                        </tr>

                        <tr>
                            <th>SAL</th>
                            <td><%= mostrar(empleado.getSal()) %></td>
                        </tr>

                        <tr>
                            <th>COMM</th>
                            <td><%= mostrar(empleado.getComm()) %></td>
                        </tr>

                        <tr>
                            <th>DEPTNO</th>
                            <td><%= mostrar(empleado.getDeptno()) %></td>
                        </tr>

                    </tbody>

                </table>

            </div>

            <div class="alerta-error" role="alert">

                <strong>Estás seguro de que quieres despedir a este usuario?!</strong>

                <p>
                    Esta acción es permanente, si un tribunal dictamina despido improcedente, debes volver a registrarlo todo!!
                    No podrás deshacerla!!
                </p>

            </div>

            <div class="formulario-acciones">

                <form action="<%= request.getContextPath() %>/eliminarEmpleado"
                      method="post"
                      onsubmit="return confirm('¿Confirmas la eliminación definitiva del empleado?');">

                    <input type="hidden"
                           name="empno"
                           value="<%= empleado.getEmpno() %>">

                    <button type="submit"
                            class="boton-primario boton-peligro">
                        Sí, eliminar definitivamente
                    </button>

                </form>

                <a href="<%= request.getContextPath() %>/index.jsp"
                   class="boton-secundario">
                    Cancelar
                </a>

            </div>

        </section>

        <footer class="pie">
            <span>Gestión de Empleados · Java + JDBC + MariaDB · BY: William Marin</span>
        </footer>

    </main>

</body>
</html>
